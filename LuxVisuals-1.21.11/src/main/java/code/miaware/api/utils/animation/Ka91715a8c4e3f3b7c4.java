package code.miaware.api.utils.animation;

import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import lombok.Generated;

public class Ka91715a8c4e3f3b7c4 {
   private long start = 0L;
   private long duration = 0L;
   private double fromValue = (double)0.0F;
   private double toValue = (double)0.0F;
   private double value = (double)0.0F;
   private Kf3b62ecfba0c3544e6 easing;

   public Ka91715a8c4e3f3b7c4() {
      this.easing = Kf3b62ecfba0c3544e6.LINEAR;
   }

   public Ka91715a8c4e3f3b7c4 run(float valueTo, long duration, Kf3b62ecfba0c3544e6 easing) {
      return this.run((double)valueTo, duration, easing, false);
   }

   public Ka91715a8c4e3f3b7c4 run(double valueTo, long duration, Kf3b62ecfba0c3544e6 easing) {
      return this.run(valueTo, duration, easing, false);
   }

   public Ka91715a8c4e3f3b7c4 run(double valueTo, long duration, Kf3b62ecfba0c3544e6 easing, boolean safe) {
      if (!this.check(safe, valueTo)) {
         this.easing = easing;
         this.duration = duration;
         this.start = System.currentTimeMillis();
         this.fromValue = this.value;
         this.toValue = valueTo;
      }

      return this;
   }

   public boolean update() {
      boolean alive = this.isAlive();
      if (alive) {
         this.value = Ka0484a23398fb5f9ac.interpolate(this.fromValue, this.toValue, this.easing.apply(this.K8407c6999892eef6ef()));
      } else {
         this.start = 0L;
         this.value = this.toValue;
      }

      return alive;
   }

   public boolean isAlive() {
      return !this.isFinished();
   }

   public boolean isFinished() {
      return this.K8407c6999892eef6ef() >= (double)1.0F;
   }

   private double K8407c6999892eef6ef() {
      return (double)(System.currentTimeMillis() - this.start) / (double)this.duration;
   }

   private boolean check(boolean safe, double valueTo) {
      return safe && this.isAlive() && (valueTo == this.fromValue || valueTo == this.toValue || valueTo == this.value);
   }

   @Generated
   public void setDuration(long duration) {
      this.duration = duration;
   }

   @Generated
   public double getFromValue() {
      return this.fromValue;
   }

   @Generated
   public double getToValue() {
      return this.toValue;
   }

   @Generated
   public void setValue(double value) {
      this.value = value;
   }

   @Generated
   public double getValue() {
      return this.value;
   }

   @Generated
   public void setEasing(Kf3b62ecfba0c3544e6 easing) {
      this.easing = easing;
   }
}
