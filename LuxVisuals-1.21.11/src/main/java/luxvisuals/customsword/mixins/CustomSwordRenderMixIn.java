package luxvisuals.customsword.mixins;

import luxvisuals.customsword.CustomSwordRuntime;
import net.minecraft.class_1268;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_742;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
   targets = {"net.minecraft.class_759"}
)
public class CustomSwordRenderMixIn {
   @Inject(
      method = {"method_3228"},
      at = @At("HEAD")
   )
   private void lux$before(class_742 var1, float var2, float var3, class_1268 var4, float var5, class_1799 var6, float var7, class_4587 var8, class_4597 var9, int var10, CallbackInfo var11) {
      CustomSwordRuntime.before(var6);
   }

   @Inject(
      method = {"method_3228"},
      at = @At("RETURN")
   )
   private void lux$after(class_742 var1, float var2, float var3, class_1268 var4, float var5, class_1799 var6, float var7, class_4587 var8, class_4597 var9, int var10, CallbackInfo var11) {
      CustomSwordRuntime.after(var6);
   }
}
