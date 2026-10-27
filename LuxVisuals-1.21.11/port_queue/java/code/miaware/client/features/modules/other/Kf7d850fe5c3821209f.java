package code.miaware.client.features.modules.other;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.other.K7d074a66e20f0c1531;
import lombok.Generated;
import net.minecraft.class_3414;

@ModuleRegister(
   name = "Toggle Sounds",
   category = Kf76f042931183af5ba.OTHER,
   description = "Звуки при включении/выключении модулей"
)
public class Kf7d850fe5c3821209f extends Kab8a8c478f7d0077cd {
   private static final Kf7d850fe5c3821209f instance = new Kf7d850fe5c3821209f();
   private final Kbd66519397e9a89967 sound = (new Kbd66519397e9a89967("Sound")).value("Blop").values("Smooth", "Celestial", "Nursultan", "Akrien", "Tech", "Blop");
   public final Kb9e875ead00e0d03bd volume = (new Kb9e875ead00e0d03bd("Volume")).value(60.0F).range(1.0F, 100.0F).step(1.0F);

   public Kf7d850fe5c3821209f() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.sound, this.volume});
   }

   public static void playToggle(boolean state) {
      if (instance.isEnabled()) {
         K7d074a66e20f0c1531.playSound(state ? instance.K21a90c735a83f34733() : instance.K7f93f55649b5303555());
      }
   }

   private class_3414 K21a90c735a83f34733() {
      class_3414 var10000;
      switch ((String)this.sound.getValue()) {
         case "Nursultan" -> var10000 = K7d074a66e20f0c1531.ENABLE_NU_EVENT;
         case "Celestial" -> var10000 = K7d074a66e20f0c1531.ENABLE_CEL_EVENT;
         case "Akrien" -> var10000 = K7d074a66e20f0c1531.ENABLE_AK_EVENT;
         case "Tech" -> var10000 = K7d074a66e20f0c1531.ENABLE_TECH_EVENT;
         case "Blop" -> var10000 = K7d074a66e20f0c1531.ENABLE_BLOP_EVENT;
         default -> var10000 = K7d074a66e20f0c1531.ENABLE_SMOOTH_EVENT;
      }

      return var10000;
   }

   private class_3414 K7f93f55649b5303555() {
      class_3414 var10000;
      switch ((String)this.sound.getValue()) {
         case "Nursultan" -> var10000 = K7d074a66e20f0c1531.DISABLE_NU_EVENT;
         case "Celestial" -> var10000 = K7d074a66e20f0c1531.DISABLE_CEL_EVENT;
         case "Akrien" -> var10000 = K7d074a66e20f0c1531.DISABLE_AK_EVENT;
         case "Tech" -> var10000 = K7d074a66e20f0c1531.DISABLE_TECH_EVENT;
         case "Blop" -> var10000 = K7d074a66e20f0c1531.DISABLE_BLOP_EVENT;
         default -> var10000 = K7d074a66e20f0c1531.DISABLE_SMOOTH_EVENT;
      }

      return var10000;
   }

   public void onEvent() {
   }

   @Generated
   public static Kf7d850fe5c3821209f getInstance() {
      return instance;
   }
}
