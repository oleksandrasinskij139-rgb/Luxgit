package code.miaware.client.features.modules.render;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import java.util.UUID;
import net.minecraft.class_1007;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_591;
import net.minecraft.class_742;
import net.minecraft.class_897;

final class K422ce647b761c71a03 {
   private final UUID ownerId;
   private final long createdAt;
   private final class_243 position;
   private final class_238 box;
   private final float yaw;
   private final float pitch;
   private final float headYaw;
   private final float leftArmPitch;
   private final float leftArmYaw;
   private final float leftArmRoll;
   private final float rightArmPitch;
   private final float rightArmYaw;
   private final float rightArmRoll;
   private final float leftLegPitch;
   private final float leftLegYaw;
   private final float leftLegRoll;
   private final float rightLegPitch;
   private final float rightLegYaw;
   private final float rightLegRoll;

   private K422ce647b761c71a03(UUID ownerId, long createdAt, class_243 position, class_238 box, float yaw, float pitch, float headYaw, float leftArmPitch, float leftArmYaw, float leftArmRoll, float rightArmPitch, float rightArmYaw, float rightArmRoll, float leftLegPitch, float leftLegYaw, float leftLegRoll, float rightLegPitch, float rightLegYaw, float rightLegRoll) {
      this.ownerId = ownerId;
      this.createdAt = createdAt;
      this.position = position;
      this.box = box;
      this.yaw = yaw;
      this.pitch = pitch;
      this.headYaw = headYaw;
      this.leftArmPitch = leftArmPitch;
      this.leftArmYaw = leftArmYaw;
      this.leftArmRoll = leftArmRoll;
      this.rightArmPitch = rightArmPitch;
      this.rightArmYaw = rightArmYaw;
      this.rightArmRoll = rightArmRoll;
      this.leftLegPitch = leftLegPitch;
      this.leftLegYaw = leftLegYaw;
      this.leftLegRoll = leftLegRoll;
      this.rightLegPitch = rightLegPitch;
      this.rightLegYaw = rightLegYaw;
      this.rightLegRoll = rightLegRoll;
   }

   private static K422ce647b761c71a03 K27d608350e39941171(class_742 source, class_243 position, long createdAt) {
      class_238 box = source.method_5829().method_997(position.method_1020(source.method_19538()));
      float leftArmPitch = 0.0F;
      float leftArmYaw = 0.0F;
      float leftArmRoll = 0.0F;
      float rightArmPitch = 0.0F;
      float rightArmYaw = 0.0F;
      float rightArmRoll = 0.0F;
      float leftLegPitch = 0.0F;
      float leftLegYaw = 0.0F;
      float leftLegRoll = 0.0F;
      float rightLegPitch = 0.0F;
      float rightLegYaw = 0.0F;
      float rightLegRoll = 0.0F;

      try {
         class_897<? super class_742, ?> renderer = K4600bef438dafc1a9c.mc.method_1561().method_3953(source);
         if (renderer instanceof class_1007 playerRenderer) {
            class_591 model = (class_591)playerRenderer.method_4038();
            leftArmPitch = model.field_27433.field_3654;
            leftArmYaw = model.field_27433.field_3675;
            leftArmRoll = model.field_27433.field_3674;
            rightArmPitch = model.field_3401.field_3654;
            rightArmYaw = model.field_3401.field_3675;
            rightArmRoll = model.field_3401.field_3674;
            leftLegPitch = model.field_3397.field_3654;
            leftLegYaw = model.field_3397.field_3675;
            leftLegRoll = model.field_3397.field_3674;
            rightLegPitch = model.field_3392.field_3654;
            rightLegYaw = model.field_3392.field_3675;
            rightLegRoll = model.field_3392.field_3674;
         }
      } catch (Exception var20) {
      }

      return new K422ce647b761c71a03(source.method_5667(), createdAt, position, box, source.method_36454(), source.method_36455(), source.method_5791(), leftArmPitch, leftArmYaw, leftArmRoll, rightArmPitch, rightArmYaw, rightArmRoll, leftLegPitch, leftLegYaw, leftLegRoll, rightLegPitch, rightLegYaw, rightLegRoll);
   }

   private boolean K27dba2e84846dc476f(long now) {
      return now - this.createdAt > 1500L;
   }
}
