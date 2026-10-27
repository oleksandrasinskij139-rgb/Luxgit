package code.miaware.api.utils.rotation.rotations;

import code.miaware.api.utils.animation.wrap.infinity.Kcd44fb0f71a556c8e0;
import code.miaware.api.utils.combat.Kfb99cba3d2d6857c00;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.api.utils.player.K131e64425767c5c203;
import code.miaware.api.utils.rotation.K970216267e60d1d8b5;
import code.miaware.api.utils.rotation.Kef2fce79615318605c;
import code.miaware.api.utils.rotation.manager.Kbdd81cc863fb7d1df0;
import code.miaware.api.utils.rotation.manager.Keb4a85ca52fb971c57;
import net.minecraft.class_1297;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_3966;

public class K61006a43f5c18a1cfa extends Kbdd81cc863fb7d1df0 {
   private final Kfb99cba3d2d6857c00 clickScheduler;
   private final Kcd44fb0f71a556c8e0 interpolation = new Kcd44fb0f71a556c8e0();
   private float slowPitchTicks = 0.0F;
   private float slowYawTicks = 0.0F;
   private float someTicks = 0.0F;
   private float lastYawDelta;
   private float lastPitchDelta;

   public K61006a43f5c18a1cfa(Kfb99cba3d2d6857c00 clickScheduler) {
      super("Spooky Time");
      this.clickScheduler = clickScheduler;
   }

   public Keb4a85ca52fb971c57 process(Keb4a85ca52fb971c57 currentRotation, Keb4a85ca52fb971c57 targetRotation, class_243 vec3d, class_1297 entity) {
      Keb4a85ca52fb971c57 delta = Kef2fce79615318605c.calculateDelta(currentRotation, targetRotation);
      float yawDelta = delta.getYaw();
      float pitchDelta = delta.getPitch();
      float auraDistance = 3.0F;
      float distanceToTarget = entity != null ? (float)mc.field_1724.method_19538().method_1022(entity.method_19538()) : 1.0F;
      float distanceFactor = class_3532.method_15363(0.3F + 0.7F * (distanceToTarget / auraDistance), 0.0F, 1.0F);
      boolean hasTarget = entity != null;
      class_3966 hitResult = K970216267e60d1d8b5.raytraceEntity((double)auraDistance, currentRotation, false);
      boolean hasTrace = hasTarget && hitResult != null && hitResult.method_17782() == entity;
      float raytrace = hasTrace ? 0.4F : 1.0F;
      boolean check = hasTarget && K131e64425767c5c203.hasCollisionWith(entity) && (stalin(entity) || K131e64425767c5c203.getBlock(0.0F, 2.0F, 0.0F) != class_2246.field_10124 && K131e64425767c5c203.getBlock(0.0F, -1.0F, 0.0F) != class_2246.field_10124 && K131e64425767c5c203.getBlock(0.0F, 2.0F, 0.0F) != class_2246.field_10382 && K131e64425767c5c203.getBlock(0.0F, -1.0F, 0.0F) != class_2246.field_10382);
      if (check) {
         yawDelta /= 30.0F;
      }

      float random = Ka0484a23398fb5f9ac.randomInRange(0.5F, 0.7F);
      float yawSpeed = Ka0484a23398fb5f9ac.randomInRange(25.0F, 31.0F);
      float pitchSpeed = Ka0484a23398fb5f9ac.randomInRange(6.0F, 7.5F);
      if (hasTrace) {
         this.slowYawTicks = Ka0484a23398fb5f9ac.interpolate(this.slowYawTicks, 0.7F, 0.6F);
         this.slowPitchTicks = Ka0484a23398fb5f9ac.interpolate(this.slowPitchTicks, 0.5F, 0.5F);
      } else {
         this.slowYawTicks = Ka0484a23398fb5f9ac.interpolate(this.slowYawTicks, 1.0F, 0.9F);
         this.slowPitchTicks = Ka0484a23398fb5f9ac.interpolate(this.slowPitchTicks, 1.0F, 0.2F);
      }

      yawSpeed *= this.slowYawTicks;
      pitchSpeed *= this.slowPitchTicks;
      yawDelta += (float)(Math.cos((double)System.currentTimeMillis() / (double)50.0F) * (double)(3.0F * distanceFactor));
      pitchDelta += (float)(Math.sin((double)System.currentTimeMillis() / (double)60.0F) * (double)(9.0F * distanceFactor));
      return new Keb4a85ca52fb971c57(currentRotation.getYaw() + class_3532.method_15363(yawDelta, -yawSpeed, yawSpeed) * random, currentRotation.getPitch() + class_3532.method_15363(pitchDelta, -pitchSpeed, pitchSpeed) * random / yawSpeed * 14.0F * raytrace);
   }

   public static boolean stalin(class_1297 target) {
      class_243 pos = target.method_19538();
      class_238 hitbox = target.method_5829();
      float off = 0.05F;
      return !isAir(hitbox.field_1323 - (double)off, pos.field_1351, hitbox.field_1321 - (double)off) || !isAir(hitbox.field_1320 + (double)off, pos.field_1351, hitbox.field_1321 - (double)off) || !isAir(hitbox.field_1323 - (double)off, pos.field_1351, hitbox.field_1324 + (double)off) || !isAir(hitbox.field_1320 + (double)off, pos.field_1351, hitbox.field_1324 + (double)off);
   }

   public static boolean isAir(double x, double y, double z) {
      return mc.field_1687.method_8320(new class_2338(class_3532.method_15357(x), class_3532.method_15357(y), class_3532.method_15357(z))).method_26204() == class_2246.field_10124;
   }
}
