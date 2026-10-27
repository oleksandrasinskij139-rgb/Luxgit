package code.miaware.client.ui.widget.overlay;

import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import code.miaware.client.ui.widget.Kdd908967ec41b41bcf;
import code.miaware.client.ui.widget.Ke7efbae1920d8c2967;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.class_1306;
import net.minecraft.class_1799;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class K1d1af359ed93c5ac1a extends K548ca8198549ddf9d7 {
   private final List<class_1799> armorItems = new ArrayList(4);
   private static final float PILL_H = 21.0F;
   private static final float PAD = 6.0F;
   private static final float SLOT = 16.0F;
   private static final float ITEM = 12.0F;
   private static final float SLOT_TEXT_GAP = 5.0F;
   private static final float TEXT_SIZE = 6.6F;
   private static final float SEGMENT_GAP = 6.0F;
   private static final float GAP = 4.0F;

   public K1d1af359ed93c5ac1a() {
      super(30.0F, 100.0F);
      this.setEnabled(true);
   }

   public String getName() {
      return "Armor";
   }

   protected boolean hasSettingsMenu() {
      return false;
   }

   protected List<Ke7efbae1920d8c2967> getSettingsMenuEntries() {
      return List.of();
   }

   public void render(class_4587 matrixStack) {
      this.render(new class_332(mc, mc.method_22940().method_23000()), 0, 0, 0.0F);
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      if (super.isEnabled() && mc.field_1724 != null) {
         this.Kdc7e95690b83eb6b20();
         class_4587 matrices = context.method_51448();
         boolean anyArmor = this.armorItems.stream().anyMatch((stack) -> !stack.method_7960());
         if (!anyArmor && !this.hudLayoutMode()) {
            this.getDraggable().setWidth(0.0F);
            this.getDraggable().setHeight(0.0F);
         } else {
            float screenWidth = (float)mc.method_22683().method_4486();
            float screenHeight = (float)mc.method_22683().method_4502();
            float hotbarLeft = screenWidth / 2.0F - 91.0F;
            float hotbarRight = screenWidth / 2.0F + 91.0F;
            if (!mc.field_1724.method_6079().method_7960()) {
               if (mc.field_1724.method_6068() == class_1306.field_6183) {
                  hotbarLeft -= 29.0F;
               } else {
                  hotbarRight += 29.0F;
               }
            }

            float y = screenHeight - 21.0F - 0.5F;
            float leftW = K148d8e8f6e016718ec(2);
            this.K7959e84c140332e16a(matrices, hotbarLeft - 4.0F - leftW, y, (class_1799)this.armorItems.get(3), (class_1799)this.armorItems.get(2));
            this.K7959e84c140332e16a(matrices, hotbarRight + 4.0F, y, (class_1799)this.armorItems.get(1), (class_1799)this.armorItems.get(0));
            this.getDraggable().setWidth(0.0F);
            this.getDraggable().setHeight(0.0F);
         }
      } else {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      }
   }

   private static float Kfec19a2f8c3df839c5() {
      return Math.max(Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), "100%", 6.6F), 20.0F);
   }

   private static float Kf343ff9f7b035f98c9() {
      return 21.0F + Kfec19a2f8c3df839c5();
   }

   private static float K148d8e8f6e016718ec(int cells) {
      return 12.0F + Kf343ff9f7b035f98c9() * (float)cells + 12.0F * (float)(cells - 1);
   }

   private void K7959e84c140332e16a(class_4587 ms, float x, float y, class_1799 first, class_1799 second) {
      Ka20f8cfdbc113ccdec.panel(ms, x, y, K148d8e8f6e016718ec(2), 21.0F, 6.0F, 1.0F);
      float cy = y + 10.5F;
      float cx = x + 6.0F;
      this.K86a08a8f1658ae5432(ms, first, cx, cy);
      cx += Kf343ff9f7b035f98c9();
      Ka20f8cfdbc113ccdec.tick(ms, cx + 6.0F, cy, 7.5600004F, 1.0F);
      cx += 12.0F;
      this.K86a08a8f1658ae5432(ms, second, cx, cy);

      try (Kdd908967ec41b41bcf items = Ka20f8cfdbc113ccdec.items(ms, 1.0F)) {
         items.draw(first, x + 6.0F + 2.0F, cy - 6.0F, 12.0F);
         items.draw(second, cx + 2.0F, cy - 6.0F, 12.0F);
      }

   }

   private void K86a08a8f1658ae5432(class_4587 ms, class_1799 stack, float x, float cy) {
      Ka20f8cfdbc113ccdec.rect(ms, x, cy - 8.0F, 16.0F, 16.0F, 4.0F, Ka20f8cfdbc113ccdec.SLOT);
      float tx = x + 16.0F + 5.0F;
      float bw = Kfec19a2f8c3df839c5();
      float textY = cy - 2.6F;
      float barY = cy + 3.4F;
      float barH = 1.8F;
      Ka20f8cfdbc113ccdec.rect(ms, tx, barY, bw, barH, barH / 2.0F, Ka20f8cfdbc113ccdec.TRACK);
      if (stack.method_7960()) {
         Ka20f8cfdbc113ccdec.icon(ms, "cross", x + 8.0F, cy, 5.5F, Ka20f8cfdbc113ccdec.MUTED);
         Ka20f8cfdbc113ccdec.text(ms, Ka20f8cfdbc113ccdec.semibold(), "—", tx, textY, 6.6F, Ka20f8cfdbc113ccdec.MUTED);
      } else {
         float durability = this.Kd33cea3ac20081e714(stack);
         int percent = Math.round(durability * 100.0F);
         String number = String.valueOf(percent);
         Ka20f8cfdbc113ccdec.text(ms, Ka20f8cfdbc113ccdec.semibold(), number, tx, textY, 6.6F, Ka20f8cfdbc113ccdec.TEXT);
         Ka20f8cfdbc113ccdec.text(ms, Ka20f8cfdbc113ccdec.semibold(), "%", tx + Ka20f8cfdbc113ccdec.width(Ka20f8cfdbc113ccdec.semibold(), number, 6.6F), textY, 6.6F, Ka20f8cfdbc113ccdec.ACCENT_SOFT);
         Ka20f8cfdbc113ccdec.rect(ms, tx, barY, Math.max(barH, bw * durability), barH, barH / 2.0F, this.K566fdba30f4532fddd(durability));
      }
   }

   private float Kd33cea3ac20081e714(class_1799 item) {
      if (!item.method_7963()) {
         return 1.0F;
      } else {
         float maxDamage = Math.max(1.0F, (float)item.method_7936());
         return Math.max(0.0F, Math.min(1.0F, (maxDamage - (float)item.method_7919()) / maxDamage));
      }
   }

   private Color K566fdba30f4532fddd(float durability) {
      if (durability >= 0.6F) {
         return Ka20f8cfdbc113ccdec.ACCENT;
      } else {
         return durability >= 0.3F ? Ka20f8cfdbc113ccdec.WARM : Ka20f8cfdbc113ccdec.RED;
      }
   }

   private void Kdc7e95690b83eb6b20() {
      this.armorItems.clear();
      if (mc.field_1724 == null) {
         this.armorItems.addAll(Collections.nCopies(4, class_1799.field_8037));
      } else {
         this.armorItems.addAll(mc.field_1724.method_31548().field_7548);

         while(this.armorItems.size() < 4) {
            this.armorItems.add(class_1799.field_8037);
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
