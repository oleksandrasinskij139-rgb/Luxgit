package code.miaware.inject.chat;

import code.miaware.client.ui.screen.Kea0f5fac15ba91019e;
import net.minecraft.class_342;
import net.minecraft.class_408;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({class_408.class})
public abstract class MixinChatScreenPrivacy implements Kea0f5fac15ba91019e {
   @Shadow
   protected class_342 field_2382;

   public boolean lux$isChatInput(Object field) {
      return field == this.field_2382;
   }
}
