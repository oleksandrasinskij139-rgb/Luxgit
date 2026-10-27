package code.miaware.client.ui.widget.overlay;

import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.fonts.Icons;
import code.miaware.api.utils.render.fonts.K896065ea0a2bba4f47;
import code.miaware.client.ui.clickgui.K87984c5639b98954c5;
import code.miaware.client.ui.widget.K3cd579a2686cad0597;
import java.awt.Color;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class Kd3a6f02eec093fff34 extends K3cd579a2686cad0597 {
   public Kd3a6f02eec093fff34() {
      super(50.0F, 100.0F);
   }

   public String getName() {
      return "FPS";
   }

   public String getValue() {
      return String.valueOf(mc.method_47599());
   }

   public Icons getIcon() {
      return Icons.PERFORMANCE;
   }

   public boolean isVisible() {
      return true;
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.render(context.method_51448());
   }

   public void render(class_4587 matrixStack) {
      if (this.isVisible()) {
         float x = this.getDraggable().getX();
         float y = this.getDraggable().getY();
         String name = this.getDisplayName();
         String value = this.getValue();
         float fontSize = this.hudTextSize();
         float nameWidth = this.getSemiBoldFont().getWidth(name, fontSize);
         float valueWidth = this.getSemiBoldFont().getWidth(value, fontSize);
         float gap = this.getGap();
         float spacing = gap * 0.55F;
         float backgroundWidth = nameWidth + spacing + valueWidth + gap * 1.75F;
         float backgroundHeight = this.hudSingleLineHeight();
         float round = backgroundHeight * 0.2F;
         Color staticBgColor = K87984c5639b98954c5.widgetBody(220);
         Color staticOutlineColor = K87984c5639b98954c5.widgetOutline(150);
         float outlineWidth = 0.5F;
         K2b5e2c5f2b9891b450.RECT.draw(matrixStack, x - outlineWidth, y - outlineWidth, backgroundWidth + outlineWidth * 2.0F, backgroundHeight + outlineWidth * 2.0F, round + outlineWidth, staticOutlineColor);
         K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrixStack, x, y, backgroundWidth, backgroundHeight, round, staticBgColor);
         float textX = x + gap * 0.85F;
         float textY = y + (backgroundHeight - fontSize) * 0.42F;
         Icons icon = this.getIcon();
         if (icon != null) {
            String iconChar = "X";
            float iconWidth = K896065ea0a2bba4f47.ICONS_NURIK.getWidth(iconChar, fontSize);
            K896065ea0a2bba4f47.ICONS_NURIK.drawText(matrixStack, iconChar, textX, textY, fontSize, Kb18d549c13309af9a5.primary());
            textX += iconWidth + spacing;
         } else {
            this.getSemiBoldFont().drawGradientText(matrixStack, name, textX, textY, fontSize, Kb18d549c13309af9a5.primary(), Kb18d549c13309af9a5.secondary(), nameWidth / 4.0F);
            textX += nameWidth + spacing;
         }

         this.getSemiBoldFont().drawText(matrixStack, value, textX, textY, fontSize, Kb18d549c13309af9a5.textColor());
         this.getDraggable().setWidth(backgroundWidth);
         this.getDraggable().setHeight(backgroundHeight);
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
