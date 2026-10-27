package code.miaware.client.features.modules.combat;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import lombok.Generated;

@ModuleRegister(
   name = "Counter",
   category = Kf76f042931183af5ba.COMBAT,
   description = "Показывает количество предметов в инвентаре"
)
public class K582e27fb3f07a5c101 extends Kab8a8c478f7d0077cd {
   private static final K582e27fb3f07a5c101 instance = new K582e27fb3f07a5c101();
   private final K00fef91b3d4d1b3ecc chorus = (new K00fef91b3d4d1b3ecc("Хорусы")).value(true);
   private final K00fef91b3d4d1b3ecc pearls = (new K00fef91b3d4d1b3ecc("Перки")).value(true);
   private final K00fef91b3d4d1b3ecc fireworks = (new K00fef91b3d4d1b3ecc("Фейерверки")).value(true);
   private final K00fef91b3d4d1b3ecc goldenApples = (new K00fef91b3d4d1b3ecc("Золотые яблоки")).value(true);
   private final K00fef91b3d4d1b3ecc enchantedApples = (new K00fef91b3d4d1b3ecc("Зачарованные яблоки")).value(true);
   private final K00fef91b3d4d1b3ecc totems = (new K00fef91b3d4d1b3ecc("Тотемы")).value(true);
   private final K00fef91b3d4d1b3ecc horizontal = (new K00fef91b3d4d1b3ecc("Горизонтально")).value(false);

   public K582e27fb3f07a5c101() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.chorus, this.pearls, this.fireworks, this.goldenApples, this.enchantedApples, this.totems, this.horizontal});
   }

   public void onEvent() {
   }

   @Generated
   public static K582e27fb3f07a5c101 getInstance() {
      return instance;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getChorus() {
      return this.chorus;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getPearls() {
      return this.pearls;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getFireworks() {
      return this.fireworks;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getGoldenApples() {
      return this.goldenApples;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getEnchantedApples() {
      return this.enchantedApples;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getTotems() {
      return this.totems;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getHorizontal() {
      return this.horizontal;
   }
}
