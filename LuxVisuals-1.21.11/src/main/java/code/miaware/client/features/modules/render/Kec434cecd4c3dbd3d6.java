package code.miaware.client.features.modules.render;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import net.minecraft.class_243;
import org.joml.Quaternionf;

class Kec434cecd4c3dbd3d6 {
   private final int index;
   private final class_243 pos;
   private final K08a5a61f384ca4573f percentRunner;
   private final Quaternionf quaternion;

   public Kec434cecd4c3dbd3d6(int index, class_243 pos, long maxTime) {
      this.index = index;
      this.pos = pos;
      this.percentRunner = new K08a5a61f384ca4573f(maxTime);
      this.quaternion = K4600bef438dafc1a9c.mc.field_1773.method_19418().method_23767();
   }

   public boolean isToRemove() {
      return this.percentRunner.getPercent() == 1.0F || K4600bef438dafc1a9c.mc.field_1724.method_19538().method_1022(this.pos) > (double)100.0F;
   }

   public boolean renderRule() {
      return !this.isToRemove();
   }
}
