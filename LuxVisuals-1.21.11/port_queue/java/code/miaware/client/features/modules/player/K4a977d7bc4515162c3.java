package code.miaware.client.features.modules.player;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K6449169e9fd0c18e12;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.ServerRestriction;
import code.miaware.api.module.setting.K1d3d0b4020f933282f;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.server.K079c3cf92902a5b0ab;
import code.miaware.client.features.modules.player.elytraswap.K14061464cf17a12dea;
import code.miaware.client.features.modules.player.elytraswap.Ka77e06e792f2b9f5ec;
import code.miaware.client.features.modules.player.elytraswap.Kf5923afbe778603c71;
import code.miaware.client.ui.widget.overlay.Ka58c9e2c4d8aa9af2e;
import code.miaware.client.ui.widget.overlay.Kb7ca6c2b4209b8a116;
import java.util.List;
import lombok.Generated;
import net.minecraft.class_10192;
import net.minecraft.class_1304;
import net.minecraft.class_1661;
import net.minecraft.class_1713;
import net.minecraft.class_1723;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_638;
import net.minecraft.class_746;
import net.minecraft.class_9334;

@ServerRestriction(
   blockedServers = {K079c3cf92902a5b0ab.REALLYWORLD, K079c3cf92902a5b0ab.DREAMWORLD, K079c3cf92902a5b0ab.VIBEPVP, K079c3cf92902a5b0ab.SWORLD, K079c3cf92902a5b0ab.GULPVP}
)
@ModuleRegister(
   name = "Elytra Helper",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Меняет элитры и нагрудник с возвратом в исходный слот"
)
public class K4a977d7bc4515162c3 extends Kab8a8c478f7d0077cd {
   private static final K4a977d7bc4515162c3 instance = new K4a977d7bc4515162c3();
   private final K1d3d0b4020f933282f swapKey = (new K1d3d0b4020f933282f("Свап")).value(-1);
   private boolean busy;
   private boolean used;
   private long lastSwap;
   private int pairedSlot = -1;
   private class_1799 pairedStack;
   private class_746 pairedPlayer;
   private class_638 pairedWorld;

