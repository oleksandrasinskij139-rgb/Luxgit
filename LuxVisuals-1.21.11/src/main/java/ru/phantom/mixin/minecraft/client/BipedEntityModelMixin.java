package ru.phantom.mixin.minecraft.client;

import II1II1II1II1II1II1II1II1.I5_i4.iIli11_Ilili_iiIilIl_1Il1ii_I;
import net.minecraft.class_10034;
import net.minecraft.class_572;
import net.minecraft.class_630;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_572.class})
public abstract class BipedEntityModelMixin {
   @Inject(
      method = {"method_17087"},
      at = {@At("TAIL")}
   )
   private void waveAnimation(class_10034 state, CallbackInfo ci) {
      if (iIli11_Ilili_iiIilIl_1Il1ii_I.li___l_1IIlI1IIi_lIl1_1Ii1___1i() || iIli11_Ilili_iiIilIl_1Il1ii_I.I_1lIl1l1_1_i1iI_I_I1l_I() || iIli11_Ilili_iiIilIl_1Il1ii_I.l1__iiIlI___l_l_1_1ll1ililllIII() || iIli11_Ilili_iiIilIl_1Il1ii_I.i1iiil1l_1I_1Ii1IIl__II_I1l1i()) {
         class_572<?> model = (class_572)this;
         model.field_3401.field_3654 = iIli11_Ilili_iiIilIl_1Il1ii_I.IlIil1l_l_IiillIil_i_i;
         model.field_3401.field_3675 = iIli11_Ilili_iiIilIl_1Il1ii_I.I1iili_IlIi_II____1ilI_lIi1ii;
         model.field_3401.field_3674 = iIli11_Ilili_iiIilIl_1Il1ii_I.l_ii1IlI1IIiiIliI11lliI__1Ii;
         model.field_27433.field_3654 = iIli11_Ilili_iiIilIl_1Il1ii_I.IIi1lIlI_11_l_ii_I1li11IIi_l;
         model.field_27433.field_3675 = iIli11_Ilili_iiIilIl_1Il1ii_I.I_l1__ll1_I_1_l11I_1i__ilI_;
         model.field_27433.field_3674 = iIli11_Ilili_iiIilIl_1Il1ii_I.ii1iiilI_1liIIill__1iIi_Iii_lli;
         class_630 var10000 = model.field_3398;
         var10000.field_3654 += iIli11_Ilili_iiIilIl_1Il1ii_I.I__lI1l_1Ii11I1i_Ii_Ii_11l1__1;
         var10000 = model.field_3391;
         var10000.field_3654 += iIli11_Ilili_iiIilIl_1Il1ii_I.lI_Ill11_ll11l_I_ll_Illi1__l1ll;
      }
   }
}
