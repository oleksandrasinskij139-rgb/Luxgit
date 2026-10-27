package ru.phantom.mixin.minecraft.client.gui.screen;

import II1II1II1II1II1II1II1II1.I2_i1.llIli11lI1__1_1_liI1lI_l_1i__1_;
import II1II1II1II1II1II1II1II1.I5_i4.IIiI1iiI1l1__I_1i11IiIlIii_;
import II1II1II1II1II1II1II1II1.I5_i4.Illi_lI1l___i_iII1_IliIIIII1lI;
import II1II1II1II1II1II1II1II1.I5_i4.ilI1_l1II1II__1ii1_il_liIIl1;
import II1II1II1II1II1II1II1II1.l2_I1.il_iII1__Il1illlI_iliI1II1l1;
import II1II1II1II1II1II1II1II1.l8_I7.II_iil_I_1iIiIi__li1_i;
import II1II1II1II1II1II1II1II1.l8_I7.i_ii_I1iI1iiI1_I1____1il11;
import II1II1II1II1II1II1II1II1.l8_I7.l1_1Iill_11iIl_l1lliII1I1I_I;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.class_1735;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_437;
import net.minecraft.class_465;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.phantom.Phantom;

@Mixin({class_465.class})
public abstract class HandledScreenMixin extends class_437 implements l1_1Iill_11iIl_l1lliII1I1I_I {
   @Shadow
   public int field_2792;
   @Shadow
   public int field_2779;
   @Shadow
   protected @Nullable class_1735 field_2787;
   @Unique
   private static final float PHANTOM$OPEN_MS = 120.0F;
   @Unique
   private static final float PHANTOM$CLOSE_MS = 80.0F;
   @Unique
   private long phantom$openedAt;
   @Unique
   private boolean phantom$closing;
   @Unique
   private long phantom$closingStartedAt;
   @Unique
   private boolean phantom$transformed;
   @Unique
   private final Map<class_1735, Float> phantom$slotHoverScales = new WeakHashMap();
   @Unique
   private boolean phantom$slotScaled;

   @Shadow
   protected abstract void method_2389(class_332 var1, float var2, int var3, int var4);

   protected HandledScreenMixin(class_2561 title) {
      super(title);
   }

   public boolean phantom$isTransformed() {
      return this.phantom$transformed;
   }

   public void phantom$setTransformed(boolean transformed) {
      this.phantom$transformed = transformed;
   }

   @Inject(
      method = {"method_25426()V"},
      at = {@At("HEAD")}
   )
   private void Phantom$onInit(CallbackInfo ci) {
      this.phantom$openedAt = System.currentTimeMillis();
      this.phantom$closing = false;
      this.phantom$closingStartedAt = 0L;
      this.phantom$transformed = false;
      this.phantom$slotHoverScales.clear();
   }

   @Inject(
      method = {"method_25432"},
      at = {@At("HEAD")}
   )
   private void Phantom$onRemoved(CallbackInfo ci) {
      this.phantom$closing = false;
      this.phantom$transformed = false;
      this.phantom$slotHoverScales.clear();
   }

