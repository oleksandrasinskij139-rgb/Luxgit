package code.miaware.client.sound;

import code.miaware.client.features.modules.other.K856430871086d15b92;
import code.miaware.client.sound.filters.K3ef80fb4edefd217d2;
import code.miaware.client.sound.filters.Ka93fb8a2af23094faf;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1113;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_310;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.lwjgl.openal.ALCapabilities;
import org.lwjgl.openal.EXTEfx;

@Environment(EnvType.CLIENT)
public class K1837f9b6f4d15a143f {
   private final K3ef80fb4edefd217d2 reverbFilter = new K3ef80fb4edefd217d2();
   private final Ka93fb8a2af23094faf lowPassFilter = new Ka93fb8a2af23094faf();
   private float echoPercent = 0.0F;
   private float reflectPercent = 0.0F;
   private float lowPassGain = 1.0F;
   private float lowPassGainHF = 1.0F;
   private int reverbEffectSlot = -1;
   private int reverbEffect = -1;
   private int lowPassFilter_AL = -1;
   private boolean initialized = false;
   private boolean efxSupported = false;

   public static K1837f9b6f4d15a143f loadMixer() {
      return new K1837f9b6f4d15a143f();
   }

   public void setEchoEffect(float echo, float reflect) {
      this.echoPercent = echo;
      this.reflectPercent = reflect;
   }

   public void setLowPass(float gain, float gainHF) {
      this.lowPassGain = gain;
      this.lowPassGainHF = gainHF;
   }

   public float getEchoPercent() {
      return this.echoPercent;
   }

   public float getReflectPercent() {
      return this.reflectPercent;
   }

   public float getLowPassGain() {
      return this.lowPassGain;
   }

   public float getLowPassGainHF() {
      return this.lowPassGainHF;
   }

   public void initializeEffects() {
      try {
         ALCapabilities capabilities = AL.getCapabilities();
         if (capabilities == null || !capabilities.ALC_EXT_EFX) {
            System.out.println("[RTX Sounds] EFX extension not supported, audio effects disabled");
            this.efxSupported = false;
            return;
         }

         this.efxSupported = true;
         System.out.println("[RTX Sounds] EFX extension supported, initializing audio effects...");
         this.reverbEffect = EXTEfx.alGenEffects();
         if (AL10.alGetError() != 0) {
            System.err.println("[RTX Sounds] Failed to create reverb effect");
            this.efxSupported = false;
            return;
         }

         EXTEfx.alEffecti(this.reverbEffect, 32769, 1);
         this.reverbEffectSlot = EXTEfx.alGenAuxiliaryEffectSlots();
         if (AL10.alGetError() != 0) {
            System.err.println("[RTX Sounds] Failed to create effect slot");
            this.efxSupported = false;
            return;
         }

         this.lowPassFilter_AL = EXTEfx.alGenFilters();
         if (AL10.alGetError() != 0) {
            System.err.println("[RTX Sounds] Failed to create lowpass filter");
            this.efxSupported = false;
            return;
         }

         EXTEfx.alFilteri(this.lowPassFilter_AL, 32769, 1);
         System.out.println("[RTX Sounds] Audio effects initialized successfully");
      } catch (Exception var2) {
         System.err.println("[RTX Sounds] Failed to initialize OpenAL effects: " + var2.getMessage());
         this.efxSupported = false;
      }

   }

   public void injectFiltersToChannel(class_1113 sound, int sourceId) {
      if (sourceId > 0) {
         if (!this.initialized) {
            this.initializeEffects();
            this.initialized = true;
         }

         if (this.efxSupported) {
            try {
               if (this.reverbEffect != -1 && this.reverbEffectSlot != -1) {
                  this.K94026b3fb031577f11();
                  EXTEfx.alAuxiliaryEffectSloti(this.reverbEffectSlot, 1, this.reverbEffect);
                  if (AL10.alGetError() == 0) {
                     AL11.alSource3i(sourceId, 131078, this.reverbEffectSlot, 0, 0);
                  }
               }

               if (this.lowPassFilter_AL != -1) {
                  this.Ke6df7ccc88b22f15b1();
                  AL10.alSourcei(sourceId, 131077, this.lowPassFilter_AL);
               }

               if ((Boolean)K856430871086d15b92.getInstance().getStereo().getValue()) {
                  this.K11e7817c9f4963cb69(sound, sourceId);
               }

               if ((Boolean)K856430871086d15b92.getInstance().getTone().getValue()) {
                  this.K32d869ac95db5965d4(sound, sourceId);
               }
            } catch (Exception var4) {
            }
         }
      }

   }

