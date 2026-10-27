package code.miaware.api.utils.rotation.rotations;

import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.neuro.K49e4f6d5d86e774072;
import code.miaware.api.utils.rotation.Kef2fce79615318605c;
import code.miaware.api.utils.rotation.manager.Kbdd81cc863fb7d1df0;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_5611;

public class Ke378c57f3ea5616161 extends Kbdd81cc863fb7d1df0 {
   private final K49e4f6d5d86e774072 predictor;
   private final float yawSpeed;
   private final float pitchSpeed;
   Keb4a85ca52fb971c57 aiRotation;
   Keb4a85ca52fb971c57 prevRotation;

   public Ke378c57f3ea5616161(K49e4f6d5d86e774072 predictor, float yawSpeed, float pitchSpeed) {
      super("Neuro");
      this.aiRotation = Keb4a85ca52fb971c57.DEFAULT;
      this.prevRotation = Keb4a85ca52fb971c57.DEFAULT;
      this.predictor = predictor;
      this.yawSpeed = yawSpeed;
      this.pitchSpeed = pitchSpeed;
   }

   public void resetState() {
      this.aiRotation = Keb4a85ca52fb971c57.DEFAULT;
      this.prevRotation = Keb4a85ca52fb971c57.DEFAULT;
   }

   public Keb4a85ca52fb971c57 process(Keb4a85ca52fb971c57 currentRotation, Keb4a85ca52fb971c57 targetRotation, class_243 vec3d, class_1297 entity) {
      this.prevRotation = this.aiRotation;
      if (entity != null && mc.field_1724 != null) {
         this.aiRotation = this.predictor.predict(entity, this.aiRotation, this.prevRotation, new class_5611(100.0F - this.yawSpeed, 100.0F - this.pitchSpeed));
         return this.aiRotation;
      } else {
         float y = 13.333333F;
         float p = 6.6666665F;
         Keb4a85ca52fb971c57 delta = Kef2fce79615318605c.calculateDelta(currentRotation, targetRotation);
         return new Keb4a85ca52fb971c57(currentRotation.getYaw() + class_3532.method_15363(delta.getYaw(), -y, y), currentRotation.getPitch() + class_3532.method_15363(delta.getPitch(), -p, p));
      }
   }

   public float sinWave(double value, double delayMS, Kf3b62ecfba0c3544e6 easing) {
      return (float)(class_3532.method_15350(easing.apply((Math.sin((double)System.currentTimeMillis() / delayMS) + (double)1.0F) / (double)2.0F), (double)0.0F, (double)1.0F) * value);
   }

   public float cosWave(double value, double delayMS, Kf3b62ecfba0c3544e6 easing) {
      return (float)(class_3532.method_15350(easing.apply((Math.cos((double)System.currentTimeMillis() / delayMS) + (double)1.0F) / (double)2.0F), (double)0.0F, (double)1.0F) * value);
   }

   public float sinWave(double from, double to, double delayMS, Kf3b62ecfba0c3544e6 easing) {
      return (float)(from + (double)this.sinWave(to, delayMS, easing));
   }

   public float cosWave(double from, double to, double delayMS, Kf3b62ecfba0c3544e6 easing) {
      return (float)(from + (double)this.cosWave(to, delayMS, easing));
   }

   public float calc(float input, float target, double step) {
      return (float)((double)input + step * (double)(target - input));
   }
}
