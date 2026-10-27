package code.miaware.client.features.modules.render.shulkerview;

import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector2f;
import org.joml.Vector3f;

public final class K8234ec92a2fab0a6d2 {
   private K8234ec92a2fab0a6d2() {
   }

   public static @Nullable Vector2f worldToScreen(double x, double y, double z) {
      class_310 mc = class_310.method_1551();
      class_4184 camera = mc.field_1773.method_19418();
      if (camera == null) {
         return null;
      } else {
         class_243 camPos = camera.method_19326();
         Vector3f pos = new Vector3f((float)(x - camPos.field_1352), (float)(y - camPos.field_1351), (float)(z - camPos.field_1350));
         Quaternionf rotation = (new Quaternionf(camera.method_23767())).conjugate();
         pos.rotate(rotation);
         if (pos.z() >= 0.0F) {
            return null;
         } else {
            float halfW = (float)mc.method_22683().method_4486() * 0.5F;
            float halfH = (float)mc.method_22683().method_4502() * 0.5F;
            double fov = (double)(Integer)mc.field_1690.method_41808().method_41753();
            float scale = (float)((double)halfH / ((double)(-pos.z()) * Math.tan(Math.toRadians(fov * (double)0.5F))));
            float screenX = pos.x() * scale + halfW;
            float screenY = halfH - pos.y() * scale;
            return Float.isFinite(screenX) && Float.isFinite(screenY) ? new Vector2f(screenX, screenY) : null;
         }
      }
   }
}
