package code.miaware.inject.render;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.client.features.modules.combat.Kc42cd3d5e3fdc3da0e;
import code.miaware.client.features.modules.combat.hitbox.K622b5654eecec333de;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1531;
import net.minecraft.class_238;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_5498;
import net.minecraft.class_898;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_898.class})
public class MixinEntityRenderDispatcher implements K4600bef438dafc1a9c {
   @ModifyExpressionValue(
      method = {"render(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/EntityRenderer;)V"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/client/render/entity/state/EntityRenderState;invisible:Z"
)}
   )
   private boolean renderHitboxHook(boolean original, @Local(ordinal = 0,argsOnly = true) class_1297 entity) {
      return original || entity instanceof class_1531 || miaware$shouldHideHitbox(entity);
   }

   @Inject(
      method = {"renderHitbox"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void renderHitboxHook(class_4587 matrices, class_4588 vertices, class_1297 entity, float tickDelta, float red, float green, float blue, CallbackInfo ci) {
      if (miaware$shouldHideHitbox(entity)) {
         ci.cancel();
      } else {
         Kc42cd3d5e3fdc3da0e hitboxColor = Kc42cd3d5e3fdc3da0e.getInstance();
         if (hitboxColor != null && hitboxColor.isEnabled()) {
            if (entity != mc.field_1724 || mc.field_1690.method_31044() != class_5498.field_26664) {
               class_238 localBox = entity.method_5829().method_989(-entity.method_23317(), -entity.method_23318(), -entity.method_23321());
               K622b5654eecec333de.render(matrices, localBox, hitboxColor.renderColor(), (Boolean)hitboxColor.getCorners().getValue(), (Boolean)hitboxColor.getFill().getValue(), hitboxColor.getFillAlpha().getValue());
            }

            ci.cancel();
         }
      }
   }

   @Unique
   private static boolean miaware$shouldHideHitbox(class_1297 entity) {
      if (entity == null) {
         return true;
      } else if (entity.method_5767()) {
         return true;
      } else if (mc.field_1724 != null && entity.method_5756(mc.field_1724)) {
         return true;
      } else {
         boolean var10000;
         if (entity instanceof class_1309) {
            class_1309 living = (class_1309)entity;
            if (living.method_6059(class_1294.field_5905)) {
               var10000 = true;
               return var10000;
            }
         }

         var10000 = false;
         return var10000;
      }
   }
}
