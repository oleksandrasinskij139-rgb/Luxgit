package code.miaware.api.command;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import lombok.Generated;
import net.minecraft.class_2172;

public abstract class Kcba974810ca05b56ea implements K4600bef438dafc1a9c {
   private final String name;
   public int SINGLE_SUCCESS = 1;

   public Kcba974810ca05b56ea() {
      CommandRegister metadata = (CommandRegister)this.getClass().getAnnotation(CommandRegister.class);
      this.name = metadata.name();
   }

   public abstract void execute(LiteralArgumentBuilder<class_2172> var1);

   public final void register(CommandDispatcher<class_2172> dispatcher) {
      LiteralArgumentBuilder<class_2172> builder = LiteralArgumentBuilder.literal(this.name);
      this.execute(builder);
      dispatcher.register(builder);
   }

   public static <T> RequiredArgumentBuilder<class_2172, T> argument(String name, ArgumentType<T> type) {
      return RequiredArgumentBuilder.argument(name, type);
   }

   public static LiteralArgumentBuilder<class_2172> literal(String name) {
      return LiteralArgumentBuilder.literal(name);
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public int getSINGLE_SUCCESS() {
      return this.SINGLE_SUCCESS;
   }
}
