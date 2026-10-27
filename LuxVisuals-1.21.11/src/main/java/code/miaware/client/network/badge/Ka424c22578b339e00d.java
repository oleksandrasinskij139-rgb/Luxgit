package code.miaware.client.network.badge;

import java.util.UUID;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_634;

public final class Ka424c22578b339e00d {
   private static final Kd54913d10ff6ea6960 DIRECTORY = new Kd54913d10ff6ea6960();
   public static final String LOGO = "\ue999";
   private static final class_2960 FONT = class_2960.method_60655("luxvisuals", "badge");
   private static boolean initialized;

   private Ka424c22578b339e00d() {
   }

   public static synchronized void init() {
   }

   public static class_2561 decorate(UUID uuid, String name, class_2561 original) {
      return (class_2561)(original != null && !original.getString().contains("\ue999") && DIRECTORY.contains(uuid, name) ? class_2561.method_43473().method_10852(class_2561.method_43470("\ue999").method_10862(class_2583.field_24360.method_27704(FONT).method_36139(16777215))).method_10852(class_2561.method_43470(" ").method_10862(class_2583.field_24360)).method_10852(original) : original);
   }

   // $FF: synthetic method
   private static void lambda$init$2(class_310 client) {
      DIRECTORY.close();
   }

   // $FF: synthetic method
   private static void lambda$init$1(class_634 handler, class_310 client) {
      DIRECTORY.close();
   }

   // $FF: synthetic method
   private static void lambda$init$0(class_310 client) {
      if (client.field_1724 != null && client.field_1687 != null && client.method_1562() != null) {
         DIRECTORY.start(client.field_1724.method_5667(), client.field_1724.method_7334().getName());
      } else {
         DIRECTORY.close();
      }
   }
}
