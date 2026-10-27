package code.miaware.client.features.modules.player;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

@ModuleRegister(
   name = "Chat Helper",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Объединяет одинаковые сообщения в чате"
)
public class K724ae88646b9ca0c5d extends Kab8a8c478f7d0077cd {
   private static final K724ae88646b9ca0c5d instance = new K724ae88646b9ca0c5d();
   private final Map<String, Integer> messageCount = new HashMap();

   public void onEnable() {
      this.messageCount.clear();
   }

   public void onDisable() {
      this.messageCount.clear();
   }

   public void onEvent() {
   }

   public void registerNewMessage(String messageText) {
      this.messageCount.put(messageText, 1);
   }

   public int incrementMessage(String messageText) {
      int count = (Integer)this.messageCount.getOrDefault(messageText, 1) + 1;
      this.messageCount.put(messageText, count);
      return count;
   }

   @Generated
   public static K724ae88646b9ca0c5d getInstance() {
      return instance;
   }
}
