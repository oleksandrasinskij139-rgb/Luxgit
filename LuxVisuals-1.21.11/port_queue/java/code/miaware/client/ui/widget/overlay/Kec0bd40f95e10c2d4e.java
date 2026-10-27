package code.miaware.client.ui.widget.overlay;

import code.miaware.api.module.K5c460ed4a42f0797d6;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.system.backend.Kd9271a319f911decbe;
import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.client.features.modules.render.K30b126617e809f138b;
import code.miaware.client.ui.widget.K424a42176df1675858;
import code.miaware.client.ui.widget.K5af30bf5e82a937089;
import code.miaware.client.ui.widget.K71a43541bec306e7a2;
import code.miaware.client.ui.widget.K8974f150cc9dad5e95;
import code.miaware.client.ui.widget.K9c25dd69cb23ed0eb8;
import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class Kec0bd40f95e10c2d4e extends K424a42176df1675858 {
   private final Ka91715a8c4e3f3b7c4 showAnimation = new Ka91715a8c4e3f3b7c4();
   private boolean lastPreview;

   public Kec0bd40f95e10c2d4e() {
      super(3.0F, 120.0F);
   }

   public String getName() {
      return "Keybinds";
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.render(context.method_51448());
   }

   public void render(class_4587 matrixStack) {
      if (!super.isEnabled()) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      } else {
         Map<String, K8974f150cc9dad5e95> data = this.getCurrentData();
         boolean hasData = !data.isEmpty();
         boolean preview = this.hudLayoutMode();
         this.showAnimation.update();
         if (!preview && this.lastPreview && !hasData) {
            this.showAnimation.setValue((double)0.0F);
         } else {
            this.showAnimation.run(!hasData && !preview ? (double)0.0F : (double)1.0F, 220L, Kf3b62ecfba0c3544e6.QUINT_OUT);
         }

         this.lastPreview = preview;
         float anim = (float)this.showAnimation.getValue();
         if (anim <= 0.01F) {
            this.getDraggable().setWidth(0.0F);
            this.getDraggable().setHeight(0.0F);
         } else {
            float x = this.getDraggable().getX();
            float y = this.getDraggable().getY();
            List<K71a43541bec306e7a2> rows = new ArrayList();

            for(Kab8a8c478f7d0077cd module : K5c460ed4a42f0797d6.getInstance().getModules()) {
               if (!(module instanceof K30b126617e809f138b) && module.isEnabled() && module.hasBind()) {
                  rows.add((new K71a43541bec306e7a2()).badge(Kf85ad50100f81dbfbd(module.getCategory())).left(module.getDisplayName(), Ka20f8cfdbc113ccdec.TEXT).rightBold(Ka35e7d6b31db9984f1.getInstance().translate(Kd9271a319f911decbe.getBind(module.getBind())), Ka20f8cfdbc113ccdec.ACCENT_SOFT));
               }
            }

            rows.sort(Comparator.comparing((r) -> ((K5af30bf5e82a937089)r.left.get(0)).text()));
            if (rows.isEmpty()) {
               rows.add((new K71a43541bec306e7a2()).badge("clickgui/render").left("Interface", Ka20f8cfdbc113ccdec.TEXT).rightBold("R", Ka20f8cfdbc113ccdec.ACCENT_SOFT));
               rows.add((new K71a43541bec306e7a2()).badge("clickgui/movement").left("Sprint", Ka20f8cfdbc113ccdec.TEXT).rightBold("V", Ka20f8cfdbc113ccdec.ACCENT_SOFT));
            }

            String title = Ka35e7d6b31db9984f1.getInstance().translate("Binds");
            K9c25dd69cb23ed0eb8 m = this.listMetrics();
            float w = Ka20f8cfdbc113ccdec.listWidth(title, rows, m, this.scaled(96.0F));
            float h = Ka20f8cfdbc113ccdec.listHeight(rows, m);
            float centerX = x + w * 0.5F;
            float centerY = y + h * 0.5F;
            float slideX = this.scaled(12.0F) * (1.0F - anim);
            matrixStack.method_22903();
            matrixStack.method_46416(centerX + slideX, centerY, 0.0F);
            matrixStack.method_22905(anim, anim, 1.0F);
            matrixStack.method_46416(-centerX, -centerY, 0.0F);
            Ka20f8cfdbc113ccdec.list(matrixStack, x, y, "command", title, rows, m, this.scaled(96.0F), anim);
            matrixStack.method_22909();
            this.getDraggable().setWidth(w);
            this.getDraggable().setHeight(h);
         }
      }
   }

   private static String Kf85ad50100f81dbfbd(Kf76f042931183af5ba category) {
      if (category == null) {
         return "clickgui/misc";
      } else {
         String var10000;
         switch (category) {
            case COMBAT -> var10000 = "clickgui/combat";
            case MOVEMENT -> var10000 = "clickgui/movement";
            case RENDER -> var10000 = "clickgui/render";
            case PLAYER -> var10000 = "clickgui/player";
            default -> var10000 = "clickgui/misc";
         }

         return var10000;
      }
   }

   protected Map<String, K8974f150cc9dad5e95> getCurrentData() {
      Map<String, K8974f150cc9dad5e95> map = new HashMap();

      for(Kab8a8c478f7d0077cd m : K5c460ed4a42f0797d6.getInstance().getModules()) {
         if (!(m instanceof K30b126617e809f138b) && m.isEnabled() && m.hasBind()) {
            map.put(m.getDisplayName(), new K8974f150cc9dad5e95(Ka35e7d6b31db9984f1.getInstance().translate(Kd9271a319f911decbe.getBind(m.getBind()))));
         }
      }

      if (map.isEmpty() && this.hudLayoutMode()) {
         map.put("Interface", new K8974f150cc9dad5e95("R"));
         map.put("Sprint", new K8974f150cc9dad5e95("V"));
      }

      return map;
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
