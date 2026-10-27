package code.miaware.inject.render;

import code.miaware.client.features.modules.render.armor.K422eab760990558fde;
import net.minecraft.class_10197;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin({class_10197.class})
public abstract class MixinEquipmentRendererDurability {
   @ModifyArg(
      method = {"render(Lnet/minecraft/client/render/entity/equipment/EquipmentModel$LayerType;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/client/model/Model;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/util/Identifier;)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/model/Model;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V"
),
      index = 4
   )
   private int lux$durabilityColor(int original) {
      Integer tint = K422eab760990558fde.get();
      return tint == null ? original : original & -16777216 | tint & 16777215;
   }
}
