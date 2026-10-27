package code.miaware.inject.other;

import code.miaware.api.system.backend.K8e471a90dc4db42e81;
import code.miaware.api.utils.rotation.manager.K960322f580344d3bad;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_3959;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_1792.class})
public abstract class MixinItem {
   @ModifyExpressionValue(
      method = {"raycast"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/player/PlayerEntity;getRotationVector(FF)Lnet/minecraft/util/math/Vec3d;"
)}
   )
   private static class_243 hookFixRotation(class_243 original, class_1937 world, class_1657 player, class_3959.class_242 fluidHandling) {
      Keb4a85ca52fb971c57 rotation = K960322f580344d3bad.getInstance().getCurrentRotation();
      return player == K8e471a90dc4db42e81.player() && rotation != null ? rotation.getVector() : original;
   }
}
