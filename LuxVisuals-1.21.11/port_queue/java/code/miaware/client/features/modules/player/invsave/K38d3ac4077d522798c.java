package code.miaware.client.features.modules.player.invsave;

import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.client.features.modules.player.Ke3c45c7c5ab01f24df;
import code.miaware.client.ui.clickgui.K64dfc1330d745b4d7f;
import code.miaware.client.ui.clickgui.K756e30c0738d06c926;
import code.miaware.client.ui.clickgui.Kc517862407c2394736;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_124;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1921;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_465;
import net.minecraft.class_490;

public final class K38d3ac4077d522798c implements AutoCloseable {
   private static final class_2960 FRAME = class_2960.method_60656("textures/gui/container/inventory.png");
   private static final int TAB_WIDTH = 34;
   private static final int TAB_HEIGHT = 16;
   private final class_310 mc = class_310.method_1551();
   private final Ke3c45c7c5ab01f24df module = Ke3c45c7c5ab01f24df.getInstance();
   private final class_465<?> screen;
   private final K402a602b5f2f2b781f ghosts = new K402a602b5f2f2b781f();
   private final K64dfc1330d745b4d7f canvas = new K64dfc1330d745b4d7f();
   private final class_1799 chest;
   private final class_342 name;
   private int bx;
   private int by;
   private int scroll;
   private boolean positioned;
   private boolean open;
   private long opened;
   private long noticeUntil;
   private String notice;
   private Kb829f755817df4f5cd guide;
   private K9e64dd2dd1a3d84dbb guideLayout;
   private int guideAge;
   private int missingScroll;
   private int sx;
   private int sy;
   private int sw;
   private int sh;
   private boolean sidebar;

   public K38d3ac4077d522798c(class_465<?> screen) {
      this.chest = new class_1799(class_1802.field_8106);
      this.notice = "";
      this.guideAge = Integer.MIN_VALUE;
      this.screen = screen;
      this.name = new class_342(this.mc.field_1772, 0, 0, 100, 18, class_2561.method_43470("Название инвентаря"));
      this.name.method_1880(24);
      this.name.method_1858(false);
      this.name.method_1868(-1184267);
      this.name.method_47404(class_2561.method_43470("Название инвентаря"));
   }

   public boolean supported() {
      return this.module.isEnabled() && this.screen.getClass() == class_490.class;
   }

   public boolean open() {
      return this.open && this.supported();
   }

   private K775957aef270c0236c K10fb8197ff9804d285() {
      return this.module.getStore();
   }

   private K26771f24bfecb7a1f9 K8734bc9e6fbdd4040d() {
      return K26771f24bfecb7a1f9.fit(this.screen.field_22789, this.screen.field_22790);
   }

   private void position(int x, int y, int width) {
      this.bx = Math.clamp((long)(x + width - 34), 3, Math.max(3, this.screen.field_22789 - 34 - 3));
      this.by = Math.clamp((long)(y - 16 + 4), 3, Math.max(3, this.screen.field_22790 - 16 - 1));
      this.positioned = true;
      this.K7cac5cb07d724dc5d5();
   }

   private void K7cac5cb07d724dc5d5() {
      K26771f24bfecb7a1f9 l = this.K8734bc9e6fbdd4040d();
      this.name.method_46421(l.x() + 24);
      this.name.method_46419(l.y() + 70);
      this.name.method_25358(Math.max(1, l.width() - 48));
   }

   private boolean tab(double x, double y) {
      return this.positioned && K26771f24bfecb7a1f9.inside(x, y, this.bx, this.by, 34, 16);
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta, int x, int y, int width) {
      if (this.supported() && this.mc.field_1724 != null) {
         this.position(x, y, width);
         K9e64dd2dd1a3d84dbb selected = this.K10fb8197ff9804d285().selected();
         this.sidebar = false;
         if (selected != null) {
            this.Kbd858745b3877b54a2(selected);
            this.Kbfb23d38f0890be9e9(context, x, y);
            this.ghosts.render(context, selected, this.K10fb8197ff9804d285().revision(), this.screen.method_17577().field_7761, this.mc.field_1724.method_31548(), x, y);
            if (!this.open) {
               this.K32ae2f12cbd3a0c4d1(context, selected, mouseX, mouseY, x, y);
            }
         }

         context.method_51448().method_22903();

         try {
            context.method_51448().method_46416(0.0F, 0.0F, 450.0F);
            this.K3014b398e4a9be1fa6(context, this.tab((double)mouseX, (double)mouseY));
            if (!this.open && selected != null) {
               this.Ka17f3a737358dec626(context, mouseX, mouseY, x, y, width);
            }

            if (this.open) {
               this.K76d286a76c9469a388(context, mouseX, mouseY, delta);
            } else if (this.tab((double)mouseX, (double)mouseY)) {
               context.method_51438(this.mc.field_1772, class_2561.method_43470("Менеджер инвентарей"), mouseX, mouseY);
            }
         } finally {
            context.method_51448().method_22909();
         }

      }
   }

