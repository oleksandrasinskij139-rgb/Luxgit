package code.miaware.client.features.modules.render.killeffect;

import net.minecraft.class_243;

public final class K44b0ee4754e3a85f75 {
   private static final double TAU = (Math.PI * 2D);
   private static final double FLOOR = 0.035;
   private final K54fcfad7d30024ba41 mesh;
   private final int tint;
   private final double height;
   private final double width;

   private K44b0ee4754e3a85f75(K54fcfad7d30024ba41 mesh, int tint, double height, double width) {
      this.mesh = mesh;
      this.tint = tint & 16777215;
      this.height = Math.max((double)0.75F, Math.min((double)3.0F, height));
      this.width = Math.max(0.4, Math.min(1.4, width));
   }

   public static void emit(K1037f35c571a07da2f style, double progress, int tint, double height, double width, K54fcfad7d30024ba41 mesh) {
      if (Double.isFinite(progress) && !(progress <= (double)0.0F) && !(progress >= (double)1.0F)) {
         K44b0ee4754e3a85f75 scene = new K44b0ee4754e3a85f75(mesh, tint, height, width);
         switch (style.ordinal()) {
            case 0 -> scene.K318f2e05aa4ae5f21b(progress);
            case 1 -> scene.Kafe56ca045943d8f8c(progress);
            case 2 -> scene.K7cb8173a561f42e160(progress);
            case 3 -> scene.K6c4d94b50956fa7c77(progress);
         }

      }
   }

   private static double K1798643771ec46ce47(double x) {
      return Math.max((double)0.0F, Math.min((double)1.0F, x));
   }

   private static double K7122f91dab52604729(double x) {
      x = K1798643771ec46ce47(x);
      return x * x * ((double)3.0F - (double)2.0F * x);
   }

   private static double K488be8e2efc958924b(double t, double start, double end) {
      return K7122f91dab52604729((t - start) / (end - start));
   }

   private static int Kc0c6037ce2c8f48f66(int rgb, double alpha) {
      return (int)(K1798643771ec46ce47(alpha) * (double)255.0F) << 24 | rgb & 16777215;
   }

   private static int Kcdddce57b575430821(int a, int b, double t) {
      t = K1798643771ec46ce47(t);
      int r = (int)((double)(a >> 16 & 255) * ((double)1.0F - t) + (double)(b >> 16 & 255) * t);
      int g = (int)((double)(a >> 8 & 255) * ((double)1.0F - t) + (double)(b >> 8 & 255) * t);
      int blue = (int)((double)(a & 255) * ((double)1.0F - t) + (double)(b & 255) * t);
      return r << 16 | g << 8 | blue;
   }

   private static class_243 K23b88806e632361887(double x, double y, double z) {
      return new class_243(x, y, z);
   }

   private void K309f90b65ef703123f(class_243 a, class_243 b, class_243 c, int color) {
      this.Kd92b212b6e2830ba93(a, b, c, color, color, color);
   }

   private void Kd92b212b6e2830ba93(class_243 a, class_243 b, class_243 c, int ca, int cb, int cc) {
      if ((ca | cb | cc) >>> 24 != 0) {
         this.mesh.triangle(a, b, c, ca, cb, cc);
      }

   }

   private void Kc721067f53bcafd4ec(class_243 a, class_243 b, class_243 c, class_243 d, int color) {
      this.K309f90b65ef703123f(a, b, c, color);
      this.K309f90b65ef703123f(a, c, d, color);
   }

   private void Kccae8e2a3b71dfc960(double radius, int rgb, double alpha) {
      if (!(radius < 0.01) && !(alpha < 0.004)) {
         class_243 center = K23b88806e632361887((double)0.0F, 0.035, (double)0.0F);

         for(int i = 0; i < 48; ++i) {
            double a = (double)i * (Math.PI * 2D) / (double)48.0F;
            double b = (double)(i + 1) * (Math.PI * 2D) / (double)48.0F;
            this.K309f90b65ef703123f(center, K23b88806e632361887(Math.cos(a) * radius, 0.035, Math.sin(a) * radius), K23b88806e632361887(Math.cos(b) * radius, 0.035, Math.sin(b) * radius), Kc0c6037ce2c8f48f66(rgb, alpha));
         }

      }
   }

