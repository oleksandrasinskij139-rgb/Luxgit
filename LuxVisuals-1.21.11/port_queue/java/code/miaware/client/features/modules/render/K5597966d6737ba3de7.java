package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.world.K503912bdf3fa552d14;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.other.K7d074a66e20f0c1531;
import lombok.Generated;
import net.minecraft.class_1109;
import net.minecraft.class_1309;

@ModuleRegister(
   name = "Kill Sound",
   category = Kf76f042931183af5ba.RENDER,
   description = "Воспроизводит звук при убийстве"
)
public class K5597966d6737ba3de7 extends Kab8a8c478f7d0077cd {
   private static final K5597966d6737ba3de7 instance = new K5597966d6737ba3de7();
   public final Kb9e875ead00e0d03bd volume = (new Kb9e875ead00e0d03bd("Громкость")).value(100.0F).range(0.0F, 200.0F).step(5.0F);

   public K5597966d6737ba3de7() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.volume});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 killEvent = K503912bdf3fa552d14.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            class_1309 killed = event.killedEntity();
            float vol = this.volume.getValue() / 100.0F;
            mc.method_1483().method_4873(class_1109.method_4757(K7d074a66e20f0c1531.KILLSOUND_RAMPAGE_EVENT, 1.0F, vol));
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{killEvent});
   }

   @Generated
   public static K5597966d6737ba3de7 getInstance() {
      return instance;
   }
}
