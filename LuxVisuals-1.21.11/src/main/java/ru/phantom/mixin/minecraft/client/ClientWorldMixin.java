package ru.phantom.mixin.minecraft.client;

import II1II1II1II1II1II1II1II1.I5_i4.ilI111Il11__1II1IiI1__I1i1i;
import net.minecraft.class_243;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_638.class})
public class ClientWorldMixin {
   @Inject(
      method = {"method_23777"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void changeSkyColor(class_243 cameraPos, float tickDelta, CallbackInfoReturnable<Integer> cir) {
      ilI111Il11__1II1IiI1__I1i1i ambience = ilI111Il11__1II1IiI1__I1i1i.iI1__1_l_11i1ll1l11i_11i__1l();
      if (ambience != null && ambience.shouldChangeSky()) {
         cir.setReturnValue(ambience.getSkyColorRgb());
      }

   }
}
