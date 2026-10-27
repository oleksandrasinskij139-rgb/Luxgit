package code.miaware.api.system.interfaces;

import code.miaware.api.utils.other.K756e8fdd34195c2119;
import code.miaware.api.utils.other.Kf5ec3d7f18b288e7f3;
import net.minecraft.class_2596;
import net.minecraft.class_310;
import net.minecraft.class_7204;

public interface K4600bef438dafc1a9c {
   class_310 mc = class_310.method_1551();

   default void print(String message) {
      Kf5ec3d7f18b288e7f3.sendMessage(message);
   }

   default void sendPacket(class_7204 packet) {
      K756e8fdd34195c2119.sendPacket(packet);
   }

   default void sendSilentPacket(class_7204 packet) {
      K756e8fdd34195c2119.sendSilentPacket(packet);
   }

   default void sendPacket(class_2596<?> packet) {
      K756e8fdd34195c2119.sendPacket(packet);
   }

   default void sendSilentPacket(class_2596<?> packet) {
      K756e8fdd34195c2119.sendSilentPacket(packet);
   }
}
