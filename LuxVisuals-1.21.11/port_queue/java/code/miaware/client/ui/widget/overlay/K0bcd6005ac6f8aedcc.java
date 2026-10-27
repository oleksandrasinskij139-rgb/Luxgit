package code.miaware.client.ui.widget.overlay;

import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import code.miaware.api.utils.render.display.Ka344a99dbf6b098920;
import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import code.miaware.client.ui.widget.K9c25dd69cb23ed0eb8;
import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import code.miaware.client.ui.widget.Kdd908967ec41b41bcf;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1799;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class K0bcd6005ac6f8aedcc extends K548ca8198549ddf9d7 {
   private final List<class_1799> inventoryItems = new ArrayList(27);

   public K0bcd6005ac6f8aedcc() {
      super(100.0F, 100.0F);
   }

   public String getName() {
      return "InvView";
   }

   protected boolean hasSettingsMenu() {
      return false;
   }

   public void render(class_4587 matrixStack) {
      this.render(new class_332(mc, mc.method_22940().method_23000()), 0, 0, 0.0F);
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      if (!super.isEnabled()) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      } else {
         this.Ked9ea80b1b5744fb51();
         class_4587 matrices = context.method_51448();
         float x = this.getDraggable().getX();
         float y = this.getDraggable().getY();
         K9c25dd69cb23ed0eb8 m = this.listMetrics();
         float slot = this.scaled(15.0F);
         float gap = this.scaled(2.0F);
         int columns = 9;
         int rows = 3;
         float panelWidth = (float)columns * slot + (float)(columns - 1) * gap + m.pad() * 2.0F;
         float panelHeight = m.headerHeight() + (float)rows * slot + (float)(rows - 1) * gap + m.pad();
         Ka20f8cfdbc113ccdec.panel(matrices, x, y, panelWidth, panelHeight, m.radius(), 1.0F);
         Ka20f8cfdbc113ccdec.header(matrices, x, y, "box", Ka35e7d6b31db9984f1.getInstance().translate("Inventory"), m, 1.0F);
         float gridY = y + m.headerHeight();
         List<Ka344a99dbf6b098920> slots = new ArrayList(columns * rows);

         for(int i = 0; i < columns * rows && i < this.inventoryItems.size(); ++i) {
            slots.add(new Ka344a99dbf6b098920(x + m.pad() + (float)(i % columns) * (slot + gap), gridY + (float)(i / columns) * (slot + gap)));
         }

         Ka20f8cfdbc113ccdec.rects(matrices, slots, slot, slot, this.scaled(3.0F), Ka20f8cfdbc113ccdec.SLOT);

         for(int i = 0; i < columns * rows && i < this.inventoryItems.size(); ++i) {
            float sx = x + m.pad() + (float)(i % columns) * (slot + gap);
            float sy = gridY + (float)(i / columns) * (slot + gap);
            if (((class_1799)this.inventoryItems.get(i)).method_7960()) {
               Ka20f8cfdbc113ccdec.icon(matrices, "cross", sx + slot / 2.0F, sy + slot / 2.0F, slot * 0.36F, Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.MUTED, 0.7F));
            }
         }

         float itemSize = slot * 0.8F;
         float countSize = this.scaled(5.4F);

         try (Kdd908967ec41b41bcf items = Ka20f8cfdbc113ccdec.items(matrices, 1.0F)) {
            for(int i = 0; i < columns * rows && i < this.inventoryItems.size(); ++i) {
               class_1799 item = (class_1799)this.inventoryItems.get(i);
               if (!item.method_7960()) {
                  float sx = x + m.pad() + (float)(i % columns) * (slot + gap);
                  float sy = gridY + (float)(i / columns) * (slot + gap);
                  items.draw(item, sx + (slot - itemSize) / 2.0F, sy + (slot - itemSize) / 2.0F, itemSize);
               }
            }
         }

         matrices.method_22903();
         matrices.method_46416(0.0F, 0.0F, 200.0F);

         for(int i = 0; i < columns * rows && i < this.inventoryItems.size(); ++i) {
            class_1799 item = (class_1799)this.inventoryItems.get(i);
            if (!item.method_7960() && item.method_7947() > 1) {
               float sx = x + m.pad() + (float)(i % columns) * (slot + gap);
               float sy = gridY + (float)(i / columns) * (slot + gap);
               String count = String.valueOf(item.method_7947());
               Ka20f8cfdbc113ccdec.text(matrices, Ka20f8cfdbc113ccdec.semibold(), count, sx + slot - Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), count, countSize) - this.scaled(1.0F), sy + slot - countSize * 0.55F, countSize, Ka20f8cfdbc113ccdec.TEXT);
            }
         }

         matrices.method_22909();
         this.getDraggable().setWidth(panelWidth);
         this.getDraggable().setHeight(panelHeight);
      }
   }

   private void Ked9ea80b1b5744fb51() {
      this.inventoryItems.clear();
      if (mc.field_1724 == null) {
         for(int i = 0; i < 27; ++i) {
            this.inventoryItems.add(class_1799.field_8037);
         }

      } else {
         for(int i = 9; i < 36; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            this.inventoryItems.add(stack);
         }

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
