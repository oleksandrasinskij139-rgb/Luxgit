package code.miaware.client.features.cosmetics;

import code.miaware.api.system.backend.K93d0ef925a2fcdf0d6;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_2960;
import net.minecraft.class_310;

public final class K697051202eac7cfc74 {
   private static final K697051202eac7cfc74 INSTANCE = new K697051202eac7cfc74();
   private static final String ROOT = "/assets/luxvisuals/cosmetics/";
   private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
   private final List<Ka5dfe6b89219e29d09> entries = new ArrayList();
   private final Map<Integer, Ka5dfe6b89219e29d09> byId = new HashMap();
   private final EnumMap<K9a0eb2753a3143de82, Integer> equipped = new EnumMap(K9a0eb2753a3143de82.class);
   private final Map<Integer, Kf70c8667f5f3361806> models = new HashMap();
   private final Map<Integer, class_2960[]> capeFrames = new HashMap();
   private final long startMillis = System.currentTimeMillis();
   private boolean loaded;
   private Ka5dfe6b89219e29d09 previewEntry;

   private K697051202eac7cfc74() {
   }

   public static K697051202eac7cfc74 getInstance() {
      INSTANCE.K92a79671a76d0893de();
      return INSTANCE;
   }

   private synchronized void K92a79671a76d0893de() {
      if (!this.loaded) {
         this.loaded = true;

         try {
            InputStream in = K697051202eac7cfc74.class.getResourceAsStream("/assets/luxvisuals/cosmetics/index.json");

            try {
               if (in != null) {
                  JsonObject root = JsonParser.parseString(new String(in.readAllBytes(), StandardCharsets.UTF_8)).getAsJsonObject();

                  for(JsonElement el : root.getAsJsonArray("cosmetics")) {
                     Ka5dfe6b89219e29d09 e = Kab9d09b5327b485b2d(el.getAsJsonObject());
                     this.entries.add(e);
                     this.byId.put(e.id, e);
                  }
               }
            } catch (Throwable var7) {
               if (in != null) {
                  try {
                     in.close();
                  } catch (Throwable var6) {
                     var7.addSuppressed(var6);
                  }
               }

               throw var7;
            }

            if (in != null) {
               in.close();
            }
         } catch (Exception e) {
            System.err.println("[Cosmetics] index.json: " + String.valueOf(e));
         }

         this.Kf627710bbb3a19fd8e();
      }
   }

   private static Ka5dfe6b89219e29d09 Kab9d09b5327b485b2d(JsonObject o) {
      int texFrames = 0;
      int texFrameTime = 1;
      int capeFrames = 0;
      int capeW = 0;
      int capeH = 0;
      int capeTime = 1;
      float share = 0.0F;
      if (o.has("textureAnimation")) {
         JsonObject t = o.getAsJsonObject("textureAnimation");
         texFrames = t.get("frameCount").getAsInt();
         texFrameTime = Math.max(1, t.get("frameTime").getAsInt());
         share = t.get("frameHeight").getAsFloat() / Math.max(1.0F, t.get("totalHeight").getAsFloat());
      }

      if (o.has("capeAnimation")) {
         JsonObject c = o.getAsJsonObject("capeAnimation");
         capeFrames = c.get("frameCount").getAsInt();
         capeW = c.get("frameWidth").getAsInt();
         capeH = c.get("frameHeight").getAsInt();
         capeTime = Math.max(1, c.get("frameTime").getAsInt());
      }

      return new Ka5dfe6b89219e29d09(o.get("id").getAsInt(), o.get("name").getAsString(), K9a0eb2753a3143de82.byKey(o.get("type").getAsString()), o.has("pos") ? o.get("pos").getAsInt() : 1, f(o, "x", 0.0F), f(o, "y", 0.0F), f(o, "z", 0.0F), f(o, "yaw", 0.0F), f(o, "pitch", 0.0F), f(o, "roll", 0.0F), f(o, "scale", 1.0F), texFrames, texFrameTime, share, capeFrames, capeW, capeH, capeTime);
   }

   private static float f(JsonObject o, String key, float def) {
      return o.has(key) ? o.get(key).getAsFloat() : def;
   }

   public List<Ka5dfe6b89219e29d09> entries() {
      return Collections.unmodifiableList(this.entries);
   }

