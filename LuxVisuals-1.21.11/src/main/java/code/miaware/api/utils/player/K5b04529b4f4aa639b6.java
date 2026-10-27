package code.miaware.api.utils.player;

import net.minecraft.class_1657;
import net.minecraft.class_266;
import net.minecraft.class_269;
import net.minecraft.class_8646;
import net.minecraft.class_9014;

public final class K5b04529b4f4aa639b6 {
   private K5b04529b4f4aa639b6() {
   }

   public static float getBelowNameHealth(class_1657 player) {
      if (player != null && player.method_37908() != null) {
         class_269 scoreboard = player.method_7327();
         if (scoreboard == null) {
            return -1.0F;
         } else {
            class_266 objective = scoreboard.method_1189(class_8646.field_45158);
            if (objective == null) {
               return -1.0F;
            } else {
               class_9014 score = scoreboard.method_1180(player, objective);
               int value = score.method_55409();
               return value > 0 ? (float)value : -1.0F;
            }
         }
      } else {
         return -1.0F;
      }
   }

   public static boolean hasResolvableHealth(class_1657 player) {
      return getBelowNameHealth(player) > 0.0F;
   }
}
