package code.miaware.client.ui.widget;

import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.fonts.Icons;
import net.minecraft.class_4587;

public abstract class K3cd579a2686cad0597 extends K548ca8198549ddf9d7 {
   public K3cd579a2686cad0597(float x, float y) {
      super(x, y);
   }

   public abstract boolean isVisible();

   public void render(class_4587 matrixStack) {
      float x = this.getDraggable().getX();
      float y = this.getDraggable().getY();
      String valueText = " " + this.getValue();
      float fontSize = this.hudTextSize();
      String displayName = this.getDisplayName();
      float nameWidth = this.getSemiBoldFont().getWidth(displayName, fontSize);
      float valueWidth = this.getSemiBoldFont().getWidth(valueText, fontSize);
      float backgroundWidth = nameWidth + valueWidth + this.getGap() * 1.75F;
      float backgroundHeight = this.hudSingleLineHeight();
      float round = backgroundHeight * 0.3F;
      float textX = x + this.getGap() * 0.85F;
      float textY = y + (backgroundHeight - fontSize) * 0.42F;
      K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrixStack, x, y, backgroundWidth, backgroundHeight, round, Kb18d549c13309af9a5.widgetBlur());
      this.getSemiBoldFont().drawGradientText(matrixStack, displayName, textX, textY, fontSize, Kb18d549c13309af9a5.secondary(), Kb18d549c13309af9a5.primary(), nameWidth / 4.0F);
      this.getSemiBoldFont().drawText(matrixStack, valueText, textX + nameWidth, textY, fontSize, Kb18d549c13309af9a5.textColor());
      this.getDraggable().setWidth(backgroundWidth);
      this.getDraggable().setHeight(backgroundHeight);
   }

   public abstract String getValue();

   public abstract Icons getIcon();
}