   private void K86f7eb1cb8a1e97ccb(double radius, double y, double thickness, int rgb, double alpha) {
      if (!(radius < 0.01) && !(alpha < 0.004)) {
         for(int i = 0; i < 48; ++i) {
            double a = (double)i * (Math.PI * 2D) / (double)48.0F;
            double b = (double)(i + 1) * (Math.PI * 2D) / (double)48.0F;
            double inner = Math.max((double)0.0F, radius - thickness);
            class_243 a0 = K23b88806e632361887(Math.cos(a) * inner, y, Math.sin(a) * inner);
            class_243 b0 = K23b88806e632361887(Math.cos(b) * inner, y, Math.sin(b) * inner);
            class_243 a1 = K23b88806e632361887(Math.cos(a) * (radius + thickness), y, Math.sin(a) * (radius + thickness));
            class_243 b1 = K23b88806e632361887(Math.cos(b) * (radius + thickness), y, Math.sin(b) * (radius + thickness));
            this.Kd92b212b6e2830ba93(a0, b0, b1, Kc0c6037ce2c8f48f66(rgb, alpha), Kc0c6037ce2c8f48f66(rgb, alpha), Kc0c6037ce2c8f48f66(rgb, (double)0.0F));
            this.Kd92b212b6e2830ba93(a0, b1, a1, Kc0c6037ce2c8f48f66(rgb, alpha), Kc0c6037ce2c8f48f66(rgb, (double)0.0F), Kc0c6037ce2c8f48f66(rgb, (double)0.0F));
         }

      }
   }

   private void Kc2d8f48fa97373b6f0(double t, double size) {
      double r = (1.12 + this.width * 0.22) * size;
      this.Kccae8e2a3b71dfc960(r, 328458, 0.96 * size);
      this.K86f7eb1cb8a1e97ccb(r, 0.03900000000000001, 0.15, this.tint, 0.22 * size);
      this.K86f7eb1cb8a1e97ccb(r, 0.041, 0.025, Kcdddce57b575430821(this.tint, 16777215, 0.32), 0.9 * size);

      for(int strand = 0; strand < 3; ++strand) {
         class_243[] path = new class_243[25];

         for(int i = 0; i < path.length; ++i) {
            double q = (double)i / (double)24.0F;
            double angle = q * (Math.PI * 2D) * 1.3 + t * (double)12.0F + (double)strand * (Math.PI * 2D) / (double)3.0F;
            path[i] = K23b88806e632361887(Math.cos(angle) * r * q, 0.045000000000000005, Math.sin(angle) * r * q);
         }

         this.Ke771a88b076166d500(path, 0.009, Kcdddce57b575430821(this.tint, 590865, 0.55), 0.72 * size);
      }

   }

