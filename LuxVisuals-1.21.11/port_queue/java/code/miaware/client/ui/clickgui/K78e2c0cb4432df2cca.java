package code.miaware.client.ui.clickgui;

import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import code.miaware.api.utils.other.K7d074a66e20f0c1531;
import code.miaware.api.utils.player.Kdfea46ed519f932f28;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.display.Kbab85e821f770b3523;
import code.miaware.client.features.modules.render.K30b126617e809f138b;
import code.miaware.client.services.Ke4708cf52c3b89f5ae;
import code.miaware.client.ui.theme.K6ea7546faa48739c0d;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.minecraft.class_1109;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_3414;
import net.minecraft.class_437;

public class K78e2c0cb4432df2cca extends class_437 implements K4600bef438dafc1a9c {
   private static final K78e2c0cb4432df2cca instance = new K78e2c0cb4432df2cca();
   private static final class_2960 RU_FLAG = Kc1f6f8c785f6a08448.getImage("clickgui/locale/ru");
   private static final class_2960 EN_FLAG = Kc1f6f8c785f6a08448.getImage("clickgui/locale/uk");
   private static final class_3414 DESCRIPTION_KEY_SOUND = class_3414.method_47908(class_2960.method_60655("luxvisuals", "description_key"));
   private final List<class_1109> descriptionSounds = new ArrayList();
   private final K64dfc1330d745b4d7f canvas = new K64dfc1330d745b4d7f();
   private final K87493c3dea3de1905b view = new K87493c3dea3de1905b(new K7b25658683e2c67815(this));
   private boolean open;
   private float openProgress;
   private long lastFrameNanos;
   private long openedAtMillis;

   public K78e2c0cb4432df2cca() {
      super(class_2561.method_30163(""));
   }

   protected void method_25426() {
      boolean reopening = !this.open;
      this.open = true;
      if (reopening) {
         this.openedAtMillis = System.currentTimeMillis();
      }

      this.lastFrameNanos = 0L;
      if (reopening) {
         try {
            K6ea7546faa48739c0d.getInstance().init();
         } catch (Throwable var3) {
         }

         K7d074a66e20f0c1531.playGuiSound(K7d074a66e20f0c1531.GUI_OPEN_EVENT);
      }

      super.method_25426();
   }

   public void method_25419() {
      if (this.open) {
         this.open = false;
         this.view.onClose();
      }

   }

   public void method_25432() {
      if (this.open) {
         this.open = false;
         this.view.onClose();
      }

      this.openProgress = 0.0F;
      super.method_25432();
   }

   private void K27b4a0a475156f8cbe() {
      try {
         K6ea7546faa48739c0d.getInstance().save(false);
      } catch (Throwable var2) {
      }

      this.openProgress = 0.0F;
      super.method_25419();
   }

   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
      long now = System.nanoTime();
      float dt = this.lastFrameNanos == 0L ? 0.016F : (float)(now - this.lastFrameNanos) / 1.0E9F;
      this.lastFrameNanos = now;
      float target = this.open ? 1.0F : 0.0F;
      float speed = this.open ? 11.0F : 14.0F;
      this.openProgress += (target - this.openProgress) * (1.0F - (float)Math.exp((double)(-Math.min(dt, 0.1F) * speed)));
      if (Math.abs(target - this.openProgress) < 0.004F) {
         this.openProgress = target;
      }

