package code.miaware.api.utils.render.elytra;

public final class Ka8710cc9fac660be2e {
   private static final int SEGMENTS = 96;
   private static final double[] COS = new double[97];
   private static final double[] SIN = new double[97];
   private static final float[] BAND = new float[]{-1.0F, -0.38F, 0.0F, 0.38F, 1.0F};
   private static final float[] ALPHA = new float[]{0.0F, 0.28F, 1.0F, 0.28F, 0.0F};

   private Ka8710cc9fac660be2e() {
   }

   public static boolean hasRings(K8f299431979bb8cb2f state, double time) {
      for(K571a00567230f04d40 ring : state.rings()) {
         if (time >= ring.born && time - ring.born < 0.95) {
            return true;
         }
      }

      return false;
   }

   public static void rings(K8f299431979bb8cb2f state, double time, double cameraX, double cameraY, double cameraZ, float size, float opacity, int tint, K573c982277009138ed sink) {
      for(K571a00567230f04d40 ring : state.rings()) {
         double age = time - ring.born;
         double p = age / 0.95;
         if (!(p <= (double)0.0F) && !(p >= (double)1.0F)) {
            double fade = Math.min((double)1.0F, age / 0.075) * Math.pow((double)1.0F - p, 1.15);
            double radius = (double)size * (0.82 + 2.45 * ((double)1.0F - Math.pow((double)1.0F - p, (double)1.5F)));
            double width = (double)size * (0.12 + p * 0.22);
            double cx = ring.x - cameraX - ring.dx * age * (double)0.5F;
            double cy = ring.y - cameraY - ring.dy * age * (double)0.5F;
            double cz = ring.z - cameraZ - ring.dz * age * (double)0.5F;

            for(int layer = 0; layer < 2; ++layer) {
               double r = radius * (layer == 0 ? 1.055 : 0.935);
               double w = width * (layer == 0 ? (double)1.0F : 0.16);
               double baseAlpha = (double)opacity * fade * (0.45 + ring.strength * 0.55) * (layer == 0 ? 0.78 : 1.15);
               int layerTint = layer == 0 ? K8e5457df0fe8ae92e0(tint) : tint;

               for(int i = 0; i < 96; ++i) {
                  double a = (Math.PI * 2D) * (double)i / (double)96.0F;
                  double b = (Math.PI * 2D) * (double)(i + 1) / (double)96.0F;
                  double wa = (double)1.0F + 0.008 * Math.sin(a * (double)3.0F + ring.phase);
                  double wb = (double)1.0F + 0.008 * Math.sin(b * (double)3.0F + ring.phase);
                  double aa = 0.78 + 0.22 * Math.pow(Math.sin(a * (double)2.0F + ring.phase), (double)2.0F);
                  double ab = 0.78 + 0.22 * Math.pow(Math.sin(b * (double)2.0F + ring.phase), (double)2.0F);

                  for(int band = 0; band < BAND.length - 1; ++band) {
                     Kc6c2841f8760a71d87(sink, ring, cx, cy, cz, i, r * wa + (double)BAND[band] * w, layerTint, baseAlpha * aa * (double)ALPHA[band], layer);
                     Kc6c2841f8760a71d87(sink, ring, cx, cy, cz, i + 1, r * wb + (double)BAND[band] * w, layerTint, baseAlpha * ab * (double)ALPHA[band], layer);
                     Kc6c2841f8760a71d87(sink, ring, cx, cy, cz, i + 1, r * wb + (double)BAND[band + 1] * w, layerTint, baseAlpha * ab * (double)ALPHA[band + 1], layer);
                     Kc6c2841f8760a71d87(sink, ring, cx, cy, cz, i, r * wa + (double)BAND[band + 1] * w, layerTint, baseAlpha * aa * (double)ALPHA[band + 1], layer);
                  }
               }
            }
         }
      }

   }

