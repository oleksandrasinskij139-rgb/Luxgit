package code.miaware.client.features.modules.render;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Ka4b85e821e3749c3d9;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.color.K258ef67f9722180a72;
import code.miaware.client.ui.Kd4fcd0c3e89314449a;
import code.miaware.client.ui.widget.K5e6ae649a852530a89;
import lombok.Generated;

@ModuleRegister(
   name = "Interface",
   category = Kf76f042931183af5ba.RENDER,
   description = "Настройки отображения HUD элементов на экране"
)
public class Kfae29c687712462375 extends Kab8a8c478f7d0077cd {
   private static final Kfae29c687712462375 instance = new Kfae29c687712462375();
   public final K00fef91b3d4d1b3ecc gradientAnimation = (new K00fef91b3d4d1b3ecc("Переливание")).value(true);
   public final Ka4b85e821e3749c3d9 widgets = new Ka4b85e821e3749c3d9("Widgets");

   public static float getGlassy() {
      return 1.0F;
   }

   public static int getPasses() {
      return 3;
   }

   public static float getOffset() {
      return 10.0F;
   }

   public static Kd4fcd0c3e89314449a getStyle() {
      return Kd4fcd0c3e89314449a.STYLE_1;
   }

   public void init() {
      this.gradientAnimation.onAction(() -> K258ef67f9722180a72.setEnabled((Boolean)this.gradientAnimation.getValue()));
      this.widgets.value(K5e6ae649a852530a89.getInstance().getWidgets().stream().filter((widget) -> {
         String name = widget.getName();
         return !name.equals("Logo") && !name.equals("ServerInfo");
      }).map((widget) -> {
         K00fef91b3d4d1b3ecc setting = (new K00fef91b3d4d1b3ecc(widget.getName())).value(widget.isEnabled());
         setting.onAction(() -> widget.setEnabled((Boolean)setting.getValue()));
         return setting;
      }).toList());
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.gradientAnimation, this.widgets});
   }

   public void onEvent() {
   }

   @Generated
   public static Kfae29c687712462375 getInstance() {
      return instance;
   }
}
