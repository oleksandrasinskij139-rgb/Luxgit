package code.miaware.api.utils.combat;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.rotation.Kef2fce79615318605c;
import code.miaware.api.utils.rotation.manager.K960322f580344d3bad;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.Generated;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class Kaab2896c59e70ad7f2 implements K4600bef438dafc1a9c {
   private class_1309 currentTarget;
   private List<class_1309> potentialTargets = List.of();

   public class_1309 updateTarget(Iterable<class_1297> entities, float maxDistance, Predicate<class_1309> predicate, Comparator<class_1309> comparator, boolean keepCurrentTarget) {
      this.searchTargets(entities, maxDistance);
      this.potentialTargets = this.potentialTargets.stream().filter(predicate).sorted(comparator).toList();
      if (keepCurrentTarget && this.K69c088e80dde7a02b3(maxDistance, predicate)) {
         return this.currentTarget;
      } else {
         this.currentTarget = (class_1309)this.potentialTargets.stream().findFirst().orElse((Object)null);
         return this.currentTarget;
      }
   }

   public void lockTarget(class_1309 target) {
      this.currentTarget = target;
   }

   public void releaseTarget() {
      this.currentTarget = null;
   }

   public void validateTarget(Predicate<class_1309> predicate) {
      if (this.currentTarget == null || !predicate.test(this.currentTarget)) {
         this.currentTarget = (class_1309)this.K60d9004cbc8ee78acc(predicate).orElse((Object)null);
      }
   }

   public void searchTargets(Iterable<class_1297> entities, float maxDistance) {
      if (mc.field_1724 == null) {
         this.releaseTarget();
         this.potentialTargets = List.of();
      } else {
         if (this.K3748a356e67a36e480(maxDistance)) {
            this.releaseTarget();
         }

         this.potentialTargets = this.K1b982d8f0ad4254b87(entities, maxDistance);
      }
   }

   private boolean K3748a356e67a36e480(float maxDistance) {
      if (mc.field_1724 != null && this.currentTarget != null) {
         return this.currentTarget != null && Kef2fce79615318605c.getSpot(this.currentTarget).method_1022(mc.field_1724.method_33571()) > (double)maxDistance;
      } else {
         return false;
      }
   }

   private boolean K69c088e80dde7a02b3(float maxDistance, Predicate<class_1309> predicate) {
      return this.currentTarget != null && !this.currentTarget.method_31481() && !this.K3748a356e67a36e480(maxDistance) && predicate.test(this.currentTarget);
   }

   private List<class_1309> K1b982d8f0ad4254b87(Iterable<class_1297> entities, float maxDistance) {
      Stream var10000 = StreamSupport.stream(entities.spliterator(), false);
      Objects.requireNonNull(class_1309.class);
      var10000 = var10000.filter(class_1309.class::isInstance);
      Objects.requireNonNull(class_1309.class);
      return var10000.map(class_1309.class::cast).filter((entity) -> !entity.method_31481()).filter((it) -> {
         class_243 spot = Kef2fce79615318605c.getSpot(it);
         return mc.field_1724.method_33571().method_1022(spot) <= (double)maxDistance;
      }).sorted(Comparator.comparingDouble((it) -> {
         class_243 spot = Kef2fce79615318605c.getSpot(it);
         return mc.field_1724.method_33571().method_1022(spot);
      })).toList();
   }

   private Optional<class_1309> K60d9004cbc8ee78acc(Predicate<class_1309> predicate) {
      return this.potentialTargets.stream().filter(predicate).findFirst();
   }

   public Comparator<class_1309> createComparator(String priorityMode) {
      Comparator<class_1309> distance = Comparator.comparingDouble(this::K82bc6b24bff808c03d).thenComparingDouble(this::K60c335e548befe17b4);
      Comparator var10000;
      switch (priorityMode) {
         case "Low HP" -> var10000 = Comparator.comparingDouble((entity) -> (double)this.K60c335e548befe17b4(entity) * 1.15 + Math.sqrt(this.K82bc6b24bff808c03d(entity)) * 0.95).thenComparing(distance);
         case "FOV" -> var10000 = Comparator.comparingDouble(this::Ka9bd723533fc0f58d7).thenComparing(distance);
         case "Naked" -> var10000 = Comparator.comparingInt(this::Kf7a8b38ffc99d5c638).thenComparing(distance);
         case "Armored" -> var10000 = Comparator.comparingInt(this::Kb0ad0abf6c35dfddc0).thenComparing(Comparator.comparingInt(this::K306c88ab111abd7a76).reversed()).thenComparing(distance);
         case "Invisible" -> var10000 = Comparator.comparingInt(this::K66b9d8a28eb52eaf7e).thenComparing(distance);
         case "Mixed" -> var10000 = Comparator.comparingDouble(this::K860a989cdd904a3d68).thenComparing(distance);
         default -> var10000 = distance;
      }

      return var10000;
   }

   private double K82bc6b24bff808c03d(class_1309 entity) {
      return mc.field_1724 == null ? Double.MAX_VALUE : mc.field_1724.method_33571().method_1025(Kef2fce79615318605c.getSpot(entity));
   }

   private int Kf7a8b38ffc99d5c638(class_1309 entity) {
      return K303707ce2607570bff(entity) ? 0 : 1;
   }

   private int Kb0ad0abf6c35dfddc0(class_1309 entity) {
      return this.K306c88ab111abd7a76(entity) > 0 ? 0 : 1;
   }

   private int K66b9d8a28eb52eaf7e(class_1309 entity) {
      return entity.method_5767() ? 0 : 1;
   }

   private double K860a989cdd904a3d68(class_1309 entity) {
      double distanceWeight = Math.sqrt(this.K82bc6b24bff808c03d(entity)) * 1.65;
      double healthWeight = (double)this.K60c335e548befe17b4(entity) * 1.15;
      double armorWeight = (double)this.K306c88ab111abd7a76(entity) * (double)1.25F;
      double invisibleWeight = entity.method_5767() ? (double)4.0F : (double)0.0F;
      double nakedBonus = K303707ce2607570bff(entity) ? (double)-2.0F : (double)0.0F;
      return distanceWeight + healthWeight + armorWeight + invisibleWeight + nakedBonus;
   }

   private float K60c335e548befe17b4(class_1309 entity) {
      return entity.method_6032() + entity.method_6067();
   }

   private double Ka9bd723533fc0f58d7(class_1309 entity) {
      if (mc.field_1724 == null) {
         return Double.MAX_VALUE;
      } else {
         Keb4a85ca52fb971c57 baseRotation = K960322f580344d3bad.getInstance().getRotation();
         Keb4a85ca52fb971c57 targetRotation = Kef2fce79615318605c.rotationAt(Kef2fce79615318605c.getSpot(entity));
         float yawDiff = Math.abs(class_3532.method_15393(targetRotation.getYaw() - baseRotation.getYaw()));
         float pitchDiff = Math.abs(class_3532.method_15393(targetRotation.getPitch() - baseRotation.getPitch()));
         return (double)yawDiff + (double)pitchDiff * 0.35;
      }
   }

   private int K306c88ab111abd7a76(class_1309 entity) {
      if (entity instanceof class_1657 player) {
         return player.method_6096();
      } else {
         return 0;
      }
   }

   private static boolean K303707ce2607570bff(class_1309 entity) {
      boolean var10000;
      if (entity instanceof class_1657 player) {
         if (K4922501b20a9d95c15(player)) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private static boolean K4922501b20a9d95c15(class_1657 player) {
      for(class_1799 armorStack : player.method_5661()) {
         if (!armorStack.method_7960()) {
            return false;
         }
      }

      return true;
   }

   @Generated
   public class_1309 getCurrentTarget() {
      return this.currentTarget;
   }
}
