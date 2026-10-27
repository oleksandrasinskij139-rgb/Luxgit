package code.miaware.inject.other;

import code.miaware.client.features.modules.render.K58d4d66f061a2a6fed;
import net.minecraft.class_1735;
import net.minecraft.class_332;
import net.minecraft.class_465;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_465.class})
public class MixinHandledScreenItemESP {
   @Inject(
      method = {"drawSlot"},
      at = {@At("RETURN")}
   )
   private void onDrawSlot(class_332 context, class_1735 slot, CallbackInfo ci) {
      K58d4d66f061a2a6fed.getInstance().renderSlotHighlight(context, (class_465)this, slot);
   }
}
