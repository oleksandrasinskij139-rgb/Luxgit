package code.miaware.inject.other;

import code.miaware.api.event.events.other.Kbec6a700ec92806750;
import code.miaware.api.event.events.other.Kd85c1184f6b82de9cb;
import code.miaware.api.system.backend.K8e471a90dc4db42e81;
import code.miaware.api.system.backend.Kd9271a319f911decbe;
import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import code.miaware.client.features.modules.other.K1bd34fc1db8d360bd3;
import code.miaware.client.features.modules.render.K6a4053265563878282;
import code.miaware.client.features.modules.render.Kacf94aeb4cdc6e13ca;
import code.miaware.client.features.modules.render.shulkerview.Kfb1cafeb5e8b3204dd;
import net.minecraft.class_1703;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3675;
import net.minecraft.class_3936;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_465;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_465.class})
public abstract class MixinHandledScreen<T extends class_1703> extends class_437 implements class_3936<T> {
   @Shadow
   protected class_1735 field_2787;
   @Unique
   private final Kf39dc27225f4d9fe6e timerUtil = new Kf39dc27225f4d9fe6e();
   @Unique
   private long openTime = 0L;
   @Unique
   private static final long INVENTORY_ANIMATION_DURATION = 250L;

   protected MixinHandledScreen(class_2561 title) {
      super(title);
   }

   @Shadow
   protected abstract boolean method_2387(class_1735 var1, double var2, double var4);

   @Shadow
   protected abstract void method_2383(class_1735 var1, int var2, int var3, class_1713 var4);

   @Inject(
      method = {"init"},
      at = {@At("TAIL")}
   )
   private void onInit(CallbackInfo ci) {
      this.openTime = System.currentTimeMillis();
      Kbec6a700ec92806750 event = new Kbec6a700ec92806750(this);
      Kd85c1184f6b82de9cb.getInstance().call(event);

      for(class_4185 button : event.buttons()) {
         this.method_37063(button);
      }

   }

   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   private void applyInventoryAnimation(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      if (Kacf94aeb4cdc6e13ca.getInstance().isEnabled() && (Boolean)Kacf94aeb4cdc6e13ca.getInstance().getInventoryAnimation().getValue()) {
         if (this.getClass().getSimpleName().equals("InventoryScreen")) {
            long elapsed = System.currentTimeMillis() - this.openTime;
            if (elapsed < 250L) {
               float progress = (float)elapsed / 250.0F;
               progress = 1.0F - (float)Math.pow((double)(1.0F - progress), (double)3.0F);
               int screenHeight = class_310.method_1551().method_22683().method_4502();
               float yOffset = (float)screenHeight * (1.0F - progress);
               context.method_51448().method_22903();
               context.method_51448().method_46416(0.0F, yOffset, 0.0F);
            }
         }
      }
   }

   @Inject(
      method = {"render"},
      at = {@At("RETURN")}
   )
   private void removeInventoryAnimation(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      if (Kacf94aeb4cdc6e13ca.getInstance().isEnabled() && (Boolean)Kacf94aeb4cdc6e13ca.getInstance().getInventoryAnimation().getValue()) {
         if (this.getClass().getSimpleName().equals("InventoryScreen")) {
            long elapsed = System.currentTimeMillis() - this.openTime;
            if (elapsed < 250L) {
               context.method_51448().method_22909();
            }
         }
      }
   }

   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   private void drawScreenHook(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      if (K8e471a90dc4db42e81.player() != null) {
         K1bd34fc1db8d360bd3 mouseTweaks = K1bd34fc1db8d360bd3.getInstance();
         if (mouseTweaks.isEnabled() && this.shouldUse() && this.mouseIsHolding()) {
            for(int i = 0; i < K8e471a90dc4db42e81.player().field_7512.field_7761.size(); ++i) {
               class_1735 slot = (class_1735)K8e471a90dc4db42e81.player().field_7512.field_7761.get(i);
               if (this.method_2387(slot, (double)mouseX, (double)mouseY) && slot.method_7682() && this.timerUtil.finished(mouseTweaks.delay.getValue().longValue())) {
                  this.method_2383(slot, slot.field_7874, 0, class_1713.field_7794);
                  this.timerUtil.reset();
               }
            }

         }
      }
   }

   @Unique
   private boolean shouldUse() {
      long handle = class_310.method_1551().method_22683().method_4490();
      class_3675.class_306 sneakKey = class_3675.method_15981(class_310.method_1551().field_1690.field_1832.method_1428());
      return class_3675.method_15987(handle, sneakKey.method_1444());
   }

   @Inject(
      method = {"drawSlot(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/screen/slot/Slot;)V"},
      at = {@At("TAIL")}
   )
   protected void drawSlotHook(class_332 context, class_1735 slot, CallbackInfo ci) {
   }

   @Inject(
      method = {"render"},
      at = {@At("RETURN")}
   )
   private void shulkerview$renderHoveredPreview(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      if (K6a4053265563878282.getInstance().shouldRenderScreenPreview()) {
         Kfb1cafeb5e8b3204dd.getInstance().renderHoveredShulkerPreview((class_465)this, context, mouseX, mouseY, this.field_2787, this.field_22789, this.field_22790);
      }
   }

   @Inject(
      method = {"drawMouseoverTooltip"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void shulkerview$cancelShulkerTooltip(class_332 context, int x, int y, CallbackInfo ci) {
      if (K6a4053265563878282.getInstance().shouldRenderScreenPreview()) {
         if (this.field_2787 != null && this.field_2787.method_7681() && Kfb1cafeb5e8b3204dd.isShulker(this.field_2787.method_7677())) {
            ci.cancel();
         }

      }
   }

   @Unique
   private boolean mouseIsHolding() {
      return Kd9271a319f911decbe.isPressed(-100);
   }
}