   private void box(double x, double y, double z, double sx, double sy, double sz, int rgb, double alpha) {
      double low = Math.max(0.043000000000000003, y);
      double high = y + sy;
      if (!(high <= low) && !(alpha < 0.004)) {
         class_243[] p = new class_243[]{K23b88806e632361887(x - sx / (double)2.0F, low, z - sz / (double)2.0F), K23b88806e632361887(x + sx / (double)2.0F, low, z - sz / (double)2.0F), K23b88806e632361887(x + sx / (double)2.0F, low, z + sz / (double)2.0F), K23b88806e632361887(x - sx / (double)2.0F, low, z + sz / (double)2.0F), K23b88806e632361887(x - sx / (double)2.0F, high, z - sz / (double)2.0F), K23b88806e632361887(x + sx / (double)2.0F, high, z - sz / (double)2.0F), K23b88806e632361887(x + sx / (double)2.0F, high, z + sz / (double)2.0F), K23b88806e632361887(x - sx / (double)2.0F, high, z + sz / (double)2.0F)};
         int side = Kc0c6037ce2c8f48f66(rgb, alpha);
         int shadow = Kc0c6037ce2c8f48f66(Kcdddce57b575430821(rgb, 0, (double)0.25F), alpha);
         this.Kc721067f53bcafd4ec(p[0], p[1], p[5], p[4], shadow);
         this.Kc721067f53bcafd4ec(p[1], p[2], p[6], p[5], side);
         this.Kc721067f53bcafd4ec(p[2], p[3], p[7], p[6], side);
         this.Kc721067f53bcafd4ec(p[3], p[0], p[4], p[7], shadow);
         this.Kc721067f53bcafd4ec(p[4], p[5], p[6], p[7], Kc0c6037ce2c8f48f66(Kcdddce57b575430821(rgb, 16777215, 0.15), alpha));
         this.Kc721067f53bcafd4ec(p[3], p[2], p[1], p[0], shadow);
      }
   }

   private double[][] K35d2beb281645a143f() {
      double w = this.width;
      double h = this.height;
      return new double[][]{{-0.23 * w, (double)0.0F, (double)0.0F, 0.34 * w, 0.36 * h, 0.48 * w}, {0.23 * w, (double)0.0F, (double)0.0F, 0.34 * w, 0.36 * h, 0.48 * w}, {(double)0.0F, 0.36 * h, (double)0.0F, 0.85 * w, 0.4 * h, (double)0.5F * w}, {-0.62 * w, 0.37 * h, (double)0.0F, 0.29 * w, 0.38 * h, 0.42 * w}, {0.62 * w, 0.37 * h, (double)0.0F, 0.29 * w, 0.38 * h, 0.42 * w}, {(double)0.0F, 0.76 * h, (double)0.0F, 0.72 * w, 0.24 * h, 0.7 * w}};
   }

   private void Kbb4a938f78805fe1ec(double offset, double alpha, int rgb, double scale) {
      for(double[] p : this.K35d2beb281645a143f()) {
         this.box(p[0] * scale, p[1] * scale + offset, p[2] * scale, p[3] * scale, p[4] * scale, p[5] * scale, rgb, alpha);
      }

   }

   private void Ke771a88b076166d500(class_243[] path, double radius, int rgb, double alpha) {
      if (!(alpha < 0.004) && !(radius < 0.001) && path.length >= 2) {
         class_243[][] rings = new class_243[path.length][6];

         for(int i = 0; i < path.length; ++i) {
            class_243 direction = path[Math.min(path.length - 1, i + 1)].method_1020(path[Math.max(0, i - 1)]);
            if (direction.method_1027() < 1.0E-10) {
               direction = K23b88806e632361887((double)0.0F, (double)1.0F, (double)0.0F);
            }

            direction = direction.method_1029();
            class_243 ref = Math.abs(direction.field_1351) > 0.9 ? K23b88806e632361887((double)1.0F, (double)0.0F, (double)0.0F) : K23b88806e632361887((double)0.0F, (double)1.0F, (double)0.0F);
            class_243 right = direction.method_1036(ref).method_1029();
            class_243 up = direction.method_1036(right).method_1029();
            double r = radius * ((double)1.0F - 0.74 * (double)i / ((double)path.length - (double)1.0F));

            for(int j = 0; j < 6; ++j) {
               double a = (double)j * (Math.PI * 2D) / (double)6.0F;
               rings[i][j] = path[i].method_1019(right.method_1021(Math.cos(a) * r)).method_1019(up.method_1021(Math.sin(a) * r));
            }
         }

         for(int i = 0; i < path.length - 1; ++i) {
            for(int j = 0; j < 6; ++j) {
               int shade = Kcdddce57b575430821(rgb, 328458, j == 1 ? 0.08 : (j == 2 ? 0.28 : 0.68));
               this.Kc721067f53bcafd4ec(rings[i][j], rings[i][(j + 1) % 6], rings[i + 1][(j + 1) % 6], rings[i + 1][j], Kc0c6037ce2c8f48f66(shade, alpha));
            }
         }

      }
   }

