package code.miaware.api.utils.math;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import lombok.Generated;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_742;
import net.minecraft.class_7833;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector2f;
import org.joml.Vector3f;

public final class K038ab8652c7f91cee6 implements K4600bef438dafc1a9c {
   private static float previousSpeed = 0.0F;
   private static float horizontalSpeed = 0.0F;

   public static Vector2f project(@NotNull class_243 vec3d) {
      return project(vec3d.method_10216(), vec3d.method_10214(), vec3d.method_10215());
   }

   public static Vector2f project(double x, double y, double z) {
      class_4184 camera = mc.method_1561().field_4686;
      class_243 cameraPos = camera.method_19326();
      Quaternionf yawQuat = class_7833.field_40716.rotationDegrees(-camera.method_19330());
      Quaternionf pitchQuat = class_7833.field_40714.rotationDegrees(camera.method_19329());
      Quaternionf cameraRotation = yawQuat.mul(pitchQuat, new Quaternionf());
      cameraRotation = cameraRotation.conjugate(new Quaternionf());
      Vector3f result3f = new Vector3f((float)(cameraPos.method_10216() - x), (float)(cameraPos.method_10214() - y), (float)(cameraPos.method_10215() - z));
      result3f.rotate(cameraRotation);
      if ((Boolean)mc.field_1690.method_42448().method_41753()) {
         class_1297 var13 = mc.method_1560();
         if (var13 instanceof class_742) {
            class_742 p = (class_742)var13;
            Kb477cc73846042c5e6((class_742)mc.method_1560(), result3f);
         }
      }

      float fov = mc.field_1773.method_3196(camera, mc.method_61966().method_60637(false), true);
      return Ke685cedab510ee424b(result3f, (double)fov);
   }

   private static void Kb477cc73846042c5e6(class_742 player, Vector3f result3f) {
      float tickDelta = mc.method_61966().method_60637(false);
      float distanceMoved = player.field_53039;
      float lastDistanceMoved = player.field_53038;
      float distanceDelta = distanceMoved - lastDistanceMoved;
      float g = -(distanceMoved + distanceDelta * tickDelta);
      float strideDistance = class_3532.method_16439(tickDelta, player.field_7505, player.field_7483);
      float translateX = class_3532.method_15374(g * (float)Math.PI) * strideDistance * 0.5F;
      float translateY = -Math.abs(class_3532.method_15362(g * (float)Math.PI) * strideDistance);
      float rotateZ = class_3532.method_15374(g * (float)Math.PI) * strideDistance * 3.0F;
      float rotateX = Math.abs(class_3532.method_15362(g * (float)Math.PI - 0.2F) * strideDistance) * 5.0F;
      float rotateZRad = rotateZ * ((float)Math.PI / 180F);
      float rotateXRad = rotateX * ((float)Math.PI / 180F);
      if (rotateZRad != 0.0F) {
         Quaternionf zRot = (new Quaternionf()).setAngleAxis(rotateZRad, 0.0F, 0.0F, 1.0F).conjugate();
         result3f.rotate(zRot);
      }

      if (rotateXRad != 0.0F) {
         Quaternionf xRot = (new Quaternionf()).setAngleAxis(rotateXRad, 1.0F, 0.0F, 0.0F).conjugate();
         result3f.rotate(xRot);
      }

      result3f.add(translateX, -translateY, 0.0F);
   }

   private static Vector2f Ke685cedab510ee424b(Vector3f result3f, double fov) {
      float width = (float)mc.method_22683().method_4486() / 2.0F;
      float height = (float)mc.method_22683().method_4502() / 2.0F;
      float x = result3f.x;
      float y = result3f.y;
      float z = result3f.z;
      double scaleFactor = (double)height / ((double)z * Math.tan(Math.toRadians(fov / (double)2.0F)));
      return z < 0.0F ? new Vector2f((float)((double)(-x) * scaleFactor + (double)width), (float)((double)height - (double)y * scaleFactor)) : new Vector2f(Float.MAX_VALUE, Float.MAX_VALUE);
   }

   @Generated
   private K038ab8652c7f91cee6() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