   public K4a977d7bc4515162c3() {
      this.pairedStack = class_1799.field_8037;
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.swapKey});
   }

   private void reset() {
      this.used = false;
      this.pairedSlot = -1;
      this.pairedStack = class_1799.field_8037;
      this.pairedPlayer = null;
      this.pairedWorld = null;
   }

   public void onEnable() {
      this.reset();
   }

   public void onDisable() {
      this.reset();
   }

   public void onEvent() {
      this.addEvents(new K8d7165024ff567f5e7[]{K6449169e9fd0c18e12.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (event.action() == 1 && event.matchesBind((Integer)this.swapKey.getValue())) {
            this.K1e4e46aac1691f54cb();
         }

      }))});
   }

   private static boolean K3faa815a0647289923(class_1799 stack, boolean chestplate) {
      if (stack.method_7960()) {
         return false;
      } else if (!chestplate) {
         return stack.method_31574(class_1802.field_8833);
      } else {
         class_10192 equipped = (class_10192)stack.method_57824(class_9334.field_54196);
         return !stack.method_31574(class_1802.field_8833) && equipped != null && equipped.comp_3174() == class_1304.field_6174;
      }
   }

   private int Kbd8be8984b62055103(boolean chestplate) {
      class_1661 inventory = mc.field_1724.method_31548();
      if (this.pairedSlot >= 0 && this.pairedSlot < 36 && K3faa815a0647289923(inventory.method_5438(this.pairedSlot), chestplate) && class_1799.method_31577(inventory.method_5438(this.pairedSlot), this.pairedStack)) {
         return this.pairedSlot;
      } else {
         for(int i = 0; i < 36; ++i) {
            if (K3faa815a0647289923(inventory.method_5438(i), chestplate)) {
               return i;
            }
         }

         return -1;
      }
   }

   private void K1e4e46aac1691f54cb() {
      if (!this.busy && mc.field_1724 != null && mc.field_1687 != null && mc.field_1761 != null && mc.field_1755 == null && mc.field_1724.method_5805()) {
         final class_746 player = mc.field_1724;
         final class_638 world = mc.field_1687;
         final class_1723 handler = player.field_7498;
         if (player.field_7512 == handler && handler.method_34255().method_7960()) {
            if (this.pairedPlayer != player || this.pairedWorld != world) {
               this.reset();
               this.pairedPlayer = player;
               this.pairedWorld = world;
            }

            long now = System.nanoTime();
            if (!this.used || now - this.lastSwap >= 200000000L) {
               boolean chestplate = player.method_6118(class_1304.field_6174).method_31574(class_1802.field_8833);
               int index = this.Kbd8be8984b62055103(chestplate);
               if (index >= 0) {
                  int sourceId = index < 9 ? index + 36 : index;
                  class_1735 source = (class_1735)handler.field_7761.get(sourceId);
                  class_1735 armor = (class_1735)handler.field_7761.get(6);
                  class_1799 incoming = source.method_7677().method_7972();
                  class_1799 outgoing = armor.method_7677().method_7972();
                  if (source.method_7674(player) && armor.method_7674(player) && armor.method_7680(incoming) && incoming.method_7947() <= armor.method_7676(incoming) && (outgoing.method_7960() || source.method_7680(outgoing) && outgoing.method_7947() <= source.method_7676(outgoing))) {
                     int scratch = -1;
                     if (index >= 9) {
                        int selected = player.method_31548().field_7545;

                        for(int pass = 0; pass < 2 && scratch < 0; ++pass) {
                           for(int i = 0; i < 9; ++i) {
                              if (i != selected) {
                                 class_1735 hotbar = (class_1735)handler.field_7761.get(36 + i);
                                 class_1799 stack = hotbar.method_7677();
                                 if ((pass != 0 || stack.method_7960()) && hotbar.method_7674(player) && hotbar.method_7680(incoming) && (stack.method_7960() || source.method_7680(stack) && stack.method_7947() <= source.method_7676(stack))) {
                                    scratch = i;
                                    break;
                                 }
                              }
                           }
                        }

                        if (scratch < 0) {
                           return;
                        }
                     }

                     List<Kf5923afbe778603c71> plan = K14061464cf17a12dea.plan(index, scratch);
                     this.used = true;
                     this.lastSwap = now;
                     this.busy = true;

                     try {
                        boolean success = K14061464cf17a12dea.execute(plan, new Ka77e06e792f2b9f5ec<class_1799>() {
                           public boolean valid() {
                              return K4600bef438dafc1a9c.mc.field_1724 == player && K4600bef438dafc1a9c.mc.field_1687 == world && player.method_5805() && player.field_7512 == handler && K4600bef438dafc1a9c.mc.field_1755 == null;
                           }

                           public boolean cursorEmpty() {
                              return handler.method_34255().method_7960();
                           }

                           public class_1799 copy(int slot) {
                              return ((class_1735)handler.field_7761.get(slot)).method_7677().method_7972();
                           }

                           public boolean same(class_1799 a, class_1799 b) {
                              return class_1799.method_7973(a, b);
                           }

                           public void swap(Kf5923afbe778603c71 action) {
                              K4600bef438dafc1a9c.mc.field_1761.method_2906(handler.field_7763, action.slot(), action.hotbar(), class_1713.field_7791, player);
                           }
                        });
                        if (success) {
                           this.pairedSlot = index;
                           this.pairedStack = outgoing;
                        } else {
                           Kb7ca6c2b4209b8a116.notify("Elytra Helper: свап остановлен, инвентарь изменился", Ka58c9e2c4d8aa9af2e.INFO);
                        }
                     } finally {
                        this.busy = false;
                     }

                  }
               }
            }
         }
      }
   }

   @Generated
   public static K4a977d7bc4515162c3 getInstance() {
      return instance;
   }
}
