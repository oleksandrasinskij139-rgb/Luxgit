package code.miaware.client.features.modules.render.targetesp;

import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.api.utils.render.K300f7955bbb2ec50a2;
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
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Ke99984f8c7995a1b31 extends K57a68ff1a2b51cf90d {
   private float moving = 0.0F;
   private float prevMoving = 0.0F;
   private float impactProgress = 0.0F;
   private int prevHurtTime = 0;

   public void onUpdate() {
      if (currentTarget != null && this.canDraw()) {
         K34929def563f4a28cf module = K34929def563f4a28cf.getInstance();
         this.prevMoving = this.moving;
         this.moving += module.crystalSpeed.getValue();
         this.K6facb9d907682bd617();
      }
   }

   private void K6facb9d907682bd617() {
      K34929def563f4a28cf module = K34929def563f4a28cf.getInstance();
      if ((Boolean)module.crystalRedOnImpact.getValue() && currentTarget != null) {
         float fadeInSpeed = 0.3F;
         float fadeOutSpeed = 0.08F;
         float maxIntensity = 1.0F;
         int currentHurtTime = currentTarget.field_6235;
         if (currentHurtTime <= this.prevHurtTime && (currentHurtTime <= 0 || this.prevHurtTime != 0)) {
            if (currentHurtTime > 0) {
               this.impactProgress = Math.min(maxIntensity, this.impactProgress + fadeInSpeed * 0.5F);
            } else {
               this.impactProgress = Math.max(0.0F, this.impactProgress - fadeOutSpeed);
            }
         } else {
            this.impactProgress = Math.min(maxIntensity, this.impactProgress + fadeInSpeed);
         }

         this.prevHurtTime = currentHurtTime;
      } else {
         this.impactProgress = 0.0F;
         this.prevHurtTime = 0;
      }
   }

   public void onRender3D(K9ee087b2e1791ad6b2 event) {
      if (currentTarget != null && this.canDraw()) {
         K34929def563f4a28cf module = K34929def563f4a28cf.getInstance();
         class_4587 ms = event.matrixStack();
         class_4184 camera = mc.field_1773.method_19418();
         float alphaPC = (float)showAnimation.getValue();
         class_243 renderPos = new class_243((double)Ka0484a23398fb5f9ac.interpolate(currentTarget.field_6014, currentTarget.method_23317()), (double)Ka0484a23398fb5f9ac.interpolate(currentTarget.field_6036, currentTarget.method_23318()), (double)Ka0484a23398fb5f9ac.interpolate(currentTarget.field_5969, currentTarget.method_23321()));
         float width = currentTarget.method_17681() * 1.5F;
         float movingValue = Ka0484a23398fb5f9ac.interpolate((double)this.prevMoving, (double)this.moving);
         ms.method_22903();
         RenderSystem.enableBlend();
         RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
         RenderSystem.enableDepthTest();
         RenderSystem.disableCull();
         RenderSystem.depthMask(false);
         class_243 cameraPos = camera.method_19326();
         ms.method_22904(renderPos.field_1352 - cameraPos.field_1352, renderPos.field_1351 - cameraPos.field_1351, renderPos.field_1350 - cameraPos.field_1350);
         class_287 builder = K300f7955bbb2ec50a2.createBuffer();

         for(int i = 0; i < 360; i += 20) {
            float val = 1.2F - 0.5F * alphaPC;
            float rad = (float)Math.toRadians((double)((float)i + movingValue * 0.3F));
            float sin = (float)(Math.sin((double)rad) * (double)width * (double)val);
            float cos = (float)(Math.cos((double)rad) * (double)width * (double)val);
            float size = 0.1F;
            ms.method_22903();
            float verticalPos = 0.1F + currentTarget.method_17682() * (float)Math.abs(Math.sin((double)i));
            ms.method_46416(sin, verticalPos, cos);
            class_243 crystalPos = renderPos.method_1031((double)sin, (double)1.0F, (double)cos);
            class_243 targetPos = currentTarget.method_19538().method_1031((double)0.0F, (double)currentTarget.method_17682() / (double)2.0F, (double)0.0F);
            Vector3f directionToTarget = (new Vector3f((float)(targetPos.field_1352 - crystalPos.field_1352), (float)(targetPos.field_1351 - crystalPos.field_1351), (float)(targetPos.field_1350 - crystalPos.field_1350))).normalize();
            Vector3f initialDirection = new Vector3f(0.0F, 1.0F, 0.0F);
            Quaternionf rotation = (new Quaternionf()).rotationTo(initialDirection, directionToTarget);
            ms.method_22907(rotation);
            int alpha = (int)(255.0F * alphaPC);
            Color color = Kb18d549c13309af9a5.gradient(i * 3, alpha);
            if (this.impactProgress > 0.0F) {
               Color impactColor = new Color(255, 32, 32, alpha);
               color = this.Kb3329817b04740ffc4(color, impactColor, this.impactProgress);
            }

            K300f7955bbb2ec50a2.render(ms, builder, 0.0F, 0.0F, 0.0F, size, color);
            ms.method_22909();
         }

         class_286.method_43433(builder.method_60800());
         if ((Boolean)module.crystalBloom.getValue()) {
            RenderSystem.setShaderTexture(0, Kc1f6f8c785f6a08448.getImage("particles/glow"));
            RenderSystem.setShader(class_10142.field_53880);
            float bigSize = module.crystalBloomSize.getValue();

            for(int i = 0; i < 360; i += 20) {
               float val = 1.2F - 0.5F * alphaPC;
               float rad = (float)Math.toRadians((double)((float)i + movingValue * 0.3F));
               float sin = (float)(Math.sin((double)rad) * (double)width * (double)val);
               float cos = (float)(Math.cos((double)rad) * (double)width * (double)val);
               float verticalPos = 0.1F + currentTarget.method_17682() * (float)Math.abs(Math.sin((double)i));
               ms.method_22903();
               ms.method_46416(sin, verticalPos, cos);
               ms.method_22907(camera.method_23767());
               Matrix4f matrix = ms.method_23760().method_23761();
               int bloomAlpha = (int)(255.0F * alphaPC * 0.2F);
               Color color = Kb18d549c13309af9a5.gradient(i * 3, bloomAlpha);
               if (this.impactProgress > 0.0F) {
                  Color impactColor = new Color(255, 32, 32, bloomAlpha);
                  color = this.Kb3329817b04740ffc4(color, impactColor, this.impactProgress);
               }

               int colorRGB = color.getRGB();
               class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
               buffer.method_22918(matrix, -bigSize / 2.0F, -bigSize / 2.0F, 0.0F).method_22913(0.0F, 0.0F).method_39415(colorRGB);
               buffer.method_22918(matrix, -bigSize / 2.0F, bigSize / 2.0F, 0.0F).method_22913(0.0F, 1.0F).method_39415(colorRGB);
               buffer.method_22918(matrix, bigSize / 2.0F, bigSize / 2.0F, 0.0F).method_22913(1.0F, 1.0F).method_39415(colorRGB);
               buffer.method_22918(matrix, bigSize / 2.0F, -bigSize / 2.0F, 0.0F).method_22913(1.0F, 0.0F).method_39415(colorRGB);
               class_286.method_43433(buffer.method_60800());
               ms.method_22909();
            }
         }

         RenderSystem.depthMask(true);
         RenderSystem.setShaderTexture(0, 0);
         RenderSystem.disableBlend();
         RenderSystem.enableCull();
         RenderSystem.enableDepthTest();
         RenderSystem.defaultBlendFunc();
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         ms.method_22909();
      }
   }

   private Color Kb3329817b04740ffc4(Color color1, Color color2, float progress) {
      progress = Math.max(0.0F, Math.min(1.0F, progress));
      int r1 = color1.getRed();
      int g1 = color1.getGreen();
      int b1 = color1.getBlue();
      int a1 = color1.getAlpha();
      int r2 = color2.getRed();
      int g2 = color2.getGreen();
      int b2 = color2.getBlue();
      int a2 = color2.getAlpha();
      float smoothProgress = progress * progress * (3.0F - 2.0F * progress);
      int r = (int)((float)r1 + (float)(r2 - r1) * smoothProgress);
      int g = (int)((float)g1 + (float)(g2 - g1) * smoothProgress);
      int b = (int)((float)b1 + (float)(b2 - b1) * smoothProgress);
      int a = (int)((float)a1 + (float)(a2 - a1) * smoothProgress);
      return new Color(r, g, b, a);
   }

   public void updateTarget() {
      super.updateTarget();
      if (currentTarget == null) {
         this.impactProgress = 0.0F;
         this.prevHurtTime = 0;
      }

   }
}
