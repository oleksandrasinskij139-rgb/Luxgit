package code.miaware.client.features.modules.render;

import code.miaware.api.module.setting.Ke298bfdac4e34a00d9;
import lombok.Generated;

public enum K2b73b8449b9c4b236e implements Ke298bfdac4e34a00d9 {
   NO_CHANGE("No change"),
   SUNNY("Sunny"),
   RAINY("Rainy"),
   SNOWY("Snowy"),
   THUNDER("Thunder");

   private final String name;

   public String getName() {
      return this.name;
   }

   @Generated
   private K2b73b8449b9c4b236e(final String name) {
      this.name = name;
   }

   // $FF: synthetic method
   private static K2b73b8449b9c4b236e[] $values() {
      return new K2b73b8449b9c4b236e[]{NO_CHANGE, SUNNY, RAINY, SNOWY, THUNDER};
   }
}
