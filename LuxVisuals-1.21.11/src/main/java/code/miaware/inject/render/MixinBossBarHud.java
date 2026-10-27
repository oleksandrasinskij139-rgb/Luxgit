package code.miaware.inject.render;

import code.miaware.client.features.modules.render.K3875e4bdc53cc1074e;
import net.minecraft.class_310;
import net.minecraft.class_337;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_337.class})
public class MixinBossBarHud {
   @Inject(
      method = {"render"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onRender(CallbackInfo ci) {
      if (class_310.method_1551().field_1724 != null) {
         if (K3875e4bdc53cc1074e.getInstance().isBossBar()) {
            ci.cancel();
         }

      }
   }
}
