package code.miaware.client.util.render;

import code.miaware.api.utils.render.shader.K745964d54ca1b172b8;
import code.miaware.client.features.modules.render.K1b30271ad5c4a4666f;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_276;
import net.minecraft.class_284;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_5944;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

public class K2516fc881f143ca1ea {
   private static K745964d54ca1b172b8 playerGlassProgram;
   private static K745964d54ca1b172b8 playerMirageProgram;
   private final class_276 withPlayerFbo;
   private final class_276 beforePlayerFbo;
   private final float[] tintColor;
   private final int screenW;
   private final int screenH;

   public static void init() {
      playerGlassProgram = new K745964d54ca1b172b8(class_2960.method_60655("luxvisuals", "core/hand_glass"), class_290.field_1576);
      playerMirageProgram = new K745964d54ca1b172b8(class_2960.method_60655("luxvisuals", "core/hand_mirage"), class_290.field_1576);
   }

   public K2516fc881f143ca1ea(class_276 withPlayerFbo, class_276 beforePlayerFbo, float[] tintColor, int screenW, int screenH) {
      this.withPlayerFbo = withPlayerFbo;
      this.beforePlayerFbo = beforePlayerFbo;
      this.tintColor = tintColor;
      this.screenW = screenW;
      this.screenH = screenH;
   }

   public void render(Matrix4f matrix) {
      String mode = K1b30271ad5c4a4666f.getInstance().getShaderMode();
      if (!mode.equals("None")) {
         K745964d54ca1b172b8 program = null;
         if (mode.equals("Glass")) {
            program = playerGlassProgram;
         } else if (mode.equals("Mirage")) {
            program = playerMirageProgram;
         }

         if (program != null) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableCull();
            RenderSystem.disableDepthTest();
            RenderSystem.setShaderTexture(0, this.withPlayerFbo.method_30277());
            RenderSystem.setShaderTexture(1, this.beforePlayerFbo.method_30277());
            class_5944 shader = program.use();
            if (shader == null) {
               RenderSystem.enableDepthTest();
               RenderSystem.enableCull();
               RenderSystem.disableBlend();
            } else {
               if (mode.equals("Glass")) {
                  this.Kd1a91b925b1b1f8081(program);
               } else if (mode.equals("Mirage")) {
                  this.K31148cb773368323bd(program);
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
   }

   private void Kd1a91b925b1b1f8081(K745964d54ca1b172b8 program) {
      K1b30271ad5c4a4666f module = K1b30271ad5c4a4666f.getInstance();
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

   private void K31148cb773368323bd(K745964d54ca1b172b8 program) {
      K1b30271ad5c4a4666f module = K1b30271ad5c4a4666f.getInstance();
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
}
