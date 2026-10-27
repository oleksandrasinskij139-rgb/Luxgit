package code.miaware.api.module.setting;

import java.util.function.Supplier;

public class K00fef91b3d4d1b3ecc extends Kdced9e23f1e229ba9d<Boolean> {
   public K00fef91b3d4d1b3ecc(String name) {
      super(name);
   }

   public K00fef91b3d4d1b3ecc value(Boolean value) {
      this.setValue(value);
      return this;
   }

   public void setValue(Boolean value) {
      if (!this.sameValue(value)) {
         super.setValue(value);
      }
   }

   public K00fef91b3d4d1b3ecc setVisible(Supplier<Boolean> condition) {
      return (K00fef91b3d4d1b3ecc)super.setVisible(condition);
   }

   public K00fef91b3d4d1b3ecc onAction(Runnable action) {
      return (K00fef91b3d4d1b3ecc)super.onAction(action);
   }

   public void toggle() {
      this.setValue(!(Boolean)this.getValue());
   }
}
