package code.miaware.client.features.modules.render.prediction;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_243;

public final class K602c8c3c069aad5074 {
   private K602c8c3c069aad5074() {
   }

   public static class_243 origin(class_243 eye, class_243 look, float yaw, boolean rightHanded, boolean offHand, boolean firstPerson) {
      double radians = Math.toRadians((double)yaw);
      class_243 right = new class_243(-Math.cos(radians), (double)0.0F, -Math.sin(radians));
      class_243 up = right.method_1036(look).method_1029();
      int side = (rightHanded ? 1 : -1) * (offHand ? -1 : 1);
      return eye.method_1019(right.method_1021(0.3 * (double)side)).method_1020(up.method_1021(firstPerson ? 0.24 : 0.36)).method_1019(look.method_1021(firstPerson ? 0.6 : 0.15));
   }

   public static class_243 inheritedMovement(class_243 movement, boolean onGround, boolean crossbow) {
      return crossbow ? class_243.field_1353 : new class_243(movement.field_1352, onGround ? (double)0.0F : movement.field_1351, movement.field_1350);
   }

   public static List<class_243> create(List<class_243> physics, class_243 hand) {
      if (physics.size() < 2) {
         return List.copyOf(physics);
      } else {
         double length = (double)0.0F;

         for(int i = 1; i < physics.size(); ++i) {
            length += ((class_243)physics.get(i)).method_1022((class_243)physics.get(i - 1));
         }

         if (length < 1.0E-8) {
            return List.copyOf(physics);
         } else {
            double mergeLength = length;
            class_243 offset = hand.method_1020((class_243)physics.getFirst());
            ArrayList<class_243> result = new ArrayList();
            result.add(hand);
            double distance = (double)0.0F;

            for(int i = 1; i < physics.size(); ++i) {
               class_243 start = (class_243)physics.get(i - 1);
               class_243 end = (class_243)physics.get(i);
               double segmentLength = start.method_1022(end);
               class_243 previous = i > 1 ? (class_243)physics.get(i - 2) : start.method_1020(end.method_1020(start));
               class_243 next = i + 1 < physics.size() ? (class_243)physics.get(i + 1) : end.method_1019(end.method_1020(start));
               class_243 tangentStart = end.method_1020(previous).method_1021((double)0.5F);
               class_243 tangentEnd = next.method_1020(start).method_1021((double)0.5F);
               boolean collisionSegment = i == physics.size() - 1;
               int subdivisions = Math.max(1, (int)Math.ceil(segmentLength / 0.16));

               for(int j = 1; j <= subdivisions; ++j) {
                  double t = (double)j / (double)subdivisions;
                  double t2 = t * t;
                  double t3 = t2 * t;
                  class_243 point = collisionSegment ? start.method_1019(end.method_1020(start).method_1021(t)) : start.method_1021((double)2.0F * t3 - (double)3.0F * t2 + (double)1.0F).method_1019(tangentStart.method_1021(t3 - (double)2.0F * t2 + t)).method_1019(end.method_1021((double)-2.0F * t3 + (double)3.0F * t2)).method_1019(tangentEnd.method_1021(t3 - t2));
                  double progress = Math.min((double)1.0F, (distance + segmentLength * t) / mergeLength);
                  double blend = (double)1.0F - progress * progress * progress * (progress * (progress * (double)6.0F - (double)15.0F) + (double)10.0F);
                  result.add(point.method_1019(offset.method_1021(blend)));
               }

               distance += segmentLength;
            }

            result.set(result.size() - 1, (class_243)physics.getLast());
            return result;
         }
      }
   }
}
