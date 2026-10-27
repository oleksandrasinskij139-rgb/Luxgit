package code.miaware.inject.chat;

import code.miaware.api.utils.other.Kb1ccce79ff543c4410;
import code.miaware.client.features.modules.other.Kaced9f283cac8188b0;
import code.miaware.client.ui.screen.Kea0f5fac15ba91019e;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_342;
import net.minecraft.class_437;
import net.minecraft.class_5250;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_342.class})
public abstract class MixinPrivateChatTextField {
   @Unique
   private boolean lux$isProtected(String text) {
      class_437 var3 = class_310.method_1551().field_1755;
      boolean var10000;
      if (var3 instanceof Kea0f5fac15ba91019e access) {
         if (access.lux$isChatInput(this) && Kaced9f283cac8188b0.getInstance().hides(text)) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   @ModifyExpressionValue(
      method = {"renderWidget", "onClick", "updateFirstCharacterIndex", "getCharacterX"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;text:Ljava/lang/String;"
)}
   )
   private String lux$maskDisplayedText(String original) {
      return this.lux$isProtected(original) ? Kb1ccce79ff543c4410.mask(original) : original;
   }

   @Inject(
      method = {"getNarrationMessage"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$maskChatNarration(CallbackInfoReturnable<class_5250> cir) {
      String input = ((class_342)this).method_1882();
      if (this.lux$isProtected(input)) {
         cir.setReturnValue(class_2561.method_43470(Kb1ccce79ff543c4410.mask(input)));
      }

   }
}
