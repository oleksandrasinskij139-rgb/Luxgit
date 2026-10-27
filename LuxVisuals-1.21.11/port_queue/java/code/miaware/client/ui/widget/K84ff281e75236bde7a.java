package code.miaware.client.ui.widget;

import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import code.miaware.client.ui.clickgui.K87984c5639b98954c5;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_310;
import net.minecraft.class_4587;

public final class K84ff281e75236bde7a {
   private static final float CLICK_DRAG_THRESHOLD = 4.0F;
   private final K548ca8198549ddf9d7 widget;
   private final List<Kbb3fe9844db0dc7f40> rowBounds = new ArrayList();
   private boolean open;
   private boolean pressedAnchor;
   private float pressedMouseX;
   private float pressedMouseY;
   private float animation;
   private float anchorX;
   private float anchorY;
   private float anchorWidth;
   private float anchorHeight;
   private float popupX;
   private float popupY;
   private float popupWidth;
   private float popupHeight;

   public boolean isVisible() {
      return this.animation > 0.01F;
   }

   public float getBottom() {
      return this.popupY + this.popupHeight;
   }

   public K84ff281e75236bde7a(K548ca8198549ddf9d7 widget) {
      this.widget = widget;
   }

   public void setAnchor(float x, float y, float width, float height) {
      this.anchorX = x;
      this.anchorY = y;
      this.anchorWidth = width;
      this.anchorHeight = height;
   }

   public void updateState(boolean allowed) {
      if (!allowed) {
         this.open = false;
      }

      this.animation += this.open ? 0.14F : -0.14F;
      this.animation = Math.max(0.0F, Math.min(1.0F, this.animation));
      if (this.animation <= 0.01F) {
         this.rowBounds.clear();
         this.popupWidth = 0.0F;
         this.popupHeight = 0.0F;
      }

   }

   public void close() {
      this.open = false;
      this.pressedAnchor = false;
   }

