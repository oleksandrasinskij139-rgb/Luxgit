package code.miaware.inject.client;

import code.miaware.api.command.Ke66e08ec83c92514e4;
import code.miaware.api.utils.other.Kb1ccce79ff543c4410;
import code.miaware.client.features.modules.other.Kaced9f283cac8188b0;
import code.miaware.client.ui.screen.Kea0f5fac15ba91019e;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.suggestion.Suggestions;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_2172;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_342;
import net.minecraft.class_437;
import net.minecraft.class_4717;
import net.minecraft.class_5481;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_4717.class})
public abstract class MixinChatInputSuggestor {
   @Final
   @Shadow
   class_342 field_21599;
   @Shadow
   boolean field_21614;
   @Shadow
   private ParseResults<class_2172> field_21610;
   @Shadow
   private CompletableFuture<Suggestions> field_21611;
   @Shadow
   private class_4717.class_464 field_21612;
   @Shadow
   @Final
   private List<class_5481> field_21607;

   @Shadow
   protected abstract void method_23937();

   @Unique
   private boolean lux$privateCommand() {
      class_437 var2 = class_310.method_1551().field_1755;
      boolean var10000;
      if (var2 instanceof Kea0f5fac15ba91019e access) {
         if (access.lux$isChatInput(this.field_21599) && Kaced9f283cac8188b0.getInstance().hides(this.field_21599.method_1882())) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   @Inject(
      method = {"provideRenderText"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$privateRenderText(String text, int firstCharacterIndex, CallbackInfoReturnable<class_5481> cir) {
      if (this.lux$privateCommand()) {
         cir.setReturnValue(class_2561.method_43470(Kb1ccce79ff543c4410.visibleSlice(this.field_21599.method_1882(), firstCharacterIndex, text.length())).method_30937());
      }

   }

   @Inject(
      method = {"refresh"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$clearPrivateSuggestions(CallbackInfo ci) {
      if (this.lux$privateCommand()) {
         this.field_21612 = null;
         this.field_21610 = null;
         this.field_21611 = Suggestions.empty();
         this.field_21607.clear();
         this.field_21599.method_1887((String)null);
         ci.cancel();
      }
   }

   @Inject(
      method = {"showCommandSuggestions", "renderMessages", "render"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$hidePrivateSuggestions(CallbackInfo ci) {
      if (this.lux$privateCommand()) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"tryRenderWindow"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$hidePrivateWindow(CallbackInfoReturnable<Boolean> cir) {
      if (this.lux$privateCommand()) {
         cir.setReturnValue(false);
      }

   }

   @Inject(
      method = {"refresh"},
      at = {@At(
   value = "INVOKE",
   target = "Lcom/mojang/brigadier/StringReader;canRead()Z",
   remap = false
)},
      cancellable = true
   )
   public void onRefresh(CallbackInfo callbackInfo, @Local StringReader reader) {
      if (reader.canRead(Ke66e08ec83c92514e4.getInstance().getPrefix().length()) && reader.getString().startsWith(Ke66e08ec83c92514e4.getInstance().getPrefix(), reader.getCursor())) {
         reader.setCursor(reader.getCursor() + 1);
         if (this.field_21610 == null) {
            this.field_21610 = Ke66e08ec83c92514e4.getInstance().getDispatcher().parse(reader, Ke66e08ec83c92514e4.getInstance().getSource());
         }

         int cursor = this.field_21599.method_1881();
         if (cursor >= 1 && (this.field_21612 == null || !this.field_21614)) {
            this.field_21611 = Ke66e08ec83c92514e4.getInstance().getDispatcher().getCompletionSuggestions(this.field_21610, cursor);
            this.field_21611.thenRun(() -> {
               if (this.field_21611.isDone()) {
                  this.method_23937();
               }

            });
         }

         callbackInfo.cancel();
      }

   }
}
