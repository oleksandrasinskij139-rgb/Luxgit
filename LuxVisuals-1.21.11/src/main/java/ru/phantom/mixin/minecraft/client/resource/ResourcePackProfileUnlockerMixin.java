package ru.phantom.mixin.minecraft.client.resource;

import II1II1II1II1II1II1II1II1.l8_I7.lI1I_Il11iI1ll_ll_i_li1I_II_;
import net.minecraft.class_3288;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_3288.class})
public abstract class ResourcePackProfileUnlockerMixin {
   @Inject(
      method = {"method_14465"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void phantom$unlockServerPackProfile(CallbackInfoReturnable<Boolean> cir) {
      if (lI1I_Il11iI1ll_ll_i_li1I_II_.IiIIl_1Iii_l1_i11iil__1I1_liI()) {
         cir.setReturnValue(Boolean.FALSE);
      }

   }
}
