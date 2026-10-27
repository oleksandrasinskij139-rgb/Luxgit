package dev.luxvisuals.menu;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

final class MenuGuiScale {
   private static final double WANTED = (double)2.0F;
   private static final String MENU = "II1II1II1II1II1II1II1II1.I8_i7.Ill_iiIll1___l1_l1li1I1l11I";
   private static boolean applied;
   private static double original = (double)-1.0F;
   private static boolean broken;
   private static Method setScale;

   static void tick() {
   }

   private static void apply(Object var0, Object var1, Object var2, double var3) throws Exception {
      setScale.invoke(var2, var3);
      if (var1 != null) {
         int var5 = (Integer)call(var2.getClass(), var2, "method_4486");
         int var6 = (Integer)call(var2.getClass(), var2, "method_4502");
         Method var7 = null;

         for(Method var11 : var1.getClass().getMethods()) {
            if (var11.getName().equals("method_25410") && var11.getParameterCount() == 3) {
               var7 = var11;
               break;
            }
         }

         if (var7 != null) {
            var7.invoke(var1, var0, var5, var6);
         }
      }

   }

   private static Method findSetter(Class<?> var0) {
      for(Method var4 : var0.getDeclaredMethods()) {
         Class[] var5 = var4.getParameterTypes();
         if (var5.length == 1 && var5[0] == Double.TYPE && var4.getReturnType() == Void.TYPE && !Modifier.isStatic(var4.getModifiers())) {
            var4.setAccessible(true);
            return var4;
         }
      }

      throw new IllegalStateException("Window.setScaleFactor(double) не найден");
   }

   private static Object call(Class<?> var0, Object var1, String var2) throws Exception {
      Method var3 = var0.getMethod(var2);
      return var3.invoke(var1);
   }
}
