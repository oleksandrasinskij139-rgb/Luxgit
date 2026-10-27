package code.miaware.api.utils.color;

import code.miaware.client.ui.theme.K6ea7546faa48739c0d;
import java.awt.Color;

public class K258ef67f9722180a72 {
   private static long lastUpdateTime = System.currentTimeMillis();
   private static float animationTime = 0.0F;
   private static final float ANIMATION_SPEED = 0.15F;
   private static boolean enabled = true;

   public static void setEnabled(boolean value) {
      enabled = value;
   }

   public static boolean isEnabled() {
      return enabled;
   }

   public static void update() {
      long currentTime = System.currentTimeMillis();
      float deltaSeconds = (float)(currentTime - lastUpdateTime) / 1000.0F;
      lastUpdateTime = currentTime;

      for(animationTime += deltaSeconds * 0.15F; animationTime >= 1.0F; --animationTime) {
      }

   }

   public static Color getAnimatedColor(int alpha) {
      if (!enabled) {
         Color primaryColor = K6ea7546faa48739c0d.getInstance().getCurrentTheme().getPrimaryColor();
         return K80ee108ea129adac11.setAlpha(primaryColor, alpha);
      } else {
         Color primary = K6ea7546faa48739c0d.getInstance().getCurrentTheme().getPrimaryColor();
         Color secondary = K6ea7546faa48739c0d.getInstance().getCurrentTheme().getSecondaryColor();
         float t = (float)(Math.sin((double)animationTime * Math.PI * (double)2.0F) * (double)0.5F + (double)0.5F);
         return K80ee108ea129adac11.setAlpha(Kb40e1cf3cdef370e66(primary, secondary, t), alpha);
      }
   }

   public static Color getAnimatedColor() {
      return getAnimatedColor(255);
   }

   private static Color Kb40e1cf3cdef370e66(Color c1, Color c2, float t) {
      t = Math.max(0.0F, Math.min(1.0F, t));
      int r = (int)((float)c1.getRed() + (float)(c2.getRed() - c1.getRed()) * t);
      int g = (int)((float)c1.getGreen() + (float)(c2.getGreen() - c1.getGreen()) * t);
      int b = (int)((float)c1.getBlue() + (float)(c2.getBlue() - c1.getBlue()) * t);
      int a = (int)((float)c1.getAlpha() + (float)(c2.getAlpha() - c1.getAlpha()) * t);
      return new Color(r, g, b, a);
   }

   public static void reset() {
      animationTime = 0.0F;
      lastUpdateTime = System.currentTimeMillis();
   }
}