   private class_243[] K788652d006369d8d21(class_243 a, class_243 b, class_243 c, class_243 d, double growth, double wobble) {
      int count = 25;
      class_243[] points = new class_243[count];

      for(int i = 0; i < count; ++i) {
         double q = (double)i / (double)24.0F * K1798643771ec46ce47(growth);
         double s = (double)1.0F - q;
         class_243 point = a.method_1021(s * s * s).method_1019(b.method_1021((double)3.0F * s * s * q)).method_1019(c.method_1021((double)3.0F * s * q * q)).method_1019(d.method_1021(q * q * q));
         double wave = Math.sin(q * Math.PI) * Math.sin(q * (Math.PI * 2D) * (double)1.5F + wobble) * 0.1;
         points[i] = point.method_1031(wave, (double)0.0F, wave * 0.6);
      }

      return points;
   }

   private void orb(class_243 center, double radius, int rgb, double alpha) {
      if (!(radius < 0.001) && !(alpha < 0.004)) {
         for(int lat = 0; lat < 6; ++lat) {
            for(int lon = 0; lon < 12; ++lon) {
               double a = (double)lat * Math.PI / (double)6.0F;
               double b = (double)(lat + 1) * Math.PI / (double)6.0F;
               double c = (double)lon * (Math.PI * 2D) / (double)12.0F;
               double d = (double)(lon + 1) * (Math.PI * 2D) / (double)12.0F;
               class_243 p0 = Kf8a46c64d4c53b4a72(center, radius, a, c);
               class_243 p1 = Kf8a46c64d4c53b4a72(center, radius, a, d);
               class_243 p2 = Kf8a46c64d4c53b4a72(center, radius, b, d);
               class_243 p3 = Kf8a46c64d4c53b4a72(center, radius, b, c);
               this.Kc721067f53bcafd4ec(p0, p1, p2, p3, Kc0c6037ce2c8f48f66(rgb, alpha));
            }
         }

      }
   }

   private static class_243 Kf8a46c64d4c53b4a72(class_243 p, double r, double a, double b) {
      return p.method_1031(Math.sin(a) * Math.cos(b) * r, Math.cos(a) * r, Math.sin(a) * Math.sin(b) * r);
   }

