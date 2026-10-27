package code.miaware.client.features.modules.render.killeffect;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10142;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4587;
import net.minecraft.class_9801;
import net.minecraft.class_293.class_5596;
import org.lwjgl.opengl.GL11;

public final class K540b1eed9037d7ce28 {
   private K540b1eed9037d7ce28() {
   }

   public static void render(class_4587 matrices, K1037f35c571a07da2f style, double progress, int tint, double height, double width) {
      if (!(progress <= (double)0.0F) && !(progress >= (double)1.0F)) {
         boolean blend = GL11.glIsEnabled(3042);
         boolean cull = GL11.glIsEnabled(2884);
         boolean depth = GL11.glIsEnabled(2929);
         boolean depthMask = GL11.glGetBoolean(2930);
         float[] shaderColor = (float[])RenderSystem.getShaderColor().clone();
         int srcRgb = GL11.glGetInteger(32969);
         int dstRgb = GL11.glGetInteger(32968);
         int srcAlpha = GL11.glGetInteger(32971);
         int dstAlpha = GL11.glGetInteger(32970);

         try {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableCull();
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.setShader(class_10142.field_53876);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);
            class_4587.class_4665 entry = matrices.method_23760();
            K44b0ee4754e3a85f75.emit(style, progress, tint, height, width, (a, b, c, ca, cb, cc) -> {
               K048ecbe4422f362195(buffer, entry, a, ca);
               K048ecbe4422f362195(buffer, entry, b, cb);
               K048ecbe4422f362195(buffer, entry, c, cc);
            });
            class_9801 built = buffer.method_60794();
            if (built != null) {
               class_286.method_43433(built);
            }
         } finally {
            RenderSystem.setShaderColor(shaderColor[0], shaderColor[1], shaderColor[2], shaderColor[3]);
            RenderSystem.depthMask(depthMask);
            RenderSystem.blendFuncSeparate(srcRgb, dstRgb, srcAlpha, dstAlpha);
            if (blend) {
               RenderSystem.enableBlend();
            } else {
               RenderSystem.disableBlend();
            }

            if (cull) {
               RenderSystem.enableCull();
            } else {
               RenderSystem.disableCull();
            }

            if (depth) {
               RenderSystem.enableDepthTest();
            } else {
               RenderSystem.disableDepthTest();
            }

         }

      }
   }

   private static void K048ecbe4422f362195(class_287 buffer, class_4587.class_4665 entry, class_243 point, int color) {
      buffer.method_56824(entry, (float)point.field_1352, (float)point.field_1351, (float)point.field_1350).method_39415(color);
   }
}
