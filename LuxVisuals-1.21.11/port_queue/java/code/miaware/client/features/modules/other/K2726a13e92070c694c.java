package code.miaware.client.features.modules.other;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import lombok.Generated;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1802;
import net.minecraft.class_476;
import net.minecraft.class_7472;

@ModuleRegister(
   name = "AutoResell",
   category = Kf76f042931183af5ba.OTHER,
   description = "Авто-перепродажа на FunTime"
)
public class K2726a13e92070c694c extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K2726a13e92070c694c instance = new K2726a13e92070c694c();
   public final Kb9e875ead00e0d03bd interval = (new Kb9e875ead00e0d03bd("Интервал (сек)")).value(61.0F).range(10.0F, 300.0F).step(1.0F);
   private Kb21ffa49c7328544b3 state;
   private long lastActionTime;
   private long stateEnteredTime;

   public K2726a13e92070c694c() {
      this.state = Kb21ffa49c7328544b3.IDLE;
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.interval});
   }

   public void onEnable() {
      super.onEnable();
      this.state = Kb21ffa49c7328544b3.IDLE;
      this.lastActionTime = System.currentTimeMillis();
   }

   public void onDisable() {
      super.onDisable();
      this.state = Kb21ffa49c7328544b3.IDLE;
   }

   public void onEvent() {
      K8d7165024ff567f5e7 updateEvent = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            long now = System.currentTimeMillis();
            switch (this.state.ordinal()) {
               case 0:
                  if ((float)(now - this.lastActionTime) >= this.interval.getValue() * 1000.0F) {
                     mc.method_1562().method_52787(new class_7472("ah"));
                     this.state = Kb21ffa49c7328544b3.WAIT_FOR_AH;
                     this.stateEnteredTime = now;
                  }
                  break;
               case 1:
                  if (mc.field_1755 instanceof class_476 && mc.field_1724.field_7512 != null) {
                     this.state = Kb21ffa49c7328544b3.WAIT_BEFORE_FIND;
                     this.stateEnteredTime = now;
                  } else if (now - this.stateEnteredTime > 5000L) {
                     this.state = Kb21ffa49c7328544b3.IDLE;
                     this.lastActionTime = now;
                  }
                  break;
               case 2:
                  if (now - this.stateEnteredTime >= 500L) {
                     this.state = Kb21ffa49c7328544b3.FIND_ENDER_CHEST;
                     this.stateEnteredTime = now;
                  }
                  break;
               case 3:
                  if (mc.field_1724.field_7512 == null) {
                     this.state = Kb21ffa49c7328544b3.IDLE;
                     this.lastActionTime = now;
                  } else {
                     class_1735 targetSlot = null;

                     for(class_1735 slot : mc.field_1724.field_7512.field_7761) {
                        if (slot.field_7871 != mc.field_1724.method_31548() && slot.method_7681()) {
                           String itemName = slot.method_7677().method_7964().getString();
                           if (itemName.contains("Хранилище")) {
                              targetSlot = slot;
                              break;
                           }
                        }
                     }

                     if (targetSlot != null) {
                        mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, targetSlot.field_7874, 0, class_1713.field_7790, mc.field_1724);
                        this.state = Kb21ffa49c7328544b3.CLICK_CLOCK;
                        this.stateEnteredTime = now;
                     } else {
                        mc.field_1724.method_7346();
                        this.state = Kb21ffa49c7328544b3.IDLE;
                        this.lastActionTime = now;
                     }
                  }
                  break;
               case 4:
                  if (now - this.stateEnteredTime >= 500L) {
                     if (mc.field_1724.field_7512 == null) {
                        this.state = Kb21ffa49c7328544b3.IDLE;
                        this.lastActionTime = now;
                     } else {
                        class_1735 updateSlot = null;

                        for(class_1735 slot : mc.field_1724.field_7512.field_7761) {
                           if (slot.field_7871 != mc.field_1724.method_31548() && slot.method_7681() && slot.method_7677().method_7909() == class_1802.field_8137) {
                              String itemName = slot.method_7677().method_7964().getString();
                              if (itemName.contains("Обновить")) {
                                 updateSlot = slot;
                                 break;
                              }
                           }
                        }

                        if (updateSlot != null) {
                           mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, updateSlot.field_7874, 0, class_1713.field_7790, mc.field_1724);
                        }

                        this.state = Kb21ffa49c7328544b3.CLOSE_GUI;
                        this.stateEnteredTime = now;
                     }
                  }
                  break;
               case 5:
                  if (now - this.stateEnteredTime >= 300L) {
                     mc.field_1724.method_7346();
                     this.state = Kb21ffa49c7328544b3.IDLE;
                     this.lastActionTime = now;
                  }
            }

         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{updateEvent});
   }

   @Generated
   public static K2726a13e92070c694c getInstance() {
      return instance;
   }
}
