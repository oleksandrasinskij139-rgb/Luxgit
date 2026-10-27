package code.miaware.api.utils.rotation;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import lombok.Generated;
import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public final class Kef2fce79615318605c implements K4600bef438dafc1a9c {
   public static boolean inFov(class_243 vec3d, float fov) {
      Keb4a85ca52fb971c57 rotation = rotationAt(vec3d);
      float deltaYaw = class_3532.method_15393(rotation.getYaw() - mc.field_1724.method_36454());
      float deltaPitch = class_3532.method_15393(rotation.getPitch() - mc.field_1724.method_36455());
      return Math.abs(deltaYaw) <= fov && Math.abs(deltaPitch) <= fov;
   }

   public static Keb4a85ca52fb971c57 rotationAt(class_243 position) {
      if (position != null && mc.field_1724 != null) {
         class_243 playerPos = mc.field_1724.method_19538().method_1031((double)0.0F, (double)mc.field_1724.method_18381(mc.field_1724.method_18376()), (double)0.0F);
         float diffX = (float)(position.field_1352 - playerPos.field_1352);
         float diffY = (float)(position.field_1351 - playerPos.field_1351);
         float diffZ = (float)(position.field_1350 - playerPos.field_1350);
         float dist = (float)Math.sqrt((double)(diffX * diffX + diffZ * diffZ));
         float yaw = (float)(Math.toDegrees(Math.atan2((double)diffZ, (double)diffX)) - (double)90.0F);
         float pitch = (float)(-Math.toDegrees(Math.atan2((double)diffY, (double)dist)));
         return new Keb4a85ca52fb971c57(yaw, pitch);
      } else {
         return Keb4a85ca52fb971c57.DEFAULT;
      }
   }

   public static Keb4a85ca52fb971c57 fromVec2f(class_241 vector2f) {
      return new Keb4a85ca52fb971c57(vector2f.field_1342, vector2f.field_1343);
   }

   public static Keb4a85ca52fb971c57 fromVec3d(class_243 vector) {
      return new Keb4a85ca52fb971c57(class_3532.method_15393((float)Math.toDegrees(Math.atan2(vector.field_1350, vector.field_1352)) - 90.0F), class_3532.method_15393((float)Math.toDegrees(-Math.atan2(vector.field_1351, Math.hypot(vector.field_1352, vector.field_1350)))));
   }

   public static Keb4a85ca52fb971c57 calculateDelta(Keb4a85ca52fb971c57 start, Keb4a85ca52fb971c57 end) {
      float deltaYaw = class_3532.method_15393(end.getYaw() - start.getYaw());
      float deltaPitch = class_3532.method_15393(end.getPitch() - start.getPitch());
      return new Keb4a85ca52fb971c57(deltaYaw, deltaPitch);
   }

   public static class_243 getSpot(class_1297 entity) {
      if (entity == null) {
         return class_243.field_1353;
      } else if (mc.field_1724 == null) {
         return entity.method_5829().method_1005();
      } else {
         class_243 eye = mc.field_1724.method_33571();
         class_238 box = entity.method_5829();
         return new class_243(class_3532.method_15350(eye.field_1352, box.field_1323, box.field_1320), class_3532.method_15350(eye.field_1351, box.field_1322, box.field_1325), class_3532.method_15350(eye.field_1350, box.field_1321, box.field_1324));
      }
   }

   public static class_243 rayCastBox(class_1297 entity, class_243 end) {
      class_238 box = entity.method_5829();
      class_243 start = mc.method_1560().method_5836(1.0F);
      class_243 min = new class_243(box.field_1323, box.field_1322, box.field_1321);
      class_243 max = new class_243(box.field_1320, box.field_1325, box.field_1324);
      double tMin = -Double.MAX_VALUE;
      double tMax = Double.MAX_VALUE;
      class_243 delta = end.method_1020(start);
      if (delta.method_1027() < 1.0E-7) {
         return end;
      } else {
         class_243 direction = delta.method_1029();

         for(int axis = 0; axis < 3; ++axis) {
            double d;
            double minVal;
            double maxVal;
            double startVal;
            switch (axis) {
               case 0:
                  d = direction.field_1352;
                  minVal = min.field_1352;
                  maxVal = max.field_1352;
                  startVal = start.field_1352;
                  break;
               case 1:
                  d = direction.field_1351;
                  minVal = min.field_1351;
                  maxVal = max.field_1351;
                  startVal = start.field_1351;
                  break;
               case 2:
                  d = direction.field_1350;
                  minVal = min.field_1350;
                  maxVal = max.field_1350;
                  startVal = start.field_1350;
                  break;
               default:
                  continue;
            }

            if (Math.abs(d) < 1.0E-7) {
               if (startVal < minVal || startVal > maxVal) {
                  return end;
               }
            } else {
               double t1 = (minVal - startVal) / d;
               double t2 = (maxVal - startVal) / d;
               if (t1 > t2) {
                  double temp = t1;
                  t1 = t2;
                  t2 = temp;
               }

               tMin = Math.max(tMin, t1);
               tMax = Math.min(tMax, t2);
               if (tMin > tMax) {
                  return end;
               }
            }
         }

         double distance = start.method_1022(end);
         if (!(tMin > distance) && !(tMin < (double)0.0F)) {
            return start.method_1019(direction.method_1021(tMin));
         } else {
            return end;
         }
      }
   }

   @Generated
   private Kef2fce79615318605c() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
