package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_310;

@ModuleRegister(
   name = "Figura",
   category = Kf76f042931183af5ba.RENDER,
   description = "Управление Figura аватарами"
)
public class K3a0fe7837c1fb9677c extends Kab8a8c478f7d0077cd {
   private static final K3a0fe7837c1fb9677c instance = new K3a0fe7837c1fb9677c();
   private final Kbd66519397e9a89967 avatarSelect;
   private String lastAppliedAvatar = "";
   private Method loadAvatarMethod;
   private boolean figuraApiResolved;

   public K3a0fe7837c1fb9677c() {
      List<String> avatars = this.K940cf7652f2edb06a2();
      this.avatarSelect = (new Kbd66519397e9a89967("Модель")).value(avatars.isEmpty() ? "Нет моделей" : (String)avatars.get(0)).values((String[])avatars.toArray(new String[0]));
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.avatarSelect});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 tickEvent = Kdee350abc91aa294b2.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (this.isEnabled()) {
            String selectedAvatar = (String)this.avatarSelect.getValue();
            if (selectedAvatar != null && !selectedAvatar.equals("Нет моделей")) {
               if (!selectedAvatar.equals(this.lastAppliedAvatar)) {
                  this.Kb931fc8a6d5de6d057(selectedAvatar);
                  this.lastAppliedAvatar = selectedAvatar;
               }

            }
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{tickEvent});
   }

   public void onEnable() {
      super.onEnable();
      this.lastAppliedAvatar = "";
   }

   public void onDisable() {
      super.onDisable();
      this.K9eeb97a3db817c9c6d();
      this.lastAppliedAvatar = "";
   }

   private void Kb931fc8a6d5de6d057(String avatarName) {
      if (FabricLoader.getInstance().isModLoaded("figura")) {
         try {
            class_310 mc = class_310.method_1551();
            if (mc == null || mc.field_1697 == null) {
               return;
            }

            Path avatarPath = mc.field_1697.toPath().resolve("figura").resolve("avatars").resolve(avatarName).normalize();
            if (!Files.exists(avatarPath, new LinkOption[0]) || !Files.isDirectory(avatarPath, new LinkOption[0])) {
               return;
            }

            if (!this.figuraApiResolved) {
               this.K7da447eaff0eaf0255();
               this.figuraApiResolved = true;
            }

            if (this.loadAvatarMethod != null) {
               this.loadAvatarMethod.invoke((Object)null, avatarPath);
            }
         } catch (Throwable var4) {
         }

      }
   }

   private void K9eeb97a3db817c9c6d() {
      if (FabricLoader.getInstance().isModLoaded("figura")) {
         try {
            if (!this.figuraApiResolved) {
               this.K7da447eaff0eaf0255();
               this.figuraApiResolved = true;
            }

            if (this.loadAvatarMethod != null) {
               this.loadAvatarMethod.invoke((Object)null, null);
            }
         } catch (Throwable var2) {
         }

      }
   }

   private void K7da447eaff0eaf0255() {
      try {
         Class<?> managerClass = Class.forName("org.figuramc.figura.avatar.AvatarManager");

         for(Method method : managerClass.getDeclaredMethods()) {
            if (method.getName().equals("loadLocalAvatar") && method.getParameterCount() == 1 && method.getParameterTypes()[0] == Path.class) {
               method.setAccessible(true);
               this.loadAvatarMethod = method;
               break;
            }
         }
      } catch (Throwable var6) {
      }

   }

   public String getSelectedAvatar() {
      return (String)this.avatarSelect.getValue();
   }

   private List<String> K940cf7652f2edb06a2() {
      return this.K3b31f98e776cd57543();
   }

   private List<String> K3b31f98e776cd57543() {
      List<String> defaults = new ArrayList();

      for(int i = 1; i <= 72; ++i) {
         defaults.add("Tyan-" + i);
      }

      return defaults;
   }

   @Generated
   public static K3a0fe7837c1fb9677c getInstance() {
      return instance;
   }
}
