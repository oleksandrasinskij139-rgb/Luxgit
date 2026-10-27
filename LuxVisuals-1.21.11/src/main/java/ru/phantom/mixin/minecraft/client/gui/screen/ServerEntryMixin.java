package ru.phantom.mixin.minecraft.client.gui.screen;

import II1II1II1II1II1II1II1II1.l8_I7.ilIl____1i__lI11_il11I1Il1i1lIl;
import net.minecraft.class_332;
import net.minecraft.class_4267;
import net.minecraft.class_642;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_4267.class_4270.class})
public abstract class ServerEntryMixin {
   @Shadow
   @Final
   private class_642 field_19120;

   @Inject(
      method = {"method_25343"},
      at = {@At("TAIL")}
   )
   private void phantom$renderOrangeBorder(class_332 context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickProgress, CallbackInfo ci) {
      if (ilIl____1i__lI11_il11I1Il1i1lIl.Illil_i1I1lll1lillilii_1llii(this.field_19120)) {
         int orange = -29696;
         int glow = 587172864;
         context.method_25294(x - 2, y - 1, x + entryWidth + 2, y + entryHeight + 1, glow);
         context.method_25294(x - 2, y - 2, x + entryWidth + 2, y - 1, orange);
         context.method_25294(x - 2, y + entryHeight + 1, x + entryWidth + 2, y + entryHeight + 2, orange);
         context.method_25294(x - 2, y - 1, x - 1, y + entryHeight + 1, orange);
         context.method_25294(x + entryWidth + 1, y - 1, x + entryWidth + 2, y + entryHeight + 1, orange);
      }

   }
}
