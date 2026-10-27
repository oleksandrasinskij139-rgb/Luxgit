package code.miaware.api.utils.render.display;

import net.minecraft.class_4587;

public class Kc304e80e5cabdb5ac3 {
   public void scaleStart(class_4587 matrixStack, float x, float y, double scale) {
      matrixStack.method_22903();
      matrixStack.method_46416(x, y, 0.0F);
      matrixStack.method_22905((float)scale, (float)scale, 1.0F);
      matrixStack.method_46416(-x, -y, 0.0F);
   }

   public void squshStart(class_4587 matrixStack, float x, float y, double scale) {
      matrixStack.method_22903();
      matrixStack.method_46416(x, y, 0.0F);
      matrixStack.method_22905(1.0F, (float)scale, 1.0F);
      matrixStack.method_46416(-x, -y, 0.0F);
   }

   public void scaleStop(class_4587 matrixStack) {
      matrixStack.method_22909();
   }
}
