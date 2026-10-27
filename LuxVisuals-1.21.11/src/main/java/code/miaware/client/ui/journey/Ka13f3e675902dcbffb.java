package code.miaware.client.ui.journey;

public final class Ka13f3e675902dcbffb {
   private Object screen;
   private String dimension;
   private long began;
   private long departed;
   private boolean leaving;

   public void begin(Object screen, String dimension, long now) {
      if (this.screen != screen || !dimension.equals(this.dimension)) {
         this.screen = screen;
         this.dimension = dimension;
         this.began = now;
         this.leaving = false;
      }

   }

   public void finish(Object screen, long now) {
      if (this.screen == screen && this.dimension != null && !this.leaving) {
         this.departed = now;
         this.leaving = true;
      }

   }

   public String dimension() {
      return this.dimension;
   }

   public double seconds(long now) {
      return Math.max((double)0.0F, (double)(now - this.began) / (double)1.0E9F);
   }

   public float opacity(long now, float duration) {
      if (this.dimension == null) {
         return 0.0F;
      } else if (!this.leaving) {
         return 1.0F;
      } else {
         float progress = Math.max(0.0F, (float)(now - this.departed) / (Math.max(0.1F, duration) * 1.0E9F));
         if (progress >= 1.0F) {
            this.reset();
            return 0.0F;
         } else {
            return 1.0F - progress * progress * (3.0F - 2.0F * progress);
         }
      }
   }

   public boolean leaving() {
      return this.leaving;
   }

   public void reset() {
      this.screen = null;
      this.dimension = null;
      this.leaving = false;
   }
}
