package code.miaware.client.ui.widget.overlay;

import code.miaware.api.module.setting.K1d3d0b4020f933282f;
import code.miaware.api.system.backend.Kd9271a319f911decbe;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.client.features.modules.player.K6f46e31d3de2b9b245;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import code.miaware.client.ui.widget.K424a42176df1675858;
import code.miaware.client.ui.widget.K8974f150cc9dad5e95;
import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.class_124;
import net.minecraft.class_1792;
import net.minecraft.class_1796;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class K5ffb65634b086e35c7 extends K424a42176df1675858 {
   private final Ka91715a8c4e3f3b7c4 showAnimation = new Ka91715a8c4e3f3b7c4();
   private boolean lastPreview;
   private final Map<String, Ka91715a8c4e3f3b7c4> itemAnimations = new LinkedHashMap();
   private int lastItemCount = 0;
   private final Map<String, Long> customCooldowns = new HashMap();
   private final Map<String, Long> detectedCooldowns = new HashMap();
   private final Map<String, Long> cooldownStartTimes = new HashMap();
   private static final long DEFAULT_TRAP_COOLDOWN_MS = 50000L;
   private static final long DEFAULT_ANTI_FLIGHT_COOLDOWN_MS = 15000L;
   private static final String TRAP_KEYWORD = "ловушка";
   private static final String ANTI_FLIGHT_KEYWORD = "анти полет";
   private static final class_1792 TRAP_ICON;
   private static final class_1792 ANTI_FLIGHT_ICON;

   public K5ffb65634b086e35c7() {
      super(120.0F, 100.0F);
      this.setEnabled(false);
      this.itemAnimations.put("trap", new Ka91715a8c4e3f3b7c4());
      this.itemAnimations.put("antiflight", new Ka91715a8c4e3f3b7c4());
   }

   public void registerItemUse(String keyword) {
      long currentTime = System.currentTimeMillis();
      if (keyword.contains("ловушка")) {
         this.customCooldowns.put("trap", currentTime);
         this.cooldownStartTimes.put("trap", currentTime);
      } else if (keyword.contains("анти полет")) {
         this.customCooldowns.put("antiflight", currentTime);
         this.cooldownStartTimes.put("antiflight", currentTime);
      }

   }

   private void K8ecdc2a14b7fd5491a(String key, class_1799 stack) {
      if (mc.field_1724 != null && stack != null && this.detectedCooldowns.containsKey(key)) {
         class_1796 manager = mc.field_1724.method_7357();
         if (manager.method_7904(stack)) {
            float serverProgress = manager.method_7905(stack, mc.method_61966().method_60637(false));
            if (serverProgress > 0.01F && serverProgress < 0.99F) {
               Long startTime = (Long)this.cooldownStartTimes.get(key);
               if (startTime != null) {
                  long elapsed = System.currentTimeMillis() - startTime;
                  long estimatedDuration = (long)((float)elapsed / (1.0F - serverProgress));
                  Long currentDetected = (Long)this.detectedCooldowns.get(key);
                  if (currentDetected == null) {
                     this.detectedCooldowns.put(key, estimatedDuration);
                  } else {
                     long smoothed = (currentDetected + estimatedDuration) / 2L;
                     this.detectedCooldowns.put(key, smoothed);
                  }
               }
            }
         }

      }
   }

   private long K193d2d6e37871fadbc(String key) {
      Long detected = (Long)this.detectedCooldowns.get(key);
      if (detected != null && detected > 1000L) {
         return detected;
      } else if (key.equals("trap")) {
         return 50000L;
      } else {
         return key.equals("antiflight") ? 15000L : 0L;
      }
   }

   public String getName() {
      return "RWCooldowns";
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.render(context.method_51448());
   }

   public void render(class_4587 matrixStack) {
      Map<String, K15475402e0816cdb02> data = this.getCurrentRWData();
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

            for(Map.Entry<String, K15475402e0816cdb02> entry : data.entrySet()) {
               String itemKey = (String)entry.getKey();
               K15475402e0816cdb02 cooldownEntry = (K15475402e0816cdb02)entry.getValue();
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

               String bindKey = this.K22ac6450bc00e527ff(itemKey);
               if (bindKey != null && !bindKey.isEmpty()) {
                  float bindFontSize = this.scaled(6.5F);
                  float bindWidth = this.getMediumFont().getWidth(bindKey, bindFontSize);
                  float bindX = itemX + itemSize - bindWidth - this.scaled(2.0F);
                  float bindY = y + itemSize - bindFontSize - this.scaled(2.0F);
                  this.getMediumFont().drawText(matrixStack, bindKey, bindX, bindY, bindFontSize, new Color(255, 255, 255, (int)(255.0F * anim * itemAnimValue)));
               }

               if (cooldownProgress > 0.01F) {
                  int secondsLeft = this.Kbf2404e058ffd3923f(itemStack, cooldownProgress);
                  if (secondsLeft > 0) {
                     String timeText = secondsLeft + "s";
                     float timeFontSize = this.scaled(6.5F);
                     float timeX = itemX + this.scaled(2.0F);
                     float timeY = y + itemSize - timeFontSize - this.scaled(2.0F);
                     this.getMediumFont().drawText(matrixStack, timeText, timeX, timeY, timeFontSize, new Color(255, 100, 100, (int)(255.0F * anim * itemAnimValue)));
                  }
               }

               ++index;
            }

            matrixStack.method_22909();
            this.getDraggable().setWidth(totalWidth);
            this.getDraggable().setHeight(itemSize);
         }
      }
   }

   private int Kbf2404e058ffd3923f(class_1799 stack, float cooldownProgress) {
      if (mc.field_1724 != null && stack != null && !stack.method_7960()) {
         if (cooldownProgress <= 0.01F) {
            return 0;
         } else {
            String itemName = class_124.method_539(stack.method_7964().getString()).toLowerCase();
            String key = null;
            if (itemName.contains("ловушка")) {
               key = "trap";
            } else if (itemName.contains("анти полет")) {
               key = "antiflight";
            }

            if (key != null) {
               long cooldownDurationMs = this.K193d2d6e37871fadbc(key);
               Long useTime = (Long)this.customCooldowns.get(key);
               if (useTime != null) {
                  long currentTime = System.currentTimeMillis();
                  long elapsed = currentTime - useTime;
                  long remaining = cooldownDurationMs - elapsed;
                  if (remaining > 0L) {
                     return (int)Math.ceil((double)remaining / (double)1000.0F);
                  }
               }
            }

            return 0;
         }
      } else {
         return 0;
      }
   }

   private String K22ac6450bc00e527ff(String itemKey) {
      if (!K6f46e31d3de2b9b245.getInstance().isEnabled()) {
         return "";
      } else {
         int bindCode;
         if (itemKey.equals("trap")) {
            K6f46e31d3de2b9b245.getInstance();
            bindCode = (Integer)K6f46e31d3de2b9b245.getInstance().getSettings().stream().filter((s) -> s.getDisplayName().equals("Ловушка")).findFirst().map((s) -> (Integer)((K1d3d0b4020f933282f)s).getValue()).orElse(-1);
         } else {
            if (!itemKey.equals("antiflight")) {
               return "";
            }

            K6f46e31d3de2b9b245.getInstance();
            bindCode = (Integer)K6f46e31d3de2b9b245.getInstance().getSettings().stream().filter((s) -> s.getDisplayName().equals("Анти Полет")).findFirst().map((s) -> (Integer)((K1d3d0b4020f933282f)s).getValue()).orElse(-1);
         }

         return bindCode != -1 && bindCode != -999 ? Kd9271a319f911decbe.getBind(bindCode) : "";
      }
   }

   protected Map<String, K15475402e0816cdb02> getCurrentRWData() {
      Map<String, K15475402e0816cdb02> rwData = new LinkedHashMap();
      if (mc.field_1724 == null) {
         if (this.hudLayoutMode()) {
            rwData.put("trap", new K15475402e0816cdb02(new class_1799(TRAP_ICON), 0.6F));
            rwData.put("antiflight", new K15475402e0816cdb02(new class_1799(ANTI_FLIGHT_ICON), 0.3F));
         }

         return rwData;
      } else {
         class_1799 trapStack = null;
         float trapCooldown = 0.0F;
         class_1799 antiFlightStack = null;
         float antiFlightCooldown = 0.0F;
         class_1796 manager = mc.field_1724.method_7357();
         float tickDelta = mc.method_61966().method_60637(false);

         for(int i = 0; i < mc.field_1724.method_31548().method_5439(); ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (!stack.method_7960()) {
               String displayName = class_124.method_539(stack.method_7964().getString());
               if (displayName != null) {
                  String lowerName = displayName.toLowerCase();
                  if (lowerName.contains("ловушка")) {
                     trapStack = stack.method_7972();
                     this.K8ecdc2a14b7fd5491a("trap", stack);
                     trapCooldown = this.Kf6f00e28c55a0d9a71("trap");
                  }

                  if (lowerName.contains("анти полет")) {
                     antiFlightStack = stack.method_7972();
                     this.K8ecdc2a14b7fd5491a("antiflight", stack);
                     antiFlightCooldown = this.Kf6f00e28c55a0d9a71("antiflight");
                  }
               }
            }
         }

         if (trapStack != null) {
            rwData.put("trap", new K15475402e0816cdb02(trapStack, trapCooldown));
         }

         if (antiFlightStack != null) {
            rwData.put("antiflight", new K15475402e0816cdb02(antiFlightStack, antiFlightCooldown));
         }

         return rwData;
      }
   }

   private float Kf6f00e28c55a0d9a71(String key) {
      Long useTime = (Long)this.customCooldowns.get(key);
      if (useTime == null) {
         return 0.0F;
      } else {
         long cooldownDurationMs = this.K193d2d6e37871fadbc(key);
         long currentTime = System.currentTimeMillis();
         long elapsed = currentTime - useTime;
         if (elapsed >= cooldownDurationMs) {
            this.customCooldowns.remove(key);
            this.cooldownStartTimes.remove(key);
            return 0.0F;
         } else {
            float progress = 1.0F - (float)elapsed / (float)cooldownDurationMs;
            return progress;
         }
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
      TRAP_ICON = class_1802.field_8207;
      ANTI_FLIGHT_ICON = class_1802.field_8450;
   }
}
