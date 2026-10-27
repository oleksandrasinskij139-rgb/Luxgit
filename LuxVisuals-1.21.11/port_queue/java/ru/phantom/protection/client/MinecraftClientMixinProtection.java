package ru.phantom.protection.client;

import II1II1II1II1II1II1II1II1.I7_i6.i1iIi1l1I1lIIl_1II_1__iliil11_;
import II1II1II1II1II1II1II1II1.l3_I2.Ii_iI1iiII1__I111IliI1;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.phantom.Phantom;

public class MinecraftClientMixinProtection {
   @Ii_iI1iiII1__I111IliI1(
      type = i1iIi1l1I1lIIl_1II_1__iliil11_.MUTATION
   )
   public static void init() {
      Phantom.INSTANCE.initialize();
   }

   @Ii_iI1iiII1__I111IliI1(
      type = i1iIi1l1I1lIIl_1II_1__iliil11_.MUTATION
   )
   public static void shutdown() {
      Phantom.INSTANCE.shutdown();
   }

   public static void updateTitle(CallbackInfoReturnable<String> cir) {
      String title = "%s %s (%s)".formatted("Phantom", "5.0", "Beta");
      cir.setReturnValue(title);
   }
}
