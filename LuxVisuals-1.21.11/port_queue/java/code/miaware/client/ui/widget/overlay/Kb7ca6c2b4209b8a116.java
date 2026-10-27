package code.miaware.client.ui.widget.overlay;

import code.miaware.api.event.events.render.Kb590f148cfda70c907;
import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import code.miaware.client.ui.widget.K5af30bf5e82a937089;
import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import code.miaware.client.ui.widget.Kd80e03babf074b8b76;
import code.miaware.client.ui.widget.Kdeecdbb204319837a4;
import code.miaware.client.ui.widget.Ke7efbae1920d8c2967;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;
import lombok.Generated;
import net.minecraft.class_1799;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public abstract class Kb7ca6c2b4209b8a116 extends K548ca8198549ddf9d7 {
   private static final Pattern FORMATTING_CODE = Pattern.compile("(?i)§.");
   private static Kb7ca6c2b4209b8a116 instance;
   private final List<Ka2d652bed71429059a> notifications = new CopyOnWriteArrayList();
   private long lastDuraCheck;
   private String lastLowDurabilityItem = "";
   private boolean defaultAnchorApplied;
   private boolean moduleState = true;
   private boolean potionAlerts = true;
   private boolean lowDurability;

   public Kb7ca6c2b4209b8a116() {
      super(0.0F, 0.0F);
      instance = this;
      this.setEnabled(true);
   }

   public static boolean isModuleNotificationsEnabled() {
      return instance != null && instance.moduleState;
   }

   public static boolean isPotionAlertsEnabled() {
      return instance != null && instance.potionAlerts;
   }

   public void addNotif(String text) {
      this.addNotif(text, Ka58c9e2c4d8aa9af2e.INFO);
   }

   public void addNotif(String text, Ka58c9e2c4d8aa9af2e type) {
      this.notifications.add(new Ka2d652bed71429059a(text, type));
   }

   public static void notifyModule(String moduleName, boolean enabled) {
      if (instance != null) {
         Ka2d652bed71429059a n = new Ka2d652bed71429059a(moduleName, enabled ? Ka58c9e2c4d8aa9af2e.SUCCESS : Ka58c9e2c4d8aa9af2e.INFO);
         n.module = true;
         n.enabled = enabled;
         instance.notifications.add(n);
      }

   }

   public static void notify(String text, Ka58c9e2c4d8aa9af2e type) {
      if (instance != null) {
         instance.addNotif(text, type);
      }

   }

   public String getName() {
      return "Notifications";
   }

   protected boolean hasSettingsMenu() {
      return true;
   }

   protected String getSettingsMenuTitle() {
      return "lux visuals Notifications";
   }

   protected List<Ke7efbae1920d8c2967> getSettingsMenuEntries() {
      return List.of(Ke7efbae1920d8c2967.of("Module state", "Show toggle notifications from modules", () -> this.moduleState, (value) -> this.moduleState = value), Ke7efbae1920d8c2967.of("Potion alerts", "Warn before important effects end", () -> this.potionAlerts, (value) -> this.potionAlerts = value), Ke7efbae1920d8c2967.of("Low durability", "Warn when held items get low", () -> this.lowDurability, (value) -> this.lowDurability = value));
   }

   public void render(Kb590f148cfda70c907 event) {
      class_4587 matrices = event.matrixStack();
      this.notifications.removeIf(Ka2d652bed71429059a::Kf6c7ec8e283c92e34e);
      if (this.lowDurability && System.currentTimeMillis() - this.lastDuraCheck > 5000L) {
         this.K2dec2aa7e7c73fd4a6();
         this.lastDuraCheck = System.currentTimeMillis();
      }

      if (!this.hudLayoutMode()) {
         this.closeSettingsMenu();
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
         this.Ka2331231397acd475b(matrices);
      } else {
         this.updateSettingsMenuState();
         this.K7cc504b0f0681ca7e5(matrices);
         this.Ka2331231397acd475b(matrices);
      }
   }

   private void K7cc504b0f0681ca7e5(class_4587 matrices) {
      Kdeecdbb204319837a4 m = this.pillMetrics();
      List<Kd80e03babf074b8b76> segments = List.of((new Kd80e03babf074b8b76("bell", Ka20f8cfdbc113ccdec.ACCENT)).add(Ka35e7d6b31db9984f1.getInstance().translate("Notifications"), Ka20f8cfdbc113ccdec.TEXT));
      float width = Ka20f8cfdbc113ccdec.pillWidth(segments, m);
      float height = m.height();
      this.Kd7d9fa5070a492d90f(width, height);
      float x = this.getDraggable().getX();
      float y = this.getDraggable().getY();
      Ka20f8cfdbc113ccdec.pill(matrices, x, y, segments, m, 1.0F);
      this.setSettingsMenuAnchor(x, y, width, height);
      this.renderSettingsMenu(matrices);
      this.getDraggable().setWidth(width);
      this.getDraggable().setHeight(height);
   }

   private void Ka2331231397acd475b(class_4587 matrices) {
      Kdeecdbb204319837a4 m = this.pillMetrics();
      float previewWidth = this.scaled(76.0F);
      float previewHeight = m.height();
      this.Kd7d9fa5070a492d90f(previewWidth, previewHeight);
      float anchorWidth = Math.max(this.getDraggable().getWidth(), previewWidth);
      float centerX = this.getDraggable().getX() + anchorWidth / 2.0F;
      float anchorBottom = this.getDraggable().getY() + Math.max(this.getDraggable().getHeight(), previewHeight);
      if (this.isSettingsMenuVisible()) {
         anchorBottom = Math.max(anchorBottom, this.getSettingsMenuBottom());
      }

      float currentTargetY = anchorBottom + this.scaled(8.0F);

      for(Ka2d652bed71429059a notification : this.notifications) {
         float visibility = notification.K84689c8c5788020351();
         if (!(visibility <= 0.03F)) {
            if (!notification.positionInitialized) {
               notification.visualY = currentTargetY;
               notification.positionInitialized = true;
            } else {
               notification.visualY = Ka0484a23398fb5f9ac.interpolate(notification.visualY, currentTargetY, 0.18F);
            }

            float y = notification.visualY;
            float slide = this.scaled(14.0F) * (1.0F - visibility);
            if (notification.module) {
               this.K07aeb3e1ecc1a396bf(matrices, notification, centerX + slide, y, m, visibility);
            } else {
               List<Kd80e03babf074b8b76> segments = List.of((new Kd80e03babf074b8b76("bell", notification.type.accent())).add(K1170298ca934ffa59e(notification.text), Ka20f8cfdbc113ccdec.TEXT));
               float width = Ka20f8cfdbc113ccdec.pillWidth(segments, m);
               Ka20f8cfdbc113ccdec.pill(matrices, centerX - width / 2.0F + slide, y, segments, m, visibility);
            }

            currentTargetY += (m.height() + this.scaled(4.5F)) * visibility;
         }
      }

   }

   private void K07aeb3e1ecc1a396bf(class_4587 ms, Ka2d652bed71429059a n, float centerX, float y, Kdeecdbb204319837a4 m, float alpha) {
      boolean ru = Ka35e7d6b31db9984f1.getInstance().isRussian();
      String prefix = ru ? "Функция " : "Module ";
      String name = K1170298ca934ffa59e(Ka35e7d6b31db9984f1.getInstance().translateFeature(n.text));
      String suffix = ru ? (n.enabled ? " активирована" : " деактивирована") : (n.enabled ? " enabled" : " disabled");
      List<K5af30bf5e82a937089> parts = List.of(K5af30bf5e82a937089.of(prefix, Ka20f8cfdbc113ccdec.TEXT), K5af30bf5e82a937089.bold(name, Ka20f8cfdbc113ccdec.ACCENT_SOFT), K5af30bf5e82a937089.of(suffix, Ka20f8cfdbc113ccdec.TEXT));
      float trackW = m.iconSize() * 1.75F;
      float trackH = m.iconSize();
      float width = m.padX() * 2.0F + trackW + m.iconGap() * 1.4F + Ka20f8cfdbc113ccdec.partsWidth(parts, m.textSize());
      float x = centerX - width / 2.0F;
      Ka20f8cfdbc113ccdec.panel(ms, x, y, width, m.height(), m.radius(), alpha);
      float cy = y + m.height() / 2.0F;
      float tx = x + m.padX();
      n.knob = Ka0484a23398fb5f9ac.interpolate(n.knob, n.enabled ? 1.0F : 0.0F, 0.2F);
      Ka20f8cfdbc113ccdec.rect(ms, tx, cy - trackH / 2.0F, trackW, trackH, trackH / 2.0F, Ka20f8cfdbc113ccdec.a(n.enabled ? Ka20f8cfdbc113ccdec.ACCENT : Ka20f8cfdbc113ccdec.DIVIDER, alpha));
      float knob = trackH - this.scaled(2.4F);
      float kx = tx + this.scaled(1.2F) + (trackW - knob - this.scaled(2.4F)) * n.knob;
      Ka20f8cfdbc113ccdec.rect(ms, kx, cy - knob / 2.0F, knob, knob, knob / 2.0F, Ka20f8cfdbc113ccdec.a(n.enabled ? Ka20f8cfdbc113ccdec.BG : Ka20f8cfdbc113ccdec.TEXT, alpha));
      Ka20f8cfdbc113ccdec.drawParts(ms, parts, tx + trackW + m.iconGap() * 1.4F, cy, m.textSize(), alpha);
   }

   private void Kd7d9fa5070a492d90f(float width, float height) {
      if (!this.defaultAnchorApplied && mc != null && mc.method_22683() != null) {
         if (!(Math.abs(this.getDraggable().getX()) > 0.01F) && !(Math.abs(this.getDraggable().getY()) > 0.01F)) {
            this.getDraggable().setX((float)mc.method_22683().method_4486() / 2.0F - width / 2.0F);
            this.getDraggable().setY((float)mc.method_22683().method_4502() / 2.0F + this.scaled(18.0F));
            this.defaultAnchorApplied = true;
         } else {
            this.defaultAnchorApplied = true;
         }
      }
   }

   private void K2dec2aa7e7c73fd4a6() {
      if (mc.field_1724 != null) {
         for(class_1799 stack : mc.field_1724.method_31548().field_7547) {
            if (!stack.method_7960() && stack.method_7963()) {
               int durabilityLeft = stack.method_7936() - stack.method_7919();
               if (durabilityLeft > 0 && durabilityLeft < 100) {
                  String itemName = K1170298ca934ffa59e(stack.method_7964().getString());
                  if (!itemName.equals(this.lastLowDurabilityItem)) {
                     this.lastLowDurabilityItem = itemName;
                     this.addNotif("Low durability: " + itemName, Ka58c9e2c4d8aa9af2e.ERROR);
                  }

                  return;
               }
            }
         }

         this.lastLowDurabilityItem = "";
      }
   }

   private static String K1170298ca934ffa59e(String text) {
      return text == null ? "" : FORMATTING_CODE.matcher(text).replaceAll("").replace(" ", " ").trim();
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.render(context.method_51448());
   }

   public void render(class_4587 matrixStack) {
   }

   public void mouseClicked(double mouseX, double mouseY, int button) {
      this.handleSettingsMenuClick(mouseX, mouseY, button);
   }

   public void mouseReleased(double mouseX, double mouseY, int button) {
      this.handleSettingsMenuRelease(mouseX, mouseY, button);
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
   }

   public void mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
   }

   @Generated
   public static Kb7ca6c2b4209b8a116 getInstance() {
      return instance;
   }
}
