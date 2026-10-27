package code.miaware.api.utils.task;

import lombok.Generated;

public enum K08a614ab3119f6f1e9 {
   CRITICAL(5),
   REQUIRED(4),
   HIGH(3),
   MEDIUM_HIGH(2),
   MEDIUM(1),
   NORMAL(0),
   LOW(-1),
   LOWEST(-2);

   private final int priority;

   @Generated
   public int getPriority() {
      return this.priority;
   }

   @Generated
   private K08a614ab3119f6f1e9(final int priority) {
      this.priority = priority;
   }

   // $FF: synthetic method
   private static K08a614ab3119f6f1e9[] $values() {
      return new K08a614ab3119f6f1e9[]{CRITICAL, REQUIRED, HIGH, MEDIUM_HIGH, MEDIUM, NORMAL, LOW, LOWEST};
   }
}
