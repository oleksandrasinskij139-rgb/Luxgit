package code.miaware.api.utils.render;

import jdk.jfr.Category;
import jdk.jfr.Event;
import jdk.jfr.EventType;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.StackTrace;
import jdk.jfr.Threshold;

@Name("luxvisuals.HudWidget")
@Label("HUD widget render")
@Category({"LuxVisuals", "Rendering"})
@StackTrace(false)
@Threshold("100 us")
public final class K142f6ac381bd580456 extends Event {
   private static final EventType TYPE = EventType.getEventType(K142f6ac381bd580456.class);
   @Label("Widget")
   public String widget;

   public static K142f6ac381bd580456 start(String widget) {
      if (!TYPE.isEnabled()) {
         return null;
      } else {
         K142f6ac381bd580456 sample = new K142f6ac381bd580456();
         sample.widget = widget;
         sample.begin();
         return sample;
      }
   }

   public static void finish(K142f6ac381bd580456 sample) {
      if (sample != null) {
         sample.end();
         if (sample.shouldCommit()) {
            sample.commit();
         }

      }
   }
}
