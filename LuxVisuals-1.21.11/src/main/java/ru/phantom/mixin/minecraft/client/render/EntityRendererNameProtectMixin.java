package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.l8_I7.I_1i1l__i1iill_1Iii1ii_Iil;
import net.minecraft.class_1297;
import net.minecraft.class_2561;
import net.minecraft.class_897;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin({class_897.class})
public class EntityRendererNameProtectMixin<T extends class_1297> {
   @ModifyVariable(
      method = {"method_3926"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private class_2561 Phantom$protectEntityLabel(class_2561 text) {
      return I_1i1l__i1iill_1Iii1ii_Iil.l1l_l1iI1i1_lI_1l_1_Ilii1III_() && text != null ? I_1i1l__i1iill_1Iii1ii_Iil.l1liI__i_il_1i_l_iIIlI_(text) : text;
   }
}
