package code.miaware.client.features.modules.combat.hitbox;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.List;
import net.minecraft.class_10142;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4587;
import net.minecraft.class_5944;
import net.minecraft.class_293.class_5596;
import org.lwjgl.opengl.GL11;

public final class K622b5654eecec333de {
   private K622b5654eecec333de() {
   }

   public static void render(class_4587 matrices, class_238 box, Color color, boolean corners, boolean fill, float opacity) {
      List<K7da25e2776ad4abe8f> edges = Ka3966ff009ad323fe6.edges(box, corners);
      if (!edges.isEmpty()) {
         boolean depth = GL11.glIsEnabled(2929);
         boolean blend = GL11.glIsEnabled(3042);
         boolean cull = GL11.glIsEnabled(2884);
         boolean mask = GL11.glGetBoolean(2930);
         int depthFunc = GL11.glGetInteger(2932);
         int srcRgb = GL11.glGetInteger(32969);
         int dstRgb = GL11.glGetInteger(32968);
         int srcAlpha = GL11.glGetInteger(32971);
         int dstAlpha = GL11.glGetInteger(32970);
         float width = RenderSystem.getShaderLineWidth();
         float[] shaderColor = (float[])RenderSystem.getShaderColor().clone();
         class_5944 shader = RenderSystem.getShader();

         try {
            RenderSystem.enableDepthTest();
            RenderSystem.depthFunc(515);
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableCull();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            if (fill && opacity > 0.0F) {
               RenderSystem.setShader(class_10142.field_53876);
               class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);

               for(List<class_243> face : Ka3966ff009ad323fe6.faces(box)) {
                  for(class_243 p : face) {
                     float height = box.method_17940() > 1.0E-6 ? (float)((p.field_1351 - box.field_1322) / box.method_17940()) : 1.0F;
                     int alpha = Math.round((float)color.getAlpha() * Math.clamp(opacity, 0.0F, 50.0F) / 100.0F * (0.6F + 0.4F * height));
                     buffer.method_22918(matrices.method_23760().method_23761(), (float)p.field_1352, (float)p.field_1351, (float)p.field_1350).method_1336(color.getRed(), color.getGreen(), color.getBlue(), alpha);
                  }
               }

               class_286.method_43433(buffer.method_60800());
            }

            RenderSystem.setShader(class_10142.field_53864);
            lines(matrices, edges, new Color(10, 12, 18, Math.round((float)color.getAlpha() * 0.45F)), 2.8F);
            lines(matrices, edges, color, 1.6F);
         } finally {
            RenderSystem.setShader(shader);
            RenderSystem.setShaderColor(shaderColor[0], shaderColor[1], shaderColor[2], shaderColor[3]);
            RenderSystem.lineWidth(width);
            RenderSystem.depthFunc(depthFunc);
            RenderSystem.depthMask(mask);
            if (depth) {
               RenderSystem.enableDepthTest();
            } else {
               RenderSystem.disableDepthTest();
            }

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

            RenderSystem.blendFuncSeparate(srcRgb, dstRgb, srcAlpha, dstAlpha);
         }

      }
   }

   private static void lines(class_4587 matrices, List<K7da25e2776ad4abe8f> edges, Color color, float width) {
      RenderSystem.lineWidth(width);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27377, class_290.field_29337);
      class_4587.class_4665 entry = matrices.method_23760();

      for(K7da25e2776ad4abe8f edge : edges) {
         class_243 normal = edge.end().method_1020(edge.start()).method_1029();

         for(class_243 p : List.of(edge.start(), edge.end())) {
            buffer.method_22918(entry.method_23761(), (float)p.field_1352, (float)p.field_1351, (float)p.field_1350).method_39415(color.getRGB()).method_60831(entry, (float)normal.field_1352, (float)normal.field_1351, (float)normal.field_1350);
         }
      }

      class_286.method_43433(buffer.method_60800());
   }
}
