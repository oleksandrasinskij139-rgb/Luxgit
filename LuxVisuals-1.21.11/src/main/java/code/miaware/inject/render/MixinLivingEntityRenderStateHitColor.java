package code.miaware.inject.render;

import code.miaware.client.features.modules.render.hitcolor.Kc8717417882af19c93;
import net.minecraft.class_10042;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin({class_10042.class})
public abstract class MixinLivingEntityRenderStateHitColor implements Kc8717417882af19c93 {
   @Unique
   private float lux$hitStrength;

   public float lux$hitStrength() {
      return this.lux$hitStrength;
   }

   public void lux$hitStrength(float value) {
      this.lux$hitStrength = value;
   }
}
