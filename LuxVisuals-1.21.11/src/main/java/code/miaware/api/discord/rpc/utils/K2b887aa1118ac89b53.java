package code.miaware.api.discord.rpc.utils;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class K2b887aa1118ac89b53 extends Structure {
   public String userId;
   public String username;
   /** @deprecated */
   @Deprecated
   public String discriminator;
   public String avatar;

   protected List<String> getFieldOrder() {
      return Arrays.asList("userId", "username", "discriminator", "avatar");
   }
}
