package code.miaware.client.features.modules.player;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.Kdee350abc91aa294b2;
import code.miaware.api.event.events.render.K14caf22f676a0204f7;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.color.K258ef67f9722180a72;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Generated;
import net.minecraft.class_1297;
import net.minecraft.class_1531;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1810;
import net.minecraft.class_332;
import net.minecraft.class_4587;

@ModuleRegister(
   name = "Mine Helper",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Автоматически убирает кирку с руки при низкой прочности и показывает информацию о шахтах"
)
public class K6d925cb38008efb1ff extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K6d925cb38008efb1ff instance = new K6d925cb38008efb1ff();
   private final Kb9e875ead00e0d03bd durability = (new Kb9e875ead00e0d03bd("Прочность")).range(1.0F, 100.0F).value(10.0F);
   private final K00fef91b3d4d1b3ecc showWidget = (new K00fef91b3d4d1b3ecc("Показывать виджет")).value(true);
   private final Kbd66519397e9a89967 serverType = (new Kbd66519397e9a89967("Тип сервера")).value("FunTime").values("FunTime", "HolyWorld").setVisible(() -> (Boolean)this.showWidget.getValue());
   private boolean wasLowDurability = false;
   private final Pattern timePattern = Pattern.compile("^(\\d{1,2}):(\\d{2})$");
   private final Pattern timerPattern = Pattern.compile("(\\d+)\\s*мин\\.?\\s*(\\d+)\\s*сек\\.?");
   private float showProgress = 0.0F;
   private long lastRenderTime = 0L;

   public K6d925cb38008efb1ff() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.durability, this.showWidget, this.serverType});
   }

   public void onDisable() {
      super.onDisable();
      this.showProgress = 0.0F;
   }

   private String K0b104b0384d26d120e(String input) {
      return input == null ? "" : input.replaceAll("§[0-9a-fklmnor]", "").trim();
   }

   private String Kb0368d2962d4b02614(String text) {
      if (text != null && !text.isEmpty()) {
         Matcher matcher = this.timerPattern.matcher(text);
         if (matcher.find()) {
            int minutes = Integer.parseInt(matcher.group(1));
            int seconds = Integer.parseInt(matcher.group(2));
            return String.format("%02d:%02d", minutes, seconds);
         } else {
            return "";
         }
      } else {
         return "";
      }
   }

   public void onEvent() {
      K8d7165024ff567f5e7 tickEvent = Kdee350abc91aa294b2.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            class_1799 mainHand = mc.field_1724.method_6047();
            if (!mainHand.method_7960() && mainHand.method_7909() instanceof class_1810) {
               int maxDamage = mainHand.method_7936();
               int currentDamage = mainHand.method_7919();
               int remaining = maxDamage - currentDamage;
               if ((float)remaining <= this.durability.getValue()) {
                  if (!this.wasLowDurability) {
                     int currentSlot = mc.field_1724.method_31548().field_7545;
                     int targetSlot = this.K9797da2f4472311c44(currentSlot);
                     if (targetSlot != -1) {
                        mc.field_1724.method_31548().field_7545 = targetSlot;
                     }

                     this.wasLowDurability = true;
                  }
               } else {
                  this.wasLowDurability = false;
               }

            } else {
               this.wasLowDurability = false;
            }
         }
      }));
      K8d7165024ff567f5e7 render2DEvent = K14caf22f676a0204f7.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if ((Boolean)this.showWidget.getValue()) {
            if (mc.field_1724 != null && mc.field_1687 != null) {
               try {
                  class_332 context = event.context();
                  List<class_1531> armorStands = this.K7225ba8b7c1ed2b199();
                  if (this.serverType.is("FunTime")) {
                     this.Kfdf5a2a036c58f509f(context, armorStands);
                  } else if (this.serverType.is("HolyWorld")) {
                     this.K2f6955acb723a5a0c0(context, armorStands);
                  }
               } catch (Exception e) {
                  System.err.println("[MineHelper] Render error: " + e.getMessage());
                  e.printStackTrace();
               }

            }
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{tickEvent, render2DEvent});
   }

   private int K9797da2f4472311c44(int currentSlot) {
      for(int offset = 1; offset <= 8; ++offset) {
         int rightSlot = (currentSlot + offset) % 9;
         int leftSlot = (currentSlot - offset + 9) % 9;
         if (this.K3e44050aa11cf48bd4(rightSlot)) {
            return rightSlot;
         }

         if (this.K3e44050aa11cf48bd4(leftSlot)) {
            return leftSlot;
         }
      }

      return -1;
   }

   private boolean K3e44050aa11cf48bd4(int slot) {
      class_1799 stack = mc.field_1724.method_31548().method_5438(slot);
      if (stack.method_7960()) {
         return true;
      } else if (!(stack.method_7909() instanceof class_1810)) {
         return true;
      } else {
         int remaining = stack.method_7936() - stack.method_7919();
         return (float)remaining > this.durability.getValue();
      }
   }

   private List<class_1531> K7225ba8b7c1ed2b199() {
      return (List)mc.field_1687.method_8390(class_1531.class, mc.field_1724.method_5829().method_1014((double)24.0F), class_1297::method_16914).stream().sorted(Comparator.comparingDouble((e) -> (double)e.method_5739(mc.field_1724))).collect(Collectors.toList());
   }

   private void Kfdf5a2a036c58f509f(class_332 context, List<class_1531> armorStands) {
      String mineName = "";

      for(class_1531 entity : armorStands) {
         String clean = this.K0b104b0384d26d120e(entity.method_5477().getString());
         if (clean.contains("Следующая:")) {
            mineName = clean.replace("Следующая:", "").trim();
            break;
         }
      }

      String timerValue = "";

      for(class_1531 entity : armorStands) {
         String clean = this.K0b104b0384d26d120e(entity.method_5477().getString());
         if (clean.contains("Обновление через:")) {
            class_1531 closestTimer = null;
            double bestDistance = Double.MAX_VALUE;

            for(class_1531 candidate : armorStands) {
               String candidateClean = this.K0b104b0384d26d120e(candidate.method_5477().getString());
               if (this.timePattern.matcher(candidateClean).matches()) {
                  double dy = Math.abs(candidate.method_23318() - entity.method_23318());
                  if (dy < bestDistance) {
                     bestDistance = dy;
                     closestTimer = candidate;
                  }
               }
            }

            if (closestTimer != null) {
               String timerClean = this.K0b104b0384d26d120e(closestTimer.method_5477().getString());
               Matcher matcher = this.timePattern.matcher(timerClean);
               if (matcher.matches()) {
                  int minutes = Integer.parseInt(matcher.group(1));
                  int seconds = Integer.parseInt(matcher.group(2));
                  timerValue = String.format("%02d:%02d", minutes, seconds);
               }
            }
            break;
         }
      }

      if (timerValue.isEmpty()) {
         for(class_1531 entity : armorStands) {
            String clean = this.K0b104b0384d26d120e(entity.method_5477().getString());
            Matcher matcher = this.timePattern.matcher(clean);
            if (matcher.matches()) {
               int minutes = Integer.parseInt(matcher.group(1));
               int seconds = Integer.parseInt(matcher.group(2));
               timerValue = String.format("%02d:%02d", minutes, seconds);
               break;
            }
         }
      }

      String info = mineName.isEmpty() && timerValue.isEmpty() ? "—" : mineName + (timerValue.isEmpty() ? "" : " " + timerValue);
      this.Kcc8ce0c2b7a693477c(context, "Авто Шахта", info, timerValue);
   }

   private void K2f6955acb723a5a0c0(class_332 context, List<class_1531> armorStands) {
      String timer = "";

      for(class_1531 entity : armorStands) {
         String clean = this.K0b104b0384d26d120e(entity.method_5477().getString());
         if (clean.contains("осталось:")) {
            String parsed = this.Kb0368d2962d4b02614(clean);
            if (!parsed.isEmpty()) {
               timer = parsed;
               break;
            }
         }
      }

      if (timer.isEmpty()) {
         for(class_1531 entity : armorStands) {
            String clean = this.K0b104b0384d26d120e(entity.method_5477().getString());
            Matcher matcher = this.timePattern.matcher(clean);
            if (matcher.matches()) {
               int minutes = Integer.parseInt(matcher.group(1));
               int seconds = Integer.parseInt(matcher.group(2));
               timer = String.format("%02d:%02d", minutes, seconds);
               break;
            }
         }
      }

      List<class_1531> mineNameStands = new ArrayList();

      for(class_1531 entity : armorStands) {
         String clean = this.K0b104b0384d26d120e(entity.method_5477().getString());
         if (!clean.isEmpty() && !clean.matches("(?i).*(шахта|обновления|осталось|до\\s*обновления|▲|▼|опыт|навыки|/mineskills|авто-шахта|блуждающий|хранитель|покупка|выполняйте|очки|бустеры|квестовик|верху|энда|ада|энд|мир|опытный|драгоценных|возможностей|невероятных).*") && (clean.length() > 2 || clean.matches(".*[а-яА-Яa-zA-Z].*")) && !clean.matches("[0-9!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?\\\\|`~]+")) {
            mineNameStands.add(entity);
         }
      }

      mineNameStands.sort(Comparator.comparingDouble((e) -> (double)e.method_5739(mc.field_1724)));
      String currentMine = "";
      String nextMine = "";
      if (!mineNameStands.isEmpty()) {
         currentMine = this.K0b104b0384d26d120e(((class_1531)mineNameStands.get(0)).method_5477().getString());
         if (mineNameStands.size() > 1) {
            nextMine = this.K0b104b0384d26d120e(((class_1531)mineNameStands.get(1)).method_5477().getString());
         }
      }

      if (currentMine.isEmpty()) {
         class_1531 currentAnchor = null;
         class_1531 nextAnchor = null;

         for(class_1531 entity : armorStands) {
            String clean = this.K0b104b0384d26d120e(entity.method_5477().getString());
            if (clean.contains("Текущая шахта:")) {
               currentAnchor = entity;
            } else if (clean.contains("Следующая шахта:")) {
               nextAnchor = entity;
            }
         }

         if (currentAnchor != null) {
            currentMine = this.K06116a3ce34d638a40(armorStands, currentAnchor);
         }

         if (nextAnchor != null) {
            nextMine = this.K06116a3ce34d638a40(armorStands, nextAnchor);
         }
      }

      StringBuilder info = new StringBuilder();
      if (!currentMine.isEmpty()) {
         info.append("Текущая: ").append(currentMine);
      }

      if (!nextMine.isEmpty()) {
         if (info.length() > 0) {
            info.append(" | ");
         }

         info.append("Следующая: ").append(nextMine);
      }

      if (!timer.isEmpty()) {
         if (info.length() > 0) {
            info.append(" ");
         }

         info.append(timer);
      }

      String finalInfo = info.length() == 0 ? "—" : info.toString();
      this.Kcc8ce0c2b7a693477c(context, "Авто Шахта", finalInfo, timer);
   }

   private String K06116a3ce34d638a40(List<class_1531> armorStands, class_1531 anchor) {
      String bestName = "";
      double bestDist = Double.MAX_VALUE;

      for(class_1531 entity : armorStands) {
         if (entity != anchor) {
            String clean = this.K0b104b0384d26d120e(entity.method_5477().getString());
            if (!clean.isEmpty() && !clean.matches("(?i).*(шахта|обновления|осталось|до\\s*обновления|▲|▼|опыт|навыки|/mineskills|авто-шахта|блуждающий|хранитель|покупка|выполняйте|очки|бустеры|квестовик|верху|энда|ада|энд|мир|опытный|драгоценных|возможностей|невероятных).*") && (clean.length() > 2 || clean.matches(".*[а-яА-Яa-zA-Z].*"))) {
               double dist = (double)entity.method_5739(anchor);
               if (dist < bestDist) {
                  bestDist = dist;
                  bestName = clean;
               }
            }
         }
      }

      return bestName;
   }

   private void Kcc8ce0c2b7a693477c(class_332 context, String title, String info, String timer) {
      long currentTime = System.currentTimeMillis();
      if (this.lastRenderTime == 0L) {
         this.lastRenderTime = currentTime;
      }

      long delta = currentTime - this.lastRenderTime;
      this.lastRenderTime = currentTime;
      this.showProgress = Math.min(1.0F, this.showProgress + (float)delta / 300.0F);
      float alpha = this.showProgress;
      if (!(alpha < 0.01F)) {
         int screenWidth = mc.method_22683().method_4486();
         int screenHeight = mc.method_22683().method_4502();
         if (info == null || info.isEmpty()) {
            info = "—";
         }

         float paddingX = 10.0F;
         float paddingY = 8.0F;
         float iconSize = 22.0F;
         float gap = 8.0F;
         float titleSize = 8.0F;
         float infoSize = 7.0F;
         float timerSize = 7.0F;
         String timerDisplay = timer.isEmpty() ? "" : timer;
         float titleWidth = (float)mc.field_1772.method_1727(title);
         float infoWidth = (float)mc.field_1772.method_1727(info);
         float timerWidth = timerDisplay.isEmpty() ? 0.0F : (float)mc.field_1772.method_1727(timerDisplay) + 4.0F;
         float textWidth = Math.max(titleWidth, infoWidth) + timerWidth;
         float totalWidth = paddingX * 2.0F + iconSize + gap + textWidth + (timerDisplay.isEmpty() ? 0.0F : 6.0F);
         float totalHeight = paddingY * 2.0F + Math.max(iconSize, titleSize + infoSize + 4.0F);
         float centerX = (float)screenWidth / 2.0F;
         float topY = (float)screenHeight / 7.5F;
         float x = centerX - totalWidth / 2.0F;
         float y = topY - 18.0F;
         class_4587 matrices = context.method_51448();
         float round = 8.0F;
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, x - 2.0F, y - 2.0F, totalWidth + 4.0F, totalHeight + 4.0F, round + 2.0F, new Color(0, 0, 0, (int)(80.0F * alpha)));
         K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, x, y, totalWidth, totalHeight, round, new Color(15, 17, 22, (int)(240.0F * alpha)));
         K2b5e2c5f2b9891b450.RECT.draw(matrices, x - 0.5F, y - 0.5F, totalWidth + 1.0F, totalHeight + 1.0F, round + 0.5F, new Color(255, 255, 255, (int)(12.0F * alpha)));
         float lineW = totalWidth * 0.3F;
         float lineH = 2.5F;
         Color gradLeft = K258ef67f9722180a72.getAnimatedColor((int)(255.0F * alpha));
         Color gradRight = K258ef67f9722180a72.getAnimatedColor((int)(200.0F * alpha));
         K2b5e2c5f2b9891b450.GRADIENT_RECT.draw(matrices, x + totalWidth - lineW - 6.0F, y, lineW, lineH, 1.5F, gradLeft, gradRight, gradLeft, gradRight);
         class_1799 pickaxe = new class_1799(class_1802.field_8377);
         float iconX = x + paddingX;
         float iconY = y + (totalHeight - iconSize) / 2.0F;
         matrices.method_22903();
         matrices.method_46416(iconX, iconY, 0.0F);
         matrices.method_22905(iconSize / 16.0F, iconSize / 16.0F, 1.0F);
         context.method_51427(pickaxe, 0, 0);
         matrices.method_22909();
         float textX = iconX + iconSize + gap;
         float titleY = y + paddingY;
         float infoY = titleY + titleSize + 3.0F;
         context.method_51433(mc.field_1772, title, (int)textX, (int)titleY, (new Color(220, 220, 230, (int)(255.0F * alpha))).getRGB(), true);
         context.method_51433(mc.field_1772, info, (int)textX, (int)infoY, (new Color(200, 205, 215, (int)(255.0F * alpha))).getRGB(), true);
         if (!timerDisplay.isEmpty()) {
            float timerX = x + totalWidth - paddingX - timerWidth - 4.0F;
            float timerY = y + (totalHeight - timerSize) / 2.0F;
            float timerBgW = timerWidth + 8.0F;
            float timerBgH = timerSize + 6.0F;
            K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, timerX - 4.0F, timerY - 3.0F, timerBgW, timerBgH, 4.0F, new Color(0, 0, 0, (int)(120.0F * alpha)));

            Color timerColor;
            try {
               String[] parts = timerDisplay.split(":");
               int mins = Integer.parseInt(parts[0]);
               int secs = Integer.parseInt(parts[1]);
               int totalSecs = mins * 60 + secs;
               if (totalSecs < 10) {
                  timerColor = new Color(255, 80, 80);
               } else if (totalSecs < 30) {
                  timerColor = new Color(255, 200, 50);
               } else {
                  timerColor = new Color(100, 255, 100);
               }
            } catch (Exception var51) {
               timerColor = new Color(200, 200, 200);
            }

            context.method_51433(mc.field_1772, timerDisplay, (int)timerX, (int)timerY, K80ee108ea129adac11.setAlpha(timerColor, (int)(255.0F * alpha)).getRGB(), true);
         }

         RenderSystem.disableBlend();
      }
   }

   @Generated
   public static K6d925cb38008efb1ff getInstance() {
      return instance;
   }
}
