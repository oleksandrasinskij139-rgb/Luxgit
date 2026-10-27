package code.miaware.client.ui.widget.overlay;

import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.client.features.modules.render.K2d30cfc9532670dc97;
import code.miaware.client.features.modules.render.K4c5d11bb3af87d48e9;
import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_7833;

public class K26da897597ee71d7fd extends K548ca8198549ddf9d7 {
   private static final float PAD = 8.0F;
   private static final float HEADER_H = 21.0F;
   private static final float ROW_H = 25.0F;
   private static final float HEAD = 17.0F;
   private static final float SLOT = 17.0F;
   private final Ka91715a8c4e3f3b7c4 showAnim = new Ka91715a8c4e3f3b7c4();
   private final Map<UUID, Float> arrowAngles = new HashMap();
   private final Map<UUID, Float> rowAnims = new HashMap();
   private List<K2d30cfc9532670dc97> lastRows = new ArrayList();
   private long lastFrame;

   public K26da897597ee71d7fd() {
      super(10.0F, 120.0F);
      this.setEnabled(true);
   }

   public String getName() {
      return "BetterNear";
   }

   public void render(class_4587 matrixStack) {
      this.render(new class_332(mc, mc.method_22940().method_23000()), 0, 0, 0.0F);
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      K4c5d11bb3af87d48e9 module = K4c5d11bb3af87d48e9.getInstance();
      if (super.isEnabled() && module.isEnabled() && mc.field_1724 != null) {
         this.showAnim.update();
         long now = System.currentTimeMillis();
         float dt = this.lastFrame == 0L ? 0.016F : Math.min(0.1F, (float)(now - this.lastFrame) / 1000.0F);
         this.lastFrame = now;
         List<K2d30cfc9532670dc97> rows = module.nearest();
         boolean preview = rows.isEmpty() && this.hudLayoutMode();
         if (!rows.isEmpty()) {
            this.lastRows = rows;
         }

         this.showAnim.run(rows.isEmpty() && !preview ? (double)0.0F : (double)1.0F, 300L, Kf3b62ecfba0c3544e6.CUBIC_OUT);
         float alpha = (float)this.showAnim.getValue();
         if (alpha <= 0.01F) {
            this.K3e029a9dfcc824f220();
         } else {
            if (preview) {
               this.K972432857bb59ea417(context.method_51448(), List.of(), alpha, dt, delta, true);
            } else {
               this.K972432857bb59ea417(context.method_51448(), this.lastRows, alpha, dt, delta, false);
            }

         }
      } else {
         this.K3e029a9dfcc824f220();
      }
   }

   private void K3e029a9dfcc824f220() {
      this.getDraggable().setWidth(0.0F);
      this.getDraggable().setHeight(0.0F);
   }

