package code.miaware.api.utils.render.fonts;

import code.miaware.api.system.backend.K6e82e9f893ec34a816;
import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.other.Kd10bcb33e37f629df6;
import code.miaware.api.utils.other.Kf5ec3d7f18b288e7f3;
import code.miaware.api.utils.render.K5b5bf2e4f791eb67a2;
import code.miaware.api.utils.render.Ke8433384857ddcab3d;
import code.miaware.client.services.Ke4708cf52c3b89f5ae;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import net.minecraft.class_10149;
import net.minecraft.class_10156;
import net.minecraft.class_1044;
import net.minecraft.class_2561;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_5944;
import net.minecraft.class_9801;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

public final class K5fe84dad3db1af4bb7 {
   private static final class_10156 shaderKey;
   private final String name;
   private final class_1044 texture;
   private final K4dd063f656b2c6da7b atlas;
   private final K47baed1b195ed0a2a1 metrics;
   private final Map<Integer, Kd81f0583cabc01b39d> glyphs;
   private final Map<Integer, Map<Integer, Float>> kernings;
   private final Map<Kb8ed56845c54c9e745, Float> widths = new LinkedHashMap<Kb8ed56845c54c9e745, Float>(256, 0.75F, true) {
      protected boolean removeEldestEntry(Map.Entry<Kb8ed56845c54c9e745, Float> eldest) {
         return this.size() > 1024;
      }
   };

   public void drawTextRuns(class_4587 matrices, List<K367cc7b6858ae36355> runs) {
      if (!runs.isEmpty()) {
         this.start(0.0F, 0.0F, 0.5F, -1);

         try {
            class_287 builder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
            Matrix4f matrix = matrices.method_23760().method_23761();

            for(K367cc7b6858ae36355 run : runs) {
               this.applyGlyphs(matrix, builder, Kd10bcb33e37f629df6.protectedString(run.text()), run.size(), 0.0F, 0.0F, run.x(), run.y() + this.metrics.baselineHeight() * run.size(), 0.0F, run.color(), -1, -1.0F);
            }

            class_9801 buffer = builder.method_60794();
            if (buffer != null) {
               class_286.method_43433(buffer);
            }
         } finally {
            RenderSystem.setShaderTexture(0, 0);
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
         }

      }
   }

   public K5fe84dad3db1af4bb7(String name, class_1044 texture, K4dd063f656b2c6da7b atlas, K47baed1b195ed0a2a1 metrics, Map<Integer, Kd81f0583cabc01b39d> glyphs, Map<Integer, Map<Integer, Float>> kernings) {
      this.name = name;
      this.texture = texture;
      this.atlas = atlas;
      this.metrics = metrics;
      this.glyphs = glyphs;
      this.kernings = kernings;
   }

   private K6e82e9f893ec34a816<Float, Float> K9e26ffbfab24545409(float x, float y) {
      float scale = Ke4708cf52c3b89f5ae.getInstance().getScale();
      float x1 = x;
      float y1 = y;
      boolean isPS = this.name.contains("product_sans");
      boolean isSF = this.name.contains("sf_pro");
      if (isSF || isPS) {
         y1 = y - scale;
         if (isPS) {
            x1 = x - scale / 2.0F;
         }
      }

      return new K6e82e9f893ec34a816<Float, Float>(x1, y1);
   }

   public void drawText(class_4587 matrixStack, class_2561 text, float x, float y, float size, float thickness, float smoothness, float spacing, int outlineColor, float outlineThickness) {
      if (text != null) {
         try {
            Matrix4f matrix = matrixStack.method_23760().method_23761();
            this.start(outlineThickness, thickness, smoothness, outlineColor);
            class_287 builder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
            this.applyGlyphs(matrix, builder, Kf5ec3d7f18b288e7f3.parseTextToColoredGlyphs(Kd10bcb33e37f629df6.replaceSymbols(text)), size, (thickness + outlineThickness * 0.5F) * 0.5F * size, spacing, x, y + this.getMetrics().baselineHeight() * size, 0.0F);
            this.end(builder);
         } catch (Exception var13) {
         }

      }
   }

   public void drawText(class_4587 matrixStack, String text, float x, float y, float size, float thickness, int color, int colorSecond, float offset, float smoothness, float spacing, int outlineColor, float outlineThickness) {
      try {
         Matrix4f matrix = matrixStack.method_23760().method_23761();
         this.start(outlineThickness, thickness, smoothness, outlineColor);
         class_287 builder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
         this.applyGlyphs(matrix, builder, Kd10bcb33e37f629df6.protectedString(text), size, (thickness + outlineThickness * 0.5F) * 0.5F * size, spacing, x, y + this.getMetrics().baselineHeight() * size, 0.0F, color, colorSecond, offset);
         this.end(builder);
      } catch (Exception var16) {
      }

   }

   private void end(class_287 builder) {
      class_286.method_43433(builder.method_60800());
      RenderSystem.setShaderTexture(0, 0);
      RenderSystem.enableCull();
      RenderSystem.disableBlend();
   }

