package code.miaware.client.features.commands;

import code.miaware.api.command.CommandRegister;
import code.miaware.api.command.Kcba974810ca05b56ea;
import code.miaware.client.ui.test.Kdb1b45aed5803af4e9;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_2172;

@CommandRegister(
   name = "shadertest"
)
public class K7f50010e3e9d5c5ce9 extends Kcba974810ca05b56ea {
   public void execute(LiteralArgumentBuilder<class_2172> builder) {
      builder.executes((context) -> {
         System.out.println("[CommandShaderTest] Command executed!");
         mc.execute(() -> {
            System.out.println("[CommandShaderTest] Opening shader test screen...");
            Kdb1b45aed5803af4e9 screen = new Kdb1b45aed5803af4e9();
            mc.method_1507(screen);
            System.out.println("[CommandShaderTest] Screen set! Current screen: " + String.valueOf(mc.field_1755));
         });
         return this.SINGLE_SUCCESS;
      });
   }
}