   private void K3014b398e4a9be1fa6(class_332 c, boolean hovered) {
      int join = 12;
      c.method_25290(class_1921::method_62277, FRAME, this.bx, this.by, 0.0F, 0.0F, 30, 4, 256, 256);
      c.method_25290(class_1921::method_62277, FRAME, this.bx + 34 - 4, this.by, 172.0F, 0.0F, 4, 4, 256, 256);
      c.method_25290(class_1921::method_62277, FRAME, this.bx, this.by + 4, 0.0F, 4.0F, 4, join - 4, 256, 256);
      c.method_25290(class_1921::method_62277, FRAME, this.bx + 4, this.by + 4, 80.0F, 4.0F, 26, join - 4, 256, 256);
      c.method_25290(class_1921::method_62277, FRAME, this.bx + 34 - 4, this.by + 4, 172.0F, 4.0F, 4, join - 4, 256, 256);
      c.method_25290(class_1921::method_62277, FRAME, this.bx, this.by + join, 80.0F, 4.0F, 30, 4, 256, 256);
      c.method_25290(class_1921::method_62277, FRAME, this.bx + 34 - 4, this.by + join, 172.0F, 4.0F, 4, 4, 256, 256);
      if (hovered || this.open) {
         int tint = Kb18d549c13309af9a5.primary().getRGB() & 16777215 | 637534208;
         c.method_25294(this.bx + 4, this.by + 3, this.bx + 34 - 4, this.by + 16, tint);
      }

      c.method_51448().method_22903();

      try {
         c.method_51448().method_46416((float)(this.bx + 10), (float)(this.by + 1), 0.0F);
         c.method_51448().method_22905(0.875F, 0.875F, 1.0F);
         c.method_51427(this.chest, 0, 0);
      } finally {
         c.method_51448().method_22909();
      }

   }

   private void Kbd858745b3877b54a2(K9e64dd2dd1a3d84dbb selected) {
      if (this.guideLayout != selected || this.guideAge != this.mc.field_1724.field_6012) {
         this.guideLayout = selected;
         this.guideAge = this.mc.field_1724.field_6012;
         ArrayList<class_1799> actual = new ArrayList(41);

         for(int i = 0; i < 41; ++i) {
            actual.add(this.mc.field_1724.method_31548().method_5438(i));
         }

         this.guide = Kb829f755817df4f5cd.analyze(selected.slots(), actual);
      }
   }

   private void Kbfb23d38f0890be9e9(class_332 c, int x, int y) {
      c.method_51448().method_22903();

      try {
         c.method_51448().method_46416(0.0F, 0.0F, 300.0F);

         for(class_1735 slot : this.screen.method_17577().field_7761) {
            int index = slot.method_34266();
            if (slot.field_7871 == this.mc.field_1724.method_31548() && index >= 0 && index < 41 && slot.method_7682()) {
               K3d3e59b2721b64e503 state = this.guide.status()[index];
               if (state == K3d3e59b2721b64e503.MISSING || state == K3d3e59b2721b64e503.MISPLACED) {
                  int rgb = state == K3d3e59b2721b64e503.MISSING ? 15822194 : 15910492;
                  int px = x + slot.field_7873;
                  int py = y + slot.field_7872;
                  c.method_25294(px, py, px + 16, py + 16, 889192448 | rgb);
                  int border = -1342177280 | rgb;
                  c.method_25294(px - 1, py - 1, px + 17, py, border);
                  c.method_25294(px - 1, py + 16, px + 17, py + 17, border);
                  c.method_25294(px - 1, py, px, py + 16, border);
                  c.method_25294(px + 16, py, px + 17, py + 16, border);
               }
            }
         }

         c.method_51452();
      } finally {
         c.method_51448().method_22909();
      }

   }

