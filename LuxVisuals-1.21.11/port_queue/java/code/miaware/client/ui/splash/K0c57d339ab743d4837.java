package code.miaware.client.ui.splash;

import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.fonts.K5fe84dad3db1af4bb7;
import code.miaware.api.utils.render.fonts.K896065ea0a2bba4f47;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_442;
import net.minecraft.class_4587;

public class K0c57d339ab743d4837 extends class_437 {
   private static final String[] STAGE_LABELS = new String[]{"Bootstrapping environment", "Loading core systems", "Initializing modules", "Loading configuration", "Compiling resources", "Setting up UI", "Connecting services", "Finalizing startup"};
   private final long startTime = System.currentTimeMillis();
   private int currentStage = 0;
   private float progress = 0.0F;
   private float targetProgress = 0.0F;
   private final List<String> logLines = new ArrayList();
   private long nextLogTime = 0L;
   private int logIndex = 0;
   private boolean transitioning = false;
   private float fadeAlpha = 0.0F;
   private final Random random = new Random();
   private final List<K5d24469d971406353c> particles = new ArrayList();
   private K5fe84dad3db1af4bb7 boldFont;
   private K5fe84dad3db1af4bb7 mediumFont;

   public K0c57d339ab743d4837() {
      super(class_2561.method_43470("Loading"));
      this.Kd6496b560c1fc1dcaf();
   }

   private void Kd6496b560c1fc1dcaf() {
      this.logLines.add("> Initializing MiaWare client...");
      this.logLines.add("> Fabric API detected");
      this.nextLogTime = System.currentTimeMillis() + 100L;
   }

   protected void method_25426() {
      this.boldFont = K896065ea0a2bba4f47.PS_BOLD;
      this.mediumFont = K896065ea0a2bba4f47.PS_MEDIUM;
      this.particles.clear();

      for(int i = 0; i < 40; ++i) {
         this.particles.add(new K5d24469d971406353c(this));
      }

   }

   private void K679294194307cd4c09() {
      long now = System.currentTimeMillis();
      if (this.currentStage >= STAGE_LABELS.length) {
         this.targetProgress = 1.0F;
         if (this.progress > 0.99F && !this.transitioning) {
            this.transitioning = true;
         }

      } else {
         if (now >= this.nextLogTime && this.logIndex < this.Kd519f8c46e7ff785ca(this.currentStage)) {
            this.K336c73ec5cdc43b722(this.currentStage, this.logIndex);
            ++this.logIndex;
            this.nextLogTime = now + 80L + (long)this.random.nextInt(60);
         }

         long stageTime = (long)(350 + this.currentStage * 50);
         float stageProgress = Math.min(1.0F, (float)(now - this.startTime) / (float)((long)STAGE_LABELS.length * stageTime));
         if (now - this.startTime > (long)(this.currentStage + 1) * stageTime && this.currentStage < STAGE_LABELS.length) {
            String var10001 = STAGE_LABELS[this.currentStage];
            this.logLines.add("✓ " + var10001);
            ++this.currentStage;
            this.logIndex = 0;
         }

         this.targetProgress = Math.min(1.0F, stageProgress);
      }
   }

   private int Kd519f8c46e7ff785ca(int stage) {
      byte var10000;
      switch (stage) {
         case 0 -> var10000 = 2;
         case 1 -> var10000 = 3;
         case 2 -> var10000 = 4;
         case 3 -> var10000 = 3;
         case 4 -> var10000 = 3;
         case 5 -> var10000 = 4;
         case 6 -> var10000 = 3;
         case 7 -> var10000 = 2;
         default -> var10000 = 1;
      }

      return var10000;
   }

   private void K336c73ec5cdc43b722(int stage, int index) {
      String log0 = "Allocating resources...";
      String log1 = "JIT compiler ready";
      String log2 = "Loading managers...";
      String log3 = "Theme loaded";
      String log4 = "Services initialized";
      String log5 = "Loading modules...";
      String log6 = "Rotations registered";
      String log7 = "Combat ready";
      String log8 = "Movement ready";
      String log9 = "Loading configs...";
      String log10 = "Applying settings";
      String log11 = "Keybinds restored";
      String log12 = "Loading fonts...";
      String log13 = "Shaders compiled";
      String log14 = "Blur ready";
      String log15 = "Building UI...";
      String log16 = "ClickGUI loaded";
      String log17 = "Widgets ready";
      String log18 = "Overlays prepared";
      String log19 = "Starting RPC...";
      String log20 = "Discord connected";
      String log21 = "Heartbeat active";
      String log22 = "Done!";
      String log23 = "Ready to play";
      String[][] logs = new String[][]{{log0, log1}, {log2, log3, log4}, {log5, log6, log7, log8}, {log9, log10, log11}, {log12, log13, log14}, {log15, log16, log17, log18}, {log19, log20, log21}, {log22, log23}};
      if (stage < logs.length && index < logs[stage].length) {
         this.logLines.add(logs[stage][index]);
      }

   }

   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
      this.K679294194307cd4c09();
      class_4587 matrices = context.method_51448();
      long now = System.currentTimeMillis();
      this.progress = K20a7d0f23a80303e0a(this.progress, this.targetProgress, 0.05F);
      context.method_25294(0, 0, this.field_22789, this.field_22790, -16119278);

      for(K5d24469d971406353c p : this.particles) {
         p.update();
         p.render(context);
      }

