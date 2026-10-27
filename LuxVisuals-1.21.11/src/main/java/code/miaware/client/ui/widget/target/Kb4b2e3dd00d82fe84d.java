package code.miaware.client.ui.widget.target;

import java.util.Random;

public final class Kb4b2e3dd00d82fe84d {
   public static final int CAPACITY = 48;
   public static final double TEXT_DURATION = 0.27;
   private final Kffaa8b78ebdaa4c534[] bubbles = new Kffaa8b78ebdaa4c534[48];
   private final Random random = new Random();
   private Object target;
   private int cursor;
   private float lastHealth;
   private boolean known;
   private String text = "";
   private String previous = "";
   private double changed = (double)-10.0F;
   private int direction = 1;

   public Kb4b2e3dd00d82fe84d() {
      for(int i = 0; i < this.bubbles.length; ++i) {
         this.bubbles[i] = new Kffaa8b78ebdaa4c534();
      }

   }

   public Kffaa8b78ebdaa4c534[] bubbles() {
      return this.bubbles;
   }

   public String text() {
      return this.text;
   }

   public String previous() {
      return this.previous;
   }

   public int direction() {
      return this.direction;
   }

   public void reset() {
      if (this.target != null) {
         this.target = null;
         this.text = this.previous = "";
         this.known = false;
         this.cursor = 0;
         this.changed = (double)-10.0F;

         for(Kffaa8b78ebdaa4c534 bubble : this.bubbles) {
            bubble.born = (double)-10.0F;
         }

      }
   }

   public double progress(double now) {
      double t = Math.max((double)0.0F, Math.min((double)1.0F, (now - this.changed) / 0.27));
      return (double)1.0F - Math.pow((double)1.0F - t, (double)3.0F);
   }

   public void observe(Object nextTarget, float hp, float maximum, boolean isKnown, String label, double now) {
      isKnown &= Float.isFinite(hp) && Float.isFinite(maximum) && maximum > 0.0F;
      if (this.target == nextTarget && this.known == isKnown) {
         float difference = hp - this.lastHealth;
         if (isKnown && Math.abs(difference) >= 0.025F) {
            boolean healing = difference > 0.0F;
            int count = Math.min(14, 7 + (int)Math.ceil((double)Math.abs(difference)));

            for(int i = 0; i < count; ++i) {
               Kffaa8b78ebdaa4c534 bubble = this.bubbles[this.cursor];
               this.cursor = (this.cursor + 1) % 48;
               bubble.born = now;
               bubble.life = 0.6 + this.random.nextDouble() * 0.35;
               bubble.vx = (double)-12.0F - this.random.nextDouble() * (double)24.0F;
               bubble.vy = (double)-19.0F + this.random.nextDouble() * (double)32.0F;
               bubble.barFraction = (double)1.0F - Math.max((double)0.0F, Math.min((double)1.0F, ((double)hp + (double)(this.lastHealth - hp) * this.random.nextDouble()) / (double)maximum));
               bubble.radius = 0.8 + this.random.nextDouble() * 1.05;
               bubble.healing = healing;
            }

            this.direction = healing ? -1 : 1;
         }

         this.lastHealth = hp;
         if (!this.text.equals(label)) {
            this.previous = this.text;
            this.text = label;
            this.changed = now;
         }

      } else {
         this.reset();
         this.target = nextTarget;
         this.known = isKnown;
         this.lastHealth = hp;
         this.text = this.previous = label;
         this.changed = now - 0.27;
      }
   }

   public static double bubbleAlpha(Kffaa8b78ebdaa4c534 bubble, double now) {
      double age = now - bubble.born;
      return !(age < (double)0.0F) && !(age >= bubble.life) && !(bubble.life <= (double)0.0F) ? Math.min((double)1.0F, age / 0.035) * Math.pow((double)1.0F - age / bubble.life, 1.65) : (double)0.0F;
   }
}