      if (!this.open && this.openProgress <= 0.02F) {
         this.K27b4a0a475156f8cbe();
      } else {
         if (!this.view.isTyping()) {
            Kdfea46ed519f932f28.updateMovementKeys();
         }

         Ke4708cf52c3b89f5ae.getInstance().updateScale();
         float screenW = (float)mc.method_22683().method_4486();
         float screenH = (float)mc.method_22683().method_4502();
         this.view.layout(screenW, screenH, K30b126617e809f138b.getUiScale() / 1.2F);
         float eased = 1.0F - (1.0F - this.openProgress) * (1.0F - this.openProgress);
         this.view.offsetY((1.0F - eased) * 10.0F * this.view.getScale());
         Kbab85e821f770b3523.clickGuiContext = true;

         try {
            this.canvas.begin(context);
            this.canvas.setAlpha(eased);
            this.view.render(this.canvas, this.open ? (float)mouseX : -100000.0F, this.open ? (float)mouseY : -100000.0F, dt);
            this.canvas.setAlpha(1.0F);
            this.K7f6f29b9d266111836(context, mouseX, mouseY, eased, screenH);
         } finally {
            Kbab85e821f770b3523.clickGuiContext = false;
         }

         K6ea7546faa48739c0d themeEditor = K6ea7546faa48739c0d.getInstance();
         themeEditor.setAnim(eased);
         if (themeEditor.isOpen()) {
            themeEditor.setX(screenW - themeEditor.getWidth() * themeEditor.getAnim() - ui(12.0F));
            themeEditor.setY(screenH / 2.0F - themeEditor.getHeight() / 2.0F);
         }

         themeEditor.render(context, mouseX, mouseY, delta);
      }
   }

   private void K7f6f29b9d266111836(class_332 context, int mouseX, int mouseY, float alpha, float screenH) {
      try {
         float w = ui(34.0F);
         float h = ui(24.0F);
         float x = ui(12.0F);
         float y = screenH - h - ui(12.0F);
         int texture = mc.method_1531().method_4619(Ka35e7d6b31db9984f1.getInstance().isRussian() ? RU_FLAG : EN_FLAG).method_4624();
         boolean hovered = (float)mouseX >= x && (float)mouseX <= x + w && (float)mouseY >= y && (float)mouseY <= y + h;
         int a = Math.round((float)(hovered ? 255 : 200) * alpha);
         K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(context.method_51448(), x, y, w, h, ui(2.6F), new Color(255, 255, 255, a), 0.0F, 0.0F, 1.0F, 1.0F, texture);
      } catch (Throwable var13) {
      }

   }

   private boolean Kece51d1c5dd214ec80(double mouseX, double mouseY, int button) {
      float screenH = (float)mc.method_22683().method_4502();
      float w = ui(34.0F);
      float h = ui(24.0F);
      float x = ui(12.0F);
      float y = screenH - h - ui(12.0F);
      if (!(mouseX < (double)x) && !(mouseX > (double)(x + w)) && !(mouseY < (double)y) && !(mouseY > (double)(y + h))) {
         if (button == 0) {
            Ka35e7d6b31db9984f1.getInstance().toggle();
         } else if (button == 1) {
            Ka35e7d6b31db9984f1.getInstance().toggleFeatureTranslations();
         }

         return true;
      } else {
         return false;
      }
   }

   private boolean K198bedb1d7a544f3f1(double mouseX, double mouseY) {
      K6ea7546faa48739c0d themeEditor = K6ea7546faa48739c0d.getInstance();
      return themeEditor.isOpen() && mouseX >= (double)themeEditor.getX() && mouseX <= (double)(themeEditor.getX() + themeEditor.getWidth()) && mouseY >= (double)themeEditor.getY() && mouseY <= (double)(themeEditor.getY() + themeEditor.getHeight());
   }

   private static float ui(float value) {
      return Ke4708cf52c3b89f5ae.getInstance().scaledUi(value);
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      if (!this.open) {
         return false;
      } else if (this.K198bedb1d7a544f3f1(mouseX, mouseY)) {
         K6ea7546faa48739c0d.getInstance().mouseClicked(mouseX, mouseY, button);
         return true;
      } else if (this.Kece51d1c5dd214ec80(mouseX, mouseY, button)) {
         return true;
      } else {
         this.view.mouseClicked((float)mouseX, (float)mouseY, button);
         return true;
      }
   }

   public boolean method_25406(double mouseX, double mouseY, int button) {
      this.view.mouseReleased((float)mouseX, (float)mouseY, button);
      K6ea7546faa48739c0d.getInstance().mouseReleased(mouseX, mouseY, button);
      return true;
   }

   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
      if (!this.open) {
         return true;
      } else if (this.K198bedb1d7a544f3f1(mouseX, mouseY)) {
         K6ea7546faa48739c0d.getInstance().mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
         return true;
      } else {
         this.view.mouseScrolled((float)mouseX, (float)mouseY, verticalAmount);
         return true;
      }
   }

   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
      if (!this.open) {
         return true;
      } else if (this.view.keyPressed(keyCode, modifiers)) {
         return true;
      } else {
         K6ea7546faa48739c0d themeEditor = K6ea7546faa48739c0d.getInstance();
         if (themeEditor.isOpen()) {
            if (keyCode == 256) {
               themeEditor.setOpen(false);
               return true;
            }

            themeEditor.keyPressed(keyCode, scanCode, modifiers);
         }

         if (keyCode == 256) {
            this.method_25419();
            return true;
         } else {
            K30b126617e809f138b module = K30b126617e809f138b.getInstance();
            if (module != null && module.hasBind() && keyCode == module.getBind() && System.currentTimeMillis() - this.openedAtMillis > 250L) {
               this.method_25419();
               return true;
            } else {
               return super.method_25404(keyCode, scanCode, modifiers);
            }
         }
      }
   }

   public boolean method_25400(char chr, int modifiers) {
      if (!this.open) {
         return false;
      } else if (this.view.charTyped(chr)) {
         return true;
      } else {
         K6ea7546faa48739c0d themeEditor = K6ea7546faa48739c0d.getInstance();
         return themeEditor.isOpen() && themeEditor.charTyped(chr, modifiers);
      }
   }

   public void method_48267() {
   }

   public boolean method_25422() {
      return false;
   }

   public void method_25420(class_332 context, int mouseX, int mouseY, float delta) {
   }

   public boolean method_25421() {
      return false;
   }

   protected void method_57734() {
   }

   @Generated
   public static K78e2c0cb4432df2cca getInstance() {
      return instance;
   }

   @Generated
   public boolean isOpen() {
      return this.open;
   }
}
