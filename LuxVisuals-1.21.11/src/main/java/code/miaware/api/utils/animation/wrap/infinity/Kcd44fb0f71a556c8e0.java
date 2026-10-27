package code.miaware.api.utils.animation.wrap.infinity;

import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;

public class Kcd44fb0f71a556c8e0 {
   private final Kf524b92407cde1e049 yaw = new Kf524b92407cde1e049();
   private final Kf524b92407cde1e049 pitch = new Kf524b92407cde1e049();

   public Keb4a85ca52fb971c57 animate(Keb4a85ca52fb971c57 rotation, int yawSpeed, int pitchSpeed) {
      return new Keb4a85ca52fb971c57(this.yaw.animate(rotation.getYaw(), yawSpeed), this.pitch.animate(rotation.getPitch(), pitchSpeed));
   }

   public float getYaw() {
      return this.yaw.get();
   }

   public float getPitch() {
      return this.pitch.get();
   }

   public Kcd44fb0f71a556c8e0 easing(Kf3b62ecfba0c3544e6 easing) {
      this.yaw.easing(easing);
      this.yaw.easing(easing);
      return this;
   }
}
