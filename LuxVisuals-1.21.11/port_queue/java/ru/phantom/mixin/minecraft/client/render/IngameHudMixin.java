package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I5_i4.I1IlIII11__I_1I_ll1_Ii1I;
import II1II1II1II1II1II1II1II1.I5_i4.IIiI1iiI1l1__I_1i11IiIlIii_;
import II1II1II1II1II1II1II1II1.I5_i4.Ii1i_1i_lIl1lI_Ii_11lI1i1i_i__;
import II1II1II1II1II1II1II1II1.I5_i4.Il1ll11iiiIIIlI1IliI_1l;
import II1II1II1II1II1II1II1II1.I5_i4.iiIIliIli1Il_I_i_I1iiil11;
import II1II1II1II1II1II1II1II1.l8_I7.II111_iIl_IlI1I_i_IiIili_i_iI;
import II1II1II1II1II1II1II1II1.l8_I7.II_iil_I_1iIiIi__li1_i;
import II1II1II1II1II1II1II1II1.l8_I7.i_ii_I1iI1iiI1_I1____1il11;
import II1II1II1II1II1II1II1II1.l8_I7.lIi1i11_II_1i1_iillil_li_I1lll;
import java.util.function.Function;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_266;
import net.minecraft.class_2960;
import net.minecraft.class_329;
import net.minecraft.class_332;
import net.minecraft.class_355;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.phantom.Phantom;

@Mixin({class_329.class})
public class IngameHudMixin {
   @Shadow
   @Final
   private static class_2960 field_45311;
   @Shadow
   @Final
   private class_355 field_2015;

   @Inject(
      method = {"method_1753"},
      at = {@At("HEAD")}
   )
   private void phantom$renderColorBoost(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      Ii1i_1i_lIl1lI_Ii_11lI1i1i_i__ colorBoost = Ii1i_1i_lIl1lI_Ii_11lI1i1i_i__.I1I_l_1liII_l1iIi_I11l_i_I();
      if (colorBoost != null && colorBoost.isEnable()) {
         colorBoost.render(context.method_51448().method_23760().method_23761(), context.method_51421(), context.method_51443());
      }

   }

   @Inject(
      method = {"method_1753"},
      at = {@At("HEAD")}
   )
   private void Phantom$syncTabState(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      IIiI1iiI1l1__I_1i11IiIlIii_ animations = IIiI1iiI1l1__I_1i11IiIlIii_.i1I_1IlilIll1_l_11_iiIll();
      if (animations != null && animations.shouldSmoothTab() && II111_iIl_IlI1I_i_IiIili_i_iI.mc.field_1687 != null) {
         animations.setTabPressed(II111_iIl_IlI1I_i_IiIili_i_iI.mc.field_1690.field_1907.method_1434());
         animations.updateTabProgress();
         if (animations.isTabVisible()) {
            animations.setTabSyncRender(true);

            try {
               this.field_2015.method_1919(context, II111_iIl_IlI1I_i_IiIili_i_iI.mc.method_22683().method_4486(), II111_iIl_IlI1I_i_IiIili_i_iI.mc.field_1687.method_8428(), (class_266)null);
            } finally {
               animations.setTabSyncRender(false);
            }
         }

      }
   }

   @Inject(
      method = {"method_1753"},
      at = {@At("RETURN")}
   )
   public void render(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      context.method_51452();
      lIi1i11_II_1i1_iillil_li_I1lll eventRender2D = new lIi1i11_II_1i1_iillil_li_I1lll(context, tickCounter);
      Phantom.getInstance().getEventBus().post(eventRender2D);
      II_iil_I_1iIiIi__li1_i.lil_il_11iIil__llI11___iiiI(context);
      int i = (int)(II111_iIl_IlI1I_i_IiIili_i_iI.mc.field_1729.method_1603() * (double)II111_iIl_IlI1I_i_IiIili_i_iI.mc.method_22683().method_4486() / (double)II111_iIl_IlI1I_i_IiIili_i_iI.mc.method_22683().method_4480());
      int j = (int)(II111_iIl_IlI1I_i_IiIili_i_iI.mc.field_1729.method_1604() * (double)II111_iIl_IlI1I_i_IiIili_i_iI.mc.method_22683().method_4502() / (double)II111_iIl_IlI1I_i_IiIili_i_iI.mc.method_22683().method_4507());
      Phantom.getInstance().getHudManager().renderHud(context, i, j);
   }