   private void K32ae2f12cbd3a0c4d1(class_332 c, K9e64dd2dd1a3d84dbb layout, int mx, int my, int x, int y) {
      for(class_1735 slot : this.screen.method_17577().field_7761) {
         int index = slot.method_34266();
         if (slot.field_7871 == this.mc.field_1724.method_31548() && index >= 0 && index < 41 && slot.method_7682()) {
            class_1799 expected = (class_1799)layout.slots().get(index);
            if (!expected.method_7960() && this.guide.status()[index] != K3d3e59b2721b64e503.CORRECT) {
               int px = x + slot.field_7873;
               int py = y + slot.field_7872;
               if (!slot.method_7681() && expected.method_7947() > 1) {
                  String count = Integer.toString(expected.method_7947());
                  c.method_51433(this.mc.field_1772, count, px + 17 - this.mc.field_1772.method_1727(count), py + 9, -570425345, true);
               }

               if (K26771f24bfecb7a1f9.inside((double)mx, (double)my, px, py, 16, 16)) {
                  String status = this.guide.status()[index] == K3d3e59b2721b64e503.MISSING ? "Не хватает: " : "Не на своём месте: ";
                  c.method_51438(this.mc.field_1772, class_2561.method_43470(status).method_10852(expected.method_7964()).method_27693(" ×" + expected.method_7947()), mx, my);
               }
            }
         }
      }

   }

