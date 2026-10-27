package code.miaware.inject.other;

import code.miaware.client.features.modules.other.K3d47d22da0f48eceb7;
import net.minecraft.class_1735;
import net.minecraft.class_332;
import net.minecraft.class_465;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_465.class})
public class MixinHandledScreenAH {
   @Inject(
      method = {"drawSlot"},
      at = {@At("HEAD")}
   )
   private void onDrawSlot(class_332 context, class_1735 slot, CallbackInfo ci) {
      K3d47d22da0f48eceb7.getInstance().renderSlotHighlight(context, (class_465)this, slot);
   }
}
