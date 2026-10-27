package code.miaware.inject.client;

import code.miaware.client.features.modules.player.K28fed1fbb8f6d6c65a;
import code.miaware.client.ui.screen.K4fb532bfd9466813ac;
import net.minecraft.class_156;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_433;
import net.minecraft.class_437;
import net.minecraft.class_442;
import net.minecraft.class_500;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_433.class})
public class MixinGameMenuScreen extends class_437 {
   protected MixinGameMenuScreen(class_2561 title) {
      super(title);
   }

   @Inject(
      method = {"initWidgets"},
      at = {@At("RETURN")}
   )
   private void onInitWidgets(CallbackInfo ci) {
      int buttonWidth = 98;
      int buttonHeight = 20;
      int leftX = this.field_22789 / 2 - 102;
      int rightX = this.field_22789 / 2 + 4;
      int y = this.field_22790 / 4 + 120 + 10;
      class_4185 disconnectButton = null;

      for(class_364 element : this.method_25396()) {
         if (element instanceof class_4185 button) {
            String buttonText = button.method_25369().getString();
            if (buttonText.contains("Отключиться") || buttonText.contains("Disconnect")) {
               disconnectButton = button;
               break;
            }
         }
      }

      if (disconnectButton != null) {
         int origX = disconnectButton.method_46426();
         int origY = disconnectButton.method_46427();
         int origWidth = disconnectButton.method_25368();
         int origHeight = disconnectButton.method_25364();
         class_2561 origMessage = disconnectButton.method_25369();
         this.method_37066(disconnectButton);
         this.method_37063(class_4185.method_46430(origMessage, (btn) -> {
            K28fed1fbb8f6d6c65a antiLeave = K28fed1fbb8f6d6c65a.getInstance();
            class_310 client = class_310.method_1551();
            if (antiLeave != null && antiLeave.shouldConfirmDisconnect()) {
               K4fb532bfd9466813ac confirmScreen = new K4fb532bfd9466813ac((class_433)this, () -> {
                  boolean singleplayer = client.method_1542();
                  client.field_1687.method_8525();
                  if (singleplayer) {
                     client.method_56134(new class_442());
                  } else {
                     client.method_56134(new class_500(new class_442()));
                  }

               });
               client.method_1507(confirmScreen);
            } else {
               boolean singleplayer = client.method_1542();
               client.field_1687.method_8525();
               if (singleplayer) {
                  client.method_56134(new class_442());
               } else {
                  client.method_56134(new class_500(new class_442()));
               }
            }

         }).method_46434(origX, origY, origWidth, origHeight).method_46431());
      }

   }

   // $FF: synthetic method
   private static void lambda$onInitWidgets$1(class_4185 button) {
      class_156.method_668().method_670("https://t.me/K3dVisuals");
   }

   // $FF: synthetic method
   private static void lambda$onInitWidgets$0(class_4185 button) {
      class_156.method_668().method_670("https://discord.gg/9tvBFX4sfC");
   }
}
