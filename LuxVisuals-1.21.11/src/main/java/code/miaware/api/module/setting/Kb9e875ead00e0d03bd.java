package code.miaware.api.module.setting;

import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import java.util.function.Supplier;
import lombok.Generated;

public class Kb9e875ead00e0d03bd extends Kdced9e23f1e229ba9d<Float> {
   private float min = Float.MIN_VALUE;
   private float max = Float.MAX_VALUE;
   private float step = 1.0F;

   public Kb9e875ead00e0d03bd(String name) {
      super(name);
   }

   public Kb9e875ead00e0d03bd value(Float value) {
      this.setValue(value);
      return this;
   }

   public Float getValue() {
      return this.value == null ? null : Ka0484a23398fb5f9ac.round((Float)this.value, this.step);
   }

   public void setValue(Float value) {
      float clamped = Math.clamp(value, this.min, this.max);
      float normalized = Ka0484a23398fb5f9ac.round(clamped, this.step);
      if (this.value == null || Float.compare((Float)this.value, normalized) != 0) {
         super.setValue(normalized);
      }
   }

   public Kb9e875ead00e0d03bd setVisible(Supplier<Boolean> condition) {
      return (Kb9e875ead00e0d03bd)super.setVisible(condition);
   }

   public Kb9e875ead00e0d03bd onAction(Runnable action) {
      return (Kb9e875ead00e0d03bd)super.onAction(action);
   }

   public Kb9e875ead00e0d03bd range(float min, float max) {
      this.min = min;
      this.max = max;
      return this;
   }

   public Kb9e875ead00e0d03bd step(float step) {
      this.step = step;
      return this;
   }

   @Generated
   public float getMin() {
      return this.min;
   }

   @Generated
   public float getMax() {
      return this.max;
   }

   @Generated
   public float getStep() {
      return this.step;
   }
}
