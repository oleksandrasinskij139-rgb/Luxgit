package code.miaware.client.ui.widget.overlay;

class Ka2d652bed71429059a {
   private final String text;
   private final Ka58c9e2c4d8aa9af2e type;
   private final long startedAt = System.currentTimeMillis();
   private final long durationMs = 2500L;
   private boolean expired;
   private float visualY;
   private boolean positionInitialized;
   private boolean module;
   private boolean enabled;
   private float knob;

   private Ka2d652bed71429059a(String text, Ka58c9e2c4d8aa9af2e type) {
      this.text = text;
      this.type = type;
   }

   private float K84689c8c5788020351() {
      long elapsed = System.currentTimeMillis() - this.startedAt;
      long fadeMs = 180L;
      if (elapsed < fadeMs) {
         return (float)elapsed / (float)fadeMs;
      } else if (elapsed < 2500L - fadeMs) {
         return 1.0F;
      } else {
         return elapsed < 2500L ? 1.0F - (float)(elapsed - (2500L - fadeMs)) / (float)fadeMs : 0.0F;
      }
   }

   private boolean Kf6c7ec8e283c92e34e() {
      if (!this.expired && System.currentTimeMillis() - this.startedAt > 2500L) {
         this.expired = true;
      }

      return this.expired && this.K84689c8c5788020351() <= 0.03F;
   }
}
