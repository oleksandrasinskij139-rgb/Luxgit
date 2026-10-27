package code.miaware.api.system.client;

import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.client.features.modules.render.targetesp.K34929def563f4a28cf;
import code.miaware.client.features.modules.render.targetesp.K57a68ff1a2b51cf90d;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_3966;
import net.minecraft.class_239.class_240;

public class K4d1f360e533c3997d9 implements K4600bef438dafc1a9c {
   private static final K4d1f360e533c3997d9 INSTANCE = new K4d1f360e533c3997d9();
   private final Map<Integer, Long> recentTargets = new HashMap();
   private static final long TARGET_DURATION = 1500L;
   private class_1309 lastTarget = null;

   public static K4d1f360e533c3997d9 getInstance() {
      return INSTANCE;
   }

   public void init() {
      K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> this.K3d82f3737217eca3ef()));
   }

   private void K3d82f3737217eca3ef() {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         long currentTime = System.currentTimeMillis();
         class_1309 target = this.Ke89edcd70ec33880e0();
         if (target != null) {
            this.recentTargets.put(target.method_5628(), currentTime);
            this.lastTarget = target;
            K57a68ff1a2b51cf90d.currentTarget = target;
         } else {
            this.recentTargets.entrySet().removeIf((entry) -> currentTime - (Long)entry.getValue() > 1500L);
            if (this.lastTarget != null) {
               Long lastInteraction = (Long)this.recentTargets.get(this.lastTarget.method_5628());
               if (lastInteraction != null && currentTime - lastInteraction <= 1500L) {
                  K57a68ff1a2b51cf90d.currentTarget = this.lastTarget;
               } else {
                  this.recentTargets.remove(this.lastTarget.method_5628());
                  this.lastTarget = null;
                  K57a68ff1a2b51cf90d.currentTarget = null;
               }
            } else {
               K57a68ff1a2b51cf90d.currentTarget = null;
            }
         }

      } else {
         this.recentTargets.clear();
         this.lastTarget = null;
         K57a68ff1a2b51cf90d.currentTarget = null;
      }
   }

   private class_1309 Ke89edcd70ec33880e0() {
      if (mc.field_1765 != null && mc.field_1765.method_17783() == class_240.field_1331) {
         class_3966 entityHit = (class_3966)mc.field_1765;
         class_1297 entity = entityHit.method_17782();
         if (entity instanceof class_1309) {
            class_1309 living = (class_1309)entity;
            if (entity != mc.field_1724 && living.method_5805()) {
               if ((Boolean)K34929def563f4a28cf.getInstance().playersOnly.getValue()) {
                  if (!(living instanceof class_1657)) {
                     return null;
                  }

                  if (K137d4e93c7270b0a04.getInstance().contains(living.method_5477().getString())) {
                     return null;
                  }
               }

               double distance = mc.field_1724.method_5858(living);
               if (distance <= (double)16.0F) {
                  return living;
               }
            }
         }
      }

      return null;
   }

   public void markTarget(class_1309 entity) {
      if (entity != null && entity != mc.field_1724) {
         if ((Boolean)K34929def563f4a28cf.getInstance().playersOnly.getValue()) {
            if (!(entity instanceof class_1657)) {
               return;
            }

            if (K137d4e93c7270b0a04.getInstance().contains(entity.method_5477().getString())) {
               return;
            }
         }

         this.recentTargets.put(entity.method_5628(), System.currentTimeMillis());
         this.lastTarget = entity;
         K57a68ff1a2b51cf90d.currentTarget = entity;
      }

   }
}
