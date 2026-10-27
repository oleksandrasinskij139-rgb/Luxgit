package code.miaware.inject.render;

import code.miaware.client.features.modules.render.K1b30271ad5c4a4666f;
import code.miaware.client.util.render.K2516fc881f143ca1ea;
import net.minecraft.class_10042;
import net.minecraft.class_10055;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_6367;
import net.minecraft.class_922;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_922.class})
public class MixinLivingEntityRendererPlayerShader {
   @Unique
   private class_276 lux$beforePlayerFbo;
   @Unique
   private class_276 lux$afterPlayerFbo;

   @Inject(
      method = {"render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At("HEAD")}
   )
   private void captureBeforePlayer(class_10042 state, class_4587 matrixStack, class_4597 vertexConsumerProvider, int light, CallbackInfo ci) {
      if (state instanceof class_10055) {
         if (K1b30271ad5c4a4666f.getInstance().isEnabled()) {
            if (!K1b30271ad5c4a4666f.getInstance().getShaderMode().equals("None")) {
               class_310 mc = class_310.method_1551();
               if (mc.field_1687 != null && mc.field_1724 != null) {
                  class_10055 playerState = (class_10055)state;
                  class_1297 entity = mc.field_1687.method_8469(playerState.field_53528);
                  if (entity instanceof class_1657) {
                     class_1657 playerEntity = (class_1657)entity;
                     if (!playerEntity.equals(mc.field_1724)) {
                        class_1657 targetPlayer = K1b30271ad5c4a4666f.getInstance().getShaderTarget();
                        if (targetPlayer != null && targetPlayer.equals(playerEntity)) {
                           float shaderAlpha = K1b30271ad5c4a4666f.getInstance().getShaderAlpha();
                           if (!(shaderAlpha <= 0.01F)) {
                              int width = mc.method_22683().method_4489();
                              int height = mc.method_22683().method_4506();
                              if (this.lux$beforePlayerFbo == null || this.lux$beforePlayerFbo.field_1482 != width || this.lux$beforePlayerFbo.field_1481 != height) {
                                 if (this.lux$beforePlayerFbo != null) {
                                    this.lux$beforePlayerFbo.method_1238();
                                 }

                                 this.lux$beforePlayerFbo = new class_6367(width, height, true);
                              }

                              class_276 mainFbo = mc.method_1522();
                              mainFbo.method_35610();
                              this.lux$beforePlayerFbo.method_1235(false);
                              mainFbo.method_1237(width, height);
                              mainFbo.method_1242();
                              mainFbo.method_1235(true);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Inject(
      method = {"render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At("TAIL")}
   )
   private void captureAfterPlayer(class_10042 state, class_4587 matrixStack, class_4597 vertexConsumerProvider, int light, CallbackInfo ci) {
      if (state instanceof class_10055) {
         if (K1b30271ad5c4a4666f.getInstance().isEnabled()) {
            if (!K1b30271ad5c4a4666f.getInstance().getShaderMode().equals("None")) {
               class_310 mc = class_310.method_1551();
               if (mc.field_1687 != null && mc.field_1724 != null) {
                  class_10055 playerState = (class_10055)state;
                  class_1297 entity = mc.field_1687.method_8469(playerState.field_53528);
                  if (entity instanceof class_1657) {
                     class_1657 playerEntity = (class_1657)entity;
                     if (!playerEntity.equals(mc.field_1724)) {
                        class_1657 targetPlayer = K1b30271ad5c4a4666f.getInstance().getShaderTarget();
                        if (targetPlayer != null && targetPlayer.equals(playerEntity)) {
                           float shaderAlpha = K1b30271ad5c4a4666f.getInstance().getShaderAlpha();
                           if (!(shaderAlpha <= 0.01F)) {
                              int width = mc.method_22683().method_4489();
                              int height = mc.method_22683().method_4506();
                              if (this.lux$afterPlayerFbo == null || this.lux$afterPlayerFbo.field_1482 != width || this.lux$afterPlayerFbo.field_1481 != height) {
                                 if (this.lux$afterPlayerFbo != null) {
                                    this.lux$afterPlayerFbo.method_1238();
                                 }

                                 this.lux$afterPlayerFbo = new class_6367(width, height, true);
                              }

                              class_276 mainFbo = mc.method_1522();
                              mainFbo.method_35610();
                              this.lux$afterPlayerFbo.method_1235(false);
                              mainFbo.method_1237(width, height);
                              mainFbo.method_1242();
                              mainFbo.method_1235(true);
                              this.lux$applyPlayerShader(width, height, shaderAlpha);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Unique
   private void lux$applyPlayerShader(int width, int height, float fadeAlpha) {
      if (this.lux$beforePlayerFbo != null && this.lux$afterPlayerFbo != null) {
         if (this.lux$beforePlayerFbo.method_30277() != 0 && this.lux$afterPlayerFbo.method_30277() != 0) {
            class_310 mc = class_310.method_1551();
            mc.method_1522().method_1235(true);
            K1b30271ad5c4a4666f module = K1b30271ad5c4a4666f.getInstance();
            float[] tintColor = module.getEffectColorArray();
            float[] fadedTintColor = new float[]{tintColor[0], tintColor[1], tintColor[2], tintColor[3] * fadeAlpha};
            K2516fc881f143ca1ea renderer = new K2516fc881f143ca1ea(this.lux$afterPlayerFbo, this.lux$beforePlayerFbo, fadedTintColor, width, height);
            Matrix4f matrix = new Matrix4f();
            renderer.render(matrix);
         }
      }
   }
}