   private void Ka17f3a737358dec626(class_332 c, int mx, int my, int inventoryX, int inventoryY, int inventoryWidth) {
      int right = inventoryX + inventoryWidth + 8;
      int leftRoom = inventoryX - 16;
      int rightRoom = this.screen.field_22789 - right - 8;
      boolean left = leftRoom >= 48 || leftRoom > rightRoom;
      this.sw = Math.min(164, Math.max(48, left ? leftRoom : rightRoom));
      this.sx = left ? Math.max(4, inventoryX - this.sw - 8) : Math.min(right, this.screen.field_22789 - this.sw - 4);
      this.sy = Math.clamp((long)inventoryY, 4, Math.max(4, this.screen.field_22790 - 100));
      int rowHeight = this.sw < 100 ? 29 : 22;
      int desiredHeight = this.guide.missing().isEmpty() ? 96 : 56 + Math.min(6, this.guide.missing().size()) * rowHeight;
      this.sh = Math.min(desiredHeight, this.screen.field_22790 - this.sy - 8);
      this.sidebar = true;
      int rows = Math.max(1, (this.sh - 56) / rowHeight);
      this.missingScroll = Math.clamp((long)this.missingScroll, 0, Math.max(0, this.guide.missing().size() - rows));
      c.method_51452();
      this.canvas.begin(c);
      this.canvas.setAlpha(1.0F);
      this.canvas.rect((float)(this.sx - 1), (float)(this.sy - 1), (float)(this.sw + 2), (float)(this.sh + 2), 7.0F, Kb18d549c13309af9a5.primary(65));
      this.canvas.rect((float)this.sx, (float)this.sy, (float)this.sw, (float)this.sh, 6.0F, new Color(18, 19, 26, 245));
      this.canvas.text(Kc517862407c2394736.SEMIBOLD, this.guide.missing().isEmpty() ? "Всё есть" : "Не хватает", (float)(this.sx + 8), (float)(this.sy + 13), this.sw < 80 ? 7.0F : 10.0F, this.guide.missing().isEmpty() ? new Color(120, 217, 164) : new Color(245, 121, 127));

      for(int row = 0; row < rows && row + this.missingScroll < this.guide.missing().size(); ++row) {
         Ka90d44e8813eebf293 entry = (Ka90d44e8813eebf293)this.guide.missing().get(row + this.missingScroll);
         int yy = this.sy + 25 + row * rowHeight;
         this.canvas.rect((float)(this.sx + 5), (float)yy, (float)(this.sw - 10), (float)(rowHeight - 2), 4.0F, new Color(255, 255, 255, K26771f24bfecb7a1f9.inside((double)mx, (double)my, this.sx + 5, yy, this.sw - 10, rowHeight - 2) ? 20 : 7));
         String itemName = class_124.method_539(entry.stack().method_7964().getString()).replace('\n', ' ');
         if (this.sw < 100) {
            this.canvas.text(Kc517862407c2394736.MEDIUM, entry.count() + "×", (float)(this.sx + 27), (float)(yy + 9), 8.0F, Kb18d549c13309af9a5.textColor());
            this.canvas.text(Kc517862407c2394736.MEDIUM, this.K07d65f24e6d4bb0862(itemName, (float)(this.sw - 16), 7.0F), (float)(this.sx + 8), (float)(yy + 22), 7.0F, Kb18d549c13309af9a5.textColor());
         } else {
            this.canvas.text(Kc517862407c2394736.MEDIUM, this.K07d65f24e6d4bb0862(entry.count() + "× " + itemName, (float)(this.sw - 35), 8.0F), (float)(this.sx + 27), (float)(yy + 10), 8.0F, Kb18d549c13309af9a5.textColor());
         }
      }

      if (this.guide.missing().isEmpty()) {
         boolean misplaced = Arrays.stream(this.guide.status()).anyMatch((state) -> state == K3d3e59b2721b64e503.MISPLACED);
         this.canvas.text(Kc517862407c2394736.MEDIUM, misplaced ? "Жёлтые слоты" : "Все вещи", (float)(this.sx + 7), (float)(this.sy + 36), this.sw < 80 ? 6.0F : 8.0F, Kb18d549c13309af9a5.inactiveTextColor());
         this.canvas.text(Kc517862407c2394736.MEDIUM, misplaced ? "нужно разложить" : "на своих местах", (float)(this.sx + 7), (float)(this.sy + 49), this.sw < 80 ? 5.5F : 8.0F, Kb18d549c13309af9a5.inactiveTextColor());
      }

      this.K9ce1d6172f5203f04a(this.module.getArranger().active() ? "Остановить" : "Разложить всё", this.sx + 6, this.sy + this.sh - 28, this.sw - 12, 22, mx, my, true, this.module.getArranger().active() || this.guide.canArrange());
      if (this.guide.missing().size() > rows) {
         this.canvas.text(Kc517862407c2394736.MEDIUM, "↕", (float)(this.sx + this.sw - 10), (float)(this.sy + this.sh - 36), 8.0F, Kb18d549c13309af9a5.inactiveTextColor());
      }

      class_1799 hovered = null;

      for(int row = 0; row < rows && row + this.missingScroll < this.guide.missing().size(); ++row) {
         Ka90d44e8813eebf293 entry = (Ka90d44e8813eebf293)this.guide.missing().get(row + this.missingScroll);
         int yy = this.sy + 25 + row * rowHeight;
         c.method_51427(entry.stack(), this.sx + 7, yy + 2);
         if (K26771f24bfecb7a1f9.inside((double)mx, (double)my, this.sx + 5, yy, this.sw - 10, rowHeight - 2)) {
            hovered = entry.stack();
         }
      }

      if (hovered != null) {
         c.method_51434(this.mc.field_1772, List.of(hovered.method_7964(), class_2561.method_43470("ЛКМ — поиск на аукционе").method_27692(class_124.field_1080)), mx, my);
      }

   }

   private String K07d65f24e6d4bb0862(String text, float width, float size) {
      if (this.canvas.textWidth(Kc517862407c2394736.MEDIUM, text, size) <= width) {
         return text;
      } else {
         while(!text.isEmpty() && this.canvas.textWidth(Kc517862407c2394736.MEDIUM, text + "…", size) > width) {
            text = text.substring(0, text.length() - 1);
         }

         return text + "…";
      }
   }

   private void K9ce1d6172f5203f04a(String label, int x, int y, int w, int h, int mx, int my, boolean accent, boolean enabled) {
      Color base = accent ? Kb18d549c13309af9a5.primary(enabled ? 175 : 60) : new Color(255, 255, 255, enabled ? 22 : 9);
      if (enabled && K26771f24bfecb7a1f9.inside((double)mx, (double)my, x, y, w, h)) {
         base = accent ? Kb18d549c13309af9a5.primary(230) : new Color(255, 255, 255, 36);
      }

      this.canvas.rect((float)x, (float)y, (float)w, (float)h, 6.0F, base);
      float size = Math.min(8.5F, 8.5F * (float)Math.max(1, w - 8) / Math.max(1.0F, this.canvas.textWidth(Kc517862407c2394736.MEDIUM, label, 8.5F)));
      this.canvas.text(Kc517862407c2394736.MEDIUM, label, (float)x + ((float)w - this.canvas.textWidth(Kc517862407c2394736.MEDIUM, label, size)) / 2.0F, (float)y + (float)h / 2.0F, size, enabled ? Kb18d549c13309af9a5.textColor() : Kb18d549c13309af9a5.inactiveTextColor(120));
   }

