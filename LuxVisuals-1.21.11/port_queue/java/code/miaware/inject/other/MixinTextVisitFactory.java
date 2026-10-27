package code.miaware.inject.other;

import code.miaware.api.system.backend.K8e471a90dc4db42e81;
import code.miaware.api.utils.other.Kd10bcb33e37f629df6;
import code.miaware.client.features.modules.other.K5fee4323853dcfc345;
import net.minecraft.class_5223;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin({class_5223.class})
public class MixinTextVisitFactory {
   @ModifyArg(
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/text/TextVisitFactory;visitFormatted(Ljava/lang/String;ILnet/minecraft/text/Style;Lnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z",
   ordinal = 0
),
      method = {"visitFormatted(Ljava/lang/String;ILnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z"},
      index = 0
   )
   private static String visitFormatted(String string) {
      return K5fee4323853dcfc345.getInstance().isEnabled() && K8e471a90dc4db42e81.player() != null ? Kd10bcb33e37f629df6.protectedString(string) : string;
   }
}
