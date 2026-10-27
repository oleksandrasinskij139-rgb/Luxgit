package code.miaware.api.utils.math;

import java.util.Random;

public class K4ccfce20b90a27da2f extends Random {
   private long seed;

   public K4ccfce20b90a27da2f(long seed) {
      this.seed = seed;
   }

   public K4ccfce20b90a27da2f() {
      this(System.nanoTime());
   }

   public int randomInRange(int min, int max) {
      return min != max && min <= max ? this.nextInt(max - min + 1) + min : min;
   }

   public float randomInRange(float min, float max) {
      return min + this.nextFloat() * (max - min);
   }

   public double randomInRange(double min, double max) {
      return min + this.nextDouble() * (max - min);
   }

   protected int next(int bits) {
      this.seed ^= this.seed << 13;
      this.seed ^= this.seed >>> 17;
      this.seed ^= this.seed << 5;
      return (int)(this.seed & (1L << bits) - 1L);
   }
}
