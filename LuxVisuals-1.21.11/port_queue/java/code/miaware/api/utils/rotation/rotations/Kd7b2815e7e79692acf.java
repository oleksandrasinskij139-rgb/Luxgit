package code.miaware.api.utils.rotation.rotations;

import code.miaware.api.utils.rotation.Kef2fce79615318605c;
import code.miaware.api.utils.rotation.manager.Kbdd81cc863fb7d1df0;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import java.security.SecureRandom;
import java.util.LinkedList;
import net.minecraft.class_1297;
import net.minecraft.class_243;

public class Kd7b2815e7e79692acf extends Kbdd81cc863fb7d1df0 {
   private static final SecureRandom R = new SecureRandom();
   private final float baseSpeed = 25.5F;
   private final float fastSpeed = 44.5F;
   private final LinkedList<Float> yawHist = new LinkedList();
   private final LinkedList<Float> pitchHist = new LinkedList();
   private final int histSize = 3;
   private long lastHit;
   private long lastPause;
   private long lastBlink;
   private long lastTremor;
   private long lastBreath;
   private long lastMicro;
   private long lastPrediction;
   private int combo;
   private int miss;
   private int focus = 85;
   private float fatigue;
   private float wanderYaw;
   private float wanderPitch;
   private float errorYaw;
   private float errorPitch;
   private float aimDrift;
   private float headNoise;
   private float tremorYaw;
   private float tremorPitch;
   private float breath;
   private float microYaw;
   private float microPitch;
   private float predictedYaw;
   private float predictedPitch;
   private float lastTargetYaw;
   private float lastTargetPitch;
   private final float[] yawVel = new float[3];
   private int velIdx;
   private final float[] bezierBuf = new float[8];
   private int bezierIdx;
   private float avgReaction = 50.0F;
   private final float[] reactionTimes = new float[15];
   private int reactIdx;
   private boolean blinking;
   private long blinkStart;
   private final float[] randCache = new float[64];
   private int randIdx;

   public Kd7b2815e7e79692acf() {
      super("Spooky Duel");

      for(int i = 0; i < this.randCache.length; ++i) {
         this.randCache[i] = R.nextFloat();
      }

      for(int i = 0; i < this.reactionTimes.length; ++i) {
         this.reactionTimes[i] = 40.0F + R.nextFloat() * 30.0F;
      }

   }

   private float K56fe609fa68ed7ceb3(float min, float max) {
      this.randIdx = this.randIdx + 1 & 63;
      return min + this.randCache[this.randIdx] * (max - min);
   }

   private float Ke2438f51a633d0bdcc() {
      this.randIdx = this.randIdx + 1 & 63;
      return this.randCache[this.randIdx];
   }

   private float Ka5f91c42e3af3287ad(float t, float a, float b) {
      return a + t * (b - a);
   }

   private float Kd8fca971af3a8490c6(float v, float min, float max) {
      return Math.max(min, Math.min(max, v));
   }

   private void K62ca8a2699671b8a7e(Keb4a85ca52fb971c57 target, long time) {
      float dt = (float)Math.min(50L, time - this.lastPrediction) / 1000.0F;
      if (this.lastPrediction > 0L && dt > 0.0F && dt < 0.1F) {
         float yawV = (target.getYaw() - this.lastTargetYaw) / dt;
         float pitchV = (target.getPitch() - this.lastTargetPitch) / dt;
         this.yawVel[this.velIdx] = yawV;
         this.velIdx = (this.velIdx + 1) % 3;
         float avgYawV = (this.yawVel[0] + this.yawVel[1] + this.yawVel[2]) / 3.0F;
         float predTime = 0.04F + this.Ke2438f51a633d0bdcc() * 0.03F;
         this.predictedYaw = target.getYaw() + avgYawV * predTime;
         this.predictedPitch = target.getPitch() + pitchV * predTime;
      } else {
         this.predictedYaw = target.getYaw();
         this.predictedPitch = target.getPitch();
      }

      this.lastTargetYaw = target.getYaw();
      this.lastTargetPitch = target.getPitch();
      this.lastPrediction = time;
   }

   private float Kc62fda293a9d595d6c(float val) {
      this.bezierBuf[this.bezierIdx] = val;
      this.bezierIdx = (this.bezierIdx + 1) % 8;
      if (this.bezierIdx < 3) {
         return val;
      } else {
         float p0 = this.bezierBuf[(this.bezierIdx - 3 + 8) % 8];
         float p1 = this.bezierBuf[(this.bezierIdx - 2 + 8) % 8];
         float p2 = this.bezierBuf[(this.bezierIdx - 1 + 8) % 8];
         float t = 0.3F;
         float mt = 1.0F - t;
         return mt * mt * mt * p0 + 3.0F * mt * mt * t * p1 + 3.0F * mt * t * t * p2 + t * t * t * val;
      }
   }

