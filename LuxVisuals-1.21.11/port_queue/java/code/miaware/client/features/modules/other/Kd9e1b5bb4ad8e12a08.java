package code.miaware.client.features.modules.other;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import lombok.Generated;

@ModuleRegister(
   name = "ChatHeads",
   category = Kf76f042931183af5ba.OTHER,
   description = "Головы игроков в чате"
)
public class Kd9e1b5bb4ad8e12a08 extends Kab8a8c478f7d0077cd {
   private static final Kd9e1b5bb4ad8e12a08 instance = new Kd9e1b5bb4ad8e12a08();
   private final K00fef91b3d4d1b3ecc shadow = (new K00fef91b3d4d1b3ecc("Тень")).value(true);
   private final K00fef91b3d4d1b3ecc offsetSystem = (new K00fef91b3d4d1b3ecc("Смещать системные")).value(true);

   public Kd9e1b5bb4ad8e12a08() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.shadow, this.offsetSystem});
   }

   public void onEvent() {
   }

   @Generated
   public static Kd9e1b5bb4ad8e12a08 getInstance() {
      return instance;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getShadow() {
      return this.shadow;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getOffsetSystem() {
      return this.offsetSystem;
   }
}
