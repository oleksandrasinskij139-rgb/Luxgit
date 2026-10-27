package code.miaware.client.features.modules.render;

import code.miaware.api.module.setting.Ke298bfdac4e34a00d9;
import lombok.Generated;

enum K31c42aa8803f61159b implements Ke298bfdac4e34a00d9 {
   NO_CHANGE("No change"),
   DAWN("Dawn"),
   DAY("Day"),
   NOON("Noon"),
   DUSK("Dusk"),
   NIGHT("Night"),
   MID_NIGHT("Mid Night");

   private final String name;

   public String getName() {
      return this.name;
   }

   @Generated
   private K31c42aa8803f61159b(final String name) {
      this.name = name;
   }

   // $FF: synthetic method
   private static K31c42aa8803f61159b[] $values() {
      return new K31c42aa8803f61159b[]{NO_CHANGE, DAWN, DAY, NOON, DUSK, NIGHT, MID_NIGHT};
   }
}
