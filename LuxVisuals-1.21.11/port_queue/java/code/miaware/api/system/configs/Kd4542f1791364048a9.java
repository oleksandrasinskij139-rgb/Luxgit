package code.miaware.api.system.configs;

import code.miaware.api.module.K5c460ed4a42f0797d6;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.K1d3d0b4020f933282f;
import code.miaware.api.module.setting.K917aeb5cdb0f7aca26;
import code.miaware.api.module.setting.Ka4b85e821e3749c3d9;
import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.backend.K93d0ef925a2fcdf0d6;
import code.miaware.api.system.backend.Kd9271a319f911decbe;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Generated;

public class Kd4542f1791364048a9 {
   private static final Kd4542f1791364048a9 instance = new Kd4542f1791364048a9();
   private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
   private static final int CONFIG_VERSION = 12;
   private static final Map<String, Map<String, String[]>> SETTING_ALIASES = K214729b27d7ef07a04();
   private final Path CONFIG_DIR;
   private boolean isLoading = false;
   private boolean autoConfigLoaded = false;
   private String currentConfigName = null;

   private Kd4542f1791364048a9() {
      this.CONFIG_DIR = Paths.get(K93d0ef925a2fcdf0d6.CONFIG_PATH_MAIN);

      try {
         Files.createDirectories(this.CONFIG_DIR);
      } catch (IOException e) {
         throw new RuntimeException("Failed to create config dir", e);
      }
   }

   public boolean isLoading() {
      return this.isLoading;
   }

   public String getCurrentConfigName() {
      return this.currentConfigName;
   }

   public List<String> getConfigsNames() {
      File configDir = new File(K93d0ef925a2fcdf0d6.CONFIG_PATH_MAIN);
      if (!configDir.exists()) {
         return Collections.emptyList();
      } else {
         File[] files = configDir.listFiles((dir, name) -> name.endsWith(".json"));
         return files == null ? Collections.emptyList() : (List)Arrays.stream(files).map((f) -> f.getName().replace(".json", "")).map(this::sanitizeConfigName).filter((name) -> !name.isBlank()).distinct().collect(Collectors.toList());
      }
   }

   public String sanitizeConfigName(String name) {
      String sanitized = name == null ? "" : name.trim();

      for(sanitized = sanitized.replaceAll("[\\\\/:*?\"<>|\\p{Cntrl}]", "_"); sanitized.contains(".."); sanitized = sanitized.replace("..", "_")) {
      }

      sanitized = sanitized.replaceAll("\\s+", " ").trim();
      return sanitized.isEmpty() ? "config" : sanitized;
   }

   private Path K59762ba784c4188fb9(String name) {
      Path var10000 = this.CONFIG_DIR;
      String var10001 = this.sanitizeConfigName(name);
      Path file = var10000.resolve(var10001 + ".json").normalize();
      if (!file.startsWith(this.CONFIG_DIR.normalize())) {
         throw new IllegalArgumentException("Config path escapes config dir: " + name);
      } else {
         return file;
      }
   }

   public void save(String name) {
      if (name.equals("autoConfig") && !this.autoConfigLoaded) {
         System.out.println("[ConfigManager] Пропускаем сохранение autoConfig - еще не загружен");
      } else {
         System.out.println("[ConfigManager] Сохраняем конфиг: " + name);
         JsonObject root = new JsonObject();
         root.addProperty("configVersion", 12);
         List<Kab8a8c478f7d0077cd> modules = K5c460ed4a42f0797d6.getInstance().getModules();
         JsonObject modulesJson = new JsonObject();

         for(Kab8a8c478f7d0077cd m : modules) {
            modulesJson.add(m.getName(), this.K9b5447c13e4cbc2f23(m));
         }

         root.add("Modules", modulesJson);
         Path file = this.K59762ba784c4188fb9(name);

         try {
            if (!Files.exists(file, new LinkOption[0])) {
               Files.createFile(file);
            }

            Files.writeString(file, GSON.toJson(root), StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);
            System.out.println("[ConfigManager] Конфиг сохранен: " + String.valueOf(file.toAbsolutePath()));
         } catch (IOException e) {
            System.err.printf("Failed to save config %s: %s%n", name, e.getMessage());
            e.printStackTrace();
         }

      }
   }

