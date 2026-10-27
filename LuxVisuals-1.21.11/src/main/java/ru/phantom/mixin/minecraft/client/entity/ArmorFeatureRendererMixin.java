package ru.phantom.mixin.minecraft.client.entity;

import II1II1II1II1II1II1II1II1.I0_i9.i_1IIil1__11iilII_ilI_lll1i1ii;
import net.minecraft.class_10034;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_970;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_970.class})
public class ArmorFeatureRendererMixin {
   @Inject(
      method = {"method_17157(Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;ILnet/minecraft/class_10034;FF)V"},
      at = {@At("HEAD")}
   )
   private void phantomvisuals$captureArmorHurt(class_4587 matrices, class_4597 vertexConsumers, int light, class_10034 state, float limbAngle, float limbDistance, CallbackInfo ci) {
      i_1IIil1__11iilII_ilI_lll1i1ii.I_Illli1ili1IllIl_1IlI_l_ll(state.field_53460);
   }

   @Inject(
      method = {"method_17157(Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;ILnet/minecraft/class_10034;FF)V"},
      at = {@At("RETURN")}
   )
   private void phantomvisuals$clearArmorHurt(class_4587 matrices, class_4597 vertexConsumers, int light, class_10034 state, float limbAngle, float limbDistance, CallbackInfo ci) {
      i_1IIil1__11iilII_ilI_lll1i1ii.I_Illli1ili1IllIl_1IlI_l_ll(false);
   }
}