      float titleSize = 28.0F;
      String titleText = "lux visuals";
      float titleWidth = this.boldFont.getWidth(titleText, titleSize);
      float titleX = ((float)this.field_22789 - titleWidth) / 2.0F;
      float titleY = (float)this.field_22790 / 2.0F - 70.0F;
      this.boldFont.drawText(matrices, titleText, titleX + 2.0F, titleY + 2.0F, titleSize, new Color(0, 0, 0, 128));
      this.boldFont.drawText(matrices, titleText, titleX, titleY, titleSize, Kb18d549c13309af9a5.primary());
      String stageText = this.currentStage < STAGE_LABELS.length ? STAGE_LABELS[this.currentStage] : "Done!";
      float stageWidth = this.mediumFont.getWidth(stageText, 8.0F);
      this.mediumFont.drawText(matrices, stageText, ((float)this.field_22789 - stageWidth) / 2.0F, (float)this.field_22790 / 2.0F - 35.0F, 8.0F, Kb18d549c13309af9a5.inactiveTextColor());
      float barWidth = Math.min(400.0F, (float)this.field_22789 * 0.5F);
      float barHeight = 6.0F;
      float barX = ((float)this.field_22789 - barWidth) / 2.0F;
      float barY = (float)this.field_22790 / 2.0F - 20.0F;
      Color trackColor = new Color(25, 20, 40, 200);
      K2b5e2c5f2b9891b450.RECT.draw(matrices, barX, barY, barWidth, barHeight, barHeight / 2.0F, trackColor);
      float fillWidth = Math.max(barHeight, barWidth * this.progress);
      Color fillColor = Kb18d549c13309af9a5.primary();
      K2b5e2c5f2b9891b450.RECT.draw(matrices, barX, barY, fillWidth, barHeight, barHeight / 2.0F, fillColor);
      float shimmerPos = (float)(now % 1500L) / 1500.0F * (fillWidth + 40.0F) - 20.0F;
      if (shimmerPos > 0.0F && shimmerPos < fillWidth) {
         Color shimmerColor = new Color(255, 255, 255, 40);
         K2b5e2c5f2b9891b450.RECT.draw(matrices, barX + shimmerPos - 10.0F, barY - 1.0F, 20.0F, barHeight + 2.0F, barHeight / 2.0F, shimmerColor);
      }

      String percent = (int)(this.progress * 100.0F) + "%";
      float pctWidth = this.mediumFont.getWidth(percent, 7.0F);
      this.mediumFont.drawText(matrices, percent, ((float)this.field_22789 - pctWidth) / 2.0F, barY + barHeight + 6.0F, 7.0F, Kb18d549c13309af9a5.textColor());
      float logWidth = Math.min(450.0F, (float)this.field_22789 * 0.6F);
      float logHeight = 70.0F;
      float logX = ((float)this.field_22789 - logWidth) / 2.0F;
      float logY = barY + barHeight + 25.0F;
      Color logBgColor = new Color(12, 10, 25, 180);
      K2b5e2c5f2b9891b450.RECT.draw(matrices, logX, logY, logWidth, logHeight, 4.0F, logBgColor);
      int maxLines = 5;
      int startLine = Math.max(0, this.logLines.size() - maxLines);
      float logFontSize = 6.0F;

      for(int i = startLine; i < this.logLines.size(); ++i) {
         String line = (String)this.logLines.get(i);
         Color lineColor = i == this.logLines.size() - 1 ? Kb18d549c13309af9a5.textColor() : Kb18d549c13309af9a5.inactiveTextColor();
         this.mediumFont.drawText(matrices, line, logX + 10.0F, logY + 5.0F + (float)((i - startLine) * 11), logFontSize, lineColor);
      }

      if (now / 500L % 2L == 0L && this.currentStage < STAGE_LABELS.length) {
         String lastLog = this.logLines.isEmpty() ? "" : (String)this.logLines.get(this.logLines.size() - 1);
         float cursorX = logX + 10.0F + this.mediumFont.getWidth(lastLog, logFontSize);
         float cursorY = logY + 5.0F + (float)Math.min(this.logLines.size(), maxLines - 1) * 11.0F;
         if (cursorY < logY + logHeight - 10.0F) {
            this.mediumFont.drawText(matrices, "_", cursorX, cursorY, logFontSize, Kb18d549c13309af9a5.primary());
         }
      }

      String versionText = "v0.0.3";
      float versionWidth = this.mediumFont.getWidth(versionText, 6.0F);
      this.mediumFont.drawText(matrices, versionText, (float)this.field_22789 - versionWidth - 10.0F, (float)(this.field_22790 - 16), 6.0F, Kb18d549c13309af9a5.inactiveTextColor());
      if (this.transitioning) {
         this.fadeAlpha = Math.min(1.0F, this.fadeAlpha + 0.03F);
         if (this.fadeAlpha >= 0.99F) {
            class_442 titleScreen = new class_442();
            class_310.method_1551().method_1507(titleScreen);
         }
      }

      if (this.fadeAlpha > 0.0F) {
         context.method_25294(0, 0, this.field_22789, this.field_22790, (int)(this.fadeAlpha * 255.0F) << 24);
      }

   }

   public boolean method_25422() {
      return false;
   }

   public boolean method_25421() {
      return false;
   }

   private static float K20a7d0f23a80303e0a(float a, float b, float t) {
      return a + (b - a) * t;
   }
}