   public void load(String name) {
      Path file = this.K59762ba784c4188fb9(name);
      System.out.println("[ConfigManager] Загружаем конфиг: " + name + " из " + String.valueOf(file.toAbsolutePath()));
      if (!Files.exists(file, new LinkOption[0])) {
         System.out.println("[ConfigManager] Файл конфига не существует: " + String.valueOf(file.toAbsolutePath()));
         if (name.equals("autoConfig")) {
            this.autoConfigLoaded = true;
         }

      } else {
         this.isLoading = true;
         this.currentConfigName = name;

         try {
            String content = Files.readString(file);
            System.out.println("[ConfigManager] Прочитано " + content.length() + " символов из конфига");
            JsonObject root = (JsonObject)GSON.fromJson(content, JsonObject.class);
            if (root.has("Modules")) {
               JsonObject modulesJson = root.getAsJsonObject("Modules");
               System.out.println("[ConfigManager] Найдено модулей в конфиге: " + modulesJson.size());
               modulesJson.entrySet().forEach((entry) -> {
                  Kab8a8c478f7d0077cd module = (Kab8a8c478f7d0077cd)K5c460ed4a42f0797d6.getInstance().getModules().stream().filter((m) -> m.getName().equalsIgnoreCase((String)entry.getKey())).findFirst().orElse((Object)null);
                  if (module == null) {
                     System.err.println("Module not found for config: " + (String)entry.getKey());
                  } else {
                     System.out.println("[ConfigManager] Применяем конфиг к модулю: " + (String)entry.getKey());
                     this.K63f3bb90b2d448d6fb(module, ((JsonElement)entry.getValue()).getAsJsonObject());
                  }

               });
               return;
            }

            System.out.println("[ConfigManager] В конфиге нет секции Modules!");
         } catch (IOException e) {
            System.err.printf("Failed to load config %s: %s%n", name, e.getMessage());
            e.printStackTrace();
            return;
         } finally {
            this.isLoading = false;
            if (name.equals("autoConfig")) {
               this.autoConfigLoaded = true;
            }

            System.out.println("[ConfigManager] Загрузка конфига завершена");
         }

      }
   }

   public void remove(String name) {
      try {
         Files.deleteIfExists(this.K59762ba784c4188fb9(name));
      } catch (IOException e) {
         System.err.printf("Failed to remove config %s: %s%n", name, e.getMessage());
      }

   }

   public boolean exists(String name) {
      return Files.exists(this.K59762ba784c4188fb9(name), new LinkOption[0]);
   }

   public boolean hasModuleEntry(String configName, String moduleName) {
      Path file = this.K59762ba784c4188fb9(configName);
      if (!Files.exists(file, new LinkOption[0])) {
         return false;
      } else {
         try {
            JsonObject root = (JsonObject)GSON.fromJson(Files.readString(file), JsonObject.class);
            if (root != null && root.has("Modules")) {
               JsonObject modules = root.getAsJsonObject("Modules");
               return modules.entrySet().stream().anyMatch((entry) -> ((String)entry.getKey()).equalsIgnoreCase(moduleName));
            } else {
               return false;
            }
         } catch (Exception var6) {
            return false;
         }
      }
   }

   private JsonObject K9b5447c13e4cbc2f23(Kab8a8c478f7d0077cd module) {
      JsonObject json = new JsonObject();
      json.addProperty("enabled", module.isEnabled());
      json.addProperty("bind", module.getBind());
      json.addProperty("bindEncoding", 2);
      JsonObject settings = new JsonObject();
      module.getSettings().forEach((s) -> {
         JsonElement v = this.Ked79d718b75fd06a3d(s);
         if (v != null) {
            settings.add(s.getName(), v);
         }

      });
      json.add("settings", settings);
      return json;
   }

