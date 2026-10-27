package code.miaware.api.system.client;

import code.miaware.api.event.events.player.world.K503912bdf3fa552d14;
import code.miaware.api.event.events.player.world.Kbe70ab59f013299613;
import code.miaware.api.system.backend.K8e471a90dc4db42e81;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1297;
import net.minecraft.class_1309;

public class K363a067af3649bf06b {
   private static final Map<UUID, Float> lastHealthMap = new HashMap();
   private static final Set<UUID> damagedByPlayer = new HashSet();
   private static final Set<UUID> processedKills = new HashSet();
   private static int debugCounter = 0;

   public static void tick() {
      if (K8e471a90dc4db42e81.player() != null && K8e471a90dc4db42e81.world() != null) {
         ++debugCounter;

         for(class_1297 entity : K8e471a90dc4db42e81.world().method_18112()) {
            if (entity instanceof class_1309) {
               class_1309 livingEntity = (class_1309)entity;
               if (!entity.equals(K8e471a90dc4db42e81.player())) {
                  UUID entityUUID = entity.method_5667();
                  float currentHealth = livingEntity.method_6032();
                  Float lastHealth = (Float)lastHealthMap.get(entityUUID);
                  lastHealthMap.put(entityUUID, currentHealth);
                  if (lastHealth != null && currentHealth < lastHealth) {
                     if (livingEntity.method_6065() != null && livingEntity.method_6065().method_5667().equals(K8e471a90dc4db42e81.player().method_5667())) {
                        damagedByPlayer.add(entityUUID);
                        if (debugCounter % 20 == 0) {
                           PrintStream var10000 = System.out;
                           String var10001 = livingEntity.method_5864().method_5897().getString();
                           var10000.println("[KillTracker] Entity damaged by player: " + var10001 + " HP: " + currentHealth);
                        }
                     }

                     double distance = entity.method_5858(K8e471a90dc4db42e81.player());
                     if (distance < (double)100.0F) {
                        damagedByPlayer.add(entityUUID);
                     }
                  }

                  if (damagedByPlayer.contains(entityUUID) && !processedKills.contains(entityUUID) && (livingEntity.method_29504() || currentHealth <= 0.0F)) {
                     processedKills.add(entityUUID);
                     damagedByPlayer.remove(entityUUID);
                     K503912bdf3fa552d14.getInstance().call(new Kbe70ab59f013299613(livingEntity));
                     System.out.println("[KillTracker] *** KILL DETECTED ***: " + livingEntity.method_5864().method_5897().getString());
                  }
               }
            }
         }

         if (K8e471a90dc4db42e81.player().field_6012 % 100 == 0) {
            K027a6fac1f22680b03();
         }

      }
   }

   private static void K027a6fac1f22680b03() {
      if (K8e471a90dc4db42e81.world() != null) {
         Set<UUID> currentEntities = new HashSet();

         for(class_1297 entity : K8e471a90dc4db42e81.world().method_18112()) {
            currentEntities.add(entity.method_5667());
         }

         lastHealthMap.keySet().retainAll(currentEntities);
         damagedByPlayer.retainAll(currentEntities);
         processedKills.retainAll(currentEntities);
         System.out.println("[KillTracker] Cleanup: tracking " + damagedByPlayer.size() + " damaged entities");
      }
   }

   public static void reset() {
      lastHealthMap.clear();
      damagedByPlayer.clear();
      processedKills.clear();
   }
}
