package code.miaware.client.ui.widget;

import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.display.Ka344a99dbf6b098920;
import code.miaware.api.utils.render.fonts.K367cc7b6858ae36355;
import code.miaware.api.utils.render.fonts.K5fe84dad3db1af4bb7;
import code.miaware.api.utils.render.fonts.K896065ea0a2bba4f47;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_1044;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4587;

public final class Ka20f8cfdbc113ccdec {
   public static final Color BG = new Color(15, 20, 26);
   public static final Color RING = new Color(36, 41, 51);
   public static final Color TEXT = new Color(234, 237, 243);
   public static final Color MUTED = new Color(124, 130, 144);
   public static Color ACCENT = new Color(158, 165, 254);
   public static Color ACCENT_SOFT = new Color(170, 176, 255);
   public static final Color DIVIDER = new Color(56, 61, 70);
   public static final Color TRACK = new Color(36, 41, 60);
   public static final Color SLOT = new Color(27, 32, 42);
   public static final Color WARM = new Color(255, 206, 157);
   public static final Color RED = new Color(255, 128, 128);
   public static final Color GREEN = new Color(170, 236, 140);
   public static final float BG_ALPHA = 0.94F;
   private static final float CAP = 0.711F;
   private static final float INSET = 0.4F;
   private static final Map<String, class_2960> ICON_IDS = new HashMap();
   private static final Map<String, Boolean> FILTERED = new HashMap();

   private Ka20f8cfdbc113ccdec() {
   }

   public static Color a(Color c, float alpha) {
      int value = Math.round((float)c.getAlpha() * Math.max(0.0F, Math.min(1.0F, alpha)));
      return new Color(c.getRed(), c.getGreen(), c.getBlue(), value);
   }

   public static K5fe84dad3db1af4bb7 regular() {
      return K896065ea0a2bba4f47.SF_MEDIUM;
   }

   public static K5fe84dad3db1af4bb7 semibold() {
      return K896065ea0a2bba4f47.SF_SEMIBOLD;
   }

   public static void text(class_4587 ms, K5fe84dad3db1af4bb7 font, String text, float x, float centerY, float size, Color color) {
      if (text != null && !text.isEmpty() && color.getAlpha() > 0) {
         float y = centerY - font.getMetrics().baselineHeight() * size + 0.711F * size / 2.0F;
         font.drawText(ms, text, x, y, size, 0.0F, color.getRGB(), -1, -1.0F, 0.5F, 0.0F, -1, 0.0F);
      }
   }

   public static float width(K5fe84dad3db1af4bb7 font, String text, float size) {
      return text != null && !text.isEmpty() ? font.getWidth(text, size) : 0.0F;
   }

   public static String ellipsize(K5fe84dad3db1af4bb7 font, String text, float size, float maxWidth) {
      if (text == null) {
         return "";
      } else if (!(maxWidth <= 0.0F) && !(width(font, text, size) <= maxWidth)) {
         String dots = "…";

         int end;
         for(end = text.length(); end > 0 && width(font, text.substring(0, end) + dots, size) > maxWidth; --end) {
         }

         String var10000 = text.substring(0, end).stripTrailing();
         return var10000 + dots;
      } else {
         return text;
      }
   }

   public static void rect(class_4587 ms, float x, float y, float w, float h, float r, Color color) {
      if (!(w <= 0.0F) && !(h <= 0.0F) && color.getAlpha() > 0) {
         float radius = Math.min(r, Math.min(w, h) / 2.0F) + 0.4F;
         K2b5e2c5f2b9891b450.RECT.draw(ms, x - 0.4F, y - 0.4F, w + 0.8F, h + 0.8F, radius, color);
      }
   }

   public static void rects(class_4587 ms, List<Ka344a99dbf6b098920> positions, float w, float h, float r, Color color) {
      List<Ka344a99dbf6b098920> adjusted = new ArrayList(positions.size());

      for(Ka344a99dbf6b098920 position : positions) {
         adjusted.add(new Ka344a99dbf6b098920(position.x() - 0.4F, position.y() - 0.4F));
      }

      K2b5e2c5f2b9891b450.RECT.drawBatch(ms, adjusted, w + 0.8F, h + 0.8F, Math.min(r, Math.min(w, h) / 2.0F) + 0.4F, color);
   }

