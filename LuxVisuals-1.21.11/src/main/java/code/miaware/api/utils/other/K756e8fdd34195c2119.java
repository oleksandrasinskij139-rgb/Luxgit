package code.miaware.api.utils.other;

import code.miaware.api.system.backend.K8e471a90dc4db42e81;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;
import net.minecraft.class_2596;
import net.minecraft.class_7202;
import net.minecraft.class_7204;
import net.minecraft.class_746;

public final class K756e8fdd34195c2119 {
   private static boolean silentPacket = false;
   private static final Map<Class<?>, Long> lastPacketTime = new HashMap();
   private static final Map<Class<?>, Integer> packetCount = new HashMap();
   private static final long PACKET_COOLDOWN_MS = 100L;
   private static final int MAX_PACKETS_PER_SECOND = 20;

   public static void sendPacket(class_2596<?> packet) {
      class_746 player = player();
      if (player != null && player.field_3944 != null) {
         if (!silentPacket && !K624a26a92da7bd90a5(packet.getClass())) {
            System.out.println("[NetworkUtil] Пакет " + packet.getClass().getSimpleName() + " заблокирован (защита от спама)");
         } else {
            player.field_3944.method_52787(packet);
            if (!silentPacket) {
               lastPacketTime.put(packet.getClass(), System.currentTimeMillis());
            }

         }
      }
   }

   public static void sendPacket(class_7204 packet) {
      class_746 player = player();
      if (player != null && player.field_3944 != null && player.field_17892 != null) {
         class_7202 ignored = player.field_17892.field_37951.method_41937();

         try {
            int sequence = player.field_17892.field_37951.method_41942();
            player.field_3944.method_52787(packet.predict(sequence));
         } catch (Throwable var6) {
            if (ignored != null) {
               try {
                  ignored.close();
               } catch (Throwable var5) {
                  var6.addSuppressed(var5);
               }
            }

            throw var6;
         }

         if (ignored != null) {
            ignored.close();
         }

      }
   }

   public static void sendSilentPacket(class_2596<?> packet) {
      try {
         silentPacket = true;
         sendPacket(packet);
      } finally {
         silentPacket = false;
      }

   }

   public static void sendSilentPacket(class_7204 packet) {
      try {
         silentPacket = true;
         sendPacket(packet);
      } finally {
         silentPacket = false;
      }

   }

   private static boolean K624a26a92da7bd90a5(Class<?> packetClass) {
      long currentTime = System.currentTimeMillis();
      Long lastTime = (Long)lastPacketTime.get(packetClass);
      if (lastTime != null) {
         long timeSinceLastPacket = currentTime - lastTime;
         if (timeSinceLastPacket < 100L) {
            return false;
         }
      }

      Integer count = (Integer)packetCount.get(packetClass);
      if (count == null) {
         count = 0;
      }

      if (lastTime != null && currentTime - lastTime >= 1000L) {
         packetCount.put(packetClass, 0);
         count = 0;
      }

      if (count >= 20) {
         PrintStream var10000 = System.out;
         String var10001 = packetClass.getSimpleName();
         var10000.println("[NetworkUtil] Достигнут лимит пакетов для " + var10001 + " (" + count + "/20)");
         return false;
      } else {
         packetCount.put(packetClass, count + 1);
         return true;
      }
   }

   private static class_746 player() {
      return K8e471a90dc4db42e81.player();
   }

   public static void cleanup() {
      long currentTime = System.currentTimeMillis();
      lastPacketTime.entrySet().removeIf((entry) -> currentTime - (Long)entry.getValue() > 5000L);
      packetCount.entrySet().removeIf((entry) -> (Integer)entry.getValue() == 0);
   }

   @Generated
   private K756e8fdd34195c2119() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   @Generated
   public static boolean silentPacket() {
      return silentPacket;
   }
}
