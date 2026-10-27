package code.miaware.client.features.modules.player;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Ka4b85e821e3749c3d9;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import lombok.Generated;

@ModuleRegister(
   name = "Lock Slot",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Блокирует выброс предметов из выбранных слотов хотбара"
)
public class K08ed84328ad9502e9e extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K08ed84328ad9502e9e instance = new K08ed84328ad9502e9e();
   private final Ka4b85e821e3749c3d9 slots = (new Ka4b85e821e3749c3d9("Слоты")).value((new K00fef91b3d4d1b3ecc("Слот 1")).value(false), (new K00fef91b3d4d1b3ecc("Слот 2")).value(false), (new K00fef91b3d4d1b3ecc("Слот 3")).value(false), (new K00fef91b3d4d1b3ecc("Слот 4")).value(false), (new K00fef91b3d4d1b3ecc("Слот 5")).value(false), (new K00fef91b3d4d1b3ecc("Слот 6")).value(false), (new K00fef91b3d4d1b3ecc("Слот 7")).value(false), (new K00fef91b3d4d1b3ecc("Слот 8")).value(false), (new K00fef91b3d4d1b3ecc("Слот 9")).value(false));

   public K08ed84328ad9502e9e() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.slots});
   }

   public void onEvent() {
   }

   public boolean isSlotLocked(int slotIndex) {
      if (!this.isEnabled()) {
         return false;
      } else if (slotIndex >= 0 && slotIndex <= 8) {
         String slotName = "Слот " + (slotIndex + 1);
         return this.slots.isEnabled(slotName);
      } else {
         return false;
      }
   }

   @Generated
   public static K08ed84328ad9502e9e getInstance() {
      return instance;
   }
}
