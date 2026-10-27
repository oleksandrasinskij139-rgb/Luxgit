package code.miaware.client.ui.widget.overlay;

import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.utils.render.fonts.K896065ea0a2bba4f47;
import code.miaware.client.features.modules.combat.K582e27fb3f07a5c101;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import code.miaware.client.ui.widget.Kdd908967ec41b41bcf;
import code.miaware.client.ui.widget.Ke7efbae1920d8c2967;
import java.awt.Color;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class K9ee1eeb793b49de7cb extends K548ca8198549ddf9d7 {
   private final K00fef91b3d4d1b3ecc chorus = (new K00fef91b3d4d1b3ecc("Хорусы")).value(true);
   private final K00fef91b3d4d1b3ecc pearls = (new K00fef91b3d4d1b3ecc("Перки")).value(true);
   private final K00fef91b3d4d1b3ecc fireworks = (new K00fef91b3d4d1b3ecc("Фейерверки")).value(true);
   private final K00fef91b3d4d1b3ecc goldenApples = (new K00fef91b3d4d1b3ecc("Золотые яблоки")).value(true);
   private final K00fef91b3d4d1b3ecc enchantedApples = (new K00fef91b3d4d1b3ecc("Зачарованные яблоки")).value(true);
   private final K00fef91b3d4d1b3ecc totems = (new K00fef91b3d4d1b3ecc("Тотемы")).value(true);
   private final K00fef91b3d4d1b3ecc horizontal = (new K00fef91b3d4d1b3ecc("Горизонтально")).value(false);
   private final Map<class_1792, Integer> itemCounts = new IdentityHashMap();
   private final Map<class_1792, class_1799> icons = new IdentityHashMap();
   private class_1657 countedPlayer;
   private int countedTick = -1;

   public K9ee1eeb793b49de7cb() {
      super(10.0F, 100.0F);
   }

   public String getName() {
      return "Counter";
   }

   protected boolean hasSettingsMenu() {
      return true;
   }

   protected String getSettingsMenuTitle() {
      return "Counter";
   }

   protected List<Ke7efbae1920d8c2967> getSettingsMenuEntries() {
      return List.of(Ke7efbae1920d8c2967.of("Вертикально", "Отображать предметы вертикально", () -> !(Boolean)this.horizontal.getValue(), (value) -> {
         this.horizontal.setValue(false);
         this.K3fcb3c1500125b18da();
      }), Ke7efbae1920d8c2967.of("Горизонтально", "Отображать предметы горизонтально", () -> (Boolean)this.horizontal.getValue(), (value) -> {
         this.horizontal.setValue(true);
         this.K3fcb3c1500125b18da();
      }));
   }

   private void K3fcb3c1500125b18da() {
      K582e27fb3f07a5c101 module = K582e27fb3f07a5c101.getInstance();
      if (module != null) {
         module.getHorizontal().setValue((Boolean)this.horizontal.getValue());
      }

   }

   public List<K00fef91b3d4d1b3ecc> getSettings() {
      List<K00fef91b3d4d1b3ecc> settings = new ArrayList();
      settings.add(this.chorus);
      settings.add(this.pearls);
      settings.add(this.fireworks);
      settings.add(this.goldenApples);
      settings.add(this.enchantedApples);
      settings.add(this.horizontal);
      return settings;
   }

   public void render(class_4587 matrixStack) {
      this.render(new class_332(mc, mc.method_22940().method_23000()), 0, 0, 0.0F);
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      if (!super.isEnabled()) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
         this.closeSettingsMenu();
      } else if (mc.field_1724 == null) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
         this.closeSettingsMenu();
      } else {
         this.updateSettingsMenuState();
         class_4587 matrices = context.method_51448();
         float x = this.getDraggable().getX();
         float y = this.getDraggable().getY();
         if (this.countedPlayer != mc.field_1724 || this.countedTick != mc.field_1724.field_6012) {
            this.itemCounts.clear();
            class_1661 inventory = mc.field_1724.method_31548();

            for(int slot = 0; slot < inventory.method_5439(); ++slot) {
               class_1799 stack = inventory.method_5438(slot);
               if (!stack.method_7960()) {
                  this.itemCounts.merge(stack.method_7909(), stack.method_7947(), Integer::sum);
               }
            }

            this.countedPlayer = mc.field_1724;
            this.countedTick = mc.field_1724.field_6012;
         }

         List<Kb1abcfbdc202fc3d44> items = new ArrayList(6);
         this.Kb45a3c3f33253bd81d(items, this.chorus, class_1802.field_8233);
         this.Kb45a3c3f33253bd81d(items, this.pearls, class_1802.field_8634);
         this.Kb45a3c3f33253bd81d(items, this.fireworks, class_1802.field_8639);
         this.Kb45a3c3f33253bd81d(items, this.goldenApples, class_1802.field_8463);
         this.Kb45a3c3f33253bd81d(items, this.enchantedApples, class_1802.field_8367);
         this.Kb45a3c3f33253bd81d(items, this.totems, class_1802.field_8288);
         if (items.isEmpty()) {
            this.getDraggable().setWidth(20.0F);
            this.getDraggable().setHeight(20.0F);
         } else {
            if ((Boolean)this.horizontal.getValue()) {
               this.K85d8fa7f370831c011(context, matrices, x, y, items);
            } else {
               this.Kd253732ef0d34e6f72(context, matrices, x, y, items);
            }

            this.renderSettingsMenu(matrices);
         }
      }
   }

   private void Kd253732ef0d34e6f72(class_332 context, class_4587 matrices, float x, float y, List<Kb1abcfbdc202fc3d44> items) {
      float paddingX = this.scaled(2.0F);
      float paddingY = this.scaled(3.0F);
      float itemSize = this.scaled(12.0F);
      float textSpacing = this.scaled(1.0F);
      float fontSize = this.scaled(5.0F);
      float separatorWidth = this.scaled(1.2F);
      float itemSpacing = this.scaled(1.5F);
      float maxTextWidth = 0.0F;

      for(Kb1abcfbdc202fc3d44 item : items) {
         String text = this.K704fad5b233101714e(item.count);
         float textWidth = K896065ea0a2bba4f47.SF_MEDIUM.getWidth(text, fontSize);
         if (textWidth > maxTextWidth) {
            maxTextWidth = textWidth;
         }
      }

      float totalHeight = paddingY * 2.0F;

      for(int i = 0; i < items.size(); ++i) {
         totalHeight += itemSize;
         if (i < items.size() - 1) {
            totalHeight += itemSpacing;
         }
      }

      float width = paddingX * 2.0F + itemSize + textSpacing + maxTextWidth;
      if (Kfae29c687712462375.getStyle().isStrict()) {
         float var10000 = 0.0F;
      } else {
         this.scaled(4.0F);
      }

      new Color(30, 30, 36, 255);
      new Color(80, 80, 90, 200);
      float outlineWidth = this.scaled(0.5F);
      Ka20f8cfdbc113ccdec.panel(matrices, x, y, width, totalHeight, this.scaled(5.0F), 1.0F);
      float itemX = x + paddingX;
      float currentY = y + paddingY;
      float[] itemPositions = new float[items.size()];

      for(int i = 0; i < items.size(); ++i) {
         Kb1abcfbdc202fc3d44 item = (Kb1abcfbdc202fc3d44)items.get(i);
         itemPositions[i] = currentY;
         String text = this.K704fad5b233101714e(item.count);
         float textX = itemX + itemSize - this.scaled(1.0F) + this.scaled(1.5F);
         float textY = currentY + (itemSize - fontSize) / 2.0F + this.scaled(2.5F);
         K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, text, textX, textY, fontSize, Ka20f8cfdbc113ccdec.TEXT);
         currentY += itemSize;
         if (i < items.size() - 1) {
            float separatorY = currentY + itemSpacing / 2.0F;
            float separatorX = x + paddingX;
            Ka20f8cfdbc113ccdec.rect(matrices, separatorX, separatorY, width - paddingX * 2.0F, separatorWidth * 0.6F, 0.0F, Ka20f8cfdbc113ccdec.DIVIDER);
            currentY += itemSpacing;
         }
      }

      try (Kdd908967ec41b41bcf batch = Ka20f8cfdbc113ccdec.items(matrices, 1.0F)) {
         for(int i = 0; i < items.size(); ++i) {
            batch.draw((class_1799)this.icons.computeIfAbsent(((Kb1abcfbdc202fc3d44)items.get(i)).item, class_1799::new), itemX, itemPositions[i], 12.0F);
         }
      }

      this.setSettingsMenuAnchor(x, y, width, totalHeight);
      this.getDraggable().setWidth(width);
      this.getDraggable().setHeight(totalHeight);
   }

   private void K85d8fa7f370831c011(class_332 context, class_4587 matrices, float x, float y, List<Kb1abcfbdc202fc3d44> items) {
      float paddingX = this.scaled(2.0F);
      float paddingY = this.scaled(3.0F);
      float itemSize = this.scaled(12.0F);
      float fontSize = this.scaled(5.0F);
      float separatorWidth = this.scaled(1.2F);
      float itemSpacing = this.scaled(3.0F);
      float totalWidth = paddingX * 2.0F;

      for(int i = 0; i < items.size(); ++i) {
         String text = this.K704fad5b233101714e(((Kb1abcfbdc202fc3d44)items.get(i)).count);
         float textWidth = K896065ea0a2bba4f47.SF_MEDIUM.getWidth(text, fontSize);
         totalWidth += itemSize + textWidth + this.scaled(2.0F);
         if (i < items.size() - 1) {
            totalWidth += itemSpacing;
         }
      }

      float height = paddingY * 2.0F + itemSize;
      if (Kfae29c687712462375.getStyle().isStrict()) {
         float var10000 = 0.0F;
      } else {
         this.scaled(4.0F);
      }

      new Color(30, 30, 36, 255);
      new Color(80, 80, 90, 200);
      float outlineWidth = this.scaled(0.5F);
      Ka20f8cfdbc113ccdec.panel(matrices, x, y, totalWidth, height, this.scaled(5.0F), 1.0F);
      float currentX = x + paddingX;
      float itemY = y + paddingY;
      float[] itemPositions = new float[items.size()];

      for(int i = 0; i < items.size(); ++i) {
         Kb1abcfbdc202fc3d44 item = (Kb1abcfbdc202fc3d44)items.get(i);
         itemPositions[i] = currentX;
         String text = this.K704fad5b233101714e(item.count);
         float textX = currentX + itemSize + this.scaled(1.0F) + this.scaled(1.5F);
         float textY = itemY + (itemSize - fontSize) / 2.0F + this.scaled(2.5F);
         K896065ea0a2bba4f47.SF_SEMIBOLD.drawText(matrices, text, textX, textY, fontSize, Ka20f8cfdbc113ccdec.TEXT);
         float textWidth = K896065ea0a2bba4f47.SF_MEDIUM.getWidth(text, fontSize);
         currentX += itemSize + textWidth + this.scaled(2.0F);
         if (i < items.size() - 1) {
            float separatorX = currentX + itemSpacing / 2.0F;
            Ka20f8cfdbc113ccdec.rect(matrices, separatorX, itemY + itemSize * 0.2F, separatorWidth * 0.6F, itemSize * 0.6F, 0.0F, Ka20f8cfdbc113ccdec.DIVIDER);
            currentX += itemSpacing;
         }
      }

      try (Kdd908967ec41b41bcf batch = Ka20f8cfdbc113ccdec.items(matrices, 1.0F)) {
         for(int i = 0; i < items.size(); ++i) {
            batch.draw((class_1799)this.icons.computeIfAbsent(((Kb1abcfbdc202fc3d44)items.get(i)).item, class_1799::new), itemPositions[i], itemY, 12.0F);
         }
      }

      this.setSettingsMenuAnchor(x, y, totalWidth, height);
      this.getDraggable().setWidth(totalWidth);
      this.getDraggable().setHeight(height);
   }

   private String K704fad5b233101714e(int count) {
      return String.valueOf(count);
   }

   private void Kb45a3c3f33253bd81d(List<Kb1abcfbdc202fc3d44> items, K00fef91b3d4d1b3ecc setting, class_1792 item) {
      int count = (Integer)this.itemCounts.getOrDefault(item, 0);
      if ((Boolean)setting.getValue() && count > 0) {
         items.add(new Kb1abcfbdc202fc3d44(item, count));
      }

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
   public K00fef91b3d4d1b3ecc getChorus() {
      return this.chorus;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getPearls() {
      return this.pearls;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getFireworks() {
      return this.fireworks;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getGoldenApples() {
      return this.goldenApples;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getEnchantedApples() {
      return this.enchantedApples;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getTotems() {
      return this.totems;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getHorizontal() {
      return this.horizontal;
   }
}
