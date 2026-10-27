package code.miaware.inject.input;

import code.miaware.api.event.events.client.K256ab2bf60c15ab260;
import code.miaware.api.event.events.client.K3b99645f43150c542e;
import code.miaware.api.event.events.client.K6449169e9fd0c18e12;
import code.miaware.api.event.events.client.K8f3417125c2e5b6c2e;
import code.miaware.api.system.backend.K8e471a90dc4db42e81;
import code.miaware.api.system.draggable.Kd8969ae8f12d70edf3;
import code.miaware.client.features.modules.other.K71265226ea4595dd6d;
import code.miaware.client.features.modules.player.K4d8e14bc1bc511ee65;
import code.miaware.client.features.modules.player.Kc945defa97ab363313;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import code.miaware.client.ui.clickgui.K78e2c0cb4432df2cca;
import code.miaware.client.ui.widget.K5e6ae649a852530a89;
import net.minecraft.class_310;
import net.minecraft.class_312;
import net.minecraft.class_437;
import net.minecraft.class_746;
import net.minecraft.class_3675.class_307;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_312.class})
public class MixinMouse {
   @Final
   @Shadow
   private class_310 field_1779;

   @Inject(
      method = {"lockCursor"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V"
)},
      cancellable = true
   )
   private void lockCursorHook(CallbackInfo ci) {
      class_437 var3 = class_310.method_1551().field_1755;
      if (var3 instanceof K78e2c0cb4432df2cca gui) {
         if (gui.isOpen()) {
            ci.cancel();
         }
      }

   }

   @Redirect(
      method = {"updateMouse"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayerEntity;changeLookDirection(DD)V"
)
   )
   private void onUpdateMouseFreeLook(class_746 player, double x, double y) {
      if (K4d8e14bc1bc511ee65.getInstance().isFreeLookActive()) {
         Kc945defa97ab363313.updateRotation(x, y);
      } else {
         player.method_5872(x, y);
      }

   }

   @Inject(
      method = {"onMouseButton"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/option/InactivityFpsLimiter;onInput()V"
)}
   )
   public void mousePressHook(long window, int button, int action, int mods, CallbackInfo ci) {
      if (K8e471a90dc4db42e81.player() != null) {
         K6449169e9fd0c18e12.getInstance().call(new K256ab2bf60c15ab260(button, action, true));
         if (action == 1 && button == 0 && Kfae29c687712462375.getInstance().isEnabled()) {
            class_310 mc = class_310.method_1551();
            double mx = mc.field_1729.method_1603() / mc.method_22683().method_4495();
            double my = mc.field_1729.method_1604() / mc.method_22683().method_4495();
            if (K5e6ae649a852530a89.getInstance().dispatchHudMouseClick(mx, my, button)) {
               return;
            }
         }

         if (Kfae29c687712462375.getInstance().isEnabled()) {
            class_310 mc = class_310.method_1551();
            double mx = mc.field_1729.method_1603() / mc.method_22683().method_4495();
            double my = mc.field_1729.method_1604() / mc.method_22683().method_4495();
            if (action == 1) {
               K5e6ae649a852530a89.getInstance().dispatchWidgetMouseClick(mx, my, button);
            } else if (action == 0) {
               K5e6ae649a852530a89.getInstance().dispatchWidgetMouseRelease(mx, my, button);
            }
         }

         Kd8969ae8f12d70edf3.getInstance().getDraggables().forEach((s, draggable) -> {
            if (draggable.getModule().isEnabled()) {
               if (action == 0) {
                  draggable.onRelease(button);
               } else if (action == 1) {
                  draggable.onClick(button);
               }
            }

         });
      }
   }

   @Inject(
      method = {"onMouseButton"},
      at = {@At("HEAD")}
   )
   public void onMouseButton(long window, int button, int action, int mods, CallbackInfo ci) {
      if (button != -1 && window == this.field_1779.method_22683().method_4490()) {
         K71265226ea4595dd6d creamyKeys = K71265226ea4595dd6d.getInstance();
         if (creamyKeys != null) {
            creamyKeys.handleInput(class_307.field_1672, button, action);
         }
      }

   }

   @Inject(
      method = {"onMouseScroll"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void onMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
      if (window == this.field_1779.method_22683().method_4490() && K8e471a90dc4db42e81.player() != null) {
         if (K5e6ae649a852530a89.getInstance().resizeHeldWidget(vertical)) {
            ci.cancel();
            return;
         }

         class_310 mc = class_310.method_1551();
         double mouseX = mc.field_1729.method_1603() / mc.method_22683().method_4495();
         double mouseY = mc.field_1729.method_1604() / mc.method_22683().method_4495();
         K3b99645f43150c542e eventData = new K3b99645f43150c542e(mouseX, mouseY, horizontal, vertical);
         K8f3417125c2e5b6c2e.getInstance().call(eventData);
         if (K8f3417125c2e5b6c2e.getInstance().isCancel()) {
            ci.cancel();
         }
      }

   }
}
