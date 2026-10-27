package code.miaware.api.utils.rotation.rotations;

import code.miaware.api.utils.rotation.Kef2fce79615318605c;
import code.miaware.api.utils.rotation.manager.Kbdd81cc863fb7d1df0;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class Kf304e7c948ff2afd48 extends Kbdd81cc863fb7d1df0 {
   private float lastYawStep;
   private float lastPitchStep;
   private float driftYaw;
   private float driftPitch;
   private long nextDriftUpdate;

   public Kf304e7c948ff2afd48() {
      super("Legit");
   }

   public Keb4a85ca52fb971c57 process(Keb4a85ca52fb971c57 currentRotation, Keb4a85ca52fb971c57 targetRotation, class_243 vec3d, class_1297 entity) {
      Keb4a85ca52fb971c57 delta = Kef2fce79615318605c.calculateDelta(currentRotation, targetRotation);
      float yawDelta = delta.getYaw();
      float pitchDelta = delta.getPitch();
      this.K47410b6e9254891dca();
      int age = mc.field_1724 != null ? mc.field_1724.field_6012 : 0;
      float yawSpeed = class_3532.method_15363(2.8F + Math.abs(yawDelta) * 0.22F, 1.8F, 15.0F);
      float pitchSpeed = class_3532.method_15363(1.8F + Math.abs(pitchDelta) * 0.16F, 1.0F, 9.5F);
      float yawNoise = (float)Math.sin((double)((float)age * 0.33F + (entity != null ? (float)entity.method_5628() * 0.11F : 0.0F))) * 0.16F + this.driftYaw;
      float pitchNoise = (float)Math.cos((double)((float)age * 0.27F + (entity != null ? (float)entity.method_5628() * 0.07F : 0.0F))) * 0.09F + this.driftPitch;
      float yawStep = this.Kaf0ef57f679a6126f5(yawDelta + yawNoise, yawSpeed, 0.56F);
      float pitchStep = this.Kaf0ef57f679a6126f5(pitchDelta + pitchNoise, pitchSpeed, 0.48F);
      yawStep = class_3532.method_16439(0.38F, this.lastYawStep, yawStep);
      pitchStep = class_3532.method_16439(0.32F, this.lastPitchStep, pitchStep);
      if (Math.abs(yawStep) > Math.abs(yawDelta)) {
         yawStep = yawDelta;
      }

      if (Math.abs(pitchStep) > Math.abs(pitchDelta)) {
         pitchStep = pitchDelta;
      }

      this.lastYawStep = yawStep;
      this.lastPitchStep = pitchStep;
      return new Keb4a85ca52fb971c57(currentRotation.getYaw() + yawStep, class_3532.method_15363(currentRotation.getPitch() + pitchStep, -90.0F, 90.0F));
   }

   private float Kaf0ef57f679a6126f5(float delta, float maxStep, float smoothing) {
      if (Math.abs(delta) < 0.001F) {
         return 0.0F;
      } else {
         float step = class_3532.method_15363(delta * smoothing, -maxStep, maxStep);
         if (Math.abs(delta) < maxStep * 0.55F) {
            step = delta * 0.8F;
         }

         return step;
      }
   }

   private void K47410b6e9254891dca() {
      long time = System.currentTimeMillis();
      if (time < this.nextDriftUpdate) {
         this.driftYaw *= 0.92F;
         this.driftPitch *= 0.92F;
      } else {
         ThreadLocalRandom random = ThreadLocalRandom.current();
         this.driftYaw = random.nextFloat() * 0.28F - 0.14F;
         this.driftPitch = random.nextFloat() * 0.18F - 0.09F;
         this.nextDriftUpdate = time + random.nextLong(85L, 165L);
      }
   }
}
