package code.miaware.client.ui.clickgui;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.fonts.K896065ea0a2bba4f47;
import code.miaware.client.config.K3e319beb26054fd921;
import code.miaware.client.network.K3e5d28a93090bc4a75;
import code.miaware.client.services.Ke4708cf52c3b89f5ae;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.util.List;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_4587;

public class K9ba98127da70d67819 extends class_437 implements K4600bef438dafc1a9c {
   private static K9ba98127da70d67819 instance;
   private final Ka91715a8c4e3f3b7c4 openAnimation = new Ka91715a8c4e3f3b7c4();
   private boolean closing = false;
   private K7b670ccd156aee8fe5 currentMode;
   private boolean waitingForConfigSelect;
   private boolean isOneTimeKey;
   private List<String> savedConfigs;
   private float configsScrollOffset;
   private String keyInput;
   private boolean keyInputFocused;
   private String notificationMessage;
   private long notificationTime;
   private final Ka91715a8c4e3f3b7c4 notificationAnimation;

   private K9ba98127da70d67819() {
      super(class_2561.method_30163("Cloud Config"));
      this.currentMode = K7b670ccd156aee8fe5.UPLOAD;
      this.waitingForConfigSelect = false;
      this.isOneTimeKey = false;
      this.configsScrollOffset = 0.0F;
      this.keyInput = "";
      this.keyInputFocused = false;
      this.notificationMessage = "";
      this.notificationTime = 0L;
      this.notificationAnimation = new Ka91715a8c4e3f3b7c4();
   }

   public static K9ba98127da70d67819 getInstance() {
      if (instance == null) {
         instance = new K9ba98127da70d67819();
      }

      return instance;
   }

   public void setSelectedTab(int tab) {
      if (tab == 0) {
         this.currentMode = K7b670ccd156aee8fe5.UPLOAD;
      } else {
         this.currentMode = K7b670ccd156aee8fe5.DOWNLOAD;
      }

   }

   public void setKeyType(int keyType) {
      this.waitingForConfigSelect = true;
      this.isOneTimeKey = keyType == 0;
      this.savedConfigs = K3e319beb26054fd921.getInstance().getSavedConfigs();
   }

