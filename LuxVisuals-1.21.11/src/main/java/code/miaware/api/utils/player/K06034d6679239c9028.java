package code.miaware.api.utils.player;

public final class K06034d6679239c9028 {
   private K06034d6679239c9028() {
   }

   public static int releaseTicks(float delay) {
      int level = Float.isFinite(delay) ? Math.clamp((long)Math.round(delay), 1, 5) : 1;
      return 2 + level;
   }

   public static boolean shouldRelease(int useTicks, float delay) {
      return useTicks >= releaseTicks(delay);
   }
}
