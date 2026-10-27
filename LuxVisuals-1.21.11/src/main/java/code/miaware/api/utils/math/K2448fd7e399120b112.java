package code.miaware.api.utils.math;

public class K2448fd7e399120b112 {
   private static final int PERMUTATION_SIZE = 256;
   private final int[] permutation;

   public K2448fd7e399120b112() {
      this(System.nanoTime());
   }

   public K2448fd7e399120b112(long seed) {
      this.permutation = new int[512];
      K4ccfce20b90a27da2f randomUtil = new K4ccfce20b90a27da2f(seed);
      int[] p = new int[256];

      for(int i = 0; i < 256; p[i] = i++) {
      }

      for(int i = 255; i > 0; --i) {
         int j = randomUtil.nextInt(i + 1);
         int temp = p[i];
         p[i] = p[j];
         p[j] = temp;
      }

      for(int i = 0; i < 512; ++i) {
         this.permutation[i] = p[i & 255];
      }

   }

   public float perlinNoise(float x) {
      int xi = (int)Math.floor((double)x) & 255;
      float xf = x - (float)Math.floor((double)x);
      float u = this.K5cf69a259d89dc6a98(xf);
      int a = this.permutation[xi];
      int b = this.permutation[xi + 1];
      return this.Kd94e01bf372dc7a34a(u, this.Kf57ad870113dc8d8c1(a, xf), this.Kf57ad870113dc8d8c1(b, xf - 1.0F)) * 2.0F;
   }

   public float perlinNoise(float x, float y) {
      int xi = (int)Math.floor((double)x) & 255;
      int yi = (int)Math.floor((double)y) & 255;
      float xf = x - (float)Math.floor((double)x);
      float yf = y - (float)Math.floor((double)y);
      float u = this.K5cf69a259d89dc6a98(xf);
      float v = this.K5cf69a259d89dc6a98(yf);
      int aa = this.permutation[this.permutation[xi] + yi];
      int ab = this.permutation[this.permutation[xi] + yi + 1];
      int ba = this.permutation[this.permutation[xi + 1] + yi];
      int bb = this.permutation[this.permutation[xi + 1] + yi + 1];
      float x1 = this.Kd94e01bf372dc7a34a(u, this.K0a6d59f168bad0e4c4(aa, xf, yf), this.K0a6d59f168bad0e4c4(ba, xf - 1.0F, yf));
      float x2 = this.Kd94e01bf372dc7a34a(u, this.K0a6d59f168bad0e4c4(ab, xf, yf - 1.0F), this.K0a6d59f168bad0e4c4(bb, xf - 1.0F, yf - 1.0F));
      return this.Kd94e01bf372dc7a34a(v, x1, x2);
   }

   public float perlinNoise(float x, float y, float z) {
      int xi = (int)Math.floor((double)x) & 255;
      int yi = (int)Math.floor((double)y) & 255;
      int zi = (int)Math.floor((double)z) & 255;
      float xf = x - (float)Math.floor((double)x);
      float yf = y - (float)Math.floor((double)y);
      float zf = z - (float)Math.floor((double)z);
      float u = this.K5cf69a259d89dc6a98(xf);
      float v = this.K5cf69a259d89dc6a98(yf);
      float w = this.K5cf69a259d89dc6a98(zf);
      int aaa = this.permutation[this.permutation[this.permutation[xi] + yi] + zi];
      int aab = this.permutation[this.permutation[this.permutation[xi] + yi] + zi + 1];
      int aba = this.permutation[this.permutation[this.permutation[xi] + yi + 1] + zi];
      int abb = this.permutation[this.permutation[this.permutation[xi] + yi + 1] + zi + 1];
      int baa = this.permutation[this.permutation[this.permutation[xi + 1] + yi] + zi];
      int bab = this.permutation[this.permutation[this.permutation[xi + 1] + yi] + zi + 1];
      int bba = this.permutation[this.permutation[this.permutation[xi + 1] + yi + 1] + zi];
      int bbb = this.permutation[this.permutation[this.permutation[xi + 1] + yi + 1] + zi + 1];
      float x1 = this.Kd94e01bf372dc7a34a(u, this.Kc593545f439e386ddb(aaa, xf, yf, zf), this.Kc593545f439e386ddb(baa, xf - 1.0F, yf, zf));
      float x2 = this.Kd94e01bf372dc7a34a(u, this.Kc593545f439e386ddb(aba, xf, yf - 1.0F, zf), this.Kc593545f439e386ddb(bba, xf - 1.0F, yf - 1.0F, zf));
      float y1 = this.Kd94e01bf372dc7a34a(v, x1, x2);
      x1 = this.Kd94e01bf372dc7a34a(u, this.Kc593545f439e386ddb(aab, xf, yf, zf - 1.0F), this.Kc593545f439e386ddb(bab, xf - 1.0F, yf, zf - 1.0F));
      x2 = this.Kd94e01bf372dc7a34a(u, this.Kc593545f439e386ddb(abb, xf, yf - 1.0F, zf - 1.0F), this.Kc593545f439e386ddb(bbb, xf - 1.0F, yf - 1.0F, zf - 1.0F));
      float y2 = this.Kd94e01bf372dc7a34a(v, x1, x2);
      return this.Kd94e01bf372dc7a34a(w, y1, y2);
   }

   private float K5cf69a259d89dc6a98(float t) {
      return t * t * t * (t * (t * 6.0F - 15.0F) + 10.0F);
   }

   private float Kd94e01bf372dc7a34a(float amount, float left, float right) {
      return left + amount * (right - left);
   }

   private float Kf57ad870113dc8d8c1(int hash, float x) {
      return (hash & 1) == 0 ? x : -x;
   }

   private float K0a6d59f168bad0e4c4(int hash, float x, float y) {
      switch (hash & 3) {
         case 0 -> {
            return x + y;
         }
         case 1 -> {
            return -x + y;
         }
         case 2 -> {
            return x - y;
         }
         case 3 -> {
            return -x - y;
         }
         default -> {
            return 0.0F;
         }
      }
   }

   private float Kc593545f439e386ddb(int hash, float x, float y, float z) {
      int h = hash & 15;
      float u = h < 8 ? x : y;
      float v = h < 4 ? y : (h != 12 && h != 14 ? z : x);
      return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
   }

   public float octaveNoise(float x, float y, int octaves, float persistence, float lacunarity) {
      float total = 0.0F;
      float frequency = 1.0F;
      float amplitude = 1.0F;
      float maxValue = 0.0F;

      for(int i = 0; i < octaves; ++i) {
         total += this.perlinNoise(x * frequency, y * frequency) * amplitude;
         maxValue += amplitude;
         amplitude *= persistence;
         frequency *= lacunarity;
      }

      return total / maxValue;
   }
}
