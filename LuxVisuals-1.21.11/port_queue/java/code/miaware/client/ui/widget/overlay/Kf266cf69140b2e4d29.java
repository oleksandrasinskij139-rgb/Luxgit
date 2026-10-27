package code.miaware.client.ui.widget.overlay;

import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.display.Ka344a99dbf6b098920;
import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import code.miaware.client.ui.widget.Kdd908967ec41b41bcf;
import code.miaware.client.ui.widget.target.K677fcc4f631147f8f0;
import code.miaware.client.ui.widget.target.Kb4b2e3dd00d82fe84d;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_408;
import net.minecraft.class_4587;

public class Kf266cf69140b2e4d29 extends K548ca8198549ddf9d7 {
   private static final float HEIGHT = 52.0F;
   private static final float PAD = 6.0F;
   private static final float SLOT = 14.0F;
   private static final float SLOT_GAP = 2.6F;
   private final Ka91715a8c4e3f3b7c4 showAnim = new Ka91715a8c4e3f3b7c4();
   private float healthAnim;
   private float absorptionAnim;
   private class_1309 lastTarget;
   private final Kb4b2e3dd00d82fe84d healthEffects = new Kb4b2e3dd00d82fe84d();
   public final Kbd66519397e9a89967 mode = (new Kbd66519397e9a89967("Режим")).value("Линия").values("Кружок", "Линия", "New", "Test");

   public Kf266cf69140b2e4d29() {
      super(30.0F, 30.0F);
   }

   public String getName() {
      return "Target Info";
   }

