package dev.luxvisuals.fix;

import dev.luxvisuals.client.managers.C14;
import dev.luxvisuals.client.managers.C26;
import dev.luxvisuals.client.managers.C28;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.lang.reflect.RecordComponent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.class_2960;

public final class ElytraRt {
   private static Object eqType;
   private static Object chestSlot;
   private static Method getComp;
   private static Method setComp;
   private static Method getEquipped;
   private static Method keyOf;
   private static Method keyOfRegistry;
   private static final List<Object[]> APPLIED = new ArrayList();
   private static final Pattern KEY = Pattern.compile("\\[(.+?) / (.+?)\\]");
   private static Class<?> eqCls;
   private static Constructor<?> eqCtor;
   private static Method[] eqAcc;
   private static int assetIdx = -1;
   private static boolean ok;

   private ElytraRt() {
   }

   static void register() {
      StringBuilder var0 = new StringBuilder();

      try {
         Class var1 = Class.forName("net.minecraft.class_9334");

         for(Field var5 : var1.getFields()) {
            if (Modifier.isStatic(var5.getModifiers())) {
               Object var6 = var5.get((Object)null);
               if (var6 != null && String.valueOf(var6).contains("equippable")) {
                  eqType = var6;
                  break;
               }
            }
         }

         Class var13 = Class.forName("net.minecraft.class_1304");

         for(Object var23 : var13.getEnumConstants()) {
            if (((Enum)var23).name().equals("CHEST")) {
               chestSlot = var23;
            }
         }

         Class var15 = Class.forName("net.minecraft.class_1799");

         for(Method var7 : var15.getMethods()) {
            if (var7.getName().equals("method_57824") && var7.getParameterCount() == 1) {
               getComp = var7;
            }

            if (var7.getName().equals("method_57379") && var7.getParameterCount() == 2) {
               setComp = var7;
            }
         }

         Class var18 = Class.forName("net.minecraft.class_1309");

         for(Method var8 : var18.getMethods()) {
            if (var8.getParameterCount() == 1 && var8.getParameterTypes()[0] == var13 && var8.getReturnType() == var15) {
               getEquipped = var8;
               break;
            }
         }

         Class var22 = Class.forName("net.minecraft.class_5321");

         for(Method var9 : var22.getMethods()) {
            if (Modifier.isStatic(var9.getModifiers()) && var9.getReturnType() == var22) {
               Class[] var10 = var9.getParameterTypes();
               if (var10.length == 2 && var10[0] == var22 && var10[1] == class_2960.class) {
                  keyOf = var9;
               }

               if (var10.length == 1 && var10[0] == class_2960.class) {
                  keyOfRegistry = var9;
               }
            }
         }

         ok = eqType != null && chestSlot != null && getComp != null && setComp != null && getEquipped != null && keyOf != null && keyOfRegistry != null;
         var0.append("equippable=").append(eqType).append(" chest=").append(chestSlot).append(" getComp=").append(getComp != null).append(" setComp=").append(setComp != null).append(" getEquipped=").append(getEquipped != null).append(" keyOf=").append(keyOf != null).append(" keyOfRegistry=").append(keyOfRegistry != null).append(" ok=").append(ok).append('\n');
         if (ok) {
            Class var27 = Class.forName("net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents");
            hook(var27, "START_CLIENT_TICK", "StartTick", false);
            hook(var27, "END_CLIENT_TICK", "EndTick", true);
         }
      } catch (Throwable var12) {
         ok = false;
         var0.append("error: ").append(var12).append('\n');
      }

      try {
         Files.createDirectories(Path.of("config"));
         Files.writeString(Path.of("config", "luxvisuals_elytra_rt.txt"), var0.toString());
      } catch (Throwable var11) {
      }

   }

