package code.miaware.client.features.modules.render;

import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import lombok.Generated;
import net.minecraft.class_10142;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

class Ke3847952eb862510db {
   private final class_243 position;
   private final float size;
   private final int index;
   private final int lifeTime;
   private final int spawnDur;
   private final int dyingDur;
   private final String animMode;
   private final String textureMode;
   private final float playerYaw;
   private final Kf39dc27225f4d9fe6e timerUtil = new Kf39dc27225f4d9fe6e();
   private final Ka91715a8c4e3f3b7c4 animation = new Ka91715a8c4e3f3b7c4();
   private final Ka91715a8c4e3f3b7c4 sizeAnimation = new Ka91715a8c4e3f3b7c4();
   private boolean isBack = false;

   public boolean update() {
      if (this.timerUtil.finished((long)(this.spawnDur + this.lifeTime))) {
         this.isBack = true;
      }

      return this.animation.getValue() <= 0.1 && this.isBack;
   }

   public void render(class_4587 matrixStack, boolean pentagram, boolean bubbles, boolean procedural) {
      this.animation.update();
      this.sizeAnimation.update();
      this.sizeAnimation.run(this.isBack ? (this.animMode.equals("In") ? (double)0.0F : (double)1.0F) : (double)1.0F, this.isBack ? (long)this.dyingDur : (long)this.spawnDur, Kf3b62ecfba0c3544e6.SINE_OUT);
      this.animation.run(this.isBack ? (double)0.0F : (double)1.0F, this.isBack ? (long)this.dyingDur : (long)this.spawnDur, Kf3b62ecfba0c3544e6.SINE_OUT);
      float anim = (float)this.animation.getValue();
      float smoothAnim = anim * anim * (3.0F - 2.0F * anim);
      int alpha = (int)(smoothAnim * 255.0F);
      float scale = (float)((double)(pentagram ? 0.68F : 1.0F) * this.sizeAnimation.getValue() * (double)this.size);
      int color1RGB = K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(this.index), alpha).getRGB();
      int color2RGB = K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(this.index + 90), alpha).getRGB();
      int color3RGB = K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(this.index + 180), alpha).getRGB();
      int color4RGB = K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(this.index + 240), alpha).getRGB();
      matrixStack.method_22903();
      matrixStack.method_22904(this.position.field_1352 - K4600bef438dafc1a9c.mc.method_1561().field_4686.method_19326().method_10216(), this.position.field_1351 - K4600bef438dafc1a9c.mc.method_1561().field_4686.method_19326().method_10214(), this.position.field_1350 - K4600bef438dafc1a9c.mc.method_1561().field_4686.method_19326().method_10215());
      matrixStack.method_22907(class_7833.field_40714.rotationDegrees(90.0F));
      float rotationDegrees = 0.0F;
      if (pentagram) {
         rotationDegrees = (float)this.timerUtil.getElapsedTime() * 0.18F;
      } else if (this.textureMode.equals("Круг 4")) {
         rotationDegrees = -this.playerYaw + 180.0F + (float)this.timerUtil.getElapsedTime() * 0.08F;
      } else if (!this.textureMode.equals("Круг 3") && !this.textureMode.equals("Круг 5")) {
         rotationDegrees = (float)this.timerUtil.getElapsedTime();
      } else if (this.animMode.equals("Slow")) {
         rotationDegrees = (float)this.timerUtil.getElapsedTime() * 0.08F;
      }

      matrixStack.method_22907(class_7833.field_40718.rotationDegrees(rotationDegrees));
      Matrix4f matrix = matrixStack.method_23760().method_23761();
      if (procedural) {
         int baseTheme = Kb18d549c13309af9a5.gradient(this.index).getRGB();
         float alphaF = (float)alpha / 255.0F;
         long elapsed = this.timerUtil.getElapsedTime();
         if (this.textureMode.equals("Круг 8")) {
            this.Ke60f1ce621ff0fbc63(matrix, scale, alphaF, elapsed, baseTheme);
         } else if (this.textureMode.equals("Круг 9")) {
            this.Ka2726f69df943ef6e8(matrix, scale, alphaF, elapsed, baseTheme);
         } else if (this.textureMode.equals("Круг 10")) {
            this.K0ca9595ec8d0d67585(matrix, scale, alphaF, elapsed, baseTheme);
         }
      } else if (pentagram) {
         this.Kb4cabd96f058496354(matrix, scale, alpha);
      } else if (bubbles) {
         for(int i = 0; i < 2; ++i) {
            class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
            buffer.method_22918(matrix, -scale, -scale, 0.0F).method_22913(0.0F, 0.0F).method_39415(color1RGB);
            buffer.method_22918(matrix, -scale, scale, 0.0F).method_22913(0.0F, 1.0F).method_39415(color4RGB);
            buffer.method_22918(matrix, scale, scale, 0.0F).method_22913(1.0F, 1.0F).method_39415(color3RGB);
            buffer.method_22918(matrix, scale, -scale, 0.0F).method_22913(1.0F, 0.0F).method_39415(color2RGB);
            class_286.method_43433(buffer.method_60800());
         }
      } else {
         class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
         buffer.method_22918(matrix, scale, -scale, 0.0F).method_22913(0.0F, 1.0F).method_39415(color1RGB);
         buffer.method_22918(matrix, -scale, -scale, 0.0F).method_22913(1.0F, 1.0F).method_39415(color4RGB);
         buffer.method_22918(matrix, -scale, scale, 0.0F).method_22913(1.0F, 0.0F).method_39415(color3RGB);
         buffer.method_22918(matrix, scale, scale, 0.0F).method_22913(0.0F, 0.0F).method_39415(color2RGB);
         class_286.method_43433(buffer.method_60800());
      }

      matrixStack.method_22909();
   }

   private void Ke60f1ce621ff0fbc63(Matrix4f mat, float r, float alpha, long elapsed, int themeRGB) {
      RenderSystem.setShader(class_10142.field_53876);
      class_289 tess = class_289.method_1348();
      class_287 fills = tess.method_60827(class_5596.field_27382, class_290.field_1576);

      for(int g = 0; g < 12; ++g) {
         float gf = (float)g / 12.0F;
         float grI = r * (1.0F + gf * 0.7F);
         float grO = r * (1.0F + (gf + 0.083333336F) * 0.7F);
         float ga = alpha * (1.0F - gf) * (1.0F - gf) * 0.18F;
         this.Kabfeb7299f3992e993(fills, mat, grI, grO, 48, this.K5edf578b1289b8477e(themeRGB, ga));
      }

      int nebSegs = 52;
      int nebBands = 7;
      double elapsedD = (double)elapsed * 0.0028;

      for(int b = 0; b < nebBands; ++b) {
         float bf = (float)b / (float)nebBands;
         float pulse = (float)(Math.sin(elapsedD + (double)b * 0.95) * (double)0.5F + (double)0.5F);
         float bandR = r * (0.15F + bf * 0.72F);
         float thick = r * (0.055F + pulse * 0.035F);
         float ba = alpha * K45c34df60baf36141a.NEB_POW[b] * (0.06F + pulse * 0.1F);
         if (ba >= 0.004F) {
            this.Kabfeb7299f3992e993(fills, mat, bandR - thick, bandR + thick, nebSegs, this.K5edf578b1289b8477e(themeRGB, ba));
         }
      }

      float trailRot = (float)((double)elapsed * 3.5E-4);
      double elapsedTrail = (double)elapsed * 0.003;
      float halfW = r * 0.03F;

      for(int arm = 0; arm < 2; ++arm) {
         float cosBase = (float)Math.cos((double)arm * Math.PI + (double)trailRot);
         float sinBase = (float)Math.sin((double)arm * Math.PI + (double)trailRot);

         for(int seg = 0; seg < 39; ++seg) {
            float edge = K45c34df60baf36141a.TRAIL_EDGE[seg];
            float fA = K45c34df60baf36141a.TRAIL_F[seg];
            float pulse = (float)(Math.sin(elapsedTrail + (double)fA * (double)5.0F + (double)arm * 2.1) * (double)0.25F + (double)0.75F);
            float sa = alpha * edge * pulse * 0.22F;
            if (!(sa < 0.004F)) {
               float coA = cosBase * K45c34df60baf36141a.TRAIL_COS_OFFSET[seg] - sinBase * K45c34df60baf36141a.TRAIL_SIN_OFFSET[seg];
               float siA = sinBase * K45c34df60baf36141a.TRAIL_COS_OFFSET[seg] + cosBase * K45c34df60baf36141a.TRAIL_SIN_OFFSET[seg];
               float coB = cosBase * K45c34df60baf36141a.TRAIL_COS_OFFSET_B[seg] - sinBase * K45c34df60baf36141a.TRAIL_SIN_OFFSET_B[seg];
               float siB = sinBase * K45c34df60baf36141a.TRAIL_COS_OFFSET_B[seg] + cosBase * K45c34df60baf36141a.TRAIL_SIN_OFFSET_B[seg];
               float dxA = -siA;
               float dxB = -siB;
               float fB = K45c34df60baf36141a.TRAIL_F_B[seg];
               float rA = fA * r * 0.88F;
               float rB = fB * r * 0.88F;
               fills.method_22918(mat, coA * rA + dxA * halfW, siA * rA + coA * halfW, 0.0F).method_39415(this.K5edf578b1289b8477e(themeRGB, sa));
               fills.method_22918(mat, coA * rA - dxA * halfW, siA * rA - coA * halfW, 0.0F).method_39415(this.K5edf578b1289b8477e(themeRGB, sa * 0.15F));
               fills.method_22918(mat, coB * rB - dxB * halfW, siB * rB - coB * halfW, 0.0F).method_39415(this.K5edf578b1289b8477e(themeRGB, sa * 0.15F));
               fills.method_22918(mat, coB * rB + dxB * halfW, siB * rB + coB * halfW, 0.0F).method_39415(this.K5edf578b1289b8477e(themeRGB, sa));
            }
         }
      }

      float cp = (float)(Math.sin((double)elapsed * 0.005) * (double)0.5F + (double)0.5F);
      float cpMod = 0.35F + cp * 0.3F;

      for(int g = 0; g < 9; ++g) {
         float gr = 0.006F + (float)g * 0.016F;
         float ga = alpha * K45c34df60baf36141a.PULSE_CENTER_POW[g] * cpMod;
         this.Ka3a9ec627f810d0554(fills, mat, gr * 0.25F, gr, 20, this.K5edf578b1289b8477e(themeRGB, ga), 0.0F, 0.0F);
      }

      class_286.method_43433(fills.method_60800());
      class_287 lines = tess.method_60827(class_5596.field_29344, class_290.field_1576);
      this.K28d1e2d5a0bedfc7da(lines, mat, r * 1.12F, 64, this.K5edf578b1289b8477e(themeRGB, alpha * 0.05F));
      this.K28d1e2d5a0bedfc7da(lines, mat, r * 1.06F, 64, this.K5edf578b1289b8477e(themeRGB, alpha * 0.14F));
      this.K28d1e2d5a0bedfc7da(lines, mat, r * 1.02F, 64, this.K5edf578b1289b8477e(themeRGB, alpha * 0.35F));
      this.K28d1e2d5a0bedfc7da(lines, mat, r * 1.0F, 64, this.K5edf578b1289b8477e(themeRGB, alpha * 0.8F));
      this.K28d1e2d5a0bedfc7da(lines, mat, r * 0.98F, 64, this.K5edf578b1289b8477e(themeRGB, alpha * 1.0F));
      double rot = (double)elapsed * 6.5E-4;
      double elapsedRay = (double)elapsed * 0.0045;

      for(int i = 0; i < 16; ++i) {
         double angle = (Math.PI / 8D) * (double)i + rot;
         float pulse = (float)(Math.sin(elapsedRay + (double)i * 0.6) * (double)0.5F + (double)0.5F);
         float rLen = r * (0.1F + pulse * 0.35F);
         float rS = r * 1.005F;
         float ca = (float)Math.cos(angle);
         float sa2 = (float)Math.sin(angle);
         float pulsedAlphaBase = 0.2F + pulse * 0.6F;

         for(int l = 0; l < 3; ++l) {
            float la = alpha * pulsedAlphaBase * K45c34df60baf36141a.PULSE_LAYER_A[l];
            lines.method_22918(mat, ca * rS, sa2 * rS, 0.0F).method_39415(this.K5edf578b1289b8477e(themeRGB, la));
            lines.method_22918(mat, ca * (rS + rLen * K45c34df60baf36141a.PULSE_LEN_M[l]), sa2 * (rS + rLen * K45c34df60baf36141a.PULSE_LEN_M[l]), 0.0F).method_39415(this.K3d0ce3480c8c935351(themeRGB));
         }
      }

      for(int e = 0; e < 3; ++e) {
         float ep = (float)((elapsed + (long)e * 500L) % 1600L) / 1600.0F;
         float er = r * (1.0F + ep * 0.85F);
         float ea = alpha * (1.0F - ep) * (1.0F - ep) * 0.45F;
         if (ea >= 0.004F) {
            this.K28d1e2d5a0bedfc7da(lines, mat, er, 56, this.K5edf578b1289b8477e(themeRGB, ea * 0.4F));
            this.K28d1e2d5a0bedfc7da(lines, mat, er * 0.991F, 56, this.K5edf578b1289b8477e(themeRGB, ea * 0.8F));
            this.K28d1e2d5a0bedfc7da(lines, mat, er * 0.982F, 56, this.K5edf578b1289b8477e(themeRGB, ea));
         }
      }

      class_286.method_43433(lines.method_60800());
   }

   private void Ka2726f69df943ef6e8(Matrix4f mat, float r, float alpha, long elapsed, int themeRGB) {
      RenderSystem.setShader(class_10142.field_53876);
      class_289 tess = class_289.method_1348();
      class_287 fills = tess.method_60827(class_5596.field_27382, class_290.field_1576);
      int gs = 44;

      for(int g = 0; g < 10; ++g) {
         float gf = (float)g / 10.0F;
         float gr = r * (1.0F + gf * 0.55F);
         float ga = alpha * K45c34df60baf36141a.SPIRAL_HALO_POW[g] * 0.16F;
         this.Kabfeb7299f3992e993(fills, mat, gr * 0.976F, gr, gs, this.K5edf578b1289b8477e(themeRGB, ga));
      }

      for(int i = 0; i < 10; ++i) {
         float rf = (float)i / 10.0F;
         float ia = alpha * K45c34df60baf36141a.SPIRAL_INNER_POW[i] * 0.22F;
         this.Kabfeb7299f3992e993(fills, mat, r * rf * 0.92F, r * (rf + 0.1F) * 0.92F, gs, this.K5edf578b1289b8477e(themeRGB, ia));
      }

      int spirals = 3;

      for(int s = 0; s < spirals; ++s) {
         double phase = (double)elapsed * 0.0013 * (s % 2 == 0 ? (double)1.0F : -1.15) + 2.0943951023931953 * (double)s;
         double tipA = 17.27875959474386 + phase;
         float tx = (float)(Math.cos(tipA) * (double)r);
         float ty = (float)(Math.sin(tipA) * (double)r);
         float pulse = (float)(Math.sin((double)elapsed * 0.006 + (double)s * 2.1) * (double)0.5F + (double)0.5F);
         float pBase = alpha * pulse * 0.75F;

         for(int g = 0; g < 7; ++g) {
            float gr = 0.015F + (float)g * 0.028F;
            float ga = pBase * K45c34df60baf36141a.SPIRAL_TIP_POW[g];
            this.Ka3a9ec627f810d0554(fills, mat, gr * 0.4F, gr, 20, this.K5edf578b1289b8477e(themeRGB, ga), tx, ty);
         }
      }

      class_286.method_43433(fills.method_60800());
      class_287 lines = tess.method_60827(class_5596.field_29344, class_290.field_1576);
      int segs = 80;

      for(int s = 0; s < spirals; ++s) {
         double dir = s % 2 == 0 ? (double)1.0F : -1.15;
         double phase = (double)elapsed * 0.0013 * dir + 2.0943951023931953 * (double)s;

         for(int i = 0; i < segs - 1; ++i) {
            float fA = (float)i / (float)segs;
            float fB = (float)(i + 1) / (float)segs;
            float edge = K45c34df60baf36141a.SPIRAL_EDGE_POW07[i];
            float pulse = (float)(Math.sin((double)elapsed * 0.004 + (double)fA * (double)8.0F + (double)s) * 0.06 + 0.94);
            float rA = fA * r * pulse;
            float rB = fB * r * pulse;
            double aA = (double)fA * Math.PI * (double)5.5F + phase;
            double aB = (double)fB * Math.PI * (double)5.5F + phase;
            float coA = (float)Math.cos(aA);
            float siA = (float)Math.sin(aA);
            float coB = (float)Math.cos(aB);
            float siB = (float)Math.sin(aB);
            float xA = coA * rA;
            float yA = siA * rA;
            float xB = coB * rB;
            float yB = siB * rB;

            for(int layer = 0; layer < 3; ++layer) {
               float sa = alpha * edge * K45c34df60baf36141a.SPIRAL_LAYER_ALPHA[layer] * pulse;
               if (sa >= 0.008F) {
                  lines.method_22918(mat, xA, yA, 0.0F).method_39415(this.K5edf578b1289b8477e(themeRGB, sa));
                  lines.method_22918(mat, xB, yB, 0.0F).method_39415(this.K5edf578b1289b8477e(themeRGB, sa));
               }
            }
         }
      }

      this.K28d1e2d5a0bedfc7da(lines, mat, r * 1.1F, 64, this.K5edf578b1289b8477e(themeRGB, alpha * 0.05F));
      this.K28d1e2d5a0bedfc7da(lines, mat, r * 1.05F, 64, this.K5edf578b1289b8477e(themeRGB, alpha * 0.15F));
      this.K28d1e2d5a0bedfc7da(lines, mat, r * 1.01F, 64, this.K5edf578b1289b8477e(themeRGB, alpha * 0.4F));
      this.K28d1e2d5a0bedfc7da(lines, mat, r * 1.0F, 64, this.K5edf578b1289b8477e(themeRGB, alpha * 0.85F));
      this.K28d1e2d5a0bedfc7da(lines, mat, r * 0.97F, 64, this.K5edf578b1289b8477e(themeRGB, alpha * 0.5F));

      for(int e = 0; e < 2; ++e) {
         float ep = (float)((elapsed + (long)e * 700L) % 1400L) / 1400.0F;
         float er = r * (1.0F + ep * 0.7F);
         float ea = alpha * (1.0F - ep) * (1.0F - ep) * 0.38F;
         if (ea >= 0.004F) {
            this.K28d1e2d5a0bedfc7da(lines, mat, er, 56, this.K5edf578b1289b8477e(themeRGB, ea * 0.5F));
            this.K28d1e2d5a0bedfc7da(lines, mat, er * 0.989F, 56, this.K5edf578b1289b8477e(themeRGB, ea));
         }
      }

      class_286.method_43433(lines.method_60800());
   }

   private void K0ca9595ec8d0d67585(Matrix4f mat, float r, float alpha, long elapsed, int themeRGB) {
      RenderSystem.setShader(class_10142.field_53876);
      class_289 tess = class_289.method_1348();
      int arcCount = 6;
      float[] arcStartA = new float[6];

      for(int a = 0; a < 6; ++a) {
         arcStartA[a] = (float)((double)((float)elapsed * K45c34df60baf36141a.ARC_SPEEDS[a]) + (double)a * (Math.PI / 3D));
      }

      double orbitBase0 = (double)((float)elapsed * K45c34df60baf36141a.ORBIT_SPDS[0] * (float)K45c34df60baf36141a.ORBIT_DIRS[0]);
      double orbitBase1 = (double)((float)elapsed * K45c34df60baf36141a.ORBIT_SPDS[1] * (float)K45c34df60baf36141a.ORBIT_DIRS[1]);
      int cnt0 = K45c34df60baf36141a.ORBIT_DOT_COUNTS[0];
      int cnt1 = K45c34df60baf36141a.ORBIT_DOT_COUNTS[1];
      float[] orbitCos0 = new float[cnt0];
      float[] orbitSin0 = new float[cnt0];
      float[] orbitCos1 = new float[cnt1];
      float[] orbitSin1 = new float[cnt1];

      for(int i = 0; i < cnt0; ++i) {
         double a = K45c34df60baf36141a.TWO_PI_ORBIT_0 * (double)i + orbitBase0;
         orbitCos0[i] = (float)Math.cos(a);
         orbitSin0[i] = (float)Math.sin(a);
      }

      for(int i = 0; i < cnt1; ++i) {
         double a = K45c34df60baf36141a.TWO_PI_ORBIT_1 * (double)i + orbitBase1;
         orbitCos1[i] = (float)Math.cos(a);
         orbitSin1[i] = (float)Math.sin(a);
      }

      class_287 fills = tess.method_60827(class_5596.field_27382, class_290.field_1576);
      int gs = 52;

      for(int g = 0; g < 14; ++g) {
         float gf = (float)g / 14.0F;
         float gr = r * (1.0F + gf * 0.9F);
         float ga = alpha * K45c34df60baf36141a.HALO_POW[g] * 0.2F;
         this.Kabfeb7299f3992e993(fills, mat, gr * 0.976F, gr, gs, this.K5edf578b1289b8477e(themeRGB, ga));
      }

      float webR = r * 0.88F;
      double webRot = (double)elapsed * 8.0E-4;

      for(int sp = 0; sp < 12; ++sp) {
         double angle = (Math.PI / 6D) * (double)sp + webRot;
         float pulse3 = (float)(Math.sin((double)elapsed * 0.005 + (double)sp * 0.8) * (double)0.5F + (double)0.5F);
         float pulseAlpha = 0.08F + pulse3 * 0.12F;
         float ca = (float)Math.cos(angle);
         float si = (float)Math.sin(angle);

         for(int seg = 0; seg < 9; ++seg) {
            float edgeA = K45c34df60baf36141a.SPOKE_EDGE[seg];
            float edgeB = K45c34df60baf36141a.SPOKE_EDGE[seg + 1];
            float sa = alpha * edgeA * pulseAlpha;
            float sb = alpha * edgeB * pulseAlpha;
            if (!(sa < 0.004F) || !(sb < 0.004F)) {
               float fA = (float)seg / 10.0F;
               float fB = (float)(seg + 1) / 10.0F;
               float rA = fA * webR;
               float rB = fB * webR;
               fills.method_22918(mat, ca * rA + si * 0.025F, si * rA - ca * 0.025F, 0.0F).method_39415(this.K5edf578b1289b8477e(themeRGB, sa));
               fills.method_22918(mat, ca * rA - si * 0.025F, si * rA + ca * 0.025F, 0.0F).method_39415(this.K5edf578b1289b8477e(themeRGB, sa));
               fills.method_22918(mat, ca * rB - si * 0.025F, si * rB + ca * 0.025F, 0.0F).method_39415(this.K5edf578b1289b8477e(themeRGB, sb));
               fills.method_22918(mat, ca * rB + si * 0.025F, si * rB - ca * 0.025F, 0.0F).method_39415(this.K5edf578b1289b8477e(themeRGB, sb));
            }
         }
      }

      for(int wr = 1; wr <= 4; ++wr) {
         float wrf = (float)wr / 4.0F;
         float wRad = webR * wrf;
         float pulse4 = (float)(Math.sin((double)elapsed * 0.004 + (double)wr * 1.2) * (double)0.5F + (double)0.5F);
         float wa = alpha * (1.0F - wrf * 0.5F) * (0.08F + pulse4 * 0.12F);
         this.Kabfeb7299f3992e993(fills, mat, wRad * 0.985F, wRad * 1.015F, 40, this.K5edf578b1289b8477e(themeRGB, wa));
      }

      for(int g = 0; g < 10; ++g) {
         float gr = 0.01F + (float)g * 0.02F;
         float ga = alpha * K45c34df60baf36141a.CENTER_GLOW_POW[g] * 0.65F;
         this.Ka3a9ec627f810d0554(fills, mat, gr * 0.25F, gr, 20, this.K5edf578b1289b8477e(themeRGB, ga), 0.0F, 0.0F);
      }

      for(int a = 0; a < 6; ++a) {
         float arcR = r * K45c34df60baf36141a.ARC_R_MULT[a];
         double tipA = (double)arcStartA[a] + (Math.PI * 2D) * (double)K45c34df60baf36141a.ARC_LEN[a];
         float tx = (float)(Math.cos(tipA) * (double)arcR);
         float ty = (float)(Math.sin(tipA) * (double)arcR);
         float pulse = (float)(Math.sin((double)elapsed * 0.007 + (double)a * 1.7) * (double)0.5F + (double)0.5F);
         float pBase = alpha * pulse * 0.85F;

         for(int g = 0; g < 8; ++g) {
            float gr = 0.012F + (float)g * 0.022F;
            float ga = pBase * K45c34df60baf36141a.ARC_GLOW_POW[g];
            this.Ka3a9ec627f810d0554(fills, mat, gr * 0.35F, gr, 16, this.K5edf578b1289b8477e(themeRGB, ga), tx, ty);
         }
      }

      float[] orbitRs = new float[]{r * 1.28F, r * 1.48F};

      for(int o = 0; o < 2; ++o) {
         int dc = o == 0 ? cnt0 : cnt1;
         float or = orbitRs[o];
         float[] oCos = o == 0 ? orbitCos0 : orbitCos1;
         float[] oSin = o == 0 ? orbitSin0 : orbitSin1;

         for(int i = 0; i < dc; ++i) {
            float dx = oCos[i] * or;
            float dy = oSin[i] * or;
            float pulse = (float)(Math.sin((double)elapsed * 0.008 + (double)i * 1.3 + (double)o) * (double)0.5F + (double)0.5F);
            float daB = alpha * (0.3F + pulse * 0.55F) * 0.7F;

            for(int g = 0; g < 5; ++g) {
               float gr = 0.01F + (float)g * 0.018F;
               float ga = daB * K45c34df60baf36141a.DOT_GLOW_POW[g];
               this.Ka3a9ec627f810d0554(fills, mat, gr * 0.3F, gr, 12, this.K5edf578b1289b8477e(themeRGB, ga), dx, dy);
            }
         }
      }

      class_286.method_43433(fills.method_60800());
      class_287 lines = tess.method_60827(class_5596.field_29344, class_290.field_1576);
      int arcSegs = 36;

      for(int a = 0; a < 6; ++a) {
         float arcR = r * K45c34df60baf36141a.ARC_R_MULT[a];
         int total = (int)((float)arcSegs * K45c34df60baf36141a.ARC_LEN[a]);
         float pulse = (float)(Math.sin((double)elapsed * 0.005 + (double)a * 1.3) * 0.35 + 0.65);
         double arcAngleSpan = (Math.PI * 2D) * (double)K45c34df60baf36141a.ARC_LEN[a];

         for(int layer = 0; layer < 3; ++layer) {
            float rW = arcR * (1.0F + K45c34df60baf36141a.NOVA_LAYER_W[layer] * 0.012F);
            float layerAlphaPulse = alpha * K45c34df60baf36141a.NOVA_LAYER_A[layer] * pulse;

            for(int seg = 0; seg < total - 1; ++seg) {
               int edgeIdx = seg * 63 / (total - 1);
               float edge = K45c34df60baf36141a.ARC_EDGE_LUT[edgeIdx];
               float sa = layerAlphaPulse * edge;
               if (!(sa < 0.008F)) {
                  double aA = (double)arcStartA[a] + (double)seg / (double)total * arcAngleSpan;
                  double aB = (double)arcStartA[a] + (double)(seg + 1) / (double)total * arcAngleSpan;
                  lines.method_22918(mat, (float)(Math.cos(aA) * (double)rW), (float)(Math.sin(aA) * (double)rW), 0.0F).method_39415(this.K5edf578b1289b8477e(themeRGB, sa));
                  lines.method_22918(mat, (float)(Math.cos(aB) * (double)rW), (float)(Math.sin(aB) * (double)rW), 0.0F).method_39415(this.K5edf578b1289b8477e(themeRGB, sa));
               }
            }
         }
      }

      this.K28d1e2d5a0bedfc7da(lines, mat, r * 1.14F, 72, this.K5edf578b1289b8477e(themeRGB, alpha * 0.04F));
      this.K28d1e2d5a0bedfc7da(lines, mat, r * 1.08F, 72, this.K5edf578b1289b8477e(themeRGB, alpha * 0.1F));
      this.K28d1e2d5a0bedfc7da(lines, mat, r * 1.03F, 72, this.K5edf578b1289b8477e(themeRGB, alpha * 0.25F));
      this.K28d1e2d5a0bedfc7da(lines, mat, r * 1.0F, 72, this.K5edf578b1289b8477e(themeRGB, alpha * 0.75F));
      this.K28d1e2d5a0bedfc7da(lines, mat, r * 0.98F, 72, this.K5edf578b1289b8477e(themeRGB, alpha * 1.0F));
      this.K28d1e2d5a0bedfc7da(lines, mat, r * 0.95F, 72, this.K5edf578b1289b8477e(themeRGB, alpha * 0.4F));

      for(int o = 0; o < 2; ++o) {
         int dc = o == 0 ? cnt0 : cnt1;
         float or = orbitRs[o];
         float[] oCos = o == 0 ? orbitCos0 : orbitCos1;
         float[] oSin = o == 0 ? orbitSin0 : orbitSin1;
         float cosDelta = K45c34df60baf36141a.TAIL_COS_DELTA;
         float sinDelta = o == 0 ? -K45c34df60baf36141a.TAIL_SIN_DELTA : K45c34df60baf36141a.TAIL_SIN_DELTA;

         for(int i = 0; i < dc; ++i) {
            float pulse = (float)(Math.sin((double)elapsed * 0.008 + (double)i * 1.3 + (double)o) * (double)0.5F + (double)0.5F);
            float da = alpha * (0.3F + pulse * 0.55F);
            float px = oCos[i] * or;
            float py = oSin[i] * or;

            for(int seg = 1; seg <= 3; ++seg) {
               float sa = da * (1.0F - (float)seg * 0.28F);
               float nx = px * cosDelta - py * sinDelta;
               float ny = px * sinDelta + py * cosDelta;
               if (sa >= 0.008F) {
                  lines.method_22918(mat, px, py, 0.0F).method_39415(this.K5edf578b1289b8477e(themeRGB, sa));
                  lines.method_22918(mat, nx, ny, 0.0F).method_39415(this.K5edf578b1289b8477e(themeRGB, sa));
               }

               px = nx;
               py = ny;
            }
         }
      }

      for(int e = 0; e < 3; ++e) {
         float ep = (float)((elapsed + (long)e * 550L) % 1800L) / 1800.0F;
         float er = r * (1.0F + ep);
         float ea = alpha * (1.0F - ep) * (1.0F - ep) * 0.48F;
         if (ea >= 0.004F) {
            this.K28d1e2d5a0bedfc7da(lines, mat, er, 72, this.K5edf578b1289b8477e(themeRGB, ea * 0.3F));
            this.K28d1e2d5a0bedfc7da(lines, mat, er * 0.993F, 72, this.K5edf578b1289b8477e(themeRGB, ea * 0.65F));
            this.K28d1e2d5a0bedfc7da(lines, mat, er * 0.985F, 72, this.K5edf578b1289b8477e(themeRGB, ea));
         }
      }

      class_286.method_43433(lines.method_60800());
   }

   private void K28d1e2d5a0bedfc7da(class_287 buf, Matrix4f mat, float r, int segs, int color) {
      float step = 512.0F / (float)segs;

      for(int i = 0; i < segs; ++i) {
         int iA = (int)((float)i * step) & 511;
         int iB = (int)((float)(i + 1) * step) & 511;
         buf.method_22918(mat, K45c34df60baf36141a.COS_LUT[iA] * r, K45c34df60baf36141a.SIN_LUT[iA] * r, 0.0F).method_39415(color);
         buf.method_22918(mat, K45c34df60baf36141a.COS_LUT[iB] * r, K45c34df60baf36141a.SIN_LUT[iB] * r, 0.0F).method_39415(color);
      }

   }

   private void Kabfeb7299f3992e993(class_287 buf, Matrix4f mat, float inner, float outer, int segs, int color) {
      this.Ka3a9ec627f810d0554(buf, mat, inner, outer, segs, color, 0.0F, 0.0F);
   }

   private void Ka3a9ec627f810d0554(class_287 buf, Matrix4f mat, float inner, float outer, int segs, int color, float cx, float cy) {
      float step = 512.0F / (float)segs;

      for(int i = 0; i < segs; ++i) {
         int iA = (int)((float)i * step) & 511;
         int iB = (int)((float)(i + 1) * step) & 511;
         float coA = K45c34df60baf36141a.COS_LUT[iA];
         float siA = K45c34df60baf36141a.SIN_LUT[iA];
         float coB = K45c34df60baf36141a.COS_LUT[iB];
         float siB = K45c34df60baf36141a.SIN_LUT[iB];
         buf.method_22918(mat, cx + coA * outer, cy + siA * outer, 0.0F).method_39415(color);
         buf.method_22918(mat, cx + coA * inner, cy + siA * inner, 0.0F).method_39415(color);
         buf.method_22918(mat, cx + coB * inner, cy + siB * inner, 0.0F).method_39415(color);
         buf.method_22918(mat, cx + coB * outer, cy + siB * outer, 0.0F).method_39415(color);
      }

   }

   private int K5edf578b1289b8477e(int themeRGB, float a) {
      int ai = Math.max(0, Math.min(255, (int)(a * 255.0F)));
      return themeRGB & 16777215 | ai << 24;
   }

   private int K3d0ce3480c8c935351(int themeRGB) {
      return themeRGB & 16777215;
   }

   private void Kb4cabd96f058496354(Matrix4f matrix, float scale, int alpha) {
      RenderSystem.setShader(class_10142.field_53876);
      RenderSystem.lineWidth(1.65F);
      class_287 circleBuffer = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);
      int segments = 68;

      for(int i = 0; i < segments; ++i) {
         float angle1 = (float)((Math.PI * 2D) * (double)i / (double)segments);
         float angle2 = (float)((Math.PI * 2D) * (double)(i + 1) / (double)segments);
         float x1 = (float)Math.cos((double)angle1) * scale;
         float y1 = (float)Math.sin((double)angle1) * scale;
         float x2 = (float)Math.cos((double)angle2) * scale;
         float y2 = (float)Math.sin((double)angle2) * scale;
         Color c1 = K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(this.index + i * 4), alpha);
         Color c2 = K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(this.index + (i + 1) * 4), alpha);
         circleBuffer.method_22918(matrix, x1, y1, 0.0F).method_39415(c1.getRGB());
         circleBuffer.method_22918(matrix, x2, y2, 0.0F).method_39415(c2.getRGB());
      }

      class_286.method_43433(circleBuffer.method_60800());
      float innerRadius = scale * 0.42F;
      float outerRadius = scale * 0.92F;
      float[][] star = new float[10][2];

      for(int i = 0; i < 10; ++i) {
         double angle = (-Math.PI / 2D) + Math.PI * (double)i / (double)5.0F;
         float r = i % 2 == 0 ? outerRadius : innerRadius;
         star[i][0] = (float)Math.cos(angle) * r;
         star[i][1] = (float)Math.sin(angle) * r;
      }

      class_287 starBuffer = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);

      for(int i = 0; i < 5; ++i) {
         int a = i * 2;
         int b = (i * 2 + 4) % 10;
         Color c1 = K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(this.index + i * 45), alpha);
         Color c2 = K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(this.index + (i + 2) * 45), alpha);
         starBuffer.method_22918(matrix, star[a][0], star[a][1], 0.0F).method_39415(c1.getRGB());
         starBuffer.method_22918(matrix, star[b][0], star[b][1], 0.0F).method_39415(c2.getRGB());
      }

      class_286.method_43433(starBuffer.method_60800());
      RenderSystem.setShaderTexture(0, Kc1f6f8c785f6a08448.getImage("particles/glow"));
      RenderSystem.setShader(class_10142.field_53880);
      float glowSize = scale * 0.16F;
      this.K60650d18d121d005bf(matrix, 0.0F, 0.0F, glowSize * 1.3F, new Color(255, 255, 255, (int)((float)alpha * 0.5F)));

      for(int i = 0; i < 5; ++i) {
         this.K60650d18d121d005bf(matrix, star[i * 2][0], star[i * 2][1], glowSize * 0.72F, K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(this.index + i * 40), (int)((float)alpha * 0.82F)));
      }

   }

   private void K60650d18d121d005bf(Matrix4f matrix, float x, float y, float size, Color color) {
      class_287 glow = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
      glow.method_22918(matrix, x - size, y - size, 0.0F).method_22913(0.0F, 0.0F).method_39415(color.getRGB());
      glow.method_22918(matrix, x - size, y + size, 0.0F).method_22913(0.0F, 1.0F).method_39415(color.getRGB());
      glow.method_22918(matrix, x + size, y + size, 0.0F).method_22913(1.0F, 1.0F).method_39415(color.getRGB());
      glow.method_22918(matrix, x + size, y - size, 0.0F).method_22913(1.0F, 0.0F).method_39415(color.getRGB());
      class_286.method_43433(glow.method_60800());
   }

   @Generated
   public Ke3847952eb862510db(final K45c34df60baf36141a var1, final class_243 position, final float size, final int index, final int lifeTime, final int spawnDur, final int dyingDur, final String animMode, final String textureMode, final float playerYaw) {
      this.position = position;
      this.size = size;
      this.index = index;
      this.lifeTime = lifeTime;
      this.spawnDur = spawnDur;
      this.dyingDur = dyingDur;
      this.animMode = animMode;
      this.textureMode = textureMode;
      this.playerYaw = playerYaw;
   }
}
