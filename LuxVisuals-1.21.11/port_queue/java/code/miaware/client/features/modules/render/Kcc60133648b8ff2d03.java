package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.module.setting.Ke298bfdac4e34a00d9;
import code.miaware.api.system.backend.K46bff99e1f90a113f9;
import code.miaware.client.util.render.K3dbb0e5dfbba4d83dc;
import com.mojang.blaze3d.platform.GlStateManager;
import java.awt.Color;
import java.util.Objects;
import lombok.Generated;
import net.minecraft.class_4184;
import net.minecraft.class_5636;
import net.minecraft.class_6854;
import net.minecraft.class_9958;

@ModuleRegister(
   name = "Ambience",
   category = Kf76f042931183af5ba.RENDER,
   description = "Изменяет время суток и погоду"
)
public class Kcc60133648b8ff2d03 extends Kab8a8c478f7d0077cd {
   private static final Kcc60133648b8ff2d03 instance = new Kcc60133648b8ff2d03();
   private final Kbd66519397e9a89967 time;
   public final Kbd66519397e9a89967 weather;
   public final K00fef91b3d4d1b3ecc customFog;
   private final Ka52d224feda41b0761 fogColor;
   private final Kb9e875ead00e0d03bd fogStart;
   private final Kb9e875ead00e0d03bd fogEnd;
   public final K00fef91b3d4d1b3ecc skyShader;
   private final Kbd66519397e9a89967 skyShaderMode;
   private final Ka52d224feda41b0761 skyShaderColor;
   private final Kb9e875ead00e0d03bd skyShaderSpeed;
   private final Kb9e875ead00e0d03bd skyShaderScale;
   private final Kb9e875ead00e0d03bd skyShaderIntensity;
   private final Kb9e875ead00e0d03bd skyShaderAlpha;

