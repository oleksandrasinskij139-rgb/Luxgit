package code.miaware.client.ui.widget.overlay;

import code.miaware.api.system.localization.Ka35e7d6b31db9984f1;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.other.Kf5ec3d7f18b288e7f3;
import code.miaware.client.ui.clickgui.K87984c5639b98954c5;
import code.miaware.client.ui.widget.K424a42176df1675858;
import code.miaware.client.ui.widget.K71a43541bec306e7a2;
import code.miaware.client.ui.widget.K8974f150cc9dad5e95;
import code.miaware.client.ui.widget.K9c25dd69cb23ed0eb8;
import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.class_1058;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1921;
import net.minecraft.class_2477;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_408;
import net.minecraft.class_4587;
import net.minecraft.class_6880;
import net.minecraft.class_7923;

public class Kcf5c3bed12d824555f extends K424a42176df1675858 {
   private static final long WARNING_THRESHOLD_TICKS = 600L;
   private static final int BLINK_THRESHOLD_TICKS = 600;
   private static final class_2960[] BAD_EFFECTS = new class_2960[]{class_2960.method_60655("minecraft", "wither"), class_2960.method_60655("minecraft", "poison"), class_2960.method_60655("minecraft", "slowness"), class_2960.method_60655("minecraft", "weakness"), class_2960.method_60655("minecraft", "mining_fatigue"), class_2960.method_60655("minecraft", "nausea"), class_2960.method_60655("minecraft", "blindness"), class_2960.method_60655("minecraft", "hunger"), class_2960.method_60655("minecraft", "levitation"), class_2960.method_60655("minecraft", "unluck")};
   private static final class_2960[] COOL_EFFECTS = new class_2960[]{class_2960.method_60655("minecraft", "speed"), class_2960.method_60655("minecraft", "strength"), class_2960.method_60655("minecraft", "regeneration")};
   private static final class_2960[] WARNING_EFFECTS = new class_2960[]{class_2960.method_60655("minecraft", "speed"), class_2960.method_60655("minecraft", "strength"), class_2960.method_60655("minecraft", "invisibility")};
   private final Ka91715a8c4e3f3b7c4 showAnimation = new Ka91715a8c4e3f3b7c4();
   private final Map<String, K177704995a61a22217> entries = new LinkedHashMap();
   private boolean lastPreview;
   private static final Pattern LEVEL = Pattern.compile("^(.*) (\\d+)$");

   public Kcf5c3bed12d824555f() {
      super(3.0F, 120.0F);
   }

   public String getName() {
      return "Potions";
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.render(context.method_51448());
   }

