package code.miaware.client.features.commands;

import code.miaware.api.command.CommandRegister;
import code.miaware.api.command.Kcba974810ca05b56ea;
import code.miaware.api.system.client.K908217fb7543df2740;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_2172;
import org.joml.Vector2i;

@CommandRegister(
   name = "gps"
)
public class K1382f97539fdf687f7 extends Kcba974810ca05b56ea {
   public void execute(LiteralArgumentBuilder<class_2172> builder) {
      builder.then(literal("off").executes((context) -> {
         if (K908217fb7543df2740.getInstance().getGpsPosition() == null) {
            this.print("GPS route is already disabled.");
         } else {
            K908217fb7543df2740.getInstance().setGpsPosition((Vector2i)null);
            this.print("GPS route removed.");
         }

         return this.SINGLE_SUCCESS;
      }));
      builder.then(argument("x", IntegerArgumentType.integer()).then(argument("z", IntegerArgumentType.integer()).executes((context) -> {
         int x = IntegerArgumentType.getInteger(context, "x");
         int z = IntegerArgumentType.getInteger(context, "z");
         return this.K7a19cc8b96fbab2193(x, z);
      })));
   }

   private int K7a19cc8b96fbab2193(int x, int z) {
      if (x == 0 && z == 0) {
         this.print("Zero coordinates are not allowed for GPS.");
      } else {
         Vector2i newPos = new Vector2i(x, z);
         if (K908217fb7543df2740.getInstance().getGpsPosition() != null && K908217fb7543df2740.getInstance().getGpsPosition().equals(newPos)) {
            this.print("This GPS point is already active.");
         } else {
            K908217fb7543df2740.getInstance().setGpsPosition(newPos);
            this.print("GPS set to: " + newPos.x + " " + newPos.y);
         }
      }

      return this.SINGLE_SUCCESS;
   }
}