   private void K63f3bb90b2d448d6fb(Kab8a8c478f7d0077cd module, JsonObject json) {
      boolean legacyBinds = !json.has("bindEncoding") || json.get("bindEncoding").getAsInt() < 2;
      if (json.has("enabled")) {
         module.setEnabled(json.get("enabled").getAsBoolean(), true);
      }

      if (json.has("bind")) {
         int bind = json.get("bind").getAsInt();
         module.setBind(legacyBinds ? Kd9271a319f911decbe.migrateLegacyScrollBind(bind) : bind);
      }

      if (json.has("settings")) {
         JsonObject settings = json.getAsJsonObject("settings");
         module.getSettings().forEach((s) -> {
            JsonElement value = this.Kf0b401eee343dcbde2(module, settings, s);
            if (value != null) {
               this.K02cb759052f3253946(module, s, value, legacyBinds);
            }

         });
      }

   }

   private JsonElement Ked79d718b75fd06a3d(Kdced9e23f1e229ba9d<?> s) {
      Objects.requireNonNull(s);
      byte var3 = 0;
      Object var10000;
      //$FF: var3->value
      //0->code/miaware/api/module/setting/K00fef91b3d4d1b3ecc
      //1->code/miaware/api/module/setting/Kbd66519397e9a89967
      //2->code/miaware/api/module/setting/Kb9e875ead00e0d03bd
      //3->code/miaware/api/module/setting/K917aeb5cdb0f7aca26
      //4->code/miaware/api/module/setting/K1d3d0b4020f933282f
      //5->code/miaware/api/module/setting/Ka4b85e821e3749c3d9
      //6->code/miaware/api/module/setting/Ka52d224feda41b0761
      switch (s.typeSwitch<invokedynamic>(s, var3)) {
         case 0:
            K00fef91b3d4d1b3ecc b = (K00fef91b3d4d1b3ecc)s;
            var10000 = GSON.toJsonTree(b.getValue());
            break;
         case 1:
            Kbd66519397e9a89967 m = (Kbd66519397e9a89967)s;
            var10000 = GSON.toJsonTree(m.getValue());
            break;
         case 2:
            Kb9e875ead00e0d03bd sl = (Kb9e875ead00e0d03bd)s;
            var10000 = GSON.toJsonTree(Ka0484a23398fb5f9ac.round(sl.getValue(), sl.getStep()));
            break;
         case 3:
            K917aeb5cdb0f7aca26 st = (K917aeb5cdb0f7aca26)s;
            var10000 = GSON.toJsonTree(st.getValue());
            break;
         case 4:
            K1d3d0b4020f933282f bi = (K1d3d0b4020f933282f)s;
            var10000 = GSON.toJsonTree(bi.getValue());
            break;
         case 5:
            Ka4b85e821e3749c3d9 mb = (Ka4b85e821e3749c3d9)s;
            JsonObject obj = new JsonObject();
            ((List)mb.getValue()).forEach((bx) -> obj.addProperty(bx.getName(), (Boolean)bx.getValue()));
            var10000 = obj;
            break;
         case 6:
            Ka52d224feda41b0761 c = (Ka52d224feda41b0761)s;
            Color col = (Color)c.getValue();
            JsonObject obj = new JsonObject();
            obj.addProperty("r", col.getRed());
            obj.addProperty("g", col.getGreen());
            obj.addProperty("b", col.getBlue());
            obj.addProperty("a", col.getAlpha());
            var10000 = obj;
            break;
         default:
            var10000 = null;
      }

      return (JsonElement)var10000;
   }

