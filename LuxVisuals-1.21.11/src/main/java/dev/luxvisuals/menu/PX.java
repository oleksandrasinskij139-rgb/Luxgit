package dev.luxvisuals.menu;

import II1II1II1II1II1II1II1II1.I5_i4.Illi_lI1l___i_iII1_IliIIIII1lI;
import II1II1II1II1II1II1II1II1.I5_i4.ilI1_l1II1II__1ii1_il_liIIl1;
import II1II1II1II1II1II1II1II1.I6_i5.IIIiii1liIIl__1i_I_iIi1l_1I_l1;
import II1II1II1II1II1II1II1II1.I6_i5.i_I_IIllII1IlII1_i_11_lI_;
import II1II1II1II1II1II1II1II1.I6_i5.i_ii1_iii1_i_iiii_ilI1_l;
import II1II1II1II1II1II1II1II1.l2_I1.il_iII1__Il1illlI_iliI1II1l1;
import II1II1II1II1II1II1II1II1.l8_I7.II111_iIl_IlI1I_i_IiIili_i_iI;
import II1II1II1II1II1II1II1II1.l8_I7.l1i_i1i_Il1_lii111_Il_ll_1_li_1;
import java.awt.Color;
import net.minecraft.class_156;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import ru.phantom.Phantom;

public final class PX {
   public static final int MAINMENU = 0;
   public static final int MEDIUM = 1;
   public static final int REGULAR = 2;
   public static final int ICONS1 = 3;

   private PX() {
   }

   public static i_ii1_iii1_i_iiii_ilI1_l font(int var0) {
      switch (var0) {
         case 0 -> {
            return (i_ii1_iii1_i_iiii_ilI1_l)i_I_IIllII1IlII1_i_11_lI_.MAINMENU1.get();
         }
         case 1 -> {
            return (i_ii1_iii1_i_iiii_ilI1_l)i_I_IIllII1IlII1_i_11_lI_.MEDIUM.get();
         }
         case 2 -> {
            return (i_ii1_iii1_i_iiii_ilI1_l)i_I_IIllII1IlII1_i_11_lI_.REGULAR.get();
         }
         default -> {
            return (i_ii1_iii1_i_iiii_ilI1_l)i_I_IIllII1IlII1_i_11_lI_.ICONS1.get();
         }
      }
   }

   public static float w(int var0, String var1, float var2) {
      return font(var0).getWidth(var1, var2);
   }

   public static float sw() {
      return l1i_i1i_Il1_lii111_Il_ll_1_li_1.sr.getScaledWidth();
   }

   public static float sh() {
      return l1i_i1i_Il1_lii111_Il_ll_1_li_1.sr.getScaledHeight();
   }

   public static class_310 mc() {
      return II111_iIl_IlI1I_i_IiIili_i_iI.mc;
   }

   public static void text(class_332 var0, int var1, String var2, float var3, float var4, float var5, Color var6) {
      IIIiii1liIIl__1i_I_iIi1l_1I_l1.I1I11_1i11Ii1Ii_11l_I_i_1(var0, font(var1), var2, var3, var4, var5, var6);
   }

   public static void rr(class_332 var0, float var1, float var2, float var3, float var4, float var5, Color var6) {
      IIIiii1liIIl__1i_I_iIi1l_1I_l1.III_II_li1I111iiI11lIl1I_I(var0, var1, var2, var3, var4, var5, var6);
   }

   public static void rect(class_332 var0, float var1, float var2, float var3, float var4, Color var5) {
      IIIiii1liIIl__1i_I_iIi1l_1I_l1.i___i_iI_iI1Il_i____1_II_l(var0, var1, var2, var3, var4, var5);
   }

   public static void blur(class_332 var0, float var1, float var2, float var3, float var4, float var5, float var6, Color var7) {
      IIIiii1liIIl__1i_I_iIi1l_1I_l1.Iill___1__II111illlI____(var0, var1, var2, var3, var4, var5, var6, var7);
   }

   public static void tex(class_332 var0, class_2960 var1, float var2, float var3, float var4, float var5, float var6, Color var7) {
      IIIiii1liIIl__1i_I_iIi1l_1I_l1.llI11l1I_ll1li1i1iil1l_1i11l(var0, var1, var2, var3, var4, var5, var6, var7);
   }

   public static class_2960 id(String var0) {
      return Phantom.id(var0);
   }

   public static boolean hover(double var0, double var2, float var4, float var5, float var6, float var7) {
      return il_iII1__Il1illlI_iliI1II1l1.iiIiIIIIll1llI1l_1l11liiI___(var0, var2, var4, var5, var6, var7);
   }

   public static float fastAnim(float var0, float var1, float var2) {
      return il_iII1__Il1illlI_iliI1II1l1.Il1ll_IlIi_l_illi_1lIi_i11(var0, var1, var2);
   }

   public static void handCursor() {
      ilI1_l1II1II__1ii1_il_liIIl1.I_I1iii11IlI_lli_I_IiI(Illi_lI1l___i_iII1_IliIIIII1lI.HAND);
   }

   public static double deltaTime() {
      int var0 = mc().method_47599();
      return var0 > 0 ? Math.min((double)1.0F / (double)var0, (double)1.0F) : (double)1.0F;
   }

   public static double lerp(double var0, double var2, double var4) {
      double var6 = class_3532.method_15350(var4 * deltaTime(), (double)0.0F, (double)1.0F);
      return var0 + (var2 - var0) * var6;
   }

   public static Color lerpColor(Color var0, Color var1, float var2) {
      var2 = class_3532.method_15363(var2, 0.0F, 1.0F);
      return new Color(class_3532.method_48781(var2, var0.getRed(), var1.getRed()), class_3532.method_48781(var2, var0.getGreen(), var1.getGreen()), class_3532.method_48781(var2, var0.getBlue(), var1.getBlue()), class_3532.method_48781(var2, var0.getAlpha(), var1.getAlpha()));
   }

   public static long now() {
      return class_156.method_658();
   }
}
