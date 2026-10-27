package code.miaware.client.features.modules.render.armor;

public final class Kc35e3ae6ac3cc22bf6 {
   private Kc35e3ae6ac3cc22bf6() {
   }

   public static int color(int damage, int maximum) {
      double remaining = maximum <= 0 ? (double)1.0F : Math.max((double)0.0F, Math.min((double)1.0F, (double)1.0F - (double)damage / (double)maximum));
      if (remaining > (double)0.75F) {
         return -13116571;
      } else if (remaining > (double)0.5F) {
         return -27597;
      } else {
         return remaining > (double)0.25F ? -9160 : -1031100;
      }
   }
}
