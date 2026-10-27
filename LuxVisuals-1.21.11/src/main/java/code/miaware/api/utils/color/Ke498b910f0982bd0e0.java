package code.miaware.api.utils.color;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_1011;

public class Ke498b910f0982bd0e0 {
   private static final int SAMPLE_SIZE = 30;
   private static final int MIN_SATURATION = 25;
   private static final int MIN_BRIGHTNESS = 35;

   public static Color extractAccentColor(class_1011 image) {
      if (image == null) {
         return null;
      } else {
         try {
            int width = image.method_4307();
            int height = image.method_4323();
            if (width > 0 && height > 0) {
               List<Color> colors = new ArrayList();
               int stepX = Math.max(1, width / 30);
               int stepY = Math.max(1, height / 30);

               for(int x = 0; x < width; x += stepX) {
                  for(int y = 0; y < height; y += stepY) {
                     try {
                        int abgr = image.method_4315(x, y);
                        int a = abgr >> 24 & 255;
                        int b = abgr >> 16 & 255;
                        int g = abgr >> 8 & 255;
                        int r = abgr & 255;
                        if (a >= 128) {
                           Color color = new Color(r, g, b);
                           float[] hsb = Color.RGBtoHSB(r, g, b, (float[])null);
                           float saturation = hsb[1] * 100.0F;
                           float brightness = hsb[2] * 100.0F;
                           if (saturation >= 25.0F && brightness >= 35.0F && brightness <= 95.0F) {
                              colors.add(color);
                           }
                        }
                     } catch (Exception var17) {
                     }
                  }
               }

               if (colors.isEmpty()) {
                  return null;
               } else {
                  return K18b63ab5e7751544d1(colors);
               }
            } else {
               return null;
            }
         } catch (Exception var18) {
            return null;
         }
      }
   }

   private static Color K18b63ab5e7751544d1(List<Color> colors) {
      if (colors.isEmpty()) {
         return null;
      } else {
         Map<Color, Integer> colorGroups = new HashMap();
         int TOLERANCE = 50;

         for(Color color : colors) {
            boolean foundGroup = false;

            for(Color groupColor : new ArrayList(colorGroups.keySet())) {
               if (K3c709d31b17f776259(color, groupColor) < (double)50.0F) {
                  colorGroups.put(groupColor, (Integer)colorGroups.get(groupColor) + 1);
                  foundGroup = true;
                  break;
               }
            }

            if (!foundGroup) {
               colorGroups.put(color, 1);
            }
         }

         Color dominantColor = null;
         int maxCount = 0;

         for(Map.Entry<Color, Integer> entry : colorGroups.entrySet()) {
            if ((Integer)entry.getValue() > maxCount) {
               maxCount = (Integer)entry.getValue();
               dominantColor = (Color)entry.getKey();
            }
         }

         if (dominantColor != null) {
            return K371cb2cd0831eb5bba(dominantColor);
         } else {
            return null;
         }
      }
   }

   private static double K3c709d31b17f776259(Color c1, Color c2) {
      int dr = c1.getRed() - c2.getRed();
      int dg = c1.getGreen() - c2.getGreen();
      int db = c1.getBlue() - c2.getBlue();
      return Math.sqrt((double)(dr * dr + dg * dg + db * db));
   }

   private static Color K371cb2cd0831eb5bba(Color color) {
      float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), (float[])null);
      float saturation = Math.min(1.0F, hsb[1] * 1.5F);
      float brightness = hsb[2];
      if (brightness < 0.4F) {
         brightness = Math.min(0.65F, brightness * 1.4F);
      }

      return Color.getHSBColor(hsb[0], saturation, brightness);
   }
}
