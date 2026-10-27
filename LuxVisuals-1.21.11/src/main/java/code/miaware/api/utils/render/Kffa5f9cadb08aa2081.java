package code.miaware.api.utils.render;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class Kffa5f9cadb08aa2081<T> {
   private final Executor executor;
   private final Supplier<T> fetch;
   private final long interval;
   private CompletableFuture<T> pending;
   private long next;
   private boolean started;

   public Kffa5f9cadb08aa2081(Executor executor, Supplier<T> fetch, long interval) {
      if (interval <= 0L) {
         throw new IllegalArgumentException("Positive polling interval required");
      } else {
         this.executor = executor;
         this.fetch = fetch;
         this.interval = interval;
      }
   }

   public void update(long now, Consumer<T> consume) {
      if (this.pending != null && this.pending.isDone()) {
         try {
            consume.accept(this.pending.getNow((Object)null));
         } catch (CompletionException var5) {
         }

         this.pending = null;
      }

      if (this.pending == null && (!this.started || now >= this.next)) {
         this.started = true;
         this.next = now + this.interval;
         this.pending = CompletableFuture.supplyAsync(this.fetch, this.executor);
      }

   }
}
