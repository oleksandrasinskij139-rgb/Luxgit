package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import java.awt.Color;
import lombok.Generated;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1657;
import net.minecraft.class_310;

@ModuleRegister(
   name = "Custom Player",
   category = Kf76f042931183af5ba.RENDER,
   description = "Применяет шейдер к другим игрокам"
)
@Environment(EnvType.CLIENT)
public class K1b30271ad5c4a4666f extends Kab8a8c478f7d0077cd {
   private static final K1b30271ad5c4a4666f instance = new K1b30271ad5c4a4666f();
   private class_1657 targetPlayer = null;
   private class_1657 lastTargetPlayer = null;
   private final Ka91715a8c4e3f3b7c4 shaderAlphaAnim = new Ka91715a8c4e3f3b7c4();
   private static final long FADE_DURATION_MS = 500L;
   private final Kbd66519397e9a89967 shaderMode = (new Kbd66519397e9a89967("Режим шейдера")).value("None").values("None", "Glass", "Mirage");
   private final Kbd66519397e9a89967 colorMode = (new Kbd66519397e9a89967("Режим цвета")).value("Тема").values("Тема", "Свой");
   private final Ka52d224feda41b0761 customColor = (new Ka52d224feda41b0761("Свой цвет")).value(new Color(255, 50, 50));
   private final Kb9e875ead00e0d03bd glassBlur = (new Kb9e875ead00e0d03bd("Размытие Glass")).value(2.0F).range(0.0F, 10.0F).step(0.1F);
   private final Kb9e875ead00e0d03bd glassDistortion = (new Kb9e875ead00e0d03bd("Искажение Glass")).value(1.0F).range(0.0F, 5.0F).step(0.1F);
   private final Kb9e875ead00e0d03bd glassEdge = (new Kb9e875ead00e0d03bd("Края Glass")).value(1.0F).range(0.0F, 3.0F).step(0.1F);
   private final Kb9e875ead00e0d03bd glassRadius = (new Kb9e875ead00e0d03bd("Радиус Glass")).value(12.0F).range(1.0F, 30.0F).step(1.0F);
   private final Kb9e875ead00e0d03bd glassWaveSpeed = (new Kb9e875ead00e0d03bd("Скорость волн Glass")).value(1.0F).range(0.0F, 5.0F).step(0.1F);
   private final Kb9e875ead00e0d03bd glassWaveAmp = (new Kb9e875ead00e0d03bd("Амплитуда волн Glass")).value(1.0F).range(0.0F, 5.0F).step(0.1F);
   private final Kb9e875ead00e0d03bd glassAlpha = (new Kb9e875ead00e0d03bd("Прозрачность Glass")).value(0.6F).range(0.0F, 1.0F).step(0.05F);
   private final Kbd66519397e9a89967 glassQuality = (new Kbd66519397e9a89967("Качество Glass")).value("Medium").values("Low", "Medium", "High");
   private final Kb9e875ead00e0d03bd mirageBlur = (new Kb9e875ead00e0d03bd("Размытие Mirage")).value(4.0F).range(0.0F, 10.0F).step(0.1F);
   private final Kb9e875ead00e0d03bd mirageDistortion = (new Kb9e875ead00e0d03bd("Искажение Mirage")).value(2.0F).range(0.0F, 5.0F).step(0.1F);
   private final Kb9e875ead00e0d03bd mirageEdge = (new Kb9e875ead00e0d03bd("Края Mirage")).value(1.5F).range(0.0F, 3.0F).step(0.1F);
   private final Kb9e875ead00e0d03bd mirageWaveSpeed = (new Kb9e875ead00e0d03bd("Скорость волн Mirage")).value(1.5F).range(0.0F, 5.0F).step(0.1F);
   private final Kb9e875ead00e0d03bd mirageWaveAmp = (new Kb9e875ead00e0d03bd("Амплитуда волн Mirage")).value(0.5F).range(0.0F, 5.0F).step(0.1F);
   private final Kb9e875ead00e0d03bd mirageAlpha = (new Kb9e875ead00e0d03bd("Прозрачность Mirage")).value(1.0F).range(0.0F, 1.0F).step(0.05F);
   private final Kbd66519397e9a89967 mirageQuality = (new Kbd66519397e9a89967("Качество Mirage")).value("Medium").values("Low", "Medium", "High");

