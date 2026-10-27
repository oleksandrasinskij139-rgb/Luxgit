package ru.phantom.mixin.minecraft.client;

import II1II1II1II1II1II1II1II1.I0_i9.I11i1iIiI_11iI_i1___1liIIii;
import II1II1II1II1II1II1II1II1.l2_I1.I_l_IiI__ilii1_I1__Ii__1_ii;
import II1II1II1II1II1II1II1II1.l8_I7.II111_iIl_IlI1I_i_IiIili_i_iI;
import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.class_1802;
import net.minecraft.class_2960;
import net.minecraft.class_640;
import net.minecraft.class_8685;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.phantom.Phantom;

@Mixin({class_640.class})
public class PlayerListEntryMixin {
   @Shadow
   @Final
   private GameProfile field_3741;

   private I11i1iIiI_11iI_i1___1liIIii getActiveCape() {
      if (Phantom.getInstance() == null) {
         return null;
      } else {
         List<I11i1iIiI_11iI_i1___1liIIii> capes = Phantom.getInstance().getCapesManager().getCapes();
         return capes == null ? null : (I11i1iIiI_11iI_i1___1liIIii)capes.stream().filter(I11i1iIiI_11iI_i1___1liIIii::isEnable).filter((c) -> c.getCosmeticType() == I_l_IiI__ilii1_I1__Ii__1_ii.CAPES).findFirst().orElse((Object)null);
      }
   }

   private class_2960 getCapeTexture(I11i1iIiI_11iI_i1___1liIIii cape) {
      return cape.getCapeTexture();
   }

   @Inject(
      method = {"method_52810"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void injectCape(CallbackInfoReturnable<class_8685> cir) {
      if (II111_iIl_IlI1I_i_IiIili_i_iI.mc.field_1724 != null && II111_iIl_IlI1I_i_IiIili_i_iI.mc.field_1687 != null) {
         if (this.field_3741.getId().equals(II111_iIl_IlI1I_i_IiIili_i_iI.mc.field_1724.method_7334().getId())) {
            if (II111_iIl_IlI1I_i_IiIili_i_iI.mc.field_1724.method_31548().method_7372(2).method_7909() != class_1802.field_8833) {
               I11i1iIiI_11iI_i1___1liIIii cape = this.getActiveCape();
               if (cape != null) {
                  class_8685 old = (class_8685)cir.getReturnValue();
                  if (old != null) {
                     class_2960 capeTex = this.getCapeTexture(cape);
                     cir.setReturnValue(new class_8685(old.comp_1626(), old.comp_1911(), capeTex, capeTex, old.comp_1629(), old.comp_1630()));
                  }
               }
            }
         }
      }
   }
}
