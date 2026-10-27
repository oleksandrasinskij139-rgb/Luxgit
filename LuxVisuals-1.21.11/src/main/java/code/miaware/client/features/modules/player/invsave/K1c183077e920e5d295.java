package code.miaware.client.features.modules.player.invsave;

import net.minecraft.class_124;
import net.minecraft.class_1799;

public final class K1c183077e920e5d295 {
   private static final String PREFIX = "ah search ";

   private K1c183077e920e5d295() {
   }

   public static String commandFor(class_1799 stack) {
      if (stack != null && !stack.method_7960()) {
         String command = fromName(stack.method_7964().getString());
         return command.isEmpty() ? fromName(stack.method_7909().method_63680().getString()) : command;
      } else {
         return "";
      }
   }

   public static String fromName(String name) {
      if (name == null) {
         return "";
      } else {
         String plain = class_124.method_539(name);
         StringBuilder query = new StringBuilder();
         boolean space = false;
         int offset = 0;

         while(offset < plain.length()) {
            int codepoint = plain.codePointAt(offset);
            offset += Character.charCount(codepoint);
            if (!Character.isWhitespace(codepoint) && !Character.isSpaceChar(codepoint)) {
               if (!Character.isISOControl(codepoint)) {
                  int length = Character.charCount(codepoint) + (space ? 1 : 0);
                  if ("ah search ".length() + query.length() + length > 256) {
                     break;
                  }

                  if (space) {
                     query.append(' ');
                  }

                  query.appendCodePoint(codepoint);
                  space = false;
               }
            } else {
               space = !query.isEmpty();
            }
         }

         return query.isEmpty() ? "" : "ah search " + String.valueOf(query);
      }
   }
}
