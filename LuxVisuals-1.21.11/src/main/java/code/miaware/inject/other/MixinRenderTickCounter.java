package code.miaware.inject.other;

import code.miaware.api.system.client.K075888bb470f86bd78;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_9779.class_9781.class})
public class MixinRenderTickCounter {
   @Shadow
   private float field_51958;

   @Inject(
      method = {"beginRenderTick(J)I"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/client/render/RenderTickCounter$Dynamic;lastFrameDuration:F",
   shift = Shift.AFTER
)}
   )
   private void timer(CallbackInfoReturnable<Integer> callback) {
      float customTimer = K075888bb470f86bd78.getInstance().getTimerSpeed();
      if (customTimer > 0.0F) {
         this.field_51958 *= customTimer;
      }

   }
}
