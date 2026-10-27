package code.miaware.client.features.modules.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10142;
import net.minecraft.class_5944;
import org.lwjgl.opengl.GL11;

final class K96b2df1735cfd9ad42 implements AutoCloseable {
   private final boolean blend = GL11.glIsEnabled(3042);
   private final boolean cull = GL11.glIsEnabled(2884);
   private final boolean depth = GL11.glIsEnabled(2929);
   private final boolean mask = GL11.glGetBoolean(2930);
   private final int src = GL11.glGetInteger(32969);
   private final int dst = GL11.glGetInteger(32968);
   private final int srcA = GL11.glGetInteger(32971);
   private final int dstA = GL11.glGetInteger(32970);
   private final float[] color = (float[])RenderSystem.getShaderColor().clone();
   private final class_5944 shader = RenderSystem.getShader();

   K96b2df1735cfd9ad42() {
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableCull();
      RenderSystem.enableDepthTest();
      RenderSystem.depthMask(false);
      RenderSystem.setShader(class_10142.field_53876);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public void close() {
      RenderSystem.setShader(this.shader);
      RenderSystem.setShaderColor(this.color[0], this.color[1], this.color[2], this.color[3]);
      RenderSystem.depthMask(this.mask);
      RenderSystem.blendFuncSeparate(this.src, this.dst, this.srcA, this.dstA);
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