   private void K76d286a76c9469a388(class_332 c, int mx, int my, float delta) {
      K26771f24bfecb7a1f9 l = this.K8734bc9e6fbdd4040d();
      float fade = 1.0F - (float)Math.pow((double)(1.0F - Math.min(1.0F, (float)(System.nanoTime() - this.opened) / 1.8E8F)), (double)3.0F);
      c.method_25294(0, 0, this.screen.field_22789, this.screen.field_22790, (int)(160.0F * fade) << 24);
      c.method_51452();
      this.canvas.begin(c);
      this.canvas.setAlpha(fade);
      this.canvas.rect((float)(l.x() - 1), (float)(l.y() - 1), (float)(l.width() + 2), (float)(l.height() + 2), 11.0F, Kb18d549c13309af9a5.primary(65));
      this.canvas.rect((float)l.x(), (float)l.y(), (float)l.width(), (float)l.height(), 10.0F, new Color(18, 19, 26, 248));
      this.canvas.text(Kc517862407c2394736.MEDIUM, "← Назад", (float)(l.x() + 16), (float)(l.y() + 16), 8.5F, K26771f24bfecb7a1f9.inside((double)mx, (double)my, l.x() + 10, l.y() + 6, 70, 20) ? Kb18d549c13309af9a5.textColor() : Kb18d549c13309af9a5.inactiveTextColor());
      String title = "Менеджер инвентарей";
      float titleSize = Math.min(12.0F, (float)(12 * Math.max(1, l.width() - 124)) / Math.max(1.0F, this.canvas.textWidth(Kc517862407c2394736.SEMIBOLD, title, 12.0F)));
      this.canvas.text(Kc517862407c2394736.SEMIBOLD, title, (float)(l.x() + 38), (float)(l.y() + 42), titleSize, Kb18d549c13309af9a5.textColor());
      this.K9ce1d6172f5203f04a("+ Сохранить", l.x() + l.width() - 80, l.y() + 30, 64, 24, mx, my, true, !this.name.method_1882().isBlank());
      this.canvas.rect((float)(l.x() + 15), (float)(l.y() + 61), (float)(l.width() - 30), 30.0F, 7.0F, this.name.method_25370() ? Kb18d549c13309af9a5.primary(110) : new Color(255, 255, 255, 13));
      this.canvas.rect((float)(l.x() + 16), (float)(l.y() + 62), (float)(l.width() - 32), 28.0F, 6.0F, new Color(33, 35, 46));
      this.name.method_25394(c, mx, my, delta);
      c.method_51452();
      List<K9e64dd2dd1a3d84dbb> layouts = this.K10fb8197ff9804d285().layouts();
      this.scroll = Math.clamp((long)this.scroll, 0, Math.max(0, layouts.size() - l.rows()));
      K9e64dd2dd1a3d84dbb active = this.K10fb8197ff9804d285().selected();
      K9e64dd2dd1a3d84dbb preview = null;
      int previewRowY = 0;

      for(int row = 0; row < l.rows() && row + this.scroll < layouts.size(); ++row) {
         K9e64dd2dd1a3d84dbb entry = (K9e64dd2dd1a3d84dbb)layouts.get(row + this.scroll);
         int yy = l.rowY(row);
         int hh = l.rowHeight() - 5;
         boolean selected = active != null && Objects.equals(active.name(), entry.name());
         boolean hovered = K26771f24bfecb7a1f9.inside((double)mx, (double)my, l.x() + 16, yy, l.width() - 146, hh);
         if (hovered) {
            preview = entry;
            previewRowY = yy;
         }

         this.canvas.rect((float)(l.x() + 16), (float)yy, (float)(l.width() - 32), (float)hh, 7.0F, selected ? Kb18d549c13309af9a5.primary(48) : (hovered ? new Color(39, 42, 55) : new Color(30, 32, 42)));
         if (selected) {
            this.canvas.rect((float)(l.x() + 16), (float)(yy + 8), 2.0F, (float)(hh - 16), 1.0F, Kb18d549c13309af9a5.primary());
         }

         this.canvas.text(Kc517862407c2394736.MEDIUM, this.K07d65f24e6d4bb0862(entry.name(), (float)(l.width() - 163), 10.0F), (float)(l.x() + 26), (float)(yy + 12), 10.0F, Kb18d549c13309af9a5.textColor());
         K64dfc1330d745b4d7f var10000 = this.canvas;
         Kc517862407c2394736 var10001 = Kc517862407c2394736.MEDIUM;
         int var10002 = entry.occupiedSlots();
         var10000.text(var10001, var10002 + " предм.", (float)(l.x() + 26), (float)(yy + hh - 9), 7.5F, Kb18d549c13309af9a5.inactiveTextColor());
         this.K9ce1d6172f5203f04a(selected ? "Загружено" : "Загрузить", l.x() + l.width() - 122, yy + (hh - 22) / 2, 72, 22, mx, my, selected, true);
         int dx = l.x() + l.width() - 42;
         int dy = yy + (hh - 22) / 2;
         boolean deleteHover = K26771f24bfecb7a1f9.inside((double)mx, (double)my, dx, dy, 22, 22);
         this.canvas.rect((float)dx, (float)dy, 22.0F, 22.0F, 5.0F, new Color(225, 80, 86, deleteHover ? 100 : 42));
         this.canvas.icon(K756e30c0738d06c926.TRASH, (float)(dx + 11), (float)(dy + 11), 11.0F, new Color(247, 132, 138));
      }

      if (layouts.isEmpty()) {
         this.canvas.text(Kc517862407c2394736.MEDIUM, "Пока нет сохранённых инвентарей", (float)(l.x() + 24), (float)(l.listY() + 22), 10.0F, Kb18d549c13309af9a5.inactiveTextColor());
         this.canvas.text(Kc517862407c2394736.MEDIUM, "Введи название и нажми «Сохранить»", (float)(l.x() + 24), (float)(l.listY() + 40), 8.0F, Kb18d549c13309af9a5.inactiveTextColor(160));
      }

      if (layouts.size() > l.rows() && l.rows() > 0) {
         float track = (float)(l.rows() * l.rowHeight() - 5);
         float thumb = Math.max(12.0F, track * (float)l.rows() / (float)layouts.size());
         float sy = (float)l.listY() + (track - thumb) * (float)this.scroll / (float)(layouts.size() - l.rows());
         this.canvas.rect((float)(l.x() + l.width() - 10), (float)l.listY(), 2.0F, track, 1.0F, new Color(255, 255, 255, 16));
         this.canvas.rect((float)(l.x() + l.width() - 10), sy, 2.0F, thumb, 1.0F, Kb18d549c13309af9a5.primary(190));
      }

      boolean hasNotice = System.nanoTime() < this.noticeUntil;
      this.canvas.rect((float)(l.x() + 17), (float)(l.footerY() - 2), 4.0F, 4.0F, 2.0F, active == null ? Kb18d549c13309af9a5.inactiveTextColor() : Kb18d549c13309af9a5.primary());
      String status = hasNotice ? this.notice : (active == null ? "Оверлей выключен" : "Оверлей: " + active.name());
      this.canvas.text(Kc517862407c2394736.MEDIUM, this.K07d65f24e6d4bb0862(status, (float)(l.width() - 135), 8.0F), (float)(l.x() + 27), (float)l.footerY(), 8.0F, hasNotice ? Kb18d549c13309af9a5.textColor() : Kb18d549c13309af9a5.inactiveTextColor());
      if (active != null) {
         this.K9ce1d6172f5203f04a("Выключить", l.x() + l.width() - 92, l.footerY() - 11, 76, 22, mx, my, false, true);
      }

      c.method_51448().method_22903();

      try {
         c.method_51448().method_46416((float)(l.x() + 16), (float)(l.y() + 34), 0.0F);
         c.method_51448().method_22905(0.875F, 0.875F, 1.0F);
         c.method_51427(this.chest, 0, 0);
      } finally {
         c.method_51448().method_22909();
      }

      if (preview != null) {
         c.method_51452();
         this.K14579243a5be48f718(c, preview, K48f87f72ad6d3cbadc.fit(l, this.screen.field_22789, this.screen.field_22790, previewRowY));
      }

   }

