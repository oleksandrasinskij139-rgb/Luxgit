package code.miaware.api.utils.combat;

import code.miaware.api.event.events.player.move.K363439524bd338fc89;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.player.K131e64425767c5c203;
import code.miaware.api.utils.rotation.K970216267e60d1d8b5;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Generated;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_3966;

public class K48861d9d76e5aa971f implements K4600bef438dafc1a9c {
   private final Kfb99cba3d2d6857c00 clickScheduler = new Kfb99cba3d2d6857c00();
   private final K2dd10d1be31c8d9f18 sprintManager;
   private final Kadfdac81e92b1251ea shieldBreakManager;
   private static final long RAYTRACE_FAIL_BACKOFF_MS = 90L;
   private static final long CRIT_WAIT_BACKOFF_MS = 42L;
   private static final float MIN_CRIT_FALL_DISTANCE = 0.054F;
   private static final float MAX_CRIT_FALL_DISTANCE = 0.096F;
   private static final double MIN_CRIT_FALL_VELOCITY = -0.056;
   private static final long MIN_ATTACK_JITTER_MS = 4L;
   private static final long MAX_ATTACK_JITTER_MS = 14L;
   private boolean critConsumedInAir;
   private boolean wasGrounded;
   private float critFallDistance;
   private double critFallVelocity;
   private Kbbe454d0235a8ec5cb configurable;

   public K48861d9d76e5aa971f() {
      this.sprintManager = new K2dd10d1be31c8d9f18(Kdf7e707136931b1e4d.LEGIT);
      this.shieldBreakManager = new Kadfdac81e92b1251ea();
      this.wasGrounded = true;
      this.critFallDistance = Ka4830e1868106c1e29();
      this.critFallVelocity = K00ad691629fa5d5783();
   }

   public void handleSprintEvent(K363439524bd338fc89 event) {
      if (mc.field_1724 != null) {
         this.sprintManager.sprintType = this.K4a9a6bab4789abc58b();
         this.K33c77e2d4fec31fc2b();
         this.sprintManager.legitSprint(event, this.shouldSuppressSprintForAttack());
      }
   }

   public void handleAttack() {
      if (mc.field_1724 != null && mc.field_1761 != null && this.configurable != null && this.configurable.target != null) {
         this.sprintManager.sprintType = this.K4a9a6bab4789abc58b();
         this.K33c77e2d4fec31fc2b();
         if (this.canAttack()) {
            if (this.K91fb7d4a5051ac1e9f(this.K521412b77df4e2bbe6())) {
               this.clickScheduler.defer(90L);
            } else {
               if (mc.field_1724.method_6039() && this.configurable.alwaysShield) {
                  mc.field_1761.method_2897(mc.field_1724);
               }

               boolean criticalAttack = this.configurable.onlyCrits && this.K63cf23e18d0831a7c0();
               boolean resetSprint = this.configurable.resetSprint && !mc.field_1724.method_5681() && !mc.field_1724.method_5799() && !mc.field_1724.method_5869();
               boolean restoreSprint = this.sprintManager.stopSprintForAttack(resetSprint);

               try {
                  boolean attacked;
                  label137: {
                     if (this.configurable.shieldBreak) {
                        class_1309 var6 = this.configurable.target;
                        if (var6 instanceof class_1657) {
                           class_1657 player = (class_1657)var6;
                           if (this.shieldBreakManager.canBreakShield(player)) {
                              attacked = this.shieldBreakManager.breakShield(player, this.configurable.packetShieldBreak);
                              break label137;
                           }
                        }
                     }

                     mc.field_1761.method_2918(mc.field_1724, this.configurable.target);
                     mc.field_1724.method_6104(class_1268.field_5808);
                     attacked = true;
                  }

                  if (attacked) {
                     if (criticalAttack && !mc.field_1724.method_24828()) {
                        this.critConsumedInAir = true;
                        this.critFallDistance = Ka4830e1868106c1e29();
                        this.critFallVelocity = K00ad691629fa5d5783();
                     }

                     this.clickScheduler.recalculate(this.Kba71df64ccbab3162f());
                     return;
                  }
               } finally {
                  this.sprintManager.restoreSprintAfterAttack(restoreSprint, resetSprint);
               }

            }
         }
      }
   }

   public boolean canAttack() {
      if (mc.field_1724 != null && this.configurable != null && this.configurable.target != null) {
         this.K33c77e2d4fec31fc2b();
         if (this.Ka45d2008c0f8f9463d()) {
            return false;
         } else if (this.configurable.noAttackIfEat && K131e64425767c5c203.isEating()) {
            return false;
         } else {
            if (this.configurable.shieldBreak) {
               class_1309 var2 = this.configurable.target;
               if (var2 instanceof class_1657) {
                  class_1657 player = (class_1657)var2;
                  if (this.shieldBreakManager.canBreakShield(player)) {
                     return true;
                  }
               }
            }

            if (!this.configurable.onlyCrits) {
               return true;
            } else if (this.K63cf23e18d0831a7c0()) {
               return true;
            } else {
               boolean fallback = this.configurable.smartCrits && this.Kebed0881e1ff1ad0c1();
               if (!fallback) {
                  this.clickScheduler.defer(42L);
               }

               return fallback;
            }
         }
      } else {
         return false;
      }
   }

