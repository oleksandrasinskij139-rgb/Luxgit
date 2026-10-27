package code.miaware.api.event;

import code.miaware.api.event.interfaces.K636f39e011f01693c0;
import code.miaware.api.event.interfaces.Ka9bf815d554e81fc76;
import code.miaware.api.event.interfaces.Kee8f9219365ba072fa;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Kd00cfb1d2b6aeb7641<T> implements K636f39e011f01693c0<T>, Kee8f9219365ba072fa<Ka68e0b23b1084c01e4<T>, T>, Ka9bf815d554e81fc76<T> {
   private final ConcurrentSkipListSet<Ka68e0b23b1084c01e4<T>> listeners = new ConcurrentSkipListSet();
   private volatile Ka68e0b23b1084c01e4<T>[] cache = new Ka68e0b23b1084c01e4[0];
   private final AtomicBoolean rebuildCache = new AtomicBoolean(true);

   public Ka68e0b23b1084c01e4<T>[] getCache() {
      if (this.rebuildCache.compareAndSet(true, false)) {
         this.cache = (Ka68e0b23b1084c01e4[])this.listeners.toArray((x$0) -> new Ka68e0b23b1084c01e4[x$0]);
      }

      return this.cache;
   }

   public K8d7165024ff567f5e7 subscribe(Ka68e0b23b1084c01e4<T> listener) {
      this.listeners.add(listener);
      this.rebuildCache.set(true);
      return new K8d7165024ff567f5e7(() -> this.unsubscribe(listener));
   }

   public void unsubscribe(Ka68e0b23b1084c01e4<T> listener) {
      if (this.listeners.remove(listener)) {
         this.rebuildCache.set(true);
      }

   }

   public void notify(T event) {
      Ka68e0b23b1084c01e4<T>[] cachedListeners = this.getCache();
      int length = cachedListeners.length;

      for(int i = 0; i < length; ++i) {
         cachedListeners[i].getHandler().accept(event);
      }

   }
}
