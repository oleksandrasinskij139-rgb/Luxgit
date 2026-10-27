package code.miaware.client.util.render.effects;

import java.nio.ByteBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

public final class K2c4b0ab5cb7fa68cf2 implements AutoCloseable {
   private final K6588cb1ea6dceff65b pass = new K6588cb1ea6dceff65b();
   private ByteBuffer data;

   public void render(float screenW, float screenH, float cx, float cy, float sf, float scale, float alpha, int hovered, float[] hover, int color) {
      try (Kb74d5d2c9e20d74d0d state = new Kb74d5d2c9e20d74d0d()) {
         this.pass.init("triple_wheel", "WheelData", 128);
         if (this.data == null) {
            this.data = MemoryUtil.memAlloc(128);
         }

         float outer = 112.0F * scale * sf;
         float inner = 40.0F * scale * sf;
         float pad = outer + 26.0F * sf;
         this.data.clear();
         this.data.putFloat(screenW).putFloat(screenH).putFloat(cx * sf - pad).putFloat(cy * sf - pad);
         this.data.putFloat(pad * 2.0F).putFloat(pad * 2.0F).putFloat(pad).putFloat(pad);
         this.data.putFloat(inner).putFloat(outer).putFloat(7.0F).putFloat(3.0F);
         this.data.putFloat((float)hovered).putFloat(1.8F * sf).putFloat(0.55F).putFloat(alpha);
         float r = (float)(color >>> 16 & 255) / 255.0F;
         float g = (float)(color >>> 8 & 255) / 255.0F;
         float b = (float)(color & 255) / 255.0F;
         this.data.putFloat(r).putFloat(g).putFloat(b).putFloat(1.0F);

         for(int i = 0; i < 3; ++i) {
            float m = 0.82F + (float)i * 0.07F;
            this.data.putFloat(r * m).putFloat(g * m).putFloat(b * m).putFloat(hover[i]);
         }

         this.data.flip();
         this.pass.bind(this.data);
         GL11.glDisable(2929);
         GL11.glDrawArrays(4, 0, 6);
      }

   }

   public void close() {
      this.pass.close();
      if (this.data != null) {
         MemoryUtil.memFree(this.data);
      }

      this.data = null;
   }
}
