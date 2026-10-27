package code.miaware.client.ui.widget.overlay;

import java.awt.Color;
import net.minecraft.class_2960;

final class Kc6773ac34c8c8cb28f {
   private final String key;
   private final class_2960 id;
   private final String name;
   private final String durationText;
   private final Color durationColor;
   private final Color accentColor;
   private final int durationTicks;
   private final boolean urgent;
   private final boolean infinite;

   private Kc6773ac34c8c8cb28f(String key, class_2960 id, String name, String durationText, Color durationColor, Color accentColor, int durationTicks, boolean urgent, boolean infinite) {
      this.key = key;
      this.id = id;
      this.name = name;
      this.durationText = durationText;
      this.durationColor = durationColor;
      this.accentColor = accentColor;
      this.durationTicks = durationTicks;
      this.urgent = urgent;
      this.infinite = infinite;
   }
}
