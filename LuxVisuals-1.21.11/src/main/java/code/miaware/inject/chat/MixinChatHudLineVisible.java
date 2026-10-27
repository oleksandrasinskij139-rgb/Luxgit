package code.miaware.inject.chat;

import code.miaware.client.features.modules.other.chatheads.Kca2883196afc0ea01c;
import code.miaware.client.features.modules.other.chatheads.Kd7ebad26b0611ce013;
import code.miaware.client.features.modules.other.chatheads.Kff6743acd552d72675;
import net.minecraft.class_303;
import net.minecraft.class_5481;
import net.minecraft.class_7591;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_303.class_7590.class})
public class MixinChatHudLineVisible implements Kd7ebad26b0611ce013 {
   @Unique
   private Kca2883196afc0ea01c miaware$chatHeadData;

   public MixinChatHudLineVisible() {
      this.miaware$chatHeadData = Kca2883196afc0ea01c.EMPTY;
   }

   @Inject(
      method = {"<init>"},
      at = {@At("TAIL")}
   )
   private void miaware$attachPendingHeadData(int addedTime, class_5481 content, class_7591 indicator, boolean endOfEntry, CallbackInfo ci) {
      this.miaware$chatHeadData = Kff6743acd552d72675.getPendingVisibleHeadData();
   }

   public Kca2883196afc0ea01c miaware$getChatHeadData() {
      return this.miaware$chatHeadData;
   }

   public void miaware$setChatHeadData(Kca2883196afc0ea01c data) {
      this.miaware$chatHeadData = data == null ? Kca2883196afc0ea01c.EMPTY : data;
   }
}
