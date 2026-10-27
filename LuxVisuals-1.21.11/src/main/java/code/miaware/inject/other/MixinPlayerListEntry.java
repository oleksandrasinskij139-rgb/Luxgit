package code.miaware.inject.other;

import code.miaware.client.features.commands.K3b0f6d87510852bb83;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import java.util.function.Supplier;
import net.minecraft.class_310;
import net.minecraft.class_640;
import net.minecraft.class_746;
import net.minecraft.class_8685;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_640.class})
public class MixinPlayerListEntry {
   @ModifyReturnValue(
      method = {"getSkinTextures"},
      at = {@At("RETURN")}
   )
   private class_8685 skinTexturesHook(class_8685 original) {
      Supplier<class_8685> customSkin = K3b0f6d87510852bb83.getCustomSkinTextures();
      class_746 player = class_310.method_1551().field_1724;
      if (player != null && customSkin != null) {
         class_640 playerListEntry = player.method_3123();
         if (playerListEntry != null && playerListEntry.equals(this)) {
            original = (class_8685)customSkin.get();
         }
      }

      return original;
   }

   @ModifyExpressionValue(
      method = {"texturesSupplier"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/MinecraftClient;uuidEquals(Ljava/util/UUID;)Z"
)}
   )
   private static boolean texturesSupplierHook(boolean original) {
      return original || K3b0f6d87510852bb83.getCustomSkinTextures() != null;
   }
}
