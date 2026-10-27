package code.miaware.api.module.setting;

import java.util.function.Supplier;

public class K1d3d0b4020f933282f extends Kdced9e23f1e229ba9d<Integer> {
   public K1d3d0b4020f933282f(String name) {
      super(name);
      this.value = -999;
   }

   public K1d3d0b4020f933282f value(Integer value) {
      this.setValue(value);
      return this;
   }

   public void setValue(Integer value) {
      if (!this.sameValue(value)) {
         super.setValue(value);
      }
   }

   public K1d3d0b4020f933282f setVisible(Supplier<Boolean> condition) {
      return (K1d3d0b4020f933282f)super.setVisible(condition);
   }

   public K1d3d0b4020f933282f onAction(Runnable action) {
      return (K1d3d0b4020f933282f)super.onAction(action);
   }
}
