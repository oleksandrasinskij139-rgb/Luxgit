package code.miaware.client.ui.theme;

import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.other.K855ca916f0dcdcda01;
import code.miaware.api.system.configs.Ke74a88931f3b59a118;
import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.math.K75fb14cd72c582f1b2;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.fonts.Icons;
import code.miaware.api.utils.render.fonts.K896065ea0a2bba4f47;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import code.miaware.client.ui.Kd6abad1c41237b249f;
import code.miaware.client.ui.clickgui.module.settings.K18e955b3038fdd540d;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import lombok.Generated;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class K6ea7546faa48739c0d extends Kd6abad1c41237b249f {
   private static final K6ea7546faa48739c0d instance = new K6ea7546faa48739c0d();
   private final List<K56aaf6fb28094e22de> themeSelectables = new ArrayList();
   private final List<K46d8fc3f7447d52c8b> themeBounds = new ArrayList();
   private final Theme defaultTheme = K047a33db8928fe0997.create(0);
   private Theme currentTheme;
   protected Theme editTheme;
   private final String placeHolderText;
   private String typingText;
   private boolean typing;
   private Kf096d588a16690dc21 deleteButton;
   private final Ka91715a8c4e3f3b7c4 openAnimation;
   private boolean open;
   private float anim;

   private int K01394acaaa02ba924c() {
      return (int)(this.openAnimation.getValue() * (double)this.anim * (double)255.0F);
   }

   public K6ea7546faa48739c0d() {
      this.currentTheme = this.defaultTheme;
      this.placeHolderText = "Enter name...";
      this.typingText = "";
      this.deleteButton = new Kf096d588a16690dc21(-1.0F, -1.0F, -1.0F);
      this.openAnimation = new Ka91715a8c4e3f3b7c4();
      this.setWidth(this.scaled(95.0F));
      this.setHeight(this.scaled(150.0F));
      K855ca916f0dcdcda01.getInstance().subscribe(new Ka68e0b23b1084c01e4(-1, (event) -> this.setWidth(this.scaled(95.0F))));
   }

   public void init() {
      this.K488f59e2d79c6ae997();
      this.selectPreset(K2c59a7f522ec4ea35b.selected());
   }

   private void K488f59e2d79c6ae997() {
      Ke74a88931f3b59a118.getInstance().refresh();
   }

   public void save(boolean last) {
      if (!last) {
         Ke74a88931f3b59a118.getInstance().saveAll();
      } else if (this.currentTheme != null) {
         Ke74a88931f3b59a118.getInstance().saveLastSelected(this.currentTheme);
      }

   }

   public void load() {
      Ke74a88931f3b59a118.getInstance().refresh();
      this.editTheme = null;
      K2c59a7f522ec4ea35b.ensureLoaded();
      this.selectPreset(K2c59a7f522ec4ea35b.selected());
   }

   public void selectPreset(int index) {
      int safe = index >= 0 && index < K2c59a7f522ec4ea35b.PRESETS.size() ? index : 0;
      String name = ((K51ad80c57a5258fb43)K2c59a7f522ec4ea35b.PRESETS.get(safe)).nameEn();
      this.currentTheme = (Theme)this.themeSelectables.stream().map(K56aaf6fb28094e22de::getTheme).filter((t) -> t.getName().equals(name)).findFirst().orElseGet(() -> K047a33db8928fe0997.create(safe));
      Kb18d549c13309af9a5.clearThemeCache();
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.openAnimation.update();
      this.openAnimation.run(this.open ? (double)1.0F : (double)0.0F, 100L, Kf3b62ecfba0c3544e6.SINE_OUT);
      if (!(this.openAnimation.getValue() <= 0.1)) {
         this.themeBounds.clear();
         boolean isStrict = Kfae29c687712462375.getStyle().isStrict();
         float round = isStrict ? 0.0F : this.getWidth() * 0.05F;
         float headerHeight = this.scaled(this.Kdfac0a86ebd50c3962());
         float headerFontSize = headerHeight * 0.52F;
         Ka35e7d6b31db9984f1 localization = Ka35e7d6b31db9984f1.getInstance();
         String text = localization.translate("Theme Editor");
         float textWidth = K896065ea0a2bba4f47.PS_BOLD.getWidth(text, headerFontSize);
         this.setWidth(textWidth * 1.5F);
         class_4587 matrixStack = context.method_51448();
         K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrixStack, this.getX(), this.getY(), this.getWidth(), this.getHeight(), round, Kb18d549c13309af9a5.blur(this.K01394acaaa02ba924c()));
         float iconSize = headerHeight * 0.6F;
         float iconX = this.getX() + this.getWidth() / 2.0F - (textWidth + iconSize + this.scaled(4.0F)) / 2.0F;
         float iconY = this.getY() + headerHeight / 2.0F - iconSize / 2.0F;
         K896065ea0a2bba4f47.ICONS_DESHUX.drawGradientText(matrixStack, "p", iconX, iconY, iconSize, Kb18d549c13309af9a5.secondary(this.K01394acaaa02ba924c()), Kb18d549c13309af9a5.primary(this.K01394acaaa02ba924c()), iconSize / 2.0F);
         float textX = iconX + iconSize + this.scaled(4.0F);
         K896065ea0a2bba4f47.PS_BOLD.drawGradientText(matrixStack, text, textX, this.getY() + headerHeight / 2.0F - headerFontSize / 2.0F, headerFontSize, Kb18d549c13309af9a5.secondary(this.K01394acaaa02ba924c()), Kb18d549c13309af9a5.primary(this.K01394acaaa02ba924c()), K896065ea0a2bba4f47.PS_BOLD.getWidth(text, headerFontSize) / 4.0F);
         this.K83fc6dcbc3bd8fd086(context, mouseX, mouseY, delta);
         float xOffset = this.getX() + this.offset();
         float widthOffset = this.getWidth() - this.offset() * 2.0F;
         float themeY = this.gap() + headerHeight + this.Ke2541c761975cf55ed()[3];
         if (this.editTheme == null) {
            for(K56aaf6fb28094e22de theme : this.themeSelectables) {
               theme.setAlpha((float)(this.openAnimation.getValue() * (double)this.anim));
               theme.setX(xOffset);
               theme.setY(this.getY() + themeY);
               theme.setWidth(widthOffset);
               theme.setHeight(this.scaled(17.0F));
               theme.render(context, mouseX, mouseY, delta);
               themeY += theme.getHeight() + this.gap();
            }

            this.setHeight(themeY);
         } else {
            float elementY = themeY;
            float elementHeight = this.scaled(this.Kda4cbd971c189cbe77());
            float textSize = elementHeight * 0.6F;
            float textY = this.getY() + elementHeight / 2.0F - textSize / 2.0F;
            float elementWidth = this.getWidth() - this.offset();
            float colorSize = elementHeight * 0.8F;
            float colorX = this.getX() + elementWidth - colorSize;
            float colorY = this.getY() + elementHeight / 2.0F - colorSize / 2.0F;
            float roundColor = isStrict ? 0.0F : colorSize * 0.2F;

            for(K89eed12a5c142a77f4 elementColor : this.editTheme.getElementColors()) {
               float height = elementHeight;
               K896065ea0a2bba4f47.PS_MEDIUM.drawText(matrixStack, localization.translate(elementColor.getName()), xOffset, textY + elementY, textSize, Kb18d549c13309af9a5.textColor(this.K01394acaaa02ba924c()));
               K2b5e2c5f2b9891b450.RECT.draw(matrixStack, colorX, colorY + elementY, colorSize, colorSize, roundColor, K80ee108ea129adac11.setAlpha(elementColor.getColor(), this.K01394acaaa02ba924c()));
               K18e955b3038fdd540d colorComponent = elementColor.getColorComponent();
               colorComponent.updateOpen();
               float cAnim = colorComponent.getValue();
               if ((double)cAnim > (double)0.0F) {
                  colorComponent.setX(xOffset);
                  colorComponent.setY(textY + elementY + elementHeight);
                  colorComponent.setWidth(widthOffset);
                  colorComponent.setAlpha((float)this.K01394acaaa02ba924c() / 255.0F);
                  colorComponent.render(context, mouseX, mouseY, delta);
                  height = elementHeight + colorComponent.getHeight() + this.gap() * 2.0F * cAnim;
               }

               this.themeBounds.add(new K46d8fc3f7447d52c8b(xOffset, colorY + elementY, elementWidth, height, elementColor));
               elementY += height + this.gap();
            }

            this.setHeight(elementY);
         }

      }
   }

   private void K83fc6dcbc3bd8fd086(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrixStack = context.method_51448();
      boolean isStrict = Kfae29c687712462375.getStyle().isStrict();
      float[] placeCoords = this.Ke2541c761975cf55ed();
      float placeX = placeCoords[0];
      float placeY = placeCoords[1];
      float placeWidth = placeCoords[2];
      float placeHeight = placeCoords[3];
      float placeFontSize = placeHeight * 0.4F;
      float placeRound = isStrict ? 0.0F : placeHeight * 0.2F;
      String cursor = this.typing && System.currentTimeMillis() % 1000L > 500L ? "_" : " ";
      String typeText = this.typingText.isEmpty() && !this.typing ? Ka35e7d6b31db9984f1.getInstance().translate("Enter name...") : this.typingText + cursor;
      boolean edit = this.editTheme != null;
      if (edit) {
         typeText = this.editTheme.getName();
      }

      K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrixStack, placeX, placeY, placeWidth, placeHeight, placeRound, Kb18d549c13309af9a5.widgetBlur(this.K01394acaaa02ba924c()));
      float paletteIconSize = placeHeight * 0.55F;
      float paletteIconPadding = this.offset();
      float paletteIconX = placeX + paletteIconPadding;
      float textX = paletteIconX + paletteIconSize + paletteIconPadding;
      K896065ea0a2bba4f47.PS_BOLD.drawText(matrixStack, typeText, textX, placeY + placeHeight / 2.0F - placeFontSize / 2.0F, placeFontSize, Kb18d549c13309af9a5.textColor(this.K01394acaaa02ba924c()));
      float paletteIconY = placeY + placeHeight / 2.0F - paletteIconSize / 2.0F;
      K896065ea0a2bba4f47.ICONS_DESHUX.drawGradientText(matrixStack, "p", paletteIconX, paletteIconY, paletteIconSize, Kb18d549c13309af9a5.primary(this.K01394acaaa02ba924c()), Kb18d549c13309af9a5.secondary(this.K01394acaaa02ba924c()), paletteIconSize / 2.0F);
      if (edit && this.themeSelectables.size() > 1) {
         float margin = this.gap();
         float deleteSize = placeHeight - margin * 2.0F;
         float deleteX = placeX + this.getWidth() - deleteSize - margin - this.offset() * 2.0F;
         float deleteY = placeY + margin;
         float iconSize = deleteSize * 0.5F;
         K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrixStack, deleteX, deleteY, deleteSize, deleteSize, placeRound, Kb18d549c13309af9a5.blur(this.K01394acaaa02ba924c()));
         K896065ea0a2bba4f47.ICONS.drawCenteredText(matrixStack, Icons.CROSS.getLetter(), deleteX + deleteSize / 2.0F, deleteY + deleteSize / 2.0F - iconSize / 2.0F, iconSize, Kb18d549c13309af9a5.textColor(this.K01394acaaa02ba924c()), 0.1F);
         this.deleteButton = new Kf096d588a16690dc21(deleteX, deleteY, deleteSize);
      }

   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
      if (this.open) {
         if (this.typing && this.editTheme == null) {
            switch (keyCode) {
               case 257:
                  if (!this.typingText.isEmpty()) {
                     boolean exists = this.themeSelectables.stream().anyMatch((ts) -> ts.getTheme().getName().equalsIgnoreCase(this.typingText));
                     if (!exists) {
                        Theme newTheme = new Theme(Ke74a88931f3b59a118.getInstance().safeFileName(this.typingText));
                        newTheme.getElementColors().clear();

                        for(K89eed12a5c142a77f4 elementColor : this.currentTheme.getElementColors()) {
                           newTheme.getElementColors().add(new K89eed12a5c142a77f4(elementColor.getName(), elementColor.getColor()));
                        }

                        this.themeSelectables.add(new K56aaf6fb28094e22de(newTheme));
                        this.currentTheme = newTheme;
                     }

                     this.typingText = "";
                     this.typing = false;
                  }
                  break;
               case 259:
                  if (!this.typingText.isEmpty()) {
                     this.typingText = this.typingText.substring(0, this.typingText.length() - 1);
                  }
            }
         }

      }
   }

   public void mouseClicked(double mouseX, double mouseY, int button) {
      if (this.open) {
         float headerHeight = this.scaled(this.Kdfac0a86ebd50c3962());
         float iconSize = headerHeight * 0.6F;
         float headerFontSize = headerHeight * 0.52F;
         String text = Ka35e7d6b31db9984f1.getInstance().translate("Theme Editor");
         float textWidth = K896065ea0a2bba4f47.PS_BOLD.getWidth(text, headerFontSize);
         float iconX = this.getX() + this.getWidth() / 2.0F - (textWidth + iconSize + this.scaled(4.0F)) / 2.0F;
         float iconY = this.getY() + headerHeight / 2.0F - iconSize / 2.0F;
         if (K75fb14cd72c582f1b2.isHovered(mouseX, mouseY, (double)iconX, (double)iconY, (double)iconSize, (double)iconSize)) {
            this.open = false;
         } else {
            if (this.editTheme != null) {
               if (this.themeSelectables.size() > 1 && K75fb14cd72c582f1b2.isHovered(mouseX, mouseY, (double)this.deleteButton.x, (double)this.deleteButton.y, (double)this.deleteButton.size, (double)this.deleteButton.size)) {
                  if (this.themeSelectables.removeIf((ts) -> ts.getTheme() == this.editTheme)) {
                     Ke74a88931f3b59a118.getInstance().remove(this.editTheme.getName());
                     this.editTheme = null;
                  }

                  return;
               }

               if (K75fb14cd72c582f1b2.isHovered(mouseX, mouseY, (double)this.getX(), (double)this.getY(), (double)this.getWidth(), (double)this.scaled(this.Kdfac0a86ebd50c3962()))) {
                  this.editTheme = null;
                  return;
               }

               for(K46d8fc3f7447d52c8b themeBound : this.themeBounds) {
                  K18e955b3038fdd540d colorComponent = themeBound.elementColor.getColorComponent();
                  if (K75fb14cd72c582f1b2.isHovered(mouseX, mouseY, (double)themeBound.x, (double)themeBound.y, (double)themeBound.width, (double)this.scaled(this.Kda4cbd971c189cbe77()))) {
                     colorComponent.toggleOpen();
                     return;
                  }

                  if (K75fb14cd72c582f1b2.isHovered(mouseX, mouseY, (double)themeBound.x, (double)themeBound.y, (double)themeBound.width, (double)themeBound.height)) {
                     colorComponent.mouseClicked(mouseX, mouseY, button);
                  }
               }
            } else {
               if (K75fb14cd72c582f1b2.isHovered(mouseX, mouseY, (double)this.Ke2541c761975cf55ed()[0], (double)this.Ke2541c761975cf55ed()[1], (double)this.Ke2541c761975cf55ed()[2], (double)this.Ke2541c761975cf55ed()[3])) {
                  this.typing = !this.typing;
                  return;
               }

               for(K56aaf6fb28094e22de theme : this.themeSelectables) {
                  if (K75fb14cd72c582f1b2.isHovered(mouseX, mouseY, (double)theme.getX(), (double)theme.getY(), (double)theme.getWidth(), (double)theme.getHeight())) {
                     if (button == 1) {
                        this.editTheme = theme.getTheme();
                     } else if (button == 0) {
                        int preset = IntStream.range(0, K2c59a7f522ec4ea35b.PRESETS.size()).filter((i) -> ((K51ad80c57a5258fb43)K2c59a7f522ec4ea35b.PRESETS.get(i)).nameEn().equals(theme.getTheme().getName())).findFirst().orElse(0);
                        K2c59a7f522ec4ea35b.select(preset);
                     }
                  }
               }
            }

         }
      }
   }

   public void mouseReleased(double mouseX, double mouseY, int button) {
      if (this.open) {
         if (this.editTheme != null) {
            for(K46d8fc3f7447d52c8b themeBound : this.themeBounds) {
               themeBound.elementColor.getColorComponent().mouseReleased(mouseX, mouseY, button);
            }
         }

      }
   }

   public boolean charTyped(char chr, int modifiers) {
      if (!this.open) {
         return false;
      } else if (this.typing && this.typingText.length() < 10) {
         this.typingText = this.typingText + chr;
         return true;
      } else {
         return false;
      }
   }

   private float[] Ke2541c761975cf55ed() {
      float x = this.getX() + this.offset();
      float y = this.getY() + this.scaled(this.Kdfac0a86ebd50c3962());
      float width = this.getWidth() - this.offset() * 2.0F;
      float height = this.scaled(this.Kddf9dc3534a5c06206());
      return new float[]{x, y, width, height};
   }

   private float Kda4cbd971c189cbe77() {
      return 12.0F;
   }

   private float Kdfac0a86ebd50c3962() {
      return 19.0F;
   }

   private float Kddf9dc3534a5c06206() {
      return 19.0F;
   }

   public void mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
   }

   @Generated
   public List<K56aaf6fb28094e22de> getThemeSelectables() {
      return this.themeSelectables;
   }

   @Generated
   public List<K46d8fc3f7447d52c8b> getThemeBounds() {
      return this.themeBounds;
   }

   @Generated
   public Theme getDefaultTheme() {
      return this.defaultTheme;
   }

   @Generated
   public Theme getCurrentTheme() {
      return this.currentTheme;
   }

   @Generated
   public Theme getEditTheme() {
      return this.editTheme;
   }

   @Generated
   public String getPlaceHolderText() {
      Objects.requireNonNull(this);
      return "Enter name...";
   }

   @Generated
   public String getTypingText() {
      return this.typingText;
   }

   @Generated
   public boolean isTyping() {
      return this.typing;
   }

   @Generated
   public Kf096d588a16690dc21 getDeleteButton() {
      return this.deleteButton;
   }

   @Generated
   public Ka91715a8c4e3f3b7c4 getOpenAnimation() {
      return this.openAnimation;
   }

   @Generated
   public boolean isOpen() {
      return this.open;
   }

   @Generated
   public float getAnim() {
      return this.anim;
   }

   @Generated
   public static K6ea7546faa48739c0d getInstance() {
      return instance;
   }

   @Generated
   public void setOpen(boolean open) {
      this.open = open;
   }

   @Generated
   public void setAnim(float anim) {
      this.anim = anim;
   }
}