   public Ka5dfe6b89219e29d09 entry(int id) {
      return (Ka5dfe6b89219e29d09)this.byId.get(id);
   }

   public int count(K9a0eb2753a3143de82 type) {
      int n = 0;

      for(Ka5dfe6b89219e29d09 e : this.entries) {
         if (type == null || e.type == type) {
            ++n;
         }
      }

      return n;
   }

   public boolean isEquipped(Ka5dfe6b89219e29d09 e) {
      Integer id = (Integer)this.equipped.get(e.type);
      return id != null && id == e.id;
   }

   public void toggle(Ka5dfe6b89219e29d09 e) {
      if (this.isEquipped(e)) {
         this.equipped.remove(e.type);
      } else {
         this.equipped.put(e.type, e.id);
      }

      this.K1e3576101fb4771b37();
   }

   public void unequipAll() {
      if (!this.equipped.isEmpty()) {
         this.equipped.clear();
         this.K1e3576101fb4771b37();
      }

   }

   public int equippedCount() {
      return this.equipped.size();
   }

   public List<Ka5dfe6b89219e29d09> equippedModels() {
      List<Ka5dfe6b89219e29d09> out = new ArrayList(4);

      for(Map.Entry<K9a0eb2753a3143de82, Integer> e : this.equipped.entrySet()) {
         if (e.getKey() != K9a0eb2753a3143de82.CAPE && (this.previewEntry == null || e.getKey() != this.previewEntry.type)) {
            Ka5dfe6b89219e29d09 entry = (Ka5dfe6b89219e29d09)this.byId.get(e.getValue());
            if (entry != null) {
               out.add(entry);
            }
         }
      }

      if (this.previewEntry != null && !this.previewEntry.isCape()) {
         out.add(this.previewEntry);
      }

      return out;
   }

   public void renderPreview(Ka5dfe6b89219e29d09 entry, Runnable render) {
      Ka5dfe6b89219e29d09 previous = this.previewEntry;
      this.previewEntry = entry;

      try {
         render.run();
      } finally {
         this.previewEntry = previous;
      }

   }

   public synchronized Kf70c8667f5f3361806 model(Ka5dfe6b89219e29d09 e) {
      if (this.models.containsKey(e.id)) {
         return (Kf70c8667f5f3361806)this.models.get(e.id);
      } else {
         Kf70c8667f5f3361806 result = null;

         try {
            InputStream in = K697051202eac7cfc74.class.getResourceAsStream("/assets/luxvisuals/cosmetics/" + e.id + "/model.json");

            try {
               if (in != null) {
                  JsonObject root = JsonParser.parseString(new String(in.readAllBytes(), StandardCharsets.UTF_8)).getAsJsonObject();
                  Kf4f48084c4ba0516bf model = Kf4f48084c4ba0516bf.parse(root.getAsJsonObject("model"));
                  JsonElement anim = root.get("animation");
                  K24d5dfabcf442da65f animation = K24d5dfabcf442da65f.parse(anim != null && anim.isJsonObject() ? anim.getAsJsonObject() : null);
                  if (model != null) {
                     result = new Kf70c8667f5f3361806(model, animation);
                  }
               }
            } catch (Throwable var9) {
               if (in != null) {
                  try {
                     in.close();
                  } catch (Throwable var8) {
                     var9.addSuppressed(var8);
                  }
               }

               throw var9;
            }

            if (in != null) {
               in.close();
            }
         } catch (Exception ex) {
            int var10001 = e.id;
            System.err.println("[Cosmetics] model " + var10001 + ": " + String.valueOf(ex));
         }

         this.models.put(e.id, result);
         return result;
      }
   }

   public float seconds() {
      return (float)(System.currentTimeMillis() - this.startMillis) / 1000.0F;
   }

   public float textureOffset(Ka5dfe6b89219e29d09 e) {
      if (e.texFrames <= 1) {
         return 0.0F;
      } else {
         long ticks = (System.currentTimeMillis() - this.startMillis) / 50L;
         int frame = (int)(ticks / (long)e.texFrameTime % (long)e.texFrames);
         return (float)frame * e.texFrameShare;
      }
   }

