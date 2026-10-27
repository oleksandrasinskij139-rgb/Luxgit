package code.miaware.client.features.commands.arguments;

import code.miaware.api.system.configs.K8855472f0415e5af4f;
import code.miaware.api.system.configs.Kae5f6f8dfd322901c3;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class K57e572a9489694aae2 implements ArgumentType<String> {
   public String parse(StringReader reader) throws CommandSyntaxException {
      return StringArgumentType.string().parse(reader);
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
      List<String> macroNames = Kae5f6f8dfd322901c3.getInstance().getMacros().stream().map(K8855472f0415e5af4f::getName).toList();
      String remaining = builder.getRemaining().toLowerCase();
      Stream var10000 = macroNames.stream().filter((name) -> name.toLowerCase().startsWith(remaining));
      Objects.requireNonNull(builder);
      var10000.forEach(builder::suggest);
      return builder.buildFuture();
   }

   public Collection<String> getExamples() {
      return List.of("home", "login", "tp");
   }
}
