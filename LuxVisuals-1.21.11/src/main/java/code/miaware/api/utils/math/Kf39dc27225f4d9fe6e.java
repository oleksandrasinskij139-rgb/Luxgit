package code.miaware.api.utils.math;

import lombok.Generated;

public class Kf39dc27225f4d9fe6e {
   private long millis;

   public Kf39dc27225f4d9fe6e() {
      this.reset();
   }

   public boolean finished(float delay) {
      return System.currentTimeMillis() - this.millis >= (long)delay;
   }

   public boolean finished(long delay) {
      return System.currentTimeMillis() - this.millis >= delay;
   }

   public void reset() {
      this.millis = System.currentTimeMillis();
   }

   public long getElapsedTime() {
      return System.currentTimeMillis() - this.millis;
   }

   @Generated
   public long getMillis() {
      return this.millis;
   }

   @Generated
   public void setMillis(long millis) {
      this.millis = millis;
   }
}
