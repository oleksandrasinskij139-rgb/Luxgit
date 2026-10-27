package code.miaware.client.features.cosmetics;

import net.minecraft.class_4587;
import net.minecraft.class_630;
import net.minecraft.class_7833;

final class Ka4846d078fcf91c1d3 {
   private Ka4846d078fcf91c1d3() {
   }

   static void apply(class_4587 matrices, class_630 part, Ka5dfe6b89219e29d09 entry) {
      part.method_22703(matrices);
      matrices.method_22907(class_7833.field_40718.rotationDegrees(180.0F));
      float bodyOffset = entry.pos == 2 ? 0.0F : -0.3F;
      matrices.method_46416(entry.x, entry.y + bodyOffset, entry.z);
      if (entry.yaw != 0.0F) {
         matrices.method_22907(class_7833.field_40716.rotationDegrees(entry.yaw));
      }

      if (entry.pitch != 0.0F) {
         matrices.method_22907(class_7833.field_40714.rotationDegrees(entry.pitch));
      }

      if (entry.roll != 0.0F) {
         matrices.method_22907(class_7833.field_40718.rotationDegrees(entry.roll));
      }

      matrices.method_22905(entry.scale, entry.scale, entry.scale);
   }
}
