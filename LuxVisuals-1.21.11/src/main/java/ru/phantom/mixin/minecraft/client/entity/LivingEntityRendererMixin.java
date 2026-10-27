package ru.phantom.mixin.minecraft.client.entity;

import II1II1II1II1II1II1II1II1.I5_i4.i_i_III11iI_IlII11ll_1__l11;
import II1II1II1II1II1II1II1II1.I5_i4.l1l11lIiiIll___i_1i1I1;
import II1II1II1II1II1II1II1II1.I5_i4.liI_iiiIiIIi1i1_il11I1i_i;
import II1II1II1II1II1II1II1II1.I9_i8.I_1ii1Ii11lI1I_1lliI_iI;
import II1II1II1II1II1II1II1II1.l8_I7.iI_iliili1I__l1IIli1liill_;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_10042;
import net.minecraft.class_10055;
import net.minecraft.class_1294;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_583;
import net.minecraft.class_922;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_922.class})
public abstract class LivingEntityRendererMixin<T extends class_1309, S extends class_10042, M extends class_583<? super S>> {
   @Unique
   private static final int PHANTOMVISUALS_FULL_BRIGHT_LIGHT = 15728880;
   @Unique
   private static boolean phantomvisuals$currentStateHurt;
   @Unique
   private static boolean phantomvisuals$currentStateInvisible;

   @Shadow
   public abstract class_2960 method_3885(S var1);

   @Inject(
      method = {"method_24302"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void phantomvisuals$getTranslucentRenderLayer(S state, boolean showBody, boolean translucent, boolean showOutline, CallbackInfoReturnable<class_1921> cir) {
      if (iI_iliili1I__l1IIli1liill_.IIl1ll111llI1I1l1l_I__I_11llll_() && state instanceof class_10055 && (state.field_53333 || phantomvisuals$currentStateInvisible)) {
         cir.setReturnValue(class_1921.method_29379(this.method_3885(state)));
      }

   }

   @Inject(
      method = {"method_4055"},
      at = {@At("HEAD")},
      cancellable = true
   )
   protected void phantomvisuals$onHasLabel(class_1309 entity, double squaredDistance, CallbackInfoReturnable<Boolean> cir) {
      if (!iI_iliili1I__l1IIli1liill_.IIl1ll111llI1I1l1l_I__I_11llll_() || !(entity instanceof class_1657) || !entity.method_5767() && !entity.method_6059(class_1294.field_5905)) {
         if (liI_iiiIiIIi1i1_il11I1i_i.l_il_Ii1I_Ii_II1l1i_liIiiI1__()) {
            class_310 mc = class_310.method_1551();
            if (mc.field_1724 != null && entity != null && entity.method_5628() == mc.field_1724.method_5628()) {
               cir.setReturnValue(true);
            }
         }

      } else {
         cir.setReturnValue(true);
      }
   }

   @Inject(
      method = {"method_62355(Lnet/minecraft/class_1309;Lnet/minecraft/class_10042;F)V"},
      at = {@At("TAIL")}
   )
   private void phantomvisuals$updateInvisibleLivingState(T entity, S state, float tickDelta, CallbackInfo ci) {
      if (iI_iliili1I__l1IIli1liill_.IIl1ll111llI1I1l1l_I__I_11llll_() && entity instanceof class_1657 && (entity.method_5767() || entity.method_6059(class_1294.field_5905))) {
         state.field_53333 = true;
         state.field_53461 = false;
      }

   }

   @Inject(
      method = {"method_4054(Lnet/minecraft/class_10042;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;I)V"},
      at = {@At("HEAD")}
   )
   private void phantomvisuals$captureHitColorState(S state, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
      phantomvisuals$currentStateHurt = state.field_53460 && l1l11lIiiIll___i_1i1I1.ii11I_iIli1llii1l_i_lIl();
      phantomvisuals$currentStateInvisible = state.field_53333 && iI_iliili1I__l1IIli1liill_.IIl1ll111llI1I1l1l_I__I_11llll_() && state instanceof class_10055;
   }

   @Inject(
      method = {"method_4054(Lnet/minecraft/class_10042;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;I)V"},
      at = {@At("RETURN")}
   )
   private void phantomvisuals$clearHitColorState(S state, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
      phantomvisuals$currentStateHurt = false;
      phantomvisuals$currentStateInvisible = false;
   }

   @ModifyExpressionValue(
      method = {"method_4054(Lnet/minecraft/class_10042;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;I)V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_922;method_62483(Lnet/minecraft/class_10042;)Z"
)}
   )
   private boolean phantom$farLivingFeatures(boolean original, S state) {
      return original && i_i_III11iI_IlII11ll_1__l11.I1liIiiIiIiIil1lIi__llI_1_1i1__() && !(state instanceof class_10055) && I_1ii1Ii11lI1I_1lliI_iI.II1I_i11_1__llIl_iili_Il1l_li(state.field_53332) ? false : original;
   }

   @WrapOperation(
      method = {"method_4054(Lnet/minecraft/class_10042;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;I)V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_583;method_62100(Lnet/minecraft/class_4587;Lnet/minecraft/class_4588;III)V"
)}
   )
   private void phantomvisuals$hitColorWhitePlayerPass(class_583<?> model, class_4587 matrices, class_4588 vertices, int light, int overlay, int color, Operation<Void> original) {
      if (!phantomvisuals$currentStateInvisible && !phantomvisuals$currentStateHurt) {
         original.call(new Object[]{model, matrices, vertices, light, overlay, color});
      } else {
         if (phantomvisuals$currentStateInvisible && iI_iliili1I__l1IIli1liill_.IIl1ll111llI1I1l1l_I__I_11llll_()) {
            int alpha = (int)(255.0F * iI_iliili1I__l1IIli1liill_.I1_Ii1_iI1llli_I1IilIII1Il1());
            color = color & 16777215 | alpha << 24;
         }

         l1l11lIiiIll___i_1i1I1 hitColor = l1l11lIiiIll___i_1i1I1.IilI1il_II_II1lI1l_i1_il1li();
         if (phantomvisuals$currentStateHurt && hitColor != null && hitColor.isEnable()) {
            if (hitColor.shouldColorArmor()) {
               overlay = class_4608.field_21444;
            } else if (hitColor.shouldColorPlayer()) {
               if (hitColor.getBrightness() <= 0.0F) {
                  overlay = class_4608.field_21444;
               } else if (hitColor.shouldUseWhiteOverlay()) {
                  light = 15728880;
                  overlay = class_4608.method_23624(1.0F, false);
                  color = hitColor.getTintColor(color);
               } else {
                  overlay = class_4608.field_21444;
                  color = hitColor.getTintColor(color);
               }
            }
         }

         original.call(new Object[]{model, matrices, vertices, light, overlay, color});
         if (phantomvisuals$currentStateHurt && hitColor != null && hitColor.shouldColorPlayer() && hitColor.shouldUseWhiteOverlay()) {
            original.call(new Object[]{model, matrices, vertices, 15728880, class_4608.method_23624(1.0F, false), -1});
            original.call(new Object[]{model, matrices, vertices, 15728880, class_4608.method_23624(1.0F, false), -1});
         }

      }
   }
}
