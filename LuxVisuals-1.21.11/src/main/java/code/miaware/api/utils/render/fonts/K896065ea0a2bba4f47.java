package code.miaware.api.utils.render.fonts;

import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

public final class K896065ea0a2bba4f47 {
   public static final String sf = "sf_pro";
   public static final String ps = "product_sans";
   private static final Map<String, K5fe84dad3db1af4bb7> cache = new HashMap();
   public static final K5fe84dad3db1af4bb7 SF_BOLD = get("sf_pro/sf_bold");
   public static final K5fe84dad3db1af4bb7 SF_SEMIBOLD = get("sf_pro/sf_semibold");
   public static final K5fe84dad3db1af4bb7 SF_MEDIUM = get("sf_pro/sf_medium");
   public static final K5fe84dad3db1af4bb7 SF_REGULAR = get("sf_pro/sf_regular");
   public static final K5fe84dad3db1af4bb7 SF_LIGHT = get("sf_pro/sf_light");
   public static final K5fe84dad3db1af4bb7 PS_BLACK = get("product_sans/productsans_black");
   public static final K5fe84dad3db1af4bb7 PS_BOLD = get("product_sans/productsans_bold");
   public static final K5fe84dad3db1af4bb7 PS_MEDIUM = get("product_sans/productsans_medium");
   public static final K5fe84dad3db1af4bb7 PS_REGULAR = get("product_sans/productsans_regular");
   public static final K5fe84dad3db1af4bb7 PS_LIGHT = get("product_sans/productsans_light");
   public static final K5fe84dad3db1af4bb7 PS_THIN = get("product_sans/productsans_thin");
   public static final K5fe84dad3db1af4bb7 ICONS = get("other/icons");
   public static final K5fe84dad3db1af4bb7 ICONS_NURIK = get("other/icons_nurik");
   public static final K5fe84dad3db1af4bb7 ICONS_DESHUX = get("other/icon_deshux");
   public static final K5fe84dad3db1af4bb7 ICON = get("other/icon");
   public static final K5fe84dad3db1af4bb7 ICOES = get("other/icoes");
   public static final K5fe84dad3db1af4bb7 ICOLIST = get("other/icolist");
   public static final K5fe84dad3db1af4bb7 CLICKGUI = get("other/clickgui");
   public static final K5fe84dad3db1af4bb7 ICON_DANQ;
   public static final K5fe84dad3db1af4bb7 ICOTEST;
   public static final K5fe84dad3db1af4bb7 NUR;
   public static final K11ef91e33d46fe9b37 ROLE_ICONS_BITMAP;

   public static float getMediumThickness() {
      return 0.07F;
   }

   public static float getBoldThickness() {
      return 0.1F;
   }

   private static K5fe84dad3db1af4bb7 get(String input) {
      return K5fe84dad3db1af4bb7.builder().find(input).load();
   }

   @Generated
   private K896065ea0a2bba4f47() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   static {
      ICON_DANQ = ICON;
      ICOTEST = get("other/icotest");
      NUR = get("other/nur");
      System.out.println("============================================");
      System.out.println("[Fonts] Инициализация ROLE_ICONS...");
      System.out.println("============================================");
      ROLE_ICONS_BITMAP = new K11ef91e33d46fe9b37("fonts/other/role_icons.png", 320, 192, 64);
   }
}