   private static void Kc6c2841f8760a71d87(K573c982277009138ed sink, K571a00567230f04d40 ring, double x, double y, double z, int angle, double radius, int tint, double alpha, int layer) {
      double u = COS[angle] * radius;
      double v = SIN[angle] * radius;
      sink.vertex((float)(x + ring.ux * u + ring.vx * v), (float)(y + ring.uy * u + ring.vy * v), (float)(z + ring.uz * u + ring.vz * v), color(tint, layer == 1 ? 0.96 : 0.06, alpha));
   }

   public static void wind(double time, double intensity, int width, int height, float opacity, int tint, K573c982277009138ed sink) {
      if (!(intensity < 0.015) && width > 0 && height > 0) {
         for(int i = 0; i < 40; ++i) {
            double angle = (double)i * 2.3999632297;
            double dx = Math.cos(angle);
            double dy = Math.sin(angle);
            double cycle = time * (0.7 + intensity * 0.95) + (double)i * 0.61803398875;
            double p = cycle - Math.floor(cycle);
            double radius = 0.16 + p * 0.94;
            double length = (0.09 + p * p * (double)0.25F) * (0.65 + intensity);
            double envelope = Math.sin(Math.PI * p);
            double alpha = (double)opacity * Math.sqrt(intensity) * (0.4 + intensity * 0.6) * envelope * envelope * 0.95;
            double halfWidth = 0.65 + intensity * 0.95;

            for(int section = 0; section < 3; ++section) {
               double f0 = (double)section / (double)3.0F;
               double f1 = (double)(section + 1) / (double)3.0F;
               double r0 = radius + length * f0;
               double r1 = radius + length * f1;
               double a0 = Math.sin(Math.PI * f0) * alpha;
               double a1 = Math.sin(Math.PI * f1) * alpha;

               for(int side = -1; side <= 0; ++side) {
                  K0e25c5e0b23f91e4ca(sink, width, height, dx, dy, r0, (double)side * halfWidth, tint, side == 0 ? a0 : (double)0.0F);
                  K0e25c5e0b23f91e4ca(sink, width, height, dx, dy, r1, (double)side * halfWidth, tint, side == 0 ? a1 : (double)0.0F);
                  K0e25c5e0b23f91e4ca(sink, width, height, dx, dy, r1, (double)(side + 1) * halfWidth, tint, side == -1 ? a1 : (double)0.0F);
                  K0e25c5e0b23f91e4ca(sink, width, height, dx, dy, r0, (double)(side + 1) * halfWidth, tint, side == -1 ? a0 : (double)0.0F);
               }
            }
         }

      }
   }

   private static void K0e25c5e0b23f91e4ca(K573c982277009138ed sink, int width, int height, double dx, double dy, double radius, double offset, int tint, double alpha) {
      sink.vertex((float)((double)width * (double)0.5F + dx * (double)width * 0.57 * radius - dy * offset), (float)((double)height * (double)0.5F + dy * (double)height * 0.64 * radius + dx * offset), 0.0F, color(tint, 0.8, alpha));
   }

   private static int K8e5457df0fe8ae92e0(int tint) {
      int r = tint >>> 16 & 255;
      int g = tint >>> 8 & 255;
      int b = tint & 255;
      return tint & -16777216 | (int)((double)r * 0.65) << 16 | g << 8 | b;
   }

   private static int color(int tint, double white, double alpha) {
      int r = tint >>> 16 & 255;
      int g = tint >>> 8 & 255;
      int b = tint & 255;
      r += (int)((double)(255 - r) * white);
      g += (int)((double)(255 - g) * white);
      b += (int)((double)(255 - b) * white);
      int a = (int)(K8f299431979bb8cb2f.clamp(alpha) * (double)(tint >>> 24));
      return a << 24 | r << 16 | g << 8 | b;
   }

   static {
      for(int i = 0; i <= 96; ++i) {
         double angle = (double)i * Math.PI * (double)2.0F / (double)96.0F;
         COS[i] = Math.cos(angle);
         SIN[i] = Math.sin(angle);
      }

   }
}
