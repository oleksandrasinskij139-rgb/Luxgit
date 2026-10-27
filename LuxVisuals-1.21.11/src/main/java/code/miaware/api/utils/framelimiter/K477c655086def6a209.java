package code.miaware.api.utils.framelimiter;

import net.minecraft.class_310;

public class K477c655086def6a209 {
   private long lastHookTime = System.nanoTime();
   private int accumulatedCalls;
   private final boolean useMCFrameRate;
   private int currentFps = 0;
   private long hookIntervalNS = 0L;

   public K477c655086def6a209(boolean useMCFrameRate) {
      this.useMCFrameRate = useMCFrameRate;
      this.accumulatedCalls = 0;
   }

   public void execute(int fps, Kcde94bc697d9cdd481... calls) {
      if (this.currentFps != fps) {
         this.hookIntervalNS = 1000000000L / (long)fps;
         this.currentFps = fps;
      }

      long nanoTime = System.nanoTime();
      long elapsed = nanoTime - this.lastHookTime;
      this.accumulatedCalls += (int)(elapsed / this.hookIntervalNS);
      this.lastHookTime += (long)this.accumulatedCalls * this.hookIntervalNS;

      for(this.accumulatedCalls = Math.min(this.accumulatedCalls, this.useMCFrameRate ? Math.min(this.currentFps, class_310.method_1551().method_47599()) : this.currentFps); this.accumulatedCalls > 0; --this.accumulatedCalls) {
         for(Kcde94bc697d9cdd481 call : calls) {
            call.execute();
         }
      }

   }
}
