package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.world.K59e91c1b6e54ef922d;
import code.miaware.api.event.events.player.world.K8d0b25c690b59e234d;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.other.K7d074a66e20f0c1531;
import lombok.Generated;
import net.minecraft.class_1109;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_3414;

@ModuleRegister(
   name = "Hit Sound",
   category = Kf76f042931183af5ba.RENDER,
   description = "Воспроизводит звук при ударе"
)
public class K3f8376221170a10de7 extends Kab8a8c478f7d0077cd {
   private static final K3f8376221170a10de7 instance = new K3f8376221170a10de7();
   public final Kbd66519397e9a89967 soundMode = (new Kbd66519397e9a89967("Sound")).value("UwU").values("UwU", "Bonk 1", "Bonk 2", "Signal", "Screenshot", "Screenshot 2", "Oplata", "Aura");
   public final K00fef91b3d4d1b3ecc onlyCrits = (new K00fef91b3d4d1b3ecc("Только криты")).value(false);
   public final Kb9e875ead00e0d03bd volume = (new Kb9e875ead00e0d03bd("Volume")).value(50.0F).range(0.0F, 100.0F).step(1.0F);

   public K3f8376221170a10de7() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.soundMode, this.onlyCrits, this.volume});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 attackEvent = K59e91c1b6e54ef922d.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> this.Ka306da510bae5968a4(event)));
      this.addEvents(new K8d7165024ff567f5e7[]{attackEvent});
   }

   private void Ka306da510bae5968a4(K8d0b25c690b59e234d event) {
      if (this.isEnabled()) {
         if (mc.field_1724 != null) {
            class_1297 target = event.entity();
            if (target instanceof class_1309) {
               if ((Boolean)this.onlyCrits.getValue()) {
                  boolean isCrit = mc.field_1724.field_6017 > 0.0F && !mc.field_1724.method_24828() && !mc.field_1724.method_6101() && !mc.field_1724.method_5799() && !mc.field_1724.method_5765() && !mc.field_1724.method_5624();
                  if (!isCrit) {
                     return;
                  }
               }

               this.Kc475e5f8e8a0744f81();
            }
         }
      }
   }

   private void Kc475e5f8e8a0744f81() {
      class_3414 sound = this.Kf3a964a2effc02bac0();
      if (sound != null) {
         float vol = this.volume.getValue() / 100.0F;
         mc.method_1483().method_4873(class_1109.method_4757(sound, 1.0F, vol));
      }
   }

   private class_3414 Kf3a964a2effc02bac0() {
      switch ((String)this.soundMode.getValue()) {
         case "UwU" -> {
            return K7d074a66e20f0c1531.HITSOUND_UWU_EVENT;
         }
         case "Bonk 1" -> {
            return K7d074a66e20f0c1531.HITSOUND_BONK1_EVENT;
         }
         case "Bonk 2" -> {
            return K7d074a66e20f0c1531.HITSOUND_BONK2_EVENT;
         }
         case "Signal" -> {
            return K7d074a66e20f0c1531.HITSOUND_SIGNAL_EVENT;
         }
         case "Screenshot" -> {
            return K7d074a66e20f0c1531.HITSOUND_SCREENSHOT_EVENT;
         }
         case "Screenshot 2" -> {
            return K7d074a66e20f0c1531.HITSOUND_SCREENSHOT2_EVENT;
         }
         case "Oplata" -> {
            return K7d074a66e20f0c1531.HITSOUND_OPLATA_EVENT;
         }
         case "Aura" -> {
            return K7d074a66e20f0c1531.HITSOUND_AURA_EVENT;
         }
         default -> {
            return K7d074a66e20f0c1531.HITSOUND_UWU_EVENT;
         }
      }
   }

   @Generated
   public static K3f8376221170a10de7 getInstance() {
      return instance;
   }
}