   private void K14579243a5be48f718(class_332 c, K9e64dd2dd1a3d84dbb saved, K48f87f72ad6d3cbadc p) {
      c.method_51448().method_22903();

      try {
         c.method_51448().method_46416(0.0F, 0.0F, 180.0F);
         this.canvas.begin(c);
         this.canvas.setAlpha(1.0F);
         this.canvas.rect((float)(p.x() - 1), (float)(p.y() - 1), 192.0F, 158.0F, 9.0F, Kb18d549c13309af9a5.primary(90));
         this.canvas.rect((float)p.x(), (float)p.y(), 190.0F, 156.0F, 8.0F, new Color(18, 19, 26, 253));
         this.canvas.text(Kc517862407c2394736.SEMIBOLD, "Просмотр", (float)(p.x() + 14), (float)(p.y() + 18), 11.0F, Kb18d549c13309af9a5.textColor());
         this.canvas.text(Kc517862407c2394736.MEDIUM, this.K07d65f24e6d4bb0862(saved.name(), 86.0F, 8.0F), (float)(p.x() + 82), (float)(p.y() + 18), 8.0F, Kb18d549c13309af9a5.inactiveTextColor());

         for(int cell = 0; cell < 41; ++cell) {
            this.canvas.rect((float)(p.x() + K48f87f72ad6d3cbadc.cellX(cell)), (float)(p.y() + K48f87f72ad6d3cbadc.cellY(cell)), 17.0F, 17.0F, 3.0F, new Color(37, 39, 50));
         }

         this.canvas.rect((float)(p.x() + 14), (float)(p.y() + 55), 162.0F, 1.0F, 0.0F, Kb18d549c13309af9a5.primary(45));

         for(int cell = 0; cell < 41; ++cell) {
            class_1799 stack = (class_1799)saved.slots().get(K48f87f72ad6d3cbadc.slotIndex(cell));
            if (!stack.method_7960()) {
               int sx = p.x() + K48f87f72ad6d3cbadc.cellX(cell) + 1;
               int sy = p.y() + K48f87f72ad6d3cbadc.cellY(cell) + 1;
               c.method_51427(stack, sx, sy);
               c.method_51431(this.mc.field_1772, stack, sx, sy);
            }
         }

         c.method_51452();
      } finally {
         c.method_51448().method_22909();
      }

   }

