package code.miaware.client.features.modules.render;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.client.features.modules.render.armor.Kc35e3ae6ac3cc22bf6;
import lombok.Generated;
import net.minecraft.class_1799;

@ModuleRegister(
   name = "ArmorDurability",
   category = Kf76f042931183af5ba.RENDER,
   description = "Окрашивает броню игроков по оставшейся прочности"
)
public final class K8eca1d6eead3aa6b07 extends Kab8a8c478f7d0077cd {
   private static final K8eca1d6eead3aa6b07 instance = new K8eca1d6eead3aa6b07();

   public int color(class_1799 stack) {
      return Kc35e3ae6ac3cc22bf6.color(stack.method_7919(), stack.method_7936());
   }

   public void onEvent() {
   }

   @Generated
   public static K8eca1d6eead3aa6b07 getInstance() {
      return instance;
   }
}
