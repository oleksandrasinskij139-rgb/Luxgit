package ru.phantom.mixin.minecraft.client.gui.screen;

import II1II1II1II1II1II1II1II1.I5_i4.IIiI1iiI1l1__I_1i11IiIlIii_;
import II1II1II1II1II1II1II1II1.I5_i4.Il1ll11iiiIIIlI1IliI_1l;
import II1II1II1II1II1II1II1II1.l8_I7.II111_iIl_IlI1I_i_IiIili_i_iI;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_408;
import net.minecraft.class_437;
import net.minecraft.class_4717;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.phantom.Phantom;

@Mixin({class_408.class})
public class ChatScreenMixin extends class_437 implements II111_iIl_IlI1I_i_IiIili_i_iI {
   @Shadow
   protected class_342 field_2382;
   @Shadow
   private class_4717 field_21616;
   @Unique
   private boolean Phantom$chatScreenAnimationApplied;

   protected ChatScreenMixin(class_2561 title) {
      super(title);
   }

   @Inject(
      method = {"method_44056(Ljava/lang/String;Z)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onSendMessage(String text, boolean addToHistory, CallbackInfo ci) {
      if (Phantom.getInstance().getCommandManager().dispatch(text)) {
         mc.field_1705.method_1743().method_1803(text);
         ci.cancel();
      }

   }

   @Inject(
      method = {"method_25394"},
      at = {@At("HEAD")}
   )
   private void Phantom$setupSmoothChat(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      IIiI1iiI1l1__I_1i11IiIlIii_ animations = IIiI1iiI1l1__I_1i11IiIlIii_.i1I_1IlilIll1_l_11_iiIll();
      if (animations != null && animations.shouldSmoothChat()) {
         float value = animations.getChatOpenProgress();
         float offsetY = 24.0F * (1.0F - value);
         if (Math.abs(offsetY) < 0.01F) {
            this.Phantom$chatScreenAnimationApplied = false;
         } else {
            this.Phantom$chatScreenAnimationApplied = true;
            context.method_51448().method_22903();
            context.method_51448().method_46416(0.0F, offsetY, 0.0F);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
         }
      } else {
         this.Phantom$chatScreenAnimationApplied = false;
      }
   }

   @Inject(
      method = {"method_25394"},
      at = {@At("TAIL")}
   )
   public void onRender(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      context.method_51452();
      if (this.Phantom$chatScreenAnimationApplied) {
         context.method_51448().method_22909();
         RenderSystem.disableBlend();
         this.Phantom$chatScreenAnimationApplied = false;
      }

      Phantom.getInstance().getDraggableManager().updateDraggble((float)mouseX, (float)mouseY);
      Phantom.getInstance().getHudManager().renderOverlay(context, mouseX, mouseY);
      context.method_51452();
   }

   @Inject(
      method = {"method_25401"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void Phantom$onMouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount, CallbackInfoReturnable<Boolean> cir) {
      if (Phantom.getInstance().getHudManager().mouseScrolled(mouseX, mouseY, verticalAmount)) {
         cir.setReturnValue(true);
      }

   }

   @Inject(
      method = {"method_25402"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onMouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
      if (Il1ll11iiiIIIlI1IliI_1l.I_IlI_l_Ii1_Il1IIi1_I1I_(mouseX, mouseY, button)) {
         cir.setReturnValue(true);
      } else if (Phantom.getInstance().getHudManager().click((int)mouseX, (int)mouseY, button)) {
         cir.setReturnValue(true);
      } else {
         Phantom.getInstance().getDraggableManager().mouseClick((float)mouseX, (float)mouseY, button, true);
      }
   }

   public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
      return Phantom.getInstance().getHudManager().drag((int)mouseX, (int)mouseY, button) ? true : super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
   }

   public boolean method_25406(double mouseX, double mouseY, int button) {
      Il1ll11iiiIIIlI1IliI_1l.lli_il1i1li_IIiI_iiiIl1I1iI_il();
      Phantom.getInstance().getHudManager().drag((int)mouseX, (int)mouseY, button);
      Phantom.getInstance().getHudManager().release(button);
      Phantom.getInstance().getDraggableManager().mouseRelease();
      return super.method_25406(mouseX, mouseY, button);
   }

   @Inject(
      method = {"method_25432"},
      at = {@At("HEAD")}
   )
   private void onRemoved(CallbackInfo ci) {
      Il1ll11iiiIIIlI1IliI_1l.lli_il1i1li_IIiI_iiiIl1I1iI_il();
      Phantom.getInstance().getHudManager().release(-1);
      Phantom.getInstance().getDraggableManager().mouseRelease();
   }
}