   private void K318f2e05aa4ae5f21b(double t) {
      double open = K488be8e2efc958924b(t, (double)0.0F, 0.17) * ((double)1.0F - K488be8e2efc958924b(t, 0.87, (double)1.0F));
      this.Kc2d8f48fa97373b6f0(t, open);
      double y = 0.15 + K488be8e2efc958924b(t, 0.13, 0.37) * 0.95 - K488be8e2efc958924b(t, 0.59, 0.79) * (this.height + 1.1);
      double fade = K488be8e2efc958924b(t, 0.09, 0.19) * ((double)1.0F - K488be8e2efc958924b(t, (double)0.75F, 0.84));
      this.Kbb4a938f78805fe1ec(y, fade * 0.52, Kcdddce57b575430821(this.tint, 13235967, 0.66), 0.8);
      double grip = K488be8e2efc958924b(t, 0.27, 0.49) * ((double)1.0F - K488be8e2efc958924b(t, 0.79, 0.87));

      for(int i = 0; i < 3; ++i) {
         double a = (double)i * (Math.PI * 2D) / (double)3.0F + t * 0.6;
         double targetY = Math.max(0.1, y + this.height * 0.45);
         class_243 start = K23b88806e632361887(Math.cos(a) * 1.05 * open, 0.09, Math.sin(a) * 1.05 * open);
         class_243 target = K23b88806e632361887(Math.cos(a + t * (double)9.0F) * this.width * 0.28, targetY, Math.sin(a + t * (double)9.0F) * this.width * 0.28);
         this.Ke771a88b076166d500(this.K788652d006369d8d21(start, K23b88806e632361887(Math.cos(a) * 1.38, this.height * 0.78, Math.sin(a) * 1.38), target.method_1031(Math.sin(a) * 0.24, 0.45, Math.cos(a) * 0.24), target, grip, t * (double)17.0F + (double)i), 0.095, this.tint, grip * 0.96);
         if (t > 0.46) {
            class_243[] coil = new class_243[25];

            for(int j = 0; j < 25; ++j) {
               double q = (double)j / (double)24.0F;
               double angle = a + q * (Math.PI * 2D) * 1.4;
               coil[j] = K23b88806e632361887(Math.cos(angle) * this.width * 0.43, Math.max(0.085, targetY - 0.2 + q * 0.33), Math.sin(angle) * this.width * 0.43);
            }

            this.Ke771a88b076166d500(coil, 0.035, this.tint, grip * 0.88);
         }
      }

      for(int i = 0; i < 28; ++i) {
         double q = (t * 1.3 + (double)i / (double)28.0F) % (double)1.0F;
         double a = (double)i * 2.399 + t * (double)9.0F;
         double r = ((double)1.0F - q) * 1.7 * open;
         this.orb(K23b88806e632361887(Math.cos(a) * r, 0.1 + ((double)1.0F - q) * this.height * 0.6, Math.sin(a) * r), 0.018, Kcdddce57b575430821(this.tint, 16777215, (double)0.25F), grip * ((double)1.0F - q));
      }

      this.K86f7eb1cb8a1e97ccb(K488be8e2efc958924b(t, 0.84, 0.99) * (double)2.0F, 0.047, 0.09, this.tint, ((double)1.0F - K488be8e2efc958924b(t, 0.84, 0.99)) * K488be8e2efc958924b(t, 0.83, 0.87) * (double)0.5F);
   }

   private void Kafe56ca045943d8f8c(double t) {
      double open = K488be8e2efc958924b(t, (double)0.0F, 0.18) * ((double)1.0F - K488be8e2efc958924b(t, 0.81, (double)1.0F));
      if (open > 0.01) {
         for(int i = 0; i < 32; ++i) {
            double a = (double)i * (Math.PI * 2D) / (double)32.0F;
            double b = (double)(i + 1) * (Math.PI * 2D) / (double)32.0F;
            double r = (1.12 + 0.15 * Math.sin((double)i * 2.7)) * open;
            double s = (1.12 + 0.15 * Math.sin((double)(i + 1) * 2.7)) * open;
            class_243 p = K23b88806e632361887(Math.cos(a) * r, 0.035, Math.sin(a) * r);
            class_243 q = K23b88806e632361887(Math.cos(b) * s, 0.035, Math.sin(b) * s);
            this.K309f90b65ef703123f(K23b88806e632361887((double)0.0F, 0.035, (double)0.0F), p, q, Kc0c6037ce2c8f48f66(262664, 0.95 * open));
            this.Kc721067f53bcafd4ec(p, q, q.method_1021(1.06).method_1031((double)0.0F, 0.001, (double)0.0F), p.method_1021(1.06).method_1031((double)0.0F, 0.001, (double)0.0F), Kc0c6037ce2c8f48f66(this.tint, (double)0.75F * open));
         }
      }

      double[][] body = this.K35d2beb281645a143f();

      for(int i = 0; i < body.length; ++i) {
         double[] p = body[i];
         double pull = K488be8e2efc958924b(t, 0.3 + (double)i * 0.023, 0.66 + (double)i * 0.023);
         this.box(p[0] * ((double)1.0F - pull), p[1] + 0.15 - pull * (this.height + 0.35), p[2], p[3] * ((double)1.0F - (double)0.25F * pull), p[4], p[5], Kcdddce57b575430821(this.tint, 13561855, 0.6), K488be8e2efc958924b(t, 0.06, 0.17) * ((double)1.0F - K488be8e2efc958924b(t, 0.72, 0.84)) * 0.65);
      }

      double grab = K488be8e2efc958924b(t, 0.18, 0.39) * ((double)1.0F - K488be8e2efc958924b(t, 0.77, 0.87));
      double pull = K488be8e2efc958924b(t, 0.42, 0.77);

      for(int i = 0; i < 5; ++i) {
         double a = (double)i * (Math.PI * 2D) / (double)5.0F + 0.3;
         double r = 1.18 * open;
         double endR = (double)0.5F * ((double)1.0F - pull);
         double yy = Math.max(0.1, this.height * 0.6 * ((double)1.0F - pull));
         class_243[] claw = new class_243[]{K23b88806e632361887(Math.cos(a) * r, 0.09, Math.sin(a) * r), K23b88806e632361887(Math.cos(a) * (r + 0.22), this.height * (double)0.5F * grab, Math.sin(a) * (r + 0.22)), K23b88806e632361887(Math.cos(a) * 0.65, this.height * 0.85 * grab, Math.sin(a) * 0.65), K23b88806e632361887(Math.cos(a) * endR, yy, Math.sin(a) * endR)};
         this.Ke771a88b076166d500(claw, 0.13, Kcdddce57b575430821(this.tint, 15390446, (double)0.25F), grab);
      }

      this.K86f7eb1cb8a1e97ccb(1.15 * open, 0.049, 0.16, this.tint, 0.2 * open);
   }

