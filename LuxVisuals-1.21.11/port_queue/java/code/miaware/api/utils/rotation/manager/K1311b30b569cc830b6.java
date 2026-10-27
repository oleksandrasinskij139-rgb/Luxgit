package code.miaware.api.utils.rotation.manager;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.rotation.Kef2fce79615318605c;
import lombok.Generated;
import net.minecraft.class_1297;
import net.minecraft.class_243;

public class K1311b30b569cc830b6 implements K4600bef438dafc1a9c {
   private final Keb4a85ca52fb971c57 rotation;
   private final class_243 vec3d;
   private final class_1297 entity;
   private final Kbdd81cc863fb7d1df0 rotationMode;
   private final int ticksUntilReset;
   private final float resetThreshold;
   private final boolean moveCorrection;
   private boolean freeMoveCorrection;
   private boolean clientLook;
   private final Kab8a8c478f7d0077cd provider;

   public K1311b30b569cc830b6(Keb4a85ca52fb971c57 rotation, class_243 vec3d, class_1297 entity, Kbdd81cc863fb7d1df0 rotationMode, int ticksUntilReset, float resetThreshold, boolean moveCorrection, boolean freeMoveCorrection, Kab8a8c478f7d0077cd provider) {
      this.rotation = rotation;
      this.vec3d = vec3d;
      this.entity = entity;
      this.rotationMode = rotationMode;
      this.ticksUntilReset = ticksUntilReset;
      this.resetThreshold = resetThreshold;
      this.moveCorrection = moveCorrection;
      this.freeMoveCorrection = freeMoveCorrection;
      this.provider = provider;
   }

   public Keb4a85ca52fb971c57 nextRotation(Keb4a85ca52fb971c57 fromRotation, boolean isResetting) {
      return isResetting ? this.rotationMode.process(fromRotation, Kef2fce79615318605c.fromVec2f(mc.field_1724.method_5802())) : this.rotationMode.process(fromRotation, this.rotation, this.vec3d, this.entity);
   }

   @Generated
   public Keb4a85ca52fb971c57 rotation() {
      return this.rotation;
   }

   @Generated
   public class_243 vec3d() {
      return this.vec3d;
   }

   @Generated
   public class_1297 entity() {
      return this.entity;
   }

   @Generated
   public Kbdd81cc863fb7d1df0 rotationMode() {
      return this.rotationMode;
   }

   @Generated
   public int ticksUntilReset() {
      return this.ticksUntilReset;
   }

   @Generated
   public float resetThreshold() {
      return this.resetThreshold;
   }

   @Generated
   public boolean moveCorrection() {
      return this.moveCorrection;
   }

   @Generated
   public boolean freeMoveCorrection() {
      return this.freeMoveCorrection;
   }

   @Generated
   public boolean clientLook() {
      return this.clientLook;
   }

   @Generated
   public Kab8a8c478f7d0077cd provider() {
      return this.provider;
   }

   @Generated
   public K1311b30b569cc830b6 freeMoveCorrection(boolean freeMoveCorrection) {
      this.freeMoveCorrection = freeMoveCorrection;
      return this;
   }

   @Generated
   public K1311b30b569cc830b6 clientLook(boolean clientLook) {
      this.clientLook = clientLook;
      return this;
   }
}
