package code.miaware.client.features.modules.movement;

import code.miaware.api.module.setting.Ke298bfdac4e34a00d9;
import lombok.Generated;

public enum K1257a42c13af7578a8 implements Ke298bfdac4e34a00d9 {
   ROTATE_360("360"),
   LAYING("Лежачий");

   private final String name;

   public String getName() {
      return this.name;
   }

   @Generated
   private K1257a42c13af7578a8(final String name) {
      this.name = name;
   }

   // $FF: synthetic method
   private static K1257a42c13af7578a8[] $values() {
      return new K1257a42c13af7578a8[]{ROTATE_360, LAYING};
   }
}
