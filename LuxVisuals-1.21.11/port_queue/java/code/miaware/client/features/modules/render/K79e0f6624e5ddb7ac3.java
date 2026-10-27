package code.miaware.client.features.modules.render;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import java.awt.Color;
import lombok.Generated;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_5498;
import org.joml.Matrix4f;

@ModuleRegister(
   name = "Beautiful Hands",
   category = Kf76f042931183af5ba.RENDER,
   description = "Изменяет отображение руки от первого лица"
)
@Environment(EnvType.CLIENT)
public class K79e0f6624e5ddb7ac3 extends Kab8a8c478f7d0077cd {
   private static final K79e0f6624e5ddb7ac3 instance = new K79e0f6624e5ddb7ac3();
   private final Kbd66519397e9a89967 shaderMode = (new Kbd66519397e9a89967("Режим шейдера")).value("Blur").values("Blur", "Glass", "Mirage", "Fill", "Glow", "Mirror", "Outline", "Fire");
   private final Kbd66519397e9a89967 colorMode = (new Kbd66519397e9a89967("Режим цвета")).value("Тема").values("Тема", "Свой").setVisible(() -> !this.shaderMode.is("Mirror"));
   private final Ka52d224feda41b0761 customColor = (new Ka52d224feda41b0761("Свой цвет")).value(new Color(255, 50, 50)).setVisible(() -> this.colorMode.is("Свой") && !this.shaderMode.is("Mirror"));
   private final Kb9e875ead00e0d03bd blurRadius = (new Kb9e875ead00e0d03bd("Радиус размытия")).value(12.0F).range(0.0F, 50.0F).step(1.0F).setVisible(() -> this.shaderMode.is("Blur"));
   private final Kb9e875ead00e0d03bd threshold = (new Kb9e875ead00e0d03bd("Порог")).value(0.01F).range(0.001F, 0.1F).step(0.001F).setVisible(() -> this.shaderMode.is("Blur"));
   private final Kb9e875ead00e0d03bd tintOpacity = (new Kb9e875ead00e0d03bd("Прозрачность")).value(0.6F).range(0.0F, 1.0F).step(0.05F).setVisible(() -> this.shaderMode.is("Blur"));
   private final Kb9e875ead00e0d03bd glassBlur = (new Kb9e875ead00e0d03bd("Размытие стекла")).value(2.0F).range(0.0F, 8.0F).step(0.5F).setVisible(() -> this.shaderMode.is("Glass"));
   private final Kb9e875ead00e0d03bd glassDistortion = (new Kb9e875ead00e0d03bd("Искажение стекла")).value(1.0F).range(0.0F, 5.0F).step(0.1F).setVisible(() -> this.shaderMode.is("Glass"));
   private final Kb9e875ead00e0d03bd glassEdge = (new Kb9e875ead00e0d03bd("Края стекла")).value(1.0F).range(0.0F, 2.0F).step(0.1F).setVisible(() -> this.shaderMode.is("Glass"));
   private final Kb9e875ead00e0d03bd glassRadius = (new Kb9e875ead00e0d03bd("Радиус стекла")).value(12.0F).range(0.0F, 30.0F).step(0.5F).setVisible(() -> this.shaderMode.is("Glass"));
   private final Kb9e875ead00e0d03bd glassWaveSpeed = (new Kb9e875ead00e0d03bd("Скорость волн стекла")).value(1.0F).range(0.1F, 3.0F).step(0.1F).setVisible(() -> this.shaderMode.is("Glass"));
   private final Kb9e875ead00e0d03bd glassWaveAmp = (new Kb9e875ead00e0d03bd("Амплитуда волн стекла")).value(1.0F).range(0.0F, 2.0F).step(0.1F).setVisible(() -> this.shaderMode.is("Glass"));
   private final Kb9e875ead00e0d03bd glassAlpha = (new Kb9e875ead00e0d03bd("Прозрачность стекла")).value(0.6F).range(0.0F, 1.0F).step(0.05F).setVisible(() -> this.shaderMode.is("Glass"));
   private final Kbd66519397e9a89967 glassQuality = (new Kbd66519397e9a89967("Качество стекла")).value("Высокое").values("Низкое", "Среднее", "Высокое").setVisible(() -> this.shaderMode.is("Glass"));
   private final Kb9e875ead00e0d03bd mirageBlur = (new Kb9e875ead00e0d03bd("Размытие миража")).value(4.0F).range(0.0F, 20.0F).step(0.5F).setVisible(() -> this.shaderMode.is("Mirage"));
   private final Kb9e875ead00e0d03bd mirageDistortion = (new Kb9e875ead00e0d03bd("Искажение миража")).value(2.0F).range(0.0F, 5.0F).step(0.1F).setVisible(() -> this.shaderMode.is("Mirage"));
   private final Kb9e875ead00e0d03bd mirageEdge = (new Kb9e875ead00e0d03bd("Края миража")).value(1.5F).range(0.0F, 3.0F).step(0.1F).setVisible(() -> this.shaderMode.is("Mirage"));
   private final Kb9e875ead00e0d03bd mirageWaveSpeed = (new Kb9e875ead00e0d03bd("Скорость волн миража")).value(1.5F).range(0.1F, 5.0F).step(0.1F).setVisible(() -> this.shaderMode.is("Mirage"));
   private final Kb9e875ead00e0d03bd mirageWaveAmp = (new Kb9e875ead00e0d03bd("Амплитуда волн миража")).value(0.5F).range(0.0F, 2.0F).step(0.1F).setVisible(() -> this.shaderMode.is("Mirage"));
   private final Kb9e875ead00e0d03bd mirageAlpha = (new Kb9e875ead00e0d03bd("Прозрачность миража")).value(1.0F).range(0.0F, 1.0F).step(0.05F).setVisible(() -> this.shaderMode.is("Mirage"));
   private final Kbd66519397e9a89967 mirageQuality = (new Kbd66519397e9a89967("Качество миража")).value("Среднее").values("Низкое", "Среднее", "Высокое").setVisible(() -> this.shaderMode.is("Mirage"));
   private final Kb9e875ead00e0d03bd fillAlpha = (new Kb9e875ead00e0d03bd("Прозрачность")).value(0.8F).range(0.0F, 1.0F).step(0.05F).setVisible(() -> this.shaderMode.is("Fill"));
   private final Kb9e875ead00e0d03bd shadingStrength = (new Kb9e875ead00e0d03bd("Затенение")).value(0.3F).range(0.0F, 1.0F).step(0.1F).setVisible(() -> this.shaderMode.is("Fill"));
   private final Kb9e875ead00e0d03bd glowExposure = (new Kb9e875ead00e0d03bd("Яркость")).value(2.0F).range(0.5F, 5.0F).step(0.1F).setVisible(() -> this.shaderMode.is("Glow"));
   private final Ka52d224feda41b0761 glowColor2 = (new Ka52d224feda41b0761("Второй цвет")).value(new Color(255, 107, 158)).setVisible(() -> this.shaderMode.is("Glow"));
   private final Kb9e875ead00e0d03bd mirrorMixFactor = (new Kb9e875ead00e0d03bd("Смешивание")).value(0.5F).range(0.0F, 1.0F).step(0.05F).setVisible(() -> this.shaderMode.is("Mirror"));
   private final Kb9e875ead00e0d03bd mirrorAlpha = (new Kb9e875ead00e0d03bd("Прозрачность")).value(1.0F).range(0.0F, 1.0F).step(0.05F).setVisible(() -> this.shaderMode.is("Mirror"));
   private final Kb9e875ead00e0d03bd outlineWidth = (new Kb9e875ead00e0d03bd("Ширина")).value(1.0F).range(0.5F, 5.0F).step(0.1F).setVisible(() -> this.shaderMode.is("Outline"));
   private final Kb9e875ead00e0d03bd outlineAlpha = (new Kb9e875ead00e0d03bd("Прозрачность")).value(1.0F).range(0.0F, 1.0F).step(0.05F).setVisible(() -> this.shaderMode.is("Outline"));
   private final Kb9e875ead00e0d03bd fireHeight = (new Kb9e875ead00e0d03bd("Высота")).value(0.3F).range(0.05F, 1.0F).step(0.05F).setVisible(() -> this.shaderMode.is("Fire"));
   private final Kb9e875ead00e0d03bd fireSpeed = (new Kb9e875ead00e0d03bd("Скорость")).value(1.5F).range(0.5F, 3.0F).step(0.1F).setVisible(() -> this.shaderMode.is("Fire"));
   private final Kb9e875ead00e0d03bd fireIntensity = (new Kb9e875ead00e0d03bd("Интенсивность")).value(1.0F).range(0.3F, 2.0F).step(0.1F).setVisible(() -> this.shaderMode.is("Fire"));
   private final Kb9e875ead00e0d03bd fireWind = (new Kb9e875ead00e0d03bd("Ветер")).value(1.0F).range(0.0F, 3.0F).step(0.1F).setVisible(() -> this.shaderMode.is("Fire"));
   private final Kb9e875ead00e0d03bd fireWave = (new Kb9e875ead00e0d03bd("Волны")).value(1.0F).range(0.0F, 3.0F).step(0.1F).setVisible(() -> this.shaderMode.is("Fire"));
   private final Ka52d224feda41b0761 fireColor = (new Ka52d224feda41b0761("Цвет (низ)")).value(new Color(255, 191, 51)).setVisible(() -> this.shaderMode.is("Fire"));
   private final Ka52d224feda41b0761 fireColor2 = (new Ka52d224feda41b0761("Цвет (верх)")).value(new Color(255, 64, 13)).setVisible(() -> this.shaderMode.is("Fire"));
   private class_276 savedBefore = null;
   private class_276 savedAfter = null;

