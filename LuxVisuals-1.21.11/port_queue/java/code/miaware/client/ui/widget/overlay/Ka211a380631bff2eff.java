package code.miaware.client.ui.widget.overlay;

import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import java.awt.Color;
import net.minecraft.class_304;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class Ka211a380631bff2eff extends K548ca8198549ddf9d7 {
   private static final float KEY_SIZE = 18.0F;
   private static final float KEY_GAP = 3.0F;

   public Ka211a380631bff2eff() {
      super(3.0F, 3.0F);
   }

   public String getName() {
      return "KeyCaps";
   }

   public void render(class_4587 matrixStack) {
      this.render(new class_332(mc, mc.method_22940().method_23000()), 0, 0, 0.0F);
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      if (!super.isEnabled()) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      } else if (mc.field_1724 == null) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      } else {
         class_4587 matrices = context.method_51448();
         float x = this.getDraggable().getX();
         float y = this.getDraggable().getY();
         float keySize = this.scaled(18.0F);
         float gap = this.scaled(3.0F);
         boolean isStrict = Kfae29c687712462375.getStyle().isStrict();
         float round = isStrict ? 0.0F : this.scaled(4.0F);
         boolean wPressed = this.Kf8a7939246ddf961b7(mc.field_1690.field_1894);
         boolean aPressed = this.Kf8a7939246ddf961b7(mc.field_1690.field_1913);
         boolean sPressed = this.Kf8a7939246ddf961b7(mc.field_1690.field_1881);
         boolean dPressed = this.Kf8a7939246ddf961b7(mc.field_1690.field_1849);
         boolean spacePressed = this.Kf8a7939246ddf961b7(mc.field_1690.field_1903);
         float wX = x + keySize + gap;
         this.K30be181fbb1c4160ca(matrices, wX, y, keySize, keySize, "W", wPressed, round);
         float aY = y + keySize + gap;
         this.K30be181fbb1c4160ca(matrices, x, aY, keySize, keySize, "A", aPressed, round);
         float sX = x + keySize + gap;
         float sY = y + keySize + gap;
         this.K30be181fbb1c4160ca(matrices, sX, sY, keySize, keySize, "S", sPressed, round);
         float dX = x + (keySize + gap) * 2.0F;
         float dY = y + keySize + gap;
         this.K30be181fbb1c4160ca(matrices, dX, dY, keySize, keySize, "D", dPressed, round);
         float spaceWidth = keySize * 3.0F + gap * 2.0F;
         float spaceY = y + (keySize + gap) * 2.0F;
         this.K30be181fbb1c4160ca(matrices, x, spaceY, spaceWidth, keySize, "SPACE", spacePressed, round);
         float totalWidth = keySize * 3.0F + gap * 2.0F;
         float totalHeight = keySize * 3.0F + gap * 2.0F;
         this.getDraggable().setWidth(totalWidth);
         this.getDraggable().setHeight(totalHeight);
      }
   }

   private void K026082e8410f77c711(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrices = context.method_51448();
      float x = this.getDraggable().getX();
      float y = this.getDraggable().getY();
      float keySize = this.scaled(18.0F);
      float gap = this.scaled(3.0F);
      float round = this.scaled(3.0F);
      boolean wPressed = this.Kf8a7939246ddf961b7(mc.field_1690.field_1894);
      boolean aPressed = this.Kf8a7939246ddf961b7(mc.field_1690.field_1913);
      boolean sPressed = this.Kf8a7939246ddf961b7(mc.field_1690.field_1881);
      boolean dPressed = this.Kf8a7939246ddf961b7(mc.field_1690.field_1849);
      boolean spacePressed = this.Kf8a7939246ddf961b7(mc.field_1690.field_1903);
      float wX = x + keySize + gap;
      this.K781ba10cca10499a86(matrices, wX, y, keySize, keySize, "W", wPressed, round);
      float aY = y + keySize + gap;
      this.K781ba10cca10499a86(matrices, x, aY, keySize, keySize, "A", aPressed, round);
      float sX = x + keySize + gap;
      float sY = y + keySize + gap;
      this.K781ba10cca10499a86(matrices, sX, sY, keySize, keySize, "S", sPressed, round);
      float dX = x + (keySize + gap) * 2.0F;
      float dY = y + keySize + gap;
      this.K781ba10cca10499a86(matrices, dX, dY, keySize, keySize, "D", dPressed, round);
      float spaceWidth = keySize * 3.0F + gap * 2.0F;
      float spaceY = y + (keySize + gap) * 2.0F;
      this.K781ba10cca10499a86(matrices, x, spaceY, spaceWidth, keySize, "SPACE", spacePressed, round);
      float totalWidth = keySize * 3.0F + gap * 2.0F;
      float totalHeight = keySize * 3.0F + gap * 2.0F;
      this.getDraggable().setWidth(totalWidth);
      this.getDraggable().setHeight(totalHeight);
   }

   private void K781ba10cca10499a86(class_4587 matrices, float x, float y, float width, float height, String label, boolean pressed, float round) {
      Color bgColor = pressed ? new Color(40, 40, 50, 200) : new Color(20, 20, 30, 160);
      K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, x, y, width, height, round, bgColor);
      float fontSize = this.scaled(label.equals("SPACE") ? 5.5F : 6.0F);
      float textX = x + width / 2.0F;
      float textY = y + height / 2.0F - fontSize / 2.0F;
      Color textColor = pressed ? new Color(220, 220, 230, 255) : new Color(160, 160, 170, 255);
      this.getSemiBoldFont().drawCenteredText(matrices, label, textX, textY, fontSize, textColor);
   }

   private void K30be181fbb1c4160ca(class_4587 matrices, float x, float y, float width, float height, String label, boolean pressed, float round) {
      if (pressed) {
         Ka20f8cfdbc113ccdec.rect(matrices, x, y, width, height, this.scaled(5.0F), Ka20f8cfdbc113ccdec.ACCENT);
      } else {
         Ka20f8cfdbc113ccdec.panel(matrices, x, y, width, height, this.scaled(5.0F), 1.0F);
      }

      float fontSize = this.scaled(label.equals("SPACE") ? 5.8F : 6.6F);
      float textW = Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), label, fontSize);
      Ka20f8cfdbc113ccdec.text(matrices, Ka20f8cfdbc113ccdec.semibold(), label, x + (width - textW) / 2.0F, y + height / 2.0F, fontSize, pressed ? Ka20f8cfdbc113ccdec.BG : Ka20f8cfdbc113ccdec.TEXT);
   }

   private boolean Kf8a7939246ddf961b7(class_304 keyBinding) {
      return keyBinding.method_1434();
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
