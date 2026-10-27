package code.miaware.inject.render;

import code.miaware.api.event.events.render.K65d2ffbfabdb68c6a7;
import code.miaware.api.event.events.render.K7fa1ead6ee12b3fdf6;
import code.miaware.api.system.backend.K8e471a90dc4db42e81;
import code.miaware.api.utils.rotation.manager.K1311b30b569cc830b6;
import code.miaware.api.utils.rotation.manager.K960322f580344d3bad;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import code.miaware.client.features.modules.render.Kf8178702dbaef55c58;
import code.miaware.client.features.modules.render.Kfda0fbad3916542229;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_10042;
import net.minecraft.class_10055;
import net.minecraft.class_1309;
import net.minecraft.class_1921;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_583;
import net.minecraft.class_591;
import net.minecraft.class_7833;
import net.minecraft.class_922;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_922.class})
public abstract class MixinLivingEntityRenderer<T extends class_1309, S extends class_10042, M extends class_583<? super S>> {
   @Unique
   private static int invisibleEntityColor = -1;

   @Shadow
   protected abstract M method_4038();

   @Inject(
      method = {"updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V"},
      at = {@At("RETURN")}
   )
   private void lux$revealInvisible(class_1309 entity, class_10042 state, float delta, CallbackInfo ci) {
      if (Kf8178702dbaef55c58.shouldReveal(state)) {
         state.field_53461 = false;
      }

   }

   @ModifyExpressionValue(
      method = {"updateRenderState*"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;getLerpedPitch(F)F"
)}
   )
   private float updateVisalPitch(float original, class_1309 entity, S state, float tickDelta) {
      if (entity != K8e471a90dc4db42e81.player()) {
         return original;
      } else {
         K960322f580344d3bad rotationManager = K960322f580344d3bad.getInstance();
         Keb4a85ca52fb971c57 rotation = rotationManager.getRotation();
         Keb4a85ca52fb971c57 rotationPrev = rotationManager.getPreviousRotation();
         K1311b30b569cc830b6 currentRotationPlan = rotationManager.getCurrentRotationPlan();
         return currentRotationPlan == null ? original : class_3532.method_17821(tickDelta, rotationPrev.getPitch(), rotation.getPitch());
      }
   }

   @Shadow
   protected abstract @Nullable class_1921 method_24302(class_10042 var1, boolean var2, boolean var3, boolean var4);

   @Redirect(
      method = {"render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;getRenderLayer(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;ZZZ)Lnet/minecraft/client/render/RenderLayer;"
)
   )
   private class_1921 renderHook(class_922 instance, class_10042 state, boolean showBody, boolean translucent, boolean showOutline) {
      if (!translucent && state instanceof class_10055) {
         int defaultColor = -1;
         K7fa1ead6ee12b3fdf6 eventData = new K7fa1ead6ee12b3fdf6(defaultColor);
         K65d2ffbfabdb68c6a7.getInstance().call(eventData);
         if (eventData.color() != defaultColor) {
            translucent = true;
            invisibleEntityColor = eventData.color();
         } else {
            invisibleEntityColor = -1;
         }
      } else {
         invisibleEntityColor = -1;
      }

      return this.method_24302(state, showBody, translucent, showOutline);
   }

   @Redirect(
      method = {"render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V"
)
   )
   private void renderModelHook(class_583<?> instance, class_4587 matrixStack, class_4588 vertexConsumer, int light, int overlay, int color, @Local(ordinal = 0,argsOnly = true) class_10042 renderState, @Local(argsOnly = true) class_4597 consumers) {
      int renderColor = color;
      if (renderState.field_53461 && invisibleEntityColor != -1) {
         renderColor = invisibleEntityColor;
      }

      class_310 mc = class_310.method_1551();
      boolean applied = false;
      boolean pushed = false;
      class_591 pm = null;
      float rp = 0.0F;
      float lp = 0.0F;
      float rr = 0.0F;
      float lr = 0.0F;
      float ry = 0.0F;
      float ly = 0.0F;
      float hp = 0.0F;
      float hy = 0.0F;
      float hr = 0.0F;
      if (mc != null && mc.field_1724 != null && instance instanceof class_591 && renderState instanceof class_10055) {
         class_10055 ps = (class_10055)renderState;
         if (ps.field_53528 == mc.field_1724.method_5628() && lux_visuals$narutoEnabled()) {
            float speed = (float)mc.field_1724.method_18798().method_37267();
            float run = class_3532.method_15363(speed * 10.0F, 0.0F, 1.0F);
            if (run >= 0.1F && !ps.field_53412 && !ps.field_53411 && !ps.field_53414 && !ps.field_53410 && !mc.field_1690.method_31044().method_31034()) {
               pm = (class_591)instance;
               rp = pm.field_3401.field_3654;
               lp = pm.field_27433.field_3654;
               rr = pm.field_3401.field_3674;
               lr = pm.field_27433.field_3674;
               ry = pm.field_3401.field_3675;
               ly = pm.field_27433.field_3675;
               hp = pm.field_3398.field_3654;
               hy = pm.field_3398.field_3675;
               hr = pm.field_3398.field_3674;
               float tickDelta = mc.method_61966().method_60637(false);
               float tt = ((float)mc.field_1724.field_6012 + tickDelta) * 0.6F;
               float swing = class_3532.method_15362(tt) * 0.2F * run;
               float armBack = 1.25F + 0.45F * run + swing;
               float armRoll = 0.55F * run;
               float headDown = -0.15F * run;
               float bodyLean = 0.45F * run;
               pm.field_3401.field_3654 = armBack;
               pm.field_27433.field_3654 = armBack;
               pm.field_3401.field_3674 = armRoll;
               pm.field_27433.field_3674 = -armRoll;
               pm.field_3401.field_3675 += 0.1F * run;
               pm.field_27433.field_3675 -= 0.1F * run;
               pm.field_3398.field_3654 += headDown;
               float pivotY = 0.75F;
               matrixStack.method_22903();
               pushed = true;
               matrixStack.method_46416(0.0F, pivotY, 0.0F);
               matrixStack.method_22907(class_7833.field_40714.rotation(bodyLean));
               matrixStack.method_46416(0.0F, -pivotY, 0.0F);
               applied = true;
            }
         }
      }

      try {
         renderColor = Kf8178702dbaef55c58.color(renderState, renderColor);
         instance.method_62100(matrixStack, vertexConsumer, light, overlay, renderColor);
      } finally {
         if (pushed) {
            matrixStack.method_22909();
         }

         if (applied && pm != null) {
            pm.field_3401.field_3654 = rp;
            pm.field_27433.field_3654 = lp;
            pm.field_3401.field_3674 = rr;
            pm.field_27433.field_3674 = lr;
            pm.field_3401.field_3675 = ry;
            pm.field_27433.field_3675 = ly;
            pm.field_3398.field_3654 = hp;
            pm.field_3398.field_3675 = hy;
            pm.field_3398.field_3674 = hr;
         }

      }

      invisibleEntityColor = -1;
   }

   @Unique
   private static boolean lux_visuals$narutoEnabled() {
      Kfda0fbad3916542229 narutoModule = Kfda0fbad3916542229.getInstance();
      return narutoModule != null && narutoModule.isEnabled();
   }
}