   private void K0833921394231e53dd(long time, boolean canHit) {
      if (time - this.lastHit < 100L && this.combo > 2) {
         this.reactionTimes[this.reactIdx] = Math.max(35.0F, this.avgReaction - 1.0F);
         this.reactIdx = (this.reactIdx + 1) % this.reactionTimes.length;
         float s = 0.0F;

         for(float r : this.reactionTimes) {
            s += r;
         }

         this.avgReaction = s / (float)this.reactionTimes.length;
      }

      if (time - this.lastTremor > 20L) {
         this.lastTremor = time;
         float ti = 0.06F * (1.0F + this.fatigue * 0.5F);
         this.tremorYaw = (float)(Math.sin((double)time / (double)45.0F) * 0.05 + Math.sin((double)time / (double)23.0F) * 0.03) * ti;
         this.tremorPitch = (float)(Math.cos((double)time / (double)48.0F) * 0.04 + Math.sin((double)time / (double)27.0F) * 0.02) * ti;
      }

      if (time - this.lastBreath > 100L) {
         this.lastBreath = time;
         this.breath = (float)(Math.sin((double)time / (double)2800.0F) * 0.04 + Math.sin((double)time / (double)1400.0F) * 0.02);
      }

      if ((float)(time - this.lastMicro) > 150.0F + this.K56fe609fa68ed7ceb3(0.0F, 200.0F)) {
         this.lastMicro = time;
         this.microYaw = this.K56fe609fa68ed7ceb3(-0.3F, 0.3F);
         this.microPitch = this.K56fe609fa68ed7ceb3(-0.2F, 0.2F);
      }

      this.microYaw *= 0.92F;
      this.microPitch *= 0.92F;
      if (time - this.lastBlink > 200L) {
         this.lastBlink = time;
         this.headNoise = this.K56fe609fa68ed7ceb3(-0.25F, 0.25F);
      }

      this.headNoise *= 0.95F;
      if (this.blinking && time - this.blinkStart > 100L) {
         this.blinking = false;
      } else if (!this.blinking && time - this.lastBlink > 3500L && this.Ke2438f51a633d0bdcc() < 0.005F) {
         this.blinking = true;
         this.blinkStart = time;
      }

      if (time - this.lastPause > 5000L) {
         if (canHit) {
            this.focus = Math.max(30, this.focus - R.nextInt(10));
         } else {
            this.focus = Math.min(100, this.focus + R.nextInt(15));
         }

         this.fatigue = Math.min(1.0F, this.fatigue + (canHit ? 0.02F : -0.01F));
         this.lastPause = time;
      }

      this.aimDrift = this.Ka5f91c42e3af3287ad(0.99F, this.aimDrift, this.K56fe609fa68ed7ceb3(-1.2F, 1.2F));
      float ws = (canHit ? 0.5F : 1.2F) * (1.0F + this.fatigue * 0.7F);
      this.wanderYaw = this.Ka5f91c42e3af3287ad(0.35F, this.wanderYaw, this.K56fe609fa68ed7ceb3(-0.8F, 0.8F) * ws);
      this.wanderPitch = this.Ka5f91c42e3af3287ad(0.35F, this.wanderPitch, this.K56fe609fa68ed7ceb3(-0.5F, 0.5F) * ws);
   }

   private float K7baaac83d27bcb73c9(float amp, long time, boolean yaw) {
      double phase = (double)(time / (long)(yaw ? 70 : 90));
      return (float)(Math.sin(phase) * (double)amp * 0.55 + Math.sin(phase * 2.3) * (double)amp * (double)0.25F + Math.sin(phase * 4.8) * (double)amp * 0.1);
   }

   private float Kcfa50ea736d75de05b(float delta, float maxSpeed) {
      if (Math.abs(delta) < 0.02F) {
         return 0.0F;
      } else {
         float sign = Math.signum(delta);
         float abs = Math.abs(delta);
         float clamped = Math.min(abs, maxSpeed);
         float t = clamped / Math.max(abs, 1.0F);
         return sign * clamped * (1.0F - (float)Math.pow((double)(1.0F - t), (double)1.85F));
      }
   }

   private float smooth(float val, LinkedList<Float> hist) {
      hist.addLast(val);

      while(hist.size() > 3) {
         hist.removeFirst();
      }

      if (hist.isEmpty()) {
         return val;
      } else {
         float s = 0.0F;
         float w = 0.0F;
         int i = 0;

         for(float v : hist) {
            float ww = (float)Math.pow((double)1.15F, (double)(i++));
            s += v * ww;
            w += ww;
         }

         return s / w;
      }
   }

