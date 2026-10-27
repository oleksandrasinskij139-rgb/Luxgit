package code.miaware.api.utils.rotation.rotations;

import code.miaware.api.utils.player.K131e64425767c5c203;
import code.miaware.api.utils.rotation.K970216267e60d1d8b5;
import code.miaware.api.utils.rotation.Kef2fce79615318605c;
import code.miaware.api.utils.rotation.manager.Kbdd81cc863fb7d1df0;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import net.minecraft.class_1297;
import net.minecraft.class_2246;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_3966;

public class K55933dd215f5217675 extends Kbdd81cc863fb7d1df0 {
   private final float dermoYaw;
   private final float dermoPitch;
   private final boolean spookySkeletons;
   private final boolean huly;
   private int jopa = 0;
   private float lastYawDelta = 0.0F;
   private float lastPitchDelta = 0.0F;
   private int lastPitchChangeDirection = 0;
   private int ticksSinceSwitchedDirection = 0;

   public K55933dd215f5217675(float maxYawSpeed, float maxPitchSpeed, boolean spookySkeletons, boolean huly) {
      super("Vulcan");
      this.dermoYaw = maxYawSpeed;
      this.dermoPitch = maxPitchSpeed;
      this.spookySkeletons = spookySkeletons;
      this.huly = huly;
   }

