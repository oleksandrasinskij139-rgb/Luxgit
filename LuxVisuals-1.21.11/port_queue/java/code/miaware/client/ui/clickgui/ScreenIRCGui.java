package code.miaware.client.ui.clickgui;

import code.miaware.api.system.backend.K04bfb908d9b141e27c;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.other.K7d074a66e20f0c1531;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.fonts.K896065ea0a2bba4f47;
import code.miaware.client.network.K204c47a3480aa2d86b;
import code.miaware.client.network.K3e5d28a93090bc4a75;
import code.miaware.client.network.K8ee912418ef60c15b7;
import code.miaware.client.services.Ke4708cf52c3b89f5ae;
import code.miaware.client.ui.test.K1aa473fe0ce338f657;
import code.miaware.client.ui.theme.K6ea7546faa48739c0d;
import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1109;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_2568;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_5250;
import net.minecraft.class_5251;
import net.minecraft.class_742;
import net.minecraft.class_8685;
import net.minecraft.class_2558.class_2559;
import net.minecraft.class_2568.class_5247;
import org.joml.Vector4f;

public class ScreenIRCGui extends class_437 implements K4600bef438dafc1a9c {
   private static final ScreenIRCGui instance = new ScreenIRCGui();
   private final Ka91715a8c4e3f3b7c4 openAnimation = new Ka91715a8c4e3f3b7c4();
   private final K6ea7546faa48739c0d themeEditor = K6ea7546faa48739c0d.getInstance();
   private boolean open;
   private boolean guiDragging = false;
   private float dragOffsetX = 0.0F;
   private float dragOffsetY = 0.0F;
   private float guiOffsetX = 0.0F;
   private float guiOffsetY = 0.0F;
   private String messageInput = "";
   private boolean isMessageFieldFocused = false;
   private int cursorPosition = 0;
   private long lastCursorBlink = 0L;
   private int selectionStart = 0;
   private int selectionEnd = 0;
   private float inputTextScrollY = 0.0F;
   private final List<K8001222dc0dc25d83d> chatMessages = new ArrayList();
   private final List<K8001222dc0dc25d83d> friendChatMessages = new ArrayList();
   private float chatScrollOffset = 0.0F;
   private float rightPanelScrollOffset = 0.0F;
   private boolean showFriendContextMenu = false;
   private String selectedFriend = null;
   private float contextMenuX = 0.0F;
   private float contextMenuY = 0.0F;
   private boolean showMessageContextMenu = false;
   private String selectedMessageAuthor = null;
   private float messageContextMenuX = 0.0F;
   private float messageContextMenuY = 0.0F;
   private Set<String> ignoredPlayers = new HashSet();
   private List<K7db34ec333fff41384> messageBounds = new ArrayList();
   private boolean showDButtonMenu = false;
   private float dMenuX = 0.0F;
   private float dMenuY = 0.0F;
   private Color selectedThemeColor = new Color(150, 100, 255);
   private Color customThemeColor = new Color(255, 215, 0);
   private boolean hideFriendPanel = false;
   private boolean isPremiumUser = false;
   private boolean showColorPicker = false;
   private float colorPickerX = 0.0F;
   private float colorPickerY = 0.0F;
   private boolean draggingColorPicker = false;
   private float colorPickerDragOffsetX = 0.0F;
   private float colorPickerDragOffsetY = 0.0F;
   private float customHue = 0.15F;
   private float customSaturation = 1.0F;
   private float customBrightness = 1.0F;
   private boolean draggingCustomHue = false;
   private boolean draggingCustomSatBright = false;
   private boolean draggingCustomAlpha = false;
   private int customColorR = 255;
   private int customColorG = 215;
   private int customColorB = 0;
   private int customColorA = 255;
   private boolean draggingRedSlider = false;
   private boolean draggingGreenSlider = false;
   private boolean draggingBlueSlider = false;
   private boolean enableSendSound = true;
   private boolean enableReceiveSound = true;
   private boolean enableWindowsNotifications = true;
   private float ircSoundVolume = 1.0F;
   private boolean draggingVolumeSlider = false;
   private boolean showMessageTime = true;
   private static final Set<String> favoriteFriends = new HashSet();
   private boolean friendChatMode = false;
   private String currentPrivateChat = null;
   private float friendChatScrollOffset = 0.0F;
   private boolean showAddFriendInput = false;
   private String addFriendInputText = "";
   private boolean addFriendInputFocused = false;
   private long lastActivityTime = System.currentTimeMillis();
   private double lastPlayerX = (double)0.0F;
   private double lastPlayerY = (double)0.0F;
   private double lastPlayerZ = (double)0.0F;
   private float lastPlayerYaw = 0.0F;
   private float lastPlayerPitch = 0.0F;
   private boolean wasMoving = false;
   private boolean showPlayerHeadMenu = false;
   private String customTag = "";
   private boolean customTagInputFocused = false;
   private long lastCustomTagChangeTime = 0L;
   private String playerSearchInput = "";
   private boolean playerSearchFocused = false;
   private List<String> searchResults = new ArrayList();
   private String lastSearchQuery = "";
   private boolean isCheckingTagUniqueness = false;
   private String pendingCustomTag = "";
   private String editingCustomTag = "";
   private boolean showRulesWindow = false;
   private boolean rulesAccepted = false;
   private boolean useCustomMessageColor = false;
   private Color customMessageColor = new Color(255, 255, 255);
   private boolean showPrefixMenu = false;
   private int selectedPrefixIndex = -1;
   private String[] basicPrefixes = new String[10];
   private String[] premiumPrefixes = new String[20];
   private boolean showFriendRequest = false;
   private String friendRequestFromUuid = null;
   private String friendRequestFromUsername = null;

   public static ScreenIRCGui getInstance() {
      return instance;
   }

   private Color getPlayerActivityColor(float alpha) {
      if (mc.field_1724 == null) {
         return new Color(255, 0, 0, (int)(255.0F * alpha));
      } else {
         double currentX = mc.field_1724.method_23317();
         double currentY = mc.field_1724.method_23318();
         double currentZ = mc.field_1724.method_23321();
         float currentYaw = mc.field_1724.method_36454();
         float currentPitch = mc.field_1724.method_36455();
         boolean isMoving = currentX != this.lastPlayerX || currentY != this.lastPlayerY || currentZ != this.lastPlayerZ || currentYaw != this.lastPlayerYaw || currentPitch != this.lastPlayerPitch;
         this.lastPlayerX = currentX;
         this.lastPlayerY = currentY;
         this.lastPlayerZ = currentZ;
         this.lastPlayerYaw = currentYaw;
         this.lastPlayerPitch = currentPitch;
         if (isMoving) {
            this.lastActivityTime = System.currentTimeMillis();
            this.wasMoving = true;
         }

         long timeSinceActivity = System.currentTimeMillis() - this.lastActivityTime;
         if (timeSinceActivity < 3000L) {
            return new Color(0, 255, 0, (int)(255.0F * alpha));
         } else {
            return timeSinceActivity < 60000L ? new Color(255, 165, 0, (int)(255.0F * alpha)) : new Color(255, 0, 0, (int)(255.0F * alpha));
         }
      }
   }

   public boolean isReceiveSoundEnabled() {
      return this.enableReceiveSound;
   }

   public float getIrcSoundVolume() {
      return this.ircSoundVolume;
   }

   public boolean isWindowsNotificationsEnabled() {
      return this.enableWindowsNotifications;
   }

   public void setPremiumUser(boolean premium) {
      this.isPremiumUser = premium;
      System.out.println("Premium статус: " + (premium ? "АКТИВИРОВАН" : "ДЕАКТИВИРОВАН"));
   }

   public boolean isPremiumUser() {
      return this.isPremiumUser;
   }

   public void setCustomThemeColor(Color color) {
      this.customThemeColor = color;
      PrintStream var10000 = System.out;
      int var10001 = color.getRed();
      var10000.println("Кастомный цвет установлен: RGB(" + var10001 + "," + color.getGreen() + "," + color.getBlue() + ")");
   }

   public Color getCustomThemeColor() {
      return this.customThemeColor;
   }

   private void sendPremiumRequiredMessage() {
      if (mc.field_1724 != null) {
         try {
            class_5250 message = class_2561.method_43470("§c[IRC] §fЧтобы получить разные функции, вам нужно приобрести §ePremium§f. ");
            class_5250 link = class_2561.method_43470("§a§n[Купить Premium]").method_27694((style) -> style.method_27703(class_5251.method_27717(65280)).method_30938(true).method_10958(new class_2558(class_2559.field_11749, "https://t.me/priceexcl")).method_10949(new class_2568(class_5247.field_24342, class_2561.method_43470("§eНажми чтобы перейти на сайт"))));
            message.method_10852(link);
            mc.field_1724.method_7353(message, false);
            System.out.println("[IRC GUI] Отправлено сообщение о Premium");
         } catch (Exception e) {
            System.out.println("[IRC GUI] Ошибка отправки сообщения: " + e.getMessage());
            e.printStackTrace();
         }

      }
   }

   private ScreenIRCGui() {
      super(class_2561.method_30163("IRC"));
   }

   protected void method_25426() {
      this.themeEditor.init();
      this.open = true;
      K7d074a66e20f0c1531.playGuiSound(K7d074a66e20f0c1531.GUI_OPEN_EVENT);
      this.updateUserRole();
      this.loadCustomTag();
      this.loadRulesAcceptance();
      if (!this.rulesAccepted) {
         this.showRulesWindow = true;
      }

      this.initializePrefixes();
      if (!K3e5d28a93090bc4a75.getInstance().isConnected()) {
         String username = mc.method_1548().method_1676();
         String uuid = K3e5d28a93090bc4a75.getInstance().getPersistentUUID();
         K3e5d28a93090bc4a75.getInstance().connect(username, uuid);
      }

      super.method_25426();
   }

   private void initializePrefixes() {
      this.basicPrefixes[0] = "§x§F§F§6§B§C§BНяшка";
      this.basicPrefixes[1] = "§x§F§F§B§0§2§0Блатной";
      this.basicPrefixes[2] = "§x§4§D§D§2§F§FБро";
      this.basicPrefixes[3] = "§x§5§0§F§A§7§BДушнила";
      this.basicPrefixes[4] = "§x§4§A§D§E§8§0Чиллер";
      this.basicPrefixes[5] = "§x§F§F§9§E§5§7Носатый";
      this.basicPrefixes[6] = "§x§F§F§D§7§0§0Легенда";
      this.basicPrefixes[7] = "§x§F§F§4§D§8§DКраш";
      this.basicPrefixes[8] = "§x§8§B§5§C§F§6Фанат";
      this.basicPrefixes[9] = "§x§F§F§E§5§A§0Пельмень";
      this.premiumPrefixes[0] = "§x§F§F§5§A§3§6Гигачад";
      this.premiumPrefixes[1] = "§x§7§C§8§3§F§DСигма";
      this.premiumPrefixes[2] = "§x§3§B§8§2§F§6Альфа";
      this.premiumPrefixes[3] = "§x§0§0§E§5§F§FИмба";
      this.premiumPrefixes[4] = "§x§A§1§6§2§0§7Тапок";
      this.premiumPrefixes[5] = "§x§C§0§8§4§F§CБосс";
      this.premiumPrefixes[6] = "§x§F§F§C§8§A§0Дед";
      this.premiumPrefixes[7] = "§x§F§F§3§B§3§0Читер";
      this.premiumPrefixes[8] = "§x§F§F§8§A§6§5Котяра";
      this.premiumPrefixes[9] = "§x§D§C§2§6§2§6Монстр";
      this.premiumPrefixes[10] = "§x§E§C§4§8§9§9Псих";
      this.premiumPrefixes[11] = "§x§6§4§7§4§8§BВолк";
      this.premiumPrefixes[12] = "§x§0§6§B§6§D§4Киборг";
      this.premiumPrefixes[13] = "§x§2§2§C§5§5§EХищник";
      this.premiumPrefixes[14] = "§x§F§A§C§C§1§5GOAT";
      this.premiumPrefixes[15] = "§x§5§5§F§F§F§FLux";
      this.premiumPrefixes[16] = "§x§F§F§A§5§D§0Кидуля";
      this.premiumPrefixes[17] = "§x§8§0§F§F§D§0Чилловый";
      this.premiumPrefixes[18] = "§x§4§0§C§0§F§FПарнишка";
      this.premiumPrefixes[19] = "§x§F§F§D§7§0§0number one";
   }

   private void updateUserRole() {
      K04bfb908d9b141e27c roleManager = K04bfb908d9b141e27c.getInstance();
      this.isPremiumUser = roleManager.hasPremiumAccess();
      String role = roleManager.getRole();
      System.out.println("[IRC GUI] Роль пользователя: " + role + " | Premium доступ: " + this.isPremiumUser);
   }

   public void resetTagCheckingFlag() {
      this.isCheckingTagUniqueness = false;
      System.out.println("[IRC] Флаг проверки тега сброшен");
   }

   private String getRoleDisplayName() {
      K04bfb908d9b141e27c roleManager = K04bfb908d9b141e27c.getInstance();
      String role = roleManager.getRole();
      return role != null && !role.isEmpty() ? role : "User";
   }

   private boolean canChangeCustomTag() {
      K04bfb908d9b141e27c roleManager = K04bfb908d9b141e27c.getInstance();
      String role = roleManager.getRole();
      if (!"Developer".equalsIgnoreCase(role) && !"Admin".equalsIgnoreCase(role) && !"Owner".equalsIgnoreCase(role)) {
         if (this.customTag != null && !this.customTag.isEmpty()) {
            if (this.lastCustomTagChangeTime == 0L) {
               return true;
            } else {
               long currentTime = System.currentTimeMillis();
               long timeSinceLastChange = currentTime - this.lastCustomTagChangeTime;
               if (this.isPremiumUser) {
                  return timeSinceLastChange >= 14400000L;
               } else {
                  return timeSinceLastChange >= 86400000L;
               }
            }
         } else {
            return true;
         }
      } else {
         return true;
      }
   }

   private String getTimeUntilNextTagChange() {
      if (this.customTag != null && !this.customTag.isEmpty()) {
         if (this.lastCustomTagChangeTime == 0L) {
            return "Можно изменить";
         } else {
            long currentTime = System.currentTimeMillis();
            long timeSinceLastChange = currentTime - this.lastCustomTagChangeTime;
            long cooldown = this.isPremiumUser ? 14400000L : 86400000L;
            long timeRemaining = cooldown - timeSinceLastChange;
            if (timeRemaining <= 0L) {
               return "Можно изменить";
            } else {
               long hours = timeRemaining / 3600000L;
               long minutes = timeRemaining % 3600000L / 60000L;
               return hours + "ч " + minutes + "м";
            }
         }
      } else {
         return "Можно изменить";
      }
   }

   private void saveCustomTag() {
      try {
         File configDir = new File(mc.field_1697, "config/miaware");
         if (!configDir.exists()) {
            configDir.mkdirs();
         }

         File tagFile = new File(configDir, "irc_custom_tag.txt");
         FileWriter writer = new FileWriter(tagFile);
         writer.write(this.customTag + "\n");
         writer.write(this.lastCustomTagChangeTime + "\n");
         writer.write(this.selectedPrefixIndex + "\n");
         writer.close();
         System.out.println("[IRC] Custom tag сохранён: @" + this.customTag);
      } catch (Exception e) {
         System.err.println("[IRC] Ошибка сохранения custom tag: " + e.getMessage());
      }

   }

