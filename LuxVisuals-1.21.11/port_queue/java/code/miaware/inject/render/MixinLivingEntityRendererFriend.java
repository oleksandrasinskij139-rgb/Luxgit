package code.miaware.inject.render;

import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10042;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_922;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_922.class})
public class MixinLivingEntityRendererFriend {
   @Inject(
      method = {"render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At("HEAD")}
   )
   private void onRenderFriend(class_10042 state, class_4587 matrixStack, class_4597 vertexConsumerProvider, int light, CallbackInfo ci) {
      if (state.field_53337 != null) {
         String name = state.field_53337.getString();
         if (K137d4e93c7270b0a04.getInstance().contains(name)) {
            RenderSystem.disableDepthTest();
         }
      }

   }

   @Inject(
      method = {"render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At("RETURN")}
   )
   private void afterRenderFriend(class_10042 state, class_4587 matrixStack, class_4597 vertexConsumerProvider, int light, CallbackInfo ci) {
      if (state.field_53337 != null) {
         String name = state.field_53337.getString();
         if (K137d4e93c7270b0a04.getInstance().contains(name)) {
            RenderSystem.enableDepthTest();
         }
      }

   }
}
