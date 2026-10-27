package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.l8_I7.I_1i1l__i1iill_1Iii1ii_Iil;
import net.minecraft.class_2561;
import net.minecraft.class_355;
import net.minecraft.class_640;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_355.class})
public class PlayerListHudNameProtectMixin {
   @Inject(
      method = {"method_1918"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void Phantom$protectPlayerListName(class_640 entry, CallbackInfoReturnable<class_2561> cir) {
      cir.setReturnValue(I_1i1l__i1iill_1Iii1ii_Iil.l1liI__i_il_1i_l_iIIlI_((class_2561)cir.getReturnValue()));
   }
}
