package code.miaware.api.utils.render.elytra;

import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import code.miaware.api.event.events.render.Kb590f148cfda70c907;
import java.awt.Color;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_9801;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

public final class K27418430766eb72d11 {
   public void renderRings(K9ee087b2e1791ad6b2 event, K8f299431979bb8cb2f state, float size, float opacity, Color tint) {
      double time = state.time(event.partialTicks());
      if (Ka8710cc9fac660be2e.hasRings(state, time)) {
         try (K54fd1c8282fb3dd387 ignored = new K54fd1c8282fb3dd387(true)) {
            Matrix4f matrix = event.matrixStack().method_23760().method_23761();
            class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);
            class_243 camera = event.cameraPos();
            Ka8710cc9fac660be2e.rings(state, time, camera.field_1352, camera.field_1351, camera.field_1350, size, opacity, tint.getRGB(), (x, y, z, color) -> buffer.method_22918(matrix, x, y, z).method_39415(color));
            class_9801 built = buffer.method_60794();
            if (built != null) {
               class_286.method_43433(built);
            }
         }

      }
   }

   public void renderWind(Kb590f148cfda70c907 event, K8f299431979bb8cb2f state, int width, int height, float opacity, Color tint) {
      double intensity = state.intensity(event.partialTicks());
      if (!(intensity < 0.015)) {
         event.context().method_51452();

         try (K54fd1c8282fb3dd387 ignored = new K54fd1c8282fb3dd387(false)) {
            Matrix4f matrix = event.matrixStack().method_23760().method_23761();
            class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);
            Ka8710cc9fac660be2e.wind(state.time(event.partialTicks()), intensity, width, height, opacity, tint.getRGB(), (x, y, z, color) -> buffer.method_22918(matrix, x, y, z).method_39415(color));
            class_9801 built = buffer.method_60794();
            if (built != null) {
               class_286.method_43433(built);
            }
         }

      }
   }
}
