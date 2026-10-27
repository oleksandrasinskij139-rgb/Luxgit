package code.miaware.client.features.modules.render.targetesp.modes;

import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.utils.color.K258ef67f9722180a72;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.client.features.modules.render.targetesp.K34929def563f4a28cf;
import code.miaware.client.features.modules.render.targetesp.K57a68ff1a2b51cf90d;
import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_10142;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

public class Kdd381d681f391b4ad7 extends K57a68ff1a2b51cf90d {
   private float rotation = 0.0F;
   private boolean rotatingForward = true;

   public void onUpdate() {
      if (!(Boolean)K34929def563f4a28cf.getInstance().skullAnimation.getValue()) {
         this.rotation = 0.0F;
      } else {
         float speed = K34929def563f4a28cf.getInstance().skullRotationSpeed.getValue();
         String rotationType = (String)K34929def563f4a28cf.getInstance().skullRotationType.getValue();
         if (rotationType.equals("Вращение")) {
            this.rotation += speed;
            if (this.rotation >= 360.0F) {
               this.rotation -= 360.0F;
            }
         } else if (this.rotatingForward) {
            this.rotation += speed;
            if (this.rotation >= 180.0F) {
               this.rotation = 180.0F;
               this.rotatingForward = false;
            }
         } else {
            this.rotation -= speed;
            if (this.rotation <= -180.0F) {
               this.rotation = -180.0F;
               this.rotatingForward = true;
            }
         }

      }
   }

   public void onRender3D(K9ee087b2e1791ad6b2 event) {
      if (currentTarget != null && this.canDraw()) {
         class_4184 camera = mc.field_1773.method_19418();
         float healthPercent = currentTarget.method_6032() / Math.max(currentTarget.method_6063(), 1.0F);
         int hurtTicks = currentTarget.field_6235;
         boolean hurt = hurtTicks > 0;
         float lowHealthShake = (1.0F - healthPercent) * 0.08F;
         float hurtShake = hurt ? (float)hurtTicks / 8.0F * 0.2F : 0.0F;
         float shake = lowHealthShake + hurtShake;
         double shakeX = (double)0.0F;
         double shakeY = (double)0.0F;
         double shakeZ = (double)0.0F;
         if (shake > 0.001F) {
            shakeX = (Math.random() - 0.4) * (double)shake;
            shakeY = (Math.random() - 0.4) * (double)shake;
            shakeZ = (Math.random() - 0.4) * (double)shake;
         }

         double x = getTargetX() - camera.method_19326().field_1352 + shakeX;
         double y = getTargetY() - camera.method_19326().field_1351 + (double)currentTarget.method_17682() / (double)2.0F + shakeY;
         double z = getTargetZ() - camera.method_19326().field_1350 + shakeZ;
         double size = (double)Ka0484a23398fb5f9ac.interpolate((double)this.prevSizeAnimation, sizeAnimation.getValue());
         String texture = healthPercent > 0.6F ? "target/skull_state_0" : (healthPercent > 0.3F ? "target/skull_state_1" : "target/skull_state_2");
         int alpha = (int)(showAnimation.getValue() * (double)255.0F);
         Color base = K258ef67f9722180a72.getAnimatedColor(alpha);
         Color red = new Color(255, 50, 50, alpha);
         Color color;
         if (hurt) {
            float hurtProgress = (float)Math.abs(Math.sin((double)hurtTicks * (Math.PI / 10D)));
            color = K80ee108ea129adac11.interpolate(base, red, hurtProgress);
         } else if (healthPercent < 0.3F) {
            color = K80ee108ea129adac11.interpolate(base, red, (1.0F - healthPercent / 0.3F) * 0.4F);
         } else {
            color = base;
         }

         class_4587 matrices = new class_4587();
         RenderSystem.disableDepthTest();
         RenderSystem.disableCull();
         RenderSystem.enableBlend();
         RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
         matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
         matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
         matrices.method_22904(x, y, z);
         matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
         matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
         if ((Boolean)K34929def563f4a28cf.getInstance().skullAnimation.getValue()) {
            String rotationType = (String)K34929def563f4a28cf.getInstance().skullRotationType.getValue();
            if (rotationType.equals("Вращение")) {
               matrices.method_22907(class_7833.field_40718.rotationDegrees(this.rotation));
            } else {
               matrices.method_22907(class_7833.field_40716.rotationDegrees(this.rotation));
            }
         }

         Matrix4f matrix = matrices.method_23760().method_23761();
         float halfSize = (float)size * 0.5F;
         RenderSystem.setShaderTexture(0, Kc1f6f8c785f6a08448.getImage(texture));
         RenderSystem.setShader(class_10142.field_53880);
         int skullColor = color.getRGB();
         class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
         buffer.method_22918(matrix, -halfSize, -halfSize, 0.0F).method_22913(0.0F, 1.0F).method_39415(skullColor);
         buffer.method_22918(matrix, halfSize, -halfSize, 0.0F).method_22913(1.0F, 1.0F).method_39415(skullColor);
         buffer.method_22918(matrix, halfSize, halfSize, 0.0F).method_22913(1.0F, 0.0F).method_39415(skullColor);
         buffer.method_22918(matrix, -halfSize, halfSize, 0.0F).method_22913(0.0F, 0.0F).method_39415(skullColor);
         class_286.method_43433(buffer.method_60800());
         float glowSize = halfSize * (1.5F + (1.0F - healthPercent) * 0.5F);
         int glowAlpha = (int)(showAnimation.getValue() * (double)(100.0F + (1.0F - healthPercent) * 50.0F));
         int glowColor = K80ee108ea129adac11.setAlpha(color, glowAlpha).getRGB();
         RenderSystem.setShaderTexture(0, Kc1f6f8c785f6a08448.getImage("particles/glow"));
         class_287 glowBuffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
         glowBuffer.method_22918(matrix, -glowSize, -glowSize, 0.0F).method_22913(0.0F, 0.0F).method_39415(glowColor);
         glowBuffer.method_22918(matrix, glowSize, -glowSize, 0.0F).method_22913(1.0F, 0.0F).method_39415(glowColor);
         glowBuffer.method_22918(matrix, glowSize, glowSize, 0.0F).method_22913(1.0F, 1.0F).method_39415(glowColor);
         glowBuffer.method_22918(matrix, -glowSize, glowSize, 0.0F).method_22913(0.0F, 1.0F).method_39415(glowColor);
         class_286.method_43433(glowBuffer.method_60800());
         RenderSystem.enableCull();
         RenderSystem.enableDepthTest();
         RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA);
         RenderSystem.disableBlend();
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      }
   }
}
