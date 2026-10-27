package code.miaware.client.features.modules.movement;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.K14caf22f676a0204f7;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import lombok.Generated;
import net.minecraft.class_10142;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_5498;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

@ModuleRegister(
   name = "Crosshair",
   category = Kf76f042931183af5ba.MOVEMENT,
   description = "Отображает кастомный прицел"
)
public class K0574a7e784c41d227d extends Kab8a8c478f7d0077cd {
   private static final K0574a7e784c41d227d instance = new K0574a7e784c41d227d();
   private final Kbd66519397e9a89967 mode = (new Kbd66519397e9a89967("Режим")).value("Кругляшок").values("Кастомный", "Кругляшок");
   private final K00fef91b3d4d1b3ecc animate = (new K00fef91b3d4d1b3ecc("Анимация удара")).value(true);
   private final K00fef91b3d4d1b3ecc dot = (new K00fef91b3d4d1b3ecc("Точка")).value(true).setVisible(() -> this.mode.is("Кастомный"));
   private final K00fef91b3d4d1b3ecc tShape = (new K00fef91b3d4d1b3ecc("T-образный")).value(false).setVisible(() -> this.mode.is("Кастомный"));
   private final Kb9e875ead00e0d03bd width = (new Kb9e875ead00e0d03bd("Ширина")).value(5.0F).range(0.0F, 10.0F).step(1.0F).setVisible(() -> this.mode.is("Кастомный"));
   private final Kb9e875ead00e0d03bd height = (new Kb9e875ead00e0d03bd("Высота")).value(5.0F).range(0.0F, 10.0F).step(1.0F).setVisible(() -> this.mode.is("Кастомный"));
   private final Kb9e875ead00e0d03bd gap = (new Kb9e875ead00e0d03bd("Отступ")).value(3.0F).range(0.0F, 10.0F).step(1.0F).setVisible(() -> this.mode.is("Кастомный"));
   private final Kb9e875ead00e0d03bd thickness = (new Kb9e875ead00e0d03bd("Толщина")).value(1.0F).range(1.0F, 10.0F).step(1.0F).setVisible(() -> this.mode.is("Кастомный"));
   private final Ka91715a8c4e3f3b7c4 animation = new Ka91715a8c4e3f3b7c4();

   public K0574a7e784c41d227d() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.mode, this.animate, this.dot, this.tShape, this.width, this.height, this.gap, this.thickness});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 render2DEvent = K14caf22f676a0204f7.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1690.method_31044() == class_5498.field_26664) {
            class_4587 matrices = event.matrixStack();
            int screenWidth = mc.method_22683().method_4486();
            int screenHeight = mc.method_22683().method_4502();
            float centerX = (float)screenWidth / 2.0F;
            float centerY = (float)screenHeight / 2.0F;
            float swingProgress = mc.field_1724.method_6055(event.partialTicks());
            float sin = class_3532.method_15374(swingProgress * swingProgress * (float)Math.PI);
            this.animation.update();
            if ((Boolean)this.animate.getValue()) {
               int swingDuration = mc.field_1724.field_6279 > 0 ? mc.field_1724.field_6279 : 6;
               this.animation.run(sin, (long)(swingDuration * 50), Kf3b62ecfba0c3544e6.LINEAR);
            } else {
               this.animation.setValue((double)0.0F);
            }

            Color color = Color.WHITE;
            if (this.mode.is("Кастомный")) {
               this.K737c736f774dc09a8e(matrices, centerX, centerY, color);
            } else if (this.mode.is("Кругляшок")) {
               this.K66645bb6707ac3e0ef(matrices, centerX, centerY);
            }

         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{render2DEvent});
   }

   private void K737c736f774dc09a8e(class_4587 matrices, float centerX, float centerY, Color color) {
      float cwidth = this.width.getValue();
      float cheight = this.height.getValue();
      float thick = this.thickness.getValue();
      float gapValue = this.gap.getValue() + ((Boolean)this.animate.getValue() ? (float)(this.animation.getValue() * (double)4.0F) : 0.0F);
      if ((Boolean)this.dot.getValue()) {
         K2b5e2c5f2b9891b450.RECT.draw(matrices, centerX - 0.5F, centerY - 0.5F, 1.0F, 1.0F, 0.0F, color);
      }

      if (!(Boolean)this.tShape.getValue()) {
         K2b5e2c5f2b9891b450.RECT.draw(matrices, centerX - thick / 2.0F, centerY - gapValue - cheight, thick, cheight, 0.0F, color);
      }

      K2b5e2c5f2b9891b450.RECT.draw(matrices, centerX - thick / 2.0F, centerY + gapValue, thick, cheight, 0.0F, color);
      K2b5e2c5f2b9891b450.RECT.draw(matrices, centerX - gapValue - cwidth, centerY - thick / 2.0F, cwidth, thick, 0.0F, color);
      K2b5e2c5f2b9891b450.RECT.draw(matrices, centerX + gapValue, centerY - thick / 2.0F, cwidth, thick, 0.0F, color);
   }

   private void K66645bb6707ac3e0ef(class_4587 matrices, float centerX, float centerY) {
      float shrink = (float)this.animation.getValue() * 4.0F;
      float outerRadius = Math.max(6.0F - shrink, 2.0F);
      float innerRadius = Math.max(4.5F - shrink, 1.0F);
      Matrix4f matrix = matrices.method_23760().method_23761();
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableDepthTest();
      RenderSystem.setShader(class_10142.field_53876);
      int segments = 100;
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27380, class_290.field_1576);

      for(int i = 0; i <= segments + 1; ++i) {
         float a = (float)((Math.PI * 2D) * (double)Math.min(i, segments) / (double)segments);
         float cos = (float)Math.cos((double)a);
         float sin = (float)Math.sin((double)a);
         float outerX = centerX + outerRadius * cos;
         float outerY = centerY + outerRadius * sin;
         float innerX = centerX + innerRadius * cos;
         float innerY = centerY + innerRadius * sin;
         Color c = Kb18d549c13309af9a5.gradient((int)((float)Math.min(i, segments) * 360.0F / (float)segments));
         buffer.method_22918(matrix, outerX, outerY, 0.0F).method_39415(c.getRGB());
         buffer.method_22918(matrix, innerX, innerY, 0.0F).method_39415(c.getRGB());
      }

      class_286.method_43433(buffer.method_60800());
      RenderSystem.enableDepthTest();
      RenderSystem.disableBlend();
   }

   @Generated
   public static K0574a7e784c41d227d getInstance() {
      return instance;
   }
}
