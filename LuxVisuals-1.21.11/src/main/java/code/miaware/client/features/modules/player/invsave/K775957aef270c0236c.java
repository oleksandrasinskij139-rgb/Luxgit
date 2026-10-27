package code.miaware.client.features.modules.player.invsave;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_638;
import net.minecraft.class_6903;
import net.minecraft.class_9334;

public final class K775957aef270c0236c {
   private final LinkedHashMap<String, K9e64dd2dd1a3d84dbb> layouts = new LinkedHashMap();
   private final class_310 mc = class_310.method_1551();
   private class_638 loadedWorld;
   private String selected;
   private long revision;
   private boolean loadFailed;

   private Path file() {
      return this.mc.field_1697.toPath().resolve("luxvisuals/invsave/layouts.json");
   }

   public long revision() {
      this.ensureLoaded();
      return this.revision;
   }

   public List<K9e64dd2dd1a3d84dbb> layouts() {
      this.ensureLoaded();
      return List.copyOf(this.layouts.values());
   }

   public K9e64dd2dd1a3d84dbb selected() {
      this.ensureLoaded();
      return (K9e64dd2dd1a3d84dbb)this.layouts.get(this.selected);
   }

   public String nextName() {
      this.ensureLoaded();

      int i;
      for(i = 1; this.layouts.containsKey("inv" + i); ++i) {
      }

      return "inv" + i;
   }

   private static String key(String name) {
      return name.toLowerCase(Locale.ROOT);
   }

   public void ensureLoaded() {
      if (this.mc.field_1687 != null && this.loadedWorld != this.mc.field_1687) {
         this.loadedWorld = this.mc.field_1687;
         this.layouts.clear();
         this.selected = null;
         ++this.revision;
         this.loadFailed = false;
         if (Files.isRegularFile(this.file(), new LinkOption[0])) {
            try {
               JsonObject root = JsonParser.parseString(Files.readString(this.file())).getAsJsonObject();
               class_6903<JsonElement> ops = class_6903.method_46632(JsonOps.INSTANCE, this.mc.field_1687.method_30349());

               for(JsonElement element : root.getAsJsonArray("layouts")) {
                  if (this.layouts.size() >= 32) {
                     break;
                  }

                  JsonObject object = element.getAsJsonObject();
                  String name = validName(object.get("name").getAsString());
                  List<class_1799> slots = new ArrayList(Collections.nCopies(41, class_1799.field_8037));

                  for(Map.Entry<String, JsonElement> entry : object.getAsJsonObject("slots").entrySet()) {
                     int index = Integer.parseInt((String)entry.getKey());
                     if (index >= 0 && index < 41) {
                        slots.set(index, (class_1799)class_1799.field_24671.parse(ops, (JsonElement)entry.getValue()).getOrThrow());
                     }
                  }

                  this.layouts.put(key(name), new K9e64dd2dd1a3d84dbb(name, List.copyOf(slots)));
               }

               if (root.has("selected") && !root.get("selected").isJsonNull()) {
                  this.selected = root.get("selected").getAsString();
               }
            } catch (Exception e) {
               this.layouts.clear();
               this.selected = null;
               this.loadFailed = true;
               System.err.println("[InvSave] Cannot load layouts: " + e.getMessage());
            }

         }
      }
   }

   public static String validName(String text) {
      String name = text == null ? "" : text.strip();
      if (!name.isEmpty() && name.length() <= 24 && !name.codePoints().anyMatch(Character::isISOControl)) {
         return name;
      } else {
         throw new IllegalArgumentException("Название: от 1 до 24 символов");
      }
   }

