package code.miaware.client.features.modules.other;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import lombok.Generated;

@ModuleRegister(
   name = "Sound Controller",
   category = Kf76f042931183af5ba.OTHER,
   description = "Контроль громкости определенных звуков"
)
public class K03de12e3617fa217aa extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K03de12e3617fa217aa instance = new K03de12e3617fa217aa();
   private final Kb9e875ead00e0d03bd fireworkVolume = (new Kb9e875ead00e0d03bd("Фейерверки")).value(100.0F).range(0.0F, 100.0F).step(1.0F);
   private final Kb9e875ead00e0d03bd totemVolume = (new Kb9e875ead00e0d03bd("Тотемы")).value(100.0F).range(0.0F, 100.0F).step(1.0F);
   private final Kb9e875ead00e0d03bd experienceVolume = (new Kb9e875ead00e0d03bd("Опыт")).value(100.0F).range(0.0F, 100.0F).step(1.0F);

   public K03de12e3617fa217aa() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.fireworkVolume, this.totemVolume, this.experienceVolume});
   }

   public void onEvent() {
   }

   public float modifyVolume(String soundId, float originalVolume) {
      if (!this.isEnabled()) {
         return originalVolume;
      } else {
         String lowerSoundId = soundId.toLowerCase();
         if (lowerSoundId.contains("firework")) {
            float newVolume = originalVolume * (this.fireworkVolume.getValue() / 100.0F);
            System.out.println("[SoundController] Firework: " + soundId + " | Original: " + originalVolume + " | New: " + newVolume);
            return newVolume;
         } else if (lowerSoundId.contains("totem")) {
            float newVolume = originalVolume * (this.totemVolume.getValue() / 100.0F);
            System.out.println("[SoundController] Totem: " + soundId + " | Original: " + originalVolume + " | New: " + newVolume);
            return newVolume;
         } else if (!lowerSoundId.contains("experience") && !lowerSoundId.contains("orb")) {
            return originalVolume;
         } else {
            float newVolume = originalVolume * (this.experienceVolume.getValue() / 100.0F);
            System.out.println("[SoundController] Experience: " + soundId + " | Original: " + originalVolume + " | New: " + newVolume);
            return newVolume;
         }
      }
   }

   @Generated
   public static K03de12e3617fa217aa getInstance() {
      return instance;
   }
}
