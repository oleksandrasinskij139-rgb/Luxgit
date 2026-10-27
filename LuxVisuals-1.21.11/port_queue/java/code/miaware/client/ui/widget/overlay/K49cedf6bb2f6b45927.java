package code.miaware.client.ui.widget.overlay;

import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1657;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_638;

public class K49cedf6bb2f6b45927 extends K548ca8198549ddf9d7 {
   private final Ka91715a8c4e3f3b7c4 showAnim = new Ka91715a8c4e3f3b7c4();
   private final Map<UUID, Float> healthAnims = new HashMap();
   private List<class_1657> lastFriends = new ArrayList();
   private List<class_1657> nearbyFriends = List.of();
   private class_638 friendsWorld;
   private int friendsTick = Integer.MIN_VALUE;
   private static final float PAD = 8.0F;
   private static final float HEADER_H = 21.0F;
   private static final float ROW_H = 27.0F;
   private static final float HEAD = 20.0F;
   private static final float NAME_SIZE = 7.4F;
   private static final float HP_SIZE = 7.2F;
   private static final float SMALL_SIZE = 5.8F;
   private static final float HEART = 7.0F;

   public K49cedf6bb2f6b45927() {
      super(30.0F, 30.0F);
   }

   public String getName() {
      return "Dryn";
   }

