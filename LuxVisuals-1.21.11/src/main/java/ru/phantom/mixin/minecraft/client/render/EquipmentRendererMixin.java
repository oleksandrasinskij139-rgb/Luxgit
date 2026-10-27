package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I0_i9.i_1IIil1__11iilII_ilI_lll1i1ii;
import II1II1II1II1II1II1II1II1.I5_i4.l1l11lIiiIll___i_1i1I1;
import II1II1II1II1II1II1II1II1.l8_I7.llIll11li11IIii1IliIiIl_l1iI_;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import java.lang.management.ManagementFactory;
import net.minecraft.class_10186;
import net.minecraft.class_10197;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_3879;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_5321;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_10197.class})
public class EquipmentRendererMixin {
   private static final int FULL_BRIGHT_LIGHT = 15728880;
   @Unique
   private static class_1799 phantomvisuals$currentEquipmentStack;

   @Inject(
      method = {"method_64078(Lnet/minecraft/class_10186$class_10190;Lnet/minecraft/class_5321;Lnet/minecraft/class_3879;Lnet/minecraft/class_1799;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;ILnet/minecraft/class_2960;)V"},
      at = {@At("HEAD")}
   )
   private void phantomvisuals$captureEquipmentStack(class_10186.class_10190 layerType, class_5321 assetKey, class_3879 model, class_1799 stack, class_4587 matrices, class_4597 vertexConsumers, int light, class_2960 texture, CallbackInfo ci) {
      if (llIll11li11IIii1IliIiIl_l1iI_.iii_l111l_I_l1_I1lil1il_iIi()) {
         phantomvisuals$currentEquipmentStack = stack;
      }
   }

   @Inject(
      method = {"method_64078(Lnet/minecraft/class_10186$class_10190;Lnet/minecraft/class_5321;Lnet/minecraft/class_3879;Lnet/minecraft/class_1799;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;ILnet/minecraft/class_2960;)V"},
      at = {@At("RETURN")}
   )
   private void phantomvisuals$clearEquipmentStack(class_10186.class_10190 layerType, class_5321 assetKey, class_3879 model, class_1799 stack, class_4587 matrices, class_4597 vertexConsumers, int light, class_2960 texture, CallbackInfo ci) {
      phantomvisuals$currentEquipmentStack = class_1799.field_8037;
   }