   public Kcc60133648b8ff2d03() {
      this.time = (new Kbd66519397e9a89967("Time")).value(K31c42aa8803f61159b.DAY).values((Enum[])K31c42aa8803f61159b.values());
      this.weather = (new Kbd66519397e9a89967("Weather")).value(K2b73b8449b9c4b236e.SUNNY).values((Enum[])K2b73b8449b9c4b236e.values());
      this.customFog = (new K00fef91b3d4d1b3ecc("Custom fog")).value(false);
      Ka52d224feda41b0761 var10001 = (new Ka52d224feda41b0761("Fog color")).value(new Color(120, 196, 255));
      K00fef91b3d4d1b3ecc var10002 = this.customFog;
      Objects.requireNonNull(var10002);
      this.fogColor = var10001.setVisible(var10002::getValue);
      Kb9e875ead00e0d03bd var1 = (new Kb9e875ead00e0d03bd("Fog start")).value(10.0F).range(0.0F, 100.0F).step(1.0F);
      var10002 = this.customFog;
      Objects.requireNonNull(var10002);
      this.fogStart = var1.setVisible(var10002::getValue);
      var1 = (new Kb9e875ead00e0d03bd("Fog end")).value(50.0F).range(0.0F, 200.0F).step(1.0F);
      var10002 = this.customFog;
      Objects.requireNonNull(var10002);
      this.fogEnd = var1.setVisible(var10002::getValue);
      this.skyShader = (new K00fef91b3d4d1b3ecc("Sky Shader")).value(false);
      Kbd66519397e9a89967 var3 = (new Kbd66519397e9a89967("Shader Mode")).value("Water").values("Water", "Caustic", "Nebula", "Nebula2");
      var10002 = this.skyShader;
      Objects.requireNonNull(var10002);
      this.skyShaderMode = var3.setVisible(var10002::getValue);
      Ka52d224feda41b0761 var4 = (new Ka52d224feda41b0761("Shader Color")).value(new Color(120, 196, 255));
      var10002 = this.skyShader;
      Objects.requireNonNull(var10002);
      this.skyShaderColor = var4.setVisible(var10002::getValue);
      Kb9e875ead00e0d03bd var5 = (new Kb9e875ead00e0d03bd("Speed")).value(1.0F).range(0.1F, 5.0F).step(0.1F);
      var10002 = this.skyShader;
      Objects.requireNonNull(var10002);
      this.skyShaderSpeed = var5.setVisible(var10002::getValue);
      var5 = (new Kb9e875ead00e0d03bd("Scale")).value(5.0F).range(1.0F, 20.0F).step(0.5F);
      var10002 = this.skyShader;
      Objects.requireNonNull(var10002);
      this.skyShaderScale = var5.setVisible(var10002::getValue);
      var5 = (new Kb9e875ead00e0d03bd("Intensity")).value(0.01F).range(0.001F, 0.05F).step(0.001F);
      var10002 = this.skyShader;
      Objects.requireNonNull(var10002);
      this.skyShaderIntensity = var5.setVisible(var10002::getValue);
      var5 = (new Kb9e875ead00e0d03bd("Alpha")).value(1.0F).range(0.3F, 1.0F).step(0.05F);
      var10002 = this.skyShader;
      Objects.requireNonNull(var10002);
      this.skyShaderAlpha = var5.setVisible(var10002::getValue);
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.time, this.weather, this.customFog, this.fogColor, this.fogStart, this.fogEnd, this.skyShader, this.skyShaderMode, this.skyShaderColor, this.skyShaderSpeed, this.skyShaderScale, this.skyShaderIntensity, this.skyShaderAlpha});
   }

   public boolean isSkyShaderEnabled() {
      return (Boolean)this.skyShader.getValue();
   }

   public String getSkyShaderMode() {
      return (String)this.skyShaderMode.getValue();
   }

   public Color getSkyShaderColor() {
      return (Color)this.skyShaderColor.getValue();
   }

   public float getSkyShaderSpeed() {
      return this.skyShaderSpeed.getValue();
   }

   public float getSkyShaderScale() {
      return this.skyShaderScale.getValue();
   }

   public float getSkyShaderIntensity() {
      return this.skyShaderIntensity.getValue();
   }

   public float getSkyShaderAlpha() {
      return this.skyShaderAlpha.getValue();
   }

   public long getTime(long original) {
      if (mc.field_1687 != null && this.isEnabled()) {
         K31c42aa8803f61159b selected = (K31c42aa8803f61159b)K46bff99e1f90a113f9.getChoiceByName((String)this.time.getValue(), (Ke298bfdac4e34a00d9[])K31c42aa8803f61159b.values());
         long var10000;
         switch (selected.ordinal()) {
            case 0 -> var10000 = original;
            case 1 -> var10000 = 23041L;
            case 2 -> var10000 = 1000L;
            case 3 -> var10000 = 6000L;
            case 4 -> var10000 = 12610L;
            case 5 -> var10000 = 13000L;
            case 6 -> var10000 = 18000L;
            default -> throw new MatchException((String)null, (Throwable)null);
         }

         return var10000;
      } else {
         return original;
      }
   }

   public boolean applyBackgroundColor() {
      if (this.isEnabled() && (Boolean)this.customFog.getValue()) {
         GlStateManager._clearColor((float)((Color)this.fogColor.getValue()).getRed() / 255.0F, (float)((Color)this.fogColor.getValue()).getGreen() / 255.0F, (float)((Color)this.fogColor.getValue()).getBlue() / 255.0F, (float)((Color)this.fogColor.getValue()).getAlpha() / 255.0F);
         return true;
      } else {
         return false;
      }
   }

   public class_9958 applyCustomFog(class_4184 camera, float viewDistance, class_9958 fog) {
      if (this.isEnabled() && (Boolean)this.customFog.getValue()) {
         float start = Math.max(0.0F, this.fogStart.getValue());
         float end = Math.max(start + 1.0F, this.fogEnd.getValue());
         start = Math.min(start, viewDistance);
         end = Math.min(end, viewDistance);
         class_6854 shape = fog.comp_3011();
         class_5636 type = camera.method_19334();
         if (type == class_5636.field_27888) {
            shape = class_6854.field_36350;
         }

         return new class_9958(start, end, shape, (float)((Color)this.fogColor.getValue()).getRed() / 255.0F, (float)((Color)this.fogColor.getValue()).getGreen() / 255.0F, (float)((Color)this.fogColor.getValue()).getBlue() / 255.0F, (float)((Color)this.fogColor.getValue()).getAlpha() / 255.0F);
      } else {
         return fog;
      }
   }

   public void onEvent() {
      K8d7165024ff567f5e7 render3DEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if ((Boolean)this.skyShader.getValue()) {
            K3dbb0e5dfbba4d83dc.render();
         }

      }));
      this.addEvents(new K8d7165024ff567f5e7[]{render3DEvent});
   }

   @Generated
   public static Kcc60133648b8ff2d03 getInstance() {
      return instance;
   }
}
