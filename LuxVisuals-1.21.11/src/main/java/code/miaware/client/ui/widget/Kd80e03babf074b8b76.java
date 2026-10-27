package code.miaware.client.ui.widget;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public final class Kd80e03babf074b8b76 {
   public final String icon;
   public final Color iconColor;
   public final List<K5af30bf5e82a937089> parts = new ArrayList();

   public Kd80e03babf074b8b76(String icon, Color iconColor) {
      this.icon = icon;
      this.iconColor = iconColor;
   }

   public Kd80e03babf074b8b76 add(String text, Color color) {
      this.parts.add(K5af30bf5e82a937089.of(text, color));
      return this;
   }

   public Kd80e03babf074b8b76 addBold(String text, Color color) {
      this.parts.add(K5af30bf5e82a937089.bold(text, color));
      return this;
   }
}
