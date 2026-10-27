package code.miaware.api.system.client;

import code.miaware.api.event.events.player.world.K503912bdf3fa552d14;
import code.miaware.api.event.events.player.world.Kbe70ab59f013299613;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_310;
import net.minecraft.class_746;

public final class K9a4619034baf09d6b2 {
   private static final K70557a473eb0eb377a evidence = new K70557a473eb0eb377a();
   private static Object world;

   private K9a4619034baf09d6b2() {
   }

   private static boolean ready(class_1309 victim) {
      class_310 mc = class_310.method_1551();
      if (world != mc.field_1687) {
         evidence.reset();
         world = mc.field_1687;
      }

      return mc.field_1687 != null && mc.field_1724 != null && victim != null && !victim.method_5667().equals(mc.field_1724.method_5667());
   }

   private static boolean local(class_1297 attacker) {
      class_746 player = class_310.method_1551().field_1724;
      return attacker != null && player != null && attacker.method_5667().equals(player.method_5667());
   }

   public static synchronized void attack(class_1309 victim) {
      if (ready(victim) && victim.method_5805() && !class_310.method_1551().field_1724.method_7325()) {
         evidence.attack(victim.method_5667(), System.nanoTime());
      }

   }

   public static synchronized void damage(class_1309 victim, class_1282 source) {
      if (ready(victim)) {
         class_1297 attacker = source.method_5529();
         evidence.damage(victim.method_5667(), local(attacker), attacker != null && !local(attacker), System.nanoTime());
      }
   }

   public static synchronized void death(class_1309 victim, class_1282 source) {
      if (ready(victim)) {
         class_1297 attacker = (class_1297)(source != null ? source.method_5529() : victim.method_6065());
         if (evidence.death(victim.method_5667(), 3, local(attacker), attacker != null && !local(attacker), System.nanoTime())) {
            K503912bdf3fa552d14.getInstance().call(new Kbe70ab59f013299613(victim));
         }

      }
   }

   public static synchronized void reset() {
      evidence.reset();
      world = null;
   }

   static {
      ClientPlayConnectionEvents.DISCONNECT.register((ClientPlayConnectionEvents.Disconnect)(handler, client) -> reset());
   }
}
