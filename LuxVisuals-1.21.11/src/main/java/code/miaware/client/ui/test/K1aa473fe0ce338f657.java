package code.miaware.client.ui.test;

import code.miaware.api.utils.render.fonts.K896065ea0a2bba4f47;
import java.awt.Color;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_4587;

public class K1aa473fe0ce338f657 extends class_437 {
   private float scrollOffset = 0.0F;
   private float maxScroll = 0.0F;

   public K1aa473fe0ce338f657() {
      super(class_2561.method_43470("Icon Test"));
   }

   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
      this.scrollOffset -= (float)verticalAmount * 20.0F;
      if (this.scrollOffset < 0.0F) {
         this.scrollOffset = 0.0F;
      }

      if (this.scrollOffset > this.maxScroll) {
         this.scrollOffset = this.maxScroll;
      }

      return true;
   }

   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
      super.method_25420(context, mouseX, mouseY, delta);
      class_4587 matrices = context.method_51448();
      matrices.method_22903();
      matrices.method_46416(0.0F, -this.scrollOffset, 0.0F);
      float startX = 25.0F;
      float startY = 50.0F;
      float iconSize = 18.0F;
      float spacing = 30.0F;
      String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      int col = 0;
      int row = 0;
      int maxCols = 13;
      K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, "ICONS (Fonts.ICONS)", 10.0F, 3.0F, 8.0F, Color.YELLOW);

      for(int i = 0; i < alphabet.length(); ++i) {
         char letter = alphabet.charAt(i);
         String letterStr = String.valueOf(letter);
         float x = startX + (float)col * spacing;
         float y = startY + (float)row * spacing;
         K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, letterStr, x + 2.0F, y - 12.0F, 6.0F, Color.YELLOW);
         K896065ea0a2bba4f47.ICONS.drawText(matrices, letterStr, x, y, iconSize, Color.WHITE);
         ++col;
         if (col >= maxCols) {
            col = 0;
            ++row;
         }
      }

      float nurikY = startY + (float)(row + 1) * spacing + 5.0F;
      K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, "ICONS_NURIK (Fonts.ICONS_NURIK)", 10.0F, nurikY - 25.0F, 8.0F, Color.CYAN);
      col = 0;
      row = 0;

      for(int i = 0; i < alphabet.length(); ++i) {
         char letter = alphabet.charAt(i);
         String letterStr = String.valueOf(letter);
         float x = startX + (float)col * spacing;
         float y = nurikY + (float)row * spacing;
         K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, letterStr, x + 2.0F, y - 12.0F, 6.0F, Color.CYAN);
         K896065ea0a2bba4f47.ICONS_NURIK.drawText(matrices, letterStr, x, y, iconSize, Color.WHITE);
         ++col;
         if (col >= maxCols) {
            col = 0;
            ++row;
         }
      }

      float deshuxY = nurikY + (float)(row + 1) * spacing + 5.0F;
      K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, "ICONS_DESHUX (Fonts.ICONS_DESHUX) - lowercase only!", 10.0F, deshuxY - 25.0F, 8.0F, Color.MAGENTA);
      col = 0;
      row = 0;
      String lowercaseAlphabet = "abcdefghijklmnopqrstuvwxyz";

      for(int i = 0; i < lowercaseAlphabet.length(); ++i) {
         char letter = lowercaseAlphabet.charAt(i);
         String letterStr = String.valueOf(letter);
         float x = startX + (float)col * spacing;
         float y = deshuxY + (float)row * spacing;
         K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, letterStr, x + 2.0F, y - 12.0F, 6.0F, Color.MAGENTA);
         K896065ea0a2bba4f47.ICONS_DESHUX.drawText(matrices, letterStr, x, y, iconSize, Color.WHITE);
         ++col;
         if (col >= maxCols) {
            col = 0;
            ++row;
         }
      }

      float iconY = deshuxY + (float)(row + 1) * spacing + 5.0F;
      K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, "ICON (Fonts.ICON)", 10.0F, iconY - 25.0F, 8.0F, Color.GREEN);
      col = 0;
      row = 0;

      for(int i = 0; i < alphabet.length(); ++i) {
         char letter = alphabet.charAt(i);
         String letterStr = String.valueOf(letter);
         float x = startX + (float)col * spacing;
         float y = iconY + (float)row * spacing;
         K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, letterStr, x + 2.0F, y - 12.0F, 6.0F, Color.GREEN);
         K896065ea0a2bba4f47.ICON.drawText(matrices, letterStr, x, y, iconSize, Color.WHITE);
         ++col;
         if (col >= maxCols) {
            col = 0;
            ++row;
         }
      }

      float icoesY = iconY + (float)(row + 1) * spacing + 5.0F;
      K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, "ICOES (Fonts.ICOES)", 10.0F, icoesY - 25.0F, 8.0F, Color.ORANGE);
      col = 0;
      row = 0;

      for(int i = 0; i < lowercaseAlphabet.length(); ++i) {
         char letter = lowercaseAlphabet.charAt(i);
         String letterStr = String.valueOf(letter);
         float x = startX + (float)col * spacing;
         float y = icoesY + (float)row * spacing;
         K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, letterStr, x + 2.0F, y - 12.0F, 6.0F, Color.ORANGE);
         K896065ea0a2bba4f47.ICOES.drawText(matrices, letterStr, x, y, iconSize, Color.WHITE);
         ++col;
         if (col >= maxCols) {
            col = 0;
            ++row;
         }
      }

      float icolistY = icoesY + (float)(row + 1) * spacing + 5.0F;
      K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, "ICOLIST (Fonts.ICOLIST)", 10.0F, icolistY - 25.0F, 8.0F, new Color(255, 100, 255));
      col = 0;
      row = 0;

      for(int i = 0; i < alphabet.length(); ++i) {
         char letter = alphabet.charAt(i);
         String letterStr = String.valueOf(letter);
         float x = startX + (float)col * spacing;
         float y = icolistY + (float)row * spacing;
         K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, letterStr, x + 2.0F, y - 12.0F, 6.0F, new Color(255, 100, 255));
         K896065ea0a2bba4f47.ICOLIST.drawText(matrices, letterStr, x, y, iconSize, Color.WHITE);
         ++col;
         if (col >= maxCols) {
            col = 0;
            ++row;
         }
      }

      float clickguiY = icolistY + (float)(row + 1) * spacing + 5.0F;
      K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, "CLICKGUI (Fonts.CLICKGUI)", 10.0F, clickguiY - 25.0F, 8.0F, new Color(100, 255, 255));
      col = 0;
      row = 0;

      for(int i = 0; i < alphabet.length(); ++i) {
         char letter = alphabet.charAt(i);
         String letterStr = String.valueOf(letter);
         float x = startX + (float)col * spacing;
         float y = clickguiY + (float)row * spacing;
         K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, letterStr, x + 2.0F, y - 12.0F, 6.0F, new Color(100, 255, 255));
         K896065ea0a2bba4f47.CLICKGUI.drawText(matrices, letterStr, x, y, iconSize, Color.WHITE);
         ++col;
         if (col >= maxCols) {
            col = 0;
            ++row;
         }
      }

      float danqY = clickguiY + (float)(row + 1) * spacing + 5.0F;
      K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, "ICON_DANQ (Fonts.ICON_DANQ)", 10.0F, danqY - 25.0F, 8.0F, new Color(255, 255, 100));
      col = 0;
      row = 0;

      for(int i = 0; i < alphabet.length(); ++i) {
         char letter = alphabet.charAt(i);
         String letterStr = String.valueOf(letter);
         float x = startX + (float)col * spacing;
         float y = danqY + (float)row * spacing;
         K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, letterStr, x + 2.0F, y - 12.0F, 6.0F, new Color(255, 255, 100));
         K896065ea0a2bba4f47.ICON_DANQ.drawText(matrices, letterStr, x, y, iconSize, Color.WHITE);
         ++col;
         if (col >= maxCols) {
            col = 0;
            ++row;
         }
      }

      float icotestY = danqY + (float)(row + 1) * spacing + 5.0F;
      K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, "ICOTEST (Fonts.ICOTEST)", 10.0F, icotestY - 25.0F, 8.0F, new Color(100, 255, 100));
      col = 0;
      row = 0;

      for(int i = 0; i < alphabet.length(); ++i) {
         char letter = alphabet.charAt(i);
         String letterStr = String.valueOf(letter);
         float x = startX + (float)col * spacing;
         float y = icotestY + (float)row * spacing;
         K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, letterStr, x + 2.0F, y - 12.0F, 6.0F, new Color(100, 255, 100));
         K896065ea0a2bba4f47.ICOTEST.drawText(matrices, letterStr, x, y, iconSize, Color.WHITE);
         ++col;
         if (col >= maxCols) {
            col = 0;
            ++row;
         }
      }

      float nurY = icotestY + (float)(row + 1) * spacing + 5.0F;
      K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, "NUR (Fonts.NUR)", 10.0F, nurY - 25.0F, 8.0F, new Color(255, 150, 150));
      col = 0;
      row = 0;

      for(int i = 0; i < alphabet.length(); ++i) {
         char letter = alphabet.charAt(i);
         String letterStr = String.valueOf(letter);
         float x = startX + (float)col * spacing;
         float y = nurY + (float)row * spacing;
         K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, letterStr, x + 2.0F, y - 12.0F, 6.0F, new Color(255, 150, 150));
         K896065ea0a2bba4f47.NUR.drawText(matrices, letterStr, x, y, iconSize, Color.WHITE);
         ++col;
         if (col >= maxCols) {
            col = 0;
            ++row;
         }
      }

      float roleY = nurY + (float)(row + 1) * spacing + 5.0F;
      K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, "ROLE_ICONS (Fonts.ROLE_ICONS_BITMAP) - Custom Bitmap Icons", 10.0F, roleY - 25.0F, 8.0F, new Color(255, 100, 200));
      col = 0;
      row = 0;
      String roleChars = "ABCDEFGHI";

      for(int i = 0; i < roleChars.length(); ++i) {
         char letter = roleChars.charAt(i);
         String letterStr = String.valueOf(letter);
         float x = startX + (float)col * spacing;
         float y = roleY + (float)row * spacing;
         K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, letterStr, x + 2.0F, y - 12.0F, 6.0F, new Color(255, 100, 200));
         K896065ea0a2bba4f47.ROLE_ICONS_BITMAP.drawIcon(matrices, letter, x, y, iconSize, Color.WHITE);
         ++col;
         if (col >= maxCols) {
            col = 0;
            ++row;
         }
      }

      K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, "11 icon fonts available - scroll down to see all", 10.0F, (float)context.method_51443() - 20.0F + this.scrollOffset, 7.0F, Color.GRAY);
      this.maxScroll = roleY + (float)(row + 1) * spacing - (float)context.method_51443() + 50.0F;
      if (this.maxScroll < 0.0F) {
         this.maxScroll = 0.0F;
      }

      matrices.method_22909();
   }

   public boolean method_25421() {
      return false;
   }
}
