package code.miaware.client.ui.widget.overlay;

import code.miaware.api.system.backend.K04bfb908d9b141e27c;
import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import code.miaware.client.ui.widget.Kd80e03babf074b8b76;
import code.miaware.client.ui.widget.Kdeecdbb204319837a4;
import java.awt.Color;
import java.util.List;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class K24c96eb9c47ca05ca8 extends K548ca8198549ddf9d7 {
   public K24c96eb9c47ca05ca8() {
      super(3.0F, 80.0F);
      this.setEnabled(true);
   }

   public String getName() {
      return "Role";
   }

   protected boolean hasSettingsMenu() {
      return false;
   }

   public void render(class_4587 matrixStack) {
      if (!super.isEnabled()) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      } else if (mc.field_1724 == null) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      } else {
         this.K5044014393db6e4679(matrixStack);
      }
   }

   private void K5044014393db6e4679(class_4587 matrixStack) {
      float x = this.getDraggable().getX();
      float y = this.getDraggable().getY();
      String role = K04bfb908d9b141e27c.getInstance().getRole();
      if (role == null || role.isBlank()) {
         role = "User";
      }

      Kdeecdbb204319837a4 m = this.pillMetrics();
      List<Kd80e03babf074b8b76> segments = List.of((new Kd80e03babf074b8b76("clickgui/crown", Ka20f8cfdbc113ccdec.ACCENT)).add(role, this.K505815aa4f9a30b708(role)));
      float width = Ka20f8cfdbc113ccdec.pill(matrixStack, x, y, segments, m, 1.0F);
      this.getDraggable().setWidth(width);
      this.getDraggable().setHeight(m.height());
   }

   private Color K505815aa4f9a30b708(String role) {
      Color var10000;
      switch (role) {
         case "Developer" -> var10000 = Ka20f8cfdbc113ccdec.WARM;
         case "Tester" -> var10000 = new Color(255, 232, 140);
         case "YouTube" -> var10000 = Ka20f8cfdbc113ccdec.RED;
         case "Friend" -> var10000 = Ka20f8cfdbc113ccdec.GREEN;
         default -> var10000 = Ka20f8cfdbc113ccdec.TEXT;
      }

      return var10000;
   }

   private String K5bcd8ad7c313bc24ba(String role) {
      switch (role) {
         case "Developer" -> {
            return "A";
         }
         case "Friend" -> {
            return "C";
         }
         case "User" -> {
            return "H";
         }
         case "YouTube" -> {
            return "I";
         }
         case "TikTok" -> {
            return "G";
         }
         case "Tester" -> {
            return "D";
         }
         default -> {
            return "H";
         }
      }
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.render(context.method_51448());
   }

   public void mouseClicked(double mouseX, double mouseY, int button) {
      if (button == 0) {
         this.getDraggable().onClick(button);
      }

   }

   public void mouseReleased(double mouseX, double mouseY, int button) {
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
   }

   public void mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
   }
}