   public void render(class_4587 matrixStack, String title, String badge, List<Ke7efbae1920d8c2967> entries) {
      this.rowBounds.clear();
      if (!(this.animation <= 0.01F) && !entries.isEmpty()) {
         float pad = this.widget.scaled(7.0F);
         float gap = this.widget.scaled(4.0F);
         float titleSize = this.widget.scaled(6.9F);
         float badgeSize = this.widget.scaled(5.3F);
         float labelSize = this.widget.scaled(6.15F);
         float descSize = this.widget.scaled(5.05F);
         float checkboxSize = this.widget.scaled(10.5F);
         float rowPadX = this.widget.scaled(6.0F);
         float rowGap = this.widget.scaled(0.5F);
         float headerHeight = this.widget.scaled(24.0F);
         float maxTextWidth = this.widget.getSemiBoldFont().getWidth(title, titleSize);

         for(Ke7efbae1920d8c2967 entry : entries) {
            maxTextWidth = Math.max(maxTextWidth, this.widget.getSemiBoldFont().getWidth(Ka35e7d6b31db9984f1.getInstance().translate(entry.label()), labelSize));
            if (!entry.description().isBlank()) {
               maxTextWidth = Math.max(maxTextWidth, this.widget.getMediumFont().getWidth(Ka35e7d6b31db9984f1.getInstance().translate(entry.description()), descSize));
            }
         }

         float popupInnerWidth = Math.max(this.widget.scaled(132.0F), maxTextWidth + checkboxSize + rowPadX * 2.0F + gap * 4.0F);
         float computedPopupWidth = popupInnerWidth + pad * 2.0F;
         float computedPopupHeight = pad + headerHeight;

         for(Ke7efbae1920d8c2967 entry : entries) {
            computedPopupHeight += this.K79641bace4ed4c0c0b(entry, labelSize, descSize) + rowGap;
         }

         computedPopupHeight += pad - rowGap;
         float desiredX = this.anchorX;
         float desiredY = this.anchorY + this.anchorHeight + this.widget.scaled(6.0F);
         class_310 mc = class_310.method_1551();
         float screenWidth = (float)mc.method_22683().method_4486();
         float screenHeight = (float)mc.method_22683().method_4502();
         float margin = this.widget.scaled(6.0F);
         this.popupWidth = computedPopupWidth;
         this.popupHeight = computedPopupHeight;
         this.popupX = Math.max(margin, Math.min(desiredX, screenWidth - this.popupWidth - margin));
         this.popupY = desiredY + this.popupHeight <= screenHeight - margin ? desiredY : Math.max(margin, this.anchorY - this.popupHeight - this.widget.scaled(6.0F));
         matrixStack.method_22903();
         float centerX = this.popupX + this.popupWidth / 2.0F;
         float centerY = this.popupY + this.popupHeight / 2.0F;
         matrixStack.method_46416(centerX, centerY, 0.0F);
         matrixStack.method_22905(this.animation, this.animation, 1.0F);
         matrixStack.method_46416(-centerX, -centerY, 0.0F);
         int alpha = Math.max(0, Math.min(255, (int)(255.0F * this.animation)));
         boolean isStrict = Kfae29c687712462375.getStyle().isStrict();
         float round = isStrict ? 0.0F : this.widget.scaled(4.0F);
         K2b5e2c5f2b9891b450.RECT.draw(matrixStack, this.popupX - 0.5F, this.popupY - 0.5F, this.popupWidth + 1.0F, this.popupHeight + 1.0F, round + 0.5F, new Color(80, 80, 90, Math.max(18, (int)((float)alpha * 0.72F))));
         K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrixStack, this.popupX, this.popupY, this.popupWidth, this.popupHeight, round, new Color(30, 30, 36, Math.max(24, (int)((float)alpha * 0.94F))));
         float stripeHeight = this.widget.scaled(1.45F);
         K2b5e2c5f2b9891b450.RECT.draw(matrixStack, this.popupX + pad, this.popupY + pad, this.popupWidth - pad * 2.0F, stripeHeight, stripeHeight * 0.5F, new Color(100, 100, 110, Math.max(24, (int)((float)alpha * 0.92F))));
         float badgeWidth = 0.0F;
         float headerTop = this.popupY + pad + stripeHeight + this.widget.scaled(4.0F);
         float centerXText = this.popupX + this.popupWidth / 2.0F;
         if (badge != null && !badge.isBlank()) {
            float badgePadX = this.widget.scaled(4.0F);
            float badgeHeight = this.widget.scaled(8.2F);
            badgeWidth = this.widget.getMediumFont().getWidth(badge, badgeSize) + badgePadX * 2.0F;
            float badgeX = this.popupX + pad;
            K2b5e2c5f2b9891b450.RECT.draw(matrixStack, badgeX - 0.45F, headerTop - 0.45F, badgeWidth + 0.9F, badgeHeight + 0.9F, badgeHeight * 0.45F, new Color(80, 80, 90, Math.max(18, (int)((float)alpha * 0.65F))));
            K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrixStack, badgeX, headerTop, badgeWidth, badgeHeight, badgeHeight * 0.45F, new Color(30, 30, 36, Math.max(42, (int)((float)alpha * 0.9F))));
            this.widget.getMediumFont().drawText(matrixStack, badge, badgeX + badgePadX, headerTop + this.widget.scaled(1.7F), badgeSize, K87984c5639b98954c5.text(alpha));
         }

         float titleWidth = this.widget.getSemiBoldFont().getWidth(title, titleSize);
         this.widget.getSemiBoldFont().drawText(matrixStack, title, centerXText - titleWidth / 2.0F, headerTop + this.widget.scaled(0.55F), titleSize, K87984c5639b98954c5.text(alpha));
         String subtitle = Ka35e7d6b31db9984f1.getInstance().translate("Widget settings");
         float subtitleWidth = this.widget.getMediumFont().getWidth(subtitle, descSize);
         this.widget.getMediumFont().drawText(matrixStack, subtitle, centerXText - subtitleWidth / 2.0F, headerTop + this.widget.scaled(7.8F), descSize, K87984c5639b98954c5.mutedText(Math.max(20, (int)((float)alpha * 0.82F))));
         K2b5e2c5f2b9891b450.RECT.draw(matrixStack, this.popupX + pad, this.popupY + headerHeight + this.widget.scaled(1.0F), this.popupWidth - pad * 2.0F, this.widget.scaled(0.7F), this.widget.scaled(0.3F), new Color(80, 80, 90, Math.max(16, (int)((float)alpha * 0.8F))));
         float currentY = this.popupY + pad + headerHeight;
         double mouseX = this.K82b945186317e8b2b6(mc);
         double mouseY = this.Kbd4074515165b9a1b6(mc);

