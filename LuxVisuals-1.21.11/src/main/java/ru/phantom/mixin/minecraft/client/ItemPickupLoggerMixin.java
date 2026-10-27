package ru.phantom.mixin.minecraft.client;

import II1II1II1II1II1II1II1II1.l8_I7.II111_iIl_IlI1I_i_IiIili_i_iI;
import II1II1II1II1II1II1II1II1.l8_I7.lii_llIiil1ii__1_l1111iI__iii;
import net.minecraft.class_2653;
import net.minecraft.class_2775;
import net.minecraft.class_634;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_634.class})
public class ItemPickupLoggerMixin {
   @Inject(
      method = {"method_11150"},
      at = {@At("HEAD")}
   )
   private void phantom$pickup(class_2775 packet, CallbackInfo ci) {
      if (II111_iIl_IlI1I_i_IiIili_i_iI.mc.method_18854()) {
         lii_llIiil1ii__1_l1111iI__iii module = lii_llIiil1ii__1_l1111iI__iii.II1i11lI_lI_li1lIIllII_();
         if (module != null) {
            module.onPickup(packet);
         }

      }
   }

   @Inject(
      method = {"method_11109"},
      at = {@At("HEAD")}
   )
   private void phantom$slot(class_2653 packet, CallbackInfo ci) {
      if (II111_iIl_IlI1I_i_IiIili_i_iI.mc.method_18854()) {
         lii_llIiil1ii__1_l1111iI__iii module = lii_llIiil1ii__1_l1111iI__iii.II1i11lI_lI_li1lIIllII_();
         if (module != null) {
            module.onSlotUpdate(packet);
         }

      }
   }
}
