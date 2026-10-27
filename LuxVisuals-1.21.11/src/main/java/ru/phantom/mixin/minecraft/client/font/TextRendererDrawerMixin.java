package ru.phantom.mixin.minecraft.client.font;

import II1II1II1II1II1II1II1II1.I5_i4.i_i_III11iI_IlII11ll_1__l11;
import net.minecraft.class_1921;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
   targets = {"net/minecraft/class_327$class_5232"}
)
public abstract class TextRendererDrawerMixin {
   @Unique
   private class_1921 phantom$lastLayer;
   @Unique
   private class_4588 phantom$lastConsumer;

   @Inject(
      method = {"method_65050"},
      at = {@At("HEAD")}
   )
   private void phantom$resetCachedBuffer(CallbackInfo ci) {
      this.phantom$lastLayer = null;
      this.phantom$lastConsumer = null;
   }

   @Redirect(
      method = {"method_65050"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_4597;getBuffer(Lnet/minecraft/class_1921;)Lnet/minecraft/class_4588;"
)
   )
   private class_4588 phantom$cachedGetBuffer(class_4597 provider, class_1921 layer) {
      if (i_i_III11iI_IlII11ll_1__l11.ilIIiiII1l1iI_lliilI1I()) {
         if (this.phantom$lastLayer == layer && this.phantom$lastConsumer != null) {
            return this.phantom$lastConsumer;
         } else {
            this.phantom$lastLayer = layer;
            this.phantom$lastConsumer = provider.getBuffer(layer);
            return this.phantom$lastConsumer;
         }
      } else {
         return provider.getBuffer(layer);
      }
   }
}
