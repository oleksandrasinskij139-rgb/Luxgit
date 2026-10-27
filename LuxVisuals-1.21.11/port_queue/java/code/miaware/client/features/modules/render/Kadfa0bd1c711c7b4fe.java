package code.miaware.client.features.modules.render;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import lombok.Generated;

@ModuleRegister(
   name = "AspectRatio",
   category = Kf76f042931183af5ba.RENDER,
   description = "Изменяет соотношение сторон экрана"
)
public class Kadfa0bd1c711c7b4fe extends Kab8a8c478f7d0077cd {
   private static final Kadfa0bd1c711c7b4fe instance = new Kadfa0bd1c711c7b4fe();
   public final Kbd66519397e9a89967 preset = (new Kbd66519397e9a89967("Ratio")).value("16:9").values("16:9", "16:10", "21:9", "4:3", "Custom");
   public final Kb9e875ead00e0d03bd customRatio = (new Kb9e875ead00e0d03bd("Custom ratio")).value(1.78F).range(0.5F, 3.0F).step(0.01F).setVisible(() -> this.preset.is("Custom"));

   public Kadfa0bd1c711c7b4fe() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.preset, this.customRatio});
   }

   public void onEvent() {
   }

   public float getAspectRatioValue() {
      float var10000;
      switch ((String)this.preset.getValue()) {
         case "16:10" -> var10000 = 1.6F;
         case "21:9" -> var10000 = 2.3333333F;
         case "4:3" -> var10000 = 1.3333334F;
         case "Custom" -> var10000 = this.customRatio.getValue();
         default -> var10000 = 1.7777778F;
      }

      return var10000;
   }

   @Generated
   public static Kadfa0bd1c711c7b4fe getInstance() {
      return instance;
   }
}
