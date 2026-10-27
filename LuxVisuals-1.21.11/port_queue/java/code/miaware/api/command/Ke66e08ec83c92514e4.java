package code.miaware.api.command;

import code.miaware.client.features.commands.K05adec9baa81afbf47;
import code.miaware.client.features.commands.K0e0a9aacde27957005;
import code.miaware.client.features.commands.K1382f97539fdf687f7;
import code.miaware.client.features.commands.K3b0f6d87510852bb83;
import code.miaware.client.features.commands.K5f4417c104ff9e6723;
import code.miaware.client.features.commands.K644e8931e18e43b39f;
import code.miaware.client.features.commands.K7f50010e3e9d5c5ce9;
import code.miaware.client.features.commands.Kc53aa0905f5a4e3930;
import code.miaware.client.features.commands.Kda95e3894f09ed4a55;
import code.miaware.client.features.commands.Ke4f7ec191cbb94f817;
import code.miaware.client.features.commands.Kec838766e9dfe8e09d;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.minecraft.class_2172;
import net.minecraft.class_310;
import net.minecraft.class_637;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class Ke66e08ec83c92514e4 {
   private static final Ke66e08ec83c92514e4 instance = new Ke66e08ec83c92514e4();
   private final CommandDispatcher<class_2172> dispatcher = new CommandDispatcher();
   private final List<Kcba974810ca05b56ea> commands = new ArrayList();

   public void load() {
      this.register(new Kda95e3894f09ed4a55(), new Kc53aa0905f5a4e3930(), new K5f4417c104ff9e6723(), new K05adec9baa81afbf47(), new K1382f97539fdf687f7(), new K644e8931e18e43b39f(), new K3b0f6d87510852bb83(), new Kec838766e9dfe8e09d(), new K7f50010e3e9d5c5ce9(), new Ke4f7ec191cbb94f817(), new K0e0a9aacde27957005());
   }

   public void register(Kcba974810ca05b56ea... commands) {
      for(Kcba974810ca05b56ea command : commands) {
         command.register(this.dispatcher);
         this.commands.add(command);
      }

   }

   public String getPrefix() {
      return ".";
   }

   public void executeCommands(String message, CallbackInfo ci) {
      if (message.startsWith(this.getPrefix())) {
         try {
            this.getDispatcher().execute(message.substring(this.getPrefix().length()), this.Kac70d4eaf6d8ba83eb());
         } catch (CommandSyntaxException var4) {
         }

         ci.cancel();
      }

   }

   public class_637 getSource() {
      return this.Kac70d4eaf6d8ba83eb();
   }

   private class_637 Kac70d4eaf6d8ba83eb() {
      class_310 client = class_310.method_1551();
      return new class_637(client.method_1562(), client);
   }

   @Generated
   public CommandDispatcher<class_2172> getDispatcher() {
      return this.dispatcher;
   }

   @Generated
   public List<Kcba974810ca05b56ea> getCommands() {
      return this.commands;
   }

   @Generated
   public static Ke66e08ec83c92514e4 getInstance() {
      return instance;
   }
}
