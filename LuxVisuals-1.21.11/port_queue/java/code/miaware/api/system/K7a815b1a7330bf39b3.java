package code.miaware.api.system;

import code.miaware.api.discord.rpc.utils.K58c24c5030de440d40;
import code.miaware.api.discord.rpc.utils.K68b77923e989ac28ac;
import code.miaware.api.discord.rpc.utils.K99690a624fa8fb05b3;
import code.miaware.api.discord.rpc.utils.Kc339d7f4ca09116837;
import code.miaware.api.system.backend.K93d0ef925a2fcdf0d6;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.other.Kd10bcb33e37f629df6;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import lombok.Generated;
import net.minecraft.class_642;

public final class K7a815b1a7330bf39b3 implements K4600bef438dafc1a9c {
   private static final String APPLICATION_ID = "1506349412794892468";
   private static final String SINGLEPLAYER_LABEL = "Singleplayer";
   private static final String UNKNOWN_SERVER = "Unknown";
   private static final String TELEGRAM_URL = "https://t.me/k3dclient";
   private static final String DISCORD_URL = "https://discord.gg/7eGNr8sx7M";
   private static boolean running;
   private static String lastDetails = "";
   private static String lastImageText = "";
   private static Thread discordThread;

   public static boolean isRunning() {
      return running;
   }

   public static void startRPC() {
      if (running) {
         refreshPresence();
      } else {
         try {
            running = true;
            K68b77923e989ac28ac handlers = (new K99690a624fa8fb05b3()).build();
            Kc339d7f4ca09116837.INSTANCE.Discord_Initialize("1506349412794892468", handlers, true, "");
            refreshPresence();
            discordThread = new Thread(() -> {
               try {
                  while(running) {
                     Kc339d7f4ca09116837.INSTANCE.Discord_RunCallbacks();
                     Thread.sleep(2000L);
                  }
               } catch (InterruptedException var1) {
                  Thread.currentThread().interrupt();
               }

            }, "Discord-RPC");
            discordThread.setDaemon(true);
            discordThread.start();
         } catch (Throwable t) {
            running = false;
            System.err.println("[MiaWare] Discord RPC failed to start: " + t.getMessage());
            t.printStackTrace();
         }

      }
   }

   public static void refreshPresence() {
      if (running) {
         try {
            String state = "lux visuals";
            String details = K64796fec45136134f7() + " | t.me/k3dclient";
            String imageText = Kf4b25847a411d7cd4a();
            if (details.equals(lastDetails) && imageText.equals(lastImageText)) {
               return;
            }

            lastDetails = details;
            lastImageText = imageText;
            K58c24c5030de440d40 builder = new K58c24c5030de440d40();
            builder.setState(state);
            builder.setDetails(details);
            builder.setLargeImage("ava", imageText);
            builder.setStartTimestamp(System.currentTimeMillis() / 1000L);
            Kc339d7f4ca09116837.INSTANCE.Discord_UpdatePresence(builder.build());
         } catch (Throwable t) {
            System.err.println("[MiaWare] Discord RPC update failed: " + t.getMessage());
         }

      }
   }

   public static void stopRPC() {
      if (running) {
         try {
            running = false;
            lastDetails = "";
            lastImageText = "";
            if (discordThread != null) {
               discordThread.interrupt();
            }

            Kc339d7f4ca09116837.INSTANCE.Discord_Shutdown();
         } catch (Throwable t) {
            System.err.println("[MiaWare] Discord RPC failed to stop: " + t.getMessage());
         }

      }
   }

   private static String Kd1b7d7c2bd94cf1ea0() {
      return K64796fec45136134f7() + " | Developer build";
   }

   private static String Kf4b25847a411d7cd4a() {
      return K16dc01903bb4f9d660();
   }

   private static String K64796fec45136134f7() {
      return Kd10bcb33e37f629df6.protectedString(K93d0ef925a2fcdf0d6.getUsername());
   }

   private static String K16dc01903bb4f9d660() {
      if (mc.method_1542()) {
         return "Singleplayer";
      } else {
         try {
            class_642 serverInfo = mc.method_1558();
            if (serverInfo != null && serverInfo.field_3761 != null && !serverInfo.field_3761.isBlank()) {
               return K7f3a723aedd6e579b7(serverInfo.field_3761);
            }
         } catch (Throwable var4) {
         }

         try {
            if (mc.method_1562() != null && mc.method_1562().method_48296() != null) {
               SocketAddress address = mc.method_1562().method_48296().method_10755();
               if (address instanceof InetSocketAddress) {
                  InetSocketAddress inetSocketAddress = (InetSocketAddress)address;
                  String host = inetSocketAddress.getHostString();
                  if (host != null && !host.isBlank()) {
                     return host;
                  }
               } else if (address != null) {
                  return K7f3a723aedd6e579b7(address.toString());
               }
            }
         } catch (Throwable var3) {
         }

         return "Unknown";
      }
   }

   private static String K7f3a723aedd6e579b7(String address) {
      if (address != null && !address.isBlank()) {
         String normalized = address.trim().replace("<unresolved>", "").trim();
         if (normalized.contains("/")) {
            String[] split = normalized.split("/", 2);
            String left = split.length > 0 ? split[0].trim() : "";
            String right = split.length > 1 ? split[1].trim() : "";
            normalized = !left.isBlank() ? left : right;
         }

         if (normalized.startsWith("[") && normalized.contains("]:")) {
            int end = normalized.indexOf(93);
            if (end > 0) {
               return normalized.substring(0, end + 1);
            }
         }

         long colonCount = normalized.chars().filter((ch) -> ch == 58).count();
         if (colonCount == 1L) {
            int colonIndex = normalized.lastIndexOf(58);
            if (colonIndex > 0) {
               normalized = normalized.substring(0, colonIndex);
            }
         }

         return normalized.isBlank() ? "Unknown" : normalized;
      } else {
         return "Unknown";
      }
   }

   @Generated
   private K7a815b1a7330bf39b3() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
