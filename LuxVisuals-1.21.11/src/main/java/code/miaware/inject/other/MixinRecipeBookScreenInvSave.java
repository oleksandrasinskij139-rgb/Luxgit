package code.miaware.inject.other;

import code.miaware.client.features.modules.player.invsave.K38d3ac4077d522798c;
import code.miaware.client.features.modules.player.invsave.K3e7c95e382ed62da0e;
import net.minecraft.class_10260;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_10260.class})
public abstract class MixinRecipeBookScreenInvSave {
   @Unique
   private K38d3ac4077d522798c lux$managerInput() {
      return ((K3e7c95e382ed62da0e)this).lux$inventoryManager();
   }

   @Inject(
      method = {"charTyped"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$managerCharacter(char c, int modifiers, CallbackInfoReturnable<Boolean> cir) {
      K38d3ac4077d522798c ui = this.lux$managerInput();
      if (ui != null && ui.character(c, modifiers)) {
         cir.setReturnValue(true);
      }

   }

   @Inject(
      method = {"keyPressed"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$managerKeyFirst(int key, int scan, int modifiers, CallbackInfoReturnable<Boolean> cir) {
      K38d3ac4077d522798c ui = this.lux$managerInput();
      if (ui != null && ui.key(key, scan, modifiers)) {
         cir.setReturnValue(true);
      }

   }

   @Inject(
      method = {"mouseClicked"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$managerClickFirst(double mx, double my, int button, CallbackInfoReturnable<Boolean> cir) {
      K38d3ac4077d522798c ui = this.lux$managerInput();
      if (ui != null && ui.click(mx, my, button)) {
         cir.setReturnValue(true);
      }

   }
}
