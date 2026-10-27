package code.miaware.client.ui.widget.overlay;

import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import code.miaware.client.ui.clickgui.K87984c5639b98954c5;
import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import java.awt.Color;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class K8a6247e7413ad35b1a extends K548ca8198549ddf9d7 {
   private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

   public K8a6247e7413ad35b1a() {
      super(3.0F, 3.0F);
   }

   public String getName() {
      return "Time";
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.render(context.method_51448());
   }

   public void render(class_4587 matrixStack) {
      if (!super.isEnabled()) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      } else {
         String currentTime = LocalTime.now().format(TIME_FORMATTER);
         String text = "Time: " + currentTime;
         float fontSize = this.hudTextSize();
         float textWidth = this.getSemiBoldFont().getWidth(text, fontSize);
         float gap = this.getGap();
         float backgroundWidth = textWidth + gap * 1.75F;
         float backgroundHeight = this.hudSingleLineHeight();
         float round = Kfae29c687712462375.getStyle().isStrict() ? 0.0F : backgroundHeight * 0.2F;
         if (this.getDraggable().getX() == 3.0F && this.getDraggable().getY() == 3.0F && mc.method_22683() != null) {
            float screenWidth = (float)mc.method_22683().method_4486();
            this.getDraggable().setX((screenWidth - backgroundWidth) / 2.0F);
            this.getDraggable().setY(this.scaled(20.0F));
         }

         float x = this.getDraggable().getX();
         float y = this.getDraggable().getY();
         Color staticBgColor = K87984c5639b98954c5.widgetBody(220);
         Color staticOutlineColor = K87984c5639b98954c5.widgetOutline(150);
         float outlineWidth = 0.5F;
         K2b5e2c5f2b9891b450.RECT.draw(matrixStack, x - outlineWidth, y - outlineWidth, backgroundWidth + outlineWidth * 2.0F, backgroundHeight + outlineWidth * 2.0F, round + outlineWidth, staticOutlineColor);
         K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrixStack, x, y, backgroundWidth, backgroundHeight, round, staticBgColor);
         float textX = x + gap * 0.85F;
         float textY = y + (backgroundHeight - fontSize) * 0.42F;
         this.getSemiBoldFont().drawText(matrixStack, text, textX, textY, fontSize, Kb18d549c13309af9a5.textColor());
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
