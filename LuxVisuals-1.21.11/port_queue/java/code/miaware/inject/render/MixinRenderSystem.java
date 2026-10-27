package code.miaware.inject.render;

import code.miaware.client.features.modules.render.Kcc60133648b8ff2d03;
import com.mojang.blaze3d.systems.RenderSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({RenderSystem.class})
public class MixinRenderSystem {
   @Inject(
      method = {"clearColor"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void fogColor(float red, float green, float blue, float alpha, CallbackInfo ci) {
      if (Kcc60133648b8ff2d03.getInstance().applyBackgroundColor()) {
         ci.cancel();
      }

   }
}
