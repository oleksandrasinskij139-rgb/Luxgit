package ru.phantom.mixin.minecraft.client.input;

import II1II1II1II1II1II1II1II1.l8_I7.II111_iIl_IlI1I_i_IiIili_i_iI;
import II1II1II1II1II1II1II1II1.l8_I7.IlII1li1IIlil_1_iI__liii;
import net.minecraft.class_743;
import net.minecraft.class_744;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_743.class})
public class ShiftTapInputMixin extends class_744 {
   @Inject(
      method = {"method_3129"},
      at = {@At("RETURN")}
   )
   private void phantom$applyShiftTap(CallbackInfo ci) {
      if (II111_iIl_IlI1I_i_IiIili_i_iI.mc.field_1724 != null && II111_iIl_IlI1I_i_IiIili_i_iI.mc.field_1724.field_3913 == this) {
         IlII1li1IIlil_1_iI__liii module = IlII1li1IIlil_1_iI__liii.ili1_1l1l1ili_II1Ii_11();
         if (module != null && module.isEnable()) {
            this.field_54155 = module.applyInput(this.field_54155);
         }

      }
   }
}
