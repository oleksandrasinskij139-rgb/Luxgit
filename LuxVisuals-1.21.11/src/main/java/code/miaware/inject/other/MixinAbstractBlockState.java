package code.miaware.inject.other;

import code.miaware.api.event.events.player.move.K43637bb2e0379bf2d1;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_3726;
import net.minecraft.class_4970;
import net.minecraft.class_2350.class_2351;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_4970.class_4971.class})
public abstract class MixinAbstractBlockState {
   @Inject(
      method = {"getCollisionShape(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/ShapeContext;)Lnet/minecraft/util/shape/VoxelShape;"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void removeXZCollision(class_1922 world, class_2338 pos, class_3726 context, CallbackInfoReturnable<class_265> cir) {
      if (K43637bb2e0379bf2d1.getInstance().call()) {
         class_265 original = (class_265)cir.getReturnValue();
         double minY = original.method_1091(class_2351.field_11052);
         double maxY = original.method_1105(class_2351.field_11052);
         if (minY >= maxY) {
            maxY = minY + 0.001;
         }

         class_265 finalShape = class_259.method_17786(class_259.method_1081((double)0.0F, minY, (double)0.0F, (double)0.0F, maxY, (double)0.0F), new class_265[0]);
         cir.setReturnValue(finalShape);
      }

   }
}
