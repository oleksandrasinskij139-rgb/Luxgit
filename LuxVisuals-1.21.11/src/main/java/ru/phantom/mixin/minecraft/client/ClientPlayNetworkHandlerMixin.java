package ru.phantom.mixin.minecraft.client;

import II1II1II1II1II1II1II1II1.I2_i1.i_i__Illlili__i_liiIi11lI__;
import II1II1II1II1II1II1II1II1.l8_I7.II111_iIl_IlI1I_i_IiIili_i_iI;
import II1II1II1II1II1II1II1II1.l8_I7.l1I1__il1_ll1iI_iillililli;
import net.minecraft.class_243;
import net.minecraft.class_2664;
import net.minecraft.class_2678;
import net.minecraft.class_2724;
import net.minecraft.class_2743;
import net.minecraft.class_634;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.phantom.Phantom;

@Mixin({class_634.class})
public abstract class ClientPlayNetworkHandlerMixin implements II111_iIl_IlI1I_i_IiIili_i_iI {
   @Inject(
      method = {"method_11120"},
      at = {@At("TAIL")}
   )
   private void onGameJoin(class_2678 packet, CallbackInfo ci) {
      Phantom.getInstance().getEventBus().post(new i_i__Illlili__i_liiIi11lI__());
   }

   @Inject(
      method = {"method_11117"},
      at = {@At("TAIL")}
   )
   private void onPlayerRespawn(class_2724 packet, CallbackInfo ci) {
      Phantom.getInstance().getEventBus().post(new i_i__Illlili__i_liiIi11lI__());
   }

   @Inject(
      method = {"method_45730"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onSendChatCommand(String command, CallbackInfo ci) {
      l1I1__il1_ll1iI_iillililli pvpSafe = l1I1__il1_ll1iI_iillililli.i1ll11iIlliil11_I1iiI_1ll___();
      if (pvpSafe != null && pvpSafe.isBlocked(command)) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"method_11132"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onEntityVelocityUpdate(class_2743 packet, CallbackInfo ci) {
      if (Math.abs(packet.method_11815()) > (double)800000.0F || Math.abs(packet.method_11816()) > (double)800000.0F || Math.abs(packet.method_11819()) > (double)800000.0F) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"method_11124"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onExplosion(class_2664 packet, CallbackInfo ci) {
      if (packet.comp_2884().isPresent()) {
         class_243 knockback = (class_243)packet.comp_2884().get();
         if (!Double.isFinite(knockback.field_1352) || !Double.isFinite(knockback.field_1351) || !Double.isFinite(knockback.field_1350) || Math.abs(knockback.field_1352) > (double)99.0F || Math.abs(knockback.field_1351) > (double)99.0F || Math.abs(knockback.field_1350) > (double)99.0F) {
            ci.cancel();
         }
      }

   }
}
