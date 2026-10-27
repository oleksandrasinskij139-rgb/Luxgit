package code.miaware.api.utils.render.fonts;

import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_10149;
import net.minecraft.class_10156;
import net.minecraft.class_1044;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_5944;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

public class K11ef91e33d46fe9b37 {
   private static final class_10156 shaderKey;
   private final class_1044 texture;
   private final int atlasWidth;
   private final int atlasHeight;
   private final int iconSize;
   private final int columns;

   public K11ef91e33d46fe9b37(String texturePath, int atlasWidth, int atlasHeight, int iconSize) {
      class_2960 textureId = class_2960.method_60655("luxvisuals".toLowerCase(), texturePath);
      this.texture = class_310.method_1551().method_1531().method_4619(textureId);
      this.atlasWidth = atlasWidth;
      this.atlasHeight = atlasHeight;
      this.iconSize = iconSize;
      this.columns = atlasWidth / iconSize;
      RenderSystem.recordRenderCall(() -> this.texture.method_4527(true, false));
   }

   public void drawIcon(class_4587 matrices, int iconIndex, float x, float y, float size, Color color) {
      int col = iconIndex % this.columns;
      int row = iconIndex / this.columns;
      float u0 = (float)(col * this.iconSize) / (float)this.atlasWidth;
      float v0 = (float)(row * this.iconSize) / (float)this.atlasHeight;
      float u1 = (float)((col + 1) * this.iconSize) / (float)this.atlasWidth;
      float v1 = (float)((row + 1) * this.iconSize) / (float)this.atlasHeight;
      this.Kbb03fee30668943aec(matrices, x, y, size, size, u0, v0, u1, v1, color);
   }

   public void drawIcon(class_4587 matrices, char c, float x, float y, float size, Color color) {
      int iconIndex = c - 65;
      if (iconIndex >= 0 && iconIndex < 26) {
         this.drawIcon(matrices, iconIndex, x, y, size, color);
      }
   }

   public void drawText(class_4587 matrices, String text, float x, float y, float size, Color color) {
      float currentX = x;

      for(int i = 0; i < text.length(); ++i) {
         char c = text.charAt(i);
         if (c >= 'A' && c <= 'Z') {
            this.drawIcon(matrices, c, currentX, y, size, color);
            currentX += size;
         }
      }

   }

   private void Kbb03fee30668943aec(class_4587 matrices, float x, float y, float width, float height, float u0, float v0, float u1, float v1, Color color) {
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.setShaderTexture(0, this.texture.method_4624());
      class_5944 shader = RenderSystem.setShader(shaderKey);
      Matrix4f matrix = matrices.method_23760().method_23761();
      class_287 builder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
      int r = color.getRed();
      int g = color.getGreen();
      int b = color.getBlue();
      int a = color.getAlpha();
      builder.method_22918(matrix, x, y + height, 0.0F).method_22913(u0, v1).method_1336(r, g, b, a);
      builder.method_22918(matrix, x + width, y + height, 0.0F).method_22913(u1, v1).method_1336(r, g, b, a);
      builder.method_22918(matrix, x + width, y, 0.0F).method_22913(u1, v0).method_1336(r, g, b, a);
      builder.method_22918(matrix, x, y, 0.0F).method_22913(u0, v0).method_1336(r, g, b, a);
      class_286.method_43433(builder.method_60800());
      RenderSystem.disableBlend();
   }

   public float getWidth(String text, float size) {
      return (float)text.length() * size;
   }

   static {
      shaderKey = new class_10156(Kc1f6f8c785f6a08448.getShader("rect/texture_rect"), class_290.field_1575, class_10149.field_53930);
   }
}
