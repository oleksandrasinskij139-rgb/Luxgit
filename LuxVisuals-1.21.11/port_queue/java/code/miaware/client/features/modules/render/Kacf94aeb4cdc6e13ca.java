package code.miaware.client.features.modules.render;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import lombok.Generated;

@ModuleRegister(
   name = "Better Minecraft",
   category = Kf76f042931183af5ba.RENDER,
   description = "Красивые анимации открытия инвентаря, чата и таба"
)
public class Kacf94aeb4cdc6e13ca extends Kab8a8c478f7d0077cd {
   private static final Kacf94aeb4cdc6e13ca instance = new Kacf94aeb4cdc6e13ca();
   private final K00fef91b3d4d1b3ecc inventoryAnimation = (new K00fef91b3d4d1b3ecc("Анимация инвентаря")).value(true);
   private final K00fef91b3d4d1b3ecc chatAnimation = (new K00fef91b3d4d1b3ecc("Анимация чата")).value(true);
   private final K00fef91b3d4d1b3ecc tabAnimation = (new K00fef91b3d4d1b3ecc("Анимация таба")).value(true);
   private final K00fef91b3d4d1b3ecc hotbarAnimation = (new K00fef91b3d4d1b3ecc("Анимация хотбара")).value(true);
   private final K00fef91b3d4d1b3ecc f5Animation = (new K00fef91b3d4d1b3ecc("Анимация F5")).value(true);

   public Kacf94aeb4cdc6e13ca() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.inventoryAnimation, this.chatAnimation, this.tabAnimation, this.hotbarAnimation, this.f5Animation});
   }

   public void onEvent() {
   }

   @Generated
   public static Kacf94aeb4cdc6e13ca getInstance() {
      return instance;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getInventoryAnimation() {
      return this.inventoryAnimation;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getChatAnimation() {
      return this.chatAnimation;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getTabAnimation() {
      return this.tabAnimation;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getHotbarAnimation() {
      return this.hotbarAnimation;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getF5Animation() {
      return this.f5Animation;
   }
}
