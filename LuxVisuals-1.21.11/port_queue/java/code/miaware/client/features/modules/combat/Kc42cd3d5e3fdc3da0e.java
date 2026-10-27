package code.miaware.client.features.modules.combat;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import java.awt.Color;
import lombok.Generated;

@ModuleRegister(
   name = "HitboxColor",
   category = Kf76f042931183af5ba.COMBAT,
   description = "Аккуратная рамка хитбокса с углами и заливкой"
)
public class Kc42cd3d5e3fdc3da0e extends Kab8a8c478f7d0077cd {
   private static final Kc42cd3d5e3fdc3da0e instance = new Kc42cd3d5e3fdc3da0e();
   private final Kbd66519397e9a89967 colorMode = (new Kbd66519397e9a89967("Режим цвета")).value("Клиентская").values("Своя", "Клиентская");
   private final Ka52d224feda41b0761 color = (new Ka52d224feda41b0761("Цвет")).value(new Color(255, 101, 57, 255)).setVisible(() -> ((String)this.colorMode.getValue()).equals("Своя"));
   private final K00fef91b3d4d1b3ecc corners = (new K00fef91b3d4d1b3ecc("Углы")).value(false);
   private final K00fef91b3d4d1b3ecc fill = (new K00fef91b3d4d1b3ecc("Заливка")).value(false);
   private final Kb9e875ead00e0d03bd fillAlpha = (new Kb9e875ead00e0d03bd("Прозрачность заливки")).range(0.0F, 50.0F).step(1.0F).value(12.0F).setVisible(() -> (Boolean)this.fill.getValue());

   public Kc42cd3d5e3fdc3da0e() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.colorMode, this.color, this.corners, this.fill, this.fillAlpha});
   }

   public Color renderColor() {
      return this.colorMode.is("Клиентская") ? Kb18d549c13309af9a5.primary() : (Color)this.color.getValue();
   }

   public void onEvent() {
   }

   @Generated
   public static Kc42cd3d5e3fdc3da0e getInstance() {
      return instance;
   }

   @Generated
   public Kbd66519397e9a89967 getColorMode() {
      return this.colorMode;
   }

   @Generated
   public Ka52d224feda41b0761 getColor() {
      return this.color;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getCorners() {
      return this.corners;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getFill() {
      return this.fill;
   }

   @Generated
   public Kb9e875ead00e0d03bd getFillAlpha() {
      return this.fillAlpha;
   }
}
