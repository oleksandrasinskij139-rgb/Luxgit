package code.miaware.api.utils.rotation.rotations;

import code.miaware.api.utils.rotation.Kef2fce79615318605c;
import code.miaware.api.utils.rotation.manager.Kbdd81cc863fb7d1df0;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import net.minecraft.class_1297;
import net.minecraft.class_243;

public class K6a50a7884a8b240fad extends Kbdd81cc863fb7d1df0 {
   public K6a50a7884a8b240fad() {
      super("Smooth");
   }

   public Keb4a85ca52fb971c57 process(Keb4a85ca52fb971c57 currentRotation, Keb4a85ca52fb971c57 targetRotation, class_243 vec3d, class_1297 entity) {
      Keb4a85ca52fb971c57 delta = Kef2fce79615318605c.calculateDelta(currentRotation, targetRotation);
      float yawDelta = delta.getYaw();
      float pitchDelta = delta.getPitch();
      float rotationDifference = (float)Math.hypot((double)Math.abs(yawDelta), (double)Math.abs(pitchDelta));
      if (rotationDifference < 1.0E-4F) {
         return currentRotation;
      } else {
         float straightLineYaw = Math.abs(yawDelta / rotationDifference) * 20.0F;
         float straightLinePitch = Math.abs(pitchDelta / rotationDifference) * 10.0F;
         return new Keb4a85ca52fb971c57(currentRotation.getYaw() + Math.min(Math.max(yawDelta, -straightLineYaw), straightLineYaw), currentRotation.getPitch() + Math.min(Math.max(pitchDelta, -straightLinePitch), straightLinePitch));
      }
   }
}
