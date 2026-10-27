package ru.phantom.mixin.minecraft.client.network;

import II1II1II1II1II1II1II1II1.l8_I7.lI1I_Il11iI1ll_ll_i_li1I_II_;
import java.util.UUID;
import net.minecraft.class_2720;
import net.minecraft.class_2856;
import net.minecraft.class_310;
import net.minecraft.class_634;
import net.minecraft.class_642;
import net.minecraft.class_8705;
import net.minecraft.class_2856.class_2857;
import net.minecraft.class_642.class_643;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_2720.class})
public abstract class ResourcePackSendS2CPacketUnlockerMixin {
   @Shadow
   public abstract UUID comp_2158();

   @Inject(
      method = {"method_11774(Lnet/minecraft/class_8705;)V"},
      at = {@At("HEAD")},
      cancellable = true,
      require = 0
   )
   private void phantom$unlockServerPack(class_8705 listener, CallbackInfo ci) {
      if (lI1I_Il11iI1ll_ll_i_li1I_II_.IiIIl_1Iii_l1_i11iil__1I1_liI()) {
         class_310 client = class_310.method_1551();
         class_642 serverInfo = client.method_1558();
         if (serverInfo != null && serverInfo.method_2990() == class_643.field_3767) {
            class_634 clientPlayNetworkHandler = client.method_1562();
            if (clientPlayNetworkHandler != null) {
               clientPlayNetworkHandler.method_52787(new class_2856(this.comp_2158(), class_2857.field_13017));
            }

            ci.cancel();
         }
      }

   }
}
