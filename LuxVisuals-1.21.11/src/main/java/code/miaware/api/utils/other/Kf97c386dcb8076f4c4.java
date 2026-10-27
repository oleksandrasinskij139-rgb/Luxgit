package code.miaware.api.utils.other;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Kf97c386dcb8076f4c4 {
   public static void sendNotification(String title, String message) {
      System.out.println("[Notification] Отправка уведомления: " + title);

      try {
         String escapedTitle = Kc23dfc68d866d4962b(title);
         String escapedMessage = Kc23dfc68d866d4962b(message);
         ProcessBuilder pb = new ProcessBuilder(new String[]{"cmd.exe", "/c", "msg", "*", "/TIME:5", "IRC: " + title + "\n\n" + message});
         pb.redirectErrorStream(true);
         Process process = pb.start();
         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "CP866"));

         String line;
         try {
            while((line = reader.readLine()) != null) {
               System.out.println("[Notification] CMD: " + line);
            }
         } catch (Throwable var10) {
            try {
               reader.close();
            } catch (Throwable var9) {
               var10.addSuppressed(var9);
            }

            throw var10;
         }

         reader.close();
         int exitCode = process.waitFor();
         if (exitCode == 0) {
            System.out.println("[Notification] Уведомление отправлено успешно");
         } else {
            System.err.println("[Notification] CMD завершился с кодом: " + exitCode);
         }
      } catch (Exception e) {
         System.err.println("[Notification] Ошибка отправки уведомления: " + e.getMessage());
         e.printStackTrace();
      }

   }

   private static String Kc23dfc68d866d4962b(String text) {
      return text == null ? "" : text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&apos;");
   }
}
