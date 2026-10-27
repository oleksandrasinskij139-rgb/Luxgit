package code.miaware.client.features.modules.movement;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_10142;
import net.minecraft.class_2248;
import net.minecraft.class_2302;
import net.minecraft.class_2320;
import net.minecraft.class_2338;
import net.minecraft.class_2356;
import net.minecraft.class_238;
import net.minecraft.class_2420;
import net.minecraft.class_243;
import net.minecraft.class_2473;
import net.minecraft.class_2523;
import net.minecraft.class_2541;
import net.minecraft.class_2553;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4587;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

@ModuleRegister(
   name = "JumpEffect",
   category = Kf76f042931183af5ba.MOVEMENT,
   description = "Красивое свечение блоков при прыжке"
)
public class Ka09756d64144ec388c extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final Ka09756d64144ec388c instance = new Ka09756d64144ec388c();
   private final Kb9e875ead00e0d03bd radius = (new Kb9e875ead00e0d03bd("Радиус")).value(3.0F).range(1.0F, 15.0F).step(0.5F);
   private final Kb9e875ead00e0d03bd speed = (new Kb9e875ead00e0d03bd("Скорость")).value(1500.0F).range(100.0F, 3000.0F).step(100.0F);
   private final Kb9e875ead00e0d03bd fillIntensity = (new Kb9e875ead00e0d03bd("Интенсивность заливки")).value(40.0F).range(0.0F, 100.0F).step(5.0F);
   private final Kbd66519397e9a89967 colorMode = (new Kbd66519397e9a89967("Режим цвета")).value("Тема").values("Тема", "Кастом");
   private final Ka52d224feda41b0761 customColor = (new Ka52d224feda41b0761("Кастомный цвет")).value(new Color(100, 200, 255)).setVisible(() -> ((String)this.colorMode.getValue()).equals("Кастом"));
   private final CopyOnWriteArrayList<Ka300fde9a7487d6179> circles = new CopyOnWriteArrayList();
   private boolean lastOnGround = false;
   private int jumpCooldown = 0;

   public Ka09756d64144ec388c() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.radius, this.speed, this.fillIntensity, this.colorMode, this.customColor});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 updateEvent = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null) {
            boolean nowOnGround = mc.field_1724.method_24828();
            if (this.jumpCooldown > 0) {
               --this.jumpCooldown;
            }

            if (this.lastOnGround && !nowOnGround && this.jumpCooldown == 0 && mc.field_1724.method_18798().field_1351 > (double)0.0F) {
               class_243 pos = mc.field_1724.method_19538().method_1031((double)0.0F, 0.05, (double)0.0F);
               this.circles.add(new Ka300fde9a7487d6179(pos));
               this.jumpCooldown = 10;
            }

            this.lastOnGround = nowOnGround;
            this.circles.removeIf((c) -> (float)(System.currentTimeMillis() - c.time) > this.speed.getValue());
         }
      }));
      K8d7165024ff567f5e7 renderEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (!this.circles.isEmpty()) {
            this.K06fd2c50254ba06095(event);
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{updateEvent, renderEvent});
   }

   private void K06fd2c50254ba06095(K9ee087b2e1791ad6b2 event) {
      class_243 camPos = mc.field_1773.method_19418().method_19326();
      class_4587 matrix = event.matrixStack();

      for(Ka300fde9a7487d6179 circle : this.circles) {
         float progress = (float)(System.currentTimeMillis() - circle.time) / this.speed.getValue();
         if (!(progress > 1.0F)) {
            float currentRadius = this.Kc6d73ce06b6e5a937a(progress, 0.0F, this.radius.getValue());
            float ringThickness = 0.8F;
            class_2338 center = class_2338.method_49638(circle.position);
            int r = (int)Math.ceil((double)(currentRadius + ringThickness));

            for(int x = -r; x <= r; ++x) {
               for(int z = -r; z <= r; ++z) {
                  double dist = Math.sqrt((double)(x * x + z * z));
                  if (dist >= (double)(currentRadius - ringThickness) && dist <= (double)(currentRadius + ringThickness)) {
                     class_2338 blockPos = center.method_10069(x, 0, z);
                     class_2338 foundBlock = this.Kab8772f9357fe24437(blockPos);
                     if (foundBlock != null) {
                        this.Ka2d1699214a885c456(foundBlock, progress, camPos, matrix);
                     }
                  }
               }
            }
         }
      }

   }

   private class_2338 Kab8772f9357fe24437(class_2338 startPos) {
      if (mc.field_1687 == null) {
         return null;
      } else if (this.Kf47767f8201ee9cc1b(startPos)) {
         return startPos;
      } else {
         for(int y = 1; y <= 5; ++y) {
            class_2338 below = startPos.method_10087(y);
            if (this.Kf47767f8201ee9cc1b(below)) {
               return below;
            }
         }

         for(int y = 1; y <= 5; ++y) {
            class_2338 above = startPos.method_10086(y);
            if (this.Kf47767f8201ee9cc1b(above)) {
               return above;
            }
         }

         return null;
      }
   }

   private boolean Kf47767f8201ee9cc1b(class_2338 pos) {
      if (mc.field_1687 == null) {
         return false;
      } else {
         class_2680 state = mc.field_1687.method_8320(pos);
         if (state.method_26215()) {
            return false;
         } else {
            class_2248 block = state.method_26204();
            return !(block instanceof class_2320) && !(block instanceof class_2356) && !(block instanceof class_2473) && !(block instanceof class_2302) && !(block instanceof class_2523) && !(block instanceof class_2541) && !(block instanceof class_2553) && !(block instanceof class_2420);
         }
      }
   }

   private void Ka2d1699214a885c456(class_2338 pos, float progress, class_243 camPos, class_4587 matrix) {
      if (mc.field_1687 != null) {
         class_2680 state = mc.field_1687.method_8320(pos);
         class_265 shape = state.method_26218(mc.field_1687, pos);
         class_238 box;
         if (shape.method_1110()) {
            box = new class_238((double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)1.0F, (double)1.0F);
         } else {
            box = shape.method_1107();
         }

         matrix.method_22903();
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableCull();
         RenderSystem.depthMask(false);
         RenderSystem.disableDepthTest();
         matrix.method_22904((double)pos.method_10263() - camPos.field_1352 + box.field_1323, (double)pos.method_10264() - camPos.field_1351 + box.field_1322, (double)pos.method_10260() - camPos.field_1350 + box.field_1321);
         float width = (float)(box.field_1320 - box.field_1323);
         float height = (float)(box.field_1325 - box.field_1322);
         float depth = (float)(box.field_1324 - box.field_1321);
         float alpha = 1.0F - progress;
         Color color;
         if (((String)this.colorMode.getValue()).equals("Тема")) {
            int colorIndex = (int)(progress * 360.0F);
            color = Kb18d549c13309af9a5.gradient(colorIndex);
         } else {
            color = new Color(((Color)this.customColor.getValue()).getRGB());
         }

         this.Kea946a85ac47f1a0d8(matrix, width, height, depth, K80ee108ea129adac11.setAlpha(color, (int)(alpha * this.fillIntensity.getValue() * 2.55F)));
         this.Kf35bff1eaa0998e4dd(matrix, width, height, depth, K80ee108ea129adac11.setAlpha(color, (int)(alpha * 100.0F * 2.55F)));
         RenderSystem.enableDepthTest();
         RenderSystem.depthMask(true);
         RenderSystem.enableCull();
         RenderSystem.disableBlend();
         matrix.method_22909();
      }
   }

   private void Kea946a85ac47f1a0d8(class_4587 matrix, float width, float height, float depth, Color color) {
      Matrix4f mat = matrix.method_23760().method_23761();
      float r = (float)color.getRed() / 255.0F;
      float g = (float)color.getGreen() / 255.0F;
      float b = (float)color.getBlue() / 255.0F;
      float a = (float)color.getAlpha() / 255.0F;
      RenderSystem.setShader(class_10142.field_53876);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);
      buffer.method_22918(mat, 0.0F, 0.0F, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, 0.0F, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, 0.0F, depth).method_22915(r, g, b, a);
      buffer.method_22918(mat, 0.0F, 0.0F, depth).method_22915(r, g, b, a);
      buffer.method_22918(mat, 0.0F, height, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, 0.0F, height, depth).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, height, depth).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, height, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, 0.0F, 0.0F, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, 0.0F, height, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, height, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, 0.0F, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, 0.0F, 0.0F, depth).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, 0.0F, depth).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, height, depth).method_22915(r, g, b, a);
      buffer.method_22918(mat, 0.0F, height, depth).method_22915(r, g, b, a);
      buffer.method_22918(mat, 0.0F, 0.0F, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, 0.0F, 0.0F, depth).method_22915(r, g, b, a);
      buffer.method_22918(mat, 0.0F, height, depth).method_22915(r, g, b, a);
      buffer.method_22918(mat, 0.0F, height, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, 0.0F, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, height, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, height, depth).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, 0.0F, depth).method_22915(r, g, b, a);
      class_286.method_43433(buffer.method_60800());
   }

   private void Kf35bff1eaa0998e4dd(class_4587 matrix, float width, float height, float depth, Color color) {
      Matrix4f mat = matrix.method_23760().method_23761();
      float r = (float)color.getRed() / 255.0F;
      float g = (float)color.getGreen() / 255.0F;
      float b = (float)color.getBlue() / 255.0F;
      float a = (float)color.getAlpha() / 255.0F;
      RenderSystem.setShader(class_10142.field_53876);
      RenderSystem.lineWidth(3.0F);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27377, class_290.field_1576);
      buffer.method_22918(mat, 0.0F, height, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, height, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, height, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, height, depth).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, height, depth).method_22915(r, g, b, a);
      buffer.method_22918(mat, 0.0F, height, depth).method_22915(r, g, b, a);
      buffer.method_22918(mat, 0.0F, height, depth).method_22915(r, g, b, a);
      buffer.method_22918(mat, 0.0F, height, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, 0.0F, 0.0F, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, 0.0F, height, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, 0.0F, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, height, 0.0F).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, 0.0F, depth).method_22915(r, g, b, a);
      buffer.method_22918(mat, width, height, depth).method_22915(r, g, b, a);
      buffer.method_22918(mat, 0.0F, 0.0F, depth).method_22915(r, g, b, a);
      buffer.method_22918(mat, 0.0F, height, depth).method_22915(r, g, b, a);
      class_286.method_43433(buffer.method_60800());
      RenderSystem.lineWidth(1.0F);
   }

   private float Kc6d73ce06b6e5a937a(float t, float a, float b) {
      return a + t * (b - a);
   }

   public void onDisable() {
      super.onDisable();
      this.circles.clear();
      this.lastOnGround = false;
      this.jumpCooldown = 0;
   }

   public static Ka09756d64144ec388c getInstance() {
      return instance;
   }
}
