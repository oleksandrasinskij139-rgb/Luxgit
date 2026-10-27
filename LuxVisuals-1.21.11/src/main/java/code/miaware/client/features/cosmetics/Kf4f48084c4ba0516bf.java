package code.miaware.client.features.cosmetics;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_7833;

public final class Kf4f48084c4ba0516bf {
   private static final String[] FACES = new String[]{"west", "east", "down", "up", "north", "south"};
   private static final float[][] NORMALS = new float[][]{{-1.0F, 0.0F, 0.0F}, {1.0F, 0.0F, 0.0F}, {0.0F, -1.0F, 0.0F}, {0.0F, 1.0F, 0.0F}, {0.0F, 0.0F, -1.0F}, {0.0F, 0.0F, 1.0F}};
   public final List<K8d9543f10197471a10> roots = new ArrayList();
   public final Map<String, K8d9543f10197471a10> bones = new HashMap();

   public static Kf4f48084c4ba0516bf parse(JsonObject root) {
      JsonArray geometries = root.getAsJsonArray("minecraft:geometry");
      if (geometries != null && !geometries.isEmpty()) {
         JsonObject geometry = geometries.get(0).getAsJsonObject();
         JsonObject desc = geometry.has("description") ? geometry.getAsJsonObject("description") : new JsonObject();
         float texW = desc.has("texture_width") ? desc.get("texture_width").getAsFloat() : 64.0F;
         float texH = desc.has("texture_height") ? desc.get("texture_height").getAsFloat() : 64.0F;
         Kf4f48084c4ba0516bf model = new Kf4f48084c4ba0516bf();
         JsonArray bonesJson = geometry.getAsJsonArray("bones");
         if (bonesJson == null) {
            return model;
         } else {
            List<String[]> links = new ArrayList();

            for(JsonElement el : bonesJson) {
               JsonObject b = el.getAsJsonObject();
               String name = b.get("name").getAsString();
               float[] pivot = K7f55bef83e4350581e(b, "pivot", 0.0F, 0.0F, 0.0F);
               float[] rot = K7f55bef83e4350581e(b, "rotation", 0.0F, 0.0F, 0.0F);
               K8d9543f10197471a10 bone = new K8d9543f10197471a10(name, -pivot[0] / 16.0F, pivot[1] / 16.0F, pivot[2] / 16.0F, (float)Math.toRadians((double)(-rot[0])), (float)Math.toRadians((double)(-rot[1])), (float)Math.toRadians((double)rot[2]));
               if (b.has("cubes")) {
                  for(JsonElement c : b.getAsJsonArray("cubes")) {
                     bone.cubes.add(K5000a939902d1357c9(c.getAsJsonObject(), texW, texH));
                  }
               }

               model.bones.put(name, bone);
               links.add(new String[]{name, b.has("parent") ? b.get("parent").getAsString() : null});
            }

            for(String[] link : links) {
               K8d9543f10197471a10 bone = (K8d9543f10197471a10)model.bones.get(link[0]);
               K8d9543f10197471a10 parent = link[1] != null ? (K8d9543f10197471a10)model.bones.get(link[1]) : null;
               if (parent != null && parent != bone) {
                  parent.children.add(bone);
               } else {
                  model.roots.add(bone);
               }
            }

            return model;
         }
      } else {
         return null;
      }
   }

