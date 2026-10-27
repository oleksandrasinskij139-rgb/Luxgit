package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I5_i4.i1I_i11_IiiIi1ililI1i_1lI;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_10442;
import net.minecraft.class_1799;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Environment(EnvType.CLIENT)
@Mixin({class_10442.class})
public abstract class ItemModelManagerMixin {
   @ModifyVariable(
      method = {"method_65598(Lnet/minecraft/class_10444;Lnet/minecraft/class_1799;Lnet/minecraft/class_811;ZLnet/minecraft/class_1937;Lnet/minecraft/class_1309;I)V"},
      at = @At("HEAD"),
      argsOnly = true,
      ordinal = 0
   )
   private class_1799 phantom$replaceRenderedSword(class_1799 stack) {
      i1I_i11_IiiIi1ililI1i_1lI customSword = i1I_i11_IiiIi1ililI1i_1lI.iIl11I1_il1i1__lll_11li();
      return customSword != null && customSword.isEnable() ? customSword.getRenderStack(stack) : stack;
   }
}
