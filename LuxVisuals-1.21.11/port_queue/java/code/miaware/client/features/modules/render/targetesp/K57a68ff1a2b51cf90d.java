package code.miaware.client.features.modules.render.targetesp;

import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import lombok.Generated;
import net.minecraft.class_1309;

public abstract class K57a68ff1a2b51cf90d implements K4600bef438dafc1a9c {
   public static final Ka91715a8c4e3f3b7c4 showAnimation = new Ka91715a8c4e3f3b7c4();
   public static final Ka91715a8c4e3f3b7c4 sizeAnimation = new Ka91715a8c4e3f3b7c4();
   public static class_1309 currentTarget = null;
   public float prevShowAnimation = 0.0F;
   public float prevSizeAnimation = 0.0F;
   private static double targetX = (double)-1.0F;
   private static double targetY = (double)-1.0F;
   private static double targetZ = (double)-1.0F;
   private static double lastTargetX = (double)-1.0F;
   private static double lastTargetY = (double)-1.0F;
   private static double lastTargetZ = (double)-1.0F;

   public void updateTarget() {
   }

   public void updateAnimation(long duration, String mode, float size, float in, float out) {
      this.prevShowAnimation = (float)showAnimation.getValue();
      this.prevSizeAnimation = (float)sizeAnimation.getValue();
      sizeAnimation.update();
      double var10000;
      switch (mode) {
         case "In" -> var10000 = (double)in;
         case "Out" -> var10000 = (double)out;
         default -> var10000 = (double)size;
      }

      double dyingSize = var10000;
      sizeAnimation.run(this.reason() ? (double)size : dyingSize, duration, Kf3b62ecfba0c3544e6.SINE_OUT);
      showAnimation.update();
      showAnimation.run(this.reason() ? (double)1.0F : (double)0.0F, duration, Kf3b62ecfba0c3544e6.SINE_OUT);
   }

   public boolean reason() {
      return currentTarget != null && currentTarget.method_5805() && !currentTarget.method_5767();
   }

   public boolean canDraw() {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         return showAnimation.getValue() > (double)0.0F;
      } else {
         return false;
      }
   }

   public static void resetState() {
      currentTarget = null;
      targetZ = (double)-1.0F;
      targetY = (double)-1.0F;
      targetX = (double)-1.0F;
      lastTargetZ = (double)-1.0F;
      lastTargetY = (double)-1.0F;
      lastTargetX = (double)-1.0F;
      showAnimation.setValue((double)0.0F);
      sizeAnimation.setValue((double)0.0F);
   }

   public static void updatePositions() {
      float animationValue = (float)showAnimation.getValue();
      float animationTarget = (float)showAnimation.getToValue();
      boolean preventUpdate = (double)animationTarget == (double)0.0F && animationValue <= 0.9F;
      if (currentTarget != null && !preventUpdate) {
         lastTargetX = (double)Ka0484a23398fb5f9ac.interpolate((double)((float)currentTarget.field_6014), (double)((float)currentTarget.method_23317()));
         lastTargetY = (double)Ka0484a23398fb5f9ac.interpolate((double)((float)currentTarget.field_6036), (double)((float)currentTarget.method_23318()));
         lastTargetZ = (double)Ka0484a23398fb5f9ac.interpolate((double)((float)currentTarget.field_5969), (double)((float)currentTarget.method_23321()));
      }

      targetX = lastTargetX;
      targetY = lastTargetY;
      targetZ = lastTargetZ;
   }

   public abstract void onUpdate();

   public abstract void onRender3D(K9ee087b2e1791ad6b2 var1);

   @Generated
   public static double getTargetX() {
      return targetX;
   }

   @Generated
   public static double getTargetY() {
      return targetY;
   }

   @Generated
   public static double getTargetZ() {
      return targetZ;
   }
}