   private static Ka06b3f80b800a13ac7 K5000a939902d1357c9(JsonObject c, float texW, float texH) {
      float[] origin = K7f55bef83e4350581e(c, "origin", 0.0F, 0.0F, 0.0F);
      float[] size = K7f55bef83e4350581e(c, "size", 1.0F, 1.0F, 1.0F);
      float[] pivot = c.has("pivot") ? K7f55bef83e4350581e(c, "pivot", 0.0F, 0.0F, 0.0F) : (float[])(([F)origin).clone();
      float[] rot = K7f55bef83e4350581e(c, "rotation", 0.0F, 0.0F, 0.0F);
      float inflate = c.has("inflate") ? c.get("inflate").getAsFloat() : 0.0F;
      Ka06b3f80b800a13ac7 cube = new Ka06b3f80b800a13ac7();
      cube.pivotX = -pivot[0] / 16.0F;
      cube.pivotY = pivot[1] / 16.0F;
      cube.pivotZ = pivot[2] / 16.0F;
      cube.rotX = (float)Math.toRadians((double)(-rot[0]));
      cube.rotY = (float)Math.toRadians((double)(-rot[1]));
      cube.rotZ = (float)Math.toRadians((double)rot[2]);
      cube.flatX = size[0] == 0.0F;
      cube.flatY = size[1] == 0.0F;
      cube.flatZ = size[2] == 0.0F;
      float ox = origin[0] - inflate;
      float oy = origin[1] - inflate;
      float oz = origin[2] - inflate;
      float w = size[0] + inflate * 2.0F;
      float h = size[1] + inflate * 2.0F;
      float d = size[2] + inflate * 2.0F;
      float x0 = -(ox + w) / 16.0F;
      float y0 = oy / 16.0F;
      float z0 = oz / 16.0F;
      float x1 = -ox / 16.0F;
      float y1 = (oy + h) / 16.0F;
      float z1 = (oz + d) / 16.0F;
      JsonElement uvEl = c.get("uv");
      JsonObject perFace = uvEl != null && uvEl.isJsonObject() ? uvEl.getAsJsonObject() : null;
      float boxU = 0.0F;
      float boxV = 0.0F;
      if (uvEl != null && uvEl.isJsonArray()) {
         boxU = uvEl.getAsJsonArray().get(0).getAsFloat();
         boxV = uvEl.getAsJsonArray().get(1).getAsFloat();
      }

      for(int f = 0; f < FACES.length; ++f) {
         String face = FACES[f];
         float u;
         float v;
         float du;
         float dv;
         if (perFace != null) {
            if (!perFace.has(face)) {
               continue;
            }

            JsonObject fo = perFace.getAsJsonObject(face);
            JsonArray uv = fo.getAsJsonArray("uv");
            JsonArray uvSize = fo.getAsJsonArray("uv_size");
            if (uv == null || uvSize == null) {
               continue;
            }

            u = uv.get(0).getAsFloat() / texW;
            v = uv.get(1).getAsFloat() / texH;
            du = uvSize.get(0).getAsFloat() / texW;
            dv = uvSize.get(1).getAsFloat() / texH;
         } else {
            float sw = size[0];
            float sh = size[1];
            float sd = size[2];
            switch (face) {
               case "north":
                  u = boxU + sd;
                  v = boxV + sd;
                  du = sw;
                  dv = sh;
                  break;
               case "south":
                  u = boxU + sd + sw + sd;
                  v = boxV + sd;
                  du = sw;
                  dv = sh;
                  break;
               case "east":
                  u = boxU;
                  v = boxV + sd;
                  du = sd;
                  dv = sh;
                  break;
               case "west":
                  u = boxU + sd + sw;
                  v = boxV + sd;
                  du = sd;
                  dv = sh;
                  break;
               case "up":
                  u = boxU + sd;
                  v = boxV;
                  du = sw;
                  dv = sd;
                  break;
               default:
                  u = boxU + sd + sw;
                  v = boxV;
                  du = sw;
                  dv = sd;
            }

            u /= texW;
            v /= texH;
            du /= texW;
            dv /= texH;
         }

         Kc77cb34e1a69909410 q = Ka36d93fe241b8bf8eb(face, x0, y0, z0, x1, y1, z1, u, v, du, dv);
         q.nx = NORMALS[f][0];
         q.ny = NORMALS[f][1];
         q.nz = NORMALS[f][2];
         cube.quads.add(q);
      }

      return cube;
   }

   private static Kc77cb34e1a69909410 Ka36d93fe241b8bf8eb(String face, float x0, float y0, float z0, float x1, float y1, float z1, float u, float v, float du, float dv) {
      float u1 = u + du;
      float v1 = v + dv;
      float[][] p;
      float[][] t;
      switch (face) {
         case "north":
            p = new float[][]{{x1, y1, z0}, {x0, y1, z0}, {x0, y0, z0}, {x1, y0, z0}};
            t = new float[][]{{u, v}, {u1, v}, {u1, v1}, {u, v1}};
            break;
         case "south":
            p = new float[][]{{x0, y1, z1}, {x1, y1, z1}, {x1, y0, z1}, {x0, y0, z1}};
            t = new float[][]{{u, v}, {u1, v}, {u1, v1}, {u, v1}};
            break;
         case "east":
            p = new float[][]{{x1, y1, z1}, {x1, y1, z0}, {x1, y0, z0}, {x1, y0, z1}};
            t = new float[][]{{u, v}, {u1, v}, {u1, v1}, {u, v1}};
            break;
         case "west":
            p = new float[][]{{x0, y1, z0}, {x0, y1, z1}, {x0, y0, z1}, {x0, y0, z0}};
            t = new float[][]{{u, v}, {u1, v}, {u1, v1}, {u, v1}};
            break;
         case "up":
            p = new float[][]{{x0, y1, z0}, {x0, y1, z1}, {x1, y1, z1}, {x1, y1, z0}};
            t = new float[][]{{u, v}, {u, v1}, {u1, v1}, {u1, v}};
            break;
         default:
            p = new float[][]{{x1, y0, z0}, {x1, y0, z1}, {x0, y0, z1}, {x0, y0, z0}};
            t = new float[][]{{u, v}, {u, v1}, {u1, v1}, {u1, v}};
      }

      Kc77cb34e1a69909410 q = new Kc77cb34e1a69909410();

      for(int i = 0; i < 4; ++i) {
         q.pos[i * 3] = p[i][0];
         q.pos[i * 3 + 1] = p[i][1];
         q.pos[i * 3 + 2] = p[i][2];
         q.uv[i * 2] = t[i][0];
         q.uv[i * 2 + 1] = t[i][1];
      }

      return q;
   }

   private static float[] K7f55bef83e4350581e(JsonObject o, String key, float... def) {
      if (o.has(key) && o.get(key).isJsonArray()) {
         JsonArray a = o.getAsJsonArray(key);
         float[] out = (float[])(([F)def).clone();

         for(int i = 0; i < Math.min(a.size(), out.length); ++i) {
            try {
               out[i] = a.get(i).getAsFloat();
            } catch (Exception var7) {
            }
         }

         return out;
      } else {
         return def;
      }
   }

   public void resetAnimation() {
      for(K8d9543f10197471a10 b : this.bones.values()) {
         b.resetAnimation();
      }

   }

   public void render(class_4587 ms, class_4588 vc, int light, int overlay, float vOffset) {
      for(K8d9543f10197471a10 bone : this.roots) {
         this.K247d8e56547d687bfb(bone, ms, vc, light, overlay, vOffset);
      }

   }

   private void K247d8e56547d687bfb(K8d9543f10197471a10 b, class_4587 ms, class_4588 vc, int light, int overlay, float vOffset) {
      ms.method_22903();
      if (b.animPosX != 0.0F || b.animPosY != 0.0F || b.animPosZ != 0.0F) {
         ms.method_46416(-b.animPosX / 16.0F, b.animPosY / 16.0F, b.animPosZ / 16.0F);
      }

      ms.method_46416(b.pivotX, b.pivotY, b.pivotZ);
      rotate(ms, b.baseRotX + b.animRotX, b.baseRotY + b.animRotY, b.baseRotZ + b.animRotZ);
      if (b.animScaleX != 1.0F || b.animScaleY != 1.0F || b.animScaleZ != 1.0F) {
         ms.method_22905(b.animScaleX, b.animScaleY, b.animScaleZ);
      }

      ms.method_46416(-b.pivotX, -b.pivotY, -b.pivotZ);

      for(Ka06b3f80b800a13ac7 cube : b.cubes) {
         ms.method_22903();
         ms.method_46416(cube.pivotX, cube.pivotY, cube.pivotZ);
         rotate(ms, cube.rotX, cube.rotY, cube.rotZ);
         ms.method_46416(-cube.pivotX, -cube.pivotY, -cube.pivotZ);
         class_4587.class_4665 entry = ms.method_23760();

         for(Kc77cb34e1a69909410 q : cube.quads) {
            float nx = !cube.flatY && !cube.flatZ ? q.nx : Math.abs(q.nx);
            float ny = !cube.flatX && !cube.flatZ ? q.ny : Math.abs(q.ny);
            float nz = !cube.flatX && !cube.flatY ? q.nz : Math.abs(q.nz);

            for(int i = 0; i < 4; ++i) {
               vc.method_56824(entry, q.pos[i * 3], q.pos[i * 3 + 1], q.pos[i * 3 + 2]).method_39415(-1).method_22913(q.uv[i * 2], q.uv[i * 2 + 1] + vOffset).method_22922(overlay).method_60803(light).method_60831(entry, nx, ny, nz);
            }
         }

         ms.method_22909();
      }

      for(K8d9543f10197471a10 child : b.children) {
         this.K247d8e56547d687bfb(child, ms, vc, light, overlay, vOffset);
      }

      ms.method_22909();
   }

   private static void rotate(class_4587 ms, float x, float y, float z) {
      if (z != 0.0F) {
         ms.method_22907(class_7833.field_40718.rotation(z));
      }

      if (y != 0.0F) {
         ms.method_22907(class_7833.field_40716.rotation(y));
      }

      if (x != 0.0F) {
         ms.method_22907(class_7833.field_40714.rotation(x));
      }

   }
}
