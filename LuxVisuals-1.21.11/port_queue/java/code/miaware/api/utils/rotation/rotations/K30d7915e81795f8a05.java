package code.miaware.api.utils.rotation.rotations;

import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.api.utils.rotation.Kef2fce79615318605c;
import code.miaware.api.utils.rotation.manager.Kbdd81cc863fb7d1df0;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class K30d7915e81795f8a05 extends Kbdd81cc863fb7d1df0 {
   private float lastYaw;
   private float lastPitch;

   public K30d7915e81795f8a05() {
      super("Matrix");
   }

   public Keb4a85ca52fb971c57 process(Keb4a85ca52fb971c57 currentRotation, Keb4a85ca52fb971c57 targetRotation, class_243 vec3d, class_1297 entity) {
      Keb4a85ca52fb971c57 delta = Kef2fce79615318605c.calculateDelta(currentRotation, targetRotation);
      float yawDelta = delta.getYaw();
      float pitchDelta = delta.getPitch();
      float yawSpeed = (float)Ka0484a23398fb5f9ac.randomInRange(58, 60);
      float pitchSpeed = entity == null ? Math.abs(pitchDelta) : 0.333F;
      float minPitchStep = (float)(0.8 + Math.random() * (double)1.0F);
      float pitchStep = Math.max(Math.abs(pitchDelta), minPitchStep);
      pitchDelta = Math.copySign(pitchStep, pitchDelta);
      float jitterPower = 3.0F;
      if (Math.abs(yawDelta - this.lastYaw) >= jitterPower) {
         float jitterApplier = jitterPower + 0.1F;
         yawDelta += yawDelta > 0.0F ? jitterApplier : -jitterApplier;
      }

      this.lastYaw = yawDelta;
      this.lastPitch = pitchDelta;
      return new Keb4a85ca52fb971c57(currentRotation.getYaw() + class_3532.method_15363(yawDelta, -yawSpeed, yawSpeed), class_3532.method_15363(currentRotation.getPitch() + class_3532.method_15363(pitchDelta, -pitchSpeed, pitchSpeed), -90.0F, 90.0F));
   }
}
