package code.miaware.client.features.cosmetics;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public final class K24d5dfabcf442da65f {
   public final Map<String, Kbc7ec19a6555bfbd23> clips = new LinkedHashMap();

   public static K24d5dfabcf442da65f parse(JsonObject root) {
      K24d5dfabcf442da65f anim = new K24d5dfabcf442da65f();
      if (root != null && root.has("animations")) {
         for(Map.Entry<String, JsonElement> e : root.getAsJsonObject("animations").entrySet()) {
            if (((JsonElement)e.getValue()).isJsonObject()) {
               JsonObject a = ((JsonElement)e.getValue()).getAsJsonObject();
               float length = a.has("animation_length") ? K04706c4125465238c7(a.get("animation_length"), 1.0F) : 0.0F;
               boolean loop = true;
               if (a.has("loop") && a.get("loop").isJsonPrimitive()) {
                  JsonPrimitive p = a.get("loop").getAsJsonPrimitive();
                  loop = p.isBoolean() ? p.getAsBoolean() : !"hold_on_last_frame".equals(p.getAsString());
               }

               Kbc7ec19a6555bfbd23 clip = new Kbc7ec19a6555bfbd23((String)e.getKey(), length, loop);
               float maxTime = 0.0F;
               if (a.has("bones")) {
                  for(Map.Entry<String, JsonElement> be : a.getAsJsonObject("bones").entrySet()) {
                     if (((JsonElement)be.getValue()).isJsonObject()) {
                        JsonObject bo = ((JsonElement)be.getValue()).getAsJsonObject();
                        K58e357daa04348ba18 track = new K58e357daa04348ba18();
                        track.rotation = channel(bo.get("rotation"), false);
                        track.position = channel(bo.get("position"), false);
                        track.scale = channel(bo.get("scale"), true);

                        for(Kf80ff9622dbb5b04b9 ch : new Kf80ff9622dbb5b04b9[]{track.rotation, track.position, track.scale}) {
                           if (ch != null && ch.times.length > 0) {
                              maxTime = Math.max(maxTime, ch.times[ch.times.length - 1]);
                           }
                        }

                        clip.bones.put((String)be.getKey(), track);
                     }
                  }
               }

               if (clip.length <= 0.0F) {
                  clip = K758eb98985d0660df8(clip, Math.max(maxTime, 0.05F));
               }

               anim.clips.put((String)e.getKey(), clip);
            }
         }

         return anim;
      } else {
         return anim;
      }
   }

   private static Kbc7ec19a6555bfbd23 K758eb98985d0660df8(Kbc7ec19a6555bfbd23 c, float length) {
      Kbc7ec19a6555bfbd23 out = new Kbc7ec19a6555bfbd23(c.name, length, c.loop);
      out.bones.putAll(c.bones);
      return out;
   }

   private static Kf80ff9622dbb5b04b9 channel(JsonElement el, boolean uniformScale) {
      if (el != null && !el.isJsonNull()) {
         if (!el.isJsonArray() && !el.isJsonPrimitive()) {
            if (!el.isJsonObject()) {
               return null;
            } else {
               List<Map.Entry<Float, JsonElement>> keys = new ArrayList();

               for(Map.Entry<String, JsonElement> e : el.getAsJsonObject().entrySet()) {
                  try {
                     keys.add(Map.entry(Float.parseFloat((String)e.getKey()), (JsonElement)e.getValue()));
                  } catch (NumberFormatException var16) {
                  }
               }

               if (keys.isEmpty()) {
                  return null;
               } else {
                  keys.sort(Entry.comparingByKey());
                  int n = keys.size();
                  float[] times = new float[n];
                  float[][] pre = new float[n][];
                  float[][] post = new float[n][];
                  int[] mode = new int[n];

                  for(int i = 0; i < n; ++i) {
                     times[i] = (Float)((Map.Entry)keys.get(i)).getKey();
                     JsonElement v = (JsonElement)((Map.Entry)keys.get(i)).getValue();
                     if (v.isJsonObject()) {
                        JsonObject o = v.getAsJsonObject();
                        float[] vecV = o.has("vector") ? K0d6d31f4c4f6267e94(o.get("vector"), uniformScale) : null;
                        float[] postV = o.has("post") ? K0d6d31f4c4f6267e94(o.get("post"), uniformScale) : vecV;
                        float[] preV = o.has("pre") ? K0d6d31f4c4f6267e94(o.get("pre"), uniformScale) : postV;
                        if (postV == null) {
                           postV = preV != null ? preV : new float[]{uniformScale ? 1.0F : 0.0F, uniformScale ? 1.0F : 0.0F, uniformScale ? 1.0F : 0.0F};
                        }

                        pre[i] = preV != null ? preV : postV;
                        post[i] = postV;
                        String lerp = o.has("lerp_mode") ? o.get("lerp_mode").getAsString().toLowerCase(Locale.ROOT) : "";
                        String easing = o.has("easing") ? o.get("easing").getAsString().toLowerCase(Locale.ROOT) : "";
                        mode[i] = lerp.contains("catmull") ? 1 : (easing.contains("inout") ? 2 : 0);
                     } else {
                        float[] value = K0d6d31f4c4f6267e94(v, uniformScale);
                        pre[i] = value;
                        post[i] = value;
                     }
                  }

                  return new Kf80ff9622dbb5b04b9(times, pre, post, mode);
               }
            }
         } else {
            float[] v = K0d6d31f4c4f6267e94(el, uniformScale);
            return new Kf80ff9622dbb5b04b9(new float[]{0.0F}, new float[][]{v}, new float[][]{v}, new int[]{0});
         }
      } else {
         return null;
      }
   }

   private static float[] K0d6d31f4c4f6267e94(JsonElement el, boolean uniformScale) {
      if (!el.isJsonArray()) {
         float v = K04706c4125465238c7(el, uniformScale ? 1.0F : 0.0F);
         return new float[]{v, v, v};
      } else {
         JsonArray a = el.getAsJsonArray();
         float[] out = new float[3];
         Arrays.fill(out, uniformScale ? 1.0F : 0.0F);

         for(int i = 0; i < Math.min(3, a.size()); ++i) {
            out[i] = K04706c4125465238c7(a.get(i), out[i]);
         }

         return out;
      }
   }

   private static float K04706c4125465238c7(JsonElement el, float def) {
      try {
         return el.getAsFloat();
      } catch (Exception var3) {
         return def;
      }
   }

   public Kbc7ec19a6555bfbd23 pick(Kb4880a82bc3a5e90a5 motion, boolean sneaking, boolean inWater) {
      if (this.clips.isEmpty()) {
         return null;
      } else {
         Kbc7ec19a6555bfbd23 base = null;
         if (motion == Kb4880a82bc3a5e90a5.FLYING) {
            base = this.Ke3654ef73e15fa5366("elytra", "fly");
         }

         if (base == null && motion != Kb4880a82bc3a5e90a5.IDLE) {
            base = this.Ke3654ef73e15fa5366("moving", "walk", "move");
         }

         if (base == null) {
            base = this.Ke3654ef73e15fa5366("idle", "main", "afk");
         }

         if (base == null) {
            for(Kbc7ec19a6555bfbd23 c : this.clips.values()) {
               String n = c.name.toLowerCase(Locale.ROOT);
               if (!n.contains("gui") && !n.contains(".sneak") && !n.contains(".water")) {
                  base = c;
                  break;
               }
            }
         }

         if (base == null) {
            base = (Kbc7ec19a6555bfbd23)this.clips.values().iterator().next();
         }

         String suffix = sneaking ? ".sneak" : (inWater ? ".water" : null);
         if (suffix != null) {
            Kbc7ec19a6555bfbd23 variant = (Kbc7ec19a6555bfbd23)this.clips.get(base.name + suffix);
            if (variant != null) {
               return variant;
            }
         }

         return base;
      }
   }

   private Kbc7ec19a6555bfbd23 Ke3654ef73e15fa5366(String... keys) {
      for(String key : keys) {
         for(Kbc7ec19a6555bfbd23 c : this.clips.values()) {
            String n = c.name.toLowerCase(Locale.ROOT);
            if (!n.contains("gui") && !n.endsWith(".sneak") && !n.endsWith(".water")) {
               String last = n.contains(".") ? n.substring(n.lastIndexOf(46) + 1) : n;
               if (last.contains(key)) {
                  return c;
               }
            }
         }
      }

      return null;
   }

   public static void apply(Kf4f48084c4ba0516bf model, Kbc7ec19a6555bfbd23 clip, float seconds) {
      model.resetAnimation();
      if (clip != null) {
         float t = clip.loop && clip.length > 0.0F ? seconds % clip.length : Math.min(seconds, clip.length);
         float[] v = new float[3];

         for(Map.Entry<String, K58e357daa04348ba18> e : clip.bones.entrySet()) {
            K8d9543f10197471a10 bone = (K8d9543f10197471a10)model.bones.get(e.getKey());
            if (bone != null) {
               K58e357daa04348ba18 tr = (K58e357daa04348ba18)e.getValue();
               if (tr.rotation != null) {
                  tr.rotation.sample(t, v);
                  bone.animRotX = (float)Math.toRadians((double)(-v[0]));
                  bone.animRotY = (float)Math.toRadians((double)(-v[1]));
                  bone.animRotZ = (float)Math.toRadians((double)v[2]);
               }

               if (tr.position != null) {
                  tr.position.sample(t, v);
                  bone.animPosX = v[0];
                  bone.animPosY = v[1];
                  bone.animPosZ = v[2];
               }

               if (tr.scale != null) {
                  tr.scale.sample(t, v);
                  bone.animScaleX = v[0];
                  bone.animScaleY = v[1];
                  bone.animScaleZ = v[2];
               }
            }
         }

      }
   }
}
