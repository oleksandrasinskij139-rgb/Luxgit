package code.miaware.client.features.modules.player.invsave;

import java.util.List;
import net.minecraft.class_1799;

public record K9e64dd2dd1a3d84dbb(String name, List<class_1799> slots) {
   public int occupiedSlots() {
      return (int)this.slots.stream().filter((stack) -> !stack.method_7960()).count();
   }
}
