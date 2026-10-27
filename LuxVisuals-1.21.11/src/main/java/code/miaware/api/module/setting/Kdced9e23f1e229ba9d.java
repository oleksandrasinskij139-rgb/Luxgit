package code.miaware.api.module.setting;

import code.miaware.api.system.configs.Kd4542f1791364048a9;
import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import java.util.function.Supplier;
import lombok.Generated;

public abstract class Kdced9e23f1e229ba9d<T> {
   protected String name;
   protected T value;
   protected Supplier<Boolean> visibilityCondition = () -> true;
   protected Runnable action;

   public Kdced9e23f1e229ba9d(String name) {
      this.name = name;
   }

   public Kdced9e23f1e229ba9d<T> setVisible(Supplier<Boolean> condition) {
      this.visibilityCondition = condition;
      return this;
   }

   public void runAction() {
      if (this.action != null) {
         this.action.run();
      }

   }

   protected boolean sameValue(T newValue) {
      if (this.value != null && newValue != null) {
         return this.value == newValue;
      } else {
         return false;
      }
   }

   public Kdced9e23f1e229ba9d<T> onAction(Runnable action) {
      this.action = action;
      return this;
   }

   public boolean isVisible() {
      return (Boolean)this.visibilityCondition.get();
   }

   public String getDisplayName() {
      return Ka35e7d6b31db9984f1.getInstance().translate(this.name);
   }

   public void setValue(T value) {
      if (!this.sameValue(value)) {
         this.value = value;
         this.runAction();
         if (!Kd4542f1791364048a9.getInstance().isLoading()) {
            Kd4542f1791364048a9.getInstance().save("autoConfig");
         }
      }

   }

   public abstract Kdced9e23f1e229ba9d<T> value(T var1);

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public T getValue() {
      return this.value;
   }

   @Generated
   public Supplier<Boolean> getVisibilityCondition() {
      return this.visibilityCondition;
   }

   @Generated
   public Runnable getAction() {
      return this.action;
   }

   @Generated
   public void setName(String name) {
      this.name = name;
   }

   @Generated
   public void setVisibilityCondition(Supplier<Boolean> visibilityCondition) {
      this.visibilityCondition = visibilityCondition;
   }

   @Generated
   public void setAction(Runnable action) {
      this.action = action;
   }
}
