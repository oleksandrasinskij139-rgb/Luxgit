package code.miaware.client.features.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.concurrent.CompletableFuture;

public class K047d904c2fd7b9e1da implements ArgumentType<String> {
   public static K047d904c2fd7b9e1da create() {
      return new K047d904c2fd7b9e1da();
   }

   public String parse(StringReader r) {
      int start = r.getCursor();

      while(r.canRead() && !Character.isWhitespace(r.peek())) {
         r.skip();
      }

      return r.getString().substring(start, r.getCursor());
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
      return builder.buildFuture();
   }
}
