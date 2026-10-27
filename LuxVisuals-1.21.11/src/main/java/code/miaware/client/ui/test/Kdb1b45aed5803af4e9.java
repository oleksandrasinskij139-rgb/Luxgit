package code.miaware.client.ui.test;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;

public class Kdb1b45aed5803af4e9 extends class_437 implements K4600bef438dafc1a9c {
   public Kdb1b45aed5803af4e9() {
      super(class_2561.method_43470("Shader Test"));
      System.out.println("[ShaderTest] Screen created!");
   }

   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
      System.out.println("[ShaderTest] render() called!");
      this.method_25420(context, mouseX, mouseY, delta);
      context.method_25300(this.field_22793, "=== SHADER TEST SCREEN ===", this.field_22789 / 2, this.field_22790 / 2 - 20, 16777215);
      context.method_25300(this.field_22793, "Press ESC to close", this.field_22789 / 2, this.field_22790 / 2 + 20, 16746632);
      super.method_25394(context, mouseX, mouseY, delta);
   }

   public boolean method_25421() {
      return false;
   }

   public void method_25426() {
      super.method_25426();
      System.out.println("[ShaderTest] Screen initialized! Size: " + this.field_22789 + "x" + this.field_22790);
      (new Thread(() -> {
         for(int i = 0; i < 10; ++i) {
            try {
               Thread.sleep(1000L);
               System.out.println("[ShaderTest] Check #" + i + " - Current screen: " + (mc.field_1755 != null ? mc.field_1755.getClass().getSimpleName() : "null"));
            } catch (InterruptedException var2) {
               break;
            }
         }

      })).start();
   }

   public void method_25419() {
      System.out.println("[ShaderTest] Screen closing!");
      super.method_25419();
   }
}
