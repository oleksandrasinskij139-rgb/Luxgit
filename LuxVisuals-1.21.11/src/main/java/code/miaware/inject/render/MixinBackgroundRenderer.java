package code.miaware.inject.render;

import code.miaware.client.features.modules.render.Kcc60133648b8ff2d03;
import net.minecraft.class_4184;
import net.minecraft.class_758;
import net.minecraft.class_9958;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_758.class})
public class MixinBackgroundRenderer {
   @Inject(
      method = {"applyFog"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private static void modifyFog(class_4184 camera, class_758.class_4596 fogType, Vector4f color, float viewDistance, boolean thickFog, float tickDelta, CallbackInfoReturnable<class_9958> cir) {
      Kcc60133648b8ff2d03 ambience = Kcc60133648b8ff2d03.getInstance();
      if (ambience.isEnabled() && (Boolean)ambience.customFog.getValue()) {
         class_9958 customFog = ambience.applyCustomFog(camera, viewDistance, (class_9958)cir.getReturnValue());
         cir.setReturnValue(customFog);
      }

   }
}
