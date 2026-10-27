package code.miaware.client.ui.swap;

import code.miaware.client.features.modules.combat.tripleswap.K5bb032ff100ed9755b;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_332;
import net.minecraft.class_490;

public final class K960d3835845d6d5dfa extends class_490 {
   private final K5bb032ff100ed9755b controller;

   public K960d3835845d6d5dfa(K5bb032ff100ed9755b controller, class_1657 player) {
      super(player);
      this.controller = controller;
   }

   protected void method_2383(class_1735 slot, int slotId, int button, class_1713 actionType) {
      if (slot != null && slot.method_7681()) {
         this.controller.pick(slot.method_7677());
      }

   }

   public boolean method_25404(int key, int scan, int modifiers) {
      if (key != 256 && !this.field_22787.field_1690.field_1822.method_1417(key, scan)) {
         return super.method_25404(key, scan, modifiers);
      } else {
         this.controller.cancelPick();
         return true;
      }
   }

   public void method_25394(class_332 context, int x, int y, float delta) {
      super.method_25394(context, x, y, delta);
      context.method_25300(this.field_22793, "Нажми на предмет для кольца • Esc — назад", this.field_22789 / 2, this.field_22790 / 2 - 110, -1);
   }
}
