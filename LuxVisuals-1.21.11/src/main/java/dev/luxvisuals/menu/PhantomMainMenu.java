package dev.luxvisuals.menu;

import II1II1II1II1II1II1II1II1.I6_i5.l_lllI_i11IiIl_i11iiii1;
import II1II1II1II1II1II1II1II1.l8_I7.li__IIIi_IIi1I_11l__il_lil_il_;
import java.awt.Color;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_426;
import net.minecraft.class_429;
import net.minecraft.class_437;
import net.minecraft.class_442;
import net.minecraft.class_500;
import net.minecraft.class_526;

public class PhantomMainMenu extends class_437 {
   public static final Color ACCENT = new Color(255, 140, 40);
   public static boolean phantomScreen = true;
   public static boolean fire = false;
   public static boolean sakura = false;
   public static boolean calma = true;
   public static boolean crimson = false;
   private static boolean broken = false;
   private float hoverTheme;
   private float hoverScreen;
   private float openAnimation;
   private final float[] hb = new float[7];
   private boolean openTheme = false;
   private static l_lllI_i11IiIl_i11iiii1 gif;
   private static boolean gifTried;

   public PhantomMainMenu() {
      super(class_2561.method_30163("LuxVisuals"));
   }

   private static Color acc(int var0) {
      return new Color(ACCENT.getRed(), ACCENT.getGreen(), ACCENT.getBlue(), var0);
   }

   public static String themeName() {
      if (fire) {
         return "fire";
      } else {
         return sakura ? "sakura" : "crimson";
      }
   }

   public static void drawBackground(class_332 var0, float var1, float var2) {
      if (!gifTried) {
         gifTried = true;

         try {
            gif = new l_lllI_i11IiIl_i11iiii1(PX.id("images/crimson.gif"), -2.0F, -2.0F, var1 + 4.0F, var2 + 4.0F);
         } catch (Throwable var6) {
            gif = null;
         }
      }

      boolean var3 = false;
      if (gif != null) {
         try {
            gif.set(-2.0F, -2.0F, var1 + 4.0F, var2 + 4.0F);
            gif.update(var0);
            gif.render(var0);
            var3 = true;
         } catch (Throwable var5) {
            gif = null;
         }
      }

      if (!var3) {
         PX.tex(var0, PX.id("images/crimson.png"), -2.0F, -2.0F, var1 + 4.0F, var2 + 4.0F, 0.0F, new Color(-1));
      }

      PX.blur(var0, -2.0F, -2.0F, var1 + 4.0F, var2 + 4.0F, 3.0F, 0.0F, new Color(-1, true));
      PX.rr(var0, -2.0F, -2.0F, var1 + 4.0F, var2 + 4.0F, 0.0F, new Color(0, 0, 0, 90));
   }

   protected void method_25426() {
      phantomScreen = true;
   }

   private float bx() {
      return (PX.sw() - PX.w(0, "A", 20.0F)) / 2.0F - 20.0F - 3.0F - 25.0F - 50.0F;
   }

   private float by() {
      return (PX.sh() - PX.w(0, "A", 20.0F)) / 2.0F;
   }

   private float[] rectOf(int var1) {
      float var2 = this.bx();
      float var3 = this.by();
      if (var1 == 2) {
         return new float[]{-1000.0F, -1000.0F, 0.0F, 0.0F};
      } else {
         return var1 < 2 ? new float[]{var2, var3 - 32.0F + (float)var1 * 27.0F, 244.0F, 24.0F} : new float[]{var2 + 65.0F + (float)(var1 - 3) * 27.0F, var3 + 45.0F, 24.0F, 24.0F};
      }
   }

