package code.miaware.inject.render;

import code.miaware.api.event.events.render.Kb236a96927fce94988;
import code.miaware.api.event.events.render.Ke9769112fe9e34d7c2;
import code.miaware.client.features.modules.render.K47431d944f09e79d50;
import code.miaware.client.features.modules.render.K69d319c58ab678134e;
import code.miaware.client.features.modules.render.Kaca9ea0013e20c91ae;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1799;
import net.minecraft.class_1806;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_742;
import net.minecraft.class_759;
import net.minecraft.class_7833;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_759.class})
public abstract class MixinHeldItemRenderer {
   @Shadow
   public class_310 field_4050;

   @Inject(
      method = {"renderFirstPersonItem"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onRenderItem(class_742 player, float tickDelta, float pitch, class_1268 hand, float swingProgress, class_1799 item, float equipProgress, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
      if (K69d319c58ab678134e.getInstance().isEnabled() && !item.method_7960()) {
         boolean isMainHand = hand == class_1268.field_5808;
         class_1306 arm = isMainHand ? player.method_6068() : player.method_6068().method_5928();
         boolean var14 = arm == class_1306.field_6183;
      }

      if (K47431d944f09e79d50.getInstance().isEnabled()) {
         boolean isMainHand = hand == class_1268.field_5808;
         class_1306 arm = isMainHand ? player.method_6068() : player.method_6068().method_5928();
         Ke9769112fe9e34d7c2.getInstance().call(new Kb236a96927fce94988(matrices, hand, arm, item));
      }

      if (Kaca9ea0013e20c91ae.getInstance().isEnabled() && !item.method_7960() && !(item.method_7909() instanceof class_1806)) {
         ci.cancel();
         Kaca9ea0013e20c91ae.getInstance().handleRenderItem(player, tickDelta, pitch, hand, swingProgress, item, equipProgress, matrices, vertexConsumers, light);
      }

   }

   @Inject(
      method = {"renderFirstPersonItem"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
   shift = Shift.BEFORE
)},
      cancellable = false
   )
   private void applyItemRotationVanilla(class_742 player, float tickDelta, float pitch, class_1268 hand, float swingProgress, class_1799 item, float equipProgress, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
      if (K69d319c58ab678134e.getInstance().isEnabled()) {
         if (!Kaca9ea0013e20c91ae.getInstance().isEnabled()) {
            float rotX;
            float rotY;
            float rotZ;
            if (hand == class_1268.field_5808) {
               rotX = K69d319c58ab678134e.getInstance().mainHandRotationX.getValue();
               rotY = K69d319c58ab678134e.getInstance().mainHandRotationY.getValue();
               rotZ = K69d319c58ab678134e.getInstance().mainHandRotationZ.getValue();
            } else {
               rotX = K69d319c58ab678134e.getInstance().offHandRotationX.getValue();
               rotY = K69d319c58ab678134e.getInstance().offHandRotationY.getValue();
               rotZ = K69d319c58ab678134e.getInstance().offHandRotationZ.getValue();
            }

            if (rotX != 0.0F) {
               matrices.method_22907(class_7833.field_40714.rotationDegrees(rotX));
            }

            if (rotY != 0.0F) {
               matrices.method_22907(class_7833.field_40716.rotationDegrees(rotY));
            }

            if (rotZ != 0.0F) {
               matrices.method_22907(class_7833.field_40718.rotationDegrees(rotZ));
            }

         }
      }
   }

   @Inject(
      method = {"renderArmHoldingItem"},
      at = {@At("HEAD")}
   )
   private void applyViewModelToArm(class_4587 matrices, class_4597 vertexConsumers, int light, float equipProgress, float swingProgress, class_1306 arm, CallbackInfo ci) {
   }
}