         for(Ke7efbae1920d8c2967 entry : entries) {
            float rowHeight = this.K79641bace4ed4c0c0b(entry, labelSize, descSize);
            boolean hovered = this.K79a577629d02a1ca1a(mouseX, mouseY, this.popupX + pad, currentY, this.popupWidth - pad * 2.0F, rowHeight);
            this.K8c35180d6ae3904103(matrixStack, entry, this.popupX + pad, currentY, this.popupWidth - pad * 2.0F, rowHeight, labelSize, descSize, checkboxSize, hovered, alpha);
            this.rowBounds.add(new Kbb3fe9844db0dc7f40(this.popupX + pad, currentY, this.popupWidth - pad * 2.0F, rowHeight, entry));
            currentY += rowHeight + rowGap;
         }

         matrixStack.method_22909();
      }
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button, List<Ke7efbae1920d8c2967> entries) {
      if (button != 1) {
         return false;
      } else if (this.animation > 0.01F && this.K79a577629d02a1ca1a(mouseX, mouseY, this.popupX, this.popupY, this.popupWidth, this.popupHeight)) {
         for(Kbb3fe9844db0dc7f40 row : this.rowBounds) {
            if (this.K79a577629d02a1ca1a(mouseX, mouseY, row.x(), row.y(), row.width(), row.height())) {
               row.entry().toggle();
               return true;
            }
         }

         return true;
      } else if (this.K79a577629d02a1ca1a(mouseX, mouseY, this.anchorX, this.anchorY, this.anchorWidth, this.anchorHeight) && !entries.isEmpty()) {
         this.pressedAnchor = true;
         this.pressedMouseX = (float)mouseX;
         this.pressedMouseY = (float)mouseY;
         return false;
      } else {
         if (this.open) {
            this.open = false;
         }

         return false;
      }
   }

   public void mouseReleased(double mouseX, double mouseY, int button) {
      if (button != 1) {
         this.pressedAnchor = false;
      } else {
         if (this.pressedAnchor && this.K79a577629d02a1ca1a(mouseX, mouseY, this.anchorX, this.anchorY, this.anchorWidth, this.anchorHeight)) {
            float deltaX = Math.abs((float)mouseX - this.pressedMouseX);
            float deltaY = Math.abs((float)mouseY - this.pressedMouseY);
            if (deltaX <= 4.0F && deltaY <= 4.0F) {
               this.open = !this.open;
            }
         }

         this.pressedAnchor = false;
      }
   }

   private void K8c35180d6ae3904103(class_4587 matrixStack, Ke7efbae1920d8c2967 entry, float x, float y, float width, float height, float labelSize, float descSize, float checkboxSize, boolean hovered, int alpha) {
      boolean enabled = entry.state().get();
      boolean isStrict = Kfae29c687712462375.getStyle().isStrict();
      float round = isStrict ? 0.0F : this.widget.scaled(3.1F);
      K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrixStack, x, y, width, height, round, hovered ? K80ee108ea129adac11.setAlpha(K87984c5639b98954c5.fieldFill(Math.max(30, (int)((float)alpha * 0.9F))), Math.max(24, (int)((float)alpha * 0.24F))) : K80ee108ea129adac11.setAlpha(K87984c5639b98954c5.fieldFill(Math.max(18, (int)((float)alpha * 0.8F))), Math.max(14, (int)((float)alpha * 0.12F))));
      float accentWidth = this.widget.scaled(1.4F);
      K2b5e2c5f2b9891b450.RECT.draw(matrixStack, x + this.widget.scaled(2.5F), y + this.widget.scaled(3.0F), accentWidth, height - this.widget.scaled(6.0F), accentWidth * 0.5F, enabled ? K87984c5639b98954c5.accent(Math.max(18, (int)((float)alpha * 0.9F))) : new Color(100, 100, 110, Math.max(12, (int)((float)alpha * 0.75F))));
      float textX = x + this.widget.scaled(7.0F);
      float labelY = y + this.widget.scaled(3.15F);
      this.widget.getSemiBoldFont().drawText(matrixStack, Ka35e7d6b31db9984f1.getInstance().translate(entry.label()), textX, labelY, labelSize, K87984c5639b98954c5.text(alpha));
      if (!entry.description().isBlank()) {
         this.widget.getMediumFont().drawText(matrixStack, Ka35e7d6b31db9984f1.getInstance().translate(entry.description()), textX, labelY + this.widget.scaled(6.5F), descSize, K87984c5639b98954c5.mutedText(Math.max(20, (int)((float)alpha * 0.82F))));
      }

      float checkboxX = x + width - this.widget.scaled(7.0F) - checkboxSize;
      float checkboxY = y + (height - checkboxSize) / 2.0F;
      this.K28e7b406df70fb9117(matrixStack, checkboxX, checkboxY, checkboxSize, enabled, alpha);
      K2b5e2c5f2b9891b450.RECT.draw(matrixStack, x, y + height - this.widget.scaled(0.6F), width, this.widget.scaled(0.6F), this.widget.scaled(0.3F), new Color(80, 80, 90, Math.max(10, (int)((float)alpha * 0.55F))));
   }

   private void K28e7b406df70fb9117(class_4587 matrixStack, float x, float y, float size, boolean enabled, int alpha) {
      boolean isStrict = Kfae29c687712462375.getStyle().isStrict();
      float round = isStrict ? 0.0F : this.widget.scaled(2.6F);
      Color border = enabled ? K80ee108ea129adac11.setAlpha(K87984c5639b98954c5.accent(Math.max(18, (int)((float)alpha * 0.9F))), Math.max(24, (int)((float)alpha * 0.95F))) : K87984c5639b98954c5.fieldOutline(Math.max(18, (int)((float)alpha * 0.65F)));
      Color fill = enabled ? K80ee108ea129adac11.setAlpha(K87984c5639b98954c5.accent(Math.max(18, (int)((float)alpha * 0.9F))), Math.max(20, (int)((float)alpha * 0.38F))) : K87984c5639b98954c5.fieldFill(Math.max(20, (int)((float)alpha * 0.78F)));
      K2b5e2c5f2b9891b450.RECT.draw(matrixStack, x - this.widget.scaled(0.45F), y - this.widget.scaled(0.45F), size + this.widget.scaled(0.9F), size + this.widget.scaled(0.9F), round + this.widget.scaled(0.45F), border);
      K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrixStack, x, y, size, size, round, fill);
      if (enabled) {
         float checkThickness = this.widget.scaled(1.15F);
         float startX = x + size * 0.24F;
         float startY = y + size * 0.55F;
         float midX = x + size * 0.46F;
         float midY = y + size * 0.75F;
         float endX = x + size * 0.78F;
         float endY = y + size * 0.28F;
         Color checkColor = K87984c5639b98954c5.text(alpha);
         this.K66f4d05034826ebbfa(matrixStack, startX, startY, midX, midY, checkThickness, checkColor);
         this.K66f4d05034826ebbfa(matrixStack, midX, midY, endX, endY, checkThickness, checkColor);
      }
   }

   private void K66f4d05034826ebbfa(class_4587 matrixStack, float x1, float y1, float x2, float y2, float thickness, Color color) {
      float dx = x2 - x1;
      float dy = y2 - y1;
      int segments = Math.max(2, (int)(Math.hypot((double)dx, (double)dy) / (double)this.widget.scaled(0.6F)));

      for(int i = 0; i <= segments; ++i) {
         float t = (float)i / (float)segments;
         float px = x1 + dx * t;
         float py = y1 + dy * t;
         K2b5e2c5f2b9891b450.RECT.draw(matrixStack, px - thickness / 2.0F, py - thickness / 2.0F, thickness, thickness, thickness * 0.5F, color);
      }

   }

   private float K79641bace4ed4c0c0b(Ke7efbae1920d8c2967 entry, float labelSize, float descSize) {
      return entry.description().isBlank() ? Math.max(this.widget.scaled(15.0F), labelSize + this.widget.scaled(8.0F)) : Math.max(this.widget.scaled(18.5F), labelSize + descSize + this.widget.scaled(9.0F));
   }

   private double K82b945186317e8b2b6(class_310 mc) {
      return mc.field_1729.method_1603() * (double)mc.method_22683().method_4486() / (double)mc.method_22683().method_4480();
   }

   private double Kbd4074515165b9a1b6(class_310 mc) {
      return mc.field_1729.method_1604() * (double)mc.method_22683().method_4502() / (double)mc.method_22683().method_4507();
   }

   private boolean K79a577629d02a1ca1a(double mouseX, double mouseY, float x, float y, float width, float height) {
      return mouseX >= (double)x && mouseX <= (double)(x + width) && mouseY >= (double)y && mouseY <= (double)(y + height);
   }
}
