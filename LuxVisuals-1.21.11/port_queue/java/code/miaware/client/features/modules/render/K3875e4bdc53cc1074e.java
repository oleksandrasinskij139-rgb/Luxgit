package code.miaware.client.features.modules.render;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Ka4b85e821e3749c3d9;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import java.util.Arrays;
import lombok.Generated;

@ModuleRegister(
   name = "Removals",
   category = Kf76f042931183af5ba.RENDER,
   description = "Убирает различные визуальные эффекты"
)
public class K3875e4bdc53cc1074e extends Kab8a8c478f7d0077cd {
   private static final K3875e4bdc53cc1074e instance = new K3875e4bdc53cc1074e();
   private final String[] elements = new String[]{"Fire overlay", "Hurt camera", "Water overlay", "Scoreboard", "Glow effect", "Bad effects", "Boss bar", "Clear lava", "Убрать воду"};
   private final Ka4b85e821e3749c3d9 remove;

   public K3875e4bdc53cc1074e() {
      this.remove = (new Ka4b85e821e3749c3d9("Remove")).value((K00fef91b3d4d1b3ecc[])Arrays.stream(this.elements).map((name) -> (new K00fef91b3d4d1b3ecc(name)).value(false)).toArray((x$0) -> new K00fef91b3d4d1b3ecc[x$0]));
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.remove});
   }

   public boolean isFireOverlay() {
      return this.isEnabled() && this.remove.isEnabled("Fire overlay");
   }

   public boolean isHurtCamera() {
      return this.isEnabled() && this.remove.isEnabled("Hurt camera");
   }

   public boolean isWaterOverlay() {
      return this.isEnabled() && this.remove.isEnabled("Water overlay");
   }

   public boolean isScoreboard() {
      return this.isEnabled() && this.remove.isEnabled("Scoreboard");
   }

   public boolean isGlowEffect() {
      return this.isEnabled() && this.remove.isEnabled("Glow effect");
   }

   public boolean isBadEffects() {
      return this.isEnabled() && this.remove.isEnabled("Bad effects");
   }

   public boolean isBossBar() {
      return this.isEnabled() && this.remove.isEnabled("Boss bar");
   }

   public boolean isClearLava() {
      return this.isEnabled() && this.remove.isEnabled("Clear lava");
   }

   public boolean isClearWater() {
      return this.isEnabled() && this.remove.isEnabled("Убрать воду");
   }

   public void onEvent() {
   }

   @Generated
   public static K3875e4bdc53cc1074e getInstance() {
      return instance;
   }
}