   private JsonElement Kf0b401eee343dcbde2(Kab8a8c478f7d0077cd module, JsonObject settings, Kdced9e23f1e229ba9d<?> setting) {
      if (settings.has(setting.getName())) {
         return settings.get(setting.getName());
      } else {
         Map<String, String[]> moduleAliases = (Map)SETTING_ALIASES.getOrDefault(module.getName(), Collections.emptyMap());
         String[] aliases = (String[])moduleAliases.getOrDefault(setting.getName(), new String[0]);
         String matchedKey = this.K90721f438705611fc3(settings, setting.getName(), aliases);
         if (matchedKey != null) {
            K67bdd314d57a081c42(module.getName(), setting.getName(), matchedKey);
            return settings.get(matchedKey);
         } else {
            return null;
         }
      }
   }

   private String K90721f438705611fc3(JsonObject settings, String currentName, String[] aliases) {
      for(String alias : aliases) {
         if (settings.has(alias)) {
            return alias;
         }
      }

      String normalizedCurrent = K728a0abb9f7497097d(currentName);

      for(Map.Entry<String, JsonElement> entry : settings.entrySet()) {
         if (K728a0abb9f7497097d((String)entry.getKey()).equals(normalizedCurrent)) {
            return (String)entry.getKey();
         }
      }

      for(String alias : aliases) {
         String normalizedAlias = K728a0abb9f7497097d(alias);

         for(Map.Entry<String, JsonElement> entry : settings.entrySet()) {
            if (K728a0abb9f7497097d((String)entry.getKey()).equals(normalizedAlias)) {
               return (String)entry.getKey();
            }
         }
      }

      return null;
   }

   private String K018c80c19969fdb48e(JsonObject settings, String settingName) {
      if (settings.has(settingName)) {
         return settingName;
      } else {
         String normalizedSettingName = K728a0abb9f7497097d(settingName);

         for(Map.Entry<String, JsonElement> entry : settings.entrySet()) {
            if (K728a0abb9f7497097d((String)entry.getKey()).equals(normalizedSettingName)) {
               return (String)entry.getKey();
            }
         }

         return null;
      }
   }

   private void K02cb759052f3253946(Kab8a8c478f7d0077cd module, Kdced9e23f1e229ba9d<?> s, JsonElement e, boolean legacyBinds) {
      try {
         Objects.requireNonNull(s);
         byte var6 = 0;
         //$FF: var6->value
         //0->code/miaware/api/module/setting/K00fef91b3d4d1b3ecc
         //1->code/miaware/api/module/setting/Kbd66519397e9a89967
         //2->code/miaware/api/module/setting/Kb9e875ead00e0d03bd
         //3->code/miaware/api/module/setting/K917aeb5cdb0f7aca26
         //4->code/miaware/api/module/setting/K1d3d0b4020f933282f
         //5->code/miaware/api/module/setting/Ka4b85e821e3749c3d9
         //6->code/miaware/api/module/setting/Ka52d224feda41b0761
         switch (s.typeSwitch<invokedynamic>(s, var6)) {
            case 0:
               K00fef91b3d4d1b3ecc b = (K00fef91b3d4d1b3ecc)s;
               b.setValue(e.getAsBoolean());
               break;
            case 1:
               Kbd66519397e9a89967 m = (Kbd66519397e9a89967)s;
               m.setValue(e.getAsString());
               break;
            case 2:
               Kb9e875ead00e0d03bd sl = (Kb9e875ead00e0d03bd)s;
               sl.setValue(Ka0484a23398fb5f9ac.round(e.getAsFloat(), sl.getStep()));
               break;
            case 3:
               K917aeb5cdb0f7aca26 st = (K917aeb5cdb0f7aca26)s;
               st.setValue(e.getAsString());
               break;
            case 4:
               K1d3d0b4020f933282f bi = (K1d3d0b4020f933282f)s;
               bi.setValue(legacyBinds ? Kd9271a319f911decbe.migrateLegacyScrollBind(e.getAsInt()) : e.getAsInt());
               break;
            case 5:
               Ka4b85e821e3749c3d9 mb = (Ka4b85e821e3749c3d9)s;
               JsonObject obj = e.getAsJsonObject();
               ((List)mb.getValue()).forEach((bx) -> {
                  String matchedKey = this.K018c80c19969fdb48e(obj, bx.getName());
                  if (matchedKey != null) {
                     if (!matchedKey.equals(bx.getName())) {
                        K67bdd314d57a081c42(module.getName(), s.getName() + "." + bx.getName(), matchedKey);
                     }

                     bx.setValue(obj.get(matchedKey).getAsBoolean());
                  }

               });
               break;
            case 6:
               Ka52d224feda41b0761 c = (Ka52d224feda41b0761)s;
               JsonObject obj = e.getAsJsonObject();
               int r = obj.get("r").getAsInt();
               int g = obj.get("g").getAsInt();
               int b = obj.get("b").getAsInt();
               int a = obj.get("a").getAsInt();
               c.setValue(new Color(r, g, b, a));
         }
      } catch (Exception ex) {
         System.err.printf("Failed to deserialize %s: %s%n", s.getName(), ex.getMessage());
      }

   }

