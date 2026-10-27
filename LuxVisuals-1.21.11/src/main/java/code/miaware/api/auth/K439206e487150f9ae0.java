package code.miaware.api.auth;

import java.util.UUID;

public class K439206e487150f9ae0 {
   public static UUID parseUuid(String string) {
      try {
         return UUID.fromString(string);
      } catch (IllegalArgumentException var2) {
         return Kfa290ad8b4971e7a86(string);
      }
   }

   private static UUID Kfa290ad8b4971e7a86(String input) {
      if (input.length() != 32) {
         throw new IllegalArgumentException("FUNOFF4IK XYU");
      } else {
         long mostSigBits = Long.parseUnsignedLong(input.substring(0, 16), 16);
         long leastSigBits = Long.parseUnsignedLong(input.substring(16, 32), 16);
         return new UUID(mostSigBits, leastSigBits);
      }
   }

   public static UUID generateOfflinePlayerUuid(String name) {
      return UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes());
   }
}
