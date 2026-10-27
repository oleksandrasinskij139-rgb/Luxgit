package ru.phantom.mixin.minecraft.client;

import II1II1II1II1II1II1II1II1.I5_i4.I_l_III_IIlliliiII_iII;
import II1II1II1II1II1II1II1II1.I5_i4.ilI111Il11__1II1IiI1__I1i1i;
import II1II1II1II1II1II1II1II1.l2_I1.i1lIi_iIl_il_Ili_1l1il_1II11i;
import net.minecraft.class_4184;
import net.minecraft.class_5636;
import net.minecraft.class_638;
import net.minecraft.class_6854;
import net.minecraft.class_758;
import net.minecraft.class_9958;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_758.class})
public class BackgroundRendererMixin {
   @Inject(
      method = {"method_62185"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void getFogColorHook(class_4184 camera, float tickDelta, class_638 world, int clampedViewDistance, float skyDarkness, CallbackInfoReturnable<Vector4f> cir) {
      ilI111Il11__1II1IiI1__I1i1i ambience = ilI111Il11__1II1IiI1__I1i1i.iI1__1_l_11i1ll1l11i_11i__1l();
      if (ambience != null && ambience.shouldChangeFog()) {
         int color = ambience.getFogColorRgb();
         cir.setReturnValue(new Vector4f(i1lIi_iIl_il_Ili_1l1il_1II11i.i1i_IilliII11l1_iilI11__i1Iil_1(color), i1lIi_iIl_il_Ili_1l1il_1II11i.lii1I1Il_Il_I_1I1lil1lIIiI1i(color), i1lIi_iIl_il_Ili_1l1il_1II11i.iil__1ll___ilii_il1_iIiili1(color), i1lIi_iIl_il_Ili_1l1il_1II11i.lI11il1Ii__l_iIil1__l11(color)));
      }

   }

   @Inject(
      method = {"method_3211"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void modifyFog(class_4184 camera, class_758.class_4596 fogType, Vector4f vector4f, float viewDistance, boolean thickenFog, float tickDelta, CallbackInfoReturnable<class_9958> cir) {
      I_l_III_IIlliliiII_iII noFluid = I_l_III_IIlliliiII_iII.IIl1i_Il_I__1ilIl__ll__I1();
      if (noFluid != null && noFluid.isEnable()) {
         class_5636 submersionType = camera.method_19334();
         if (submersionType == class_5636.field_27886 || submersionType == class_5636.field_27885) {
            cir.setReturnValue(class_9958.field_53065);
            return;
         }
      }

      ilI111Il11__1II1IiI1__I1i1i ambience = ilI111Il11__1II1IiI1__I1i1i.iI1__1_l_11i1ll1l11i_11i__1l();
      if (ambience != null && ambience.shouldChangeFog()) {
         int color = ambience.getFogColorRgb();
         cir.setReturnValue(new class_9958(2.0F, ambience.fog_distance.getValue(), class_6854.field_36351, i1lIi_iIl_il_Ili_1l1il_1II11i.i1i_IilliII11l1_iilI11__i1Iil_1(color), i1lIi_iIl_il_Ili_1l1il_1II11i.lii1I1Il_Il_I_1I1lil1lIIiI1i(color), i1lIi_iIl_il_Ili_1l1il_1II11i.iil__1ll___ilii_il1_iIiili1(color), i1lIi_iIl_il_Ili_1l1il_1II11i.lI11il1Ii__l_iIil1__l11(color)));
      }

   }
}