   private static void hook(Class<?> var0, String var1, String var2, boolean var3) throws Exception {
      String var10000 = var0.getName();
      Class var4 = Class.forName(var10000 + "$" + var2);
      InvocationHandler var5 = (var1x, var2x, var3x) -> {
         if (var2x.getDeclaringClass() == Object.class) {
            switch (var2x.getName()) {
               case "hashCode" -> {
                  return System.identityHashCode(var1x);
               }
               case "equals" -> {
                  return var1x == var3x[0];
               }
               default -> {
                  return "LuxElytra";
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
      Object var6 = Proxy.newProxyInstance(var4.getClassLoader(), new Class[]{var4}, var5);
      Object var7 = var0.getField(var1).get((Object)null);
      Class.forName("net.fabricmc.fabric.api.event.Event").getMethod("register", Object.class).invoke(var7, var6);
   }

   private static void restore() {
      for(Object[] var1 : APPLIED) {
         try {
            setComp.invoke(var1[0], eqType, var1[1]);
         } catch (Throwable var3) {
         }
      }

      APPLIED.clear();
   }

   private static String name(C28 var0) {
      return var0 != null && var0.id().startsWith("elytra_") ? var0.id() : null;
   }

   private static void apply() throws Exception {
      Object var0 = LuxPlus.mc();
      Object var1 = LuxPlus.field(var0, "field_1724");
      Object var2 = LuxPlus.field(var0, "field_1687");
      if (var1 != null && var2 != null) {
         String var3 = name(ElytraCos.wing());
         boolean var4 = C26.isWingsOnOthersEnabled();
         if (var3 != null) {
            put(var1, var3);
         }

         for(Object var6 : (List)LuxPlus.call(var2, "method_18456")) {
            if (var6 != var1) {
               String var7 = var4 ? var3 : name(C14.getRemoteWing((UUID)LuxPlus.call(var6, "method_5667")));
               if (var7 != null) {
                  put(var6, var7);
               }
            }
         }

      }
   }

   static void put(Object var0, String var1) {
      try {
         Object var2 = getEquipped.invoke(var0, chestSlot);
         if (var2 == null) {
            return;
         }

         for(Object[] var4 : APPLIED) {
            if (var4[0] == var2) {
               return;
            }
         }

         Object var12 = getComp.invoke(var2, eqType);
         if (var12 == null) {
            return;
         }

         if (eqCls != var12.getClass()) {
            RecordComponent[] var13 = var12.getClass().getRecordComponents();
            Class[] var5 = new Class[var13.length];
            Method[] var6 = new Method[var13.length];
            int var7 = -1;

            for(int var8 = 0; var8 < var13.length; ++var8) {
               var5[var8] = var13[var8].getType();
               var6[var8] = var13[var8].getAccessor();
               var6[var8].setAccessible(true);
               Object var9 = var6[var8].invoke(var12);
               if (var7 < 0 && var9 instanceof Optional) {
                  Optional var10 = (Optional)var9;
                  if (var10.isPresent() && var10.get().getClass().getName().equals("net.minecraft.class_5321")) {
                     var7 = var8;
                  }
               }
            }

            if (var7 < 0) {
               return;
            }

            Constructor var19 = var12.getClass().getDeclaredConstructor(var5);
            var19.setAccessible(true);
            eqCls = var12.getClass();
            eqCtor = var19;
            eqAcc = var6;
            assetIdx = var7;
         }

         Object[] var14 = new Object[eqAcc.length];

         for(int var15 = 0; var15 < var14.length; ++var15) {
            var14[var15] = eqAcc[var15].invoke(var12);
         }

         Optional var16 = (Optional)var14[assetIdx];
         if (var16.isEmpty()) {
            return;
         }

         Matcher var17 = KEY.matcher(String.valueOf(var16.get()));
         if (!var17.find() || !var17.group(2).contains("elytra") || var17.group(2).startsWith("luxvisuals")) {
            return;
         }

         Object var18 = keyOfRegistry.invoke((Object)null, class_2960.method_60654(var17.group(1)));
         var14[assetIdx] = Optional.of(keyOf.invoke((Object)null, var18, class_2960.method_60654("luxvisuals:" + var1)));
         setComp.invoke(var2, eqType, eqCtor.newInstance(var14));
         APPLIED.add(new Object[]{var2, var12});
      } catch (Throwable var11) {
      }

   }
}
