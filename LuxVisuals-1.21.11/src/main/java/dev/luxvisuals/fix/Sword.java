package dev.luxvisuals.fix;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class Sword {
   private static Object cmdType;
   private static Object swordA;
   private static Object swordB;
   private static Constructor<?> cmdCtor;
   private static Method getComp;
   private static Method setComp;
   private static final List<Object[]> APPLIED = new ArrayList();
   private static Method perspectiveGetter;
   private static boolean perspectiveSearched;
   private static Object freeLookModule;
   private static boolean freeLookSearched;

   private Sword() {
   }

   static void register() {
      ElytraRt.register();

      try {
         cmdType = Class.forName("net.minecraft.class_9334").getField("field_49637").get((Object)null);
         Class var0 = Class.forName("net.minecraft.class_1802");
         swordA = var0.getField("field_8802").get((Object)null);
         swordB = var0.getField("field_22022").get((Object)null);
         cmdCtor = Class.forName("net.minecraft.class_9280").getConstructor(List.class, List.class, List.class, List.class);

         for(Method var4 : Class.forName("net.minecraft.class_1799").getMethods()) {
            if (var4.getName().equals("method_57824") && var4.getParameterCount() == 1) {
               getComp = var4;
            }

            if (var4.getName().equals("method_57379") && var4.getParameterCount() == 2) {
               setComp = var4;
            }
         }

         Class var6 = Class.forName("net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents");
         hook(var6, "START_CLIENT_TICK", "StartTick", false);
         hook(var6, "END_CLIENT_TICK", "EndTick", true);
      } catch (Throwable var5) {
      }

   }

   private static void hook(Class<?> var0, String var1, String var2, boolean var3) throws Exception {
      String var4 = var0.getName();
      Class var5 = Class.forName(var4 + "$" + var2);
      InvocationHandler var6 = (var1x, var2x, var3x) -> {
         if (var2x.getDeclaringClass() == Object.class) {
            switch (var2x.getName()) {
               case "hashCode" -> {
                  return System.identityHashCode(var1x);
               }
               case "equals" -> {
                  return var1x == var3x[0];
               }
               default -> {
                  return "LuxSword";
               }
            }
         } else {
            try {
               if (var3) {
                  apply();
               } else {
                  restore();
               }
            } catch (Throwable var6) {
            }

            return null;
         }
      };
      Object var7 = Proxy.newProxyInstance(var5.getClassLoader(), new Class[]{var5}, var6);
      Object var8 = var0.getField(var1).get((Object)null);
      Class.forName("net.fabricmc.fabric.api.event.Event").getMethod("register", Object.class).invoke(var8, var7);
   }

   private static void restore() {
      for(Object[] var1 : APPLIED) {
         try {
            setComp.invoke(var1[0], cmdType, var1[1]);
         } catch (Throwable var3) {
         }
      }

      APPLIED.clear();
   }

   private static boolean isTarget(Object var0) throws Exception {
      Object var1 = var0.getClass().getMethod("method_7909").invoke(var0);
      return var1 == swordA || var1 == swordB;
   }

   private static void put(Object var0, String var1) {
      try {
         if (var0 == null || !isTarget(var0)) {
            return;
         }

         for(Object[] var3 : APPLIED) {
            if (var3[0] == var0) {
               return;
            }
         }

         Object var5 = getComp.invoke(var0, cmdType);
         Object var6 = cmdCtor.newInstance(Collections.emptyList(), Collections.emptyList(), List.of(var1), Collections.emptyList());
         setComp.invoke(var0, cmdType, var6);
         APPLIED.add(new Object[]{var0, var5});
      } catch (Throwable var4) {
      }

   }

   private static void both(Object var0, String var1) throws Exception {
      put(var0.getClass().getMethod("method_6047").invoke(var0), var1);
      put(var0.getClass().getMethod("method_6079").invoke(var0), var1);
   }

   private static boolean plainFirstPerson(Object var0) {
      try {
         if (freeLookActive()) {
            return false;
         } else {
            Object var1 = LuxPlus.field(var0, "field_1690");
            if (var1 == null) {
               return false;
            } else {
               if (!perspectiveSearched) {
                  perspectiveSearched = true;

                  for(Method var5 : var1.getClass().getMethods()) {
                     if (var5.getParameterCount() == 0 && var5.getReturnType().isEnum()) {
                        for(Object var9 : var5.getReturnType().getEnumConstants()) {
                           if (((Enum)var9).name().equals("FIRST_PERSON")) {
                              perspectiveGetter = var5;
                              break;
                           }
                        }

                        if (perspectiveGetter != null) {
                           break;
                        }
                     }
                  }
               }

               if (perspectiveGetter == null) {
                  return false;
               } else {
                  Object var11 = perspectiveGetter.invoke(var1);
                  return var11 != null && ((Enum)var11).name().equals("FIRST_PERSON");
               }
            }
         }
      } catch (Throwable var10) {
         return false;
      }
   }

   private static boolean freeLookActive() {
      try {
         if (!freeLookSearched) {
            freeLookSearched = true;
            Class var0 = Class.forName("dev.luxvisuals.LuxVisuals");
            Object var1 = var0.getMethod("getInstance").invoke((Object)null);
            Object var2 = var0.getMethod("getModuleManager").invoke(var1);

            for(Object var4 : (List)var2.getClass().getMethod("getModules").invoke(var2)) {
               String var5 = String.valueOf(var4.getClass().getMethod("getName").invoke(var4)).toLowerCase().replaceAll("[^a-z]", "");
               if (var5.contains("freelook")) {
                  freeLookModule = var4;
                  break;
               }
            }
         }

         return freeLookModule == null ? false : (Boolean)freeLookModule.getClass().getMethod("isToggled").invoke(freeLookModule);
      } catch (Throwable var6) {
         return false;
      }
   }

   private static void apply() throws Exception {
      String[] var0 = LuxPlus.swordState();
      if ("1".equals(var0[1])) {
         Object var1 = LuxPlus.mc();
         Object var2 = LuxPlus.field(var1, "field_1724");
         Object var3 = LuxPlus.field(var1, "field_1687");
         if (var2 != null && var3 != null) {
            if (!plainFirstPerson(var1)) {
               both(var2, var0[0]);
            }

            if (!LuxPlus.REMOTE_SWORD.isEmpty()) {
               for(Object var5 : (List)LuxPlus.call(var3, "method_18456")) {
                  if (var5 != var2) {
                     String var6 = (String)LuxPlus.REMOTE_SWORD.get(LuxPlus.call(var5, "method_5667"));
                     if (var6 != null) {
                        both(var5, var6);
                     }
                  }
               }
            }
         }
      }

   }
}
