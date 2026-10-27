package code.miaware.client.features.modules.render;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_243;
import net.minecraft.class_287;
import org.joml.Matrix4f;

final class K4d69a1bf9db467dafb {
   private final List<int[]> nodes;
   private final Ka91715a8c4e3f3b7c4 animation;
   private int[] direction;
   private int stepsLeft;
   private int ticksLeft;
   private int lastSet;
   private boolean dying;
   // $FF: synthetic field
   final Ke3e0017c49d95e97e2 this$0;

   private K4d69a1bf9db467dafb(final Ke3e0017c49d95e97e2 var1, int[] spawn, int steps) {
      this.this$0 = var1;
      this.nodes = new ArrayList();
      this.animation = new Ka91715a8c4e3f3b7c4();
      this.nodes.add(spawn);
      this.direction = var1.Kf54825f985654b0897();
      this.stepsLeft = steps;
      this.animation.setValue((double)0.0F);
      this.animation.run((double)1.0F, 600L, Kf3b62ecfba0c3544e6.SINE_OUT);
   }

   private void tick() {
      this.animation.update();
      if (this.stepsLeft == 0) {
         if (!this.dying) {
            this.dying = true;
            this.animation.run((double)0.0F, 400L, Kf3b62ecfba0c3544e6.SINE_IN);
         }

      } else if (this.ticksLeft > 0) {
         this.ticksLeft -= (Boolean)this.this$0.slow.getValue() ? 1 : 2;
         if (this.ticksLeft < 0) {
            this.ticksLeft = 0;
         }

      } else {
         this.direction = this.this$0.K15d229dd1ebf2ef727(this.direction);
         this.lastSet = this.ticksLeft = this.this$0.random.nextInt(0, 3);
         this.nodes.add(this.this$0.step((int[])this.nodes.getLast(), this.direction, Math.max(1, this.ticksLeft)));
         --this.stepsLeft;
      }
   }

   private float K14e5743d1ff92c1660() {
      return (float)this.animation.getValue();
   }

   private boolean K925b9b8ee6fb4e35c1() {
      return this.K11af9b13deaf356b8f() || this.stepsLeft == 0 && this.animation.getValue() < 0.01 && this.animation.isFinished();
   }

   private boolean K11af9b13deaf356b8f() {
      if (K4600bef438dafc1a9c.mc.field_1724 != null && !this.nodes.isEmpty()) {
         int[] first = (int[])this.nodes.getFirst();
         double dx = (double)first[0] - K4600bef438dafc1a9c.mc.field_1724.method_23317();
         double dy = (double)first[1] - K4600bef438dafc1a9c.mc.field_1724.method_23318();
         double dz = (double)first[2] - K4600bef438dafc1a9c.mc.field_1724.method_23321();
         return dx * dx + dy * dy + dz * dz > (double)5184.0F;
      } else {
         return false;
      }
   }

   private int Kcc791a631b8764bf60() {
      return this.nodes.size();
   }

   private void K13507035993e9aa619(class_287 buffer, Matrix4f matrix, class_243 camera, int colorSeed, long now, String mode) {
      int colorIndex = colorSeed;
      int points = this.Kcc791a631b8764bf60();

      for(int i = 0; i < points; ++i) {
         float alphaPart = this.K14e5743d1ff92c1660() * (0.25F + (float)i / (float)points / 1.75F);
         Color color = this.this$0.K0eb63415f23be5c87a(colorIndex, alphaPart, now, mode);
         buffer.method_22918(matrix, (float)(this.Kafac111ed684007781(i) - camera.field_1352), (float)(this.Ke33d3fbaeaf4a39144(i) - camera.field_1351), (float)(this.K6272038a0775138029(i) - camera.field_1350)).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
         colorIndex += 180;
      }

   }

   private void Kf030095fcc0c303a7c(class_287 buffer, Matrix4f matrix, class_243 camera, int colorSeed, long now, String mode, float size) {
      int colorIndex = colorSeed;
      int points = this.Kcc791a631b8764bf60();

      for(int i = 0; i < points; ++i) {
         float alphaPart = this.K14e5743d1ff92c1660() * (0.25F + (float)i / (float)points / 1.75F);
         Color color = this.this$0.K0eb63415f23be5c87a(colorIndex, alphaPart, now, mode);
         float x = (float)(this.Kafac111ed684007781(i) - camera.field_1352);
         float y = (float)(this.Ke33d3fbaeaf4a39144(i) - camera.field_1351);
         float z = (float)(this.K6272038a0775138029(i) - camera.field_1350);
         buffer.method_22918(matrix, x, y - size, z).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
         buffer.method_22918(matrix, x, y + size, z).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
         colorIndex += 180;
      }

   }

   private double Kafac111ed684007781(int index) {
      return this.K8a156ec3256f1c4338(index, 0);
   }

   private double Ke33d3fbaeaf4a39144(int index) {
      return this.K8a156ec3256f1c4338(index, 1);
   }

   private double K6272038a0775138029(int index) {
      return this.K8a156ec3256f1c4338(index, 2);
   }

   private double K8a156ec3256f1c4338(int index, int axis) {
      int[] node = (int[])this.nodes.get(index);
      double value = (double)node[axis];
      if (index == this.nodes.size() - 1 && this.nodes.size() >= 2) {
         int[] previousNode = (int[])this.nodes.get(index - 1);
         value = this.Kdeeb29ffe3634b5a10((double)previousNode[axis], value, this.Ke56c9c8abf7b5a8717());
      }

      return value;
   }

   private float Ke56c9c8abf7b5a8717() {
      return this.lastSet > 0 ? Math.max(0.0F, Math.min(1.0F, 1.0F - (float)this.ticksLeft / (float)this.lastSet)) : 1.0F;
   }

   private double Kdeeb29ffe3634b5a10(double from, double to, float progress) {
      return from + (to - from) * (double)progress;
   }
}
