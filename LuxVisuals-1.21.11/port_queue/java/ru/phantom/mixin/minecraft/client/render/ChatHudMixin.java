package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I5_i4.IIiI1iiI1l1__I_1i11IiIlIii_;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.List;
import net.minecraft.class_2561;
import net.minecraft.class_303;
import net.minecraft.class_332;
import net.minecraft.class_338;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_338.class})
public class ChatHudMixin {
   @Shadow
   private int field_2066;
   @Shadow
   public List<class_303.class_7590> field_2064;
   @Unique
   private boolean Phantom$chatAnimationApplied;
   @Unique
   private float Phantom$messageOffsetX;
   @Unique
   private int Phantom$renderedMessages;
   @Unique
   private int Phantom$totalMessagesToRender;
   @Unique
   private boolean Phantom$isMessageAnimating;

   @Shadow
   public int method_1813() {
      return 0;
   }

   @Inject(
      method = {"method_1805"},
      at = {@At("HEAD")}
   )
   private void Phantom$setupChatAnimation(class_332 context, int currentTick, int mouseX, int mouseY, boolean focused, CallbackInfo ci) {
      IIiI1iiI1l1__I_1i11IiIlIii_ animations = IIiI1iiI1l1__I_1i11IiIlIii_.i1I_1IlilIll1_l_11_iiIll();
      if (animations != null && animations.shouldSmoothChat()) {
         float messageProgress = animations.updateChatMessageProgress();
         this.Phantom$isMessageAnimating = messageProgress < 1.0F;
         this.Phantom$messageOffsetX = (1.0F - messageProgress) * 20.0F;
         float open = animations.updateChatOpenProgress(focused);
         float offsetY = focused ? 24.0F * (1.0F - open) : 0.0F;
         boolean hasY = Math.abs(offsetY) >= 0.01F;
         if (hasY) {
            this.Phantom$chatAnimationApplied = true;
            context.method_51448().method_22903();
            context.method_51448().method_46416(0.0F, offsetY, 0.0F);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
         } else {
            this.Phantom$chatAnimationApplied = false;
         }

         this.Phantom$renderedMessages = 0;
         this.Phantom$totalMessagesToRender = Math.max(0, Math.min(this.field_2064.size() - this.field_2066, this.method_1813()));
      } else {
         this.Phantom$chatAnimationApplied = false;
      }
   }

   @Inject(
      method = {"method_1805"},
      at = {@At("RETURN")}
   )
   private void Phantom$cleanupChatAnimation(class_332 context, int currentTick, int mouseX, int mouseY, boolean focused, CallbackInfo ci) {
      if (this.Phantom$chatAnimationApplied) {
         context.method_51448().method_22909();
         RenderSystem.disableBlend();
         this.Phantom$chatAnimationApplied = false;
      }

   }

   @ModifyArg(
      method = {"method_1805"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_332;method_35720(Lnet/minecraft/class_327;Lnet/minecraft/class_5481;III)I"
),
      index = 2
   )
   private int Phantom$modifyMessageX(int x) {
      IIiI1iiI1l1__I_1i11IiIlIii_ animations = IIiI1iiI1l1__I_1i11IiIlIii_.i1I_1IlilIll1_l_11_iiIll();
      if (animations != null && animations.shouldSmoothChat() && this.Phantom$isMessageAnimating) {
         boolean isNewest = this.Phantom$renderedMessages == 0;
         ++this.Phantom$renderedMessages;
         return isNewest ? x - (int)this.Phantom$messageOffsetX : x;
      } else {
         ++this.Phantom$renderedMessages;
         return x;
      }
   }

   @Inject(
      method = {"method_1812(Lnet/minecraft/class_2561;)V"},
      at = {@At("HEAD")}
   )
   private void Phantom$animateNewMessage(class_2561 message, CallbackInfo ci) {
      IIiI1iiI1l1__I_1i11IiIlIii_ animations = IIiI1iiI1l1__I_1i11IiIlIii_.i1I_1IlilIll1_l_11_iiIll();
      if (animations != null && animations.shouldSmoothChat()) {
         animations.resetChatMessageAnimation();
      }

   }
}
