package code.miaware.api.utils.animation.wrap.infinity;

import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.animation.wrap.Kab44bdaaca813a51d2;
import lombok.Generated;

public class Kf524b92407cde1e049 {
   private float output;
   private float endpoint;
   private Kf3b62ecfba0c3544e6 easing;
   private Kab44bdaaca813a51d2 animation;

   public Kf524b92407cde1e049() {
      this.easing = Kf3b62ecfba0c3544e6.LINEAR;
      this.animation = (new Kab44bdaaca813a51d2()).setSize(0.0F).setSpeed(0).setForward(false).setEasing(this.easing);
   }

   public float animate(float destination, int ms) {
      ms = Math.max(1, ms);
      this.output = this.endpoint - this.animation.get();
      this.endpoint = destination;
      if (this.output != this.endpoint - destination) {
         this.animation = (new Kab44bdaaca813a51d2()).setSize(this.endpoint - this.output).setSpeed(ms).setForward(false).setEasing(this.easing);
      }

      return this.output;
   }

   public boolean finished() {
      return this.output == this.endpoint || this.animation.finished() || this.animation.finished(false);
   }

   public float get() {
      this.output = this.endpoint - this.animation.get();
      return this.output;
   }

   public Kf524b92407cde1e049 easing(Kf3b62ecfba0c3544e6 easing) {
      this.easing = easing;
      return this;
   }

   @Generated
   public Kab44bdaaca813a51d2 getAnimation() {
      return this.animation;
   }
}
