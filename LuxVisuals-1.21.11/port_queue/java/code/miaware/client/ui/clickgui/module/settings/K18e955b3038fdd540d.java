package code.miaware.client.ui.clickgui.module.settings;

import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.math.K75fb14cd72c582f1b2;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.fonts.K896065ea0a2bba4f47;
import code.miaware.client.ui.clickgui.module.Kda23dcba76059a64da;
import code.miaware.client.ui.theme.K89eed12a5c142a77f4;
import java.awt.Color;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class K18e955b3038fdd540d extends Kda23dcba76059a64da {
   private final K89eed12a5c142a77f4 elementColor;
   private final Ka52d224feda41b0761 setting;
   private boolean draggingHue = false;
   private boolean draggingSatBright = false;
   private boolean draggingAlpha = false;
   private float hueCache = 0.0F;
   private boolean inited;

   public K18e955b3038fdd540d(Ka52d224feda41b0761 setting) {
      super(setting);
      this.setting = setting;
      this.elementColor = null;
      this.updateHeight(this.K3f6ce7b9be13bd7ce1());
      this.K2644cbdf98da3bf037();
   }

   public K18e955b3038fdd540d(K89eed12a5c142a77f4 elementColor) {
      super((Kdced9e23f1e229ba9d)null);
      this.elementColor = elementColor;
      this.setting = null;
      this.updateHeight(this.K3f6ce7b9be13bd7ce1());
   }

   private void K2644cbdf98da3bf037() {
      if (!this.inited) {
         Color color = this.K92e7787f6703a7284b();
         float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), (float[])null);
         this.hueCache = hsb[0];
         this.inited = true;
      }
   }

   private Color K92e7787f6703a7284b() {
      return this.setting != null ? (Color)this.setting.getValue() : this.elementColor.getColor();
   }

   private void K4bfbdc0dfee6925acb(Color color) {
      if (this.setting != null) {
         this.setting.setValue(color);
      } else {
         this.elementColor.setColor(color);
      }

   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 ms = context.method_51448();
      this.updateOpen();
      this.K2644cbdf98da3bf037();
      if (this.draggingHue) {
         this.K5b53cd543a587b4504((double)mouseX);
      }

      if (this.draggingSatBright) {
         this.K2168e6cd6c14a689d9((double)mouseX, (double)mouseY);
      }

      if (this.draggingAlpha) {
         this.K68a57ff3e62d3404d7((double)mouseX);
      }

      float baseHeight = this.scaled(this.K3f6ce7b9be13bd7ce1());
      float fontSize = baseHeight * 0.45F;
      int fullAlpha = (int)(this.getAlpha() * 255.0F);
      if (this.setting != null) {
         K896065ea0a2bba4f47.PS_MEDIUM.drawText(ms, this.setting.getDisplayName(), this.getX(), this.getY() + baseHeight / 2.0F - fontSize / 2.0F, fontSize, Kb18d549c13309af9a5.textColor(fullAlpha));
         float colorBoxSize = baseHeight * 0.6F;
         float colorBoxX = this.getX() + this.getWidth() - colorBoxSize - this.scaled(4.0F);
         float colorBoxY = this.getY() + baseHeight / 2.0F - colorBoxSize / 2.0F;
         float colorBoxRound = colorBoxSize * 0.15F;
         Color currentColor = this.K92e7787f6703a7284b();
         K2b5e2c5f2b9891b450.RECT.draw(ms, colorBoxX, colorBoxY, colorBoxSize, colorBoxSize, colorBoxRound, K80ee108ea129adac11.setAlpha(new Color(30, 30, 35), fullAlpha));
         float innerPadding = this.scaled(2.0F);
         K2b5e2c5f2b9891b450.RECT.draw(ms, colorBoxX + innerPadding, colorBoxY + innerPadding, colorBoxSize - innerPadding * 2.0F, colorBoxSize - innerPadding * 2.0F, colorBoxRound * 0.7F, K80ee108ea129adac11.setAlpha(currentColor, fullAlpha));
         this.updateHeight(this.K3f6ce7b9be13bd7ce1());
      }

      float animValue = this.Ke96471f3d77ac17a64();
      if ((double)animValue > (double)0.0F) {
         Color[] colors = this.K478d6b2b746136a202(animValue);
         float colorPickerRound = this.getWidth() * 0.02F;
         K2b5e2c5f2b9891b450.GRADIENT_RECT.draw(ms, this.K4ce7484689bc2d32a8(), this.K8ec8cbf2ce078be883() + this.K9aa78be00baac3185f(), this.Kfbe7edb69e5736afde(), this.K356c636c3b7cc30113(), colorPickerRound, colors[0], colors[1], colors[2], colors[3]);
         this.Ked39f9b91cd7fb5443(ms, animValue);
         this.Kd36f705bdcfa805e2f(ms, animValue);
         this.K3539d37e8ec88427be(ms);
         float alphaHeight = this.K8e4c7d595c577cd495() + this.gap();
         float extraHeight = (this.K003e90958ee6ac924b() + this.K356c636c3b7cc30113() + alphaHeight + this.gap()) * animValue;
         float baseHeightFinal = this.setting != null ? baseHeight : 0.0F;
         this.setHeight(baseHeightFinal + extraHeight);
      }

   }

   public void mouseClicked(double mouseX, double mouseY, int button) {
      if (this.setting != null) {
         float baseHeight = this.scaled(this.K3f6ce7b9be13bd7ce1());
         float colorBoxSize = baseHeight * 0.6F;
         float colorBoxX = this.getX() + this.getWidth() - colorBoxSize - this.scaled(4.0F);
         float colorBoxY = this.getY() + baseHeight / 2.0F - colorBoxSize / 2.0F;
         if (K75fb14cd72c582f1b2.isHovered(mouseX, mouseY, (double)colorBoxX, (double)colorBoxY, (double)colorBoxSize, (double)colorBoxSize)) {
            this.toggleOpen();
            return;
         }
      }

      if (!this.isNotOver()) {
         if (K75fb14cd72c582f1b2.isHovered(mouseX, mouseY, (double)this.K4ce7484689bc2d32a8(), (double)this.K8ec8cbf2ce078be883(), (double)this.Kfbe7edb69e5736afde(), (double)this.K356c636c3b7cc30113())) {
            this.draggingSatBright = true;
            this.K2168e6cd6c14a689d9(mouseX, mouseY);
         } else if (K75fb14cd72c582f1b2.isHovered(mouseX, mouseY, (double)this.K4ce7484689bc2d32a8(), (double)this.K1afd638c9ffa061929(), (double)this.Kfbe7edb69e5736afde(), (double)this.K003e90958ee6ac924b())) {
            this.draggingHue = true;
            this.K5b53cd543a587b4504(mouseX);
         } else if (K75fb14cd72c582f1b2.isHovered(mouseX, mouseY, (double)this.K4ce7484689bc2d32a8(), (double)this.K7164efa7594b98238f(), (double)this.Kfbe7edb69e5736afde(), (double)this.K8e4c7d595c577cd495())) {
            this.draggingAlpha = true;
            this.K68a57ff3e62d3404d7(mouseX);
         }

      }
   }

   public void mouseReleased(double mouseX, double mouseY, int button) {
      this.draggingHue = false;
      this.draggingSatBright = false;
      this.draggingAlpha = false;
   }

   private void K5b53cd543a587b4504(double mouseX) {
      float rel = (float)((mouseX - (double)this.K4ce7484689bc2d32a8()) / (double)this.Kfbe7edb69e5736afde());
      rel = Math.max(0.0F, Math.min(1.0F, rel));
      this.hueCache = rel;
      Color color = this.K92e7787f6703a7284b();
      float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), (float[])null);
      this.K4bfbdc0dfee6925acb(new Color(Color.HSBtoRGB(rel, hsb[1], hsb[2]), true));
   }

   private void K2168e6cd6c14a689d9(double mouseX, double mouseY) {
      float sat = (float)((mouseX - (double)this.K4ce7484689bc2d32a8()) / (double)this.Kfbe7edb69e5736afde());
      float bri = 1.0F - (float)((mouseY - (double)this.K8ec8cbf2ce078be883()) / (double)this.K356c636c3b7cc30113());
      sat = Math.max(0.0F, Math.min(1.0F, sat));
      bri = Math.max(0.0F, Math.min(1.0F, bri));
      Color color = this.K92e7787f6703a7284b();
      float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), (float[])null);
      float hue = hsb[1] != 0.0F && hsb[2] != 0.0F ? hsb[0] : this.hueCache;
      Color newColor = new Color(Color.HSBtoRGB(hue, sat, bri));
      this.K4bfbdc0dfee6925acb(new Color(newColor.getRed(), newColor.getGreen(), newColor.getBlue(), color.getAlpha()));
   }

   private void K68a57ff3e62d3404d7(double mouseX) {
      float rel = (float)((mouseX - (double)this.K4ce7484689bc2d32a8()) / (double)this.Kfbe7edb69e5736afde());
      rel = Math.max(0.0F, Math.min(1.0F, rel));
      int alpha = (int)(rel * 255.0F);
      Color c = this.K92e7787f6703a7284b();
      this.K4bfbdc0dfee6925acb(new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha));
   }

   private void Kd36f705bdcfa805e2f(class_4587 ms, float animValue) {
      float y = this.K7164efa7594b98238f() + this.K9aa78be00baac3185f();
      float h = this.K8e4c7d595c577cd495();
      Color c = this.K92e7787f6703a7284b();
      Color left = new Color(c.getRed(), c.getGreen(), c.getBlue(), 0);
      Color right = new Color(c.getRed(), c.getGreen(), c.getBlue(), (int)(this.Ke96471f3d77ac17a64() * this.getAlpha() * 255.0F));
      K2b5e2c5f2b9891b450.GRADIENT_RECT.draw(ms, this.K4ce7484689bc2d32a8(), y, this.Kfbe7edb69e5736afde(), h, h * 0.3F, left, right, left, right);
   }

   private void Ked39f9b91cd7fb5443(class_4587 ms, float animValue) {
      float y = this.K1afd638c9ffa061929() + this.K9aa78be00baac3185f();
      float h = this.K8e4c7d595c577cd495();
      K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(ms, this.K4ce7484689bc2d32a8(), y, this.Kfbe7edb69e5736afde(), h, h * 0.3F, new Color(255, 255, 255, (int)(this.Ke96471f3d77ac17a64() * this.getAlpha() * 255.0F)), 0.0F, 0.0F, 1.0F, 1.0F, K4600bef438dafc1a9c.mc.method_1531().method_4619(Kc1f6f8c785f6a08448.getImage("interface/hue")).method_4624());
   }

   private void K3539d37e8ec88427be(class_4587 ms) {
      int alpha = (int)(this.Ke96471f3d77ac17a64() * this.getAlpha() * 255.0F);
      Color currentColor = this.K92e7787f6703a7284b();
      Color cursorColor = K80ee108ea129adac11.setAlpha(Color.WHITE, alpha);
      float[] hsb = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), (float[])null);
      float lineOffset = this.scaled(4.0F);
      float lineRound = lineOffset * 0.5F;
      float lineYOffset = this.K003e90958ee6ac924b() / 2.0F - lineOffset / 2.0F;
      float circleOffset = this.scaled(2.0F);
      float circleSize = circleOffset * 2.0F;
      float satX = this.K4ce7484689bc2d32a8() + hsb[1] * this.Kfbe7edb69e5736afde();
      float briY = this.K8ec8cbf2ce078be883() + (1.0F - hsb[2]) * this.K356c636c3b7cc30113();
      K2b5e2c5f2b9891b450.RECT.draw(ms, satX - circleOffset, briY + this.K9aa78be00baac3185f() - circleOffset, circleSize, circleSize, circleSize * 0.5F, cursorColor);
      float hueX = this.K4ce7484689bc2d32a8() + this.hueCache * this.Kfbe7edb69e5736afde();
      K2b5e2c5f2b9891b450.RECT.draw(ms, hueX - lineOffset, this.K1afd638c9ffa061929() + this.K9aa78be00baac3185f() + lineYOffset, lineOffset, lineOffset, lineRound, cursorColor);
      float alphaRel = (float)currentColor.getAlpha() / 255.0F;
      float alphaX = this.K4ce7484689bc2d32a8() + alphaRel * this.Kfbe7edb69e5736afde();
      K2b5e2c5f2b9891b450.RECT.draw(ms, alphaX - lineOffset, this.K7164efa7594b98238f() + this.K9aa78be00baac3185f() + lineYOffset, lineOffset, lineOffset, lineRound, cursorColor);
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
   }

   public void mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
   }

   private float K9aa78be00baac3185f() {
      return -this.gap() * (1.0F - this.Ke96471f3d77ac17a64());
   }

   private float K8ec8cbf2ce078be883() {
      return this.getY() + (this.setting != null ? this.scaled(this.K3f6ce7b9be13bd7ce1()) : 0.0F);
   }

   private float K356c636c3b7cc30113() {
      return this.getWidth() * this.Ke96471f3d77ac17a64() * 0.36F;
   }

   private float K1afd638c9ffa061929() {
      return this.K8ec8cbf2ce078be883() + this.K356c636c3b7cc30113() + this.gap();
   }

   private float K003e90958ee6ac924b() {
      return this.scaled(5.0F) * this.Ke96471f3d77ac17a64();
   }

   private float K7164efa7594b98238f() {
      return this.K1afd638c9ffa061929() + this.K003e90958ee6ac924b() + this.gap();
   }

   private float K8e4c7d595c577cd495() {
      return this.K003e90958ee6ac924b();
   }

   private float K4ce7484689bc2d32a8() {
      return this.getX();
   }

   private float Kfbe7edb69e5736afde() {
      return this.getWidth();
   }

   private float K3f6ce7b9be13bd7ce1() {
      return 15.0F;
   }

   private float Ke96471f3d77ac17a64() {
      return this.getValue();
   }

   private Color[] K478d6b2b746136a202(float anim) {
      int alpha = (int)(anim * this.getAlpha() * 255.0F);
      Color topLeft = K80ee108ea129adac11.setAlpha(Color.WHITE, alpha);
      Color bottom = K80ee108ea129adac11.setAlpha(Color.BLACK, alpha);
      float hue = this.hueCache;
      Color hueColor = new Color(Color.HSBtoRGB(hue, 1.0F, 1.0F));
      Color topRight = K80ee108ea129adac11.setAlpha(hueColor, alpha);
      return new Color[]{topLeft, topRight, bottom, bottom};
   }
}
