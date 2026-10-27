package code.miaware.inject.chat;

import code.miaware.client.features.modules.other.chatheads.Kff6743acd552d72675;
import com.mojang.authlib.GameProfile;
import java.time.Instant;
import net.minecraft.class_2556;
import net.minecraft.class_2561;
import net.minecraft.class_7471;
import net.minecraft.class_7594;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_7594.class})
public class MixinMessageHandler {
   @Inject(
      method = {"processChatMessageInternal"},
      at = {@At("HEAD")}
   )
   private void miaware$preparePlayerHeadData(class_2556.class_7602 params, class_7471 message, class_2561 decorated, GameProfile sender, boolean onlyShowSecureChat, Instant receptionTimestamp, CallbackInfoReturnable<Boolean> cir) {
      Kff6743acd552d72675.preparePlayerMessage(sender, decorated);
   }

   @Inject(
      method = {"processChatMessageInternal"},
      at = {@At("RETURN")}
   )
   private void miaware$clearPlayerHeadData(class_2556.class_7602 params, class_7471 message, class_2561 decorated, GameProfile sender, boolean onlyShowSecureChat, Instant receptionTimestamp, CallbackInfoReturnable<Boolean> cir) {
      Kff6743acd552d72675.clearIncomingHeadData();
   }

   @Inject(
      method = {"onGameMessage"},
      at = {@At("HEAD")}
   )
   private void miaware$prepareSystemHeadData(class_2561 message, boolean overlay, CallbackInfo ci) {
      Kff6743acd552d72675.prepareSystemMessage(message, overlay);
   }

   @Inject(
      method = {"onGameMessage"},
      at = {@At("RETURN")}
   )
   private void miaware$clearSystemHeadData(class_2561 message, boolean overlay, CallbackInfo ci) {
      Kff6743acd552d72675.clearIncomingHeadData();
   }
}
