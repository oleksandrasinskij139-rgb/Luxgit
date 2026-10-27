package code.miaware.inject.client;

import code.miaware.MiaWare;
import code.miaware.api.event.events.client.K2a56ae9ebefcb944e3;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.system.backend.K8e471a90dc4db42e81;
import code.miaware.api.utils.framelimiter.K477c655086def6a209;
import net.minecraft.class_310;
import net.minecraft.class_542;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_310.class})
public abstract class MixinMinecraftClient {
   @Unique
   private final K477c655086def6a209 frameLimiter = new K477c655086def6a209(false);

   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   public void gameLoopHook(boolean tick, CallbackInfo ci) {
      this.frameLimiter.execute(60, () -> K2a56ae9ebefcb944e3.getInstance().call());
   }

   @Inject(
      method = {"tick"},
      at = {@At("HEAD")}
   )
   public void preTickHook(CallbackInfo ci) {
      if (K8e471a90dc4db42e81.player() != null) {
         Kdee350abc91aa294b2.getInstance().call();
      }
   }

   @Inject(
      method = {"close"},
      at = {@At("HEAD")}
   )
   public void closeHook(CallbackInfo ci) {
      MiaWare.INSTANCE.onClose();
   }

   @Inject(
      method = {"<init>"},
      at = {@At("RETURN")}
   )
   public void initHook(class_542 args, CallbackInfo ci) {
      MiaWare.INSTANCE.postLoad();
   }

   @Inject(
      method = {"getWindowTitle"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void lux$windowTitleHook(CallbackInfoReturnable<String> cir) {
      cir.setReturnValue("lux visuals 1.21.4");
   }
}
