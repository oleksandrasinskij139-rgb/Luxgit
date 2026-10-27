package code.miaware.client.ui.swap;

import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.render.fonts.K5fe84dad3db1af4bb7;
import code.miaware.api.utils.render.fonts.K896065ea0a2bba4f47;
import code.miaware.api.utils.render.fonts.Kd81f0583cabc01b39d;
import code.miaware.client.features.modules.combat.tripleswap.K5bb032ff100ed9755b;
import code.miaware.client.features.modules.combat.tripleswap.Kb64b8db37bafed12ae;
import code.miaware.client.util.render.effects.K2c4b0ab5cb7fa68cf2;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_10185;
import net.minecraft.class_1109;
import net.minecraft.class_124;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3675;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_3675.class_307;
import org.lwjgl.glfw.GLFW;

public final class Kb7a870ae80057bb597 extends class_437 {
   private final K5bb032ff100ed9755b controller;
   private final K2c4b0ab5cb7fa68cf2 renderer = new K2c4b0ab5cb7fa68cf2();
   private final int bind;
   private final float[] hover = new float[3];
   private final String[] labelNames = new String[3];
   private final List<String>[] labelLines = new List[3];
   private long opened;
   private long lastFrame;
   private long closeTime;
   private float progress;
   private float closeFrom;
   private int hovered = -1;
   private boolean closing;
   private boolean shaderFailed;

   public Kb7a870ae80057bb597(K5bb032ff100ed9755b controller, int bind) {
      super(class_2561.method_43470("Тройной свап"));
      this.controller = controller;
      this.bind = bind;
   }

   protected void method_25426() {
      this.opened = this.lastFrame = System.nanoTime();
      this.closing = false;
      this.progress = 0.0F;
      this.hovered = -1;
      this.K5c845d8b8a2a29e4d4(0.6F);
   }

   private void K5c845d8b8a2a29e4d4(float pitch) {
      if (this.field_22787 != null) {
         this.field_22787.method_1483().method_4873(class_1109.method_4757((class_3414)class_3417.field_15015.comp_349(), pitch, 0.3F));
      }

   }

   private float K45191ff3da8fe09c25() {
      return (float)this.field_22789 / 2.0F;
   }

   private float Kf2cad10de7f9f8cf62() {
      return (float)this.field_22790 / 2.0F + 12.0F;
   }

   private float scale() {
      return this.closing ? this.progress : 0.88F + 0.12F * this.progress;
   }

   private int Kc81da1b362519794b7(double x, double y) {
      return Kb64b8db37bafed12ae.sectorAt(x - (double)this.K45191ff3da8fe09c25(), y - (double)this.Kf2cad10de7f9f8cf62(), this.scale());
   }

   private void K515236525d7fa1451f() {
      if (!this.closing) {
         this.closing = true;
         this.closeTime = System.nanoTime();
         this.closeFrom = this.progress;
      }

   }

   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
      long now = System.nanoTime();
      float dt = Math.min(0.1F, (float)(now - this.lastFrame) / 1.0E9F);
      this.lastFrame = now;
      if (this.closing) {
         this.progress = this.closeFrom * (float)Math.pow((double)(1.0F - Math.min(1.0F, (float)(now - this.closeTime) / 1.8E8F)), (double)4.0F);
      } else {
         this.progress = 1.0F - (float)Math.pow((double)(1.0F - Math.min(1.0F, (float)(now - this.opened) / 2.2E8F)), (double)4.0F);
      }

