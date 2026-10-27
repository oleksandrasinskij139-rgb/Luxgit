package code.miaware.client.features.modules.player.elytraswap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class K14061464cf17a12dea {
   private K14061464cf17a12dea() {
   }

   public static List<Kf5923afbe778603c71> plan(int inventorySlot, int scratch) {
      if (inventorySlot >= 0 && inventorySlot < 36) {
         return inventorySlot < 9 ? List.of(new Kf5923afbe778603c71(6, inventorySlot)) : List.of(new Kf5923afbe778603c71(inventorySlot, scratch), new Kf5923afbe778603c71(6, scratch), new Kf5923afbe778603c71(inventorySlot, scratch));
      } else {
         throw new IllegalArgumentException("Source must be a storage slot");
      }
   }

   private static <T> void Kfc9c4a37b5159b5939(Map<Integer, T> expected, Kf5923afbe778603c71 action) {
      T previous = (T)expected.get(action.slot());
      expected.put(action.slot(), expected.get(action.hotbarSlot()));
      expected.put(action.hotbarSlot(), previous);
   }

   private static <T> boolean K51a3fa38b03dbe31af(Map<Integer, T> expected, Ka77e06e792f2b9f5ec<T> inventory) {
      for(Map.Entry<Integer, T> entry : expected.entrySet()) {
         if (!inventory.same(entry.getValue(), inventory.copy((Integer)entry.getKey()))) {
            return false;
         }
      }

      return true;
   }

   private static <T> void K0135cf5e972b129953(List<Kf5923afbe778603c71> done, Map<Integer, T> expected, Ka77e06e792f2b9f5ec<T> inventory) {
      for(int i = done.size() - 1; i >= 0; --i) {
         if (!inventory.valid() || !inventory.cursorEmpty() || !K51a3fa38b03dbe31af(expected, inventory)) {
            return;
         }

         Kf5923afbe778603c71 action = (Kf5923afbe778603c71)done.get(i);
         inventory.swap(action);
         Kfc9c4a37b5159b5939(expected, action);
         if (!K51a3fa38b03dbe31af(expected, inventory)) {
            return;
         }
      }

   }

   public static <T> boolean execute(List<Kf5923afbe778603c71> plan, Ka77e06e792f2b9f5ec<T> inventory) {
      if (inventory.valid() && inventory.cursorEmpty()) {
         Map<Integer, T> expected = new LinkedHashMap();

         for(Kf5923afbe778603c71 action : plan) {
            expected.putIfAbsent(action.slot(), inventory.copy(action.slot()));
            expected.putIfAbsent(action.hotbarSlot(), inventory.copy(action.hotbarSlot()));
         }

         List<Kf5923afbe778603c71> done = new ArrayList();

         for(Kf5923afbe778603c71 action : plan) {
            if (inventory.valid() && inventory.cursorEmpty() && K51a3fa38b03dbe31af(expected, inventory)) {
               Map<Integer, T> before = new LinkedHashMap(expected);
               Kfc9c4a37b5159b5939(expected, action);
               inventory.swap(action);
               if (inventory.cursorEmpty() && inventory.valid()) {
                  if (!K51a3fa38b03dbe31af(expected, inventory)) {
                     if (K51a3fa38b03dbe31af(before, inventory)) {
                        K0135cf5e972b129953(done, before, inventory);
                     }

                     return false;
                  }

                  done.add(action);
                  continue;
               }

               return false;
            }

            K0135cf5e972b129953(done, expected, inventory);
            return false;
         }

         return true;
      } else {
         return false;
      }
   }
}
