package code.miaware.inject.entity;

import code.miaware.api.event.events.player.move.K1b621b67e13f65405c;
import code.miaware.api.event.events.player.world.K59e91c1b6e54ef922d;
import code.miaware.api.event.events.player.world.K8d0b25c690b59e234d;
import code.miaware.api.system.backend.K8e471a90dc4db42e81;
import code.miaware.client.features.modules.combat.K8722fab69227107712;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1657.class})
public abstract class MixinPlayerEntity extends MixinLivingEntity {
   @Inject(
      method = {"attack"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void attackEventHook(class_1297 target, CallbackInfo ci) {
      if (K8e471a90dc4db42e81.player() != null) {
         if (this == K8e471a90dc4db42e81.player() && K8722fab69227107712.shouldBlock(target)) {
            ci.cancel();
         } else {
            if (this == K8e471a90dc4db42e81.player()) {
               K59e91c1b6e54ef922d.getInstance().call(new K8d0b25c690b59e234d(target));
            }

         }
      }
   }

   @Inject(
      method = {"travel"},
      at = {@At("HEAD")}
   )
   public void travelHook(class_243 movementInput, CallbackInfo ci) {
      if (this == K8e471a90dc4db42e81.player()) {
         K1b621b67e13f65405c.getInstance().call();
      }
   }
}
