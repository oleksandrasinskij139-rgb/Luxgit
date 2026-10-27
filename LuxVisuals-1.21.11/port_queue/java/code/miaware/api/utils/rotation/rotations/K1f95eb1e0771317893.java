package code.miaware.api.utils.rotation.rotations;

import code.miaware.api.utils.rotation.K970216267e60d1d8b5;
import code.miaware.api.utils.rotation.manager.Kbdd81cc863fb7d1df0;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_3966;

public class K1f95eb1e0771317893 extends Kbdd81cc863fb7d1df0 {
   private float accelerationValue = 0.0F;
   private boolean backwardsRotating = false;
   private float lastYaw = 0.0F;
   private float lastPitch = 0.0F;
   private Keb4a85ca52fb971c57 lastRotation = null;

   public K1f95eb1e0771317893() {
      super("Lony Grief");
   }

   public Keb4a85ca52fb971c57 process(Keb4a85ca52fb971c57 currentRotation, Keb4a85ca52fb971c57 targetRotation, class_243 vec3d, class_1297 entity) {
      if (this.lastRotation == null) {
         this.lastRotation = currentRotation;
         this.lastYaw = currentRotation.getYaw();
         this.lastPitch = currentRotation.getPitch();
      }

      boolean hasTarget = entity != null;
      class_3966 hitResult = hasTarget ? K970216267e60d1d8b5.raytraceEntity((double)3.0F, currentRotation, false) : null;
      boolean hasTrace = hasTarget && hitResult != null && hitResult.method_17782() == entity;
      float deltaYaw = class_3532.method_15393(targetRotation.getYaw() - this.lastYaw);
      float deltaPitch = targetRotation.getPitch() - this.lastPitch;
      if (mc.field_1724.method_6128()) {
         if (!this.backwardsRotating) {
            this.accelerationValue += 0.005F;
            if (this.accelerationValue >= 0.13F) {
               this.backwardsRotating = true;
            }
         } else {
            if (this.accelerationValue >= -0.02F) {
               this.accelerationValue -= 0.005F;
            }

            if (this.accelerationValue <= -0.02F) {
               this.backwardsRotating = false;
            }
         }
      } else if (!hasTrace) {
         this.accelerationValue += 0.0015F;
      } else if (this.accelerationValue > 0.0F) {
         this.accelerationValue -= 0.01F;
      }

      float smooth = Math.max(this.accelerationValue, 0.0F);
      float newYaw = this.lastYaw + deltaYaw * Math.min(Math.max(smooth, 0.0F), 1.0F);
      float newPitch = this.lastPitch + deltaPitch * Math.min(Math.max(smooth / 2.0F, 0.0F), 1.0F);
      newYaw += this.K28c2e1195b39e8c2bc(mc.field_1724.method_6128(), true);
      newPitch = class_3532.method_15363(newPitch + this.K28c2e1195b39e8c2bc(mc.field_1724.method_6128(), false), -89.0F, 89.0F);
      Keb4a85ca52fb971c57 smoothRotation = this.K9026c3237a6b9a550b(this.lastYaw, this.lastPitch, new Keb4a85ca52fb971c57(newYaw, newPitch));
      this.lastYaw = smoothRotation.getYaw();
      this.lastPitch = smoothRotation.getPitch();
      this.lastRotation = smoothRotation;
      return smoothRotation;
   }

   private float K28c2e1195b39e8c2bc(boolean isGliding, boolean isYaw) {
      float time = (float)(System.currentTimeMillis() % 10000L) / 1000.0F;
      float amplitude = isGliding ? 2.5F : 1.5F;
      return isYaw ? (float)Math.sin((double)(time * 2.0F) * Math.PI * (double)3.0F) * amplitude : (float)Math.cos((double)(time * 2.0F) * Math.PI * (double)2.5F) * amplitude;
   }

   private Keb4a85ca52fb971c57 K9026c3237a6b9a550b(float lastYaw, float lastPitch, Keb4a85ca52fb971c57 target) {
      float gcd = this.K663eccb0f2b070997c();
      float deltaYaw = target.getYaw() - lastYaw;
      float deltaPitch = target.getPitch() - lastPitch;
      float snappedDeltaYaw = (float)Math.round(deltaYaw / gcd) * gcd;
      float snappedDeltaPitch = (float)Math.round(deltaPitch / gcd) * gcd;
      return new Keb4a85ca52fb971c57(lastYaw + snappedDeltaYaw, lastPitch + snappedDeltaPitch);
   }

   private float K663eccb0f2b070997c() {
      double sensitivity = (Double)mc.field_1690.method_42495().method_41753();
      float f = (float)sensitivity;
      float gcd = f * 0.6F + 0.2F;
      gcd = gcd * gcd * gcd * 8.0F;
      return Math.max(gcd, 0.1F);
   }
}
