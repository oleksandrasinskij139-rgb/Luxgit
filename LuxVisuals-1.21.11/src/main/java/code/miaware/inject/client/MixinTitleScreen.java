package code.miaware.inject.client;

import dev.luxvisuals.menu.PhantomMainMenu;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_442;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_442.class})
public class MixinTitleScreen extends class_437 {
   protected MixinTitleScreen(class_2561 var1) {
      super(var1);
   }

   @Inject(
      method = {"method_25426"},
      at = {@At("HEAD")},
      cancellable = true,
      require = 0
   )
   private void lux$phantomMenu(CallbackInfo var1) {
      if (PhantomMainMenu.phantomScreen) {
         try {
            class_310.method_1551().method_1507(new PhantomMainMenu());
            var1.cancel();
         } catch (Throwable var3) {
            PhantomMainMenu.phantomScreen = false;
            var3.printStackTrace();
         }

      }
   }

   @Inject(
      method = {"method_25426"},
      at = {@At("TAIL")},
      require = 0
   )
   private void lux$menuButton(CallbackInfo var1) {
      this.method_37063(class_4185.method_46430(class_2561.method_30163("LuxVisuals Menu"), (var0) -> {
         PhantomMainMenu.phantomScreen = true;
         class_310.method_1551().method_1507(new PhantomMainMenu());
      }).method_46434(10, 10, 90, 20).method_46431());
   }
}
