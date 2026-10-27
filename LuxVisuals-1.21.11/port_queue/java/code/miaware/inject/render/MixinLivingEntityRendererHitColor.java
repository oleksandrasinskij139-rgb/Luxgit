package code.miaware.inject.render;

import code.miaware.client.features.modules.render.Kd9a0d0354b82743c74;
import code.miaware.client.features.modules.render.hitcolor.K6c4a2ec8c78f89fb31;
import code.miaware.client.features.modules.render.hitcolor.Kc8717417882af19c93;
import code.miaware.client.features.modules.render.hitcolor.Ke2d221182e949c2cf9;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.class_10042;
import net.minecraft.class_1309;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_922;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_922.class})
public abstract class MixinLivingEntityRendererHitColor {
   @Inject(
      method = {"updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V"},
      at = {@At("RETURN")}
   )
   private void lux$hitAnimation(class_1309 entity, class_10042 state, float delta, CallbackInfo ci) {
      ((Kc8717417882af19c93)state).lux$hitStrength(K6c4a2ec8c78f89fb31.strength((float)entity.field_6235 - delta, (float)entity.field_6254));
   }

   @ModifyReturnValue(
      method = {"getOverlay"},
      at = {@At("RETURN")}
   )
   private static int lux$smoothOverlay(int original, class_10042 state, float whiteOverlay) {
      return Kd9a0d0354b82743c74.getInstance().isEnabled() && state.field_53460 && state.field_53449 == 0.0F ? K6c4a2ec8c78f89fb31.overlayUv(((Kc8717417882af19c93)state).lux$hitStrength()) : original;
   }

   @Inject(
      method = {"render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At("HEAD")}
   )
   private void lux$hitColorHead(class_10042 state, class_4587 matrices, class_4597 consumers, int light, CallbackInfo ci) {
      Ke2d221182e949c2cf9.sync();
      Kd9a0d0354b82743c74 module = Kd9a0d0354b82743c74.getInstance();
      Ke2d221182e949c2cf9.renderingHurt = state != null && state.field_53460 && state.field_53449 == 0.0F && module != null && module.isEnabled();
      Ke2d221182e949c2cf9.renderingOverlay = Ke2d221182e949c2cf9.renderingHurt ? K6c4a2ec8c78f89fb31.overlayUv(((Kc8717417882af19c93)state).lux$hitStrength()) : class_4608.field_21444;
   }

   @Inject(
      method = {"render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At("RETURN")}
   )
   private void lux$hitColorReturn(class_10042 state, class_4587 matrices, class_4597 consumers, int light, CallbackInfo ci) {
      Ke2d221182e949c2cf9.renderingHurt = false;
      Ke2d221182e949c2cf9.renderingOverlay = class_4608.field_21444;
   }
}
