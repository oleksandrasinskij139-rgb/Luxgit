package code.miaware.client.features.commands;

import code.miaware.api.command.CommandRegister;
import code.miaware.api.command.Kcba974810ca05b56ea;
import code.miaware.client.network.K204c47a3480aa2d86b;
import code.miaware.client.network.K3e5d28a93090bc4a75;
import code.miaware.client.network.K8ee912418ef60c15b7;
import code.miaware.client.ui.clickgui.ScreenIRCGui;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.List;
import net.minecraft.class_2172;

@CommandRegister(
   name = "irc"
)
public class K0e0a9aacde27957005 extends Kcba974810ca05b56ea {
   public void execute(LiteralArgumentBuilder<class_2172> builder) {
      builder.then(literal("gui").executes((context) -> {
         System.out.println("[CommandIRC] GUI команда вызвана!");
         mc.execute(() -> mc.execute(() -> {
               System.out.println("[CommandIRC] Открываем IRC GUI");
               ScreenIRCGui screen = ScreenIRCGui.getInstance();
               mc.method_1507(screen);
               System.out.println("[CommandIRC] GUI открыт: " + (mc.field_1755 instanceof ScreenIRCGui));
            }));
         return this.SINGLE_SUCCESS;
      }));
      builder.then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)literal("friend").then(literal("add").then(argument("target", StringArgumentType.greedyString()).executes((context) -> {
         String target = StringArgumentType.getString(context, "target").trim();
         if (target.isEmpty()) {
            this.print("§c[IRC] §fУкажите юзера: §e.irc friend add @tag §7или §e.irc friend add nickname");
            return this.SINGLE_SUCCESS;
         } else {
            K3e5d28a93090bc4a75.getInstance().sendAddIRCFriendRequest(target);
            this.print("§a[IRC] §fОтправлен запрос на добавление: §e" + target);
            return this.SINGLE_SUCCESS;
         }
      })))).then(literal("remove").then(argument("target", StringArgumentType.greedyString()).executes((context) -> {
         String target = StringArgumentType.getString(context, "target").trim();
         if (target.isEmpty()) {
            this.print("§c[IRC] §fУкажите юзера для удаления");
            return this.SINGLE_SUCCESS;
         } else {
            K3e5d28a93090bc4a75.getInstance().sendRemoveIRCFriendRequest(target);
            this.print("§a[IRC] §fОтправлен запрос на удаление: §e" + target);
            return this.SINGLE_SUCCESS;
         }
      })))).then(literal("clear").executes((context) -> {
         List<K204c47a3480aa2d86b> friends = K8ee912418ef60c15b7.getInstance().getIRCFriends();
         if (friends.isEmpty()) {
            this.print("§a[IRC] §fУ вас нет IRC друзей");
         } else {
            for(K204c47a3480aa2d86b friend : friends) {
               K3e5d28a93090bc4a75.getInstance().sendRemoveIRCFriendRequest(friend.getUsername());
            }

            this.print("§a[IRC] §fУдалено §e" + friends.size() + " §fIRC друзей");
         }

         return this.SINGLE_SUCCESS;
      }))).then(literal("list").executes((context) -> {
         List<K204c47a3480aa2d86b> friends = K8ee912418ef60c15b7.getInstance().getIRCFriends();
         if (friends.isEmpty()) {
            this.print("§a[IRC] §fУ вас нет IRC друзей");
         } else {
            this.print("§a[IRC] §fВаши IRC друзья §7(" + friends.size() + ")§f:");

            for(K204c47a3480aa2d86b friend : friends) {
               String displayName = friend.getDisplayName();
               String status = friend.isOnline() ? "§aонлайн" : "§7оффлайн";
               this.print("  §e" + displayName + " §8[" + status + "§8]");
            }
         }

         return this.SINGLE_SUCCESS;
      })));
      builder.executes((context) -> {
         this.print("§a[IRC] §fИспользование:");
         this.print("§e  .irc gui §7- Открыть IRC чат");
         this.print("§e  .irc friend add <@tag/nickname> §7- Добавить IRC друга");
         this.print("§e  .irc friend remove <@tag/nickname> §7- Удалить IRC друга");
         this.print("§e  .irc friend clear §7- Удалить всех IRC друзей");
         this.print("§e  .irc friend list §7- Список IRC друзей");
         return this.SINGLE_SUCCESS;
      });
   }
}
