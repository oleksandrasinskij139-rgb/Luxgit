package code.miaware.inject.chat;

import code.miaware.client.features.modules.other.chatheads.Kca2883196afc0ea01c;
import code.miaware.client.features.modules.other.chatheads.Kd7ebad26b0611ce013;
import net.minecraft.class_303;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin({class_303.class})
public class MixinChatHudLine implements Kd7ebad26b0611ce013 {
   @Unique
   private Kca2883196afc0ea01c miaware$chatHeadData;

   public MixinChatHudLine() {
      this.miaware$chatHeadData = Kca2883196afc0ea01c.EMPTY;
   }

   public Kca2883196afc0ea01c miaware$getChatHeadData() {
      return this.miaware$chatHeadData;
   }

   public void miaware$setChatHeadData(Kca2883196afc0ea01c data) {
      this.miaware$chatHeadData = data == null ? Kca2883196afc0ea01c.EMPTY : data;
   }
}
