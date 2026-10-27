package code.miaware.client.features.modules.movement;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K001d59b5164d1d1f95;
import code.miaware.api.event.events.client.K6449169e9fd0c18e12;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.K1d3d0b4020f933282f;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.client.K908217fb7543df2740;
import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_7439;
import org.joml.Vector2i;

@ModuleRegister(
   name = "CordHelp",
   category = Kf76f042931183af5ba.MOVEMENT,
   description = "Отправляет координаты в чат по нажатию кнопки"
)
public class K148657544662b40c19 extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K148657544662b40c19 instance = new K148657544662b40c19();
   private final K1d3d0b4020f933282f key = (new K1d3d0b4020f933282f("Клавиша")).value(-1);
   private final K00fef91b3d4d1b3ecc autoGPSFriend = (new K00fef91b3d4d1b3ecc("Gps Frined")).value(true);
   private static final Pattern PATTERN_XYZ = Pattern.compile(".*?x[:\\s]*(-?\\d+).*?y[:\\s]*(-?\\d+).*?z[:\\s]*(-?\\d+).*?", 2);
   private static final Pattern PATTERN_THREE_NUMBERS = Pattern.compile("(-?\\d+)[,\\s]+(-?\\d+)[,\\s]+(-?\\d+)");
   private static final Pattern PATTERN_NUMBER_XYZ = Pattern.compile("(-?\\d+)\\s*x[,\\s]+(-?\\d+)\\s*y[,\\s]+(-?\\d+)\\s*z", 2);
   private static final Pattern PATTERN_TWO_NUMBERS = Pattern.compile("^[^\\d]*(-?\\d+)[,\\s]+(-?\\d+)[^\\d]*$");

   public K148657544662b40c19() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.key, this.autoGPSFriend});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 keyEvent = K6449169e9fd0c18e12.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1755 == null) {
            if (event.matchesBind((Integer)this.key.getValue()) && event.action() == 1) {
               if ((Integer)this.key.getValue() != -1) {
                  this.K5db652cd6bf2b88c7d();
               }
            }
         }
      }));
      K8d7165024ff567f5e7 packetEvent = K001d59b5164d1d1f95.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null) {
            if ((Boolean)this.autoGPSFriend.getValue()) {
               if (event.isReceive()) {
                  class_2596 patt0$temp = event.packet();
                  if (patt0$temp instanceof class_7439) {
                     class_7439 packet = (class_7439)patt0$temp;
                     class_2561 message = packet.comp_763();
                     String messageText = message.getString();
                     System.out.println("[CordHelp DEBUG] Получено сообщение: " + messageText);
                     String senderName = this.K5f2dcc4dd437bca3c2(messageText);
                     if (senderName == null) {
                        return;
                     }

                     System.out.println("[CordHelp DEBUG] Отправитель: " + senderName);
                     boolean isFriendCheck = this.K804087172b3280a867(senderName);
                     System.out.println("[CordHelp DEBUG] Это друг? " + isFriendCheck);
                     if (!isFriendCheck) {
                        return;
                     }

                     int[] coords = this.K8d7386faa138f0d6ae(messageText);
                     String var10001 = coords != null ? coords[0] + " " + coords[1] + " " + coords[2] : "null";
                     System.out.println("[CordHelp DEBUG] Координаты найдены: " + var10001);
                     if (coords != null) {
                        int x = coords[0];
                        int y = coords[1];
                        int z = coords[2];
                        K908217fb7543df2740.getInstance().setGpsPosition(new Vector2i(x, z));
                        if (mc.field_1724 != null) {
                           String visualMessage = String.valueOf(class_124.field_1080) + "[" + String.valueOf(class_124.field_1076) + "lux visuals" + String.valueOf(class_124.field_1080) + "] " + String.valueOf(class_124.field_1068) + "Поставилась точка на друга " + String.valueOf(class_124.field_1075) + senderName + String.valueOf(class_124.field_1068) + " [" + String.valueOf(class_124.field_1065) + x + " " + y + " " + z + String.valueOf(class_124.field_1068) + "]";
                           mc.field_1724.method_7353(class_2561.method_43470(visualMessage), false);
                        }
                     }
                  }

               }
            }
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{keyEvent, packetEvent});
   }

   private void K5db652cd6bf2b88c7d() {
      if (mc.field_1724 != null) {
         int x = (int)mc.field_1724.method_23317();
         int y = (int)mc.field_1724.method_23318();
         int z = (int)mc.field_1724.method_23321();
         String message = "!" + x + " " + y + " " + z;
         if (mc.field_1724.field_3944 != null) {
            mc.field_1724.field_3944.method_45729(message);
            if (mc.field_1705 != null) {
               mc.field_1705.method_1743().method_1812(class_2561.method_43470("§a[Cord Help] §7Координаты отправлены в глобальный чат"));
            }
         }

      }
   }

   private String K5f2dcc4dd437bca3c2(String message) {
      if (message.startsWith("<") && message.contains(">")) {
         int endIndex = message.indexOf(">");
         String name = message.substring(1, endIndex).trim();
         name = name.replaceAll("[^a-zA-Z0-9_]", "");
         return name.isEmpty() ? null : name;
      } else {
         if (message.contains(":") || message.contains("»")) {
            String separator = message.contains(":") ? ":" : "»";
            String[] parts = message.split(separator, 2);
            if (parts.length >= 1) {
               String name = class_124.method_539(parts[0]).trim();
               name = name.replaceAll("\\[.*?\\]", "").trim();
               name = name.replaceAll("[^a-zA-Z0-9_]", "");
               return name.isEmpty() ? null : name;
            }
         }

         return null;
      }
   }

   private int[] K8d7386faa138f0d6ae(String message) {
      String cleanMessage = class_124.method_539(message);
      Matcher matcher = PATTERN_XYZ.matcher(cleanMessage);
      if (matcher.find()) {
         try {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int z = Integer.parseInt(matcher.group(3));
            return new int[]{x, y, z};
         } catch (NumberFormatException var10) {
         }
      }

      matcher = PATTERN_NUMBER_XYZ.matcher(cleanMessage);
      if (matcher.find()) {
         try {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int z = Integer.parseInt(matcher.group(3));
            return new int[]{x, y, z};
         } catch (NumberFormatException var9) {
         }
      }

      matcher = PATTERN_THREE_NUMBERS.matcher(cleanMessage);
      if (matcher.find()) {
         try {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int z = Integer.parseInt(matcher.group(3));
            if (y >= -64 && y <= 320) {
               return new int[]{x, y, z};
            }
         } catch (NumberFormatException var7) {
         }
      }

      matcher = PATTERN_TWO_NUMBERS.matcher(cleanMessage);
      if (matcher.find()) {
         try {
            int x = Integer.parseInt(matcher.group(1));
            int z = Integer.parseInt(matcher.group(2));
            int y = 64;
            return new int[]{x, y, z};
         } catch (NumberFormatException var8) {
         }
      }

      return null;
   }

   private boolean K804087172b3280a867(String playerName) {
      System.out.println("[CordHelp DEBUG] Проверка друга: " + playerName);
      System.out.println("[CordHelp DEBUG] Список друзей: " + String.valueOf(K137d4e93c7270b0a04.getInstance().getData()));

      for(Object friend : K137d4e93c7270b0a04.getInstance().getData()) {
         PrintStream var10000 = System.out;
         String var10001 = String.valueOf(friend);
         var10000.println("[CordHelp DEBUG] Проверяю друга: " + var10001 + " (тип: " + friend.getClass().getName() + ")");
         if (friend instanceof String && ((String)friend).equalsIgnoreCase(playerName)) {
            System.out.println("[CordHelp DEBUG] НАЙДЕН ДРУГ!");
            return true;
         }
      }

      System.out.println("[CordHelp DEBUG] Друг не найден");
      return false;
   }

   public static K148657544662b40c19 getInstance() {
      return instance;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getAutoGPSFriend() {
      return this.autoGPSFriend;
   }
}
