package code.miaware.client.features.commands;

import code.miaware.api.command.CommandRegister;
import code.miaware.api.command.Kcba974810ca05b56ea;
import code.miaware.client.ui.test.K1aa473fe0ce338f657;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_2172;

@CommandRegister(
   name = "icontest"
)
public class Kc662b96fd4c5564b1a extends Kcba974810ca05b56ea {
   public void execute(LiteralArgumentBuilder<class_2172> builder) {
      builder.executes((context) -> {
         mc.execute(() -> {
            K1aa473fe0ce338f657 screen = new K1aa473fe0ce338f657();
            mc.method_1507(screen);
         });
         this.print("Opened icon test screen!");
         return this.SINGLE_SUCCESS;
      });
   }
}
