package code.miaware.client.features.modules.render.armor;

public final class K422eab760990558fde {
   private static final ThreadLocal<Integer> COLOR = new ThreadLocal();

   public static Integer get() {
      return (Integer)COLOR.get();
   }

   public static void set(Integer value) {
      if (value == null) {
         COLOR.remove();
      } else {
         COLOR.set(value);
      }

   }
}
