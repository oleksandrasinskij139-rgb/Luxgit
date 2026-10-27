package code.miaware.inject.render;

import code.miaware.api.module.setting.Ke298bfdac4e34a00d9;
import code.miaware.api.system.backend.K46bff99e1f90a113f9;
import code.miaware.client.features.modules.movement.K0c95c065010a5fd6c7;
import code.miaware.client.features.modules.movement.K1257a42c13af7578a8;
import code.miaware.client.features.modules.render.K45978f900e11d6615f;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_10039;
import net.minecraft.class_1542;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3959;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_7833;
import net.minecraft.class_916;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_916.class})
public class MixinItemEntityRenderer {
   private static final Map<Integer, Boolean> visibilityCache = new HashMap();
   private static int lastCheckTick = 0;

   @Inject(
      method = {"render(Lnet/minecraft/client/render/entity/state/ItemEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onRenderHead(class_10039 state, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
      class_310 mc = class_310.method_1551();
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (K45978f900e11d6615f.getInstance().isItemCullingEnabled()) {
            if (mc.field_1724.field_6012 - lastCheckTick > 10) {
               visibilityCache.clear();
               lastCheckTick = mc.field_1724.field_6012;
            }

            int cacheKey = (int)(state.field_53325 * (double)1000.0F) ^ (int)(state.field_53326 * (double)1000.0F) ^ (int)(state.field_53327 * (double)1000.0F);
            if (visibilityCache.containsKey(cacheKey)) {
               if (!(Boolean)visibilityCache.get(cacheKey)) {
                  ci.cancel();
               }

            } else {
               class_243 itemPos = new class_243(state.field_53325, state.field_53326 + (double)0.25F, state.field_53327);
               class_243 playerEyes = mc.field_1724.method_33571();
               class_3959 context = new class_3959(playerEyes, itemPos, class_3960.field_17558, class_242.field_1348, mc.field_1724);
               class_239 result = mc.field_1687.method_17742(context);
               boolean visible = true;
               if (result.method_17783() == class_240.field_1332) {
                  double distanceToHit = playerEyes.method_1022(result.method_17784());
                  double distanceToItem = playerEyes.method_1022(itemPos);
                  if (distanceToHit < distanceToItem - (double)0.5F) {
                     visible = false;
                  }
               }

               visibilityCache.put(cacheKey, visible);
               if (!visible) {
                  ci.cancel();
               }

            }
         }
      }
   }

   @Inject(
      method = {"render(Lnet/minecraft/client/render/entity/state/ItemEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/util/math/MatrixStack;push()V",
   ordinal = 0,
   shift = Shift.AFTER
)}
   )
   private void freezeRotation(class_10039 state, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
      if (K0c95c065010a5fd6c7.getInstance().isEnabled()) {
         K1257a42c13af7578a8 mode = (K1257a42c13af7578a8)K46bff99e1f90a113f9.getChoiceByName((String)K0c95c065010a5fd6c7.getInstance().mode.getValue(), (Ke298bfdac4e34a00d9[])K1257a42c13af7578a8.values());
         if (mode == K1257a42c13af7578a8.LAYING) {
            state.field_53328 = 0.0F;
         }

      }
   }

   @Inject(
      method = {"updateRenderState(Lnet/minecraft/entity/ItemEntity;Lnet/minecraft/client/render/entity/state/ItemEntityRenderState;F)V"},
      at = {@At("RETURN")}
   )
   private void removeUniqueOffset(class_1542 entity, class_10039 state, float tickDelta, CallbackInfo ci) {
      if (K0c95c065010a5fd6c7.getInstance().isEnabled()) {
         K1257a42c13af7578a8 mode = (K1257a42c13af7578a8)K46bff99e1f90a113f9.getChoiceByName((String)K0c95c065010a5fd6c7.getInstance().mode.getValue(), (Ke298bfdac4e34a00d9[])K1257a42c13af7578a8.values());
         if (mode == K1257a42c13af7578a8.LAYING) {
            state.field_53435 = 0.0F;
         }
      }

   }

   @Inject(
      method = {"render(Lnet/minecraft/client/render/entity/state/ItemEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V",
   ordinal = 0,
   shift = Shift.AFTER
)}
   )
   private void applyCustomRotation(class_10039 state, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
      if (K0c95c065010a5fd6c7.getInstance().isEnabled()) {
         K1257a42c13af7578a8 mode = (K1257a42c13af7578a8)K46bff99e1f90a113f9.getChoiceByName((String)K0c95c065010a5fd6c7.getInstance().mode.getValue(), (Ke298bfdac4e34a00d9[])K1257a42c13af7578a8.values());
         if (mode == K1257a42c13af7578a8.ROTATE_360) {
            float rotation = state.field_53328 / 20.0F + state.field_53435;
            matrices.method_22907(class_7833.field_40718.rotationDegrees(rotation * 180.0F));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(rotation * 180.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(rotation * 180.0F));
         } else if (mode == K1257a42c13af7578a8.LAYING) {
            matrices.method_22907(class_7833.field_40714.rotationDegrees(90.0F));
            float scale = K0c95c065010a5fd6c7.getInstance().layingScale.getValue();
            matrices.method_22905(scale, scale, scale);
         }

      }
   }
}
