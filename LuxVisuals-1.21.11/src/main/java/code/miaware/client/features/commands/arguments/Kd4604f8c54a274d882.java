package code.miaware.client.features.commands.arguments;

import code.miaware.api.system.backend.Kd9271a319f911decbe;
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
import java.util.stream.Stream;
import net.minecraft.class_2172;
import net.minecraft.class_2561;
import org.lwjgl.glfw.GLFW;

public class Kd4604f8c54a274d882 implements ArgumentType<String> {
   public static Kd4604f8c54a274d882 create() {
      return new Kd4604f8c54a274d882();
   }

   public String parse(StringReader reader) throws CommandSyntaxException {
      String key = reader.readString();
      if (Kd9271a319f911decbe.getBind(key) == -1) {
         throw (new DynamicCommandExceptionType((name) -> class_2561.method_43470("Клавиша " + name.toString() + " не найдена"))).create(key);
      } else {
         return key;
      }
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
      return class_2172.method_9265((Iterable)Stream.of(GLFW.class.getDeclaredFields()).filter((f) -> f.getName().startsWith("GLFW_KEY_")).map((f) -> f.getName().substring("GLFW_KEY_".length())).collect(Collectors.toList()), builder);
   }

   public Collection<String> getExamples() {
      return List.of("A", "B", "C", "D", "E");
   }
}