   public K79e0f6624e5ddb7ac3() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.shaderMode, this.colorMode, this.customColor, this.blurRadius, this.threshold, this.tintOpacity, this.glassBlur, this.glassDistortion, this.glassEdge, this.glassRadius, this.glassWaveSpeed, this.glassWaveAmp, this.glassAlpha, this.glassQuality, this.mirageBlur, this.mirageDistortion, this.mirageEdge, this.mirageWaveSpeed, this.mirageWaveAmp, this.mirageAlpha, this.mirageQuality, this.fillAlpha, this.shadingStrength, this.glowExposure, this.glowColor2, this.mirrorMixFactor, this.mirrorAlpha, this.outlineWidth, this.outlineAlpha, this.fireHeight, this.fireSpeed, this.fireIntensity, this.fireWind, this.fireWave, this.fireColor, this.fireColor2});
   }

   public void onShader(class_276 beforeHandFbo, class_276 withHandFbo) {
      this.savedBefore = beforeHandFbo;
      this.savedAfter = withHandFbo;
   }

   public void renderHandShader(int screenWidth, int screenHeight, Matrix4f matrix) {
      if (this.isEnabled()) {
         class_310 mc = class_310.method_1551();
         if (mc.field_1690.method_31044() == class_5498.field_26664) {
            if (this.savedBefore != null && this.savedAfter != null) {
               if (this.savedBefore.method_30277() != 0 && this.savedAfter.method_30277() != 0) {
                  if (screenWidth <= 0 || screenHeight <= 0) {
                     ;
                  }
               }
            }
         }
      }
   }

   public Color getEffectColor() {
      return this.colorMode.is("Свой") ? (Color)this.customColor.getValue() : Kb18d549c13309af9a5.primary();
   }

   public float[] getEffectColorArray() {
      Color color = this.getEffectColor();
      return new float[]{(float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, this.tintOpacity.getValue()};
   }

   public float getBlurRadius() {
      return this.blurRadius.getValue();
   }

   public float getThreshold() {
      return this.threshold.getValue();
   }

   public String getShaderMode() {
      return (String)this.shaderMode.getValue();
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
      return q.equals("Низкое") ? 0 : (q.equals("Среднее") ? 1 : 2);
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
      return q.equals("Низкое") ? 0 : (q.equals("Среднее") ? 1 : 2);
   }

   public float getFillAlpha() {
      return this.fillAlpha.getValue();
   }

   public float getShadingStrength() {
      return this.shadingStrength.getValue();
   }

   public float getGlowExposure() {
      return this.glowExposure.getValue();
   }

   public Color getGlowColor2() {
      return (Color)this.glowColor2.getValue();
   }

   public float[] getGlowColor2Array() {
      Color c = (Color)this.glowColor2.getValue();
      return new float[]{(float)c.getRed() / 255.0F, (float)c.getGreen() / 255.0F, (float)c.getBlue() / 255.0F};
   }

   public float getMirrorMixFactor() {
      return this.mirrorMixFactor.getValue();
   }

   public float getMirrorAlpha() {
      return this.mirrorAlpha.getValue();
   }

   public float getOutlineWidth() {
      return this.outlineWidth.getValue();
   }

   public float getOutlineAlpha() {
      return this.outlineAlpha.getValue();
   }

   public float getFireHeight() {
      return this.fireHeight.getValue();
   }

   public float getFireSpeed() {
      return this.fireSpeed.getValue();
   }

   public float getFireIntensity() {
      return this.fireIntensity.getValue();
   }

   public float getFireWind() {
      return this.fireWind.getValue();
   }

   public float getFireWave() {
      return this.fireWave.getValue();
   }

   public Color getFireColor() {
      return (Color)this.fireColor.getValue();
   }

   public Color getFireColor2() {
      return (Color)this.fireColor2.getValue();
   }

   public float[] getFireColorArray() {
      Color c = (Color)this.fireColor.getValue();
      return new float[]{(float)c.getRed() / 255.0F, (float)c.getGreen() / 255.0F, (float)c.getBlue() / 255.0F};
   }

   public float[] getFireColor2Array() {
      Color c = (Color)this.fireColor2.getValue();
      return new float[]{(float)c.getRed() / 255.0F, (float)c.getGreen() / 255.0F, (float)c.getBlue() / 255.0F};
   }

   public void onEvent() {
   }

   public void onEnable() {
   }

   public void onDisable() {
      this.savedBefore = null;
      this.savedAfter = null;
   }

   @Generated
   public static K79e0f6624e5ddb7ac3 getInstance() {
      return instance;
   }
}
