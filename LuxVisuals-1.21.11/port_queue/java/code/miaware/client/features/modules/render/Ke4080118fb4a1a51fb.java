package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K001d59b5164d1d1f95;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.minecraft.class_10142;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2663;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

@ModuleRegister(
   name = "AngelTotem",
   category = Kf76f042931183af5ba.RENDER,
   description = "Душа вылетает при использовании тотема"
)
public class Ke4080118fb4a1a51fb extends Kab8a8c478f7d0077cd {
   private static final Ke4080118fb4a1a51fb instance = new Ke4080118fb4a1a51fb();
   private final Kb9e875ead00e0d03bd fadeSpeed = (new Kb9e875ead00e0d03bd("Скорость")).value(3.0F).range(1.0F, 10.0F).step(0.5F);
   private final Kb9e875ead00e0d03bd riseHeight = (new Kb9e875ead00e0d03bd("Высота")).value(3.5F).range(1.0F, 10.0F).step(0.5F);
   private final K00fef91b3d4d1b3ecc fill = (new K00fef91b3d4d1b3ecc("Заливка")).value(true);
   private final Kbd66519397e9a89967 colorMode = (new Kbd66519397e9a89967("Цвет")).value("Theme").values("Theme", "Custom");
   private final Ka52d224feda41b0761 customColor = (new Ka52d224feda41b0761("Свой цвет")).value(new Color(255, 255, 255)).setVisible(() -> ((String)this.colorMode.getValue()).equals("Custom"));
   private final K00fef91b3d4d1b3ecc halo = (new K00fef91b3d4d1b3ecc("Нимб")).value(true);
   private final K00fef91b3d4d1b3ecc wings = (new K00fef91b3d4d1b3ecc("Крылья")).value(true);
   private final List<Kae0feda02ff0f80ade> souls = new ArrayList();
   private static final Kcab1ec2ccb651b98aa[] WING_SHAPE = new Kcab1ec2ccb651b98aa[]{new Kcab1ec2ccb651b98aa(0.08F, 0.1F, 0.88F), new Kcab1ec2ccb651b98aa(0.28F, 0.34F, 0.78F), new Kcab1ec2ccb651b98aa(0.56F, 0.82F, 0.62F), new Kcab1ec2ccb651b98aa(0.86F, 0.3F, 0.52F), new Kcab1ec2ccb651b98aa(1.14F, 0.46F, 0.4F), new Kcab1ec2ccb651b98aa(1.24F, 0.04F, 0.3F), new Kcab1ec2ccb651b98aa(1.02F, -0.18F, 0.28F), new Kcab1ec2ccb651b98aa(1.18F, -0.64F, 0.22F), new Kcab1ec2ccb651b98aa(0.86F, -0.46F, 0.2F), new Kcab1ec2ccb651b98aa(0.8F, -0.98F, 0.14F), new Kcab1ec2ccb651b98aa(0.54F, -0.74F, 0.16F), new Kcab1ec2ccb651b98aa(0.3F, -1.16F, 0.12F), new Kcab1ec2ccb651b98aa(0.1F, -0.54F, 0.18F)};

