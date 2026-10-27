package code.miaware.client.features.commands.arguments;

import code.miaware.api.system.configs.Kd4542f1791364048a9;
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

public class K5636eb578c7a6eb384 implements ArgumentType<String> {
   public static K5636eb578c7a6eb384 create() {
      return new K5636eb578c7a6eb384();
   }

   public String parse(StringReader reader) {
      String configName = null;

      try {
         configName = reader.readString();
      } catch (CommandSyntaxException e) {
         throw new RuntimeException(e);
      }

      if (!Kd4542f1791364048a9.getInstance().getConfigsNames().contains(configName)) {
         try {
            throw (new DynamicCommandExceptionType((name) -> class_2561.method_43470("Конфиг с именем " + String.valueOf(name) + " не найден"))).create(configName);
         } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
         }
      } else {
         return configName;
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
