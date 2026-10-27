package code.miaware.api.utils.combat;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K001d59b5164d1d1f95;
import code.miaware.api.event.events.client.Kb652aee96d70f375af;
import lombok.Generated;
import net.minecraft.class_2761;
import net.minecraft.class_3532;

public class Ka9db51464bdacb7bf5 {
   private static final Ka9db51464bdacb7bf5 instance = new Ka9db51464bdacb7bf5();
   private final K8d7165024ff567f5e7 packetListener = K001d59b5164d1d1f95.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
      if (event.packetType() == Kb652aee96d70f375af.RECEIVE) {
         if (event.packet() instanceof class_2761) {
            long now = System.currentTimeMillis();
            if (this.lastWorldTimePacketAt > 0L) {
               long diff = now - this.lastWorldTimePacketAt;
               if (diff > 0L) {
                  float measuredTps = class_3532.method_15363(20000.0F / (float)diff, 0.1F, 20.0F);
                  this.currentTps = class_3532.method_16439(0.35F, this.currentTps, measuredTps);
               }
            }

            this.lastWorldTimePacketAt = now;
         }
      }
   }));
   private long lastWorldTimePacketAt = -1L;
   private float currentTps = 20.0F;

   private Ka9db51464bdacb7bf5() {
   }

   public float getCurrentTps() {
      return class_3532.method_15363(this.currentTps, 0.1F, 20.0F);
   }

   public long getAdjustedCooldown(long baseCooldown) {
      float tps = this.getCurrentTps();
      if (tps >= 19.85F) {
         return baseCooldown;
      } else {
         float multiplier = 20.0F / tps;
         long adjusted = (long)((float)baseCooldown * multiplier);
         return Math.min(adjusted, 3000L);
      }
   }

   @Generated
   public static Ka9db51464bdacb7bf5 getInstance() {
      return instance;
   }
}
