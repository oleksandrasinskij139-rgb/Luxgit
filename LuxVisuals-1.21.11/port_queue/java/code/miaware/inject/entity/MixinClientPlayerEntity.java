package code.miaware.inject.entity;

import code.miaware.api.event.events.player.move.K363439524bd338fc89;
import code.miaware.api.event.events.player.move.K5663417ec466186161;
import code.miaware.api.event.events.player.move.K7341fc34d45814592f;
import code.miaware.api.event.events.player.move.K9127285e6d255e9450;
import code.miaware.api.event.events.player.move.Kbad16f7868b17c69a5;
import code.miaware.api.event.events.player.move.Kd15060a6387ae9ac88;
import code.miaware.api.event.events.player.other.K4aa7dd2c48a255a6bb;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.system.backend.K8e471a90dc4db42e81;
import code.miaware.api.utils.player.K8c202e08d32fc608f9;
import code.miaware.api.utils.rotation.manager.K1311b30b569cc830b6;
import code.miaware.api.utils.rotation.manager.K960322f580344d3bad;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import code.miaware.client.features.modules.player.K08ed84328ad9502e9e;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;
import net.minecraft.class_1313;
import net.minecraft.class_243;
import net.minecraft.class_638;
import net.minecraft.class_742;
import net.minecraft.class_744;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_746.class})
public class MixinClientPlayerEntity extends class_742 {
   @Shadow
   public class_744 field_3913;

   public MixinClientPlayerEntity(class_638 world, GameProfile profile) {
      super(world, profile);
   }

   @Inject(
      method = {"dropSelectedItem"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onDropSelectedItem(boolean entireStack, CallbackInfoReturnable<Boolean> cir) {
      K08ed84328ad9502e9e lockSlot = K08ed84328ad9502e9e.getInstance();
      if (lockSlot != null && lockSlot.isEnabled()) {
         int currentSlot = this.method_31548().field_7545;
         if (lockSlot.isSlotLocked(currentSlot)) {
            cir.setReturnValue(false);
         }

      }
   }

   @Inject(
      method = {"tick"},
      at = {@At("HEAD")}
   )
   public void tickHook(CallbackInfo ci) {
      K584aa6c028ebeb26be.getInstance().call();
   }

   @Inject(
      method = {"move"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V"
)},
      cancellable = true
   )
   public void moveHook(class_1313 movementType, class_243 movement, CallbackInfo ci) {
      Kbad16f7868b17c69a5 event = new Kbad16f7868b17c69a5(movement.field_1352, movement.field_1351, movement.field_1350);
      if (K7341fc34d45814592f.getInstance().call(event)) {
         super.method_5784(movementType, new class_243(event.getX(), event.getY(), event.getZ()));
         ci.cancel();
      }

   }

   @ModifyExpressionValue(
      method = {"sendMovementPackets", "tick"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayerEntity;getYaw()F"
)}
   )
   private float silentRotationYaw(float original) {
      K960322f580344d3bad rotationManager = K960322f580344d3bad.getInstance();
      K1311b30b569cc830b6 currentRotationPlan = rotationManager.getCurrentRotationPlan();
      if (currentRotationPlan == null) {
         return original;
      } else {
         Keb4a85ca52fb971c57 rotation = rotationManager.getRotation();
         if (K8e471a90dc4db42e81.player() != null) {
            float yaw = rotation.getYaw();
            this.method_5847(yaw);
            this.method_5636(yaw);
         }

         return rotation.getYaw();
      }
   }

   @ModifyExpressionValue(
      method = {"sendMovementPackets", "tick"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayerEntity;getPitch()F"
)}
   )
   private float silentRotationPitch(float original) {
      K1311b30b569cc830b6 rotation = K960322f580344d3bad.getInstance().getCurrentRotationPlan();
      if (rotation == null) {
         return original;
      } else {
         Keb4a85ca52fb971c57 rot = K960322f580344d3bad.getInstance().getRotation();
         return rot.getPitch();
      }
   }

   @Inject(
      method = {"sendMovementPackets"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void preMotion(CallbackInfo ci) {
      K5663417ec466186161 event = new K5663417ec466186161(this.method_23317(), this.method_23318(), this.method_23321(), (double)this.method_5705(1.0F), (double)this.method_5695(1.0F), this.method_24828());
      if (Kd15060a6387ae9ac88.getInstance().call(event)) {
         ci.cancel();
      }

   }

   @ModifyExpressionValue(
      method = {"tickMovement"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayerEntity;canSprint()Z"
)}
   )
   private boolean sprintEventTick(boolean original) {
      return this.sprintHook(original);
   }

   @ModifyExpressionValue(
      method = {"tickMovement"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/option/KeyBinding;isPressed()Z"
)}
   )
   private boolean sprintEventInput(boolean original) {
      return this.sprintHook(original);
   }

   @Inject(
      method = {"closeHandledScreen"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V"
)},
      cancellable = true
   )
   private void onCloseHandledScreen(CallbackInfo ci) {
      if (K4aa7dd2c48a255a6bb.getInstance().call()) {
         ci.cancel();
      }

   }

   @Unique
   private boolean sprintHook(boolean origin) {
      return K9127285e6d255e9450.getInstance().call(new K363439524bd338fc89(origin, new K8c202e08d32fc608f9(this.field_3913)));
   }
}
