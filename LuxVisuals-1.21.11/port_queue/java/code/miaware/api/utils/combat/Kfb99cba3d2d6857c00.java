package code.miaware.api.utils.combat;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;

public class Kfb99cba3d2d6857c00 implements K4600bef438dafc1a9c {
   private static final long MIN_ATTACK_DELAY_MS = 90L;
   private static final long MIN_PREPARE_WINDOW_MS = 35L;
   private long delay = 0L;
   private long lastClickTime = System.currentTimeMillis();

   public boolean isCooldownComplete() {
      float currentCooldown = mc.field_1724 != null ? mc.field_1724.method_7261(0.5F) : 1.0F;
      long currentTime = System.currentTimeMillis();
      long timeSinceLastClick = currentTime - this.lastClickTime;
      long requiredDelay = this.K81dbf637ae0d350440();
      return timeSinceLastClick >= requiredDelay && currentCooldown >= 0.985F;
   }

   public boolean hasTicksElapsedSinceLastClick(int ticks) {
      return this.lastClickPassed() >= (long)ticks * 50L;
   }

   public long lastClickPassed() {
      return System.currentTimeMillis() - this.lastClickTime;
   }

   public void recalculate(long delay) {
      this.lastClickTime = System.currentTimeMillis();
      this.delay = delay;
   }

   public void defer(long delay) {
      this.lastClickTime = System.currentTimeMillis();
      this.delay = Math.max(delay, 90L);
   }

   public boolean isOneTickBeforeAttack() {
      return this.willClickAt(1);
   }

   public boolean willClickAt(int tick) {
      long timeSinceLastClick = this.lastClickPassed();
      long requiredDelay = this.K81dbf637ae0d350440();
      long window = Math.max(35L, (long)tick * 50L);
      return timeSinceLastClick >= requiredDelay - window && timeSinceLastClick < requiredDelay + window;
   }

   public long toNext() {
      return Math.max(0L, this.K81dbf637ae0d350440() - this.lastClickPassed());
   }

   public float cooldownProgress(int baseTime) {
      return mc.field_1724 == null ? 0.0F : (float)(mc.field_1724.field_6273 + baseTime) / mc.field_1724.method_7279();
   }

   private long K81dbf637ae0d350440() {
      return Math.max(this.delay, 90L);
   }
}
