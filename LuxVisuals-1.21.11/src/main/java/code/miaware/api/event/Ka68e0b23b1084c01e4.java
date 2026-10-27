package code.miaware.api.event;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import lombok.Generated;

public class Ka68e0b23b1084c01e4<T> implements Comparable<Ka68e0b23b1084c01e4<T>> {
   private static final AtomicLong COUNTER = new AtomicLong();
   private final int priority;
   private final Consumer<T> handler;
   private final long id;

   public Ka68e0b23b1084c01e4(int priority, Consumer<T> handler) {
      this.priority = priority;
      this.handler = handler;
      this.id = COUNTER.getAndIncrement();
   }

   public Ka68e0b23b1084c01e4(Consumer<T> handler) {
      this(0, handler);
   }

   public int compareTo(Ka68e0b23b1084c01e4<T> other) {
      int prioCompare = Integer.compare(other.priority, this.priority);
      return prioCompare != 0 ? prioCompare : Long.compare(this.id, other.id);
   }

   @Generated
   public int getPriority() {
      return this.priority;
   }

   @Generated
   public Consumer<T> getHandler() {
      return this.handler;
   }
}
