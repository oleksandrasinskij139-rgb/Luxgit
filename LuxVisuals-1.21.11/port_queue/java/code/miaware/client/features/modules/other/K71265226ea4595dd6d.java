package code.miaware.client.features.modules.other;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Generated;
import net.minecraft.class_1109;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import net.minecraft.class_3532;
import net.minecraft.class_3675;
import net.minecraft.class_3675.class_307;

@ModuleRegister(
   name = "CreamyKeys",
   category = Kf76f042931183af5ba.OTHER,
   description = "Воспроизводит звуки клавиатуры и мыши"
)
public class K71265226ea4595dd6d extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K71265226ea4595dd6d instance = new K71265226ea4595dd6d();
   private final K00fef91b3d4d1b3ecc keyboardSounds = (new K00fef91b3d4d1b3ecc("Звуки клавиатуры")).value(true);
   private final K00fef91b3d4d1b3ecc mouseSounds = (new K00fef91b3d4d1b3ecc("Звуки мыши")).value(true);
   private final K00fef91b3d4d1b3ecc playInMenus = (new K00fef91b3d4d1b3ecc("В меню")).value(true);
   private final Kbd66519397e9a89967 keyboardPack = (new Kbd66519397e9a89967("Пак клавиатуры")).values("Blue ABS", "Blue ABS 2", "Black ABS", "Black PBT", "Brown ABS", "Brown PBT", "Red ABS").value("Brown PBT").setVisible(() -> (Boolean)this.keyboardSounds.getValue());
   private final Kbd66519397e9a89967 mousePack = (new Kbd66519397e9a89967("Пак мыши")).values("Classic", "Sharp", "Deep", "Click", "Film", "Laser", "Ponk", "Corsair LMB", "Corsair MMB").value("Classic").setVisible(() -> (Boolean)this.mouseSounds.getValue());
   private final Kb9e875ead00e0d03bd keyboardVolume = (new Kb9e875ead00e0d03bd("Громкость клавиатуры")).range(0.0F, 100.0F).value(80.0F).step(1.0F).setVisible(() -> (Boolean)this.keyboardSounds.getValue());
   private final Kb9e875ead00e0d03bd mouseVolume = (new Kb9e875ead00e0d03bd("Громкость мыши")).range(0.0F, 100.0F).value(75.0F).step(1.0F).setVisible(() -> (Boolean)this.mouseSounds.getValue());
   private final Kb9e875ead00e0d03bd keyboardAmplify = (new Kb9e875ead00e0d03bd("Усиление клавиатуры")).range(1.0F, 5.0F).value(1.0F).step(1.0F).setVisible(() -> (Boolean)this.keyboardSounds.getValue());
   private final Kb9e875ead00e0d03bd mouseAmplify = (new Kb9e875ead00e0d03bd("Усиление мыши")).range(1.0F, 5.0F).value(1.0F).step(1.0F).setVisible(() -> (Boolean)this.mouseSounds.getValue());

   public K71265226ea4595dd6d() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.keyboardSounds, this.mouseSounds, this.playInMenus, this.keyboardPack, this.mousePack, this.keyboardVolume, this.mouseVolume, this.keyboardAmplify, this.mouseAmplify});
   }

   public void onEvent() {
   }

   public void onEnable() {
      super.onEnable();
      if ((Boolean)this.keyboardSounds.getValue() && ((Boolean)this.playInMenus.getValue() || mc.field_1724 != null && mc.field_1687 != null)) {
         this.playSound(this.K833a9b9c742734cf54(), 0.75F, 1.0F);
      }

   }

   public void handleInput(class_3675.class_307 type, int key, int action) {
      if (this.isEnabled()) {
         if (action == 1) {
            boolean outOfWorld = mc.field_1724 == null || mc.field_1687 == null;
            if (mc.field_1755 == null && !outOfWorld || (Boolean)this.playInMenus.getValue()) {
               if ((Boolean)this.keyboardSounds.getValue() || (Boolean)this.mouseSounds.getValue()) {
                  if (type == class_307.field_1668) {
                     this.K6436587bd26b0fbad4(key);
                  } else {
                     if (type == class_307.field_1672) {
                        this.K6cb52d2006694e64fc(key);
                     }

                  }
               }
            }
         }
      }
   }

   private void K6436587bd26b0fbad4(int key) {
      if ((Boolean)this.keyboardSounds.getValue() && key != -1) {
         this.Kc018fb3ff25295b246(this.K833a9b9c742734cf54(), this.keyboardVolume.getValue() / 100.0F, this.keyboardAmplify.getValue().intValue());
      }
   }

   private void K6cb52d2006694e64fc(int button) {
      if ((Boolean)this.mouseSounds.getValue() && button >= 0 && button <= 7) {
         this.Kc018fb3ff25295b246(this.K42eb88e0f65e19ad46(), this.mouseVolume.getValue() / 100.0F, this.mouseAmplify.getValue().intValue());
      }
   }

   private void Kc018fb3ff25295b246(class_3414 soundEvent, float baseVolume, int amplify) {
      if (!(baseVolume <= 0.0F)) {
         ThreadLocalRandom random = ThreadLocalRandom.current();
         int layers = Math.max(1, amplify);

         for(int index = 0; index < layers; ++index) {
            float layerVolume = baseVolume * Math.max(0.35F, 1.0F - (float)index * 0.16F);
            float pitch = class_3532.method_15363(1.0F + (random.nextFloat() - 0.5F) * 0.08F + (float)index * 0.01F, 0.82F, 1.24F);
            this.playSound(soundEvent, layerVolume, pitch);
         }

      }
   }

   private void playSound(class_3414 sound, float volume, float pitch) {
      if (mc.method_1483() != null) {
         mc.method_1483().method_4873(class_1109.method_4757(sound, pitch, volume));
      }

   }

   private class_3414 K833a9b9c742734cf54() {
      String var10000;
      switch ((String)this.keyboardPack.getValue()) {
         case "Blue ABS" -> var10000 = "keyboard_cherrymx_blue_abs";
         case "Blue ABS 2" -> var10000 = "keyboard_cherrymx_blue_abs_2";
         case "Black ABS" -> var10000 = "keyboard_cherrymx_black_abs";
         case "Black PBT" -> var10000 = "keyboard_cherrymx_black_pbt";
         case "Brown ABS" -> var10000 = "keyboard_cherrymx_brown_abs";
         case "Red ABS" -> var10000 = "keyboard_cherrymx_red_abs";
         default -> var10000 = "keyboard_cherrymx_brown_pbt";
      }

      String packId = var10000;
      return class_3414.method_47908(class_2960.method_60655("miaware", "creamykeys_" + packId));
   }

   private class_3414 K42eb88e0f65e19ad46() {
      String var10000;
      switch ((String)this.mousePack.getValue()) {
         case "Sharp" -> var10000 = "mouse_hit1";
         case "Deep" -> var10000 = "mouse_hit2";
         case "Click" -> var10000 = "mouse_hit3";
         case "Film" -> var10000 = "mouse_hit4";
         case "Laser" -> var10000 = "mouse_hit5";
         case "Ponk" -> var10000 = "mouse_hit6";
         case "Corsair LMB" -> var10000 = "mouse_hit7";
         case "Corsair MMB" -> var10000 = "mouse_hit8";
         default -> var10000 = "mouse_hit";
      }

      String packId = var10000;
      return class_3414.method_47908(class_2960.method_60655("miaware", "creamykeys_" + packId));
   }

   @Generated
   public static K71265226ea4595dd6d getInstance() {
      return instance;
   }
}
