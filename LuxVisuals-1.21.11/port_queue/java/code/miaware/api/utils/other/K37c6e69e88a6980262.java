package code.miaware.api.utils.other;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.player.Kdfea46ed519f932f28;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.minecraft.class_304;
import net.minecraft.class_3675;

public final class K37c6e69e88a6980262 implements K4600bef438dafc1a9c {
   private static final List<Kfe798e29e7a8b1d73a> entries = new ArrayList(4);
   public static boolean slowed = false;

   public static boolean isEnabled() {
      return false;
   }

   public static void applySlowness(long totalMs, long delayMs, Runnable action) {
      long now = System.nanoTime();
      long execNs = now + delayMs * 1000000L;
      long endNs = now + totalMs * 1000000L;
      entries.add(new Kfe798e29e7a8b1d73a(execNs, endNs, action));
      if (!slowed) {
         for(class_304 key : Kdfea46ed519f932f28.getMovementKeys()) {
            key.method_23481(false);
         }

         slowed = true;
      }

   }

   public static void applySlowness(long totalMs, Runnable action) {
      applySlowness(totalMs, 1L, action);
   }

   public static void tick() {
      if (slowed || !entries.isEmpty()) {
         long now = System.nanoTime();
         List<Kfe798e29e7a8b1d73a> toExecute = null;

         for(Kfe798e29e7a8b1d73a e : entries) {
            if (!e.executed && now >= e.execNs) {
               if (toExecute == null) {
                  toExecute = new ArrayList(4);
               }

               toExecute.add(e);
            }
         }

         if (toExecute != null) {
            for(Kfe798e29e7a8b1d73a e : toExecute) {
               try {
                  if (e.action != null) {
                     e.action.run();
                  }
               } catch (Throwable t) {
                  t.printStackTrace();
               } finally {
                  e.executed = true;
               }
            }
         }

         for(int i = entries.size() - 1; i >= 0; --i) {
            Kfe798e29e7a8b1d73a e = (Kfe798e29e7a8b1d73a)entries.get(i);
            if (e.executed && now >= e.endNs) {
               entries.remove(i);
            }
         }

         if (entries.isEmpty() && slowed) {
            K166247ec3f36cb7131();
         }

      }
   }

   private static void K166247ec3f36cb7131() {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         long handle = mc.method_22683().method_4490();

         for(class_304 key : Kdfea46ed519f932f28.getMovementKeys()) {
            class_3675.class_306 boundKey = class_3675.method_15981(key.method_1428());
            key.method_23481(class_3675.method_15987(handle, boundKey.method_1444()));
         }

         slowed = false;
      } else {
         entries.clear();
         slowed = false;
      }
   }

   @Generated
   private K37c6e69e88a6980262() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
