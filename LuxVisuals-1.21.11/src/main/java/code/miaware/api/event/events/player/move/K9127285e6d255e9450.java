package code.miaware.api.event.events.player.move;

import code.miaware.api.event.events.Event;
import lombok.Generated;

public class K9127285e6d255e9450 extends Event<K363439524bd338fc89> {
   private static final K9127285e6d255e9450 instance = new K9127285e6d255e9450();

   public boolean call(K363439524bd338fc89 any) {
      super.call(any);
      return any.isSprint();
   }

   @Generated
   public static K9127285e6d255e9450 getInstance() {
      return instance;
   }
}
