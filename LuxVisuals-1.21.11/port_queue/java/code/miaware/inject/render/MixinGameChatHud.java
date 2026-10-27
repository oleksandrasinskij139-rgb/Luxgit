package code.miaware.inject.render;

import code.miaware.client.features.modules.player.K28fed1fbb8f6d6c65a;
import code.miaware.client.features.modules.player.Kc554a5d3999b48bf50;
import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import code.miaware.client.ui.widget.K5e6ae649a852530a89;
import code.miaware.client.ui.widget.overlay.Kc9c316c9999e00cc23;
import net.minecraft.class_2561;
import net.minecraft.class_338;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_338.class})
public class MixinGameChatHud {
   @Inject(
      method = {"addMessage(Lnet/minecraft/text/Text;)V"},
      at = {@At("TAIL")}
   )
   private void onAddMessage(class_2561 message, CallbackInfo ci) {
      if (message != null) {
         String messageStr = message.getString();
         if (messageStr != null && !messageStr.isEmpty()) {
            try {
               K28fed1fbb8f6d6c65a antiLeave = K28fed1fbb8f6d6c65a.getInstance();
               if (antiLeave != null) {
                  antiLeave.onChatMessage(messageStr);
               }
            } catch (Exception var7) {
            }

            try {
               Kc554a5d3999b48bf50 rwJoiner = Kc554a5d3999b48bf50.getInstance();
               if (rwJoiner != null) {
                  rwJoiner.onChatMessage(messageStr);
               }
            } catch (Exception var6) {
            }

            try {
               Kc9c316c9999e00cc23 chatWidget = this.findChatWidget();
               if (chatWidget != null && chatWidget.isEnabled() && chatWidget.shouldAcceptMessage(messageStr)) {
                  chatWidget.addMessage(messageStr);
               }
            } catch (Exception var5) {
            }

         }
      }
   }

   private Kc9c316c9999e00cc23 findChatWidget() {
      try {
         K5e6ae649a852530a89 widgetManager = K5e6ae649a852530a89.getInstance();
         if (widgetManager != null) {
            for(K548ca8198549ddf9d7 widget : widgetManager.getWidgets()) {
               if (widget instanceof Kc9c316c9999e00cc23) {
                  return (Kc9c316c9999e00cc23)widget;
               }
            }
         }
      } catch (Exception var4) {
      }

      return null;
   }
}
