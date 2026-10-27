package code.miaware.client.ui.widget.target;

import code.miaware.api.utils.render.Ke8433384857ddcab3d;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10142;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4587;
import net.minecraft.class_9801;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public final class K677fcc4f631147f8f0 {
   private static final int SEGMENTS = 16;
   private static final float[] COS = new float[17];
   private static final float[] SIN = new float[17];

   private K677fcc4f631147f8f0() {
   }

   public static void render(class_4587 matrices, Kb4b2e3dd00d82fe84d animation, double now, float barX, float barY, float barHeight, float scale, float opacity) {
      boolean active = false;

      for(Kffaa8b78ebdaa4c534 bubble : animation.bubbles()) {
         if (Kb4b2e3dd00d82fe84d.bubbleAlpha(bubble, now) > 0.005) {
            active = true;
            break;
         }
      }

      if (active && !(opacity <= 0.01F)) {
         boolean blend = GL11.glIsEnabled(3042);
         boolean cull = GL11.glIsEnabled(2884);
         boolean depth = GL11.glIsEnabled(2929);
         boolean mask = GL11.glGetBoolean(2930);
         int src = GL11.glGetInteger(32969);
         int dst = GL11.glGetInteger(32968);
         int srcA = GL11.glGetInteger(32971);
         int dstA = GL11.glGetInteger(32970);
         float[] shaderColor = RenderSystem.getShaderColor();
         float red = shaderColor[0];
         float green = shaderColor[1];
         float blue = shaderColor[2];
         float alpha = shaderColor[3];

         try {
            RenderSystem.enableBlend();
            Ke8433384857ddcab3d.defaultBlendFunc();
            RenderSystem.disableCull();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.setShader(class_10142.field_53876);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);
            Matrix4f matrix = matrices.method_23760().method_23761();

            for(Kffaa8b78ebdaa4c534 bubble : animation.bubbles()) {
               double fade = Kb4b2e3dd00d82fe84d.bubbleAlpha(bubble, now) * (double)opacity;
               if (!(fade <= 0.005)) {
                  double age = now - bubble.born;
                  float x = barX + (float)(bubble.vx * age) * scale;
                  float y = barY + (float)bubble.barFraction * barHeight + (float)(bubble.vy * age + (double)9.0F * age * age) * scale;
                  float radius = (float)(bubble.radius * ((double)1.0F - age / bubble.life * 0.35)) * scale;
                  int tint = bubble.healing ? 6681504 : 16737661;
                  Ke8433384857ddcab3d.record(matrix, x - radius * 2.4F, y - radius * 2.4F, radius * 4.8F, radius * 4.8F);
                  K1b632b9348be03183c(buffer, matrix, x, y, radius * 2.4F, tint, (int)(fade * (double)45.0F), 0);
                  K1b632b9348be03183c(buffer, matrix, x, y, radius, tint, (int)(fade * (double)240.0F), (int)(fade * (double)105.0F));
                  K1b632b9348be03183c(buffer, matrix, x - radius * 0.25F, y - radius * 0.3F, radius * 0.34F, 16777215, (int)(fade * (double)180.0F), 0);
               }
            }

            class_9801 built = buffer.method_60794();
            if (built != null) {
               class_286.method_43433(built);
            }
         } finally {
            RenderSystem.setShaderColor(red, green, blue, alpha);
            RenderSystem.depthMask(mask);
            RenderSystem.blendFuncSeparate(src, dst, srcA, dstA);
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

   private static void K1b632b9348be03183c(class_287 buffer, Matrix4f matrix, float x, float y, float radius, int rgb, int middle, int edge) {
      int centerColor = middle << 24 | rgb;
      int edgeColor = edge << 24 | rgb;

      for(int i = 0; i < 16; ++i) {
         buffer.method_22918(matrix, x, y, 0.0F).method_39415(centerColor);
         buffer.method_22918(matrix, x + COS[i] * radius, y + SIN[i] * radius, 0.0F).method_39415(edgeColor);
         buffer.method_22918(matrix, x + COS[i + 1] * radius, y + SIN[i + 1] * radius, 0.0F).method_39415(edgeColor);
      }

   }

   static {
      for(int i = 0; i <= 16; ++i) {
         COS[i] = (float)Math.cos((double)i * Math.PI * (double)2.0F / (double)16.0F);
         SIN[i] = (float)Math.sin((double)i * Math.PI * (double)2.0F / (double)16.0F);
      }

   }
}
