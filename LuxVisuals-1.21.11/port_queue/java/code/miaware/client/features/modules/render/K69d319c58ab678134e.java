package code.miaware.client.features.modules.render;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import lombok.Generated;

@ModuleRegister(
   name = "Item Inspect",
   category = Kf76f042931183af5ba.RENDER,
   description = "Поворот предмета в руке по всем осям"
)
public class K69d319c58ab678134e extends Kab8a8c478f7d0077cd {
   private static final K69d319c58ab678134e instance = new K69d319c58ab678134e();
   public final Kb9e875ead00e0d03bd mainHandRotationX = (new Kb9e875ead00e0d03bd("Основная рука X (вверх/вниз)")).value(0.0F).range(0.0F, 360.0F).step(1.0F);
   public final Kb9e875ead00e0d03bd mainHandRotationY = (new Kb9e875ead00e0d03bd("Основная рука Y (влево/вправо)")).value(0.0F).range(0.0F, 360.0F).step(1.0F);
   public final Kb9e875ead00e0d03bd mainHandRotationZ = (new Kb9e875ead00e0d03bd("Основная рука Z (наклон)")).value(0.0F).range(0.0F, 360.0F).step(1.0F);
   public final Kb9e875ead00e0d03bd offHandRotationX = (new Kb9e875ead00e0d03bd("Второстепенная рука X (вверх/вниз)")).value(0.0F).range(0.0F, 360.0F).step(1.0F);
   public final Kb9e875ead00e0d03bd offHandRotationY = (new Kb9e875ead00e0d03bd("Второстепенная рука Y (влево/вправо)")).value(0.0F).range(0.0F, 360.0F).step(1.0F);
   public final Kb9e875ead00e0d03bd offHandRotationZ = (new Kb9e875ead00e0d03bd("Второстепенная рука Z (наклон)")).value(0.0F).range(0.0F, 360.0F).step(1.0F);

   public K69d319c58ab678134e() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.mainHandRotationX, this.mainHandRotationY, this.mainHandRotationZ, this.offHandRotationX, this.offHandRotationY, this.offHandRotationZ});
   }

   public void onEvent() {
   }

   @Generated
   public static K69d319c58ab678134e getInstance() {
      return instance;
   }
}
