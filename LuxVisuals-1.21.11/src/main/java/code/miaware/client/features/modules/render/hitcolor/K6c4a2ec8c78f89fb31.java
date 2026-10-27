package code.miaware.client.features.modules.render.hitcolor;

public final class K6c4a2ec8c78f89fb31 {
   private K6c4a2ec8c78f89fb31() {
   }

   public static float strength(float remaining, float duration) {
      if (!(duration <= 0.0F) && !(remaining <= 0.0F)) {
         float progress = Math.clamp(1.0F - remaining / duration, 0.0F, 1.0F);
         float attack = smooth(Math.min(1.0F, progress / 0.12F));
         float fade = 1.0F - smooth(Math.max(0.0F, (progress - 0.12F) / 0.88F));
         return attack * fade;
      } else {
         return 0.0F;
      }
   }

   private static float smooth(float t) {
      return t * t * (3.0F - 2.0F * t);
   }

   public static int overlayUv(float strength) {
      int sample = Math.round(Math.clamp(strength, 0.0F, 1.0F) * 127.0F);
      return sample & 15 | sample >> 4 << 16;
   }

   public static int pixel(int selected, float intensity, int sample) {
      float opacity = (float)(selected >>> 24) / 255.0F * Math.clamp(intensity, 0.0F, 1.0F) * (float)Math.clamp((long)sample, 0, 127) / 127.0F;
      return Math.round(255.0F * (1.0F - opacity)) << 24 | selected & 16777215;
   }
}
