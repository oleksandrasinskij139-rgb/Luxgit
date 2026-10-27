package code.miaware.client.ui.theme;

import net.minecraft.class_1041;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_437;

public final class K3195adfcc0c0135ece {
   private static final Kfa308fa591bea5a311 renderer = new Kfa308fa591bea5a311();
   private static class_437 screen;
   private static boolean failed;

   private K3195adfcc0c0135ece() {
   }

   public static void begin(int tint) {
      class_310 mc = class_310.method_1551();
      if (!failed && mc != null && mc.field_1755 != null && mc.method_18854()) {
         class_1041 window = mc.method_22683();
         if (window != null && window.method_4480() > 0 && window.method_4507() > 0) {
            try {
               class_276 target = mc.method_1522();
               renderer.begin(target.field_1476, target.field_1482, target.field_1481, (float)(mc.field_1729.method_1603() / (double)window.method_4480()), (float)(mc.field_1729.method_1604() / (double)window.method_4507()), tint, System.nanoTime());
               screen = mc.field_1755;
            } catch (RuntimeException error) {
               K117aa4eb05c8a40a70(error);
            }

         }
      }
   }

   public static void render() {
      if (renderer.active()) {
         class_310 mc = class_310.method_1551();
         if (mc.field_1755 != screen) {
            renderer.cancel();
            screen = null;
         } else {
            try {
               class_276 target = mc.method_1522();
               renderer.render(target.field_1482, target.field_1481, System.nanoTime());
               if (!renderer.active()) {
                  screen = null;
               }
            } catch (RuntimeException error) {
               K117aa4eb05c8a40a70(error);
            }

         }
      }
   }

   private static void K117aa4eb05c8a40a70(RuntimeException error) {
      renderer.close();
      screen = null;
      failed = true;
      System.err.println("[ThemeTransition] " + error.getMessage());
   }
}
