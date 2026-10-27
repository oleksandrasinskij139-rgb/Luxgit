package code.miaware.api.utils.server;

import code.miaware.api.module.K5c460ed4a42f0797d6;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.ServerRestriction;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.client.features.modules.render.Kf8178702dbaef55c58;
import java.io.PrintStream;
import lombok.Generated;

public class Kbe30937e40237cb7db implements K4600bef438dafc1a9c {
   private static K079c3cf92902a5b0ab currentServer;

   public static void detectServer() {
      if (mc.method_1558() == null) {
         currentServer = K079c3cf92902a5b0ab.UNKNOWN;
      } else {
         String serverAddress = mc.method_1558().field_3761.toLowerCase();
         if (serverAddress.contains("funtime")) {
            currentServer = K079c3cf92902a5b0ab.FUNTIME;
         } else if (!serverAddress.contains("reallyworld") && !serverAddress.contains("rw")) {
            if (!serverAddress.contains("hollyworld") && !serverAddress.contains("hw")) {
               if (serverAddress.contains("dreamworld")) {
                  currentServer = K079c3cf92902a5b0ab.DREAMWORLD;
               } else if (serverAddress.contains("vibepvp")) {
                  currentServer = K079c3cf92902a5b0ab.VIBEPVP;
               } else if (serverAddress.contains("sworld")) {
                  currentServer = K079c3cf92902a5b0ab.SWORLD;
               } else if (serverAddress.contains("gulpvp")) {
                  currentServer = K079c3cf92902a5b0ab.GULPVP;
               } else {
                  currentServer = K079c3cf92902a5b0ab.UNKNOWN;
               }
            } else {
               currentServer = K079c3cf92902a5b0ab.HOLLYWORLD;
            }
         } else {
            currentServer = K079c3cf92902a5b0ab.REALLYWORLD;
         }

         Kcd3ca7382719354041();
      }
   }

   private static void Kcd3ca7382719354041() {
      for(Kab8a8c478f7d0077cd module : K5c460ed4a42f0797d6.getInstance().getModules()) {
         if (module.isEnabled() && isModuleBlocked(module)) {
            PrintStream var10000 = System.out;
            String var10001 = module.getName();
            var10000.println("[ServerDetector] Отключаем модуль " + var10001 + " на сервере " + String.valueOf(currentServer));
            module.setEnabled(false, true);
         }
      }

   }

   public static boolean isModuleBlocked(Kab8a8c478f7d0077cd module) {
      if (module instanceof Kf8178702dbaef55c58) {
         return mc != null && mc.field_1687 != null && mc.field_1724 != null && !Kf8178702dbaef55c58.serverAllowed();
      } else if (("FastExp".equalsIgnoreCase(module.getName()) || "AutoExp".equalsIgnoreCase(module.getName())) && isFastExpBlocked()) {
         return true;
      } else {
         ServerRestriction restriction = (ServerRestriction)module.getClass().getAnnotation(ServerRestriction.class);
         if (restriction == null) {
            return false;
         } else {
            for(K079c3cf92902a5b0ab blocked : restriction.blockedServers()) {
               if (blocked == currentServer) {
                  return true;
               }
            }

            if (restriction.allowedServers().length <= 0) {
               return false;
            } else {
               boolean allowed = false;

               for(K079c3cf92902a5b0ab allowedServer : restriction.allowedServers()) {
                  if (allowedServer == currentServer) {
                     allowed = true;
                     break;
                  }
               }

               return !allowed;
            }
         }
      }
   }

   public static boolean isFastExpBlocked() {
      return currentServer == K079c3cf92902a5b0ab.REALLYWORLD || currentServer == K079c3cf92902a5b0ab.DREAMWORLD;
   }

   public static boolean isFunTime() {
      return currentServer == K079c3cf92902a5b0ab.FUNTIME;
   }

   public static boolean isReallyWorld() {
      return currentServer == K079c3cf92902a5b0ab.REALLYWORLD;
   }

   public static boolean isHollyWorld() {
      return currentServer == K079c3cf92902a5b0ab.HOLLYWORLD;
   }

   public static boolean isDreamWorld() {
      return currentServer == K079c3cf92902a5b0ab.DREAMWORLD;
   }

   public static boolean isVibePVP() {
      return currentServer == K079c3cf92902a5b0ab.VIBEPVP;
   }

   public static boolean isSWorld() {
      return currentServer == K079c3cf92902a5b0ab.SWORLD;
   }

   public static boolean isGulPVP() {
      return currentServer == K079c3cf92902a5b0ab.GULPVP;
   }

   @Generated
   public static K079c3cf92902a5b0ab getCurrentServer() {
      return currentServer;
   }

   static {
      currentServer = K079c3cf92902a5b0ab.UNKNOWN;
   }
}