   private void K972432857bb59ea417(class_4587 ms, List<K2d30cfc9532670dc97> rows, float alpha, float dt, float tickDelta, boolean preview) {
      boolean ru = Ka35e7d6b31db9984f1.getInstance().isRussian();
      float x = this.getDraggable().getX();
      float y = this.getDraggable().getY();
      float pad = this.scaled(8.0F);
      float headerH = this.scaled(21.0F);
      float rowH = this.scaled(25.0F);
      float head = this.scaled(17.0F);
      float slot = this.scaled(17.0F);
      float nameSize = this.scaled(7.4F);
      float small = this.scaled(5.9F);
      float content = Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), ru ? "Никого рядом" : "Nobody nearby", nameSize);

      for(K2d30cfc9532670dc97 n : rows) {
         content = Math.max(content, Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), n.name, nameSize));
      }

      float w = Math.max(this.scaled(140.0F), pad * 2.0F + head + this.scaled(7.0F) + content + this.scaled(10.0F) + slot);
      int count = Math.max(1, rows.size());
      float h = headerH + rowH * (float)count + this.scaled(4.0F);
      float cx = x + w / 2.0F;
      float cy = y + h / 2.0F;
      ms.method_22903();
      ms.method_46416(cx, cy, 0.0F);
      ms.method_22905(0.92F + 0.08F * alpha, 0.92F + 0.08F * alpha, 1.0F);
      ms.method_46416(-cx, -cy, 0.0F);
      Ka20f8cfdbc113ccdec.panel(ms, x, y, w, h, this.scaled(7.0F), alpha);
      float hcy = y + headerH / 2.0F;
      float icon = this.scaled(9.5F);
      Ka20f8cfdbc113ccdec.icon(ms, "pin", x + pad + icon / 2.0F, hcy, icon, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.ACCENT, alpha));
      float tickX = x + pad + icon + this.scaled(6.4F);
      Ka20f8cfdbc113ccdec.tick(ms, tickX, hcy, headerH * 0.36F, alpha);
      Ka20f8cfdbc113ccdec.text(ms, Ka20f8cfdbc113ccdec.semibold(), ru ? "Рядом" : "Nearby", tickX + this.scaled(6.4F), hcy, this.scaled(7.8F), Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.TEXT, alpha));
      String badge = String.valueOf(rows.size());
      float badgeSize = this.scaled(6.2F);
      float badgeW = Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), badge, badgeSize) + this.scaled(8.0F);
      float badgeH = this.scaled(10.0F);
      float badgeX = x + w - pad - badgeW;
      Ka20f8cfdbc113ccdec.rect(ms, badgeX, hcy - badgeH / 2.0F, badgeW, badgeH, badgeH / 2.0F, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.TRACK, alpha));
      Ka20f8cfdbc113ccdec.text(ms, Ka20f8cfdbc113ccdec.semibold(), badge, badgeX + this.scaled(4.0F), hcy, badgeSize, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.ACCENT_SOFT, alpha));
      Ka20f8cfdbc113ccdec.rect(ms, x + pad, y + headerH - this.scaled(0.5F), w - pad * 2.0F, this.scaled(0.6F), 0.0F, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.DIVIDER, alpha));
      float ry = y + headerH + this.scaled(2.0F);
      if (rows.isEmpty()) {
         Ka20f8cfdbc113ccdec.text(ms, Ka20f8cfdbc113ccdec.regular(), ru ? "Никого рядом" : "Nobody nearby", x + pad, ry + rowH / 2.0F, this.scaled(6.8F), Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.MUTED, alpha));
      }

      class_243 me = mc.field_1724.method_30950(tickDelta);
      float yaw = mc.field_1724.method_5705(tickDelta);
      float right = x + w - pad;
      long now = System.currentTimeMillis();
      boolean snap = true;

      for(K2d30cfc9532670dc97 n : rows) {
         float appear = (Float)this.rowAnims.getOrDefault(n.uuid, 0.0F);
         appear += (1.0F - appear) * Math.min(1.0F, dt * 10.0F);
         this.rowAnims.put(n.uuid, appear);
         float ra = alpha * appear * (n.live ? 1.0F : 0.55F);
         class_243 pos = n.live && n.entity != null ? n.entity.method_30950(tickDelta) : n.pos;
         double dx = pos.field_1352 - me.field_1352;
         double dz = pos.field_1350 - me.field_1350;
         double dist = Math.sqrt(dx * dx + dz * dz + Math.pow(pos.field_1351 - me.field_1351, (double)2.0F));
         float headY = ry + (rowH - head) / 2.0F;
         this.K0f5266b31e4ecdbc54(ms, n, x + pad, headY, head, ra);
         float tx = x + pad + head + this.scaled(7.0F);
         Color nameColor = n.friend ? Ka20f8cfdbc113ccdec.GREEN : Ka20f8cfdbc113ccdec.TEXT;
         Ka20f8cfdbc113ccdec.text(ms, Ka20f8cfdbc113ccdec.semibold(), Ka20f8cfdbc113ccdec.ellipsize(Ka20f8cfdbc113ccdec.semibold(), n.name, nameSize, right - slot - this.scaled(6.0F) - tx), tx, ry + this.scaled(9.0F), nameSize, Ka20f8cfdbc113ccdec.a(nameColor, ra));
         long var10000 = Math.round(dist);
         String meters = var10000 + (ru ? " м" : " m");
         Color distColor = dist < (double)16.0F ? Ka20f8cfdbc113ccdec.RED : (dist < (double)48.0F ? Ka20f8cfdbc113ccdec.WARM : Ka20f8cfdbc113ccdec.ACCENT_SOFT);
         Ka20f8cfdbc113ccdec.text(ms, Ka20f8cfdbc113ccdec.semibold(), meters, tx, ry + this.scaled(17.5F), small, Ka20f8cfdbc113ccdec.a(distColor, ra));
         float mw = Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), meters, small);
         String extra = null;
         if (!n.live) {
            long sec = Math.max(0L, (now - n.lastSeen) / 1000L);
            extra = ru ? "был " + sec + "с назад" : "seen " + sec + "s ago";
         } else {
            double dy = pos.field_1351 - me.field_1351;
            if (Math.abs(dy) >= (double)4.0F) {
               extra = (dy > (double)0.0F ? (ru ? "выше на " : "above ") : (ru ? "ниже на " : "below ")) + Math.round(Math.abs(dy));
            }
         }

         if (extra != null) {
            Ka20f8cfdbc113ccdec.text(ms, Ka20f8cfdbc113ccdec.regular(), "· " + extra, tx + mw + this.scaled(3.0F), ry + this.scaled(17.5F), small, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.MUTED, ra));
         }

         float target = (float)Math.toDegrees(Math.atan2(dz, dx)) - 90.0F;
         float rel = class_3532.method_15393(target - yaw);
         if (snap) {
            rel = (float)Math.round(rel / 45.0F) * 45.0F;
         }

         float cur = (Float)this.arrowAngles.getOrDefault(n.uuid, rel);
         cur += class_3532.method_15393(rel - cur) * Math.min(1.0F, dt * 14.0F);
         this.arrowAngles.put(n.uuid, cur);
         float sx = right - slot;
         float sy = ry + (rowH - slot) / 2.0F;
         Ka20f8cfdbc113ccdec.rect(ms, sx, sy, slot, slot, slot / 2.0F, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.SLOT, ra));
         float closeness = (float)class_3532.method_15350((double)1.0F - dist / (double)64.0F, (double)0.0F, (double)1.0F);
         Color arrowColor = n.live ? K2357932e5b34e333ea(Ka20f8cfdbc113ccdec.ACCENT, Ka20f8cfdbc113ccdec.RED, closeness * 0.6F) : Ka20f8cfdbc113ccdec.MUTED;
         this.Ke3b50483feac5c5273(ms, sx + slot / 2.0F, sy + slot / 2.0F, slot * 0.62F, cur, Ka20f8cfdbc113ccdec.a(arrowColor, ra));
         ry += rowH;
      }

      ms.method_22909();
      if (this.rowAnims.size() > 32) {
         this.rowAnims.keySet().retainAll(rows.stream().map((r) -> r.uuid).toList());
         this.arrowAngles.keySet().retainAll(rows.stream().map((r) -> r.uuid).toList());
      }

      this.getDraggable().setWidth(w);
      this.getDraggable().setHeight(h);
   }

   private void K0f5266b31e4ecdbc54(class_4587 ms, K2d30cfc9532670dc97 n, float x, float y, float size, float alpha) {
      Color tint = Ka20f8cfdbc113ccdec.a(Color.WHITE, alpha);
      RenderSystem.enableBlend();
      if (n.entity != null) {
         K2b5e2c5f2b9891b450.TEXTURE_RECT.drawHead(ms, n.entity, x, y, size, size, 0.0F, this.scaled(4.5F), tint);
      } else {
         class_2960 skin = n.skin;
         if (skin == null) {
            Ka20f8cfdbc113ccdec.rect(ms, x, y, size, size, this.scaled(4.5F), Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.SLOT, alpha));
            Ka20f8cfdbc113ccdec.icon(ms, "user", x + size / 2.0F, y + size / 2.0F, size * 0.55F, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.MUTED, alpha));
         } else {
            int texture = mc.method_1531().method_4619(skin).method_4624();
            float u = 0.125F;
            float u2 = 0.625F;
            K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(ms, x, y, size, size, this.scaled(4.5F), tint, u, u, u, u, texture);
            K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(ms, x, y, size, size, this.scaled(4.5F), tint, u2, u, u, u, texture);
         }
      }
   }

   private void Ke3b50483feac5c5273(class_4587 ms, float cx, float cy, float size, float angle, Color color) {
      float t = Math.max(1.2F, size * 0.17F);
      float tip = size * 0.46F;
      float wing = size * 0.42F;
      ms.method_22903();
      ms.method_46416(cx, cy, 0.0F);
      ms.method_22907(class_7833.field_40718.rotationDegrees(angle));
      K2b5e2c5f2b9891b450.RECT.draw(ms, -t / 2.0F, -tip + t * 0.3F, t, tip * 2.0F - t * 0.3F, t / 2.0F, color);

      for(float side : new float[]{45.0F, -45.0F}) {
         ms.method_22903();
         ms.method_46416(0.0F, -tip, 0.0F);
         ms.method_22907(class_7833.field_40718.rotationDegrees(side));
         K2b5e2c5f2b9891b450.RECT.draw(ms, -t / 2.0F, -t / 2.0F, t, wing, t / 2.0F, color);
         ms.method_22909();
      }

      ms.method_22909();
   }

   private static Color K2357932e5b34e333ea(Color a, Color b, float k) {
      return new Color(Math.round((float)a.getRed() + (float)(b.getRed() - a.getRed()) * k), Math.round((float)a.getGreen() + (float)(b.getGreen() - a.getGreen()) * k), Math.round((float)a.getBlue() + (float)(b.getBlue() - a.getBlue()) * k), Math.round((float)a.getAlpha() + (float)(b.getAlpha() - a.getAlpha()) * k));
   }

   public void mouseClicked(double mouseX, double mouseY, int button) {
      this.getDraggable().onClick(button);
   }

   public void mouseReleased(double mouseX, double mouseY, int button) {
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
   }

   public void mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
   }
}
