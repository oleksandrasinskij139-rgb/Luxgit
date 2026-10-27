package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I0_i9.l_1_lII1_1lI_iIlI1_1l_;
import II1II1II1II1II1II1II1II1.I5_i4.Il1ll11iiiIIIlI1IliI_1l;
import II1II1II1II1II1II1II1II1.I6_i5.i_I_IIllII1IlII1_i_11_lI_;
import II1II1II1II1II1II1II1II1.I6_i5.i_ii1_iii1_i_iiii_ilI1_l;
import II1II1II1II1II1II1II1II1.l8_I7.I_1i1l__i1iill_1Iii1ii_Iil;
import net.minecraft.class_2561;
import net.minecraft.class_327;
import net.minecraft.class_329;
import net.minecraft.class_332;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({class_329.class})
public class ScoreboardNameProtectMixin {
   @Redirect(
      method = {"method_1757(Lnet/minecraft/class_332;Lnet/minecraft/class_266;)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_332;method_51439(Lnet/minecraft/class_327;Lnet/minecraft/class_2561;IIIZ)I"
)
   )
   private int Phantom$renderScoreboardText(class_332 context, class_327 textRenderer, class_2561 text, int x, int y, int color, boolean shadow) {
      return this.Phantom$drawScoreboardText(context, textRenderer, text, x, y, color, shadow);
   }

   @Redirect(
      method = {"method_1757(Lnet/minecraft/class_332;Lnet/minecraft/class_266;)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_332;method_27535(Lnet/minecraft/class_327;Lnet/minecraft/class_2561;III)I"
),
      require = 0
   )
   private int Phantom$renderScoreboardShadowText(class_332 context, class_327 textRenderer, class_2561 text, int x, int y, int color) {
      return this.Phantom$drawScoreboardText(context, textRenderer, text, x, y, color, true);
   }

   @Redirect(
      method = {"method_1757(Lnet/minecraft/class_332;Lnet/minecraft/class_266;)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_332;method_27534(Lnet/minecraft/class_327;Lnet/minecraft/class_2561;III)V"
),
      require = 0
   )
   private void Phantom$renderScoreboardCenteredText(class_332 context, class_327 textRenderer, class_2561 text, int centerX, int y, int color) {
      class_2561 protectedText = I_1i1l__i1iill_1Iii1ii_Iil.l1liI__i_il_1i_l_iIIlI_(text);
      float width = Il1ll11iiiIIIlI1IliI_1l.Ii____I1I1_1iI11__lI_i_() ? l_1_lII1_1lI_iIlI1_1l_.l__l_ilIliIlII1il__Ii_I_lIi_il(textRenderer, (i_ii1_iii1_i_iiii_ilI1_l)i_I_IIllII1IlII1_i_11_lI_.SEMIBOLD.get(), protectedText, 8.0F, color) : (float)l_1_lII1_1lI_iIlI1_1l_.iii11IiliiiiIlIIIi1Ii11ii_11_l1(textRenderer, protectedText, color);
      this.Phantom$drawScoreboardText(context, textRenderer, protectedText, Math.round((float)centerX - width / 2.0F), y, color, true);
   }

   private int Phantom$drawScoreboardText(class_332 context, class_327 textRenderer, class_2561 text, int x, int y, int color, boolean vanillaShadow) {
      class_2561 protectedText = I_1i1l__i1iill_1Iii1ii_Iil.l1liI__i_il_1i_l_iIIlI_(text);
      boolean drawShadow = vanillaShadow || Il1ll11iiiIIIlI1IliI_1l.li_iIllIiI____1_I_IlII_I_I();
      context.method_51452();
      if (Il1ll11iiiIIIlI1IliI_1l.Ii____I1I1_1iI11__lI_i_()) {
         float measuredWidth = l_1_lII1_1lI_iIlI1_1l_.l__l_ilIliIlII1il__Ii_I_lIi_il(textRenderer, (i_ii1_iii1_i_iiii_ilI1_l)i_I_IIllII1IlII1_i_11_lI_.SEMIBOLD.get(), protectedText, 8.0F, color);
         int width = Math.round(measuredWidth);
         int drawX = Il1ll11iiiIIIlI1IliI_1l.Il1lliliIiIIli1liiIl1i_i1lI1I(x, y, width);
         int drawY = Il1ll11iiiIIIlI1IliI_1l.iI1IlI_I__ii_l__iIli_1li1(y);
         l_1_lII1_1lI_iIlI1_1l_.Il_li1IlilI1l_il_1lIi1Ii1(context, textRenderer, (i_ii1_iii1_i_iiii_ilI1_l)i_I_IIllII1IlII1_i_11_lI_.SEMIBOLD.get(), protectedText, 8.0F, (float)drawX, (float)drawY, color, drawShadow);
         context.method_51452();
         return width;
      } else {
         int width = l_1_lII1_1lI_iIlI1_1l_.iii11IiliiiiIlIIIi1Ii11ii_11_l1(textRenderer, protectedText, color);
         int drawX = Il1ll11iiiIIIlI1IliI_1l.Il1lliliIiIIli1liiIl1i_i1lI1I(x, y, width);
         int drawY = Il1ll11iiiIIIlI1IliI_1l.iI1IlI_I__ii_l__iIli_1li1(y);
         int res = context.method_51439(textRenderer, protectedText, drawX, drawY, color, drawShadow);
         context.method_51452();
         return res;
      }
   }
}
