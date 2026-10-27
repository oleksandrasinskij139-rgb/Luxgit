package code.miaware.client.features.modules.combat.tripleswap;

public final class Kb64b8db37bafed12ae {
   public static final int SECTORS = 3;
   public static final float INNER = 40.0F;
   public static final float OUTER = 112.0F;
   public static final float ICON_RADIUS = 76.0F;
   public static final float GAP = 7.0F;

   private Kb64b8db37bafed12ae() {
   }

   public static boolean valid(int sector) {
      return sector >= 0 && sector < 3;
   }

   public static int sectorAt(double dx, double dy, float scale) {
      if (Double.isFinite(dx) && Double.isFinite(dy) && Float.isFinite(scale) && !(scale <= 0.0F)) {
         double radius = Math.hypot(dx, dy) / (double)scale;
         if (!(radius < (double)40.0F) && !(radius > (double)121.0F)) {
            double angle = (Math.toDegrees(Math.atan2(dx, -dy)) + (double)420.0F) % (double)360.0F;
            double inSector = angle % (double)120.0F;
            return Math.min(inSector, (double)120.0F - inSector) < (double)3.5F ? -1 : (int)(angle / (double)120.0F);
         } else {
            return -1;
         }
      } else {
         return -1;
      }
   }

   public static String key(String item, String customName) {
      return item != null && !item.isBlank() ? item + "|" + (customName == null ? "" : customName) : "";
   }

   public static boolean matches(String item, String customName, String key) {
      return key != null && !key.isEmpty() && key(item, customName).equals(key);
   }
}
