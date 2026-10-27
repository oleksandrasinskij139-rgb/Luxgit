package code.miaware.api.utils.rotation;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import java.util.Optional;
import java.util.function.Predicate;
import lombok.Generated;
import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;

public final class K970216267e60d1d8b5 implements K4600bef438dafc1a9c {
   private static boolean Kfc3214cd488fdf5cf1(class_243 start, class_243 entityPos, class_3965 blockHit) {
      if (blockHit != null && blockHit.method_17783() == class_240.field_1332) {
         if (mc.field_1687 == null) {
            return false;
         } else {
            class_2680 blockState = mc.field_1687.method_8320(blockHit.method_17777());
            if (blockState == null) {
               return false;
            } else if (!blockState.method_26220(mc.field_1687, blockHit.method_17777()).method_1110()) {
               class_243 blockPos = blockHit.method_17784();
               return blockPos.method_1025(start) < entityPos.method_1025(start);
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   public static class_3965 raycast(Keb4a85ca52fb971c57 rotation, double range, boolean includeFluids, float tickDelta) {
      return raycast(range, includeFluids, mc.field_1724.method_5836(tickDelta), rotation.getVector(), mc.field_1719);
   }

   public static class_3965 raycast(double range, boolean includeFluids, class_243 start, class_243 direction, class_1297 entity) {
      class_243 end = start.method_1031(direction.field_1352 * range, direction.field_1351 * range, direction.field_1350 * range);
      return mc.field_1687.method_17742(new class_3959(start, end, class_3960.field_17559, includeFluids ? class_242.field_1347 : class_242.field_1348, entity));
   }

   public static class_3966 raytraceEntity(double range, Keb4a85ca52fb971c57 rotation, boolean ignoreWalls) {
      return raytraceEntity(range, rotation, ignoreWalls, (entity) -> true, 0.0F);
   }

   public static class_3966 raytraceEntity(double range, Keb4a85ca52fb971c57 rotation, boolean ignoreWalls, float expand) {
      return raytraceEntity(range, rotation, ignoreWalls, (entity) -> true, expand);
   }

   public static class_3966 raytraceEntity(double range, Keb4a85ca52fb971c57 rotation, boolean ignoreWalls, Predicate<class_1297> filter, float expand) {
      class_1297 entity = mc.method_1560();
      if (entity != null && mc.field_1687 != null) {
         class_243 cameraVec = entity.method_33571();
         class_243 rotationVec = rotation.getVector();
         class_243 endVec = cameraVec.method_1031(rotationVec.field_1352 * range, rotationVec.field_1351 * range, rotationVec.field_1350 * range);
         double hitboxExpand = (double)Math.max(0.0F, expand);
         class_238 searchBox = entity.method_5829().method_18804(rotationVec.method_1021(range)).method_1014((double)1.0F + hitboxExpand);
         class_3966 entityHit = null;
         double closestDistance = range * range;

         for(class_1297 possibleTarget : mc.field_1687.method_8333(entity, searchBox, (target) -> target.method_5863() && target.method_5805() && !target.method_7325() && filter.test(target))) {
            class_238 targetBox = possibleTarget.method_5829().method_1014((double)possibleTarget.method_5871() + hitboxExpand);
            if (targetBox.method_1006(cameraVec)) {
               entityHit = new class_3966(possibleTarget, cameraVec);
               closestDistance = (double)0.0F;
            } else {
               Optional<class_243> hitPos = targetBox.method_992(cameraVec, endVec);
               if (!hitPos.isEmpty()) {
                  double distanceToHit = cameraVec.method_1025((class_243)hitPos.get());
                  if (!(distanceToHit > closestDistance)) {
                     closestDistance = distanceToHit;
                     entityHit = new class_3966(possibleTarget, (class_243)hitPos.get());
                  }
               }
            }
         }

         if (entityHit == null) {
            return null;
         } else if (ignoreWalls) {
            return entityHit;
         } else {
            class_243 entityPos = entityHit.method_17784();
            class_3965 blockHit = mc.field_1687 != null ? mc.field_1687.method_17742(new class_3959(cameraVec, entityPos, class_3960.field_17558, class_242.field_1347, mc.field_1724)) : null;
            return Kfc3214cd488fdf5cf1(cameraVec, entityPos, blockHit) ? null : entityHit;
         }
      } else {
         return null;
      }
   }

   @Generated
   private K970216267e60d1d8b5() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
