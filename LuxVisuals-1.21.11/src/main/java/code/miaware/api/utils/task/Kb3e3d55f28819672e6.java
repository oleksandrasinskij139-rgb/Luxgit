package code.miaware.api.utils.task;

import code.miaware.api.module.Kab8a8c478f7d0077cd;

public class Kb3e3d55f28819672e6<T> {
   public long expiresIn;
   public final int priority;
   public final Kab8a8c478f7d0077cd provider;
   public final T value;

   public Kb3e3d55f28819672e6(int expiresIn, int priority, Kab8a8c478f7d0077cd provider, T value) {
      this.expiresIn = (long)expiresIn;
      this.priority = priority;
      this.provider = provider;
      this.value = value;
   }

   public void run() {
   }

   public String toString() {
      long var10000 = this.expiresIn;
      return "Task(expiresIn=" + var10000 + ", priority=" + this.priority + ", provider=" + String.valueOf(this.provider) + ", value=" + String.valueOf(this.value) + ")";
   }
}