   private void message(String text) {
      this.notice = text == null ? "Не удалось сохранить раскладку" : text;
      this.noticeUntil = System.nanoTime() + 3000000000L;
   }

   private void Kc4f12fc34a1831bcb9() {
      this.open = false;
      this.name.method_25365(false);
   }

   private void save() {
      try {
         this.K10fb8197ff9804d285().save(this.name.method_1882());
         this.name.method_1852("");
         this.message("Инвентарь сохранён");
      } catch (Exception e) {
         this.message(e.getMessage());
      }

   }

   public boolean click(double mx, double my, int button) {
      if (!this.supported()) {
         return false;
      } else if (!this.open) {
         if (this.sidebar && K26771f24bfecb7a1f9.inside(mx, my, this.sx, this.sy, this.sw, this.sh)) {
            if (button == 0 && K26771f24bfecb7a1f9.inside(mx, my, this.sx + 6, this.sy + this.sh - 28, this.sw - 12, 22)) {
               if (this.module.getArranger().active()) {
                  this.module.getArranger().stop();
               } else if (this.guide != null && this.guide.canArrange()) {
                  this.module.getArranger().start(this.K10fb8197ff9804d285().selected());
               }

               return true;
            } else {
               if (button == 0 && this.guide != null) {
                  int rowHeight = this.sw < 100 ? 29 : 22;
                  int rows = Math.max(1, (this.sh - 56) / rowHeight);

                  for(int row = 0; row < rows && row + this.missingScroll < this.guide.missing().size(); ++row) {
                     if (K26771f24bfecb7a1f9.inside(mx, my, this.sx + 5, this.sy + 25 + row * rowHeight, this.sw - 10, rowHeight - 2)) {
                        this.module.searchAuction(((Ka90d44e8813eebf293)this.guide.missing().get(row + this.missingScroll)).stack());
                        return true;
                     }
                  }
               }

               return true;
            }
         } else {
            this.module.getArranger().stop();
            if (!this.tab(mx, my)) {
               return false;
            } else {
               if (button == 0 && this.screen.method_17577().method_34255().method_7960()) {
                  this.open = true;
                  this.opened = System.nanoTime();
                  this.K7cac5cb07d724dc5d5();
                  this.name.method_25365(true);
               }

               return true;
            }
         }
      } else {
         K26771f24bfecb7a1f9 l = this.K8734bc9e6fbdd4040d();
         if (button != 0) {
            return true;
         } else if (l.contains(mx, my) && !K26771f24bfecb7a1f9.inside(mx, my, l.x() + 10, l.y() + 6, 70, 20)) {
            if (K26771f24bfecb7a1f9.inside(mx, my, l.x() + l.width() - 80, l.y() + 30, 64, 24)) {
               if (!this.name.method_1882().isBlank()) {
                  this.save();
               }

               return true;
            } else {
               try {
                  if (this.K10fb8197ff9804d285().selected() != null && K26771f24bfecb7a1f9.inside(mx, my, l.x() + l.width() - 92, l.footerY() - 11, 76, 22)) {
                     this.K10fb8197ff9804d285().select((String)null);
                     return true;
                  }

                  List<K9e64dd2dd1a3d84dbb> layouts = this.K10fb8197ff9804d285().layouts();
                  this.scroll = Math.clamp((long)this.scroll, 0, Math.max(0, layouts.size() - l.rows()));

                  for(int row = 0; row < l.rows() && row + this.scroll < layouts.size(); ++row) {
                     K9e64dd2dd1a3d84dbb entry = (K9e64dd2dd1a3d84dbb)layouts.get(row + this.scroll);
                     int yy = l.rowY(row);
                     int hh = l.rowHeight() - 5;
                     if (K26771f24bfecb7a1f9.inside(mx, my, l.x() + l.width() - 42, yy + (hh - 22) / 2, 22, 22)) {
                        this.K10fb8197ff9804d285().delete(entry.name());
                        this.scroll = Math.min(this.scroll, Math.max(0, layouts.size() - 1 - l.rows()));
                        return true;
                     }

                     if (K26771f24bfecb7a1f9.inside(mx, my, l.x() + l.width() - 122, yy + (hh - 22) / 2, 72, 22)) {
                        this.K10fb8197ff9804d285().select(entry.name());
                        this.Kc4f12fc34a1831bcb9();
                        return true;
                     }
                  }

                  this.name.method_25402(mx, my, button);
               } catch (Exception e) {
                  this.message(e.getMessage());
               }

               return true;
            }
         } else {
            this.Kc4f12fc34a1831bcb9();
            return true;
         }
      }
   }