   public void save(String text) throws Exception {
      this.ensureLoaded();
      if (this.mc.field_1724 != null && this.mc.field_1687 != null) {
         String name = validName(text);
         String id = key(name);
         if (!this.layouts.containsKey(id) && this.layouts.size() >= 32) {
            throw new IllegalArgumentException("Максимум 32 схемы");
         } else {
            List<class_1799> slots = new ArrayList();

            for(int i = 0; i < 41; ++i) {
               slots.add(this.mc.field_1724.method_31548().method_5438(i).method_7972());
            }

            K9e64dd2dd1a3d84dbb before = (K9e64dd2dd1a3d84dbb)this.layouts.put(id, new K9e64dd2dd1a3d84dbb(name, List.copyOf(slots)));
            String previous = this.selected;

            try {
               this.K528f5a4dcb48440d8a();
               ++this.revision;
            } catch (Exception e) {
               if (before == null) {
                  this.layouts.remove(id);
               } else {
                  this.layouts.put(id, before);
               }

               this.selected = previous;
               throw e;
            }
         }
      }
   }

   public void select(String name) throws Exception {
      this.ensureLoaded();
      String previous = this.selected;
      if (name != null && !this.layouts.containsKey(key(name))) {
         throw new IllegalArgumentException("Раскладка не найдена");
      } else {
         this.selected = name == null ? null : key(name);

         try {
            this.K528f5a4dcb48440d8a();
            ++this.revision;
         } catch (Exception e) {
            this.selected = previous;
            throw e;
         }
      }
   }

   public void delete(String name) throws Exception {
      this.ensureLoaded();
      String id = key(name);
      String previous = this.selected;
      K9e64dd2dd1a3d84dbb removed = (K9e64dd2dd1a3d84dbb)this.layouts.remove(id);
      if (Objects.equals(this.selected, id)) {
         this.selected = null;
      }

      try {
         this.K528f5a4dcb48440d8a();
         ++this.revision;
      } catch (Exception e) {
         if (removed != null) {
            this.layouts.put(id, removed);
         }

         this.selected = previous;
         throw e;
      }
   }

   private void K528f5a4dcb48440d8a() throws Exception {
      if (this.loadFailed) {
         throw new IllegalStateException("Не удалось прочитать файл схем");
      } else if (this.mc.field_1687 == null) {
         throw new IllegalStateException("Мир не загружен");
      } else {
         class_6903<JsonElement> ops = class_6903.method_46632(JsonOps.INSTANCE, this.mc.field_1687.method_30349());
         JsonObject root = new JsonObject();
         JsonArray entries = new JsonArray();
         root.addProperty("version", 1);
         root.addProperty("selected", this.selected);

         for(K9e64dd2dd1a3d84dbb layout : this.layouts.values()) {
            JsonObject object = new JsonObject();
            JsonObject slots = new JsonObject();
            object.addProperty("name", layout.name());

            for(int i = 0; i < 41; ++i) {
               class_1799 stack = (class_1799)layout.slots().get(i);
               if (!stack.method_7960()) {
                  slots.add(Integer.toString(i), (JsonElement)class_1799.field_24671.encodeStart(ops, stack).getOrThrow());
               }
            }

            object.add("slots", slots);
            entries.add(object);
         }

         root.add("layouts", entries);
         Path target = this.file();
         Files.createDirectories(target.getParent());
         Path temporary = target.resolveSibling("layouts.json.tmp");
         Files.writeString(temporary, (new GsonBuilder()).setPrettyPrinting().create().toJson(root));

         try {
            Files.move(temporary, target, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
         } catch (AtomicMoveNotSupportedException var10) {
            Files.move(temporary, target, StandardCopyOption.REPLACE_EXISTING);
         }

      }
   }

   public static boolean fits(class_1799 expected, class_1799 actual) {
      return !actual.method_7960() && class_1799.method_7984(expected, actual) && (!expected.method_57826(class_9334.field_49631) || Objects.equals(expected.method_57824(class_9334.field_49631), actual.method_57824(class_9334.field_49631))) && Objects.equals(expected.method_57824(class_9334.field_49651), actual.method_57824(class_9334.field_49651));
   }
}
