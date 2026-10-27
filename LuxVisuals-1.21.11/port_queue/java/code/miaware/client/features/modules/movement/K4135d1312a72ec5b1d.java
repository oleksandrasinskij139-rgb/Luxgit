package code.miaware.client.features.modules.movement;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K001d59b5164d1d1f95;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import net.minecraft.class_2596;
import net.minecraft.class_7439;

@ModuleRegister(
   name = "AutoGps",
   category = Kf76f042931183af5ba.MOVEMENT,
   description = "Автоматически добавляет GPS метки из RTP сообщений"
)
public class K4135d1312a72ec5b1d extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K4135d1312a72ec5b1d instance = new K4135d1312a72ec5b1d();
   private String lastProcessedMessage = "";

   public K4135d1312a72ec5b1d() {
      this.setEnabled(true);
   }

   public void onEvent() {
      K8d7165024ff567f5e7 packetEvent = K001d59b5164d1d1f95.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (event.isReceive()) {
            class_2596 patt0$temp = event.packet();
            if (patt0$temp instanceof class_7439) {
               class_7439 packet = (class_7439)patt0$temp;

               try {
                  String message = packet.comp_763().getString();
                  if (message != null && !message.isEmpty()) {
                     this.K2f34bb117e95f1e5dd(message);
                  }
               } catch (Exception var4) {
               }
            }

         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{packetEvent});
   }

   private void K2f34bb117e95f1e5dd(String message) {
      if (message != null && !this.lastProcessedMessage.equals(message)) {
         String cleanMessage = message.replaceAll("§[0-9a-fk-or]", "");
         if (cleanMessage != null) {
            boolean hasRTPKeywords = cleanMessage.contains("телепортировались") && cleanMessage.contains("игроком") && cleanMessage.contains("координаты");
            if (hasRTPKeywords) {
               this.Kfea7606af56c7e417d(cleanMessage);
               this.lastProcessedMessage = message;
            }

         }
      }
   }

   private void Kfea7606af56c7e417d(String message) {
      try {
         message = message.replace("ʟᴏɴʏɢʀɪᴇꜰ » ", "").replace("[CHAT] ", "").trim();
         int playerStart = message.indexOf("игроком ");
         if (playerStart == -1) {
            return;
         }

         playerStart += 8;
         int playerEnd = message.indexOf("!", playerStart);
         if (playerEnd == -1) {
            return;
         }

         String playerName = message.substring(playerStart, playerEnd).trim();
         int coordsStart = message.indexOf("координаты:");
         if (coordsStart == -1) {
            return;
         }

         coordsStart += 11;
         String coordsText = message.substring(coordsStart).trim();
         if (coordsText.endsWith(".")) {
            coordsText = coordsText.substring(0, coordsText.length() - 1);
         }

         String[] coordParts = coordsText.split("\\s*\\.\\s*");
         if (coordParts.length < 3) {
            return;
         }

         int x = Integer.parseInt(coordParts[0].trim());
         int y = Integer.parseInt(coordParts[1].trim());
         int z = Integer.parseInt(coordParts[2].trim());
         String command = ".gps add " + playerName + " " + x + " " + y + " " + z;
         if (mc.field_1724 != null && mc.field_1724.field_3944 != null) {
            mc.field_1724.field_3944.method_45729(command);
         }
      } catch (Exception var12) {
      }

   }

   public static K4135d1312a72ec5b1d getInstance() {
      return instance;
   }
}
