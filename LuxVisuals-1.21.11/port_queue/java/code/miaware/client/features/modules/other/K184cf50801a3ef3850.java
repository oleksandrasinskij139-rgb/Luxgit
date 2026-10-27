package code.miaware.client.features.modules.other;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import code.miaware.api.utils.other.Kf5ec3d7f18b288e7f3;
import code.miaware.api.utils.player.K131e64425767c5c203;
import java.util.ArrayDeque;
import java.util.Locale;
import java.util.Objects;
import java.util.Queue;
import lombok.Generated;
import net.minecraft.class_2338;
import net.minecraft.class_634;
import net.minecraft.class_640;

@ModuleRegister(
   name = "Death Coords",
   category = Kf76f042931183af5ba.OTHER,
   description = "Сохраняет координаты места смерти"
)
public class K184cf50801a3ef3850 extends Kab8a8c478f7d0077cd {
   private static final K184cf50801a3ef3850 instance = new K184cf50801a3ef3850();
   private final K00fef91b3d4d1b3ecc selfChat = (new K00fef91b3d4d1b3ecc("Self chat")).value(true);
   private final K00fef91b3d4d1b3ecc autoGPS = (new K00fef91b3d4d1b3ecc("Авто GPS")).value(true);
   private final K00fef91b3d4d1b3ecc sendFriends = (new K00fef91b3d4d1b3ecc("Send friends")).value(false);
   private final Kb9e875ead00e0d03bd delaySeconds;
   private final Queue<String> pendingFriends;
   private boolean wasAlive;
   private long nextFriendMessageAt;
   private String queuedMessage;

   public K184cf50801a3ef3850() {
      Kb9e875ead00e0d03bd var10001 = (new Kb9e875ead00e0d03bd("Friend delay")).value(7.0F).range(3.0F, 15.0F).step(1.0F);
      K00fef91b3d4d1b3ecc var10002 = this.sendFriends;
      Objects.requireNonNull(var10002);
      this.delaySeconds = var10001.setVisible(var10002::getValue);
      this.pendingFriends = new ArrayDeque();
      this.wasAlive = true;
      this.queuedMessage = "";
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.selfChat, this.autoGPS, this.sendFriends, this.delaySeconds});
   }

   public void onDisable() {
      this.pendingFriends.clear();
      this.queuedMessage = "";
      this.wasAlive = true;
      this.nextFriendMessageAt = 0L;
   }

   public void onEvent() {
      K8d7165024ff567f5e7 tickEvent = Kdee350abc91aa294b2.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 == null) {
            this.pendingFriends.clear();
            this.queuedMessage = "";
            this.wasAlive = true;
         } else {
            boolean alive = mc.field_1724.method_5805();
            if (this.wasAlive && !alive) {
               this.K84555d2dbdfb801d17(mc.field_1724.method_24515());
            }

            this.wasAlive = alive;
            this.Kf9407669f8b0a5b6e6();
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{tickEvent});
   }

   private void K84555d2dbdfb801d17(class_2338 pos) {
      int var10000 = pos.method_10263();
      String coords = var10000 + " " + pos.method_10264() + " " + pos.method_10260();
      this.queuedMessage = "Мои координаты смерти: " + coords;
      if ((Boolean)this.selfChat.getValue()) {
         Kf5ec3d7f18b288e7f3.sendMessage(this.queuedMessage);
      }

      if ((Boolean)this.autoGPS.getValue() && mc.field_1724 != null) {
         class_634 var5 = mc.field_1724.field_3944;
         int var10001 = pos.method_10263();
         var5.method_45730("gps " + var10001 + " " + pos.method_10264() + " " + pos.method_10260());
      }

      this.pendingFriends.clear();
      if ((Boolean)this.sendFriends.getValue()) {
         for(String friend : K137d4e93c7270b0a04.getInstance().getData()) {
            if (this.K3e9f9eb5d930559215(friend)) {
               this.pendingFriends.offer(friend);
            }
         }

         this.nextFriendMessageAt = System.currentTimeMillis() + (long)(this.delaySeconds.getValue() * 1000.0F);
      }
   }

   private void Kf9407669f8b0a5b6e6() {
      if ((Boolean)this.sendFriends.getValue() && !this.pendingFriends.isEmpty() && !this.queuedMessage.isBlank() && mc.field_1724 != null) {
         long now = System.currentTimeMillis();
         if (now >= this.nextFriendMessageAt) {
            while(!this.pendingFriends.isEmpty()) {
               String friend = (String)this.pendingFriends.poll();
               if (this.K3e9f9eb5d930559215(friend)) {
                  mc.field_1724.field_3944.method_45730("msg " + friend + " " + this.queuedMessage);
                  this.nextFriendMessageAt = now + (long)(this.delaySeconds.getValue() * 1000.0F);
                  break;
               }
            }

         }
      }
   }

   private boolean K3e9f9eb5d930559215(String friendName) {
      if (mc.method_1562() != null && friendName != null && !friendName.isBlank()) {
         String target = friendName.toLowerCase(Locale.ROOT);

         for(class_640 entry : mc.method_1562().method_2880()) {
            String name = K131e64425767c5c203.sanitizeDisplayText(entry.method_2966().getName()).toLowerCase(Locale.ROOT);
            if (target.equals(name)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   @Generated
   public static K184cf50801a3ef3850 getInstance() {
      return instance;
   }
}
