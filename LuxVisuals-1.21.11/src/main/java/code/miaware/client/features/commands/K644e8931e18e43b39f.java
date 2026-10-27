package code.miaware.client.features.commands;

import code.miaware.api.command.CommandRegister;
import code.miaware.api.command.Kcba974810ca05b56ea;
import code.miaware.api.system.client.K33c58dee118bf41a95;
import code.miaware.api.system.client.K6ac827b634fa8bac43;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_2172;

@CommandRegister(
   name = "way"
)
public class K644e8931e18e43b39f extends Kcba974810ca05b56ea {
   public void execute(LiteralArgumentBuilder<class_2172> builder) {
      builder.then(literal("add").then(argument("name", StringArgumentType.word()).then(argument("x", IntegerArgumentType.integer()).then(argument("y", IntegerArgumentType.integer()).then(argument("z", IntegerArgumentType.integer()).executes((context) -> {
         String name = StringArgumentType.getString(context, "name");
         int x = IntegerArgumentType.getInteger(context, "x");
         int y = IntegerArgumentType.getInteger(context, "y");
         int z = IntegerArgumentType.getInteger(context, "z");
         boolean exists = K33c58dee118bf41a95.getInstance().getWaypoint(name) != null;
         if (exists) {
            K33c58dee118bf41a95.getInstance().removeWaypoint(name);
         }

         K33c58dee118bf41a95.getInstance().addWaypoint(name, x, y, z);
         if (exists) {
            this.print("Updated waypoint '" + name + "' at " + x + " " + y + " " + z);
         } else {
            this.print("Added waypoint '" + name + "' at " + x + " " + y + " " + z);
         }

         return this.SINGLE_SUCCESS;
      }))))));
      builder.then(literal("remove").then(argument("name", StringArgumentType.word()).executes((context) -> {
         String name = StringArgumentType.getString(context, "name");
         if (K33c58dee118bf41a95.getInstance().getWaypoint(name) == null) {
            this.print("Waypoint '" + name + "' not found!");
            return this.SINGLE_SUCCESS;
         } else {
            K33c58dee118bf41a95.getInstance().removeWaypoint(name);
            this.print("Removed waypoint '" + name + "'");
            return this.SINGLE_SUCCESS;
         }
      })));
      builder.then(literal("clear").executes((context) -> {
         int count = K33c58dee118bf41a95.getInstance().getWaypoints().size();
         K33c58dee118bf41a95.getInstance().clearWaypoints();
         this.print("Cleared " + count + " waypoint(s)");
         return this.SINGLE_SUCCESS;
      }));
      builder.then(literal("list").executes((context) -> {
         if (K33c58dee118bf41a95.getInstance().getWaypoints().isEmpty()) {
            this.print("No waypoints");
            return this.SINGLE_SUCCESS;
         } else {
            this.print("Waypoints:");

            for(K6ac827b634fa8bac43 waypoint : K33c58dee118bf41a95.getInstance().getWaypoints()) {
               String var10001 = waypoint.getName();
               this.print("  " + var10001 + " - " + waypoint.getPosition().x + " " + waypoint.getPosition().y + " " + waypoint.getPosition().z);
            }

            return this.SINGLE_SUCCESS;
         }
      }));
   }
}
