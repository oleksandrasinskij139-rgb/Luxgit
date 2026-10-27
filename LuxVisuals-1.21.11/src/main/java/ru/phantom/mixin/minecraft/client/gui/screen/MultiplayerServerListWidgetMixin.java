package ru.phantom.mixin.minecraft.client.gui.screen;

import II1II1II1II1II1II1II1II1.l8_I7.ilIl____1i__lI11_il11I1Il1i1lIl;
import java.util.List;
import net.minecraft.class_4267;
import net.minecraft.class_641;
import net.minecraft.class_642;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.phantom.mixin.minecraft.client.accessors.ServerListAccessor;

@Mixin({class_4267.class})
public abstract class MultiplayerServerListWidgetMixin {
   @Inject(
      method = {"method_20125"},
      at = {@At("HEAD")}
   )
   private void phantom$pinServer(class_641 serverList, CallbackInfo ci) {
      if (serverList != null) {
         try {
            List<class_642> list = ((ServerListAccessor)serverList).phantom$getServers();
            list.removeIf(ilIl____1i__lI11_il11I1Il1i1lIl::Illil_i1I1lll1lillilii_1llii);
            class_642 pinned = ilIl____1i__lI11_il11I1Il1i1lIl.l_iiI1II_li_I_1il_1l1il_i1l();
            if (pinned != null) {
               list.add(0, pinned);
            }
         } catch (Throwable var5) {
         }
      }

   }
}
