package code.miaware.api.utils.render.display;

import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.Ke8433384857ddcab3d;
import code.miaware.api.utils.render.Kecb75e73598eb5799c;
import code.miaware.client.features.modules.render.K45978f900e11d6615f;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_10149;
import net.minecraft.class_10156;
import net.minecraft.class_276;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4587;
import net.minecraft.class_5944;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class Kbab85e821f770b3523 implements K4600bef438dafc1a9c {
   public static boolean clickGuiContext = false;
   private static final class_10156 shaderKey;

   public void drawNoBlur(class_4587 matrixStack, float x, float y, float width, float height, float radius, Color color) {
      K2b5e2c5f2b9891b450.RECT.draw(matrixStack, x, y, width, height, radius, color);
   }

   public void drawNoBlur(class_4587 matrixStack, float x, float y, float width, float height, Vector4f radius, Color color) {
      K2b5e2c5f2b9891b450.RECT.draw(matrixStack, x, y, width, height, radius.x, color);
   }

   public void draw(class_4587 matrixStack, float x, float y, float width, float height, float radius, Color color, float mix) {
      this.draw(matrixStack, x, y, width, height, new Vector4f(radius, radius, radius, radius), color, color, color, color, mix);
   }

   public void draw(class_4587 matrixStack, float x, float y, float width, float height, float radius, Color color) {
      this.draw(matrixStack, x, y, width, height, radius, color, Kfae29c687712462375.getGlassy());
   }

   public void draw(class_4587 matrixStack, float x, float y, float width, float height, Vector4f radius, Color color) {
      this.draw(matrixStack, x, y, width, height, radius, color, color, color, color, Kfae29c687712462375.getGlassy());
   }

   public void draw(class_4587 matrixStack, float x, float y, float width, float height, Vector4f radius, Color topLeft, Color topRight, Color bottomLeft, Color bottomRight) {
      this.draw(matrixStack, x, y, width, height, radius, topLeft, topRight, bottomLeft, bottomRight, Kfae29c687712462375.getGlassy());
   }

   public void draw(class_4587 matrixStack, float x, float y, float width, float height, Vector4f radius, Color topLeft, Color topRight, Color bottomLeft, Color bottomRight, float mix) {
      boolean isClickGUI = clickGuiContext;
      if (!isClickGUI && !Ke8433384857ddcab3d.isCapturing()) {
         try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

            for(StackTraceElement element : stackTrace) {
               String className = element.getClassName();
               if (className.contains(".clickgui.")) {
                  isClickGUI = true;
                  break;
               }
            }
         } catch (Exception var43) {
         }
      }

      boolean blurDisabled = false;
      if (!isClickGUI) {
         try {
            blurDisabled = K45978f900e11d6615f.getInstance().isBlurDisabled();
         } catch (Exception var42) {
            blurDisabled = true;
         }
      }

      if (blurDisabled) {
         K2b5e2c5f2b9891b450.RECT.draw(matrixStack, x, y, width, height, radius.x, topLeft);
      } else {
         float z = 0.0F;
         float[] color1 = K80ee108ea129adac11.normalize(topLeft);
         float[] color2 = K80ee108ea129adac11.normalize(bottomLeft);
         float[] color3 = K80ee108ea129adac11.normalize(bottomRight);
         float[] color4 = K80ee108ea129adac11.normalize(topRight);
         float a = (color1[3] + color2[3] + color3[3] + color4[3]) / 4.0F;
         Matrix4f matrix4f = matrixStack.method_23760().method_23761();
         class_276 fbo = (class_276)Kecb75e73598eb5799c.fbos.getFirst();
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
         RenderSystem.setShaderTexture(0, fbo.method_30277());
         class_5944 shader = RenderSystem.setShader(shaderKey);
         shader.method_34582("uSize").method_1255(width, height);
         shader.method_34582("uRadius").method_35657(radius.x, radius.z, radius.w, radius.y);
         shader.method_34582("uMix").method_1251(mix);
         shader.method_34582("uSmoothness").method_1251(smoothness);
         shader.method_34582("uAlpha").method_1251(a);
         shader.method_34582("uTopLeftColor").method_35657(color1[0], color1[1], color1[2], color1[3]);
         shader.method_34582("uBottomLeftColor").method_35657(color2[0], color2[1], color2[2], color2[3]);
         shader.method_34582("uBottomRightColor").method_35657(color3[0], color3[1], color3[2], color3[3]);
         shader.method_34582("uTopRightColor").method_35657(color4[0], color4[1], color4[2], color4[3]);
         float uLeft;
         float uRight;
         float vTop;
         float vBottom;
         if (mix != 1.0F) {
            int texW = fbo.field_1482;
            int texH = fbo.field_1481;
            double scale = (double)texW / (double)mc.method_22683().method_4486();
            float fx = (float)((double)x * scale);
            float fy = (float)((double)y * scale);
            float fw = (float)((double)width * scale);
            float fh = (float)((double)height * scale);
            uLeft = fx / (float)texW;
            uRight = (fx + fw) / (float)texW;
            vTop = 1.0F - fy / (float)texH;
            vBottom = 1.0F - (fy + fh) / (float)texH;
         } else {
            vBottom = 0.0F;
            vTop = 0.0F;
            uRight = 0.0F;
            uLeft = 0.0F;
         }

         class_287 builder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
         builder.method_22918(matrix4f, adjustedX, adjustedY, z).method_22913(uLeft, vTop).method_39415(topLeft.getRGB());
         builder.method_22918(matrix4f, adjustedX, adjustedY + adjustedHeight, z).method_22913(uLeft, vBottom).method_39415(bottomLeft.getRGB());
         builder.method_22918(matrix4f, adjustedX + adjustedWidth, adjustedY + adjustedHeight, z).method_22913(uRight, vBottom).method_39415(bottomRight.getRGB());
         builder.method_22918(matrix4f, adjustedX + adjustedWidth, adjustedY, z).method_22913(uRight, vTop).method_39415(topRight.getRGB());
         class_286.method_43433(builder.method_60800());
         RenderSystem.enableCull();
         RenderSystem.disableBlend();
      }
   }

   static {
      shaderKey = new class_10156(Kc1f6f8c785f6a08448.getShader("rect/blurred_rect"), class_290.field_1575, class_10149.field_53930);
   }
}
