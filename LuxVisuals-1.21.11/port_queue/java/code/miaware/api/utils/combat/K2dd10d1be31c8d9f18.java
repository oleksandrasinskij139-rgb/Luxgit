package code.miaware.api.utils.combat;

import code.miaware.api.event.events.player.move.K363439524bd338fc89;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import net.minecraft.class_2848;
import net.minecraft.class_2848.class_2849;

public class K2dd10d1be31c8d9f18 implements K4600bef438dafc1a9c {
   private static final long LEGIT_SUPPRESSION_MS = 58L;
   public Kdf7e707136931b1e4d sprintType;
   private long suppressSprintUntil;

   public K2dd10d1be31c8d9f18(Kdf7e707136931b1e4d sprintType) {
      this.sprintType = sprintType;
   }

   public void legitSprint(K363439524bd338fc89 event, boolean suppressSprint) {
      if (suppressSprint) {
         this.extendLegitSuppression(58L);
      }

      if (this.sprintType == Kdf7e707136931b1e4d.LEGIT && this.isLegitSprintSuppressed()) {
         event.setSprint(false);
      }

   }

   public boolean stopSprintForAttack(boolean enabled) {
      if (enabled && mc.field_1724 != null && this.sprintType != Kdf7e707136931b1e4d.NONE) {
         boolean wasSprinting = mc.field_1724.method_5624() || mc.field_1724.field_3919;
         if (!wasSprinting) {
            return false;
         } else {
            if (this.sprintType == Kdf7e707136931b1e4d.LEGIT) {
               this.extendLegitSuppression(58L);
            }

            this.K8604cd60d21bc61ed8(false);
            return true;
         }
      } else {
         return false;
      }
   }

   public void restoreSprintAfterAttack(boolean restore, boolean enabled) {
      if (enabled && restore && mc.field_1724 != null && this.sprintType != Kdf7e707136931b1e4d.NONE) {
         if (this.sprintType == Kdf7e707136931b1e4d.LEGIT) {
            this.suppressSprintUntil = Math.min(this.suppressSprintUntil, System.currentTimeMillis() + 18L);
            if (mc.field_1690.field_1894.method_1434() && !mc.field_1724.method_6115() && !mc.field_1724.method_5715() && !mc.field_1724.field_5976 && mc.field_1724.method_7344().method_7586() > 6) {
               mc.field_1724.method_5728(true);
            }

         } else {
            this.K8604cd60d21bc61ed8(true);
         }
      }
   }

   public boolean isLegitSprintSuppressed() {
      return this.sprintType == Kdf7e707136931b1e4d.LEGIT && System.currentTimeMillis() < this.suppressSprintUntil;
   }

   public void extendLegitSuppression(long durationMs) {
      if (this.sprintType == Kdf7e707136931b1e4d.LEGIT) {
         this.suppressSprintUntil = Math.max(this.suppressSprintUntil, System.currentTimeMillis() + Math.max(0L, durationMs));
      }
   }

   private void K8604cd60d21bc61ed8(boolean enable) {
      if (mc.field_1724 != null) {
         if (this.sprintType == Kdf7e707136931b1e4d.PACKET) {
            this.sendPacket(new class_2848(mc.field_1724, enable ? class_2849.field_12981 : class_2849.field_12985));
         }

         mc.field_1724.method_5728(enable);
      }
   }
}
