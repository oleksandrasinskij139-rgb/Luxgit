package dev.luxvisuals.menu;

public final class LuxTabs {
   private LuxTabs() {
   }

   public static boolean match(Enum<?> var0, Enum<?> var1) {
      if (var1 == null) {
         return var0 == null;
      } else if ("ALL".equals(var1.name())) {
         return true;
      } else {
         return var0 == var1;
      }
   }
}
