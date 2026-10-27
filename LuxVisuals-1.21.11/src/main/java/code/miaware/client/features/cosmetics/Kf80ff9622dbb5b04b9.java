package code.miaware.client.features.cosmetics;

final class Kf80ff9622dbb5b04b9 {
   final float[] times;
   final float[][] pre;
   final float[][] post;
   final int[] mode;

   Kf80ff9622dbb5b04b9(float[] times, float[][] pre, float[][] post, int[] mode) {
      this.times = times;
      this.pre = pre;
      this.post = post;
      this.mode = mode;
   }

   void sample(float t, float[] out) {
      int n = this.times.length;
      if (n == 1) {
         copy(this.post[0], out);
      } else if (t <= this.times[0]) {
         copy(t < this.times[0] ? this.pre[0] : this.post[0], out);
      } else if (t >= this.times[n - 1]) {
         copy(this.post[n - 1], out);
      } else {
         int i;
         for(i = 0; i < n - 2 && t >= this.times[i + 1]; ++i) {
         }

         float t0 = this.times[i];
         float t1 = this.times[i + 1];
         float k = t1 > t0 ? (t - t0) / (t1 - t0) : 0.0F;
         float[] a = this.post[i];
         float[] b = this.pre[i + 1];
         if (this.mode[i] != 1 && this.mode[i + 1] != 1) {
            if (this.mode[i + 1] == 2) {
               k = k < 0.5F ? 2.0F * k * k : 1.0F - (float)Math.pow((double)(-2.0F * k + 2.0F), (double)2.0F) / 2.0F;
            }

            for(int c = 0; c < 3; ++c) {
               out[c] = a[c] + (b[c] - a[c]) * k;
            }

         } else {
            float[] p0 = i > 0 ? this.post[i - 1] : a;
            float[] p3 = i + 2 < n ? this.pre[i + 2] : b;

            for(int c = 0; c < 3; ++c) {
               out[c] = catmull(p0[c], a[c], b[c], p3[c], k);
            }

         }
      }
   }

   private static float catmull(float p0, float p1, float p2, float p3, float t) {
      float t2 = t * t;
      float t3 = t2 * t;
      return 0.5F * (2.0F * p1 + (-p0 + p2) * t + (2.0F * p0 - 5.0F * p1 + 4.0F * p2 - p3) * t2 + (-p0 + 3.0F * p1 - 3.0F * p2 + p3) * t3);
   }

   private static void copy(float[] src, float[] out) {
      out[0] = src[0];
      out[1] = src[1];
      out[2] = src[2];
   }
}
