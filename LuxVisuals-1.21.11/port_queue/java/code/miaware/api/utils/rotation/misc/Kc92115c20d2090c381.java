package code.miaware.api.utils.rotation.misc;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import code.miaware.api.utils.rotation.Kef2fce79615318605c;
import code.miaware.api.utils.rotation.manager.K960322f580344d3bad;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Generated;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;

public final class Kc92115c20d2090c381 implements K4600bef438dafc1a9c {
   private static class_243 dvdPoint;
   private static class_243 dvdMotion;
   private static float hitCount;
   private static Kf39dc27225f4d9fe6e attackTimer;
   private static int adaptiveTicks;
   private static int adaptiveHoldTicks;
   private static int lastAdaptiveIndex;
   private static class_243 lastAdaptivePoint;

   public static void onAttack(String mode) {
      switch (mode) {
         case "SpookyTime":
         case "Spooky Time":
         case "SpookyDuel":
         case "Spooky Duel":
            float hits = 0.3F;
            hitCount += hits;
            if (hitCount >= hits * 2.0F) {
               hitCount = -hits;
            }
            break;
         case "LonyGrief":
         case "Lony Grief":
            attackTimer.reset();
            break;
         default:
            ++hitCount;
            if (hitCount >= 3.0F) {
               hitCount = 0.0F;
            }
      }

   }

   public static class_243 getAimpoint(class_1309 entity, String mode) {
      return getAimpoint(entity, mode, "Default", 0.0F);
   }

   public static class_243 getAimpoint(class_1309 entity, String mode, String pointMode, float hitboxExpand) {
      if (entity == null) {
         return class_243.field_1353;
      } else {
         class_243 var10000;
         switch (mode) {
            case "HolyWorld":
            case "Holy World":
               var10000 = getDvDPoint(entity);
               break;
            case "SpookyTime":
            case "Spooky Time":
            case "SpookyDuel":
            case "Spooky Duel":
               var10000 = getSpookyTimePoint(entity);
               break;
            case "LonyGrief":
            case "Lony Grief":
            case "FunTime":
            case "Fun Time":
               var10000 = getDistanceBasedPoint(entity);
               break;
            default:
               var10000 = Kef2fce79615318605c.getSpot(entity);
         }

         class_243 basePoint = var10000;
         switch (pointMode) {
            case "Closest" -> var10000 = Kef2fce79615318605c.getSpot(entity);
            case "Multipoint" -> var10000 = K1bd51d4b518a98b436(entity, basePoint, hitboxExpand);
            case "Adaptive" -> var10000 = K5bd42c6ec642482c84(entity, basePoint, hitboxExpand);
            default -> var10000 = basePoint;
         }

         return var10000;
      }
   }

