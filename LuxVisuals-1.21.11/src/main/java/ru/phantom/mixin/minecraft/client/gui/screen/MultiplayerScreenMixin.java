package ru.phantom.mixin.minecraft.client.gui.screen;

import II1II1II1II1II1II1II1II1.l8_I7.ilIl____1i__lI11_il11I1Il1i1lIl;
import net.minecraft.class_4185;
import net.minecraft.class_4267;
import net.minecraft.class_500;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_500.class})
public abstract class MultiplayerScreenMixin {
   @Shadow
   protected class_4267 field_3043;
   @Shadow
   private class_4185 field_3041;
   @Shadow
   private class_4185 field_3047;

   @Inject(
      method = {"method_20121"},
      at = {@At("TAIL")}
   )
   private void phantom$onUpdateButtonActivationStates(CallbackInfo ci) {
      if (this.field_3043 != null) {
         class_4267.class_504 selected = (class_4267.class_504)this.field_3043.method_25334();
         if (selected instanceof class_4267.class_4270) {
            class_4267.class_4270 serverEntry = (class_4267.class_4270)selected;
            if (ilIl____1i__lI11_il11I1Il1i1lIl.Illil_i1I1lll1lillilii_1llii(serverEntry.method_20133())) {
               if (this.field_3041 != null) {
                  this.field_3041.field_22763 = false;
               }

               if (this.field_3047 != null) {
                  this.field_3047.field_22763 = false;
               }
            }
         }
      }

   }

   @Inject(
      method = {"method_25404"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void phantom$onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
      if (this.field_3043 != null) {
         class_4267.class_504 selected = (class_4267.class_504)this.field_3043.method_25334();
         if (selected instanceof class_4267.class_4270) {
            class_4267.class_4270 serverEntry = (class_4267.class_4270)selected;
            if (ilIl____1i__lI11_il11I1Il1i1lIl.Illil_i1I1lll1lillilii_1llii(serverEntry.method_20133())) {
               if (keyCode == 261 || keyCode == 259) {
                  cir.setReturnValue(true);
                  return;
               }

               if ((modifiers & 1) != 0 && (keyCode == 264 || keyCode == 265)) {
                  cir.setReturnValue(true);
                  return;
               }
            }
         }
      }

   }
}
