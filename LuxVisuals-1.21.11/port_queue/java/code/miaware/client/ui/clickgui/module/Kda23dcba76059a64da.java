package code.miaware.client.ui.clickgui.module;

import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;

public abstract class Kda23dcba76059a64da extends K47d6c4891dc9ff4469 {
   private final K561489c43b963b7d59 expandableBase = new K561489c43b963b7d59() {
   };

   public Kda23dcba76059a64da(Kdced9e23f1e229ba9d<?> setting) {
      super(setting);
   }

   public void updateOpen() {
      this.expandableBase.updateOpen();
   }

   public void toggleOpen() {
      this.expandableBase.toggleOpen();
   }

   public boolean isOpen() {
      return this.expandableBase.isOpen();
   }

   public void setOpen(boolean value) {
      this.expandableBase.setOpen(value);
   }

   public float getValue() {
      return (float)this.expandableBase.getOpenAnimation().getValue();
   }

   public Ka91715a8c4e3f3b7c4 getAnim() {
      return this.expandableBase.getOpenAnimation();
   }

   public boolean isNotOver() {
      return (double)this.getValue() < 0.8;
   }
}
