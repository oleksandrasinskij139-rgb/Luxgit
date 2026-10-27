package code.miaware.client.features.modules.render;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import java.util.Locale;
import lombok.Generated;
import net.minecraft.class_10042;
import net.minecraft.class_10055;

@ModuleRegister(
   name = "SeeInvisibe",
   category = Kf76f042931183af5ba.RENDER,
   description = "Невидимые игроки: HolyWorld и одиночный мир"
)
public final class Kf8178702dbaef55c58 extends Kab8a8c478f7d0077cd {
   private static final Kf8178702dbaef55c58 instance = new Kf8178702dbaef55c58();
   public final Kb9e875ead00e0d03bd visibility = (new Kb9e875ead00e0d03bd("Видимость")).value(5.0F).range(1.0F, 10.0F).step(1.0F);

   public Kf8178702dbaef55c58() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.visibility});
   }

   public void onEvent() {
   }

   public static boolean isHolyWorld(String address) {
      if (address == null) {
         return false;
      } else {
         String host = address.strip().toLowerCase(Locale.ROOT).split(":", 2)[0];

         for(String label : host.split("\\.")) {
            if (label.equals("holyworld")) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean serverAllowed() {
      return mc != null && (mc.method_1542() || mc.method_1558() != null && isHolyWorld(mc.method_1558().field_3761));
   }

   public static boolean shouldReveal(class_10042 state) {
      return instance.isEnabled() && state instanceof class_10055 && state.field_53333 && serverAllowed();
   }

   public static int color(class_10042 state, int original) {
      return shouldReveal(state) ? original & 16777215 | Math.round(Math.clamp(instance.visibility.getValue(), 1.0F, 10.0F) * 25.5F) << 24 : original;
   }

   @Generated
   public static Kf8178702dbaef55c58 getInstance() {
      return instance;
   }
}
