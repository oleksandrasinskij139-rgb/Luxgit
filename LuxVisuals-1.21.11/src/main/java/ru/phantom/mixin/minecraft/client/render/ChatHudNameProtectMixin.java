package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.l8_I7.I_1i1l__i1iill_1Iii1ii_Iil;
import net.minecraft.class_2561;
import net.minecraft.class_338;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin({class_338.class})
public class ChatHudNameProtectMixin {
   @ModifyVariable(
      method = {"method_1812(Lnet/minecraft/class_2561;)V"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private class_2561 Phantom$protectChatMessage(class_2561 message) {
      return I_1i1l__i1iill_1Iii1ii_Iil.l1liI__i_il_1i_l_iIIlI_(message);
   }
}
