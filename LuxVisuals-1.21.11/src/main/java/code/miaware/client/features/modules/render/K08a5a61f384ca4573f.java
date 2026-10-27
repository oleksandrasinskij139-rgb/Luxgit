package code.miaware.client.features.modules.render;

import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import net.minecraft.class_3532;

class K08a5a61f384ca4573f {
   private final Kf39dc27225f4d9fe6e timer = new Kf39dc27225f4d9fe6e();
   private final Ka91715a8c4e3f3b7c4 animation = new Ka91715a8c4e3f3b7c4();
   private final long maxTime;

   public K08a5a61f384ca4573f(long maxTime) {
      this.maxTime = maxTime;
      this.timer.reset();
      this.animation.run(1.0F, maxTime, Kf3b62ecfba0c3544e6.LINEAR);
   }

   public float getPercent() {
      this.animation.update();
      return (float)class_3532.method_15350(this.animation.getValue() / (double)1.0F, (double)0.0F, (double)1.0F);
   }

   public float getPercent010() {
      float percent = this.getPercent();
      return percent < 0.5F ? percent * 2.0F : (1.0F - percent) * 2.0F;
   }
}
