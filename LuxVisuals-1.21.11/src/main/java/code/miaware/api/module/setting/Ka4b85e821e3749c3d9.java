package code.miaware.api.module.setting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Ka4b85e821e3749c3d9 extends Kdced9e23f1e229ba9d<List<K00fef91b3d4d1b3ecc>> {
   public Ka4b85e821e3749c3d9(String name) {
      super(name);
      this.value = new ArrayList();
   }

   public Ka4b85e821e3749c3d9 value(List<K00fef91b3d4d1b3ecc> value) {
      this.setValue(value);
      return this;
   }

   public Ka4b85e821e3749c3d9 value(K00fef91b3d4d1b3ecc... value) {
      this.setValue(new ArrayList(Arrays.asList(value)));
      return this;
   }

   public void setValue(List<K00fef91b3d4d1b3ecc> value) {
      if (!this.sameValue(value)) {
         super.setValue(value);
      }
   }

   public Ka4b85e821e3749c3d9 onAction(Runnable action) {
      return (Ka4b85e821e3749c3d9)super.onAction(action);
   }

   public Ka4b85e821e3749c3d9 addValues(K00fef91b3d4d1b3ecc... value) {
      ((List)this.value).addAll(Arrays.asList(value));
      return this;
   }

   public boolean isEnabled(String name) {
      K00fef91b3d4d1b3ecc setting = this.K80147e91e7e0c847a7(name);
      return setting != null && Boolean.TRUE.equals(setting.getValue());
   }

   public boolean toggle(String name) {
      K00fef91b3d4d1b3ecc setting = this.K80147e91e7e0c847a7(name);
      if (setting == null) {
         return false;
      } else {
         setting.value(!(Boolean)setting.getValue());
         return true;
      }
   }

   public List<String> getAllNames() {
      return (List)((List)this.value).stream().map(Kdced9e23f1e229ba9d::getName).collect(Collectors.toList());
   }

   public List<String> getList() {
      return (List)((List)this.value).stream().filter(Kdced9e23f1e229ba9d::getValue).map(Kdced9e23f1e229ba9d::getName).collect(Collectors.toList());
   }

   private K00fef91b3d4d1b3ecc K80147e91e7e0c847a7(String name) {
      for(int i = 0; i < ((List)this.value).size(); ++i) {
         K00fef91b3d4d1b3ecc setting = (K00fef91b3d4d1b3ecc)((List)this.value).get(i);
         if (setting.getName().equalsIgnoreCase(name)) {
            return setting;
         }
      }

      return null;
   }

   public Ka4b85e821e3749c3d9 setVisible(Supplier<Boolean> condition) {
      return (Ka4b85e821e3749c3d9)super.setVisible(condition);
   }
}
