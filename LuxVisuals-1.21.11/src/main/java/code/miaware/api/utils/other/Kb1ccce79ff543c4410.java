package code.miaware.api.utils.other;

import java.util.Locale;
import java.util.Set;

public final class Kb1ccce79ff543c4410 {
   private static final Set<String> COMMANDS = Set.of("login", "l", "log", "reg", "register", "registration", "regestration", "auth", "authenticate", "signin", "signup", "changepassword", "changepass");

   private Kb1ccce79ff543c4410() {
   }

   public static int secretStart(String input) {
      int start;
      for(start = 0; start < input.length() && Character.isWhitespace(input.charAt(start)); ++start) {
      }

      if (start < input.length() && input.charAt(start) == '/') {
         int end;
         for(end = start + 1; end < input.length() && !Character.isWhitespace(input.charAt(end)); ++end) {
         }

         String command = input.substring(start + 1, end).toLowerCase(Locale.ROOT);
         int namespace = command.indexOf(58);
         if (namespace >= 0) {
            if (namespace == 0 || command.indexOf(58, namespace + 1) >= 0) {
               return -1;
            }

            command = command.substring(namespace + 1);
         }

         return COMMANDS.contains(command) ? end : -1;
      } else {
         return -1;
      }
   }

   public static boolean isAuthentication(String input) {
      return secretStart(input) >= 0;
   }

   public static String mask(String input) {
      int start = secretStart(input);
      if (start >= 0 && start != input.length()) {
         StringBuilder visible = new StringBuilder(input);

         for(int i = start; i < input.length(); ++i) {
            if (!Character.isWhitespace(input.charAt(i))) {
               visible.setCharAt(i, '*');
            }
         }

         return visible.toString();
      } else {
         return input;
      }
   }

   public static String visibleSlice(String fullInput, int offset, int length) {
      String masked = mask(fullInput);
      int start = Math.max(0, Math.min(masked.length(), offset));
      return masked.substring(start, start + Math.max(0, Math.min(masked.length() - start, length)));
   }
}
