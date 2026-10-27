package code.miaware.client.ui.journey;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public final class K35d6e3ad814c5a9922 {
   public static final float WIDTH = 1600.0F;
   public static final float HEIGHT = 900.0F;

   public static Kfd874cf8dd9b0294d6 palette(String dimension) {
      Kfd874cf8dd9b0294d6 var10000;
      switch (dimension) {
         case "minecraft:the_nether":
            var10000 = new Kfd874cf8dd9b0294d6("The Nether", "02", new Color(255, 174, 100), new Color(24, 12, 24), new Color(153, 48, 36));
            break;
         case "minecraft:the_end":
            var10000 = new Kfd874cf8dd9b0294d6("The End", "03", new Color(204, 184, 255), new Color(12, 12, 32), new Color(67, 49, 96));
            break;
         case "minecraft:overworld":
            var10000 = new Kfd874cf8dd9b0294d6("Overworld", "01", new Color(241, 205, 155), new Color(18, 39, 55), new Color(169, 112, 105));
            break;
         default:
            String path = dimension.substring(dimension.indexOf(58) + 1).replace('_', ' ').replace('/', ' ');
            String name = path.isEmpty() ? "Unknown World" : Character.toUpperCase(path.charAt(0)) + path.substring(1);
            var10000 = new Kfd874cf8dd9b0294d6(name, "04", new Color(155, 218, 205), new Color(16, 31, 48), new Color(55, 100, 113));
      }

      return var10000;
   }

   public static List<Keea4a4bc04198e2324> landscape(String dimension, double seconds, boolean motion) {
      List<Keea4a4bc04198e2324> out = new ArrayList();
      Kfd874cf8dd9b0294d6 p = palette(dimension);
      K434d274a0d1e502a03(out, p.sky, p.horizon, -120.0F, -100.0F, 1840.0F, 1100.0F);
      double time = motion ? seconds : (double)0.0F;
      float far = (float)Math.sin(time * 0.055) * 14.0F;
      float near = (float)Math.sin(time * 0.055) * 34.0F;
      if (dimension.equals("minecraft:the_nether")) {
         K39c5b1383302acc289(out, time, far, near);
      } else if (dimension.equals("minecraft:the_end")) {
         end(out, time, far, near);
      } else {
         K892932b235c8118eb1(out, time, far, near);
      }

      K434d274a0d1e502a03(out, new Color(5, 9, 19, 15), new Color(5, 9, 19, 120), 0.0F, 560.0F, 1600.0F, 340.0F);
      return out;
   }

   private static void K892932b235c8118eb1(List<Keea4a4bc04198e2324> out, double t, float far, float near) {
      Ka033ffbba3d4067bac(out, new Color(255, 211, 159, 12), 1210.0F + far, 300.0F, 215.0F);
      Ka033ffbba3d4067bac(out, new Color(255, 211, 159, 17), 1210.0F + far, 300.0F, 165.0F);
      Ka033ffbba3d4067bac(out, new Color(248, 205, 157), 1210.0F + far, 300.0F, 103.0F);

      for(int i = 0; i < 8; ++i) {
         float x = (float)((i * 279 + 130) % 1800 - 100) + far;
         float y = (float)(152 + i % 3 * 78);
         Ke3fe94d90000d2a7cb(out, new Color(231, 197, 184, 18), x, y, (float)(170 + i * 11), 3.0F);
      }

      Kb32049d950edbcaa9d(out, new Color(119, 98, 109), far, 0.0F, 610.0F, 160.0F, 430.0F, 270.0F, 535.0F, 390.0F, 345.0F, 580.0F, 580.0F, 740.0F, 454.0F, 890.0F, 552.0F, 1110.0F, 440.0F, 1360.0F, 565.0F, 1510.0F, 380.0F, 1700.0F, 605.0F);
      Kb32049d950edbcaa9d(out, new Color(60, 79, 91), far * 1.5F, -100.0F, 690.0F, 90.0F, 574.0F, 170.0F, 612.0F, 300.0F, 491.0F, 540.0F, 670.0F, 810.0F, 563.0F, 990.0F, 688.0F, 1230.0F, 539.0F, 1400.0F, 632.0F, 1690.0F, 508.0F);
      K434d274a0d1e502a03(out, new Color(121, 133, 132), new Color(29, 54, 66), -100.0F, 655.0F, 1800.0F, 245.0F);

      for(int i = 0; i < 20; ++i) {
         float y = (float)(683 + i * 10);
         float x = (float)(980 - i * 8) + far;
         Ke3fe94d90000d2a7cb(out, new Color(239, 193, 153, 30 - i), x, y, (float)(200 + i * 15), 2.0F);
      }

      Kb32049d950edbcaa9d(out, new Color(23, 42, 49), near, -100.0F, 560.0F, 90.0F, 605.0F, 280.0F, 716.0F, 540.0F, 813.0F, 690.0F, 920.0F);
      Kb32049d950edbcaa9d(out, new Color(18, 34, 41), near, 1130.0F, 920.0F, 1270.0F, 805.0F, 1380.0F, 695.0F, 1560.0F, 602.0F, 1720.0F, 650.0F);

      for(int i = 0; i < 12; ++i) {
         float x = (float)(5 + i * 38) + near;
         K2aedce0fc3ba524dbf(out, x, (float)(620 + i * 17), (float)(55 + i * 17 % 63), new Color(17, 33, 40));
      }

      for(int i = 0; i < 8; ++i) {
         K2aedce0fc3ba524dbf(out, (float)(1430 + i * 31) + near, (float)(704 + i * 7), (float)(70 + i * 6), new Color(14, 27, 35));
      }

      particles(out, t, new Color(242, 210, 145), 22, 550.0F, 800.0F);
   }

   private static void K39c5b1383302acc289(List<Keea4a4bc04198e2324> out, double t, float far, float near) {
      Ka033ffbba3d4067bac(out, new Color(245, 99, 53, 14), 1040.0F + far, 470.0F, 290.0F);
      Ka033ffbba3d4067bac(out, new Color(245, 99, 53, 18), 1040.0F + far, 470.0F, 210.0F);

      for(int i = 0; i < 13; ++i) {
         float x = (float)(-80 + i * 150) + far;
         float top = (float)(180 + i * 47 % 240);
         K9c1941924c64660cf7(out, new Color(76, 34, 40), x, 730.0F, x + 15.0F, top + 38.0F, x + 60.0F, top, x + 85.0F, top + 13.0F, x + 105.0F, 730.0F);
         K9c1941924c64660cf7(out, new Color(100, 41, 42), x + 60.0F, top, x + 85.0F, top + 13.0F, x + 105.0F, 730.0F, x + 64.0F, 730.0F);
         if (i % 3 == 0) {
            K434d274a0d1e502a03(out, new Color(238, 111, 50, 155), new Color(248, 85, 32, 15), x + 70.0F, top + 70.0F, 5.0F, 580.0F - top);
         }
      }

      Kb32049d950edbcaa9d(out, new Color(58, 27, 35), far * 1.4F, -100.0F, 620.0F, 120.0F, 554.0F, 320.0F, 678.0F, 590.0F, 632.0F, 790.0F, 696.0F, 1020.0F, 614.0F, 1340.0F, 669.0F, 1660.0F, 565.0F);
      K434d274a0d1e502a03(out, new Color(255, 130, 58), new Color(164, 42, 32), -100.0F, 717.0F, 1800.0F, 200.0F);

      for(int i = 0; i < 18; ++i) {
         Ke3fe94d90000d2a7cb(out, new Color(255, 190, 98, 75), (float)(560 + i * 97 % 510) + far, (float)(736 + i * 9), (float)(90 + i * 23 % 160), 2.0F);
      }

      K9c1941924c64660cf7(out, new Color(36, 19, 27), -100.0F + near, 280.0F, 95.0F + near, 240.0F, 232.0F + near, 586.0F, 501.0F + near, 741.0F, 713.0F + near, 900.0F, -100.0F + near, 900.0F);
      K9c1941924c64660cf7(out, new Color(63, 29, 32), 95.0F + near, 240.0F, 170.0F + near, 285.0F, 290.0F + near, 612.0F, 232.0F + near, 586.0F);
      K9c1941924c64660cf7(out, new Color(30, 17, 25), 1140.0F + near, 900.0F, 1210.0F + near, 725.0F, 1380.0F + near, 654.0F, 1460.0F + near, 370.0F, 1700.0F + near, 225.0F, 1700.0F + near, 900.0F);
      K9c1941924c64660cf7(out, new Color(77, 32, 32), 1460.0F + near, 370.0F, 1500.0F + near, 382.0F, 1440.0F + near, 673.0F, 1380.0F + near, 654.0F);
      K434d274a0d1e502a03(out, new Color(255, 141, 60, 150), new Color(255, 85, 35, 40), 270.0F + near, 641.0F, 6.0F, 210.0F);
      particles(out, t, new Color(255, 163, 90), 42, 160.0F, 850.0F);
   }

   private static void end(List<Keea4a4bc04198e2324> out, double t, float far, float near) {
      Ka033ffbba3d4067bac(out, new Color(181, 143, 244, 7), 1170.0F + far, 260.0F, 280.0F);
      Ka033ffbba3d4067bac(out, new Color(181, 143, 244, 9), 1170.0F + far, 260.0F, 190.0F);
      Ka033ffbba3d4067bac(out, new Color(206, 186, 244), 1170.0F + far, 260.0F, 72.0F);
      Ka033ffbba3d4067bac(out, new Color(27, 23, 49), 1195.0F + far, 236.0F, 70.0F);

      for(int i = 0; i < 95; ++i) {
         float x = (float)(i * 347 % 1660) + far;
         float y = (float)(65 + i * 139 % 590);
         int alpha = 65 + (int)((double)55.0F * ((double)1.0F + Math.sin(t * 0.6 + (double)i)));
         Ke3fe94d90000d2a7cb(out, new Color(219, 205, 255, alpha), x, y, i % 9 == 0 ? 3.0F : 1.5F, i % 9 == 0 ? 3.0F : 1.5F);
      }

      Kb65b8ba15203771a5c(out, 690.0F + far, 560.0F, 235.0F, 86.0F, new Color(119, 105, 140), new Color(52, 43, 77));
      Kb65b8ba15203771a5c(out, 1290.0F + far, 530.0F, 145.0F, 100.0F, new Color(99, 86, 123), new Color(43, 33, 66));
      Kb65b8ba15203771a5c(out, 980.0F + far, 698.0F, 105.0F, 72.0F, new Color(91, 81, 111), new Color(36, 32, 59));
      Kb65b8ba15203771a5c(out, 110.0F + near, 690.0F, 560.0F, 315.0F, new Color(154, 137, 164), new Color(44, 34, 64));
      Kb65b8ba15203771a5c(out, 1510.0F + near, 762.0F, 540.0F, 310.0F, new Color(124, 109, 147), new Color(35, 28, 57));

      for(int i = 0; i < 5; ++i) {
         float x = (float)(80 + i * 48) + near;
         float height = (float)(160 + i * 47 % 160);
         Ke3fe94d90000d2a7cb(out, new Color(20, 19, 35), x, 676.0F - height, 24.0F, height);
         Ke3fe94d90000d2a7cb(out, new Color(37, 29, 52), x + 18.0F, 676.0F - height, 6.0F, height);
         Ka033ffbba3d4067bac(out, new Color(198, 148, 255, 35), x + 12.0F, 676.0F - height - 5.0F, 12.0F);
         Ke3fe94d90000d2a7cb(out, new Color(231, 203, 255), x + 7.0F, 676.0F - height - 9.0F, 9.0F, 9.0F);
      }

      particles(out, t, new Color(196, 166, 253), 24, 370.0F, 880.0F);
   }

   private static void Kb65b8ba15203771a5c(List<Keea4a4bc04198e2324> out, float x, float y, float width, float depth, Color top, Color side) {
      K9c1941924c64660cf7(out, side, x - width / 2.0F, y, x + width / 2.0F, y, x + width * 0.22F, y + depth * 0.7F, x - width * 0.08F, y + depth, x - width * 0.32F, y + depth * 0.52F);
      K9c1941924c64660cf7(out, top, x - width / 2.0F, y, x - width * 0.27F, y - 30.0F, x + width * 0.24F, y - 38.0F, x + width / 2.0F, y, x + width * 0.12F, y + 19.0F, x - width * 0.24F, y + 12.0F);
      K9c1941924c64660cf7(out, new Color(side.getRed() + 14, side.getGreen() + 11, side.getBlue() + 18), x + width * 0.12F, y + 19.0F, x + width / 2.0F, y, x + width * 0.22F, y + depth * 0.7F, x - width * 0.08F, y + depth);
   }

   private static void K2aedce0fc3ba524dbf(List<Keea4a4bc04198e2324> out, float x, float y, float h, Color color) {
      Ke3fe94d90000d2a7cb(out, color, x - 3.0F, y - h * 0.5F, 6.0F, h * 0.5F);

      for(int i = 0; i < 3; ++i) {
         float top = y - h + (float)i * h * 0.19F;
         float half = h * (0.18F + (float)i * 0.07F);
         K9c1941924c64660cf7(out, color, x, top, x + half, top + h * 0.48F, x - half, top + h * 0.48F);
      }

   }

   private static void particles(List<Keea4a4bc04198e2324> out, double t, Color color, int count, float minY, float maxY) {
      for(int i = 0; i < count; ++i) {
         float x = (float)(i * 431 % 1600) + (float)Math.sin(t * 0.2 + (double)i) * 9.0F;
         float y = maxY - (float)(((double)(i * 157) + t * (double)12.0F) % (double)(maxY - minY));
         int alpha = 35 + (int)((double)55.0F * ((double)1.0F + Math.sin(t * 0.8 + (double)(i * 3))));
         Ke3fe94d90000d2a7cb(out, new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha), x, y, 2.0F, 3.0F);
      }

   }

   private static void Kb32049d950edbcaa9d(List<Keea4a4bc04198e2324> out, Color color, float offset, float... edge) {
      for(int i = 0; i < edge.length - 2; i += 2) {
         K9c1941924c64660cf7(out, color, edge[i] + offset, edge[i + 1], edge[i + 2] + offset, edge[i + 3], edge[i + 2] + offset, 1050.0F, edge[i] + offset, 1050.0F);
      }

   }

   private static void Ka033ffbba3d4067bac(List<Keea4a4bc04198e2324> out, Color color, float x, float y, float radius) {
      float[] points = new float[96];

      for(int i = 0; i < 48; ++i) {
         points[i * 2] = x + (float)Math.cos((double)i * Math.PI / (double)24.0F) * radius;
         points[i * 2 + 1] = y + (float)Math.sin((double)i * Math.PI / (double)24.0F) * radius;
      }

      K9c1941924c64660cf7(out, color, points);
   }

   private static void Ke3fe94d90000d2a7cb(List<Keea4a4bc04198e2324> out, Color color, float x, float y, float w, float h) {
      K434d274a0d1e502a03(out, color, color, x, y, w, h);
   }

   private static void K434d274a0d1e502a03(List<Keea4a4bc04198e2324> out, Color top, Color bottom, float x, float y, float w, float h) {
      out.add(new Keea4a4bc04198e2324(new float[]{x, y, x + w, y, x + w, y + h, x, y + h}, top, bottom));
   }

   private static void K9c1941924c64660cf7(List<Keea4a4bc04198e2324> out, Color color, float... points) {
      out.add(new Keea4a4bc04198e2324(points, color, color));
   }
}
