package code.miaware.client.features.modules.other;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.utils.other.Kb1ccce79ff543c4410;
import lombok.Generated;

@ModuleRegister(
   name = "CommandHider",
   category = Kf76f042931183af5ba.OTHER,
   description = "Скрывает пароли в командах чата"
)
public final class Kaced9f283cac8188b0 extends Kab8a8c478f7d0077cd {
   private static final Kaced9f283cac8188b0 instance = new Kaced9f283cac8188b0();

   public boolean hides(String input) {
      return this.isEnabled() && Kb1ccce79ff543c4410.isAuthentication(input);
   }

   public void onEvent() {
   }

   @Generated
   public static Kaced9f283cac8188b0 getInstance() {
      return instance;
   }
}
