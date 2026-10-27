package code.miaware.inject.render;

import code.miaware.client.features.modules.render.K2b73b8449b9c4b236e;
import code.miaware.client.features.modules.render.Kcc60133648b8ff2d03;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.class_1959;
import net.minecraft.class_9976;
import net.minecraft.class_1959.class_1963;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_9976.class})
public abstract class MixinWeatherRendering {
   @ModifyExpressionValue(
      method = {"addParticlesAndSound"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/world/ClientWorld;getRainGradient(F)F"
)}
   )
   private float ambientPrecipitation(float original) {
      Kcc60133648b8ff2d03 moduleCustomAmbience = Kcc60133648b8ff2d03.getInstance();
      return moduleCustomAmbience.isEnabled() && moduleCustomAmbience.weather.is(K2b73b8449b9c4b236e.SNOWY) ? 0.0F : original;
   }

   @ModifyReturnValue(
      method = {"getPrecipitationAt"},
      at = {@At(
   value = "RETURN",
   ordinal = 1
)}
   )
   private class_1959.class_1963 modifyBiomePrecipitation(class_1959.class_1963 original) {
      Kcc60133648b8ff2d03 moduleOverrideWeather = Kcc60133648b8ff2d03.getInstance();
      return moduleOverrideWeather.isEnabled() && moduleOverrideWeather.weather.is(K2b73b8449b9c4b236e.SNOWY) ? class_1963.field_9383 : original;
   }
}
