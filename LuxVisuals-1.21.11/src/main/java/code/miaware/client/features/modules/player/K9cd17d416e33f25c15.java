package code.miaware.client.features.modules.player;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import lombok.Generated;

@ModuleRegister(
   name = "Auto Near",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Автоматически прописывает /near при телепортации"
)
public class K9cd17d416e33f25c15 extends Kab8a8c478f7d0077cd {
   private static final K9cd17d416e33f25c15 instance = new K9cd17d416e33f25c15();
   private double lastX = (double)0.0F;
   private double lastY = (double)0.0F;
   private double lastZ = (double)0.0F;
   private int ticksSinceTeleport = 0;
   private boolean commandSent = true;
   private static final int DELAY_TICKS = 20;
   private static final double TELEPORT_DISTANCE = (double)50.0F;

   public void onEnable() {
      if (mc.field_1724 != null) {
         this.lastX = mc.field_1724.method_23317();
         this.lastY = mc.field_1724.method_23318();
         this.lastZ = mc.field_1724.method_23321();
      }

      this.ticksSinceTeleport = 0;
      this.commandSent = true;
   }

   public void onEvent() {
      K8d7165024ff567f5e7 tickEvent = Kdee350abc91aa294b2.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            double currentX = mc.field_1724.method_23317();
            double currentY = mc.field_1724.method_23318();
            double currentZ = mc.field_1724.method_23321();
            double distance = Math.sqrt(Math.pow(currentX - this.lastX, (double)2.0F) + Math.pow(currentY - this.lastY, (double)2.0F) + Math.pow(currentZ - this.lastZ, (double)2.0F));
            if (distance > (double)50.0F) {
               this.ticksSinceTeleport = 1;
               this.commandSent = false;
            }

            this.lastX = currentX;
            this.lastY = currentY;
            this.lastZ = currentZ;
            if (!this.commandSent && this.ticksSinceTeleport > 0) {
               ++this.ticksSinceTeleport;
               if (this.ticksSinceTeleport >= 20) {
                  mc.field_1724.field_3944.method_45729("/near");
                  this.commandSent = true;
                  this.ticksSinceTeleport = 0;
               }
            }

         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{tickEvent});
   }

   public void onChatMessage(String message) {
   }

   @Generated
   public static K9cd17d416e33f25c15 getInstance() {
      return instance;
   }
}
