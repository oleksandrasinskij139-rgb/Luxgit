package code.miaware.inject.render;

import code.miaware.client.features.modules.movement.K0574a7e784c41d227d;
import net.minecraft.class_329;
import net.minecraft.class_332;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_329.class})
public class MixinInGameHudCrosshair {
   @Inject(
      method = {"renderCrosshair"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onRenderCrosshair(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      if (K0574a7e784c41d227d.getInstance().isEnabled()) {
         ci.cancel();
      }

   }
}
