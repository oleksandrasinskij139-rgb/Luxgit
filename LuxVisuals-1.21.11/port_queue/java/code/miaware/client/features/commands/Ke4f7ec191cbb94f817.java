package code.miaware.client.features.commands;

import code.miaware.api.command.CommandRegister;
import code.miaware.api.command.Kcba974810ca05b56ea;
import code.miaware.client.features.commands.arguments.K46ec1731f1ea95b1c1;
import code.miaware.client.features.modules.render.K58d4d66f061a2a6fed;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_2172;

@CommandRegister(
   name = "itemesp"
)
public class Ke4f7ec191cbb94f817 extends Kcba974810ca05b56ea {
   public void execute(LiteralArgumentBuilder<class_2172> builder) {
      K58d4d66f061a2a6fed module = K58d4d66f061a2a6fed.getInstance();
      builder.then(literal("add").then(argument("item_id", K46ec1731f1ea95b1c1.create()).suggests(this::K92e39e96322e963ff4).executes((context) -> {
         String itemId = (String)context.getArgument("item_id", String.class);
         if (!itemId.contains(":")) {
            itemId = "minecraft:" + itemId;
         }

         module.addItem(itemId);
         return this.SINGLE_SUCCESS;
      })));
      builder.then(literal("remove").then(argument("item_id", K46ec1731f1ea95b1c1.create()).suggests(this::Kaccb166ab474cc050c).executes((context) -> {
         String itemId = (String)context.getArgument("item_id", String.class);
         if (!itemId.contains(":")) {
            itemId = "minecraft:" + itemId;
         }

         module.removeItem(itemId);
         return this.SINGLE_SUCCESS;
      })));
      builder.then(literal("clear").executes((context) -> {
         module.clearItems();
         return this.SINGLE_SUCCESS;
      }));
      builder.then(literal("list").executes((context) -> {
         if (module.getTrackedItems().isEmpty()) {
            this.print("§a[ItemESP] §fСписок отслеживаемых предметов пуст");
         } else {
            this.print("§a[ItemESP] §fОтслеживаемые предметы:");

            for(String item : module.getTrackedItems()) {
               this.print("§7  - §e" + item);
            }
         }

         return this.SINGLE_SUCCESS;
      }));
   }

   private CompletableFuture<Suggestions> K92e39e96322e963ff4(CommandContext<class_2172> context, SuggestionsBuilder builder) {
      String partial = builder.getRemaining().toLowerCase();
      K58d4d66f061a2a6fed module = K58d4d66f061a2a6fed.getInstance();

      for(String suggestion : module.getItemSuggestions(partial)) {
         builder.suggest(suggestion);
      }

      return builder.buildFuture();
   }

   private CompletableFuture<Suggestions> Kaccb166ab474cc050c(CommandContext<class_2172> context, SuggestionsBuilder builder) {
      String partial = builder.getRemaining().toLowerCase();
      K58d4d66f061a2a6fed module = K58d4d66f061a2a6fed.getInstance();

      for(String item : module.getTrackedItems()) {
         if (item.toLowerCase().contains(partial)) {
            builder.suggest(item);
         }
      }

      return builder.buildFuture();
   }
}
