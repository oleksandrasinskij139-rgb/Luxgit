package code.miaware.api.utils.math;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import lombok.Generated;

public final class K75fb14cd72c582f1b2 implements K4600bef438dafc1a9c {
   public static float getGCD() {
      float sensitivity = ((Double)mc.field_1690.method_42495().method_41753()).floatValue() * 0.6F + 0.2F;
      float pow = sensitivity * sensitivity * sensitivity * 8.0F;
      return pow * 0.15F;
   }

   public static boolean isHovered(float mouseX, float mouseY, float x, float y, float width, float height) {
      return mouseX >= x && mouseY >= y && mouseX <= x + width && mouseY <= y + height;
   }

   public static boolean isHovered(double mouseX, double mouseY, double x, double y, double width, double height) {
      return mouseX >= x && mouseY >= y && mouseX <= x + width && mouseY <= y + height;
   }

   public static boolean isHovered(float mouseX, float mouseY, float x, float y, float width, float height, float round) {
      if (!(mouseX < x) && !(mouseY < y) && !(mouseX > x + width) && !(mouseY > y + height)) {
         if (round <= 0.0F) {
            return true;
         } else {
            float nearestX = Math.max(x + round, Math.min(mouseX, x + width - round));
            float nearestY = Math.max(y + round, Math.min(mouseY, y + height - round));
            float dx = mouseX - nearestX;
            float dy = mouseY - nearestY;
            float distanceSq = dx * dx + dy * dy;
            return distanceSq <= round * round;
         }
      } else {
         return false;
      }
   }

   public static boolean isHovered(double mouseX, double mouseY, double x, double y, double width, double height, double round) {
      if (!(mouseX < x) && !(mouseY < y) && !(mouseX > x + width) && !(mouseY > y + height)) {
         if (round <= (double)0.0F) {
            return true;
         } else {
            double nearestX = Math.max(x + round, Math.min(mouseX, x + width - round));
            double nearestY = Math.max(y + round, Math.min(mouseY, y + height - round));
            double dx = mouseX - nearestX;
            double dy = mouseY - nearestY;
            double distanceSq = dx * dx + dy * dy;
            return distanceSq <= round * round;
         }
      } else {
         return false;
      }
   }

   @Generated
   private K75fb14cd72c582f1b2() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
