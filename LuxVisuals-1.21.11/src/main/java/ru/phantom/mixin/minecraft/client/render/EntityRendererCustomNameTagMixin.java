package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I0_i9.l1lIIlI_lII_IliI1I1l1_I_I;
import net.minecraft.class_10017;
import net.minecraft.class_2561;
import net.minecraft.class_327;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_897;
import net.minecraft.class_898;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_897.class})
public abstract class EntityRendererCustomNameTagMixin {
   @Shadow
   @Final
   protected class_898 field_4676;

   @Shadow
   protected abstract class_327 method_3932();

   @Inject(
      method = {"method_3926"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void phantom$renderCustomNameTag(class_10017 state, class_2561 text, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
      if (l1lIIlI_lII_IliI1I1l1_I_I.l_IIiil_l1ii_iiii1_ilIi(state, text, matrices, vertexConsumers, light, this.method_3932(), this.field_4676.method_24197())) {
         ci.cancel();
      }

   }
}
