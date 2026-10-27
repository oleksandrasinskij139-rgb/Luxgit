package code.miaware.client.features.modules.other;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K001d59b5164d1d1f95;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import lombok.Generated;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1686;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1844;
import net.minecraft.class_238;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2663;
import net.minecraft.class_9334;

@ModuleRegister(
   name = "Player Tracker",
   category = Kf76f042931183af5ba.OTHER,
   description = "Отслеживает действия других игроков"
)
public class K039dcb7a6545414345 extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K039dcb7a6545414345 instance = new K039dcb7a6545414345();
   private final K00fef91b3d4d1b3ecc trackTotem = (new K00fef91b3d4d1b3ecc("Тотем")).value(true);
   private final K00fef91b3d4d1b3ecc trackGoldenApple = (new K00fef91b3d4d1b3ecc("Золотое яблоко")).value(true);
   private final K00fef91b3d4d1b3ecc trackEnchantedApple = (new K00fef91b3d4d1b3ecc("Зачарованное яблоко")).value(true);
   private final K00fef91b3d4d1b3ecc trackHealingPotion = (new K00fef91b3d4d1b3ecc("Зелье исцеления")).value(true);
   private final Map<UUID, class_1799> activeUseItem = new HashMap();
   private final Map<UUID, Integer> useStartTick = new HashMap();
   private final Map<Integer, Ka38eb13d450be5d439> trackedPotions = new HashMap();

   public K039dcb7a6545414345() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.trackTotem, this.trackGoldenApple, this.trackEnchantedApple, this.trackHealingPotion});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 packetListener = K001d59b5164d1d1f95.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1687 != null && mc.field_1724 != null) {
            if (event.isReceive()) {
               class_2596 patt0$temp = event.packet();
               if (patt0$temp instanceof class_2663) {
                  class_2663 packet = (class_2663)patt0$temp;
                  if (packet.method_11470() == 35 && (Boolean)this.trackTotem.getValue()) {
                     class_1297 entity = packet.method_11469(mc.field_1687);
                     if (!(entity instanceof class_1657)) {
                        return;
                     }

                     class_1657 player = (class_1657)entity;
                     if (player.method_5667().equals(mc.field_1724.method_5667())) {
                        return;
                     }

                     String playerName = player.method_5477().getString();
                     this.Kf4f5d0cae60b674cab("§f" + playerName + " §eиспользовал §6✦ Тотем бессмертия");
                  }
               }

            }
         }
      }));
      K8d7165024ff567f5e7 tickListener = Kdee350abc91aa294b2.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            if ((Boolean)this.trackGoldenApple.getValue() || (Boolean)this.trackEnchantedApple.getValue() || (Boolean)this.trackHealingPotion.getValue()) {
               for(class_1657 player : mc.field_1687.method_18456()) {
                  if (!player.method_5667().equals(mc.field_1724.method_5667())) {
                     UUID playerId = player.method_5667();
                     if (player.method_6115()) {
                        if (!this.activeUseItem.containsKey(playerId)) {
                           this.activeUseItem.put(playerId, player.method_6030().method_7972());
                           this.useStartTick.put(playerId, player.field_6012);
                        }
                     } else {
                        class_1799 usedItem = (class_1799)this.activeUseItem.remove(playerId);
                        Integer startTick = (Integer)this.useStartTick.remove(playerId);
                        if (usedItem != null && !usedItem.method_7960() && startTick != null) {
                           int useDuration = player.field_6012 - startTick;
                           if (useDuration >= 31) {
                              String playerName = player.method_5477().getString();
                              if (usedItem.method_31574(class_1802.field_8367) && (Boolean)this.trackEnchantedApple.getValue()) {
                                 this.Kf4f5d0cae60b674cab("§f" + playerName + " §eсъел §5✦ §6Зачарованное золотое яблоко");
                              } else if (usedItem.method_31574(class_1802.field_8463) && (Boolean)this.trackGoldenApple.getValue()) {
                                 this.Kf4f5d0cae60b674cab("§f" + playerName + " §eсъел §6✹ Золотое яблоко");
                              } else if (usedItem.method_31574(class_1802.field_8574) && (Boolean)this.trackHealingPotion.getValue() && this.K94e0bce34c5b2c05df(usedItem)) {
                                 String potionName = this.Kda598d49aade3b4fef(usedItem);
                                 this.Kf4f5d0cae60b674cab("§f" + playerName + " §eвыпил §d♥ " + potionName);
                              }
                           }
                        }
                     }
                  }
               }
            }

            if ((Boolean)this.trackHealingPotion.getValue()) {
               this.K10e50cf2107a0bce81();
            }

         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{packetListener, tickListener});
   }

   private void K10e50cf2107a0bce81() {
      Set<Integer> currentPotions = new HashSet();

      for(class_1297 entity : mc.field_1687.method_18112()) {
         if (entity instanceof class_1686 potion) {
            int entityId = potion.method_5628();
            currentPotions.add(entityId);
            class_1799 stack = potion.method_7495();
            if (this.K94e0bce34c5b2c05df(stack)) {
               if (!this.trackedPotions.containsKey(entityId)) {
                  this.trackedPotions.put(entityId, new Ka38eb13d450be5d439(stack.method_7972(), potion.method_23317(), potion.method_23318(), potion.method_23321()));
               } else {
                  Ka38eb13d450be5d439 data = (Ka38eb13d450be5d439)this.trackedPotions.get(entityId);
                  data.lastX = potion.method_23317();
                  data.lastY = potion.method_23318();
                  data.lastZ = potion.method_23321();
               }
            }
         }
      }

      Set<Integer> removedPotions = new HashSet(this.trackedPotions.keySet());
      removedPotions.removeAll(currentPotions);

      for(int entityId : removedPotions) {
         Ka38eb13d450be5d439 data = (Ka38eb13d450be5d439)this.trackedPotions.remove(entityId);
         if (data != null) {
            class_238 hitBox = new class_238(data.lastX - (double)4.0F, data.lastY - (double)2.0F, data.lastZ - (double)4.0F, data.lastX + (double)4.0F, data.lastY + (double)2.0F, data.lastZ + (double)4.0F);

            for(class_1309 hit : mc.field_1687.method_8390(class_1309.class, hitBox, (e) -> true)) {
               if (hit instanceof class_1657) {
                  class_1657 player = (class_1657)hit;
                  if (!player.method_5667().equals(mc.field_1724.method_5667())) {
                     double dx = player.method_23317() - data.lastX;
                     double dz = player.method_23321() - data.lastZ;
                     double distance = Math.sqrt(dx * dx + dz * dz);
                     if (!(distance > (double)4.0F)) {
                        String playerName = player.method_5477().getString();
                        String potionName = this.Kda598d49aade3b4fef(data.stack);
                        this.Kf4f5d0cae60b674cab("§f" + playerName + " §eполучил §d♥ " + potionName);
                     }
                  }
               }
            }
         }
      }

   }

   private boolean K94e0bce34c5b2c05df(class_1799 stack) {
      if (stack.method_7960()) {
         return false;
      } else if (!stack.method_31574(class_1802.field_8574) && !stack.method_31574(class_1802.field_8436) && !stack.method_31574(class_1802.field_8150)) {
         return false;
      } else {
         class_1844 potionContents = (class_1844)stack.method_57824(class_9334.field_49651);
         if (potionContents == null) {
            return false;
         } else {
            for(class_1293 effect : potionContents.method_57397()) {
               if (effect.method_5579() == class_1294.field_5915) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   private String Kda598d49aade3b4fef(class_1799 stack) {
      class_1844 potionContents = (class_1844)stack.method_57824(class_9334.field_49651);
      if (potionContents == null) {
         return "Зелье исцеления";
      } else {
         for(class_1293 effect : potionContents.method_57397()) {
            if (effect.method_5579() == class_1294.field_5915) {
               int amplifier = effect.method_5578();
               if (amplifier >= 1) {
                  return "Зелье исцеления II";
               }

               return "Зелье исцеления";
            }
         }

         return stack.method_7964().getString();
      }
   }

   public void onDisable() {
      this.activeUseItem.clear();
      this.useStartTick.clear();
      this.trackedPotions.clear();
      super.onDisable();
   }

   private void Kf4f5d0cae60b674cab(String message) {
      if (mc.field_1724 != null) {
         mc.field_1724.method_7353(class_2561.method_43470("§8[§5Player Tracker§8] §r" + message), false);
      }
   }

   @Generated
   public static K039dcb7a6545414345 getInstance() {
      return instance;
   }
}
