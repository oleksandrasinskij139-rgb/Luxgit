package code.miaware.client.features.commands;

import code.miaware.api.command.CommandRegister;
import code.miaware.api.command.Kcba974810ca05b56ea;
import code.miaware.api.system.backend.K8e471a90dc4db42e81;
import code.miaware.api.system.backend.K93d0ef925a2fcdf0d6;
import code.miaware.api.system.configs.Kd4542f1791364048a9;
import code.miaware.client.features.commands.arguments.K5636eb578c7a6eb384;
import code.miaware.client.features.commands.arguments.Ka0f32fbc0c9a7c1d9d;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.Collection;
import net.minecraft.class_2172;

@CommandRegister(
   name = "config"
)
public class Kda95e3894f09ed4a55 extends Kcba974810ca05b56ea {
   public void execute(LiteralArgumentBuilder<class_2172> builder) {
      builder.then(literal("list").executes((context) -> {
         Collection<String> configs = Kd4542f1791364048a9.getInstance().getConfigsNames();
         if (configs.isEmpty()) {
            this.print("Config list is empty.");
         } else {
            this.print("Configs: " + String.join(", ", configs));
         }

         return this.SINGLE_SUCCESS;
      }));
      builder.then(literal("remove").then(argument("config", K5636eb578c7a6eb384.create()).executes((context) -> {
         String config = StringArgumentType.getString(context, "config");
         if (Kd4542f1791364048a9.getInstance().exists(config)) {
            Kd4542f1791364048a9.getInstance().remove(config);
            this.print("Removed config: " + config);
         } else {
            this.print("Config not found.");
         }

         return this.SINGLE_SUCCESS;
      })));
      builder.then(literal("load").then(argument("config", K5636eb578c7a6eb384.create()).executes((context) -> {
         String config = StringArgumentType.getString(context, "config");
         Kd4542f1791364048a9.getInstance().load(config);
         if (Kd4542f1791364048a9.getInstance().exists(config)) {
            this.print("Loaded config: " + config);
         } else {
            this.print("Config not found.");
         }

         return this.SINGLE_SUCCESS;
      })));
      builder.then(literal("save").then(argument("config", Ka0f32fbc0c9a7c1d9d.create()).executes((context) -> {
         String config = StringArgumentType.getString(context, "config");
         String safeConfig = Kd4542f1791364048a9.getInstance().sanitizeConfigName(config);
         Kd4542f1791364048a9.getInstance().save(config);
         this.print("Saved config: " + safeConfig);
         return this.SINGLE_SUCCESS;
      })));
      builder.then(literal("folder").executes((context) -> {
         if (K8e471a90dc4db42e81.openFolder(K93d0ef925a2fcdf0d6.CONFIG_PATH_MAIN)) {
            this.print("Opening config folder...");
         } else {
            this.print("Failed to open config folder.");
         }

         return this.SINGLE_SUCCESS;
      }));
   }
}
