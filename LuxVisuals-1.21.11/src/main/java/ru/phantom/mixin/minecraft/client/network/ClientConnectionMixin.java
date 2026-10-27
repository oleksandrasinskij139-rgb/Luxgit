package ru.phantom.mixin.minecraft.client.network;

import II1II1II1II1II1II1II1II1.l8_I7.i_I_IlIl1I_IIIi1iIiiI1__il1;
import net.minecraft.class_2535;
import net.minecraft.class_2547;
import net.minecraft.class_2596;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.phantom.Phantom;

@Mixin({class_2535.class})
public class ClientConnectionMixin {
   @Inject(
      method = {"method_10759"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void onPacketReceive(class_2596<? extends class_2547> packet, class_2547 listener, CallbackInfo ci) {
      i_I_IlIl1I_IIIi1iIiiI1__il1 eventPacket = new i_I_IlIl1I_IIIi1iIiiI1__il1(packet, i_I_IlIl1I_IIIi1iIiiI1__il1.i1_lI_1iliIi1li11_IIIi.Receive);
      Phantom.getInstance().getEventBus().post(eventPacket);
      if (eventPacket.isCancelled()) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"method_10743(Lnet/minecraft/class_2596;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onPacketSend(class_2596<?> packet, CallbackInfo ci) {
      i_I_IlIl1I_IIIi1iIiiI1__il1 eventPacket = new i_I_IlIl1I_IIIi1iIiiI1__il1(packet, i_I_IlIl1I_IIIi1iIiiI1__il1.i1_lI_1iliIi1li11_IIIi.Send);
      Phantom.getInstance().getEventBus().post(eventPacket);
      if (eventPacket.isCancelled()) {
         ci.cancel();
      }

   }
}