   public Keb4a85ca52fb971c57 process(Keb4a85ca52fb971c57 currentRotation, Keb4a85ca52fb971c57 targetRotation, class_243 vec3d, class_1297 entity) {
      long time = System.currentTimeMillis();
      boolean canHit = entity != null;
      this.K62ca8a2699671b8a7e(targetRotation, time);
      this.K0833921394231e53dd(time, canHit);
      Keb4a85ca52fb971c57 predTarget = new Keb4a85ca52fb971c57(this.predictedYaw, this.predictedPitch);
      Keb4a85ca52fb971c57 delta = Kef2fce79615318605c.calculateDelta(currentRotation, predTarget);
      float yawD = delta.getYaw();
      float pitchD = delta.getPitch();
      float total = (float)Math.hypot((double)yawD, (double)pitchD);
      if (canHit) {
         if (total < 6.0F) {
            ++this.combo;
            this.miss = Math.max(0, this.miss - 1);
            this.lastHit = time;
         } else {
            ++this.miss;
         }
      }

      if ((this.miss <= 8 || time - this.lastHit <= 800L) && (!(this.Ke2438f51a633d0bdcc() < 0.008F) || canHit)) {
         if (this.blinking && this.Ke2438f51a633d0bdcc() < 0.5F) {
            return currentRotation;
         } else {
            float fatigueM = 1.0F - this.fatigue * 0.35F;
            float focusM = (float)this.focus / 100.0F;
            float missM = this.miss > 15 ? 0.65F : (this.miss > 8 ? 0.8F : (this.miss > 4 ? 0.9F : 1.0F));
            float reactionM = this.avgReaction / 50.0F;
            float yawSpeed = (Math.abs(yawD) > 32.0F ? 44.5F : 25.5F) * fatigueM * missM * focusM * reactionM;
            float pitchSpeed = (Math.abs(pitchD) > 14.0F ? 31.15F : 17.85F) * fatigueM * missM * focusM * reactionM;
            float yawStep = this.Kcfa50ea736d75de05b(yawD, yawSpeed);
            float pitchStep = this.Kcfa50ea736d75de05b(pitchD, pitchSpeed);
            float inacc = (float)(100 - this.focus) / 100.0F * 0.8F + this.fatigue * 0.5F;
            float targetOffY = this.K56fe609fa68ed7ceb3(-1.2F, 1.2F) * 0.97F * (1.0F - this.fatigue * 0.4F);
            float targetOffP = this.K56fe609fa68ed7ceb3(-0.6F, 0.6F) * 0.97F * (1.0F - this.fatigue * 0.4F);
            float jitterY = this.K7baaac83d27bcb73c9(1.5F + this.fatigue * 1.1F, time, true) + this.K56fe609fa68ed7ceb3(-inacc, inacc) + targetOffY + this.aimDrift + this.headNoise + this.tremorYaw * 0.3F + this.breath * 0.2F + this.microYaw;
            float jitterP = this.K7baaac83d27bcb73c9(0.7F + this.fatigue * 1.1F, time, false) + this.K56fe609fa68ed7ceb3(-inacc * 0.6F, inacc * 0.6F) + targetOffP + this.aimDrift * 0.5F + this.tremorPitch * 0.3F + this.breath * 0.15F + this.microPitch;
            yawStep = this.smooth(yawStep, this.yawHist);
            pitchStep = this.smooth(pitchStep, this.pitchHist);
            if (canHit && total < 10.0F && this.Ke2438f51a633d0bdcc() < 0.25F) {
               float over = 0.1F * (1.0F - Math.min(total / 10.0F, 1.0F));
               yawStep *= 1.0F + over;
               pitchStep *= 1.0F + over;
            }

            float mouseNoise = (float)Math.sin((double)time / (double)150.0F) * 0.6F * (Math.abs(yawD) + Math.abs(pitchD)) / 25.0F;
            yawStep += this.errorYaw + this.wanderYaw + mouseNoise;
            pitchStep += this.errorPitch + this.wanderPitch + mouseNoise * 0.7F;
            yawStep = this.Kd8fca971af3a8490c6(yawStep, -50.0F, 50.0F);
            pitchStep = this.Kd8fca971af3a8490c6(pitchStep, -30.0F, 30.0F);
            float finalYaw = currentRotation.getYaw() + yawStep + jitterY;
            float finalPitch = this.Kd8fca971af3a8490c6(currentRotation.getPitch() + pitchStep + jitterP, -90.0F, 90.0F);
            float yawDiff = finalYaw - currentRotation.getYaw();
            if (Math.abs(yawDiff) > 30.0F) {
               finalYaw = currentRotation.getYaw() + Math.signum(yawDiff) * 30.0F;
            }

            finalYaw = this.Kc62fda293a9d595d6c(finalYaw);
            return (new Keb4a85ca52fb971c57(finalYaw, finalPitch)).adjustSensitivity();
         }
      } else {
         if (this.miss > 8) {
            this.miss = 0;
         }

         return currentRotation;
      }
   }
}