   @Inject(
      method = {"method_25419"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void Phantom$onClose(CallbackInfo ci) {
      IIiI1iiI1l1__I_1i11IiIlIii_ animations = IIiI1iiI1l1__I_1i11IiIlIii_.i1I_1IlilIll1_l_11_iiIll();
      if (animations != null && animations.shouldSmoothInventory() && this.field_22787 != null) {
         if (!this.phantom$closing) {
            this.phantom$closing = true;
            this.phantom$closingStartedAt = System.currentTimeMillis();
         }

         ci.cancel();
      }
   }

   @Unique
   private float[] phantom$calcTransform(IIiI1iiI1l1__I_1i11IiIlIii_ animations) {
      long now = System.currentTimeMillis();
      if (!this.phantom$closing && now - this.phantom$openedAt >= 120L) {
         return null;
      } else {
         float pivotX = (float)this.field_22789 / 2.0F;
         float pivotY = (float)this.field_22790 / 2.0F;
         float scale = 1.0F;
         float slideY = 0.0F;
         String mode = animations.getInvAnimMode();
         if (!this.phantom$closing) {
            float t = Math.min(1.0F, (float)(now - this.phantom$openedAt) / 120.0F);
            float ease = 1.0F - (float)Math.pow((double)(1.0F - t), (double)3.0F);
            switch (mode) {
               case "Всплывание":
                  float c1 = 1.70158F;
                  float c3 = c1 + 1.0F;
                  float back = 1.0F + c3 * (float)Math.pow((double)(t - 1.0F), (double)3.0F) + c1 * (float)Math.pow((double)(t - 1.0F), (double)2.0F);
                  scale = 0.85F + 0.15F * back;
                  slideY = (1.0F - ease) * 8.0F;
                  break;
               case "Масштаб":
                  scale = 0.8F + 0.2F * ease;
                  slideY = 0.0F;
                  break;
               case "Слайд":
                  scale = 1.0F;
                  slideY = (1.0F - ease) * 24.0F;
                  break;
               default:
                  float minScale = 0.85F;
                  scale = minScale + (1.0F - minScale) * ease;
                  slideY = (1.0F - ease) * 10.0F;
            }
         } else {
            float t = Math.min(1.0F, (float)(now - this.phantom$closingStartedAt) / 80.0F);
            float ease = t * (2.0F - t);
            switch (mode) {
               case "Всплывание":
                  scale = 1.0F - 0.15F * ease;
                  slideY = ease * 8.0F;
                  break;
               case "Масштаб":
                  scale = 1.0F - 0.2F * ease;
                  slideY = 0.0F;
                  break;
               case "Слайд":
                  scale = 1.0F;
                  slideY = ease * 24.0F;
                  break;
               default:
                  float minScale = 0.85F;
                  scale = 1.0F - (1.0F - minScale) * ease;
                  slideY = ease * 10.0F;
            }
         }

         return new float[]{pivotX, pivotY, scale, slideY};
      }
   }

   @Unique
   private double phantom$toGuiX(double mouseX) {
      IIiI1iiI1l1__I_1i11IiIlIii_ animations = IIiI1iiI1l1__I_1i11IiIlIii_.i1I_1IlilIll1_l_11_iiIll();
      if (animations != null && animations.shouldSmoothInventory()) {
         float[] tr = this.phantom$calcTransform(animations);
         return tr != null && !(Math.abs(tr[2]) < 0.001F) ? (double)tr[0] + (mouseX - (double)tr[0]) / (double)tr[2] : mouseX;
      } else {
         return mouseX;
      }
   }

   @Unique
   private double phantom$toGuiY(double mouseY) {
      IIiI1iiI1l1__I_1i11IiIlIii_ animations = IIiI1iiI1l1__I_1i11IiIlIii_.i1I_1IlilIll1_l_11_iiIll();
      if (animations != null && animations.shouldSmoothInventory()) {
         float[] tr = this.phantom$calcTransform(animations);
         return tr != null && !(Math.abs(tr[2]) < 0.001F) ? (double)tr[1] + (mouseY - (double)tr[3] - (double)tr[1]) / (double)tr[2] : mouseY;
      } else {
         return mouseY;
      }
   }

   @ModifyVariable(
      method = {"method_25394"},
      at = @At("HEAD"),
      ordinal = 0,
      argsOnly = true
   )
   private int phantom$modifyRenderMouseX(int mouseX) {
      return (int)Math.round(this.phantom$toGuiX((double)mouseX));
   }

   @ModifyVariable(
      method = {"method_25394"},
      at = @At("HEAD"),
      ordinal = 1,
      argsOnly = true
   )
   private int phantom$modifyRenderMouseY(int mouseY) {
      return (int)Math.round(this.phantom$toGuiY((double)mouseY));
   }

   @ModifyVariable(
      method = {"method_25402"},
      at = @At("HEAD"),
      ordinal = 0,
      argsOnly = true
   )
   private double phantom$modifyClickedMouseX(double mouseX) {
      return this.phantom$toGuiX(mouseX);
   }

   @ModifyVariable(
      method = {"method_25402"},
      at = @At("HEAD"),
      ordinal = 1,
      argsOnly = true
   )
   private double phantom$modifyClickedMouseY(double mouseY) {
      return this.phantom$toGuiY(mouseY);
   }

   @ModifyVariable(
      method = {"method_25406"},
      at = @At("HEAD"),
      ordinal = 0,
      argsOnly = true
   )
   private double phantom$modifyReleasedMouseX(double mouseX) {
      return this.phantom$toGuiX(mouseX);
   }

   @ModifyVariable(
      method = {"method_25406"},
      at = @At("HEAD"),
      ordinal = 1,
      argsOnly = true
   )
   private double phantom$modifyReleasedMouseY(double mouseY) {
      return this.phantom$toGuiY(mouseY);
   }

   @ModifyVariable(
      method = {"method_25403"},
      at = @At("HEAD"),
      ordinal = 0,
      argsOnly = true
   )
   private double phantom$modifyDraggedMouseX(double mouseX) {
      return this.phantom$toGuiX(mouseX);
   }

   @ModifyVariable(
      method = {"method_25403"},
      at = @At("HEAD"),
      ordinal = 1,
      argsOnly = true
   )
   private double phantom$modifyDraggedMouseY(double mouseY) {
      return this.phantom$toGuiY(mouseY);
   }

   @ModifyVariable(
      method = {"method_25401"},
      at = @At("HEAD"),
      ordinal = 0,
      argsOnly = true
   )
   private double phantom$modifyScrolledMouseX(double mouseX) {
      return this.phantom$toGuiX(mouseX);
   }

   @ModifyVariable(
      method = {"method_25401"},
      at = @At("HEAD"),
      ordinal = 1,
      argsOnly = true
   )
   private double phantom$modifyScrolledMouseY(double mouseY) {
      return this.phantom$toGuiY(mouseY);
   }

   @Unique
   private void phantom$applyTransform(class_332 context, float[] tr) {
      if (tr != null) {
         context.method_51448().method_22903();
         context.method_51448().method_46416(0.0F, tr[3], 0.0F);
         context.method_51448().method_46416(tr[0], tr[1], 0.0F);
         context.method_51448().method_22905(tr[2], tr[2], 1.0F);
         context.method_51448().method_46416(-tr[0], -tr[1], 0.0F);
      }
   }

   @Inject(
      method = {"method_25420"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_465;method_2389(Lnet/minecraft/class_332;FII)V"
)}
   )
   private void phantom$beforeDrawBackground(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      if (this.phantom$transformed) {
         context.method_51448().method_22909();
         this.phantom$transformed = false;
      }

      IIiI1iiI1l1__I_1i11IiIlIii_ animations = IIiI1iiI1l1__I_1i11IiIlIii_.i1I_1IlilIll1_l_11_iiIll();
      if (animations != null && animations.shouldSmoothInventory()) {
         float[] tr = this.phantom$calcTransform(animations);
         if (tr != null) {
            this.phantom$applyTransform(context, tr);
            this.phantom$transformed = true;
         }
      }

   }

   @Inject(
      method = {"method_25394"},
      at = {@At("HEAD")}
   )
   public void Phantom$beforeRender(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      if (this.phantom$transformed) {
         context.method_51448().method_22909();
         this.phantom$transformed = false;
      }

   }

   @Inject(
      method = {"method_25394"},
      at = {@At("RETURN")}
   )
   public void render(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      Phantom.getInstance().getEventBus().post(new llIli11lI1__1_1_liI1lI_l_1i__1_(context, this.field_2787, this.field_2792, this.field_2779));
      IIiI1iiI1l1__I_1i11IiIlIii_ animations = IIiI1iiI1l1__I_1i11IiIlIii_.i1I_1IlilIll1_l_11_iiIll();
      if (animations != null && animations.isEnable() && animations.shouldActiveCursor()) {
         boolean hovered = this.field_2787 != null;
         if (!hovered) {
            for(class_364 child : this.method_25396()) {
               if (child.method_25405((double)mouseX, (double)mouseY)) {
                  hovered = true;
                  break;
               }
            }
         }

         if (hovered) {
            ilI1_l1II1II__1ii1_il_liIIl1.I_I1iii11IlI_lli_I_IiI(Illi_lI1l___i_iII1_IliIIIII1lI.HAND);
         }
      }

      if (this.phantom$closing) {
         long elapsed = System.currentTimeMillis() - this.phantom$closingStartedAt;
         if (elapsed >= 80L) {
            this.phantom$closing = false;
            this.phantom$finishClose();
         }
      }

   }

   @Unique
   private void phantom$finishClose() {
      if (this.field_22787 != null) {
         if (this.field_22787.field_1724 != null) {
            this.field_22787.field_1724.method_7346();
         }

         this.field_22787.method_1507((class_437)null);
      }

   }

   @Inject(
      method = {"method_2385"},
      at = {@At("HEAD")}
   )
   private void Phantom$beforeDrawSlot(class_332 context, class_1735 slot, CallbackInfo ci) {
      if (this.phantom$slotScaled) {
         context.method_51448().method_22909();
         this.phantom$slotScaled = false;
      }

      IIiI1iiI1l1__I_1i11IiIlIii_ animations = IIiI1iiI1l1__I_1i11IiIlIii_.i1I_1IlilIll1_l_11_iiIll();
      if (animations != null && animations.shouldHoverItemScale() && slot != null && slot.method_7681()) {
         boolean isHovered = slot == this.field_2787;
         float targetScale = isHovered ? 1.15F : 1.0F;
         float prevScale = (Float)this.phantom$slotHoverScales.getOrDefault(slot, 1.0F);
         float currentScale = il_iII1__Il1illlI_iliI1II1l1.Il1ll_IlIi_l_illi_1lIi_i11(prevScale, targetScale, 18.0F);
         this.phantom$slotHoverScales.put(slot, currentScale);
         if (currentScale > 1.002F) {
            float cx = (float)slot.field_7873 + 8.0F;
            float cy = (float)slot.field_7872 + 8.0F;
            context.method_51448().method_22903();
            context.method_51448().method_46416(cx, cy, 0.0F);
            context.method_51448().method_22905(currentScale, currentScale, 1.0F);
            context.method_51448().method_46416(-cx, -cy, 0.0F);
            this.phantom$slotScaled = true;
         }

      } else {
         if (slot != null) {
            this.phantom$slotHoverScales.remove(slot);
         }

      }
   }

   @Inject(
      method = {"method_2385"},
      at = {@At("RETURN")}
   )
   private void Phantom$drawItemHighlighter(class_332 context, class_1735 slot, CallbackInfo ci) {
      i_ii_I1iI1iiI1_I1____1il11.l_l1l_il_lII__l1I1l_l1_1_I_(context, slot);
      II_iil_I_1iIiIi__li1_i.l1i_lIIi1_1_II1l1_l11_1_I__(context, slot);
      if (this.phantom$slotScaled) {
         context.method_51448().method_22909();
         this.phantom$slotScaled = false;
      }

   }

   @Inject(
      method = {"method_25402"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void Phantom$onClickWhileClosing(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
      if (this.phantom$closing) {
         this.phantom$closing = false;
         this.phantom$finishClose();
         cir.setReturnValue(true);
      }

   }

   @Inject(
      method = {"method_25404"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void Phantom$onKeyWhileClosing(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
      if (this.phantom$closing) {
         this.phantom$closing = false;
         this.phantom$finishClose();
         cir.setReturnValue(true);
      }

   }
}
