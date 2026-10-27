package code.miaware.inject.client;

import code.miaware.client.features.modules.other.K03de12e3617fa217aa;
import net.minecraft.class_1102;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1102.class})
public class MixinSoundSystem {
   @Inject(
      method = {"getVolume"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void modifyVolume(CallbackInfoReturnable<Float> cir) {
      try {
         K03de12e3617fa217aa controller = K03de12e3617fa217aa.getInstance();
         if (controller == null || !controller.isEnabled()) {
            return;
         }

         class_1102 instance = (class_1102)this;
         String soundId = instance.method_4775().toString();
         float originalVolume = (Float)cir.getReturnValue();
         System.out.println("[MixinSoundSystem] Sound: " + soundId + " | Volume: " + originalVolume);
         float modifiedVolume = controller.modifyVolume(soundId, originalVolume);
         if (Math.abs(modifiedVolume - originalVolume) > 0.001F) {
            System.out.println("[MixinSoundSystem] Modified: " + originalVolume + " -> " + modifiedVolume);
            cir.setReturnValue(modifiedVolume);
         }
      } catch (Exception e) {
         System.out.println("[MixinSoundSystem] Error: " + e.getMessage());
         e.printStackTrace();
      }

   }
}
