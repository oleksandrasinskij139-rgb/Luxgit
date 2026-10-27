package code.miaware.client.features.modules.other;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.system.K7a815b1a7330bf39b3;

@ModuleRegister(
   name = "Discord RPC",
   category = Kf76f042931183af5ba.OTHER,
   description = "Показывает активность в Discord"
)
public class K2f63e835e247f11580 extends Kab8a8c478f7d0077cd {
   private static final K2f63e835e247f11580 instance = new K2f63e835e247f11580();
   private long lastPresenceUpdate;

   public static K2f63e835e247f11580 getInstance() {
      return instance;
   }

   public void setEnabled(boolean newState, boolean config) {
      super.setEnabled(true, config);
   }

   public void setEnabled(boolean newState) {
      super.setEnabled(true, false);
   }

   public void toggle() {
   }

   public void onEnable() {
      this.lastPresenceUpdate = 0L;
      K7a815b1a7330bf39b3.startRPC();
   }

   public void onDisable() {
   }

   public void onEvent() {
      K8d7165024ff567f5e7 tickEvent = Kdee350abc91aa294b2.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         long now = System.currentTimeMillis();
         if (now - this.lastPresenceUpdate >= 1500L) {
            K7a815b1a7330bf39b3.refreshPresence();
            this.lastPresenceUpdate = now;
         }

      }));
      this.addEvents(new K8d7165024ff567f5e7[]{tickEvent});
   }
}
