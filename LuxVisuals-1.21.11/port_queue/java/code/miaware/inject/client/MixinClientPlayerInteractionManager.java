package code.miaware.inject.client;

import code.miaware.api.event.events.player.world.K932e6977c9a3797220;
import code.miaware.api.event.events.player.world.K94902759dafeeed39c;
import code.miaware.api.system.client.K4d1f360e533c3997d9;
import code.miaware.api.system.client.K9a4619034baf09d6b2;
import code.miaware.client.features.modules.combat.K8722fab69227107712;
import code.miaware.client.features.modules.player.K1cb52ce4c79b2f47f0;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_310;
import net.minecraft.class_3965;
import net.minecraft.class_636;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_636.class})
public class MixinClientPlayerInteractionManager {
   @Inject(
      method = {"interactBlock"},
      at = {@At("RETURN")}
   )
   private void lux$invSaveChest(class_746 player, class_1268 hand, class_3965 hit, CallbackInfoReturnable<class_1269> cir) {
      if (((class_1269)cir.getReturnValue()).method_23665()) {
         K1cb52ce4c79b2f47f0.getInstance().openingBlock(hit.method_17777());
      }

   }

   @Inject(
      method = {"clickSlot"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void onClickSlot(int syncId, int slotId, int button, class_1713 actionType, class_1657 player, CallbackInfo ci) {
      if (class_310.method_1551().field_1724 != null && class_310.method_1551().field_1687 != null) {
         K932e6977c9a3797220 event = new K932e6977c9a3797220(actionType, slotId, button, syncId);
         if (K94902759dafeeed39c.getInstance().call(event)) {
            ci.cancel();
         }

      }
   }

   @Inject(
      method = {"attackEntity"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onAttackEntity(class_1657 player, class_1297 target, CallbackInfo ci) {
      if (player == class_310.method_1551().field_1724 && K8722fab69227107712.shouldBlock(target)) {
         ci.cancel();
      } else {
         if (target instanceof class_1309) {
            class_1309 living = (class_1309)target;
            if (player == class_310.method_1551().field_1724) {
               K9a4619034baf09d6b2.attack(living);
            }

            K4d1f360e533c3997d9.getInstance().markTarget(living);
         }

      }
   }
}