   @WrapOperation(
      method = {"method_64078(Lnet/minecraft/class_10186$class_10190;Lnet/minecraft/class_5321;Lnet/minecraft/class_3879;Lnet/minecraft/class_1799;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;ILnet/minecraft/class_2960;)V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_3879;method_62100(Lnet/minecraft/class_4587;Lnet/minecraft/class_4588;III)V"
)}
   )
   private void phantomvisuals$hitColorWhiteArmorPass(class_3879 model, class_4587 matrices, class_4588 vertices, int light, int overlay, int color, Operation<Void> original) {
      original.call(new Object[]{model, matrices, vertices, light, overlay, color});
      if (l1l11lIiiIll___i_1i1I1.ii11I_iIli1llii1l_i_lIl() && i_1IIil1__11iilII_ilI_lll1i1ii.li__i_lIliiI11Il_l_l1II()) {
         l1l11lIiiIll___i_1i1I1 hitColor = l1l11lIiiIll___i_1i1I1.IilI1il_II_II1lI1l_i1_il1li();
         if (hitColor != null && hitColor.shouldColorArmor() && hitColor.shouldUseWhiteOverlay()) {
            int alpha = class_3532.method_15340(Math.round(255.0F * hitColor.getWhiteOverlayProgress()), 90, 255);
            int whitePassColor = alpha << 24 | 16777215;
            original.call(new Object[]{model, matrices, vertices, 15728880, class_4608.method_23624(1.0F, false), whitePassColor});
            original.call(new Object[]{model, matrices, vertices, 15728880, class_4608.method_23624(1.0F, false), -1});
            original.call(new Object[]{model, matrices, vertices, 15728880, class_4608.method_23624(1.0F, false), -1});
         }

      }
   }

   @ModifyArg(
      method = {"method_64078(Lnet/minecraft/class_10186$class_10190;Lnet/minecraft/class_5321;Lnet/minecraft/class_3879;Lnet/minecraft/class_1799;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;ILnet/minecraft/class_2960;)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_3879;method_62100(Lnet/minecraft/class_4587;Lnet/minecraft/class_4588;III)V"
),
      index = 2
   )
   private int phantomvisuals$hitColorArmorLight(int light) {
      if (l1l11lIiiIll___i_1i1I1.ii11I_iIli1llii1l_i_lIl() && i_1IIil1__11iilII_ilI_lll1i1ii.li__i_lIliiI11Il_l_l1II()) {
         l1l11lIiiIll___i_1i1I1 hitColor = l1l11lIiiIll___i_1i1I1.IilI1il_II_II1lI1l_i1_il1li();
         return hitColor != null && hitColor.shouldColorArmor() && hitColor.shouldUseWhiteOverlay() ? 15728880 : light;
      } else {
         return light;
      }
   }

   @ModifyArg(
      method = {"method_64078(Lnet/minecraft/class_10186$class_10190;Lnet/minecraft/class_5321;Lnet/minecraft/class_3879;Lnet/minecraft/class_1799;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;ILnet/minecraft/class_2960;)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_3879;method_62100(Lnet/minecraft/class_4587;Lnet/minecraft/class_4588;III)V"
),
      index = 4
   )
   private int phantomvisuals$hitColorArmor(int color) {
      if (llIll11li11IIii1IliIiIl_l1iI_.iii_l111l_I_l1_I1lil1il_iIi()) {
         llIll11li11IIii1IliIiIl_l1iI_ armorDurability = llIll11li11IIii1IliIiIl_l1iI_.IiiiIiIlii1iI1lIlil_I1();
         if (armorDurability != null) {
            color = armorDurability.getArmorColor(phantomvisuals$currentEquipmentStack, color);
         }
      }

      if (l1l11lIiiIll___i_1i1I1.ii11I_iIli1llii1l_i_lIl() && i_1IIil1__11iilII_ilI_lll1i1ii.li__i_lIliiI11Il_l_l1II()) {
         l1l11lIiiIll___i_1i1I1 hitColor = l1l11lIiiIll___i_1i1I1.IilI1il_II_II1lI1l_i1_il1li();
         if (hitColor != null && hitColor.shouldColorArmor()) {
            return hitColor.getArmorTintColor(color);
         }
      }

      return color;
   }

   @ModifyArg(
      method = {"method_64078(Lnet/minecraft/class_10186$class_10190;Lnet/minecraft/class_5321;Lnet/minecraft/class_3879;Lnet/minecraft/class_1799;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;ILnet/minecraft/class_2960;)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_3879;method_62100(Lnet/minecraft/class_4587;Lnet/minecraft/class_4588;III)V"
),
      index = 3
   )
   private int phantomvisuals$hitColorArmorOverlay(int overlay) {
      if (l1l11lIiiIll___i_1i1I1.ii11I_iIli1llii1l_i_lIl() && i_1IIil1__11iilII_ilI_lll1i1ii.li__i_lIliiI11Il_l_l1II()) {
         l1l11lIiiIll___i_1i1I1 hitColor = l1l11lIiiIll___i_1i1I1.IilI1il_II_II1lI1l_i1_il1li();
         if (hitColor != null) {
            if (hitColor.shouldColorPlayer()) {
               return class_4608.field_21444;
            }

            if (hitColor.shouldColorArmor()) {
               if (hitColor.getBrightness() <= 0.0F) {
                  return class_4608.field_21444;
               }

               if (hitColor.shouldUseWhiteOverlay()) {
                  return class_4608.method_23624(1.0F, false);
               }

               return class_4608.field_21444;
            }
         }

         return overlay;
      } else {
         return overlay;
      }
   }

   static {
      if (ManagementFactory.getRuntimeMXBean().getInputArguments().toString().contains("recaf")) {
         throw new UnsatisfiedLinkError("Security Integrity Violation");
      } else {
         phantomvisuals$currentEquipmentStack = class_1799.field_8037;
      }
   }
}
