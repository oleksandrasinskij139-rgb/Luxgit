package code.miaware.api.system.client;

import lombok.Generated;

public final class K4a005b483a6d4aa68c {
   public static Thread run(Runnable runnable) {
      Thread thread = new Thread(runnable, "miaware-worker");
      thread.setDaemon(true);
      thread.start();
      return thread;
   }

   @Generated
   private K4a005b483a6d4aa68c() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
