package code.miaware.inject.other;

import code.miaware.client.features.modules.player.K1cb52ce4c79b2f47f0;
import code.miaware.client.features.modules.player.chestsorter.K72972877a534c10b45;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_332;
import net.minecraft.class_465;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_465.class})
public abstract class MixinHandledScreenChestSorter {
   @Shadow
   protected int field_2776;
   @Shadow
   protected int field_2800;
   @Shadow
   protected int field_2792;
   @Unique
   private K72972877a534c10b45 lux$chestSorter;

   @Unique
   private K72972877a534c10b45 lux$sorterUi() {
      K1cb52ce4c79b2f47f0 module = K1cb52ce4c79b2f47f0.getInstance();
      class_465<?> screen = (class_465)this;
      if (!module.canSort(screen.method_17577())) {
         if (this.lux$chestSorter != null) {
            this.lux$chestSorter.close();
            this.lux$chestSorter = null;
         }

         return null;
      } else {
         if (this.lux$chestSorter == null) {
            this.lux$chestSorter = new K72972877a534c10b45(screen);
         }

         return this.lux$chestSorter;
      }
   }

   @Unique
   private void lux$closeSorter() {
      if (this.lux$chestSorter != null) {
         this.lux$chestSorter.close();
         this.lux$chestSorter = null;
      }

   }

   @Inject(
      method = {"init"},
      at = {@At("HEAD")}
   )
   private void lux$resetSorter(CallbackInfo ci) {
      this.lux$closeSorter();
   }

   @Inject(
      method = {"render"},
      at = {@At("TAIL")}
   )
   private void lux$renderSorter(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      K72972877a534c10b45 ui = this.lux$sorterUi();
      if (ui != null) {
         ui.render(context, mouseX, mouseY, delta, this.field_2776, this.field_2800, this.field_2792);
      }

   }

   @Inject(
      method = {"mouseClicked"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$sorterClick(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
      K72972877a534c10b45 ui = this.lux$sorterUi();
      if (ui != null && ui.click(mouseX, mouseY, button)) {
         cir.setReturnValue(true);
      }

   }

   @Inject(
      method = {"onMouseClick(Lnet/minecraft/screen/slot/Slot;IILnet/minecraft/screen/slot/SlotActionType;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$protectSort(class_1735 slot, int id, int button, class_1713 action, CallbackInfo ci) {
      K1cb52ce4c79b2f47f0 module = K1cb52ce4c79b2f47f0.getInstance();
      if (module.isEnabled() && module.getSorter().active()) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"keyPressed"},
      at = {@At("HEAD")}
   )
   private void lux$sorterKey(int key, int scan, int modifiers, CallbackInfoReturnable<Boolean> cir) {
      if (this.lux$chestSorter != null) {
         this.lux$chestSorter.key();
      }

   }

   @Inject(
      method = {"drawMouseoverTooltip"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$sorterTooltip(class_332 context, int mouseX, int mouseY, CallbackInfo ci) {
      if (this.lux$chestSorter != null && this.lux$chestSorter.consumesTooltip(mouseX, mouseY)) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"removed"},
      at = {@At("HEAD")}
   )
   private void lux$closeSorter(CallbackInfo ci) {
      this.lux$closeSorter();
   }
}
