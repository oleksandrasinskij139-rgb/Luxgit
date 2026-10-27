package code.miaware.client.features.modules.render.targetesp.modes;

import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import code.miaware.api.utils.color.K258ef67f9722180a72;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.client.features.modules.render.targetesp.K34929def563f4a28cf;
import code.miaware.client.features.modules.render.targetesp.K57a68ff1a2b51cf90d;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_10142;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.lwjgl.opengl.GL11;

public class K8e21e481d8cc09697d extends K57a68ff1a2b51cf90d {
   private float movingValue = 0.0F;
   private float impactProgress = 0.0F;
   private int prevHurtTime = 0;

   public void onUpdate() {
      if (currentTarget != null && this.canDraw()) {
         K34929def563f4a28cf module = K34929def563f4a28cf.getInstance();
         this.movingValue += module.chainSpeed.getValue() * 0.5F;
         if (this.movingValue > 360.0F) {
            this.movingValue -= 360.0F;
         }

         this.K0edc00aff546b1d0de();
      }
   }

   public void onRender3D(K9ee087b2e1791ad6b2 event) {
      if (currentTarget != null && this.canDraw()) {
         class_4587 ms = event.matrixStack();
         class_243 cam = mc.field_1773.method_19418().method_19326();
         class_243 targetPos = new class_243(class_3532.method_16436((double)event.partialTicks(), currentTarget.field_6038, currentTarget.method_23317()), class_3532.method_16436((double)event.partialTicks(), currentTarget.field_5971, currentTarget.method_23318()), class_3532.method_16436((double)event.partialTicks(), currentTarget.field_5989, currentTarget.method_23321()));
         double entX = targetPos.field_1352 - cam.field_1352;
         double entY = targetPos.field_1351 - cam.field_1351 - (double)0.5F;
         double entZ = targetPos.field_1350 - cam.field_1350;
         float gradusX = (float)((double)20.0F * Math.min((double)1.0F + Math.sin(Math.toRadians((double)this.movingValue)), (double)1.0F));
         float gradusZ = (float)((double)20.0F * (Math.min((double)1.0F + Math.sin(Math.toRadians((double)this.movingValue)), (double)2.0F) - (double)1.0F));
         float width = currentTarget.method_17681() * 1.5F * (float)sizeAnimation.getValue();
         float alphaVal = (float)showAnimation.getValue();
         RenderSystem.enableBlend();
         RenderSystem.blendFunc(770, 1);
         GL11.glDisable(2884);
         RenderSystem.depthMask(true);
         Color themeColor = K258ef67f9722180a72.getAnimatedColor((int)(255.0F * alphaVal));
         Color blendedColorObj = this.impactProgress > 0.0F ? new Color(class_3532.method_48781(this.impactProgress, themeColor.getRed(), 255), class_3532.method_48781(this.impactProgress, themeColor.getGreen(), 0), class_3532.method_48781(this.impactProgress, themeColor.getBlue(), 0), themeColor.getAlpha()) : themeColor;
         class_2960 chainTexture = class_2960.method_60655("luxvisuals", "textures/world/chain.png");
         int linksStep = 18;
         int totalAngle = 720;
         float chainSize = 4.0F;
         float down = 1.0F;

         for(int chain = 0; chain < 2; ++chain) {
            float val = 1.2F - 0.5F * (chain == 0 ? 1.0F : 0.9F);
            ms.method_22903();
            ms.method_22904(entX, entY + (double)(currentTarget.method_17682() / 2.0F), entZ);
            float x = 0.0F;
            float y = 0.0F;
            float z = 0.0F;
            ms.method_22907((new Quaternionf()).rotateZ((float)Math.toRadians(chain == 0 ? (double)gradusX : (double)(-gradusX))));
            ms.method_22907((new Quaternionf()).rotateX((float)Math.toRadians(chain == 0 ? (double)gradusZ : (double)(-gradusZ))));
            Matrix4f matrix = ms.method_23760().method_23761();
            RenderSystem.setShaderTexture(0, chainTexture);
            RenderSystem.setShader(class_10142.field_53880);
            int alphaInt = class_3532.method_15340((int)(alphaVal * 255.0F), 0, 255);
            Color colorWithAlpha = K80ee108ea129adac11.setAlpha(blendedColorObj, alphaInt);
            class_287 builder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
            int modif = linksStep / 2;

            for(int i = 0; i < totalAngle; i += modif) {
               float prevSin = (float)((double)(x + (chain == 0 ? gradusX : -gradusX) / 100.0F) + Math.sin(Math.toRadians((double)((float)(i - modif) + this.movingValue * 0.5F))) * (double)width * (double)val);
               float prevCos = (float)((double)(z + (chain == 0 ? -gradusZ : gradusZ) / 100.0F) + Math.cos(Math.toRadians((double)((float)(i - modif) + this.movingValue * 0.5F))) * (double)width * (double)val);
               float sin = (float)((double)(x + (chain == 0 ? gradusX : -gradusX) / 100.0F) + Math.sin(Math.toRadians((double)((float)i + this.movingValue * 0.5F))) * (double)width * (double)val);
               float cos = (float)((double)(z + (chain == 0 ? -gradusZ : gradusZ) / 100.0F) + Math.cos(Math.toRadians((double)((float)i + this.movingValue * 0.5F))) * (double)width * (double)val);
               int r = colorWithAlpha.getRed();
               int g = colorWithAlpha.getGreen();
               int b = colorWithAlpha.getBlue();
               int a = colorWithAlpha.getAlpha();
               builder.method_22918(matrix, prevSin, y, prevCos).method_22913(0.0027777778F * (float)(i - modif) * chainSize, 0.0F).method_1336(r, g, b, a);
               builder.method_22918(matrix, sin, y, cos).method_22913(0.0027777778F * (float)i * chainSize, 0.0F).method_1336(r, g, b, a);
               builder.method_22918(matrix, sin, y + down, cos).method_22913(0.0027777778F * (float)i * chainSize, 0.99F).method_1336(r, g, b, a);
               builder.method_22918(matrix, prevSin, y + down, prevCos).method_22913(0.0027777778F * (float)(i - modif) * chainSize, 0.99F).method_1336(r, g, b, a);
            }

            class_286.method_43433(builder.method_60800());
            ms.method_22909();
         }

         RenderSystem.defaultBlendFunc();
         RenderSystem.disableBlend();
         RenderSystem.enableDepthTest();
         GL11.glEnable(2884);
      }
   }

   private void K0edc00aff546b1d0de() {
      K34929def563f4a28cf module = K34929def563f4a28cf.getInstance();
      if ((Boolean)module.chainRedOnImpact.getValue() && currentTarget != null) {
         int currentHurtTime = currentTarget.field_6235;
         if (currentHurtTime <= this.prevHurtTime && (currentHurtTime <= 0 || this.prevHurtTime != 0)) {
            if (currentHurtTime > 0) {
               this.impactProgress = Math.min(1.0F, this.impactProgress + 0.105000004F);
            } else {
               this.impactProgress = Math.max(0.0F, this.impactProgress - 0.0544F);
            }
         } else {
            this.impactProgress = Math.min(1.0F, this.impactProgress + 0.3F);
         }

         this.prevHurtTime = currentHurtTime;
      } else {
         this.impactProgress = 0.0F;
         this.prevHurtTime = 0;
      }
   }
}
