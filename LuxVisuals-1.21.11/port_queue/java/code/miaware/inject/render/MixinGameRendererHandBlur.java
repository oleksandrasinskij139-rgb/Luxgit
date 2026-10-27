package code.miaware.inject.render;

import code.miaware.client.features.modules.render.K79e0f6624e5ddb7ac3;
import code.miaware.client.util.render.Kefb5586baca5cdbb77;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_6367;
import net.minecraft.class_757;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_757.class})
public class MixinGameRendererHandBlur {
   @Shadow
   @Final
   private class_310 field_4015;
   @Unique
   private class_276 lux$beforeHandFbo;
   @Unique
   private class_276 lux$afterHandFbo;
   @Unique
   private Kefb5586baca5cdbb77 lux$handBlurRenderer;

   @Inject(
      method = {"renderHand"},
      at = {@At("HEAD")}
   )
   private void captureBeforeHand(class_4184 camera, float tickDelta, Matrix4f matrix4f, CallbackInfo ci) {
      if (K79e0f6624e5ddb7ac3.getInstance().isEnabled()) {
         class_310 mc = class_310.method_1551();
         if (mc.field_1687 != null) {
            int width = mc.method_22683().method_4489();
            int height = mc.method_22683().method_4506();
            if (this.lux$beforeHandFbo == null || this.lux$beforeHandFbo.field_1482 != width || this.lux$beforeHandFbo.field_1481 != height) {
               if (this.lux$beforeHandFbo != null) {
                  this.lux$beforeHandFbo.method_1238();
               }

               this.lux$beforeHandFbo = new class_6367(width, height, true);
            }

            class_276 mainFbo = mc.method_1522();
            mainFbo.method_35610();
            this.lux$beforeHandFbo.method_1235(false);
            mainFbo.method_1237(width, height);
            mainFbo.method_1242();
            mainFbo.method_1235(true);
         }
      }
   }

   @Inject(
      method = {"renderHand"},
      at = {@At("RETURN")}
   )
   private void captureAfterHand(class_4184 camera, float tickDelta, Matrix4f matrix4f, CallbackInfo ci) {
      if (K79e0f6624e5ddb7ac3.getInstance().isEnabled()) {
         class_310 mc = class_310.method_1551();
         if (mc.field_1687 != null) {
            int width = mc.method_22683().method_4489();
            int height = mc.method_22683().method_4506();
            if (this.lux$afterHandFbo == null || this.lux$afterHandFbo.field_1482 != width || this.lux$afterHandFbo.field_1481 != height) {
               if (this.lux$afterHandFbo != null) {
                  this.lux$afterHandFbo.method_1238();
               }

               this.lux$afterHandFbo = new class_6367(width, height, true);
            }

            class_276 mainFbo = mc.method_1522();
            mainFbo.method_35610();
            this.lux$afterHandFbo.method_1235(false);
            mainFbo.method_1237(width, height);
            mainFbo.method_1242();
            mainFbo.method_1235(true);
            this.lux$applyHandBlur(width, height);
         }
      }
   }

   @Unique
   private void lux$applyHandBlur(int width, int height) {
      if (this.lux$beforeHandFbo != null && this.lux$afterHandFbo != null) {
         if (this.lux$beforeHandFbo.method_30277() != 0 && this.lux$afterHandFbo.method_30277() != 0) {
            class_310 mc = class_310.method_1551();
            mc.method_1522().method_1235(true);
            K79e0f6624e5ddb7ac3 module = K79e0f6624e5ddb7ac3.getInstance();
            float[] tintColor = module.getEffectColorArray();
            float blurRadius = module.getBlurRadius();
            float threshold = module.getThreshold();
            if (this.lux$handBlurRenderer == null) {
               this.lux$handBlurRenderer = new Kefb5586baca5cdbb77(this.lux$afterHandFbo, this.lux$beforeHandFbo, blurRadius, threshold, tintColor, width, height);
            } else {
               this.lux$handBlurRenderer.updateFramebuffers(this.lux$afterHandFbo, this.lux$beforeHandFbo);
               this.lux$handBlurRenderer.updateBlurParams(blurRadius, threshold);
               this.lux$handBlurRenderer.updateColor(tintColor[0], tintColor[1], tintColor[2], tintColor[3]);
            }

            Matrix4f matrix = new Matrix4f();
            this.lux$handBlurRenderer.render(matrix);
         }
      }
   }
}
