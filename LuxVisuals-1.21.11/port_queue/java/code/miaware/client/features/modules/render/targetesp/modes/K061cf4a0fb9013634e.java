package code.miaware.client.features.modules.render.targetesp.modes;

import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.utils.color.K258ef67f9722180a72;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.client.features.modules.render.targetesp.K34929def563f4a28cf;
import code.miaware.client.features.modules.render.targetesp.K57a68ff1a2b51cf90d;
import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
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

public class K061cf4a0fb9013634e extends K57a68ff1a2b51cf90d {
   private float espValue = 1.0F;
   private float prevEspValue = 0.0F;
   private float spinSpeed = 0.0F;
   private float spinDirection = 1.0F;
   private float spinAcceleration = 0.08F;
   private float maxSpinSpeed = 10.0F;
   private boolean shouldBounce = false;
   private float bounceProgress = 0.0F;
   private float matterProgress = 0.0F;
   private boolean matterShrinking = false;
   private boolean isRedOnImpact = false;
   private float redImpactProgress = 0.0F;

   public void onUpdate() {
      if (currentTarget != null && this.canDraw()) {
         String animationType = (String)K34929def563f4a28cf.getInstance().markerAnimation.getValue();
         this.prevEspValue = this.espValue;
         switch (animationType) {
            case "Статичный":
               this.espValue = 0.0F;
               break;
            case "Крутилка":
               this.spinSpeed += this.spinAcceleration * this.spinDirection;
               if (Math.abs(this.spinSpeed) >= this.maxSpinSpeed) {
                  this.spinDirection *= -1.0F;
               }

               this.espValue += this.spinSpeed;
               break;
            case "Прыжок":
               if (this.shouldBounce) {
                  this.bounceProgress += 0.15F;
                  if (this.bounceProgress >= 1.0F) {
                     this.shouldBounce = false;
                     this.bounceProgress = 0.0F;
                  }
               }
               break;
            case "Материя":
               if (this.matterShrinking) {
                  this.matterProgress += 0.08F;
                  if (this.matterProgress >= 1.0F) {
                     this.matterProgress = 1.0F;
                     this.matterShrinking = false;
                  }
               } else if (this.matterProgress > 0.0F) {
                  this.matterProgress -= 0.3F;
                  if (this.matterProgress <= 0.0F) {
                     this.matterProgress = 0.0F;
                  }
               }
         }

         if (this.isRedOnImpact) {
            this.redImpactProgress += 0.15F;
            if (this.redImpactProgress >= 1.0F) {
               this.redImpactProgress = 1.0F;
               this.isRedOnImpact = false;
            }
         } else if (this.redImpactProgress > 0.0F) {
            this.redImpactProgress -= 0.1F;
            if (this.redImpactProgress <= 0.0F) {
               this.redImpactProgress = 0.0F;
            }
         }

      }
   }

   public void onPlayerAttackTarget() {
      String animationType = (String)K34929def563f4a28cf.getInstance().markerAnimation.getValue();
      if (animationType.equals("Прыжок") && !this.shouldBounce) {
         this.shouldBounce = true;
         this.bounceProgress = 0.0F;
      } else if (animationType.equals("Материя")) {
         this.matterShrinking = true;
         this.matterProgress = 0.0F;
      }

      if ((Boolean)K34929def563f4a28cf.getInstance().markerRedOnImpact.getValue()) {
         this.isRedOnImpact = true;
         this.redImpactProgress = 0.0F;
      }

   }

   public void onRender3D(K9ee087b2e1791ad6b2 event) {
      if (currentTarget != null && this.canDraw()) {
         class_4184 camera = mc.field_1773.method_19418();
         double x = getTargetX() - camera.method_19326().field_1352;
         double y = getTargetY() - camera.method_19326().field_1351;
         double z = getTargetZ() - camera.method_19326().field_1350;
         String animationType = (String)K34929def563f4a28cf.getInstance().markerAnimation.getValue();
         double baseSize = (double)Ka0484a23398fb5f9ac.interpolate((double)this.prevSizeAnimation, sizeAnimation.getValue());
         double size = baseSize;
         double offsetY = (double)0.0F;
         double offsetX = (double)0.0F;
         double offsetZ = (double)0.0F;
         if (animationType.equals("Прыжок")) {
            if (this.shouldBounce) {
               if (this.bounceProgress < 0.5F) {
                  offsetY = (double)(this.bounceProgress / 0.5F) * (double)1.0F;
               } else {
                  offsetY = (double)((1.0F - this.bounceProgress) / 0.5F) * (double)1.0F;
               }

               size = baseSize * ((double)1.0F - (double)Math.abs(0.5F - this.bounceProgress) * 0.2);
            } else {
               offsetY = (double)0.0F;
               size = baseSize;
            }
         }

         if (animationType.equals("Материя")) {
            float shrinkFactor = 1.0F - this.matterProgress * 0.7F;
            size = baseSize * (double)shrinkFactor;
         }

         class_4587 matrices = new class_4587();
         RenderSystem.disableDepthTest();
         RenderSystem.disableCull();
         matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
         matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
         matrices.method_22904(x + offsetX, y + (double)(currentTarget.method_17682() / 2.0F) + offsetY, z + offsetZ);
         matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
         matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
         matrices.method_22907(class_7833.field_40718.rotationDegrees(Ka0484a23398fb5f9ac.interpolate((double)this.prevEspValue, (double)this.espValue)));
         RenderSystem.enableBlend();
         RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);

         try {
            String var10000;
            switch ((String)K34929def563f4a28cf.getInstance().markerTexture.getValue()) {
               case "2" -> var10000 = "target/target-esp2";
               default -> var10000 = "target/rounded_target_esp";
            }

            String textureName = var10000;
            RenderSystem.setShaderTexture(0, Kc1f6f8c785f6a08448.getImage(textureName));
            matrices.method_22904(-size / (double)2.0F, -size / (double)2.0F, -0.01);
            Matrix4f matrix = matrices.method_23760().method_23761();
            RenderSystem.setShader(class_10142.field_53880);
            alpha = (int)(showAnimation.getValue() * (double)255.0F);
            int baseColor = K258ef67f9722180a72.getAnimatedColor(alpha).getRGB();
            int color = baseColor;
            if (this.redImpactProgress > 0.0F) {
               int baseR = baseColor >> 16 & 255;
               int baseG = baseColor >> 8 & 255;
               int baseB = baseColor & 255;
               int targetR = 255;
               int targetG = 0;
               int targetB = 0;
               int r = (int)((float)baseR + (float)(targetR - baseR) * this.redImpactProgress);
               int g = (int)((float)baseG + (float)(targetG - baseG) * this.redImpactProgress);
               int b = (int)((float)baseB + (float)(targetB - baseB) * this.redImpactProgress);
               color = alpha << 24 | r << 16 | g << 8 | b;
            }

            class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
            buffer.method_22918(matrix, 0.0F, (float)size, 0.0F).method_22913(0.0F, 1.0F).method_39415(color);
            buffer.method_22918(matrix, (float)size, (float)size, 0.0F).method_22913(1.0F, 1.0F).method_39415(color);
            buffer.method_22918(matrix, (float)size, 0.0F, 0.0F).method_22913(1.0F, 0.0F).method_39415(color);
            buffer.method_22918(matrix, 0.0F, 0.0F, 0.0F).method_22913(0.0F, 0.0F).method_39415(color);
            class_286.method_43433(buffer.method_60800());
         } finally {
            RenderSystem.enableCull();
            RenderSystem.enableDepthTest();
            RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA);
            RenderSystem.disableBlend();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         }

      }
   }
}
