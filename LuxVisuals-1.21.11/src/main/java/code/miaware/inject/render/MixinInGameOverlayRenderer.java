package code.miaware.inject.render;

import code.miaware.client.features.modules.render.K3875e4bdc53cc1074e;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4603;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_4603.class})
public class MixinInGameOverlayRenderer {
   @Inject(
      method = {"renderFireOverlay"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void onRenderFireOverlay(class_4587 matrices, class_4597 vertexConsumers, CallbackInfo ci) {
      if (class_310.method_1551().field_1724 != null) {
         if (K3875e4bdc53cc1074e.getInstance().isFireOverlay()) {
            ci.cancel();
         }

      }
   }

   @Inject(
      method = {"renderUnderwaterOverlay"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void onRenderUnderwaterOverlay(class_310 client, class_4587 matrices, class_4597 vertexConsumers, CallbackInfo ci) {
      if (class_310.method_1551().field_1724 != null) {
         if (K3875e4bdc53cc1074e.getInstance().isWaterOverlay()) {
            ci.cancel();
         }

      }
   }
}
