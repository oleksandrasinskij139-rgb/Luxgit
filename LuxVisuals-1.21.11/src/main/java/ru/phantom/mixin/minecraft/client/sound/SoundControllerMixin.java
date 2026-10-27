package ru.phantom.mixin.minecraft.client.sound;

import II1II1II1II1II1II1II1II1.l8_I7.liI_l1III111I___1iIi_l11li1;
import net.minecraft.class_1113;
import net.minecraft.class_1144;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1144.class})
public abstract class SoundControllerMixin {
   @Inject(
      method = {"method_4873(Lnet/minecraft/class_1113;)V"},
      at = {@At("HEAD")},
      cancellable = true,
      require = 0
   )
   private void phantom$onPlaySound(class_1113 soundInstance, CallbackInfo ci) {
      try {
         if (!liI_l1III111I___1iIi_l11li1.ilIIli1IIl_Il11l1i_ilii1iII(soundInstance)) {
            ci.cancel();
         }
      } catch (Throwable var4) {
      }

   }
}
