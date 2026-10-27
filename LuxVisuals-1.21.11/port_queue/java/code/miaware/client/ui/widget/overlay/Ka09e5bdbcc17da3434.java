package code.miaware.client.ui.widget.overlay;

import code.miaware.api.system.backend.K93d0ef925a2fcdf0d6;
import code.miaware.api.utils.combat.Ka9db51464bdacb7bf5;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.api.utils.other.Kd10bcb33e37f629df6;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import code.miaware.client.ui.widget.K5e6ae649a852530a89;
import code.miaware.client.ui.widget.K8c553198855f5f0c73;
import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import code.miaware.client.ui.widget.Kd80e03babf074b8b76;
import code.miaware.client.ui.widget.Kdeecdbb204319837a4;
import code.miaware.client.ui.widget.Ke7efbae1920d8c2967;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_640;

public class Ka09e5bdbcc17da3434 extends K548ca8198549ddf9d7 {
   private static final class_2960 LOGO = class_2960.method_60655("luxvisuals", "images/interface/crown_logo.png");
   private static final float LOGO_ASPECT = 1.946768F;
   private boolean showLogo = true;
   private boolean showFps = true;
   private boolean showNickname = true;
   private boolean showTime = true;
   private boolean showPing = true;
   private long nextClockUpdate;
   private String clockText = "";
   private static final DateTimeFormatter CLOCK_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

   public Ka09e5bdbcc17da3434() {
      super(3.0F, 3.0F);
      this.setEnabled(true);
   }

   public String getName() {
      return "Watermark";
   }

   protected boolean hasSettingsMenu() {
      return true;
   }

   protected String getSettingsMenuTitle() {
      return "lux visuals Watermark";
   }

   private K81d5beb79596ea4544 Kd0cbf3b4a7bf313132() {
      for(K548ca8198549ddf9d7 widget : K5e6ae649a852530a89.getInstance().getWidgets()) {
         if (widget instanceof K81d5beb79596ea4544 logo) {
            return logo;
         }
      }

      return null;
   }

   private Ka747e2877c632013b5 K62eb316bb655463932() {
      for(K548ca8198549ddf9d7 widget : K5e6ae649a852530a89.getInstance().getWidgets()) {
         if (widget instanceof Ka747e2877c632013b5 info) {
            return info;
         }
      }

      return null;
   }

   protected List<Ke7efbae1920d8c2967> getSettingsMenuEntries() {
      List<Ke7efbae1920d8c2967> entries = new ArrayList();
      K81d5beb79596ea4544 logo = this.Kd0cbf3b4a7bf313132();
      if (logo != null) {
         entries.add(Ke7efbae1920d8c2967.of("Показать лого", "Показывать логотип клиента", () -> this.showLogo && logo.isEnabled(), (value) -> {
            this.showLogo = value;
            logo.setEnabled(value);
         }));
      }

      entries.add(Ke7efbae1920d8c2967.of("Показать ник", "Показывать ник игрока", () -> this.showNickname, (value) -> this.showNickname = value));
      entries.add(Ke7efbae1920d8c2967.of("Show FPS", "Real client frame rate", () -> this.showFps, (value) -> this.showFps = value));
      entries.add(Ke7efbae1920d8c2967.of("Show ping", "Ping to the server in ms", () -> this.showPing, (value) -> this.showPing = value));
      entries.add(Ke7efbae1920d8c2967.of("Показать время", "Показывать текущее время", () -> this.showTime, (value) -> this.showTime = value));
      Ka747e2877c632013b5 serverInfo = this.K62eb316bb655463932();
      if (serverInfo != null) {
         Objects.requireNonNull(serverInfo);
         K8c553198855f5f0c73 var10003 = serverInfo::isShowTps;
         Objects.requireNonNull(serverInfo);
         entries.add(Ke7efbae1920d8c2967.of("Show TPS", "Real server TPS sync", var10003, serverInfo::setShowTps));
         Objects.requireNonNull(serverInfo);
         var10003 = serverInfo::isShowServer;
         Objects.requireNonNull(serverInfo);
         entries.add(Ke7efbae1920d8c2967.of("Show server", "Server IP or singleplayer label", var10003, serverInfo::setShowServer));
      }

      return entries;
   }

   public void render(class_4587 matrixStack) {
      if (super.isEnabled() && mc.field_1724 != null) {
         this.updateSettingsMenuState();
         Kdeecdbb204319837a4 m = this.pillMetrics();
         float gap = this.pillGap();
         float x = this.getDraggable().getX();
         float y = this.getDraggable().getY();
         float cx = x;
         K81d5beb79596ea4544 logo = this.Kd0cbf3b4a7bf313132();
         if (this.showLogo && logo != null && logo.isEnabled()) {
            cx = x + this.Kf58d64402bba5b5758(matrixStack, x, y, m) + gap;
         }

         List<Kd80e03babf074b8b76> main = this.Kdec0c077bcc245206f();
         if (!main.isEmpty()) {
            cx += Ka20f8cfdbc113ccdec.pill(matrixStack, cx, y, main, m, 1.0F) + gap;
         }

         if (this.showTime) {
            List<Kd80e03babf074b8b76> time = List.of((new Kd80e03babf074b8b76("clock", Ka20f8cfdbc113ccdec.ACCENT)).add(this.K5f552f46f74a063fa0(), Ka20f8cfdbc113ccdec.TEXT));
            cx += Ka20f8cfdbc113ccdec.pill(matrixStack, cx, y, time, m, 1.0F) + gap;
         }

         Ka747e2877c632013b5 serverInfo = this.K62eb316bb655463932();
         if (serverInfo != null && serverInfo.isEnabled() && serverInfo.isShowServer()) {
            List<Kd80e03babf074b8b76> server = List.of((new Kd80e03babf074b8b76("globe", Ka20f8cfdbc113ccdec.ACCENT)).add(serverInfo.serverLabel(), Ka20f8cfdbc113ccdec.TEXT));
            cx += Ka20f8cfdbc113ccdec.pill(matrixStack, cx, y, server, m, 1.0F) + gap;
         }

         float width = Math.max(0.0F, cx - x - gap);
         this.getDraggable().setWidth(width);
         this.getDraggable().setHeight(width > 0.0F ? m.height() : 0.0F);
         this.setSettingsMenuAnchor(x, y, width, m.height());
         this.renderSettingsMenu(matrixStack);
      } else {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
         this.closeSettingsMenu();
      }
   }