   private static class_243 K5bd42c6ec642482c84(class_1297 entity, class_243 fallbackPoint, float hitboxExpand) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         class_238 box = entity.method_5829().method_1014((double)(Math.max(0.0F, hitboxExpand) * 0.5F));
         double centerX = (box.field_1323 + box.field_1320) * (double)0.5F;
         double centerZ = (box.field_1321 + box.field_1324) * (double)0.5F;
         double shoulderY = class_3532.method_16436(0.82, box.field_1322, box.field_1325);
         double chestY = class_3532.method_16436(0.7, box.field_1322, box.field_1325);
         double waistY = class_3532.method_16436(0.52, box.field_1322, box.field_1325);
         double headY = class_3532.method_16436(entity.method_5715() ? 0.78 : 0.9, box.field_1322, box.field_1325);
         double kneeY = class_3532.method_16436(0.32, box.field_1322, box.field_1325);
         double insetX = Math.max(0.03, box.method_17939() * 0.24);
         double insetZ = Math.max(0.03, box.method_17941() * 0.24);
         class_243 eyePos = mc.field_1724.method_33571();
         double distance = eyePos.method_1022(entity.method_19538());
         class_243 closestPoint = Kef2fce79615318605c.getSpot(entity);
         class_243 distancePoint = getDistanceBasedPoint(entity);
         class_243 multipoint = K1bd51d4b518a98b436(entity, fallbackPoint, hitboxExpand);
         List<class_243> points = new ArrayList();
         points.add(closestPoint);
         points.add(distancePoint);
         points.add(multipoint);
         points.add(new class_243(box.field_1323 + insetX, headY, centerZ));
         points.add(new class_243(box.field_1320 - insetX, headY, centerZ));
         points.add(new class_243(box.field_1323 + insetX, shoulderY, centerZ));
         points.add(new class_243(box.field_1320 - insetX, shoulderY, centerZ));
         points.add(new class_243(box.field_1323 + insetX, chestY, centerZ));
         points.add(new class_243(box.field_1320 - insetX, chestY, centerZ));
         points.add(new class_243(centerX, chestY, box.field_1321 + insetZ));
         points.add(new class_243(centerX, chestY, box.field_1324 - insetZ));
         points.add(new class_243(box.field_1323 + insetX, waistY, centerZ));
         points.add(new class_243(box.field_1320 - insetX, waistY, centerZ));
         points.add(new class_243(centerX, waistY, box.field_1321 + insetZ));
         points.add(new class_243(centerX, waistY, box.field_1324 - insetZ));
         points.add(new class_243(box.field_1323 + insetX, kneeY, centerZ));
         points.add(new class_243(box.field_1320 - insetX, kneeY, centerZ));
         points.add(new class_243(centerX, kneeY, box.field_1321 + insetZ));
         points.add(new class_243(centerX, kneeY, box.field_1324 - insetZ));
         List<class_243> sorted = points.stream().sorted(Comparator.comparingDouble((point) -> K6b6b40afc8230a8778(entity, point, distance))).toList();
         if (!lastAdaptivePoint.equals(class_243.field_1353) && adaptiveTicks++ < adaptiveHoldTicks) {
            return K8fcd2070a9d62b0999(Kd7c47fb64069b338b0(lastAdaptivePoint, box), box);
         } else {
            int limit = Math.min(6, sorted.size());
            int index = limit <= 1 ? 0 : ThreadLocalRandom.current().nextInt(limit);
            if (limit > 1 && index == lastAdaptiveIndex) {
               index = (index + 1 + ThreadLocalRandom.current().nextInt(limit - 1)) % limit;
            }

            lastAdaptiveIndex = index;
            adaptiveTicks = 0;
            adaptiveHoldTicks = ThreadLocalRandom.current().nextInt(2, 4);
            lastAdaptivePoint = sorted.isEmpty() ? fallbackPoint : (class_243)sorted.get(index);
            return K8fcd2070a9d62b0999(Kd7c47fb64069b338b0(lastAdaptivePoint, box), box);
         }
      } else {
         return fallbackPoint;
      }
   }

   private static class_243 Kd7c47fb64069b338b0(class_243 point, class_238 box) {
      double width = Math.max(box.method_17939(), box.method_17941());
      double horizontal = Math.min(0.045, width * 0.08);
      double vertical = Math.min(0.035, box.method_17940() * 0.025);
      return point.method_1031(ThreadLocalRandom.current().nextDouble(-horizontal, horizontal), ThreadLocalRandom.current().nextDouble(-vertical, vertical), ThreadLocalRandom.current().nextDouble(-horizontal, horizontal));
   }

   private static class_243 K8fcd2070a9d62b0999(class_243 point, class_238 box) {
      double safeX = Math.max(0.015, box.method_17939() * 0.05);
      double safeZ = Math.max(0.015, box.method_17941() * 0.05);
      return new class_243(class_3532.method_15350(point.field_1352, box.field_1323 + safeX, box.field_1320 - safeX), class_3532.method_15350(point.field_1351, box.field_1322 + 0.04, box.field_1325 - 0.04), class_3532.method_15350(point.field_1350, box.field_1321 + safeZ, box.field_1324 - safeZ));
   }

   private static double K6b6b40afc8230a8778(class_1297 entity, class_243 point, double distance) {
      class_243 eyePos = mc.field_1724.method_33571();
      boolean lineOfSight = K9dcd6cbc06fa7a4400(entity, point);
      Keb4a85ca52fb971c57 currentRotation = K960322f580344d3bad.getInstance().getRotation();
      Keb4a85ca52fb971c57 pointRotation = Kef2fce79615318605c.rotationAt(point);
      float yawDiff = Math.abs(class_3532.method_15393(pointRotation.getYaw() - currentRotation.getYaw()));
      float pitchDiff = Math.abs(class_3532.method_15393(pointRotation.getPitch() - currentRotation.getPitch()));
      double verticalPenalty = Math.abs(point.field_1351 - Kef2fce79615318605c.getSpot(entity).field_1351) * (distance > 3.7 ? 0.65 : (double)0.25F);
      double lineOfSightPenalty = lineOfSight ? (double)0.0F : (double)22.0F;
      double closenessPenalty = eyePos.method_1025(point) * 0.035;
      return (double)yawDiff + (double)pitchDiff * 0.35 + verticalPenalty + lineOfSightPenalty + closenessPenalty;
   }

   private static class_243 K1bd51d4b518a98b436(class_1297 entity, class_243 fallbackPoint, float hitboxExpand) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         class_238 box = entity.method_5829().method_1014((double)(Math.max(0.0F, hitboxExpand) * 0.5F));
         double centerX = (box.field_1323 + box.field_1320) * (double)0.5F;
         double centerY = (box.field_1322 + box.field_1325) * (double)0.5F;
         double centerZ = (box.field_1321 + box.field_1324) * (double)0.5F;
         double chestY = class_3532.method_16436(0.72, box.field_1322, box.field_1325);
         double headY = class_3532.method_16436(0.92, box.field_1322, box.field_1325);
         double legY = class_3532.method_16436(0.35, box.field_1322, box.field_1325);
         double insetX = Math.max(0.02, box.method_17939() * 0.18);
         double insetZ = Math.max(0.02, box.method_17941() * 0.18);
         List<class_243> points = new ArrayList();
         points.add(fallbackPoint);
         points.add(new class_243(centerX, centerY, centerZ));
         points.add(new class_243(centerX, chestY, centerZ));
         points.add(new class_243(centerX, headY, centerZ));
         points.add(new class_243(centerX, legY, centerZ));
         points.add(new class_243(box.field_1323 + insetX, chestY, centerZ));
         points.add(new class_243(box.field_1320 - insetX, chestY, centerZ));
         points.add(new class_243(centerX, chestY, box.field_1321 + insetZ));
         points.add(new class_243(centerX, chestY, box.field_1324 - insetZ));
         points.add(new class_243(box.field_1323 + insetX, centerY, box.field_1321 + insetZ));
         points.add(new class_243(box.field_1323 + insetX, centerY, box.field_1324 - insetZ));
         points.add(new class_243(box.field_1320 - insetX, centerY, box.field_1321 + insetZ));
         points.add(new class_243(box.field_1320 - insetX, centerY, box.field_1324 - insetZ));
         return (class_243)points.stream().min(Comparator.comparingDouble((point) -> Kbc7e3d8bd66b76ad92(entity, point, fallbackPoint))).orElse(fallbackPoint);
      } else {
         return fallbackPoint;
      }
   }

   private static double Kbc7e3d8bd66b76ad92(class_1297 entity, class_243 point, class_243 fallbackPoint) {
      class_243 eyePos = mc.field_1724.method_33571();
      boolean lineOfSight = K9dcd6cbc06fa7a4400(entity, point);
      Keb4a85ca52fb971c57 currentRotation = K960322f580344d3bad.getInstance().getRotation();
      Keb4a85ca52fb971c57 pointRotation = Kef2fce79615318605c.rotationAt(point);
      float yawDiff = Math.abs(class_3532.method_15393(pointRotation.getYaw() - currentRotation.getYaw()));
      float pitchDiff = Math.abs(class_3532.method_15393(pointRotation.getPitch() - currentRotation.getPitch()));
      double rayPenalty = lineOfSight ? (double)0.0F : (double)45.0F;
      double distancePenalty = eyePos.method_1025(point) * 0.025;
      double fallbackPenalty = point.method_1025(fallbackPoint) * 0.18;
      double verticalPenalty = Math.abs(point.field_1351 - Kef2fce79615318605c.getSpot(entity).field_1351) * 0.2;
      return rayPenalty + (double)yawDiff + (double)pitchDiff * 0.42 + distancePenalty + fallbackPenalty + verticalPenalty;
   }

   private static boolean K9dcd6cbc06fa7a4400(class_1297 entity, class_243 point) {
      class_243 eyePos = mc.field_1724.method_33571();
      class_239 hitResult = mc.field_1687.method_17742(new class_3959(eyePos, point, class_3960.field_17558, class_242.field_1348, mc.field_1724));
      return hitResult.method_17783() == class_240.field_1333 ? true : entity.method_5829().method_1014(0.05).method_1006(hitResult.method_17784());
   }

   public static class_243 getBestVector(class_1297 entity, float jitterOnBoxValue) {
      double yExpand = (double)(class_3532.method_15363(mc.field_1724.method_18381(mc.field_1724.method_18376()) - entity.method_18381(entity.method_18376()), entity.method_17682() / 2.0F, entity.method_17682()) / (mc.field_1724.method_6128() ? 10.0F : (!mc.field_1690.field_1903.method_1434() && mc.field_1724.method_24828() ? (entity.method_5715() ? 0.8F : 0.6F) : 1.0F)));
      class_243 finalVector = entity.method_19538().method_1031((double)0.0F, yExpand, (double)0.0F);
      return finalVector.method_1031((double)jitterOnBoxValue, (double)(jitterOnBoxValue / 2.0F), (double)jitterOnBoxValue);
   }

   public static class_243 getSpookyTimePoint(class_1297 entity) {
      float safe = 0.06F;
      float horValue = entity.method_17681() / 2.0F * hitCount;
      float verValue = entity.method_17682() / 4.0F * (hitCount + 1.0F);
      class_238 box = entity.method_5829();
      class_243 best = getBestVector(entity, 0.0F);
      class_243 point = new class_243(class_3532.method_15350(best.field_1352 - (double)horValue, box.field_1323 + (double)safe, box.field_1320 - (double)safe), class_3532.method_15350(best.field_1351 - (double)verValue, box.field_1322 + (double)safe, box.field_1325 - (double)safe), class_3532.method_15350(best.field_1350 + (double)horValue, box.field_1321 + (double)safe, box.field_1324 - (double)safe));
      return point;
   }

   public static class_243 getDistanceBasedPoint(class_1297 entity) {
      class_243 eye = mc.field_1724.method_33571();
      class_238 box = entity.method_5829();
      float attackDistance = 3.0F;
      float distanceFactor = (float)(mc.field_1724.method_19538().method_1022(entity.method_19538()) / (double)attackDistance);
      float minY = (float)(box.field_1325 - box.field_1322);
      float clampedY = class_3532.method_15363((float)(box.field_1322 + (double)(minY * distanceFactor)), (float)(box.field_1322 + (double)(minY * 0.3F)), (float)box.field_1325);
      float safePoint = entity.method_17681() * 0.4F;
      class_243 basePoint = new class_243(class_3532.method_15350(eye.field_1352, box.field_1323 + (double)safePoint, box.field_1320 - (double)safePoint), class_3532.method_15350(eye.field_1351, box.field_1322, (double)clampedY), class_3532.method_15350(eye.field_1350, box.field_1321 + (double)safePoint, box.field_1324 - (double)safePoint));
      return basePoint;
   }

   public static class_243 getDvDPoint(class_1297 entity) {
      float minMotionXZ = 0.003F;
      float maxMotionXZ = 0.04F;
      float minMotionY = 0.001F;
      float maxMotionY = 0.03F;
      double lengthX = entity.method_5829().method_17939();
      double lengthY = entity.method_5829().method_17940() * (double)0.8F;
      double lengthZ = entity.method_5829().method_17941();
      if (dvdMotion.equals(class_243.field_1353)) {
         dvdMotion = new class_243((double)Ka0484a23398fb5f9ac.randomInRange(-0.05F, 0.05F), (double)Ka0484a23398fb5f9ac.randomInRange(-0.05F, 0.05F), (double)Ka0484a23398fb5f9ac.randomInRange(-0.05F, 0.05F));
      }

      dvdPoint = dvdPoint.method_1019(dvdMotion);
      if (dvdPoint.field_1352 >= (lengthX - 0.05) / (double)2.0F) {
         dvdMotion = new class_243((double)(-Ka0484a23398fb5f9ac.randomInRange(minMotionXZ, maxMotionXZ)), dvdMotion.method_10214(), dvdMotion.method_10215());
      }

      if (dvdPoint.field_1351 >= lengthY) {
         dvdMotion = new class_243(dvdMotion.method_10216(), (double)(-Ka0484a23398fb5f9ac.randomInRange(minMotionY, maxMotionY)), dvdMotion.method_10215());
      }

      if (dvdPoint.field_1350 >= (lengthZ - 0.05) / (double)2.0F) {
         dvdMotion = new class_243(dvdMotion.method_10216(), dvdMotion.method_10214(), (double)(-Ka0484a23398fb5f9ac.randomInRange(minMotionXZ, maxMotionXZ)));
      }

      if (dvdPoint.field_1352 <= -(lengthX - 0.05) / (double)2.0F) {
         dvdMotion = new class_243((double)Ka0484a23398fb5f9ac.randomInRange(minMotionXZ, 0.03F), dvdMotion.method_10214(), dvdMotion.method_10215());
      }

      if (dvdPoint.field_1351 <= 0.05) {
         dvdMotion = new class_243(dvdMotion.method_10216(), (double)Ka0484a23398fb5f9ac.randomInRange(minMotionY, maxMotionY), dvdMotion.method_10215());
      }

      if (dvdPoint.field_1350 <= -(lengthZ - 0.05) / (double)2.0F) {
         dvdMotion = new class_243(dvdMotion.method_10216(), dvdMotion.method_10214(), (double)Ka0484a23398fb5f9ac.randomInRange(minMotionXZ, maxMotionXZ));
      }

      dvdPoint = dvdPoint.method_1031((double)Ka0484a23398fb5f9ac.randomInRange(-0.03F, 0.03F), (double)0.0F, (double)Ka0484a23398fb5f9ac.randomInRange(-0.03F, 0.03F));
      class_243 dvdPointed = entity.method_19538().method_1019(dvdPoint);
      class_238 box = entity.method_5829();
      return new class_243(class_3532.method_15350(dvdPointed.field_1352, box.field_1323, box.field_1320), class_3532.method_15350(dvdPointed.field_1351, box.field_1322, box.field_1325), class_3532.method_15350(dvdPointed.field_1350, box.field_1321, box.field_1324));
   }

   @Generated
   private Kc92115c20d2090c381() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   static {
      dvdPoint = class_243.field_1353;
      dvdMotion = class_243.field_1353;
      hitCount = 0.0F;
      attackTimer = new Kf39dc27225f4d9fe6e();
      lastAdaptiveIndex = -1;
      lastAdaptivePoint = class_243.field_1353;
   }
}
