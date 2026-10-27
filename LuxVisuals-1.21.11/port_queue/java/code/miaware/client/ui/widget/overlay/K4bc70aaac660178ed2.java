package code.miaware.client.ui.widget.overlay;

import code.miaware.api.module.setting.K1d3d0b4020f933282f;
import code.miaware.api.system.backend.Kd9271a319f911decbe;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.client.features.modules.player.K3232577b3fa2418719;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import code.miaware.client.ui.widget.K424a42176df1675858;
import code.miaware.client.ui.widget.K8974f150cc9dad5e95;
import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.class_124;
import net.minecraft.class_1792;
import net.minecraft.class_1796;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class K4bc70aaac660178ed2 extends K424a42176df1675858 {
   private final Ka91715a8c4e3f3b7c4 showAnimation = new Ka91715a8c4e3f3b7c4();
   private boolean lastPreview;
   private final Map<String, Ka91715a8c4e3f3b7c4> itemAnimations = new LinkedHashMap();
   private int lastItemCount = 0;
   private static final String FIRE_KEYWORD = "взрывная штучка";
   private static final String STAN_KEYWORD = "стан";
   private static final String TRAP_KEYWORD = "трапка";
   private static final String EXPLOSIVE_TRAP_KEYWORD = "взрывная трапка";
   private static final class_1792 FIRE_ICON;
   private static final class_1792 STAN_ICON;
   private static final class_1792 TRAP_ICON;
   private static final class_1792 EXPLOSIVE_TRAP_ICON;

   public K4bc70aaac660178ed2() {
      super(120.0F, 100.0F);
      this.setEnabled(false);
      this.itemAnimations.put("fire", new Ka91715a8c4e3f3b7c4());
      this.itemAnimations.put("stan", new Ka91715a8c4e3f3b7c4());
      this.itemAnimations.put("trap", new Ka91715a8c4e3f3b7c4());
      this.itemAnimations.put("explosivetrap", new Ka91715a8c4e3f3b7c4());
   }

   public String getName() {
      return "HWCooldowns";
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.render(context.method_51448());
   }

   public void render(class_4587 matrixStack) {
      Map<String, K1e48519d5804035e49> data = this.getCurrentHWData();
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
         float gap = this.scaled(3.0F);
         float itemSize = this.scaled(24.0F);
         float itemGap = this.scaled(3.0F);
         float round = Kfae29c687712462375.getStyle().isStrict() ? 0.0F : this.scaled(4.35F);
         new Color(30, 30, 36, Math.min(255, (int)(255.0F * anim)));
         new Color(80, 80, 90, Math.min(255, (int)(200.0F * anim)));
         float outlineWidth = 0.45F;
         int itemCount = data.size();
         if (itemCount == 0) {
            this.getDraggable().setWidth(0.0F);
            this.getDraggable().setHeight(0.0F);
            this.lastItemCount = 0;
         } else {
            float totalWidth = itemSize * (float)itemCount + itemGap * (float)(itemCount - 1);
            if (itemCount != this.lastItemCount && this.lastItemCount > 0) {
               float oldWidth = itemSize * (float)this.lastItemCount + itemGap * (float)(this.lastItemCount - 1);
               float widthDiff = totalWidth - oldWidth;
               float currentX = this.getDraggable().getX();
               this.getDraggable().setX(currentX - widthDiff);
            }

            this.lastItemCount = itemCount;
            float centerX = x + totalWidth * 0.5F;
            float centerY = y + itemSize * 0.5F;
            float slideX = this.scaled(12.0F) * (1.0F - anim);
            matrixStack.method_22903();
            matrixStack.method_46416(centerX + slideX, centerY, 0.0F);
            matrixStack.method_22905(anim, anim, 1.0F);
            matrixStack.method_46416(-centerX, -centerY, 0.0F);

            for(String key : this.itemAnimations.keySet()) {
               Ka91715a8c4e3f3b7c4 itemAnim = (Ka91715a8c4e3f3b7c4)this.itemAnimations.get(key);
               itemAnim.update();
               boolean hasItem = data.containsKey(key);
               itemAnim.run(hasItem ? (double)1.0F : (double)0.0F, 300L, Kf3b62ecfba0c3544e6.EXPO_OUT);
            }

            int index = 0;

            for(Map.Entry<String, K1e48519d5804035e49> entry : data.entrySet()) {
               String itemKey = (String)entry.getKey();
               K1e48519d5804035e49 cooldownEntry = (K1e48519d5804035e49)entry.getValue();
               class_1799 itemStack = cooldownEntry.itemStack;
               float cooldownProgress = cooldownEntry.cooldownProgress;
               Ka91715a8c4e3f3b7c4 itemAnim = (Ka91715a8c4e3f3b7c4)this.itemAnimations.get(itemKey);
               float itemAnimValue = itemAnim != null ? (float)itemAnim.getValue() : 1.0F;
               float itemX = x + (itemSize + itemGap) * (float)index;
               float slideOffset = this.scaled(30.0F) * (1.0F - itemAnimValue);
               itemX += slideOffset;
               Color itemBgColor = new Color(30, 30, 36, Math.min(255, (int)(255.0F * anim * itemAnimValue)));
               Color itemOutlineColor = new Color(80, 80, 90, Math.min(255, (int)(200.0F * anim * itemAnimValue)));
               K2b5e2c5f2b9891b450.RECT.draw(matrixStack, itemX - outlineWidth, y - outlineWidth, itemSize + outlineWidth * 2.0F, itemSize + outlineWidth * 2.0F, round + outlineWidth, itemOutlineColor);
               K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrixStack, itemX, y, itemSize, itemSize, round, itemBgColor);
               if (cooldownProgress > 0.01F) {
                  float progressHeight = itemSize * cooldownProgress;
                  float progressY = y + itemSize - progressHeight;
                  Color cooldownColor = new Color(60, 60, 70, Math.min(255, (int)(200.0F * anim * itemAnimValue)));
                  K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrixStack, itemX, progressY, itemSize, progressHeight, round, cooldownColor);
               }

               float iconSize = this.scaled(16.0F);
               float iconX = itemX + (itemSize - iconSize) / 2.0F;
               float iconY = y + (itemSize - iconSize) / 2.0F;
               if (itemStack != null && !itemStack.method_7960()) {
                  matrixStack.method_22903();
                  matrixStack.method_46416(iconX, iconY, 0.0F);
                  float scale = iconSize / 16.0F;
                  matrixStack.method_22905(scale, scale, 1.0F);
                  class_332 itemContext = new class_332(mc, mc.method_22940().method_23000());
                  itemContext.method_51448().method_22903();
                  itemContext.method_51448().method_34425(matrixStack.method_23760().method_23761());
                  itemContext.method_51427(itemStack, 0, 0);
                  itemContext.method_51448().method_22909();
                  itemContext.method_51452();
                  matrixStack.method_22909();
               }

               String bindKey = this.K840b92b76d55de9b6e(itemKey);
               if (bindKey != null && !bindKey.isEmpty()) {
                  float bindFontSize = this.scaled(6.5F);
                  float bindWidth = this.getMediumFont().getWidth(bindKey, bindFontSize);
                  float bindX = itemX + itemSize - bindWidth - this.scaled(2.0F);
                  float bindY = y + itemSize - bindFontSize - this.scaled(2.0F);
                  this.getMediumFont().drawText(matrixStack, bindKey, bindX, bindY, bindFontSize, new Color(255, 255, 255, (int)(255.0F * anim * itemAnimValue)));
               }

               ++index;
            }

            matrixStack.method_22909();
            this.getDraggable().setWidth(totalWidth);
            this.getDraggable().setHeight(itemSize);
         }
      }
   }

   private String K840b92b76d55de9b6e(String itemKey) {
      if (!K3232577b3fa2418719.getInstance().isEnabled()) {
         return "";
      } else {
         int bindCode;
         switch (itemKey) {
            case "fire":
               K3232577b3fa2418719.getInstance();
               bindCode = (Integer)K3232577b3fa2418719.getInstance().getSettings().stream().filter((s) -> s.getDisplayName().equals("Взрывная штучка")).findFirst().map((s) -> (Integer)((K1d3d0b4020f933282f)s).getValue()).orElse(-1);
               break;
            case "stan":
               K3232577b3fa2418719.getInstance();
               bindCode = (Integer)K3232577b3fa2418719.getInstance().getSettings().stream().filter((s) -> s.getDisplayName().equals("Стан")).findFirst().map((s) -> (Integer)((K1d3d0b4020f933282f)s).getValue()).orElse(-1);
               break;
            case "trap":
               K3232577b3fa2418719.getInstance();
               bindCode = (Integer)K3232577b3fa2418719.getInstance().getSettings().stream().filter((s) -> s.getDisplayName().equals("Трапка")).findFirst().map((s) -> (Integer)((K1d3d0b4020f933282f)s).getValue()).orElse(-1);
               break;
            case "explosivetrap":
               K3232577b3fa2418719.getInstance();
               bindCode = (Integer)K3232577b3fa2418719.getInstance().getSettings().stream().filter((s) -> s.getDisplayName().equals("Взрывная трапка")).findFirst().map((s) -> (Integer)((K1d3d0b4020f933282f)s).getValue()).orElse(-1);
               break;
            default:
               return "";
         }

         return bindCode != -1 && bindCode != -999 ? Kd9271a319f911decbe.getBind(bindCode) : "";
      }
   }

   protected Map<String, K1e48519d5804035e49> getCurrentHWData() {
      Map<String, K1e48519d5804035e49> hwData = new LinkedHashMap();
      if (mc.field_1724 == null) {
         if (this.hudLayoutMode()) {
            hwData.put("fire", new K1e48519d5804035e49(new class_1799(FIRE_ICON), 0.8F));
            hwData.put("stan", new K1e48519d5804035e49(new class_1799(STAN_ICON), 0.6F));
            hwData.put("trap", new K1e48519d5804035e49(new class_1799(TRAP_ICON), 0.4F));
            hwData.put("explosivetrap", new K1e48519d5804035e49(new class_1799(EXPLOSIVE_TRAP_ICON), 0.2F));
         }

         return hwData;
      } else {
         class_1799 fireStack = null;
         float fireCooldown = 0.0F;
         class_1799 stanStack = null;
         float stanCooldown = 0.0F;
         class_1799 trapStack = null;
         float trapCooldown = 0.0F;
         class_1799 explosiveTrapStack = null;
         float explosiveTrapCooldown = 0.0F;
         class_1796 manager = mc.field_1724.method_7357();
         float tickDelta = mc.method_61966().method_60637(false);

         for(int i = 0; i < mc.field_1724.method_31548().method_5439(); ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (!stack.method_7960()) {
               String displayName = class_124.method_539(stack.method_7964().getString());
               if (displayName != null) {
                  String lowerName = displayName.toLowerCase();
                  if (lowerName.contains("взрывная штучка")) {
                     fireStack = stack.method_7972();
                     fireCooldown = manager.method_7905(stack, tickDelta);
                  }

                  if (lowerName.contains("стан")) {
                     stanStack = stack.method_7972();
                     stanCooldown = manager.method_7905(stack, tickDelta);
                  }

                  if (lowerName.contains("взрывная") && lowerName.contains("трапка")) {
                     explosiveTrapStack = stack.method_7972();
                     explosiveTrapCooldown = manager.method_7905(stack, tickDelta);
                  } else if (lowerName.contains("трапка") && !lowerName.contains("взрывная")) {
                     trapStack = stack.method_7972();
                     trapCooldown = manager.method_7905(stack, tickDelta);
                  }
               }
            }
         }

         if (fireStack != null) {
            hwData.put("fire", new K1e48519d5804035e49(fireStack, fireCooldown));
         }

         if (stanStack != null) {
            hwData.put("stan", new K1e48519d5804035e49(stanStack, stanCooldown));
         }

         if (trapStack != null) {
            hwData.put("trap", new K1e48519d5804035e49(trapStack, trapCooldown));
         }

         if (explosiveTrapStack != null) {
            hwData.put("explosivetrap", new K1e48519d5804035e49(explosiveTrapStack, explosiveTrapCooldown));
         }

         return hwData;
      }
   }

   protected Map<String, K8974f150cc9dad5e95> getCurrentData() {
      return new LinkedHashMap();
   }

   public void mouseClicked(double mouseX, double mouseY, int button) {
   }

   public void mouseReleased(double mouseX, double mouseY, int button) {
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
   }

   public void mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
   }

   static {
      FIRE_ICON = class_1802.field_8814;
      STAN_ICON = class_1802.field_8137;
      TRAP_ICON = class_1802.field_8882;
      EXPLOSIVE_TRAP_ICON = class_1802.field_8662;
   }
}
