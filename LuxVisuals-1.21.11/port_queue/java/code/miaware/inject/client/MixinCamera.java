package code.miaware.inject.client;

import code.miaware.client.features.modules.player.K4d8e14bc1bc511ee65;
import code.miaware.client.features.modules.render.K3875e4bdc53cc1074e;
import code.miaware.client.features.modules.render.K44519432471f98716b;
import code.miaware.client.features.modules.render.Kacf94aeb4cdc6e13ca;
import code.miaware.client.features.modules.render.Kd8c2d764e94ca1dee2;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_5636;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({class_4184.class})
public abstract class MixinCamera {
   @Unique
   private static float smoothYaw = 0.0F;
   @Unique
   private static float smoothPitch = 0.0F;
   @Unique
   private static boolean initialized = false;
   @Unique
   private float perspectiveTransition = 0.0F;
   @Unique
   private int lastPerspective = 0;

   @Shadow
   protected abstract float method_19318(float var1);

   @Shadow
   protected abstract void method_19325(float var1, float var2);

   @ModifyArgs(
      method = {"update"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/Camera;setRotation(FF)V",
   ordinal = 0
)
   )
   private void applySmoothCamera(Args args) {
      K4d8e14bc1bc511ee65 freeLook = K4d8e14bc1bc511ee65.getInstance();
      if (freeLook.isFreeLookActive()) {
         args.set(0, freeLook.getSavedYaw());
         args.set(1, freeLook.getSavedPitch());
         initialized = false;
      } else {
         K44519432471f98716b smoothCamera = K44519432471f98716b.getInstance();
         if (smoothCamera != null && smoothCamera.isEnabled()) {
            float currentYaw = (Float)args.get(0);
            float currentPitch = (Float)args.get(1);
            if (!initialized) {
               smoothYaw = currentYaw;
               smoothPitch = currentPitch;
               initialized = true;
               args.set(0, smoothYaw);
               args.set(1, smoothPitch);
            } else {
               float deltaYaw = class_3532.method_15393(currentYaw - smoothYaw);
               float deltaPitch = currentPitch - smoothPitch;
               float spd = smoothCamera.speed.getValue();
               smoothYaw += deltaYaw * spd;
               smoothPitch += deltaPitch * spd;
               smoothPitch = class_3532.method_15363(smoothPitch, -90.0F, 90.0F);
               args.set(0, smoothYaw);
               args.set(1, smoothPitch);
            }
         } else {
            initialized = false;
         }
      }
   }

   @Inject(
      method = {"getSubmersionType"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void onGetSubmersionType(CallbackInfoReturnable<class_5636> cir) {
      if (K3875e4bdc53cc1074e.getInstance().isClearLava() && cir.getReturnValue() == class_5636.field_27885) {
         cir.setReturnValue(class_5636.field_27888);
      }

      if (K3875e4bdc53cc1074e.getInstance().isClearWater() && cir.getReturnValue() == class_5636.field_27886) {
         cir.setReturnValue(class_5636.field_27888);
      }

   }

   @ModifyArg(
      method = {"update"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/Camera;clipToSpace(F)F"
),
      index = 0
   )
   private float modifyCameraDistance(float distance) {
      Kd8c2d764e94ca1dee2 customF5 = Kd8c2d764e94ca1dee2.getInstance();
      if (customF5.isEnabled()) {
         distance = customF5.getCameraDistance();
      }

      Kacf94aeb4cdc6e13ca betterMc = Kacf94aeb4cdc6e13ca.getInstance();
      if (betterMc.isEnabled() && (Boolean)betterMc.getF5Animation().getValue()) {
         class_310 mc = class_310.method_1551();
         int currentPerspective = mc.field_1690.method_31044().ordinal();
         if (currentPerspective != this.lastPerspective) {
            this.perspectiveTransition = 0.0F;
            this.lastPerspective = currentPerspective;
         }

         if (this.perspectiveTransition < 1.0F) {
            float delta = mc.method_61966().method_60637(false);
            this.perspectiveTransition = class_3532.method_15363(this.perspectiveTransition + delta * 0.08F, 0.0F, 1.0F);
            float eased;
            if (this.perspectiveTransition < 0.5F) {
               eased = 4.0F * this.perspectiveTransition * this.perspectiveTransition * this.perspectiveTransition;
            } else {
               float p = this.perspectiveTransition - 1.0F;
               eased = 1.0F + 4.0F * p * p * p;
            }

            if (currentPerspective == 0) {
               distance *= 1.0F - eased;
            } else {
               float zoomEffect = (float)Math.sin((double)eased * Math.PI * (double)0.5F);
               distance *= zoomEffect;
            }
         }
      }

      return distance;
   }
}
