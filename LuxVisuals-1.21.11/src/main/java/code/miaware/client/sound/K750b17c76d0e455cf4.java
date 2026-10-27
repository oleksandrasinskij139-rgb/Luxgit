package code.miaware.client.sound;

import code.miaware.client.features.modules.other.K856430871086d15b92;
import code.miaware.client.sound.filters.K3ef80fb4edefd217d2;
import code.miaware.client.sound.filters.Ka93fb8a2af23094faf;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;

@Environment(EnvType.CLIENT)
public class K750b17c76d0e455cf4 {
   private final K1837f9b6f4d15a143f mixer = K1837f9b6f4d15a143f.loadMixer();
   private final Keaf2b52b1557dcc8c6 surround = Keaf2b52b1557dcc8c6.build();
   private boolean state = false;

   private K750b17c76d0e455cf4() {
   }

   public static K750b17c76d0e455cf4 makeDistorterMixer() {
      return new K750b17c76d0e455cf4();
   }

   public void updateMixer() {
      float[] args = new float[]{0.0F, 0.0F, 1.0F, 1.0F};
      K856430871086d15b92 rtxModule = K856430871086d15b92.getInstance();
      this.setState(rtxModule != null && rtxModule.isEnabled());
      if (this.state) {
         class_310 mc = class_310.method_1551();
         this.surround.setRtxDebug(false);
         this.surround.setPlayer(mc.field_1724);
         this.surround.setTooPerfomance(((String)rtxModule.getPerformancePriority().getValue()).equals("Производительность"));
         args = this.surround.getGainArgsFromWorld();
      } else if (!this.surround.getListOfTestVecs().isEmpty()) {
         int sz = this.surround.getListOfTestVecs().size();

         for(int i = 0; (float)i < (float)sz / 2.0F; ++i) {
            if (!this.surround.getListOfTestVecs().isEmpty()) {
               this.surround.getListOfTestVecs().remove(0);
            }
         }
      } else {
         this.surround.setRtxDebug(false);
      }

      float performanceMod = this.surround.isTooPerfomance() ? 0.75F : 1.0F;
      this.mixer.setEchoEffect(args[0] * performanceMod, args[1] * (this.surround.isTooPerfomance() ? 0.4F : 0.8F));
      this.mixer.setLowPass(args[2], args[3]);
      this.K2a05b55c735aa2f7f9();
   }

   private void K2a05b55c735aa2f7f9() {
      K1837f9b6f4d15a143f mixer = this.getMixer();
      K3ef80fb4edefd217d2 reverbFilter = mixer.getReverbFilter();
      Ka93fb8a2af23094faf lowPassFilter = mixer.getLowPassFilter();
      float echoDelay = mixer.getEchoPercent();
      float echoRev = echoDelay + mixer.getReflectPercent() * 8.000011F;
      reverbFilter.decayTime = echoDelay;
      reverbFilter.reflectionsGain = echoRev * (0.05F + 0.05F * echoDelay);
      reverbFilter.reflectionsDelay = 0.125F * echoDelay;
      reverbFilter.lateReverbGain = echoRev * (1.26F + 0.2F * echoDelay);
      reverbFilter.lateReverbDelay = 0.01F * echoDelay;
      reverbFilter.checkParameters();
      float lLevelGain = mixer.getLowPassGain();
      float lLevelGainHF = mixer.getLowPassGainHF();
      lowPassFilter.gain = this.K26587dbc9c1863779a(lowPassFilter.gain, lLevelGain, lowPassFilter.gainHF > lLevelGainHF ? 0.05F : 0.1F);
      lowPassFilter.gainHF = this.K26587dbc9c1863779a(lowPassFilter.gainHF, lLevelGainHF, lowPassFilter.gainHF > lLevelGainHF ? 0.3F : 0.12F);
      lowPassFilter.checkParameters();
   }

   private float K26587dbc9c1863779a(float a, float b, float t) {
      return a + (b - a) * t;
   }

   public boolean getHasMixerLoaded() {
      return this.mixer != null;
   }

   public void init() {
   }

   public void unload() {
      if (this.mixer != null) {
         this.mixer.cleanupEffects();
      }

   }

   public K1837f9b6f4d15a143f getMixer() {
      return this.mixer;
   }

   public Keaf2b52b1557dcc8c6 getSurround() {
      return this.surround;
   }

   public boolean isState() {
      return this.state;
   }

   public void setState(boolean state) {
      this.state = state;
   }
}
