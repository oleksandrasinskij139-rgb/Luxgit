package code.miaware.api.event.events;

import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.Kd00cfb1d2b6aeb7641;

public class Event<T> extends Kd00cfb1d2b6aeb7641<T> {
   private final ThreadLocal<Boolean> cancelState = ThreadLocal.withInitial(() -> false);

   protected T getSelf() {
      return (T)this;
   }

   public boolean isCancel() {
      return (Boolean)this.cancelState.get();
   }

   public void setCancel(boolean cancel) {
      this.cancelState.set(cancel);
   }

   public boolean call() {
      return this.call(this.getSelf());
   }

   public boolean call(T any) {
      this.setCancel(false);

      for(Ka68e0b23b1084c01e4<T> listener : this.getCache()) {
         listener.getHandler().accept(any);
         if (this.isCancel()) {
            break;
         }
      }

      return this.isCancel();
   }
}
