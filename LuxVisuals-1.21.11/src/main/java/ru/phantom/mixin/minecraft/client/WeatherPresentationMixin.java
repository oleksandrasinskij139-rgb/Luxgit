package ru.phantom.mixin.minecraft.client;

import II1II1II1II1II1II1II1II1.l8_I7.II111_iIl_IlI1I_i_IiIili_i_iI;
import II1II1II1II1II1II1II1II1.l8_I7.iIIlIl_l_I_1II1l1_ii1il111__;
import net.minecraft.class_1937;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1937.class})
public class WeatherPresentationMixin {
   @Inject(
      method = {"method_8430"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void phantom$rain(float delta, CallbackInfoReturnable<Float> cir) {
      if (this == II111_iIl_IlI1I_i_IiIili_i_iI.mc.field_1687) {
         iIIlIl_l_I_1II1l1_ii1il111__ module = iIIlIl_l_I_1II1l1_ii1il111__.l_1lI__11iii111l_1_11il1II1__();
         if (module != null) {
            cir.setReturnValue(module.rain((Float)cir.getReturnValue()));
         }

      }
   }

   @Inject(
      method = {"method_8478"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void phantom$thunder(float delta, CallbackInfoReturnable<Float> cir) {
      if (this == II111_iIl_IlI1I_i_IiIili_i_iI.mc.field_1687) {
         iIIlIl_l_I_1II1l1_ii1il111__ module = iIIlIl_l_I_1II1l1_ii1il111__.l_1lI__11iii111l_1_11il1II1__();
         if (module != null) {
            cir.setReturnValue(module.thunder((Float)cir.getReturnValue()));
         }

      }
   }
}
