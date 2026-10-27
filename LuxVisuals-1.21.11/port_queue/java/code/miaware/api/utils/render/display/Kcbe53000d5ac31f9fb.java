package code.miaware.api.utils.render.display;

import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.render.Ke8433384857ddcab3d;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
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

public class Kcbe53000d5ac31f9fb implements K4600bef438dafc1a9c {
   private static final class_10156 shaderKey;

   public void draw(class_4587 matrixStack, float x, float y, float width, float height, float radius, Color color) {
      this.draw(matrixStack, x, y, width, height, new Vector4f(radius), color);
   }

   public void draw(class_4587 matrixStack, float x, float y, float width, float height, Vector4f radius, Color color) {
      Matrix4f matrix4f = matrixStack.method_23760().method_23761();
      float z = 0.0F;
      float smoothness = 1.0F;
      float horizontalPadding = -smoothness / 2.0F + smoothness * 2.0F;
      float verticalPadding = smoothness / 2.0F + smoothness;
      float adjustedX = x - horizontalPadding / 2.0F;
      float adjustedY = y - verticalPadding / 2.0F;
      float adjustedWidth = width + horizontalPadding;
      float adjustedHeight = height + verticalPadding;
      Ke8433384857ddcab3d.record(matrix4f, adjustedX, adjustedY, adjustedWidth, adjustedHeight);
      float[] normalizedColor = K80ee108ea129adac11.normalize(color);
      RenderSystem.enableBlend();
      Ke8433384857ddcab3d.defaultBlendFunc();
      RenderSystem.disableCull();
      class_5944 shader = RenderSystem.setShader(shaderKey);
      shader.method_34582("uSize").method_1255(width, height);
      shader.method_34582("uRadius").method_35657(radius.x, radius.z, radius.w, radius.y);
      shader.method_34582("uSmoothness").method_1251(smoothness);
      float time = (float)(System.currentTimeMillis() % 1000000L) / 1000.0F;
      shader.method_34582("uTime").method_1251(time);
      class_287 builder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);
      builder.method_22918(matrix4f, adjustedX, adjustedY, z).method_39415(color.getRGB());
      builder.method_22918(matrix4f, adjustedX, adjustedY + adjustedHeight, z).method_39415(color.getRGB());
      builder.method_22918(matrix4f, adjustedX + adjustedWidth, adjustedY + adjustedHeight, z).method_39415(color.getRGB());
      builder.method_22918(matrix4f, adjustedX + adjustedWidth, adjustedY, z).method_39415(color.getRGB());
      class_286.method_43433(builder.method_60800());
      RenderSystem.enableCull();
      RenderSystem.disableBlend();
   }

   static {
      shaderKey = new class_10156(Kc1f6f8c785f6a08448.getShader("rect/glow_button"), class_290.field_1576, class_10149.field_53930);
   }
}
