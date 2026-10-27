package code.miaware.client.features.modules.render;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import net.minecraft.class_1309;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_3959;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;

class K1736f93360a509caa5 {
   private final class_243 startPos;
   private final float healthDiff;
   private final Kec38d173570ec4145f percentRunner;
   private final class_243 targetOffset;
   private final class_1309 entity;

   public K1736f93360a509caa5(class_243 startPos, float healthDiff, class_243 targetOffset, long maxTime, class_1309 entity) {
      this.startPos = startPos;
      this.healthDiff = healthDiff;
      this.targetOffset = targetOffset;
      this.percentRunner = new Kec38d173570ec4145f(maxTime);
      this.entity = entity;
   }

   public boolean canRender() {
      if (this.entity != null && this.entity.method_5805()) {
         if (K4600bef438dafc1a9c.mc.field_1687 != null && K4600bef438dafc1a9c.mc.field_1724 != null) {
            class_243 playerEyePos = K4600bef438dafc1a9c.mc.field_1724.method_33571();
            class_243 particlePos = this.getCurrentPos();
            class_3959 context = new class_3959(playerEyePos, particlePos, class_3960.field_17558, class_242.field_1348, K4600bef438dafc1a9c.mc.field_1724);
            class_239 result = K4600bef438dafc1a9c.mc.field_1687.method_17742(context);
            return result.method_17783() == class_240.field_1333 || playerEyePos.method_1022(result.method_17784()) >= playerEyePos.method_1022(particlePos) - (double)0.5F;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public class_243 getCurrentPos() {
      float progress = this.percentRunner.getPercent();
      double easedProgress = (double)1.0F - Math.pow((double)(1.0F - progress), (double)3.0F);
      return this.startPos.method_1031(this.targetOffset.field_1352 * easedProgress, this.targetOffset.field_1351 * easedProgress, this.targetOffset.field_1350 * easedProgress);
   }

   public void update() {
   }

   public boolean isToRemove() {
      return this.percentRunner.getPercent() >= 1.0F || K4600bef438dafc1a9c.mc.field_1724.method_19538().method_1022(this.getCurrentPos()) > (double)64.0F;
   }

   public boolean renderRule() {
      return !this.isToRemove();
   }
}
