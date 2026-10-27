package code.miaware.api.utils.render.display;

import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.utils.render.Ke8433384857ddcab3d;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.List;
import net.minecraft.class_10142;
import net.minecraft.class_10149;
import net.minecraft.class_10156;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4587;
import net.minecraft.class_5944;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class K4b843475c2462d48b1 {
   private static final class_10156 shaderKey;
   private static boolean lastBlendEnabled;
   private static boolean lastCullEnabled;

   public void draw(class_4587 matrixStack, float x, float y, float width, float height, float radius, Color color) {
      this.draw(matrixStack, x, y, width, height, new Vector4f(radius, radius, radius, radius), color);
   }

   public void drawBatch(class_4587 matrices, List<Ka344a99dbf6b098920> positions, float width, float height, float radius, Color color) {
      if (!positions.isEmpty() && !(width <= 0.0F) && !(height <= 0.0F) && color.getAlpha() != 0) {
         RenderSystem.enableBlend();
         Ke8433384857ddcab3d.defaultBlendFunc();
         RenderSystem.disableCull();

         try {
            class_5944 shader = RenderSystem.setShader(shaderKey);
            shader.method_34582("uSize").method_1255(width, height);
            shader.method_34582("uRadius").method_35657(radius, radius, radius, radius);
            shader.method_34582("uSmoothness").method_1251(0.8F);
            Matrix4f matrix = matrices.method_23760().method_23761();
            class_287 builder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);

            for(Ka344a99dbf6b098920 position : positions) {
               float x = position.x() - 0.6F;
               float y = position.y() - 0.6F;
               float w = width + 1.2F;
               float h = height + 1.2F;
               Ke8433384857ddcab3d.record(matrix, x, y, w, h);
               builder.method_22918(matrix, x, y, 0.0F).method_39415(color.getRGB());
               builder.method_22918(matrix, x, y + h, 0.0F).method_39415(color.getRGB());
               builder.method_22918(matrix, x + w, y + h, 0.0F).method_39415(color.getRGB());
               builder.method_22918(matrix, x + w, y, 0.0F).method_39415(color.getRGB());
            }

            class_286.method_43433(builder.method_60800());
         } finally {
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
            resetGLStateCache();
         }

      }
   }

   public void drawRoundedRect(class_4587 matrices, float x, float y, float width, float height, float radius, int color) {
      float a = (float)(color >> 24 & 255) / 255.0F;
      float r = (float)(color >> 16 & 255) / 255.0F;
      float g = (float)(color >> 8 & 255) / 255.0F;
      float b = (float)(color & 255) / 255.0F;
      RenderSystem.setShader(class_10142.field_53876);
      if (!lastBlendEnabled) {
         RenderSystem.enableBlend();
         Ke8433384857ddcab3d.defaultBlendFunc();
         lastBlendEnabled = true;
      }

      if (lastCullEnabled) {
         RenderSystem.disableCull();
         lastCullEnabled = false;
      }

      Matrix4f matrix = matrices.method_23760().method_23761();
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27381, class_290.field_1576);
      float cx = x + width / 2.0F;
      float cy = y + height / 2.0F;
      buffer.method_22918(matrix, cx, cy, 0.0F).method_22915(r, g, b, a);
      int segments = 16;

      for(int i = 0; i <= segments; ++i) {
         float angle = (float)((double)(i * 2) * Math.PI / (double)segments);
         float px = (float)(Math.cos((double)angle) * (double)(width / 2.0F));
         float py = (float)(Math.sin((double)angle) * (double)(height / 2.0F));
         buffer.method_22918(matrix, cx + px, cy + py, 0.0F).method_22915(r, g, b, a);
      }

      class_286.method_43433(buffer.method_60800());
      if (!lastCullEnabled) {
         RenderSystem.enableCull();
         lastCullEnabled = true;
      }

      if (lastBlendEnabled) {
         RenderSystem.disableBlend();
         lastBlendEnabled = false;
      }

   }

   public void drawRoundedOutline(class_4587 matrices, float x, float y, float width, float height, float radius, float lineWidth, int color) {
      float a = (float)(color >> 24 & 255) / 255.0F;
      float r = (float)(color >> 16 & 255) / 255.0F;
      float g = (float)(color >> 8 & 255) / 255.0F;
      float b = (float)(color & 255) / 255.0F;
      RenderSystem.setShader(class_10142.field_53876);
      if (!lastBlendEnabled) {
         RenderSystem.enableBlend();
         Ke8433384857ddcab3d.defaultBlendFunc();
         lastBlendEnabled = true;
      }

      RenderSystem.lineWidth(lineWidth);
      Matrix4f matrix = matrices.method_23760().method_23761();
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);
      buffer.method_22918(matrix, x + radius, y, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x + width - radius, y, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x + radius, y + height, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x + width - radius, y + height, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x, y + radius, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x, y + height - radius, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x + width, y + radius, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(matrix, x + width, y + height - radius, 0.0F).method_22915(r, g, b, a);
      class_286.method_43433(buffer.method_60800());
      if (lastBlendEnabled) {
         RenderSystem.disableBlend();
         lastBlendEnabled = false;
      }

   }

   public void draw(class_4587 matrixStack, float x, float y, float width, float height, Vector4f radius, Color color) {
      Matrix4f matrix4f = matrixStack.method_23760().method_23761();
      float smoothness = 0.8F;
      float horizontalPadding = -smoothness / 2.0F + smoothness * 2.0F;
      float verticalPadding = smoothness / 2.0F + smoothness;
      float adjustedX = x - horizontalPadding / 2.0F;
      float adjustedY = y - verticalPadding / 2.0F;
      float adjustedWidth = width + horizontalPadding;
      float adjustedHeight = height + verticalPadding;
      Ke8433384857ddcab3d.record(matrix4f, adjustedX, adjustedY, adjustedWidth, adjustedHeight);
      if (!lastBlendEnabled) {
         RenderSystem.enableBlend();
         Ke8433384857ddcab3d.defaultBlendFunc();
         lastBlendEnabled = true;
      }

      if (lastCullEnabled) {
         RenderSystem.disableCull();
         lastCullEnabled = false;
      }

      class_5944 shader = RenderSystem.setShader(shaderKey);
      shader.method_34582("uSize").method_1255(width, height);
      shader.method_34582("uRadius").method_35657(radius.x, radius.z, radius.w, radius.y);
      shader.method_34582("uSmoothness").method_1251(smoothness);
      float z = 0.0F;
      int colorInt = color.getAlpha() << 24 | color.getRed() << 16 | color.getGreen() << 8 | color.getBlue();
      class_287 builder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);
      builder.method_22918(matrix4f, adjustedX, adjustedY, z).method_39415(colorInt);
      builder.method_22918(matrix4f, adjustedX, adjustedY + adjustedHeight, z).method_39415(colorInt);
      builder.method_22918(matrix4f, adjustedX + adjustedWidth, adjustedY + adjustedHeight, z).method_39415(colorInt);
      builder.method_22918(matrix4f, adjustedX + adjustedWidth, adjustedY, z).method_39415(colorInt);
      class_286.method_43433(builder.method_60800());
      if (!lastCullEnabled) {
         RenderSystem.enableCull();
         lastCullEnabled = true;
      }

      if (lastBlendEnabled) {
         RenderSystem.disableBlend();
         lastBlendEnabled = false;
      }

   }

   public static void resetGLStateCache() {
      lastBlendEnabled = false;
      lastCullEnabled = true;
   }

   static {
      shaderKey = new class_10156(Kc1f6f8c785f6a08448.getShader("rect/rect"), class_290.field_1576, class_10149.field_53930);
      lastBlendEnabled = false;
      lastCullEnabled = true;
   }
}
