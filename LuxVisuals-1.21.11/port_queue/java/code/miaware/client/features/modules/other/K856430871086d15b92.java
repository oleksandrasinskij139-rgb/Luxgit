package code.miaware.client.features.modules.other;

import code.miaware.MiaWare;
import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import lombok.Generated;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@ModuleRegister(
   name = "RTX Sounds",
   category = Kf76f042931183af5ba.OTHER,
   description = "Улучшает качество звука с 3D-эффектами и тональной компенсацией"
)
@Environment(EnvType.CLIENT)
public class K856430871086d15b92 extends Kab8a8c478f7d0077cd {
   private static final K856430871086d15b92 instance = new K856430871086d15b92();
   public final Kbd66519397e9a89967 performancePriority = (new Kbd66519397e9a89967("Качество звука")).value("Производительность").values("Производительность", "Качество");
   public final K00fef91b3d4d1b3ecc stereo = (new K00fef91b3d4d1b3ecc("3D стерео")).value(true);
   public final K00fef91b3d4d1b3ecc tone = (new K00fef91b3d4d1b3ecc("Тон")).value(true);

   private K856430871086d15b92() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.performancePriority, this.stereo, this.tone});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 tickEvent = Kdee350abc91aa294b2.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (this.isEnabled()) {
            if (MiaWare.getSoundRTX() != null) {
               MiaWare.getSoundRTX().updateMixer();
            }

         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{tickEvent});
   }

   public void onEnable() {
      super.onEnable();
      if (mc.field_1724 != null && MiaWare.getSoundRTX() != null) {
         MiaWare.getSoundRTX().updateMixer();
      }

   }

   public void onDisable() {
      super.onDisable();
      if (MiaWare.getSoundRTX() != null) {
         MiaWare.getSoundRTX().setState(false);
      }

   }

   public void updateMixer() {
   }

   @Generated
   public static K856430871086d15b92 getInstance() {
      return instance;
   }

   @Generated
   public Kbd66519397e9a89967 getPerformancePriority() {
      return this.performancePriority;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getStereo() {
      return this.stereo;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getTone() {
      return this.tone;
   }
}
