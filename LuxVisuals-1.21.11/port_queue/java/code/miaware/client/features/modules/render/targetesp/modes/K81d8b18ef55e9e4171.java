package code.miaware.client.features.modules.render.targetesp.modes;

import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.client.features.modules.render.targetesp.K57a68ff1a2b51cf90d;
import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_10142;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

public class K81d8b18ef55e9e4171 extends K57a68ff1a2b51cf90d {
   private static final class_2960 BLOOM = class_2960.method_60655("minecraft", "textures/particle/glow.png");

   public void onUpdate() {
   }

   public void onRender3D(K9ee087b2e1791ad6b2 event) {
      if (currentTarget != null && this.canDraw()) {
         float anim = (float)showAnimation.getValue();
         if (!(anim <= 0.0F)) {
            class_4587 ms = event.matrixStack();
            class_4184 camera = mc.field_1773.method_19418();
            class_243 targetPos = new class_243((double)Ka0484a23398fb5f9ac.interpolate((double)((float)currentTarget.field_6014), (double)((float)currentTarget.method_23317())), (double)Ka0484a23398fb5f9ac.interpolate((double)((float)currentTarget.field_6036), (double)((float)currentTarget.method_23318())), (double)Ka0484a23398fb5f9ac.interpolate((double)((float)currentTarget.field_5969), (double)((float)currentTarget.method_23321())));
            double renderX = targetPos.field_1352 - camera.method_19326().field_1352;
            double renderY = targetPos.field_1351 - camera.method_19326().field_1351;
            double renderZ = targetPos.field_1350 - camera.method_19326().field_1350;
            float height = currentTarget.method_17682();
            float width = currentTarget.method_17681();
            float radius = width * 1.2F;
            float time = (float)(System.currentTimeMillis() % 4000L) / 4000.0F;
            float offset = time * 360.0F;
            int lightsCount = 30;
            int spirals = 3;
            ms.method_22903();
            ms.method_22904(renderX, renderY, renderZ);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableCull();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.setShader(class_10142.field_53876);
            class_289 tessellator = class_289.method_1348();
            class_287 buffer = tessellator.method_60827(class_5596.field_29345, class_290.field_1576);
            int wireColor = -16041205;

            for(int i = 0; i <= lightsCount; ++i) {
               float progress = (float)i / (float)lightsCount;
               float angle = (float)Math.toRadians((double)(offset + progress * 360.0F * (float)spirals));
               float currentRadius = radius * (1.0F - progress * 0.6F);
               float x = (float)Math.cos((double)angle) * currentRadius;
               float z = (float)Math.sin((double)angle) * currentRadius;
               float y = progress * height;
               int alpha = (int)(anim * 255.0F);
               int color = K80ee108ea129adac11.setAlpha(new Color(wireColor, true), alpha).getRGB();
               int r = color >> 16 & 255;
               int g = color >> 8 & 255;
               int b = color & 255;
               int a = color >> 24 & 255;
               buffer.method_22918(ms.method_23760().method_23761(), x, y, z).method_1336(r, g, b, a);
            }

            class_286.method_43433(buffer.method_60800());
            RenderSystem.setShader(class_10142.field_53880);
            RenderSystem.setShaderTexture(0, BLOOM);
            RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
            class_287 bulbBuffer = tessellator.method_60827(class_5596.field_27382, class_290.field_1575);
            float pitch = camera.method_19329();
            float yaw = camera.method_19330();

            for(int i = 0; i <= lightsCount; ++i) {
               float progress = (float)i / (float)lightsCount;
               float angle = (float)Math.toRadians((double)(offset + progress * 360.0F * (float)spirals));
               float currentRadius = radius * (1.0F - progress * 0.6F);
               float x = (float)Math.cos((double)angle) * currentRadius;
               float z = (float)Math.sin((double)angle) * currentRadius;
               float y = progress * height;
               float size = 0.08F * this.K21ba3ab75e2f4ceb0f(anim);
               float twinkle = (float)Math.sin((double)System.currentTimeMillis() / (double)100.0F + (double)i) * 0.2F + 0.8F;
               float finalAlpha = anim * twinkle;
               int color = this.Kd73e406b8ddf0b8b64(i);
               int alphaValue = (int)(finalAlpha * 255.0F);
               int finalColor = K80ee108ea129adac11.setAlpha(new Color(color, true), alphaValue).getRGB();
               this.K52fccc98e591e83b17(bulbBuffer, ms, x, y, z, size, yaw, pitch, finalColor);
            }

            class_286.method_43433(bulbBuffer.method_60800());
            RenderSystem.depthMask(true);
            RenderSystem.disableBlend();
            RenderSystem.enableCull();
            RenderSystem.enableDepthTest();
            ms.method_22909();
         }
      }
   }

   private void K52fccc98e591e83b17(class_287 buffer, class_4587 ms, float x, float y, float z, float scale, float yaw, float pitch, int color) {
      ms.method_22903();
      ms.method_46416(x, y, z);
      ms.method_22907(class_7833.field_40716.rotationDegrees(-yaw));
      ms.method_22907(class_7833.field_40714.rotationDegrees(pitch));
      Matrix4f matrix = ms.method_23760().method_23761();
      int r = color >> 16 & 255;
      int g = color >> 8 & 255;
      int b = color & 255;
      int a = color >> 24 & 255;
      buffer.method_22918(matrix, -scale, -scale, 0.0F).method_22913(0.0F, 0.0F).method_1336(r, g, b, a);
      buffer.method_22918(matrix, -scale, scale, 0.0F).method_22913(0.0F, 1.0F).method_1336(r, g, b, a);
      buffer.method_22918(matrix, scale, scale, 0.0F).method_22913(1.0F, 1.0F).method_1336(r, g, b, a);
      buffer.method_22918(matrix, scale, -scale, 0.0F).method_22913(1.0F, 0.0F).method_1336(r, g, b, a);
      ms.method_22909();
   }

   private int Kd73e406b8ddf0b8b64(int index) {
      int type = index % 4;
      int var10000;
      switch (type) {
         case 0 -> var10000 = -65536;
         case 1 -> var10000 = -10496;
         case 2 -> var10000 = -16711936;
         case 3 -> var10000 = -16728065;
         default -> var10000 = -1;
      }

      return var10000;
   }

   private float K21ba3ab75e2f4ceb0f(float x) {
      return 1.0F - (float)Math.pow((double)(1.0F - x), (double)3.0F);
   }
}
