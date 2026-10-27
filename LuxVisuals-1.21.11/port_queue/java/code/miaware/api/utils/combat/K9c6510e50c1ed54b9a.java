package code.miaware.api.utils.combat;

import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import java.util.List;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1429;
import net.minecraft.class_1657;

public class K9c6510e50c1ed54b9a implements K4600bef438dafc1a9c {
   public List<String> targetSettings;
   public List<String> ignoreSettings;
   public boolean needFriends;
   private final float lowHealthThreshold;

   public K9c6510e50c1ed54b9a(List<String> targetSettings) {
      this(targetSettings, List.of(), 8.0F);
   }

   public K9c6510e50c1ed54b9a(List<String> targetSettings, List<String> ignoreSettings, float lowHealthThreshold) {
      this.needFriends = false;
      this.targetSettings = targetSettings;
      this.ignoreSettings = ignoreSettings;
      this.lowHealthThreshold = lowHealthThreshold;
   }

   public boolean isValid(class_1309 entity) {
      if (this.Kd635f03f308c5097b7(entity)) {
         return false;
      } else if (this.K5d9ae99084c6f44a9a(entity)) {
         return false;
      } else if (this.K5cab5f1adfef7fbca0(entity)) {
         return false;
      } else {
         return this.Ke013b58f873f073392(entity) ? false : this.Kfdc07d95e435c4935c(entity);
      }
   }

   private boolean Kd635f03f308c5097b7(class_1309 entity) {
      return entity == mc.field_1724;
   }

   private boolean K5d9ae99084c6f44a9a(class_1309 entity) {
      return !entity.method_5805() || entity.method_6032() <= 0.0F;
   }

   private boolean K5cab5f1adfef7fbca0(class_1309 entity) {
      if (mc.field_1724 == null) {
         return true;
      } else {
         return entity == mc.field_1724.method_49694();
      }
   }

   private boolean Ke013b58f873f073392(class_1309 entity) {
      if (this.ignoreSettings.contains("Invisible") && entity.method_5767()) {
         return true;
      } else if (this.ignoreSettings.contains("Low HP") && entity.method_6032() <= this.lowHealthThreshold) {
         return true;
      } else {
         if (entity instanceof class_1657) {
            class_1657 player = (class_1657)entity;
            if (this.ignoreSettings.contains("Friends") && K137d4e93c7270b0a04.getInstance().contains(player.method_5477().getString()) && !this.needFriends) {
               return true;
            }

            if (this.ignoreSettings.contains("Naked") && Kaab2896c59e70ad7f2.K4922501b20a9d95c15(player)) {
               return true;
            }

            if (this.ignoreSettings.contains("Armored") && player.method_6096() > 0) {
               return true;
            }
         }

         return false;
      }
   }

   private boolean Kfdc07d95e435c4935c(class_1309 entity) {
      if (entity instanceof class_1657 player) {
         return this.targetSettings.contains("Players");
      } else if (entity instanceof class_1429) {
         return this.targetSettings.contains("Animals");
      } else {
         return entity instanceof class_1308 ? this.targetSettings.contains("Mobs") : false;
      }
   }
}
