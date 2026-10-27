package code.miaware.api.event.events.client;

import net.minecraft.class_2596;

public record Kb251204d734ffa1d20(class_2596<?> packet, Kb652aee96d70f375af packetType) {
   public boolean isReceive() {
      return this.packetType == Kb652aee96d70f375af.RECEIVE;
   }

   public boolean isSend() {
      return this.packetType == Kb652aee96d70f375af.SEND;
   }
}
