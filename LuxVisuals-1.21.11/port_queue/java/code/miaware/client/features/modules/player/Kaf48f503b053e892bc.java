package code.miaware.client.features.modules.player;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import lombok.Generated;
import net.minecraft.class_1799;
import net.minecraft.class_1802;

@ModuleRegister(
   name = "Elytra Utils",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Автоматически пишет /fly при надевании элитр"
)
public class Kaf48f503b053e892bc extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final Kaf48f503b053e892bc instance = new Kaf48f503b053e892bc();
   private boolean wasWearingElytra = false;

   public void onEvent() {
      K8d7165024ff567f5e7 tickEvent = Kdee350abc91aa294b2.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            class_1799 chestplate = mc.field_1724.method_31548().method_7372(2);
            boolean isWearingElytra = chestplate.method_31574(class_1802.field_8833);
            if (isWearingElytra && !this.wasWearingElytra && mc.field_1724.field_3944 != null) {
               mc.field_1724.field_3944.method_45729("/fly");
            }

            this.wasWearingElytra = isWearingElytra;
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{tickEvent});
   }

   public void onDisable() {
      super.onDisable();
      this.wasWearingElytra = false;
   }

   @Generated
   public static Kaf48f503b053e892bc getInstance() {
      return instance;
   }
}
