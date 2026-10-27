package code.miaware.client.features.commands.arguments;

import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import net.minecraft.class_2172;
import net.minecraft.class_2561;

public class Kb5e4bc91d7fb084fe6 implements ArgumentType<String> {
   public static Kb5e4bc91d7fb084fe6 create() {
      return new Kb5e4bc91d7fb084fe6();
   }

   public String parse(StringReader reader) {
      String friend = null;

      try {
         friend = reader.readString();
      } catch (CommandSyntaxException e) {
         throw new RuntimeException(e);
      }

      if (!K137d4e93c7270b0a04.getInstance().contains(friend)) {
         try {
            throw (new DynamicCommandExceptionType((name) -> class_2561.method_43470("Друга с именем " + String.valueOf(name) + " не найдено"))).create(friend);
         } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
         }
      } else {
         return friend;
      }
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
      return class_2172.method_9265(K137d4e93c7270b0a04.getInstance().getData(), builder);
   }

   public Collection<String> getExamples() {
      List<String> examples = (List)K137d4e93c7270b0a04.getInstance().getData().stream().limit(5L).collect(Collectors.toList());
      return examples.isEmpty() ? List.of("eva", "donyka", "sex", "swag", "paris") : examples;
   }
}
