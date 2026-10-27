package code.miaware.api.utils.auction;

import code.miaware.api.module.setting.Ke298bfdac4e34a00d9;
import lombok.Generated;

public enum K4927cecb08f74e3930 implements Ke298bfdac4e34a00d9 {
   FUN_TIME("Fun Time"),
   SPOOKY_TIME("Spooky Time"),
   HOLY_WORLD("Holy World"),
   REALLY_WORLD("Really World");

   private final String name;

   public String getName() {
      return this.name;
   }

   @Generated
   private K4927cecb08f74e3930(final String name) {
      this.name = name;
   }

   // $FF: synthetic method
   private static K4927cecb08f74e3930[] $values() {
      return new K4927cecb08f74e3930[]{FUN_TIME, SPOOKY_TIME, HOLY_WORLD, REALLY_WORLD};
   }
}
