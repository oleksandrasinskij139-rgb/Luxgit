package code.miaware.inject.render;

import code.miaware.client.features.modules.render.Kd9a0d0354b82743c74;
import code.miaware.client.features.modules.render.hitcolor.Ke2d221182e949c2cf9;
import net.minecraft.class_10197;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_4608;
import net.minecraft.class_4722;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({class_10197.class})
public abstract class MixinEquipmentRendererHitColor {
   @Unique
   private static final String RENDER = "render(Lnet/minecraft/client/render/entity/equipment/EquipmentModel$LayerType;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/client/model/Model;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/util/Identifier;)V";

   @Unique
   private static boolean lux$active() {
      Kd9a0d0354b82743c74 module = Kd9a0d0354b82743c74.getInstance();
      return Ke2d221182e949c2cf9.renderingHurt && module != null && module.isEnabled() && (Boolean)module.armor.getValue();
   }

   @Redirect(
      method = {"render(Lnet/minecraft/client/render/entity/equipment/EquipmentModel$LayerType;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/client/model/Model;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/util/Identifier;)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/RenderLayer;getArmorCutoutNoCull(Lnet/minecraft/util/Identifier;)Lnet/minecraft/client/render/RenderLayer;"
)
   )
   private class_1921 lux$armorLayer(class_2960 texture) {
      return lux$active() ? class_1921.method_28116(texture) : class_1921.method_25448(texture);
   }

   @Redirect(
      method = {"render(Lnet/minecraft/client/render/entity/equipment/EquipmentModel$LayerType;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/client/model/Model;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/util/Identifier;)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/TexturedRenderLayers;getArmorTrims(Z)Lnet/minecraft/client/render/RenderLayer;"
)
   )
   private class_1921 lux$trimLayer(boolean decal) {
      return lux$active() ? class_1921.method_28116(class_4722.field_42071) : class_4722.method_48480(decal);
   }

   @Redirect(
      method = {"render(Lnet/minecraft/client/render/entity/equipment/EquipmentModel$LayerType;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/client/model/Model;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/util/Identifier;)V"},
      at = @At(
   value = "FIELD",
   opcode = 178,
   target = "Lnet/minecraft/client/render/OverlayTexture;DEFAULT_UV:I"
)
   )
   private int lux$armorOverlay() {
      return lux$active() ? Ke2d221182e949c2cf9.renderingOverlay : class_4608.field_21444;
   }
}
