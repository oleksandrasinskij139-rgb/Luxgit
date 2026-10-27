package code.miaware.api.utils.player;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.other.K756e8fdd34195c2119;
import code.miaware.api.utils.rotation.manager.K960322f580344d3bad;
import lombok.Generated;
import net.minecraft.class_1268;
import net.minecraft.class_1304;
import net.minecraft.class_1713;
import net.minecraft.class_1743;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1829;
import net.minecraft.class_2371;
import net.minecraft.class_2815;
import net.minecraft.class_2868;
import net.minecraft.class_2886;
import net.minecraft.class_7204;

public final class Kd03e5f745eca318ca1 implements K4600bef438dafc1a9c {
   public static void dropSlot(int slot) {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         int syncId = mc.field_1724.field_7512.field_7763;
         mc.field_1761.method_2906(syncId, slot, 1, class_1713.field_7795, mc.field_1724);
         K756e8fdd34195c2119.sendPacket(new class_2815(syncId));
      }
   }

   public static void swapToOffhand(int slot) {
      if (slot != -1) {
         if (mc.field_1724 != null && mc.field_1761 != null) {
            int syncId = mc.field_1724.field_7512.field_7763;
            mc.field_1761.method_2906(syncId, slot, 40, class_1713.field_7791, mc.field_1724);
            mc.field_1724.field_3944.method_52787(new class_2815(syncId));
         }
      }
   }

   public static void swapSlots(int from, int to) {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         int syncId = mc.field_1724.field_7512.field_7763;
         int swapSlot = to >= 0 && to <= 8 ? to : (from >= 0 && from <= 8 ? from : -1);
         if (swapSlot != -1) {
            mc.field_1761.method_2906(syncId, swapSlot == to ? from : to, swapSlot, class_1713.field_7791, mc.field_1724);
            mc.field_1724.field_3944.method_52787(new class_2815(syncId));
         }
      }
   }

   public static void swapSlotsFull(int from, int to) {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         int syncId = mc.field_1724.field_7512.field_7763;
         mc.field_1761.method_2906(syncId, from, to, class_1713.field_7791, mc.field_1724);
         mc.field_1724.field_3944.method_52787(new class_2815(syncId));
      }
   }

   public static void useItem(class_1268 hand) {
      if (mc.field_1724 != null) {
         K960322f580344d3bad rotationManager = K960322f580344d3bad.getInstance();
         float currentYaw = rotationManager.getCurrentRotationPlan() != null ? rotationManager.getRotation().getYaw() : mc.field_1724.method_36454();
         float currentPitch = rotationManager.getCurrentRotationPlan() != null ? rotationManager.getRotation().getPitch() : mc.field_1724.method_36455();
         K756e8fdd34195c2119.sendPacket((class_7204)((s) -> new class_2886(hand, s, currentYaw, currentPitch)));
         mc.field_1724.method_6104(hand);
      }
   }

   public static void swapToSlot(int slot) {
      swapToSlot(slot, true);
   }

   public static void swapToSlot(int slot, boolean client) {
      if (mc.field_1724 != null) {
         if (mc.field_1724.method_31548().field_7545 != slot) {
            mc.field_1724.field_3944.method_52787(new class_2868(slot));
            if (client) {
               mc.field_1724.method_31548().field_7545 = slot;
            }

         }
      }
   }

   public static int findItem(class_1792 item, boolean inHotbar) {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         class_2371<class_1799> main = mc.field_1724.method_31548().field_7547;
         int firstSlot = inHotbar ? 0 : 9;
         int lastSlot = inHotbar ? 9 : 36;

         for(int i = firstSlot; i < lastSlot; ++i) {
            if (((class_1799)main.get(i)).method_7909() == item) {
               return i;
            }
         }

         return -1;
      }
   }

   public static int findItem(class_1792 input) {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         class_2371<class_1799> main = mc.field_1724.method_31548().field_7547;
         int slot = -1;

         for(int i = 0; i < 36; ++i) {
            class_1799 stack = (class_1799)main.get(i);
            if (stack.method_7909() == input) {
               slot = i;
               break;
            }
         }

         if (slot < 9 && slot != -1) {
            slot += 36;
         }

         return slot;
      }
   }

   public static boolean hasElytraEquipped() {
      return mc.field_1724.method_6118(class_1304.field_6174).method_31574(class_1802.field_8833);
   }

   public static int findAxeInInventory(boolean inHotbar) {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         class_2371<class_1799> main = mc.field_1724.method_31548().field_7547;
         int firstSlot = inHotbar ? 0 : 9;
         int lastSlot = inHotbar ? 9 : 36;

         for(int i = firstSlot; i < lastSlot; ++i) {
            if (((class_1799)main.get(i)).method_7909() instanceof class_1743) {
               return i;
            }
         }

         return -1;
      }
   }

   public static int findBestSlotInHotBar() {
      int emptySlot = findEmptySlot();
      return emptySlot != -1 ? emptySlot : K7e59f4a20048121f84();
   }

   public static int findEmptySlot() {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         class_2371<class_1799> main = mc.field_1724.method_31548().field_7547;
         int currentItem = mc.field_1724.method_31548().field_7545;

         for(int i = 0; i < 9; ++i) {
            if (((class_1799)main.get(i)).method_7960() && currentItem != i) {
               return i;
            }
         }

         return -1;
      }
   }

   private static int K7e59f4a20048121f84() {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         class_2371<class_1799> main = mc.field_1724.method_31548().field_7547;
         int currentItem = mc.field_1724.method_31548().field_7545;

         for(int i = 0; i < 9; ++i) {
            class_1799 stack = (class_1799)main.get(i);
            if (!(stack.method_7909() instanceof class_1829) && currentItem != i) {
               return i;
            }
         }

         return -1;
      }
   }

   public static int countNonEnchantedTotems() {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         int count = 0;
         class_2371<class_1799> main = mc.field_1724.method_31548().field_7547;

         for(int i = 0; i < 36; ++i) {
            class_1799 stack = (class_1799)main.get(i);
            if (stack.method_31574(class_1802.field_8288) && !stack.method_7942()) {
               ++count;
            }
         }

         return count;
      }
   }

   public static int findBestTotemSlot(boolean saveEnchanted) {
      class_2371<class_1799> main = mc.field_1724.method_31548().field_7547;

      for(int i = 0; i < 36; ++i) {
         class_1799 stack = (class_1799)main.get(i);
         if (stack.method_31574(class_1802.field_8288) && (!saveEnchanted || !stack.method_7942())) {
            return i < 9 ? i + 36 : i;
         }
      }

      return -1;
   }

   @Generated
   private Kd03e5f745eca318ca1() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
