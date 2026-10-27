package code.miaware.api.utils.render.display;

import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.Ke8433384857ddcab3d;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_10149;
import net.minecraft.class_10156;
import net.minecraft.class_1657;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_5944;
import net.minecraft.class_742;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class Kd372127b914786d2c2 implements K4600bef438dafc1a9c {
   private static final class_10156 shaderKey;

   public void drawHead(class_4587 matrixStack, class_1657 player, float x, float y, float width, float height, float gap, float radius, Color color) {
      class_2960 skin = ((class_742)player).method_52814().comp_1626();
      float u = 0.125F;
      float u2 = 0.625F;
      float superGap = gap * 2.0F;
      int texture = mc.method_1531().method_4619(skin).method_4624();
      K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(matrixStack, x + gap, y + gap, width - superGap, height - superGap, radius, color, u, u, u, u, texture);
      K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(matrixStack, x, y, width, height, radius, color, u2, u, u, u, texture);
   }

   public void draw(class_4587 matrixStack, float x, float y, float width, float height, float radius, Color color, float u, float v, float texWidth, float texHeight, int texture) {
      this.draw(matrixStack, x, y, width, height, new Vector4f(radius, radius, radius, radius), color, u, v, texWidth, texHeight, texture);
   }

   public void draw(class_4587 matrixStack, float x, float y, float width, float height, Vector4f radius, Color color, float u, float v, float texWidth, float texHeight, int texture) {
      Matrix4f matrix4f = matrixStack.method_23760().method_23761();
      float z = 0.0F;
      float smoothness = 0.8F;
      float horizontalPadding = -smoothness / 2.0F + smoothness * 2.0F;
      float verticalPadding = smoothness / 2.0F + smoothness;
      float adjustedX = x - horizontalPadding / 2.0F;
      float adjustedY = y - verticalPadding / 2.0F;
      float adjustedWidth = width + horizontalPadding;
      float adjustedHeight = height + verticalPadding;
      Ke8433384857ddcab3d.record(matrix4f, adjustedX, adjustedY, adjustedWidth, adjustedHeight);
      RenderSystem.enableBlend();
      Ke8433384857ddcab3d.defaultBlendFunc();
      RenderSystem.disableCull();
      RenderSystem.setShaderTexture(0, texture);
      class_5944 shader = RenderSystem.setShader(shaderKey);
      shader.method_34582("uSize").method_1255(width, height);
      shader.method_34582("uRadius").method_35657(radius.x, radius.z, radius.w, radius.y);
      shader.method_34582("uSmoothness").method_1251(smoothness);
      class_287 builder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
      builder.method_22918(matrix4f, adjustedX, adjustedY, z).method_22913(u, v).method_39415(color.getRGB());
      builder.method_22918(matrix4f, adjustedX, adjustedY + adjustedHeight, z).method_22913(u, v + texHeight).method_39415(color.getRGB());
      builder.method_22918(matrix4f, adjustedX + adjustedWidth, adjustedY + adjustedHeight, z).method_22913(u + texWidth, v + texHeight).method_39415(color.getRGB());
      builder.method_22918(matrix4f, adjustedX + adjustedWidth, adjustedY, z).method_22913(u + texWidth, v).method_39415(color.getRGB());
      class_286.method_43433(builder.method_60800());
      RenderSystem.enableCull();
      RenderSystem.disableBlend();
   }

   static {
      shaderKey = new class_10156(Kc1f6f8c785f6a08448.getShader("rect/texture_rect"), class_290.field_1575, class_10149.field_53930);
   }
}
