package code.miaware.client.ui.theme;

import code.miaware.api.system.backend.K93d0ef925a2fcdf0d6;
import code.miaware.api.system.configs.Ke74a88931f3b59a118;
import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Color;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

public final class K2c59a7f522ec4ea35b {
   public static final List<K51ad80c57a5258fb43> PRESETS = List.of(K0f8087a87fa699ca27("Синий", "Blue", new Color(96, 156, 255)), K0f8087a87fa699ca27("Жёлтый", "Yellow", new Color(255, 204, 84)), K0f8087a87fa699ca27("Зелёный", "Green", new Color(110, 226, 140)), K0f8087a87fa699ca27("Красный", "Red", new Color(255, 104, 120)), K0f8087a87fa699ca27("Фиолетовый", "Purple", new Color(178, 132, 255)), K3cb0cc81e335ac66df("Лаванда", "Lavender", new Color(158, 165, 254), new Color(255, 150, 214)));
   private static int selected = 0;
   private static boolean loaded;

   private K2c59a7f522ec4ea35b() {
   }

   private static K51ad80c57a5258fb43 K0f8087a87fa699ca27(String ru, String en, Color c) {
      return new K51ad80c57a5258fb43(ru, en, c, mix(c, Color.WHITE, 0.18F), false);
   }

   private static K51ad80c57a5258fb43 K3cb0cc81e335ac66df(String ru, String en, Color a, Color b) {
      return new K51ad80c57a5258fb43(ru, en, a, b, true);
   }

   public static int selected() {
      ensureLoaded();
      return selected;
   }

   public static Color guiAccent() {
      ensureLoaded();
      return mix(((K51ad80c57a5258fb43)PRESETS.get(selected)).primary(), Color.WHITE, 0.22F);
   }

   public static void select(int index) {
      ensureLoaded();
      int next = index >= 0 && index < PRESETS.size() ? index : 0;
      if (next != selected) {
         K3195adfcc0c0135ece.begin(((K51ad80c57a5258fb43)PRESETS.get(next)).primary().getRGB());
         selected = next;
         apply();
         save();
      }
   }

   public static void ensureLoaded() {
      if (!loaded) {
         loaded = true;

         try {
            File f = file();
            if (f.exists()) {
               JsonObject o = JsonParser.parseString(Files.readString(f.toPath())).getAsJsonObject();
               int i = o.has("preset") ? o.get("preset").getAsInt() : -1;
               selected = i >= 0 && i < PRESETS.size() ? i : 0;
            }
         } catch (Throwable var3) {
         }

         apply();
      }
   }

   private static void apply() {
      K51ad80c57a5258fb43 p = (K51ad80c57a5258fb43)PRESETS.get(selected);
      Ka20f8cfdbc113ccdec.ACCENT = p.primary();
      Ka20f8cfdbc113ccdec.ACCENT_SOFT = p.dual() ? p.secondary() : mix(p.primary(), Color.WHITE, 0.12F);
      K6ea7546faa48739c0d.getInstance().selectPreset(selected);
   }

   private static File file() {
      return new File(K93d0ef925a2fcdf0d6.CONFIG_PATH_OTHER, "accent.json");
   }

   private static void save() {
      try {
         JsonObject o = new JsonObject();
         o.addProperty("preset", selected);
         File f = file();
         f.getParentFile().mkdirs();
         Files.writeString(f.toPath(), o.toString());
         Ke74a88931f3b59a118.getInstance().saveLastSelected(K6ea7546faa48739c0d.getInstance().getCurrentTheme());
      } catch (Throwable var2) {
      }

   }

   public static Color mix(Color a, Color b, float t) {
      return new Color(Math.round((float)a.getRed() + (float)(b.getRed() - a.getRed()) * t), Math.round((float)a.getGreen() + (float)(b.getGreen() - a.getGreen()) * t), Math.round((float)a.getBlue() + (float)(b.getBlue() - a.getBlue()) * t), Math.round((float)a.getAlpha() + (float)(b.getAlpha() - a.getAlpha()) * t));
   }
}
