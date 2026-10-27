package ru.phantom.mixin.minecraft.client.input;

import com.mojang.brigadier.suggestion.Suggestions;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import net.minecraft.class_342;
import net.minecraft.class_4717;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.phantom.Phantom;

@Mixin({class_4717.class})
public abstract class ChatInputSuggestorMixin {
   @Shadow
   @Final
   class_342 field_21599;
   @Shadow
   private CompletableFuture<Suggestions> field_21611;
   @Shadow
   private @Nullable class_4717.class_464 field_21612;

   @Shadow
   public abstract void method_23920(boolean var1);

   @Inject(
      method = {"method_23934"},
      at = {@At(
   value = "INVOKE",
   target = "Lcom/mojang/brigadier/StringReader;canRead()Z",
   remap = false
)},
      cancellable = true
   )
   private void injectAutoCompletion(CallbackInfo ci) {
      String text = this.field_21599.method_1882();
      String prefix = Phantom.getInstance().getCommandManager().getPrefix();
      if (text.startsWith(prefix)) {
         this.field_21611 = Phantom.getInstance().getCommandManager().autoComplete(text, this.field_21599.method_1881());
         this.field_21611.thenRun(() -> {
            try {
               if (this.field_21611.isDone() && !((Suggestions)this.field_21611.get()).isEmpty() && this.field_21612 == null) {
                  this.method_23920(false);
                  ci.cancel();
               }
            } catch (InterruptedException | ExecutionException var3) {
            }

         });
      }

   }
}
