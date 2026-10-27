package code.miaware.client.features.commands.arguments;

import code.miaware.api.system.configs.Kd4542f1791364048a9;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import net.minecraft.class_2172;

public class Ka0f32fbc0c9a7c1d9d implements ArgumentType<String> {
   public static Ka0f32fbc0c9a7c1d9d create() {
      return new Ka0f32fbc0c9a7c1d9d();
   }

   public String parse(StringReader reader) {
      try {
         return reader.readString();
      } catch (CommandSyntaxException e) {
         throw new RuntimeException(e);
      }
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
      return class_2172.method_9265(Kd4542f1791364048a9.getInstance().getConfigsNames(), builder);
   }

   public Collection<String> getExamples() {
      List<String> examples = (List)Kd4542f1791364048a9.getInstance().getConfigsNames().stream().limit(5L).collect(Collectors.toList());
      return examples.isEmpty() ? List.of("eva", "donyka", "sex", "swag", "paris") : examples;
   }
}
