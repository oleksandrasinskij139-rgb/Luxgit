package code.miaware.client.features.modules.other;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K001d59b5164d1d1f95;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.utils.player.Kd03e5f745eca318ca1;
import lombok.Generated;
import net.minecraft.class_1268;
import net.minecraft.class_1707;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2596;
import net.minecraft.class_437;
import net.minecraft.class_476;
import net.minecraft.class_7439;

@ModuleRegister(
   name = "Joiner",
   category = Kf76f042931183af5ba.OTHER,
   description = "Автоматический вход на сервер"
)
public class Kb445c253bd70fdb9f6 extends Kab8a8c478f7d0077cd {
   private static final Kb445c253bd70fdb9f6 instance = new Kb445c253bd70fdb9f6();
   private boolean compassClick = false;
   private long last;
   private boolean restart;

   public void toggle() {
      super.toggle();
      this.compassClick = false;
   }

   public void onEvent() {
      K8d7165024ff567f5e7 updateEvent = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if ((!this.compassClick || this.restart) && mc.field_1755 == null) {
            int compassSlot = Kd03e5f745eca318ca1.findItem(class_1802.field_8251, true);
            if (compassSlot == -1) {
               return;
            }

            Kd03e5f745eca318ca1.swapToSlot(compassSlot);
            Kd03e5f745eca318ca1.useItem(class_1268.field_5808);
            this.compassClick = true;
         }

         if (this.compassClick) {
            class_437 patt0$temp = mc.field_1755;
            if (patt0$temp instanceof class_476) {
               class_476 screen = (class_476)patt0$temp;

               for(int i = 0; i < ((class_1707)screen.method_17577()).field_7761.size(); ++i) {
                  class_1799 stack = ((class_1707)screen.method_17577()).method_7611(i).method_7677();
                  if (stack.method_7964().getString().contains("Дуэли")) {
                     Kd03e5f745eca318ca1.swapSlots(i, 4);
                     this.compassClick = false;
                     this.restart = false;
                     break;
                  }
               }
            }
         }

      }));
      K8d7165024ff567f5e7 packetEvent = K001d59b5164d1d1f95.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (event.isReceive()) {
            class_2596 patt0$temp = event.packet();
            if (patt0$temp instanceof class_7439) {
               class_7439 packet = (class_7439)patt0$temp;
               String message = packet.comp_763().getString();
               if (message.contains("Вы уже подключены на этот сервер") || message.contains("Сервер заполнен") || message.contains("Вы были кикнуты с сервера 1duels")) {
                  this.compassClick = false;
                  this.restart = true;
                  K001d59b5164d1d1f95.getInstance().setCancel(true);
               }
            }

         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{updateEvent, packetEvent});
   }

   @Generated
   public static Kb445c253bd70fdb9f6 getInstance() {
      return instance;
   }
}
