package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.PrintStream;
import javax.imageio.ImageIO;
import lombok.Generated;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_2960;
import net.minecraft.class_1011.class_1012;

@ModuleRegister(
   name = "Cape",
   category = Kf76f042931183af5ba.RENDER,
   description = "Отображает кастомный плащ"
)
public class Kc4e4e0706855d7b594 extends Kab8a8c478f7d0077cd {
   private static final Kc4e4e0706855d7b594 instance = new Kc4e4e0706855d7b594();
   private class_2960 customCapeTexture = null;
   private final Kbd66519397e9a89967 capeChoice = (new Kbd66519397e9a89967("Плащ")).value("Плащ 1").values("Плащ 1", "Плащ 2", "Плащ 3", "Плащ 4", "Плащ 5", "Плащ 6");
   private String lastLoadedCape = "";

   public Kc4e4e0706855d7b594() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.capeChoice});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 updateListener = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (this.customCapeTexture != null) {
            String currentCape = (String)this.capeChoice.getValue();
            if (!currentCape.equals(this.lastLoadedCape)) {
               this.Kbcf68ef651df34e330();
            }
         }

      }));
      this.addEvents(new K8d7165024ff567f5e7[]{updateListener});
   }

   public void onEnable() {
      this.Kbcf68ef651df34e330();
   }

   public class_2960 getCape() {
      if (this.customCapeTexture == null) {
         this.Kbcf68ef651df34e330();
      }

      return this.customCapeTexture;
   }

   private class_1011 Kf5f5fed5a61d01188b(class_1011 logo) {
      int scale = 32;
      int capeTextureWidth = 64 * scale;
      int capeTextureHeight = 32 * scale;
      class_1011 cape = new class_1011(class_1012.field_4997, capeTextureWidth, capeTextureHeight, true);
      int capeWidth = 10 * scale;
      int capeHeight = 16 * scale;
      int capeX = 1 * scale;
      int capeY = 1 * scale;
      int newWidth = capeWidth;
      int newHeight = capeHeight;

      for(int y = 0; y < newHeight; ++y) {
         for(int x = 0; x < newWidth; ++x) {
            float srcXFloat = (float)x / (float)newWidth * (float)logo.method_4307();
            float srcYFloat = (float)y / (float)newHeight * (float)logo.method_4323();
            int color = this.K185656b3442095b872(logo, srcXFloat, srcYFloat);
            int destX = capeX + x;
            int destY = capeY + y;
            if (destX >= 0 && destX < capeTextureWidth && destY >= 0 && destY < capeTextureHeight) {
               cape.method_61941(destX, destY, color);
            }
         }
      }

      String text = "LUXVISUALS THE BEST";
      int textScale = scale / 4;
      int textWidth = this.K59135e412f3a572513(text, textScale);
      int centerX = 12 * scale + (capeWidth - textWidth) / 2;
      int bottomY = capeY + capeHeight - 10 * (textScale / 4);
      this.K5767663c1aae4b0c8a(cape, text, centerX, bottomY, textScale);
      System.out.println("[Cape] Плащ создан из логотипа: " + newWidth + "x" + newHeight + " пикселей (растянут на весь плащ, разрешение: " + capeTextureWidth + "x" + capeTextureHeight + ")");
      return cape;
   }

   private void K5767663c1aae4b0c8a(class_1011 cape, String text, int x, int y, int scale) {
      int[][] font = this.Kb44b769ca42f180ce4();
      int charWidth = 5 * (scale / 4);
      int charHeight = 7 * (scale / 4);
      int spacing = 1 * (scale / 4);
      int currentX = x;

      for(char c : text.toCharArray()) {
         if (c == ' ') {
            currentX += charWidth + spacing;
         } else {
            int[][] charPixels = this.K51d0cf4022ec3440a5(c, font);
            if (charPixels != null) {
               for(int py = 0; py < 7; ++py) {
                  for(int px = 0; px < 5; ++px) {
                     if (charPixels[py][px] == 1) {
                        for(int sy = 0; sy < scale / 4; ++sy) {
                           for(int sx = 0; sx < scale / 4; ++sx) {
                              int drawX = currentX + px * (scale / 4) + sx;
                              int drawY = y + py * (scale / 4) + sy;
                              if (drawX >= 0 && drawX < cape.method_4307() && drawY >= 0 && drawY < cape.method_4323()) {
                                 cape.method_61941(drawX, drawY, -1);
                              }
                           }
                        }
                     }
                  }
               }

               currentX += charWidth + spacing;
            }
         }
      }

   }

   private int K59135e412f3a572513(String text, int scale) {
      int charWidth = 5 * (scale / 4);
      int spacing = 1 * (scale / 4);
      int width = 0;

      for(char c : text.toCharArray()) {
         if (c == ' ') {
            width += charWidth + spacing;
         } else {
            width += charWidth + spacing;
         }
      }

      return width - spacing;
   }

   private int[][] K51d0cf4022ec3440a5(char c, int[][] font) {
      switch (Character.toUpperCase(c)) {
         case '3' -> {
            return new int[][]{{1, 1, 1, 1, 0}, {0, 0, 0, 0, 1}, {0, 0, 0, 1, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}, {1, 1, 1, 1, 0}};
         }
         case 'B' -> {
            return new int[][]{{1, 1, 1, 1, 0}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 0}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 0}};
         }
         case 'D' -> {
            return new int[][]{{1, 1, 1, 0, 0}, {1, 0, 0, 1, 0}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 1, 0}, {1, 1, 1, 0, 0}};
         }
         case 'E' -> {
            return new int[][]{{1, 1, 1, 1, 1}, {1, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {1, 1, 1, 1, 1}};
         }
         case 'H' -> {
            return new int[][]{{1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}};
         }
         case 'K' -> {
            return new int[][]{{1, 0, 0, 1, 0}, {1, 0, 1, 0, 0}, {1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 0, 1, 0, 0}, {1, 0, 0, 1, 0}, {1, 0, 0, 0, 1}};
         }
         case 'S' -> {
            return new int[][]{{0, 1, 1, 1, 1}, {1, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 0, 1}, {0, 0, 0, 0, 1}, {1, 1, 1, 1, 0}};
         }
         case 'T' -> {
            return new int[][]{{1, 1, 1, 1, 1}, {0, 0, 1, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 1, 0, 0}};
         }
         default -> {
            return null;
         }
      }
   }

   private int[][] Kb44b769ca42f180ce4() {
      return new int[0][0];
   }

   private int K185656b3442095b872(class_1011 image, float x, float y) {
      int x1 = (int)Math.floor((double)x);
      int y1 = (int)Math.floor((double)y);
      int x2 = Math.min(x1 + 1, image.method_4307() - 1);
      int y2 = Math.min(y1 + 1, image.method_4323() - 1);
      x1 = Math.max(0, Math.min(x1, image.method_4307() - 1));
      y1 = Math.max(0, Math.min(y1, image.method_4323() - 1));
      float fx = x - (float)x1;
      float fy = y - (float)y1;
      int c11 = image.method_4315(x1, y1);
      int c21 = image.method_4315(x2, y1);
      int c12 = image.method_4315(x1, y2);
      int c22 = image.method_4315(x2, y2);
      int a = this.Kc0c586c70094deafee(c11, c21, c12, c22, fx, fy, 24);
      int r = this.Kc0c586c70094deafee(c11, c21, c12, c22, fx, fy, 16);
      int g = this.Kc0c586c70094deafee(c11, c21, c12, c22, fx, fy, 8);
      int b = this.Kc0c586c70094deafee(c11, c21, c12, c22, fx, fy, 0);
      return a << 24 | r << 16 | g << 8 | b;
   }

   private int Kc0c586c70094deafee(int c11, int c21, int c12, int c22, float fx, float fy, int shift) {
      int v11 = c11 >> shift & 255;
      int v21 = c21 >> shift & 255;
      int v12 = c12 >> shift & 255;
      int v22 = c22 >> shift & 255;
      float v1 = (float)v11 * (1.0F - fx) + (float)v21 * fx;
      float v2 = (float)v12 * (1.0F - fx) + (float)v22 * fx;
      float v = v1 * (1.0F - fy) + v2 * fy;
      return Math.max(0, Math.min(255, (int)v));
   }

   private void Kbcf68ef651df34e330() {
      try {
         String var10000;
         switch ((String)this.capeChoice.getValue()) {
            case "Плащ 2" -> var10000 = "2";
            case "Плащ 3" -> var10000 = "3";
            case "Плащ 4" -> var10000 = "4";
            case "Плащ 5" -> var10000 = "5";
            case "Плащ 6" -> var10000 = "6";
            default -> var10000 = "1";
         }

         String capeNumber = var10000;
         resourcePath = "/assets/miaware/textures/capes/cape" + capeNumber + ".jpg";
         InputStream is = this.getClass().getResourceAsStream(resourcePath);
         if (is == null) {
            System.out.println("[Cape] Не найден файл плаща: " + resourcePath);
            return;
         }

         System.out.println("[Cape] Загружаю " + (String)this.capeChoice.getValue() + " из ресурсов");
         byte[] signature = new byte[4];
         is.read(signature);
         is.close();
         boolean isPNG = signature[0] == -119 && signature[1] == 80 && signature[2] == 78 && signature[3] == 71;
         boolean isJPG = signature[0] == -1 && signature[1] == -40;
         if (!isPNG && !isJPG) {
            System.out.println("[Cape] Неподдерживаемый формат файла! Используйте PNG или JPG.");
            return;
         }

         class_1011 logoImage;
         if (!isJPG) {
            is = this.getClass().getResourceAsStream(resourcePath);
            logoImage = class_1011.method_4309(is);
            is.close();
         } else {
            System.out.println("[Cape] Обнаружен JPG файл, конвертирую в PNG...");
            is = this.getClass().getResourceAsStream(resourcePath);
            BufferedImage bufferedImage = ImageIO.read(is);
            is.close();
            if (bufferedImage == null) {
               System.out.println("[Cape] Не удалось загрузить JPG файл!");
               return;
            }

            logoImage = new class_1011(class_1012.field_4997, bufferedImage.getWidth(), bufferedImage.getHeight(), false);

            for(int y = 0; y < bufferedImage.getHeight(); ++y) {
               for(int x = 0; x < bufferedImage.getWidth(); ++x) {
                  int argb = bufferedImage.getRGB(x, y);
                  int a = argb >> 24 & 255;
                  int r = argb >> 16 & 255;
                  int g = argb >> 8 & 255;
                  int b = argb & 255;
                  int abgr = a << 24 | b << 16 | g << 8 | r;
                  logoImage.method_61941(x, y, abgr);
               }
            }

            System.out.println("[Cape] JPG успешно конвертирован!");
         }

         PrintStream var23 = System.out;
         int var10001 = logoImage.method_4307();
         var23.println("[Cape] Логотип загружен: " + var10001 + "x" + logoImage.method_4323() + " пикселей");
         class_1011 capeImage = this.Kf5f5fed5a61d01188b(logoImage);
         mc.execute(() -> {
            try {
               class_1043 texture = new class_1043(capeImage);
               class_2960 id = class_2960.method_60655("miaware", "custom_cape");
               mc.method_1531().method_4616(id, texture);
               this.customCapeTexture = id;
               this.lastLoadedCape = (String)this.capeChoice.getValue();
               System.out.println("[Cape] Плащ успешно создан и загружен!");
            } catch (Exception e) {
               System.out.println("[Cape] Ошибка регистрации текстуры:");
               e.printStackTrace();
            }

         });
         logoImage.close();
      } catch (Exception e) {
         System.out.println("[Cape] Ошибка загрузки плаща:");
         e.printStackTrace();
      }

   }

   public static boolean isCapeEnabled() {
      return instance != null && instance.isEnabled();
   }

   @Generated
   public static Kc4e4e0706855d7b594 getInstance() {
      return instance;
   }
}
