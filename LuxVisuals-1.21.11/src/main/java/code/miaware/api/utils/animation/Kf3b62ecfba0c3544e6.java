package code.miaware.api.utils.animation;

import java.util.function.Function;
import lombok.Generated;
import org.apache.commons.lang3.StringUtils;

public enum Kf3b62ecfba0c3544e6 {
   LINEAR((x) -> x),
   SIGMOID((x) -> (double)1.0F / ((double)1.0F + Math.exp(-x))),
   QUAD_IN((x) -> x * x),
   QUAD_OUT((x) -> x * ((double)2.0F - x)),
   QUAD_BOTH((x) -> x < (double)0.5F ? (double)2.0F * x * x : (double)-1.0F + ((double)4.0F - (double)2.0F * x) * x),
   CUBIC_IN((x) -> x * x * x),
   CUBIC_OUT((x) -> --x * x * x + (double)1.0F),
   CUBIC_BOTH((x) -> x < (double)0.5F ? (double)4.0F * x * x * x : (x - (double)1.0F) * ((double)2.0F * x - (double)2.0F) * ((double)2.0F * x - (double)2.0F) + (double)1.0F),
   QUART_IN((x) -> x * x * x * x),
   QUART_OUT((x) -> (double)1.0F - --x * x * x * x),
   QUART_BOTH((x) -> x < (double)0.5F ? (double)8.0F * x * x * x * x : (double)1.0F - (double)8.0F * --x * x * x * x),
   QUINT_IN((x) -> x * x * x * x * x),
   QUINT_OUT((x) -> (double)1.0F + --x * x * x * x * x),
   QUINT_BOTH((x) -> x < (double)0.5F ? (double)16.0F * x * x * x * x * x : (double)1.0F + (double)16.0F * --x * x * x * x * x),
   SINE_IN((x) -> (double)1.0F - Math.cos(x * Math.PI / (double)2.0F)),
   SINE_OUT((x) -> Math.sin(x * Math.PI / (double)2.0F)),
   SINE_BOTH((x) -> ((double)1.0F - Math.cos(Math.PI * x)) / (double)2.0F),
   EXPO_IN((x) -> x == (double)0.0F ? (double)0.0F : Math.pow((double)2.0F, (double)10.0F * x - (double)10.0F)),
   EXPO_OUT((x) -> x == (double)1.0F ? (double)1.0F : (double)1.0F - Math.pow((double)2.0F, (double)-10.0F * x)),
   EXPO_BOTH((x) -> x == (double)0.0F ? (double)0.0F : (x == (double)1.0F ? (double)1.0F : (x < (double)0.5F ? Math.pow((double)2.0F, (double)20.0F * x - (double)10.0F) / (double)2.0F : ((double)2.0F - Math.pow((double)2.0F, (double)-20.0F * x + (double)10.0F)) / (double)2.0F))),
   CIRC_IN((x) -> (double)1.0F - Math.sqrt((double)1.0F - x * x)),
   CIRC_OUT((x) -> Math.sqrt((double)1.0F - --x * x)),
   CIRC_BOTH((x) -> x < (double)0.5F ? ((double)1.0F - Math.sqrt((double)1.0F - (double)4.0F * x * x)) / (double)2.0F : (Math.sqrt((double)1.0F - (double)4.0F * (x - (double)1.0F) * x) + (double)1.0F) / (double)2.0F),
   BACK_IN((x) -> 2.70158 * x * x * x - 1.70158 * x * x),
   BACK_OUT((x) -> (double)1.0F + 2.70158 * Math.pow(x - (double)1.0F, (double)3.0F) + 1.70158 * Math.pow(x - (double)1.0F, (double)2.0F)),
   BACK_BOTH((x) -> {
      double c1 = 2.5949095;
      double c3 = c1 + (double)1.0F;
      return x < (double)0.5F ? Math.pow((double)2.0F * x, (double)2.0F) * (c3 * (double)2.0F * x - c1) / (double)2.0F : (Math.pow((double)2.0F * x - (double)2.0F, (double)2.0F) * (c3 * ((double)2.0F * x - (double)2.0F) + c1) + (double)2.0F) / (double)2.0F;
   }),
   ELASTIC_IN((x) -> x == (double)0.0F ? (double)0.0F : (x == (double)1.0F ? (double)1.0F : -Math.pow((double)2.0F, (double)10.0F * x - (double)10.0F) * Math.sin((x * (double)10.0F - (double)10.75F) * 2.0943951023931953))),
   ELASTIC_OUT((x) -> x == (double)0.0F ? (double)0.0F : (x == (double)1.0F ? (double)1.0F : Math.pow((double)2.0F, (double)-10.0F * x) * Math.sin((x * (double)10.0F - (double)0.75F) * 2.0943951023931953) + (double)1.0F)),
   ELASTIC_BOTH((x) -> x == (double)0.0F ? (double)0.0F : (x == (double)1.0F ? (double)1.0F : (x < (double)0.5F ? -(Math.pow((double)2.0F, (double)20.0F * x - (double)10.0F) * Math.sin(((double)20.0F * x - (double)11.125F) * 0.6981317007977318)) : (Math.pow((double)2.0F, (double)-20.0F * x + (double)10.0F) * Math.sin(((double)20.0F * x - (double)11.125F) * 0.6981317007977318) + (double)2.0F) / (double)2.0F))),
   SHRINK((x) -> {
      float easeAmount = 1.3F;
      float shrink = easeAmount + 1.0F;
      return Math.max((double)0.0F, (double)1.0F + (double)shrink * Math.pow(x - (double)1.0F, (double)3.0F) + (double)easeAmount * Math.pow(x - (double)1.0F, (double)2.0F));
   });

   private final Function<Double, Double> function;

   private Kf3b62ecfba0c3544e6(final Function<Double, Double> function) {
      this.function = function;
   }

   public double apply(double x) {
      return (Double)this.getFunction().apply(x);
   }

   public float apply(float x) {
      return ((Double)this.getFunction().apply((double)x)).floatValue();
   }

   public String toString() {
      return StringUtils.capitalize(super.toString().toLowerCase().replace("_", " "));
   }

   @Generated
   public Function<Double, Double> getFunction() {
      return this.function;
   }

   // $FF: synthetic method
   private static Kf3b62ecfba0c3544e6[] $values() {
      return new Kf3b62ecfba0c3544e6[]{LINEAR, SIGMOID, QUAD_IN, QUAD_OUT, QUAD_BOTH, CUBIC_IN, CUBIC_OUT, CUBIC_BOTH, QUART_IN, QUART_OUT, QUART_BOTH, QUINT_IN, QUINT_OUT, QUINT_BOTH, SINE_IN, SINE_OUT, SINE_BOTH, EXPO_IN, EXPO_OUT, EXPO_BOTH, CIRC_IN, CIRC_OUT, CIRC_BOTH, BACK_IN, BACK_OUT, BACK_BOTH, ELASTIC_IN, ELASTIC_OUT, ELASTIC_BOTH, SHRINK};
   }
}