   public Keb4a85ca52fb971c57 process(Keb4a85ca52fb971c57 currentRotation, Keb4a85ca52fb971c57 targetRotation, class_243 vec3d, class_1297 entity) {
      Keb4a85ca52fb971c57 angleDelta = Kef2fce79615318605c.calculateDelta(currentRotation, targetRotation);
      float yawDelta = angleDelta.getYaw();
      float pitchDelta = angleDelta.getPitch();
      float maxYawSpeed = this.dermoYaw / 3.0F;
      float maxPitchSpeed = this.dermoPitch / 3.0F;
      if ((!(pitchDelta < 0.0F) || !(this.lastPitchDelta > 0.0F)) && (!(pitchDelta > 0.0F) || !(this.lastPitchDelta < 0.0F))) {
         ++this.ticksSinceSwitchedDirection;
      } else {
         this.ticksSinceSwitchedDirection = 0;
      }

      boolean invalid = this.ticksSinceSwitchedDirection == 0 && Math.abs(pitchDelta) > 5.0F;
      if (invalid) {
         --pitchDelta;
         pitchDelta *= 0.3F;
         maxPitchSpeed *= 0.4F;
      }

      if (Math.abs(pitchDelta) < 0.05F) {
         pitchDelta -= (float)(Math.random() * (double)0.05F - (double)0.225F);
      }

      if (Math.abs(yawDelta - this.lastYawDelta) < 0.08F) {
         yawDelta -= (float)(Math.random() * (double)0.15F - (double)0.125F);
      }

      if (Math.abs(pitchDelta) < 0.01F) {
         pitchDelta -= (float)(Math.random() * (double)0.01F - (double)0.005F);
      }

      if (Math.abs(yawDelta) > 180.25F) {
         maxYawSpeed *= 0.8F;
      }

      if (Math.abs(yawDelta) > 15.0F && Math.abs(pitchDelta) < 0.1F) {
         maxYawSpeed *= 0.7F;
      }

      if (Math.abs(yawDelta) < 0.05F && Math.abs(pitchDelta) < 0.05F) {
         maxYawSpeed *= 1.1F;
         maxPitchSpeed *= 1.1F;
      }

      if (yawDelta > 1.25F && this.lastYawDelta > 1.25F) {
         yawDelta -= this.lastYawDelta;
         maxYawSpeed *= 3.0F;
      }

      if (Math.abs(yawDelta) > 2.75F && Math.abs(pitchDelta) == 0.0F) {
         maxYawSpeed *= 0.8F;
         maxPitchSpeed *= 1.1F;
      }

      if (Math.abs(yawDelta) > 0.5F && Math.abs(pitchDelta) < 0.05F) {
         maxYawSpeed *= 0.7F;
         maxPitchSpeed *= 1.05F;
      }

      if (Math.abs(yawDelta) > 1.825F && Math.abs(pitchDelta) == 0.0F) {
         maxYawSpeed *= 0.6F;
         maxPitchSpeed *= 0.9F;
      }

      if (Math.abs(yawDelta) > 20.0F && Math.abs(pitchDelta) < 0.1F) {
         maxYawSpeed *= 0.5F;
         maxPitchSpeed *= 1.1F;
      }

      if (Math.abs(yawDelta) > 0.25F && Math.abs(pitchDelta) > 0.25F && Math.abs(pitchDelta) < 20.0F && Math.abs(yawDelta) < 20.0F) {
         maxYawSpeed *= 0.95F;
         maxPitchSpeed *= 0.85F;
      }

      if (Math.abs(yawDelta) > 0.1F && Math.abs(pitchDelta) > 0.1F && Math.abs(yawDelta) < 20.0F && Math.abs(pitchDelta) < 20.0F) {
         maxYawSpeed *= 0.9F;
         maxPitchSpeed *= 0.8F;
      }

      if (Math.abs(yawDelta) > 0.05F && Math.abs(pitchDelta) == 0.0F) {
         maxYawSpeed *= 0.8F;
         maxPitchSpeed *= 0.95F;
      }

      if (Math.abs(yawDelta) > 0.05F && Math.abs(pitchDelta) < 0.05F) {
         maxYawSpeed *= 0.85F;
         maxPitchSpeed *= 1.1F;
      }

      if (Math.abs(yawDelta) > 0.75F && Math.abs(pitchDelta) > 0.75F) {
         maxYawSpeed *= 0.8F;
         maxPitchSpeed *= 0.75F;
      }

      if (Math.abs(yawDelta) > 0.03F && Math.abs(pitchDelta) > 0.03F) {
         maxYawSpeed *= 0.9F;
         maxPitchSpeed *= 0.8F;
      }

      int currentPitchChangeDirection = pitchDelta > 0.0F ? 1 : -1;
      if (this.lastPitchChangeDirection != 0 && currentPitchChangeDirection != this.lastPitchChangeDirection) {
         maxPitchSpeed *= 0.2F;
      }

      this.lastPitchChangeDirection = currentPitchChangeDirection;
      class_3966 raytraceResult = K970216267e60d1d8b5.raytraceEntity((double)3.0F, currentRotation, false);
      boolean check = raytraceResult != null && raytraceResult.method_17782() == entity;
      boolean checked = entity != null && K131e64425767c5c203.hasCollisionWith(entity, 1.0F) && K131e64425767c5c203.getBlock(0.0F, 2.0F, 0.0F) != class_2246.field_10124 && K131e64425767c5c203.getBlock(0.0F, -1.0F, 0.0F) != class_2246.field_10124 && K131e64425767c5c203.getBlock(0.0F, 2.0F, 0.0F) != class_2246.field_10382 && K131e64425767c5c203.getBlock(0.0F, -1.0F, 0.0F) != class_2246.field_10382;
      if (this.spookySkeletons) {
         int maxJopa = 20;
         float deldeldel = !this.huly ? 30.0F : 13.0F;
         if (checked) {
            pitchDelta /= deldeldel;
            yawDelta /= deldeldel;
            this.jopa = maxJopa;
         }

         if (!this.huly && !checked && check) {
            maxPitchSpeed *= 1.3F;
            maxYawSpeed *= 1.1F;
         } else if (!this.huly && !checked) {
            maxPitchSpeed *= 1.1F;
            maxYawSpeed *= 1.25F;
         }

         if (this.jopa-- > 0) {
            float superJopa = Math.max(1.0F, (float)this.jopa / (float)maxJopa * 15.0F);
            yawDelta /= superJopa;
            pitchDelta /= superJopa;
         }
      }

      this.lastYawDelta = yawDelta;
      this.lastPitchDelta = pitchDelta;
      return new Keb4a85ca52fb971c57(currentRotation.getYaw() + class_3532.method_15363(yawDelta, -maxYawSpeed, maxYawSpeed), currentRotation.getPitch() + class_3532.method_15363(pitchDelta, -maxPitchSpeed, maxPitchSpeed));
   }
}
