package code.miaware.client.features.modules.render;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import lombok.Generated;

@ModuleRegister(
   name = "Custom F5",
   category = Kf76f042931183af5ba.RENDER,
   description = "Настройка камеры от третьего лица"
)
public class Kd8c2d764e94ca1dee2 extends Kab8a8c478f7d0077cd {
   private static final Kd8c2d764e94ca1dee2 instance = new Kd8c2d764e94ca1dee2();
   private final Kb9e875ead00e0d03bd cameraDistance = (new Kb9e875ead00e0d03bd("Дистанция камеры")).value(4.0F).range(2.0F, 10.0F).step(0.5F);

   public Kd8c2d764e94ca1dee2() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.cameraDistance});
   }

   public void onEvent() {
   }

   public float getCameraDistance() {
      return !this.isEnabled() ? 4.0F : this.cameraDistance.getValue();
   }

   @Generated
   public static Kd8c2d764e94ca1dee2 getInstance() {
      return instance;
   }
}
