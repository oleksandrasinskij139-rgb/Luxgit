package code.miaware.client.sound.filters;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_3532;

@Environment(EnvType.CLIENT)
public class K3ef80fb4edefd217d2 {
   public float density = 1.0F;
   public float diffusion = 1.0F;
   public float gain = 0.32F;
   public float gainHF = 0.89F;
   public float decayTime = 1.49F;
   public float decayHFRatio = 0.83F;
   public float reflectionsGain = 0.05F;
   public float reflectionsDelay = 0.007F;
   public float lateReverbGain = 1.26F;
   public float lateReverbDelay = 0.011F;
   public float airAbsorptionGainHF = 0.994F;
   public float roomRolloffFactor = 0.0F;
   private static final float MIN_DENSITY = 0.0F;
   private static final float MAX_DENSITY = 1.0F;
   private static final float MIN_DIFFUSION = 0.0F;
   private static final float MAX_DIFFUSION = 1.0F;
   private static final float MIN_GAIN = 0.0F;
   private static final float MAX_GAIN = 1.0F;
   private static final float MIN_GAIN_HF = 0.0F;
   private static final float MAX_GAIN_HF = 1.0F;
   private static final float MIN_DECAY_TIME = 0.1F;
   private static final float MAX_DECAY_TIME = 20.0F;
   private static final float MIN_DECAY_HF_RATIO = 0.1F;
   private static final float MAX_DECAY_HF_RATIO = 2.0F;
   private static final float MIN_REFLECTIONS_GAIN = 0.0F;
   private static final float MAX_REFLECTIONS_GAIN = 3.16F;
   private static final float MIN_REFLECTIONS_DELAY = 0.0F;
   private static final float MAX_REFLECTIONS_DELAY = 0.3F;
   private static final float MIN_LATE_REVERB_GAIN = 0.0F;
   private static final float MAX_LATE_REVERB_GAIN = 10.0F;
   private static final float MIN_LATE_REVERB_DELAY = 0.0F;
   private static final float MAX_LATE_REVERB_DELAY = 0.1F;
   private static final float MIN_AIR_ABSORPTION_GAIN_HF = 0.892F;
   private static final float MAX_AIR_ABSORPTION_GAIN_HF = 1.0F;
   private static final float MIN_ROOM_ROLLOFF_FACTOR = 0.0F;
   private static final float MAX_ROOM_ROLLOFF_FACTOR = 10.0F;

   public void checkParameters() {
      this.density = class_3532.method_15363(this.density, 0.0F, 1.0F);
      this.diffusion = class_3532.method_15363(this.diffusion, 0.0F, 1.0F);
      this.gain = class_3532.method_15363(this.gain, 0.0F, 1.0F);
      this.gainHF = class_3532.method_15363(this.gainHF, 0.0F, 1.0F);
      this.decayTime = class_3532.method_15363(this.decayTime, 0.1F, 20.0F);
      this.decayHFRatio = class_3532.method_15363(this.decayHFRatio, 0.1F, 2.0F);
      this.reflectionsGain = class_3532.method_15363(this.reflectionsGain, 0.0F, 3.16F);
      this.reflectionsDelay = class_3532.method_15363(this.reflectionsDelay, 0.0F, 0.3F);
      this.lateReverbGain = class_3532.method_15363(this.lateReverbGain, 0.0F, 10.0F);
      this.lateReverbDelay = class_3532.method_15363(this.lateReverbDelay, 0.0F, 0.1F);
      this.airAbsorptionGainHF = class_3532.method_15363(this.airAbsorptionGainHF, 0.892F, 1.0F);
      this.roomRolloffFactor = class_3532.method_15363(this.roomRolloffFactor, 0.0F, 10.0F);
   }

   public void reset() {
      this.density = 1.0F;
      this.diffusion = 1.0F;
      this.gain = 0.32F;
      this.gainHF = 0.89F;
      this.decayTime = 1.49F;
      this.decayHFRatio = 0.83F;
      this.reflectionsGain = 0.05F;
      this.reflectionsDelay = 0.007F;
      this.lateReverbGain = 1.26F;
      this.lateReverbDelay = 0.011F;
      this.airAbsorptionGainHF = 0.994F;
      this.roomRolloffFactor = 0.0F;
   }
}
