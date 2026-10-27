package code.miaware.api.utils.auction;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.class_124;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_2561;
import net.minecraft.class_1792.class_9635;

public class Kaddd606b5c5d59d4d2 implements K4600bef438dafc1a9c {
   private static final Pattern FUN_TIME_PRICE_PATTERN = Pattern.compile("Цен[аaAАыЫ]?:?\\s*([\\d,\\s\\.]+)", 2);
   public K4927cecb08f74e3930 currentMode;

   public Kaddd606b5c5d59d4d2() {
      this.currentMode = K4927cecb08f74e3930.FUN_TIME;
   }

   public int getPrice(class_1799 stack) {
      for(class_2561 text : stack.method_7950(class_9635.field_51353, mc.field_1724, class_1836.field_41070)) {
         String stripped = class_124.method_539(text.getString());
         String line = (stripped == null ? text.getString() : stripped).replace("§r", "").replace("¤", "").trim();
         if (this.currentMode == K4927cecb08f74e3930.FUN_TIME) {
            int funTimePrice = this.K776f162e9819cd793b(line);
            if (funTimePrice > 0) {
               return funTimePrice;
            }
         }

         String matchedPrefix = this.K402d1421b9b679caf4(line);
         if (matchedPrefix != null) {
            String numericValue = line.replace(matchedPrefix, "").replace(",", "").replace(".", "").replace(" ", "").trim();

            try {
               return Integer.parseInt(numericValue);
            } catch (NumberFormatException var9) {
               return -1;
            }
         }
      }

      return -1;
   }

   private int K776f162e9819cd793b(String line) {
      Matcher matcher = FUN_TIME_PRICE_PATTERN.matcher(line);
      if (!matcher.find()) {
         return -1;
      } else {
         try {
            String price = matcher.group(1).replaceAll("[,\\s\\.]", "");
            return Integer.parseInt(price);
         } catch (NumberFormatException var4) {
            return -1;
         }
      }
   }

   private String K402d1421b9b679caf4(String input) {
      for(String prefix : this.K7559cf8884c5048cce()) {
         if (input.startsWith(prefix)) {
            return prefix;
         }
      }

      return null;
   }

   private String[] K7559cf8884c5048cce() {
      String[] var10000;
      switch (this.currentMode) {
         case FUN_TIME -> var10000 = new String[]{"$ Цена $", "$ Ценa $", "Цена:", "Ценa:"};
         case SPOOKY_TIME -> var10000 = new String[]{"$ Цена: $"};
         case HOLY_WORLD -> var10000 = new String[]{"▪ Цена за 1 ед.:"};
         case REALLY_WORLD -> var10000 = new String[]{"Цена:"};
         default -> throw new MatchException((String)null, (Throwable)null);
      }

      return var10000;
   }
}
