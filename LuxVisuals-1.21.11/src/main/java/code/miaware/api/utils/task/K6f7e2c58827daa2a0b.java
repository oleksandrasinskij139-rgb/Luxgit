package code.miaware.api.utils.task;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import java.util.Comparator;
import java.util.PriorityQueue;

public class K6f7e2c58827daa2a0b<T> {
   private long tickCounter = 0L;
   private final PriorityQueue<Kb3e3d55f28819672e6<T>> activeTasks = new PriorityQueue(Comparator.comparingInt((task) -> task.priority).reversed());

   public void tick(int deltaTime) {
      this.tickCounter += (long)deltaTime;

      while(!this.activeTasks.isEmpty() && ((Kb3e3d55f28819672e6)this.activeTasks.peek()).expiresIn <= this.tickCounter) {
         Kb3e3d55f28819672e6<T> task = (Kb3e3d55f28819672e6)this.activeTasks.poll();
         task.run();
      }

   }

   public void addTask(Kb3e3d55f28819672e6<T> task) {
      this.activeTasks.removeIf((t) -> t.provider == task.provider);
      task.expiresIn += this.tickCounter;
      this.activeTasks.add(task);
   }

   public void removeTasksByProvider(Kab8a8c478f7d0077cd provider) {
      if (provider != null) {
         this.activeTasks.removeIf((task) -> task.provider == provider);
      }
   }

   public T fetchActiveTaskValue() {
      while(!this.activeTasks.isEmpty() && (((Kb3e3d55f28819672e6)this.activeTasks.peek()).expiresIn <= this.tickCounter || !((Kb3e3d55f28819672e6)this.activeTasks.peek()).provider.isEnabled())) {
         this.activeTasks.poll();
      }

      if (this.activeTasks.isEmpty()) {
         return null;
      } else {
         return ((Kb3e3d55f28819672e6)this.activeTasks.peek()).value;
      }
   }
}
