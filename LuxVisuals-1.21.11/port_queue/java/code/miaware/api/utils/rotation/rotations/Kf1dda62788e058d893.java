package code.miaware.api.utils.rotation.rotations;

import code.miaware.api.utils.rotation.Kef2fce79615318605c;
import code.miaware.api.utils.rotation.manager.Kbdd81cc863fb7d1df0;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class Kf1dda62788e058d893 extends Kbdd81cc863fb7d1df0 {
   private float driftYaw;
   private float driftPitch;
   private long nextOffsetRefresh;

   public Kf1dda62788e058d893() {
      super("Holy World");
   }

   public Keb4a85ca52fb971c57 process(Keb4a85ca52fb971c57 currentRotation, Keb4a85ca52fb971c57 targetRotation, class_243 vec3d, class_1297 entity) {
      this.Ka1814beab83fb85c95();
      Keb4a85ca52fb971c57 delta = Kef2fce79615318605c.calculateDelta(currentRotation, targetRotation);
      float yawDelta = delta.getYaw();
      float pitchDelta = delta.getPitch();
      float yawSpeed = class_3532.method_15363(10.0F + Math.abs(yawDelta) * 0.38F, 7.0F, 34.0F);
      float pitchSpeed = class_3532.method_15363(4.5F + Math.abs(pitchDelta) * 0.24F, 3.0F, 18.0F);
      float orbitYaw = (float)Math.sin((double)((float)(mc.field_1724 != null ? mc.field_1724.field_6012 : 0) * 0.21F)) * 1.35F + this.driftYaw;
      float orbitPitch = (float)Math.cos((double)((float)(mc.field_1724 != null ? mc.field_1724.field_6012 : 0) * 0.17F)) * 0.55F + this.driftPitch;
      return new Keb4a85ca52fb971c57(currentRotation.getYaw() + class_3532.method_15363(yawDelta + orbitYaw, -yawSpeed, yawSpeed), class_3532.method_15363(currentRotation.getPitch() + class_3532.method_15363(pitchDelta + orbitPitch, -pitchSpeed, pitchSpeed), -90.0F, 90.0F));
   }

   private void Ka1814beab83fb85c95() {
      long now = System.currentTimeMillis();
      if (now < this.nextOffsetRefresh) {
         this.driftYaw *= 0.94F;
         this.driftPitch *= 0.94F;
      } else {
         ThreadLocalRandom random = ThreadLocalRandom.current();
         this.driftYaw = random.nextFloat() * 1.35F - 0.675F;
         this.driftPitch = random.nextFloat() * 0.4F - 0.2F;
         this.nextOffsetRefresh = now + random.nextLong(120L, 240L);
      }
   }
}
