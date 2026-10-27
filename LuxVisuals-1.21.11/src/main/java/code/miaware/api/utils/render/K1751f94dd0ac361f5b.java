package code.miaware.api.utils.render;

public final class K1751f94dd0ac361f5b {
   public static final long INTERVAL_NS = 16666666L;
   private long lastRefresh;
   private long nextRefresh;
   private volatile boolean valid;

   public boolean needsRefresh(long now) {
      return !this.valid || now >= this.nextRefresh || now < this.lastRefresh;
   }

   public void refreshed(long now) {
      if (this.valid && now >= this.lastRefresh) {
         this.nextRefresh += 16666666L;
         if (this.nextRefresh <= now) {
            this.nextRefresh = now + 16666666L;
         }
      } else {
         this.nextRefresh = now + 16666666L;
      }

      this.lastRefresh = now;
      this.valid = true;
   }

   public void invalidate() {
      this.valid = false;
   }
}
