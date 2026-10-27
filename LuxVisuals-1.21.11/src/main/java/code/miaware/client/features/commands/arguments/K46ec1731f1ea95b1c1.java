package code.miaware.client.features.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class K46ec1731f1ea95b1c1 implements ArgumentType<String> {
   public static K46ec1731f1ea95b1c1 create() {
      return new K46ec1731f1ea95b1c1();
   }

   public String parse(StringReader reader) throws CommandSyntaxException {
      int start = reader.getCursor();

      while(reader.canRead() && this.K7b460216b7f9369a4e(reader.peek())) {
         reader.skip();
      }

      return reader.getString().substring(start, reader.getCursor());
   }

   private boolean K7b460216b7f9369a4e(char c) {
      return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9' || c == ':' || c == '_' || c == '-' || c == '.' || c == '/';
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
      return builder.buildFuture();
   }

   public Collection<String> getExamples() {
      return List.of("minecraft:diamond", "minecraft:totem_of_undying", "diamond", "totem_of_undying");
   }
}
