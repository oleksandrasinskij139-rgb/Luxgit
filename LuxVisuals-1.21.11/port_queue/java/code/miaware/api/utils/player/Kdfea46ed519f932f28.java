package code.miaware.api.utils.player;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.rotation.manager.K1311b30b569cc830b6;
import code.miaware.api.utils.rotation.manager.K960322f580344d3bad;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import lombok.Generated;
import net.minecraft.class_304;
import net.minecraft.class_3675;

public final class Kdfea46ed519f932f28 implements K4600bef438dafc1a9c {
   public static class_304[] getMovementKeys() {
      return new class_304[]{mc.field_1690.field_1867, mc.field_1690.field_1894, mc.field_1690.field_1881, mc.field_1690.field_1913, mc.field_1690.field_1849, mc.field_1690.field_1903};
   }

   public static void updateMovementKeys() {
      for(class_304 movementKey : getMovementKeys()) {
         class_3675.class_306 boundKey = class_3675.method_15981(movementKey.method_1428());
         movementKey.method_23481(class_3675.method_15987(mc.method_22683().method_4490(), boundKey.method_1444()));
      }

   }

   public static boolean w() {
      return mc.field_1690.field_1894.method_1434();
   }

   public static boolean s() {
      return mc.field_1690.field_1881.method_1434();
   }

   public static boolean a() {
      return mc.field_1690.field_1913.method_1434();
   }

   public static boolean d() {
      return mc.field_1690.field_1849.method_1434();
   }

   public static boolean isMoving() {
      return mc.field_1724.field_6250 != 0.0F || mc.field_1724.field_6212 != 0.0F;
   }

   public static double direction(float rotationYaw, float moveForward, float moveStrafing) {
      if (moveForward < 0.0F) {
         rotationYaw += 180.0F;
      }

      float forward = 1.0F;
      if (moveForward < 0.0F) {
         forward = -0.5F;
      }

      if (moveForward > 0.0F) {
         forward = 0.5F;
      }

      if (moveStrafing > 0.0F) {
         rotationYaw -= 90.0F * forward;
      }

      if (moveStrafing < 0.0F) {
         rotationYaw += 90.0F * forward;
      }

      return Math.toRadians((double)rotationYaw);
   }

   public static double[] forward(double speed) {
      K960322f580344d3bad rotationManager = K960322f580344d3bad.getInstance();
      Keb4a85ca52fb971c57 rotation = rotationManager.getRotation();
      K1311b30b569cc830b6 currentRotationPlan = rotationManager.getCurrentRotationPlan();
      float forward = mc.field_1724.field_3913.field_3905;
      float strafe = mc.field_1724.field_3913.field_3907;
      float yaw = currentRotationPlan == null ? mc.field_1724.method_36454() : rotation.getYaw();
      if (forward != 0.0F) {
         if (strafe > 0.0F) {
            yaw += (float)(forward > 0.0F ? -45 : 45);
         } else if (strafe < 0.0F) {
            yaw += (float)(forward > 0.0F ? 45 : -45);
         }

         strafe = 0.0F;
         if (forward > 0.0F) {
            forward = 1.0F;
         } else if (forward < 0.0F) {
            forward = -1.0F;
         }
      }

      double cosStrafe = Math.sin(Math.toRadians((double)(yaw + 90.0F)));
      double sinStrafe = Math.cos(Math.toRadians((double)(yaw + 90.0F)));
      double x = (double)forward * speed * sinStrafe + (double)strafe * speed * cosStrafe;
      double z = (double)forward * speed * cosStrafe - (double)strafe * speed * sinStrafe;
      return new double[]{x, z};
   }

   public static void setSpeed(double speed) {
      double[] forward = forward(speed);
      mc.field_1724.method_18800(forward[0], mc.field_1724.method_18798().field_1351, forward[1]);
   }

   @Generated
   private Kdfea46ed519f932f28() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