   private void updateAnimations(int var1, int var2) {
      float var3 = PX.sw();
      this.hoverTheme = (float)PX.lerp((double)this.hoverTheme, PX.hover((double)var1, (double)var2, var3 - 24.0F, 12.0F, 14.0F, 14.0F) ? (double)1.0F : (double)0.0F, (double)8.0F);
      this.hoverScreen = (float)PX.lerp((double)this.hoverScreen, PX.hover((double)var1, (double)var2, var3 - 24.0F - 14.0F - 3.0F, 12.0F, 14.0F, 14.0F) ? (double)1.0F : (double)0.0F, (double)8.0F);

      for(int var4 = 0; var4 < 7; ++var4) {
         float[] var5 = this.rectOf(var4);
         this.hb[var4] = (float)PX.lerp((double)this.hb[var4], PX.hover((double)var1, (double)var2, var5[0], var5[1], var5[2], var5[3]) ? (double)1.0F : (double)0.0F, (double)8.0F);
      }

   }

   public void method_25420(class_332 var1, int var2, int var3, float var4) {
   }

   public void method_25394(class_332 var1, int var2, int var3, float var4) {
      if (!broken) {
         try {
            this.renderMenu(var1, var2, var3, var4);
         } catch (Throwable var7) {
            broken = true;
            phantomScreen = false;
            var7.printStackTrace();
            class_310 var6 = PX.mc();
            if (var6 != null) {
               var6.method_1507(new class_442());
            }
         }

      }
   }

   private void renderMenu(class_332 var1, int var2, int var3, float var4) {
      super.method_25394(var1, var2, var3, var4);
      float var5 = PX.sw();
      float var6 = PX.sh();
      this.openAnimation = PX.fastAnim(this.openAnimation, 1.0F, 12.0F);
      this.updateAnimations(var2, var3);
      String var7 = PX.mc().method_1548().method_1676();
      String var8 = "User";
      Color var9 = new Color(4737360);
      Color var10 = new Color(11119540);
      drawBackground(var1, var5, var6);
      PX.rr(var1, 12.0F, 12.0F, PX.w(1, var7, 6.0F) + 6.0F + 8.0F + 3.0F + 2.0F, 14.0F, 3.0F, new Color(-1087360707, true));
      PX.tex(var1, PX.id("images/steve.png"), 15.0F, 15.0F, 8.0F, 8.0F, 2.0F, new Color(-1));
      PX.text(var1, 1, var7, 6.0F, 25.0F, 15.5F, new Color(-1));
      PX.rr(var1, var5 - 24.0F - 14.0F - 3.0F, 12.0F, 14.0F, 14.0F, 3.0F, PX.lerpColor(new Color(-2145904096, true), new Color(-2144917455, true), this.hoverScreen));
      PX.text(var1, 0, "C", 6.0F, var5 - 24.0F - 14.0F - 3.0F + 3.0F, 15.5F, PX.lerpColor(var9, var10, this.hoverScreen));
      if (PX.hover((double)var2, (double)var3, var5 - 24.0F - 14.0F - 3.0F, 12.0F, 14.0F, 14.0F) && !this.openTheme) {
         PX.handCursor();
      }

      float var11 = PX.w(1, var8, 6.0F);
      PX.rr(var1, var5 - 24.0F - 14.0F - 3.0F - var11 - 3.0F - 6.0F - 8.0F - 3.0F - 1.0F, 12.0F, var11 + 8.0F + 3.0F + 6.0F, 14.0F, 3.0F, new Color(ACCENT.getRed(), ACCENT.getGreen(), ACCENT.getBlue(), 100));
      PX.text(var1, 1, var8, 6.0F, var5 - 24.0F - 14.0F - 14.0F - var11 + 3.0F - 1.0F, 15.5F, ACCENT);
      PX.text(var1, 0, "B", 6.0F, var5 - 24.0F - 14.0F - 14.0F - var11 - 3.0F - 6.0F + 3.0F, 15.5F, ACCENT);
      float var12 = (var5 - PX.w(0, "A", 20.0F)) / 2.0F;
      float var13 = this.by();
      float var14 = this.bx();
      PX.text(var1, 0, "A", 20.0F, var12 - 20.0F - 3.0F - 25.0F, var13 - 82.0F, ACCENT);
      PX.text(var1, 2, "LuxVisuals", 16.0F, var12 + 5.0F - 25.0F, var13 - 80.0F, new Color(-1));
      String[] var15 = new String[]{"Одиночная игра", "Сетевая игра"};
      String[] var16 = new String[]{"F", "E"};

      for(int var17 = 0; var17 < 2; ++var17) {
         float[] var18 = this.rectOf(var17);
         Color var19 = PX.lerpColor(acc(45), acc(105), this.hb[var17]);
         Color var20 = PX.lerpColor(acc(190), acc(255), this.hb[var17]);
         PX.rr(var1, var18[0], var18[1], var18[2], var18[3], 5.0F, var19);
         float var21 = PX.w(1, var15[var17], 7.5F);
         PX.text(var1, 1, var15[var17], 7.5F, var18[0] + (244.0F - var21) / 2.0F + 0.25F, var18[1] + 6.0F + 0.5F, var20);
         PX.text(var1, 0, var16[var17], 7.0F, var18[0] + (244.0F - var21) / 2.0F + 0.25F - 3.0F - 8.0F, var18[1] + 6.0F + 0.6F, var20);
         if (PX.hover((double)var2, (double)var3, var18[0], var18[1], var18[2], var18[3]) && !this.openTheme) {
            PX.handCursor();
         }
      }

      String[] var22 = new String[]{"H", "I", "J", "K"};

      for(int var23 = 3; var23 < 7; ++var23) {
         float[] var25 = this.rectOf(var23);
         PX.rr(var1, var25[0], var25[1], var25[2], var25[3], 5.0F, PX.lerpColor(acc(45), acc(105), this.hb[var23]));
         String var26 = var22[var23 - 3];
         PX.text(var1, 0, var26, 7.5F, var25[0] + (24.0F - PX.w(0, var26, 7.5F)) / 2.0F + 0.25F - 0.5F, var25[1] + 7.0F, PX.lerpColor(acc(190), acc(255), this.hb[var23]));
         if (PX.hover((double)var2, (double)var3, var25[0], var25[1], var25[2], var25[3]) && !this.openTheme) {
            PX.handCursor();
         }
      }

      if (this.openTheme) {
         this.renderThemePicker(var1, var5, var6);
      }

      float var24 = class_3532.method_15363(1.0F - this.openAnimation, 0.0F, 1.0F);
      if (var24 > 0.01F) {
         PX.rr(var1, 0.0F, 0.0F, var5, var6, 0.0F, new Color(0, 0, 0, (int)(var24 * 220.0F)));
      }

   }