   private void K94026b3fb031577f11() {
      if (this.reverbEffect != -1) {
         try {
            EXTEfx.alEffectf(this.reverbEffect, 1, this.reverbFilter.density);
            EXTEfx.alEffectf(this.reverbEffect, 2, this.reverbFilter.diffusion);
            EXTEfx.alEffectf(this.reverbEffect, 3, this.reverbFilter.gain);
            EXTEfx.alEffectf(this.reverbEffect, 4, this.reverbFilter.gainHF);
            EXTEfx.alEffectf(this.reverbEffect, 5, this.reverbFilter.decayTime);
            EXTEfx.alEffectf(this.reverbEffect, 6, this.reverbFilter.decayHFRatio);
            EXTEfx.alEffectf(this.reverbEffect, 7, this.reverbFilter.reflectionsGain);
            EXTEfx.alEffectf(this.reverbEffect, 8, this.reverbFilter.reflectionsDelay);
            EXTEfx.alEffectf(this.reverbEffect, 9, this.reverbFilter.lateReverbGain);
            EXTEfx.alEffectf(this.reverbEffect, 10, this.reverbFilter.lateReverbDelay);
            EXTEfx.alEffectf(this.reverbEffect, 11, this.reverbFilter.airAbsorptionGainHF);
            EXTEfx.alEffectf(this.reverbEffect, 12, this.reverbFilter.roomRolloffFactor);
         } catch (Exception var2) {
            System.err.println("[RTX Sounds] Failed to apply reverb parameters: " + var2.getMessage());
         }
      }

   }

   private void Ke6df7ccc88b22f15b1() {
      if (this.lowPassFilter_AL != -1) {
         try {
            EXTEfx.alFilterf(this.lowPassFilter_AL, 1, this.lowPassFilter.gain);
            EXTEfx.alFilterf(this.lowPassFilter_AL, 2, this.lowPassFilter.gainHF);
         } catch (Exception var2) {
            System.err.println("[RTX Sounds] Failed to apply lowpass parameters: " + var2.getMessage());
         }
      }

   }

   private void K11e7817c9f4963cb69(class_1113 sound, int sourceId) {
      class_310 mc = class_310.method_1551();
      if (mc.field_1724 != null && !sound.method_4787()) {
         class_243 playerPos = new class_243(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321());
         class_243 soundPos = new class_243(sound.method_4784(), sound.method_4779(), sound.method_4778());
         class_241 angles = Ke3a5c183fe68f5be0b.calculate(playerPos, soundPos);
         float playerYaw = mc.field_1724.method_36454();
         float angleDiff = Ke3a5c183fe68f5be0b.getAngleDifference(angles.field_1343, playerYaw);
         float stereoModifier = 1.0F - angleDiff / 180.0F * 0.3F;
         stereoModifier = Math.max(0.7F, Math.min(1.0F, stereoModifier));
         float currentVolume = sound.method_4781();
         AL10.alSourcef(sourceId, 4106, currentVolume * stereoModifier);
      }

   }

   private void K32d869ac95db5965d4(class_1113 sound, int sourceId) {
      float basePitch = sound.method_4782();
      float toneModifier = 1.0F + this.echoPercent * 0.05F;
      toneModifier = Math.max(0.95F, Math.min(1.05F, toneModifier));
      AL10.alSourcef(sourceId, 4099, basePitch * toneModifier);
   }

   public void cleanupEffects() {
      try {
         if (this.reverbEffect != -1) {
            EXTEfx.alDeleteEffects(this.reverbEffect);
            this.reverbEffect = -1;
         }

         if (this.reverbEffectSlot != -1) {
            EXTEfx.alDeleteAuxiliaryEffectSlots(this.reverbEffectSlot);
            this.reverbEffectSlot = -1;
         }

         if (this.lowPassFilter_AL != -1) {
            EXTEfx.alDeleteFilters(this.lowPassFilter_AL);
            this.lowPassFilter_AL = -1;
         }
      } catch (Exception var2) {
         System.err.println("[RTX Sounds] Failed to cleanup OpenAL effects: " + var2.getMessage());
      }

   }

   public Ka93fb8a2af23094faf getLowPassFilter() {
      return this.lowPassFilter;
   }

   public K3ef80fb4edefd217d2 getReverbFilter() {
      return this.reverbFilter;
   }
}
