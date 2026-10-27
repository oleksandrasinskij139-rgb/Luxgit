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
import code.miaware.client.ui.widget.overlay.K4bc70aaac660178ed2;
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
   name = "HWHelper",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Автоматическое использование HolyWorld предметов"
)
public class K3232577b3fa2418719 extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K3232577b3fa2418719 instance = new K3232577b3fa2418719();
   private final K00fef91b3d4d1b3ecc autoSwap = (new K00fef91b3d4d1b3ecc("Авто перемещение в хотбар")).value(true);
   private final K00fef91b3d4d1b3ecc hwCooldowns = (new K00fef91b3d4d1b3ecc("HWCooldowns")).value(false);
   private final K1d3d0b4020f933282f fireKey = (new K1d3d0b4020f933282f("Взрывная штучка")).value(-1);
   private final K1d3d0b4020f933282f stanKey = (new K1d3d0b4020f933282f("Стан")).value(-1);
   private final K1d3d0b4020f933282f trapKey = (new K1d3d0b4020f933282f("Взрывная трапка")).value(-1);
   private final K1d3d0b4020f933282f trapPurpleKey = (new K1d3d0b4020f933282f("Трапка")).value(-1);
   private final List<K22857db6e43fc43aeb> abilities = new ArrayList();
   private final K4bc70aaac660178ed2 cooldownsWidget = new K4bc70aaac660178ed2();
   private K1468973717ca89dc1f swapState;
   private long lastStateChange;
   private int targetInvSlot;
   private int savedHotbarItem;
   private int savedSelectedSlot;
   private K22857db6e43fc43aeb pendingAbility;

   public K3232577b3fa2418719() {
      this.swapState = K1468973717ca89dc1f.IDLE;
      this.lastStateChange = 0L;
      this.targetInvSlot = -1;
      this.savedHotbarItem = -1;
      this.savedSelectedSlot = -1;
      this.pendingAbility = null;
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.autoSwap, this.hwCooldowns, this.fireKey, this.stanKey, this.trapKey, this.trapPurpleKey});
      this.abilities.add(new K22857db6e43fc43aeb("взрывная штучка", class_1802.field_8814, this.fireKey, "Взрывная штучка не найдена!", "Заюзал взрывную штучку!"));
      this.abilities.add(new K22857db6e43fc43aeb("стан", class_1802.field_8137, this.stanKey, "Стан не найден!", "Заюзал стан!"));
      this.abilities.add(new K22857db6e43fc43aeb("взрывная трапка", class_1802.field_8662, this.trapKey, "Взрывная трапка не найдена!", "Заюзал взрывную трапку!"));
      this.abilities.add(new K22857db6e43fc43aeb("трапка", class_1802.field_8882, this.trapPurpleKey, "Трапка не найдена!", "Заюзал трапку!"));
   }

   public void onEvent() {
      K8d7165024ff567f5e7 keyEvent = K6449169e9fd0c18e12.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null) {
            if (mc.field_1755 == null) {
               for(K22857db6e43fc43aeb ability : this.abilities) {
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
            if (this.swapState != K1468973717ca89dc1f.IDLE) {
               this.K83e24319daa40a67b6();
            } else {
               for(K22857db6e43fc43aeb ability : this.abilities) {
                  if (ability.consumeQueuedUse()) {
                     this.K9b0d059fc60e3c7f14(ability);
                     break;
                  }
               }

            }
         }
      }));
      K8d7165024ff567f5e7 render2DEvent = K14caf22f676a0204f7.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if ((Boolean)this.hwCooldowns.getValue()) {
            this.cooldownsWidget.render(event.matrixStack());
         }

      }));
      this.addEvents(new K8d7165024ff567f5e7[]{keyEvent, updateEvent, render2DEvent});
   }

   public void onDisable() {
      for(K22857db6e43fc43aeb ability : this.abilities) {
         ability.reset();
      }

      this.swapState = K1468973717ca89dc1f.IDLE;
      super.onDisable();
   }

   private void K83e24319daa40a67b6() {
      long currentTime = System.currentTimeMillis();
      switch (this.swapState.ordinal()) {
         case 1:
            mc.method_1507(new class_490(mc.field_1724));
            this.swapState = K1468973717ca89dc1f.WAIT_INVENTORY_OPEN;
            this.lastStateChange = currentTime;
            break;
         case 2:
            if (currentTime - this.lastStateChange >= 100L) {
               this.swapState = K1468973717ca89dc1f.SWAP_TO_HOTBAR;
               this.lastStateChange = currentTime;
            }
            break;
         case 3:
            this.savedHotbarItem = 8;
            this.K8078b2c912ee954abe(this.targetInvSlot, 8);
            this.swapState = K1468973717ca89dc1f.CLOSE_AND_USE;
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

               this.swapState = K1468973717ca89dc1f.SWAP_BACK;
               this.lastStateChange = currentTime;
            }
            break;
         case 5:
            if (currentTime - this.lastStateChange >= 200L) {
               mc.method_1507(new class_490(mc.field_1724));
               this.swapState = K1468973717ca89dc1f.RESTORE_SLOT;
               this.lastStateChange = currentTime;
            }
            break;
         case 6:
            if (currentTime - this.lastStateChange >= 100L) {
               this.K8078b2c912ee954abe(8, this.targetInvSlot);
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
               this.swapState = K1468973717ca89dc1f.IDLE;
               this.targetInvSlot = -1;
               this.savedHotbarItem = -1;
               this.savedSelectedSlot = -1;
               this.pendingAbility = null;
            }
      }

   }

   private void K9b0d059fc60e3c7f14(K22857db6e43fc43aeb ability) {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         if (!mc.field_1724.method_7357().method_7904(ability.getItem().method_7854())) {
            int hotbarSlot = this.Kc711cdeb58b1ddfdb9(ability.getInventoryKeyword());
            if (hotbarSlot != -1) {
               this.Kf4758362932d280654(hotbarSlot);
               this.print(ability.getSuccessMessage());
            } else {
               int invSlot = this.K0101a63684e3f89c07(ability.getInventoryKeyword());
               if (invSlot == -1) {
                  this.print(ability.getNotFoundMessage());
               } else {
                  if ((Boolean)this.autoSwap.getValue()) {
                     this.targetInvSlot = invSlot;
                     this.pendingAbility = ability;
                     this.swapState = K1468973717ca89dc1f.OPEN_INVENTORY;
                     this.lastStateChange = System.currentTimeMillis();
                  } else {
                     this.print(ability.getNotFoundMessage());
                  }

               }
            }
         }
      }
   }

   private int Kc711cdeb58b1ddfdb9(String keyword) {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         for(int i = 0; i < 9; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (!stack.method_7960()) {
               String displayName = class_124.method_539(stack.method_7964().getString());
               if (displayName != null) {
                  String lowerName = displayName.toLowerCase();
                  System.out.println("[HWHelper DEBUG] Проверяем: '" + displayName + "' (lower: '" + lowerName + "') для keyword: '" + keyword + "'");
                  if (this.K86d79611ba9b75a39e(lowerName, keyword)) {
                     System.out.println("[HWHelper] Найден предмет в хотбаре: '" + displayName + "' для keyword: '" + keyword + "'");
                     return i;
                  }
               }
            }
         }

         return -1;
      }
   }

   private int K0101a63684e3f89c07(String keyword) {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         for(int i = 9; i < 36; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (!stack.method_7960()) {
               String displayName = class_124.method_539(stack.method_7964().getString());
               if (displayName != null) {
                  String lowerName = displayName.toLowerCase();
                  System.out.println("[HWHelper DEBUG] Проверяем: '" + displayName + "' (lower: '" + lowerName + "') для keyword: '" + keyword + "'");
                  if (this.K86d79611ba9b75a39e(lowerName, keyword)) {
                     System.out.println("[HWHelper] Найден предмет в инвентаре: '" + displayName + "' для keyword: '" + keyword + "'");
                     return i;
                  }
               }
            }
         }

         return -1;
      }
   }

   private boolean K86d79611ba9b75a39e(String displayName, String keyword) {
      if (keyword.equals("взрывная трапка")) {
         return displayName.contains("взрывная") && displayName.contains("трапка");
      } else if (!keyword.equals("трапка")) {
         return keyword.equals("взрывная штучка") ? displayName.contains("взрывная штучка") : displayName.contains(keyword);
      } else {
         return displayName.contains("трапка") && !displayName.contains("взрывная");
      }
   }

   private int Ke46d3763401f0b805a() {
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

   private void K8078b2c912ee954abe(int fromSlot, int toSlot) {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         int fromPacketSlot = fromSlot < 9 ? fromSlot + 36 : fromSlot;
         int toPacketSlot = toSlot < 9 ? toSlot + 36 : toSlot;
         mc.field_1761.method_2906(0, fromPacketSlot, 0, class_1713.field_7790, mc.field_1724);
         mc.field_1761.method_2906(0, toPacketSlot, 0, class_1713.field_7790, mc.field_1724);
         mc.field_1761.method_2906(0, fromPacketSlot, 0, class_1713.field_7790, mc.field_1724);
      }
   }

   private void Kf4758362932d280654(int slot) {
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
   public static K3232577b3fa2418719 getInstance() {
      return instance;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getHwCooldowns() {
      return this.hwCooldowns;
   }
}