   private void renderThemePicker(class_332 var1, float var2, float var3) {
      String var4 = "";
      if (fire) {
         var4 = "Fire";
      }

      if (sakura) {
         var4 = "Sakura";
      }

      if (calma) {
         var4 = "Calma";
      }

      if (crimson) {
         var4 = "Crimson";
      }

      float var5 = (var2 - 262.0F) / 2.0F;
      float var6 = (var3 - 165.0F) / 2.0F;
      PX.rr(var1, -2.0F, -2.0F, var2 + 4.0F, var3 + 4.0F, 0.0F, new Color(1493172224, true));
      PX.rr(var1, var5 + 10.0F, var6 + 6.0F, 262.0F, 165.0F, 6.0F, new Color(1579552));
      PX.text(var1, 0, "D", 13.0F, var5 + 10.0F + 11.0F, var6 + 6.0F + 11.0F, new Color(4803155));
      PX.text(var1, 1, "Выберите тему", 9.0F, var5 + 10.0F + 11.0F + 13.0F + 3.0F + 5.0F, var6 + 6.0F + 11.0F + 2.0F, new Color(-1));
      PX.text(var1, 2, "Выберите понравившуюся вам тему и нажмите на нее", 7.0F, var5 + 10.0F + 11.0F, var6 + 6.0F + 11.0F + 13.0F + 5.0F, new Color(4803155));
      PX.text(var1, 3, "X", 5.0F, var5 + 262.0F - 6.0F - 8.0F, var6 + 6.0F + 11.0F + 2.0F, new Color(-1));
      PX.text(var1, 2, "Активная: ", 6.0F, var5 + 10.0F + 11.0F, var6 + 165.0F - 6.0F - 5.0F, new Color(4803155));
      PX.text(var1, 2, " " + var4, 6.0F, var5 + 10.0F + 11.0F + PX.w(2, "Активная: ", 6.0F), var6 + 165.0F - 6.0F - 5.0F, new Color(-1));
      String[] var7 = new String[]{"Fire", "Sakura", "Calma", "Crimson"};
      String[] var8 = new String[]{"fire", "sakura", "calma", "crimson"};
      boolean[] var9 = new boolean[]{fire, sakura, calma, crimson};
      float var10 = var6 + 6.0F + 11.0F + 13.0F + 5.0F + 7.0F + 5.0F;

      for(int var11 = 0; var11 < 4; ++var11) {
         float var12 = var10 + (float)var11 * 27.0F;
         PX.tex(var1, PX.id("images/" + var8[var11] + ".png"), var5 + 10.0F + 11.0F, var12, 239.0F, 24.0F, 5.0F, new Color(-1));
         PX.rr(var1, var5 + 10.0F + 11.0F, var12, 239.0F, 24.0F, 5.0F, new Color(-652731872, true));
         PX.text(var1, 1, var7[var11], 8.0F, var5 + 10.0F + 11.0F + 6.0F, var12 + 6.0F, new Color(-1));
         if (var9[var11]) {
            PX.rr(var1, var5 + 262.0F - 6.0F - 12.0F, var12 + 9.0F, 6.0F, 6.0F, 2.0F, new Color(4783872));
         }
      }

   }