   private void start(float outlineThickness, float thickness, float smoothness, int outlineColor) {
      RenderSystem.enableBlend();
      Ke8433384857ddcab3d.defaultBlendFunc();
      RenderSystem.disableCull();
      RenderSystem.setShaderTexture(0, this.texture.method_4624());
      boolean outlineEnabled = outlineThickness > 0.0F;
      class_5944 shader = RenderSystem.setShader(shaderKey);
      shader.method_34582("uRange").method_1251(this.getAtlas().range());
      shader.method_34582("uThickness").method_1251(thickness);
      shader.method_34582("uSmoothness").method_1251(smoothness);
      shader.method_34582("uOutline").method_35649(outlineEnabled ? 1 : 0);
      if (outlineEnabled) {
         shader.method_34582("uOutlineThickness").method_1251(outlineThickness);
         float[] outlineComponents = K80ee108ea129adac11.normalize(outlineColor);
         shader.method_34582("uOutlineColor").method_35657(outlineComponents[0], outlineComponents[1], outlineComponents[2], outlineComponents[3]);
      }

   }

   public void drawText(class_4587 matrixStack, class_2561 text, float x, float y, float size, float thickness) {
      K6e82e9f893ec34a816<Float, Float> coordinates = this.K9e26ffbfab24545409(x, y);
      this.drawText(matrixStack, text, (Float)coordinates.left(), (Float)coordinates.right(), size, thickness, 0.5F, 0.0F, -1, thickness);
   }

   public void drawText(class_4587 matrixStack, String text, float x, float y, float size, Color color, float thickness) {
      K6e82e9f893ec34a816<Float, Float> coordinates = this.K9e26ffbfab24545409(x, y);
      this.drawText(matrixStack, text, (Float)coordinates.left(), (Float)coordinates.right(), size, thickness, color.getRGB(), -1, -1.0F, 0.5F, 0.0F, -1, thickness);
   }

   public void drawGradientText(class_4587 matrixStack, String text, float x, float y, float size, Color colorFirst, Color colorSecond, float offset, float thickness) {
      K6e82e9f893ec34a816<Float, Float> coordinates = this.K9e26ffbfab24545409(x, y);
      this.drawText(matrixStack, text, (Float)coordinates.left(), (Float)coordinates.right(), size, thickness, colorFirst.getRGB(), colorSecond.getRGB(), offset, 0.5F, 0.0F, -1, thickness);
   }

   public void drawGradientText(class_4587 matrixStack, String text, float x, float y, float size, Color color, Color colorSecond, float offset) {
      this.drawGradientText(matrixStack, text, x, y, size, color, colorSecond, offset, 0.0F);
   }

   public void drawText(class_4587 matrixStack, class_2561 text, float x, float y, float size) {
      this.drawText(matrixStack, text, x, y, size, 0.0F);
   }

   public void drawText(class_4587 matrixStack, String text, float x, float y, float size, Color color) {
      this.drawText(matrixStack, text, x, y, size, color, 0.0F);
   }

   public void drawCenteredText(class_4587 matrixStack, String text, float x, float y, float size, Color color, float thickness) {
      this.drawText(matrixStack, text, x - this.getWidth(text, size, thickness) / 2.0F, y, size, color, thickness);
   }

   public void drawCenteredText(class_4587 matrixStack, String text, float x, float y, float size, Color color) {
      this.drawCenteredText(matrixStack, text, x, y, size, color, 0.0F);
   }

   public void drawCenteredGradientText(class_4587 matrixStack, String text, float x, float y, float size, Color color, Color colorSecond, float offset, float thickness) {
      this.drawGradientText(matrixStack, text, x - this.getWidth(text, size, thickness) / 2.0F, y, size, color, colorSecond, offset, thickness);
   }

   public void drawCenteredGradientText(class_4587 matrixStack, String text, float x, float y, float size, Color color, Color colorSecond, float offset) {
      this.drawCenteredGradientText(matrixStack, text, x, y, size, color, colorSecond, offset, 0.0F);
   }

   public void drawWrap(class_4587 matrixStack, String text, float x, float y, float width, float size, Color color, float offset, Duration cycleDuration, Duration pauseDuration) {
      if (color.getAlpha() > 0) {
         float textWidth = this.getWidth(text, size);
         if (textWidth <= width) {
            this.drawText(matrixStack, text, x, y, size, color);
         } else {
            K5b5bf2e4f791eb67a2.start(matrixStack, x, y - size / 4.0F, width, size * 1.5F);
            long cycleMillis = cycleDuration.toMillis();
            long pauseMillis = pauseDuration.toMillis();
            long totalCycleTime = cycleMillis + pauseMillis;
            long elapsed = System.currentTimeMillis() % totalCycleTime;
            float progress = elapsed < cycleMillis ? (float)elapsed / (float)cycleMillis : 1.0F;
            float value = Kf3b62ecfba0c3544e6.SINE_BOTH.apply(progress) * (textWidth + offset);
            this.drawText(matrixStack, text, x - value, y, size, color);
            this.drawText(matrixStack, text, x - value + textWidth + offset, y, size, color);
            K5b5bf2e4f791eb67a2.stop(matrixStack);
         }

      }
   }

