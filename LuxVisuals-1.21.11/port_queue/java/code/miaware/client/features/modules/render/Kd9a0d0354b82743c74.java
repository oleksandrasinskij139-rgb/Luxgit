package code.miaware.client.features.modules.render;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.client.features.modules.render.hitcolor.Ke2d221182e949c2cf9;
import java.awt.Color;
import lombok.Generated;

@ModuleRegister(
   name = "HitColor",
   category = Kf76f042931183af5ba.RENDER,
   description = "Плавно подсвечивает модель при получении урона"
)
public class Kd9a0d0354b82743c74 extends Kab8a8c478f7d0077cd {
   private static final Kd9a0d0354b82743c74 instance = new Kd9a0d0354b82743c74();
   public final Kbd66519397e9a89967 colorMode = (new Kbd66519397e9a89967("Цвет")).value("Theme").values("Theme", "Custom");
   public final Ka52d224feda41b0761 customColor = (new Ka52d224feda41b0761("Свой цвет")).value(new Color(255, 0, 0)).setVisible(() -> this.colorMode.is("Custom"));
   public final K00fef91b3d4d1b3ecc armor = (new K00fef91b3d4d1b3ecc("Красить броню")).value(false);
   public final Kb9e875ead00e0d03bd intensity = (new Kb9e875ead00e0d03bd("Интенсивность")).value(55.0F).range(10.0F, 85.0F).step(5.0F);

   public Kd9a0d0354b82743c74() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.colorMode, this.customColor, this.armor, this.intensity});
   }

   public Color getHitColor() {
      return this.colorMode.is("Custom") ? (Color)this.customColor.getValue() : Kb18d549c13309af9a5.primary();
   }

   public void onDisable() {
      Ke2d221182e949c2cf9.sync();
   }

   public void onEvent() {
   }

   @Generated
   public static Kd9a0d0354b82743c74 getInstance() {
      return instance;
   }
}
