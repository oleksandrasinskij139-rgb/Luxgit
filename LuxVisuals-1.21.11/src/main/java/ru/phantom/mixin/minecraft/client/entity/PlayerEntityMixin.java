package ru.phantom.mixin.minecraft.client.entity;

import II1II1II1II1II1II1II1II1.l8_I7.ilI1lI1_lil_Il_1II1_iIiiIiI11;
import II1II1II1II1II1II1II1II1.l8_I7.ll_li_1i__l_II1II1_iIl11I_i;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.phantom.Phantom;

@Mixin({class_1657.class})
public class PlayerEntityMixin {
   @Inject(
      method = {"method_7324"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void attackAHook2(class_1297 target, CallbackInfo ci) {
      class_310 mc = class_310.method_1551();
      if (this == mc.field_1724 && ((class_1657)this).method_37908().method_8608()) {
         ll_li_1i__l_II1II1_iIl11I_i event = new ll_li_1i__l_II1II1_iIl11I_i(target);
         Phantom.getInstance().getEventBus().post(event);
         if (event.isCancelled()) {
            ci.cancel();
         }

      }
   }

   @Inject(
      method = {"method_7324"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void attackAHook(class_1297 target, CallbackInfo ci) {
      class_310 mc = class_310.method_1551();
      if (this == mc.field_1724 && ((class_1657)this).method_37908().method_8608()) {
         ilI1lI1_lil_Il_1II1_iIiiIiI11 event = new ilI1lI1_lil_Il_1II1_iIiiIiI11(target);
         Phantom.getInstance().getEventBus().post(event);
      }
   }
}