   public void applyGlyphs(Matrix4f matrix, class_4588 consumer, String text, float size, float thickness, float spacing, float x, float y, float z, int color, int colorSecond, float offset) {
      int prevChar = -1;
      float startX = x;
      float totalWidth = offset > 1.0F ? this.getWidth(text, size) : 0.0F;
      float time = offset > 1.0F ? (float)(System.currentTimeMillis() % 3000L) / 3000.0F : 0.0F;

      for(int i = 0; i < text.length(); ++i) {
         int _char = text.charAt(i);
         Kd81f0583cabc01b39d glyph = (Kd81f0583cabc01b39d)this.glyphs.get(_char);
         if (glyph != null) {
            Map<Integer, Float> kerning = (Map)this.kernings.get(prevChar);
            if (kerning != null) {
               x += (Float)kerning.getOrDefault(_char, 0.0F) * size;
            }

            int currentColor = color;
            if (offset > 1.0F) {
               currentColor = K80ee108ea129adac11.gradient(color, colorSecond, x - startX, totalWidth, time, offset);
            }

            x += glyph.apply(matrix, consumer, size, x, y, z, currentColor) + thickness + spacing;
            prevChar = _char;
         }
      }

   }

   public void applyGlyphs(Matrix4f matrix, class_4588 consumer, List<K3bb226f78688ad2f7f> glyphs, float size, float thickness, float spacing, float x, float y, float z) {
      int prevChar = -1;

      for(int i = 0; i < glyphs.size(); ++i) {
         K3bb226f78688ad2f7f glyphData = (K3bb226f78688ad2f7f)glyphs.get(i);
         int _char = glyphData.c();
         int color = glyphData.color();
         Kd81f0583cabc01b39d glyph = (Kd81f0583cabc01b39d)this.glyphs.get(_char);
         if (glyph != null) {
            Map<Integer, Float> kerning = (Map)this.kernings.get(prevChar);
            if (kerning != null) {
               x += (Float)kerning.getOrDefault(_char, 0.0F) * size;
            }

            x += glyph.apply(matrix, consumer, size, x, y, z, color) + thickness + spacing;
            prevChar = _char;
         }
      }

   }

   public Kd81f0583cabc01b39d getGlyph(int charCode) {
      return (Kd81f0583cabc01b39d)this.glyphs.get(charCode);
   }

   public float getHeight(float size) {
      return size;
   }

   public float getWidth(class_2561 text, float size) {
      return this.getWidth(text, size, 0.0F);
   }

   public float getWidth(class_2561 text, float size, float thickness) {
      if (text == null) {
         return 0.0F;
      } else {
         List<K3bb226f78688ad2f7f> glyphs = Kf5ec3d7f18b288e7f3.parseTextToColoredGlyphs(text);
         int prevChar = -1;
         float width = 0.0F;

         for(int i = 0; i < glyphs.size(); ++i) {
            int _char = ((K3bb226f78688ad2f7f)glyphs.get(i)).c();
            Kd81f0583cabc01b39d glyph = (Kd81f0583cabc01b39d)this.glyphs.get(_char);
            if (glyph != null) {
               Map<Integer, Float> kerning = (Map)this.kernings.get(prevChar);
               if (kerning != null) {
                  width += (Float)kerning.getOrDefault(_char, 0.0F) * size * (1.0F + thickness);
               }

               width += glyph.getWidth(size);
               prevChar = _char;
            }
         }

         return width;
      }
   }

   public float getWidth(String text, float size) {
      return this.getWidth(text, size, 0.0F);
   }

   public float getWidth(String text, float size, float thickness) {
      int prevChar = -1;
      float width = 0.0F;
      String finalText = Kd10bcb33e37f629df6.protectedString(text);
      Kb8ed56845c54c9e745 key = new Kb8ed56845c54c9e745(finalText, size, thickness);
      Float cached = (Float)this.widths.get(key);
      if (cached != null) {
         return cached;
      } else {
         for(int i = 0; i < finalText.length(); ++i) {
            int _char = finalText.charAt(i);
            Kd81f0583cabc01b39d glyph = (Kd81f0583cabc01b39d)this.glyphs.get(_char);
            if (glyph != null) {
               Map<Integer, Float> kerning = (Map)this.kernings.get(prevChar);
               if (kerning != null) {
                  width += (Float)kerning.getOrDefault(_char, 0.0F) * size * (1.0F + thickness);
               }

               width += glyph.getWidth(size) * (1.0F + thickness);
               prevChar = _char;
            }
         }

         this.widths.put(key, width);
         return width;
      }
   }

   public static K57b57df54f7cd42184 builder() {
      return new K57b57df54f7cd42184();
   }

   @Generated
   public K4dd063f656b2c6da7b getAtlas() {
      return this.atlas;
   }

   @Generated
   public K47baed1b195ed0a2a1 getMetrics() {
      return this.metrics;
   }

   static {
      shaderKey = new class_10156(Kc1f6f8c785f6a08448.getShader("text"), class_290.field_1575, class_10149.field_53930);
   }
}