   public void render(class_4587 matrixStack) {
      if (!super.isEnabled()) {
         this.getDraggable().setWidth(0.0F);
         this.getDraggable().setHeight(0.0F);
      } else {
         boolean preview = this.hudLayoutMode();
         List<K177704995a61a22217> visibleEntries = this.K403d5b07091e8370dc(preview);
         boolean shouldShow = this.K422c1bc7578e0c8cff();
         this.showAnimation.update();
         if (!preview && this.lastPreview && visibleEntries.isEmpty()) {
            this.showAnimation.setValue((double)0.0F);
         } else {
            this.showAnimation.run(shouldShow ? (double)1.0F : (double)0.0F, 220L, Kf3b62ecfba0c3544e6.QUINT_OUT);
         }

         this.lastPreview = preview;
         float widgetAlpha = (float)this.showAnimation.getValue();
         if (widgetAlpha <= 0.01F) {
            this.getDraggable().setWidth(0.0F);
            this.getDraggable().setHeight(0.0F);
         } else {
            float x = this.getDraggable().getX();
            float y = this.getDraggable().getY();
            List<K71a43541bec306e7a2> rows = new ArrayList();

            for(K177704995a61a22217 entry : visibleEntries) {
               K71a43541bec306e7a2 row = (new K71a43541bec306e7a2()).alpha(entry.K1bdf600f4b9e91e617());
               Matcher level = LEVEL.matcher(entry.name);
               if (level.matches()) {
                  row.left(level.group(1), Ka20f8cfdbc113ccdec.TEXT).left(" " + level.group(2), Ka20f8cfdbc113ccdec.ACCENT_SOFT);
               } else {
                  row.left(entry.name, Ka20f8cfdbc113ccdec.TEXT);
               }

               boolean bad = this.K7a9ac70ac9bcdd58be(entry.id);
               row.right(entry.durationText, !entry.urgent && !bad ? Ka20f8cfdbc113ccdec.ACCENT_SOFT : Ka20f8cfdbc113ccdec.RED);
               rows.add(row);
            }

            K9c25dd69cb23ed0eb8 m = this.listMetrics();
            float w = Ka20f8cfdbc113ccdec.listWidth(Ka35e7d6b31db9984f1.getInstance().translate("Potions"), rows, m, this.scaled(96.0F));
            float h = Ka20f8cfdbc113ccdec.listHeight(rows, m);
            float centerX = x + w * 0.5F;
            float centerY = y + h * 0.5F;
            float slideX = this.scaled(12.0F) * (1.0F - widgetAlpha);
            matrixStack.method_22903();
            matrixStack.method_46416(centerX + slideX, centerY, 0.0F);
            matrixStack.method_22905(widgetAlpha, widgetAlpha, 1.0F);
            matrixStack.method_46416(-centerX, -centerY, 0.0F);
            Ka20f8cfdbc113ccdec.list(matrixStack, x, y, "drop", Ka35e7d6b31db9984f1.getInstance().translate("Potions"), rows, m, this.scaled(96.0F), widgetAlpha);
            matrixStack.method_22909();
            this.getDraggable().setWidth(w);
            this.getDraggable().setHeight(h);
         }
      }
   }