   private void K7cb8173a561f42e160(double t) {
      double open = K488be8e2efc958924b(t, 0.03, 0.22) * ((double)1.0F - K488be8e2efc958924b(t, 0.88, (double)1.0F));
      class_243[] slit = new class_243[]{K23b88806e632361887(-0.13 * open, 0.035, -1.6 * open), K23b88806e632361887(0.05 * open, 0.035, -0.7 * open), K23b88806e632361887(0.13 * open, 0.035, (double)0.5F * open), K23b88806e632361887((double)0.0F, 0.035, 1.6 * open), K23b88806e632361887(-0.15 * open, 0.035, 0.6 * open), K23b88806e632361887(-0.04 * open, 0.035, (double)-0.5F * open)};

      for(int i = 0; i < 6; ++i) {
         this.K309f90b65ef703123f(K23b88806e632361887((double)0.0F, 0.035, (double)0.0F), slit[i], slit[(i + 1) % 6], Kc0c6037ce2c8f48f66(196872, 0.97 * open));
      }

      class_243[] edge = new class_243[7];
      System.arraycopy(slit, 0, edge, 0, 6);
      edge[6] = slit[0];
      this.Ke771a88b076166d500(edge, 0.016, this.tint, open);

      for(int i = 0; i < 9; ++i) {
         double angle = (double)i * (Math.PI * 2D) / (double)9.0F;
         double pull = K488be8e2efc958924b(t, 0.29 + (double)i * 0.025, 0.51 + (double)i * 0.025);
         double alpha = K488be8e2efc958924b(t, 0.12, 0.23) * ((double)1.0F - K488be8e2efc958924b(t, 0.52 + (double)i * 0.025, 0.6 + (double)i * 0.025));
         class_243 point = K23b88806e632361887(Math.cos(angle) * ((double)1.0F - pull), 0.09 + this.height * (0.32 + (double)(i % 3) * 0.2) * ((double)1.0F - pull), Math.sin(angle) * ((double)1.0F - pull));
         this.orb(point, 0.08 * ((double)1.0F - 0.4 * pull), Kcdddce57b575430821(this.tint, 13168895, 0.68), alpha * 0.86);
         class_243 start = K23b88806e632361887(0.03, 0.085, -1.05 + (double)i * (double)0.25F);
         this.Ke771a88b076166d500(this.K788652d006369d8d21(start, start.method_1031(Math.sin(angle) * 0.3, 0.4, (double)0.0F), point.method_1031(Math.sin(angle) * 0.3, 0.4, (double)0.0F), point, K488be8e2efc958924b(t, (double)0.25F, 0.37), t * (double)15.0F + (double)i), 0.027, this.tint, alpha * K488be8e2efc958924b(t, (double)0.25F, 0.36));
      }

      double y = this.height * 0.85 + K488be8e2efc958924b(t, 0.14, 0.3) * 0.4 - K488be8e2efc958924b(t, (double)0.75F, 0.86) * (this.height + (double)1.0F);
      double alpha = K488be8e2efc958924b(t, 0.13, (double)0.25F) * ((double)1.0F - K488be8e2efc958924b(t, 0.82, 0.88));
      class_243 core = K23b88806e632361887((double)0.0F, Math.max(0.11, y), (double)0.0F);
      this.orb(core, 0.17, Kcdddce57b575430821(this.tint, 16777215, 0.82), alpha);
      this.orb(core, 0.26, this.tint, alpha * 0.14);
      double grip = K488be8e2efc958924b(t, 0.56, 0.71) * ((double)1.0F - K488be8e2efc958924b(t, 0.84, 0.92));

      for(int i = 0; i < 5; ++i) {
         double a = (double)i * (Math.PI * 2D) / (double)5.0F;
         class_243 start = K23b88806e632361887(0.025, 0.085, (double)-1.0F + (double)i * (double)0.5F);
         class_243 target = core.method_1031(Math.cos(a) * 0.15, (double)0.0F, Math.sin(a) * 0.15);
         this.Ke771a88b076166d500(this.K788652d006369d8d21(start, K23b88806e632361887(Math.cos(a) * 0.7, 0.65, start.field_1350), target.method_1031(Math.cos(a) * (double)0.25F, 0.38, Math.sin(a) * (double)0.25F), target, grip, t * (double)13.0F + (double)i), 0.03, this.tint, grip);
      }

      double pulse = K488be8e2efc958924b(t, 0.77, 0.92);
      this.K86f7eb1cb8a1e97ccb(pulse * 1.8, 0.053, 0.14, Kcdddce57b575430821(this.tint, 16777215, 0.55), K488be8e2efc958924b(t, 0.76, 0.8) * ((double)1.0F - pulse) * (double)0.75F);
   }