      if (this.closing && this.progress <= 0.02F) {
         if (this.field_22787.field_1755 == this) {
            this.field_22787.method_1507((class_437)null);
         }

      } else {
         int next = this.closing ? -1 : this.Kc81da1b362519794b7((double)mouseX, (double)mouseY);
         if (next != this.hovered && next >= 0) {
            this.K5c845d8b8a2a29e4d4(1.4F);
         }

         this.hovered = next;
         float blend = 1.0F - (float)Math.exp((double)(-17.3F * dt));

         for(int i = 0; i < 3; ++i) {
            float[] var10000 = this.hover;
            var10000[i] += ((this.hovered == i ? 1.0F : 0.0F) - this.hover[i]) * blend;
         }

         Color theme = Kb18d549c13309af9a5.primary();
         int alpha = (int)(255.0F * this.progress);
         context.method_25294(0, 0, this.field_22789, this.field_22790, (int)(55.0F * this.progress) << 24 | (int)((float)theme.getRed() * 0.08F) << 16 | (int)((float)theme.getGreen() * 0.08F) << 8 | (int)((float)theme.getBlue() * 0.08F));
         context.method_51452();
         if (!this.shaderFailed) {
            try {
               this.renderer.render((float)this.field_22787.method_22683().method_4489(), (float)this.field_22787.method_22683().method_4506(), this.K45191ff3da8fe09c25(), this.Kf2cad10de7f9f8cf62(), (float)this.field_22787.method_22683().method_4495(), this.scale(), this.progress, this.hovered, this.hover, theme.getRGB());
            } catch (RuntimeException e) {
               this.shaderFailed = true;
               System.err.println("[TripleSwap] Wheel shader: " + e.getMessage());
            }
         }

         class_4587 matrices = context.method_51448();
         float outer = 112.0F * this.scale();
         this.K14e7c9918d36bf431e(matrices, K896065ea0a2bba4f47.SF_SEMIBOLD, "Тройной свап", this.K45191ff3da8fe09c25(), this.Kf2cad10de7f9f8cf62() - outer - 31.0F, 13.5F, new Color(255, 250, 250, alpha));
         String help = "ЛКМ — свап   ПКМ — заменить   Esc — закрыть";
         float helpSize = Math.min(8.5F, 8.5F * (float)Math.max(1, this.field_22789 - 24) / Math.max(1.0F, K896065ea0a2bba4f47.SF_MEDIUM.getWidth(help, 8.5F)));
         this.K14e7c9918d36bf431e(matrices, K896065ea0a2bba4f47.SF_MEDIUM, help, this.K45191ff3da8fe09c25(), this.Kf2cad10de7f9f8cf62() - outer - 16.0F, helpSize, new Color(222, 216, 222, (int)(210.0F * this.progress)));

         for(int i = 0; i < 3; ++i) {
            double angle = (double)i * Math.PI * (double)2.0F / (double)3.0F - (Math.PI / 2D);
            float radius = (76.0F + this.hover[i] * 4.5F) * this.scale();
            float x = this.K45191ff3da8fe09c25() + (float)Math.cos(angle) * radius;
            float y = this.Kf2cad10de7f9f8cf62() + (float)Math.sin(angle) * radius;
            if (!this.controller.has(i)) {
               float len = (11.0F + this.hover[i] * 2.0F) * this.scale();
               float th = 2.6F * this.scale();
               context.method_25294((int)(x - len), (int)(y - th / 2.0F), (int)(x + len), (int)(y + th / 2.0F), alpha << 24 | 16777215);
               context.method_25294((int)(x - th / 2.0F), (int)(y - len), (int)(x + th / 2.0F), (int)(y + len), alpha << 24 | 16777215);
            } else {
               String name = class_124.method_539(this.controller.name(i)).replace('\n', ' ').strip();
               if (!name.equals(this.labelNames[i])) {
                  this.labelNames[i] = name;
                  this.labelLines[i] = this.K11e1eed331661e4ef2(name, i == 0 ? 136.0F : 62.0F);
               }

               List<String> lines = this.labelLines[i];
               float labelX = this.K45191ff3da8fe09c25() + (float)(i == 0 ? 0 : (i == 1 ? 54 : -54)) * this.scale();
               float labelY = this.Kf2cad10de7f9f8cf62() + (float)(i == 0 ? -51 : 63) * this.scale();
               float firstY = labelY - (float)(lines.size() - 1) * 5.5F * this.scale();

               for(int line = 0; line < lines.size(); ++line) {
                  this.K14e7c9918d36bf431e(matrices, K896065ea0a2bba4f47.SF_MEDIUM, (String)lines.get(line), labelX, firstY + (float)(line * 11) * this.scale(), 8.5F * this.scale(), new Color(246, 246, 250, alpha));
               }

               class_1799 stack = this.controller.icon(i);
               if (!stack.method_7960() && this.progress > 0.15F) {
                  float s = (1.7F + this.hover[i] * 0.25F) * this.scale();
                  matrices.method_22903();
                  matrices.method_46416(x, y - 4.0F * this.scale(), 0.0F);
                  matrices.method_22905(s, s, 1.0F);
                  context.method_51427(stack, -8, -8);
                  matrices.method_22909();
               }
            }
         }

         String hint = this.hovered < 0 ? "" : (!this.controller.has(this.hovered) ? "Добавить" : (this.controller.inOffhand(this.hovered) ? "В руке" : "Свапнуть"));
         if (!hint.isEmpty()) {
            this.K14e7c9918d36bf431e(matrices, K896065ea0a2bba4f47.SF_SEMIBOLD, hint, this.K45191ff3da8fe09c25(), this.Kf2cad10de7f9f8cf62(), 9.0F * this.scale(), new Color(255, 255, 255, (int)(245.0F * this.progress)));
         }

      }
   }

   private void K14e7c9918d36bf431e(class_4587 matrices, K5fe84dad3db1af4bb7 font, String text, float centerX, float centerY, float size, Color color) {
      if (!text.isEmpty() && !(size <= 0.0F) && color.getAlpha() != 0) {
         float top = Float.POSITIVE_INFINITY;
         float bottom = Float.NEGATIVE_INFINITY;

         for(int i = 0; i < text.length(); ++i) {
            Kd81f0583cabc01b39d glyph = font.getGlyph(text.charAt(i));
            if (glyph != null && glyph.getQuadHeight() != 0.0F) {
               top = Math.min(top, -glyph.getTopPosition() * size);
               bottom = Math.max(bottom, (glyph.getQuadHeight() - glyph.getTopPosition()) * size);
            }
         }

         if (Float.isFinite(top)) {
            float sf = (float)this.field_22787.method_22683().method_4495();
            float x = (float)Math.round((centerX - font.getWidth(text, size) / 2.0F) * sf) / sf;
            float baseline = (float)Math.round((centerY - (top + bottom) / 2.0F) * sf) / sf;
            float y = baseline - font.getMetrics().baselineHeight() * size;
            font.drawText(matrices, text, x, y, size, 0.0F, color.getRGB(), -1, -1.0F, 0.5F, 0.0F, -1, 0.0F);
         }
      }
   }

   private List<String> K11e1eed331661e4ef2(String name, float maxWidth) {
      List<String> lines = new ArrayList(2);

      int end;
      for(String rest = name; !rest.isEmpty() && lines.size() < 2; rest = rest.substring(end).stripLeading()) {
         if (K896065ea0a2bba4f47.SF_MEDIUM.getWidth(rest, 8.5F) <= maxWidth) {
            lines.add(rest);
            break;
         }

         for(end = rest.length(); end > 1; end = rest.offsetByCodePoints(end, -1)) {
            K5fe84dad3db1af4bb7 var10000 = K896065ea0a2bba4f47.SF_MEDIUM;
            String var10001 = rest.substring(0, end);
            if (!(var10000.getWidth(var10001 + (lines.size() == 1 ? "…" : ""), 8.5F) > maxWidth)) {
               break;
            }
         }

         if (lines.size() == 1) {
            String var7 = rest.substring(0, end);
            lines.add(var7.stripTrailing() + "…");
            break;
         }

         int space = rest.lastIndexOf(32, end);
         if (space > 0) {
            end = space;
         }

         lines.add(rest.substring(0, end).stripTrailing());
      }

      return lines;
   }

   private void K9a4eae71aaf01a0534(int sector) {
      if (!this.controller.has(sector)) {
         this.controller.beginPick(sector);
      } else {
         this.K5c845d8b8a2a29e4d4(0.9F);
         this.controller.swap(sector);
      }

   }

   public boolean method_25402(double x, double y, int button) {
      if (this.closing) {
         return true;
      } else {
         int sector = this.Kc81da1b362519794b7(x, y);
         if (button == 1) {
            if (sector >= 0) {
               this.controller.beginPick(sector);
            } else {
               this.K515236525d7fa1451f();
            }

            return true;
         } else if (button == 0 && sector >= 0) {
            this.K9a4eae71aaf01a0534(sector);
            return true;
         } else {
            return super.method_25402(x, y, button);
         }
      }
   }

   public boolean method_25406(double x, double y, int button) {
      if (!this.closing && this.bind == -100 + button) {
         int sector = this.Kc81da1b362519794b7(x, y);
         if (sector >= 0 && this.controller.has(sector)) {
            this.K9a4eae71aaf01a0534(sector);
            return true;
         }
      }

      return super.method_25406(x, y, button);
   }

   public boolean method_25404(int key, int scan, int modifiers) {
      if (key == 256) {
         this.K515236525d7fa1451f();
         return true;
      } else {
         return super.method_25404(key, scan, modifiers);
      }
   }

   public boolean method_16803(int key, int scan, int modifiers) {
      if (!this.closing && key == this.bind && this.hovered >= 0 && this.controller.has(this.hovered)) {
         this.K9a4eae71aaf01a0534(this.hovered);
         return true;
      } else {
         return super.method_16803(key, scan, modifiers);
      }
   }

   public boolean method_25421() {
      return false;
   }

   public void method_25420(class_332 context, int x, int y, float delta) {
   }

   public void method_52752(class_332 context) {
   }

   public void method_25432() {
      this.renderer.close();
   }

   public static class_10185 movementInput(class_10185 original) {
      class_310 mc = class_310.method_1551();
      return mc.field_1755 instanceof Kb7a870ae80057bb597 && mc.method_1569() ? new class_10185(down(mc.field_1690.field_1894), down(mc.field_1690.field_1881), down(mc.field_1690.field_1913), down(mc.field_1690.field_1849), down(mc.field_1690.field_1903), down(mc.field_1690.field_1832), down(mc.field_1690.field_1867)) : original;
   }

   private static boolean down(class_304 binding) {
      class_310 mc = class_310.method_1551();
      class_3675.class_306 key = class_3675.method_15981(binding.method_1428());
      return key.method_1442() == class_307.field_1672 ? GLFW.glfwGetMouseButton(mc.method_22683().method_4490(), key.method_1444()) == 1 : key.method_1444() >= 0 && class_3675.method_15987(mc.method_22683().method_4490(), key.method_1444());
   }
}
