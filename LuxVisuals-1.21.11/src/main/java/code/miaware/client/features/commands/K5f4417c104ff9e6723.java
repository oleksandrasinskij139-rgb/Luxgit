package code.miaware.client.features.commands;

import code.miaware.api.command.CommandRegister;
import code.miaware.api.command.Kcba974810ca05b56ea;
import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import code.miaware.client.features.commands.arguments.K9160e2463dd3f94a56;
import code.miaware.client.features.commands.arguments.Kb5e4bc91d7fb084fe6;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_2172;

@CommandRegister(
   name = "friend"
)
public class K5f4417c104ff9e6723 extends Kcba974810ca05b56ea {
   public void execute(LiteralArgumentBuilder<class_2172> builder) {
      builder.then(literal("clear").executes((context) -> {
         if (!K137d4e93c7270b0a04.getInstance().getData().isEmpty()) {
            K137d4e93c7270b0a04.getInstance().clear();
            this.print("Friend list cleared.");
         } else {
            this.print("Friend list is empty.");
         }

         return this.SINGLE_SUCCESS;
      }));
      builder.then(literal("list").executes((context) -> {
         if (K137d4e93c7270b0a04.getInstance().getData().isEmpty()) {
            this.print("Friend list is empty.");
         } else {
            try {
               String friends = String.join(", ", K137d4e93c7270b0a04.getInstance().getData());
               this.print("Friends: " + friends);
            } catch (Exception var3) {
               this.print("Failed to read the friend list.");
            }
         }

         return this.SINGLE_SUCCESS;
      }));
      builder.then(literal("add").then(argument("player", K9160e2463dd3f94a56.create()).executes((context) -> {
         String nickname = (String)context.getArgument("player", String.class);
         if (K137d4e93c7270b0a04.getInstance().contains(nickname)) {
            this.print("Player is already in your friend list: " + nickname);
         } else {
            K137d4e93c7270b0a04.getInstance().add(nickname);
            this.print("Added friend: " + nickname);
         }

         return this.SINGLE_SUCCESS;
      })));
      builder.then(literal("remove").then(argument("player", Kb5e4bc91d7fb084fe6.create()).executes((context) -> {
         String nickname = (String)context.getArgument("player", String.class);
         if (!K137d4e93c7270b0a04.getInstance().contains(nickname)) {
            this.print("Player is not in your friend list: " + nickname);
         } else {
            K137d4e93c7270b0a04.getInstance().remove(nickname);
            this.print("Removed friend: " + nickname);
         }

         return this.SINGLE_SUCCESS;
      })));
   }
}
