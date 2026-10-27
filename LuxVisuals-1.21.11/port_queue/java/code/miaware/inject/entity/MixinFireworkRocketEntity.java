package code.miaware.inject.entity;

import code.miaware.api.system.backend.K8e471a90dc4db42e81;
import code.miaware.api.utils.rotation.manager.K1311b30b569cc830b6;
import code.miaware.api.utils.rotation.manager.K960322f580344d3bad;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import net.minecraft.class_1309;
import net.minecraft.class_1671;
import net.minecraft.class_243;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({class_1671.class})
public class MixinFireworkRocketEntity {
   @Shadow
   private class_1309 field_7616;

   @Redirect(
      method = {"tick"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;"
)
   )
   public class_243 fixFireworkVelocity(class_1309 instance) {
      if (instance != K8e471a90dc4db42e81.player()) {
         return instance.method_5720();
      } else {
         K960322f580344d3bad rotationManager = K960322f580344d3bad.getInstance();
         Keb4a85ca52fb971c57 rotation = rotationManager.getRotation();
         K1311b30b569cc830b6 currentRotationPlan = rotationManager.getCurrentRotationPlan();
         return currentRotationPlan == null ? instance.method_5720() : rotation.getVector();
      }
   }
}
