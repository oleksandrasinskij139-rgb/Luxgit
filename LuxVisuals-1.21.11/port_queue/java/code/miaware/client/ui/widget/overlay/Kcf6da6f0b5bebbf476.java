package code.miaware.client.ui.widget.overlay;

import code.miaware.api.module.K5c460ed4a42f0797d6;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import code.miaware.client.ui.clickgui.K87984c5639b98954c5;
import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import java.awt.Color;
import java.util.List;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class Kcf6da6f0b5bebbf476 extends K548ca8198549ddf9d7 {
   private static Color Kf4518a183d660bd96a() {
      return K87984c5639b98954c5.widgetBody(220);
   }

   private static Color OUTLINE() {
      return K87984c5639b98954c5.widgetOutline(150);
   }

   public Kcf6da6f0b5bebbf476() {
      super(2.0F, 30.0F);
   }

   public String getName() {
      return "ArrayList";
   }

   public void render(class_4587 ms) {
      if (!super.isEnabled()) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      } else {
         float x = this.getDraggable().getX();
         float y = this.getDraggable().getY();
         float width = this.getDraggable().getWidth();
         boolean isRight = x + Math.max(width, this.scaled(24.0F)) * 0.5F > (float)mc.method_22683().method_4486() * 0.5F;
         float fontSize = this.hudTextSize();
         float gap = this.getGap();
         float rowH = this.hudListRowHeight();
         float paddingH = gap * 0.85F;
         float stripeW = this.scaled(1.35F);
         float outlineW = 0.5F;
         List<Kab8a8c478f7d0077cd> enabled = K5c460ed4a42f0797d6.getInstance().getModules().stream().filter(Kab8a8c478f7d0077cd::isEnabled).filter((m) -> !(m instanceof Kfae29c687712462375)).sorted((a, b) -> Float.compare(this.getMediumFont().getWidth(b.getDisplayName(), fontSize), this.getMediumFont().getWidth(a.getDisplayName(), fontSize))).toList();
         float currentY = y;
         float maxW = 0.0F;
         if (enabled.isEmpty() && this.hudLayoutMode()) {
            String name = Ka35e7d6b31db9984f1.getInstance().translate("Array list");
            float textW = this.getMediumFont().getWidth(name, fontSize);
            float rowW = textW + paddingH * 2.0F + stripeW;
            float round = Kfae29c687712462375.getStyle().isStrict() ? 0.0F : rowH * 0.18F;
            float rowX = isRight ? x + width - rowW : x;
            K2b5e2c5f2b9891b450.RECT.draw(ms, rowX - outlineW, y - outlineW, rowW + outlineW * 2.0F, rowH + outlineW * 2.0F, round + outlineW, OUTLINE());
            K2b5e2c5f2b9891b450.BLUR_RECT.draw(ms, rowX, y, rowW, rowH, round, Kf4518a183d660bd96a());
            float stripeX = isRight ? rowX + rowW - stripeW : rowX;
            K2b5e2c5f2b9891b450.GRADIENT_RECT.draw(ms, stripeX, y, stripeW, rowH, 0.0F, Kb18d549c13309af9a5.primary(), Kb18d549c13309af9a5.primary(), Kb18d549c13309af9a5.secondary(), Kb18d549c13309af9a5.secondary());
            float textX = isRight ? rowX + paddingH : rowX + stripeW + paddingH;
            float textY = y + (rowH - fontSize) * 0.22F + this.scaled(0.8F);
            this.getMediumFont().drawGradientText(ms, name, textX, textY, fontSize, Kb18d549c13309af9a5.secondary(), Kb18d549c13309af9a5.primary(), textW / 4.0F);
            this.getDraggable().setWidth(rowW);
            this.getDraggable().setHeight(rowH);
         } else {
            for(int i = 0; i < enabled.size(); ++i) {
               Kab8a8c478f7d0077cd module = (Kab8a8c478f7d0077cd)enabled.get(i);
               String name = module.getDisplayName();
               float textW = this.getMediumFont().getWidth(name, fontSize);
               float rowW = textW + paddingH * 2.0F + stripeW;
               float round = Kfae29c687712462375.getStyle().isStrict() ? 0.0F : rowH * 0.18F;
               float rowX = isRight ? x + width - rowW : x;
               K2b5e2c5f2b9891b450.RECT.draw(ms, rowX - outlineW, currentY - outlineW, rowW + outlineW * 2.0F, rowH + outlineW * 2.0F, round + outlineW, OUTLINE());
               K2b5e2c5f2b9891b450.BLUR_RECT.draw(ms, rowX, currentY, rowW, rowH, round, Kf4518a183d660bd96a());
               float stripeX = isRight ? rowX + rowW - stripeW : rowX;
               K2b5e2c5f2b9891b450.GRADIENT_RECT.draw(ms, stripeX, currentY, stripeW, rowH, 0.0F, Kb18d549c13309af9a5.primary(), Kb18d549c13309af9a5.primary(), Kb18d549c13309af9a5.secondary(), Kb18d549c13309af9a5.secondary());
               float textX = isRight ? rowX + paddingH : rowX + stripeW + paddingH;
               float textY = currentY + (rowH - fontSize) * 0.22F + this.scaled(0.8F);
               this.getMediumFont().drawGradientText(ms, name, textX, textY, fontSize, Kb18d549c13309af9a5.secondary(), Kb18d549c13309af9a5.primary(), textW / 4.0F);
               if (rowW > maxW) {
                  maxW = rowW;
               }

               currentY += rowH;
               if (i < enabled.size() - 1) {
                  currentY += this.scaled(1.0F);
               }
            }

            this.getDraggable().setWidth(maxW > 0.0F ? maxW : this.scaled(50.0F));
            this.getDraggable().setHeight(enabled.isEmpty() ? this.scaled(10.0F) : currentY - y);
         }
      }
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.render(context.method_51448());
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
