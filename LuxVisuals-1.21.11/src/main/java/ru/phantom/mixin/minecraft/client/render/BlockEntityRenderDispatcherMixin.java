package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I0_i9.iiIiIil1li_Iil__i1lI1l_;
import net.minecraft.class_2586;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_824;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_824.class})
public class BlockEntityRenderDispatcherMixin {
   @Inject(
      method = {"method_3555(Lnet/minecraft/class_2586;FLnet/minecraft/class_4587;Lnet/minecraft/class_4597;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private <E extends class_2586> void phantom$cullBlockEntity(E blockEntity, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, CallbackInfo ci) {
      if (iiIiIil1li_Iil__i1lI1l_.l_I_I11lIIilIilIll1l1ii1_lI_(blockEntity)) {
         ci.cancel();
      }

   }
}
