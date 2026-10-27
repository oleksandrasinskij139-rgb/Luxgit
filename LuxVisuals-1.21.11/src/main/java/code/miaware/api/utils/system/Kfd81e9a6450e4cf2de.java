package code.miaware.api.utils.system;

import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.util.Enumeration;

public class Kfd81e9a6450e4cf2de {
   private static String cachedHWID = null;

   public static String getHWID() {
      if (cachedHWID != null) {
         return cachedHWID;
      } else {
         try {
            StringBuilder hwid = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            while(networkInterfaces.hasMoreElements()) {
               NetworkInterface ni = (NetworkInterface)networkInterfaces.nextElement();
               byte[] mac = ni.getHardwareAddress();
               if (mac != null && mac.length > 0) {
                  for(byte b : mac) {
                     hwid.append(String.format("%02X", b));
                  }
                  break;
               }
            }

            hwid.append(System.getProperty("os.name"));
            hwid.append(System.getProperty("os.arch"));
            hwid.append(System.getProperty("user.name"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(hwid.toString().getBytes());
            StringBuilder result = new StringBuilder();

            for(byte b : hash) {
               result.append(String.format("%02x", b));
            }

            cachedHWID = result.toString().toUpperCase();
            return cachedHWID;
         } catch (Exception var9) {
            cachedHWID = "UNKNOWN-" + System.currentTimeMillis();
            return cachedHWID;
         }
      }
   }
}
