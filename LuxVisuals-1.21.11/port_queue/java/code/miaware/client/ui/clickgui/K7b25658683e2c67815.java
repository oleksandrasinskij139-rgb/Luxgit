package code.miaware.client.ui.clickgui;

import code.miaware.api.module.K5c460ed4a42f0797d6;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.system.backend.K04bfb908d9b141e27c;
import code.miaware.api.system.backend.K93d0ef925a2fcdf0d6;
import code.miaware.api.system.backend.Kd9271a319f911decbe;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.other.K7d074a66e20f0c1531;
import code.miaware.client.features.modules.render.K30b126617e809f138b;
import code.miaware.client.features.modules.render.Ke60d35272f9e1d2a55;
import java.util.List;
import net.minecraft.class_1109;
import net.minecraft.class_155;
import net.minecraft.class_437;

final class K7b25658683e2c67815 implements Ke4d698fab638f5cee0 {
   // $FF: synthetic field
   final K78e2c0cb4432df2cca this$0;

   private K7b25658683e2c67815(final K78e2c0cb4432df2cca var1) {
      this.this$0 = var1;
   }

   public List<Kab8a8c478f7d0077cd> modules() {
      return K5c460ed4a42f0797d6.getInstance().getModules();
   }

   public String clientName() {
      return "LuxVisuals";
   }

   public String gameVersion() {
      try {
         return class_155.method_16673().method_48019();
      } catch (Throwable var2) {
         return "1.21.4";
      }
   }

   public String username() {
      try {
         return K93d0ef925a2fcdf0d6.getUsername();
      } catch (Throwable var2) {
         return "Player";
      }
   }

   public String role() {
      try {
         String role = K04bfb908d9b141e27c.getInstance().getRole();
         return role != null && !role.isBlank() ? role : "User";
      } catch (Throwable var2) {
         return "User";
      }
   }

   public String subscriptionText() {
      return "∞";
   }

   public float subscriptionProgress() {
      return 1.0F;
   }

   public String keyName(int key) {
      return Kd9271a319f911decbe.getBind(key);
   }

   public String clipboard() {
      return K4600bef438dafc1a9c.mc.field_1774.method_1460();
   }

   public boolean controlDown() {
      return class_437.method_25441();
   }

   public void playSelect() {
      K7d074a66e20f0c1531.playGuiSound(K7d074a66e20f0c1531.SELECT_EVENT);
   }

   public void playHover() {
      Ke60d35272f9e1d2a55.getInstance().playHoverSound();
   }

   public void playDescriptionKey() {
      Ke60d35272f9e1d2a55 controller = Ke60d35272f9e1d2a55.getInstance();
      if (K4600bef438dafc1a9c.mc.method_1483() != null && controller.isDescriptionSoundEnabled()) {
         class_1109 sound = class_1109.method_4757(K78e2c0cb4432df2cca.DESCRIPTION_KEY_SOUND, 1.0F, controller.getSoundVolume());
         this.this$0.descriptionSounds.add(sound);
         K4600bef438dafc1a9c.mc.method_1483().method_4873(sound);
      }
   }

   public void stopDescriptionSound() {
      if (K4600bef438dafc1a9c.mc.method_1483() != null) {
         for(class_1109 sound : this.this$0.descriptionSounds) {
            K4600bef438dafc1a9c.mc.method_1483().method_4870(sound);
         }
      }

      this.this$0.descriptionSounds.clear();
   }

   public boolean isLocked(Kab8a8c478f7d0077cd module) {
      return module instanceof K30b126617e809f138b;
   }
}
