package code.miaware.inject.input;

import code.miaware.api.event.events.client.K256ab2bf60c15ab260;
import code.miaware.api.event.events.client.K6449169e9fd0c18e12;
import code.miaware.api.system.backend.K8e471a90dc4db42e81;
import code.miaware.client.features.modules.other.K71265226ea4595dd6d;
import net.minecraft.class_309;
import net.minecraft.class_310;
import net.minecraft.class_3675.class_307;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_309.class})
public class MixinKeyboard {
   @Final
   @Shadow
   private class_310 field_1678;

   @Inject(
      method = {"onKey"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/option/InactivityFpsLimiter;onInput()V"
)}
   )
   public void keyPressHook(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
      if (K8e471a90dc4db42e81.player() != null) {
         K6449169e9fd0c18e12.getInstance().call(new K256ab2bf60c15ab260(key, action, false));
      }
   }

   @Inject(
      method = {"onKey"},
      at = {@At("HEAD")}
   )
   private void onKey(long window, int key, int scanCode, int action, int modifiers, CallbackInfo ci) {
      if (key != -1 && window == this.field_1678.method_22683().method_4490()) {
         K71265226ea4595dd6d creamyKeys = K71265226ea4595dd6d.getInstance();
         if (creamyKeys != null) {
            creamyKeys.handleInput(class_307.field_1668, key, action);
         }
      }

   }
}
