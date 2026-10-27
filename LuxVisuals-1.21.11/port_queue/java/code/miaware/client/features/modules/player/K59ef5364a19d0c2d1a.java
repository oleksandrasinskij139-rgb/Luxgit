package code.miaware.client.features.modules.player;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K6449169e9fd0c18e12;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.event.events.render.K14caf22f676a0204f7;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.K1d3d0b4020f933282f;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.client.ui.widget.overlay.Kc2f5cef7bb2cc06f13;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.minecraft.class_124;
import net.minecraft.class_1713;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_437;
import net.minecraft.class_490;

@ModuleRegister(
   name = "FTHelper",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Автоматическое использование FunTime"
)
public class K59ef5364a19d0c2d1a extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K59ef5364a19d0c2d1a instance = new K59ef5364a19d0c2d1a();
   private final K00fef91b3d4d1b3ecc autoSwap = (new K00fef91b3d4d1b3ecc("Авто перемещение в хотбар")).value(true);
   private final K00fef91b3d4d1b3ecc ftCooldowns = (new K00fef91b3d4d1b3ecc("FTCooldowns")).value(false);
   private final K1d3d0b4020f933282f disorientationKey = (new K1d3d0b4020f933282f("Дезориентация")).value(-1);
   private final K1d3d0b4020f933282f trapKey = (new K1d3d0b4020f933282f("Трапка")).value(-1);
   private final K1d3d0b4020f933282f sugarKey = (new K1d3d0b4020f933282f("Явная пыль")).value(-1);
   private final K1d3d0b4020f933282f plastKey = (new K1d3d0b4020f933282f("Пласт")).value(-1);
   private final List<K985ae4187f60f89443> abilities = new ArrayList();
   private final Kc2f5cef7bb2cc06f13 cooldownsWidget = new Kc2f5cef7bb2cc06f13();
   private Kce266c99abe6f1d018 swapState;
   private long lastStateChange;
   private int targetInvSlot;
   private int savedHotbarItem;
   private int savedSelectedSlot;
   private K985ae4187f60f89443 pendingAbility;

   public K59ef5364a19d0c2d1a() {
      this.swapState = Kce266c99abe6f1d018.IDLE;
      this.lastStateChange = 0L;
      this.targetInvSlot = -1;
      this.savedHotbarItem = -1;
      this.savedSelectedSlot = -1;
      this.pendingAbility = null;
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.autoSwap, this.ftCooldowns, this.disorientationKey, this.trapKey, this.sugarKey, this.plastKey});
      this.abilities.add(new K985ae4187f60f89443("дезориентация", class_1802.field_8449, this.disorientationKey, "Дезориентация не найдена!", "Заюзал дезориентацию!"));
      this.abilities.add(new K985ae4187f60f89443("трапка", class_1802.field_22021, this.trapKey, "Трапка не найдена", "Заюзал трапку!"));
      this.abilities.add(new K985ae4187f60f89443("явная пыль", class_1802.field_8479, this.sugarKey, "Явная пыль не найдена!", "Заюзал явную пыль!"));
      this.abilities.add(new K985ae4187f60f89443("пласт", class_1802.field_8551, this.plastKey, "Пласт не найден!", "Заюзал пласт!"));
   }

   public void onEvent() {
      K8d7165024ff567f5e7 keyEvent = K6449169e9fd0c18e12.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null) {
            if (mc.field_1755 == null) {
               for(K985ae4187f60f89443 ability : this.abilities) {
                  int key = (Integer)ability.getBindSetting().getValue();
                  if (key != -1 && event.key() == key) {
                     ability.queueUse();
                  }
               }

            }
         }
      }));
      K8d7165024ff567f5e7 updateEvent = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null) {
            if (this.swapState != Kce266c99abe6f1d018.IDLE) {
               this.K63cd58f2dbe73ad2bd();
            } else {
               for(K985ae4187f60f89443 ability : this.abilities) {
                  if (ability.consumeQueuedUse()) {
                     this.Kc411244cedff2f4755(ability);
                     break;
                  }
               }

            }
         }
      }));
      K8d7165024ff567f5e7 render2DEvent = K14caf22f676a0204f7.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if ((Boolean)this.ftCooldowns.getValue()) {
            this.cooldownsWidget.render(event.matrixStack());
         }

      }));
      this.addEvents(new K8d7165024ff567f5e7[]{keyEvent, updateEvent, render2DEvent});
   }

   public void onDisable() {
      for(K985ae4187f60f89443 ability : this.abilities) {
         ability.reset();
      }

      this.swapState = Kce266c99abe6f1d018.IDLE;
      super.onDisable();
   }

   private void K63cd58f2dbe73ad2bd() {
      long currentTime = System.currentTimeMillis();
      switch (this.swapState.ordinal()) {
         case 1:
            mc.method_1507(new class_490(mc.field_1724));
            this.swapState = Kce266c99abe6f1d018.WAIT_INVENTORY_OPEN;
            this.lastStateChange = currentTime;
            break;
         case 2:
            if (currentTime - this.lastStateChange >= 100L) {
               this.swapState = Kce266c99abe6f1d018.SWAP_TO_HOTBAR;
               this.lastStateChange = currentTime;
            }
            break;
         case 3:
            this.savedHotbarItem = 8;
            this.K92e9c834d4a0500068(this.targetInvSlot, 8);
            this.swapState = Kce266c99abe6f1d018.CLOSE_AND_USE;
            this.lastStateChange = currentTime;
            break;
         case 4:
            if (currentTime - this.lastStateChange >= 100L) {
               mc.method_1507((class_437)null);
               this.savedSelectedSlot = mc.field_1724.method_31548().field_7545;
               mc.field_1724.method_31548().field_7545 = 8;
               mc.method_1583();
               if (this.pendingAbility != null) {
                  this.print(this.pendingAbility.getSuccessMessage());
               }

               this.swapState = Kce266c99abe6f1d018.SWAP_BACK;
               this.lastStateChange = currentTime;
            }
            break;
         case 5:
            if (currentTime - this.lastStateChange >= 200L) {
               mc.method_1507(new class_490(mc.field_1724));
               this.swapState = Kce266c99abe6f1d018.RESTORE_SLOT;
               this.lastStateChange = currentTime;
            }
            break;
         case 6:
            if (currentTime - this.lastStateChange >= 100L) {
               this.K92e9c834d4a0500068(8, this.targetInvSlot);
               mc.execute(() -> {
                  try {
                     Thread.sleep(100L);
                     mc.method_1507((class_437)null);
                     if (this.savedSelectedSlot != -1) {
                        mc.field_1724.method_31548().field_7545 = this.savedSelectedSlot;
                     }
                  } catch (Exception e) {
                     e.printStackTrace();
                  }

               });
               this.swapState = Kce266c99abe6f1d018.IDLE;
               this.targetInvSlot = -1;
               this.savedHotbarItem = -1;
               this.savedSelectedSlot = -1;
               this.pendingAbility = null;
            }
      }

   }

   private void Kc411244cedff2f4755(K985ae4187f60f89443 ability) {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         if (!mc.field_1724.method_7357().method_7904(ability.getItem().method_7854())) {
            int hotbarSlot = this.K024474a93e87454e9a(ability.getInventoryKeyword());
            if (hotbarSlot != -1) {
               this.K22c5a145b181a1d2c7(hotbarSlot);
               this.print(ability.getSuccessMessage());
            } else {
               int invSlot = this.Kcaa83784dc9d92a27a(ability.getInventoryKeyword());
               if (invSlot == -1) {
                  this.print(ability.getNotFoundMessage());
               } else {
                  if ((Boolean)this.autoSwap.getValue()) {
                     this.targetInvSlot = invSlot;
                     this.pendingAbility = ability;
                     this.swapState = Kce266c99abe6f1d018.OPEN_INVENTORY;
                     this.lastStateChange = System.currentTimeMillis();
                  } else {
                     this.print(ability.getNotFoundMessage());
                  }

               }
            }
         }
      }
   }

   private int K024474a93e87454e9a(String keyword) {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         for(int i = 0; i < 9; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (!stack.method_7960()) {
               String displayName = class_124.method_539(stack.method_7964().getString());
               if (displayName != null && displayName.toLowerCase().contains(keyword)) {
                  return i;
               }
            }
         }

         return -1;
      }
   }

   private int Kcaa83784dc9d92a27a(String keyword) {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         for(int i = 9; i < 36; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (!stack.method_7960()) {
               String displayName = class_124.method_539(stack.method_7964().getString());
               if (displayName != null && displayName.toLowerCase().contains(keyword)) {
                  return i;
               }
            }
         }

         return -1;
      }
   }

   private int Kba832e48a8ecca02e7() {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         for(int i = 0; i < 9; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7960()) {
               return i;
            }
         }

         return -1;
      }
   }

   private void K92e9c834d4a0500068(int fromSlot, int toSlot) {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         int fromPacketSlot = fromSlot < 9 ? fromSlot + 36 : fromSlot;
         int toPacketSlot = toSlot < 9 ? toSlot + 36 : toSlot;
         mc.field_1761.method_2906(0, fromPacketSlot, 0, class_1713.field_7790, mc.field_1724);
         mc.field_1761.method_2906(0, toPacketSlot, 0, class_1713.field_7790, mc.field_1724);
         mc.field_1761.method_2906(0, fromPacketSlot, 0, class_1713.field_7790, mc.field_1724);
      }
   }

   private void K22c5a145b181a1d2c7(int slot) {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         this.savedSelectedSlot = mc.field_1724.method_31548().field_7545;
         mc.field_1724.method_31548().field_7545 = slot;
         mc.method_1583();
         (new Thread(() -> {
            try {
               Thread.sleep(100L);
               if (mc.field_1724 != null && this.savedSelectedSlot != -1) {
                  mc.field_1724.method_31548().field_7545 = this.savedSelectedSlot;
               }
            } catch (InterruptedException e) {
               e.printStackTrace();
            }

         })).start();
      }
   }

   @Generated
   public static K59ef5364a19d0c2d1a getInstance() {
      return instance;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getFtCooldowns() {
      return this.ftCooldowns;
   }
}