   public void render(class_4587 matrixStack) {
      this.render(new class_332(mc, mc.method_22940().method_23000()), 0, 0, 0.0F);
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      if (!super.isEnabled()) {
         this.healthEffects.reset();
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      } else {
         this.showAnim.update();
         class_1309 target = this.K926b85d05ae6fb2872();
         if (target != null) {
            this.lastTarget = target;
         }

         this.showAnim.run(target != null ? (double)1.0F : (double)0.0F, this.getDuration(), this.getEasing());
         float alpha = (float)this.showAnim.getValue();
         if (!(alpha <= 0.01F) && this.lastTarget != null) {
            class_4587 ms = context.method_51448();
            float x = this.getDraggable().getX();
            float y = this.getDraggable().getY();
            float h = this.scaled(52.0F);
            float pad = this.scaled(6.0F);
            float health = Math.max(0.0F, this.lastTarget.method_6032());
            float maxHealth = Math.max(1.0F, this.lastTarget.method_6063());
            float absorption = Math.max(0.0F, this.lastTarget.method_6067());
            boolean unknown = this.lastTarget.method_5767() || !Float.isFinite(health) || health >= 999.0F;
            this.healthAnim = Ka0484a23398fb5f9ac.interpolate(this.healthAnim, class_3532.method_15363(health / maxHealth, 0.0F, 1.0F), 0.13F);
            this.absorptionAnim = Ka0484a23398fb5f9ac.interpolate(this.absorptionAnim, class_3532.method_15363(absorption / maxHealth, 0.0F, 1.0F), 0.13F);
            String name = this.lastTarget.method_5477().getString();
            String hp = unknown ? "?" : this.K8ffc05ab0db9f351af(health + absorption);
            double now = (double)System.nanoTime() / (double)1.0E9F;
            this.healthEffects.observe(this.lastTarget, health + absorption, maxHealth, !unknown, hp, now);
            float hpProgress = (float)this.healthEffects.progress(now);
            float nameSize = this.scaled(8.4F);
            float hpSize = this.scaled(8.4F);
            float heart = this.scaled(7.6F);
            float numberW = Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), hp, hpSize);
            if (hpProgress < 1.0F) {
               numberW = Math.max(numberW, Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), this.healthEffects.previous(), hpSize));
            }

            float hpW = heart + this.scaled(3.0F) + numberW;
            float barW = this.scaled(3.6F);
            float head = h - pad * 2.0F;
            float slot = this.scaled(14.0F);
            float slotGap = this.scaled(2.6F);
            float slotsW = slot * 5.0F + slotGap * 4.0F;
            float nameMax = this.scaled(78.0F);
            float nameW = Math.min(nameMax, Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), name, nameSize));
            float contentW = Math.max(slotsW, nameW + this.scaled(8.0F) + hpW);
            float w = pad + barW + this.scaled(5.0F) + head + this.scaled(7.0F) + contentW + pad;
            float cx = x + w / 2.0F;
            float cy = y + h / 2.0F;
            ms.method_22903();
            ms.method_46416(cx, cy, 0.0F);
            ms.method_22905(0.9F + 0.1F * alpha, 0.9F + 0.1F * alpha, 1.0F);
            ms.method_46416(-cx, -cy, 0.0F);
            Ka20f8cfdbc113ccdec.panel(ms, x, y, w, h, this.scaled(9.0F), alpha);
            float barX = x + pad;
            float barY = y + pad;
            float barH = h - pad * 2.0F;
            Ka20f8cfdbc113ccdec.rect(ms, barX, barY, barW, barH, barW / 2.0F, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.TRACK, alpha));
            if (!unknown) {
               float hFill = barH * this.healthAnim;
               if (hFill > 0.3F) {
                  Ka20f8cfdbc113ccdec.rect(ms, barX, barY + barH - hFill, barW, Math.max(barW, hFill), barW / 2.0F, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.ACCENT, alpha));
               }

               float aFill = Math.min(barH - hFill, barH * this.absorptionAnim);
               if (aFill > 0.3F) {
                  Ka20f8cfdbc113ccdec.rect(ms, barX, barY + barH - hFill - aFill, barW, Math.max(barW, aFill), barW / 2.0F, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.WARM, alpha));
               }
            }

            float headX = barX + barW + this.scaled(5.0F);
            float headY = y + pad;
            class_1309 var43 = this.lastTarget;
            if (var43 instanceof class_1657) {
               class_1657 player = (class_1657)var43;
               K2b5e2c5f2b9891b450.TEXTURE_RECT.drawHead(ms, player, headX, headY, head, head, 0.0F, this.scaled(6.0F), Ka20f8cfdbc113ccdec.a(Color.WHITE, alpha));
            } else {
               Ka20f8cfdbc113ccdec.rect(ms, headX, headY, head, head, this.scaled(6.0F), Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.SLOT, alpha));
               Ka20f8cfdbc113ccdec.icon(ms, "user", headX + head / 2.0F, headY + head / 2.0F, head * 0.45F, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.MUTED, alpha));
            }

            float tx = headX + head + this.scaled(7.0F);
            float right = x + w - pad;
            float nameY = y + pad + this.scaled(7.0F);
            Ka20f8cfdbc113ccdec.text(ms, Ka20f8cfdbc113ccdec.semibold(), Ka20f8cfdbc113ccdec.ellipsize(Ka20f8cfdbc113ccdec.semibold(), name, nameSize, nameMax), tx, nameY, nameSize, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.TEXT, alpha));
            float hpX = right - hpW;
            Ka20f8cfdbc113ccdec.icon(ms, "heart", hpX + heart / 2.0F, nameY, heart, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.ACCENT, alpha));
            float travel = this.scaled(7.0F) * (float)this.healthEffects.direction();
            if (hpProgress < 1.0F) {
               String previousHp = this.healthEffects.previous();
               float oldX = right - Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), previousHp, hpSize);
               Ka20f8cfdbc113ccdec.text(ms, Ka20f8cfdbc113ccdec.semibold(), previousHp, oldX, nameY + travel * hpProgress, hpSize, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.ACCENT_SOFT, alpha * (1.0F - hpProgress)));
            }

            float newX = right - Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), hp, hpSize);
            Color pulse = this.healthEffects.direction() < 0 ? Ka20f8cfdbc113ccdec.GREEN : Ka20f8cfdbc113ccdec.RED;
            float flash = (1.0F - hpProgress) * 0.7F;
            Color hpColor = new Color(Math.round((float)Ka20f8cfdbc113ccdec.ACCENT_SOFT.getRed() * (1.0F - flash) + (float)pulse.getRed() * flash), Math.round((float)Ka20f8cfdbc113ccdec.ACCENT_SOFT.getGreen() * (1.0F - flash) + (float)pulse.getGreen() * flash), Math.round((float)Ka20f8cfdbc113ccdec.ACCENT_SOFT.getBlue() * (1.0F - flash) + (float)pulse.getBlue() * flash));
            Ka20f8cfdbc113ccdec.text(ms, Ka20f8cfdbc113ccdec.semibold(), hp, newX, nameY - travel * (1.0F - hpProgress), hpSize, Ka20f8cfdbc113ccdec.a(hpColor, alpha * hpProgress));
            List<class_1799> equipment = this.Kad2e84259b70c05578(this.lastTarget);
            float sy = y + h - pad - slot;
            float sx = tx;
            List<Ka344a99dbf6b098920> slots = new ArrayList(5);

            for(int i = 0; i < 5; ++i) {
               slots.add(new Ka344a99dbf6b098920(tx + (float)i * (slot + slotGap), sy));
            }

            Ka20f8cfdbc113ccdec.rects(ms, slots, slot, slot, this.scaled(3.2F), Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.SLOT, alpha));

            for(int i = 0; i < 5; ++i) {
               class_1799 stack = i < equipment.size() ? (class_1799)equipment.get(i) : class_1799.field_8037;
               if (stack.method_7960()) {
                  Ka20f8cfdbc113ccdec.icon(ms, "cross", sx + slot / 2.0F, sy + slot / 2.0F, slot * 0.42F, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.MUTED, alpha * 0.8F));
               }

               sx += slot + slotGap;
            }

            float item = slot * 0.78F;
            sx = tx;

            try (Kdd908967ec41b41bcf items = Ka20f8cfdbc113ccdec.items(ms, alpha)) {
               for(int i = 0; i < 5; ++i) {
                  class_1799 stack = i < equipment.size() ? (class_1799)equipment.get(i) : class_1799.field_8037;
                  items.draw(stack, sx + (slot - item) / 2.0F, sy + (slot - item) / 2.0F, item);
                  sx += slot + slotGap;
               }
            }

            K677fcc4f631147f8f0.render(ms, this.healthEffects, now, barX + barW / 2.0F, barY, barH, this.scaled(1.0F), alpha);
            ms.method_22909();
            this.getDraggable().setWidth(w);
            this.getDraggable().setHeight(h);
         } else {
            this.healthEffects.reset();
            this.getDraggable().setWidth(0.0F);
            this.getDraggable().setHeight(0.0F);
         }
      }
   }

   private List<class_1799> Kad2e84259b70c05578(class_1309 entity) {
      List<class_1799> equipment = new ArrayList();
      equipment.add(entity.method_6047());
      equipment.add(entity.method_6118(class_1304.field_6169));
      equipment.add(entity.method_6118(class_1304.field_6174));
      equipment.add(entity.method_6118(class_1304.field_6172));
      equipment.add(entity.method_6118(class_1304.field_6166));
      return equipment;
   }

   private String K8ffc05ab0db9f351af(float value) {
      if (Float.isFinite(value) && !(value >= 999.0F)) {
         return Math.abs(value - (float)Math.round(value)) < 0.05F ? Integer.toString(Math.round(value)) : String.format(Locale.US, "%.1f", value);
      } else {
         return "?";
      }
   }

   private class_1309 K926b85d05ae6fb2872() {
      if (mc.field_1755 instanceof class_408) {
         return mc.field_1724;
      } else {
         class_1297 var2 = mc.field_1692;
         if (var2 instanceof class_1309) {
            class_1309 living = (class_1309)var2;
            return living;
         } else {
            return null;
         }
      }
   }

   public void mouseClicked(double mouseX, double mouseY, int button) {
   }

   public void mouseReleased(double mouseX, double mouseY, int button) {
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
   }

   public void mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
   }
}
