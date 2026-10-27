package code.miaware.client.util.render.effects;

import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import com.mojang.blaze3d.systems.RenderSystem;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1087;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_5819;
import net.minecraft.class_777;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

public final class K035b86a6e761d0d2cb implements AutoCloseable {
   private final K6588cb1ea6dceff65b pass = new K6588cb1ea6dceff65b();
   private ByteBuffer data;
   private ByteBuffer vertexData;
   private int vbo;
   private int capacity;
   private int white;
   private K0b86f0877e6ecce4fe uploaded;

   public static K0b86f0877e6ecce4fe build(class_2680 state, class_2338 pos) {
      class_310 mc = class_310.method_1551();
      class_1087 model = mc.method_1541().method_3349(state);
      List<float[]> vertices = new ArrayList();
      class_2960 atlas = null;
      class_5819 random = class_5819.method_43047();
      class_243 offset = state.method_26226(pos);
      class_2350[] directions = new class_2350[]{class_2350.field_11033, class_2350.field_11036, class_2350.field_11043, class_2350.field_11035, class_2350.field_11039, class_2350.field_11034, null};

      for(class_2350 direction : directions) {
         random.method_43052(state.method_26190(pos));

         for(class_777 quad : model.method_4707(state, direction, random)) {
            if (atlas == null) {
               atlas = quad.method_35788().method_45852();
            }

            int[] packed = quad.method_3357();
            int stride = packed.length / 4;
            float[][] points = new float[4][7];

            for(int i = 0; i < 4; ++i) {
               int k = i * stride;
               points[i] = new float[]{Float.intBitsToFloat(packed[k]), Float.intBitsToFloat(packed[k + 1]), Float.intBitsToFloat(packed[k + 2]), Float.intBitsToFloat(packed[k + 4]), Float.intBitsToFloat(packed[k + 5]), i >= 2 ? 1.0F : 0.0F, i != 1 && i != 2 ? 0.0F : 1.0F};
            }

            float[] p0 = points[0];
            float[] p1 = points[1];
            float[] p2 = points[2];
            Vector3f normal = (new Vector3f(p1[0] - p0[0], p1[1] - p0[1], p1[2] - p0[2])).cross(new Vector3f(p2[0] - p0[0], p2[1] - p0[1], p2[2] - p0[2]));
            if (!(normal.lengthSquared() < 1.0E-12F)) {
               normal.normalize().mul(0.0025F);

               for(float[] p : points) {
                  p[0] += normal.x + (float)offset.field_1352;
                  p[1] += normal.y + (float)offset.field_1351;
                  p[2] += normal.z + (float)offset.field_1350;
               }

               Kb05c24845eec9a3e06(vertices, points);
               if (vertices.size() > 65536) {
                  throw new IllegalStateException("Block model too large");
               }
            }
         }
      }

      if (vertices.isEmpty()) {
         for(class_238 box : state.method_26218(mc.field_1687, pos).method_1090()) {
            box(vertices, box.method_1014(0.0025));
         }

         atlas = null;
      }

      if (vertices.isEmpty()) {
         return null;
      } else {
         float[] result = new float[vertices.size() * 7];

         for(int i = 0; i < vertices.size(); ++i) {
            System.arraycopy(vertices.get(i), 0, result, i * 7, 7);
         }

         return new K0b86f0877e6ecce4fe(result, atlas);
      }
   }

   private static void Kb05c24845eec9a3e06(List<float[]> out, float[][] p) {
      for(int i : new int[]{0, 1, 2, 0, 2, 3}) {
         out.add(p[i]);
      }

   }

   private static void box(List<float[]> out, class_238 b) {
      float x = (float)b.field_1323;
      float y = (float)b.field_1322;
      float z = (float)b.field_1321;
      float X = (float)b.field_1320;
      float Y = (float)b.field_1325;
      float Z = (float)b.field_1324;
      float[][] corners = new float[][]{{x, y, z}, {X, y, z}, {X, Y, z}, {x, Y, z}, {x, y, Z}, {X, y, Z}, {X, Y, Z}, {x, Y, Z}};

      for(int[] face : new int[][]{{0, 1, 2, 3}, {5, 4, 7, 6}, {4, 0, 3, 7}, {1, 5, 6, 2}, {3, 2, 6, 7}, {4, 5, 1, 0}}) {
         float[][] p = new float[4][7];

         for(int i = 0; i < 4; ++i) {
            System.arraycopy(corners[face[i]], 0, p[i], 0, 3);
            p[i][3] = p[i][5] = i >= 2 ? 1.0F : 0.0F;
            p[i][4] = p[i][6] = i != 1 && i != 2 ? 0.0F : 1.0F;
         }

         Kb05c24845eec9a3e06(out, p);
      }

   }

