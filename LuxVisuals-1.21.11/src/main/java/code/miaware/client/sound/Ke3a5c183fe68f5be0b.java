package code.miaware.client.sound;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_3532;

@Environment(EnvType.CLIENT)
public class Ke3a5c183fe68f5be0b {
   private static final double TO_DEGREES = (180D / Math.PI);

   public static class_241 calculate(class_243 from, class_243 to) {
      class_243 diff = to.method_1020(from);
      double distance = Math.hypot(diff.field_1352, diff.field_1350);
      float yaw = (float)(class_3532.method_15349(diff.field_1350, diff.field_1352) * (180D / Math.PI)) - 90.0F;
      float pitch = (float)(-(class_3532.method_15349(diff.field_1351, distance) * (180D / Math.PI)));
      return new class_241(yaw, pitch);
   }

   public static float getAngleDifference(float dir, float yaw) {
      float f = Math.abs(yaw - dir) % 360.0F;
      return f > 180.0F ? 360.0F - f : f;
   }

   public static double easeInOutExpo(double x) {
      if (x != (double)0.0F && x != (double)1.0F) {
         return x < (double)0.5F ? Math.pow((double)2.0F, (double)20.0F * x - (double)10.0F) / (double)2.0F : ((double)2.0F - Math.pow((double)2.0F, (double)-20.0F * x + (double)10.0F)) / (double)2.0F;
      } else {
         return x;
      }
   }
}
