package code.miaware.client.features.commands;

import code.miaware.api.command.CommandRegister;
import code.miaware.api.command.Kcba974810ca05b56ea;
import code.miaware.api.system.backend.Kd9271a319f911decbe;
import code.miaware.api.system.configs.Kae5f6f8dfd322901c3;
import code.miaware.client.features.commands.arguments.K047d904c2fd7b9e1da;
import code.miaware.client.features.commands.arguments.K57e572a9489694aae2;
import code.miaware.client.features.commands.arguments.Kd4604f8c54a274d882;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_124;
import net.minecraft.class_2172;

@CommandRegister(
   name = "macro"
)
public class K05adec9baa81afbf47 extends Kcba974810ca05b56ea {
   private final Kae5f6f8dfd322901c3 macroManager = Kae5f6f8dfd322901c3.getInstance();

   public void execute(LiteralArgumentBuilder<class_2172> builder) {
      builder.then(literal("add").then(argument("name", new K047d904c2fd7b9e1da()).then(argument("key", new Kd4604f8c54a274d882()).then(argument("message", StringArgumentType.greedyString()).executes((context) -> {
         String name = StringArgumentType.getString(context, "name");
         String keyName = StringArgumentType.getString(context, "key");
         String message = StringArgumentType.getString(context, "message");
         int keyCode = Kd9271a319f911decbe.getBind(keyName);
         if (keyCode == -1) {
            this.print("Unknown key: " + keyName);
            return 0;
         } else if (this.macroManager.has(name)) {
            this.print("Macro already exists: " + name);
            return 0;
         } else {
            this.macroManager.add(name, message, keyCode);
            this.print("Added macro " + name + " on " + keyName + " -> " + message);
            return this.SINGLE_SUCCESS;
         }
      })))));
      builder.then(literal("remove").then(argument("name", new K57e572a9489694aae2()).executes((context) -> {
         String name = StringArgumentType.getString(context, "name");
         if (!this.macroManager.has(name)) {
            this.print("Macro not found: " + name);
            return 0;
         } else {
            this.macroManager.remove(name);
            this.print("Removed macro: " + name);
            return this.SINGLE_SUCCESS;
         }
      })));
      builder.then(literal("clear").executes((context) -> {
         this.macroManager.getMacros().clear();
         this.print("All macros removed.");
         return this.SINGLE_SUCCESS;
      }));
      builder.then(literal("list").executes((context) -> {
         if (this.macroManager.getMacros().isEmpty()) {
            this.print("Macro list is empty.");
            return 0;
         } else {
            this.macroManager.getMacros().forEach((macro) -> {
               String var10001 = String.valueOf(class_124.field_1080);
               this.print("Name: " + var10001 + macro.getName() + String.valueOf(class_124.field_1070) + ", Message: " + String.valueOf(class_124.field_1080) + macro.getMessage() + String.valueOf(class_124.field_1070) + ", Key: " + String.valueOf(class_124.field_1080) + Kd9271a319f911decbe.getBind(macro.getKey()));
            });
            return this.SINGLE_SUCCESS;
         }
      }));
   }
}
