package code.miaware.client.ui.widget.overlay;

import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.other.Kf5ec3d7f18b288e7f3;
import code.miaware.client.ui.widget.K424a42176df1675858;
import code.miaware.client.ui.widget.K71a43541bec306e7a2;
import code.miaware.client.ui.widget.K8974f150cc9dad5e95;
import code.miaware.client.ui.widget.K9c25dd69cb23ed0eb8;
import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import code.miaware.client.ui.widget.Kdd908967ec41b41bcf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.class_1792;
import net.minecraft.class_1796;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class Kc01b33a65c9e10c8ea extends K424a42176df1675858 {
   private final Ka91715a8c4e3f3b7c4 showAnimation = new Ka91715a8c4e3f3b7c4();
   private boolean lastPreview;

   public Kc01b33a65c9e10c8ea() {
      super(120.0F, 100.0F);
   }

   public String getName() {
      return "Cooldowns";
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.render(context.method_51448());
   }

   public void render(class_4587 matrixStack) {
      if (!super.isEnabled()) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      } else {
         Map<String, K760c743a1e32732941> data = this.getCurrentDataWithItems();
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
            K9c25dd69cb23ed0eb8 m = this.listMetrics();
            float itemSize = m.rowHeight() * 0.74F;
            List<Map.Entry<String, K760c743a1e32732941>> sorted = new ArrayList(data.entrySet());
            sorted.sort(Entry.comparingByKey());
            List<K71a43541bec306e7a2> rows = new ArrayList();

            for(Map.Entry<String, K760c743a1e32732941> entry : sorted) {
               rows.add((new K71a43541bec306e7a2()).indent(itemSize + m.pad() * 0.55F).left((String)entry.getKey(), Ka20f8cfdbc113ccdec.TEXT).right(((K760c743a1e32732941)entry.getValue()).time, Ka20f8cfdbc113ccdec.ACCENT_SOFT));
            }

            String title = Ka35e7d6b31db9984f1.getInstance().translate(this.getName());
            float w = Ka20f8cfdbc113ccdec.listWidth(title, rows, m, this.scaled(96.0F));
            float h = Ka20f8cfdbc113ccdec.listHeight(rows, m);
            float centerX = x + w * 0.5F;
            float centerY = y + h * 0.5F;
            float slideX = this.scaled(12.0F) * (1.0F - anim);
            matrixStack.method_22903();
            matrixStack.method_46416(centerX + slideX, centerY, 0.0F);
            matrixStack.method_22905(anim, anim, 1.0F);
            matrixStack.method_46416(-centerX, -centerY, 0.0F);
            Ka20f8cfdbc113ccdec.list(matrixStack, x, y, "hourglass", title, rows, m, this.scaled(96.0F), anim);
            float rowY = y + m.headerHeight();

            try (Kdd908967ec41b41bcf items = Ka20f8cfdbc113ccdec.items(matrixStack, anim)) {
               for(Map.Entry<String, K760c743a1e32732941> entry : sorted) {
                  items.draw(((K760c743a1e32732941)entry.getValue()).itemStack, x + m.pad(), rowY + (m.rowHeight() - itemSize) / 2.0F, itemSize);
                  rowY += m.rowHeight();
               }
            }

            matrixStack.method_22909();
            this.getDraggable().setWidth(w);
            this.getDraggable().setHeight(h);
         }
      }
   }

   protected Map<String, K760c743a1e32732941> getCurrentDataWithItems() {
      Map<String, K760c743a1e32732941> cooldownData = new HashMap();
      if (mc.field_1724 == null) {
         if (this.hudLayoutMode()) {
            cooldownData.put("Golden Apple", new K760c743a1e32732941("0:24", new class_1799(class_1802.field_8463)));
            cooldownData.put("Ender Pearl", new K760c743a1e32732941("0:26", new class_1799(class_1802.field_8634)));
            cooldownData.put("Chorus Fruit", new K760c743a1e32732941("0:36", new class_1799(class_1802.field_8233)));
         }

         return cooldownData;
      } else {
         class_1796 manager = mc.field_1724.method_7357();
         float tickDelta = mc.method_61966().method_60637(false);

         for(int i = 0; i < mc.field_1724.method_31548().method_5439(); ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (!stack.method_7960()) {
               class_1792 item = stack.method_7909();
               if (manager.method_7904(stack)) {
                  int remaining = this.Kf33062ab7e243696d9(stack, tickDelta);
                  if (remaining > 0) {
                     cooldownData.put(item.method_63680().getString(), new K760c743a1e32732941(Kf5ec3d7f18b288e7f3.getDurationText(remaining), stack.method_7972()));
                  }
               }
            }
         }

         if (cooldownData.isEmpty() && this.hudLayoutMode()) {
            cooldownData.put("Golden Apple", new K760c743a1e32732941("0:24", new class_1799(class_1802.field_8463)));
            cooldownData.put("Ender Pearl", new K760c743a1e32732941("0:26", new class_1799(class_1802.field_8634)));
            cooldownData.put("Chorus Fruit", new K760c743a1e32732941("0:36", new class_1799(class_1802.field_8233)));
         }

         return cooldownData;
      }
   }

   protected Map<String, K8974f150cc9dad5e95> getCurrentData() {
      Map<String, K8974f150cc9dad5e95> cooldownData = new HashMap();
      Map<String, K760c743a1e32732941> dataWithItems = this.getCurrentDataWithItems();

      for(Map.Entry<String, K760c743a1e32732941> entry : dataWithItems.entrySet()) {
         cooldownData.put((String)entry.getKey(), new K8974f150cc9dad5e95(((K760c743a1e32732941)entry.getValue()).time));
      }

      return cooldownData;
   }

   private int Kf33062ab7e243696d9(class_1799 stack, float tickDelta) {
      class_1796 manager = mc.field_1724.method_7357();
      class_2960 groupId = manager.method_62836(stack);
      class_1796.class_1797 entry = (class_1796.class_1797)manager.field_8024.get(groupId);
      return entry != null ? Math.max(0, entry.comp_3084() - (manager.field_8025 + (int)tickDelta)) : 0;
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
