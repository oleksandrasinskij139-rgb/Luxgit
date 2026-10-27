package code.miaware.api.utils.math;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.Generated;
import net.minecraft.class_1297;

public final class Ka0484a23398fb5f9ac implements K4600bef438dafc1a9c {
   private static final K4ccfce20b90a27da2f randomUtil = new K4ccfce20b90a27da2f();

   public static double getEntityBPS(class_1297 entity) {
      return Math.hypot(entity.field_6014 - entity.method_23317(), entity.field_5969 - entity.method_23321()) * (double)20.0F;
   }

   public static float interpolate(double oldValue, double newValue) {
      return (float)(oldValue + (newValue - oldValue) * (double)mc.method_61966().method_60637(false));
   }

   public static double interpolate(double start, double end, double delta) {
      return start + (end - start) * delta;
   }

   public static float interpolate(float start, float end, float delta) {
      return start + (end - start) * delta;
   }

   public static int interpolate(int oldValue, int newValue, float interpolationValue) {
      return (int)((float)oldValue + (float)(newValue - oldValue) * interpolationValue);
   }

   public static double round(double value, double step) {
      if (!(step <= (double)0.0F) && Double.isFinite(value)) {
         BigDecimal valueDecimal = K71aa914db13ea1772c(value);
         BigDecimal stepDecimal = K71aa914db13ea1772c(step);
         BigDecimal rounded = valueDecimal.divide(stepDecimal, 0, RoundingMode.HALF_UP).multiply(stepDecimal);
         return rounded.setScale(K696abdc83ad49adf25(stepDecimal), RoundingMode.HALF_UP).doubleValue();
      } else {
         return value;
      }
   }

   public static float round(float value, float step) {
      if (!(step <= 0.0F) && Float.isFinite(value)) {
         BigDecimal valueDecimal = Ka98eb3316f52064cb2(value);
         BigDecimal stepDecimal = Ka98eb3316f52064cb2(step);
         BigDecimal rounded = valueDecimal.divide(stepDecimal, 0, RoundingMode.HALF_UP).multiply(stepDecimal);
         return rounded.setScale(K696abdc83ad49adf25(stepDecimal), RoundingMode.HALF_UP).floatValue();
      } else {
         return value;
      }
   }

   public static String formatStepValue(float value, float step) {
      if (!Float.isFinite(value)) {
         return String.valueOf(value);
      } else {
         BigDecimal bd = Ka98eb3316f52064cb2(round(value, step)).stripTrailingZeros();
         return bd.scale() < 0 ? bd.setScale(0, RoundingMode.HALF_UP).toPlainString() : bd.toPlainString();
      }
   }

   private static int K4ec5a5c7d5b1dc301c(double step) {
      return K696abdc83ad49adf25(K71aa914db13ea1772c(step));
   }

   private static int K696abdc83ad49adf25(BigDecimal stepDecimal) {
      return Math.max(0, stepDecimal.stripTrailingZeros().scale());
   }

   private static BigDecimal Ka98eb3316f52064cb2(float value) {
      return new BigDecimal(Float.toString(value));
   }

   private static BigDecimal K71aa914db13ea1772c(double value) {
      return new BigDecimal(Double.toString(value));
   }

   public static int randomInRange(int min, int max) {
      return randomUtil.randomInRange(min, max);
   }

   public static float randomInRange(float min, float max) {
      return randomUtil.randomInRange(min, max);
   }

   public static double randomInRange(double min, double max) {
      return randomUtil.randomInRange(min, max);
   }

   @Generated
   private Ka0484a23398fb5f9ac() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
