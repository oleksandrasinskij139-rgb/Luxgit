package code.miaware.client.features.modules.player;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import lombok.Generated;
import net.minecraft.class_124;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1713;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_437;
import net.minecraft.class_490;

@ModuleRegister(
   name = "AutoInviz",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Автоматически пьет зелье невидимости"
)
public class K08dab3d0d42affc571 extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K08dab3d0d42affc571 instance = new K08dab3d0d42affc571();
   private static final int DRINK_THRESHOLD_SECONDS = 10;
   private long lastDrinkTime = 0L;
   private static final long DRINK_COOLDOWN = 4000L;

   public void onEvent() {
      K8d7165024ff567f5e7 updateEvent = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            if (System.currentTimeMillis() - this.lastDrinkTime >= 4000L) {
               class_1293 invisEffect = mc.field_1724.method_6112(class_1294.field_5905);
               if (invisEffect == null || invisEffect.method_5584() <= 200) {
                  this.Kc0f72cf906b8aeba0d();
               }

            }
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{updateEvent});
   }

   private void Kc0f72cf906b8aeba0d() {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         int invizSlot = this.Ka2e63e48f3ef077f99();
         if (invizSlot != -1) {
            int oldSlot = mc.field_1724.method_31548().field_7545;
            if (invizSlot < 9) {
               mc.field_1724.method_31548().field_7545 = invizSlot;
               mc.field_1690.field_1904.method_23481(true);
               (new Thread(() -> {
                  try {
                     Thread.sleep(1800L);
                     mc.execute(() -> {
                        mc.field_1690.field_1904.method_23481(false);
                        if (mc.field_1724 != null) {
                           mc.field_1724.method_31548().field_7545 = oldSlot;
                        }

                     });
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }

               })).start();
            } else {
               int emptyHotbarSlot = this.Kf0cb3604d65e6be58f();
               if (emptyHotbarSlot == -1) {
                  emptyHotbarSlot = oldSlot;
               }

               this.Kc9947dc11bb7dacd4b(invizSlot, emptyHotbarSlot, oldSlot);
            }

            this.lastDrinkTime = System.currentTimeMillis();
         }
      }
   }

   private int Ka2e63e48f3ef077f99() {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         for(int i = 0; i < 36; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (!stack.method_7960() && (stack.method_31574(class_1802.field_8574) || stack.method_31574(class_1802.field_8436) || stack.method_31574(class_1802.field_8150))) {
               String displayName = class_124.method_539(stack.method_7964().getString());
               if (displayName != null && displayName.toLowerCase().contains("невидимост")) {
                  return i;
               }
            }
         }

         return -1;
      }
   }

   private int Kf0cb3604d65e6be58f() {
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

   private void Kc9947dc11bb7dacd4b(int invSlot, int hotbarSlot, int originalSlot) {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         (new Thread(() -> {
            try {
               mc.execute(() -> mc.method_1507(new class_490(mc.field_1724)));
               Thread.sleep(50L);
               int fromPacketSlot = invSlot < 9 ? invSlot + 36 : invSlot;
               int toPacketSlot = hotbarSlot < 9 ? hotbarSlot + 36 : hotbarSlot;
               mc.field_1761.method_2906(0, fromPacketSlot, 0, class_1713.field_7790, mc.field_1724);
               mc.field_1761.method_2906(0, toPacketSlot, 0, class_1713.field_7790, mc.field_1724);
               mc.field_1761.method_2906(0, fromPacketSlot, 0, class_1713.field_7790, mc.field_1724);
               Thread.sleep(50L);
               mc.execute(() -> {
                  mc.method_1507((class_437)null);
                  mc.field_1724.method_31548().field_7545 = hotbarSlot;
                  mc.field_1690.field_1904.method_23481(true);
               });
               Thread.sleep(1800L);
               mc.execute(() -> {
                  mc.field_1690.field_1904.method_23481(false);
                  if (mc.field_1724 != null) {
                     mc.field_1724.method_31548().field_7545 = originalSlot;
                  }

               });
            } catch (InterruptedException e) {
               e.printStackTrace();
            }

         })).start();
      }
   }

   @Generated
   public static K08dab3d0d42affc571 getInstance() {
      return instance;
   }
}
