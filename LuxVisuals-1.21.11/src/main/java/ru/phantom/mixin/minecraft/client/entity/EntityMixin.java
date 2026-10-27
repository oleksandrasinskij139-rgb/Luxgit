package ru.phantom.mixin.minecraft.client.entity;

import II1II1II1II1II1II1II1II1.l8_I7.II1ll__i1IIIii_l11IlIilI1i_lii_;
import II1II1II1II1II1II1II1II1.l8_I7.iI_iliili1I__l1IIli1liill_;
import II1II1II1II1II1II1II1II1.l8_I7.illI1_11i_1__Iii_11I_11i11i;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1297.class})
public class EntityMixin implements illI1_11i_1__Iii_11I_11i11i {
   @Unique
   private float Phantom$cameraYaw;
   @Unique
   private float Phantom$cameraPitch;

   @Inject(
      method = {"method_5872"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void Phantom$changeFreeLookDirection(double cursorDeltaX, double cursorDeltaY, CallbackInfo ci) {
      if (II1ll__i1IIIii_l11IlIilI1i_lii_.Iii___1_1i_1i111IlI_l1_i1 && this == class_310.method_1551().field_1724) {
         this.Phantom$cameraYaw += (float)(cursorDeltaX * (double)0.15F);
         this.Phantom$cameraPitch += (float)(cursorDeltaY * (double)0.15F);
         this.Phantom$cameraPitch = class_3532.method_15363(this.Phantom$cameraPitch, -90.0F, 90.0F);
         ci.cancel();
      }
   }

   @Inject(
      method = {"method_5787"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void flarzen$isLogicalSideForUpdatingMovement(CallbackInfoReturnable<Boolean> cir) {
      if ((class_1297)this instanceof class_746) {
         cir.setReturnValue(true);
      }

   }

   @ModifyExpressionValue(
      method = {"method_5784"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_1297;method_65038()Z"
)}
   )
   private boolean fixFallDistanceCalculation(boolean original) {
      return this == class_310.method_1551().field_1724 ? false : original;
   }

   @ModifyVariable(
      method = {"method_5784"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private class_243 phantom$antiCrash$sanitizeMovement(class_243 movement) {
      return movement != null && Double.isFinite(movement.field_1352) && Double.isFinite(movement.field_1351) && Double.isFinite(movement.field_1350) && !(Math.abs(movement.field_1352) > (double)99.0F) && !(Math.abs(movement.field_1351) > (double)99.0F) && !(Math.abs(movement.field_1350) > (double)99.0F) ? movement : class_243.field_1353;
   }

   @ModifyVariable(
      method = {"method_18799(Lnet/minecraft/class_243;)V"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private class_243 phantom$antiCrash$sanitizeVelocity(class_243 velocity) {
      return velocity != null && Double.isFinite(velocity.field_1352) && Double.isFinite(velocity.field_1351) && Double.isFinite(velocity.field_1350) && !(Math.abs(velocity.field_1352) > (double)99.0F) && !(Math.abs(velocity.field_1351) > (double)99.0F) && !(Math.abs(velocity.field_1350) > (double)99.0F) ? velocity : class_243.field_1353;
   }

   @ModifyVariable(
      method = {"method_17835(Lnet/minecraft/class_243;)Lnet/minecraft/class_243;"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private class_243 phantom$antiCrash$sanitizeAdjustMovement(class_243 movement) {
      return movement != null && Double.isFinite(movement.field_1352) && Double.isFinite(movement.field_1351) && Double.isFinite(movement.field_1350) && !(Math.abs(movement.field_1352) > (double)99.0F) && !(Math.abs(movement.field_1351) > (double)99.0F) && !(Math.abs(movement.field_1350) > (double)99.0F) ? movement : class_243.field_1353;
   }

   @Inject(
      method = {"method_5756"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void phantom$seeInvisible$isInvisibleTo(class_1657 player, CallbackInfoReturnable<Boolean> cir) {
      if (this instanceof class_1657 && iI_iliili1I__l1IIli1liill_.IIl1ll111llI1I1l1l_I__I_11llll_()) {
         cir.setReturnValue(false);
      }

   }

   public float getCameraYaw() {
      return this.Phantom$cameraYaw;
   }

   public float getCameraPitch() {
      return this.Phantom$cameraPitch;
   }

   public void setCameraYaw(float yaw) {
      this.Phantom$cameraYaw = yaw;
   }

   public void setCameraPitch(float pitch) {
      this.Phantom$cameraPitch = pitch;
   }
}
