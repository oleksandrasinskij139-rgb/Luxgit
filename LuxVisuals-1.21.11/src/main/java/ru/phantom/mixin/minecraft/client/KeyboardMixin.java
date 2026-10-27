package ru.phantom.mixin.minecraft.client;

import II1II1II1II1II1II1II1II1.I2_i1.l_Il_1ll__lIIIll1_llIl_i1l1lII;
import II1II1II1II1II1II1II1II1.I5_i4.IIiI1iiI1l1__I_1i11IiIlIii_;
import net.minecraft.class_309;
import net.minecraft.class_310;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.phantom.Phantom;

@Mixin({class_309.class})
public class KeyboardMixin {
   @Final
   @Shadow
   private class_310 field_1678;

   @Inject(
      method = {"method_1466"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onKey(long window, int key, int scanCode, int action, int modifiers, CallbackInfo ci) {
      if (window == this.field_1678.method_22683().method_4490()) {
         if (key < 0) {
            return;
         }

         class_437 prevScreen = this.field_1678.field_1755;
         Phantom.getInstance().getEventBus().post(new l_Il_1ll__lIIIll1_llIl_i1l1lII(key, scanCode, action));
         if (prevScreen == null && this.field_1678.field_1755 != null) {
            ci.cancel();
            return;
         }

         IIiI1iiI1l1__I_1i11IiIlIii_ animations = IIiI1iiI1l1__I_1i11IiIlIii_.i1I_1IlilIll1_l_11_iiIll();
         if (animations != null && animations.isEnable()) {
            if (key == 258 && animations.shouldSmoothTab()) {
               animations.setTabPressed(action != 0);
            }

            if (key == 294 && action == 1 && animations.shouldSmoothF5()) {
               animations.resetThirdPerson();
            }
         }
      }

   }
}
