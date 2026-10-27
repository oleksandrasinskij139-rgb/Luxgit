package ru.phantom.mixin.minecraft.client.network;

import II1II1II1II1II1II1II1II1.I1_i0.iI_iI_I1Ii_IiIIiII__11I___l_1Il;
import II1II1II1II1II1II1II1II1.I1_i0.lIllilIIi1liliii_11lIiI_l__i;
import II1II1II1II1II1II1II1II1.l8_I7.II111_iIl_IlI1I_i_IiIili_i_iI;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.phantom.Phantom;

@Mixin({class_746.class})
public class ClientPlayerEntityMixin implements II111_iIl_IlI1I_i_IiIili_i_iI {
   @Inject(
      method = {"method_5773"},
      at = {@At("HEAD")}
   )
   public void triggerTickEvent(CallbackInfo ci) {
      Phantom.getInstance().getEventBus().post(new lIllilIIi1liliii_11lIiI_l__i());
   }

   @Inject(
      method = {"method_5773"},
      at = {@At("RETURN")}
   )
   public void triggerTickEndEvent(CallbackInfo ci) {
      Phantom.getInstance().getEventBus().post(new iI_iI_I1Ii_IiIIiII__11I___l_1Il());
   }
}
