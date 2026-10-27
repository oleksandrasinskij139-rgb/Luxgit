package code.miaware.inject.render;

import code.miaware.client.network.badge.Ka424c22578b339e00d;
import net.minecraft.class_10055;
import net.minecraft.class_1007;
import net.minecraft.class_742;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1007.class})
public abstract class MixinPlayerBadge {
   @Inject(
      method = {"updateRenderState(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/client/render/entity/state/PlayerEntityRenderState;F)V"},
      at = {@At("RETURN")}
   )
   private void lux$badge(class_742 player, class_10055 state, float delta, CallbackInfo ci) {
      state.field_53337 = Ka424c22578b339e00d.decorate(player.method_5667(), player.method_7334().getName(), state.field_53337);
   }
}
