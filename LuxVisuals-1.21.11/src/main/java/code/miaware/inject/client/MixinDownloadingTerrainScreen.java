package code.miaware.inject.client;

import code.miaware.client.features.modules.render.K7a3ade6f5e6840c330;
import net.minecraft.class_332;
import net.minecraft.class_434;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_434.class})
public abstract class MixinDownloadingTerrainScreen {
   @Inject(
      method = {"render"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$cinematicJourney(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      if (K7a3ade6f5e6840c330.getInstance().renderLoading(context, this)) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"close"},
      at = {@At("HEAD")}
   )
   private void lux$finishJourney(CallbackInfo ci) {
      K7a3ade6f5e6840c330.getInstance().finishLoading(this);
   }
}
