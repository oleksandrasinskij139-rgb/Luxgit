package dev.luxvisuals.menu;

import II1II1II1II1II1II1II1II1.l2_I1.I_l_IiI__ilii1_I1__Ii__1_ii;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Patch {
   private static final Map<String, String> CACHE = new ConcurrentHashMap();
   private static final Map<String, Boolean> SEEN = new ConcurrentHashMap();

   private Patch() {
   }

   public static String safe(String var0) {
      if (var0 == null) {
         return null;
      } else {
         String var1 = (String)CACHE.get(var0);
         if (var1 != null) {
            return var1;
         } else {
            String var2;
            try {
               String var3 = var0.toLowerCase(Locale.ROOT);
               StringBuilder var4 = new StringBuilder(var3.length());

               for(int var5 = 0; var5 < var3.length(); ++var5) {
                  char var6 = var3.charAt(var5);
                  boolean var7 = var6 >= 'a' && var6 <= 'z' || var6 >= '0' && var6 <= '9' || var6 == '/' || var6 == '.' || var6 == '_' || var6 == '-';
                  var4.append(var7 ? var6 : '_');
               }

               var2 = var4.toString();
               if (var2.startsWith("images/") && var2.endsWith(".png") && Patch.class.getResource("/assets/phantom/" + var2) == null) {
                  var2 = "images/blank.png";
               }
            } catch (Throwable var8) {
               var2 = "images/blank.png";
            }

            CACHE.put(var0, var2);
            return var2;
         }
      }
   }

   public static I_l_IiI__ilii1_I1__Ii__1_ii wingsRef(Object var0) {
      return var0 instanceof LuxCosmeticAdapter ? null : I_l_IiI__ilii1_I1__Ii__1_ii.WINGS;
   }

   public static void err(Throwable var0) {
      try {
         String var10000 = var0.getClass().getName();
         String var1 = var10000 + ":" + var0.getMessage();
         if (SEEN.putIfAbsent(var1, Boolean.TRUE) == null) {
            System.out.println("[LuxBridge] menu widget error suppressed: " + var1);
            var0.printStackTrace();
         }
      } catch (Throwable var2) {
      }

   }
}
