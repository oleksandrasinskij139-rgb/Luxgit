package ru.phantom.mixin.minecraft.client.gui.screen;

import II1II1II1II1II1II1II1II1.l8_I7.i11Il_l_1illl1_I_1Il1I11lli__l;
import net.minecraft.class_342;
import net.minecraft.class_408;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_408.class})
public abstract class ChatScreenBlurPasswordMixin {
   @Shadow
   protected class_342 field_2382;
   @Unique
   private String phantom$realChatText;
   @Unique
   private int phantom$realCursor;

   @Inject(
      method = {"method_25394"},
      at = {@At("HEAD")},
      require = 0
   )
   private void phantom$maskChatInput(CallbackInfo ci) {
      this.phantom$realChatText = null;
      i11Il_l_1illl1_I_1Il1I11lli__l module = i11Il_l_1illl1_I_1Il1I11lli__l.iIi_11I__i1__iiI1_llil_lI1l();
      if (module != null && module.isEnable() && this.field_2382 != null) {
         String text = this.field_2382.method_1882();
         if (text != null && module.shouldMask(text)) {
            String masked = module.mask(text);
            if (!masked.equals(text)) {
               this.phantom$realChatText = text;
               this.phantom$realCursor = this.field_2382.method_1881();
               this.field_2382.method_1852(masked);
               this.field_2382.method_1883(Math.min(this.phantom$realCursor, masked.length()), false);
            }
         }
      }
   }

   @Inject(
      method = {"method_25394"},
      at = {@At("RETURN")},
      require = 0
   )
   private void phantom$restoreChatInput(CallbackInfo ci) {
      if (this.phantom$realChatText != null && this.field_2382 != null) {
         String real = this.phantom$realChatText;
         int cursor = this.phantom$realCursor;
         this.phantom$realChatText = null;
         this.field_2382.method_1852(real);
         this.field_2382.method_1883(Math.min(cursor, real.length()), false);
      }
   }
}
