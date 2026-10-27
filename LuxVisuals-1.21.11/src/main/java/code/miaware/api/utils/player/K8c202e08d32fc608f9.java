package code.miaware.api.utils.player;

import code.miaware.api.system.interfaces.K05e8563c6cb46099e8;
import lombok.Generated;
import net.minecraft.class_10185;
import net.minecraft.class_744;

public final class K8c202e08d32fc608f9 {
   private boolean forwards;
   private boolean backwards;
   private boolean left;
   private boolean right;
   public static final K8c202e08d32fc608f9 NONE = new K8c202e08d32fc608f9(false, false, false, false);
   public static final K8c202e08d32fc608f9 FORWARDS = new K8c202e08d32fc608f9(true, false, false, false);
   public static final K8c202e08d32fc608f9 BACKWARDS = new K8c202e08d32fc608f9(false, true, false, false);
   public static final K8c202e08d32fc608f9 LEFT = new K8c202e08d32fc608f9(false, false, true, false);
   public static final K8c202e08d32fc608f9 RIGHT = new K8c202e08d32fc608f9(false, false, false, true);

   public K8c202e08d32fc608f9(boolean forwards, boolean backwards, boolean left, boolean right) {
      this.forwards = forwards;
      this.backwards = backwards;
      this.left = left;
      this.right = right;
   }

   public K8c202e08d32fc608f9(class_744 input) {
      this(((K05e8563c6cb46099e8)input).evelina$getUntransformed());
   }

   public K8c202e08d32fc608f9(class_10185 input) {
      this(input.comp_3159(), input.comp_3160(), input.comp_3161(), input.comp_3162());
   }

   public K8c202e08d32fc608f9(float movementForward, float movementSideways) {
      this(movementForward > 0.0F, movementForward < 0.0F, movementSideways > 0.0F, movementSideways < 0.0F);
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof K8c202e08d32fc608f9)) {
         return false;
      } else {
         K8c202e08d32fc608f9 that = (K8c202e08d32fc608f9)other;
         return this.forwards == that.forwards && this.backwards == that.backwards && this.left == that.left && this.right == that.right;
      }
   }

   public int hashCode() {
      int result = Boolean.hashCode(this.forwards);
      result = 31 * result + Boolean.hashCode(this.backwards);
      result = 31 * result + Boolean.hashCode(this.left);
      result = 31 * result + Boolean.hashCode(this.right);
      return result;
   }

   public boolean isMoving() {
      return this.forwards || this.backwards || this.left || this.right;
   }

   @Generated
   public boolean isForwards() {
      return this.forwards;
   }

   @Generated
   public boolean isBackwards() {
      return this.backwards;
   }

   @Generated
   public boolean isLeft() {
      return this.left;
   }

   @Generated
   public boolean isRight() {
      return this.right;
   }

   @Generated
   public void setForwards(boolean forwards) {
      this.forwards = forwards;
   }

   @Generated
   public void setBackwards(boolean backwards) {
      this.backwards = backwards;
   }

   @Generated
   public void setLeft(boolean left) {
      this.left = left;
   }

   @Generated
   public void setRight(boolean right) {
      this.right = right;
   }
}
