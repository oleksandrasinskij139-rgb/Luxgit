package code.miaware.api.module.setting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import lombok.Generated;

public class Kbd66519397e9a89967 extends Kdced9e23f1e229ba9d<String> {
   private final List<String> modes = new ArrayList();

   public Kbd66519397e9a89967(String name) {
      super(name);
   }

   public Kbd66519397e9a89967 values(String... values) {
      this.modes.addAll(Arrays.asList(values));
      if (this.value == null && !this.modes.isEmpty()) {
         this.value = (String)this.modes.getFirst();
      }

      return this;
   }

   public Kbd66519397e9a89967 values(Enum<?>... enums) {
      this.modes.addAll(Arrays.stream(enums).map((e) -> e instanceof Ke298bfdac4e34a00d9 ? ((Ke298bfdac4e34a00d9)e).getName() : e.name()).toList());
      if (this.value == null && !this.modes.isEmpty()) {
         this.value = (String)this.modes.getFirst();
      }

      return this;
   }

   public Kbd66519397e9a89967 value(Enum<?> e) {
      return this.value(e != null ? (e instanceof Ke298bfdac4e34a00d9 ? ((Ke298bfdac4e34a00d9)e).getName() : e.name()) : null);
   }

   public boolean is(Enum<?> e) {
      if (e == null) {
         return false;
      } else {
         String enumStr = e instanceof Ke298bfdac4e34a00d9 ? ((Ke298bfdac4e34a00d9)e).getName() : e.name();
         return ((String)this.value).equals(enumStr);
      }
   }

   public Kbd66519397e9a89967 value(String value) {
      this.setValue(value);
      return this;
   }

   public void setValue(String value) {
      if (!this.sameValue(value)) {
         super.setValue(value);
      }
   }

   public Kbd66519397e9a89967 setVisible(Supplier<Boolean> condition) {
      return (Kbd66519397e9a89967)super.setVisible(condition);
   }

   public Kbd66519397e9a89967 onAction(Runnable action) {
      return (Kbd66519397e9a89967)super.onAction(action);
   }

   public boolean is(String value) {
      return ((String)this.value).equals(value);
   }

   @Generated
   public List<String> getModes() {
      return this.modes;
   }
}
