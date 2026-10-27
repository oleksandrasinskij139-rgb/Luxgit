package code.miaware.inject.other;

import code.miaware.api.event.events.player.world.K01bdc111f7439dc8b5;
import code.miaware.api.event.events.player.world.K7379e4fff6201d1fcf;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_2248.class})
public class MixinBlock {
   @Inject(
      method = {"onPlaced"},
      at = {@At("HEAD")}
   )
   public void blockPlaceHook(class_1937 world, class_2338 pos, class_2680 state, class_1309 placer, class_1799 itemStack, CallbackInfo callbackInfo) {
      K7379e4fff6201d1fcf.getInstance().call(new K01bdc111f7439dc8b5((class_2248)this, state, pos, placer));
   }
}
