package code.miaware.client.features.modules.render;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.client.services.Ke4708cf52c3b89f5ae;
import code.miaware.client.ui.clickgui.K78e2c0cb4432df2cca;
import lombok.Generated;
import net.minecraft.class_437;

@ModuleRegister(
   name = "ClickGUI",
   category = Kf76f042931183af5ba.RENDER,
   bind = 344,
   description = "Открывает графический интерфейс настроек клиента"
)
public class K30b126617e809f138b extends Kab8a8c478f7d0077cd {
   private static final K30b126617e809f138b instance = new K30b126617e809f138b();
   public final Kb9e875ead00e0d03bd uiScale = (new Kb9e875ead00e0d03bd("ClickGUI Scale")).value(1.2F).range(0.95F, 2.4F).step(0.05F).onAction(() -> Ke4708cf52c3b89f5ae.getInstance().updateScale());

   public K30b126617e809f138b() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.uiScale});
      super.setEnabled(true, true);
   }

   public static float getUiScale() {
      return getInstance().uiScale.getValue();
   }

   public void onEnable() {
   }

   public void onEvent() {
   }

   public void onDisable() {
      if (mc != null) {
         class_437 var2 = mc.field_1755;
         if (var2 instanceof K78e2c0cb4432df2cca) {
            K78e2c0cb4432df2cca gui = (K78e2c0cb4432df2cca)var2;
            gui.method_25419();
         }
      }

   }

   public void toggle() {
      if (mc != null) {
         if (!this.isEnabled()) {
            super.setEnabled(true, true);
         }

         class_437 var2 = mc.field_1755;
         if (var2 instanceof K78e2c0cb4432df2cca) {
            K78e2c0cb4432df2cca gui = (K78e2c0cb4432df2cca)var2;
            if (gui.isOpen()) {
               gui.method_25419();
               return;
            }
         }

         if (mc.field_1755 == null || mc.field_1755 instanceof K78e2c0cb4432df2cca) {
            mc.method_1507(K78e2c0cb4432df2cca.getInstance());
         }

      }
   }

   public void setEnabled(boolean newState, boolean config) {
      if (!newState) {
         if (mc != null) {
            class_437 var4 = mc.field_1755;
            if (var4 instanceof K78e2c0cb4432df2cca) {
               K78e2c0cb4432df2cca gui = (K78e2c0cb4432df2cca)var4;
               gui.method_25419();
            }
         }

         if (!this.isEnabled()) {
            super.setEnabled(true, true);
         }

      } else {
         if (!this.isEnabled()) {
            super.setEnabled(true, true);
         }

      }
   }

   @Generated
   public static K30b126617e809f138b getInstance() {
      return instance;
   }
}
