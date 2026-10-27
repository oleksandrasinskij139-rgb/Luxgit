package code.miaware.client.ui.widget;

import code.miaware.api.utils.render.Ke8433384857ddcab3d;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10444;
import net.minecraft.class_1799;
import net.minecraft.class_308;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_4608;
import net.minecraft.class_811;

public final class Kdd908967ec41b41bcf implements AutoCloseable {
   private final class_4587 source;
   private final float alpha;
   private class_332 context;
   private final class_10444 renderState = new class_10444();
   private boolean flatLighting;

   private Kdd908967ec41b41bcf(class_4587 source, float alpha) {
      this.source = source;
      this.alpha = alpha;
   }

   public void draw(class_1799 stack, float x, float y, float size) {
      if (stack != null && !stack.method_7960() && !(this.alpha <= 0.02F)) {
         Ke8433384857ddcab3d.record(this.source.method_23760().method_23761(), x - 2.0F, y - 2.0F, size + 4.0F, size + 4.0F);
         if (this.context == null) {
            class_310 mc = class_310.method_1551();
            this.context = new class_332(mc, mc.method_22940().method_23000());
            this.context.method_51448().method_34425(this.source.method_23760().method_23761());
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
         }

         class_310 mc = class_310.method_1551();
         mc.method_65386().method_65598(this.renderState, stack, class_811.field_4317, false, mc.field_1687, mc.field_1724, 0);
         boolean flat = !this.renderState.method_65608();
         if (flat != this.flatLighting) {
            this.context.method_51452();
            if (flat) {
               class_308.method_24210();
            } else {
               class_308.method_24211();
            }

            this.flatLighting = flat;
         }

         class_4587 matrices = this.context.method_51448();
         matrices.method_22903();

         try {
            matrices.method_46416(x, y, 0.0F);
            matrices.method_22905(size / 16.0F, size / 16.0F, 1.0F);
            matrices.method_46416(8.0F, 8.0F, 150.0F);
            matrices.method_22905(16.0F, -16.0F, 16.0F);
            this.renderState.method_65604(matrices, mc.method_22940().method_23000(), 15728880, class_4608.field_21444);
         } finally {
            matrices.method_22909();
         }

      }
   }

   public void close() {
      if (this.context != null) {
         try {
            this.context.method_51452();
         } finally {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            if (this.flatLighting) {
               class_308.method_24211();
            }

            this.flatLighting = false;
            this.context = null;
         }

      }
   }
}
