package code.miaware.client.features.modules.other;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.world.K94902759dafeeed39c;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.backend.Kd9271a319f911decbe;
import lombok.Generated;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1792;
import net.minecraft.class_3675;

@ModuleRegister(
   name = "ItemScroller",
   category = Kf76f042931183af5ba.OTHER,
   description = "Улучшения для работы с мышью в инвентаре"
)
public class K1bd34fc1db8d360bd3 extends Kab8a8c478f7d0077cd {
   private static final K1bd34fc1db8d360bd3 instance = new K1bd34fc1db8d360bd3();
   public final Kb9e875ead00e0d03bd delay = (new Kb9e875ead00e0d03bd("Delay")).value(0.0F).range(0.0F, 200.0F).step(5.0F);
   private boolean stop = false;

   public K1bd34fc1db8d360bd3() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.delay});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 clickSlotEvent = K94902759dafeeed39c.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         class_3675.class_306 sneakKey = class_3675.method_15981(mc.field_1690.field_1832.method_1428());
         if (Kd9271a319f911decbe.isPressed(sneakKey.method_1444()) && (Kd9271a319f911decbe.isPressed(341) || Kd9271a319f911decbe.isPressed(345)) && event.slotActionType() == class_1713.field_7795 && !this.stop) {
            class_1792 copy = ((class_1735)mc.field_1724.field_7512.field_7761.get(event.slot())).method_7677().method_7909();
            this.stop = true;

            for(int i2 = 0; i2 < mc.field_1724.field_7512.field_7761.size(); ++i2) {
               if (((class_1735)mc.field_1724.field_7512.field_7761.get(i2)).method_7677().method_7909() == copy) {
                  mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, i2, 1, class_1713.field_7795, mc.field_1724);
               }
            }

            this.stop = false;
         }

      }));
      this.addEvents(new K8d7165024ff567f5e7[]{clickSlotEvent});
   }

   @Generated
   public static K1bd34fc1db8d360bd3 getInstance() {
      return instance;
   }
}
