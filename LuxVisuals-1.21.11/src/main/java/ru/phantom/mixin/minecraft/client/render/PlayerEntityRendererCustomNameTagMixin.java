package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I0_i9.i1lilIii_i1I11iilIi_iii1__l;
import II1II1II1II1II1II1II1II1.I5_i4.i_i_III11iI_IlII11ll_1__l11;
import II1II1II1II1II1II1II1II1.I9_i8.I_1ii1Ii11lI1I_1lliI_iI;
import II1II1II1II1II1II1II1II1.l8_I7.iI_iliili1I__l1IIli1liill_;
import II1II1II1II1II1II1II1II1.l8_I7.lI1__l1_l___II_1lIIl1Ii;
import net.minecraft.class_10055;
import net.minecraft.class_1007;
import net.minecraft.class_1294;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_5617;
import net.minecraft.class_742;
import net.minecraft.class_9064;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1007.class})
public class PlayerEntityRendererCustomNameTagMixin {
   @Inject(
      method = {"<init>"},
      at = {@At("RETURN")}
   )
   private void phantom$addCosmeticsFeature(class_5617.class_5618 ctx, boolean slim, CallbackInfo ci) {
      ((class_1007)this).method_4046(new i1lilIii_i1I11iilIi_iii1__l((class_1007)this));
   }

   @Inject(
      method = {"method_62604(Lnet/minecraft/class_742;Lnet/minecraft/class_10055;F)V"},
      at = {@At("TAIL")}
   )
   private void phantom$updateInvisibleNameTag(class_742 player, class_10055 state, float tickDelta, CallbackInfo ci) {
      if (state instanceof lI1__l1_l___II_1lIIl1Ii ext) {
         class_310 client = class_310.method_1551();
         boolean isLocal = client.field_1724 != null && player != null && (player == client.field_1724 || player.method_5628() == client.field_1724.method_5628() || player.method_5667() != null && player.method_5667().equals(client.field_1724.method_5667()));
         ext.phantom$setLocalPlayer(isLocal);
      }

      if (i_i_III11iI_IlII11ll_1__l11.I1liIiiIiIiIil1lIi__llI_1_1i1__() && I_1ii1Ii11lI1I_1lliI_iI.II1I_i11_1__llIl_iili_Il1l_li(state.field_53332)) {
         state.field_53543 = false;
         state.field_53544 = false;
         state.field_53530 = false;
         state.field_53531 = false;
         state.field_53545 = false;
         state.field_53546 = false;
         state.field_53532 = false;
      }

      if (iI_iliili1I__l1IIli1liill_.IIl1ll111llI1I1l1l_I__I_11llll_() && (player.method_5767() || state.field_53333 || player.method_6059(class_1294.field_5905))) {
         state.field_53333 = true;
         state.field_53461 = false;
         state.field_53337 = player.method_5476();
         if (state.field_53338 == null) {
            class_243 pos = player.method_56072().method_55675(class_9064.field_47745, 0, player.method_61415(tickDelta));
            state.field_53338 = pos != null ? pos : new class_243((double)0.0F, (double)player.method_17682(), (double)0.0F);
         }
      }

   }
}
