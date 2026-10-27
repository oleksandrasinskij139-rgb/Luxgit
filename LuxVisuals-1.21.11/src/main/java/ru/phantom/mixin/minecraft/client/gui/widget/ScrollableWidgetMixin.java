package ru.phantom.mixin.minecraft.client.gui.widget;

import II1II1II1II1II1II1II1II1.I0_i9.Ii1_l_ll_II1__l__Ill__ll11il;
import II1II1II1II1II1II1II1II1.l8_I7.Illi1i___i1_ll1ll__IiIl_1IlII;
import net.minecraft.class_7528;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_7528.class})
public abstract class ScrollableWidgetMixin implements Illi1i___i1_ll1ll__IiIl_1IlII {
   @Shadow
   private boolean field_39498;

   public boolean phantom$isScrollbarDragged() {
      return this.field_39498;
   }

   @Inject(
      method = {"method_44382"},
      at = {@At("HEAD")}
   )
   private void phantom$onSetScrollY(double scrollY, CallbackInfo ci) {
      if (!Ii1_l_ll_II1__l__Ill__ll11il.il_l_lIl1Ii_IIl1ilI1__1_1i1_()) {
         Ii1_l_ll_II1__l__Ill__ll11il.Ill_l_ililIil_Ii11l_iIii_I_Il1l((class_7528)this, scrollY);
      }

   }
}
