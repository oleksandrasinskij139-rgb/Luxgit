package dev.luxvisuals.menu;

import java.awt.Color;
import net.minecraft.class_332;
import org.joml.Quaternionf;

public class PhantomLoading {
   private static final String[] MESSAGES = new String[]{"Загружаем ассеты", "Загружаем библиотеки", "Загружаем текстуры", "Загружаем шейдеры", "Загружаем шрифты", "Подготовка к запуску"};
   private int msgIndex = 0;
   private int prevMsgIndex = -1;
   private long lastSwitch = PX.now();

   public void render(class_332 var1, float var2, float var3) {
      long var4 = PX.now();
      if (var4 - this.lastSwitch > 1200L) {
         this.prevMsgIndex = this.msgIndex;
         this.msgIndex = (this.msgIndex + 1) % MESSAGES.length;
         this.lastSwitch = var4;
      }

      float var6 = Math.min(1.0F, (float)(var4 - this.lastSwitch) / 300.0F);
      float var7 = (float)Math.sin((double)var6 * Math.PI / (double)2.0F);
      float var8 = Math.min(1.0F, var3 * 6.0F);
      float var9 = (float)Math.sin((double)var8 * Math.PI / (double)2.0F);
      int var10 = var1.method_51421();
      int var11 = var1.method_51443();
      PX.rect(var1, -1.0F, -1.0F, (float)(var10 + 2), (float)(var11 + 2), new Color(0, 0, 0, 255));
      PhantomMainMenu.drawBackground(var1, (float)var10, (float)var11);
      float var12 = 7.0F;
      float var13 = (float)var11 / 2.0F - var12 / 2.0F + 10.0F;
      float var14 = var13 + var12 + 14.0F;
      float var15 = 28.0F;
      float var16 = PX.w(0, "A", var15);
      PX.text(var1, 0, "A", var15, ((float)var10 - var16) / 2.0F, var13 - var15 - 12.0F, PhantomMainMenu.ACCENT);
      new Color(170, 170, 170, 255);
      if (this.prevMsgIndex >= 0 && var7 < 1.0F) {
         String var18 = MESSAGES[this.prevMsgIndex];
         PX.text(var1, 2, var18, var12, ((float)var10 - PX.w(2, var18, var12)) / 2.0F, var13, new Color(170, 170, 170, (int)(255.0F * (1.0F - var7))));
      }

      String var23 = MESSAGES[this.msgIndex];
      int var19 = this.prevMsgIndex < 0 ? 255 : (int)(255.0F * var7);
      PX.text(var1, 2, var23, var12, ((float)var10 - PX.w(2, var23, var12)) / 2.0F, var13, new Color(170, 170, 170, var19));
      float var20 = (float)var4 / 1000.0F * 240.0F % 360.0F;

      try {
         this.spinner(var1, var10, var20, var14);
      } catch (Exception var22) {
      }

      int var21 = (int)(255.0F * (1.0F - var9));
      if (var21 > 0) {
         PX.rr(var1, -1.0F, -1.0F, (float)(var10 + 2), (float)(var11 + 2), 0.0F, new Color(0, 0, 0, var21));
      }

   }

   private void spinner(class_332 var1, int var2, float var3, float var4) {
      int var5 = var2 / 2;
      int var6 = (int)var4;
      byte var7 = 5;
      byte var8 = 3;
      byte var9 = 48;
      float var10 = var3 - 135.0F;

      for(int var11 = 0; var11 < var9; ++var11) {
         float var12 = (float)var11 / (float)var9;
         float var13 = (float)Math.toRadians((double)(var10 + (var12 + 0.5F / (float)var9) * 270.0F));
         int var14 = (int)(255.0F * (1.0F - var12 * 0.6F));
         var1.method_51448().method_22903();
         var1.method_51448().method_46416((float)var5, (float)var6, 0.0F);
         var1.method_51448().method_22907((new Quaternionf()).rotationZ(var13));
         var1.method_51448().method_46416((float)(-var5), (float)(-var6), 0.0F);
         PX.rr(var1, (float)var5 - 0.5F, (float)(var6 - var7), 1.0F, (float)(var7 - var8), 0.0F, new Color(255, 255, 255, var14));
         var1.method_51448().method_22909();
      }

   }
}
