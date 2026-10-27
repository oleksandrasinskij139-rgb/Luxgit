package code.miaware.client.features.modules.render;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import lombok.Generated;

@ModuleRegister(
   name = "Optimization",
   category = Kf76f042931183af5ba.RENDER,
   description = "Оптимизация производительности"
)
public class K45978f900e11d6615f extends Kab8a8c478f7d0077cd {
   private static final K45978f900e11d6615f instance = new K45978f900e11d6615f();
   private final K00fef91b3d4d1b3ecc blur = (new K00fef91b3d4d1b3ecc("Enable Blur for Widgets")).value(false);
   private final K00fef91b3d4d1b3ecc cullMobs = (new K00fef91b3d4d1b3ecc("Don't render mobs through walls")).value(false);
   private final K00fef91b3d4d1b3ecc cullItems = (new K00fef91b3d4d1b3ecc("Don't render items through walls")).value(false);
   private final K00fef91b3d4d1b3ecc gpuOptimization = (new K00fef91b3d4d1b3ecc("GPU Optimization")).value(true);

   public static K45978f900e11d6615f getInstance() {
      return instance;
   }

   public K45978f900e11d6615f() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.blur, this.cullMobs, this.cullItems, this.gpuOptimization});
      this.setEnabled(true);
   }

   public void onEvent() {
   }

   public boolean isBlurDisabled() {
      return this.isEnabled() && !(Boolean)this.blur.getValue();
   }

   public boolean isShulkerViewerDisabled() {
      return false;
   }

   public boolean isHeavyModulesOptimized() {
      return false;
   }

   public boolean isMobCullingEnabled() {
      return this.isEnabled() && (Boolean)this.cullMobs.getValue();
   }

   public boolean isItemCullingEnabled() {
      return this.isEnabled() && (Boolean)this.cullItems.getValue();
   }

   public boolean isGPUOptimizationEnabled() {
      return this.isEnabled() && (Boolean)this.gpuOptimization.getValue();
   }

   public boolean isBatchRenderingEnabled() {
      return this.isGPUOptimizationEnabled();
   }

   public boolean isFrustumCullingEnabled() {
      return this.isGPUOptimizationEnabled();
   }

   public boolean isLODSystemEnabled() {
      return this.isGPUOptimizationEnabled();
   }

   public boolean isParticleReductionEnabled() {
      return this.isGPUOptimizationEnabled();
   }

   public boolean isTranslucencyOptimized() {
      return this.isGPUOptimizationEnabled();
   }

   public boolean isGLStateCachingEnabled() {
      return this.isGPUOptimizationEnabled();
   }

   public boolean isSkipHiddenRendersEnabled() {
      return this.isGPUOptimizationEnabled();
   }

   public boolean isAggressiveModeEnabled() {
      return false;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getBlur() {
      return this.blur;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getCullMobs() {
      return this.cullMobs;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getCullItems() {
      return this.cullItems;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getGpuOptimization() {
      return this.gpuOptimization;
   }
}
