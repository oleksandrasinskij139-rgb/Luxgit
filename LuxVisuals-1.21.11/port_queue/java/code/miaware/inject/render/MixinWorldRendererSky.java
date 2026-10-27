package code.miaware.inject.render;

import code.miaware.client.features.modules.render.K7f7d5fe93b117b66e5;
import code.miaware.client.features.modules.render.Kcc60133648b8ff2d03;
import net.minecraft.class_761;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_761.class})
public class MixinWorldRendererSky {
   @Inject(
      method = {"renderTargetBlockOutline"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$customBlockOutline(CallbackInfo ci) {
      if (K7f7d5fe93b117b66e5.getInstance().replacesVanillaOutline()) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"renderSky"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onRenderSkyCancel(CallbackInfo ci) {
      Kcc60133648b8ff2d03 module = Kcc60133648b8ff2d03.getInstance();
      if (module.isEnabled() && module.isSkyShaderEnabled()) {
         ci.cancel();
      }

   }
}
