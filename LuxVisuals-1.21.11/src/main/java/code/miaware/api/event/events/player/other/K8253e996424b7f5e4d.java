package code.miaware.api.event.events.player.other;

import code.miaware.api.utils.player.K8c202e08d32fc608f9;
import lombok.Generated;
import net.minecraft.class_10185;

public class K8253e996424b7f5e4d {
   private final class_10185 playerInput;
   private boolean jump;
   private boolean sneak;
   private K8c202e08d32fc608f9 directionalInput;

   @Generated
   public class_10185 getPlayerInput() {
      return this.playerInput;
   }

   @Generated
   public boolean isJump() {
      return this.jump;
   }

   @Generated
   public boolean isSneak() {
      return this.sneak;
   }

   @Generated
   public K8c202e08d32fc608f9 getDirectionalInput() {
      return this.directionalInput;
   }

   @Generated
   public K8253e996424b7f5e4d(class_10185 playerInput, boolean jump, boolean sneak, K8c202e08d32fc608f9 directionalInput) {
      this.playerInput = playerInput;
      this.jump = jump;
      this.sneak = sneak;
      this.directionalInput = directionalInput;
   }

   @Generated
   public void setJump(boolean jump) {
      this.jump = jump;
   }

   @Generated
   public void setSneak(boolean sneak) {
      this.sneak = sneak;
   }
}