   private void K8142ad912a152b5e66(class_332 context, class_2960 id, float x, float y, float size, int alpha) {
      class_6880.class_6883<class_1291> effectEntry = (class_6880.class_6883)class_7923.field_41174.method_10223(id).orElse((Object)null);
      if (effectEntry != null) {
         class_1058 sprite = mc.method_18505().method_18663(effectEntry);
         if (sprite != null) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, (float)alpha / 255.0F);
            context.method_52709(class_1921::method_62277, sprite, Math.round(x), Math.round(y), Math.round(size), Math.round(size));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         }
      }
   }

   private boolean K422c1bc7578e0c8cff() {
      boolean inChat = mc.field_1755 instanceof class_408;
      if (inChat) {
         return true;
      } else if (mc.field_1724 == null) {
         return false;
      } else {
         return !mc.field_1724.method_6088().isEmpty();
      }
   }

   protected Map<String, K8974f150cc9dad5e95> getCurrentData() {
      return new HashMap();
   }

   private List<K177704995a61a22217> K403d5b07091e8370dc(boolean preview) {
      if (!this.K422c1bc7578e0c8cff() && !preview) {
         this.entries.clear();
         return new ArrayList();
      } else {
         Map<String, Kc6773ac34c8c8cb28f> snapshots = !preview || mc.field_1724 != null && !mc.field_1724.method_6088().isEmpty() ? this.Kd69380021d8a6228b7() : this.K7274656f25e33a02cd();

         for(K177704995a61a22217 entry : this.entries.values()) {
            entry.animation.update();
            entry.seenThisFrame = false;
         }

         for(Kc6773ac34c8c8cb28f snapshot : snapshots.values()) {
            K177704995a61a22217 entry = (K177704995a61a22217)this.entries.computeIfAbsent(snapshot.key, (key) -> new K177704995a61a22217(snapshot));
            entry.Ke33b9991896e8324ba(snapshot);
            entry.seenThisFrame = true;
            if (!preview) {
               this.K6f235a05b37d09661b(entry, snapshot);
            }
         }

         this.entries.entrySet().removeIf((entryx) -> {
            K177704995a61a22217 potionEntry = (K177704995a61a22217)entryx.getValue();
            if (!potionEntry.seenThisFrame) {
               potionEntry.K306b07533b251fa9de();
            }

            return potionEntry.K000a5af5a3585a165d();
         });
         List<K177704995a61a22217> visibleEntries = new ArrayList(this.entries.values());
         visibleEntries.sort(Comparator.comparing(K177704995a61a22217::K5e1328c0b1b203e486).thenComparing(Comparator.comparingInt(K177704995a61a22217::K0de2006ca73eeaf2ec).reversed()).thenComparing((entryx) -> entryx.name));
         return visibleEntries;
      }
   }

   private Map<String, Kc6773ac34c8c8cb28f> Kd69380021d8a6228b7() {
      Map<String, Kc6773ac34c8c8cb28f> snapshots = new LinkedHashMap();
      if (mc.field_1724 == null) {
         return snapshots;
      } else {
         for(class_1293 effect : mc.field_1724.method_6088().values()) {
            class_2960 id = (class_2960)effect.method_5579().method_40230().map((keyx) -> keyx.method_29177()).orElse(class_2960.method_60655("minecraft", "unknown"));
            int amplifier = effect.method_5578();
            int durationTicks = effect.method_5584();
            boolean infinite = effect.method_48559();
            String levelSuffix = amplifier > 0 ? " " + (amplifier + 1) : "";
            String var10000 = this.K1ab0ab984b138e2b3c(id, effect.method_5586());
            String name = var10000 + levelSuffix;
            String durationText = infinite ? "**:**" : Kf5ec3d7f18b288e7f3.getDurationText(durationTicks);
            var10000 = String.valueOf(id);
            String key = var10000 + ":" + amplifier;
            snapshots.put(key, new Kc6773ac34c8c8cb28f(key, id, name, durationText, this.K66f28fb561cfa3aad3(id, durationTicks, infinite), this.Kcab22b15846ecbe89c(id, durationTicks, infinite), durationTicks, this.K35bd79836a2be4dde6(id, durationTicks, infinite), infinite));
         }

         return snapshots;
      }
   }

   private Map<String, Kc6773ac34c8c8cb28f> K7274656f25e33a02cd() {
      Map<String, Kc6773ac34c8c8cb28f> snapshots = new LinkedHashMap();
      snapshots.put("preview_speed", new Kc6773ac34c8c8cb28f("preview_speed", class_2960.method_60655("minecraft", "speed"), this.K1ab0ab984b138e2b3c(class_2960.method_60655("minecraft", "speed"), "effect.minecraft.speed") + " 2", "1:24", this.K66f28fb561cfa3aad3(class_2960.method_60655("minecraft", "speed"), 1680, false), this.Kcab22b15846ecbe89c(class_2960.method_60655("minecraft", "speed"), 1680, false), 1680, false, false));
      snapshots.put("preview_strength", new Kc6773ac34c8c8cb28f("preview_strength", class_2960.method_60655("minecraft", "strength"), this.K1ab0ab984b138e2b3c(class_2960.method_60655("minecraft", "strength"), "effect.minecraft.strength"), "0:27", this.K66f28fb561cfa3aad3(class_2960.method_60655("minecraft", "strength"), 540, false), this.Kcab22b15846ecbe89c(class_2960.method_60655("minecraft", "strength"), 540, false), 540, true, false));
      snapshots.put("preview_poison", new Kc6773ac34c8c8cb28f("preview_poison", class_2960.method_60655("minecraft", "poison"), this.K1ab0ab984b138e2b3c(class_2960.method_60655("minecraft", "poison"), "effect.minecraft.poison"), "0:04", this.K66f28fb561cfa3aad3(class_2960.method_60655("minecraft", "poison"), 80, false), this.Kcab22b15846ecbe89c(class_2960.method_60655("minecraft", "poison"), 80, false), 80, true, false));
      return snapshots;
   }

   private void K6f235a05b37d09661b(K177704995a61a22217 entry, Kc6773ac34c8c8cb28f snapshot) {
      if (Kb7ca6c2b4209b8a116.isPotionAlertsEnabled() && this.Kfe638d3e79a30390b4(snapshot.id)) {
         if (!snapshot.infinite && (long)snapshot.durationTicks <= 640L) {
            if (!entry.warned && snapshot.durationTicks > 0 && (long)snapshot.durationTicks <= 600L) {
               entry.warned = true;
               Kb7ca6c2b4209b8a116.notify("Эффект " + snapshot.name + " закончится через 30 секунд", Ka58c9e2c4d8aa9af2e.WARNING);
            }
         } else {
            entry.warned = false;
         }
      }
   }

   private Color K66f28fb561cfa3aad3(class_2960 id, int durationTicks, boolean infinite) {
      Color textColor = Kb18d549c13309af9a5.textColor();
      if (infinite) {
         return textColor;
      } else if (this.K7a9ac70ac9bcdd58be(id)) {
         return Kb18d549c13309af9a5.negativeColor();
      } else if (durationTicks > 0 && (long)durationTicks <= 600L) {
         return Kb18d549c13309af9a5.negativeColor();
      } else {
         return this.K45088f8afb28ab6830(id) ? K80ee108ea129adac11.interpolate(K87984c5639b98954c5.accent(255), textColor, 0.18F) : textColor;
      }
   }

   private Color Kcab22b15846ecbe89c(class_2960 id, int durationTicks, boolean infinite) {
      if (infinite) {
         return K87984c5639b98954c5.accent(210);
      } else if (this.K7a9ac70ac9bcdd58be(id)) {
         return Kb18d549c13309af9a5.negativeColor();
      } else if (durationTicks > 0 && (long)durationTicks <= 600L) {
         return Kb18d549c13309af9a5.negativeColor();
      } else {
         return this.K45088f8afb28ab6830(id) ? K87984c5639b98954c5.accent(210) : K87984c5639b98954c5.accent(210);
      }
   }

   private String K1ab0ab984b138e2b3c(class_2960 id, String translationKey) {
      String var10000;
      switch (id.toString()) {
         case "minecraft:speed" -> var10000 = "Скорость";
         case "minecraft:strength" -> var10000 = "Сила";
         case "minecraft:regeneration" -> var10000 = "Регенерация";
         case "minecraft:invisibility" -> var10000 = "Невидимость";
         case "minecraft:fire_resistance" -> var10000 = "Огнестойкость";
         case "minecraft:absorption" -> var10000 = "Поглощение";
         case "minecraft:resistance" -> var10000 = "Сопротивление";
         case "minecraft:haste" -> var10000 = "Спешка";
         case "minecraft:wither" -> var10000 = "Иссушение";
         case "minecraft:poison" -> var10000 = "Отравление";
         case "minecraft:slowness" -> var10000 = "Замедление";
         case "minecraft:weakness" -> var10000 = "Слабость";
         case "minecraft:mining_fatigue" -> var10000 = "Усталость";
         case "minecraft:nausea" -> var10000 = "Тошнота";
         case "minecraft:blindness" -> var10000 = "Слепота";
         case "minecraft:hunger" -> var10000 = "Голод";
         case "minecraft:levitation" -> var10000 = "Левитация";
         case "minecraft:unluck" -> var10000 = "Невезение";
         default -> var10000 = class_2477.method_10517().method_48307(translationKey);
      }

      return var10000;
   }

   private boolean K7a9ac70ac9bcdd58be(class_2960 id) {
      for(class_2960 badId : BAD_EFFECTS) {
         if (badId.equals(id)) {
            return true;
         }
      }

      return false;
   }

   private boolean K45088f8afb28ab6830(class_2960 id) {
      for(class_2960 coolId : COOL_EFFECTS) {
         if (coolId.equals(id)) {
            return true;
         }
      }

      return false;
   }

   private boolean Kfe638d3e79a30390b4(class_2960 id) {
      for(class_2960 warningId : WARNING_EFFECTS) {
         if (warningId.equals(id)) {
            return true;
         }
      }

      return false;
   }

   private boolean K35bd79836a2be4dde6(class_2960 id, int durationTicks, boolean infinite) {
      if (infinite) {
         return false;
      } else if (this.K7a9ac70ac9bcdd58be(id)) {
         return durationTicks > 0 && durationTicks <= 600;
      } else {
         return durationTicks > 0 && durationTicks <= 600 && this.Kfe638d3e79a30390b4(id);
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
