package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import lombok.Generated;
import net.minecraft.class_1293;
import net.minecraft.class_1294;

@ModuleRegister(
   name = "Night Vision",
   category = Kf76f042931183af5ba.RENDER,
   description = "Улучшает видимость в темноте"
)
public class Kd88bd6ff24b3f072b8 extends Kab8a8c478f7d0077cd {
   private static final Kd88bd6ff24b3f072b8 instance = new Kd88bd6ff24b3f072b8();

   public void onDisable() {
      this.remove();
   }

   public void onEvent() {
      K8d7165024ff567f5e7 tickEvent = Kdee350abc91aa294b2.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> this.add()));
      this.addEvents(new K8d7165024ff567f5e7[]{tickEvent});
   }

   private void remove() {
      if (mc.field_1724 != null) {
         mc.field_1724.method_6016(class_1294.field_5925);
      }
   }

   private void add() {
      if (mc.field_1724 != null) {
         mc.field_1724.method_6092(new class_1293(class_1294.field_5925, -1, 0, false, false, false));
      }
   }

   @Generated
   public static Kd88bd6ff24b3f072b8 getInstance() {
      return instance;
   }
}
