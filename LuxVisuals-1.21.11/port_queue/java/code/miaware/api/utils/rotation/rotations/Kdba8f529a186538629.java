package code.miaware.api.utils.rotation.rotations;

import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.api.utils.rotation.Kef2fce79615318605c;
import code.miaware.api.utils.rotation.manager.Kbdd81cc863fb7d1df0;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public abstract class Kdba8f529a186538629 extends Kbdd81cc863fb7d1df0 {
   private long lastTriggerTime = -1L;
   private long lastOffsetUpdateTime;
   private float yawOffset;
   private float pitchOffset;
   private float frozenPitch = Float.NaN;

   protected Kdba8f529a186538629(String name) {
      super(name);
   }

   public void triggerSnap() {
      this.lastTriggerTime = System.currentTimeMillis();
      this.frozenPitch = Float.NaN;
   }

   public boolean isSnapWindowActive() {
      long triggerTime = this.lastTriggerTime;
      return triggerTime > 0L && System.currentTimeMillis() - triggerTime < this.getProfile().windowMs();
   }

   public Keb4a85ca52fb971c57 process(Keb4a85ca52fb971c57 currentRotation, Keb4a85ca52fb971c57 targetRotation, class_243 vec3d, class_1297 entity) {
      if (mc.field_1724 != null && entity != null) {
         K2247aa0d940589341e profile = this.getProfile();
         long now = System.currentTimeMillis();
         long elapsed = this.lastTriggerTime > 0L ? now - this.lastTriggerTime : Long.MAX_VALUE;
         boolean activeWindow = elapsed < profile.windowMs();
         boolean detached = activeWindow && elapsed >= profile.detachStartMs() && elapsed < profile.reattachMs();
         boolean aggressiveSnap = activeWindow && !detached;
         boolean actionReady = false;
         this.K504e427d1cbb49df2d(now, aggressiveSnap);
         Keb4a85ca52fb971c57 clampedTarget = this.K806b60f5e0ecfe0d23(new Keb4a85ca52fb971c57(targetRotation.getYaw() + this.yawOffset, targetRotation.getPitch() + this.pitchOffset), entity);
         if (aggressiveSnap && Float.isNaN(this.frozenPitch)) {
            this.frozenPitch = currentRotation.getPitch();
         }

         float desiredYaw = detached ? mc.field_1724.method_36454() : clampedTarget.getYaw();
         float desiredPitch = detached ? this.K8eec853d08f5cf6c7a(currentRotation) : clampedTarget.getPitch();
         Keb4a85ca52fb971c57 desiredRotation = new Keb4a85ca52fb971c57(desiredYaw, desiredPitch);
         Keb4a85ca52fb971c57 delta = Kef2fce79615318605c.calculateDelta(currentRotation, desiredRotation);
         float yawDelta = delta.getYaw();
         float pitchDelta = delta.getPitch();
         double distance = Math.hypot((double)yawDelta, (double)pitchDelta);
         if (distance < 1.0E-4) {
            return new Keb4a85ca52fb971c57(currentRotation.getYaw(), class_3532.method_15363(currentRotation.getPitch(), -90.0F, 90.0F));
         } else {
            float speed = Math.min(130.0F, this.K6085afde3b732e2bb1(profile, distance, aggressiveSnap, actionReady, elapsed));
            float step = Math.min((float)distance, speed);
            if (aggressiveSnap && (actionReady || elapsed < 70L)) {
               step = Math.min((float)distance, Math.min(130.0F, Math.max(step, 24.0F + speed * 1.15F)));
            }

            float targetYaw = currentRotation.getYaw() + (float)((double)yawDelta / distance) * step;
            float targetPitch = currentRotation.getPitch() + (float)((double)pitchDelta / distance) * step;
            float lerp = Ka0484a23398fb5f9ac.randomInRange(0.82F, 0.89F);
            float nextYaw = class_3532.method_16439(lerp, currentRotation.getYaw(), targetYaw);
            float nextPitch = class_3532.method_16439(lerp, currentRotation.getPitch(), targetPitch);
            float noiseScale = aggressiveSnap ? 1.0F : (actionReady ? 0.55F : 0.28F);
            nextYaw += (float)Math.cos((double)now / (double)55.0F) * profile.yawNoise() * noiseScale + this.extraYawNoise(now, aggressiveSnap, distance);
            nextPitch += (float)Math.sin((double)now / (double)80.0F) * profile.pitchNoise() * noiseScale + this.extraPitchNoise(now, aggressiveSnap, distance);
            if (detached && !Float.isNaN(this.frozenPitch)) {
               nextPitch = class_3532.method_16439(0.35F, nextPitch, this.K8eec853d08f5cf6c7a(currentRotation));
            }

            Keb4a85ca52fb971c57 result = new Keb4a85ca52fb971c57(class_3532.method_15363(nextYaw - currentRotation.getYaw(), -130.0F, 130.0F) + currentRotation.getYaw(), class_3532.method_15363(nextPitch, -90.0F, 90.0F));
            return detached ? result : this.K806b60f5e0ecfe0d23(result, entity);
         }
      } else {
         return targetRotation;
      }
   }

   private void K504e427d1cbb49df2d(long now, boolean aggressiveSnap) {
      long interval = aggressiveSnap ? 25L : 75L;
      if (now - this.lastOffsetUpdateTime >= interval) {
         this.lastOffsetUpdateTime = now;
         float yawRange = aggressiveSnap ? 1.2F : 0.55F;
         float pitchRange = aggressiveSnap ? 0.4F : 0.18F;
         this.yawOffset = Ka0484a23398fb5f9ac.randomInRange(-yawRange, yawRange);
         this.pitchOffset = Ka0484a23398fb5f9ac.randomInRange(-pitchRange, pitchRange);
      }
   }

   private float K8eec853d08f5cf6c7a(Keb4a85ca52fb971c57 currentRotation) {
      if (mc.field_1724 == null) {
         return currentRotation.getPitch();
      } else {
         float base = Float.isNaN(this.frozenPitch) ? currentRotation.getPitch() : this.frozenPitch;
         return class_3532.method_16439(0.32F, base, mc.field_1724.method_36455());
      }
   }

   protected float extraYawNoise(long now, boolean aggressiveSnap, double distance) {
      return 0.0F;
   }

   protected float extraPitchNoise(long now, boolean aggressiveSnap, double distance) {
      return 0.0F;
   }

   protected abstract K2247aa0d940589341e getProfile();

   private float K6085afde3b732e2bb1(K2247aa0d940589341e profile, double distance, boolean aggressiveSnap, boolean actionReady, long elapsed) {
      float baseSpeed;
      if (aggressiveSnap) {
         baseSpeed = Ka0484a23398fb5f9ac.randomInRange(profile.fastMinSpeed(), profile.fastMaxSpeed());
      } else if (actionReady) {
         baseSpeed = Ka0484a23398fb5f9ac.randomInRange(profile.readyMinSpeed(), profile.readyMaxSpeed());
      } else {
         baseSpeed = Ka0484a23398fb5f9ac.randomInRange(profile.slowMinSpeed(), profile.slowMaxSpeed());
      }

      float proximityBoost = 0.72F + 0.48F * (float)Math.exp(-distance * (double)profile.proximityExponent());
      float snapBoost = aggressiveSnap ? 1.0F + 0.22F * (1.0F - class_3532.method_15363((float)elapsed / (float)profile.detachStartMs(), 0.0F, 1.0F)) : 1.0F;
      return baseSpeed * proximityBoost * snapBoost;
   }

   private Keb4a85ca52fb971c57 K806b60f5e0ecfe0d23(Keb4a85ca52fb971c57 rotation, class_1297 entity) {
      if (mc.field_1724 == null) {
         return rotation;
      } else {
         class_238 box = entity.method_5829().method_1002(0.02, 0.02, 0.02);
         class_243 eyePos = mc.field_1724.method_33571();
         double aimX = class_3532.method_15350(eyePos.field_1352, box.field_1323, box.field_1320);
         double aimZ = class_3532.method_15350(eyePos.field_1350, box.field_1321, box.field_1324);
         Keb4a85ca52fb971c57 lower = Kef2fce79615318605c.rotationAt(new class_243(aimX, box.field_1322 + 0.04, aimZ));
         Keb4a85ca52fb971c57 upper = Kef2fce79615318605c.rotationAt(new class_243(aimX, box.field_1325 - 0.04, aimZ));
         float minPitch = Math.min(lower.getPitch(), upper.getPitch());
         float maxPitch = Math.max(lower.getPitch(), upper.getPitch());
         return new Keb4a85ca52fb971c57(rotation.getYaw(), class_3532.method_15363(rotation.getPitch(), minPitch, maxPitch));
      }
   }
}
