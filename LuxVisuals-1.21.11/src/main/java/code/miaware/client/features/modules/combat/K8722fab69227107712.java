package code.miaware.client.features.modules.combat;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import lombok.Generated;
import net.minecraft.class_1297;
import net.minecraft.class_1657;

@ModuleRegister(
   name = "NoFriendDamage",
   category = Kf76f042931183af5ba.COMBAT,
   description = "Не даёт ударить друга из списка .friend"
)
public final class K8722fab69227107712 extends Kab8a8c478f7d0077cd {
   private static final K8722fab69227107712 instance = new K8722fab69227107712();

   public static boolean shouldBlock(class_1297 target) {
      if (instance.isEnabled() && target instanceof class_1657 player) {
         String name = player.method_7334().getName();
         return K137d4e93c7270b0a04.getInstance().getData().stream().anyMatch((friend) -> friend.equalsIgnoreCase(name));
      } else {
         return false;
      }
   }

   public void onEvent() {
   }

   @Generated
   public static K8722fab69227107712 getInstance() {
      return instance;
   }
}