   public static void panel(class_4587 ms, float x, float y, float w, float h, float r, float alpha) {
      float ring = Math.max(0.5F, r * 0.09F);
      rect(ms, x - ring, y - ring, w + ring * 2.0F, h + ring * 2.0F, r + ring, a(RING, alpha));
      rect(ms, x, y, w, h, r, a(BG, 0.94F * alpha));
   }

   public static void tick(class_4587 ms, float centerX, float centerY, float height, float alpha) {
      float w = Math.max(0.6F, height * 0.16F);
      rect(ms, centerX - w / 2.0F, centerY - height / 2.0F, w, height, w / 2.0F, a(DIVIDER, alpha));
   }

   public static void icon(class_4587 ms, String name, float centerX, float centerY, float size, Color color) {
      if (color.getAlpha() > 0) {
         try {
            class_2960 id = (class_2960)ICON_IDS.computeIfAbsent(name, (n) -> n.startsWith("clickgui/") ? class_2960.method_60655("luxvisuals", "images/clickgui/icons/" + n.substring(9) + ".png") : class_2960.method_60655("luxvisuals", "images/hud/icons/" + n + ".png"));
            class_1044 texture = class_310.method_1551().method_1531().method_4619(id);
            if (!FILTERED.containsKey(name)) {
               texture.method_4527(true, false);
               FILTERED.put(name, Boolean.TRUE);
            }

            float s = size + 0.8F;
            K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(ms, centerX - s / 2.0F, centerY - s / 2.0F, s, s, 0.0F, color, 0.0F, 0.0F, 1.0F, 1.0F, texture.method_4624());
         } catch (Throwable var9) {
         }

      }
   }

   public static void item(class_4587 ms, class_1799 stack, float x, float y, float size, float alpha) {
      try (Kdd908967ec41b41bcf batch = items(ms, alpha)) {
         batch.draw(stack, x, y, size);
      }

   }

   public static Kdd908967ec41b41bcf items(class_4587 ms, float alpha) {
      return new Kdd908967ec41b41bcf(ms, alpha);
   }

   public static float partsWidth(List<K5af30bf5e82a937089> parts, float size) {
      float w = 0.0F;

      for(K5af30bf5e82a937089 p : parts) {
         w += width(p.bold() ? semibold() : regular(), p.text(), size);
      }

      return w;
   }

   public static float segmentWidth(Kd80e03babf074b8b76 s, Kdeecdbb204319837a4 m) {
      float w = partsWidth(s.parts, m.textSize());
      if (s.icon != null) {
         w += m.iconSize() + (s.parts.isEmpty() ? 0.0F : m.iconGap());
      }

      return w;
   }

   public static float pillWidth(List<Kd80e03babf074b8b76> segments, Kdeecdbb204319837a4 m) {
      if (segments.isEmpty()) {
         return 0.0F;
      } else {
         float w = m.padX() * 2.0F;

         for(int i = 0; i < segments.size(); ++i) {
            w += segmentWidth((Kd80e03babf074b8b76)segments.get(i), m);
            if (i < segments.size() - 1) {
               w += m.segmentGap() * 2.0F;
            }
         }

         return w;
      }
   }

   public static float pill(class_4587 ms, float x, float y, List<Kd80e03babf074b8b76> segments, Kdeecdbb204319837a4 m, float alpha) {
      float w = pillWidth(segments, m);
      if (w <= 0.0F) {
         return 0.0F;
      } else {
         panel(ms, x, y, w, m.height(), m.radius(), alpha);
         float cy = y + m.height() / 2.0F;
         float cx = x + m.padX();

         for(int i = 0; i < segments.size(); ++i) {
            Kd80e03babf074b8b76 s = (Kd80e03babf074b8b76)segments.get(i);
            if (s.icon != null) {
               icon(ms, s.icon, cx + m.iconSize() / 2.0F, cy, m.iconSize(), a(s.iconColor, alpha));
               cx += m.iconSize() + (s.parts.isEmpty() ? 0.0F : m.iconGap());
            }

            cx = drawParts(ms, s.parts, cx, cy, m.textSize(), alpha);
            if (i < segments.size() - 1) {
               tick(ms, cx + m.segmentGap(), cy, m.height() * 0.2F, alpha);
               cx += m.segmentGap() * 2.0F;
            }
         }

         return w;
      }
   }

