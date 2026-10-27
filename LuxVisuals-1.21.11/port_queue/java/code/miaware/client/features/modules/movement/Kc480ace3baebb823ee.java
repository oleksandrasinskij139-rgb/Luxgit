package code.miaware.client.features.modules.movement;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import net.minecraft.class_2189;
import net.minecraft.class_2248;
import net.minecraft.class_2261;
import net.minecraft.class_2269;
import net.minecraft.class_2338;
import net.minecraft.class_2401;
import net.minecraft.class_2404;
import net.minecraft.class_243;
import net.minecraft.class_2440;
import net.minecraft.class_2527;
import net.minecraft.class_2577;
import net.minecraft.class_3532;

class Kc480ace3baebb823ee {
   private final int index;
   private final Kf39dc27225f4d9fe6e time = new Kf39dc27225f4d9fe6e();
   private final Ka91715a8c4e3f3b7c4 alpha = new Ka91715a8c4e3f3b7c4();
   private final int color;
   private final class_243 position;
   private double deltaX;
   private double deltaY;
   private double deltaZ;

   public Kc480ace3baebb823ee(final K4dad1424dccce51a10 var1, class_243 position, int index, int color) {
      this.position = position;
      this.index = index;
      this.color = color;
      this.time.reset();
      this.deltaX = (double)0.0F;
      this.deltaY = (double)0.0F;
      this.deltaZ = (double)0.0F;
   }

   public void update() {
      class_2338 pos1 = class_2338.method_49637(this.position.field_1352, this.position.field_1351, this.position.field_1350 + this.deltaZ);
      class_2248 block1 = K4600bef438dafc1a9c.mc.field_1687.method_8320(pos1).method_26204();
      if (this.K3b07078ec001064895(block1)) {
         this.deltaZ *= -0.8;
      }

      class_2338 pos2 = class_2338.method_49637(this.position.field_1352, this.position.field_1351 + this.deltaY, this.position.field_1350);
      class_2248 block2 = K4600bef438dafc1a9c.mc.field_1687.method_8320(pos2).method_26204();
      if (this.K3b07078ec001064895(block2)) {
         this.deltaX *= 0.999;
         this.deltaZ *= 0.999;
         this.deltaY *= -0.7;
      }

      class_2338 pos3 = class_2338.method_49637(this.position.field_1352 + this.deltaX, this.position.field_1351, this.position.field_1350);
      class_2248 block3 = K4600bef438dafc1a9c.mc.field_1687.method_8320(pos3).method_26204();
      if (this.K3b07078ec001064895(block3)) {
         this.deltaX *= -0.8;
      }

      this.updateMotion();
   }

   private boolean K3b07078ec001064895(class_2248 block) {
      return !(block instanceof class_2189) && !(block instanceof class_2261) && !(block instanceof class_2269) && !(block instanceof class_2527) && !(block instanceof class_2401) && !(block instanceof class_2440) && !(block instanceof class_2577) && !(block instanceof class_2404);
   }

   public int getAngle() {
      return (int)class_3532.method_15350((Math.sin((double)this.time.getElapsedTime() / (double)250.0F) + (double)1.0F) / (double)2.0F * (double)255.0F, (double)0.0F, (double)255.0F);
   }

   public void updateMotion() {
      double motion = 0.005;
      this.deltaX += (Math.random() - (double)0.5F) * motion;
      this.deltaY += (Math.random() - (double)0.5F) * motion;
      this.deltaZ += (Math.random() - (double)0.5F) * motion;
      double maxSpeed = (double)0.5F;
      this.deltaX = class_3532.method_15350(this.deltaX, -maxSpeed, maxSpeed);
      this.deltaY = class_3532.method_15350(this.deltaY, -maxSpeed, maxSpeed);
      this.deltaZ = class_3532.method_15350(this.deltaZ, -maxSpeed, maxSpeed);
   }
}
