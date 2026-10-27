package code.miaware.api.system.configs;

import lombok.Generated;

public final class K8855472f0415e5af4f {
   private final String name;
   private final String message;
   private final int key;

   @Generated
   public K8855472f0415e5af4f(String name, String message, int key) {
      this.name = name;
      this.message = message;
      this.key = key;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public String getMessage() {
      return this.message;
   }

   @Generated
   public int getKey() {
      return this.key;
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof K8855472f0415e5af4f)) {
         return false;
      } else {
         K8855472f0415e5af4f other = (K8855472f0415e5af4f)o;
         if (this.getKey() != other.getKey()) {
            return false;
         } else {
            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$message = this.getMessage();
            Object other$message = other.getMessage();
            if (this$message == null) {
               if (other$message != null) {
                  return false;
               }
            } else if (!this$message.equals(other$message)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getKey();
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $message = this.getMessage();
      result = result * 59 + ($message == null ? 43 : $message.hashCode());
      return result;
   }

   @Generated
   public String toString() {
      String var10000 = this.getName();
      return "MacroManager.Macro(name=" + var10000 + ", message=" + this.getMessage() + ", key=" + this.getKey() + ")";
   }
}
