package luxvisuals.customsword;

import java.util.Collections;
import java.util.List;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_9280;
import net.minecraft.class_9331;
import net.minecraft.class_9334;

public final class CustomSwordRuntime {
   private static final ThreadLocal<State> STATE = ThreadLocal.withInitial(() -> null);
   private static final class_9331 CUSTOM_MODEL_DATA;

   private static boolean isTarget(class_1799 var0) {
      try {
         class_1792 var1 = var0.method_7909();
         return var1 == class_1802.field_8802 || var1 == class_1802.field_22022;
      } catch (Throwable var2) {
         return false;
      }
   }

   public static void before(class_1799 var0) {
      CustomSword var1 = CustomSword.INSTANCE;
      if (var1 != null && var1.isToggled() && isTarget(var0)) {
         Object var2 = var0.method_57824(CUSTOM_MODEL_DATA);

         try {
            String var3 = CustomSword.selectedName();
            class_9280 var4 = new class_9280(Collections.emptyList(), Collections.emptyList(), List.of(var3), Collections.emptyList());
            var0.method_57379(CUSTOM_MODEL_DATA, var4);
            STATE.set(new State(var0, var2));
         } catch (Throwable var5) {
         }

      }
   }

   public static void after(class_1799 var0) {
      State var1 = (State)STATE.get();
      STATE.remove();
      if (var1 != null && var1.stack == var0) {
         try {
            var0.method_57379(CUSTOM_MODEL_DATA, var1.oldValue);
         } catch (Throwable var3) {
         }

      }
   }

   static {
      CUSTOM_MODEL_DATA = class_9334.field_49637;
   }

   public static record State(class_1799 stack, Object oldValue) {
   }
}
