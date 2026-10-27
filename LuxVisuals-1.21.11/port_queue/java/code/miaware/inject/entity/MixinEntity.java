package code.miaware.inject.entity;

import code.miaware.api.event.events.player.move.K2050c30dd15b86e128;
import code.miaware.api.event.events.player.move.K24d5217269a82ac585;
import code.miaware.api.system.backend.K8e471a90dc4db42e81;
import code.miaware.client.features.modules.render.K3875e4bdc53cc1074e;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1297.class})
public abstract class MixinEntity {
   @Inject(
      method = {"isGlowing"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void cancelGlowingHook(CallbackInfoReturnable<Boolean> cir) {
      if (K3875e4bdc53cc1074e.getInstance().isGlowEffect()) {
         cir.setReturnValue(false);
      }

   }

   @ModifyExpressionValue(
      method = {"move"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/Entity;isControlledByPlayer()Z"
)}
   )
   private boolean fixFallDistance(boolean original) {
      return this == K8e471a90dc4db42e81.player() ? false : original;
   }

   @Redirect(
      method = {"updateVelocity"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/Entity;movementInputToVelocity(Lnet/minecraft/util/math/Vec3d;FF)Lnet/minecraft/util/math/Vec3d;"
)
   )
   public class_243 updateVelocityHook(class_243 movementInput, float speed, float yaw) {
      if (this == K8e471a90dc4db42e81.player()) {
         K2050c30dd15b86e128 event = new K2050c30dd15b86e128(movementInput, speed, yaw, class_1297.method_18795(movementInput, speed, yaw));
         K24d5217269a82ac585.getInstance().call(event);
         return event.getVelocity();
      } else {
         return class_1297.method_18795(movementInput, speed, yaw);
      }
   }
}
