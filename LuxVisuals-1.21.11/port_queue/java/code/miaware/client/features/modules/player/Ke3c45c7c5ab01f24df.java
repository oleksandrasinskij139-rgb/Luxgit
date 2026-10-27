package code.miaware.client.features.modules.player;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.client.features.modules.player.invsave.K1c183077e920e5d295;
import code.miaware.client.features.modules.player.invsave.K775957aef270c0236c;
import code.miaware.client.features.modules.player.invsave.K7f83bd6cb90ae8d223;
import code.miaware.client.ui.widget.overlay.Ka58c9e2c4d8aa9af2e;
import code.miaware.client.ui.widget.overlay.Kb7ca6c2b4209b8a116;
import lombok.Generated;
import net.minecraft.class_1799;
import net.minecraft.class_490;
import net.minecraft.class_634;
import net.minecraft.class_746;

@ModuleRegister(
   name = "InvSave",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Менеджер раскладок инвентаря"
)
public final class Ke3c45c7c5ab01f24df extends Kab8a8c478f7d0077cd {
   private static final Ke3c45c7c5ab01f24df instance = new Ke3c45c7c5ab01f24df();
   private final K775957aef270c0236c store = new K775957aef270c0236c();
   private final K7f83bd6cb90ae8d223 arranger = new K7f83bd6cb90ae8d223();
   private String auctionCommand;
   private class_634 auctionConnection;
   private class_746 auctionPlayer;

   public void onEvent() {
      this.addEvents(new K8d7165024ff567f5e7[]{Kdee350abc91aa294b2.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (this.arranger.active()) {
            this.arranger.tick(this.store.selected());
         }

         if (this.auctionCommand != null) {
            String command = this.auctionCommand;
            boolean valid = mc.field_1724 == this.auctionPlayer && mc.method_1562() == this.auctionConnection && mc.field_1755 == null;
            this.K7a914ed2f18c794c80();
            if (valid) {
               mc.field_1724.field_3944.method_45730(command);
            }
         }

      }))});
   }

   public void searchAuction(class_1799 stack) {
      if (this.isEnabled() && mc.field_1724 != null && mc.method_1562() != null && mc.field_1755 != null && mc.field_1755.getClass() == class_490.class) {
         if (mc.method_1542()) {
            Kb7ca6c2b4209b8a116.notify("InvSave: аукцион доступен на сервере", Ka58c9e2c4d8aa9af2e.INFO);
         } else if (!mc.field_1724.field_7512.method_34255().method_7960()) {
            Kb7ca6c2b4209b8a116.notify("InvSave: сначала положи предмет с курсора", Ka58c9e2c4d8aa9af2e.INFO);
         } else {
            String command = K1c183077e920e5d295.commandFor(stack);
            if (!command.isEmpty()) {
               this.arranger.stop();
               this.auctionConnection = mc.method_1562();
               this.auctionPlayer = mc.field_1724;
               this.auctionCommand = command;
               mc.field_1724.method_7346();
            }
         }
      }
   }

   private void K7a914ed2f18c794c80() {
      this.auctionCommand = null;
      this.auctionConnection = null;
      this.auctionPlayer = null;
   }

   public void onDisable() {
      this.arranger.stop();
      this.K7a914ed2f18c794c80();
   }

   @Generated
   public static Ke3c45c7c5ab01f24df getInstance() {
      return instance;
   }

   @Generated
   public K775957aef270c0236c getStore() {
      return this.store;
   }

   @Generated
   public K7f83bd6cb90ae8d223 getArranger() {
      return this.arranger;
   }
}
