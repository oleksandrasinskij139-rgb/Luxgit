package code.miaware.client.features.modules.render;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import lombok.Generated;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_332;
import net.minecraft.class_3486;

@ModuleRegister(
   name = "TotemBar",
   category = Kf76f042931183af5ba.RENDER,
   description = "Тотемы над полоской голода"
)
public final class K64002e36b6d857aee4 extends Kab8a8c478f7d0077cd {
   private static final K64002e36b6d857aee4 instance = new K64002e36b6d857aee4();
   private class_1657 countedPlayer;
   private int countedAge = Integer.MIN_VALUE;
   private int count;
   private class_1799 icon;

   public void onEvent() {
   }

   public void onEnable() {
      this.reset();
   }

   public void onDisable() {
      this.reset();
   }

   private void reset() {
      this.countedPlayer = null;
      this.countedAge = Integer.MIN_VALUE;
      this.count = 0;
   }

   public void renderAboveFood(class_332 context, class_1657 player, int foodY, int right) {
      if (this.isEnabled() && player == mc.field_1724 && !mc.field_1690.field_1842) {
         if (this.countedPlayer != player || this.countedAge != player.field_6012) {
            this.countedPlayer = player;
            this.countedAge = player.field_6012;
            this.count = 0;
            class_1661 inventory = player.method_31548();

            for(int slot = 0; slot < inventory.method_5439(); ++slot) {
               class_1799 stack = inventory.method_5438(slot);
               if (stack.method_31574(class_1802.field_8288)) {
                  this.count += stack.method_7947();
               }
            }
         }

         if (this.count != 0) {
            if (this.icon == null) {
               this.icon = new class_1799(class_1802.field_8288);
            }

            int y = foodY - 11;
            if (player.method_5777(class_3486.field_15517) || player.method_5669() < player.method_5748()) {
               y -= 10;
            }

            int visible = Math.min(this.count, Math.max(0, (y + 2) / 10 + 1) * 10);
            context.method_51448().method_22903();

            try {
               context.method_51448().method_22905(0.5F, 0.5F, 1.0F);

               for(int i = 0; i < visible; ++i) {
                  int x = right - 9 - i % 10 * 8;
                  int rowY = y - i / 10 * 10;
                  context.method_51427(this.icon, x * 2, rowY * 2);
               }
            } finally {
               context.method_51448().method_22909();
            }

         }
      }
   }

   @Generated
   public static K64002e36b6d857aee4 getInstance() {
      return instance;
   }
}
