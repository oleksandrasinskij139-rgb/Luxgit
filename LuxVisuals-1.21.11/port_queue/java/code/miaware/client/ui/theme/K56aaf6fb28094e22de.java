package code.miaware.client.ui.theme;

import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.math.K75fb14cd72c582f1b2;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.fonts.K896065ea0a2bba4f47;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import code.miaware.client.ui.Kd6abad1c41237b249f;
import java.awt.Color;
import lombok.Generated;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class K56aaf6fb28094e22de extends Kd6abad1c41237b249f {
   private final Theme theme;
   private final Ka91715a8c4e3f3b7c4 hoverAnimation = new Ka91715a8c4e3f3b7c4();
   private final Ka91715a8c4e3f3b7c4 enableAnimation = new Ka91715a8c4e3f3b7c4();

   public K56aaf6fb28094e22de(Theme theme) {
      this.theme = theme;
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrixStack = context.method_51448();
      boolean hovered = this.K708a83aada75795f14((float)mouseX, (float)mouseY);
      this.enableAnimation.update();
      this.enableAnimation.run(Kb18d549c13309af9a5.currentTheme().getName().equals(this.theme.getName()) ? (double)1.0F : 0.7, 200L, Kf3b62ecfba0c3544e6.SINE_OUT);
      this.hoverAnimation.update();
      this.hoverAnimation.run(hovered ? (double)1.0F : (double)0.0F, 500L, Kf3b62ecfba0c3544e6.EXPO_OUT);
      int fullAlpha = (int)((double)this.getAlpha() * this.enableAnimation.getValue() * (double)255.0F);
      Color primary = K80ee108ea129adac11.setAlpha(this.theme.getPrimaryColor(), fullAlpha);
      Color secondary = K80ee108ea129adac11.setAlpha(this.theme.getSecondaryColor(), fullAlpha);
      boolean isStrict = Kfae29c687712462375.getStyle().isStrict();
      float round = isStrict ? 0.0F : this.getHeight() * 0.2F;
      float fontSize = (float)((double)(this.getHeight() * 0.4F) + this.hoverAnimation.getValue());
      K2b5e2c5f2b9891b450.GRADIENT_RECT.draw(matrixStack, this.getX(), this.getY(), this.getWidth(), this.getHeight(), round, primary, secondary, primary, secondary);
      K896065ea0a2bba4f47.PS_BOLD.drawCenteredText(matrixStack, this.theme.getName(), this.getX() + this.getWidth() / 2.0F, this.getY() + this.getHeight() / 2.0F - fontSize / 2.0F, fontSize, K80ee108ea129adac11.setAlpha(this.theme.getTextColor(), fullAlpha));
   }

   private boolean K708a83aada75795f14(float mouseX, float mouseY) {
      return K75fb14cd72c582f1b2.isHovered(mouseX, mouseY, this.getX(), this.getY(), this.getWidth(), this.getHeight());
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
   }

   public void mouseClicked(double mouseX, double mouseY, int button) {
   }

   public void mouseReleased(double mouseX, double mouseY, int button) {
   }

   public void mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
   }

   @Generated
   public Theme getTheme() {
      return this.theme;
   }

   @Generated
   public Ka91715a8c4e3f3b7c4 getHoverAnimation() {
      return this.hoverAnimation;
   }

   @Generated
   public Ka91715a8c4e3f3b7c4 getEnableAnimation() {
      return this.enableAnimation;
   }
}
