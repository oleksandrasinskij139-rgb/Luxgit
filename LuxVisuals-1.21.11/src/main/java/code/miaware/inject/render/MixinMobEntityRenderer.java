package code.miaware.inject.render;

import code.miaware.client.features.modules.render.K45978f900e11d6615f;
import code.miaware.client.util.render.Ke3c7c448bfa805757d;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_10042;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3959;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_922;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_922.class})
public class MixinMobEntityRenderer {
   @Unique
   private static final Map<Integer, Ke3c7c448bfa805757d> lux$visibilityCache = new HashMap();
   @Unique
   private static int lux$lastCleanupTick = 0;

   @Inject(
      method = {"render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onRenderLivingEntity(class_10042 state, class_4587 matrixStack, class_4597 vertexConsumerProvider, int i, CallbackInfo ci) {
      class_310 mc = class_310.method_1551();
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (K45978f900e11d6615f.getInstance().isMobCullingEnabled()) {
            if (state.field_53337 == null) {
               if (mc.field_1724.field_6012 - lux$lastCleanupTick > 40) {
                  lux$visibilityCache.entrySet().removeIf((entry) -> mc.field_1724.field_6012 - ((Ke3c7c448bfa805757d)entry.getValue()).lastCheckedTick > 40);
                  lux$lastCleanupTick = mc.field_1724.field_6012;
               }

               class_243 playerEyes = mc.field_1724.method_33571();
               class_243 entityPos = new class_243(state.field_53325, state.field_53326 + (double)state.field_53330 / (double)2.0F, state.field_53327);
               double sqDistance = playerEyes.method_1025(entityPos);
               if (!(sqDistance > (double)9216.0F)) {
                  int gridX = (int)Math.floor(state.field_53325 / (double)2.0F);
                  int gridY = (int)Math.floor(state.field_53326 / (double)2.0F);
                  int gridZ = (int)Math.floor(state.field_53327 / (double)2.0F);
                  int cacheKey = gridX * 73856093 ^ gridY * 19349663 ^ gridZ * 83492791;
                  Ke3c7c448bfa805757d cached = (Ke3c7c448bfa805757d)lux$visibilityCache.get(cacheKey);
                  if (cached != null && mc.field_1724.field_6012 - cached.lastCheckedTick < 10) {
                     if (!cached.visible) {
                        ci.cancel();
                     }

                  } else {
                     class_3959 context = new class_3959(playerEyes, entityPos, class_3960.field_17558, class_242.field_1348, mc.field_1724);
                     class_239 result = mc.field_1687.method_17742(context);
                     boolean visible = true;
                     if (result.method_17783() == class_240.field_1332) {
                        double distanceToHit = playerEyes.method_1022(result.method_17784());
                        double distanceToEntity = playerEyes.method_1022(entityPos);
                        if (distanceToHit < distanceToEntity - (double)0.5F) {
                           visible = false;
                        }
                     }

                     lux$visibilityCache.put(cacheKey, new Ke3c7c448bfa805757d(visible, mc.field_1724.field_6012));
                     if (!visible) {
                        ci.cancel();
                     }

                  }
               }
            }
         }
      }
   }
}
