package code.miaware.inject.render;

import code.miaware.api.utils.render.K9f5942af1d8c712eab;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_4597;
import net.minecraft.class_8251;
import net.minecraft.class_9799;
import net.minecraft.class_9801;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_4597.class_4598.class})
public abstract class MixinImmediateQuadSort {
   @WrapOperation(
      method = {"draw(Lnet/minecraft/client/render/RenderLayer;Lnet/minecraft/client/render/BufferBuilder;)V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/BuiltBuffer;sortQuads(Lnet/minecraft/client/util/BufferAllocator;Lcom/mojang/blaze3d/systems/VertexSorter;)Lnet/minecraft/client/render/BuiltBuffer$SortState;"
)}
   )
   private class_9801.class_9802 lux$reuseImmediateCenters(class_9801 buffer, class_9799 allocator, class_8251 sorter, Operation<class_9801.class_9802> original) {
      K9f5942af1d8c712eab.enter();

      Object var5;
      try {
         original.call(new Object[]{buffer, allocator, sorter});
         var5 = null;
      } finally {
         K9f5942af1d8c712eab.leave();
      }

      return (class_9801.class_9802)var5;
   }
}
