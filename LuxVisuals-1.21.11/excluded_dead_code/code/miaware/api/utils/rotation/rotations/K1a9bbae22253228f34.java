package code.miaware.api.utils.rotation.rotations;

public class K1a9bbae22253228f34 extends Kdba8f529a186538629 {
   private static final K2247aa0d940589341e PROFILE = new K2247aa0d940589341e(118.0F, 130.0F, 42.0F, 68.0F, 0.04F, 0.12F, 118L, 28L, 112L, 0.08F, 0.018F, 0.044F);

   public K1a9bbae22253228f34() {
      super("FunTime");
   }

   protected K2247aa0d940589341e getProfile() {
      return PROFILE;
   }

   protected float extraYawNoise(long now, boolean aggressiveSnap, double distance) {
      float scale = aggressiveSnap ? 0.68F : 0.035F;
      return (float)(Math.sin((double)now / (double)17.0F) * (double)scale + Math.cos((double)now / (double)29.0F) * (double)scale * (double)0.42F);
   }

   protected float extraPitchNoise(long now, boolean aggressiveSnap, double distance) {
      float scale = aggressiveSnap ? 0.16F : 0.012F;
      return (float)(Math.cos((double)now / (double)23.0F) * (double)scale + Math.sin((double)now / (double)41.0F) * (double)scale * (double)0.35F);
   }
}
