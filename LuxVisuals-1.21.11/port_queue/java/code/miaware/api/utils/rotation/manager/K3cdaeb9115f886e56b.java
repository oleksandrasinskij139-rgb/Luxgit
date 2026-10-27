package code.miaware.api.utils.rotation.manager;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.utils.rotation.rotations.K1f822c5754b30263ea;
import code.miaware.api.utils.rotation.rotations.K6a50a7884a8b240fad;
import lombok.Generated;
import net.minecraft.class_1297;
import net.minecraft.class_243;

public class K3cdaeb9115f886e56b {
   private final Kbdd81cc863fb7d1df0 rotationMode;
   private final boolean moveCorrection;
   private final boolean freeCorrection;
   private final float resetThreshold;
   private int ticksUntilReset;
   private boolean clientLook;
   public static final K3cdaeb9115f886e56b SMOOTH_FREE = new K3cdaeb9115f886e56b(new K6a50a7884a8b240fad(), true, true);
   public static final K3cdaeb9115f886e56b SMOOTH_FOCUS = new K3cdaeb9115f886e56b(new K6a50a7884a8b240fad(), true);
   public static final K3cdaeb9115f886e56b TARGET = new K3cdaeb9115f886e56b(new K1f822c5754b30263ea(), true);

   public K3cdaeb9115f886e56b(Kbdd81cc863fb7d1df0 rotationMode, boolean moveCorrection) {
      this.resetThreshold = 2.0F;
      this.ticksUntilReset = 5;
      this.rotationMode = rotationMode;
      this.moveCorrection = moveCorrection;
      this.freeCorrection = false;
   }

   public K3cdaeb9115f886e56b(Kbdd81cc863fb7d1df0 rotationMode, boolean moveCorrection, boolean freeCorrection) {
      this.resetThreshold = 2.0F;
      this.ticksUntilReset = 5;
      this.rotationMode = rotationMode;
      this.moveCorrection = moveCorrection;
      this.freeCorrection = freeCorrection;
   }

   public K3cdaeb9115f886e56b(boolean moveCorrection) {
      this(new K6a50a7884a8b240fad(), moveCorrection);
   }

   public K3cdaeb9115f886e56b() {
      this(new K6a50a7884a8b240fad(), true, true);
   }

   public K1311b30b569cc830b6 createRotationPlan(Keb4a85ca52fb971c57 rotation, class_243 vec, class_1297 entity, Kab8a8c478f7d0077cd provider) {
      return (new K1311b30b569cc830b6(rotation, vec, entity, this.rotationMode, this.ticksUntilReset, 2.0F, this.moveCorrection, this.freeCorrection, provider)).clientLook(this.clientLook);
   }

   public K1311b30b569cc830b6 createRotationPlan(Keb4a85ca52fb971c57 rotation, Kab8a8c478f7d0077cd provider) {
      return (new K1311b30b569cc830b6(rotation, (class_243)null, (class_1297)null, this.rotationMode, this.ticksUntilReset, 2.0F, this.moveCorrection, this.freeCorrection, provider)).clientLook(this.clientLook);
   }

   public K1311b30b569cc830b6 createRotationPlan(Keb4a85ca52fb971c57 rotation, class_243 vec, class_1297 entity, boolean moveCorrection, boolean freeCorrection, Kab8a8c478f7d0077cd provider) {
      return (new K1311b30b569cc830b6(rotation, vec, entity, this.rotationMode, this.ticksUntilReset, 2.0F, moveCorrection, freeCorrection, provider)).clientLook(this.clientLook);
   }

   @Generated
   public K3cdaeb9115f886e56b ticksUntilReset(int ticksUntilReset) {
      this.ticksUntilReset = ticksUntilReset;
      return this;
   }

   @Generated
   public K3cdaeb9115f886e56b clientLook(boolean clientLook) {
      this.clientLook = clientLook;
      return this;
   }
}
