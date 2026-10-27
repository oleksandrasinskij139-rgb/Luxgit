package code.miaware.client.ui.journey;

import code.miaware.client.ui.clickgui.K64dfc1330d745b4d7f;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_10142;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_332;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public final class K1b44e53424f6810c1d {
   private final K64dfc1330d745b4d7f canvas = new K64dfc1330d745b4d7f();

   public void render(class_332 context, String dimension, double seconds, float opacity, float darkness, boolean title, boolean motion, boolean loading) {
      float w = (float)context.method_51421();
      float h = (float)context.method_51443();
      float scale = Math.max(w / 1600.0F, h / 900.0F);
      float offsetX = (w - 1600.0F * scale) / 2.0F;
      float offsetY = (h - 900.0F * scale) / 2.0F;
      context.method_51452();
      boolean depth = GL11.glIsEnabled(2929);
      boolean cull = GL11.glIsEnabled(2884);
      boolean blend = GL11.glIsEnabled(3042);
      boolean depthMask = GL11.glGetBoolean(2930);

      try {
         RenderSystem.disableDepthTest();
         RenderSystem.depthMask(false);
         RenderSystem.disableCull();
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderSystem.setShader(class_10142.field_53876);
         class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);
         Matrix4f matrix = context.method_51448().method_23760().method_23761();

         for(Keea4a4bc04198e2324 shape : K35d6e3ad814c5a9922.landscape(dimension, seconds, motion)) {
            float[] points = shape.points();
            float minY = Float.POSITIVE_INFINITY;
            float maxY = Float.NEGATIVE_INFINITY;

            for(int i = 1; i < points.length; i += 2) {
               minY = Math.min(minY, points[i]);
               maxY = Math.max(maxY, points[i]);
            }

            for(int i = 1; i < points.length / 2 - 1; ++i) {
               for(int index : new int[]{0, i, i + 1}) {
                  float x = points[index * 2];
                  float y = points[index * 2 + 1];
                  float mix = (y - minY) / Math.max(1.0F, maxY - minY);
                  Color a = shape.top();
                  Color b = shape.bottom();
                  buffer.method_22918(matrix, offsetX + x * scale, offsetY + y * scale, 0.0F).method_1336(Math.round((float)a.getRed() + (float)(b.getRed() - a.getRed()) * mix), Math.round((float)a.getGreen() + (float)(b.getGreen() - a.getGreen()) * mix), Math.round((float)a.getBlue() + (float)(b.getBlue() - a.getBlue()) * mix), Math.round(((float)a.getAlpha() + (float)(b.getAlpha() - a.getAlpha()) * mix) * opacity));
               }
            }
         }

         class_286.method_43433(buffer.method_60800());
         this.canvas.begin(context);
         K671735e5c82b37b1f0.render(this.canvas, dimension, seconds, w, h, opacity, darkness, title, loading);
      } finally {
         this.canvas.setAlpha(1.0F);
         RenderSystem.depthMask(depthMask);
         if (depth) {
            RenderSystem.enableDepthTest();
         } else {
            RenderSystem.disableDepthTest();
         }

         if (cull) {
            RenderSystem.enableCull();
         } else {
            RenderSystem.disableCull();
         }

         if (blend) {
            RenderSystem.enableBlend();
         } else {
            RenderSystem.disableBlend();
         }

      }

   }
}
