package ru.phantom.mixin.minecraft.client;

import II1II1II1II1II1II1II1II1.l8_I7.i_IIi11l1_i_1I1lIilIi1iI__I_I;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_746.class})
public class LockSlotDropMixin {
   @Inject(
      method = {"method_7290"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void phantom$blockSelectedDrop(boolean entireStack, CallbackInfoReturnable<Boolean> cir) {
      i_IIi11l1_i_1I1lIilIi1iI__I_I module = i_IIi11l1_i_1I1lIilIi1iI__I_I.II__I_lii1illliI11l1liIIIilIII();
      if (module != null && module.blocksSelectedDrop()) {
         cir.setReturnValue(false);
      }

   }
}
