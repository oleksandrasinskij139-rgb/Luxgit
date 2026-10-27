package code.miaware.inject.render;

import code.miaware.api.utils.render.K9f5942af1d8c712eab;
import java.nio.ByteBuffer;
import net.minecraft.class_293;
import net.minecraft.class_296;
import net.minecraft.class_9801;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_9801.class})
public abstract class MixinBuiltBufferCentroids {
   @Inject(
      method = {"collectCentroids"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void lux$reuseCenters(ByteBuffer buffer, int vertexCount, class_293 format, CallbackInfoReturnable<Vector3f[]> cir) {
      Vector3f[] centers = K9f5942af1d8c712eab.collect(buffer, vertexCount, format.method_1362(), format.method_60835(class_296.field_52107));
      if (centers != null) {
         cir.setReturnValue(centers);
      }

   }
}
