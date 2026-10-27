package code.miaware.client.features.modules.render;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import lombok.Generated;

@ModuleRegister(
   name = "Saturation",
   category = Kf76f042931183af5ba.RENDER,
   description = "Настройки цветокоррекции игры"
)
public class K9f8f1b6b6c0347c2f9 extends Kab8a8c478f7d0077cd {
   private static final K9f8f1b6b6c0347c2f9 instance = new K9f8f1b6b6c0347c2f9();
   public final Kb9e875ead00e0d03bd saturation = (new Kb9e875ead00e0d03bd("Насыщенность")).value(1.0F).range(0.0F, 3.0F).step(0.05F);
   public final Kb9e875ead00e0d03bd brightness = (new Kb9e875ead00e0d03bd("Яркость")).value(1.0F).range(0.0F, 2.0F).step(0.05F);
   public final Kb9e875ead00e0d03bd contrast = (new Kb9e875ead00e0d03bd("Контраст")).value(1.0F).range(0.0F, 2.0F).step(0.05F);
   public final Kb9e875ead00e0d03bd redTint = (new Kb9e875ead00e0d03bd("Красный оттенок")).value(1.0F).range(0.0F, 2.0F).step(0.05F);
   public final Kb9e875ead00e0d03bd greenTint = (new Kb9e875ead00e0d03bd("Зелёный оттенок")).value(1.0F).range(0.0F, 2.0F).step(0.05F);
   public final Kb9e875ead00e0d03bd blueTint = (new Kb9e875ead00e0d03bd("Синий оттенок")).value(1.0F).range(0.0F, 2.0F).step(0.05F);

   public K9f8f1b6b6c0347c2f9() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.saturation, this.brightness, this.contrast, this.redTint, this.greenTint, this.blueTint});
   }

   public void onEvent() {
   }

   public float getIntensity() {
      return this.isEnabled() ? this.saturation.getValue() : 1.0F;
   }

   public float getBrightness() {
      return this.isEnabled() ? this.brightness.getValue() : 1.0F;
   }

   public float getContrast() {
      return this.isEnabled() ? this.contrast.getValue() : 1.0F;
   }

   public float getRedTint() {
      return this.isEnabled() ? this.redTint.getValue() : 1.0F;
   }

   public float getGreenTint() {
      return this.isEnabled() ? this.greenTint.getValue() : 1.0F;
   }

   public float getBlueTint() {
      return this.isEnabled() ? this.blueTint.getValue() : 1.0F;
   }

   @Generated
   public static K9f8f1b6b6c0347c2f9 getInstance() {
      return instance;
   }
}
