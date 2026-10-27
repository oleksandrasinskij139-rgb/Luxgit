package code.miaware.inject.other;

import code.miaware.client.features.modules.player.Ke3c45c7c5ab01f24df;
import code.miaware.client.features.modules.player.invsave.K38d3ac4077d522798c;
import code.miaware.client.features.modules.player.invsave.K3e7c95e382ed62da0e;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_465;
import net.minecraft.class_490;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_465.class})
public abstract class MixinHandledScreenInvSave extends class_437 implements K3e7c95e382ed62da0e {
   @Shadow
   protected int field_2776;
   @Shadow
   protected int field_2800;
   @Shadow
   protected int field_2792;
   @Unique
   private K38d3ac4077d522798c lux$manager;

   protected MixinHandledScreenInvSave(class_2561 title) {
      super(title);
   }

   public K38d3ac4077d522798c lux$inventoryManager() {
      if (Ke3c45c7c5ab01f24df.getInstance().isEnabled() && this.getClass() == class_490.class) {
         if (this.lux$manager == null) {
            this.lux$manager = new K38d3ac4077d522798c((class_465)this);
         }

         return this.lux$manager;
      } else {
         this.lux$releaseManager();
         return null;
      }
   }

   @Unique
   private void lux$releaseManager() {
      if (this.lux$manager != null) {
         this.lux$manager.close();
         this.lux$manager = null;
      }

   }

   @Inject(
      method = {"init"},
      at = {@At("HEAD")}
   )
   private void lux$initManager(CallbackInfo ci) {
      this.lux$releaseManager();
   }

   @Inject(
      method = {"render"},
      at = {@At("TAIL")}
   )
   private void lux$renderManager(class_332 context, int mx, int my, float delta, CallbackInfo ci) {
      K38d3ac4077d522798c ui = this.lux$inventoryManager();
      if (ui != null) {
         ui.render(context, mx, my, delta, this.field_2776, this.field_2800, this.field_2792);
      }

   }

   @Inject(
      method = {"mouseClicked"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$managerClick(double mx, double my, int button, CallbackInfoReturnable<Boolean> cir) {
      K38d3ac4077d522798c ui = this.lux$inventoryManager();
      if (ui != null && ui.click(mx, my, button)) {
         cir.setReturnValue(true);
      }

   }

   @Inject(
      method = {"mouseReleased"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$managerRelease(double mx, double my, int button, CallbackInfoReturnable<Boolean> cir) {
      if (this.lux$manager != null && this.lux$manager.open()) {
         cir.setReturnValue(true);
      }

   }

   @Inject(
      method = {"onMouseClick(Lnet/minecraft/screen/slot/Slot;IILnet/minecraft/screen/slot/SlotActionType;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$protectManager(class_1735 slot, int id, int button, class_1713 action, CallbackInfo ci) {
      if (this.lux$manager != null && (this.lux$manager.open() || Ke3c45c7c5ab01f24df.getInstance().getArranger().active())) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"keyPressed"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$managerKey(int key, int scan, int modifiers, CallbackInfoReturnable<Boolean> cir) {
      K38d3ac4077d522798c ui = this.lux$inventoryManager();
      if (ui != null && ui.key(key, scan, modifiers)) {
         cir.setReturnValue(true);
      }

   }

   public boolean method_25400(char character, int modifiers) {
      K38d3ac4077d522798c ui = this.lux$inventoryManager();
      return ui != null && ui.character(character, modifiers) || super.method_25400(character, modifiers);
   }

   @Inject(
      method = {"mouseScrolled"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$managerScroll(double mx, double my, double horizontal, double vertical, CallbackInfoReturnable<Boolean> cir) {
      K38d3ac4077d522798c ui = this.lux$inventoryManager();
      if (ui != null && ui.scroll(mx, my, vertical)) {
         cir.setReturnValue(true);
      }

   }

   @Inject(
      method = {"drawMouseoverTooltip"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$managerTooltip(class_332 context, int mx, int my, CallbackInfo ci) {
      if (this.lux$manager != null && this.lux$manager.consumesTooltip(mx, my)) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"removed"},
      at = {@At("HEAD")}
   )
   private void lux$removeManager(CallbackInfo ci) {
      this.lux$releaseManager();
   }
}
