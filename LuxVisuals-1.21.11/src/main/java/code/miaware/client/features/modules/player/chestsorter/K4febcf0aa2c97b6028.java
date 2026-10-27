package code.miaware.client.features.modules.player.chestsorter;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_7923;

public final class K4febcf0aa2c97b6028 {
   private static final Set<String> TREASURE = Set.of("elytra", "totem_of_undying", "nether_star", "enchanted_golden_apple", "netherite_ingot", "netherite_block", "diamond_block", "beacon", "dragon_egg", "netherite_upgrade_smithing_template");
   private static final Set<String> JUNK = Set.of("rotten_flesh", "bone", "dirt", "cobblestone", "gravel", "netherrack", "sand", "dead_bush");

   private K4febcf0aa2c97b6028() {
   }

   public static <T> Kb2d4b15c936e847c0e next(List<T> values, Comparator<T> order) {
      for(int i = 0; i < values.size(); ++i) {
         int best = i;

         for(int j = i + 1; j < values.size(); ++j) {
            if (order.compare(values.get(j), values.get(best)) < 0) {
               best = j;
            }
         }

         if (best != i) {
            return new Kb2d4b15c936e847c0e(best, i);
         }
      }

      return null;
   }

   public static K13d4503099adf1f988 value(class_1799 stack) {
      if (stack.method_7960()) {
         return new K13d4503099adf1f988(99, 99, 0, 0, "", "");
      } else {
         String id = class_7923.field_41178.method_10221(stack.method_7909()).method_12832();
         int material = id.contains("netherite") ? 0 : (id.contains("diamond") ? 1 : (id.contains("iron") ? 2 : (id.contains("gold") ? 3 : (id.contains("chainmail") ? 4 : (id.contains("stone") ? 5 : 6)))));
         int category;
         if (TREASURE.contains(id)) {
            category = 0;
         } else if (stack.method_7963()) {
            category = 1;
         } else if (!id.contains("diamond") && !id.contains("emerald") && !id.contains("ingot") && !id.contains("raw_") && !id.contains("gold_nugget")) {
            if (id.equals("enchanted_book")) {
               category = 3;
            } else if (JUNK.contains(id)) {
               category = 8;
            } else if (stack.method_7909() instanceof class_1747) {
               category = 6;
            } else if (!id.contains("potion") && !id.contains("apple") && !id.contains("bread") && !id.contains("beef") && !id.contains("porkchop") && !id.contains("carrot") && !id.contains("ender_pearl") && !id.contains("rocket") && !id.contains("arrow")) {
               category = 5;
            } else {
               category = 4;
            }
         } else {
            category = 2;
         }

         return new K13d4503099adf1f988(category, material, stack.method_7942() ? -1 : 0, stack.method_7919(), id, stack.method_7964().getString());
      }
   }
}
