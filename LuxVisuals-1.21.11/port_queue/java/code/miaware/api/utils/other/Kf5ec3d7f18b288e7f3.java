package code.miaware.api.utils.other;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.render.fonts.K3bb226f78688ad2f7f;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2572;
import net.minecraft.class_2583;
import net.minecraft.class_2588;
import net.minecraft.class_5250;
import net.minecraft.class_5251;
import net.minecraft.class_7417;
import net.minecraft.class_746;
import net.minecraft.class_8828;

public final class Kf5ec3d7f18b288e7f3 implements K4600bef438dafc1a9c {
   public static List<K3bb226f78688ad2f7f> parseTextToColoredGlyphs(class_2561 text) {
      List<K3bb226f78688ad2f7f> result = new ArrayList();
      K04ac78dd634fa1482c(text, -1, result);
      return result;
   }

   private static void K04ac78dd634fa1482c(class_2561 text, int currentColor, List<K3bb226f78688ad2f7f> result) {
      class_2583 style = text.method_10866();
      int color = style.method_10973() != null ? style.method_10973().method_27716() | -16777216 : currentColor;
      class_7417 content = text.method_10851();
      String raw = "";
      if (content instanceof class_8828.class_2585 var7) {
         class_8828.class_2585 var10000 = var7;

         try {
            var17 = var10000.comp_737();
         } catch (Throwable var12) {
            throw new MatchException(var12.toString(), var12);
         }

         String string = var17;
         raw = string;
      } else if (content instanceof class_2588 translatable) {
         raw = translatable.method_11022();
      } else if (content instanceof class_2572 keybind) {
         raw = keybind.method_10901();
      }

      raw = Kd10bcb33e37f629df6.replaceSymbols(raw);

      for(int i = 0; i < raw.length(); ++i) {
         char c = raw.charAt(i);
         if ((c == 3618 || c == 3591) && i + 1 < raw.length()) {
            ++i;
         } else {
            result.add(new K3bb226f78688ad2f7f(c, color));
         }
      }

      for(class_2561 sibling : text.method_10855()) {
         K04ac78dd634fa1482c(sibling, color, result);
      }

   }

   public static void sendMessage(String message) {
      class_746 var10000 = mc.field_1724;
      class_5250 var10001 = class_2561.method_43470("").method_10852(gradient("lux visuals", true));
      String var10002 = String.valueOf(class_124.field_1080);
      var10000.method_7353(var10001.method_10852(class_2561.method_43470(var10002 + " >> " + String.valueOf(class_124.field_1070) + message)), false);
   }

   public static String getDurationText(int ticks) {
      if (ticks == -1) {
         return "**:**";
      } else {
         int seconds = ticks / 20;
         int minutes = seconds / 60;
         int hours = minutes / 60;
         minutes %= 60;
         int remainingSeconds = seconds % 60;
         if (hours > 0) {
            return String.format("%d:%02d:%02d", hours, minutes, remainingSeconds);
         } else {
            return minutes > 0 ? String.format("%d:%02d", minutes, remainingSeconds) : String.format("%ds", seconds);
         }
      }
   }

   public static class_5250 gradient(String message, boolean bold) {
      class_5250 text = class_2561.method_43473();
      int length = message.length();

      for(int i = 0; i < length; ++i) {
         Color color = K80ee108ea129adac11.gradient((float)i / (float)(length - 1), Kb18d549c13309af9a5.primary(), Kb18d549c13309af9a5.secondary());
         text.method_10852(class_2561.method_43470(String.valueOf(message.charAt(i))).method_10862(class_2583.field_24360.method_27703(class_5251.method_27717(color.getRGB())).method_10982(bold)));
      }

      return text;
   }

   @Generated
   private Kf5ec3d7f18b288e7f3() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
