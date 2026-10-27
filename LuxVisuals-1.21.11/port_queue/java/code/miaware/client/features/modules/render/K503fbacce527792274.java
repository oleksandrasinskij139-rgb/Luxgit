package code.miaware.client.features.modules.render;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_10142;
import net.minecraft.class_2338;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

class K503fbacce527792274 {
   final class_2338 center;
   final long startTime;
   final float maxRadius;
   final long duration;
   final int colorValue;
   // $FF: synthetic field
   final K1d17a7324e507d883c this$0;

   K503fbacce527792274(final K1d17a7324e507d883c var1, class_2338 center, long startTime, float maxRadius, int colorValue) {
      this.this$0 = var1;
      this.center = center;
      this.startTime = startTime;
      this.maxRadius = maxRadius;
      this.duration = 900L + (long)Math.round(maxRadius * 25.0F);
      this.colorValue = colorValue;
   }

   boolean isExpired() {
      return System.currentTimeMillis() - this.startTime >= this.duration;
   }

   void render(class_4587 matrices) {
      long elapsed = System.currentTimeMillis() - this.startTime;
      float progress = class_3532.method_15363((float)elapsed / (float)this.duration, 0.0F, 1.0F);
      float radius = progress * this.maxRadius;
      float fade = 1.0F - progress;
      int segments = Math.max(42, (int)(this.maxRadius * 14.0F));
      Color baseColor = new Color(this.colorValue, true);
      int baseAlpha = baseColor.getAlpha();
      if (baseAlpha > 0) {
         for(int i = 0; i < segments; ++i) {
            double angle = (Math.PI * 2D) * (double)i / (double)segments;
            int x = (int)Math.round(Math.cos(angle) * (double)radius);
            int z = (int)Math.round(Math.sin(angle) * (double)radius);
            float distance = (float)Math.sqrt((double)(x * x + z * z));
            float ringFactor = 1.0F - Math.min(Math.abs(distance - radius) / 1.15F, 1.0F);
            if (!(ringFactor <= 0.0F)) {
               int alpha = class_3532.method_15340((int)((float)baseAlpha * ringFactor * fade), 0, 255);
               if (alpha >= 8) {
                  class_2338 blockPos = this.this$0.Kea1464a8dc6f6e63eb(this.center.method_10069(x, 0, z));
                  if (blockPos != null) {
                     class_2680 state = K4600bef438dafc1a9c.mc.field_1687.method_8320(blockPos);
                     if (!state.method_26215()) {
                        class_265 shape = state.method_26218(K4600bef438dafc1a9c.mc.field_1687, blockPos);
                        if (!shape.method_1110()) {
                           Color waveColor = new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), alpha);
                           this.Kfe79d5cd01083d3057(matrices, blockPos, shape, waveColor);
                        }
                     }
                  }
               }
            }
         }

      }
   }

   private void Kfe79d5cd01083d3057(class_4587 matrices, class_2338 pos, class_265 shape, Color color) {
      matrices.method_22903();
      class_4184 camera = K4600bef438dafc1a9c.mc.field_1773.method_19418();
      matrices.method_22904((double)pos.method_10263() - camera.method_19326().field_1352, (double)pos.method_10264() - camera.method_19326().field_1351, (double)pos.method_10260() - camera.method_19326().field_1350);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableCull();
      RenderSystem.disableDepthTest();
      Matrix4f matrix = matrices.method_23760().method_23761();
      int fillAlpha = this.this$0.fillIntensity.getValue().intValue();
      shape.method_1089((minX, minY, minZ, maxX, maxY, maxZ) -> this.K9999baeb4b205ecf60(matrix, (float)minX, (float)minY, (float)minZ, (float)maxX, (float)maxY, (float)maxZ, K80ee108ea129adac11.setAlpha(color, fillAlpha)));
      RenderSystem.setShader(class_10142.field_53864);
      RenderSystem.lineWidth(2.0F);
      class_289 tessellator = class_289.method_1348();
      class_287 buffer = tessellator.method_60827(class_5596.field_27377, class_290.field_29337);
      shape.method_1104((minX, minY, minZ, maxX, maxY, maxZ) -> {
         buffer.method_22918(matrix, (float)minX, (float)minY, (float)minZ).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_22914(0.0F, 1.0F, 0.0F);
         buffer.method_22918(matrix, (float)maxX, (float)maxY, (float)maxZ).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_22914(0.0F, 1.0F, 0.0F);
      });
      class_286.method_43433(buffer.method_60800());
      RenderSystem.enableCull();
      RenderSystem.enableDepthTest();
      RenderSystem.disableBlend();
      matrices.method_22909();
   }

   private void K9999baeb4b205ecf60(Matrix4f matrix, float x1, float y1, float z1, float x2, float y2, float z2, Color color) {
      float r = (float)color.getRed() / 255.0F;
      float g = (float)color.getGreen() / 255.0F;
      float b = (float)color.getBlue() / 255.0F;
      float a = (float)color.getAlpha() / 255.0F;
      RenderSystem.setShader(class_10142.field_53876);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);
      buffer.method_22918(matrix, x1, y1, z1).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x2, y1, z1).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x2, y1, z2).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x1, y1, z2).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x1, y2, z1).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x1, y2, z2).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x2, y2, z2).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x2, y2, z1).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x1, y1, z1).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x1, y2, z1).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x2, y2, z1).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x2, y1, z1).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x1, y1, z2).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x2, y1, z2).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x2, y2, z2).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x1, y2, z2).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x1, y1, z1).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x1, y1, z2).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x1, y2, z2).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x1, y2, z1).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x2, y1, z1).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x2, y2, z1).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x2, y2, z2).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x2, y1, z2).method_22915(r, g, b, a);
      class_286.method_43433(buffer.method_60800());
   }
}
