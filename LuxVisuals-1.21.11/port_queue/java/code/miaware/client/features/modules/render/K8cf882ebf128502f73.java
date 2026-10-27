package code.miaware.client.features.modules.render;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import lombok.Generated;
import net.minecraft.class_2189;
import net.minecraft.class_2231;
import net.minecraft.class_2248;
import net.minecraft.class_2269;
import net.minecraft.class_2338;
import net.minecraft.class_2401;
import net.minecraft.class_2404;
import net.minecraft.class_243;
import net.minecraft.class_2527;
import net.minecraft.class_2577;
import net.minecraft.class_2680;

public class K8cf882ebf128502f73 {
   private class_243 position;
   private class_243 velocity;
   private final int index;
   private final Kf39dc27225f4d9fe6e timer = new Kf39dc27225f4d9fe6e();
   private final Ka91715a8c4e3f3b7c4 alphaAnimation = new Ka91715a8c4e3f3b7c4();
   private float alpha = 255.0F;

   public K8cf882ebf128502f73(class_243 position, int index) {
      this.position = position;
      this.velocity = new class_243(Ka0484a23398fb5f9ac.randomInRange(-0.01, 0.01), Ka0484a23398fb5f9ac.randomInRange(-0.01, 0.01), Ka0484a23398fb5f9ac.randomInRange(-0.01, 0.01));
      this.index = index;
   }

   public void handleAlphaTransitions(int fadeTime, int maxLife) {
      this.alphaAnimation.update();
      float currentAlpha = (float)this.alphaAnimation.getValue();
      if ((double)currentAlpha <= (double)0.0F && !this.timer.finished((long)fadeTime)) {
         this.alphaAnimation.run((double)255.0F, (long)fadeTime, Kf3b62ecfba0c3544e6.LINEAR);
      }

      if ((double)currentAlpha >= (double)255.0F && this.timer.finished((long)(maxLife - fadeTime))) {
         this.alphaAnimation.run((double)0.0F, (long)fadeTime, Kf3b62ecfba0c3544e6.LINEAR);
      }

      this.alpha = (float)this.alphaAnimation.getValue();
   }

   public boolean shouldRemove(int maxLife) {
      double distance = this.position.method_1022(K4600bef438dafc1a9c.mc.field_1724.method_19538());
      boolean expired = this.timer.finished((long)maxLife) && (double)this.alpha <= (double)0.0F;
      return distance >= (double)80.0F || expired;
   }

   public void update(boolean enablePhysics) {
      if (enablePhysics) {
         this.Keb7142cb2d1f8456c9();
      } else {
         this.Kafea33ccc6100b4022();
      }

   }

   private void Keb7142cb2d1f8456c9() {
      if (this.K64df242f71f5608e5b(this.position.field_1352, this.position.field_1351, this.position.field_1350 + this.velocity.field_1350)) {
         this.velocity = new class_243(this.velocity.field_1352, this.velocity.field_1351, -this.velocity.field_1350 * 0.8);
      }

      if (this.K64df242f71f5608e5b(this.position.field_1352, this.position.field_1351 + this.velocity.field_1351, this.position.field_1350)) {
         this.velocity = new class_243(this.velocity.field_1352 * 0.999, -this.velocity.field_1351 * 0.7, this.velocity.field_1350 * 0.999);
      }

      if (this.K64df242f71f5608e5b(this.position.field_1352 + this.velocity.field_1352, this.position.field_1351, this.position.field_1350)) {
         this.velocity = new class_243(-this.velocity.field_1352 * 0.8, this.velocity.field_1351, this.velocity.field_1350);
      }

      this.Kafea33ccc6100b4022();
   }

   private void Kafea33ccc6100b4022() {
      this.position = this.position.method_1019(this.velocity);
      this.velocity = this.velocity.method_1021(0.999);
   }

   private boolean K64df242f71f5608e5b(double x, double y, double z) {
      class_2338 pos = class_2338.method_49637(x, y, z);
      class_2680 state = K4600bef438dafc1a9c.mc.field_1687.method_8320(pos);
      class_2248 block = state.method_26204();
      return this.Kae607adcdfee77f063(block);
   }

   private boolean Kae607adcdfee77f063(class_2248 block) {
      return !(block instanceof class_2189) && !(block instanceof class_2269) && !(block instanceof class_2527) && !(block instanceof class_2401) && !(block instanceof class_2231) && !(block instanceof class_2577) && !(block instanceof class_2404);
   }

   @Generated
   public class_243 getPosition() {
      return this.position;
   }

   @Generated
   public class_243 getVelocity() {
      return this.velocity;
   }

   @Generated
   public int getIndex() {
      return this.index;
   }

   @Generated
   public Kf39dc27225f4d9fe6e getTimer() {
      return this.timer;
   }

   @Generated
   public Ka91715a8c4e3f3b7c4 getAlphaAnimation() {
      return this.alphaAnimation;
   }

   @Generated
   public float getAlpha() {
      return this.alpha;
   }

   @Generated
   public void setPosition(class_243 position) {
      this.position = position;
   }

   @Generated
   public void setVelocity(class_243 velocity) {
      this.velocity = velocity;
   }

   @Generated
   public void setAlpha(float alpha) {
      this.alpha = alpha;
   }
}
