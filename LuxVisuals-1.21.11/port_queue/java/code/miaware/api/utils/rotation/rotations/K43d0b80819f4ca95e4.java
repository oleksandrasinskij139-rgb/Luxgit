package code.miaware.api.utils.rotation.rotations;

import code.miaware.api.utils.rotation.Kef2fce79615318605c;
import code.miaware.api.utils.rotation.manager.Kbdd81cc863fb7d1df0;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class K43d0b80819f4ca95e4 extends Kbdd81cc863fb7d1df0 {
   public K43d0b80819f4ca95e4() {
      super("HvH");
   }

   public Keb4a85ca52fb971c57 process(Keb4a85ca52fb971c57 currentRotation, Keb4a85ca52fb971c57 targetRotation, class_243 vec3d, class_1297 entity) {
      Keb4a85ca52fb971c57 delta = Kef2fce79615318605c.calculateDelta(currentRotation, targetRotation);
      float yawDelta = delta.getYaw();
      float pitchDelta = delta.getPitch();
      float yawSpeed = class_3532.method_15363(22.0F + Math.abs(yawDelta) * 0.55F, 18.0F, 90.0F);
      float pitchSpeed = class_3532.method_15363(10.0F + Math.abs(pitchDelta) * 0.4F, 8.0F, 45.0F);
      float overshootYaw = Math.abs(yawDelta) > 18.0F ? Math.signum(yawDelta) * 1.2F : 0.0F;
      float overshootPitch = Math.abs(pitchDelta) > 8.0F ? Math.signum(pitchDelta) * 0.45F : 0.0F;
      return new Keb4a85ca52fb971c57(currentRotation.getYaw() + class_3532.method_15363(yawDelta + overshootYaw, -yawSpeed, yawSpeed), class_3532.method_15363(currentRotation.getPitch() + class_3532.method_15363(pitchDelta + overshootPitch, -pitchSpeed, pitchSpeed), -90.0F, 90.0F));
   }
}
