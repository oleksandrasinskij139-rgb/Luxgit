package code.miaware.inject.client;

import code.miaware.client.features.modules.render.Kcc60133648b8ff2d03;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_638.class_5271.class})
public class MixinClientWorldProperties {
   @ModifyReturnValue(
      method = {"getTimeOfDay"},
      at = {@At("RETURN")}
   )
   private long getTimeOfDay(long original) {
      return Kcc60133648b8ff2d03.getInstance().getTime(original);
   }
}
