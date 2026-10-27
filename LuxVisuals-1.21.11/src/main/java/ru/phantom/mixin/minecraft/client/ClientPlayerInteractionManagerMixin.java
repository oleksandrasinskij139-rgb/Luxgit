package ru.phantom.mixin.minecraft.client;

import II1II1II1II1II1II1II1II1.I2_i1.I1ililiI__lilIlllIlllliI1Ii;
import II1II1II1II1II1II1II1II1.l8_I7.IlII1li1IIlil_1_iI__liii;
import II1II1II1II1II1II1II1II1.l8_I7.i_IIi11l1_i_1I1lIilIi1iI__I_I;
import II1II1II1II1II1II1II1II1.l8_I7.i_l11IliliIIl1iIIliI_1iI;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_636;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.phantom.Phantom;

@Mixin({class_636.class})
public class ClientPlayerInteractionManagerMixin {
   @Inject(
      method = {"method_2918"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onAttack(class_1657 player, class_1297 target, CallbackInfo ci) {
      IlII1li1IIlil_1_iI__liii module = IlII1li1IIlil_1_iI__liii.ili1_1l1l1ili_II1Ii_11();
      if (module != null && module.isEnable()) {
         module.onAttack(target);
      }

      i_l11IliliIIl1iIIliI_1iI saver = i_l11IliliIIl1iIIliI_1iI.lIl_l1i1i_liIlill_i_I1iii_();
      if (saver != null && saver.isEnable() && saver.checkAndBlockAction()) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"method_2910"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onAttackBlock(class_2338 pos, class_2350 direction, CallbackInfoReturnable<Boolean> cir) {
      i_l11IliliIIl1iIIliI_1iI saver = i_l11IliliIIl1iIIliI_1iI.lIl_l1i1i_liIlill_i_I1iii_();
      if (saver != null && saver.isEnable() && saver.checkAndBlockAction()) {
         cir.setReturnValue(false);
      }

   }

   @Inject(
      method = {"method_2902"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onUpdateBlockBreakingProgress(class_2338 pos, class_2350 direction, CallbackInfoReturnable<Boolean> cir) {
      i_l11IliliIIl1iIIliI_1iI saver = i_l11IliliIIl1iIIliI_1iI.lIl_l1i1i_liIlill_i_I1iii_();
      if (saver != null && saver.isEnable() && saver.checkAndBlockAction()) {
         cir.setReturnValue(false);
      }

   }

   @Inject(
      method = {"method_2899"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onBreakBlock(class_2338 pos, CallbackInfoReturnable<Boolean> cir) {
      i_l11IliliIIl1iIIliI_1iI saver = i_l11IliliIIl1iIIliI_1iI.lIl_l1i1i_liIlill_i_I1iii_();
      if (saver != null && saver.isEnable() && saver.checkAndBlockAction()) {
         cir.setReturnValue(false);
      }

   }

   @Inject(
      method = {"method_2906"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void clickSlotHook(int syncId, int slotId, int button, class_1713 actionType, class_1657 player, CallbackInfo info) {
      i_IIi11l1_i_1I1lIilIi1iI__I_I module = i_IIi11l1_i_1I1lIilIi1iI__I_I.II__I_lii1illliI11l1liIIIilIII();
      if (module != null && module.blocksSlotClick(syncId, slotId, button, actionType)) {
         info.cancel();
      } else {
         I1ililiI__lilIlllIlllliI1Ii event = new I1ililiI__lilIlllIlllliI1Ii(syncId, slotId, button, actionType);
         Phantom.getInstance().getEventBus().post(event);
         if (event.isCancelled()) {
            info.cancel();
         }

      }
   }
}
