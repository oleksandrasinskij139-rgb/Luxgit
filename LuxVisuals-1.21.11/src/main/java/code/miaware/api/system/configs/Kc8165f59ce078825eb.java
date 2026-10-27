package code.miaware.api.system.configs;

import code.miaware.api.system.backend.K93d0ef925a2fcdf0d6;
import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import code.miaware.client.features.commands.K3b0f6d87510852bb83;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.Generated;

public class Kc8165f59ce078825eb {
   private static final Kc8165f59ce078825eb instance = new Kc8165f59ce078825eb();
   private final Path configPath;
   private final Kf39dc27225f4d9fe6e timerUtil;
   private String cachedSkinName;
   private String lastAppliedSkinName;
   private long lastConfigReadMs;

   public Kc8165f59ce078825eb() {
      this.configPath = Paths.get(K93d0ef925a2fcdf0d6.CONFIG_PATH_OTHER, "last_skin");
      this.timerUtil = new Kf39dc27225f4d9fe6e();
      this.cachedSkinName = null;
      this.lastAppliedSkinName = null;
      this.lastConfigReadMs = 0L;
   }

   public void load() {
      try {
         if (!Files.exists(this.configPath.getParent(), new LinkOption[0])) {
            Files.createDirectories(this.configPath.getParent());
         }

         if (!Files.exists(this.configPath, new LinkOption[0])) {
            Files.createFile(this.configPath);
            Files.writeString(this.configPath, "");
         }
      } catch (IOException e) {
         e.printStackTrace();
      }

      this.update();
   }

   public void save(String skinName) {
      try {
         if (skinName != null && !skinName.trim().isEmpty()) {
            Files.writeString(this.configPath, skinName.trim());
         } else {
            Files.writeString(this.configPath, "");
         }
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public String update() {
      long now = System.currentTimeMillis();
      if (now - this.lastConfigReadMs < 1500L) {
         return this.cachedSkinName;
      } else {
         this.lastConfigReadMs = now;

         try {
            if (Files.exists(this.configPath, new LinkOption[0])) {
               String content = Files.readString(this.configPath);
               boolean em = content.isEmpty();
               if (!em) {
                  K3b0f6d87510852bb83.skinEnabled = true;
               }

               this.cachedSkinName = em ? null : content.trim();
               return this.cachedSkinName;
            }
         } catch (IOException e) {
            e.printStackTrace();
         }

         this.cachedSkinName = null;
         return null;
      }
   }

   public void fetchSkin() {
      if (K3b0f6d87510852bb83.skinEnabled && this.timerUtil.finished(2000L)) {
         String skinName = this.update();
         if (skinName != null && !skinName.isEmpty() && !skinName.equals(this.lastAppliedSkinName)) {
            K3b0f6d87510852bb83.customSkinTextures = K3b0f6d87510852bb83.createTextureSupplier(skinName);
            this.lastAppliedSkinName = skinName;
         }

         this.timerUtil.reset();
      }

   }

   @Generated
   public static Kc8165f59ce078825eb getInstance() {
      return instance;
   }
}
