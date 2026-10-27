package code.miaware.client.features.modules.other;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import lombok.Generated;
import net.minecraft.class_418;
import net.minecraft.class_437;

@ModuleRegister(
   name = "Auto Respawn",
   category = Kf76f042931183af5ba.OTHER,
   description = "Автоматически возрождает после смерти"
)
public class K7cb6e4a5f193c3b978 extends Kab8a8c478f7d0077cd {
   private static final K7cb6e4a5f193c3b978 instance = new K7cb6e4a5f193c3b978();

   public void onEvent() {
      K8d7165024ff567f5e7 tickEvent = Kdee350abc91aa294b2.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1755 instanceof class_418 && mc.field_1724.field_6213 > 2) {
            mc.field_1724.method_7331();
            mc.method_1507((class_437)null);
         }

      }));
      this.addEvents(new K8d7165024ff567f5e7[]{tickEvent});
   }

   @Generated
   public static K7cb6e4a5f193c3b978 getInstance() {
      return instance;
   }
}
