package code.miaware.client.ui.widget.overlay;

import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import code.miaware.client.ui.clickgui.K87984c5639b98954c5;
import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import java.awt.Color;
import java.util.Map;
import java.util.UUID;
import net.minecraft.class_1259;
import net.minecraft.class_332;
import net.minecraft.class_337;
import net.minecraft.class_345;
import net.minecraft.class_3532;
import net.minecraft.class_4587;

public class Kacb204ff989454e6e2 extends K548ca8198549ddf9d7 {
   private static Kacb204ff989454e6e2 instance;
   private final Ka91715a8c4e3f3b7c4 visibilityAnimation = new Ka91715a8c4e3f3b7c4();
   private boolean lastPreview;

   public Kacb204ff989454e6e2() {
      super(3.0F, 50.0F);
      instance = this;
   }

   public static Kacb204ff989454e6e2 getInstance() {
      return instance;
   }

   public String getName() {
      return "BossBar";
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.render(context.method_51448());
   }

   public void render(class_4587 matrixStack) {
      if (!super.isEnabled()) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      } else {
         float x = this.getDraggable().getX();
         float y = this.getDraggable().getY();
         float gap = this.getGap();
         if (mc.field_1724 != null && mc.field_1705 != null) {
            class_337 bossBarHud = mc.field_1705.method_1740();
            Map<UUID, class_345> bossBars = bossBarHud != null ? bossBarHud.field_2060 : Map.of();
            boolean preview = this.hudLayoutMode();
            boolean visible = preview || !bossBars.isEmpty();
            this.visibilityAnimation.update();
            if (!preview && this.lastPreview && bossBars.isEmpty()) {
               this.visibilityAnimation.setValue((double)0.0F);
            } else {
               this.visibilityAnimation.run(visible ? (double)1.0F : (double)0.0F, 220L, Kf3b62ecfba0c3544e6.QUINT_OUT);
            }

            this.lastPreview = preview;
            float reveal = (float)this.visibilityAnimation.getValue();
            if (!visible && reveal < 0.02F) {
               this.getDraggable().setWidth(0.0F);
               this.getDraggable().setHeight(0.0F);
            } else {
               float currentY = y;
               float maxWidth = 0.0F;
               float totalHeight = 0.0F;
               if (bossBars.isEmpty()) {
                  float[] placeholder = this.K8f85e71db7babaefee(matrixStack, x, y, Ka35e7d6b31db9984f1.getInstance().translate("Boss bar"), 0.58F, Kb18d549c13309af9a5.primary((int)(255.0F * reveal)), reveal);
                  maxWidth = placeholder[2];
                  totalHeight = placeholder[3];
               } else {
                  for(class_345 bossBar : bossBars.values()) {
                     float[] dims = this.K07547c50549b236ba4(matrixStack, x, currentY, bossBar, reveal);
                     maxWidth = Math.max(maxWidth, dims[2]);
                     totalHeight += dims[3] + gap;
                     currentY += dims[3] + gap;
                  }

                  if (totalHeight > 0.0F) {
                     totalHeight -= gap;
                  }
               }

               this.getDraggable().setWidth(maxWidth);
               this.getDraggable().setHeight(totalHeight);
            }
         } else {
            this.getDraggable().setWidth(0.0F);
            this.getDraggable().setHeight(0.0F);
         }
      }
   }

   private float[] K07547c50549b236ba4(class_4587 matrixStack, float x, float y, class_345 bossBar, float reveal) {
      String name = this.K71e72e714079349405(bossBar.method_5414().getString(), this.scaled(170.0F), this.hudTextSize());
      float progress = class_3532.method_15363(bossBar.method_5412(), 0.0F, 1.0F);
      return this.K8f85e71db7babaefee(matrixStack, x, y, name, progress, this.K9e0abe3609d4f8295c(bossBar.method_5420()), reveal);
   }

   private float[] K8f85e71db7babaefee(class_4587 matrixStack, float x, float y, String name, float progress, Color barColor, float reveal) {
      float fontSize = this.hudTextSize();
      float barHeight = this.scaled(4.4F);
      float gap = this.getGap() * 0.85F;
      float textWidth = this.getMediumFont().getWidth(name, fontSize);
      float contentWidth = class_3532.method_15363(textWidth + gap * 3.8F, this.scaled(92.0F), this.scaled(156.0F));
      float barWidth = contentWidth - gap * 2.4F;
      float contentHeight = fontSize + barHeight + gap * 1.9F;
      float round = Kfae29c687712462375.getStyle().isStrict() ? 0.0F : this.scaled(4.1F);
      float centerX = x + contentWidth * 0.5F;
      float centerY = y + contentHeight * 0.5F;
      float slideX = this.scaled(12.0F) * (1.0F - reveal);
      matrixStack.method_22903();
      matrixStack.method_46416(centerX + slideX, centerY, 0.0F);
      matrixStack.method_22905(reveal, reveal, 1.0F);
      matrixStack.method_46416(-centerX, -centerY, 0.0F);
      Color outlineColor = K87984c5639b98954c5.widgetOutline(Math.min(140, (int)(120.0F * reveal)));
      Color bgColor = K87984c5639b98954c5.widgetBody(Math.min(220, (int)(200.0F * reveal)));
      K2b5e2c5f2b9891b450.RECT.draw(matrixStack, x - this.scaled(0.55F), y - this.scaled(0.55F), contentWidth + this.scaled(1.1F), contentHeight + this.scaled(1.1F), round + this.scaled(0.55F), outlineColor);
      K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrixStack, x, y, contentWidth, contentHeight, round, bgColor);
      float textX = x + (contentWidth - textWidth) * 0.5F;
      float textY = y + gap;
      this.getMediumFont().drawText(matrixStack, name, textX, textY, fontSize, Kb18d549c13309af9a5.textColor((int)(255.0F * reveal)));
      float barX = x + (contentWidth - barWidth) * 0.5F;
      float barY = y + contentHeight - gap - barHeight;
      float barRound = barHeight * 0.5F;
      K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrixStack, barX, barY, barWidth, barHeight, barRound, new Color(36, 36, 46, Math.min(220, (int)(210.0F * reveal))));
      if (progress > 0.001F) {
         float filledWidth = Math.max(barHeight, barWidth * progress);
         Color second = K80ee108ea129adac11.gradient(0.45F, barColor, Kb18d549c13309af9a5.secondary((int)(255.0F * reveal)));
         K2b5e2c5f2b9891b450.GRADIENT_RECT.draw(matrixStack, barX, barY, filledWidth, barHeight, barRound, barColor, second, barColor, second);
      }

      matrixStack.method_22909();
      return new float[]{x, y, contentWidth, contentHeight};
   }

   private String K71e72e714079349405(String name, float maxWidth, float fontSize) {
      if (this.getMediumFont().getWidth(name, fontSize) <= maxWidth) {
         return name;
      } else {
         String ellipsis = "...";

         String current;
         for(current = name; !current.isEmpty() && this.getMediumFont().getWidth(current + ellipsis, fontSize) > maxWidth; current = current.substring(0, current.length() - 1)) {
         }

         return current.isEmpty() ? ellipsis : current + ellipsis;
      }
   }

   private Color K9e0abe3609d4f8295c(class_1259.class_1260 color) {
      Color var10000;
      switch (color) {
         case field_5788 -> var10000 = new Color(238, 157, 191);
         case field_5780 -> var10000 = new Color(112, 162, 238);
         case field_5784 -> var10000 = new Color(234, 104, 104);
         case field_5785 -> var10000 = new Color(106, 206, 142);
         case field_5782 -> var10000 = new Color(238, 197, 92);
         case field_5783 -> var10000 = Kb18d549c13309af9a5.primary();
         case field_5786 -> var10000 = new Color(230, 230, 235);
         default -> throw new MatchException((String)null, (Throwable)null);
      }

      return var10000;
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