   public K1b30271ad5c4a4666f() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.shaderMode, this.colorMode, this.customColor, this.glassBlur, this.glassDistortion, this.glassEdge, this.glassRadius, this.glassWaveSpeed, this.glassWaveAmp, this.glassAlpha, this.glassQuality, this.mirageBlur, this.mirageDistortion, this.mirageEdge, this.mirageWaveSpeed, this.mirageWaveAmp, this.mirageAlpha, this.mirageQuality});
   }

   public String getShaderMode() {
      return (String)this.shaderMode.getValue();
   }

   public float[] getEffectColorArray() {
      if (((String)this.colorMode.getValue()).equals("Свой")) {
         Color color = (Color)this.customColor.getValue();
         float r = (float)color.getRed() / 255.0F;
         float g = (float)color.getGreen() / 255.0F;
         float b = (float)color.getBlue() / 255.0F;
         float a = (float)color.getAlpha() / 255.0F;
         return new float[]{r, g, b, a};
      } else {
         Color themeColor = Kb18d549c13309af9a5.primary();
         if (themeColor == null) {
            return new float[]{1.0F, 0.2F, 0.2F, 0.6F};
         } else {
            float r = (float)themeColor.getRed() / 255.0F;
            float g = (float)themeColor.getGreen() / 255.0F;
            float b = (float)themeColor.getBlue() / 255.0F;
            float a = 0.6F;
            return new float[]{r, g, b, a};
         }
      }
   }

   public float getGlassBlur() {
      return this.glassBlur.getValue();
   }

   public float getGlassDistortion() {
      return this.glassDistortion.getValue();
   }

   public float getGlassEdge() {
      return this.glassEdge.getValue();
   }

   public float getGlassRadius() {
      return this.glassRadius.getValue();
   }

   public float getGlassWaveSpeed() {
      return this.glassWaveSpeed.getValue();
   }

   public float getGlassWaveAmp() {
      return this.glassWaveAmp.getValue();
   }

   public float getGlassAlpha() {
      return this.glassAlpha.getValue();
   }

   public int getGlassQuality() {
      String q = (String)this.glassQuality.getValue();
      if (q.equals("Low")) {
         return 0;
      } else {
         return q.equals("Medium") ? 1 : 2;
      }
   }

   public float getMirageBlur() {
      return this.mirageBlur.getValue();
   }

   public float getMirageDistortion() {
      return this.mirageDistortion.getValue();
   }

   public float getMirageEdge() {
      return this.mirageEdge.getValue();
   }

   public float getMirageWaveSpeed() {
      return this.mirageWaveSpeed.getValue();
   }

   public float getMirageWaveAmp() {
      return this.mirageWaveAmp.getValue();
   }

   public float getMirageAlpha() {
      return this.mirageAlpha.getValue();
   }

   public int getMirageQuality() {
      String q = (String)this.mirageQuality.getValue();
      if (q.equals("Low")) {
         return 0;
      } else {
         return q.equals("Medium") ? 1 : 2;
      }
   }

   public void onEvent() {
      K8d7165024ff567f5e7 updateEvent = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         this.K6ded16b26a36ac0431();
         this.shaderAlphaAnim.update();
         boolean hasTarget = this.targetPlayer != null;
         this.shaderAlphaAnim.run(hasTarget ? (double)1.0F : (double)0.0F, 500L, Kf3b62ecfba0c3544e6.CUBIC_OUT);
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{updateEvent});
   }

   public void onEnable() {
      this.shaderAlphaAnim.setValue((double)0.0F);
   }

   public void onDisable() {
      this.targetPlayer = null;
      this.lastTargetPlayer = null;
      this.shaderAlphaAnim.setValue((double)0.0F);
   }

   public class_1657 getShaderTarget() {
      return this.shaderAlphaAnim.getValue() > 0.01 ? this.lastTargetPlayer : null;
   }

   public float getShaderAlpha() {
      return (float)this.shaderAlphaAnim.getValue();
   }

   private void K6ded16b26a36ac0431() {
      class_310 mc = class_310.method_1551();
      if (mc.field_1724 != null && mc.field_1687 != null) {
         class_1657 newTarget = null;
         if (mc.field_1692 != null && mc.field_1692 instanceof class_1657) {
            newTarget = (class_1657)mc.field_1692;
         }

         this.targetPlayer = newTarget;
         if (this.targetPlayer != null) {
            this.lastTargetPlayer = this.targetPlayer;
         }

      } else {
         this.targetPlayer = null;
      }
   }

   @Generated
   public static K1b30271ad5c4a4666f getInstance() {
      return instance;
   }

   @Generated
   public Ka91715a8c4e3f3b7c4 getShaderAlphaAnim() {
      return this.shaderAlphaAnim;
   }
}
