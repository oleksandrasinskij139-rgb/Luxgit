package dev.luxvisuals.fix;

import java.util.regex.Pattern;

public final class Rebrand {
   private static final Pattern P = Pattern.compile("phantom(?:[ _-]?visuals?)?", 2);

   private Rebrand() {
   }

   public static String fix(String var0) {
      return var0 != null && var0.length() >= 7 && containsPhantom(var0) ? P.matcher(var0).replaceAll("LuxVisuals") : var0;
   }

   private static boolean containsPhantom(String var0) {
      int var1 = 0;

      for(int var2 = var0.length() - 6; var1 < var2; ++var1) {
         if ((var0.charAt(var1) | 32) == 112 && var0.regionMatches(true, var1, "phantom", 0, 7)) {
            return true;
         }
      }

      return false;
   }
}
