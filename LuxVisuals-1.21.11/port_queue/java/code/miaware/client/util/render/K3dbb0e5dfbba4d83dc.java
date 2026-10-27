package code.miaware.client.util.render;

import code.miaware.api.utils.render.shader.K745964d54ca1b172b8;
import code.miaware.client.features.modules.render.Kcc60133648b8ff2d03;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_284;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_5944;
import net.minecraft.class_293.class_5596;

public class K3dbb0e5dfbba4d83dc {
   private static K745964d54ca1b172b8 skyWaterProgram;
   private static K745964d54ca1b172b8 skyCausticProgram;
   private static K745964d54ca1b172b8 skyNebulaProgram;
   private static K745964d54ca1b172b8 skyNebula2Program;
   private static long startTime = -1L;

   public static void init() {
      skyWaterProgram = new K745964d54ca1b172b8(class_2960.method_60655("luxvisuals", "core/sky_water"), class_290.field_1592);
      skyCausticProgram = new K745964d54ca1b172b8(class_2960.method_60655("luxvisuals", "core/sky_caustic"), class_290.field_1592);
      skyNebulaProgram = new K745964d54ca1b172b8(class_2960.method_60655("luxvisuals", "core/sky_nebula"), class_290.field_1592);
      skyNebula2Program = new K745964d54ca1b172b8(class_2960.method_60655("luxvisuals", "core/sky_nebula2"), class_290.field_1592);
      startTime = System.currentTimeMillis();
   }

   public static void render() {
      Kcc60133648b8ff2d03 module = Kcc60133648b8ff2d03.getInstance();
      if (module.isEnabled() && module.isSkyShaderEnabled()) {
         class_310 mc = class_310.method_1551();
         if (mc.field_1687 != null && mc.field_1724 != null) {
            String mode = module.getSkyShaderMode();
            K745964d54ca1b172b8 program = null;
            if (mode.equals("Water")) {
               program = skyWaterProgram;
            } else if (mode.equals("Caustic")) {
               program = skyCausticProgram;
            } else if (mode.equals("Nebula")) {
               program = skyNebulaProgram;
            } else if (mode.equals("Nebula2")) {
               program = skyNebula2Program;
            }

            if (program != null) {
               RenderSystem.enableBlend();
               RenderSystem.defaultBlendFunc();
               RenderSystem.disableCull();
               RenderSystem.depthMask(false);
               RenderSystem.enableDepthTest();
               RenderSystem.depthFunc(514);
               class_5944 shader = program.use();
               if (shader == null) {
                  RenderSystem.depthFunc(515);
                  RenderSystem.enableDepthTest();
                  RenderSystem.depthMask(true);
                  RenderSystem.enableCull();
               } else {
                  K384e623124e64803d7(program, module);
                  class_287 builder = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1592);
                  builder.method_22912(-1.0F, -1.0F, 1.0F);
                  builder.method_22912(1.0F, -1.0F, 1.0F);
                  builder.method_22912(1.0F, 1.0F, 1.0F);
                  builder.method_22912(-1.0F, -1.0F, 1.0F);
                  builder.method_22912(1.0F, 1.0F, 1.0F);
                  builder.method_22912(-1.0F, 1.0F, 1.0F);
                  class_286.method_43433(builder.method_60800());
                  RenderSystem.depthFunc(515);
                  RenderSystem.enableDepthTest();
                  RenderSystem.depthMask(true);
                  RenderSystem.enableCull();
               }
            }
         }
      }
   }

   private static void K384e623124e64803d7(K745964d54ca1b172b8 program, Kcc60133648b8ff2d03 module) {
      class_310 mc = class_310.method_1551();
      float time = (float)(System.currentTimeMillis() - startTime) / 1000.0F;
      float fw = (float)mc.method_22683().method_4489();
      float fh = (float)mc.method_22683().method_4506();
      class_284 resU = program.findUniform("uResolution");
      if (resU != null) {
         resU.method_1255(fw, fh);
      }

      class_284 timeU = program.findUniform("uTime");
      if (timeU != null) {
         timeU.method_1251(time);
      }

      class_284 fovU = program.findUniform("uFov");
      if (fovU != null) {
         fovU.method_1251((float)(Integer)mc.field_1690.method_41808().method_41753());
      }

      Color color = module.getSkyShaderColor();
      class_284 colorU = program.findUniform("uColor");
      if (colorU != null) {
         colorU.method_1249((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F);
      }

      class_284 alphaU = program.findUniform("uAlpha");
      if (alphaU != null) {
         alphaU.method_1251(module.getSkyShaderAlpha());
      }

      class_284 speedU = program.findUniform("uSpeed");
      if (speedU != null) {
         speedU.method_1251(module.getSkyShaderSpeed());
      }

      class_284 scaleU = program.findUniform("uScale");
      if (scaleU != null) {
         scaleU.method_1251(module.getSkyShaderScale());
      }

      class_284 intensityU = program.findUniform("uIntensity");
      if (intensityU != null) {
         intensityU.method_1251(module.getSkyShaderIntensity());
      }

      class_4184 camera = mc.field_1773.method_19418();
      float yawRad = (float)Math.toRadians((double)(-camera.method_19330()));
      float pitchRad = (float)Math.toRadians((double)camera.method_19329());
      class_284 camDirU = program.findUniform("uCameraDir");
      if (camDirU != null) {
         camDirU.method_1255(yawRad, pitchRad);
      }

      float r = (float)color.getRed() / 255.0F;
      float g = (float)color.getGreen() / 255.0F;
      float b = (float)color.getBlue() / 255.0F;
      class_284 zenithU = program.findUniform("uSkyZenith");
      if (zenithU != null) {
         zenithU.method_1249(r * 0.12F, g * 0.12F, b * 0.12F);
      }

      class_284 horizonU = program.findUniform("uSkyHorizon");
      if (horizonU != null) {
         horizonU.method_1249(r * 0.28F, g * 0.28F, b * 0.28F);
      }

      class_284 neb1U = program.findUniform("uNebColor1");
      if (neb1U != null) {
         neb1U.method_1249(r * 0.55F + 0.18F, g * 0.04F, b * 1.8F);
      }

      class_284 neb2U = program.findUniform("uNebColor2");
      if (neb2U != null) {
         neb2U.method_1249(r * 0.03F, g * 0.35F, b * 1.2F);
      }

      class_284 nebIntU = program.findUniform("uNebIntensity");
      if (nebIntU != null) {
         nebIntU.method_1251(module.getSkyShaderIntensity() * 75.0F);
      }

      class_284 starU = program.findUniform("uStarColor");
      if (starU != null) {
         starU.method_1249(0.9F + r * 0.1F, 0.55F + g * 0.45F, 1.0F);
      }

   }
}
