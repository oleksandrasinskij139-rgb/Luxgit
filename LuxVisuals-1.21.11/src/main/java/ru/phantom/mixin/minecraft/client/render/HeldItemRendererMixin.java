package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I5_i4.I_iI_i_I1_liIi_Il_l1Il;
import II1II1II1II1II1II1II1II1.I5_i4.ili1_l1l_I_i1_1liIIiIii_I;
import II1II1II1II1II1II1II1II1.I5_i4.lIli1il11_llIi_l1lli_l_iillI;
import II1II1II1II1II1II1II1II1.l9_I8.lII__liii_1111II_1IIii;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1799;
import net.minecraft.class_1812;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_742;
import net.minecraft.class_746;
import net.minecraft.class_759;
import net.minecraft.class_9334;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_759.class})
public abstract class HeldItemRendererMixin {
   @Shadow
   protected abstract void method_65816(float var1, float var2, class_4587 var3, int var4, class_1306 var5);

   @Inject(
      method = {"method_22976(FLnet/minecraft/class_4587;Lnet/minecraft/class_4597$class_4598;Lnet/minecraft/class_746;I)V"},
      at = {@At("HEAD")}
   )
   private void Phantom$onRenderItemHead(float tickDelta, class_4587 matrices, class_4597.class_4598 vertexConsumers, class_746 player, int light, CallbackInfo ci) {
      lIli1il11_llIi_l1lli_l_iillI shaderHands = lIli1il11_llIi_l1lli_l_iillI.lili_lll1ii11ii1i_iiI1_i();
      if (shaderHands != null && shaderHands.isEnable()) {
         lII__liii_1111II_1IIii.i_l__1l_I1_lll__111i11l__I().captureBeforeHands();
      }
   }

   @Inject(
      method = {"method_22976(FLnet/minecraft/class_4587;Lnet/minecraft/class_4597$class_4598;Lnet/minecraft/class_746;I)V"},
      at = {@At("TAIL")}
   )
   private void Phantom$onRenderItemTail(float tickDelta, class_4587 matrices, class_4597.class_4598 vertexConsumers, class_746 player, int light, CallbackInfo ci) {
      lIli1il11_llIi_l1lli_l_iillI shaderHands = lIli1il11_llIi_l1lli_l_iillI.lili_lll1ii11ii1i_iiI1_i();
      if (shaderHands != null && shaderHands.isEnable()) {
         lII__liii_1111II_1IIii.i_l__1l_I1_lll__111i11l__I().captureAfterHands();
      }
   }

   @Inject(
      method = {"method_3228"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_4587;method_22903()V",
   shift = Shift.AFTER,
   ordinal = 0
)}
   )
   private void Phantom$applyViewModel(class_742 player, float tickDelta, float pitch, class_1268 hand, float swingProgress, class_1799 item, float equipProgress, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
      ili1_l1l_I_i1_1liIIiIii_I viewModel = ili1_l1l_I_i1_1liIIiIii_I.llii_ll_I__lI_lii_lIi1();
      if (viewModel != null && viewModel.isEnable() && !item.method_7960() && !item.method_57826(class_9334.field_49646)) {
         boolean isMainHand = hand == class_1268.field_5808;
         class_1306 arm = isMainHand ? player.method_6068() : player.method_6068().method_5928();
         float x = isMainHand ? viewModel.mainX.getValue() : viewModel.offX.getValue();
         float y = isMainHand ? viewModel.mainY.getValue() : viewModel.offY.getValue();
         float z = isMainHand ? viewModel.mainZ.getValue() : viewModel.offZ.getValue();
         class_310 client = class_310.method_1551();
         boolean isActiveItem = client.field_1724 != null && client.field_1724.method_6030().method_7909() == item.method_7909();
         boolean isFoodOrPotion = item.method_57826(class_9334.field_50075) || item.method_7909() instanceof class_1812;
         if (isFoodOrPotion && isActiveItem) {
            x = 0.0F;
            z = 0.0F;
         }

         if (arm == class_1306.field_6182) {
            x = -x;
         }

         matrices.method_46416(x, y, z);
      }
   }

   @Redirect(
      method = {"method_3228"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_759;method_65816(FFLnet/minecraft/class_4587;ILnet/minecraft/class_1306;)V",
   ordinal = 2
)
   )
   private void Phantom$redirectSwingArm(class_759 instance, float swingProgress, float equipProgress, class_4587 matrices, int armX, class_1306 arm) {
      I_iI_i_I1_liIi_Il_l1Il module = I_iI_i_I1_liIi_Il_l1Il.IIlll1l1lIIiiI_1li_IIIll1I();
      class_310 client = class_310.method_1551();
      boolean isMainHandArm = client.field_1724 != null && arm == client.field_1724.method_6068();
      if (module != null && module.isEnable() && isMainHandArm) {
         module.renderSwordAnimation(matrices, swingProgress, equipProgress, arm);
      } else {
         this.method_65816(swingProgress, equipProgress, matrices, armX, arm);
      }
   }
}
