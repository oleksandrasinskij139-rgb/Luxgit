package code.miaware.api.utils.render.elytra;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10142;
import org.lwjgl.opengl.GL11;

final class K54fd1c8282fb3dd387 implements AutoCloseable {
   private final boolean blend = GL11.glIsEnabled(3042);
   private final boolean cull = GL11.glIsEnabled(2884);
   private final boolean depth = GL11.glIsEnabled(2929);
   private final boolean mask = GL11.glGetBoolean(2930);
   private final int src = GL11.glGetInteger(32969);
   private final int dst = GL11.glGetInteger(32968);
   private final int srcAlpha = GL11.glGetInteger(32971);
   private final int dstAlpha = GL11.glGetInteger(32970);
   private final float red;
   private final float green;
   private final float blue;
   private final float alpha;

   K54fd1c8282fb3dd387(boolean world) {
      float[] color = RenderSystem.getShaderColor();
      this.red = color[0];
      this.green = color[1];
      this.blue = color[2];
      this.alpha = color[3];
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableCull();
      if (world) {
         RenderSystem.enableDepthTest();
      } else {
         RenderSystem.disableDepthTest();
      }

      RenderSystem.depthMask(false);
      RenderSystem.setShader(class_10142.field_53876);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public void close() {
      RenderSystem.setShaderColor(this.red, this.green, this.blue, this.alpha);
      RenderSystem.depthMask(this.mask);
      RenderSystem.blendFuncSeparate(this.src, this.dst, this.srcAlpha, this.dstAlpha);
      if (this.blend) {
         RenderSystem.enableBlend();
      } else {
         RenderSystem.disableBlend();
      }

      if (this.cull) {
         RenderSystem.enableCull();
      } else {
         RenderSystem.disableCull();
      }

      if (this.depth) {
         RenderSystem.enableDepthTest();
      } else {
         RenderSystem.disableDepthTest();
      }

   }
}
