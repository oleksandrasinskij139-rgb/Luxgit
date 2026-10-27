package code.miaware.client.ui.widget.overlay;

import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.api.utils.other.Kd10bcb33e37f629df6;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.K5b5bf2e4f791eb67a2;
import code.miaware.api.utils.render.fonts.Icons;
import code.miaware.api.utils.render.fonts.K5fe84dad3db1af4bb7;
import code.miaware.api.utils.render.fonts.K896065ea0a2bba4f47;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import code.miaware.client.ui.clickgui.K87984c5639b98954c5;
import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_332;
import net.minecraft.class_408;
import net.minecraft.class_4587;

public class Kc9c316c9999e00cc23 extends K548ca8198549ddf9d7 {
   private static final int MAX_MESSAGES = 40;
   private static final int MAX_VISIBLE = 6;
   private static final long MESSAGE_LIFETIME = 6500L;
   private final List<Kac87914b91bac09cd9> messages = new ArrayList();
   private float visibility;

   public Kc9c316c9999e00cc23() {
      super(10.0F, 100.0F);
      Kdee350abc91aa294b2.getInstance().subscribe(new Ka68e0b23b1084c01e4((e) -> this.K1cb7e1cacd46322f15()));
   }

   public String getName() {
      return "Chat Widget";
   }

   private void K1cb7e1cacd46322f15() {
      long currentTime = System.currentTimeMillis();
      this.messages.removeIf((msg) -> currentTime - msg.timestamp > 6500L);
      if (this.isEnabled() && Kfae29c687712462375.getInstance().isEnabled()) {
         boolean inChat = mc.field_1755 instanceof class_408;
         float target = this.messages.isEmpty() && !inChat ? 0.0F : 1.0F;
         this.visibility = Ka0484a23398fb5f9ac.interpolate(this.visibility, target, 0.14F);
      } else {
         this.visibility = 0.0F;
      }
   }

