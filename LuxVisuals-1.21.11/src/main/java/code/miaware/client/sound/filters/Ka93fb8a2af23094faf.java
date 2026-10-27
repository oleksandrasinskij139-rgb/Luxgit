package code.miaware.client.sound.filters;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_3532;

@Environment(EnvType.CLIENT)
public class Ka93fb8a2af23094faf {
   public float gain = 1.0F;
   public float gainHF = 1.0F;
   private static final float MIN_GAIN = 0.0F;
   private static final float MAX_GAIN = 1.0F;
   private static final float MIN_GAIN_HF = 0.0F;
   private static final float MAX_GAIN_HF = 1.0F;

   public void checkParameters() {
      this.gain = class_3532.method_15363(this.gain, 0.0F, 1.0F);
      this.gainHF = class_3532.method_15363(this.gainHF, 0.0F, 1.0F);
   }

   public void reset() {
      this.gain = 1.0F;
      this.gainHF = 1.0F;
   }
}
