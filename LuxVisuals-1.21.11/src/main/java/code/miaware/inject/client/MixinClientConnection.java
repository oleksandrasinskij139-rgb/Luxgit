package code.miaware.inject.client;

import code.miaware.api.event.events.client.K001d59b5164d1d1f95;
import code.miaware.api.event.events.client.Kb251204d734ffa1d20;
import code.miaware.api.event.events.client.Kb652aee96d70f375af;
import code.miaware.api.utils.other.K756e8fdd34195c2119;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2535;
import net.minecraft.class_2547;
import net.minecraft.class_2596;
import net.minecraft.class_2602;
import net.minecraft.class_310;
import net.minecraft.class_7648;
import net.minecraft.class_8042;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_2535.class})
public class MixinClientConnection {
   @Unique
   private static final ThreadLocal<Boolean> REPLAYING_FILTERED_BUNDLE = ThreadLocal.withInitial(() -> false);

   @Shadow
   private static <T extends class_2547> void method_10759(class_2596<T> packet, class_2547 listener) {
   }

   @Inject(
      method = {"send(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/PacketCallbacks;Z)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void sendPackets(class_2596<?> packet, @Nullable class_7648 callbacks, boolean flush, CallbackInfo callbackInfo) {
      if (class_310.method_1551().field_1724 != null && class_310.method_1551().field_1687 != null && !K756e8fdd34195c2119.silentPacket()) {
         if (K001d59b5164d1d1f95.getInstance().call(new Kb251204d734ffa1d20(packet, Kb652aee96d70f375af.SEND))) {
            callbackInfo.cancel();
         }

      }
   }

   @Inject(
      method = {"handlePacket"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void receivePackets(class_2596<?> packet, class_2547 listener, CallbackInfo callbackInfo) {
      if (class_310.method_1551().field_1724 != null && class_310.method_1551().field_1687 != null && !(Boolean)REPLAYING_FILTERED_BUNDLE.get()) {
         if (packet instanceof class_8042) {
            class_8042 bundlePacket = (class_8042)packet;
            List<class_2596<? super class_2602>> allowedPackets = new ArrayList();
            boolean filtered = false;

            for(class_2596<?> innerPacket : bundlePacket.method_48324()) {
               if (handleSinglePacket(innerPacket)) {
                  filtered = true;
               } else {
                  allowedPackets.add(castPlayPacket(innerPacket));
               }
            }

            if (!filtered) {
               return;
            }

            callbackInfo.cancel();
            if (allowedPackets.isEmpty()) {
               return;
            }

            REPLAYING_FILTERED_BUNDLE.set(true);

            try {
               for(class_2596<? super class_2602> allowedPacket : allowedPackets) {
                  method_10759(allowedPacket, listener);
               }
            } finally {
               REPLAYING_FILTERED_BUNDLE.set(false);
            }
         } else if (handleSinglePacket(packet)) {
            callbackInfo.cancel();
         }

      }
   }

   @Unique
   private static boolean handleSinglePacket(class_2596<?> packet) {
      return K001d59b5164d1d1f95.getInstance().call(new Kb251204d734ffa1d20(packet, Kb652aee96d70f375af.RECEIVE));
   }

   @Unique
   private static class_2596<? super class_2602> castPlayPacket(class_2596<?> packet) {
      return packet;
   }
}