   public boolean shouldAcceptMessage(String rawMessage) {
      if (rawMessage == null) {
         return false;
      } else {
         String message = rawMessage.trim();
         if (message.isEmpty()) {
            return false;
         } else {
            String lower = message.toLowerCase();
            if (!message.startsWith("/") && !message.startsWith(".")) {
               if (!lower.contains("joined the game") && !lower.contains("left the game")) {
                  if (!lower.startsWith("usage:") && !lower.contains("unknown command") && !lower.contains("unknown or incomplete command")) {
                     return !lower.contains("<--[here]");
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      }
   }

   public void addMessage(String rawMessage) {
      if (this.shouldAcceptMessage(rawMessage)) {
         String message = Kd10bcb33e37f629df6.protectedString(rawMessage.trim());
         long currentTime = System.currentTimeMillis();
         this.messages.removeIf((msg) -> currentTime - msg.timestamp > 6500L);
         if (this.messages.size() >= 40) {
            this.messages.removeFirst();
         }

         this.messages.add(new Kac87914b91bac09cd9(message, currentTime));
      }
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      if (this.isEnabled() && Kfae29c687712462375.getInstance().isEnabled() && !(this.visibility < 0.02F)) {
         this.render(context.method_51448());
      }
   }

   public void render(class_4587 matrixStack) {
      if (this.isEnabled() && Kfae29c687712462375.getInstance().isEnabled() && !(this.visibility < 0.02F)) {
         boolean inChat = mc.field_1755 instanceof class_408;
         if (this.messages.isEmpty() && !inChat) {
            this.getDraggable().setWidth(0.0F);
            this.getDraggable().setHeight(0.0F);
         } else {
            float x = this.getDraggable().getX();
            float y = this.getDraggable().getY();
            float padding = this.scaled(4.0F);
            float textSize = this.hudTextSize();
            float lineHeight = textSize + this.scaled(2.0F);
            float iconSize = textSize;
            float maxWidth = this.scaled(170.0F);
            float availableTextWidth = maxWidth - padding * 2.0F - textSize - this.scaled(4.0F);
            List<Kac87914b91bac09cd9> visible = new ArrayList();
            int start = Math.max(0, this.messages.size() - 6);

            for(int i = start; i < this.messages.size(); ++i) {
               visible.add((Kac87914b91bac09cd9)this.messages.get(i));
            }

            if (visible.isEmpty() && inChat) {
               visible.add(new Kac87914b91bac09cd9(Ka35e7d6b31db9984f1.getInstance().translate("Chat preview"), System.currentTimeMillis()));
            }

            float totalWidth = this.scaled(72.0F);
            K5fe84dad3db1af4bb7 font = this.getSemiBoldFont();

            for(Kac87914b91bac09cd9 message : visible) {
               String trimmed = this.K15efe7bf17a41b44bd(message.message, textSize, availableTextWidth);
               totalWidth = Math.max(totalWidth, padding * 2.0F + iconSize + this.scaled(4.0F) + font.getWidth(trimmed, textSize));
            }

            float totalHeight = padding * 2.0F + (float)visible.size() * lineHeight;
            float round = this.scaled(4.0F);
            float outlineWidth = this.scaled(0.5F);
            int alpha = Math.min(255, (int)(220.0F * this.visibility));
            K2b5e2c5f2b9891b450.RECT.draw(matrixStack, x - outlineWidth, y - outlineWidth, totalWidth + outlineWidth * 2.0F, totalHeight + outlineWidth * 2.0F, round + outlineWidth, K87984c5639b98954c5.widgetOutline(Math.min(150, alpha)));
            K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrixStack, x, y, totalWidth, totalHeight, round, K87984c5639b98954c5.widgetBody(alpha));
            String icon = String.valueOf(Icons.SIGNAL.getLetter());
            K896065ea0a2bba4f47.ICONS.drawText(matrixStack, icon, x + padding, y + padding, iconSize, Kb18d549c13309af9a5.primary());
            long currentTime = System.currentTimeMillis();
            float textX = x + padding + iconSize + this.scaled(4.0F);
            float textY = y + padding;

            for(Kac87914b91bac09cd9 message : visible) {
               String trimmed = this.K15efe7bf17a41b44bd(message.message, textSize, availableTextWidth);
               float localAlpha = inChat ? this.visibility : this.K84ed3655cb5fb9ce5c(currentTime - message.timestamp) * this.visibility;
               Color color = new Color((float)Kb18d549c13309af9a5.textColor().getRed() / 255.0F, (float)Kb18d549c13309af9a5.textColor().getGreen() / 255.0F, (float)Kb18d549c13309af9a5.textColor().getBlue() / 255.0F, localAlpha);
               K5b5bf2e4f791eb67a2.start(matrixStack, textX, textY - this.scaled(1.0F), availableTextWidth, lineHeight + this.scaled(2.0F));
               font.drawText(matrixStack, trimmed, textX, textY, textSize, color);
               K5b5bf2e4f791eb67a2.stop(matrixStack);
               textY += lineHeight;
            }

            this.getDraggable().setWidth(totalWidth);
            this.getDraggable().setHeight(totalHeight);
         }
      }
   }

   private float K84ed3655cb5fb9ce5c(long age) {
      float fadeStart = 4420.0F;
      return (float)age <= fadeStart ? 1.0F : Math.max(0.0F, 1.0F - ((float)age - fadeStart) / (6500.0F - fadeStart));
   }

   private String K15efe7bf17a41b44bd(String text, float size, float maxWidth) {
      if (this.getSemiBoldFont().getWidth(text, size) <= maxWidth) {
         return text;
      } else {
         String ellipsis = "...";

         String current;
         for(current = text; !current.isEmpty() && this.getSemiBoldFont().getWidth(current + ellipsis, size) > maxWidth; current = current.substring(0, current.length() - 1)) {
         }

         return current.isEmpty() ? ellipsis : current + ellipsis;
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