   protected void method_25426() {
      super.method_25426();
      this.openAnimation.run((double)1.0F, 400L, Kf3b62ecfba0c3544e6.EXPO_OUT);
      this.savedConfigs = K3e319beb26054fd921.getInstance().getSavedConfigs();
   }

   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
      super.method_25394(context, mouseX, mouseY, delta);
      class_4587 matrices = context.method_51448();
      this.openAnimation.update();
      double alpha = this.openAnimation.getValue();
      if (this.closing && alpha <= 0.01) {
         mc.method_1507(K78e2c0cb4432df2cca.getInstance());
      } else {
         float windowWidth = (float)mc.method_22683().method_4486();
         float windowHeight = (float)mc.method_22683().method_4502();
         K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, 0.0F, 0.0F, windowWidth, windowHeight, 0.0F, new Color(0, 0, 0, (int)((double)100.0F * alpha)));
         float guiWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(500.0F);
         float guiHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(400.0F);
         float guiX = (windowWidth - guiWidth) / 2.0F;
         float guiY = (windowHeight - guiHeight) / 2.0F;
         float radius = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
         K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, guiX, guiY, guiWidth, guiHeight, radius, new Color(26, 26, 35, (int)((double)240.0F * alpha)));
         K2b5e2c5f2b9891b450.RECT.draw(matrices, guiX - 1.0F, guiY - 1.0F, guiWidth + 2.0F, guiHeight + 2.0F, radius + 1.0F, new Color(80, 80, 90, (int)((double)150.0F * alpha)));
         float contentY = guiY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
         String title = "Cloud Config";
         float titleSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(16.0F);
         float titleX = guiX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
         K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, title, titleX, contentY, titleSize, new Color(255, 255, 255, (int)((double)255.0F * alpha)));
         contentY += Ke4708cf52c3b89f5ae.getInstance().scaledUi(35.0F);
         float tabWidth = guiWidth / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(30.0F);
         float tabHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(35.0F);
         float tabX = guiX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
         boolean hoverUpload = (float)mouseX >= tabX && (float)mouseX <= tabX + tabWidth && (float)mouseY >= contentY && (float)mouseY <= contentY + tabHeight;
         Color uploadBg = this.currentMode == K7b670ccd156aee8fe5.UPLOAD ? Kb18d549c13309af9a5.primary((int)((double)200.0F * alpha)) : (hoverUpload ? new Color(40, 40, 46, (int)((double)255.0F * alpha)) : new Color(30, 30, 36, (int)((double)255.0F * alpha)));
         K2b5e2c5f2b9891b450.RECT.draw(matrices, tabX, contentY, tabWidth, tabHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), uploadBg);
         String uploadText = "Загрузить конфиг";
         float uploadTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
         float uploadTextX = tabX + (tabWidth - K896065ea0a2bba4f47.SF_MEDIUM.getWidth(uploadText, uploadTextSize)) / 2.0F;
         float uploadTextY = contentY + (tabHeight - uploadTextSize) / 2.0F;
         K896065ea0a2bba4f47.SF_MEDIUM.drawText(matrices, uploadText, uploadTextX, uploadTextY, uploadTextSize, new Color(255, 255, 255, (int)((double)255.0F * alpha)));
         float downloadTabX = tabX + tabWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
         boolean hoverDownload = (float)mouseX >= downloadTabX && (float)mouseX <= downloadTabX + tabWidth && (float)mouseY >= contentY && (float)mouseY <= contentY + tabHeight;
         Color downloadBg = this.currentMode == K7b670ccd156aee8fe5.DOWNLOAD ? Kb18d549c13309af9a5.primary((int)((double)200.0F * alpha)) : (hoverDownload ? new Color(40, 40, 46, (int)((double)255.0F * alpha)) : new Color(30, 30, 36, (int)((double)255.0F * alpha)));
         K2b5e2c5f2b9891b450.RECT.draw(matrices, downloadTabX, contentY, tabWidth, tabHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), downloadBg);
         String downloadText = "Скачать конфиг";
         float downloadTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
         float downloadTextX = downloadTabX + (tabWidth - K896065ea0a2bba4f47.SF_MEDIUM.getWidth(downloadText, downloadTextSize)) / 2.0F;
         float downloadTextY = contentY + (tabHeight - downloadTextSize) / 2.0F;
         K896065ea0a2bba4f47.SF_MEDIUM.drawText(matrices, downloadText, downloadTextX, downloadTextY, downloadTextSize, new Color(255, 255, 255, (int)((double)255.0F * alpha)));
         contentY += tabHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(25.0F);
         if (this.currentMode == K7b670ccd156aee8fe5.UPLOAD) {
            this.K1638f6c80d954bbb0c(matrices, guiX, contentY, guiWidth, mouseX, mouseY, alpha);
         } else {
            this.Kf65bf86f2fe3c8f0a3(matrices, guiX, contentY, guiWidth, mouseX, mouseY, alpha);
         }

         this.Kfe73c8a0885292bf36(matrices, guiX, guiY, guiWidth, guiHeight, alpha);
      }
   }

   private void K1638f6c80d954bbb0c(class_4587 matrices, float guiX, float contentY, float guiWidth, int mouseX, int mouseY, double alpha) {
      float buttonWidth = guiWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
      float buttonHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
      float buttonX = guiX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
      String instruction = this.waitingForConfigSelect ? "Выберите конфиг из списка ниже" : "Нажмите на кнопку и выберите конфиг";
      float instrSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
      K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, instruction, buttonX, contentY, instrSize, new Color(170, 170, 170, (int)((double)255.0F * alpha)));
      contentY += Ke4708cf52c3b89f5ae.getInstance().scaledUi(25.0F);
      boolean hoverOneTime = (float)mouseX >= buttonX && (float)mouseX <= buttonX + buttonWidth && (float)mouseY >= contentY && (float)mouseY <= contentY + buttonHeight;
      Color oneTimeBg = this.waitingForConfigSelect && this.isOneTimeKey ? Kb18d549c13309af9a5.primary((int)((double)230.0F * alpha)) : (hoverOneTime ? new Color(50, 50, 56, (int)((double)255.0F * alpha)) : new Color(40, 40, 46, (int)((double)255.0F * alpha)));
      K2b5e2c5f2b9891b450.RECT.draw(matrices, buttonX, contentY, buttonWidth, buttonHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F), oneTimeBg);
      String oneTimeText = "Получить одноразовый ключ";
      float oneTimeTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(11.0F);
      float oneTimeTextX = buttonX + (buttonWidth - K896065ea0a2bba4f47.SF_MEDIUM.getWidth(oneTimeText, oneTimeTextSize)) / 2.0F;
      float oneTimeTextY = contentY + (buttonHeight - oneTimeTextSize) / 2.0F;
      K896065ea0a2bba4f47.SF_MEDIUM.drawText(matrices, oneTimeText, oneTimeTextX, oneTimeTextY, oneTimeTextSize, new Color(255, 255, 255, (int)((double)255.0F * alpha)));
      contentY += buttonHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
      boolean hoverPermanent = (float)mouseX >= buttonX && (float)mouseX <= buttonX + buttonWidth && (float)mouseY >= contentY && (float)mouseY <= contentY + buttonHeight;
      Color permanentBg = this.waitingForConfigSelect && !this.isOneTimeKey ? Kb18d549c13309af9a5.primary((int)((double)230.0F * alpha)) : (hoverPermanent ? new Color(50, 50, 56, (int)((double)255.0F * alpha)) : new Color(40, 40, 46, (int)((double)255.0F * alpha)));
      K2b5e2c5f2b9891b450.RECT.draw(matrices, buttonX, contentY, buttonWidth, buttonHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F), permanentBg);
      String permanentText = "Получить постоянный ключ";
      float permanentTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(11.0F);
      float permanentTextX = buttonX + (buttonWidth - K896065ea0a2bba4f47.SF_MEDIUM.getWidth(permanentText, permanentTextSize)) / 2.0F;
      float permanentTextY = contentY + (buttonHeight - permanentTextSize) / 2.0F;
      K896065ea0a2bba4f47.SF_MEDIUM.drawText(matrices, permanentText, permanentTextX, permanentTextY, permanentTextSize, new Color(255, 255, 255, (int)((double)255.0F * alpha)));
      contentY += buttonHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
      if (this.waitingForConfigSelect) {
         String listTitle = "Выберите конфиг:";
         float listTitleSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
         K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, listTitle, buttonX, contentY, listTitleSize, new Color(255, 255, 255, (int)((double)255.0F * alpha)));
         contentY += Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
         if (this.savedConfigs.isEmpty()) {
            String noConfigs = "Нет сохранённых конфигов";
            float noConfigsSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
            K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, noConfigs, buttonX, contentY, noConfigsSize, new Color(150, 150, 150, (int)((double)255.0F * alpha)));
         } else {
            float configHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(35.0F);
            float configGap = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);

            for(int i = 0; i < this.savedConfigs.size(); ++i) {
               String configName = (String)this.savedConfigs.get(i);
               float configY = contentY + (float)i * (configHeight + configGap);
               boolean hoverConfig = (float)mouseX >= buttonX && (float)mouseX <= buttonX + buttonWidth && (float)mouseY >= configY && (float)mouseY <= configY + configHeight;
               Color configBg = hoverConfig ? new Color(60, 60, 70, (int)((double)255.0F * alpha)) : new Color(35, 35, 45, (int)((double)255.0F * alpha));
               K2b5e2c5f2b9891b450.RECT.draw(matrices, buttonX, configY, buttonWidth, configHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), configBg);
               float configTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float configTextX = buttonX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
               float configTextY = configY + (configHeight - configTextSize) / 2.0F;
               K896065ea0a2bba4f47.SF_MEDIUM.drawText(matrices, configName, configTextX, configTextY, configTextSize, new Color(255, 255, 255, (int)((double)255.0F * alpha)));
            }
         }
      }

   }

   private void Kf65bf86f2fe3c8f0a3(class_4587 matrices, float guiX, float contentY, float guiWidth, int mouseX, int mouseY, double alpha) {
      float buttonWidth = guiWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
      float buttonX = guiX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
      String instruction = "Вставьте ключ для загрузки конфига";
      float instrSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
      K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, instruction, buttonX, contentY, instrSize, new Color(170, 170, 170, (int)((double)255.0F * alpha)));
      contentY += Ke4708cf52c3b89f5ae.getInstance().scaledUi(25.0F);
      float inputHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(45.0F);
      Color inputBg = this.keyInputFocused ? new Color(45, 45, 55, (int)((double)255.0F * alpha)) : new Color(35, 35, 45, (int)((double)255.0F * alpha));
      K2b5e2c5f2b9891b450.RECT.draw(matrices, buttonX, contentY, buttonWidth, inputHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), inputBg);
      String displayText = this.keyInput.isEmpty() ? "Введите ключ..." : this.keyInput;
      float inputTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(11.0F);
      float inputTextX = buttonX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
      float inputTextY = contentY + (inputHeight - inputTextSize) / 2.0F;
      Color inputTextColor = this.keyInput.isEmpty() ? new Color(100, 100, 100, (int)((double)255.0F * alpha)) : new Color(255, 255, 255, (int)((double)255.0F * alpha));
      K896065ea0a2bba4f47.SF_MEDIUM.drawText(matrices, displayText, inputTextX, inputTextY, inputTextSize, inputTextColor);
      contentY += inputHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(15.0F);
      float applyButtonHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
      boolean hoverApply = (float)mouseX >= buttonX && (float)mouseX <= buttonX + buttonWidth && (float)mouseY >= contentY && (float)mouseY <= contentY + applyButtonHeight;
      Color applyBg = hoverApply ? new Color(60, 180, 100, (int)((double)255.0F * alpha)) : new Color(50, 160, 80, (int)((double)255.0F * alpha));
      K2b5e2c5f2b9891b450.RECT.draw(matrices, buttonX, contentY, buttonWidth, applyButtonHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F), applyBg);
      String applyText = "Применить конфиг";
      float applyTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(11.0F);
      float applyTextX = buttonX + (buttonWidth - K896065ea0a2bba4f47.SF_MEDIUM.getWidth(applyText, applyTextSize)) / 2.0F;
      float applyTextY = contentY + (applyButtonHeight - applyTextSize) / 2.0F;
      K896065ea0a2bba4f47.SF_MEDIUM.drawText(matrices, applyText, applyTextX, applyTextY, applyTextSize, new Color(255, 255, 255, (int)((double)255.0F * alpha)));
   }

   private void Kfe73c8a0885292bf36(class_4587 matrices, float guiX, float guiY, float guiWidth, float guiHeight, double alpha) {
      this.notificationAnimation.update();
      if (System.currentTimeMillis() - this.notificationTime < 3000L) {
         this.notificationAnimation.run((double)1.0F, 200L, Kf3b62ecfba0c3544e6.CUBIC_OUT);
      } else {
         this.notificationAnimation.run((double)0.0F, 200L, Kf3b62ecfba0c3544e6.CUBIC_IN);
      }

      double notifAlpha = this.notificationAnimation.getValue();
      if (notifAlpha > 0.01 && !this.notificationMessage.isEmpty()) {
         float notifWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(300.0F);
         float notifHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(50.0F);
         float notifX = guiX + (guiWidth - notifWidth) / 2.0F;
         float notifY = guiY + guiHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(15.0F);
         K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, notifX, notifY, notifWidth, notifHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F), new Color(40, 180, 100, (int)((double)200.0F * alpha * notifAlpha)));
         float notifTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
         float notifTextX = notifX + (notifWidth - K896065ea0a2bba4f47.SF_MEDIUM.getWidth(this.notificationMessage, notifTextSize)) / 2.0F;
         float notifTextY = notifY + (notifHeight - notifTextSize) / 2.0F;
         K896065ea0a2bba4f47.SF_MEDIUM.drawText(matrices, this.notificationMessage, notifTextX, notifTextY, notifTextSize, new Color(255, 255, 255, (int)((double)255.0F * alpha * notifAlpha)));
      }

   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      if (button != 0) {
         return false;
      } else {
         float windowWidth = (float)mc.method_22683().method_4486();
         float windowHeight = (float)mc.method_22683().method_4502();
         float guiWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(500.0F);
         float guiHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(400.0F);
         float guiX = (windowWidth - guiWidth) / 2.0F;
         float guiY = (windowHeight - guiHeight) / 2.0F;
         float contentY = guiY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F) + Ke4708cf52c3b89f5ae.getInstance().scaledUi(35.0F);
         float tabWidth = guiWidth / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(30.0F);
         float tabHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(35.0F);
         float tabX = guiX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
         if (mouseX >= (double)tabX && mouseX <= (double)(tabX + tabWidth) && mouseY >= (double)contentY && mouseY <= (double)(contentY + tabHeight)) {
            this.currentMode = K7b670ccd156aee8fe5.UPLOAD;
            this.waitingForConfigSelect = false;
            return true;
         } else {
            float downloadTabX = tabX + tabWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
            if (mouseX >= (double)downloadTabX && mouseX <= (double)(downloadTabX + tabWidth) && mouseY >= (double)contentY && mouseY <= (double)(contentY + tabHeight)) {
               this.currentMode = K7b670ccd156aee8fe5.DOWNLOAD;
               return true;
            } else {
               contentY += tabHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(25.0F);
               return this.currentMode == K7b670ccd156aee8fe5.UPLOAD ? this.K50c456ac62c5a6819d(mouseX, mouseY, guiX, contentY, guiWidth) : this.K986c665d6b863c47fd(mouseX, mouseY, guiX, contentY, guiWidth);
            }
         }
      }
   }

   private boolean K50c456ac62c5a6819d(double mouseX, double mouseY, float guiX, float contentY, float guiWidth) {
      float buttonWidth = guiWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
      float buttonHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
      float buttonX = guiX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
      contentY += Ke4708cf52c3b89f5ae.getInstance().scaledUi(25.0F);
      if (mouseX >= (double)buttonX && mouseX <= (double)(buttonX + buttonWidth) && mouseY >= (double)contentY && mouseY <= (double)(contentY + buttonHeight)) {
         this.waitingForConfigSelect = true;
         this.isOneTimeKey = true;
         this.savedConfigs = K3e319beb26054fd921.getInstance().getSavedConfigs();
         return true;
      } else {
         contentY += buttonHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
         if (mouseX >= (double)buttonX && mouseX <= (double)(buttonX + buttonWidth) && mouseY >= (double)contentY && mouseY <= (double)(contentY + buttonHeight)) {
            this.waitingForConfigSelect = true;
            this.isOneTimeKey = false;
            this.savedConfigs = K3e319beb26054fd921.getInstance().getSavedConfigs();
            return true;
         } else {
            contentY += buttonHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
            if (this.waitingForConfigSelect && !this.savedConfigs.isEmpty()) {
               float configHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(35.0F);
               float configGap = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);

               for(int i = 0; i < this.savedConfigs.size(); ++i) {
                  String configName = (String)this.savedConfigs.get(i);
                  float configY = contentY + (float)i * (configHeight + configGap);
                  if (mouseX >= (double)buttonX && mouseX <= (double)(buttonX + buttonWidth) && mouseY >= (double)configY && mouseY <= (double)(configY + configHeight)) {
                     String configData = K3e319beb26054fd921.getInstance().serializeConfig(configName);
                     if (configData == null) {
                        this.Ke8403e129405cbc7aa("Ошибка загрузки конфига");
                        return true;
                     }

                     if (this.isOneTimeKey) {
                        K3e5d28a93090bc4a75.getInstance().saveConfigOneTime(configName, configData);
                     } else {
                        K3e5d28a93090bc4a75.getInstance().saveConfigPermanent(configName, configData);
                     }

                     String keyType = this.isOneTimeKey ? "одноразовый" : "постоянный";
                     this.Ke8403e129405cbc7aa("Ключ (" + keyType + ") скопирован!");
                     this.waitingForConfigSelect = false;
                     return true;
                  }
               }
            }

            return false;
         }
      }
   }

   private boolean K986c665d6b863c47fd(double mouseX, double mouseY, float guiX, float contentY, float guiWidth) {
      float buttonWidth = guiWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
      float buttonX = guiX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
      contentY += Ke4708cf52c3b89f5ae.getInstance().scaledUi(25.0F);
      float inputHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(45.0F);
      if (mouseX >= (double)buttonX && mouseX <= (double)(buttonX + buttonWidth) && mouseY >= (double)contentY && mouseY <= (double)(contentY + inputHeight)) {
         this.keyInputFocused = true;
         return true;
      } else {
         contentY += inputHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(15.0F);
         float applyButtonHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
         if (mouseX >= (double)buttonX && mouseX <= (double)(buttonX + buttonWidth) && mouseY >= (double)contentY && mouseY <= (double)(contentY + applyButtonHeight)) {
            if (!this.keyInput.trim().isEmpty()) {
               K3e5d28a93090bc4a75.getInstance().loadConfigByKey(this.keyInput.trim());
               this.Ke8403e129405cbc7aa("Загрузка конфига...");
               this.keyInput = "";
               this.keyInputFocused = false;
            } else {
               this.Ke8403e129405cbc7aa("Введите ключ!");
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
      if (keyCode == 256) {
         this.method_25419();
         return true;
      } else {
         if (this.currentMode == K7b670ccd156aee8fe5.DOWNLOAD && this.keyInputFocused) {
            if (keyCode == 259) {
               if (!this.keyInput.isEmpty()) {
                  this.keyInput = this.keyInput.substring(0, this.keyInput.length() - 1);
               }

               return true;
            }

            if (keyCode == 257 || keyCode == 335) {
               this.keyInputFocused = false;
               return true;
            }

            if (keyCode == 86 && (modifiers & 2) != 0) {
               try {
                  String clipboard = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
                  if (clipboard != null) {
                     this.keyInput = clipboard.trim();
                  }
               } catch (Exception e) {
                  e.printStackTrace();
               }

               return true;
            }
         }

         return super.method_25404(keyCode, scanCode, modifiers);
      }
   }

   public boolean method_25400(char chr, int modifiers) {
      if (this.currentMode == K7b670ccd156aee8fe5.DOWNLOAD && this.keyInputFocused && (chr >= 'a' && chr <= 'z' || chr >= 'A' && chr <= 'Z')) {
         if (this.keyInput.length() < 10) {
            this.keyInput = this.keyInput + chr;
         }

         return true;
      } else {
         return super.method_25400(chr, modifiers);
      }
   }

   public void method_25419() {
      this.closing = true;
      this.openAnimation.run((double)0.0F, 300L, Kf3b62ecfba0c3544e6.EXPO_IN);
   }

   public boolean method_25421() {
      return false;
   }

   private void Ke8403e129405cbc7aa(String message) {
      this.notificationMessage = message;
      this.notificationTime = System.currentTimeMillis();
   }
}
