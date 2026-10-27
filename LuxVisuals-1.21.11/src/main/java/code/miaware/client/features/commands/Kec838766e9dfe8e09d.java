package code.miaware.client.features.commands;

import code.miaware.api.command.CommandRegister;
import code.miaware.api.command.Kcba974810ca05b56ea;
import code.miaware.api.utils.system.Kfd81e9a6450e4cf2de;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_124;
import net.minecraft.class_2172;
import org.lwjgl.glfw.GLFW;

@CommandRegister(
   name = "hwid"
)
public class Kec838766e9dfe8e09d extends Kcba974810ca05b56ea {
   public void execute(LiteralArgumentBuilder<class_2172> builder) {
      builder.executes((context) -> {
         String hwid = Kfd81e9a6450e4cf2de.getHWID();

         try {
            long window = mc.method_22683().method_4490();
            GLFW.glfwSetClipboardString(window, hwid);
            this.print(String.valueOf(class_124.field_1080) + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            String var6 = String.valueOf(class_124.field_1075);
            this.print(var6 + "Твой HWID: " + String.valueOf(class_124.field_1068) + hwid);
            this.print(String.valueOf(class_124.field_1060) + "✓ HWID скопирован в буфер обмена!");
            this.print(String.valueOf(class_124.field_1080) + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            var6 = String.valueOf(class_124.field_1054);
            this.print(var6 + "Подсказка: " + String.valueOf(class_124.field_1080) + "Используй " + String.valueOf(class_124.field_1068) + ".hwid help" + String.valueOf(class_124.field_1080) + " для инструкции");
         } catch (Exception var5) {
            this.print(String.valueOf(class_124.field_1080) + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            String var10001 = String.valueOf(class_124.field_1075);
            this.print(var10001 + "Твой HWID: " + String.valueOf(class_124.field_1068) + hwid);
            this.print(String.valueOf(class_124.field_1061) + "✗ Не удалось скопировать в буфер обмена");
            this.print(String.valueOf(class_124.field_1080) + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
         }

         return this.SINGLE_SUCCESS;
      });
      builder.then(literal("help").executes((context) -> {
         this.print(String.valueOf(class_124.field_1080) + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
         String var10001 = String.valueOf(class_124.field_1065);
         this.print(var10001 + String.valueOf(class_124.field_1067) + "Как получить кастомную роль:");
         this.print("");
         var10001 = String.valueOf(class_124.field_1054);
         this.print(var10001 + "1. " + String.valueOf(class_124.field_1068) + "Скопируй свой HWID командой " + String.valueOf(class_124.field_1075) + ".hwid");
         var10001 = String.valueOf(class_124.field_1054);
         this.print(var10001 + "2. " + String.valueOf(class_124.field_1068) + "Отправь его администратору в Discord");
         var10001 = String.valueOf(class_124.field_1054);
         this.print(var10001 + "3. " + String.valueOf(class_124.field_1068) + "Дождись назначения роли");
         var10001 = String.valueOf(class_124.field_1054);
         this.print(var10001 + "4. " + String.valueOf(class_124.field_1068) + "Перезапусти клиент");
         this.print("");
         var10001 = String.valueOf(class_124.field_1080);
         this.print(var10001 + "Доступные роли: " + String.valueOf(class_124.field_1060) + "User" + String.valueOf(class_124.field_1080) + ", " + String.valueOf(class_124.field_1078) + "Friend" + String.valueOf(class_124.field_1080) + ", " + String.valueOf(class_124.field_1076) + "Developer" + String.valueOf(class_124.field_1080) + ", " + String.valueOf(class_124.field_1061) + "Owner");
         this.print(String.valueOf(class_124.field_1080) + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
         return this.SINGLE_SUCCESS;
      }));
   }
}
