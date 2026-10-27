package ru.phantom.mixin.minecraft.client;

import net.minecraft.class_1041;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1041.class})
public class WindowMixin {
   @Inject(
      method = {"method_4513()V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void suppressGlErrorSpam(CallbackInfo ci) {
      ci.cancel();
   }

   @Inject(
      method = {"method_4482(IJ)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void suppressGlfwErrorSpam(int error, long description, CallbackInfo ci) {
      ci.cancel();
   }
}
