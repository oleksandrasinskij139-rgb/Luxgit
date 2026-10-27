package code.miaware.inject.other;

import code.miaware.api.event.events.other.K51aaa8d55539784c1d;
import code.miaware.api.event.events.other.K855ca916f0dcdcda01;
import net.minecraft.class_1041;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1041.class})
public class MixinWindow {
   @Shadow
   @Final
   private long field_5187;

   @Inject(
      method = {"onWindowSizeChanged"},
      at = {@At("RETURN")}
   )
   public void windowResizeHook(long window, int width, int height, CallbackInfo ci) {
      K855ca916f0dcdcda01.getInstance().call();
   }

   @Inject(
      method = {"onFramebufferSizeChanged"},
      at = {@At("RETURN")}
   )
   public void framebufferResizeHook(long window, int width, int height, CallbackInfo callbackInfo) {
      if (window == this.field_5187) {
         K51aaa8d55539784c1d.getInstance().call();
      }

   }
}