   private void K6c4d94b50956fa7c77(double t) {
      int gold = 16758063;
      int hot = 16773823;
      int ember = 16737560;
      double heat = K488be8e2efc958924b(t, 0.05, 0.24) * ((double)1.0F - K488be8e2efc958924b(t, 0.83, (double)1.0F));
      class_243 sun = K23b88806e632361887((double)0.0F, this.height + (double)2.0F, (double)0.0F);
      this.orb(sun, 0.35 + Math.sin(t * (double)25.0F) * 0.025, gold, heat * (double)0.75F);
      this.orb(sun, (double)0.5F, gold, heat * 0.11);

      for(int i = 0; i < 16; ++i) {
         double a = (double)i * (Math.PI * 2D) / (double)16.0F + t * 1.4;
         class_243 start = sun.method_1031(Math.cos(a) * 0.4, Math.sin(a) * 0.4, (double)0.0F);
         class_243 end = sun.method_1031(Math.cos(a) * (0.64 + (double)(i % 2) * 0.13), Math.sin(a) * (0.64 + (double)(i % 2) * 0.13), (double)0.0F);
         this.Ke771a88b076166d500(new class_243[]{start, end}, 0.012, gold, heat * 0.82);
      }

      for(int i = 0; i < 24; ++i) {
         double a = (double)i * (Math.PI * 2D) / (double)24.0F;
         double b = (double)(i + 1) * (Math.PI * 2D) / (double)24.0F;
         class_243 p = K23b88806e632361887(Math.cos(a) * 0.9, 0.07, Math.sin(a) * 0.9);
         class_243 q = K23b88806e632361887(Math.cos(b) * 0.9, 0.07, Math.sin(b) * 0.9);
         this.Kd92b212b6e2830ba93(sun, p, q, Kc0c6037ce2c8f48f66(hot, heat * 0.075), Kc0c6037ce2c8f48f66(gold, (double)0.0F), Kc0c6037ce2c8f48f66(gold, (double)0.0F));
      }

      this.K86f7eb1cb8a1e97ccb(0.72 + K488be8e2efc958924b(t, 0.05, 0.42) * 0.6, 0.048, 0.2, gold, heat * 0.28);
      double burn = K488be8e2efc958924b(t, 0.32, 0.79);
      double cutoff = this.height * ((double)1.0F - burn);

      for(double[] p : this.K35d2beb281645a143f()) {
         double sy = Math.min(p[4], cutoff - p[1]);
         if (sy > (double)0.0F) {
            int burnt = Kcdddce57b575430821(Kcdddce57b575430821(14412014, gold, K488be8e2efc958924b(t, 0.04, (double)0.25F)), 2363658, K488be8e2efc958924b(t, 0.3, 0.61));
            this.box(p[0], p[1], p[2], p[3], sy, p[5], burnt, ((double)1.0F - K488be8e2efc958924b(t, 0.76, 0.84)) * 0.88);
            if (burn > 0.015 && p[1] + p[4] >= cutoff) {
               this.box(p[0], cutoff - 0.025, p[2], p[3] * 1.02, 0.028, p[5] * 1.02, hot, 0.9 * ((double)1.0F - burn));
            }
         }
      }

      for(int i = 0; i < 54; ++i) {
         double start = 0.22 + (double)(i % 13) * 0.031;
         double q = K1798643771ec46ce47((t - start) / 0.4);
         if (!(q <= (double)0.0F) && !(q >= (double)1.0F)) {
            double a = (double)i * 2.399;
            double rad = this.width * 0.4 + q * 0.85;
            class_243 pos = K23b88806e632361887(Math.cos(a) * rad, 0.12 + this.height * ((double)1.0F - (double)(i % 13) / (double)13.0F) + q * 1.55, Math.sin(a) * rad + q * 0.4);
            int c = Kcdddce57b575430821(ember, 4537394, K488be8e2efc958924b(q, 0.3, (double)0.75F));
            this.box(pos.field_1352, pos.field_1351, pos.field_1350, 0.025 + (double)(i % 3) * 0.012, 0.025, 0.035, c, ((double)1.0F - q) * heat);
            if (i % 3 == 0) {
               this.orb(pos, 0.025, hot, ((double)1.0F - q) * heat * 0.8);
            }
         }
      }

      for(int i = 0; i < 4; ++i) {
         class_243[] flame = new class_243[21];

         for(int j = 0; j < 21; ++j) {
            double q = (double)j / (double)20.0F;
            double a = (double)i * (Math.PI * 2D) / (double)4.0F + q * 2.4 + t * (double)12.0F;
            flame[j] = K23b88806e632361887(Math.cos(a) * (0.4 + q * 0.1), 0.08 + q * this.height * 1.12, Math.sin(a) * (0.4 + q * 0.1));
         }

         this.Ke771a88b076166d500(flame, 0.035, ember, heat * K488be8e2efc958924b(t, 0.14, 0.29) * ((double)1.0F - K488be8e2efc958924b(t, 0.57, 0.76)) * 0.64);
      }

      double end = K488be8e2efc958924b(t, 0.73, 0.96);
      this.K86f7eb1cb8a1e97ccb(end * (double)1.75F, 0.052, 0.13, gold, K488be8e2efc958924b(t, 0.71, 0.78) * ((double)1.0F - end) * (double)0.5F);
   }
}
