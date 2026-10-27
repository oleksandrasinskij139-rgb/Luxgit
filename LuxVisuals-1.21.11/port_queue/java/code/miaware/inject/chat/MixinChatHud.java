package code.miaware.inject.chat;

import code.miaware.client.features.modules.other.chatheads.Kca2883196afc0ea01c;
import code.miaware.client.features.modules.other.chatheads.Kff6743acd552d72675;
import code.miaware.client.features.modules.render.Kacf94aeb4cdc6e13ca;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_2561;
import net.minecraft.class_303;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_338;
import net.minecraft.class_3532;
import net.minecraft.class_5481;
import net.minecraft.class_7469;
import net.minecraft.class_7591;
import net.minecraft.class_9848;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_338.class})
public class MixinChatHud {
   @Shadow
   @Final
   private List<class_303> field_2061;
   @Shadow
   @Final
   private List<class_303.class_7590> field_2064;
   @Unique
   private static final Map<class_303.class_7590, Long> messageTimestamps = new HashMap();
   @Unique
   private static final long ANIMATION_DURATION = 300L;

   @WrapOperation(
      method = {"render"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V",
   ordinal = 0
)}
   )
   private void miaware$animateMessageBackground(class_332 context, int x1, int y1, int x2, int y2, int color, Operation<Void> original, @Local class_303.class_7590 line, @Local(argsOnly = true,ordinal = 0) int currentTick) {
      int offsetX = this.miaware$getChatOffsetX(line);
      float alpha = this.miaware$getChatAlpha(line, currentTick);
      original.call(new Object[]{context, x1 + offsetX, y1, x2 + offsetX, y2, this.miaware$applyAlpha(color, alpha)});
   }

   @WrapOperation(
      method = {"render"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V",
   ordinal = 1
)}
   )
   private void miaware$animateIndicatorBar(class_332 context, int x1, int y1, int x2, int y2, int color, Operation<Void> original, @Local class_303.class_7590 line, @Local(argsOnly = true,ordinal = 0) int currentTick) {
      int offsetX = this.miaware$getChatOffsetX(line);
      float alpha = this.miaware$getChatAlpha(line, currentTick);
      original.call(new Object[]{context, x1 + offsetX, y1, x2 + offsetX, y2, this.miaware$applyAlpha(color, alpha)});
   }

   @WrapOperation(
      method = {"render"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/OrderedText;III)I",
   ordinal = 0
)}
   )
   private int miaware$animateChatText(class_332 context, class_327 textRenderer, class_5481 text, int x, int y, int color, Operation<Integer> original, @Local class_303.class_7590 line, @Local(argsOnly = true,ordinal = 0) int currentTick) {
      int offsetX = this.miaware$getChatOffsetX(line);
      float alpha = this.miaware$getChatAlpha(line, currentTick);
      int result = (Integer)original.call(new Object[]{context, textRenderer, text, x + offsetX, y, this.miaware$applyAlpha(color, alpha)});
      if (line.comp_898()) {
         int textWidth = textRenderer.method_30880(text);
         Kff6743acd552d72675.renderHead(context, x + textWidth, y, line, alpha);
      }

      return result;
   }

   @WrapOperation(
      method = {"render"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/gui/hud/ChatHud;drawIndicatorIcon(Lnet/minecraft/client/gui/DrawContext;IILnet/minecraft/client/gui/hud/MessageIndicator$Icon;)V"
)}
   )
   private void miaware$moveIndicatorIcon(class_338 instance, class_332 context, int x, int y, class_7591.class_7592 icon, Operation<Void> original, @Local class_303.class_7590 line, @Local(argsOnly = true,ordinal = 0) int currentTick) {
      int offsetX = this.miaware$getChatOffsetX(line);
      original.call(new Object[]{instance, context, x + offsetX, y, icon});
   }

   @Inject(
      method = {"addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V"},
      at = {@At("TAIL")}
   )
   private void miaware$attachIncomingHeadData(class_2561 message, class_7469 signatureData, class_7591 indicator, CallbackInfo ci) {
      this.miaware$storeIncomingHeadData();
   }

   @Inject(
      method = {"addMessage(Lnet/minecraft/text/Text;)V"},
      at = {@At("TAIL")}
   )
   private void miaware$attachIncomingHeadDataSimple(class_2561 message, CallbackInfo ci) {
      this.miaware$storeIncomingHeadData();
   }

   @Inject(
      method = {"addVisibleMessage"},
      at = {@At("HEAD")}
   )
   private void miaware$prepareVisibleHeadData(class_303 message, CallbackInfo ci) {
      Kca2883196afc0ea01c data = Kff6743acd552d72675.getHeadData(message);
      if (data.isEmpty()) {
         data = Kff6743acd552d72675.peekIncomingHeadData();
         Kff6743acd552d72675.setHeadData(message, data);
      }

      Kff6743acd552d72675.beginVisibleMessage(data);
   }

   @Inject(
      method = {"addVisibleMessage"},
      at = {@At("TAIL")}
   )
   private void miaware$clearVisibleHeadData(class_303 message, CallbackInfo ci) {
      Kff6743acd552d72675.endVisibleMessage();
   }

   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   private void miaware$registerMessageTimestamp(class_332 context, int tickDelta, int mouseX, int mouseY, boolean focused, CallbackInfo ci) {
      for(class_303.class_7590 visible : this.field_2064) {
         if (!messageTimestamps.containsKey(visible)) {
            messageTimestamps.put(visible, System.currentTimeMillis());
         }
      }

   }

   @Unique
   private float miaware$getChatAlpha(class_303.class_7590 line, int currentTick) {
      return 1.0F;
   }

   @Unique
   private int miaware$getChatOffsetX(class_303.class_7590 line) {
      int chatHeadOffset = Kff6743acd552d72675.getChatOffset(line);
      if (Kacf94aeb4cdc6e13ca.getInstance().isEnabled() && (Boolean)Kacf94aeb4cdc6e13ca.getInstance().getChatAnimation().getValue()) {
         Long timestamp = (Long)messageTimestamps.get(line);
         if (timestamp == null) {
            return chatHeadOffset;
         } else {
            long elapsed = System.currentTimeMillis() - timestamp;
            if (elapsed >= 300L) {
               return chatHeadOffset;
            } else {
               float progress = (float)elapsed / 300.0F;
               progress = 1.0F - (float)Math.pow((double)(1.0F - progress), (double)3.0F);
               int animationOffset = (int)(-100.0F * (1.0F - progress));
               return chatHeadOffset + animationOffset;
            }
         }
      } else {
         return chatHeadOffset;
      }
   }

   @Unique
   private void miaware$storeIncomingHeadData() {
      Kca2883196afc0ea01c data = Kff6743acd552d72675.consumeIncomingHeadData();
      if (!data.isEmpty() && !this.field_2061.isEmpty()) {
         Kff6743acd552d72675.setHeadData(this.field_2061.getFirst(), data);
      }
   }

   @Unique
   private int miaware$applyAlpha(int color, float alphaMultiplier) {
      int alpha = class_9848.method_61320(color);
      if (alpha == 0 && color != 0) {
         alpha = 255;
      }

      int multiplied = class_3532.method_15340(Math.round((float)alpha * alphaMultiplier), 0, 255);
      return class_9848.method_61330(multiplied, color);
   }
}
