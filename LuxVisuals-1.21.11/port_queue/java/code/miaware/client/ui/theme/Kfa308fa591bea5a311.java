package code.miaware.client.ui.theme;

import code.miaware.client.util.render.effects.K6588cb1ea6dceff65b;
import code.miaware.client.util.render.effects.Kb74d5d2c9e20d74d0d;
import java.nio.ByteBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

public final class Kfa308fa591bea5a311 implements AutoCloseable {
   private final K6588cb1ea6dceff65b pass = new K6588cb1ea6dceff65b();
   private ByteBuffer data;
   private int snapshot;
   private int width;
   private int height;
   private int tint;
   private float originX;
   private float originY;
   private long started;
   private static final double DURATION_NS = (double)7.2E8F;

   public boolean active() {
      return this.snapshot != 0;
   }

   public void begin(int sourceFbo, int width, int height, float x, float y, int tint, long now) {
      if (width > 0 && height > 0) {
         try (Kb74d5d2c9e20d74d0d ignored = new Kb74d5d2c9e20d74d0d()) {
            this.pass.init("theme_reveal", "RevealData", 48);
            if (this.data == null) {
               this.data = MemoryUtil.memAlloc(48);
            }

            this.cancel();
            this.width = width;
            this.height = height;
            this.tint = tint;
            this.originX = Math.clamp(x, 0.0F, 1.0F) * (float)width;
            this.originY = (1.0F - Math.clamp(y, 0.0F, 1.0F)) * (float)height;
            this.snapshot = GL11.glGenTextures();
            GL13.glActiveTexture(33984);
            GL11.glBindTexture(3553, this.snapshot);
            GL11.glTexParameteri(3553, 10241, 9729);
            GL11.glTexParameteri(3553, 10240, 9729);
            GL11.glTexParameteri(3553, 10242, 33071);
            GL11.glTexParameteri(3553, 10243, 33071);
            GL11.glTexImage2D(3553, 0, 32856, width, height, 0, 6408, 5121, (ByteBuffer)null);
            GL30.glBindFramebuffer(36008, sourceFbo);
            GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, width, height);
            this.started = now;
         }

      }
   }

   public void render(int width, int height, long now) {
      if (this.active()) {
         double progress = Math.max((double)0.0F, (double)(now - this.started) / (double)7.2E8F);
         if (width == this.width && height == this.height && !(progress >= (double)1.0F)) {
            float t = (float)progress;
            float eased = 1.0F - (float)Math.pow((double)(1.0F - t), 2.4);
            float feather = Math.max(12.0F, (float)Math.min(width, height) * 0.026F);
            float reach = (float)Math.hypot((double)Math.max(this.originX, (float)width - this.originX), (double)Math.max(this.originY, (float)height - this.originY));
            float radius = -feather * 2.0F + (reach + feather * 4.0F) * eased;
            this.data.clear();
            this.data.putFloat((float)width).putFloat((float)height).putFloat(this.originX).putFloat(this.originY);
            this.data.putFloat(radius).putFloat(feather).putFloat(t).putFloat(0.0F);
            this.data.putFloat((float)(this.tint >> 16 & 255) / 255.0F).putFloat((float)(this.tint >> 8 & 255) / 255.0F).putFloat((float)(this.tint & 255) / 255.0F).putFloat(1.0F);
            this.data.flip();
            boolean scissor = GL11.glIsEnabled(3089);
            int[] viewport = new int[4];
            GL11.glGetIntegerv(2978, viewport);

            try {
               Kb74d5d2c9e20d74d0d ignored = new Kb74d5d2c9e20d74d0d();

               try {
                  this.pass.bind(this.data);
                  this.pass.sampler("PreviousFrame", this.snapshot);
                  GL11.glDisable(2929);
                  GL11.glDisable(3089);
                  GL11.glViewport(0, 0, width, height);
                  GL11.glDrawArrays(4, 0, 6);
               } catch (Throwable var22) {
                  try {
                     ignored.close();
                  } catch (Throwable var21) {
                     var22.addSuppressed(var21);
                  }

                  throw var22;
               }

               ignored.close();
            } finally {
               GL11.glViewport(viewport[0], viewport[1], viewport[2], viewport[3]);
               if (scissor) {
                  GL11.glEnable(3089);
               }

            }

         } else {
            this.cancel();
         }
      }
   }

   public void cancel() {
      if (this.snapshot != 0) {
         GL11.glDeleteTextures(this.snapshot);
      }

      this.snapshot = 0;
   }

   public void close() {
      this.cancel();
      this.pass.close();
      if (this.data != null) {
         MemoryUtil.memFree(this.data);
         this.data = null;
      }

   }
}
