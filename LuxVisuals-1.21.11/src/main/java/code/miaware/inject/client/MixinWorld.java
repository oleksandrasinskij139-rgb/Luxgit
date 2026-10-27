package code.miaware.inject.client;

import code.miaware.api.module.setting.Ke298bfdac4e34a00d9;
import code.miaware.api.system.backend.K46bff99e1f90a113f9;
import code.miaware.client.features.modules.render.K2b73b8449b9c4b236e;
import code.miaware.client.features.modules.render.Kcc60133648b8ff2d03;
import net.minecraft.class_1937;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1937.class})
public class MixinWorld {
   @Inject(
      method = {"getRainGradient"},
      cancellable = true,
      at = {@At("HEAD")}
   )
   private void overrideWeather(float delta, CallbackInfoReturnable<Float> cir) {
      Kcc60133648b8ff2d03 module = Kcc60133648b8ff2d03.getInstance();
      K2b73b8449b9c4b236e weather = (K2b73b8449b9c4b236e)K46bff99e1f90a113f9.getChoiceByName((String)module.weather.getValue(), (Ke298bfdac4e34a00d9[])K2b73b8449b9c4b236e.values());
      if (module.isEnabled()) {
         switch (weather) {
            case SUNNY:
               cir.setReturnValue(0.0F);
               break;
            case RAINY:
            case THUNDER:
               cir.setReturnValue(1.0F);
               break;
            case SNOWY:
               cir.setReturnValue(0.9F);
         }
      }

   }

   @Inject(
      method = {"getThunderGradient"},
      cancellable = true,
      at = {@At("HEAD")}
   )
   private void overrideThunder(float delta, CallbackInfoReturnable<Float> cir) {
      Kcc60133648b8ff2d03 module = Kcc60133648b8ff2d03.getInstance();
      K2b73b8449b9c4b236e weather = (K2b73b8449b9c4b236e)K46bff99e1f90a113f9.getChoiceByName((String)module.weather.getValue(), (Ke298bfdac4e34a00d9[])K2b73b8449b9c4b236e.values());
      if (module.isEnabled()) {
         switch (weather) {
            case SUNNY:
            case RAINY:
            case SNOWY:
               cir.setReturnValue(0.0F);
               break;
            case THUNDER:
               cir.setReturnValue(1.0F);
         }
      }

   }
}
