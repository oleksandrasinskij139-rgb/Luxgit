package code.miaware.client.config;

import code.miaware.api.system.backend.K93d0ef925a2fcdf0d6;
import code.miaware.api.system.configs.Kd4542f1791364048a9;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.class_310;

public class K3e319beb26054fd921 {
   private static K3e319beb26054fd921 instance;
   private final Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
   private final File configsDir = new File("config/luxvisuals/cloud");
   private final File savedConfigsFile;

   private K3e319beb26054fd921() {
      if (!this.configsDir.exists()) {
         this.configsDir.mkdirs();
      }

      this.savedConfigsFile = new File(this.configsDir, "saved_configs.json");
   }

   public static K3e319beb26054fd921 getInstance() {
      if (instance == null) {
         instance = new K3e319beb26054fd921();
      }

      return instance;
   }

   public boolean saveCurrentConfig(String configName) {
      try {
         File mainConfigDir = new File("config/luxvisuals/main");
         JsonObject configData = new JsonObject();
         if (mainConfigDir.exists() && mainConfigDir.isDirectory()) {
            File[] configFiles = mainConfigDir.listFiles((dir, name) -> name.endsWith(".json"));
            if (configFiles != null) {
               for(File configFile : configFiles) {
                  FileReader reader = new FileReader(configFile);

                  try {
                     JsonObject fileConfig = JsonParser.parseReader(reader).getAsJsonObject();
                     configData.add(configFile.getName().replace(".json", ""), fileConfig);
                  } catch (Throwable var15) {
                     try {
                        reader.close();
                     } catch (Throwable var13) {
                        var15.addSuppressed(var13);
                     }

                     throw var15;
                  }

                  reader.close();
               }
            }
         }

         JsonObject metadata = new JsonObject();
         metadata.addProperty("name", configName);
         metadata.addProperty("timestamp", System.currentTimeMillis());
         configData.add("_metadata", metadata);
         File configFile = new File(this.configsDir, configName + ".json");
         FileWriter writer = new FileWriter(configFile);

         try {
            this.gson.toJson(configData, writer);
         } catch (Throwable var14) {
            try {
               writer.close();
            } catch (Throwable var12) {
               var14.addSuppressed(var12);
            }

            throw var14;
         }

         writer.close();
         System.out.println("[CloudConfig] Конфиг сохранен: " + configName);
         return true;
      } catch (Exception e) {
         System.err.println("[CloudConfig] Ошибка сохранения конфига: " + e.getMessage());
         e.printStackTrace();
         return false;
      }
   }

   public boolean applyConfig(JsonObject configData, String configName) {
      try {
         System.out.println("[CloudConfig] Применение конфига: " + configName);
         File mainConfigDir = new File(K93d0ef925a2fcdf0d6.CONFIG_PATH_MAIN);
         if (!mainConfigDir.exists()) {
            mainConfigDir.mkdirs();
         }

         File configFile = new File(mainConfigDir, configName + ".json");
         FileWriter writer = new FileWriter(configFile);

         try {
            this.gson.toJson(configData, writer);
         } catch (Throwable var9) {
            try {
               writer.close();
            } catch (Throwable var8) {
               var9.addSuppressed(var8);
            }

            throw var9;
         }

         writer.close();
         System.out.println("[CloudConfig] Конфиг сохранен в: " + configFile.getAbsolutePath());
         class_310.method_1551().execute(() -> {
            System.out.println("[CloudConfig] Загружаем конфиг в главном потоке: " + configName);
            Kd4542f1791364048a9.getInstance().load(configName);
            System.out.println("[CloudConfig] Конфиг применен: " + configName);
         });
         return true;
      } catch (Exception e) {
         System.err.println("[CloudConfig] Ошибка применения конфига: " + e.getMessage());
         e.printStackTrace();
         return false;
      }
   }

   public JsonObject loadConfig(String configName) {
      try {
         File configFile = new File(this.configsDir, configName + ".json");
         if (!configFile.exists()) {
            System.err.println("[CloudConfig] Конфиг не найден: " + configName);
            return null;
         } else {
            FileReader reader = new FileReader(configFile);

            JsonObject var4;
            try {
               var4 = JsonParser.parseReader(reader).getAsJsonObject();
            } catch (Throwable var7) {
               try {
                  reader.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }

               throw var7;
            }

            reader.close();
            return var4;
         }
      } catch (Exception e) {
         System.err.println("[CloudConfig] Ошибка загрузки конфига: " + e.getMessage());
         e.printStackTrace();
         return null;
      }
   }

   public List<String> getSavedConfigs() {
      List<String> configs = new ArrayList();
      File[] files = this.configsDir.listFiles((dir, name) -> name.endsWith(".json") && !name.equals("saved_configs.json"));
      if (files != null) {
         for(File file : files) {
            configs.add(file.getName().replace(".json", ""));
         }
      }

      return configs;
   }

   public String generateRandomKey() {
      String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
      Random random = new Random();
      StringBuilder key = new StringBuilder();

      for(int i = 0; i < 10; ++i) {
         key.append(chars.charAt(random.nextInt(chars.length())));
      }

      return key.toString();
   }

   public String serializeConfig(String configName) {
      try {
         File configFile = new File(K93d0ef925a2fcdf0d6.CONFIG_PATH_MAIN, configName + ".json");
         if (!configFile.exists()) {
            System.err.println("[CloudConfig] Конфиг не найден: " + configFile.getAbsolutePath());
            return null;
         } else {
            System.out.println("[CloudConfig] Читаем конфиг: " + configFile.getAbsolutePath());
            FileReader reader = new FileReader(configFile);

            String var6;
            try {
               JsonObject config = JsonParser.parseReader(reader).getAsJsonObject();
               String jsonString = this.gson.toJson(config);
               System.out.println("[CloudConfig] Конфиг сериализован: " + configName + " (размер: " + jsonString.length() + " байт)");
               var6 = jsonString;
            } catch (Throwable var8) {
               try {
                  reader.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }

               throw var8;
            }

            reader.close();
            return var6;
         }
      } catch (Exception e) {
         System.err.println("[CloudConfig] Ошибка сериализации конфига '" + configName + "': " + e.getMessage());
         e.printStackTrace();
         return null;
      }
   }

   public JsonObject deserializeConfig(String jsonString) {
      try {
         return JsonParser.parseString(jsonString).getAsJsonObject();
      } catch (Exception e) {
         System.err.println("[CloudConfig] Ошибка парсинга JSON: " + e.getMessage());
         return null;
      }
   }
}
