package code.miaware.inject.chat;

import code.miaware.client.features.modules.player.K724ae88646b9ca0c5d;
import java.util.List;
import net.minecraft.class_2561;
import net.minecraft.class_303;
import net.minecraft.class_338;
import net.minecraft.class_7469;
import net.minecraft.class_7591;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
   value = {class_338.class},
   priority = 1100
)
public class MixinChatHudHelper {
   @Shadow
   @Final
   private List<class_303> field_2061;
   @Shadow
   @Final
   private List<class_303.class_7590> field_2064;

   @Inject(
      method = {"addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void miaware$filterDuplicateMessages(class_2561 message, class_7469 signatureData, class_7591 indicator, CallbackInfo ci) {
      K724ae88646b9ca0c5d helper = K724ae88646b9ca0c5d.getInstance();
      if (helper.isEnabled()) {
         String messageText = message.getString();
         if (!messageText.contains("━") && !messageText.contains("✓") && !messageText.contains("✗") && !messageText.contains("HWID") && !messageText.contains("Подсказка") && !messageText.contains("Friend") && !messageText.contains("Config") && !messageText.contains("Macro") && !messageText.contains("GPS")) {
            class_303 foundMessage = null;

            for(int i = 0; i < Math.min(this.field_2061.size(), 100); ++i) {
               class_303 msg = (class_303)this.field_2061.get(i);
               String msgText = msg.comp_893().getString();
               String msgTextClean = msgText.replaceAll(" §7\\[x\\d+\\]$", "");
               if (msgTextClean.equals(messageText)) {
                  foundMessage = msg;
                  break;
               }
            }

            if (foundMessage != null) {
               int newCount = helper.incrementMessage(messageText);
               this.field_2061.remove(foundMessage);
               int foundCreationTick = foundMessage.comp_892();
               this.field_2064.removeIf((line) -> line.comp_895() == foundCreationTick);
               class_2561 newMessage = class_2561.method_43470(messageText + " §7[x" + newCount + "]");
               ci.cancel();
               ((class_338)this).method_44811(newMessage, signatureData, indicator);
            } else {
               helper.registerNewMessage(messageText);
            }
         }
      }
   }
}
