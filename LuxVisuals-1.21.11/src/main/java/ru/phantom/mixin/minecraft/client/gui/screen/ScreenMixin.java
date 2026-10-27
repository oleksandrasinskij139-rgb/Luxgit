package ru.phantom.mixin.minecraft.client.gui.screen;

import II1II1II1II1II1II1II1II1.l8_I7.l1_1Iill_11iIl_l1lliII1I1I_I;
import net.minecraft.class_332;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_437.class})
public class ScreenMixin {
   @Inject(
      method = {"method_47413"},
      at = {@At("RETURN")}
   )
   private void phantom$afterRenderWithTooltip(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      if (this instanceof l1_1Iill_11iIl_l1lliII1I1I_I anim) {
         if (anim.phantom$isTransformed()) {
            context.method_51448().method_22909();
            anim.phantom$setTransformed(false);
         }
      }

   }
}
