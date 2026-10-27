package ru.phantom.mixin.minecraft.client.entity;

import II1II1II1II1II1II1II1II1.I2_i1.lIl_li___IilliI_1i__liIl1iilI;
import net.minecraft.class_1282;
import net.minecraft.class_1309;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.phantom.Phantom;

@Mixin({class_1309.class})
public class LivingEntityMixin {
   @Inject(
      method = {"method_6078"},
      at = {@At("TAIL")}
   )
   public void triggerEntityDeathEvent(class_1282 damageSource, CallbackInfo ci) {
      class_1309 entity = (class_1309)this;
      Phantom.getInstance().getEventBus().post(new lIl_li___IilliI_1i__liIl1iilI(entity, damageSource));
   }
}