   private float Kf58d64402bba5b5758(class_4587 ms, float x, float y, Kdeecdbb204319837a4 m) {
      float logoH = m.iconSize() * 0.92F;
      float logoW = logoH * 1.946768F;
      String name = "LuxVisuals";
      float textW = Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), name, m.textSize() * 1.08F);
      float w = m.padX() * 2.0F + logoW + m.iconGap() + textW;
      Ka20f8cfdbc113ccdec.panel(ms, x, y, w, m.height(), m.radius(), 1.0F);
      float cy = y + m.height() / 2.0F;
      float lx = x + m.padX();

      try {
         int texture = mc.method_1531().method_4619(LOGO).method_4624();
         K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(ms, lx, cy - logoH / 2.0F, logoW, logoH, 0.0F, Ka20f8cfdbc113ccdec.ACCENT, 0.0F, 0.0F, 1.0F, 1.0F, texture);
      } catch (Throwable var13) {
      }

      Ka20f8cfdbc113ccdec.text(ms, Ka20f8cfdbc113ccdec.semibold(), name, lx + logoW + m.iconGap(), cy, m.textSize() * 1.08F, Ka20f8cfdbc113ccdec.ACCENT);
      return w;
   }

   private List<Kd80e03babf074b8b76> Kdec0c077bcc245206f() {
      List<Kd80e03babf074b8b76> segments = new ArrayList();
      if (this.showNickname) {
         segments.add((new Kd80e03babf074b8b76("user", Ka20f8cfdbc113ccdec.ACCENT)).add(Kd10bcb33e37f629df6.protectedString(K93d0ef925a2fcdf0d6.getUsername()), Ka20f8cfdbc113ccdec.TEXT));
      }

      if (this.showFps) {
         segments.add((new Kd80e03babf074b8b76("pulse", Ka20f8cfdbc113ccdec.ACCENT)).add(String.valueOf(mc.method_47599()), Ka20f8cfdbc113ccdec.TEXT).add(" fps", Ka20f8cfdbc113ccdec.ACCENT_SOFT));
      }

      if (this.showPing) {
         segments.add((new Kd80e03babf074b8b76("wifi", Ka20f8cfdbc113ccdec.ACCENT)).add(String.valueOf(this.K7863ec90349435832a()), Ka20f8cfdbc113ccdec.TEXT).add(" ms", Ka20f8cfdbc113ccdec.ACCENT_SOFT));
      }

      Ka747e2877c632013b5 serverInfo = this.K62eb316bb655463932();
      if (serverInfo != null && serverInfo.isEnabled() && serverInfo.isShowTps()) {
         String tps = Ka0484a23398fb5f9ac.formatStepValue(Ka9db51464bdacb7bf5.getInstance().getCurrentTps(), 0.1F);
         segments.add((new Kd80e03babf074b8b76("tps", Ka20f8cfdbc113ccdec.ACCENT)).add(tps, Ka20f8cfdbc113ccdec.TEXT).add(" tps", Ka20f8cfdbc113ccdec.ACCENT_SOFT));
      }

      return segments;
   }

   private int K7863ec90349435832a() {
      try {
         if (mc.method_1562() != null && mc.field_1724 != null) {
            class_640 entry = mc.method_1562().method_2871(mc.field_1724.method_5667());
            return entry != null ? Math.max(0, entry.method_2959()) : 0;
         } else {
            return 0;
         }
      } catch (Throwable var2) {
         return 0;
      }
   }

   private String K5f552f46f74a063fa0() {
      long now = System.currentTimeMillis();
      if (now >= this.nextClockUpdate || now < this.nextClockUpdate - 1000L) {
         this.clockText = LocalTime.now().format(CLOCK_FORMAT);
         this.nextClockUpdate = now + 1000L;
      }

      return this.clockText;
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.render(context.method_51448());
   }

   public void mouseClicked(double mouseX, double mouseY, int button) {
      if (button == 0 && this.getDraggable().isHovering()) {
         this.getDraggable().onClick(button);
      } else {
         this.handleSettingsMenuClick(mouseX, mouseY, button);
      }
   }

   public void handleServerInfoSettingsClick(double mouseX, double mouseY, int button) {
      this.handleSettingsMenuClick(mouseX, mouseY, button);
   }

   public void mouseReleased(double mouseX, double mouseY, int button) {
      this.handleSettingsMenuRelease(mouseX, mouseY, button);
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
   }

   public void mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
   }
}
