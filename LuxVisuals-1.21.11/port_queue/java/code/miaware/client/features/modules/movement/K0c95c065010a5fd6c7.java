package code.miaware.client.features.modules.movement;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import lombok.Generated;

@ModuleRegister(
   name = "Item Physic",
   category = Kf76f042931183af5ba.MOVEMENT,
   description = "Добавляет физику выброшенным предметам"
)
public class K0c95c065010a5fd6c7 extends Kab8a8c478f7d0077cd {
   private static final K0c95c065010a5fd6c7 instance = new K0c95c065010a5fd6c7();
   public final Kbd66519397e9a89967 mode;
   public final Kb9e875ead00e0d03bd layingScale;

   public K0c95c065010a5fd6c7() {
      this.mode = (new Kbd66519397e9a89967("Режим")).value(K1257a42c13af7578a8.ROTATE_360).values((Enum[])K1257a42c13af7578a8.values());
      this.layingScale = (new Kb9e875ead00e0d03bd("Размер (Лежачий)")).value(0.5F).range(0.1F, 1.0F).step(0.05F).setVisible(() -> ((String)this.mode.getValue()).equals(K1257a42c13af7578a8.LAYING.getName()));
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.mode, this.layingScale});
   }

   public void onEvent() {
   }

   @Generated
   public static K0c95c065010a5fd6c7 getInstance() {
      return instance;
   }
}
