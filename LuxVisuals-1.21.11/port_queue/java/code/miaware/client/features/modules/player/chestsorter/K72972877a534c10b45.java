package code.miaware.client.features.modules.player.chestsorter;

import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.client.features.modules.player.K1cb52ce4c79b2f47f0;
import net.minecraft.class_1733;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1921;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_465;

public final class K72972877a534c10b45 implements AutoCloseable {
   private final class_310 mc = class_310.method_1551();
   private final K1cb52ce4c79b2f47f0 module = K1cb52ce4c79b2f47f0.getInstance();
   private final class_465<?> screen;
   private static final int TAB_WIDTH = 34;
   private static final int TAB_HEIGHT = 14;
   private static final class_2960 CHEST_FRAME = class_2960.method_60656("textures/gui/container/generic_54.png");
   private static final class_2960 SHULKER_FRAME = class_2960.method_60656("textures/gui/container/shulker_box.png");
   private final class_1799 controlIcon;
   private int bx;
   private int by;
   private boolean positioned;

   public K72972877a534c10b45(class_465<?> screen) {
      this.controlIcon = new class_1799(class_1802.field_8239);
      this.screen = screen;
   }

   public boolean supported() {
      return this.module.canSort(this.screen.method_17577());
   }

   private void position(int x, int y, int width) {
      this.bx = Math.clamp((long)(x + width - 34), 3, Math.max(3, this.screen.field_22789 - 34 - 3));
      this.by = Math.clamp((long)(y - 14 + 4), 3, Math.max(3, this.screen.field_22790 - 14 - 1));
      this.positioned = true;
   }

   private boolean K220a79f9a0bd3aad01(double x, double y) {
      return this.positioned && x >= (double)this.bx && y >= (double)this.by && x < (double)(this.bx + 34) && y < (double)(this.by + 14);
   }

   private void Kc38362aa004532650a(class_332 context) {
      int join = 10;
      class_2960 frame = this.screen.method_17577() instanceof class_1733 ? SHULKER_FRAME : CHEST_FRAME;
      context.method_25290(class_1921::method_62277, frame, this.bx, this.by, 0.0F, 0.0F, 30, 4, 256, 256);
      context.method_25290(class_1921::method_62277, frame, this.bx + 34 - 4, this.by, 172.0F, 0.0F, 4, 4, 256, 256);
      context.method_25290(class_1921::method_62277, frame, this.bx, this.by + 4, 0.0F, 4.0F, 30, join - 4, 256, 256);
      context.method_25290(class_1921::method_62277, frame, this.bx + 34 - 4, this.by + 4, 172.0F, 4.0F, 4, join - 4, 256, 256);
      context.method_25290(class_1921::method_62277, frame, this.bx, this.by + join, 4.0F, 4.0F, 30, 4, 256, 256);
      context.method_25290(class_1921::method_62277, frame, this.bx + 34 - 4, this.by + join, 172.0F, 4.0F, 4, 4, 256, 256);
      context.method_51448().method_22903();

      try {
         context.method_51448().method_46416((float)(this.bx + 11), (float)(this.by + 1), 0.0F);
         context.method_51448().method_22905(0.75F, 0.75F, 1.0F);
         context.method_51427(this.controlIcon, 0, 0);
      } finally {
         context.method_51448().method_22909();
      }

   }

   public void render(class_332 context, int mouseX, int mouseY, float delta, int x, int y, int width) {
      if (this.supported() && this.mc.field_1724 != null) {
         this.position(x, y, width);
         this.Ke296b6b11affb8fd35(context, x, y);
         context.method_51448().method_22903();

         try {
            context.method_51448().method_46416(0.0F, 0.0F, 450.0F);
            this.Kc38362aa004532650a(context);
            boolean sorting = this.module.getSorter().active();
            if (sorting) {
               float pulse = 0.5F + 0.5F * (float)Math.sin((double)System.nanoTime() / (double)1.2E8F);
               int color = Kb18d549c13309af9a5.primary().getRGB() & 16777215 | (int)(110.0F + pulse * 130.0F) << 24;
               context.method_25294(this.bx + 5, this.by + 14 - 2, this.bx + 34 - 5, this.by + 14 - 1, color);
            }

            if (this.K220a79f9a0bd3aad01((double)mouseX, (double)mouseY)) {
               context.method_51438(this.mc.field_1772, class_2561.method_43470(sorting ? "Остановить сортировку" : "Отсортировать контейнер"), mouseX, mouseY);
            }
         } finally {
            context.method_51448().method_22909();
         }

      }
   }

   private void Ke296b6b11affb8fd35(class_332 context, int x, int y) {
      Kb08aa924c2dac14f3e flight = this.module.getSorter().flight();
      if (flight != null) {
         float t = (float)(System.nanoTime() - flight.started()) / 7.5E7F;
         if (!(t >= 1.0F)) {
            float p = 1.0F - (float)Math.pow((double)(1.0F - Math.max(0.0F, t)), (double)3.0F);
            float xx = (float)(x + flight.sourceX()) + (float)(flight.targetX() - flight.sourceX()) * p;
            float yy = (float)(y + flight.sourceY()) + (float)(flight.targetY() - flight.sourceY()) * p;
            context.method_51448().method_22903();
            context.method_51448().method_22904((double)xx, (double)yy - Math.sin((double)p * Math.PI) * (double)6.0F, (double)350.0F);
            context.method_51427(flight.stack(), 0, 0);
            context.method_51448().method_22909();
         }
      }
   }

   public boolean click(double mx, double my, int button) {
      if (!this.supported()) {
         return false;
      } else if (this.K220a79f9a0bd3aad01(mx, my)) {
         if (button == 0 && this.screen.method_17577().method_34255().method_7960()) {
            if (this.module.getSorter().active()) {
               this.module.getSorter().stop();
            } else {
               this.module.getSorter().start(this.screen.method_17577());
            }
         }

         return true;
      } else {
         this.module.getSorter().stop();
         return false;
      }
   }

   public void key() {
      this.module.getSorter().stop();
   }

   public boolean consumesTooltip(int x, int y) {
      return this.supported() && this.K220a79f9a0bd3aad01((double)x, (double)y);
   }

   public void close() {
      this.positioned = false;
      this.module.getSorter().stop();
   }
}
