package code.miaware.inject.sound;

import code.miaware.MiaWare;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1113;
import net.minecraft.class_1140;
import net.minecraft.class_4235;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({class_1140.class})
public abstract class MixinSoundSystemRTX {
   @Shadow
   private Map<class_1113, class_4235.class_4236> field_18950;

   @Inject(
      method = {"play(Lnet/minecraft/client/sound/SoundInstance;)V"},
      at = {@At("RETURN")}
   )
   private void onSoundPlay(class_1113 sound, CallbackInfo ci) {
      if (MiaWare.getSoundRTX() != null && MiaWare.getSoundRTX().isState()) {
         try {
            class_4235.class_4236 sourceManager = (class_4235.class_4236)this.field_18950.get(sound);
            if (sourceManager != null) {
               sourceManager.method_19735((source) -> {
                  try {
                     int sourceId = ((SourceAccessor)source).rtx$getSourceId();
                     if (sourceId > 0) {
                        MiaWare.getSoundRTX().getMixer().injectFiltersToChannel(sound, sourceId);
                     }
                  } catch (Exception var3) {
                  }

               });
            }
         } catch (Exception var4) {
         }
      }

   }
}
