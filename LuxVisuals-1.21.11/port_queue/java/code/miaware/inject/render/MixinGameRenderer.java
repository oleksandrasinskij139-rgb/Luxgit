package code.miaware.inject.render;

import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.system.backend.K8e471a90dc4db42e81;
import code.miaware.api.utils.render.K1f4d66c1a0cf52e6aa;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.K357718bb1c119935c3;
import code.miaware.api.utils.rotation.K970216267e60d1d8b5;
import code.miaware.api.utils.rotation.manager.K960322f580344d3bad;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import code.miaware.client.features.modules.render.K3875e4bdc53cc1074e;
import code.miaware.client.features.modules.render.K9f8f1b6b6c0347c2f9;
import code.miaware.client.features.modules.render.Kadfa0bd1c711c7b4fe;
import code.miaware.client.features.modules.render.Kc2f9539a5b5775f961;
import code.miaware.client.features.modules.render.Kce5e38c1ce7561008e;
import code.miaware.client.ui.theme.K3195adfcc0c0135ece;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_1297;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import net.minecraft.class_7833;
import net.minecraft.class_9779;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_757.class})
public abstract class MixinGameRenderer {
   private boolean cachedHurtCamera = false;

   @ModifyExpressionValue(
      method = {"findCrosshairTarget"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/Entity;getRotationVec(F)Lnet/minecraft/util/math/Vec3d;"
)}
   )
   private class_243 rotationVectorHook(class_243 original, class_1297 camera, double blockInteractionRange, double entityInteractionRange, float tickDelta) {
      if (camera != K8e471a90dc4db42e81.player()) {
         return original;
      } else {
         Keb4a85ca52fb971c57 rotation = K960322f580344d3bad.getInstance().getCurrentRotation();
         return rotation != null ? rotation.getVector() : original;
      }
   }

   @ModifyExpressionValue(
      method = {"findCrosshairTarget"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/Entity;raycast(DFZ)Lnet/minecraft/util/hit/HitResult;"
)}
   )
   private class_239 hookRaycast(class_239 original, class_1297 camera, double blockInteractionRange, double entityInteractionRange, float tickDelta) {
      if (camera != K8e471a90dc4db42e81.player()) {
         return original;
      } else {
         Keb4a85ca52fb971c57 rotation = K960322f580344d3bad.getInstance().getCurrentRotation();
         return (class_239)(rotation == null ? original : K970216267e60d1d8b5.raycast(rotation, Math.max(blockInteractionRange, entityInteractionRange), false, tickDelta));
      }
   }

   @Inject(
      method = {"renderWorld"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/client/render/GameRenderer;renderHand:Z",
   opcode = 180,
   ordinal = 0
)}
   )
   void render3D(class_9779 renderTickCounter, CallbackInfo ci) {
      if (K8e471a90dc4db42e81.player() != null && class_310.method_1551().field_1687 != null) {
         class_4587 matrixStack = new class_4587();
         class_4184 camera = class_310.method_1551().field_1773.method_19418();
         RenderSystem.getModelViewStack().pushMatrix().mul(matrixStack.method_23760().method_23761());
         matrixStack.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
         matrixStack.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
         Ke390ca39bb18b9e97e.getInstance().call(new K9ee087b2e1791ad6b2(matrixStack, renderTickCounter.method_60637(false), camera.method_19326()));
         K2b5e2c5f2b9891b450.BOX.setup3DRender(matrixStack);
         K357718bb1c119935c3 renderUtil3D = K357718bb1c119935c3.getInstance();
         renderUtil3D.lastWorldSpaceMatrix = matrixStack.method_23760();
         renderUtil3D.savedCamPos = camera.method_19326();
         renderUtil3D.onRender3D();
         RenderSystem.getModelViewStack().popMatrix();
      }
   }

   @Inject(
      method = {"tiltViewWhenHurt"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onTiltViewWhenHurt(class_4587 matrices, float tickDelta, CallbackInfo ci) {
      if (class_310.method_1551().field_1724 != null) {
         this.cachedHurtCamera = K3875e4bdc53cc1074e.getInstance().isHurtCamera();
         if (this.cachedHurtCamera) {
            ci.cancel();
         }

      }
   }

   @Shadow
   public abstract float method_3193();

   @Inject(
      method = {"getFov"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void onGetFov(class_4184 camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Float> cir) {
      if (changingFov) {
         cir.setReturnValue(Kce5e38c1ce7561008e.getInstance().modifyFov((Float)cir.getReturnValue(), tickDelta));
      }

      if (Kc2f9539a5b5775f961.getInstance().isEnabled()) {
         float originalFov = (Float)cir.getReturnValue();
         float zoomedFov = Kc2f9539a5b5775f961.getInstance().getModifiedFov(originalFov);
         cir.setReturnValue(zoomedFov);
      }

   }

   @Inject(
      method = {"getBasicProjectionMatrix"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void onGetBasicProjectionMatrix(float fov, CallbackInfoReturnable<Matrix4f> cir) {
      boolean zoomEnabled = Kc2f9539a5b5775f961.getInstance().isEnabled();
      boolean aspectEnabled = Kadfa0bd1c711c7b4fe.getInstance().isEnabled();
      if (zoomEnabled) {
         fov = Kc2f9539a5b5775f961.getInstance().getModifiedFov(fov);
      }

      if (zoomEnabled || aspectEnabled) {
         Matrix4f matrix4f = new Matrix4f();
         float aspectRatio;
         if (aspectEnabled) {
            aspectRatio = Kadfa0bd1c711c7b4fe.getInstance().getAspectRatioValue();
         } else {
            aspectRatio = (float)class_310.method_1551().method_22683().method_4489() / (float)class_310.method_1551().method_22683().method_4506();
         }

         matrix4f.perspective((float)Math.toRadians((double)fov), aspectRatio, 0.05F, this.method_3193() * 4.0F);
         cir.setReturnValue(matrix4f);
      }

   }

   @Inject(
      method = {"render"},
      at = {@At("RETURN")}
   )
   private void applySaturationPostProcessing(class_9779 tickCounter, boolean tick, CallbackInfo ci) {
      class_310 mc = class_310.method_1551();
      if (mc.field_1687 == null) {
         K3195adfcc0c0135ece.render();
      } else {
         K9f8f1b6b6c0347c2f9 module = K9f8f1b6b6c0347c2f9.getInstance();
         if (module != null && module.isEnabled()) {
            K1f4d66c1a0cf52e6aa.render(module.getIntensity(), module.getBrightness(), module.getContrast(), module.getRedTint(), module.getGreenTint(), module.getBlueTint());
         }

         K3195adfcc0c0135ece.render();
      }
   }
}