   public Ke4080118fb4a1a51fb() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.fadeSpeed, this.riseHeight, this.fill, this.colorMode, this.customColor, this.halo, this.wings});
   }

   public void onEnable() {
      this.souls.clear();
   }

   public void onDisable() {
      this.souls.clear();
   }

   public void onEvent() {
      K8d7165024ff567f5e7 packetEvent = K001d59b5164d1d1f95.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1687 != null && event.isReceive()) {
            class_2596 patt0$temp = event.packet();
            if (patt0$temp instanceof class_2663) {
               class_2663 packet = (class_2663)patt0$temp;
               byte status = packet.method_11470();
               if (status == 35) {
                  class_1297 entity = packet.method_11469(mc.field_1687);
                  if (entity instanceof class_1657) {
                     class_1657 player = (class_1657)entity;
                     class_243 pos = new class_243(player.method_23317(), player.method_23318(), player.method_23321());
                     this.souls.add(new Kae0feda02ff0f80ade(pos, player.method_43078(), player.method_5715(), (float)player.field_6012, System.currentTimeMillis()));
                  }
               }
            }

         }
      }));
      K8d7165024ff567f5e7 renderEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> this.K9df5630e2089efd75e(event.matrixStack())));
      this.addEvents(new K8d7165024ff567f5e7[]{packetEvent, renderEvent});
   }

   public void addSoul(class_243 pos, float yaw, boolean sneak, float phase) {
      this.souls.add(new Kae0feda02ff0f80ade(pos, yaw, sneak, phase, System.currentTimeMillis()));
   }

   private void K9df5630e2089efd75e(class_4587 matrices) {
      if (mc.field_1724 != null && mc.field_1687 != null && !this.souls.isEmpty()) {
         long now = System.currentTimeMillis();
         float duration = this.fadeSpeed.getValue() * 1000.0F;
         this.souls.removeIf((soulx) -> (float)(now - soulx.time) >= duration);
         class_243 cam = mc.field_1773.method_19418().method_19326();
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableDepthTest();
         RenderSystem.disableCull();
         RenderSystem.setShader(class_10142.field_53876);

         for(Kae0feda02ff0f80ade soul : this.souls) {
            float progress = (float)(now - soul.time) / duration;
            if (!(progress >= 1.0F)) {
               float alpha = (1.0F - progress) * 0.6F;
               float rise = this.riseHeight.getValue() * this.K797e29fdfe9306e460(progress);
               Color clientColor;
               if (((String)this.colorMode.getValue()).equals("Theme")) {
                  clientColor = Kb18d549c13309af9a5.gradient(0);
               } else {
                  clientColor = (Color)this.customColor.getValue();
               }

               float r = (float)clientColor.getRed() / 255.0F;
               float g = (float)clientColor.getGreen() / 255.0F;
               float b = (float)clientColor.getBlue() / 255.0F;
               matrices.method_22903();
               matrices.method_22904(soul.pos.field_1352 - cam.field_1352, soul.pos.field_1351 - cam.field_1351 + (double)rise, soul.pos.field_1350 - cam.field_1350);
               matrices.method_22907(class_7833.field_40716.rotationDegrees(180.0F - soul.yaw));
               matrices.method_22905(-1.0F, -1.0F, 1.0F);
               matrices.method_22904((double)0.0F, (double)-1.5F, (double)0.0F);
               if (soul.sneak) {
                  matrices.method_22904((double)0.0F, 0.2, (double)0.0F);
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(28.0F));
               }

               Matrix4f mat = matrices.method_23760().method_23761();
               float u = 0.0625F;
               float swing = class_3532.method_15374(soul.phase * 0.6662F) * 0.6F;
               this.K81e23faf5efcf1682c(matrices, mat, -4.0F * u, -8.0F * u, -4.0F * u, 8.0F * u, 8.0F * u, 8.0F * u, r, g, b, alpha);
               this.K81e23faf5efcf1682c(matrices, mat, -4.0F * u, 0.0F, -2.0F * u, 8.0F * u, 12.0F * u, 4.0F * u, r, g, b, alpha);
               matrices.method_22903();
               matrices.method_46416(-6.0F * u, 2.0F * u, 0.0F);
               matrices.method_22907(class_7833.field_40714.rotation(-swing));
               matrices.method_46416(6.0F * u, -2.0F * u, 0.0F);
               this.K81e23faf5efcf1682c(matrices, matrices.method_23760().method_23761(), -8.0F * u, -2.0F * u, -2.0F * u, 4.0F * u, 12.0F * u, 4.0F * u, r, g, b, alpha);
               matrices.method_22909();
               matrices.method_22903();
               matrices.method_46416(6.0F * u, 2.0F * u, 0.0F);
               matrices.method_22907(class_7833.field_40714.rotation(swing));
               matrices.method_46416(-6.0F * u, -2.0F * u, 0.0F);
               this.K81e23faf5efcf1682c(matrices, matrices.method_23760().method_23761(), 4.0F * u, -2.0F * u, -2.0F * u, 4.0F * u, 12.0F * u, 4.0F * u, r, g, b, alpha);
               matrices.method_22909();
               matrices.method_22903();
               matrices.method_46416(-2.0F * u, 12.0F * u, 0.0F);
               matrices.method_22907(class_7833.field_40714.rotation(swing));
               matrices.method_46416(2.0F * u, -12.0F * u, 0.0F);
               this.K81e23faf5efcf1682c(matrices, matrices.method_23760().method_23761(), -4.0F * u, 12.0F * u, -2.0F * u, 4.0F * u, 12.0F * u, 4.0F * u, r, g, b, alpha);
               matrices.method_22909();
               matrices.method_22903();
               matrices.method_46416(2.0F * u, 12.0F * u, 0.0F);
               matrices.method_22907(class_7833.field_40714.rotation(-swing));
               matrices.method_46416(-2.0F * u, -12.0F * u, 0.0F);
               this.K81e23faf5efcf1682c(matrices, matrices.method_23760().method_23761(), 0.0F, 12.0F * u, -2.0F * u, 4.0F * u, 12.0F * u, 4.0F * u, r, g, b, alpha);
               matrices.method_22909();
               if ((Boolean)this.halo.getValue()) {
                  matrices.method_22903();
                  matrices.method_46416(0.0F, -10.0F * u, 0.0F);
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(90.0F));
                  this.Kf5019919693e9cb9cc(matrices, matrices.method_23760().method_23761(), 0.0F, 0.0F, 0.0F, 5.0F * u, r, g, b, alpha);
                  matrices.method_22909();
               }

               if ((Boolean)this.wings.getValue()) {
                  matrices.method_22903();
                  matrices.method_46416(0.0F, 4.0F * u, 2.0F * u);
                  float wingFlap = class_3532.method_15374((soul.phase + progress * 20.0F) * 0.15F) * 12.0F;
                  float wingScale = 0.55F;
                  matrices.method_22903();
                  matrices.method_22907(class_7833.field_40716.rotationDegrees(-wingFlap));
                  this.K2f6c449d7ec4d7b99a(matrices, matrices.method_23760().method_23761(), -1.0F, wingScale, r, g, b, alpha * 0.8F);
                  matrices.method_22909();
                  matrices.method_22903();
                  matrices.method_22907(class_7833.field_40716.rotationDegrees(wingFlap));
                  this.K2f6c449d7ec4d7b99a(matrices, matrices.method_23760().method_23761(), 1.0F, wingScale, r, g, b, alpha * 0.8F);
                  matrices.method_22909();
                  matrices.method_22909();
               }

               matrices.method_22909();
            }
         }

         RenderSystem.enableCull();
         RenderSystem.enableDepthTest();
         RenderSystem.disableBlend();
      }
   }

   private void K81e23faf5efcf1682c(class_4587 matrices, Matrix4f m, float x, float y, float z, float sx, float sy, float sz, float r, float g, float bl, float a) {
      float x2 = x + sx;
      float y2 = y + sy;
      float z2 = z + sz;
      if ((Boolean)this.fill.getValue()) {
         class_287 buf = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);
         buf.method_22918(m, x, y, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y2, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y2, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y2, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y2, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y2, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y2, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y2, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y2, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y2, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y2, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y2, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y2, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y, z2).method_22915(r, g, bl, a);
         class_286.method_43433(buf.method_60800());
      } else {
         RenderSystem.lineWidth(2.0F);
         class_287 buf = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);
         buf.method_22918(m, x, y, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y2, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y2, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y2, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y2, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y2, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y2, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y2, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y2, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y2, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y2, z).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x, y2, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y, z2).method_22915(r, g, bl, a);
         buf.method_22918(m, x2, y2, z2).method_22915(r, g, bl, a);
         class_286.method_43433(buf.method_60800());
         RenderSystem.lineWidth(1.0F);
      }

   }

   private void Kf5019919693e9cb9cc(class_4587 matrices, Matrix4f m, float cx, float cy, float cz, float radius, float r, float g, float bl, float a) {
      class_287 buf = class_289.method_1348().method_60827(class_5596.field_29345, class_290.field_1576);

      for(int i = 0; i <= 360; ++i) {
         float angle = (float)Math.toRadians((double)i);
         float x = cx + class_3532.method_15362(angle) * radius;
         float y = cy + class_3532.method_15374(angle) * radius;
         buf.method_22918(m, x, y, cz).method_22915(r, g, bl, a);
      }

      class_286.method_43433(buf.method_60800());
   }

   private void K2f6c449d7ec4d7b99a(class_4587 matrices, Matrix4f matrix, float side, float scale, float r, float g, float b, float a) {
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);

      for(int i = 0; i < WING_SHAPE.length; ++i) {
         Kcab1ec2ccb651b98aa current = WING_SHAPE[i];
         Kcab1ec2ccb651b98aa next = WING_SHAPE[(i + 1) % WING_SHAPE.length];
         buffer.method_22918(matrix, 0.0F, 0.0F, 0.0F).method_22915(r, g, b, a);
         buffer.method_22918(matrix, side * current.x * scale, current.y * scale, 0.0F).method_22915(r, g, b, a * current.alphaMul);
         buffer.method_22918(matrix, side * next.x * scale, next.y * scale, 0.0F).method_22915(r, g, b, a * next.alphaMul);
      }

      class_286.method_43433(buffer.method_60800());
   }

   private float K797e29fdfe9306e460(float t) {
      return 1.0F - (float)Math.pow((double)(1.0F - class_3532.method_15363(t, 0.0F, 1.0F)), (double)3.0F);
   }

   @Generated
   public static Ke4080118fb4a1a51fb getInstance() {
      return instance;
   }
}