   public void render(class_4587 matrixStack) {
      this.render(new class_332(mc, mc.method_22940().method_23000()), 0, 0, 0.0F);
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      if (!super.isEnabled()) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      } else {
         this.showAnim.update();
         List<class_1657> friends = this.Kaf153e9b09c24731ba();
         if (friends.isEmpty() && this.hudLayoutMode() && mc.field_1724 != null) {
            friends = List.of(mc.field_1724);
         }

         if (!friends.isEmpty()) {
            this.lastFriends = friends;
         }

         this.showAnim.run(!friends.isEmpty() ? (double)1.0F : (double)0.0F, 300L, Kf3b62ecfba0c3544e6.CUBIC_OUT);
         if (!(this.showAnim.getValue() <= 0.01) && !this.lastFriends.isEmpty()) {
            this.K15935717b99f41f8e2(context, delta);
         } else {
            if (friends.isEmpty()) {
               this.lastFriends = List.of();
               this.healthAnims.clear();
            }

            this.getDraggable().setWidth(0.0F);
            this.getDraggable().setHeight(0.0F);
         }
      }
   }

   private void K15935717b99f41f8e2(class_332 context, float delta) {
      float alpha = (float)this.showAnim.getValue();
      class_4587 ms = context.method_51448();
      float x = this.getDraggable().getX();
      float y = this.getDraggable().getY();
      float pad = this.scaled(8.0F);
      float headerH = this.scaled(21.0F);
      float rowH = this.scaled(27.0F);
      float head = this.scaled(20.0F);
      float nameSize = this.scaled(7.4F);
      float hpSize = this.scaled(7.2F);
      float small = this.scaled(5.8F);
      float heart = this.scaled(7.0F);
      float content = 0.0F;
      List<K5b6b833e313873a3ac> rows = new ArrayList(this.lastFriends.size());

      for(class_1657 friend : this.lastFriends) {
         K5b6b833e313873a3ac row = new K5b6b833e313873a3ac(friend, friend.method_5477().getString(), this.K41229425b72052372a(friend), K2cd4cead78edb0188d(friend));
         rows.add(row);
         content = Math.max(content, head + this.scaled(7.0F) + Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), row.name(), nameSize) + this.scaled(4.0F) + Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.regular(), row.distance(), small) + this.scaled(10.0F) + heart + this.scaled(3.0F) + Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), row.health(), hpSize));
      }

      float w = Math.max(this.scaled(150.0F), pad * 2.0F + content);
      float h = headerH + rowH * (float)this.lastFriends.size() + this.scaled(4.0F);
      float cx = x + w / 2.0F;
      float cy = y + h / 2.0F;
      ms.method_22903();
      ms.method_46416(cx, cy, 0.0F);
      ms.method_22905(0.92F + 0.08F * alpha, 0.92F + 0.08F * alpha, 1.0F);
      ms.method_46416(-cx, -cy, 0.0F);
      Ka20f8cfdbc113ccdec.panel(ms, x, y, w, h, this.scaled(7.0F), alpha);
      float hcy = y + headerH / 2.0F;
      float icon = this.scaled(9.5F);
      Ka20f8cfdbc113ccdec.icon(ms, "friends", x + pad + icon / 2.0F, hcy, icon, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.ACCENT, alpha));
      float tickX = x + pad + icon + this.scaled(6.4F);
      Ka20f8cfdbc113ccdec.tick(ms, tickX, hcy, headerH * 0.36F, alpha);
      String title = Ka35e7d6b31db9984f1.getInstance().isRussian() ? "Друзья" : "Friends";
      Ka20f8cfdbc113ccdec.text(ms, Ka20f8cfdbc113ccdec.semibold(), title, tickX + this.scaled(6.4F), hcy, this.scaled(7.8F), Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.TEXT, alpha));
      String count = String.valueOf(this.lastFriends.size());
      float countSize = this.scaled(6.2F);
      float badgeW = Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), count, countSize) + this.scaled(8.0F);
      float badgeH = this.scaled(10.0F);
      float badgeX = x + w - pad - badgeW;
      Ka20f8cfdbc113ccdec.rect(ms, badgeX, hcy - badgeH / 2.0F, badgeW, badgeH, badgeH / 2.0F, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.TRACK, alpha));
      Ka20f8cfdbc113ccdec.text(ms, Ka20f8cfdbc113ccdec.semibold(), count, badgeX + this.scaled(4.0F), hcy, countSize, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.ACCENT_SOFT, alpha));
      Ka20f8cfdbc113ccdec.rect(ms, x + pad, y + headerH - this.scaled(0.5F), w - pad * 2.0F, this.scaled(0.6F), 0.0F, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.DIVIDER, alpha));
      float ry = y + headerH + this.scaled(2.0F);
      float right = x + w - pad;

      for(K5b6b833e313873a3ac row : rows) {
         class_1657 friend = row.player();
         String name = row.name();
         float maxHealth = Math.max(1.0F, friend.method_6063());
         float healthPct = class_3532.method_15363(friend.method_6032() / maxHealth, 0.0F, 1.0F);
         float absorptionPct = class_3532.method_15363(friend.method_6067() / maxHealth, 0.0F, 1.0F);
         float anim = class_3532.method_16439(0.12F, (Float)this.healthAnims.getOrDefault(friend.method_5667(), healthPct), healthPct);
         this.healthAnims.put(friend.method_5667(), anim);
         boolean low = healthPct <= 0.3F;
         float headY = ry + (rowH - head) / 2.0F;
         RenderSystem.enableBlend();
         K2b5e2c5f2b9891b450.TEXTURE_RECT.drawHead(ms, friend, x + pad, headY, head, head, 0.0F, this.scaled(5.0F), K80ee108ea129adac11.setAlpha(Color.WHITE, (int)(255.0F * alpha)));
         float tx = x + pad + head + this.scaled(7.0F);
         float ncy = ry + this.scaled(9.5F);
         Ka20f8cfdbc113ccdec.text(ms, Ka20f8cfdbc113ccdec.semibold(), name, tx, ncy, nameSize, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.TEXT, alpha));
         Ka20f8cfdbc113ccdec.text(ms, Ka20f8cfdbc113ccdec.regular(), row.distance(), tx + Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), name, nameSize) + this.scaled(4.0F), ncy, small, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.MUTED, alpha));
         String hp = row.health();
         float hpW = Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), hp, hpSize);
         Color hpColor = low ? Ka20f8cfdbc113ccdec.RED : Ka20f8cfdbc113ccdec.ACCENT_SOFT;
         Ka20f8cfdbc113ccdec.text(ms, Ka20f8cfdbc113ccdec.semibold(), hp, right - hpW, ncy, hpSize, Ka20f8cfdbc113ccdec.a(hpColor, alpha));
         Ka20f8cfdbc113ccdec.icon(ms, "heart", right - hpW - this.scaled(3.0F) - heart / 2.0F, ncy, heart, Ka20f8cfdbc113ccdec.a(low ? Ka20f8cfdbc113ccdec.RED : Ka20f8cfdbc113ccdec.ACCENT, alpha));
         float barY = ry + this.scaled(18.5F);
         float barH = this.scaled(2.6F);
         float barW = right - tx;
         Ka20f8cfdbc113ccdec.rect(ms, tx, barY, barW, barH, barH / 2.0F, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.TRACK, alpha));
         float fill = barW * anim;
         Ka20f8cfdbc113ccdec.rect(ms, tx, barY, Math.max(barH, fill), barH, barH / 2.0F, Ka20f8cfdbc113ccdec.a(low ? Ka20f8cfdbc113ccdec.RED : Ka20f8cfdbc113ccdec.ACCENT, alpha));
         if (absorptionPct > 0.001F) {
            float abs = Math.min(barW - fill, barW * absorptionPct);
            if (abs > 0.3F) {
               Ka20f8cfdbc113ccdec.rect(ms, tx + fill, barY, Math.max(barH, abs), barH, barH / 2.0F, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.WARM, alpha));
            }
         }

         ry += rowH;
      }

      ms.method_22909();
      this.getDraggable().setWidth(w);
      this.getDraggable().setHeight(h);
   }

   private String K41229425b72052372a(class_1657 friend) {
      return mc.field_1724 == null ? "" : Math.round(mc.field_1724.method_5739(friend)) + "m";
   }

   private static String K2cd4cead78edb0188d(class_1657 friend) {
      float value = Math.max(0.0F, friend.method_6032() + friend.method_6067());
      return Math.abs(value - (float)Math.round(value)) < 0.05F ? Integer.toString(Math.round(value)) : String.format(Locale.US, "%.1f", value);
   }

   private List<class_1657> Kaf153e9b09c24731ba() {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         if (this.friendsWorld == mc.field_1687 && this.friendsTick == mc.field_1724.field_6012) {
            return this.nearbyFriends;
         } else {
            if (this.friendsWorld != mc.field_1687) {
               this.lastFriends = List.of();
               this.healthAnims.clear();
            }

            this.friendsWorld = mc.field_1687;
            this.friendsTick = mc.field_1724.field_6012;
            List<class_1657> friends = new ArrayList();

            for(class_1657 player : mc.field_1687.method_18456()) {
               if (player != mc.field_1724 && K137d4e93c7270b0a04.getInstance().contains(player.method_5477().getString())) {
                  friends.add(player);
               }
            }

            if (!friends.isEmpty()) {
               Set<UUID> ids = new HashSet();

               for(class_1657 friend : friends) {
                  ids.add(friend.method_5667());
               }

               this.healthAnims.keySet().retainAll(ids);
            }

            this.nearbyFriends = friends;
            return this.nearbyFriends;
         }
      } else {
         this.friendsWorld = null;
         this.nearbyFriends = List.of();
         this.lastFriends = List.of();
         this.healthAnims.clear();
         return this.nearbyFriends;
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
