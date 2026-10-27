package code.miaware.api.system.client;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.utils.task.K08a614ab3119f6f1e9;
import code.miaware.api.utils.task.K6f7e2c58827daa2a0b;
import code.miaware.api.utils.task.Kb3e3d55f28819672e6;
import lombok.Generated;

public class K075888bb470f86bd78 {
   private static final K075888bb470f86bd78 instance = new K075888bb470f86bd78();
   private final K6f7e2c58827daa2a0b<Float> taskProcessor = new K6f7e2c58827daa2a0b<Float>();

   public float getTimerSpeed() {
      Float timerSpeed = this.taskProcessor.fetchActiveTaskValue();
      return timerSpeed != null ? timerSpeed : 1.0F;
   }

   public void addTimer(float timer, K08a614ab3119f6f1e9 taskPriority, Kab8a8c478f7d0077cd provider, int ticks) {
      this.taskProcessor.addTask(new Kb3e3d55f28819672e6(ticks, taskPriority.getPriority(), provider, timer));
   }

   @Generated
   public K6f7e2c58827daa2a0b<Float> getTaskProcessor() {
      return this.taskProcessor;
   }

   @Generated
   public static K075888bb470f86bd78 getInstance() {
      return instance;
   }
}
