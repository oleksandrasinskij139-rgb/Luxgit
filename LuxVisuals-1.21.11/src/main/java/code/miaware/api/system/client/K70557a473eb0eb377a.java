package code.miaware.api.system.client;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

public final class K70557a473eb0eb377a {
   private static final long HIT_WINDOW = 10000000000L;
   private static final long DUPLICATE_WINDOW = 2000000000L;
   private final Map<UUID, Long> hits = new HashMap();
   private final Map<UUID, Long> deaths = new HashMap();

   public void reset() {
      this.hits.clear();
      this.deaths.clear();
   }

   public void attack(UUID victim, long now) {
      this.K22151fa755437d0cdc(now);
      if (this.hits.size() >= 128 && !this.hits.containsKey(victim)) {
         UUID oldest = (UUID)((Map.Entry)this.hits.entrySet().stream().min(Entry.comparingByValue()).orElseThrow()).getKey();
         this.hits.remove(oldest);
      }

      this.hits.put(victim, now);
   }

   public void damage(UUID victim, boolean localAttacker, boolean knownOtherAttacker, long now) {
      if (localAttacker) {
         this.attack(victim, now);
      } else if (knownOtherAttacker) {
         this.hits.remove(victim);
      }

   }

   public boolean death(UUID victim, int status, boolean localAttacker, boolean knownOtherAttacker, long now) {
      if (status != 3) {
         return false;
      } else {
         this.K22151fa755437d0cdc(now);
         Long hit = (Long)this.hits.remove(victim);
         if (!this.deaths.containsKey(victim) && !knownOtherAttacker && (localAttacker || hit != null)) {
            this.deaths.put(victim, now);
            return true;
         } else {
            return false;
         }
      }
   }

   private void K22151fa755437d0cdc(long now) {
      this.hits.entrySet().removeIf((e) -> now < (Long)e.getValue() || now - (Long)e.getValue() >= 10000000000L);
      this.deaths.entrySet().removeIf((e) -> now < (Long)e.getValue() || now - (Long)e.getValue() >= 2000000000L);
   }
}
