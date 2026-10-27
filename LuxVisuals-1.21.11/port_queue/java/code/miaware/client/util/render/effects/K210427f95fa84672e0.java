package code.miaware.client.util.render.effects;

import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import com.mojang.blaze3d.systems.RenderSystem;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.class_243;
import net.minecraft.class_276;
import net.minecraft.class_310;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

public final class K210427f95fa84672e0 implements AutoCloseable {
   public static final int MAX_BUBBLES = 128;
   private static final int HEADER_SIZE = 144;
   private static final int DATA_SIZE = 4240;
   private final K6588cb1ea6dceff65b pass = new K6588cb1ea6dceff65b();
   private ByteBuffer data;
   private int scene;
   private final List<Kaf2b08863adff4bf8f> visible = new ArrayList();
   private final Vector4f projectedBubble = new Vector4f();
   private static final Comparator<Kaf2b08863adff4bf8f> DEPTH_ORDER = Comparator.comparingDouble((b) -> (double)b.position().z);
   private int width;
   private int height;

   public void render(K9ee087b2e1791ad6b2 event, List<Ke506047f060db15818> bubbles, long now, int color, float refraction, float chromatic, float tint, float outline, float wobble) {
      class_310 client = class_310.method_1551();
      class_276 target = client.method_1522();
      if (target != null && target.method_30277() > 0 && target.field_1482 >= 2 && target.field_1481 >= 2) {
         class_243 camera = client.field_1773.method_19418().method_19326();
         this.visible.clear();

         for(Ke506047f060db15818 bubble : bubbles) {
            float progress = (float)((double)(now - bubble.birth()) / (double)bubble.duration());
            if (!(progress < 0.0F) && !(progress >= 1.0F)) {
               class_243 position = bubble.position();
               Vector4f view = new Vector4f((float)(position.field_1352 - camera.field_1352), (float)(position.field_1351 + (double)progress * 0.13 - camera.field_1351), (float)(position.field_1350 - camera.field_1350), 1.0F);
               event.matrixStack().method_23760().method_23761().transform(view);
               if (!(view.z >= -0.1F) && !(view.lengthSquared() > 1600.0F)) {
                  float fadeIn = Math.min(1.0F, progress / 0.1F);
                  float fadeOut = Math.min(1.0F, (1.0F - progress) / 0.3F);
                  this.visible.add(new Kaf2b08863adff4bf8f(view, bubble.radius() * (0.8F + 0.2F * fadeIn), fadeIn * fadeOut, bubble.phase()));
               }
            }
         }

         if (!this.visible.isEmpty()) {
            this.visible.sort(DEPTH_ORDER);

            try (Kb74d5d2c9e20d74d0d state = new Kb74d5d2c9e20d74d0d()) {
               this.Ke0f3b4c5bd119ca3d4(target.field_1482, target.field_1481);
               this.data.clear();
               RenderSystem.getProjectionMatrix().get(0, this.data);
               this.data.position(64);
               this.data.putFloat((float)this.width).putFloat((float)this.height).putFloat(refraction).putFloat(chromatic);
               this.data.putFloat(tint).putFloat(outline).putFloat((float)(now % 1000000000000L) / 1.0E9F).putFloat(wobble);
               this.data.putFloat((float)(color >>> 16 & 255) / 255.0F).putFloat((float)(color >>> 8 & 255) / 255.0F).putFloat((float)(color & 255) / 255.0F).putFloat(1.0F);
               int count = Math.min(this.visible.size(), 128);
               int slots = count;
               float left = 1.0F;
               float bottom = 1.0F;
               float right = -1.0F;
               float top = -1.0F;

               for(int i = 0; i < count; ++i) {
                  Kaf2b08863adff4bf8f bubble = (Kaf2b08863adff4bf8f)this.visible.get(i);
                  Vector4f clip = RenderSystem.getProjectionMatrix().transform(this.projectedBubble.set(bubble.position()));
                  float rx = Math.abs(RenderSystem.getProjectionMatrix().m00() * bubble.radius() / clip.w) * 1.75F;
                  float ry = Math.abs(RenderSystem.getProjectionMatrix().m11() * bubble.radius() / clip.w) * 1.75F;
                  left = Math.min(left, clip.x / clip.w - rx);
                  right = Math.max(right, clip.x / clip.w + rx);
                  bottom = Math.min(bottom, clip.y / clip.w - ry);
                  top = Math.max(top, clip.y / clip.w + ry);
               }

               this.data.putFloat(Math.max(-1.0F, left)).putFloat(Math.max(-1.0F, bottom)).putFloat(Math.min(1.0F, right)).putFloat(Math.min(1.0F, top));
               this.data.putFloat((float)count).putFloat(0.0F).putFloat(0.0F).putFloat(0.0F);

               for(int i = 0; i < slots; ++i) {
                  if (i < count) {
                     Kaf2b08863adff4bf8f bubble = (Kaf2b08863adff4bf8f)this.visible.get(i);
                     this.data.putFloat(bubble.position().x).putFloat(bubble.position().y).putFloat(bubble.position().z).putFloat(bubble.radius());
                  } else {
                     this.data.putFloat(0.0F).putFloat(0.0F).putFloat(0.0F).putFloat(0.0F);
                  }
               }

               this.data.position(2192);

               for(int i = 0; i < slots; ++i) {
                  if (i < count) {
                     Kaf2b08863adff4bf8f bubble = (Kaf2b08863adff4bf8f)this.visible.get(i);
                     this.data.putFloat(bubble.alpha()).putFloat(bubble.phase()).putFloat(0.0F).putFloat(0.0F);
                  } else {
                     this.data.putFloat(0.0F).putFloat(0.0F).putFloat(0.0F).putFloat(0.0F);
                  }
               }

               if (Math.max(-1.0F, left) >= Math.min(1.0F, right) || Math.max(-1.0F, bottom) >= Math.min(1.0F, top)) {
                  return;
               }

               GL30.glBindFramebuffer(36008, GL11.glGetInteger(36006));
               GL11.glBindTexture(3553, this.scene);
               GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, this.width, this.height);
               this.data.flip();
               this.pass.bind(this.data);
               this.pass.sampler("uScene", this.scene);
               GL11.glDrawArrays(4, 0, 6);
            }

         }
      }
   }

   private void Ke0f3b4c5bd119ca3d4(int newWidth, int newHeight) {
      this.pass.init("glass_vapor", "VaporData", 4240);
      if (this.data == null) {
         this.data = MemoryUtil.memAlloc(4240);
      }

      if (this.scene == 0 || this.width != newWidth || this.height != newHeight) {
         if (this.scene != 0) {
            GL11.glDeleteTextures(this.scene);
         }

         this.scene = GL11.glGenTextures();
         GL11.glBindTexture(3553, this.scene);
         GL11.glTexImage2D(3553, 0, 32856, newWidth, newHeight, 0, 6408, 5121, (ByteBuffer)null);
         GL11.glTexParameteri(3553, 10241, 9729);
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
         this.width = newWidth;
         this.height = newHeight;
      }
   }

   public void close() {
      this.pass.close();
      if (this.scene != 0) {
         GL11.glDeleteTextures(this.scene);
      }

      this.scene = 0;
      this.width = this.height = 0;
      this.visible.clear();
      if (this.data != null) {
         MemoryUtil.memFree(this.data);
      }

      this.data = null;
   }
}
