package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I5_i4.iiIIliIli1Il_I_i_I1iiil11;
import net.minecraft.class_332;
import net.minecraft.class_337;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_337.class})
public class BossBarHudMixin {
   @Inject(
      method = {"method_1796"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void Phantom$cancelBossBar(class_332 context, CallbackInfo ci) {
      if (iiIIliIli1Il_I_i_I1iiil11.lii1I11_i_l1il_liIiI111ilil1_("Boss Bar")) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"method_1796"},
      at = {@At("RETURN")}
   )
   private void Phantom$endBossBar(class_332 context, CallbackInfo ci) {
      context.method_51452();
   }
}
