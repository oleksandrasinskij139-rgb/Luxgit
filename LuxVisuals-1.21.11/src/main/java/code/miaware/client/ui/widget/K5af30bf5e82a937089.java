package code.miaware.client.ui.widget;

import java.awt.Color;

public record K5af30bf5e82a937089(String text, Color color, boolean bold) {
   public static K5af30bf5e82a937089 of(String text, Color color) {
      return new K5af30bf5e82a937089(text, color, false);
   }

   public static K5af30bf5e82a937089 bold(String text, Color color) {
      return new K5af30bf5e82a937089(text, color, true);
   }
}
