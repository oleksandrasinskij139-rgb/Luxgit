package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.other.K7d074a66e20f0c1531;
import code.miaware.client.ui.clickgui.K78e2c0cb4432df2cca;
import code.miaware.client.ui.clickgui.ScreenIRCGui;
import lombok.Generated;
import net.minecraft.class_437;

@ModuleRegister(
   name = "GUI Controller",
   category = Kf76f042931183af5ba.RENDER,
   description = "Настройка звуков открытия GUI, наведения, кликов и печати описаний"
)
public class Ke60d35272f9e1d2a55 extends Kab8a8c478f7d0077cd {
   private static final Ke60d35272f9e1d2a55 instance = new Ke60d35272f9e1d2a55();
   public final K00fef91b3d4d1b3ecc openingSound = (new K00fef91b3d4d1b3ecc("GUI opening sound")).value(true);
   public final K00fef91b3d4d1b3ecc hoverSound = (new K00fef91b3d4d1b3ecc("GUI hover sound")).value(true);
   public final K00fef91b3d4d1b3ecc clickSound = (new K00fef91b3d4d1b3ecc("GUI click sound")).value(true);
   public final K00fef91b3d4d1b3ecc descriptionSound = (new K00fef91b3d4d1b3ecc("Description typing sound")).value(true);
   public final Kb9e875ead00e0d03bd volume = (new Kb9e875ead00e0d03bd("Volume")).value(100.0F).range(0.0F, 100.0F).step(1.0F);
   private class_437 lastScreen = null;

   public Ke60d35272f9e1d2a55() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.openingSound, this.hoverSound, this.clickSound, this.descriptionSound, this.volume});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 tickEvent = Kdee350abc91aa294b2.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         class_437 currentScreen = mc.field_1755;
         if (currentScreen != null && currentScreen != this.lastScreen && !(currentScreen instanceof K78e2c0cb4432df2cca) && !(currentScreen instanceof ScreenIRCGui)) {
            this.K282186d42a2f711ab9();
         }

         this.lastScreen = currentScreen;
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{tickEvent});
   }

   public void onDisable() {
      super.onDisable();
      this.lastScreen = null;
   }

   public void playHoverSound() {
      K7d074a66e20f0c1531.playGuiHoverSound(K7d074a66e20f0c1531.SELECT_EVENT);
   }

   private void K282186d42a2f711ab9() {
      K7d074a66e20f0c1531.playGuiSound(K7d074a66e20f0c1531.GUI_OPEN_EVENT);
   }

   public float getSoundVolume() {
      return Math.max(0.0F, Math.min(1.0F, this.volume.getValue() / 100.0F));
   }

   public boolean isDescriptionSoundEnabled() {
      return this.isEnabled() && (Boolean)this.descriptionSound.getValue() && this.getSoundVolume() > 0.0F;
   }

   @Generated
   public static Ke60d35272f9e1d2a55 getInstance() {
      return instance;
   }
}
