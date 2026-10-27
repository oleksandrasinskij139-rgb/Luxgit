package dev.luxvisuals.fix;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import luxvisuals.customsword.CustomSword;
import net.fabricmc.api.ClientModInitializer;

public class LuxPlus implements ClientModInitializer {
   static final Map<UUID, String> REMOTE_SWORD = new ConcurrentHashMap();

   public void onInitializeClient() {
      try {
         Sword.register();
      } catch (Throwable var2) {
         var2.printStackTrace();
      }

   }

   static Object mc() throws Exception {
      return Class.forName("net.minecraft.class_310").getMethod("method_1551").invoke((Object)null);
   }

   static Object field(Object var0, String var1) throws Exception {
      return var0.getClass().getField(var1).get(var0);
   }

   static Object call(Object var0, String var1) throws Exception {
      return var0.getClass().getMethod(var1).invoke(var0);
   }

   static String[] swordState() {
      String var0 = CustomSword.selectedName();
      return var0 != null ? new String[]{var0, "1"} : new String[]{"", "0"};
   }

   static boolean swordEnabled() {
      return "1".equals(swordState()[1]);
   }
}
