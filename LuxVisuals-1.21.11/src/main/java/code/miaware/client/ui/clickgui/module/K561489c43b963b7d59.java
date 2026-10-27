package code.miaware.client.ui.clickgui.module;

import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import lombok.Generated;

public abstract class K561489c43b963b7d59 {
   private final Ka91715a8c4e3f3b7c4 openAnimation = new Ka91715a8c4e3f3b7c4();
   private boolean open = false;

   public void updateOpen() {
      this.openAnimation.update();
      double target = this.open ? (double)1.0F : (double)0.0F;
      long duration = this.open ? 210L : 160L;
      Kf3b62ecfba0c3544e6 easing = this.open ? Kf3b62ecfba0c3544e6.QUINT_OUT : Kf3b62ecfba0c3544e6.CUBIC_OUT;
      this.openAnimation.run(target, duration, easing);
   }

   public void toggleOpen() {
      this.open = !this.open;
   }

   @Generated
   public Ka91715a8c4e3f3b7c4 getOpenAnimation() {
      return this.openAnimation;
   }

   @Generated
   public boolean isOpen() {
      return this.open;
   }

   @Generated
   public void setOpen(boolean open) {
      this.open = open;
   }
}