   @Inject(
      method = {"method_55803"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void Phantom$cancelScoreboard(CallbackInfo ci) {
      if (iiIIliIli1Il_I_i_I1iiil11.lii1I11_i_l1il_liIiI111ilil1_("Scoreboard")) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"method_1757(Lnet/minecraft/class_332;Lnet/minecraft/class_266;)V"},
      at = {@At("HEAD")}
   )
   private void Phantom$beginCustomScoreboard(class_332 context, class_266 objective, CallbackInfo ci) {
      context.method_51452();
      Il1ll11iiiIIIlI1IliI_1l.IlI_IiiIIliiIIiliIi_11_i1ilIIi();
      float scale = Il1ll11iiiIIIlI1IliI_1l.l1_1____IliIli__ii1Iii1I();
      if (scale != 1.0F) {
         float px = Il1ll11iiiIIIlI1IliI_1l.lII1_1iIi_lliiIl_I1___11_l_l1i();
         float py = Il1ll11iiiIIIlI1IliI_1l.IiilI1iIi1I1I1_i__iI_ll();
         context.method_51448().method_22903();
         context.method_51448().method_46416(px, py, 0.0F);
         context.method_51448().method_22905(scale, scale, 1.0F);
         context.method_51448().method_46416(-px, -py, 0.0F);
      }

   }

   @Inject(
      method = {"method_1757(Lnet/minecraft/class_332;Lnet/minecraft/class_266;)V"},
      at = {@At("RETURN")}
   )
   private void Phantom$endCustomScoreboard(class_332 context, class_266 objective, CallbackInfo ci) {
      context.method_51452();
      float scale = Il1ll11iiiIIIlI1IliI_1l.l1_1____IliIli__ii1Iii1I();
      if (scale != 1.0F) {
         context.method_51448().method_22909();
      }

      Il1ll11iiiIIIlI1IliI_1l.l1_l_liIi_1IllII_II_li11_();
   }

   @Redirect(
      method = {"method_1757(Lnet/minecraft/class_332;Lnet/minecraft/class_266;)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_332;method_25294(IIIII)V"
)
   )
   private void Phantom$customScoreboardBackground(class_332 context, int x1, int y1, int x2, int y2, int color) {
      Il1ll11iiiIIIlI1IliI_1l.II1i_i1lil1iIl_II_l1I1ii1ii_Ii(x1, y1, x2, y2);
      if (!Il1ll11iiiIIIlI1IliI_1l.lil__liIiIIIlil_1_illl_li()) {
         context.method_25294(Il1ll11iiiIIIlI1IliI_1l.I_ili_i__i111li_iill1il_1lIl_(x1), Il1ll11iiiIIIlI1IliI_1l.iI1IlI_I__ii_l__iIli_1li1(y1), Il1ll11iiiIIIlI1IliI_1l.I_ili_i__i111li_iill1il_1lIl_(x2), Il1ll11iiiIIIlI1IliI_1l.iI1IlI_I__ii_l__iIli_1li1(y2), color);
      }
   }

   @Redirect(
      method = {"method_1757(Lnet/minecraft/class_332;Lnet/minecraft/class_266;)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_332;method_51739(Lnet/minecraft/class_1921;IIIII)V"
),
      require = 0
   )
   private void Phantom$customScoreboardLayerBackground(class_332 context, class_1921 renderLayer, int x1, int y1, int x2, int y2, int color) {
      Il1ll11iiiIIIlI1IliI_1l.II1i_i1lil1iIl_II_l1I1ii1ii_Ii(x1, y1, x2, y2);
      if (!Il1ll11iiiIIIlI1IliI_1l.lil__liIiIIIlil_1_illl_li()) {
         context.method_51739(renderLayer, Il1ll11iiiIIIlI1IliI_1l.I_ili_i__i111li_iill1il_1lIl_(x1), Il1ll11iiiIIIlI1IliI_1l.iI1IlI_I__ii_l__iIli_1li1(y1), Il1ll11iiiIIIlI1IliI_1l.I_ili_i__i111li_iill1il_1lIl_(x2), Il1ll11iiiIIIlI1IliI_1l.iI1IlI_I__ii_l__iIli_1li1(y2), color);
      }
   }

   @Inject(
      method = {"method_1736"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void Phantom$cancelVanillaCrosshair(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      if (I1IlIII11__I_1I_ll1_Ii1I.l_1_iI1i1li_ll_II__l_lilIIi()) {
         ci.cancel();
      }

   }

   @Redirect(
      method = {"method_1759"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_332;method_52706(Ljava/util/function/Function;Lnet/minecraft/class_2960;IIII)V"
)
   )
   private void Phantom$hideVanillaHotbarSelection(class_332 context, Function<class_2960, class_1921> renderLayers, class_2960 sprite, int x, int y, int width, int height) {
      IIiI1iiI1l1__I_1i11IiIlIii_ animations = IIiI1iiI1l1__I_1i11IiIlIii_.i1I_1IlilIll1_l_11_iiIll();
      if (animations != null && animations.shouldSmoothInventory() && field_45311.equals(sprite)) {
         context.method_52706(renderLayers, sprite, animations.smoothHotbarSelectionX(x), y, width, height);
      } else {
         context.method_52706(renderLayers, sprite, x, y, width, height);
      }
   }

   @Inject(
      method = {"method_1762"},
      at = {@At("HEAD")}
   )
   private void Phantom$renderHotbarItemBackground(class_332 context, int x, int y, class_9779 tickCounter, class_1657 player, class_1799 stack, int seed, CallbackInfo ci) {
      i_ii_I1iI1iiI1_I1____1il11.llIl_11Illi1_1lliII1llli1II(context, x, y, stack);
   }

   @Inject(
      method = {"method_1735"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void Phantom$cancelVignette(class_332 context, class_1297 entity, CallbackInfo ci) {
      if (iiIIliIli1Il_I_i_I1iiil11.lii1I11_i_l1il_liIiI111ilil1_("Затемнение")) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"method_1765"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void Phantom$cancelStatusEffectOverlay(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      if (iiIIliIli1Il_I_i_I1iiil11.lii1I11_i_l1il_liIiI111ilil1_("Зелья в углу")) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"method_55798"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void Phantom$cancelMiscOverlays(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      if (iiIIliIli1Il_I_i_I1iiil11.lii1I11_i_l1il_liIiI111ilil1_("Анимация тотема")) {
         ci.cancel();
      }

   }
}
