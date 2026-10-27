package code.miaware.api.system.localization;

import lombok.Generated;

public enum K17ee0c48cbe939a34b {
   RUSSIAN("RU"),
   ENGLISH("EN");

   private final String code;

   private K17ee0c48cbe939a34b(String code) {
      this.code = code;
   }

   public K17ee0c48cbe939a34b toggle() {
      return this == RUSSIAN ? ENGLISH : RUSSIAN;
   }

   public static K17ee0c48cbe939a34b fromCode(String code) {
      for(K17ee0c48cbe939a34b language : values()) {
         if (language.code.equalsIgnoreCase(code)) {
            return language;
         }
      }

      return RUSSIAN;
   }

   @Generated
   public String getCode() {
      return this.code;
   }

   // $FF: synthetic method
   private static K17ee0c48cbe939a34b[] $values() {
      return new K17ee0c48cbe939a34b[]{RUSSIAN, ENGLISH};
   }
}
