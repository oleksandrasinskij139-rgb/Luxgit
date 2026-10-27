package code.miaware.api.utils.color;

import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import java.awt.Color;
import lombok.Generated;
import net.minecraft.class_3532;

public final class K80ee108ea129adac11 {
   public static float[] normalize(Color color) {
      return new float[]{(float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, (float)color.getAlpha() / 255.0F};
   }

   public static float[] normalize(int color) {
      int[] components = unpack(color);
      return new float[]{(float)components[0] / 255.0F, (float)components[1] / 255.0F, (float)components[2] / 255.0F, (float)components[3] / 255.0F};
   }

   public static int[] unpack(int color) {
      return new int[]{color >> 16 & 255, color >> 8 & 255, color & 255, color >> 24 & 255};
   }

   public static Color setAlpha(Color color, int alpha) {
      return new Color(color.getRed(), color.getGreen(), color.getBlue(), class_3532.method_15340(alpha, 0, 255));
   }

   public static Color gradient(int speed, int index, Color... colors) {
      int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
      angle = (angle > 180 ? 360 - angle : angle) + 180;
      int colorIndex = (int)((float)angle / 360.0F * (float)colors.length);
      if (colorIndex == colors.length) {
         --colorIndex;
      }

      Color color1 = colors[colorIndex];
      Color color2 = colors[colorIndex == colors.length - 1 ? 0 : colorIndex + 1];
      return interpolate(color1, color2, (float)angle / 360.0F * (float)colors.length - (float)colorIndex);
   }

   public static Color interpolate(Color to, Color from, double amount) {
      return interpolate(to, from, (float)amount);
   }

   public static Color interpolate(Color to, Color from, float amount) {
      float clampedAmount = 1.0F - class_3532.method_15363(amount, 0.0F, 1.0F);
      int red1 = to.getRed();
      int green1 = to.getGreen();
      int blue1 = to.getBlue();
      int alpha1 = to.getAlpha();
      int red2 = from.getRed();
      int green2 = from.getGreen();
      int blue2 = from.getBlue();
      int alpha2 = from.getAlpha();
      int interpolatedRed = Ka0484a23398fb5f9ac.interpolate(red1, red2, clampedAmount);
      int interpolatedGreen = Ka0484a23398fb5f9ac.interpolate(green1, green2, clampedAmount);
      int interpolatedBlue = Ka0484a23398fb5f9ac.interpolate(blue1, blue2, clampedAmount);
      int interpolatedAlpha = Ka0484a23398fb5f9ac.interpolate(alpha1, alpha2, clampedAmount);
      return new Color(interpolatedRed, interpolatedGreen, interpolatedBlue, interpolatedAlpha);
   }

   public static Color gradient(float progress, Color color1, Color color2) {
      if (progress < 0.0F || progress > 1.0F) {
         progress %= 1.0F;
         if (progress < 0.0F) {
            ++progress;
         }
      }

      int r = (int)((float)color1.getRed() + (float)(color2.getRed() - color1.getRed()) * progress);
      int g = (int)((float)color1.getGreen() + (float)(color2.getGreen() - color1.getGreen()) * progress);
      int b = (int)((float)color1.getBlue() + (float)(color2.getBlue() - color1.getBlue()) * progress);
      return new Color(r, g, b);
   }

   public static int gradient(int color1, int color2, float position, float totalWidth, float time, float offset) {
      float gradientLength = 18.0F / offset;
      float wavePosition = (time + position / (totalWidth * gradientLength)) % 1.0F;
      float factor = (float)Math.sin((double)wavePosition * Math.PI * (double)2.0F) * 0.5F + 0.5F;
      int a1 = color1 >> 24 & 255;
      int r1 = color1 >> 16 & 255;
      int g1 = color1 >> 8 & 255;
      int b1 = color1 & 255;
      int a2 = color2 >> 24 & 255;
      int r2 = color2 >> 16 & 255;
      int g2 = color2 >> 8 & 255;
      int b2 = color2 & 255;
      int a = (int)((float)a1 + (float)(a2 - a1) * factor);
      int r = (int)((float)r1 + (float)(r2 - r1) * factor);
      int g = (int)((float)g1 + (float)(g2 - g1) * factor);
      int b = (int)((float)b1 + (float)(b2 - b1) * factor);
      return a << 24 | r << 16 | g << 8 | b;
   }

   public static Color flashingColor(Color flashColor, Color defaultColor) {
      return flashingColor(flashColor, defaultColor, 1.0F);
   }

   public static Color flashingColor(Color flashColor, Color defaultColor, float alpha) {
      double time = (double)(System.currentTimeMillis() % 1000L) / (double)1000.0F;
      float flashFactor = (float)(Math.sin(time * Math.PI * (double)2.0F) * (double)0.5F + (double)0.5F);
      return flashingColor(flashColor, defaultColor, alpha, flashFactor);
   }

   public static Color flashingColor(Color flashColor, Color defaultColor, float alpha, float flashFactor) {
      float[] def = normalize(defaultColor);
      float[] flash = normalize(flashColor);
      return new Color((int)((flash[0] * flashFactor + def[0] * (1.0F - flashFactor)) * 255.0F), (int)((flash[1] * flashFactor + def[1] * (1.0F - flashFactor)) * 255.0F), (int)((flash[2] * flashFactor + def[2] * (1.0F - flashFactor)) * 255.0F), (int)(alpha * 255.0F));
   }

   public static Color astolfo(float offset, float saturation) {
      float hueStep = 90.0F;
      long currentTime = System.currentTimeMillis();
      float baseHue = (float)((currentTime / 10L + (long)((int)offset)) % 360L);
      float hue = (baseHue + offset * hueStep) % 360.0F;
      hue /= 360.0F;
      saturation = class_3532.method_15363(saturation, 0.0F, 1.0F);
      float brightness = 0.7F;
      return new Color(Color.HSBtoRGB(hue, saturation, brightness));
   }

   @Generated
   private K80ee108ea129adac11() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
