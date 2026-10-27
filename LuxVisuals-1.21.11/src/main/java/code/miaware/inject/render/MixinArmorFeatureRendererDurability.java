package code.miaware.inject.render;

import code.miaware.client.features.modules.render.K8eca1d6eead3aa6b07;
import code.miaware.client.features.modules.render.armor.K422eab760990558fde;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_10034;
import net.minecraft.class_10055;
import net.minecraft.class_10186;
import net.minecraft.class_10197;
import net.minecraft.class_10394;
import net.minecraft.class_1799;
import net.minecraft.class_3879;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_5321;
import net.minecraft.class_970;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_970.class})
public abstract class MixinArmorFeatureRendererDurability {
   @Unique
   private boolean lux$playerArmor;

   @Inject(
      method = {"render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/BipedEntityRenderState;FF)V"},
      at = {@At("HEAD")}
   )
   private void lux$player(class_4587 matrices, class_4597 consumers, int light, class_10034 state, float yaw, float pitch, CallbackInfo ci) {
      this.lux$playerArmor = state instanceof class_10055;
   }

   @WrapOperation(
      method = {"renderArmor"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/entity/equipment/EquipmentRenderer;render(Lnet/minecraft/client/render/entity/equipment/EquipmentModel$LayerType;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/client/model/Model;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"
)}
   )
   private void lux$tintPiece(class_10197 renderer, class_10186.class_10190 layer, class_5321<class_10394> asset, class_3879 model, class_1799 stack, class_4587 matrices, class_4597 consumers, int light, Operation<Void> original) {
      Integer previous = K422eab760990558fde.get();
      K8eca1d6eead3aa6b07 module = K8eca1d6eead3aa6b07.getInstance();
      K422eab760990558fde.set(this.lux$playerArmor && module.isEnabled() ? module.color(stack) : null);

      try {
         original.call(new Object[]{renderer, layer, asset, model, stack, matrices, consumers, light});
      } finally {
         K422eab760990558fde.set(previous);
      }

   }
}
