package code.miaware.api.utils.animation.wrap;

import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import lombok.Generated;

public class Kab44bdaaca813a51d2 {
   private final Kf39dc27225f4d9fe6e timer = new Kf39dc27225f4d9fe6e();
   private int speed;
   private double size = (double)1.0F;
   private boolean forward;
   private Kf3b62ecfba0c3544e6 easing;

   public boolean finished(boolean forward) {
      boolean var10000;
      label25: {
         if (this.timer.finished((long)this.speed)) {
            if (forward) {
               if (this.forward) {
                  break label25;
               }
            } else if (!this.forward) {
               break label25;
            }
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public boolean finished() {
      return this.timer.finished((long)this.speed) && this.forward;
   }

   public Kab44bdaaca813a51d2 setForward(boolean forward) {
      if (this.forward != forward) {
         this.forward = forward;
         this.timer.setMillis((long)((double)System.currentTimeMillis() - (this.size - Math.min(this.size, (double)this.timer.getElapsedTime()))));
      }

      return this;
   }

   public Kab44bdaaca813a51d2 finish() {
      this.timer.setMillis(System.currentTimeMillis() - (long)this.speed);
      return this;
   }

   public Kab44bdaaca813a51d2 setEasing(Kf3b62ecfba0c3544e6 easing) {
      this.easing = easing;
      return this;
   }

   public Kab44bdaaca813a51d2 setSpeed(int speed) {
      this.speed = speed;
      return this;
   }

   public Kab44bdaaca813a51d2 setSize(float size) {
      this.size = (double)size;
      return this;
   }

   public float getLinear() {
      if (this.forward) {
         return this.timer.finished((long)this.speed) ? (float)this.size : (float)((double)this.timer.getElapsedTime() / (double)this.speed * this.size);
      } else {
         return this.timer.finished((long)this.speed) ? 0.0F : (float)(((double)1.0F - (double)this.timer.getElapsedTime() / (double)this.speed) * this.size);
      }
   }

   public float get() {
      if (this.forward) {
         return this.timer.finished((long)this.speed) ? (float)this.size : (float)(this.easing.apply((double)this.timer.getElapsedTime() / (double)this.speed) * this.size);
      } else {
         return this.timer.finished((long)this.speed) ? 0.0F : (float)(((double)1.0F - this.easing.apply((double)this.timer.getElapsedTime() / (double)this.speed)) * this.size);
      }
   }

   public float reversed() {
      return 1.0F - this.get();
   }

   public void reset() {
      this.timer.reset();
   }

   public void kuni() {
      if (this.finished()) {
         this.setForward(false);
      } else if (this.finished(false)) {
         this.setForward(true);
      }

   }

   @Generated
   public Kf39dc27225f4d9fe6e getTimer() {
      return this.timer;
   }

   @Generated
   public int getSpeed() {
      return this.speed;
   }

   @Generated
   public double getSize() {
      return this.size;
   }

   @Generated
   public Kf3b62ecfba0c3544e6 getEasing() {
      return this.easing;
   }

   @Generated
   public boolean isForward() {
      return this.forward;
   }
}
