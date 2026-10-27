package code.miaware.client.features.modules.player;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import lombok.Generated;

@ModuleRegister(
   name = "Anti Leave",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Предотвращает случайный выход с сервера"
)
public class K28fed1fbb8f6d6c65a extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K28fed1fbb8f6d6c65a instance = new K28fed1fbb8f6d6c65a();
   private boolean inPvP = false;

   public void onEvent() {
   }

   public void onChatMessage(String message) {
      if (this.isEnabled()) {
         if (!message.contains("Вы вошли в PVP режим") && (!message.contains("АнтиРелог") || !message.contains("PVP режим"))) {
            if (message.contains("PVP режим закончился") || message.contains("Можно спокойно выходить")) {
               this.inPvP = false;
               System.out.println("[AntiLeave] PvP режим ВЫКЛЮЧЕН");
            }
         } else {
            this.inPvP = true;
            System.out.println("[AntiLeave] PvP режим ВКЛЮЧЕН");
         }

      }
   }

   public boolean isInCombat() {
      return this.inPvP;
   }

   public boolean shouldConfirmDisconnect() {
      boolean should = this.isEnabled() && this.isInCombat();
      System.out.println("[AntiLeave] shouldConfirmDisconnect: " + should + " (enabled=" + this.isEnabled() + ", inPvP=" + this.inPvP + ")");
      return should;
   }

   @Generated
   public static K28fed1fbb8f6d6c65a getInstance() {
      return instance;
   }
}
