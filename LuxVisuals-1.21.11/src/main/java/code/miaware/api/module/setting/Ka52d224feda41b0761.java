package code.miaware.api.module.setting;

import java.awt.Color;
import java.util.function.Supplier;

public class Ka52d224feda41b0761 extends Kdced9e23f1e229ba9d<Color> {
   public Ka52d224feda41b0761(String name) {
      super(name);
   }

   public Ka52d224feda41b0761 value(Color value) {
      this.setValue(value);
      return this;
   }

   public void setValue(Color value) {
      if (!this.sameValue(value)) {
         super.setValue(value);
      }
   }

   public Ka52d224feda41b0761 setVisible(Supplier<Boolean> condition) {
      return (Ka52d224feda41b0761)super.setVisible(condition);
   }

   public Ka52d224feda41b0761 onAction(Runnable action) {
      return (Ka52d224feda41b0761)super.onAction(action);
   }
}
