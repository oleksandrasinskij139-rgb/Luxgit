package code.miaware.client.features.modules.other;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K001d59b5164d1d1f95;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;
import lombok.Generated;
import net.minecraft.class_2596;
import net.minecraft.class_7439;

@ModuleRegister(
   name = "Tpaaccept",
   category = Kf76f042931183af5ba.OTHER,
   description = "Автоматически принимает телепорты"
)
public class K3b966436f826058262 extends Kab8a8c478f7d0077cd {
   private static final K3b966436f826058262 instance = new K3b966436f826058262();
   private final K00fef91b3d4d1b3ecc onlyFriends = (new K00fef91b3d4d1b3ecc("Only friends")).value(true);
   private final String[] teleportMessages = new String[]{"has requested teleport", "просит телепортироваться", "хочет телепортироваться к вам", "просит к вам телепортироваться", "tpaccept"};

   public K3b966436f826058262() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.onlyFriends});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 packetEvent = K001d59b5164d1d1f95.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (event.isReceive()) {
            class_2596 patt0$temp = event.packet();
            if (patt0$temp instanceof class_7439) {
               class_7439 packet = (class_7439)patt0$temp;
               if (mc.field_1724 != null) {
                  String message = packet.comp_763().getString();
                  if (message != null && !message.isBlank()) {
                     String lowerMessage = message.toLowerCase(Locale.ROOT);
                     if (!this.K544e98affa7de93a91(lowerMessage)) {
                        return;
                     }

                     if ((Boolean)this.onlyFriends.getValue() && !this.Ke7b3d6f92cb3fb8ded(lowerMessage)) {
                        return;
                     }

                     mc.field_1724.field_3944.method_45730("tpaccept");
                     return;
                  }

                  return;
               }
            }
         }

      }));
      this.addEvents(new K8d7165024ff567f5e7[]{packetEvent});
   }

   private boolean K544e98affa7de93a91(String message) {
      Stream var10000 = Arrays.stream(this.teleportMessages).map((text) -> text.toLowerCase(Locale.ROOT));
      Objects.requireNonNull(message);
      return var10000.anyMatch(message::contains);
   }

   private boolean Ke7b3d6f92cb3fb8ded(String message) {
      for(String friend : K137d4e93c7270b0a04.getInstance().getData()) {
         if (message.contains(friend.toLowerCase(Locale.ROOT))) {
            return true;
         }
      }

      return false;
   }

   @Generated
   public static K3b966436f826058262 getInstance() {
      return instance;
   }
}
