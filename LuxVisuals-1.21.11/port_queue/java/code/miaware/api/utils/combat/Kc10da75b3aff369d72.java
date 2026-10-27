package code.miaware.api.utils.combat;

import code.miaware.api.utils.player.K131e64425767c5c203;
import net.minecraft.class_1294;
import net.minecraft.class_1309;
import net.minecraft.class_1657;

public final class Kc10da75b3aff369d72 {
   private static final float REQUIRED_FALL_DISTANCE = 0.045F;
   private static final double FALLING_VELOCITY_THRESHOLD = -0.08;

   private Kc10da75b3aff369d72() {
   }

   public static boolean canCriticalHitNow(class_1309 entity) {
      return canCriticalHitNow(entity, 0.045F, -0.08);
   }

   public static boolean canCriticalHitNow(class_1309 entity, float requiredFallDistance, double fallingVelocityThreshold) {
      if (entity == null) {
         return false;
      } else {
         return !entity.method_24828() && Kc1c4f4635880344d82(entity, requiredFallDistance, fallingVelocityThreshold) && !isEnvironmentBlocked(entity);
      }
   }

   public static boolean shouldFallbackToNormalHit(class_1309 entity) {
      return entity != null && (Kccd3780f46d3fcbc72(entity) || K85c34386c7d86caf09(entity) || Ke7e68e3f0dc2bfddcf(entity));
   }

   public static boolean isEnvironmentBlocked(class_1309 entity) {
      return Kccd3780f46d3fcbc72(entity) || K85c34386c7d86caf09(entity);
   }

   private static boolean Kccd3780f46d3fcbc72(class_1309 entity) {
      if (entity == null) {
         return true;
      } else {
         boolean var10000;
         label36: {
            if (entity instanceof class_1657) {
               class_1657 player = (class_1657)entity;
               if (player.method_31549().field_7479) {
                  var10000 = true;
                  break label36;
               }
            }

            var10000 = false;
         }

         boolean hasBlockedMovement = var10000;
         return entity.method_6059(class_1294.field_5902) || entity.method_6059(class_1294.field_5906) || K131e64425767c5c203.isInWeb(entity) || entity.method_5771() || entity.method_6101() || entity.method_5765() || entity.method_5740() || hasBlockedMovement;
      }
   }

   private static boolean K85c34386c7d86caf09(class_1309 entity) {
      return entity != null && (entity.method_5799() || entity.method_5869() || entity.method_5681());
   }

   private static boolean Ke7e68e3f0dc2bfddcf(class_1309 entity) {
      if (entity instanceof class_1657 player) {
         int slowness = player.method_6059(class_1294.field_5909) ? player.method_6112(class_1294.field_5909).method_5578() : -1;
         return slowness >= 4 || slowness >= 2 && player.method_6059(class_1294.field_5919);
      } else {
         return false;
      }
   }

   private static boolean Kc1c4f4635880344d82(class_1309 entity, float requiredFallDistance, double fallingVelocityThreshold) {
      return entity.field_6017 >= Math.max(0.0F, requiredFallDistance) && entity.method_18798().field_1351 < fallingVelocityThreshold;
   }
}
