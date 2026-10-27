package code.miaware.client.ui.widget.overlay;

import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import code.miaware.client.ui.widget.Ke7efbae1920d8c2967;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_642;

public class Ka747e2877c632013b5 extends K548ca8198549ddf9d7 {
   private static final String SINGLEPLAYER_LABEL = "Singleplayer";
   private static final String UNKNOWN_SERVER = "Unknown";
   private boolean showTps = true;
   private boolean showServer = true;

   public boolean isShowTps() {
      return this.showTps;
   }

   public void setShowTps(boolean value) {
      this.showTps = value;
   }

   public boolean isShowServer() {
      return this.showServer;
   }

   public void setShowServer(boolean value) {
      this.showServer = value;
   }

   public Ka747e2877c632013b5() {
      super(3.0F, 30.0F);
      this.setEnabled(true);
   }

   public String getName() {
      return "ServerInfo";
   }

   protected boolean hasSettingsMenu() {
      return false;
   }

   protected String getSettingsMenuTitle() {
      return "Server Info";
   }

   protected List<Ke7efbae1920d8c2967> getSettingsMenuEntries() {
      return List.of(Ke7efbae1920d8c2967.of("Show TPS", "Real server TPS sync", () -> this.showTps, (value) -> this.showTps = value), Ke7efbae1920d8c2967.of("Show server", "Server IP or singleplayer label", () -> this.showServer, (value) -> this.showServer = value));
   }

   public void render(class_4587 matrixStack) {
      this.getDraggable().setWidth(0.0F);
      this.getDraggable().setHeight(0.0F);
   }

   public String serverLabel() {
      return this.K54ae600b0677a21528();
   }

   private String K54ae600b0677a21528() {
      if (mc.method_1542()) {
         return "Singleplayer";
      } else {
         try {
            class_642 serverInfo = mc.method_1558();
            if (serverInfo != null && serverInfo.field_3761 != null && !serverInfo.field_3761.isBlank()) {
               return this.K6d51febdcda71d9e1a(serverInfo.field_3761);
            }
         } catch (Throwable var5) {
         }

         try {
            if (mc.method_1562() != null && mc.method_1562().method_48296() != null) {
               SocketAddress address = mc.method_1562().method_48296().method_10755();
               if (address instanceof InetSocketAddress) {
                  InetSocketAddress inetSocketAddress = (InetSocketAddress)address;
                  String host = inetSocketAddress.getHostString();
                  if (host != null && !host.isBlank()) {
                     return host;
                  }
               } else if (address != null) {
                  return this.K6d51febdcda71d9e1a(address.toString());
               }
            }
         } catch (Throwable var4) {
         }

         return "Unknown";
      }
   }

   private String K6d51febdcda71d9e1a(String address) {
      if (address != null && !address.isBlank()) {
         String normalized = address.trim().replace("<unresolved>", "").trim();
         if (normalized.contains("/")) {
            String[] split = normalized.split("/", 2);
            String left = split.length > 0 ? split[0].trim() : "";
            String right = split.length > 1 ? split[1].trim() : "";
            normalized = !left.isBlank() ? left : right;
         }

         if (normalized.startsWith("[") && normalized.contains("]:")) {
            int end = normalized.indexOf(93);
            if (end > 0) {
               return normalized.substring(0, end + 1);
            }
         }

         long colonCount = normalized.chars().filter((ch) -> ch == 58).count();
         if (colonCount == 1L) {
            int colonIndex = normalized.lastIndexOf(58);
            if (colonIndex > 0) {
               normalized = normalized.substring(0, colonIndex);
            }
         }

         return normalized.isBlank() ? "Unknown" : normalized;
      } else {
         return "Unknown";
      }
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.render(context.method_51448());
   }

   public void mouseClicked(double mouseX, double mouseY, int button) {
   }

   public void mouseReleased(double mouseX, double mouseY, int button) {
      this.handleSettingsMenuRelease(mouseX, mouseY, button);
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
   }

   public void mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
   }

   public void renderEditorOverlay(class_4587 matrixStack) {
   }
}
