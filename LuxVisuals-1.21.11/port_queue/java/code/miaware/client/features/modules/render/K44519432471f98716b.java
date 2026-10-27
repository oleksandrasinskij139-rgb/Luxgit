package code.miaware.client.features.modules.render;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import lombok.Generated;

@ModuleRegister(
   name = "Smooth Camera",
   category = Kf76f042931183af5ba.RENDER,
   description = "Плавное следование камеры при поворотах"
)
public class K44519432471f98716b extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K44519432471f98716b instance = new K44519432471f98716b();
   public final Kb9e875ead00e0d03bd speed = (new Kb9e875ead00e0d03bd("Скорость")).value(0.08F).range(0.01F, 0.3F).step(0.01F);

   private K44519432471f98716b() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.speed});
   }

   public void onEvent() {
   }

   @Generated
   public static K44519432471f98716b getInstance() {
      return instance;
   }
}
