package code.miaware.api.utils.color;

import code.miaware.client.ui.theme.K6ea7546faa48739c0d;
import code.miaware.client.ui.theme.Theme;
import java.awt.Color;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Generated;

public final class Kb18d549c13309af9a5 {
   private static final Map<String, Color> gradientCache = new ConcurrentHashMap(360);
   private static long lastCacheClear = System.currentTimeMillis();
   private static final long CACHE_CLEAR_INTERVAL_MS = 5000L;

   public static void clearThemeCache() {
      gradientCache.clear();
   }

   public static Theme currentTheme() {
      return K6ea7546faa48739c0d.getInstance().getCurrentTheme();
   }

   private static Color K89121eb7deab982c5d(Color color, int alpha) {
      int finalAlpha = (int)((float)color.getAlpha() / 255.0F * (float)alpha);
      return K80ee108ea129adac11.setAlpha(color, finalAlpha);
   }

   public static Color gradient(int index) {
      return gradient(index, 255);
   }

   public static Color gradient(int index, int alpha) {
      long now = System.currentTimeMillis();
      if (now - lastCacheClear > 5000L) {
         gradientCache.clear();
         lastCacheClear = now;
      }

      String cacheKey = index + "_" + alpha;
      Color cached = (Color)gradientCache.get(cacheKey);
      if (cached != null) {
         return cached;
      } else {
         Color result = K89121eb7deab982c5d(K80ee108ea129adac11.gradient(15, index, primary(alpha), secondary(alpha)), alpha);
         gradientCache.put(cacheKey, result);
         return result;
      }
   }

   public static Color blur() {
      return blur(255);
   }

   public static Color blur(int alpha) {
      return K89121eb7deab982c5d(currentTheme().getBlurColor(), alpha);
   }

   public static Color widgetBlur() {
      return widgetBlur(255);
   }

   public static Color widgetBlur(int alpha) {
      return K89121eb7deab982c5d(currentTheme().getWidgetBlurColor(), alpha);
   }

   public static Color backgroundBlur() {
      return backgroundBlur(255);
   }

   public static Color backgroundBlur(int alpha) {
      return K89121eb7deab982c5d(currentTheme().getBackgroundBlurColor(), alpha);
   }

   public static Color primary() {
      return primary(255);
   }

   public static Color primary(int alpha) {
      return K258ef67f9722180a72.isEnabled() ? K258ef67f9722180a72.getAnimatedColor(alpha) : K89121eb7deab982c5d(currentTheme().getPrimaryColor(), alpha);
   }

   public static Color secondary() {
      return secondary(255);
   }

   public static Color secondary(int alpha) {
      return K89121eb7deab982c5d(currentTheme().getSecondaryColor(), alpha);
   }

   public static Color knob() {
      return knob(255);
   }

   public static Color knob(int alpha) {
      return K89121eb7deab982c5d(currentTheme().getKnobColor(), alpha);
   }

   public static Color inactiveKnob() {
      return inactiveKnob(255);
   }

   public static Color inactiveKnob(int alpha) {
      return K89121eb7deab982c5d(currentTheme().getInactiveKnobColor(), alpha);
   }

   public static Color textColor() {
      return textColor(255);
   }

   public static Color textColor(int alpha) {
      return K89121eb7deab982c5d(currentTheme().getTextColor(), alpha);
   }

   public static Color inactiveTextColor() {
      return inactiveTextColor(255);
   }

   public static Color inactiveTextColor(int alpha) {
      return K89121eb7deab982c5d(currentTheme().getInactiveTextColor(), alpha);
   }

   public static Color positiveColor() {
      return positiveColor(255);
   }

   public static Color positiveColor(int alpha) {
      return K89121eb7deab982c5d(currentTheme().getPositiveColor(), alpha);
   }

   public static Color middleColor() {
      return middleColor(255);
   }

   public static Color middleColor(int alpha) {
      return K89121eb7deab982c5d(currentTheme().getMiddleColor(), alpha);
   }

   public static Color negativeColor() {
      return negativeColor(255);
   }

   public static Color negativeColor(int alpha) {
      return K89121eb7deab982c5d(currentTheme().getNegativeColor(), alpha);
   }

   @Generated
   private Kb18d549c13309af9a5() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
