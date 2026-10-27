package dev.luxvisuals.menu.mixin;

import code.miaware.api.command.Kcba974810ca05b56ea;
import code.miaware.api.command.Ke66e08ec83c92514e4;
import net.minecraft.class_310;
import net.minecraft.class_408;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
   value = {class_408.class},
   priority = 500
)
public class LuxChatCommandMixin {
   @Inject(
      method = {"method_44056(Ljava/lang/String;Z)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void luxvisuals$luxCommands(String var1, boolean var2, CallbackInfo var3) {
      try {
         Ke66e08ec83c92514e4 var4 = Ke66e08ec83c92514e4.getInstance();
         String var5 = var4.getPrefix();
         if (var1 == null || !var1.startsWith(var5) || var1.length() <= var5.length()) {
            return;
         }

         String var6 = var1.substring(var5.length()).trim();
         String var7 = var6.split("\\s+", 2)[0];
         boolean var8 = false;

         for(Kcba974810ca05b56ea var10 : var4.getCommands()) {
            if (var7.equalsIgnoreCase(var10.getName())) {
               var8 = true;
               break;
            }
         }

         if (!var8) {
            return;
         }

         if (var2) {
            class_310.method_1551().field_1705.method_1743().method_1803(var1);
         }

         var4.executeCommands(var1, var3);
         if (!var3.isCancelled()) {
            var3.cancel();
         }
      } catch (Throwable var11) {
         System.out.println("[LuxBridge] chat command: " + String.valueOf(var11));
      }

   }
}