   public static float drawParts(class_4587 ms, List<K5af30bf5e82a937089> parts, float x, float cy, float size, float alpha) {
      if (parts.isEmpty()) {
         return x;
      } else if (parts.size() == 1) {
         K5af30bf5e82a937089 part = (K5af30bf5e82a937089)parts.getFirst();
         K5fe84dad3db1af4bb7 font = part.bold() ? semibold() : regular();
         text(ms, font, part.text(), x, cy, size, a(part.color(), alpha));
         return x + width(font, part.text(), size);
      } else {
         K5fe84dad3db1af4bb7 active = null;
         List<K367cc7b6858ae36355> runs = new ArrayList(parts.size());

         for(K5af30bf5e82a937089 p : parts) {
            K5fe84dad3db1af4bb7 f = p.bold() ? semibold() : regular();
            if (active != null && active != f) {
               active.drawTextRuns(ms, runs);
               runs.clear();
            }

            active = f;
            Color color = a(p.color(), alpha);
            if (p.text() != null && !p.text().isEmpty() && color.getAlpha() > 0) {
               runs.add(new K367cc7b6858ae36355(p.text(), x, cy - f.getMetrics().baselineHeight() * size + 0.711F * size / 2.0F, size, color.getRGB()));
            }

            x += width(f, p.text(), size);
         }

         if (active != null) {
            active.drawTextRuns(ms, runs);
         }

         return x;
      }
   }

   public static float listWidth(String title, List<K71a43541bec306e7a2> rows, K9c25dd69cb23ed0eb8 m, float minWidth) {
      float header = m.headerIcon() + m.pad() * 1.6F + width(semibold(), title, m.titleSize());
      float rowsW = 0.0F;

      for(K71a43541bec306e7a2 r : rows) {
         float lw = r.indent + partsWidth(r.left, m.textSize()) + (r.badgeIcon != null ? m.badgeSize() + m.pad() * 0.45F : 0.0F);
         float rw = partsWidth(r.right, m.textSize());
         rowsW = Math.max(rowsW, lw + (rw > 0.0F ? m.columnGap() + rw : 0.0F));
      }

      return Math.max(minWidth, Math.max(header, rowsW) + m.pad() * 2.0F);
   }

   public static float listHeight(List<K71a43541bec306e7a2> rows, K9c25dd69cb23ed0eb8 m) {
      float h = m.headerHeight();
      if (!rows.isEmpty()) {
         float rowsH = 0.0F;

         for(K71a43541bec306e7a2 r : rows) {
            rowsH += m.rowHeight() * Math.max(0.0F, Math.min(1.0F, r.alpha));
         }

         h += rowsH + m.bottomPad();
      }

      return h;
   }

   public static void header(class_4587 ms, float x, float y, String icon, String title, K9c25dd69cb23ed0eb8 m, float alpha) {
      float cy = y + m.headerHeight() / 2.0F;
      float ix = x + m.pad() + m.headerIcon() / 2.0F;
      icon(ms, icon, ix, cy, m.headerIcon(), a(ACCENT, alpha));
      float tickX = ix + m.headerIcon() / 2.0F + m.pad() * 0.8F;
      tick(ms, tickX, cy, m.headerHeight() * 0.18F, alpha);
      text(ms, semibold(), title, tickX + m.pad() * 0.8F, cy, m.titleSize(), a(TEXT, alpha));
   }

   public static float[] list(class_4587 ms, float x, float y, String icon, String title, List<K71a43541bec306e7a2> rows, K9c25dd69cb23ed0eb8 m, float minWidth, float alpha) {
      float w = listWidth(title, rows, m, minWidth);
      float h = listHeight(rows, m);
      panel(ms, x, y, w, h, m.radius(), alpha);
      header(ms, x, y, icon, title, m, alpha);
      float ry = y + m.headerHeight();

      for(K71a43541bec306e7a2 r : rows) {
         float ra = Math.max(0.0F, Math.min(1.0F, r.alpha));
         float rowA = alpha * ra;
         float cy = ry + m.rowHeight() * ra / 2.0F;
         float lx = x + m.pad() + r.indent;
         if (r.badgeIcon != null) {
            float b = m.badgeSize();
            rect(ms, lx, cy - b / 2.0F, b, b, b * 0.22F, a(ACCENT, rowA));
            icon(ms, r.badgeIcon, lx + b / 2.0F, cy, b * 0.78F, a(BG, rowA));
            lx += b + m.pad() * 0.45F;
         }

         drawParts(ms, r.left, lx, cy, m.textSize(), rowA);
         float rw = partsWidth(r.right, m.textSize());
         drawParts(ms, r.right, x + w - m.pad() - rw, cy, m.textSize(), rowA);
         ry += m.rowHeight() * ra;
      }

      return new float[]{w, h};
   }
}