   private void pickTheme(int var1) {
      fire = var1 == 0;
      sakura = var1 == 1;
      calma = var1 == 2;
      crimson = var1 == 3;
   }

   public boolean method_25402(double var1, double var3, int var5) {
      float var6 = PX.sw();
      float var7 = PX.sh();
      if (!this.openTheme) {
         if (var5 == 0) {
            label49:
            for(int var8 = 0; var8 < 7; ++var8) {
               float[] var9 = this.rectOf(var8);
               if (PX.hover(var1, var3, var9[0], var9[1], var9[2], var9[3])) {
                  class_310 var10 = PX.mc();

                  try {
                     switch (var8) {
                        case 0:
                           var10.method_1507(new class_526(this));
                           break label49;
                        case 1:
                           var10.method_1507(new class_500(this));
                        case 2:
                        default:
                           break label49;
                        case 3:
                           var10.method_1507(new class_429(this, var10.field_1690));
                           break label49;
                        case 4:
                           var10.method_1507(new class_426(this, var10.field_1690, var10.method_1526()));
                           break label49;
                        case 5:
                           var10.method_1507(new li__IIIi_IIi1I_11l__il_lil_il_());
                           break label49;
                        case 6:
                           var10.method_1592();
                     }
                  } catch (Throwable var12) {
                     var12.printStackTrace();
                  }
                  break;
               }
            }

            if (PX.hover(var1, var3, var6 - 24.0F - 14.0F - 3.0F, 12.0F, 14.0F, 14.0F)) {
               phantomScreen = false;
               PX.mc().method_1507(new class_442());
               return true;
            }
         }
      } else if (var5 == 0) {
         float var13 = (var6 - 262.0F) / 2.0F;
         float var14 = (var7 - 165.0F) / 2.0F;
         if (PX.hover(var1, var3, var13 + 262.0F - 6.0F - 8.0F, var14 + 6.0F + 11.0F + 2.0F, 8.0F, 8.0F)) {
            this.openTheme = false;
         }

         float var15 = var14 + 6.0F + 11.0F + 13.0F + 5.0F + 7.0F + 5.0F;

         for(int var11 = 0; var11 < 4; ++var11) {
            if (PX.hover(var1, var3, var13 + 10.0F + 11.0F, var15 + (float)var11 * 27.0F, 239.0F, 24.0F)) {
               this.pickTheme(var11);
            }
         }
      }

      return super.method_25402(var1, var3, var5);
   }

   public boolean method_25404(int var1, int var2, int var3) {
      if (this.openTheme && var1 == 256) {
         this.openTheme = false;
      }

      return super.method_25404(var1, var2, var3);
   }
}
