package ru.phantom.mixin.minecraft.client.gui.widget;

import II1II1II1II1II1II1II1II1.I0_i9.Ii1_l_ll_II1__l__Ill__ll11il;
import net.minecraft.class_332;
import net.minecraft.class_350;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_350.class})
public abstract class EntryListWidgetMixin {
   @Inject(
      method = {"method_48579(Lnet/minecraft/class_332;IIF)V"},
      at = {@At("HEAD")}
   )
   private void phantom$onRenderWidget(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      Ii1_l_ll_II1__l__Ill__ll11il.l__l_I1I_lli__I1Ii1I1l_li_1ll_((class_350)this, delta);
   }
}
