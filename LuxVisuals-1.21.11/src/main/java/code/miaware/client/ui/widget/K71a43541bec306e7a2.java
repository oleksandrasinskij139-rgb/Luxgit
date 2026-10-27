package code.miaware.client.ui.widget;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public final class K71a43541bec306e7a2 {
   public final List<K5af30bf5e82a937089> left = new ArrayList();
   public final List<K5af30bf5e82a937089> right = new ArrayList();
   public String badgeIcon;
   public float alpha = 1.0F;
   public float indent;

   public K71a43541bec306e7a2 indent(float indent) {
      this.indent = indent;
      return this;
   }

   public K71a43541bec306e7a2 left(String text, Color color) {
      this.left.add(K5af30bf5e82a937089.of(text, color));
      return this;
   }

   public K71a43541bec306e7a2 right(String text, Color color) {
      this.right.add(K5af30bf5e82a937089.of(text, color));
      return this;
   }

   public K71a43541bec306e7a2 rightBold(String text, Color color) {
      this.right.add(K5af30bf5e82a937089.bold(text, color));
      return this;
   }

   public K71a43541bec306e7a2 badge(String icon) {
      this.badgeIcon = icon;
      return this;
   }

   public K71a43541bec306e7a2 alpha(float alpha) {
      this.alpha = alpha;
      return this;
   }
}