   public void render(K9ee087b2e1791ad6b2 event, K0b86f0877e6ecce4fe shape, class_243 origin, float fade, long now, int color, boolean themeOutline, float opacity, float speed, float scale, float width, float outlineOpacity, float glow, int mode) {
      if (shape != null && !(fade < 0.001F)) {
         int atlasTexture = shape.atlas == null ? 0 : class_310.method_1551().method_1531().method_4619(shape.atlas).method_4624();

         try (Kb74d5d2c9e20d74d0d state = new Kb74d5d2c9e20d74d0d()) {
            this.pass.init("block_model_overlay", "OverlayData", 208);
            if (this.data == null) {
               this.data = MemoryUtil.memAlloc(208);
            }

            class_243 camera = event.cameraPos();
            float ox = (float)(origin.field_1352 - camera.field_1352);
            float oy = (float)(origin.field_1351 - camera.field_1351);
            float oz = (float)(origin.field_1350 - camera.field_1350);
            this.data.clear();
            event.matrixStack().method_23760().method_23761().get(0, this.data);
            RenderSystem.getProjectionMatrix().get(64, this.data);
            this.data.position(128);
            this.data.putFloat((float)(color >>> 16 & 255) / 255.0F).putFloat((float)(color >>> 8 & 255) / 255.0F).putFloat((float)(color & 255) / 255.0F).putFloat(themeOutline ? 1.0F : 0.0F);
            this.data.putFloat((float)(now % 1000000000000L) / 1.0E9F).putFloat(speed).putFloat(scale).putFloat(opacity);
            this.data.putFloat(width).putFloat(outlineOpacity).putFloat(glow).putFloat((float)mode);
            this.data.putFloat(ox).putFloat(oy).putFloat(oz).putFloat(0.0F);
            this.data.putFloat(ox + 1.0F).putFloat(oy + 1.0F).putFloat(oz + 1.0F).putFloat(fade);
            this.data.flip();
            this.pass.bind(this.data);
            if (this.vbo == 0) {
               this.vbo = GL15.glGenBuffers();
            }

            GL15.glBindBuffer(34962, this.vbo);
            if (this.uploaded != shape) {
               int need = shape.vertices.length * 4;
               if (this.capacity < need) {
                  if (this.vertexData != null) {
                     MemoryUtil.memFree(this.vertexData);
                  }

                  this.capacity = Math.max(need, 65536);
                  this.vertexData = MemoryUtil.memAlloc(this.capacity);
                  GL15.glBufferData(34962, (long)this.capacity, 35048);
               }

               this.vertexData.clear();
               this.vertexData.asFloatBuffer().put(shape.vertices);
               this.vertexData.limit(need);
               GL15.glBufferSubData(34962, 0L, this.vertexData);
               this.uploaded = shape;
            }

            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            GL20.glEnableVertexAttribArray(2);
            GL20.glVertexAttribPointer(0, 3, 5126, false, 28, 0L);
            GL20.glVertexAttribPointer(1, 2, 5126, false, 28, 12L);
            GL20.glVertexAttribPointer(2, 2, 5126, false, 28, 20L);
            int texture;
            if (shape.atlas != null) {
               texture = atlasTexture;
            } else {
               if (this.white == 0) {
                  this.white = GL11.glGenTextures();
                  GL11.glBindTexture(3553, this.white);
                  GL11.glTexImage2D(3553, 0, 32856, 1, 1, 0, 6408, 5121, new int[]{-1});
                  GL11.glTexParameteri(3553, 10241, 9728);
                  GL11.glTexParameteri(3553, 10240, 9728);
               }

               texture = this.white;
            }

            this.pass.sampler("Sampler0", texture);
            GL11.glDrawArrays(4, 0, shape.count());
         }

      }
   }

   public void close() {
      this.pass.close();
      if (this.vbo != 0) {
         GL15.glDeleteBuffers(this.vbo);
      }

      if (this.white != 0) {
         GL11.glDeleteTextures(this.white);
      }

      if (this.data != null) {
         MemoryUtil.memFree(this.data);
      }

      if (this.vertexData != null) {
         MemoryUtil.memFree(this.vertexData);
      }

      this.vbo = this.white = this.capacity = 0;
      this.data = this.vertexData = null;
      this.uploaded = null;
   }
}