   public class_2960 capeTexture() {
      Integer id = (Integer)this.equipped.get(K9a0eb2753a3143de82.CAPE);
      Ka5dfe6b89219e29d09 e = this.previewEntry != null && this.previewEntry.isCape() ? this.previewEntry : (id != null ? (Ka5dfe6b89219e29d09)this.byId.get(id) : null);
      if (e == null) {
         return null;
      } else if (e.capeFrames <= 1) {
         return e.texture;
      } else {
         class_2960[] frames = (class_2960[])this.capeFrames.computeIfAbsent(e.id, (k) -> this.Kf761bf6687996bcdde(e));
         if (frames != null && frames.length != 0) {
            long ticks = (System.currentTimeMillis() - this.startMillis) / 50L;
            return frames[(int)(ticks / (long)e.capeFrameTime % (long)frames.length)];
         } else {
            return e.texture;
         }
      }
   }

   private class_2960[] Kf761bf6687996bcdde(Ka5dfe6b89219e29d09 e) {
      class_310 mc = class_310.method_1551();
      if (!mc.method_18854()) {
         return null;
      } else {
         try {
            InputStream in = K697051202eac7cfc74.class.getResourceAsStream("/assets/luxvisuals/cosmetics/" + e.id + "/texture.png");

            Object var14;
            label81: {
               class_2960[] var15;
               try {
                  if (in == null) {
                     var14 = null;
                     break label81;
                  }

                  class_1011 strip = class_1011.method_4309(in);
                  int scale = Math.max(1, Math.round((float)e.capeFrameW / 22.0F));
                  class_2960[] out = new class_2960[e.capeFrames];

                  for(int i = 0; i < e.capeFrames; ++i) {
                     class_1011 frame = new class_1011(64 * scale, 32 * scale, true);

                     for(int y = 0; y < e.capeFrameH && i * e.capeFrameH + y < strip.method_4323(); ++y) {
                        for(int x = 0; x < e.capeFrameW && x < strip.method_4307(); ++x) {
                           frame.method_61941(x, y, strip.method_61940(x, i * e.capeFrameH + y));
                        }
                     }

                     class_2960 id = class_2960.method_60655("luxvisuals", "cosmetics_cape/" + e.id + "/" + i);
                     mc.method_1531().method_4616(id, new class_1043(frame));
                     out[i] = id;
                  }

                  strip.close();
                  var15 = out;
               } catch (Throwable var12) {
                  if (in != null) {
                     try {
                        in.close();
                     } catch (Throwable var11) {
                        var12.addSuppressed(var11);
                     }
                  }

                  throw var12;
               }

               if (in != null) {
                  in.close();
               }

               return var15;
            }

            if (in != null) {
               in.close();
            }

            return (class_2960[])var14;
         } catch (Exception ex) {
            int var10001 = e.id;
            System.err.println("[Cosmetics] cape frames " + var10001 + ": " + String.valueOf(ex));
            return null;
         }
      }
   }

   private File file() {
      return new File(K93d0ef925a2fcdf0d6.CONFIG_PATH_OTHER, "cosmetics.json");
   }

   private void Kf627710bbb3a19fd8e() {
      try {
         File f = this.file();
         if (!f.exists()) {
            return;
         }

         JsonObject o = JsonParser.parseString(Files.readString(f.toPath())).getAsJsonObject();

         for(K9a0eb2753a3143de82 t : K9a0eb2753a3143de82.values()) {
            if (o.has(t.key)) {
               int id = o.get(t.key).getAsInt();
               Ka5dfe6b89219e29d09 e = (Ka5dfe6b89219e29d09)this.byId.get(id);
               if (e != null && e.type == t) {
                  this.equipped.put(t, id);
               }
            }
         }
      } catch (Throwable e) {
         System.err.println("[Cosmetics] load: " + String.valueOf(e));
      }

   }

   private void K1e3576101fb4771b37() {
      try {
         JsonObject o = new JsonObject();

         for(Map.Entry<K9a0eb2753a3143de82, Integer> e : this.equipped.entrySet()) {
            o.addProperty(((K9a0eb2753a3143de82)e.getKey()).key, (Number)e.getValue());
         }

         File f = this.file();
         f.getParentFile().mkdirs();
         Files.writeString(f.toPath(), GSON.toJson(o));
      } catch (Throwable e) {
         System.err.println("[Cosmetics] save: " + String.valueOf(e));
      }

   }
}