   private void loadCustomTag() {
      try {
         File configDir = new File(mc.field_1697, "config/miaware");
         File tagFile = new File(configDir, "irc_custom_tag.txt");
         if (tagFile.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(tagFile));
            String tag = reader.readLine();
            String timeStr = reader.readLine();
            String prefixStr = reader.readLine();
            reader.close();
            if (tag != null && !tag.isEmpty()) {
               this.customTag = tag;
            }

            if (timeStr != null && !timeStr.isEmpty()) {
               try {
                  this.lastCustomTagChangeTime = Long.parseLong(timeStr);
               } catch (NumberFormatException var8) {
                  this.lastCustomTagChangeTime = 0L;
               }
            }

            if (prefixStr != null && !prefixStr.isEmpty()) {
               try {
                  int loadedIndex = Integer.parseInt(prefixStr);
                  if (loadedIndex >= -1 && loadedIndex < 30) {
                     this.selectedPrefixIndex = loadedIndex;
                  } else {
                     this.selectedPrefixIndex = -1;
                     System.err.println("[IRC] Некорректный prefix index: " + loadedIndex + ", сброшен на -1");
                  }
               } catch (NumberFormatException var9) {
                  this.selectedPrefixIndex = -1;
               }
            }

            System.out.println("[IRC] Custom tag загружен: @" + this.customTag + ", prefix index: " + this.selectedPrefixIndex);
         }
      } catch (Exception e) {
         System.err.println("[IRC] Ошибка загрузки custom tag: " + e.getMessage());
      }

   }

   public String getCurrentPrivateChat() {
      return this.currentPrivateChat;
   }

   public void showFriendRequest(String fromUuid, String fromUsername) {
      this.friendRequestFromUuid = fromUuid;
      this.friendRequestFromUsername = fromUsername;
      this.showFriendRequest = true;
      String systemMessage = "§e⚠ §f" + fromUsername + " §7отправил вам запрос в друзья §8[§aПринять§8] §8[§cОтклонить§8]";
      K8001222dc0dc25d83d friendRequestMsg = new K8001222dc0dc25d83d("СИСТЕМА", systemMessage, false, true, (String)null);
      this.chatMessages.add(friendRequestMsg);
      System.out.println("[IRC GUI] Показан запрос в друзья от: " + fromUsername);
   }

   private void loadRulesAcceptance() {
      try {
         File configDir = new File(mc.field_1697, "config/miaware");
         File rulesFile = new File(configDir, "irc_rules_accepted.txt");
         if (rulesFile.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(rulesFile));
            String accepted = reader.readLine();
            reader.close();
            this.rulesAccepted = "true".equals(accepted);
            System.out.println("[IRC] Правила приняты: " + this.rulesAccepted);
         } else {
            this.rulesAccepted = false;
         }
      } catch (Exception e) {
         System.err.println("[IRC] Ошибка загрузки статуса правил: " + e.getMessage());
         this.rulesAccepted = false;
      }

   }

   private void saveRulesAcceptance() {
      try {
         File configDir = new File(mc.field_1697, "config/miaware");
         if (!configDir.exists()) {
            configDir.mkdirs();
         }

         File rulesFile = new File(configDir, "irc_rules_accepted.txt");
         BufferedWriter writer = new BufferedWriter(new FileWriter(rulesFile));
         writer.write("true");
         writer.close();
         System.out.println("[IRC] Правила сохранены как принятые");
      } catch (Exception e) {
         System.err.println("[IRC] Ошибка сохранения статуса правил: " + e.getMessage());
      }

   }

   public String getDisplayName() {
      if (this.customTag != null && !this.customTag.isEmpty()) {
         return this.customTag;
      } else {
         return mc.field_1724 != null ? mc.field_1724.method_7334().getName() : "Player";
      }
   }

   public String getSelectedPrefix() {
      if (this.selectedPrefixIndex >= 0 && this.selectedPrefixIndex < 30) {
         return this.selectedPrefixIndex < 10 ? this.basicPrefixes[this.selectedPrefixIndex] : this.premiumPrefixes[this.selectedPrefixIndex - 10];
      } else {
         return null;
      }
   }

   private float drawMinecraftColoredText(class_4587 matrices, String text, float x, float y, float size, float alpha) {
      float currentX = x;
      int i = 0;

      while(i < text.length()) {
         if (i < text.length() - 13 && text.charAt(i) == 167 && text.charAt(i + 1) == 'x') {
            try {
               int r = Integer.parseInt("" + text.charAt(i + 3) + text.charAt(i + 5), 16);
               int g = Integer.parseInt("" + text.charAt(i + 7) + text.charAt(i + 9), 16);
               int b = Integer.parseInt("" + text.charAt(i + 11) + text.charAt(i + 13), 16);
               i += 14;

               StringBuilder coloredPart;
               for(coloredPart = new StringBuilder(); i < text.length() && text.charAt(i) != 167; ++i) {
                  coloredPart.append(text.charAt(i));
               }

               String part = coloredPart.toString();
               K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, part, currentX, y, size, new Color(r, g, b, (int)(255.0F * alpha)));
               currentX += K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(part, size);
            } catch (Exception var14) {
               char c = text.charAt(i);
               String charStr = String.valueOf(c);
               K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, charStr, currentX, y, size, new Color(255, 255, 255, (int)(255.0F * alpha)));
               currentX += K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(charStr, size);
               ++i;
            }
         } else {
            char c = text.charAt(i);
            String charStr = String.valueOf(c);
            K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, charStr, currentX, y, size, new Color(255, 255, 255, (int)(255.0F * alpha)));
            currentX += K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(charStr, size);
            ++i;
         }
      }

      return currentX;
   }

   public void onTagUniquenessResponse(String tag, boolean isUnique) {
      System.out.println("[IRC DEBUG] onTagUniquenessResponse: tag=" + tag + ", isUnique=" + isUnique + ", isCheckingTagUniqueness=" + this.isCheckingTagUniqueness + ", pendingCustomTag=" + this.pendingCustomTag);
      if (this.isCheckingTagUniqueness && tag.equalsIgnoreCase(this.pendingCustomTag)) {
         this.isCheckingTagUniqueness = false;
         if (isUnique) {
            this.customTag = this.pendingCustomTag;
            this.lastCustomTagChangeTime = System.currentTimeMillis();
            this.customTagInputFocused = false;
            this.editingCustomTag = "";
            this.saveCustomTag();
            System.out.println("[IRC] Custom tag сохранён: @" + this.customTag);
            if (mc.field_1724 != null) {
               mc.field_1724.method_7353(class_2561.method_43470("§a[IRC] §fВы успешно использовали тег!"), false);
            }
         } else {
            System.out.println("[IRC] Тег уже занят: @" + tag);
            this.customTagInputFocused = false;
            this.editingCustomTag = "";
            if (mc.field_1724 != null) {
               mc.field_1724.method_7353(class_2561.method_43470("§c[IRC] §fК сожалению, тег занят"), false);
            }
         }

         this.pendingCustomTag = "";
      } else {
         System.out.println("[IRC DEBUG] Игнорируем ответ: неподходящий запрос");
      }
   }

   public void method_25419() {
      this.open = false;
   }

   public void method_25420(class_332 context, int mouseX, int mouseY, float delta) {
   }

   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
      Ke4708cf52c3b89f5ae.getInstance().updateScale();
      this.openAnimation.update();
      this.openAnimation.run(this.open ? (double)1.0F : (double)0.0F, this.open ? 400L : 350L, Kf3b62ecfba0c3544e6.EXPO_OUT);
      if (!this.open && this.openAnimation.getValue() < 0.01) {
         this.themeEditor.save(false);
         super.method_25419();
      } else {
         float alpha = (float)this.openAnimation.getValue();
         if (!(alpha < 0.05F)) {
            float windowWidth = (float)mc.method_22683().method_4486();
            float windowHeight = (float)mc.method_22683().method_4502();
            float guiWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(600.0F);
            float guiHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(400.0F);
            float guiX = windowWidth / 2.0F - guiWidth / 2.0F + this.guiOffsetX;
            float guiY = windowHeight / 2.0F - guiHeight / 2.0F + this.guiOffsetY;
            class_4587 matrices = context.method_51448();
            float radius = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
            K2b5e2c5f2b9891b450.RECT.draw(matrices, guiX - Ke4708cf52c3b89f5ae.getInstance().scaledUi(0.5F), guiY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(0.5F), guiWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), guiHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), radius + Ke4708cf52c3b89f5ae.getInstance().scaledUi(0.5F), new Color(42, 42, 54, (int)(200.0F * alpha)));
            K2b5e2c5f2b9891b450.RECT.draw(matrices, guiX, guiY, guiWidth, guiHeight, radius, new Color(21, 21, 28, (int)(255.0F * alpha)));
            float sidebarWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(50.0F);
            float menuLeftPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
            float menuTopPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
            float menuX = guiX + sidebarWidth + menuLeftPadding;
            float menuY = guiY + menuTopPadding;
            float menuWidth = guiWidth - sidebarWidth - menuLeftPadding;
            float menuHeight = guiHeight - menuTopPadding;
            float smallRadius = Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
            K2b5e2c5f2b9891b450.RECT.draw(matrices, menuX, menuY, menuWidth, menuHeight, new Vector4f(smallRadius, smallRadius, 0.0F, radius), new Color(18, 18, 24, (int)(255.0F * alpha)));
            float lineOffset = Ke4708cf52c3b89f5ae.getInstance().scaledUi(120.0F);
            float lineTopPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(45.0F);
            float lineThickness = Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F);
            float cornerRadius = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
            float verticalLineX = menuX + menuWidth - lineOffset;
            float verticalLineY = menuY + lineTopPadding;
            float verticalLineHeight = menuHeight - lineTopPadding;
            if (!this.hideFriendPanel) {
               K2b5e2c5f2b9891b450.RECT.draw(matrices, verticalLineX, verticalLineY, lineOffset, verticalLineHeight, new Vector4f(cornerRadius, 0.0F, 0.0F, cornerRadius), new Color(37, 37, 46, (int)(255.0F * alpha)));
               K2b5e2c5f2b9891b450.RECT.draw(matrices, verticalLineX + lineThickness, verticalLineY + lineThickness, lineOffset - lineThickness, verticalLineHeight - lineThickness, new Vector4f(cornerRadius - lineThickness, 0.0F, 0.0F, cornerRadius - lineThickness), new Color(18, 18, 24, (int)(255.0F * alpha)));
               float rightPanelLineY = verticalLineY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
               float rightPanelLineX = verticalLineX + lineThickness + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               float rightPanelLineWidth = lineOffset - lineThickness * 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               K2b5e2c5f2b9891b450.RECT.draw(matrices, rightPanelLineX, rightPanelLineY, rightPanelLineWidth, lineThickness, 0.0F, new Color(37, 37, 46, (int)(255.0F * alpha)));
               float friendTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float friendTextY = rightPanelLineY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(23.0F);
               K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, "Friend", rightPanelLineX, friendTextY, friendTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               int friendCount = K8ee912418ef60c15b7.getInstance().getIRCFriends().size();
               String friendCountStr = String.valueOf(friendCount);
               float friendTextWidth = K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth("Friend", friendTextSize);
               K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, friendCountStr, rightPanelLineX + friendTextWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F), friendTextY, friendTextSize, new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               float addButtonSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(18.0F);
               float addButtonX = verticalLineX + cornerRadius - addButtonSize / 2.0F;
               float addButtonY = verticalLineY + cornerRadius - addButtonSize / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(19.0F);
               boolean hoverAddButton = (float)mouseX >= addButtonX && (float)mouseX <= addButtonX + addButtonSize && (float)mouseY >= addButtonY && (float)mouseY <= addButtonY + addButtonSize;
               if (hoverAddButton) {
                  K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, addButtonX, addButtonY, addButtonSize, addButtonSize, Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), new Color(18, 18, 24, (int)(255.0F * alpha)));
                  float outlineWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F);
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, addButtonX - outlineWidth, addButtonY - outlineWidth, addButtonSize + outlineWidth * 2.0F, addButtonSize + outlineWidth * 2.0F, Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F + outlineWidth), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               }

               float plusIconSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(14.0F);
               float plusIconX = addButtonX + (addButtonSize - plusIconSize) / 2.0F + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
               float plusIconY = addButtonY + (addButtonSize - plusIconSize) / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
               K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, "+", plusIconX, plusIconY, plusIconSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               K2b5e2c5f2b9891b450.RECT.draw(matrices, rightPanelLineX, rightPanelLineY, rightPanelLineWidth, lineThickness, 0.0F, new Color(37, 37, 46, (int)(255.0F * alpha)));
               float addFriendInputHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(25.0F);
               float addFriendInputY = rightPanelLineY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               float friendListStartY = rightPanelLineY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               if (this.hideFriendPanel && this.showAddFriendInput) {
                  this.showAddFriendInput = false;
                  this.addFriendInputFocused = false;
               }

               if (this.showAddFriendInput && !this.hideFriendPanel) {
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, rightPanelLineX, addFriendInputY, rightPanelLineWidth, addFriendInputHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), new Color(26, 26, 32, (int)(255.0F * alpha)));
                  String displayText = this.addFriendInputText.isEmpty() ? "Введите юз..." : this.addFriendInputText;
                  Color textColor = this.addFriendInputText.isEmpty() ? new Color(128, 128, 144, (int)(255.0F * alpha)) : new Color(255, 255, 255, (int)(255.0F * alpha));
                  float inputTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(7.0F);
                  float inputTextX = rightPanelLineX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                  float inputTextY = addFriendInputY + (addFriendInputHeight - inputTextSize) / 2.0F;
                  K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, displayText, inputTextX, inputTextY, inputTextSize, textColor);
                  if (this.addFriendInputFocused && System.currentTimeMillis() / 500L % 2L == 0L) {
                     float cursorX = inputTextX + K896065ea0a2bba4f47.SF_REGULAR.getWidth(this.addFriendInputText, inputTextSize);
                     K2b5e2c5f2b9891b450.RECT.draw(matrices, cursorX, inputTextY, Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), inputTextSize, 0.0F, new Color(255, 255, 255, (int)(255.0F * alpha)));
                  }

                  friendListStartY = addFriendInputY + addFriendInputHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               }

               float friendEntryHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
               float friendEntryPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               float headSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(32.0F);
               List<K204c47a3480aa2d86b> friends = K8ee912418ef60c15b7.getInstance().getIRCFriends();
               friends.sort((a, bx) -> {
                  if (a.isOnline() && !bx.isOnline()) {
                     return -1;
                  } else if (!a.isOnline() && bx.isOnline()) {
                     return 1;
                  } else {
                     boolean aFav = favoriteFriends.contains(a.getUsername());
                     boolean bFav = favoriteFriends.contains(bx.getUsername());
                     if (aFav && !bFav) {
                        return -1;
                     } else {
                        return !aFav && bFav ? 1 : 0;
                     }
                  }
               });
               float friendListAvailableHeight = verticalLineY + verticalLineHeight - friendListStartY;
               int maxVisibleFriends = (int)Math.floor((double)((friendListAvailableHeight + friendEntryPadding) / (friendEntryHeight + friendEntryPadding)));
               boolean needsScroll = friends.size() > maxVisibleFriends;
               if (needsScroll) {
                  float scrollBarWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F);
                  float scrollBarX = rightPanelLineX + rightPanelLineWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, scrollBarX, friendListStartY, scrollBarWidth, friendListAvailableHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.5F), new Color(37, 37, 46, (int)(255.0F * alpha)));
                  float scrollThumbHeight = Math.max(Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F), (float)maxVisibleFriends / (float)friends.size() * friendListAvailableHeight);
                  float maxScrollOffset = (float)(friends.size() - maxVisibleFriends);
                  float scrollProgress = Math.min(1.0F, this.rightPanelScrollOffset / maxScrollOffset);
                  float scrollThumbY = friendListStartY + scrollProgress * (friendListAvailableHeight - scrollThumbHeight);
                  scrollThumbY = Math.max(friendListStartY, Math.min(scrollThumbY, friendListStartY + friendListAvailableHeight - scrollThumbHeight));
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, scrollBarX, scrollThumbY, scrollBarWidth, scrollThumbHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.5F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               }

               int startIndex = (int)this.rightPanelScrollOffset;
               int endIndex = Math.min(startIndex + maxVisibleFriends, friends.size());
               float currentFriendY = friendListStartY;

               for(int i = startIndex; i < endIndex; ++i) {
                  K204c47a3480aa2d86b friend = (K204c47a3480aa2d86b)friends.get(i);
                  String friendName = friend.getUsername();
                  GameProfile gameProfile = new GameProfile(UUID.nameUUIDFromBytes(("OfflinePlayer:" + friendName).getBytes()), friendName);
                  class_742 player = null;
                  if (mc.field_1687 != null) {
                     for(class_742 p : mc.field_1687.method_18456()) {
                        if (p.method_5477().getString().equalsIgnoreCase(friendName)) {
                           player = p;
                           gameProfile = p.method_7334();
                           break;
                        }
                     }
                  }

                  class_8685 skinTextures = mc.method_1582().method_52862(gameProfile);
                  float headRadius = Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F);
                  float headGap = Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
                  if (favoriteFriends.contains(friendName)) {
                     K2b5e2c5f2b9891b450.RECT.draw(matrices, rightPanelLineX - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.5F), currentFriendY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.5F), headSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F), headSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F), headRadius + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), new Color(255, 215, 100, (int)(255.0F * alpha)));
                  }

                  K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, rightPanelLineX, currentFriendY, headSize, headSize, headRadius, new Color(0, 0, 0, (int)(185.0F * alpha)));
                  if (player != null) {
                     K2b5e2c5f2b9891b450.TEXTURE_RECT.drawHead(matrices, player, rightPanelLineX + headGap, currentFriendY + headGap, headSize - headGap * 2.0F, headSize - headGap * 2.0F, headRadius, 1.0F, new Color(255, 255, 255, (int)(255.0F * alpha)));
                  } else {
                     class_2960 skinTexture = skinTextures.comp_1626();
                     int textureId = mc.method_1531().method_4619(skinTexture).method_4624();
                     K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(matrices, rightPanelLineX + headGap, currentFriendY + headGap, headSize - headGap * 2.0F, headSize - headGap * 2.0F, headRadius, new Color(255, 255, 255, (int)(255.0F * alpha)), 0.125F, 0.125F, 0.125F, 0.125F, textureId);
                     K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(matrices, rightPanelLineX, currentFriendY, headSize, headSize, headRadius, new Color(255, 255, 255, (int)(255.0F * alpha)), 0.625F, 0.125F, 0.125F, 0.125F, textureId);
                  }

                  float nicknameX = rightPanelLineX + headSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                  float nicknameY = currentFriendY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
                  float nicknameSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
                  float maxNicknameWidth = rightPanelLineX + rightPanelLineWidth - nicknameX - Ke4708cf52c3b89f5ae.getInstance().scaledUi(15.0F);
                  String displayName = friend.getDisplayName();
                  float nicknameWidth = K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(displayName, nicknameSize);
                  if (nicknameWidth > maxNicknameWidth) {
                     while(displayName.length() > 1) {
                        displayName = displayName.substring(0, displayName.length() - 1);
                        float testWidth = K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(displayName + "...", nicknameSize);
                        if (testWidth <= maxNicknameWidth) {
                           displayName = displayName + "...";
                           break;
                        }
                     }
                  }

                  K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, displayName, nicknameX, nicknameY, nicknameSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
                  float statusY = nicknameY + nicknameSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F);
                  float circleSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F);
                  float circleY = statusY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
                  Color statusCircleColor = friend.isOnline() ? new Color(50, 200, 50, (int)(255.0F * alpha)) : new Color(200, 50, 50, (int)(255.0F * alpha));
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, nicknameX, circleY, circleSize, circleSize, circleSize / 2.0F, statusCircleColor);
                  float statusSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(7.0F);
                  String statusText = friend.isOnline() ? "В игре" : "Не в сети";
                  float statusTextX = nicknameX + circleSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
                  K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, statusText, statusTextX, statusY, statusSize, new Color(180, 180, 180, (int)(255.0F * alpha)));
                  currentFriendY += friendEntryHeight + friendEntryPadding;
               }
            }

            if (this.showFriendContextMenu && this.selectedFriend != null) {
               float contextMenuWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(180.0F);
               float contextMenuHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(90.0F);
               float menuRadius = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, this.contextMenuX, this.contextMenuY, contextMenuWidth, contextMenuHeight, menuRadius, new Color(21, 21, 28, (int)(230.0F * alpha)));
               K2b5e2c5f2b9891b450.RECT.draw(matrices, this.contextMenuX - Ke4708cf52c3b89f5ae.getInstance().scaledUi(0.5F), this.contextMenuY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(0.5F), contextMenuWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), contextMenuHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), menuRadius + Ke4708cf52c3b89f5ae.getInstance().scaledUi(0.5F), new Color(42, 42, 54, (int)(200.0F * alpha)));
               float itemHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(30.0F);
               float itemPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(0.0F);
               float iconSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(14.0F);
               float textSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
               float itemY = this.contextMenuY;
               boolean hoverFavorite = (float)mouseX >= this.contextMenuX && (float)mouseX <= this.contextMenuX + contextMenuWidth && (float)mouseY >= itemY && (float)mouseY <= itemY + itemHeight;
               boolean hoverCopy = (float)mouseX >= this.contextMenuX && (float)mouseX <= this.contextMenuX + contextMenuWidth && (float)mouseY >= itemY + itemHeight + itemPadding && (float)mouseY <= itemY + (itemHeight + itemPadding) * 2.0F;
               boolean hoverMessage = (float)mouseX >= this.contextMenuX && (float)mouseX <= this.contextMenuX + contextMenuWidth && (float)mouseY >= itemY + (itemHeight + itemPadding) * 2.0F && (float)mouseY <= itemY + (itemHeight + itemPadding) * 3.0F;
               if (hoverFavorite) {
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, this.contextMenuX, itemY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), contextMenuWidth, itemHeight - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(50.0F * alpha)));
               }

               float iconX = this.contextMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F);
               float iconY = itemY + (itemHeight - iconSize) / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
               boolean isFavorite = favoriteFriends.contains(this.selectedFriend);
               K896065ea0a2bba4f47.ROLE_ICONS_BITMAP.drawIcon(matrices, 'F', iconX, iconY, iconSize, isFavorite ? new Color(255, 215, 100, (int)(255.0F * alpha)) : new Color(255, 255, 255, (int)(255.0F * alpha)));
               float textX = this.contextMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(30.0F);
               float textY = itemY + (itemHeight - K896065ea0a2bba4f47.SF_REGULAR.getHeight(textSize)) / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
               K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, isFavorite ? "Убрать из избранного" : "Добавить в избранные", textX, textY, textSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               itemY += itemHeight + itemPadding;
               if (hoverCopy) {
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, this.contextMenuX, itemY, contextMenuWidth, itemHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(0.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(50.0F * alpha)));
               }

               iconX = this.contextMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F);
               iconY = itemY + (itemHeight - iconSize) / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
               K896065ea0a2bba4f47.ICONS_DESHUX.drawText(matrices, "m", iconX, iconY, iconSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               textX = this.contextMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(30.0F);
               textY = itemY + (itemHeight - K896065ea0a2bba4f47.SF_REGULAR.getHeight(textSize)) / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
               K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, "Копировать ник", textX, textY, textSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               itemY += itemHeight + itemPadding;
               if (hoverMessage) {
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, this.contextMenuX, itemY, contextMenuWidth, itemHeight - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(50.0F * alpha)));
               }

               iconX = this.contextMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F);
               iconY = itemY + (itemHeight - iconSize) / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
               K896065ea0a2bba4f47.ROLE_ICONS_BITMAP.drawIcon(matrices, 'J', iconX, iconY, iconSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               textX = this.contextMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(30.0F);
               textY = itemY + (itemHeight - K896065ea0a2bba4f47.SF_REGULAR.getHeight(textSize)) / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
               K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, "Написать личное сообщение", textX, textY, textSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
            }

            float gapBetweenPanels = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
            float leftLineWidth;
            if (this.hideFriendPanel) {
               leftLineWidth = menuWidth;
            } else {
               leftLineWidth = verticalLineX - menuX - gapBetweenPanels;
            }

            K2b5e2c5f2b9891b450.RECT.draw(matrices, menuX, verticalLineY, leftLineWidth, verticalLineHeight, new Vector4f(cornerRadius, cornerRadius, 0.0F, 0.0F), new Color(37, 37, 46, (int)(255.0F * alpha)));
            K2b5e2c5f2b9891b450.RECT.draw(matrices, menuX + lineThickness, verticalLineY + lineThickness, leftLineWidth - lineThickness * 2.0F, verticalLineHeight - lineThickness, new Vector4f(cornerRadius - lineThickness, cornerRadius - lineThickness, 0.0F, 0.0F), new Color(18, 18, 24, (int)(255.0F * alpha)));
            float bottomLineOffset = Ke4708cf52c3b89f5ae.getInstance().scaledUi(50.0F);
            float sideInset = Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.5F);
            float bottomLineY = verticalLineY + verticalLineHeight - bottomLineOffset;
            float textX = menuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
            float textY = bottomLineY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(293.0F);
            float textSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
            float ircTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(11.0F);
            float ircTextY = textY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(43.0F);
            K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, "IRC Chat", textX, ircTextY, ircTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
            String chatModeText;
            if (this.friendChatMode && this.currentPrivateChat != null) {
               K204c47a3480aa2d86b friend = K8ee912418ef60c15b7.getInstance().getIRCFriendByUsername(this.currentPrivateChat);
               String onlineStatus = "";
               if (friend != null) {
                  onlineStatus = friend.isOnline() ? " • В игре" : " • Не в сети";
               }

               chatModeText = "Message: " + this.currentPrivateChat + onlineStatus;
            } else if (this.friendChatMode && this.currentPrivateChat == null) {
               chatModeText = "Private Message";
            } else {
               chatModeText = "Global Chat";
            }

            K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, chatModeText, textX, textY, textSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
            if (this.friendChatMode && this.currentPrivateChat == null) {
               float textWidth = K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(chatModeText, textSize);
               float searchFieldX = textX + textWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(15.0F);
               float searchFieldY = textY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F);
               float searchFieldWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(150.0F);
               float searchFieldHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
               boolean hoverSearchField = (float)mouseX >= searchFieldX && (float)mouseX <= searchFieldX + searchFieldWidth && (float)mouseY >= searchFieldY && (float)mouseY <= searchFieldY + searchFieldHeight;
               K2b5e2c5f2b9891b450.RECT.draw(matrices, searchFieldX, searchFieldY, searchFieldWidth, searchFieldHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), hoverSearchField ? new Color(48, 48, 56, (int)(255.0F * alpha)) : new Color(32, 32, 40, (int)(255.0F * alpha)));
               if (this.playerSearchFocused) {
                  float searchLineThickness = Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, searchFieldX, searchFieldY + searchFieldHeight - searchLineThickness, searchFieldWidth, searchLineThickness, 0.0F, new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               }

               float searchTextX = searchFieldX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float searchTextY = searchFieldY + (searchFieldHeight - Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F)) / 2.0F;
               float searchTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
               if (this.playerSearchInput.isEmpty()) {
                  K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, "Поиск игрока...", searchTextX, searchTextY, searchTextSize, new Color(96, 96, 112, (int)(255.0F * alpha)));
                  if (this.playerSearchFocused && System.currentTimeMillis() % 1000L < 500L) {
                     K2b5e2c5f2b9891b450.RECT.draw(matrices, searchTextX, searchTextY, Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), searchTextSize, 0.0F, new Color(255, 255, 255, (int)(255.0F * alpha)));
                  }
               } else {
                  String displayText = "@" + this.playerSearchInput;
                  K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, displayText, searchTextX, searchTextY, searchTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
                  if (this.playerSearchFocused && System.currentTimeMillis() % 1000L < 500L) {
                     float cursorX = searchTextX + K896065ea0a2bba4f47.SF_REGULAR.getWidth(displayText, searchTextSize);
                     K2b5e2c5f2b9891b450.RECT.draw(matrices, cursorX, searchTextY, Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), searchTextSize, 0.0F, new Color(255, 255, 255, (int)(255.0F * alpha)));
                  }
               }
            }

            float textWidth = K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(chatModeText, textSize);
            float iconButtonSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(16.0F);
            float iconButtonX = textX + textWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
            if (this.friendChatMode && this.currentPrivateChat == null) {
               iconButtonX += Ke4708cf52c3b89f5ae.getInstance().scaledUi(165.0F);
            }

            float iconButtonY = textY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
            boolean hoverBackButton = (float)mouseX >= iconButtonX && (float)mouseX <= iconButtonX + iconButtonSize && (float)mouseY >= iconButtonY && (float)mouseY <= iconButtonY + iconButtonSize;
            K2b5e2c5f2b9891b450.RECT.draw(matrices, iconButtonX, iconButtonY, iconButtonSize, iconButtonSize, Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), hoverBackButton ? new Color(53, 53, 62, (int)(255.0F * alpha)) : new Color(37, 37, 46, (int)(255.0F * alpha)));
            float iconSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
            float iconX = iconButtonX + (iconButtonSize - iconSize) / 2.0F;
            float iconY = iconButtonY + (iconButtonSize - iconSize) / 2.0F;
            if (this.friendChatMode && this.currentPrivateChat != null) {
               K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, "←", iconX - Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), iconY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), iconSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), new Color(255, 255, 255, (int)(255.0F * alpha)));
            } else {
               K896065ea0a2bba4f47.ICONS_DESHUX.drawText(matrices, "p", iconX, iconY, iconSize, new Color(170, 170, 170, (int)(255.0F * alpha)));
            }

            if (this.friendChatMode && this.currentPrivateChat == null) {
               List<String> allDialogs = K8ee912418ef60c15b7.getInstance().getDialogUsers();
               List<String> displayList;
               if (!this.playerSearchInput.isEmpty()) {
                  displayList = new ArrayList(this.searchResults);
                  if (!this.playerSearchInput.equals(this.lastSearchQuery) && this.playerSearchInput.length() >= 2) {
                     this.lastSearchQuery = this.playerSearchInput;
                     K3e5d28a93090bc4a75.getInstance().searchUsers(this.playerSearchInput);
                     System.out.println("[IRC SEARCH] Отправлен запрос поиска: " + this.playerSearchInput);
                  }

                  System.out.println("[IRC SEARCH] Показываем результатов: " + displayList.size());
               } else {
                  displayList = allDialogs;
                  this.searchResults.clear();
                  this.lastSearchQuery = "";
               }

               float friendsListStartY = ircTextY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(75.0F);
               float friendsListX = menuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               float friendHeadSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(28.0F);
               float friendItemHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
               float friendItemSpacing = Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.5F);
               float friendChatBottomY = verticalLineY + verticalLineHeight;
               float availableHeight = friendChatBottomY - friendsListStartY;
               int maxVisibleFriendsInChat = (int)Math.floor((double)(availableHeight / (friendItemHeight + friendItemSpacing)));
               float scrollBarSpace = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
               float panelGap = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               float rightBoundary = verticalLineX - panelGap;
               float itemWidth = rightBoundary - friendsListX - scrollBarSpace - Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               boolean needsFriendChatScroll = displayList.size() > maxVisibleFriendsInChat;
               if (needsFriendChatScroll) {
                  float scrollBarWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F);
                  float scrollBarX = rightBoundary - scrollBarWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                  float scrollableHeight = (float)maxVisibleFriendsInChat * (friendItemHeight + friendItemSpacing);
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, scrollBarX, friendsListStartY, scrollBarWidth, scrollableHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.5F), new Color(37, 37, 46, (int)(255.0F * alpha)));
                  float scrollThumbHeight = Math.max(Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F), (float)maxVisibleFriendsInChat / (float)displayList.size() * scrollableHeight);
                  float maxScrollOffset = (float)(displayList.size() - maxVisibleFriendsInChat);
                  float scrollProgress = maxScrollOffset > 0.0F ? Math.min(1.0F, this.friendChatScrollOffset / maxScrollOffset) : 0.0F;
                  float scrollThumbY = friendsListStartY + scrollProgress * (scrollableHeight - scrollThumbHeight);
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, scrollBarX, scrollThumbY, scrollBarWidth, scrollThumbHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.5F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               }

               if (displayList.isEmpty() && !this.playerSearchInput.isEmpty()) {
                  float noResultsY = friendsListStartY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
                  K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, "Ничего не найдено", friendsListX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F), noResultsY, Ke4708cf52c3b89f5ae.getInstance().scaledUi(11.0F), new Color(128, 128, 144, (int)(255.0F * alpha)));
               } else {
                  int chatStartIndex = (int)this.friendChatScrollOffset;
                  int chatEndIndex = Math.min(chatStartIndex + maxVisibleFriendsInChat, displayList.size());
                  float currentY = friendsListStartY;

                  for(int i = chatStartIndex; i < chatEndIndex; ++i) {
                     String friendName = (String)displayList.get(i);
                     float bgRadius = Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F);
                     K2b5e2c5f2b9891b450.RECT.draw(matrices, friendsListX - Ke4708cf52c3b89f5ae.getInstance().scaledUi(0.5F), currentY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(0.5F), itemWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), friendItemHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), bgRadius + Ke4708cf52c3b89f5ae.getInstance().scaledUi(0.5F), new Color(42, 42, 54, (int)(200.0F * alpha)));
                     K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, friendsListX, currentY, itemWidth, friendItemHeight, bgRadius, new Color(21, 21, 28, (int)(180.0F * alpha)));
                     float headX = friendsListX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F);
                     float headY = currentY + (friendItemHeight - friendHeadSize) / 2.0F;
                     float headRadius = Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
                     K2b5e2c5f2b9891b450.RECT.draw(matrices, headX, headY, friendHeadSize, friendHeadSize, headRadius, new Color(0, 0, 0, (int)(255.0F * alpha)));
                     class_742 player = null;

                     for(class_742 p : mc.field_1687.method_18456()) {
                        if (p.method_7334().getName().equals(friendName)) {
                           player = p;
                           break;
                        }
                     }

                     if (player != null) {
                        K2b5e2c5f2b9891b450.TEXTURE_RECT.drawHead(matrices, player, headX, headY, friendHeadSize, friendHeadSize, headRadius, headRadius, new Color(255, 255, 255, (int)(255.0F * alpha)));
                     }

                     float nickX = headX + friendHeadSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                     float nickY = currentY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                     float nickSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
                     K204c47a3480aa2d86b dialogFriend = K8ee912418ef60c15b7.getInstance().getIRCFriendByUsername(friendName);
                     String displayName = dialogFriend != null ? dialogFriend.getDisplayName() : friendName;
                     K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, displayName, nickX, nickY, nickSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
                     float statusY = nickY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(13.0F);
                     float statusCircleSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F);
                     boolean isOnline = dialogFriend != null && dialogFriend.isOnline();
                     Color statusColor = isOnline ? new Color(0, 255, 0, (int)(255.0F * alpha)) : new Color(255, 0, 0, (int)(255.0F * alpha));
                     K2b5e2c5f2b9891b450.RECT.draw(matrices, nickX, statusY, statusCircleSize, statusCircleSize, statusCircleSize / 2.0F, statusColor);
                     float statusTextX = nickX + statusCircleSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
                     float statusTextY = statusY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F);
                     float statusTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(7.0F);
                     K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, isOnline ? "В игре" : "Не в сети", statusTextX, statusTextY, statusTextSize, new Color(150, 150, 150, (int)(255.0F * alpha)));
                     int messageCount = K8ee912418ef60c15b7.getInstance().getUnreadCount(friendName);
                     if (messageCount > 0) {
                        float msgCountX = friendsListX + itemWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(25.0F) - 1.0F;
                        float msgCountY = currentY + (friendItemHeight - Ke4708cf52c3b89f5ae.getInstance().scaledUi(16.0F)) / 2.0F;
                        float msgCircleSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(16.0F);
                        K2b5e2c5f2b9891b450.RECT.draw(matrices, msgCountX, msgCountY, msgCircleSize, msgCircleSize, msgCircleSize / 2.0F, new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
                        String countText = String.valueOf(messageCount);
                        float msgTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                        float msgTextWidth = K896065ea0a2bba4f47.SF_REGULAR.getWidth(countText, msgTextSize);
                        K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, countText, msgCountX + msgCircleSize / 2.0F - msgTextWidth / 2.0F, msgCountY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F) + 1.5F, msgTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
                     }

                     currentY += friendItemHeight + friendItemSpacing;
                  }
               }
            }

            if (!this.friendChatMode || this.friendChatMode && this.currentPrivateChat != null) {
               K2b5e2c5f2b9891b450.RECT.draw(matrices, menuX + sideInset, bottomLineY, leftLineWidth - sideInset * 2.0F, lineThickness, 0.0F, new Color(37, 37, 46, (int)(255.0F * alpha)));
               float rectLeftPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               float rectRightPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
               float rectWidth = leftLineWidth - rectLeftPadding - rectRightPadding;
               float rectHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(30.0F);
               float availableSpace = verticalLineY + verticalLineHeight - (bottomLineY + lineThickness);
               float rectY = bottomLineY + lineThickness + (availableSpace - rectHeight) / 2.0F;
               K2b5e2c5f2b9891b450.RECT.draw(matrices, menuX + rectLeftPadding, rectY, rectWidth, rectHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), new Color(26, 26, 34, (int)(255.0F * alpha)));
               float inputTextX = menuX + rectLeftPadding + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float inputTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
               float lineHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
               float maxTextWidth = rectWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(16.0F);
               if (this.messageInput.isEmpty() && !this.isMessageFieldFocused) {
                  float placeholderY = rectY + (rectHeight - inputTextSize) / 2.0F;
                  K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, "Напишите сообщение", inputTextX, placeholderY, inputTextSize, new Color(102, 102, 102, (int)(255.0F * alpha)));
               } else {
                  List<String> lines = new ArrayList();
                  String currentLine = "";

                  for(int i = 0; i < this.messageInput.length(); ++i) {
                     String testLine = currentLine + this.messageInput.charAt(i);
                     float testWidth = K896065ea0a2bba4f47.SF_REGULAR.getWidth(testLine, inputTextSize);
                     if (testWidth > maxTextWidth && !currentLine.isEmpty()) {
                        lines.add(currentLine);
                        currentLine = String.valueOf(this.messageInput.charAt(i));
                     } else {
                        currentLine = testLine;
                     }
                  }

                  if (!currentLine.isEmpty()) {
                     lines.add(currentLine);
                  }

                  float totalTextHeight = (float)lines.size() * lineHeight;
                  float startY;
                  if (totalTextHeight <= rectHeight) {
                     startY = rectY + (rectHeight - totalTextHeight) / 2.0F;
                     this.inputTextScrollY = 0.0F;
                  } else {
                     this.inputTextScrollY = totalTextHeight - rectHeight;
                     startY = rectY - this.inputTextScrollY;
                  }

                  if (this.hasSelection()) {
                     int selStart = Math.min(this.selectionStart, this.selectionEnd);
                     int selEnd = Math.max(this.selectionStart, this.selectionEnd);
                     int charCount = 0;
                     float currentY = startY;

                     for(int lineIdx = 0; lineIdx < lines.size(); ++lineIdx) {
                        String line = (String)lines.get(lineIdx);
                        int lineEnd = charCount + line.length();
                        if (lineEnd > selStart && charCount < selEnd) {
                           int selStartInLine = Math.max(0, selStart - charCount);
                           int selEndInLine = Math.min(line.length(), selEnd - charCount);
                           String beforeSel = line.substring(0, selStartInLine);
                           String selectedPart = line.substring(selStartInLine, selEndInLine);
                           float selX = inputTextX + K896065ea0a2bba4f47.SF_REGULAR.getWidth(beforeSel, inputTextSize);
                           float selWidth = K896065ea0a2bba4f47.SF_REGULAR.getWidth(selectedPart, inputTextSize);
                           if (currentY + lineHeight >= rectY && currentY <= rectY + rectHeight) {
                              K2b5e2c5f2b9891b450.RECT.draw(matrices, selX, currentY, selWidth, inputTextSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), 0.0F, new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(120.0F * alpha)));
                           }
                        }

                        charCount += line.length();
                        currentY += lineHeight;
                     }
                  }

                  float currentY = startY;

                  for(String line : lines) {
                     if (currentY + lineHeight >= rectY && currentY <= rectY + rectHeight) {
                        K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, line, inputTextX, currentY, inputTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
                     }

                     currentY += lineHeight;
                  }

                  if (this.isMessageFieldFocused && !this.hasSelection() && (System.currentTimeMillis() - this.lastCursorBlink) % 1000L < 500L) {
                     float cursorX = inputTextX;
                     float cursorY;
                     if (this.messageInput.isEmpty()) {
                        cursorY = rectY + (rectHeight - inputTextSize) / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
                     } else {
                        int charCount = 0;
                        int cursorLine = 0;
                        float cursorXInLine = 0.0F;

                        for(int lineIdx = 0; lineIdx < lines.size(); ++lineIdx) {
                           String line = (String)lines.get(lineIdx);
                           if (charCount + line.length() >= this.cursorPosition) {
                              cursorLine = lineIdx;
                              String textBeforeCursorInLine = line.substring(0, this.cursorPosition - charCount);
                              cursorXInLine = K896065ea0a2bba4f47.SF_REGULAR.getWidth(textBeforeCursorInLine, inputTextSize);
                              break;
                           }

                           charCount += line.length();
                        }

                        cursorX = inputTextX + cursorXInLine;
                        cursorY = startY + (float)cursorLine * lineHeight;
                     }

                     K2b5e2c5f2b9891b450.RECT.draw(matrices, cursorX, cursorY, Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), inputTextSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), 0.0F, new Color(255, 255, 255, (int)(255.0F * alpha)));
                  }
               }

               float chatAreaX = menuX + lineThickness + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               float chatAreaY = verticalLineY + lineThickness + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               float chatAreaWidth = leftLineWidth - lineThickness * 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float chatAreaHeight = bottomLineY - verticalLineY - lineThickness - Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float messageY = bottomLineY - lineThickness - Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float messageTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
               float messageLineHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
               float messagePadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float bubblePaddingX = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float bubblePaddingY = Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F);
               float sendButtonArea = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
               float maxBubbleWidth = chatAreaWidth - sendButtonArea - Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               List<K8001222dc0dc25d83d> currentMessages;
               if (this.friendChatMode && this.currentPrivateChat != null) {
                  currentMessages = K8ee912418ef60c15b7.getInstance().getPrivateMessages(this.currentPrivateChat);
               } else {
                  currentMessages = K8ee912418ef60c15b7.getInstance().getGlobalMessages();
               }

               float totalMessagesHeight = 0.0F;
               List<Float> messageHeights = new ArrayList();

               for(K8001222dc0dc25d83d msg : currentMessages) {
                  List<String> messageLines = new ArrayList();
                  String messageText = msg.getMessage();
                  String currentLine = "";

                  for(int charIdx = 0; charIdx < messageText.length(); ++charIdx) {
                     String testLine = currentLine + messageText.charAt(charIdx);
                     float testWidth = K896065ea0a2bba4f47.SF_REGULAR.getWidth(testLine, messageTextSize);
                     if (testWidth > maxBubbleWidth - bubblePaddingX * 2.0F && !currentLine.isEmpty()) {
                        messageLines.add(currentLine);
                        currentLine = String.valueOf(messageText.charAt(charIdx));
                     } else {
                        currentLine = testLine;
                     }
                  }

                  if (!currentLine.isEmpty()) {
                     messageLines.add(currentLine);
                  }

                  float nicknameHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(11.0F);
                  float msgHeight = nicknameHeight + (float)messageLines.size() * messageLineHeight + bubblePaddingY * 2.0F + messagePadding;
                  messageHeights.add(msgHeight);
                  totalMessagesHeight += msgHeight;
               }

               boolean needsChatScroll = totalMessagesHeight > chatAreaHeight;
               float maxChatScrollOffset = Math.max(0.0F, totalMessagesHeight - chatAreaHeight);
               if (this.chatScrollOffset < 0.0F) {
                  this.chatScrollOffset = 0.0F;
               }

               if (this.chatScrollOffset > maxChatScrollOffset) {
                  this.chatScrollOffset = maxChatScrollOffset;
               }

               if (needsChatScroll) {
                  float scrollBarWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F);
                  float panelGap = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                  float rightBoundary = !this.hideFriendPanel ? verticalLineX : menuX + leftLineWidth;
                  float scrollBarX = rightBoundary - panelGap + (panelGap - scrollBarWidth) / 2.0F;
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, scrollBarX, chatAreaY, scrollBarWidth, chatAreaHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.5F), new Color(37, 37, 46, (int)(255.0F * alpha)));
                  float scrollThumbHeight = Math.max(Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F), chatAreaHeight / totalMessagesHeight * chatAreaHeight);
                  float scrollProgress = maxChatScrollOffset > 0.0F ? Math.min(1.0F, this.chatScrollOffset / maxChatScrollOffset) : 0.0F;
                  float scrollThumbY = chatAreaY + (1.0F - scrollProgress) * (chatAreaHeight - scrollThumbHeight);
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, scrollBarX, scrollThumbY, scrollBarWidth, scrollThumbHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.5F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               }

               double scale = mc.method_22683().method_4495();
               int scissorX = (int)((double)chatAreaX * scale);
               int scissorY = (int)(((double)mc.method_22683().method_4507() / scale - (double)(chatAreaY + chatAreaHeight)) * scale);
               int scissorWidth = (int)((double)chatAreaWidth * scale);
               int scissorHeight = (int)((double)chatAreaHeight * scale);
               RenderSystem.enableScissor(scissorX, scissorY, scissorWidth, scissorHeight);
               this.messageBounds.clear();
               messageY += this.chatScrollOffset;
               String currentRole = this.getRoleDisplayName();
               boolean isPremium = currentRole.equals("Premium") || currentRole.equals("Developer") || currentRole.equals("Admin") || currentRole.equals("Owner");

               for(int i = currentMessages.size() - 1; i >= 0; --i) {
                  K8001222dc0dc25d83d msg = (K8001222dc0dc25d83d)currentMessages.get(i);
                  List<String> messageLines = new ArrayList();
                  String messageText = msg.getMessage();
                  String currentLine = "";

                  for(int charIdx = 0; charIdx < messageText.length(); ++charIdx) {
                     String testLine = currentLine + messageText.charAt(charIdx);
                     float testWidth = K896065ea0a2bba4f47.SF_REGULAR.getWidth(testLine, messageTextSize);
                     if (testWidth > maxBubbleWidth - bubblePaddingX * 2.0F && !currentLine.isEmpty()) {
                        messageLines.add(currentLine);
                        currentLine = String.valueOf(messageText.charAt(charIdx));
                     } else {
                        currentLine = testLine;
                     }
                  }

                  if (!currentLine.isEmpty()) {
                     messageLines.add(currentLine);
                  }

                  float nicknameHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(11.0F);
                  float bubbleHeight = nicknameHeight + (float)messageLines.size() * messageLineHeight + bubblePaddingY * 2.0F;
                  float msgBubbleY = messageY - bubbleHeight;
                  if (msg.isOwnMessage()) {
                     float bubbleWidth = 0.0F;
                     String timeStr = msg.getTime();
                     float timeSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(7.0F);
                     float timeWidth = K896065ea0a2bba4f47.SF_REGULAR.getWidth(timeStr, timeSize);
                     float timeGap = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                     String displayNickForOwn = this.friendChatMode && this.currentPrivateChat != null ? "Вы" : msg.getNickname();
                     float nicknameWidth = K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(displayNickForOwn, messageTextSize);
                     if (msg.getPrefix() != null && !msg.getPrefix().isEmpty()) {
                        String var694 = msg.getPrefix();
                        String cleanPrefix = var694.replaceAll("§.", "") + " ";
                        nicknameWidth += K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(cleanPrefix, messageTextSize);
                     }

                     if (nicknameWidth > bubbleWidth) {
                        bubbleWidth = nicknameWidth;
                     }

                     for(int lineIdx = 0; lineIdx < messageLines.size(); ++lineIdx) {
                        String line = (String)messageLines.get(lineIdx);
                        float lineWidth = K896065ea0a2bba4f47.SF_REGULAR.getWidth(line, messageTextSize);
                        if (lineIdx == messageLines.size() - 1) {
                           lineWidth += timeGap + timeWidth;
                        }

                        if (lineWidth > bubbleWidth) {
                           bubbleWidth = lineWidth;
                        }
                     }

                     bubbleWidth += bubblePaddingX * 2.0F;
                     float bubbleX = chatAreaX + chatAreaWidth - bubbleWidth;
                     if (this.useCustomMessageColor && isPremium) {
                        float time = (float)(System.currentTimeMillis() % 6000L) / 6000.0F;
                        float wave = (float)Math.sin((double)time * Math.PI * (double)2.0F) * 0.5F + 0.5F;
                        int r = 255;
                        int g = (int)(230.0F + wave * 25.0F);
                        int b = (int)(wave * 50.0F);
                        K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, bubbleX, msgBubbleY, bubbleWidth, bubbleHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F), new Color(r, g, b, (int)(140.0F * alpha)));
                     } else {
                        K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, bubbleX, msgBubbleY, bubbleWidth, bubbleHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(128.0F * alpha)));
                     }

                     float nicknameX = bubbleX + bubblePaddingX;
                     float nicknameY = msgBubbleY + bubblePaddingY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F);
                     K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, displayNickForOwn, nicknameX, nicknameY, messageTextSize, new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
                     if (msg.getPrefix() != null && !msg.getPrefix().isEmpty()) {
                        float prefixX = nicknameX + K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(displayNickForOwn, messageTextSize) + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                        this.drawMinecraftColoredText(matrices, msg.getPrefix(), prefixX, nicknameY, messageTextSize, alpha);
                     }

                     float msgTextX = bubbleX + bubblePaddingX;
                     float msgTextY = nicknameY + nicknameHeight;

                     for(String line : messageLines) {
                        K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, line, msgTextX, msgTextY, messageTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
                        msgTextY += messageLineHeight;
                     }

                     if (this.showMessageTime) {
                        float timeX = bubbleX + bubbleWidth - bubblePaddingX - timeWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F);
                        float timeY = msgBubbleY + bubbleHeight - bubblePaddingY - timeSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
                        K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, timeStr, timeX, timeY, timeSize, new Color(153, 153, 153, (int)(200.0F * alpha)));
                     }

                     this.messageBounds.add(new K7db34ec333fff41384(msg.getNickname(), bubbleX, msgBubbleY, bubbleWidth, bubbleHeight));
                  } else {
                     boolean isIgnored = this.ignoredPlayers.contains(msg.getNickname());
                     float bubbleWidth = 0.0F;
                     String timeStr = msg.getTime();
                     float timeSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(7.0F);
                     float timeWidth = K896065ea0a2bba4f47.SF_REGULAR.getWidth(timeStr, timeSize);
                     float timeGap = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                     float nicknameWidth = K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(msg.getNickname(), messageTextSize);
                     if (msg.getPrefix() != null && !msg.getPrefix().isEmpty()) {
                        String var10000 = msg.getPrefix();
                        String cleanPrefix = var10000.replaceAll("§.", "") + " ";
                        nicknameWidth += K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(cleanPrefix, messageTextSize);
                     }

                     if (nicknameWidth > bubbleWidth) {
                        bubbleWidth = nicknameWidth;
                     }

                     for(int lineIdx = 0; lineIdx < messageLines.size(); ++lineIdx) {
                        String line = (String)messageLines.get(lineIdx);
                        float lineWidth = K896065ea0a2bba4f47.SF_REGULAR.getWidth(line, messageTextSize);
                        if (lineIdx == messageLines.size() - 1) {
                           lineWidth += timeGap + timeWidth;
                        }

                        if (lineWidth > bubbleWidth) {
                           bubbleWidth = lineWidth;
                        }
                     }

                     bubbleWidth += bubblePaddingX * 2.0F;
                     K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, chatAreaX, msgBubbleY, bubbleWidth, bubbleHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F), new Color(64, 64, 80, (int)(128.0F * alpha)));
                     float nicknameX = chatAreaX + bubblePaddingX;
                     float nicknameY = msgBubbleY + bubblePaddingY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F);
                     K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, msg.getNickname(), nicknameX, nicknameY, messageTextSize, new Color(204, 204, 204, (int)(255.0F * alpha)));
                     if (msg.getPrefix() != null && !msg.getPrefix().isEmpty()) {
                        float prefixX = nicknameX + K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(msg.getNickname(), messageTextSize) + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                        this.drawMinecraftColoredText(matrices, msg.getPrefix(), prefixX, nicknameY, messageTextSize, alpha);
                     }

                     float msgTextX = chatAreaX + bubblePaddingX;
                     float msgTextY = nicknameY + nicknameHeight;
                     if (isIgnored) {
                        int wordCount = messageText.split("\\s+").length;
                        String hiddenText = "";

                        for(int w = 0; w < wordCount; ++w) {
                           hiddenText = hiddenText + (w > 0 ? " " : "") + "###";
                        }

                        K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, hiddenText, msgTextX, msgTextY, messageTextSize, new Color(128, 128, 128, (int)(255.0F * alpha)));
                     } else {
                        for(String line : messageLines) {
                           K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, line, msgTextX, msgTextY, messageTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
                           msgTextY += messageLineHeight;
                        }
                     }

                     if (this.showMessageTime) {
                        float timeX = chatAreaX + bubbleWidth - bubblePaddingX - timeWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F);
                        float timeY = msgBubbleY + bubbleHeight - bubblePaddingY - timeSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
                        K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, timeStr, timeX, timeY, timeSize, new Color(153, 153, 153, (int)(200.0F * alpha)));
                     }

                     this.messageBounds.add(new K7db34ec333fff41384(msg.getNickname(), chatAreaX, msgBubbleY, bubbleWidth, bubbleHeight));
                  }

                  messageY -= bubbleHeight + messagePadding;
               }

               RenderSystem.disableScissor();
               float squareX = menuX + rectLeftPadding + rectWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, squareX, rectY, rectHeight, rectHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), 255));
               float squareIconSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(16.0F);
               float squareIconX = squareX + (rectHeight - squareIconSize) / 2.0F;
               float squareIconY = rectY + (rectHeight - squareIconSize) / 2.0F;
               float centerOffsetX = -Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F);
               float centerOffsetY = Ke4708cf52c3b89f5ae.getInstance().scaledUi(0.5F);
               K896065ea0a2bba4f47.ICONS_DESHUX.drawText(matrices, "q", squareIconX + centerOffsetX, squareIconY + centerOffsetY, squareIconSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
            }

            K2b5e2c5f2b9891b450.RECT.draw(matrices, guiX, guiY, sidebarWidth, guiHeight, new Vector4f(radius, 0.0F, radius, 0.0F), new Color(26, 26, 34, (int)(255.0F * alpha)));
            float chatButtonSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(24.0F);
            float chatButtonX = guiX + (sidebarWidth - chatButtonSize) / 2.0F;
            float chatButtonY = guiY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(50.0F);
            boolean isChatButtonHovered = (float)mouseX >= chatButtonX && (float)mouseX <= chatButtonX + chatButtonSize && (float)mouseY >= chatButtonY && (float)mouseY <= chatButtonY + chatButtonSize;
            if (!this.friendChatMode || isChatButtonHovered) {
               K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, chatButtonX, chatButtonY, chatButtonSize, chatButtonSize, Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), 255));
            }

            float chatIconSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(16.0F);
            float chatIconX = chatButtonX + (chatButtonSize - chatIconSize) / 2.0F;
            float chatIconY = chatButtonY + (chatButtonSize - chatIconSize) / 2.0F;
            K896065ea0a2bba4f47.ROLE_ICONS_BITMAP.drawIcon(matrices, 'J', chatIconX, chatIconY, chatIconSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
            float wButtonSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(24.0F);
            float wButtonX = guiX + (sidebarWidth - wButtonSize) / 2.0F;
            float wButtonY = chatButtonY + chatButtonSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
            boolean isWButtonHovered = (float)mouseX >= wButtonX && (float)mouseX <= wButtonX + wButtonSize && (float)mouseY >= wButtonY && (float)mouseY <= wButtonY + wButtonSize;
            if (this.friendChatMode || isWButtonHovered) {
               K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, wButtonX, wButtonY, wButtonSize, wButtonSize, Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), 255));
            }

            float wIconSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(16.0F);
            float wIconX = wButtonX + (wButtonSize - wIconSize) / 2.0F;
            float wIconY = wButtonY + (wButtonSize - wIconSize) / 2.0F;
            K896065ea0a2bba4f47.ICONS_NURIK.drawText(matrices, "W", wIconX, wIconY, wIconSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
            int totalUnread = 0;

            for(String dialogUser : K8ee912418ef60c15b7.getInstance().getDialogUsers()) {
               totalUnread += K8ee912418ef60c15b7.getInstance().getUnreadCount(dialogUser);
            }

            if (totalUnread > 0) {
               String countStr = totalUnread > 99 ? "99+" : String.valueOf(totalUnread);
               float badgeSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
               float badgeX = wButtonX + wButtonSize - badgeSize * 0.6F;
               float badgeY = wButtonY - badgeSize * 0.4F;
               K2b5e2c5f2b9891b450.RECT.draw(matrices, badgeX, badgeY, badgeSize, badgeSize, badgeSize / 2.0F, new Color(220, 50, 50, (int)(255.0F * alpha)));
               float badgeTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F);
               float textW = K896065ea0a2bba4f47.SF_BOLD.getWidth(countStr, badgeTextSize);
               K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, countStr, badgeX + badgeSize / 2.0F - textW / 2.0F, badgeY + badgeSize / 2.0F - badgeTextSize / 2.0F + 0.5F, badgeTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
            }

            float dButtonSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(24.0F);
            float dButtonX = guiX + (sidebarWidth - dButtonSize) / 2.0F;
            float dButtonY = wButtonY + wButtonSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
            boolean isDButtonHovered = (float)mouseX >= dButtonX && (float)mouseX <= dButtonX + dButtonSize && (float)mouseY >= dButtonY && (float)mouseY <= dButtonY + dButtonSize;
            if (this.showDButtonMenu || isDButtonHovered) {
               K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, dButtonX, dButtonY, dButtonSize, dButtonSize, Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), 255));
            }

            float dIconSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(16.0F);
            float dIconX = dButtonX + (dButtonSize - dIconSize) / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F);
            float dIconY = dButtonY + (dButtonSize - dIconSize) / 2.0F;
            K896065ea0a2bba4f47.ICONS_NURIK.drawText(matrices, "D", dIconX, dIconY, dIconSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
            float skinHeadSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(35.0F);
            float skinHeadX = guiX + (sidebarWidth - skinHeadSize) / 2.0F;
            float skinHeadY = guiY + guiHeight - skinHeadSize - Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
            float skinOutlineWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.5F);
            float headRadius = Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F);
            Color activityColor = this.getPlayerActivityColor(alpha);
            K2b5e2c5f2b9891b450.RECT.draw(matrices, skinHeadX - skinOutlineWidth, skinHeadY - skinOutlineWidth, skinHeadSize + skinOutlineWidth * 2.0F, skinHeadSize + skinOutlineWidth * 2.0F, headRadius + skinOutlineWidth, activityColor);
            K2b5e2c5f2b9891b450.RECT.draw(matrices, skinHeadX, skinHeadY, skinHeadSize, skinHeadSize, headRadius, new Color(0, 0, 0, (int)(255.0F * alpha)));

            try {
               if (mc.field_1724 != null) {
                  K2b5e2c5f2b9891b450.TEXTURE_RECT.drawHead(matrices, mc.field_1724, skinHeadX, skinHeadY, skinHeadSize, skinHeadSize, headRadius, headRadius, new Color(255, 255, 255, (int)(255.0F * alpha)));
               }
            } catch (Exception var177) {
               K2b5e2c5f2b9891b450.RECT.draw(matrices, skinHeadX, skinHeadY, skinHeadSize, skinHeadSize, headRadius, new Color(42, 42, 54, (int)(255.0F * alpha)));
               float playerIconSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
               float playerIconX = skinHeadX + (skinHeadSize - playerIconSize) / 2.0F;
               float playerIconY = skinHeadY + (skinHeadSize - playerIconSize) / 2.0F;
               K896065ea0a2bba4f47.ICONS.drawText(matrices, "u", playerIconX, playerIconY, playerIconSize, new Color(128, 128, 144, (int)(255.0F * alpha)));
            }

            if (this.showPlayerHeadMenu) {
               float headMenuWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(100.0F);
               float headMenuHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(100.0F);
               float headMenuRadius = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float headMenuX = skinHeadX - headMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               float headMenuOutlineWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F);
               K2b5e2c5f2b9891b450.RECT.draw(matrices, headMenuX - headMenuOutlineWidth, skinHeadY - headMenuOutlineWidth, headMenuWidth + headMenuOutlineWidth * 2.0F, headMenuHeight + headMenuOutlineWidth * 2.0F, headMenuRadius, activityColor);
               K2b5e2c5f2b9891b450.RECT.draw(matrices, headMenuX, skinHeadY, headMenuWidth, headMenuHeight, headMenuRadius, new Color(26, 26, 34, (int)(255.0F * alpha)));
               float titleY = skinHeadY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F);
               float titleIconSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
               float titleIconX = headMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float titleIconY = titleY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
               K896065ea0a2bba4f47.ICONS_NURIK.drawText(matrices, "W", titleIconX, titleIconY, titleIconSize, new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               float titleTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
               float titleTextX = titleIconX + titleIconSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               float titleTextY = titleY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F);
               K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, "Мой профиль", titleTextX, titleTextY, titleTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               float lineY = titleY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(18.0F);
               float lineX = headMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float lineWidth = headMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(16.0F);
               K2b5e2c5f2b9891b450.RECT.draw(matrices, lineX, lineY, lineWidth, Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), 0.0F, new Color(37, 37, 46, (int)(255.0F * alpha)));
               float displayY = lineY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               String displayUsername;
               if (this.customTagInputFocused) {
                  displayUsername = this.editingCustomTag.isEmpty() ? "username" : this.editingCustomTag;
               } else {
                  displayUsername = this.customTag.isEmpty() ? "username" : this.customTag;
               }

               String fullDisplayUsername = "@" + displayUsername;
               float usernameTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float usernameWidth = K896065ea0a2bba4f47.SF_REGULAR.getWidth(fullDisplayUsername, usernameTextSize) + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float usernameHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(16.0F);
               float usernameX = headMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               boolean hoverUsername = (float)mouseX >= usernameX && (float)mouseX <= usernameX + usernameWidth && (float)mouseY >= displayY && (float)mouseY <= displayY + usernameHeight;
               boolean canEdit = this.canChangeCustomTag();
               Color usernameBg;
               if (this.customTagInputFocused) {
                  usernameBg = new Color(37, 37, 46, (int)(255.0F * alpha));
               } else if (hoverUsername && canEdit) {
                  usernameBg = new Color(34, 34, 42, (int)(255.0F * alpha));
               } else {
                  usernameBg = new Color(30, 30, 38, (int)(255.0F * alpha));
               }

               K2b5e2c5f2b9891b450.RECT.draw(matrices, usernameX, displayY, usernameWidth, usernameHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), usernameBg);
               float usernameTextX = usernameX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
               float usernameTextY = displayY + (usernameHeight - usernameTextSize) / 2.0F;
               if (this.customTagInputFocused) {
                  String editText = "@" + this.editingCustomTag;
                  K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, editText, usernameTextX, usernameTextY, usernameTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
                  if (System.currentTimeMillis() / 500L % 2L == 0L) {
                     float cursorX = usernameTextX + K896065ea0a2bba4f47.SF_REGULAR.getWidth(editText, usernameTextSize);
                     K2b5e2c5f2b9891b450.RECT.draw(matrices, cursorX, usernameTextY, Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), usernameTextSize, 0.0F, new Color(255, 255, 255, (int)(255.0F * alpha)));
                  }
               } else {
                  K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, fullDisplayUsername, usernameTextX, usernameTextY, usernameTextSize, this.customTag.isEmpty() ? new Color(128, 128, 144, (int)(255.0F * alpha)) : new Color(255, 255, 255, (int)(255.0F * alpha)));
               }

               String roleText = this.getRoleDisplayName();
               float roleTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float roleWidth = K896065ea0a2bba4f47.SF_REGULAR.getWidth(roleText, roleTextSize) + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float roleHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(16.0F);
               float roleX = usernameX + usernameWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               K2b5e2c5f2b9891b450.RECT.draw(matrices, roleX, displayY, roleWidth, roleHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               float roleTextX = roleX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
               float roleTextY = displayY + (roleHeight - roleTextSize) / 2.0F;
               K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, roleText, roleTextX, roleTextY, roleTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               String currentRole = this.getRoleDisplayName();
               boolean isPremium = currentRole.equals("Premium") || currentRole.equals("Developer") || currentRole.equals("Admin") || currentRole.equals("Owner");
               float checkboxSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
               float colorCheckboxY = displayY + usernameHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float colorCheckboxX = headMenuX + headMenuWidth - checkboxSize - Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float colorTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float colorTextX = headMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float colorTextY = colorCheckboxY + (checkboxSize - colorTextSize) / 2.0F;
               K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, "Цвет сообщения", colorTextX, colorTextY, colorTextSize, isPremium ? new Color(255, 255, 255, (int)(255.0F * alpha)) : new Color(128, 128, 144, (int)(255.0F * alpha)));
               K2b5e2c5f2b9891b450.RECT.draw(matrices, colorCheckboxX, colorCheckboxY, checkboxSize, checkboxSize, Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), new Color(42, 42, 54, (int)(200.0F * alpha)));
               if (this.useCustomMessageColor) {
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, colorCheckboxX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F), colorCheckboxY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F), checkboxSize - Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), checkboxSize - Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               }

               float prefixButtonY = colorCheckboxY + checkboxSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float prefixButtonX = headMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float prefixButtonWidth = headMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(16.0F);
               float prefixButtonHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
               float prefixButtonRadius = Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
               boolean hoverPrefixButton = (float)mouseX >= prefixButtonX && (float)mouseX <= prefixButtonX + prefixButtonWidth && (float)mouseY >= prefixButtonY && (float)mouseY <= prefixButtonY + prefixButtonHeight;
               float prefixOutlineWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F);
               K2b5e2c5f2b9891b450.RECT.draw(matrices, prefixButtonX - prefixOutlineWidth, prefixButtonY - prefixOutlineWidth, prefixButtonWidth + prefixOutlineWidth * 2.0F, prefixButtonHeight + prefixOutlineWidth * 2.0F, prefixButtonRadius, new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               Color prefixButtonBg = hoverPrefixButton ? new Color(34, 34, 42, (int)(255.0F * alpha)) : new Color(26, 26, 34, (int)(255.0F * alpha));
               K2b5e2c5f2b9891b450.RECT.draw(matrices, prefixButtonX, prefixButtonY, prefixButtonWidth, prefixButtonHeight, prefixButtonRadius, prefixButtonBg);
               String prefixText = "Префиксы";
               float prefixTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
               float prefixTextWidth = K896065ea0a2bba4f47.SF_REGULAR.getWidth(prefixText, prefixTextSize);
               float prefixTextX = prefixButtonX + (prefixButtonWidth - prefixTextWidth) / 2.0F;
               float prefixTextY = prefixButtonY + (prefixButtonHeight - prefixTextSize) / 2.0F;
               K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, prefixText, prefixTextX, prefixTextY, prefixTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
            }

            if (this.showPrefixMenu) {
               float prefixMenuWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(380.0F);
               float prefixMenuHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(440.0F);
               float prefixMenuRadius = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float prefixMenuX = ((float)this.field_22789 - prefixMenuWidth) / 2.0F;
               float prefixMenuY = ((float)this.field_22790 - prefixMenuHeight) / 2.0F;
               float prefixOutlineWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F);
               K2b5e2c5f2b9891b450.RECT.draw(matrices, prefixMenuX - prefixOutlineWidth, prefixMenuY - prefixOutlineWidth, prefixMenuWidth + prefixOutlineWidth * 2.0F, prefixMenuHeight + prefixOutlineWidth * 2.0F, prefixMenuRadius, new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               K2b5e2c5f2b9891b450.RECT.draw(matrices, prefixMenuX, prefixMenuY, prefixMenuWidth, prefixMenuHeight, prefixMenuRadius, new Color(26, 26, 34, (int)(255.0F * alpha)));
               float titleSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
               String titleText = "Префиксы";
               float titleWidth = K896065ea0a2bba4f47.SF_REGULAR.getWidth(titleText, titleSize);
               float titleX = prefixMenuX + (prefixMenuWidth - titleWidth) / 2.0F;
               float titleY = prefixMenuY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, titleText, titleX, titleY, titleSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               float lineY = titleY + titleSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float lineX = prefixMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float lineWidth = prefixMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
               K2b5e2c5f2b9891b450.RECT.draw(matrices, lineX, lineY, lineWidth, Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), 0.0F, new Color(37, 37, 46, (int)(255.0F * alpha)));
               String currentRole = this.getRoleDisplayName();
               boolean isPremium = currentRole.equals("Premium") || currentRole.equals("Developer") || currentRole.equals("Admin") || currentRole.equals("Owner");
               int totalPrefixes = isPremium ? 25 : 10;
               float prefixItemHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(30.0F);
               float prefixItemPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               float prefixStartY = lineY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               int columns = 3;
               float horizontalPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(15.0F);
               float prefixItemWidth = (prefixMenuWidth - horizontalPadding * 2.0F - prefixItemPadding * 2.0F) / 3.0F;

               for(int i = 0; i < 30; ++i) {
                  int row = i / columns;
                  int col = i % columns;
                  float itemX = prefixMenuX + horizontalPadding + (float)col * (prefixItemWidth + prefixItemPadding);
                  float itemY = prefixStartY + (float)row * (prefixItemHeight + prefixItemPadding);
                  if (itemY + prefixItemHeight > prefixMenuY + prefixMenuHeight - Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F)) {
                     break;
                  }

                  boolean hoverItem = (float)mouseX >= itemX && (float)mouseX <= itemX + prefixItemWidth && (float)mouseY >= itemY && (float)mouseY <= itemY + prefixItemHeight;
                  boolean selected = this.selectedPrefixIndex == i;
                  boolean isPremiumPrefix = i >= 10;
                  boolean isLocked = isPremiumPrefix && !isPremium;
                  Color itemBg;
                  if (selected) {
                     itemBg = new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(200.0F * alpha));
                  } else if (hoverItem) {
                     itemBg = new Color(37, 37, 46, (int)(255.0F * alpha));
                  } else {
                     itemBg = new Color(30, 30, 38, (int)(255.0F * alpha));
                  }

                  K2b5e2c5f2b9891b450.RECT.draw(matrices, itemX, itemY, prefixItemWidth, prefixItemHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), itemBg);
                  if (isLocked) {
                     float outlineWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
                     K2b5e2c5f2b9891b450.RECT.draw(matrices, itemX - outlineWidth, itemY - outlineWidth, prefixItemWidth + outlineWidth * 2.0F, prefixItemHeight + outlineWidth * 2.0F, Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), new Color(255, 50, 50, (int)(200.0F * alpha)));
                     K2b5e2c5f2b9891b450.RECT.draw(matrices, itemX, itemY, prefixItemWidth, prefixItemHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), itemBg);
                  }

                  String prefixText;
                  if (i < 10) {
                     prefixText = this.basicPrefixes[i];
                  } else {
                     prefixText = this.premiumPrefixes[i - 10];
                  }

                  String cleanText = prefixText;
                  Color baseColor = new Color(255, 255, 255, (int)(255.0F * alpha));
                  if (prefixText.startsWith("§x")) {
                     cleanText = prefixText.replaceAll("§.", "");

                     try {
                        char var695 = prefixText.charAt(3);
                        String hexR = "" + var695 + prefixText.charAt(5);
                        var695 = prefixText.charAt(7);
                        String hexG = "" + var695 + prefixText.charAt(9);
                        var695 = prefixText.charAt(11);
                        String hexB = "" + var695 + prefixText.charAt(13);
                        int r = Integer.parseInt(hexR, 16);
                        int g = Integer.parseInt(hexG, 16);
                        int b = Integer.parseInt(hexB, 16);
                        if (isLocked) {
                           baseColor = new Color(128, 128, 144, (int)(255.0F * alpha));
                        } else {
                           baseColor = new Color(r, g, b, (int)(255.0F * alpha));
                        }
                     } catch (Exception var176) {
                     }
                  }

                  float prefixTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                  float prefixTextWidth = K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(cleanText, prefixTextSize);
                  float prefixTextX = itemX + (prefixItemWidth - prefixTextWidth) / 2.0F;
                  float prefixTextY = itemY + (prefixItemHeight - prefixTextSize) / 2.0F;
                  if (!isLocked) {
                     Color darkColor = new Color((int)((float)baseColor.getRed() * 0.7F), (int)((float)baseColor.getGreen() * 0.7F), (int)((float)baseColor.getBlue() * 0.7F), (int)(255.0F * alpha));
                     float time = (float)(System.currentTimeMillis() % 3000L) / 3000.0F;
                     float currentX = prefixTextX;

                     for(int charIdx = 0; charIdx < cleanText.length(); ++charIdx) {
                        String character = String.valueOf(cleanText.charAt(charIdx));
                        float charWidth = K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(character, prefixTextSize);
                        float charProgress = (float)charIdx / (float)Math.max(1, cleanText.length() - 1);
                        float animatedProgress = (charProgress + time) % 1.0F;
                        float gradientFactor = (float)((Math.sin((double)animatedProgress * Math.PI * (double)2.0F) + (double)1.0F) / (double)2.0F);
                        int r = (int)((float)baseColor.getRed() * gradientFactor + (float)darkColor.getRed() * (1.0F - gradientFactor));
                        int g = (int)((float)baseColor.getGreen() * gradientFactor + (float)darkColor.getGreen() * (1.0F - gradientFactor));
                        int b = (int)((float)baseColor.getBlue() * gradientFactor + (float)darkColor.getBlue() * (1.0F - gradientFactor));
                        Color charColor = new Color(r, g, b, (int)(255.0F * alpha));
                        K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, character, currentX, prefixTextY, prefixTextSize, charColor);
                        currentX += charWidth;
                     }
                  } else {
                     K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, cleanText, prefixTextX, prefixTextY, prefixTextSize, baseColor);
                  }
               }

               float closeButtonWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(80.0F);
               float closeButtonHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
               float closeButtonX = prefixMenuX + (prefixMenuWidth - closeButtonWidth) / 2.0F;
               float closeButtonY = prefixMenuY + prefixMenuHeight - closeButtonHeight - Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               boolean hoverClose = (float)mouseX >= closeButtonX && (float)mouseX <= closeButtonX + closeButtonWidth && (float)mouseY >= closeButtonY && (float)mouseY <= closeButtonY + closeButtonHeight;
               Color closeBg = hoverClose ? new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)) : new Color(30, 30, 38, (int)(255.0F * alpha));
               K2b5e2c5f2b9891b450.RECT.draw(matrices, closeButtonX, closeButtonY, closeButtonWidth, closeButtonHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), closeBg);
               String closeText = "Закрыть";
               float closeTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
               float closeTextWidth = K896065ea0a2bba4f47.SF_REGULAR.getWidth(closeText, closeTextSize);
               float closeTextX = closeButtonX + (closeButtonWidth - closeTextWidth) / 2.0F;
               float closeTextY = closeButtonY + (closeButtonHeight - closeTextSize) / 2.0F;
               K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, closeText, closeTextX, closeTextY, closeTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
            }

            if (this.showMessageContextMenu && this.selectedMessageAuthor != null) {
               float contextMenuWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(140.0F);
               float contextMenuHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(60.0F);
               float menuRadius = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, this.messageContextMenuX, this.messageContextMenuY, contextMenuWidth, contextMenuHeight, menuRadius, new Color(21, 21, 28, (int)(230.0F * alpha)));
               K2b5e2c5f2b9891b450.RECT.draw(matrices, this.messageContextMenuX - Ke4708cf52c3b89f5ae.getInstance().scaledUi(0.5F), this.messageContextMenuY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(0.5F), contextMenuWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), contextMenuHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), menuRadius + Ke4708cf52c3b89f5ae.getInstance().scaledUi(0.5F), new Color(42, 42, 54, (int)(200.0F * alpha)));
               float itemHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(30.0F);
               float itemPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(0.0F);
               float menuTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
               float itemY = this.messageContextMenuY;
               boolean hoverDM = (float)mouseX >= this.messageContextMenuX && (float)mouseX <= this.messageContextMenuX + contextMenuWidth && (float)mouseY >= itemY && (float)mouseY <= itemY + itemHeight;
               boolean hoverIgnore = (float)mouseX >= this.messageContextMenuX && (float)mouseX <= this.messageContextMenuX + contextMenuWidth && (float)mouseY >= itemY + itemHeight + itemPadding && (float)mouseY <= itemY + (itemHeight + itemPadding) * 2.0F;
               if (hoverDM) {
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, this.messageContextMenuX, itemY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), contextMenuWidth, itemHeight - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(50.0F * alpha)));
               }

               float dmTextX = this.messageContextMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float dmTextY = itemY + (itemHeight - menuTextSize) / 2.0F;
               K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, "Написать ЛС", dmTextX, dmTextY, menuTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               itemY += itemHeight + itemPadding;
               boolean isIgnored = this.ignoredPlayers.contains(this.selectedMessageAuthor);
               String ignoreText = isIgnored ? "Разигнорировать" : "Игнорировать";
               if (hoverIgnore) {
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, this.messageContextMenuX, itemY, contextMenuWidth, itemHeight - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(50.0F * alpha)));
               }

               float ignoreTextX = this.messageContextMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float ignoreTextY = itemY + (itemHeight - menuTextSize) / 2.0F;
               K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, ignoreText, ignoreTextX, ignoreTextY, menuTextSize, isIgnored ? new Color(0, 255, 0, (int)(255.0F * alpha)) : new Color(255, 100, 100, (int)(255.0F * alpha)));
            }

            this.themeEditor.render(context, mouseX, mouseY, delta);
            super.method_25394(context, mouseX, mouseY, delta);
            if (this.showDButtonMenu) {
               float dMenuWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(180.0F);
               float dMenuHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(186.0F);
               float menuRadius = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               K2b5e2c5f2b9891b450.RECT.draw(matrices, this.dMenuX - Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), this.dMenuY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), dMenuWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), dMenuHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), menuRadius, new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               K2b5e2c5f2b9891b450.RECT.draw(matrices, this.dMenuX, this.dMenuY, dMenuWidth, dMenuHeight, menuRadius, new Color(18, 18, 24, (int)(255.0F * alpha)));
               float colorCircleSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
               float circlePadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               Color purpleColor = new Color(150, 100, 255);
               Color blueColor = new Color(120, 160, 220);
               Color peachColor = new Color(255, 180, 140);
               new Color(255, 215, 0);
               float startX = this.dMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float circleY = this.dMenuY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               boolean hoverPurple = (float)mouseX >= startX && (float)mouseX <= startX + colorCircleSize && (float)mouseY >= circleY && (float)mouseY <= circleY + colorCircleSize;
               if (hoverPurple) {
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, startX - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), circleY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), colorCircleSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), colorCircleSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), (colorCircleSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F)) / 2.0F, new Color(255, 255, 255, (int)(50.0F * alpha)));
               }

               K2b5e2c5f2b9891b450.RECT.draw(matrices, startX, circleY, colorCircleSize, colorCircleSize, colorCircleSize / 2.0F, new Color(purpleColor.getRed(), purpleColor.getGreen(), purpleColor.getBlue(), (int)(255.0F * alpha)));
               if (this.selectedThemeColor.equals(purpleColor)) {
                  float checkX = startX + colorCircleSize / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.5F);
                  float checkY = circleY + colorCircleSize / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                  K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, "✓", checkX, checkY, Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F), new Color(255, 255, 255, (int)(255.0F * alpha)));
               }

               float circle2X = startX + colorCircleSize + circlePadding;
               boolean hoverBlue = (float)mouseX >= circle2X && (float)mouseX <= circle2X + colorCircleSize && (float)mouseY >= circleY && (float)mouseY <= circleY + colorCircleSize;
               if (hoverBlue) {
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, circle2X - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), circleY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), colorCircleSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), colorCircleSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), (colorCircleSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F)) / 2.0F, new Color(255, 255, 255, (int)(50.0F * alpha)));
               }

               K2b5e2c5f2b9891b450.RECT.draw(matrices, circle2X, circleY, colorCircleSize, colorCircleSize, colorCircleSize / 2.0F, new Color(blueColor.getRed(), blueColor.getGreen(), blueColor.getBlue(), (int)(255.0F * alpha)));
               if (this.selectedThemeColor.equals(blueColor)) {
                  float checkX = circle2X + colorCircleSize / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.5F);
                  float checkY = circleY + colorCircleSize / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                  K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, "✓", checkX, checkY, Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F), new Color(255, 255, 255, (int)(255.0F * alpha)));
               }

               float circle3X = circle2X + colorCircleSize + circlePadding;
               boolean hoverPeach = (float)mouseX >= circle3X && (float)mouseX <= circle3X + colorCircleSize && (float)mouseY >= circleY && (float)mouseY <= circleY + colorCircleSize;
               if (hoverPeach) {
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, circle3X - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), circleY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), colorCircleSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), colorCircleSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), (colorCircleSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F)) / 2.0F, new Color(255, 255, 255, (int)(50.0F * alpha)));
               }

               K2b5e2c5f2b9891b450.RECT.draw(matrices, circle3X, circleY, colorCircleSize, colorCircleSize, colorCircleSize / 2.0F, new Color(peachColor.getRed(), peachColor.getGreen(), peachColor.getBlue(), (int)(255.0F * alpha)));
               if (this.selectedThemeColor.equals(peachColor)) {
                  float checkX = circle3X + colorCircleSize / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.5F);
                  float checkY = circleY + colorCircleSize / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                  K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, "✓", checkX, checkY, Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F), new Color(255, 255, 255, (int)(255.0F * alpha)));
               }

               float circle4X = circle3X + colorCircleSize + circlePadding;
               boolean hoverCustom = (float)mouseX >= circle4X && (float)mouseX <= circle4X + colorCircleSize && (float)mouseY >= circleY && (float)mouseY <= circleY + colorCircleSize;
               if (hoverCustom) {
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, circle4X - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), circleY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), colorCircleSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), colorCircleSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), (colorCircleSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F)) / 2.0F, new Color(255, 255, 255, (int)(50.0F * alpha)));
               }

               K2b5e2c5f2b9891b450.RECT.draw(matrices, circle4X, circleY, colorCircleSize, colorCircleSize, colorCircleSize / 2.0F, new Color(this.customThemeColor.getRed(), this.customThemeColor.getGreen(), this.customThemeColor.getBlue(), (int)(255.0F * alpha)));
               if (this.selectedThemeColor.equals(this.customThemeColor)) {
                  float checkX = circle4X + colorCircleSize / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.5F);
                  float checkY = circleY + colorCircleSize / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                  K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, "✓", checkX, checkY, Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F), new Color(255, 255, 255, (int)(255.0F * alpha)));
               }

               if (!this.selectedThemeColor.equals(this.customThemeColor)) {
                  float paletteIconSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(7.0F);
                  float paletteX = circle4X + colorCircleSize / 2.0F - paletteIconSize / 2.0F;
                  float paletteY = circleY + colorCircleSize / 2.0F - paletteIconSize / 2.0F;
                  K896065ea0a2bba4f47.ICONS_DESHUX.drawText(matrices, "p", paletteX, paletteY, paletteIconSize, new Color(255, 255, 255, (int)(220.0F * alpha)));
               }

               float lineY = circleY + colorCircleSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               float lineX = this.dMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float lineWidth = dMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
               K2b5e2c5f2b9891b450.RECT.draw(matrices, lineX, lineY, lineWidth, Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), 0.0F, new Color(37, 37, 46, (int)(255.0F * alpha)));
               float hideY = lineY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float hideCheckboxSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
               float hideHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(22.0F);
               float hideCheckboxX = this.dMenuX + dMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F) - hideCheckboxSize;
               boolean hoverHide = (float)mouseX >= this.dMenuX && (float)mouseX <= this.dMenuX + dMenuWidth && (float)mouseY >= hideY && (float)mouseY <= hideY + hideHeight;
               if (hoverHide) {
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, this.dMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F), hideY, dMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F), hideHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(50.0F * alpha)));
               }

               float hideIconSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float hideIconX = this.dMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float hideIconY = hideY + (hideHeight - hideIconSize) / 2.0F;
               K896065ea0a2bba4f47.ICONS_NURIK.drawText(matrices, "Y", hideIconX, hideIconY, hideIconSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               String hideText = "Скрыть друзей";
               float hideTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
               float hideTextX = hideIconX + hideIconSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
               float hideTextY = hideY + (hideHeight - K896065ea0a2bba4f47.SF_REGULAR.getHeight(hideTextSize)) / 2.0F;
               K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, hideText, hideTextX, hideTextY, hideTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               float checkboxY = hideY + (hideHeight - hideCheckboxSize) / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F);
               K2b5e2c5f2b9891b450.RECT.draw(matrices, hideCheckboxX, checkboxY, hideCheckboxSize, hideCheckboxSize, Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), new Color(42, 42, 54, (int)(200.0F * alpha)));
               if (this.hideFriendPanel) {
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, hideCheckboxX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F), checkboxY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F), hideCheckboxSize - Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), hideCheckboxSize - Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               }

               float checkboxSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
               float checkboxY1 = hideY + hideHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               float checkboxY2 = checkboxY1 + checkboxSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               float checkboxY3 = checkboxY2 + checkboxSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               float checkboxX = this.dMenuX + dMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F) - checkboxSize;
               float icon1Size = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float icon1X = this.dMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float icon1Y = checkboxY1 + (checkboxSize - icon1Size) / 2.0F;
               K896065ea0a2bba4f47.ICONS_DESHUX.drawText(matrices, "q", icon1X, icon1Y, icon1Size, new Color(255, 255, 255, (int)(255.0F * alpha)));
               String text1 = "Звук отправки";
               float text1Size = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
               float text1X = icon1X + icon1Size + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
               float text1Y = checkboxY1 + (checkboxSize - K896065ea0a2bba4f47.SF_REGULAR.getHeight(text1Size)) / 2.0F;
               K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, text1, text1X, text1Y, text1Size, new Color(255, 255, 255, (int)(255.0F * alpha)));
               K2b5e2c5f2b9891b450.RECT.draw(matrices, checkboxX, checkboxY1, checkboxSize, checkboxSize, Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), new Color(42, 42, 54, (int)(200.0F * alpha)));
               if (this.enableSendSound) {
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, checkboxX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F), checkboxY1 + Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F), checkboxSize - Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), checkboxSize - Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               }

               float icon2Size = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float icon2X = this.dMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float icon2Y = checkboxY2 + (checkboxSize - icon2Size) / 2.0F;
               K896065ea0a2bba4f47.ICONS_DESHUX.drawText(matrices, "n", icon2X, icon2Y, icon2Size, new Color(255, 255, 255, (int)(255.0F * alpha)));
               String text2 = "Звук получения";
               float text2Size = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
               float text2X = icon2X + icon2Size + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
               float text2Y = checkboxY2 + (checkboxSize - K896065ea0a2bba4f47.SF_REGULAR.getHeight(text2Size)) / 2.0F;
               K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, text2, text2X, text2Y, text2Size, new Color(255, 255, 255, (int)(255.0F * alpha)));
               K2b5e2c5f2b9891b450.RECT.draw(matrices, checkboxX, checkboxY2, checkboxSize, checkboxSize, Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), new Color(42, 42, 54, (int)(200.0F * alpha)));
               if (this.enableReceiveSound) {
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, checkboxX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F), checkboxY2 + Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F), checkboxSize - Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), checkboxSize - Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               }

               float icon3Size = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float icon3X = this.dMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float icon3Y = checkboxY3 + (checkboxSize - icon3Size) / 2.0F;
               K896065ea0a2bba4f47.ICON_DANQ.drawText(matrices, "I", icon3X, icon3Y, icon3Size, new Color(255, 255, 255, (int)(255.0F * alpha)));
               String text3 = "Уведомление Power";
               float text3Size = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
               float text3X = icon3X + icon3Size + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
               float text3Y = checkboxY3 + (checkboxSize - K896065ea0a2bba4f47.SF_REGULAR.getHeight(text3Size)) / 2.0F;
               K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, text3, text3X, text3Y, text3Size, new Color(255, 255, 255, (int)(255.0F * alpha)));
               K2b5e2c5f2b9891b450.RECT.draw(matrices, checkboxX, checkboxY3, checkboxSize, checkboxSize, Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), new Color(42, 42, 54, (int)(200.0F * alpha)));
               if (this.enableWindowsNotifications) {
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, checkboxX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F), checkboxY3 + Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F), checkboxSize - Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), checkboxSize - Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               }

               float sliderY = checkboxY3 + checkboxSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(18.0F);
               float sliderWidth = dMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
               float sliderHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
               float sliderX = this.dMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float volumeIconSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float volumeIconX = this.dMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float volumeIconY = sliderY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(13.5F);
               K896065ea0a2bba4f47.ICONS_DESHUX.drawText(matrices, "m", volumeIconX, volumeIconY, volumeIconSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               int volumePercent = (int)(this.ircSoundVolume * 100.0F);
               String volumeText = "Громкость: " + volumePercent + "%";
               float volumeTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
               float volumeTextX = volumeIconX + volumeIconSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
               float volumeTextY = volumeIconY + (volumeIconSize - K896065ea0a2bba4f47.SF_REGULAR.getHeight(volumeTextSize)) / 2.0F;
               K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, volumeText, volumeTextX, volumeTextY, volumeTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               K2b5e2c5f2b9891b450.RECT.draw(matrices, sliderX, sliderY, sliderWidth, sliderHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), new Color(42, 42, 54, (int)(200.0F * alpha)));
               float filledWidth = sliderWidth * this.ircSoundVolume;
               if (filledWidth > 0.0F) {
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, sliderX, sliderY, filledWidth, sliderHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               }

               float thumbSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float thumbX = sliderX + sliderWidth * this.ircSoundVolume - thumbSize / 2.0F;
               float thumbY = sliderY - thumbSize / 2.0F + sliderHeight / 2.0F;
               K2b5e2c5f2b9891b450.RECT.draw(matrices, thumbX, thumbY, thumbSize, thumbSize, thumbSize / 2.0F, new Color(255, 255, 255, (int)(255.0F * alpha)));
               float timeCheckboxY = sliderY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
               float timeCheckboxX = this.dMenuX + dMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F) - checkboxSize;
               float timeIconSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float timeIconX = this.dMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float timeIconY = timeCheckboxY + (checkboxSize - timeIconSize) / 2.0F;
               K896065ea0a2bba4f47.NUR.drawText(matrices, "V", timeIconX, timeIconY, timeIconSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               String timeText = "Время сообщения";
               float timeTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
               float timeTextX = timeIconX + timeIconSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
               float timeTextY = timeCheckboxY + (checkboxSize - K896065ea0a2bba4f47.SF_REGULAR.getHeight(timeTextSize)) / 2.0F;
               K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, timeText, timeTextX, timeTextY, timeTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               K2b5e2c5f2b9891b450.RECT.draw(matrices, timeCheckboxX, timeCheckboxY, checkboxSize, checkboxSize, Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), new Color(42, 42, 54, (int)(200.0F * alpha)));
               if (this.showMessageTime) {
                  K2b5e2c5f2b9891b450.RECT.draw(matrices, timeCheckboxX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F), timeCheckboxY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F), checkboxSize - Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), checkboxSize - Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               }

               float line2Y = timeCheckboxY + checkboxSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               float line2X = this.dMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               float line2Width = dMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
               K2b5e2c5f2b9891b450.RECT.draw(matrices, line2X, line2Y, line2Width, Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), 0.0F, new Color(37, 37, 46, (int)(255.0F * alpha)));
               float resetButtonY = line2Y + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               float resetButtonHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(24.0F);
               float resetButtonWidth = dMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
               float resetButtonX = this.dMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               boolean hoverReset = (float)mouseX >= resetButtonX && (float)mouseX <= resetButtonX + resetButtonWidth && (float)mouseY >= resetButtonY && (float)mouseY <= resetButtonY + resetButtonHeight;
               K2b5e2c5f2b9891b450.RECT.draw(matrices, resetButtonX, resetButtonY, resetButtonWidth, resetButtonHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), hoverReset ? new Color(53, 53, 64, (int)(200.0F * alpha)) : new Color(42, 42, 54, (int)(200.0F * alpha)));
               float gIconSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(14.0F);
               float gIconX = resetButtonX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F);
               float gIconY = resetButtonY + (resetButtonHeight - gIconSize) / 2.0F;
               K896065ea0a2bba4f47.ICONS.drawText(matrices, "G", gIconX, gIconY, gIconSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               String resetText = "Сбросить настройки";
               float resetTextX = gIconX + gIconSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F);
               float resetTextY = resetButtonY + (resetButtonHeight - K896065ea0a2bba4f47.SF_REGULAR.getHeight(hideTextSize)) / 2.0F;
               K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, resetText, resetTextX, resetTextY, hideTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
            }

            if (this.showColorPicker) {
               float pickerWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(150.0F);
               float pickerHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(120.0F);
               float pickerRadius = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               if (this.draggingCustomHue) {
                  float rel = Math.max(0.0F, Math.min(1.0F, ((float)mouseX - this.colorPickerX) / pickerWidth));
                  this.customHue = rel;
                  this.updateCustomColorFromHSV();
                  this.customThemeColor = new Color(this.customColorR, this.customColorG, this.customColorB, this.customColorA);
                  this.selectedThemeColor = this.customThemeColor;
               }

               if (this.draggingCustomSatBright) {
                  float squareHeight = pickerWidth * 0.5F;
                  float sat = Math.max(0.0F, Math.min(1.0F, ((float)mouseX - this.colorPickerX) / pickerWidth));
                  float bri = 1.0F - Math.max(0.0F, Math.min(1.0F, ((float)mouseY - (this.colorPickerY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F))) / squareHeight));
                  this.customSaturation = sat;
                  this.customBrightness = bri;
                  this.updateCustomColorFromHSV();
                  this.customThemeColor = new Color(this.customColorR, this.customColorG, this.customColorB, this.customColorA);
                  this.selectedThemeColor = this.customThemeColor;
               }

               if (this.draggingCustomAlpha) {
                  float rel = Math.max(0.0F, Math.min(1.0F, ((float)mouseX - this.colorPickerX) / pickerWidth));
                  this.customColorA = (int)(rel * 255.0F);
                  this.updateCustomColorFromHSV();
                  this.customThemeColor = new Color(this.customColorR, this.customColorG, this.customColorB, this.customColorA);
                  this.selectedThemeColor = this.customThemeColor;
               }

               K2b5e2c5f2b9891b450.RECT.draw(matrices, this.colorPickerX - Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), this.colorPickerY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), pickerWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), pickerHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), pickerRadius, new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
               K2b5e2c5f2b9891b450.RECT.draw(matrices, this.colorPickerX, this.colorPickerY, pickerWidth, pickerHeight, pickerRadius, new Color(18, 18, 24, (int)(255.0F * alpha)));
               String pickerTitle = "Цвет";
               float titleSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
               float titleX = this.colorPickerX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float titleY = this.colorPickerY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F);
               K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, pickerTitle, titleX, titleY, titleSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               float squareHeight = pickerWidth * 0.5F;
               float squareX = this.colorPickerX;
               float squareY = this.colorPickerY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
               Color hueColor = new Color(Color.HSBtoRGB(this.customHue, 1.0F, 1.0F));
               Color topLeft = new Color(255, 255, 255, (int)(255.0F * alpha));
               Color topRight = new Color(hueColor.getRed(), hueColor.getGreen(), hueColor.getBlue(), (int)(255.0F * alpha));
               Color bottom = new Color(0, 0, 0, (int)(255.0F * alpha));
               K2b5e2c5f2b9891b450.GRADIENT_RECT.draw(matrices, squareX, squareY, pickerWidth, squareHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F), topLeft, topRight, bottom, bottom);
               float satX = squareX + this.customSaturation * pickerWidth;
               float briY = squareY + (1.0F - this.customBrightness) * squareHeight;
               float cursorSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
               K2b5e2c5f2b9891b450.RECT.draw(matrices, satX - cursorSize, briY - cursorSize, cursorSize * 2.0F, cursorSize * 2.0F, cursorSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
               float hueBarHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float hueBarY = squareY + squareHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               float segmentWidth = pickerWidth / 6.0F;
               Color[] rainbowColors = new Color[]{new Color(255, 0, 0), new Color(255, 255, 0), new Color(0, 255, 0), new Color(0, 255, 255), new Color(0, 0, 255), new Color(255, 0, 255)};

               for(int i = 0; i < 6; ++i) {
                  float segX = this.colorPickerX + segmentWidth * (float)i;
                  Color c1 = rainbowColors[i];
                  Color c2 = rainbowColors[(i + 1) % 6];
                  K2b5e2c5f2b9891b450.GRADIENT_RECT.draw(matrices, segX, hueBarY, segmentWidth + 1.0F, hueBarHeight, 0.0F, new Color(c1.getRed(), c1.getGreen(), c1.getBlue(), (int)(255.0F * alpha)), new Color(c2.getRed(), c2.getGreen(), c2.getBlue(), (int)(255.0F * alpha)), new Color(c1.getRed(), c1.getGreen(), c1.getBlue(), (int)(255.0F * alpha)), new Color(c2.getRed(), c2.getGreen(), c2.getBlue(), (int)(255.0F * alpha)));
               }

               float hueX = this.colorPickerX + this.customHue * pickerWidth;
               float hueLineWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               K2b5e2c5f2b9891b450.RECT.draw(matrices, hueX - hueLineWidth / 2.0F, hueBarY, hueLineWidth, hueBarHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), new Color(255, 255, 255, (int)(255.0F * alpha)));
               float alphaBarHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float alphaBarY = hueBarY + hueBarHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
               Color alphaLeft = new Color(this.customColorR, this.customColorG, this.customColorB, 0);
               Color alphaRight = new Color(this.customColorR, this.customColorG, this.customColorB, (int)(255.0F * alpha));
               K2b5e2c5f2b9891b450.GRADIENT_RECT.draw(matrices, this.colorPickerX, alphaBarY, pickerWidth, alphaBarHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F), alphaLeft, alphaRight, alphaLeft, alphaRight);
               float alphaRel = (float)this.customColorA / 255.0F;
               float alphaX = this.colorPickerX + alphaRel * pickerWidth;
               K2b5e2c5f2b9891b450.RECT.draw(matrices, alphaX - hueLineWidth / 2.0F, alphaBarY, hueLineWidth, alphaBarHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), new Color(255, 255, 255, (int)(255.0F * alpha)));
            }

            if (this.showRulesWindow) {
               this.renderRulesWindow(matrices, windowWidth, windowHeight, alpha, mouseX, mouseY);
            }

            if (this.showFriendRequest && this.friendRequestFromUsername != null) {
               this.renderFriendRequestWindow(matrices, windowWidth, windowHeight, alpha, mouseX, mouseY);
            }

         }
      }
   }

   private void updateCustomColorFromHSV() {
      int rgb = Color.HSBtoRGB(this.customHue, this.customSaturation, this.customBrightness);
      Color c = new Color(rgb);
      this.customColorR = c.getRed();
      this.customColorG = c.getGreen();
      this.customColorB = c.getBlue();
      this.customThemeColor = new Color(this.customColorR, this.customColorG, this.customColorB, this.customColorA);
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      float windowWidth = (float)mc.method_22683().method_4486();
      float windowHeight = (float)mc.method_22683().method_4502();
      float guiWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(600.0F);
      float guiHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(400.0F);
      float guiX = windowWidth / 2.0F - guiWidth / 2.0F + this.guiOffsetX;
      float guiY = windowHeight / 2.0F - guiHeight / 2.0F + this.guiOffsetY;
      float sidebarWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(50.0F);
      float menuTopPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
      float menuX = guiX + sidebarWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
      float menuY = guiY + menuTopPadding;
      float menuWidth = guiWidth - sidebarWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
      float menuHeight = guiHeight - menuTopPadding;
      float lineOffset = Ke4708cf52c3b89f5ae.getInstance().scaledUi(120.0F);
      float lineTopPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(45.0F);
      float verticalLineX = menuX + menuWidth - lineOffset;
      float verticalLineY = menuY + lineTopPadding;
      float verticalLineHeight = menuHeight - lineTopPadding;
      float rightPanelLineY = verticalLineY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
      float rightPanelLineX = verticalLineX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F) + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
      float rightPanelLineWidth = lineOffset - Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
      if (this.showRulesWindow && button == 0) {
         float rulesWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(500.0F);
         float rulesHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(350.0F);
         float rulesX = (windowWidth - rulesWidth) / 2.0F;
         float rulesY = (windowHeight - rulesHeight) / 2.0F;
         float buttonWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(200.0F);
         float buttonHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(35.0F);
         float buttonX = rulesX + (rulesWidth - buttonWidth) / 2.0F;
         float buttonY = rulesY + rulesHeight - buttonHeight - Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
         if (mouseX >= (double)buttonX && mouseX <= (double)(buttonX + buttonWidth) && mouseY >= (double)buttonY && mouseY <= (double)(buttonY + buttonHeight)) {
            this.rulesAccepted = true;
            this.showRulesWindow = false;
            this.saveRulesAcceptance();
            System.out.println("[IRC] Правила приняты игроком");
            return true;
         } else {
            return true;
         }
      } else if (this.showFriendRequest && button == 0) {
         float windowW = Ke4708cf52c3b89f5ae.getInstance().scaledUi(350.0F);
         float windowH = Ke4708cf52c3b89f5ae.getInstance().scaledUi(150.0F);
         float windowX = (windowWidth - windowW) / 2.0F;
         float windowY = (windowHeight - windowH) / 2.0F;
         float buttonWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(120.0F);
         float buttonHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(32.0F);
         float buttonGap = Ke4708cf52c3b89f5ae.getInstance().scaledUi(15.0F);
         float buttonsY = windowY + windowH - buttonHeight - Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
         float acceptButtonX = windowX + windowW / 2.0F - buttonWidth - buttonGap / 2.0F;
         float declineButtonX = windowX + windowW / 2.0F + buttonGap / 2.0F;
         if (mouseX >= (double)acceptButtonX && mouseX <= (double)(acceptButtonX + buttonWidth) && mouseY >= (double)buttonsY && mouseY <= (double)(buttonsY + buttonHeight)) {
            K3e5d28a93090bc4a75.getInstance().sendFriendRequestResponse(this.friendRequestFromUuid, true);
            this.showFriendRequest = false;
            this.friendRequestFromUuid = null;
            this.friendRequestFromUsername = null;
            System.out.println("[IRC] Запрос в друзья принят");
            return true;
         } else if (mouseX >= (double)declineButtonX && mouseX <= (double)(declineButtonX + buttonWidth) && mouseY >= (double)buttonsY && mouseY <= (double)(buttonsY + buttonHeight)) {
            K3e5d28a93090bc4a75.getInstance().sendFriendRequestResponse(this.friendRequestFromUuid, false);
            this.showFriendRequest = false;
            this.friendRequestFromUuid = null;
            this.friendRequestFromUsername = null;
            System.out.println("[IRC] Запрос в друзья отклонён");
            return true;
         } else {
            return true;
         }
      } else {
         if (button == 1 && !this.friendChatMode) {
            for(K7db34ec333fff41384 bounds : this.messageBounds) {
               if (bounds.contains((float)mouseX, (float)mouseY)) {
                  String myNickname = mc.field_1724 != null ? mc.field_1724.method_5477().getString() : "";
                  if (!bounds.author.equals(myNickname)) {
                     this.selectedMessageAuthor = bounds.author;
                     this.messageContextMenuX = (float)mouseX;
                     this.messageContextMenuY = (float)mouseY;
                     this.showMessageContextMenu = true;
                     return true;
                  }
               }
            }
         }

         if (this.showColorPicker) {
            float pickerWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(150.0F);
            float pickerHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(120.0F);
            float squareHeight = pickerWidth * 0.5F;
            float squareY = this.colorPickerY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
            float hueBarHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
            float hueBarY = squareY + squareHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
            float alphaBarHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
            float alphaBarY = hueBarY + hueBarHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
            float titleBarHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
            if (mouseX >= (double)this.colorPickerX && mouseX <= (double)(this.colorPickerX + pickerWidth) && mouseY >= (double)this.colorPickerY && mouseY <= (double)(this.colorPickerY + titleBarHeight)) {
               this.draggingColorPicker = true;
               this.colorPickerDragOffsetX = (float)(mouseX - (double)this.colorPickerX);
               this.colorPickerDragOffsetY = (float)(mouseY - (double)this.colorPickerY);
               return true;
            }

            if (mouseX >= (double)this.colorPickerX && mouseX <= (double)(this.colorPickerX + pickerWidth) && mouseY >= (double)squareY && mouseY <= (double)(squareY + squareHeight)) {
               this.draggingCustomSatBright = true;
               float sat = (float)Math.max((double)0.0F, Math.min((double)1.0F, (mouseX - (double)this.colorPickerX) / (double)pickerWidth));
               float bri = 1.0F - (float)Math.max((double)0.0F, Math.min((double)1.0F, (mouseY - (double)squareY) / (double)squareHeight));
               this.customSaturation = sat;
               this.customBrightness = bri;
               this.updateCustomColorFromHSV();
               this.customThemeColor = new Color(this.customColorR, this.customColorG, this.customColorB, this.customColorA);
               this.selectedThemeColor = this.customThemeColor;
               return true;
            }

            if (mouseX >= (double)this.colorPickerX && mouseX <= (double)(this.colorPickerX + pickerWidth) && mouseY >= (double)hueBarY && mouseY <= (double)(hueBarY + hueBarHeight)) {
               this.draggingCustomHue = true;
               float rel = (float)Math.max((double)0.0F, Math.min((double)1.0F, (mouseX - (double)this.colorPickerX) / (double)pickerWidth));
               this.customHue = rel;
               this.updateCustomColorFromHSV();
               this.customThemeColor = new Color(this.customColorR, this.customColorG, this.customColorB, this.customColorA);
               this.selectedThemeColor = this.customThemeColor;
               return true;
            }

            if (mouseX >= (double)this.colorPickerX && mouseX <= (double)(this.colorPickerX + pickerWidth) && mouseY >= (double)alphaBarY && mouseY <= (double)(alphaBarY + alphaBarHeight)) {
               this.draggingCustomAlpha = true;
               float rel = (float)Math.max((double)0.0F, Math.min((double)1.0F, (mouseX - (double)this.colorPickerX) / (double)pickerWidth));
               this.customColorA = (int)(rel * 255.0F);
               this.updateCustomColorFromHSV();
               this.customThemeColor = new Color(this.customColorR, this.customColorG, this.customColorB, this.customColorA);
               this.selectedThemeColor = this.customThemeColor;
               return true;
            }

            if (!(mouseX >= (double)this.colorPickerX) || !(mouseX <= (double)(this.colorPickerX + pickerWidth)) || !(mouseY >= (double)this.colorPickerY) || !(mouseY <= (double)(this.colorPickerY + pickerHeight))) {
               this.showColorPicker = false;
               System.out.println("ColorPicker закрыт");
            }
         }

         if (this.showPrefixMenu && button == 0) {
            float prefixMenuWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(380.0F);
            float prefixMenuHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(440.0F);
            float prefixMenuX = ((float)this.field_22789 - prefixMenuWidth) / 2.0F;
            float prefixMenuY = ((float)this.field_22790 - prefixMenuHeight) / 2.0F;
            if (mouseX >= (double)prefixMenuX && mouseX <= (double)(prefixMenuX + prefixMenuWidth) && mouseY >= (double)prefixMenuY && mouseY <= (double)(prefixMenuY + prefixMenuHeight)) {
               float closeButtonWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(80.0F);
               float closeButtonHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
               float closeButtonX = prefixMenuX + (prefixMenuWidth - closeButtonWidth) / 2.0F;
               float closeButtonY = prefixMenuY + prefixMenuHeight - closeButtonHeight - Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
               if (mouseX >= (double)closeButtonX && mouseX <= (double)(closeButtonX + closeButtonWidth) && mouseY >= (double)closeButtonY && mouseY <= (double)(closeButtonY + closeButtonHeight)) {
                  this.showPrefixMenu = false;
                  return true;
               } else {
                  String currentRole = this.getRoleDisplayName();
                  boolean isPremium = currentRole.equals("Premium") || currentRole.equals("Developer") || currentRole.equals("Admin") || currentRole.equals("Owner");
                  float titleSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
                  float lineY = prefixMenuY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F) + titleSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
                  float prefixStartY = lineY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                  float prefixItemHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(30.0F);
                  float prefixItemPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                  int columns = 3;
                  float horizontalPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(15.0F);
                  float prefixItemWidth = (prefixMenuWidth - horizontalPadding * 2.0F - prefixItemPadding * 2.0F) / 3.0F;

                  for(int i = 0; i < 30; ++i) {
                     int row = i / columns;
                     int col = i % columns;
                     float itemX = prefixMenuX + horizontalPadding + (float)col * (prefixItemWidth + prefixItemPadding);
                     float itemY = prefixStartY + (float)row * (prefixItemHeight + prefixItemPadding);
                     if (itemY + prefixItemHeight > prefixMenuY + prefixMenuHeight - Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F)) {
                        break;
                     }

                     if (mouseX >= (double)itemX && mouseX <= (double)(itemX + prefixItemWidth) && mouseY >= (double)itemY && mouseY <= (double)(itemY + prefixItemHeight)) {
                        boolean isPremiumPrefix = i >= 10;
                        if (isPremiumPrefix && !isPremium) {
                           this.sendPremiumRequiredMessage();
                        } else {
                           this.selectedPrefixIndex = i;
                           String var315 = i < 10 ? this.basicPrefixes[i] : this.premiumPrefixes[i - 10];
                           System.out.println("Выбран префикс: " + var315);
                           this.saveCustomTag();
                           this.showPrefixMenu = false;
                        }

                        return true;
                     }
                  }

                  return true;
               }
            } else {
               this.showPrefixMenu = false;
               return true;
            }
         } else if (this.showFriendContextMenu) {
            float contextMenuWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(180.0F);
            float contextMenuHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(90.0F);
            if (mouseX >= (double)this.contextMenuX && mouseX <= (double)(this.contextMenuX + contextMenuWidth) && mouseY >= (double)this.contextMenuY && mouseY <= (double)(this.contextMenuY + contextMenuHeight)) {
               if (button == 0) {
                  float itemHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(26.0F);
                  float itemPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
                  float itemY = this.contextMenuY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F);
                  if (mouseY >= (double)itemY && mouseY <= (double)(itemY + itemHeight)) {
                     if (favoriteFriends.contains(this.selectedFriend)) {
                        favoriteFriends.remove(this.selectedFriend);
                        System.out.println("Удалён из избранного: " + this.selectedFriend);
                     } else {
                        favoriteFriends.add(this.selectedFriend);
                        System.out.println("Добавлен в избранное: " + this.selectedFriend);
                     }

                     this.showFriendContextMenu = false;
                     return true;
                  }

                  if (mouseY >= (double)(itemY + itemHeight + itemPadding) && mouseY <= (double)(itemY + (itemHeight + itemPadding) * 2.0F)) {
                     mc.field_1774.method_1455(this.selectedFriend);
                     System.out.println("Скопирован ник: " + this.selectedFriend);
                     this.showFriendContextMenu = false;
                     return true;
                  }

                  if (mouseY >= (double)(itemY + (itemHeight + itemPadding) * 2.0F) && mouseY <= (double)(itemY + (itemHeight + itemPadding) * 3.0F)) {
                     this.friendChatMode = true;
                     this.currentPrivateChat = this.selectedFriend;
                     this.chatScrollOffset = 0.0F;
                     K3e5d28a93090bc4a75.getInstance().requestPrivateHistory(this.selectedFriend);
                     K8ee912418ef60c15b7.getInstance().markAsRead(this.selectedFriend);
                     System.out.println("[IRC] Открыт диалог с другом: " + this.selectedFriend);
                     this.showFriendContextMenu = false;
                     return true;
                  }
               }
            } else {
               this.showFriendContextMenu = false;
               this.selectedFriend = null;
            }

            return true;
         } else if (this.showMessageContextMenu && this.selectedMessageAuthor != null) {
            float contextMenuWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(140.0F);
            float contextMenuHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(60.0F);
            if (mouseX >= (double)this.messageContextMenuX && mouseX <= (double)(this.messageContextMenuX + contextMenuWidth) && mouseY >= (double)this.messageContextMenuY && mouseY <= (double)(this.messageContextMenuY + contextMenuHeight)) {
               if (button == 0) {
                  float itemHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(30.0F);
                  float itemY = this.messageContextMenuY;
                  if (mouseY >= (double)itemY && mouseY <= (double)(itemY + itemHeight)) {
                     this.friendChatMode = true;
                     this.currentPrivateChat = this.selectedMessageAuthor;
                     this.chatScrollOffset = 0.0F;
                     K8ee912418ef60c15b7.getInstance().createDialog(this.selectedMessageAuthor);
                     K3e5d28a93090bc4a75.getInstance().requestPrivateHistory(this.selectedMessageAuthor);
                     K8ee912418ef60c15b7.getInstance().markAsRead(this.selectedMessageAuthor);
                     System.out.println("[IRC] Переключение на личные сообщения с: " + this.selectedMessageAuthor);
                     this.showMessageContextMenu = false;
                     return true;
                  }

                  if (mouseY >= (double)(itemY + itemHeight) && mouseY <= (double)(itemY + itemHeight * 2.0F)) {
                     if (this.ignoredPlayers.contains(this.selectedMessageAuthor)) {
                        this.ignoredPlayers.remove(this.selectedMessageAuthor);
                        System.out.println("[IRC] Разигнорирован: " + this.selectedMessageAuthor);
                     } else {
                        this.ignoredPlayers.add(this.selectedMessageAuthor);
                        System.out.println("[IRC] Игнорирован: " + this.selectedMessageAuthor);
                     }

                     this.showMessageContextMenu = false;
                     return true;
                  }
               }
            } else {
               this.showMessageContextMenu = false;
               this.selectedMessageAuthor = null;
            }

            return true;
         } else {
            if (this.showPlayerHeadMenu) {
               windowWidth = (float)this.field_22789;
               windowHeight = (float)this.field_22790;
               guiWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(600.0F);
               guiHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(400.0F);
               guiX = windowWidth / 2.0F - guiWidth / 2.0F + this.guiOffsetX;
               guiY = windowHeight / 2.0F - guiHeight / 2.0F + this.guiOffsetY;
               float headMenuWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(100.0F);
               float headMenuHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(100.0F);
               sidebarWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(50.0F);
               float skinHeadSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(35.0F);
               float skinHeadX = guiX + (sidebarWidth - skinHeadSize) / 2.0F;
               float skinHeadY = guiY + guiHeight - skinHeadSize - Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
               float headMenuX = skinHeadX - headMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
               if (mouseX >= (double)headMenuX && mouseX <= (double)(headMenuX + headMenuWidth) && mouseY >= (double)skinHeadY && mouseY <= (double)(skinHeadY + headMenuHeight)) {
                  if (button == 0) {
                     String currentRole = this.getRoleDisplayName();
                     boolean isPremium = currentRole.equals("Premium") || currentRole.equals("Developer") || currentRole.equals("Admin") || currentRole.equals("Owner");
                     float titleY = skinHeadY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F);
                     float lineY = titleY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(18.0F);
                     float displayY = lineY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                     String displayUsername = this.customTag.isEmpty() ? "username" : this.customTag;
                     String fullDisplayUsername = "@" + displayUsername;
                     float usernameTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                     float usernameWidth = K896065ea0a2bba4f47.SF_REGULAR.getWidth(fullDisplayUsername, usernameTextSize) + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                     float usernameHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(16.0F);
                     float usernameX = headMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                     if (mouseX >= (double)usernameX && mouseX <= (double)(usernameX + usernameWidth) && mouseY >= (double)displayY && mouseY <= (double)(displayY + usernameHeight)) {
                        if (this.canChangeCustomTag()) {
                           this.customTagInputFocused = true;
                           this.editingCustomTag = this.customTag;
                           System.out.println("Редактирование custom tag");
                        } else {
                           String timeRemaining = this.getTimeUntilNextTagChange();
                           if (mc.field_1724 != null) {
                              mc.field_1724.method_7353(class_2561.method_43470("§c[IRC] §fСменить тег можно через: " + timeRemaining), false);
                           }

                           System.out.println("Нельзя менять тег. Осталось: " + timeRemaining);
                        }

                        return true;
                     }

                     float checkboxSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
                     float colorCheckboxY = displayY + usernameHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                     float colorCheckboxX = headMenuX + headMenuWidth - checkboxSize - Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                     if (mouseX >= (double)colorCheckboxX && mouseX <= (double)(colorCheckboxX + checkboxSize) && mouseY >= (double)colorCheckboxY && mouseY <= (double)(colorCheckboxY + checkboxSize)) {
                        if (isPremium) {
                           this.useCustomMessageColor = !this.useCustomMessageColor;
                           System.out.println("[IRC] Цвет сообщения: " + (this.useCustomMessageColor ? "включен" : "выключен"));
                        } else if (mc.field_1724 != null) {
                           mc.field_1724.method_7353(class_2561.method_43470("§c[IRC] §fДоступно только для Premium"), false);
                        }

                        return true;
                     }

                     float prefixButtonY = colorCheckboxY + checkboxSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                     float prefixButtonX = headMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                     float prefixButtonWidth = headMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(16.0F);
                     float prefixButtonHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
                     if (mouseX >= (double)prefixButtonX && mouseX <= (double)(prefixButtonX + prefixButtonWidth) && mouseY >= (double)prefixButtonY && mouseY <= (double)(prefixButtonY + prefixButtonHeight)) {
                        this.showPrefixMenu = !this.showPrefixMenu;
                        System.out.println("Меню префиксов: " + (this.showPrefixMenu ? "открыто" : "закрыто"));
                        return true;
                     }

                     this.customTagInputFocused = false;
                  }
               } else {
                  this.showPlayerHeadMenu = false;
                  this.customTagInputFocused = false;
                  this.editingCustomTag = "";
               }
            }

            if (this.showDButtonMenu) {
               float dMenuWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(180.0F);
               float dMenuHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(186.0F);
               if (mouseX >= (double)this.dMenuX && mouseX <= (double)(this.dMenuX + dMenuWidth) && mouseY >= (double)this.dMenuY && mouseY <= (double)(this.dMenuY + dMenuHeight)) {
                  if (button == 0 || button == 1) {
                     float checkboxSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
                     float circlePadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                     float startX = this.dMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                     float circleY = this.dMenuY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                     Color purpleColor = new Color(150, 100, 255);
                     Color blueColor = new Color(120, 160, 220);
                     Color peachColor = new Color(255, 180, 140);
                     new Color(255, 215, 0);
                     float circle2X = startX + checkboxSize + circlePadding;
                     float circle3X = circle2X + checkboxSize + circlePadding;
                     float circle4X = circle3X + checkboxSize + circlePadding;
                     if (mouseX >= (double)startX && mouseX <= (double)(startX + checkboxSize) && mouseY >= (double)circleY && mouseY <= (double)(circleY + checkboxSize)) {
                        this.selectedThemeColor = purpleColor;
                        System.out.println("Выбран фиолетовый цвет");
                        return true;
                     }

                     if (mouseX >= (double)circle2X && mouseX <= (double)(circle2X + checkboxSize) && mouseY >= (double)circleY && mouseY <= (double)(circleY + checkboxSize)) {
                        this.selectedThemeColor = blueColor;
                        System.out.println("Выбран голубой цвет");
                        return true;
                     }

                     if (mouseX >= (double)circle3X && mouseX <= (double)(circle3X + checkboxSize) && mouseY >= (double)circleY && mouseY <= (double)(circleY + checkboxSize)) {
                        this.selectedThemeColor = peachColor;
                        System.out.println("Выбран персиковый цвет");
                        return true;
                     }

                     if (mouseX >= (double)circle4X && mouseX <= (double)(circle4X + checkboxSize) && mouseY >= (double)circleY && mouseY <= (double)(circleY + checkboxSize)) {
                        if (button == 0) {
                           if (this.isPremiumUser) {
                              this.selectedThemeColor = this.customThemeColor;
                              PrintStream var10000 = System.out;
                              int var10001 = this.customThemeColor.getRed();
                              var10000.println("Выбран кастомный цвет: RGB(" + var10001 + "," + this.customThemeColor.getGreen() + "," + this.customThemeColor.getBlue() + ")");
                           } else {
                              this.sendPremiumRequiredMessage();
                           }

                           return true;
                        }

                        if (button == 1) {
                           if (!this.isPremiumUser) {
                              this.sendPremiumRequiredMessage();
                              return true;
                           }

                           this.showColorPicker = !this.showColorPicker;
                           if (this.showColorPicker) {
                              float pickerWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(180.0F);
                              this.colorPickerX = this.dMenuX - pickerWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                              this.colorPickerY = this.dMenuY;
                              Color c = this.customThemeColor;
                              float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), (float[])null);
                              this.customHue = hsb[0];
                              this.customSaturation = hsb[1];
                              this.customBrightness = hsb[2];
                              this.customColorR = c.getRed();
                              this.customColorG = c.getGreen();
                              this.customColorB = c.getBlue();
                              this.customColorA = c.getAlpha();
                              System.out.println("ColorPicker открыт (ПКМ) слева");
                           } else {
                              System.out.println("ColorPicker закрыт");
                           }

                           return true;
                        }
                     }

                     float hideY = circleY + checkboxSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                     float hideHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(22.0F);
                     if (mouseX >= (double)this.dMenuX && mouseX <= (double)(this.dMenuX + dMenuWidth) && mouseY >= (double)hideY && mouseY <= (double)(hideY + hideHeight)) {
                        this.hideFriendPanel = !this.hideFriendPanel;
                        System.out.println("Скрыть друзей: " + (this.hideFriendPanel ? "ВКЛ" : "ВЫКЛ"));
                        return true;
                     }

                     float checkboxY1 = hideY + hideHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                     float checkboxY2 = checkboxY1 + checkboxSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                     float checkboxY3 = checkboxY2 + checkboxSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                     float checkboxX = this.dMenuX + dMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F) - checkboxSize;
                     if (mouseX >= (double)checkboxX && mouseX <= (double)(checkboxX + checkboxSize) && mouseY >= (double)checkboxY1 && mouseY <= (double)(checkboxY1 + checkboxSize)) {
                        this.enableSendSound = !this.enableSendSound;
                        System.out.println("Звук отправки: " + (this.enableSendSound ? "ВКЛ" : "ВЫКЛ"));
                        return true;
                     }

                     if (mouseX >= (double)checkboxX && mouseX <= (double)(checkboxX + checkboxSize) && mouseY >= (double)checkboxY2 && mouseY <= (double)(checkboxY2 + checkboxSize)) {
                        this.enableReceiveSound = !this.enableReceiveSound;
                        System.out.println("Звук получения: " + (this.enableReceiveSound ? "ВКЛ" : "ВЫКЛ"));
                        return true;
                     }

                     if (mouseX >= (double)checkboxX && mouseX <= (double)(checkboxX + checkboxSize) && mouseY >= (double)checkboxY3 && mouseY <= (double)(checkboxY3 + checkboxSize)) {
                        this.enableWindowsNotifications = !this.enableWindowsNotifications;
                        System.out.println("Уведомление Power: " + (this.enableWindowsNotifications ? "ВКЛ" : "ВЫКЛ"));
                        return true;
                     }

                     float sliderY = checkboxY3 + checkboxSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(18.0F);
                     float sliderWidth = dMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
                     float sliderHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
                     float sliderX = this.dMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                     float thumbSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                     float clickZoneHeight = Math.max(sliderHeight, thumbSize) + Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
                     float clickZoneY = sliderY - clickZoneHeight / 2.0F + sliderHeight / 2.0F;
                     if (mouseX >= (double)sliderX && mouseX <= (double)(sliderX + sliderWidth) && mouseY >= (double)clickZoneY && mouseY <= (double)(clickZoneY + clickZoneHeight)) {
                        this.draggingVolumeSlider = true;
                        float newVolume = Math.max(0.0F, Math.min(1.0F, (float)(mouseX - (double)sliderX) / sliderWidth));
                        this.ircSoundVolume = newVolume;
                        System.out.println("Громкость IRC: " + (int)(this.ircSoundVolume * 100.0F) + "%");
                        return true;
                     }

                     float timeCheckboxY = sliderY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
                     float timeCheckboxX = this.dMenuX + dMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F) - checkboxSize;
                     if (mouseX >= (double)timeCheckboxX && mouseX <= (double)(timeCheckboxX + checkboxSize) && mouseY >= (double)timeCheckboxY && mouseY <= (double)(timeCheckboxY + checkboxSize)) {
                        this.showMessageTime = !this.showMessageTime;
                        System.out.println("Время сообщения: " + (this.showMessageTime ? "ВКЛ" : "ВЫКЛ"));
                        return true;
                     }

                     float line2Y = timeCheckboxY + checkboxSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                     float resetButtonY = line2Y + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                     float resetButtonHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(24.0F);
                     float resetButtonWidth = dMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
                     float resetButtonX = this.dMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                     if (mouseX >= (double)resetButtonX && mouseX <= (double)(resetButtonX + resetButtonWidth) && mouseY >= (double)resetButtonY && mouseY <= (double)(resetButtonY + resetButtonHeight)) {
                        this.hideFriendPanel = false;
                        this.enableSendSound = false;
                        this.enableReceiveSound = false;
                        this.ircSoundVolume = 0.0F;
                        this.showMessageTime = false;
                        System.out.println("Настройки сброшены!");
                        return true;
                     }
                  }
               } else {
                  this.showDButtonMenu = false;
               }

               if (this.showColorPicker && this.isPremiumUser) {
                  float pickerWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(200.0F);
                  float pickerHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(140.0F);
                  if (mouseX >= (double)this.colorPickerX && mouseX <= (double)(this.colorPickerX + pickerWidth) && mouseY >= (double)this.colorPickerY && mouseY <= (double)(this.colorPickerY + pickerHeight)) {
                     float sliderStartY = this.colorPickerY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(45.0F);
                     float sliderSpacing = Ke4708cf52c3b89f5ae.getInstance().scaledUi(25.0F);
                     float sliderWidth = pickerWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(70.0F);
                     float sliderHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
                     float sliderX = this.colorPickerX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(50.0F);
                     float clickZoneHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
                     if (mouseX >= (double)sliderX && mouseX <= (double)(sliderX + sliderWidth) && mouseY >= (double)(sliderStartY - clickZoneHeight / 2.0F) && mouseY <= (double)(sliderStartY + sliderHeight + clickZoneHeight / 2.0F)) {
                        this.draggingRedSlider = true;
                        this.customColorR = Math.max(0, Math.min(255, (int)((mouseX - (double)sliderX) / (double)sliderWidth * (double)255.0F)));
                        System.out.println("R: " + this.customColorR);
                        return true;
                     } else {
                        float greenSliderY = sliderStartY + sliderSpacing;
                        if (mouseX >= (double)sliderX && mouseX <= (double)(sliderX + sliderWidth) && mouseY >= (double)(greenSliderY - clickZoneHeight / 2.0F) && mouseY <= (double)(greenSliderY + sliderHeight + clickZoneHeight / 2.0F)) {
                           this.draggingGreenSlider = true;
                           this.customColorG = Math.max(0, Math.min(255, (int)((mouseX - (double)sliderX) / (double)sliderWidth * (double)255.0F)));
                           System.out.println("G: " + this.customColorG);
                           return true;
                        } else {
                           float blueSliderY = sliderStartY + sliderSpacing * 2.0F;
                           if (mouseX >= (double)sliderX && mouseX <= (double)(sliderX + sliderWidth) && mouseY >= (double)(blueSliderY - clickZoneHeight / 2.0F) && mouseY <= (double)(blueSliderY + sliderHeight + clickZoneHeight / 2.0F)) {
                              this.draggingBlueSlider = true;
                              this.customColorB = Math.max(0, Math.min(255, (int)((mouseX - (double)sliderX) / (double)sliderWidth * (double)255.0F)));
                              System.out.println("B: " + this.customColorB);
                              return true;
                           } else {
                              float applyButtonY = this.colorPickerY + pickerHeight - Ke4708cf52c3b89f5ae.getInstance().scaledUi(34.0F);
                              float applyButtonHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(24.0F);
                              float applyButtonWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(80.0F);
                              float applyButtonX = this.colorPickerX + (pickerWidth - applyButtonWidth) / 2.0F;
                              if (mouseX >= (double)applyButtonX && mouseX <= (double)(applyButtonX + applyButtonWidth) && mouseY >= (double)applyButtonY && mouseY <= (double)(applyButtonY + applyButtonHeight)) {
                                 this.customThemeColor = new Color(this.customColorR, this.customColorG, this.customColorB);
                                 this.selectedThemeColor = this.customThemeColor;
                                 this.showColorPicker = false;
                                 System.out.println("Применён кастомный цвет: RGB(" + this.customColorR + "," + this.customColorG + "," + this.customColorB + ")");
                                 return true;
                              } else {
                                 return true;
                              }
                           }
                        }
                     }
                  } else {
                     this.showColorPicker = false;
                     return true;
                  }
               } else {
                  return true;
               }
            } else {
               if (button == 1 && !this.hideFriendPanel) {
                  float friendListStartY = rightPanelLineY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                  if (this.showAddFriendInput) {
                     float addFriendInputHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(25.0F);
                     friendListStartY = rightPanelLineY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F) + addFriendInputHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                  }

                  float friendEntryHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
                  float friendEntryPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                  List<K204c47a3480aa2d86b> friendsList = K8ee912418ef60c15b7.getInstance().getIRCFriends();
                  float friendListAvailableHeight = verticalLineY + verticalLineHeight - friendListStartY;
                  int maxVisibleFriends = (int)Math.floor((double)((friendListAvailableHeight + friendEntryPadding) / (friendEntryHeight + friendEntryPadding)));
                  int startIdx = (int)this.rightPanelScrollOffset;
                  int endIdx = Math.min(startIdx + maxVisibleFriends, friendsList.size());
                  float currentY = friendListStartY;

                  for(int i = startIdx; i < endIdx; ++i) {
                     K204c47a3480aa2d86b friend = (K204c47a3480aa2d86b)friendsList.get(i);
                     if (mouseX >= (double)rightPanelLineX && mouseX <= (double)(rightPanelLineX + rightPanelLineWidth) && mouseY >= (double)currentY && mouseY <= (double)(currentY + friendEntryHeight)) {
                        this.selectedFriend = friend.getUsername();
                        float menuW = Ke4708cf52c3b89f5ae.getInstance().scaledUi(180.0F);
                        float menuH = Ke4708cf52c3b89f5ae.getInstance().scaledUi(90.0F);
                        this.contextMenuX = verticalLineX + lineOffset + Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F);
                        this.contextMenuY = currentY;
                        if (this.contextMenuY + menuH > verticalLineY + verticalLineHeight) {
                           this.contextMenuY = verticalLineY + verticalLineHeight - menuH;
                        }

                        if (this.contextMenuY < verticalLineY) {
                           this.contextMenuY = verticalLineY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                        }

                        this.showFriendContextMenu = true;
                        System.out.println("[IRC] ПКМ на друге: открыто меню для " + friend.getUsername());
                        return true;
                     }

                     currentY += friendEntryHeight + friendEntryPadding;
                  }
               }

               if (button == 0) {
                  windowWidth = (float)mc.method_22683().method_4486();
                  windowHeight = (float)mc.method_22683().method_4502();
                  guiWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(600.0F);
                  guiHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(400.0F);
                  guiX = windowWidth / 2.0F - guiWidth / 2.0F + this.guiOffsetX;
                  guiY = windowHeight / 2.0F - guiHeight / 2.0F + this.guiOffsetY;
                  sidebarWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(50.0F);
                  float titleBarHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
                  if (mouseX >= (double)guiX && mouseX <= (double)(guiX + guiWidth) && mouseY >= (double)guiY && mouseY <= (double)(guiY + titleBarHeight)) {
                     this.guiDragging = true;
                     this.dragOffsetX = (float)mouseX - guiX;
                     this.dragOffsetY = (float)mouseY - guiY;
                     this.isMessageFieldFocused = false;
                     return true;
                  }

                  float chatButtonSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(24.0F);
                  float chatButtonX = guiX + (sidebarWidth - chatButtonSize) / 2.0F;
                  float chatButtonY = guiY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(50.0F);
                  float wButtonSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(24.0F);
                  float wButtonX = guiX + (sidebarWidth - wButtonSize) / 2.0F;
                  float wButtonY = chatButtonY + chatButtonSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                  if (mouseX >= (double)chatButtonX && mouseX <= (double)(chatButtonX + chatButtonSize) && mouseY >= (double)chatButtonY && mouseY <= (double)(chatButtonY + chatButtonSize)) {
                     this.friendChatMode = false;
                     this.currentPrivateChat = null;
                     System.out.println("Переключено в Global Chat");
                     return true;
                  }

                  if (mouseX >= (double)wButtonX && mouseX <= (double)(wButtonX + wButtonSize) && mouseY >= (double)wButtonY && mouseY <= (double)(wButtonY + wButtonSize)) {
                     this.friendChatMode = true;
                     this.currentPrivateChat = null;
                     System.out.println("Переключено в Friend Chat");
                     return true;
                  }

                  float menuLeftPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                  menuTopPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
                  menuX = guiX + sidebarWidth + menuLeftPadding;
                  menuY = guiY + menuTopPadding;
                  menuWidth = guiWidth - sidebarWidth - menuLeftPadding;
                  menuHeight = guiHeight - menuTopPadding;
                  lineOffset = Ke4708cf52c3b89f5ae.getInstance().scaledUi(120.0F);
                  lineTopPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(45.0F);
                  verticalLineX = menuX + menuWidth - lineOffset;
                  verticalLineY = menuY + lineTopPadding;
                  verticalLineHeight = menuHeight - lineTopPadding;
                  float gapBetweenPanels = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                  float leftLineWidth = verticalLineX - menuX - gapBetweenPanels;
                  float bottomLineY = verticalLineY + verticalLineHeight - Ke4708cf52c3b89f5ae.getInstance().scaledUi(50.0F);
                  float textX = menuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                  float textY = bottomLineY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(293.0F);
                  float textSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                  float textWidth = K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth("Global Chat", textSize);
                  float iconButtonSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(16.0F);
                  float iconButtonX = textX + textWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                  float iconButtonY = textY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
                  if (this.friendChatMode && this.currentPrivateChat != null) {
                     K204c47a3480aa2d86b friend = K8ee912418ef60c15b7.getInstance().getIRCFriendByUsername(this.currentPrivateChat);
                     String onlineStatus = "";
                     if (friend != null) {
                        onlineStatus = friend.isOnline() ? " • В игре" : " • Не в сети";
                     }

                     String chatModeText = "Message: " + this.currentPrivateChat + onlineStatus;
                     float chatTextWidth = K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(chatModeText, textSize);
                     float chatIconButtonSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(16.0F);
                     float chatIconButtonX = textX + chatTextWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                     float chatIconButtonY = textY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F);
                     float expandedHitbox = Ke4708cf52c3b89f5ae.getInstance().scaledUi(4.0F);
                     if (mouseX >= (double)(chatIconButtonX - expandedHitbox) && mouseX <= (double)(chatIconButtonX + chatIconButtonSize + expandedHitbox) && mouseY >= (double)(chatIconButtonY - expandedHitbox) && mouseY <= (double)(chatIconButtonY + chatIconButtonSize + expandedHitbox)) {
                        this.currentPrivateChat = null;
                        System.out.println("[IRC] ← Стрелка нажата! Возврат в список диалогов. friendChatMode остаётся: " + this.friendChatMode);
                        return true;
                     }
                  }

                  if (this.friendChatMode && this.currentPrivateChat == null) {
                     List<String> dialogList = K8ee912418ef60c15b7.getInstance().getDialogUsers();
                     float ircTextY = textY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(43.0F);
                     float friendsListStartY = ircTextY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(75.0F);
                     float friendsListX = menuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                     float friendItemHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
                     float friendItemSpacing = Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.5F);
                     float rightBoundary = verticalLineX - gapBetweenPanels;
                     float scrollBarSpace = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
                     float itemWidth = rightBoundary - friendsListX - scrollBarSpace - Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                     int startIndex = (int)this.friendChatScrollOffset;
                     float friendChatBottomY = verticalLineY + verticalLineHeight;
                     float availableHeight = friendChatBottomY - friendsListStartY;
                     int maxVisibleFriendsInChat = (int)Math.floor((double)(availableHeight / (friendItemHeight + friendItemSpacing)));
                     int endIndex = Math.min(startIndex + maxVisibleFriendsInChat, dialogList.size());
                     float currentY = friendsListStartY;

                     for(int i = startIndex; i < endIndex; ++i) {
                        String dialogUsername = (String)dialogList.get(i);
                        if (mouseX >= (double)friendsListX && mouseX <= (double)(friendsListX + itemWidth) && mouseY >= (double)currentY && mouseY <= (double)(currentY + friendItemHeight)) {
                           this.currentPrivateChat = dialogUsername;
                           this.chatScrollOffset = 0.0F;
                           K8ee912418ef60c15b7.getInstance().markAsRead(dialogUsername);
                           K3e5d28a93090bc4a75.getInstance().requestPrivateHistory(dialogUsername);
                           System.out.println("[IRC] Открыт диалог с: " + dialogUsername);
                           return true;
                        }

                        currentY += friendItemHeight + friendItemSpacing;
                     }
                  }

                  if (mouseX >= (double)iconButtonX && mouseX <= (double)(iconButtonX + iconButtonSize) && mouseY >= (double)iconButtonY && mouseY <= (double)(iconButtonY + iconButtonSize)) {
                     mc.method_1507(new K1aa473fe0ce338f657());
                     return true;
                  }

                  float rectLeftPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                  float rectRightPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
                  float rectWidth = leftLineWidth - rectLeftPadding - rectRightPadding;
                  float rectHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(30.0F);
                  float lineThickness = Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F);
                  float availableSpace = verticalLineY + verticalLineHeight - (bottomLineY + lineThickness);
                  float rectY = bottomLineY + lineThickness + (availableSpace - rectHeight) / 2.0F;
                  float squareX = menuX + rectLeftPadding + rectWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                  if (mouseX >= (double)squareX && mouseX <= (double)(squareX + rectHeight) && mouseY >= (double)rectY && mouseY <= (double)(rectY + rectHeight)) {
                     if (!this.messageInput.trim().isEmpty()) {
                        if (this.customTag.isEmpty()) {
                           if (mc.field_1724 != null) {
                              mc.field_1724.method_7353(class_2561.method_43470("§c[IRC] §fУстановите тег для отправки сообщений!"), false);
                              mc.field_1724.method_7353(class_2561.method_43470("§7Инструкция: §fНажмите §e'T' §f→ §eредактируйте тег §f→ §eEnter"), false);
                           }

                           return true;
                        }

                        if (this.friendChatMode && this.currentPrivateChat != null) {
                           K3e5d28a93090bc4a75.getInstance().sendPrivateMessage(this.currentPrivateChat, this.messageInput);
                           System.out.println("[IRC] Отправлено приватное сообщение для " + this.currentPrivateChat + ": " + this.messageInput);
                        } else {
                           K3e5d28a93090bc4a75.getInstance().sendGlobalMessage(this.messageInput);
                        }

                        System.out.println("Отправлено сообщение (клик): " + this.messageInput);
                        this.messageInput = "";
                        this.cursorPosition = 0;
                        this.clearSelection();
                     }

                     return true;
                  }

                  float dButtonSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(24.0F);
                  float dButtonX = guiX + (sidebarWidth - dButtonSize) / 2.0F;
                  float dButtonY = wButtonY + wButtonSize + Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                  float cornerRadius = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
                  float addButtonSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(18.0F);
                  float addButtonX = verticalLineX + cornerRadius - addButtonSize / 2.0F;
                  float addButtonY = verticalLineY + cornerRadius - addButtonSize / 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(19.0F);
                  if (mouseX >= (double)addButtonX && mouseX <= (double)(addButtonX + addButtonSize) && mouseY >= (double)addButtonY && mouseY <= (double)(addButtonY + addButtonSize)) {
                     this.showAddFriendInput = !this.showAddFriendInput;
                     if (this.showAddFriendInput) {
                        this.addFriendInputText = "";
                        this.addFriendInputFocused = true;
                     }

                     System.out.println("Поле добавления друга: " + (this.showAddFriendInput ? "открыто" : "закрыто"));
                     return true;
                  }

                  if (this.showAddFriendInput) {
                     float addFriendInputHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(25.0F);
                     float addFriendInputY = rightPanelLineY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                     if (mouseX >= (double)rightPanelLineX && mouseX <= (double)(rightPanelLineX + rightPanelLineWidth) && mouseY >= (double)addFriendInputY && mouseY <= (double)(addFriendInputY + addFriendInputHeight)) {
                        this.addFriendInputFocused = true;
                        System.out.println("Фокус на поле ввода друга");
                        return true;
                     }
                  }

                  if (!this.hideFriendPanel) {
                     float friendListStartY = rightPanelLineY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                     if (this.showAddFriendInput) {
                        float addFriendInputHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(25.0F);
                        friendListStartY = rightPanelLineY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F) + addFriendInputHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                     }

                     float friendEntryHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
                     float friendEntryPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                     List<K204c47a3480aa2d86b> friendsList = K8ee912418ef60c15b7.getInstance().getIRCFriends();
                     float friendListAvailableHeight = verticalLineY + verticalLineHeight - friendListStartY;
                     int maxVisibleFriends = (int)Math.floor((double)((friendListAvailableHeight + friendEntryPadding) / (friendEntryHeight + friendEntryPadding)));
                     int startIdx = (int)this.rightPanelScrollOffset;
                     int endIdx = Math.min(startIdx + maxVisibleFriends, friendsList.size());
                     float currentY = friendListStartY;

                     for(int i = startIdx; i < endIdx; ++i) {
                        K204c47a3480aa2d86b friend = (K204c47a3480aa2d86b)friendsList.get(i);
                        if (mouseX >= (double)rightPanelLineX && mouseX <= (double)(rightPanelLineX + rightPanelLineWidth) && mouseY >= (double)currentY && mouseY <= (double)(currentY + friendEntryHeight)) {
                           this.friendChatMode = true;
                           this.currentPrivateChat = friend.getUsername();
                           this.chatScrollOffset = 0.0F;
                           K8ee912418ef60c15b7.getInstance().createDialog(friend.getUsername());
                           K3e5d28a93090bc4a75.getInstance().requestPrivateHistory(friend.getUsername());
                           K8ee912418ef60c15b7.getInstance().markAsRead(friend.getUsername());
                           System.out.println("[IRC] ЛКМ на друге: открыт диалог с " + friend.getUsername());
                           return true;
                        }

                        currentY += friendEntryHeight + friendEntryPadding;
                     }
                  }

                  float skinHeadSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(35.0F);
                  float skinHeadX = guiX + (sidebarWidth - skinHeadSize) / 2.0F;
                  float skinHeadY = guiY + guiHeight - skinHeadSize - Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                  if (mouseX >= (double)skinHeadX && mouseX <= (double)(skinHeadX + skinHeadSize) && mouseY >= (double)skinHeadY && mouseY <= (double)(skinHeadY + skinHeadSize)) {
                     this.showPlayerHeadMenu = !this.showPlayerHeadMenu;
                     System.out.println("Меню головы: " + (this.showPlayerHeadMenu ? "открыто" : "закрыто"));
                     return true;
                  }

                  if (mouseX >= (double)dButtonX && mouseX <= (double)(dButtonX + dButtonSize) && mouseY >= (double)dButtonY && mouseY <= (double)(dButtonY + dButtonSize)) {
                     this.showDButtonMenu = !this.showDButtonMenu;
                     if (this.showDButtonMenu) {
                        float dMenuWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(180.0F);
                        float dMenuHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(186.0F);
                        this.dMenuX = dButtonX - dMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F);
                        this.dMenuY = dButtonY;
                        float guiRight = guiX + guiWidth;
                        float guiBottom = guiY + guiHeight;
                        if (this.dMenuX + dMenuWidth > guiRight) {
                           this.dMenuX = guiRight - dMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                        }

                        if (this.dMenuY + dMenuHeight > guiBottom) {
                           this.dMenuY = guiBottom - dMenuHeight - Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                        }

                        if (this.dMenuY < guiY) {
                           this.dMenuY = guiY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                        }
                     }

                     return true;
                  }

                  if (mouseX >= (double)iconButtonX && mouseX <= (double)(iconButtonX + iconButtonSize) && mouseY >= (double)iconButtonY && mouseY <= (double)(iconButtonY + iconButtonSize)) {
                     mc.method_1507(new K1aa473fe0ce338f657());
                     return true;
                  }

                  if (this.friendChatMode && this.currentPrivateChat == null) {
                     float searchTextX = menuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                     float searchTextY = bottomLineY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(293.0F);
                     float searchTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(13.0F);
                     float searchTextWidth = K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth("Private Message", searchTextSize);
                     float searchFieldX = searchTextX + searchTextWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(15.0F);
                     float searchFieldY = searchTextY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.0F);
                     float searchFieldWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(150.0F);
                     float searchFieldHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
                     if (mouseX >= (double)searchFieldX && mouseX <= (double)(searchFieldX + searchFieldWidth) && mouseY >= (double)searchFieldY && mouseY <= (double)(searchFieldY + searchFieldHeight)) {
                        this.playerSearchFocused = true;
                        this.isMessageFieldFocused = false;
                        return true;
                     }
                  }

                  float rectX = menuX + rectLeftPadding;
                  if (mouseX >= (double)rectX && mouseX <= (double)(rectX + rectWidth) && mouseY >= (double)rectY && mouseY <= (double)(rectY + rectHeight)) {
                     this.isMessageFieldFocused = true;
                     this.playerSearchFocused = false;
                     this.lastCursorBlink = System.currentTimeMillis();
                     return true;
                  }

                  this.isMessageFieldFocused = false;
                  if (!this.hideFriendPanel && !this.showRulesWindow && mouseX >= (double)verticalLineX && mouseX <= (double)(verticalLineX + lineOffset) && mouseY >= (double)verticalLineY && mouseY <= (double)(verticalLineY + verticalLineHeight)) {
                     rightPanelLineX = verticalLineX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F) + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                     float friendListStartY = rightPanelLineY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                     float friendEntryHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
                     float friendEntryPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
                     List<K204c47a3480aa2d86b> friends = K8ee912418ef60c15b7.getInstance().getIRCFriends();
                     friends.sort((a, b) -> {
                        if (a.isOnline() && !b.isOnline()) {
                           return -1;
                        } else if (!a.isOnline() && b.isOnline()) {
                           return 1;
                        } else {
                           boolean aFav = favoriteFriends.contains(a.getUsername());
                           boolean bFav = favoriteFriends.contains(b.getUsername());
                           if (aFav && !bFav) {
                              return -1;
                           } else {
                              return !aFav && bFav ? 1 : 0;
                           }
                        }
                     });
                     float friendListAvailableHeight = verticalLineY + verticalLineHeight - friendListStartY;
                     int maxVisibleFriends = (int)Math.floor((double)((friendListAvailableHeight + friendEntryPadding) / (friendEntryHeight + friendEntryPadding)));
                     int startIndex = (int)this.rightPanelScrollOffset;
                     int endIndex = Math.min(startIndex + maxVisibleFriends, friends.size());
                     float currentFriendY = friendListStartY;

                     for(int i = startIndex; i < endIndex; ++i) {
                        K204c47a3480aa2d86b friend = (K204c47a3480aa2d86b)friends.get(i);
                        String friendName = friend.getUsername();
                        if (mouseX >= (double)rightPanelLineX && mouseX <= (double)(rightPanelLineX + lineOffset - Ke4708cf52c3b89f5ae.getInstance().scaledUi(15.0F)) && mouseY >= (double)currentFriendY && mouseY <= (double)(currentFriendY + friendEntryHeight)) {
                           if (button == 0) {
                              this.friendChatMode = true;
                              this.currentPrivateChat = friendName;
                              this.chatScrollOffset = 0.0F;
                              K3e5d28a93090bc4a75.getInstance().requestPrivateHistory(friendName);
                              K8ee912418ef60c15b7.getInstance().markAsRead(friendName);
                              System.out.println("[IRC] ЛКМ: Открыт диалог с другом: " + friendName);
                              return true;
                           }

                           if (button == 1) {
                              this.selectedFriend = friendName;
                              float menuContextWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(180.0F);
                              float menuContextHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(90.0F);
                              this.contextMenuX = verticalLineX + lineOffset + Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F);
                              this.contextMenuY = currentFriendY;
                              windowWidth = (float)mc.method_22683().method_4486();
                              windowHeight = (float)mc.method_22683().method_4502();
                              guiWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(600.0F);
                              guiHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(400.0F);
                              guiX = windowWidth / 2.0F - guiWidth / 2.0F + this.guiOffsetX;
                              guiY = windowHeight / 2.0F - guiHeight / 2.0F + this.guiOffsetY;
                              if (this.contextMenuY + menuContextHeight > guiY + guiHeight) {
                                 this.contextMenuY = guiY + guiHeight - menuContextHeight - Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                              }

                              if (this.contextMenuY < verticalLineY) {
                                 this.contextMenuY = verticalLineY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
                              }

                              this.showFriendContextMenu = true;
                              System.out.println("[IRC] ПКМ: Открыто контекстное меню для: " + friendName);
                              return true;
                           }
                        }

                        currentFriendY += friendEntryHeight + friendEntryPadding;
                     }
                  }
               }

               this.themeEditor.mouseClicked(mouseX, mouseY, button);
               return super.method_25402(mouseX, mouseY, button);
            }
         }
      }
   }

   public boolean method_25406(double mouseX, double mouseY, int button) {
      if (button == 0) {
         this.guiDragging = false;
         this.draggingVolumeSlider = false;
         this.draggingRedSlider = false;
         this.draggingGreenSlider = false;
         this.draggingBlueSlider = false;
         this.draggingCustomHue = false;
         this.draggingCustomSatBright = false;
         this.draggingCustomAlpha = false;
         this.draggingColorPicker = false;
      }

      this.themeEditor.mouseReleased(mouseX, mouseY, button);
      return super.method_25406(mouseX, mouseY, button);
   }

   public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
      if (this.draggingColorPicker && button == 0) {
         this.colorPickerX = (float)(mouseX - (double)this.colorPickerDragOffsetX);
         this.colorPickerY = (float)(mouseY - (double)this.colorPickerDragOffsetY);
         return true;
      } else if (this.draggingVolumeSlider && button == 0 && this.showDButtonMenu) {
         float dMenuWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(180.0F);
         float sliderWidth = dMenuWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
         float sliderX = this.dMenuX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
         float newVolume = Math.max(0.0F, Math.min(1.0F, (float)(mouseX - (double)sliderX) / sliderWidth));
         this.ircSoundVolume = newVolume;
         return true;
      } else {
         if (this.showColorPicker && this.isPremiumUser && button == 0) {
            float pickerWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(200.0F);
            float sliderWidth = pickerWidth - Ke4708cf52c3b89f5ae.getInstance().scaledUi(70.0F);
            float sliderX = this.colorPickerX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(50.0F);
            if (this.draggingRedSlider) {
               this.customColorR = Math.max(0, Math.min(255, (int)((mouseX - (double)sliderX) / (double)sliderWidth * (double)255.0F)));
               return true;
            }

            if (this.draggingGreenSlider) {
               this.customColorG = Math.max(0, Math.min(255, (int)((mouseX - (double)sliderX) / (double)sliderWidth * (double)255.0F)));
               return true;
            }

            if (this.draggingBlueSlider) {
               this.customColorB = Math.max(0, Math.min(255, (int)((mouseX - (double)sliderX) / (double)sliderWidth * (double)255.0F)));
               return true;
            }
         }

         if (this.guiDragging && button == 0) {
            float windowWidth = (float)mc.method_22683().method_4486();
            float windowHeight = (float)mc.method_22683().method_4502();
            float guiWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(600.0F);
            float guiHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(400.0F);
            this.guiOffsetX = (float)mouseX - this.dragOffsetX - (windowWidth / 2.0F - guiWidth / 2.0F);
            this.guiOffsetY = (float)mouseY - this.dragOffsetY - (windowHeight / 2.0F - guiHeight / 2.0F);
            return true;
         } else {
            return super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
         }
      }
   }

   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
      if (keyCode == 256) {
         this.method_25419();
         return true;
      } else if (this.customTagInputFocused) {
         if (keyCode == 259 && !this.editingCustomTag.isEmpty()) {
            this.editingCustomTag = this.editingCustomTag.substring(0, this.editingCustomTag.length() - 1);
            System.out.println("Редактирование тега: @" + this.editingCustomTag);
            return true;
         } else if (keyCode != 257 && keyCode != 335) {
            if (keyCode == 256) {
               this.customTagInputFocused = false;
               this.editingCustomTag = "";
               this.isCheckingTagUniqueness = false;
               System.out.println("Редактирование отменено");
               return true;
            } else {
               return true;
            }
         } else {
            String var13 = this.editingCustomTag;
            System.out.println("[IRC DEBUG] Enter нажат. editingCustomTag: '" + var13 + "', length: " + this.editingCustomTag.length());
            System.out.println("[IRC DEBUG] isCheckingTagUniqueness: " + this.isCheckingTagUniqueness);
            System.out.println("[IRC DEBUG] customTag: '" + this.customTag + "'");
            if ((this.editingCustomTag.isEmpty() || this.editingCustomTag.length() >= 3) && !this.isCheckingTagUniqueness) {
               System.out.println("[IRC DEBUG] Прошла проверка длины и флага");
               if (this.editingCustomTag.equals(this.customTag)) {
                  System.out.println("[IRC DEBUG] Тег не изменился: " + this.editingCustomTag);
                  if (mc.field_1724 != null) {
                     mc.field_1724.method_7353(class_2561.method_43470("§c[IRC] §fЭто уже ваш текущий тег!"), false);
                  }

                  this.customTagInputFocused = false;
                  this.editingCustomTag = "";
                  return true;
               }

               System.out.println("[IRC DEBUG] Тег изменился, проверяем кулдаун");
               if (!this.canChangeCustomTag()) {
                  System.out.println("[IRC DEBUG] Кулдаун не прошёл");
                  String timeRemaining = this.getTimeUntilNextTagChange();
                  if (mc.field_1724 != null) {
                     mc.field_1724.method_7353(class_2561.method_43470("§c[IRC] §fВы сможете изменить тег через: " + timeRemaining), false);
                  }

                  this.customTagInputFocused = false;
                  this.editingCustomTag = "";
                  return true;
               }

               System.out.println("[IRC DEBUG] Кулдаун прошёл, отправляем запрос на сервер");
               this.pendingCustomTag = this.editingCustomTag;
               this.isCheckingTagUniqueness = true;
               K3e5d28a93090bc4a75.getInstance().checkTagUniqueness(this.editingCustomTag);
               System.out.println("[IRC] Проверка уникальности тега: " + this.editingCustomTag);
            } else if (this.editingCustomTag.length() < 3) {
               System.out.println("[IRC DEBUG] Тег слишком короткий: " + this.editingCustomTag.length());
               if (mc.field_1724 != null) {
                  mc.field_1724.method_7353(class_2561.method_43470("§c[IRC] §fТег должен содержать минимум 3 символа"), false);
               }
            } else {
               System.out.println("[IRC DEBUG] isCheckingTagUniqueness = true, игнорируем Enter");
            }

            return true;
         }
      } else if (this.playerSearchFocused) {
         if (keyCode == 259 && !this.playerSearchInput.isEmpty()) {
            this.playerSearchInput = this.playerSearchInput.substring(0, this.playerSearchInput.length() - 1);
            return true;
         } else if (keyCode == 256) {
            this.playerSearchFocused = false;
            this.playerSearchInput = "";
            return true;
         } else {
            return true;
         }
      } else if (this.addFriendInputFocused && this.showAddFriendInput) {
         if (keyCode != 257 && keyCode != 335) {
            if (keyCode == 259) {
               if (!this.addFriendInputText.isEmpty()) {
                  this.addFriendInputText = this.addFriendInputText.substring(0, this.addFriendInputText.length() - 1);
               }

               return true;
            } else if (keyCode == 256) {
               this.showAddFriendInput = false;
               this.addFriendInputFocused = false;
               return true;
            } else {
               return true;
            }
         } else {
            String input = this.addFriendInputText.trim();
            if (input.length() >= 3) {
               boolean isUUID = input.contains("-") && input.length() == 36;
               System.out.println("Попытка добавить друга: " + input + " (UUID: " + isUUID + ")");
               K3e5d28a93090bc4a75.getInstance().sendAddFriendRequest(input, isUUID);
               this.showAddFriendInput = false;
               this.addFriendInputText = "";
               this.addFriendInputFocused = false;
            } else if (!input.isEmpty() && mc.field_1724 != null) {
               mc.field_1724.method_7353(class_2561.method_43470("§c[IRC] §fНик должен содержать минимум 3 символа"), false);
            }

            return true;
         }
      } else {
         if (this.isMessageFieldFocused) {
            boolean isCtrlPressed = (modifiers & 2) != 0;
            if (isCtrlPressed) {
               if (keyCode == 65) {
                  this.selectionStart = 0;
                  this.selectionEnd = this.messageInput.length();
                  this.cursorPosition = this.messageInput.length();
                  return true;
               }

               if (keyCode == 67) {
                  String textToCopy = this.getSelectedText();
                  if (!textToCopy.isEmpty()) {
                     mc.field_1774.method_1455(textToCopy);
                     System.out.println("Скопировано: " + textToCopy);
                  }

                  return true;
               }

               if (keyCode == 86) {
                  String clipboard = mc.field_1774.method_1460();
                  if (clipboard != null && !clipboard.isEmpty()) {
                     this.deleteSelection();
                     this.messageInput = this.messageInput.substring(0, this.cursorPosition) + clipboard + this.messageInput.substring(this.cursorPosition);
                     this.cursorPosition += clipboard.length();
                     this.clearSelection();
                  }

                  return true;
               }

               if (keyCode == 88) {
                  String textToCut = this.getSelectedText();
                  if (!textToCut.isEmpty()) {
                     mc.field_1774.method_1455(textToCut);
                     this.deleteSelection();
                     System.out.println("Вырезано: " + textToCut);
                  }

                  return true;
               }
            }

            if (keyCode == 257 || keyCode == 335) {
               if (!this.messageInput.trim().isEmpty()) {
                  if (this.customTag.isEmpty()) {
                     if (mc.field_1724 != null) {
                        mc.field_1724.method_7353(class_2561.method_43470("§c§l[IRC] §cВы не можете писать без юза!"), false);
                        mc.field_1724.method_7353(class_2561.method_43470(""), false);
                        mc.field_1724.method_7353(class_2561.method_43470("§e§l  Как установить юз:"), false);
                        mc.field_1724.method_7353(class_2561.method_43470("§f  1. §7Кликни §fЛКМ §7на свою §fголову §7слева внизу"), false);
                        mc.field_1724.method_7353(class_2561.method_43470("§f  2. §7Кликни §fЛКМ §7на поле §fcustom tag"), false);
                        mc.field_1724.method_7353(class_2561.method_43470("§f  3. §7Напиши свой юз §f(минимум 3 символа)"), false);
                        mc.field_1724.method_7353(class_2561.method_43470("§f  4. §7Нажми §eEnter §7для подтверждения"), false);
                        mc.field_1724.method_7353(class_2561.method_43470(""), false);
                        mc.field_1724.method_7353(class_2561.method_43470("§7  Пример: §fExcluzive §7или §frevlaze"), false);
                     }

                     return true;
                  }

                  if (this.friendChatMode && this.currentPrivateChat != null) {
                     K3e5d28a93090bc4a75.getInstance().sendPrivateMessage(this.currentPrivateChat, this.messageInput);
                     System.out.println("[IRC] Отправлено приватное сообщение для " + this.currentPrivateChat + ": " + this.messageInput);
                  } else {
                     K3e5d28a93090bc4a75.getInstance().sendGlobalMessage(this.messageInput);
                  }

                  System.out.println("Отправлено сообщение: " + this.messageInput);
                  if (this.enableSendSound) {
                     float volume = this.ircSoundVolume * 20.0F;
                     mc.method_1483().method_4873(class_1109.method_4757(K7d074a66e20f0c1531.IRC_SEND_EVENT, 1.0F, volume));
                  }

                  this.messageInput = "";
                  this.cursorPosition = 0;
                  this.clearSelection();
               }

               return true;
            }

            if (keyCode == 259) {
               if (this.hasSelection()) {
                  this.deleteSelection();
               } else if (this.cursorPosition > 0 && !this.messageInput.isEmpty()) {
                  String var12 = this.messageInput.substring(0, this.cursorPosition - 1);
                  this.messageInput = var12 + this.messageInput.substring(this.cursorPosition);
                  --this.cursorPosition;
               }

               return true;
            }

            if (keyCode == 261) {
               if (this.hasSelection()) {
                  this.deleteSelection();
               } else if (this.cursorPosition < this.messageInput.length()) {
                  String var10001 = this.messageInput.substring(0, this.cursorPosition);
                  this.messageInput = var10001 + this.messageInput.substring(this.cursorPosition + 1);
               }

               return true;
            }

            if (keyCode == 263) {
               if (this.cursorPosition > 0) {
                  --this.cursorPosition;
               }

               this.clearSelection();
               return true;
            }

            if (keyCode == 262) {
               if (this.cursorPosition < this.messageInput.length()) {
                  ++this.cursorPosition;
               }

               this.clearSelection();
               return true;
            }

            if (keyCode == 268) {
               this.cursorPosition = 0;
               this.clearSelection();
               return true;
            }

            if (keyCode == 269) {
               this.cursorPosition = this.messageInput.length();
               this.clearSelection();
               return true;
            }
         }

         this.themeEditor.keyPressed(keyCode, scanCode, modifiers);
         return super.method_25404(keyCode, scanCode, modifiers);
      }
   }

   public boolean method_25400(char chr, int modifiers) {
      if (this.playerSearchFocused) {
         if ((Character.isLetterOrDigit(chr) || chr == '_') && this.playerSearchInput.length() < 16) {
            this.playerSearchInput = this.playerSearchInput + chr;
         }

         return true;
      } else if (this.customTagInputFocused) {
         if (Character.isLetterOrDigit(chr) && chr <= 127 && this.editingCustomTag.length() < 10) {
            this.editingCustomTag = this.editingCustomTag + chr;
            System.out.println("Редактирование тега: @" + this.editingCustomTag);
         }

         return true;
      } else if (this.addFriendInputFocused && this.showAddFriendInput) {
         if ((Character.isLetterOrDigit(chr) || chr == '_' || chr == '-') && this.addFriendInputText.length() < 36) {
            this.addFriendInputText = this.addFriendInputText + chr;
         }

         return true;
      } else {
         if (this.isMessageFieldFocused) {
            if (this.messageInput.length() >= 95) {
               return true;
            }

            if (Character.isLetterOrDigit(chr) || Character.isWhitespace(chr) || "!@#$%^&*()_+-=[]{}|;':\",./<>?абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".indexOf(chr) >= 0) {
               this.deleteSelection();
               this.messageInput = this.messageInput.substring(0, this.cursorPosition) + chr + this.messageInput.substring(this.cursorPosition);
               ++this.cursorPosition;
               return true;
            }
         }

         return super.method_25400(chr, modifiers);
      }
   }

   private boolean hasSelection() {
      return this.selectionStart != this.selectionEnd;
   }

   private String getSelectedText() {
      if (!this.hasSelection()) {
         return this.messageInput;
      } else {
         int start = Math.min(this.selectionStart, this.selectionEnd);
         int end = Math.max(this.selectionStart, this.selectionEnd);
         return this.messageInput.substring(start, end);
      }
   }

   private void deleteSelection() {
      if (this.hasSelection()) {
         int start = Math.min(this.selectionStart, this.selectionEnd);
         int end = Math.max(this.selectionStart, this.selectionEnd);
         String var10001 = this.messageInput.substring(0, start);
         this.messageInput = var10001 + this.messageInput.substring(end);
         this.cursorPosition = start;
         this.clearSelection();
      }
   }

   private void clearSelection() {
      this.selectionStart = 0;
      this.selectionEnd = 0;
   }

   public boolean method_25421() {
      return false;
   }

   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
      float windowWidth = (float)mc.method_22683().method_4486();
      float windowHeight = (float)mc.method_22683().method_4502();
      float guiWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(600.0F);
      float guiHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(400.0F);
      float guiX = windowWidth / 2.0F - guiWidth / 2.0F + this.guiOffsetX;
      float guiY = windowHeight / 2.0F - guiHeight / 2.0F + this.guiOffsetY;
      float sidebarWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(50.0F);
      float menuLeftPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
      float menuTopPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
      float menuX = guiX + sidebarWidth + menuLeftPadding;
      float menuY = guiY + menuTopPadding;
      float menuWidth = guiWidth - sidebarWidth - menuLeftPadding;
      float menuHeight = guiHeight - menuTopPadding;
      float lineOffset = Ke4708cf52c3b89f5ae.getInstance().scaledUi(120.0F);
      float lineTopPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(45.0F);
      float verticalLineX = menuX + menuWidth - lineOffset;
      float verticalLineY = menuY + lineTopPadding;
      float verticalLineHeight = menuHeight - lineTopPadding;
      float gapBetweenPanels = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
      float leftLineWidth;
      if (this.hideFriendPanel) {
         leftLineWidth = menuWidth;
      } else {
         leftLineWidth = verticalLineX - menuX - gapBetweenPanels;
      }

      float lineThickness = Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F);
      float bottomLineOffset = Ke4708cf52c3b89f5ae.getInstance().scaledUi(50.0F);
      float bottomLineY = verticalLineY + verticalLineHeight - bottomLineOffset;
      float chatAreaX = menuX + lineThickness + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
      float chatAreaY = verticalLineY + lineThickness + Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
      float chatAreaWidth = leftLineWidth - lineThickness * 2.0F - Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
      float chatAreaHeight = bottomLineY - verticalLineY - lineThickness - Ke4708cf52c3b89f5ae.getInstance().scaledUi(60.0F);
      if (mouseX >= (double)chatAreaX && mouseX <= (double)(chatAreaX + chatAreaWidth) && mouseY >= (double)chatAreaY && mouseY <= (double)(chatAreaY + chatAreaHeight)) {
         List<K8001222dc0dc25d83d> messages;
         if (this.friendChatMode && this.currentPrivateChat != null) {
            messages = K8ee912418ef60c15b7.getInstance().getPrivateMessages(this.currentPrivateChat);
         } else if (!this.friendChatMode) {
            messages = K8ee912418ef60c15b7.getInstance().getGlobalMessages();
         } else {
            messages = new ArrayList();
         }

         if (messages.size() > 0) {
            float totalContentHeight = 0.0F;
            float messageLineHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(11.0F);
            float bubblePaddingY = Ke4708cf52c3b89f5ae.getInstance().scaledUi(6.0F);
            float messagePadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
            float maxBubbleWidth = chatAreaWidth * 0.75F;
            float messageTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(9.0F);
            float bubblePaddingX = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);

            for(K8001222dc0dc25d83d msg : messages) {
               List<String> messageLines = new ArrayList();
               String messageText = msg.getMessage();
               String currentLine = "";

               for(int charIdx = 0; charIdx < messageText.length(); ++charIdx) {
                  String testLine = currentLine + messageText.charAt(charIdx);
                  float testWidth = K896065ea0a2bba4f47.SF_REGULAR.getWidth(testLine, messageTextSize);
                  if (testWidth > maxBubbleWidth - bubblePaddingX * 2.0F && !currentLine.isEmpty()) {
                     messageLines.add(currentLine);
                     currentLine = String.valueOf(messageText.charAt(charIdx));
                  } else {
                     currentLine = testLine;
                  }
               }

               if (!currentLine.isEmpty()) {
                  messageLines.add(currentLine);
               }

               float nicknameHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(11.0F);
               float msgHeight = nicknameHeight + (float)messageLines.size() * messageLineHeight + bubblePaddingY * 2.0F + messagePadding;
               totalContentHeight += msgHeight;
            }

            float maxScroll = Math.max(0.0F, totalContentHeight - chatAreaHeight);
            this.chatScrollOffset += (float)verticalAmount * Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
            this.chatScrollOffset = Math.max(0.0F, Math.min(this.chatScrollOffset, maxScroll));
            return true;
         }
      }

      if (this.friendChatMode) {
         float textY = bottomLineY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(293.0F);
         float ircTextY = textY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(43.0F);
         float friendsListStartY = ircTextY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(75.0F);
         float friendChatBottomY = verticalLineY + verticalLineHeight;
         if (mouseX >= (double)menuX && mouseX <= (double)(menuX + leftLineWidth) && mouseY >= (double)friendsListStartY && mouseY <= (double)friendChatBottomY) {
            List<K204c47a3480aa2d86b> friendsList = K8ee912418ef60c15b7.getInstance().getIRCFriends();
            float friendItemHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
            float friendItemSpacing = Ke4708cf52c3b89f5ae.getInstance().scaledUi(3.5F);
            float availableHeight = friendChatBottomY - friendsListStartY;
            int maxVisibleInChat = (int)Math.floor((double)(availableHeight / (friendItemHeight + friendItemSpacing)));
            if (friendsList.size() > maxVisibleInChat) {
               this.friendChatScrollOffset -= (float)verticalAmount;
               float maxScroll = (float)Math.max(0, friendsList.size() - maxVisibleInChat);
               this.friendChatScrollOffset = Math.max(0.0F, Math.min(this.friendChatScrollOffset, maxScroll));
               return true;
            }
         }
      }

      if (!this.hideFriendPanel && mouseX >= (double)verticalLineX && mouseX <= (double)(verticalLineX + lineOffset) && mouseY >= (double)verticalLineY && mouseY <= (double)(verticalLineY + verticalLineHeight)) {
         List<K204c47a3480aa2d86b> friends = K8ee912418ef60c15b7.getInstance().getIRCFriends();
         float friendEntryHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);
         float friendEntryPadding = Ke4708cf52c3b89f5ae.getInstance().scaledUi(5.0F);
         float friendListStartY = verticalLineY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(50.0F);
         float friendListAvailableHeight = verticalLineY + verticalLineHeight - friendListStartY;
         int maxVisibleFriends = (int)Math.floor((double)((friendListAvailableHeight + friendEntryPadding) / (friendEntryHeight + friendEntryPadding)));
         if (friends.size() > maxVisibleFriends) {
            this.rightPanelScrollOffset -= (float)verticalAmount;
            float maxScroll = (float)Math.max(0, friends.size() - maxVisibleFriends);
            this.rightPanelScrollOffset = Math.max(0.0F, Math.min(this.rightPanelScrollOffset, maxScroll));
            return true;
         }
      }

      return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
   }

   public void onSearchResults(String query, List<String> results) {
      if (query.equals(this.lastSearchQuery)) {
         this.searchResults = results;
         System.out.println("[IRC GUI] Получены результаты поиска: " + results.size() + " игроков");
      }

   }

   private void renderRulesWindow(class_4587 matrices, float windowWidth, float windowHeight, float alpha, int mouseX, int mouseY) {
      float rulesWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(500.0F);
      float rulesHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(350.0F);
      float rulesX = (windowWidth - rulesWidth) / 2.0F;
      float rulesY = (windowHeight - rulesHeight) / 2.0F;
      float rulesRadius = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
      K2b5e2c5f2b9891b450.RECT.draw(matrices, 0.0F, 0.0F, windowWidth, windowHeight, 0.0F, new Color(0, 0, 0, (int)(150.0F * alpha)));
      K2b5e2c5f2b9891b450.RECT.draw(matrices, rulesX - Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), rulesY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), rulesWidth + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), rulesHeight + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), rulesRadius + Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
      K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, rulesX, rulesY, rulesWidth, rulesHeight, rulesRadius, new Color(21, 21, 28, (int)(255.0F * alpha)));
      float titleSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(14.0F);
      float titleX = rulesX + Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
      float titleY = rulesY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
      K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, "Правила IRC Chat", titleX, titleY, titleSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
      String[] rules = new String[]{"• Оскорбление родных — мут.", "• Спам (3 и более одинаковых сообщений подряд) — мут.", "• Реклама серверов — мут.", "• Угрозы, травля и токсичное поведение — мут.", "• Выдача себя за администрацию — мут.", "• Обход наказания (использование другого аккаунта", "  после мута) — более строгое наказание.", "• Провокации и разжигание конфликтов — мут.", "• Использование ника или аватара с запрещённым", "  содержанием — требование изменить, при отказе — мут."};
      float ruleTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
      float ruleLineHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(18.0F);
      float rulesStartY = titleY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(40.0F);

      for(int i = 0; i < rules.length; ++i) {
         K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, rules[i], titleX, rulesStartY + (float)i * ruleLineHeight, ruleTextSize, new Color(200, 200, 200, (int)(255.0F * alpha)));
      }

      float buttonWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(200.0F);
      float buttonHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(35.0F);
      float buttonX = rulesX + (rulesWidth - buttonWidth) / 2.0F;
      float buttonY = rulesY + rulesHeight - buttonHeight - Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
      boolean hoverButton = (float)mouseX >= buttonX && (float)mouseX <= buttonX + buttonWidth && (float)mouseY >= buttonY && (float)mouseY <= buttonY + buttonHeight;
      K2b5e2c5f2b9891b450.RECT.draw(matrices, buttonX, buttonY, buttonWidth, buttonHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F), hoverButton ? new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)) : new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(200.0F * alpha)));
      String buttonText = "Я принимаю условия";
      float buttonTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(11.0F);
      float buttonTextWidth = K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(buttonText, buttonTextSize);
      float buttonTextX = buttonX + (buttonWidth - buttonTextWidth) / 2.0F;
      float buttonTextY = buttonY + (buttonHeight - buttonTextSize) / 2.0F;
      K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, buttonText, buttonTextX, buttonTextY, buttonTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
   }

   private void renderFriendRequestWindow(class_4587 matrices, float windowWidth, float windowHeight, float alpha, int mouseX, int mouseY) {
      float windowW = Ke4708cf52c3b89f5ae.getInstance().scaledUi(350.0F);
      float windowH = Ke4708cf52c3b89f5ae.getInstance().scaledUi(150.0F);
      float windowX = (windowWidth - windowW) / 2.0F;
      float windowY = (windowHeight - windowH) / 2.0F;
      float radius = Ke4708cf52c3b89f5ae.getInstance().scaledUi(12.0F);
      K2b5e2c5f2b9891b450.RECT.draw(matrices, 0.0F, 0.0F, windowWidth, windowHeight, 0.0F, new Color(0, 0, 0, (int)(150.0F * alpha)));
      K2b5e2c5f2b9891b450.RECT.draw(matrices, windowX - Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), windowY - Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), windowW + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), windowH + Ke4708cf52c3b89f5ae.getInstance().scaledUi(2.0F), radius + Ke4708cf52c3b89f5ae.getInstance().scaledUi(1.0F), new Color(this.selectedThemeColor.getRed(), this.selectedThemeColor.getGreen(), this.selectedThemeColor.getBlue(), (int)(255.0F * alpha)));
      K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, windowX, windowY, windowW, windowH, radius, new Color(21, 21, 28, (int)(255.0F * alpha)));
      float titleSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(13.0F);
      float titleY = windowY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
      String titleText = "Запрос в друзья";
      float titleWidth = K896065ea0a2bba4f47.SF_BOLD.getWidth(titleText, titleSize);
      float titleX = windowX + (windowW - titleWidth) / 2.0F;
      K896065ea0a2bba4f47.SF_BOLD.drawText(matrices, titleText, titleX, titleY, titleSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
      float messageSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(11.0F);
      float messageY = titleY + Ke4708cf52c3b89f5ae.getInstance().scaledUi(35.0F);
      String messageText = this.friendRequestFromUsername + " хочет добавить вас в друзья";
      float messageWidth = K896065ea0a2bba4f47.SF_REGULAR.getWidth(messageText, messageSize);
      float messageX = windowX + (windowW - messageWidth) / 2.0F;
      K896065ea0a2bba4f47.SF_REGULAR.drawText(matrices, messageText, messageX, messageY, messageSize, new Color(200, 200, 200, (int)(255.0F * alpha)));
      float buttonWidth = Ke4708cf52c3b89f5ae.getInstance().scaledUi(120.0F);
      float buttonHeight = Ke4708cf52c3b89f5ae.getInstance().scaledUi(32.0F);
      float buttonGap = Ke4708cf52c3b89f5ae.getInstance().scaledUi(15.0F);
      float buttonsY = windowY + windowH - buttonHeight - Ke4708cf52c3b89f5ae.getInstance().scaledUi(20.0F);
      float acceptButtonX = windowX + windowW / 2.0F - buttonWidth - buttonGap / 2.0F;
      float declineButtonX = windowX + windowW / 2.0F + buttonGap / 2.0F;
      boolean hoverAccept = (float)mouseX >= acceptButtonX && (float)mouseX <= acceptButtonX + buttonWidth && (float)mouseY >= buttonsY && (float)mouseY <= buttonsY + buttonHeight;
      boolean hoverDecline = (float)mouseX >= declineButtonX && (float)mouseX <= declineButtonX + buttonWidth && (float)mouseY >= buttonsY && (float)mouseY <= buttonsY + buttonHeight;
      K2b5e2c5f2b9891b450.RECT.draw(matrices, acceptButtonX, buttonsY, buttonWidth, buttonHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F), hoverAccept ? new Color(50, 200, 50, (int)(255.0F * alpha)) : new Color(40, 180, 40, (int)(200.0F * alpha)));
      String acceptText = "Принять";
      float acceptTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
      float acceptTextWidth = K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(acceptText, acceptTextSize);
      float acceptTextX = acceptButtonX + (buttonWidth - acceptTextWidth) / 2.0F;
      float acceptTextY = buttonsY + (buttonHeight - acceptTextSize) / 2.0F;
      K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, acceptText, acceptTextX, acceptTextY, acceptTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
      K2b5e2c5f2b9891b450.RECT.draw(matrices, declineButtonX, buttonsY, buttonWidth, buttonHeight, Ke4708cf52c3b89f5ae.getInstance().scaledUi(8.0F), hoverDecline ? new Color(200, 50, 50, (int)(255.0F * alpha)) : new Color(180, 40, 40, (int)(200.0F * alpha)));
      String declineText = "Отклонить";
      float declineTextSize = Ke4708cf52c3b89f5ae.getInstance().scaledUi(10.0F);
      float declineTextWidth = K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(declineText, declineTextSize);
      float declineTextX = declineButtonX + (buttonWidth - declineTextWidth) / 2.0F;
      float declineTextY = buttonsY + (buttonHeight - declineTextSize) / 2.0F;
      K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, declineText, declineTextX, declineTextY, declineTextSize, new Color(255, 255, 255, (int)(255.0F * alpha)));
   }
}