   public boolean key(int key, int scan, int modifiers) {
      if (!this.open()) {
         this.module.getArranger().stop();
         return false;
      } else {
         if (key == 256) {
            this.Kc4f12fc34a1831bcb9();
         } else if ((key == 257 || key == 335) && !this.name.method_1882().isBlank()) {
            this.save();
         } else if (key == 258) {
            this.name.method_25365(!this.name.method_25370());
         } else {
            this.name.method_25404(key, scan, modifiers);
         }

         return true;
      }
   }

   public boolean character(char c, int modifiers) {
      if (!this.open()) {
         return false;
      } else {
         this.name.method_25400(c, modifiers);
         return true;
      }
   }

   public boolean scroll(double mx, double my, double amount) {
      if (this.open()) {
         this.scroll -= (int)Math.signum(amount);
         return true;
      } else if (this.sidebar && K26771f24bfecb7a1f9.inside(mx, my, this.sx, this.sy, this.sw, this.sh)) {
         this.missingScroll -= (int)Math.signum(amount);
         return true;
      } else {
         return false;
      }
   }

   public boolean consumesTooltip(int mx, int my) {
      return this.supported() && (this.open || this.tab((double)mx, (double)my) || this.sidebar && K26771f24bfecb7a1f9.inside((double)mx, (double)my, this.sx, this.sy, this.sw, this.sh));
   }

   public void close() {
      this.Kc4f12fc34a1831bcb9();
      this.positioned = false;
      this.sidebar = false;
      this.module.getArranger().stop();
      this.ghosts.close();
   }
}
