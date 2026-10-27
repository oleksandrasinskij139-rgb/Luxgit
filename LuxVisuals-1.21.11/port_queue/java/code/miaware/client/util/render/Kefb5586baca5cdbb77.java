package code.miaware.client.util.render;

import code.miaware.api.utils.render.shader.K745964d54ca1b172b8;
import code.miaware.client.features.modules.render.K79e0f6624e5ddb7ac3;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_276;
import net.minecraft.class_284;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_5944;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public class Kefb5586baca5cdbb77 {
   private static K745964d54ca1b172b8 handBlurProgram;
   private static K745964d54ca1b172b8 handGlassProgram;
   private static K745964d54ca1b172b8 handMirageProgram;
   private static K745964d54ca1b172b8 handsFillProgram;
   private static K745964d54ca1b172b8 handsGlowProgram;
   private static K745964d54ca1b172b8 handsMirrorProgram;
   private static K745964d54ca1b172b8 handsOutlineProgram;
   private static K745964d54ca1b172b8 handsFireProgram;
   private class_276 withHandFbo;
   private class_276 beforeHandFbo;
   private float blurRadius;
   private float threshold;
   private final float[] tintColor;
   private final int screenW;
   private final int screenH;

   public static void init() {
      handBlurProgram = new K745964d54ca1b172b8(class_2960.method_60655("luxvisuals", "core/hand_blur"), class_290.field_1576);
      handGlassProgram = new K745964d54ca1b172b8(class_2960.method_60655("luxvisuals", "core/hand_glass"), class_290.field_1576);
      handMirageProgram = new K745964d54ca1b172b8(class_2960.method_60655("luxvisuals", "core/hand_mirage"), class_290.field_1576);
      handsFillProgram = new K745964d54ca1b172b8(class_2960.method_60655("luxvisuals", "core/hands_fill"), class_290.field_1576);
      handsGlowProgram = new K745964d54ca1b172b8(class_2960.method_60655("luxvisuals", "core/hands_glow"), class_290.field_1576);
      handsMirrorProgram = new K745964d54ca1b172b8(class_2960.method_60655("luxvisuals", "core/hands_mirror"), class_290.field_1576);
      handsOutlineProgram = new K745964d54ca1b172b8(class_2960.method_60655("luxvisuals", "core/hands_outline"), class_290.field_1576);
      handsFireProgram = new K745964d54ca1b172b8(class_2960.method_60655("luxvisuals", "core/hands_fire_pretty"), class_290.field_1576);
   }

   public Kefb5586baca5cdbb77(class_276 withHandFbo, class_276 beforeHandFbo, float blurRadius, float threshold, float[] tintColor, int screenW, int screenH) {
      this.withHandFbo = withHandFbo;
      this.beforeHandFbo = beforeHandFbo;
      this.blurRadius = blurRadius;
      this.threshold = threshold;
      this.tintColor = tintColor;
      this.screenW = screenW;
      this.screenH = screenH;
   }

   public void render(Matrix4f matrix) {
      String mode = K79e0f6624e5ddb7ac3.getInstance().getShaderMode();
      K745964d54ca1b172b8 program = null;
      if (mode.equals("Blur")) {
         program = handBlurProgram;
      } else if (mode.equals("Glass")) {
         program = handGlassProgram;
      } else if (mode.equals("Mirage")) {
         program = handMirageProgram;
      } else if (mode.equals("Fill")) {
         program = handsFillProgram;
      } else if (mode.equals("Glow")) {
         program = handsGlowProgram;
      } else if (mode.equals("Mirror")) {
         program = handsMirrorProgram;
      } else if (mode.equals("Outline")) {
         program = handsOutlineProgram;
      } else if (mode.equals("Fire")) {
         program = handsFireProgram;
      }

      if (program != null) {
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableCull();
         RenderSystem.disableDepthTest();
         RenderSystem.setShaderTexture(0, this.withHandFbo.method_30277());
         RenderSystem.setShaderTexture(1, this.beforeHandFbo.method_30277());
         class_5944 shader = program.use();
         if (shader == null) {
            RenderSystem.enableDepthTest();
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
         } else {
            if (mode.equals("Blur")) {
               this.K3b89c9f1e0cafa1464(program);
            } else if (mode.equals("Glass")) {
               this.Kd10a1daaf7c841a618(program);
            } else if (mode.equals("Mirage")) {
               this.Ka92e17f9a3ac466d07(program);
            } else if (mode.equals("Fill")) {
               this.K3f38f08e1ddca9a2d5(program);
            } else if (mode.equals("Glow")) {
               this.K9ddfc96a08ae022a6a(program);
            } else if (mode.equals("Mirror")) {
               this.Ka5f4b868aa06817729(program);
            } else if (mode.equals("Outline")) {
               this.K79a8a967f50d4c22ca(program);
            } else if (mode.equals("Fire")) {
               this.K8c4968e08665558a24(program);
            }

            int white = -1;
            class_287 builder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);
            builder.method_22912(-1.0F, -1.0F, 0.0F).method_39415(white);
            builder.method_22912(-1.0F, 1.0F, 0.0F).method_39415(white);
            builder.method_22912(1.0F, 1.0F, 0.0F).method_39415(white);
            builder.method_22912(1.0F, -1.0F, 0.0F).method_39415(white);
            class_286.method_43433(builder.method_60800());
            RenderSystem.setShaderTexture(0, 0);
            RenderSystem.setShaderTexture(1, 0);
            RenderSystem.enableDepthTest();
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
         }
      }
   }

   private void K3b89c9f1e0cafa1464(K745964d54ca1b172b8 program) {
      class_284 blurU = program.findUniform("BlurRadius");
      class_284 thrU = program.findUniform("Threshold");
      class_284 tintU = program.findUniform("TintColor");
      if (blurU != null) {
         blurU.method_1251(this.blurRadius);
      }

      if (thrU != null) {
         thrU.method_1251(this.threshold);
      }

      if (tintU != null) {
         tintU.method_35657(this.tintColor[0], this.tintColor[1], this.tintColor[2], this.tintColor[3]);
      }

   }

   private void Kd10a1daaf7c841a618(K745964d54ca1b172b8 program) {
      K79e0f6624e5ddb7ac3 module = K79e0f6624e5ddb7ac3.getInstance();
      class_284 paramsU = program.findUniform("uParams");
      if (paramsU != null) {
         paramsU.method_35657(module.getGlassBlur(), module.getGlassDistortion(), module.getGlassEdge(), module.getGlassRadius());
      }

      class_284 tintU = program.findUniform("uTint");
      if (tintU != null) {
         tintU.method_35657(this.tintColor[0], this.tintColor[1], this.tintColor[2], this.tintColor[3] * 0.3F);
      }

      class_284 stateU = program.findUniform("uState");
      if (stateU != null) {
         float time = (float)(System.currentTimeMillis() % 100000L) / 1000.0F;
         stateU.method_35657(module.getGlassAlpha(), module.getGlassWaveSpeed(), time, module.getGlassWaveAmp());
      }

      class_284 qualityU = program.findUniform("uQuality");
      if (qualityU != null) {
         qualityU.method_1251((float)module.getGlassQuality());
      }

   }

   private void Ka92e17f9a3ac466d07(K745964d54ca1b172b8 program) {
      class_310 mc = class_310.method_1551();
      K79e0f6624e5ddb7ac3 module = K79e0f6624e5ddb7ac3.getInstance();
      class_284 screenU = program.findUniform("uScreen");
      if (screenU != null) {
         screenU.method_35657((float)this.screenW, (float)this.screenH, 0.0F, 0.0F);
      }

      class_284 paramsU = program.findUniform("uParams");
      if (paramsU != null) {
         paramsU.method_35657(module.getMirageBlur(), module.getMirageDistortion(), module.getMirageEdge(), 0.0F);
      }

      class_284 tintU = program.findUniform("uTint");
      if (tintU != null) {
         tintU.method_35657(this.tintColor[0], this.tintColor[1], this.tintColor[2], this.tintColor[3] * 0.5F);
      }

      class_284 stateU = program.findUniform("uState");
      if (stateU != null) {
         float time = (float)(System.currentTimeMillis() % 100000L) / 1000.0F;
         stateU.method_35657(module.getMirageAlpha(), module.getMirageWaveSpeed(), time, module.getMirageWaveAmp());
      }

      class_284 qualityU = program.findUniform("uQuality");
      if (qualityU != null) {
         qualityU.method_1251((float)module.getMirageQuality());
      }

   }

   public void updateFramebuffers(class_276 withHand, class_276 beforeHand) {
      this.withHandFbo = withHand;
      this.beforeHandFbo = beforeHand;
   }

   public void updateBlurParams(float radius, float threshold) {
      this.blurRadius = radius;
      this.threshold = threshold;
   }

   public void updateColor(float r, float g, float b, float a) {
      this.tintColor[0] = r;
      this.tintColor[1] = g;
      this.tintColor[2] = b;
      this.tintColor[3] = a;
   }

   private void K3f38f08e1ddca9a2d5(K745964d54ca1b172b8 program) {
      K79e0f6624e5ddb7ac3 module = K79e0f6624e5ddb7ac3.getInstance();
      class_284 tintU = program.findUniform("uTint");
      if (tintU != null) {
         tintU.method_35657(this.tintColor[0], this.tintColor[1], this.tintColor[2], module.getFillAlpha());
      }

      class_284 shadingU = program.findUniform("uShading");
      if (shadingU != null) {
         shadingU.method_1251(module.getShadingStrength());
      }

   }

   private void K9ddfc96a08ae022a6a(K745964d54ca1b172b8 program) {
      K79e0f6624e5ddb7ac3 module = K79e0f6624e5ddb7ac3.getInstance();
      class_284 color1U = program.findUniform("uColor1");
      if (color1U != null) {
         color1U.method_35657(this.tintColor[0], this.tintColor[1], this.tintColor[2], 1.0F);
      }

      class_284 color2U = program.findUniform("uColor2");
      if (color2U != null) {
         float[] color2 = module.getGlowColor2Array();
         color2U.method_35657(color2[0], color2[1], color2[2], 1.0F);
      }

      class_284 exposureU = program.findUniform("uExposure");
      if (exposureU != null) {
         exposureU.method_1251(module.getGlowExposure());
      }

   }

   private void Ka5f4b868aa06817729(K745964d54ca1b172b8 program) {
      K79e0f6624e5ddb7ac3 module = K79e0f6624e5ddb7ac3.getInstance();
      class_284 mixU = program.findUniform("uMixFactor");
      if (mixU != null) {
         mixU.method_1251(module.getMirrorMixFactor());
      }

      class_284 alphaU = program.findUniform("uAlpha");
      if (alphaU != null) {
         alphaU.method_1251(module.getMirrorAlpha());
      }

   }

   private void K79a8a967f50d4c22ca(K745964d54ca1b172b8 program) {
      K79e0f6624e5ddb7ac3 module = K79e0f6624e5ddb7ac3.getInstance();
      class_284 colorU = program.findUniform("uColor");
      if (colorU != null) {
         colorU.method_35657(this.tintColor[0], this.tintColor[1], this.tintColor[2], 1.0F);
      }

      class_284 widthU = program.findUniform("uWidth");
      if (widthU != null) {
         widthU.method_1251(module.getOutlineWidth());
      }

      class_284 alphaU = program.findUniform("uAlpha");
      if (alphaU != null) {
         alphaU.method_1251(module.getOutlineAlpha());
      }

   }

   private void K8c4968e08665558a24(K745964d54ca1b172b8 program) {
      K79e0f6624e5ddb7ac3 module = K79e0f6624e5ddb7ac3.getInstance();
      class_284 timeU = program.findUniform("uTime");
      if (timeU != null) {
         float time = (float)(System.currentTimeMillis() % 100000L) / 1000.0F;
         timeU.method_1251(time);
      }

      class_284 heightU = program.findUniform("uHeight");
      if (heightU != null) {
         heightU.method_1251(module.getFireHeight());
      }

      class_284 speedU = program.findUniform("uSpeed");
      if (speedU != null) {
         speedU.method_1251(module.getFireSpeed());
      }

      class_284 intensityU = program.findUniform("uIntensity");
      if (intensityU != null) {
         intensityU.method_1251(module.getFireIntensity());
      }

      class_284 windU = program.findUniform("uWind");
      if (windU != null) {
         windU.method_1251(module.getFireWind());
      }

      class_284 waveU = program.findUniform("uWave");
      if (waveU != null) {
         waveU.method_1251(module.getFireWave());
      }

      class_284 color1U = program.findUniform("uColor1");
      if (color1U != null) {
         float[] color1 = module.getFireColorArray();
         color1U.method_35657(color1[0], color1[1], color1[2], 1.0F);
      }

      class_284 color2U = program.findUniform("uColor2");
      if (color2U != null) {
         float[] color2 = module.getFireColor2Array();
         color2U.method_35657(color2[0], color2[1], color2[2], 1.0F);
      }

   }
}
