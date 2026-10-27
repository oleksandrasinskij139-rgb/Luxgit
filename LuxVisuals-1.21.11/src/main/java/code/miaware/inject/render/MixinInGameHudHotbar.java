package code.miaware.inject.render;

import code.miaware.api.event.events.render.K0d93ffa2e6f8c5772e;
import code.miaware.api.event.events.render.Ke3aa62f5221bb20ffb;
import net.minecraft.class_329;
import net.minecraft.class_332;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_329.class})
public class MixinInGameHudHotbar {
   @Inject(
      method = {"renderHotbar"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onRenderHotbar(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      Ke3aa62f5221bb20ffb eventData = new Ke3aa62f5221bb20ffb(context, context.method_51448(), tickCounter.method_60637(false));
      K0d93ffa2e6f8c5772e.getInstance().call(eventData);
      if (K0d93ffa2e6f8c5772e.getInstance().isCancel()) {
         ci.cancel();
      }

   }
}
