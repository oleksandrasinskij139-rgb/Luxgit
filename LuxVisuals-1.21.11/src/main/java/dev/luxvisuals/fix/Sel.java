package dev.luxvisuals.fix;

import code.miaware.client.features.cosmetics.K697051202eac7cfc74;
import code.miaware.client.features.cosmetics.K9a0eb2753a3143de82;
import code.miaware.client.features.cosmetics.Ka5dfe6b89219e29d09;

public final class Sel {
   private static long swT;
   private static long elT;
   private static String swV;
   private static String elV;

   private static String find(K9a0eb2753a3143de82 var0) {
      try {
         K697051202eac7cfc74 var1 = K697051202eac7cfc74.getInstance();

         for(Ka5dfe6b89219e29d09 var3 : var1.entries()) {
            if (var3.type == var0 && var1.isEquipped(var3)) {
               return var3.name;
            }
         }
      } catch (Throwable var4) {
      }

      return null;
   }

   public static String sword() {
      long var0 = System.nanoTime();
      if (var0 - swT > 50000000L) {
         swT = var0;
         swV = find(K9a0eb2753a3143de82.SWORD);
      }

      return swV;
   }

   public static String elytra() {
      long var0 = System.nanoTime();
      if (var0 - elT > 50000000L) {
         elT = var0;
         elV = find(K9a0eb2753a3143de82.ELYTRA);
      }

      return elV;
   }
}
