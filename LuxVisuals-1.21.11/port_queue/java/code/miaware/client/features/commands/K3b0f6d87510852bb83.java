package code.miaware.client.features.commands;

import code.miaware.api.auth.K439206e487150f9ae0;
import code.miaware.api.auth.Ka9b4e2127b25b6f5fd;
import code.miaware.api.command.CommandRegister;
import code.miaware.api.command.Kcba974810ca05b56ea;
import code.miaware.api.system.configs.Kc8165f59ce078825eb;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.yggdrasil.ProfileResult;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.class_2172;
import net.minecraft.class_640;
import net.minecraft.class_8685;

@CommandRegister(
   name = "skin"
)
public class K3b0f6d87510852bb83 extends Kcba974810ca05b56ea {
   public static Supplier<class_8685> customSkinTextures = null;
   public static boolean skinEnabled = false;

   public void execute(LiteralArgumentBuilder<class_2172> builder) {
      builder.then(literal("off").executes((context) -> {
         if (!skinEnabled) {
            this.print("Custom skin is already disabled.");
         } else {
            customSkinTextures = null;
            skinEnabled = false;
            Kc8165f59ce078825eb.getInstance().save((String)null);
            this.print("Custom skin disabled.");
         }

         return this.SINGLE_SUCCESS;
      }));
      builder.then(literal("set").then(argument("name", StringArgumentType.string()).executes((context) -> {
         String username = StringArgumentType.getString(context, "name");
         this.print("Loading skin for: " + username + "...");
         (new Thread(() -> {
            try {
               Supplier<class_8685> textures = createTextureSupplier(username);
               mc.execute(() -> {
                  customSkinTextures = textures;
                  skinEnabled = true;
                  Kc8165f59ce078825eb.getInstance().save(username);
                  this.print("Custom skin set: " + username);
               });
            } catch (Exception e) {
               mc.execute(() -> this.print("Failed to set custom skin: " + e.getMessage()));
            }

         }, "SkinLoader-" + username)).start();
         return this.SINGLE_SUCCESS;
      })));
   }

   public static Supplier<class_8685> createTextureSupplier(String username) {
      UUID uuid = (new Ka9b4e2127b25b6f5fd()).uuidByName(username);
      if (uuid == null) {
         uuid = K439206e487150f9ae0.generateOfflinePlayerUuid(username);
      }

      ProfileResult result = mc.method_1495().fetchProfile(uuid, false);
      GameProfile profile = result == null ? null : result.profile();
      if (profile == null) {
         profile = new GameProfile(uuid, username);
      }

      return class_640.method_52803(profile);
   }

   public static Supplier<class_8685> getCustomSkinTextures() {
      return skinEnabled ? customSkinTextures : null;
   }
}
