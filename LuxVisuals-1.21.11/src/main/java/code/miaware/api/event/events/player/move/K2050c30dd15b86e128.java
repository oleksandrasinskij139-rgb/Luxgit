package code.miaware.api.event.events.player.move;

import lombok.Generated;
import net.minecraft.class_243;

public class K2050c30dd15b86e128 {
   private class_243 movementInput;
   private float speed;
   private float yaw;
   private class_243 velocity;

   @Generated
   public class_243 getMovementInput() {
      return this.movementInput;
   }

   @Generated
   public float getSpeed() {
      return this.speed;
   }

   @Generated
   public float getYaw() {
      return this.yaw;
   }

   @Generated
   public class_243 getVelocity() {
      return this.velocity;
   }

   @Generated
   public K2050c30dd15b86e128(class_243 movementInput, float speed, float yaw, class_243 velocity) {
      this.movementInput = movementInput;
      this.speed = speed;
      this.yaw = yaw;
      this.velocity = velocity;
   }

   @Generated
   public void setMovementInput(class_243 movementInput) {
      this.movementInput = movementInput;
   }

   @Generated
   public void setVelocity(class_243 velocity) {
      this.velocity = velocity;
   }
}
