package code.miaware.inject.entity;

import code.miaware.api.event.events.player.move.K7dc5f8e115b48c51ca;
import code.miaware.api.system.backend.K8e471a90dc4db42e81;
import code.miaware.api.system.client.K9a4619034baf09d6b2;
import code.miaware.api.utils.rotation.manager.K1311b30b569cc830b6;
import code.miaware.api.utils.rotation.manager.K960322f580344d3bad;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import code.miaware.client.features.modules.render.Kaca9ea0013e20c91ae;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_1282;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1309.class})
public abstract class MixinLivingEntity extends MixinEntity {
   @Shadow
   public int field_6228;

   @Shadow
   public abstract boolean method_6128();

   @Inject(
      method = {"getHandSwingDuration"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getArmSwingAnimationEnd(CallbackInfoReturnable<Integer> callbackInfoReturnable) {
      Kaca9ea0013e20c91ae swingAnim = Kaca9ea0013e20c91ae.getInstance();
      if ((Boolean)swingAnim.slow.getValue() && swingAnim.isEnabled()) {
         int speedValue = swingAnim.speed.getValue().intValue();
         callbackInfoReturnable.setReturnValue(speedValue);
      }

   }

   @Inject(
      method = {"jump"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;getJumpVelocity()F"
)}
   )
   public void onJumping(CallbackInfo ci) {
      if (this == K8e471a90dc4db42e81.player()) {
         K7dc5f8e115b48c51ca.getInstance().call();
      }
   }

   @ModifyExpressionValue(
      method = {"jump"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;getYaw()F"
)}
   )
   public float fixJumpVelocity(float original) {
      if (this != K8e471a90dc4db42e81.player()) {
         return original;
      } else {
         K960322f580344d3bad rotationManager = K960322f580344d3bad.getInstance();
         Keb4a85ca52fb971c57 rotation = rotationManager.getRotation();
         K1311b30b569cc830b6 currentRotationPlan = rotationManager.getCurrentRotationPlan();
         return currentRotationPlan != null && currentRotationPlan.moveCorrection() ? rotation.getYaw() : original;
      }
   }

   @ModifyExpressionValue(
      method = {"calcGlidingVelocity"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;getPitch()F"
)}
   )
   private float fixGlidingVelocity(float original) {
      if (this != K8e471a90dc4db42e81.player()) {
         return original;
      } else {
         K960322f580344d3bad rotationManager = K960322f580344d3bad.getInstance();
         Keb4a85ca52fb971c57 rotation = rotationManager.getRotation();
         K1311b30b569cc830b6 currentRotationPlan = rotationManager.getCurrentRotationPlan();
         return currentRotationPlan != null && currentRotationPlan.moveCorrection() ? rotation.getPitch() : original;
      }
   }

   @ModifyExpressionValue(
      method = {"calcGlidingVelocity"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;"
)}
   )
   private class_243 fixGlidingVelocityVector(class_243 original) {
      if (this != K8e471a90dc4db42e81.player()) {
         return original;
      } else {
         K960322f580344d3bad rotationManager = K960322f580344d3bad.getInstance();
         Keb4a85ca52fb971c57 rotation = rotationManager.getRotation();
         K1311b30b569cc830b6 currentRotationPlan = rotationManager.getCurrentRotationPlan();
         return currentRotationPlan != null && currentRotationPlan.moveCorrection() ? rotation.getVector() : original;
      }
   }

   @Inject(
      method = {"isClimbing"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void spiderLeafClimbing(CallbackInfoReturnable<Boolean> cir) {
      if (this == K8e471a90dc4db42e81.player()) {
         ;
      }
   }

   @Inject(
      method = {"onDeath"},
      at = {@At("HEAD")}
   )
   private void onEntityDeath(class_1282 damageSource, CallbackInfo ci) {
      if (K8e471a90dc4db42e81.player() != null && K8e471a90dc4db42e81.world() != null) {
         K9a4619034baf09d6b2.death((class_1309)this, damageSource);
      }
   }
}
