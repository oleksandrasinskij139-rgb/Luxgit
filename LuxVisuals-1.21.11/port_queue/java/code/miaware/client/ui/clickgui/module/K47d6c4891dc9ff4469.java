package code.miaware.client.ui.clickgui.module;

import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.client.ui.Kd6abad1c41237b249f;
import lombok.Generated;

public abstract class K47d6c4891dc9ff4469 extends Kd6abad1c41237b249f {
   private final Kdced9e23f1e229ba9d<?> setting;
   private final Ka91715a8c4e3f3b7c4 visibleAnimation = new Ka91715a8c4e3f3b7c4();

   public void updateHeight(float value) {
      this.setHeight(this.scaled(value));
   }

   @Generated
   public Kdced9e23f1e229ba9d<?> getSetting() {
      return this.setting;
   }

   @Generated
   public Ka91715a8c4e3f3b7c4 getVisibleAnimation() {
      return this.visibleAnimation;
   }

   @Generated
   public K47d6c4891dc9ff4469(Kdced9e23f1e229ba9d<?> setting) {
      this.setting = setting;
   }
}