   private static Map<String, Map<String, String[]>> K214729b27d7ef07a04() {
      Map<String, Map<String, String[]>> aliases = new LinkedHashMap();
      Map<String, String[]> autoTotem = new LinkedHashMap();
      autoTotem.put("Delay", new String[]{"Swap delay"});
      autoTotem.put("Warden Detection", new String[]{"WardenDetection", "Warden detect"});
      autoTotem.put("Warden Range", new String[]{"WardenRange"});
      autoTotem.put("Fall Detection", new String[]{"FallDetection", "Fall damage"});
      autoTotem.put("Fall Height", new String[]{"FallHeight"});
      autoTotem.put("Save Enchanted", new String[]{"Only enchanted", "Save enchanted totems"});
      autoTotem.put("Return Item", new String[]{"Swap back", "Return item"});
      aliases.put("Auto Totem", autoTotem);
      Map<String, String[]> velocity = new LinkedHashMap();
      velocity.put("Mode", new String[]{"Knockback", "Knockback mode", "Velocity mode"});
      velocity.put("Pushes", new String[]{"Push", "Push settings"});
      aliases.put("Velocity", velocity);
      Map<String, String[]> autoBuff = new LinkedHashMap();
      autoBuff.put("Ticks after spawn", new String[]{"Ticks", "Spawn delay"});
      autoBuff.put("Auto disable", new String[]{"AutoDisable"});
      autoBuff.put("Only on ground", new String[]{"OnlyOnGround", "Ground only"});
      aliases.put("Auto Buff", autoBuff);
      Map<String, String[]> swap = new LinkedHashMap();
      swap.put("First item", new String[]{"Item 1", "FirstItem"});
      swap.put("Second item", new String[]{"Item 2", "SecondItem"});
      swap.put("Bind", new String[]{"Swap key", "Key"});
      swap.put("Show swap", new String[]{"Swap render", "Render", "Notify"});
      swap.put("Chat notify", new String[]{"Chat", "Chat message"});
      swap.put("Legit", new String[]{"Legit mode"});
      swap.put("Only enchanted totems", new String[]{"Only enchanted", "Only enchanted totem"});
      aliases.put("Swap", swap);
      Map<String, String[]> skeletonEsp = new LinkedHashMap();
      skeletonEsp.put("Render self", new String[]{"Self", "Show self"});
      aliases.put("Skeleton ESP", skeletonEsp);
      return aliases;
   }

   private static String K728a0abb9f7497097d(String value) {
      return value == null ? "" : value.toLowerCase(Locale.ROOT).replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}]", "");
   }

   private static void K67bdd314d57a081c42(String moduleName, String currentSetting, String oldSetting) {
      System.out.printf("Config alias applied: module '%s' setting '%s' <- '%s'%n", moduleName, currentSetting, oldSetting);
   }

   @Generated
   public static Kd4542f1791364048a9 getInstance() {
      return instance;
   }
}