   private boolean K91fb7d4a5051ac1e9f(class_1297 targetEntity) {
      return this.configurable.raytrace && targetEntity != this.configurable.target && !mc.field_1724.method_6128();
   }

   private boolean Ka45d2008c0f8f9463d() {
      return !this.clickScheduler.isCooldownComplete();
   }

   private boolean K63cf23e18d0831a7c0() {
      return !this.critConsumedInAir && Kc10da75b3aff369d72.canCriticalHitNow(mc.field_1724, this.critFallDistance, this.critFallVelocity);
   }

   private boolean Kebed0881e1ff1ad0c1() {
      return mc.field_1724 == null || !mc.field_1724.method_5681() && !mc.field_1724.method_5799() && !mc.field_1724.method_5869() ? Kc10da75b3aff369d72.shouldFallbackToNormalHit(mc.field_1724) : true;
   }

   private long Kba71df64ccbab3162f() {
      float cooldownTicks = mc.field_1724.method_7279();
      long jitter = ThreadLocalRandom.current().nextLong(4L, 15L);
      long baseDelay = Math.max(1L, (long)Math.round(cooldownTicks * 50.0F) + 12L + jitter);
      return this.configurable.syncTps ? Ka9db51464bdacb7bf5.getInstance().getAdjustedCooldown(baseDelay) : baseDelay;
   }

   public boolean shouldSuppressSprintForAttack() {
      if (mc.field_1724 != null && this.configurable != null && this.configurable.target != null && this.configurable.resetSprint) {
         if (this.sprintManager.isLegitSprintSuppressed()) {
            return true;
         } else if (!mc.field_1724.method_5681() && !mc.field_1724.method_5799() && !mc.field_1724.method_5869()) {
            if (this.canAttack()) {
               return true;
            } else if (!this.clickScheduler.isOneTickBeforeAttack()) {
               return false;
            } else {
               return !this.configurable.onlyCrits ? true : this.Kd810e3c0ffe31d59d1();
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private class_1297 K521412b77df4e2bbe6() {
      class_3966 entityHitResult = K970216267e60d1d8b5.raytraceEntity((double)this.configurable.distance, this.configurable.rotation, this.configurable.ignoreWalls, this.configurable.hitboxExpand);
      return entityHitResult != null ? entityHitResult.method_17782() : null;
   }

   private Kdf7e707136931b1e4d K4a9a6bab4789abc58b() {
      return Kdf7e707136931b1e4d.LEGIT;
   }

   private void K33c77e2d4fec31fc2b() {
      if (mc.field_1724 == null) {
         this.critConsumedInAir = false;
         this.wasGrounded = true;
      } else {
         boolean grounded = mc.field_1724.method_24828() || Kc10da75b3aff369d72.isEnvironmentBlocked(mc.field_1724);
         if (grounded) {
            this.critConsumedInAir = false;
         }

         if (grounded && !this.wasGrounded) {
            this.critFallDistance = Ka4830e1868106c1e29();
            this.critFallVelocity = K00ad691629fa5d5783();
         }

         this.wasGrounded = grounded;
      }
   }

   private boolean Kd810e3c0ffe31d59d1() {
      if (mc.field_1724 != null && !this.critConsumedInAir && !Kc10da75b3aff369d72.isEnvironmentBlocked(mc.field_1724)) {
         return !mc.field_1724.method_24828() && mc.field_1724.method_18798().field_1351 < -0.03 && mc.field_1724.field_6017 >= this.critFallDistance * 0.65F;
      } else {
         return false;
      }
   }

   private static float Ka4830e1868106c1e29() {
      return ThreadLocalRandom.current().nextFloat(0.054F, 0.096F);
   }

   private static double K00ad691629fa5d5783() {
      return ThreadLocalRandom.current().nextDouble(-0.082, -0.06);
   }

   @Generated
   public Kfb99cba3d2d6857c00 clickScheduler() {
      return this.clickScheduler;
   }

   @Generated
   public K2dd10d1be31c8d9f18 sprintManager() {
      return this.sprintManager;
   }

   @Generated
   public Kadfdac81e92b1251ea shieldBreakManager() {
      return this.shieldBreakManager;
   }

   @Generated
   public boolean critConsumedInAir() {
      return this.critConsumedInAir;
   }

   @Generated
   public boolean wasGrounded() {
      return this.wasGrounded;
   }

   @Generated
   public float critFallDistance() {
      return this.critFallDistance;
   }

   @Generated
   public double critFallVelocity() {
      return this.critFallVelocity;
   }

   @Generated
   public Kbbe454d0235a8ec5cb configurable() {
      return this.configurable;
   }

   @Generated
   public K48861d9d76e5aa971f configurable(Kbbe454d0235a8ec5cb configurable) {
      this.configurable = configurable;
      return this;
   }
}
