package code.miaware.client.features.modules.other;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K256ab2bf60c15ab260;
import code.miaware.api.event.events.client.K6449169e9fd0c18e12;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K1d3d0b4020f933282f;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import lombok.Generated;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_239;
import net.minecraft.class_3966;

@ModuleRegister(
   name = "Click Friend",
   category = Kf76f042931183af5ba.OTHER,
   description = "Добавление/удаление друзей по клику"
)
public class K5eadf978fedb7bcffb extends Kab8a8c478f7d0077cd {
   private static final K5eadf978fedb7bcffb instance = new K5eadf978fedb7bcffb();
   private final K1d3d0b4020f933282f friendBind = (new K1d3d0b4020f933282f("Бинд друга")).value(-999);

   public K5eadf978fedb7bcffb() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.friendBind});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 keyEvent = K6449169e9fd0c18e12.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && event.action() == 1) {
            if (this.K5a0b2daaacae389d3c(event, (Integer)this.friendBind.getValue())) {
               this.K110cf78ce7d167a884();
            }

         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{keyEvent});
   }

   private void K110cf78ce7d167a884() {
      class_239 var3 = mc.field_1765;
      if (var3 instanceof class_3966 hit) {
         class_1297 var4 = hit.method_17782();
         if (var4 instanceof class_1657 player) {
            String var5 = player.method_7334().getName();
            if (K137d4e93c7270b0a04.getInstance().contains(var5)) {
               K137d4e93c7270b0a04.getInstance().remove(var5);
               this.print("Удален друг: " + var5);
            } else {
               K137d4e93c7270b0a04.getInstance().add(var5);
               this.print("Добавлен друг: " + var5);
            }

            return;
         }
      }

   }

   private boolean K5a0b2daaacae389d3c(K256ab2bf60c15ab260 event, int bind) {
      if (bind == -999) {
         return false;
      } else {
         return event.mouse() ? bind == -100 + event.key() : bind == event.key();
      }
   }

   @Generated
   public static K5eadf978fedb7bcffb getInstance() {
      return instance;
   }
}
