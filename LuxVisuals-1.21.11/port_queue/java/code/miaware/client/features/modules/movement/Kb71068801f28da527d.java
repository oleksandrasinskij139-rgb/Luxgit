package code.miaware.client.features.modules.movement;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.move.K9127285e6d255e9450;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.utils.player.K131e64425767c5c203;
import code.miaware.api.utils.player.K8c202e08d32fc608f9;
import lombok.Generated;
import net.minecraft.class_1294;
import net.minecraft.class_743;

@ModuleRegister(
   name = "Sprint",
   category = Kf76f042931183af5ba.MOVEMENT,
   description = "Автоматический бег"
)
public class Kb71068801f28da527d extends Kab8a8c478f7d0077cd {
   private static final Kb71068801f28da527d instance = new Kb71068801f28da527d();

   public Kb71068801f28da527d() {
      this.setEnabled(true);
   }

   public void onEvent() {
      K8d7165024ff567f5e7 sprintEvent = K9127285e6d255e9450.getInstance().subscribe(new Ka68e0b23b1084c01e4(1, (event) -> {
         if (this.K7d2500899bcbee09c6(event.getDirectionalInput())) {
            event.setSprint(true);
         }

      }));
      this.addEvents(new K8d7165024ff567f5e7[]{sprintEvent});
   }

   private boolean K7d2500899bcbee09c6(K8c202e08d32fc608f9 input) {
      if (mc.field_1724 != null && input != null) {
         if (!input.isMoving()) {
            return false;
         } else if (mc.field_1724.method_5715()) {
            return false;
         } else if (mc.field_1724.field_5976) {
            return false;
         } else if (mc.field_1724.method_6115()) {
            return false;
         } else if (mc.field_1724.method_3144()) {
            return false;
         } else if (mc.field_1724.method_5765()) {
            return false;
         } else if (K131e64425767c5c203.isInWeb()) {
            return false;
         } else {
            boolean inWater = mc.field_1724.method_5799() || mc.field_1724.method_5869() || mc.field_1724.method_5681();
            if (mc.field_1724.method_6059(class_1294.field_5919)) {
               return false;
            } else if (!mc.field_1724.method_31549().field_7478 && mc.field_1724.method_7344().method_7586() <= 6) {
               return false;
            } else {
               float forward = class_743.method_40218(input.isForwards(), input.isBackwards());
               if (forward <= 0.0F) {
                  return false;
               } else if (inWater) {
                  mc.field_1724.method_5728(true);
                  return true;
               } else {
                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   @Generated
   public static Kb71068801f28da527d getInstance() {
      return instance;
   }
}
