package code.miaware.api.module.setting;

import java.util.function.Supplier;
import lombok.Generated;

public class K917aeb5cdb0f7aca26 extends Kdced9e23f1e229ba9d<String> {
   private int maxLength = 32;
   private String placeholder = "";

   public K917aeb5cdb0f7aca26(String name) {
      super(name);
   }

   public K917aeb5cdb0f7aca26 value(String value) {
      this.setValue(value);
      return this;
   }

   public void setValue(String value) {
      String normalized = value == null ? "" : value.trim();
      if (normalized.length() > this.maxLength) {
         normalized = normalized.substring(0, this.maxLength);
      }

      if (!this.sameValue(normalized)) {
         super.setValue(normalized);
      }
   }

   public K917aeb5cdb0f7aca26 setVisible(Supplier<Boolean> condition) {
      return (K917aeb5cdb0f7aca26)super.setVisible(condition);
   }

   public K917aeb5cdb0f7aca26 onAction(Runnable action) {
      return (K917aeb5cdb0f7aca26)super.onAction(action);
   }

   public K917aeb5cdb0f7aca26 maxLength(int maxLength) {
      this.maxLength = Math.max(1, maxLength);
      return this;
   }

   public K917aeb5cdb0f7aca26 placeholder(String placeholder) {
      this.placeholder = placeholder == null ? "" : placeholder;
      return this;
   }

   @Generated
   public int getMaxLength() {
      return this.maxLength;
   }

   @Generated
   public String getPlaceholder() {
      return this.placeholder;
   }
}
