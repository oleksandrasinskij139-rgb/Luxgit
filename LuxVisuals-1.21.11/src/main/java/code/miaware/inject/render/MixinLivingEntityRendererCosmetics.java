package code.miaware.inject.render;

import code.miaware.client.features.cosmetics.K240a370ce98295326b;
import net.minecraft.class_1007;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_5617;
import net.minecraft.class_583;
import net.minecraft.class_922;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_922.class})
public abstract class MixinLivingEntityRendererCosmetics {
   @Shadow
   protected abstract boolean method_4046(class_3887 var1);

   @Inject(
      method = {"<init>"},
      at = {@At("RETURN")}
   )
   private void lux$addCosmetics(class_5617.class_5618 context, class_583<?> model, float shadowRadius, CallbackInfo ci) {
      if (this instanceof class_1007) {
         this.method_4046(new K240a370ce98295326b((class_3883)this));
      }

   }
}
