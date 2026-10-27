package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I5_i4.I_l_III_IIlliliiII_iII;
import II1II1II1II1II1II1II1II1.I5_i4.iiIIliIli1Il_I_i_I1iiil11;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4603;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_4603.class})
public class InGameOverlayRendererMixin {
   @Inject(
      method = {"method_23070"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void Phantom$cancelFireOverlay(class_4587 matrices, class_4597 vertexConsumers, CallbackInfo ci) {
      if (iiIIliIli1Il_I_i_I1iiil11.lii1I11_i_l1il_liIiI111ilil1_("Fire")) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"method_23069"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void Phantom$cancelUnderwaterOverlay(CallbackInfo ci) {
      I_l_III_IIlliliiII_iII noFluid = I_l_III_IIlliliiII_iII.IIl1i_Il_I__1ilIl__ll__I1();
      if (noFluid != null && noFluid.isEnable()) {
         ci.cancel();
      }

   }
}
