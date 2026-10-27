package code.miaware.api.utils.render;

import code.miaware.client.features.modules.render.K45978f900e11d6615f;
import lombok.Generated;
import net.minecraft.class_243;
import net.minecraft.class_310;

public final class K6a0793be7dc20da75c {
   public static boolean isInRenderDistance(class_243 pos) {
      if (!K45978f900e11d6615f.getInstance().isFrustumCullingEnabled()) {
         return true;
      } else {
         class_310 mc = class_310.method_1551();
         if (mc.field_1773 != null && mc.field_1773.method_19418() != null) {
            class_243 cameraPos = mc.field_1773.method_19418().method_19326();
            double distSq = pos.method_1025(cameraPos);
            double renderDistSq = (double)((Integer)mc.field_1690.method_42503().method_41753() * 16);
            renderDistSq *= renderDistSq;
            return distSq <= renderDistSq;
         } else {
            return true;
         }
      }
   }

   public static boolean isBoxInRenderDistance(class_243 center, double radius) {
      if (!K45978f900e11d6615f.getInstance().isFrustumCullingEnabled()) {
         return true;
      } else {
         class_310 mc = class_310.method_1551();
         if (mc.field_1773 != null && mc.field_1773.method_19418() != null) {
            class_243 cameraPos = mc.field_1773.method_19418().method_19326();
            double distSq = center.method_1025(cameraPos);
            double renderDistSq = (double)((Integer)mc.field_1690.method_42503().method_41753() * 16);
            renderDistSq *= renderDistSq;
            return distSq <= renderDistSq + radius * radius;
         } else {
            return true;
         }
      }
   }

   public static boolean isSphereInRenderDistance(class_243 center, double radius) {
      return isBoxInRenderDistance(center, radius);
   }

   public static int getLODLevel(class_243 pos) {
      if (!K45978f900e11d6615f.getInstance().isLODSystemEnabled()) {
         return 0;
      } else {
         class_310 mc = class_310.method_1551();
         if (mc.field_1773 != null && mc.field_1773.method_19418() != null) {
            double distSq = pos.method_1025(mc.field_1773.method_19418().method_19326());
            if (distSq > (double)1600.0F) {
               return 2;
            } else {
               return distSq > (double)400.0F ? 1 : 0;
            }
         } else {
            return 0;
         }
      }
   }

   public static int getCircleStepForLOD(int lodLevel) {
      byte var10000;
      switch (lodLevel) {
         case 0 -> var10000 = 5;
         case 1 -> var10000 = 7;
         case 2 -> var10000 = 10;
         default -> var10000 = 5;
      }

      return var10000;
   }

   public static boolean isInFrustum(class_243 pos) {
      return isInRenderDistance(pos);
   }

   public static boolean isBoxInFrustum(class_243 center, double radius) {
      return isBoxInRenderDistance(center, radius);
   }

   public static boolean isSphereInFrustum(class_243 center, double radius) {
      return isSphereInRenderDistance(center, radius);
   }

   @Generated
   private K6a0793be7dc20da75c() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
