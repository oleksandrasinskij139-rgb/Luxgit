package code.miaware.api.utils.combat;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.player.Kd03e5f745eca318ca1;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_243;

public class Kadfdac81e92b1251ea implements K4600bef438dafc1a9c {
   public boolean canBreakShield(class_1657 entity) {
      if (mc.field_1724 != null && mc.field_1761 != null && entity != null) {
         if (entity.method_6039() && this.K97790217c1aaea711a(entity)) {
            return Kd03e5f745eca318ca1.findAxeInInventory(true) != -1 || Kd03e5f745eca318ca1.findAxeInInventory(false) != -1;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public boolean breakShield(class_1657 entity, boolean packetMode) {
      if (!this.canBreakShield(entity)) {
         return false;
      } else {
         int hotBarSlot = Kd03e5f745eca318ca1.findAxeInInventory(true);
         int inventorySlot = Kd03e5f745eca318ca1.findAxeInInventory(false);
         if (hotBarSlot != -1) {
            return this.Kc9bbcaa15c12361459(entity, hotBarSlot, packetMode);
         } else {
            return inventorySlot != -1 ? this.K31c84a90fd79017307(entity, inventorySlot, packetMode) : false;
         }
      }
   }

   private boolean Kc9bbcaa15c12361459(class_1657 entity, int hotBarSlot, boolean packetMode) {
      int previousSlot = mc.field_1724.method_31548().field_7545;
      boolean clientSync = !packetMode;
      Kd03e5f745eca318ca1.swapToSlot(hotBarSlot, clientSync);
      mc.field_1761.method_2918(mc.field_1724, entity);
      mc.field_1724.method_6104(class_1268.field_5808);
      Kd03e5f745eca318ca1.swapToSlot(previousSlot, clientSync);
      return true;
   }

   private boolean K31c84a90fd79017307(class_1657 entity, int inventorySlot, boolean packetMode) {
      int bestSlot = Kd03e5f745eca318ca1.findBestSlotInHotBar();
      if (bestSlot == -1) {
         return false;
      } else {
         int previousSlot = mc.field_1724.method_31548().field_7545;
         boolean clientSync = !packetMode;
         Kd03e5f745eca318ca1.swapSlots(inventorySlot, bestSlot);
         Kd03e5f745eca318ca1.swapToSlot(bestSlot, clientSync);
         mc.field_1761.method_2918(mc.field_1724, entity);
         mc.field_1724.method_6104(class_1268.field_5808);
         Kd03e5f745eca318ca1.swapToSlot(previousSlot, clientSync);
         Kd03e5f745eca318ca1.swapSlots(inventorySlot, bestSlot);
         return true;
      }
   }

   private boolean K97790217c1aaea711a(class_1657 target) {
      if (mc.field_1724 == null) {
         return false;
      } else {
         class_243 lookVec = target.method_5828(1.0F).method_1029();
         class_243 dirToMe = mc.field_1724.method_33571().method_1020(target.method_33571()).method_1029();
         double dot = Math.max((double)-1.0F, Math.min((double)1.0F, lookVec.method_1026(dirToMe)));
         double angle = Math.toDegrees(Math.acos(dot));
         return angle < (double)100.0F;
      }
   }
}
