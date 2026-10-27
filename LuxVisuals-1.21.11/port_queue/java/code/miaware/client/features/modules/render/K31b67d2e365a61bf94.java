package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import lombok.Generated;
import net.minecraft.class_10142;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

@ModuleRegister(
   name = "Wings",
   category = Kf76f042931183af5ba.RENDER,
   description = "Добавляет крылья за спиной игрока"
)
public class K31b67d2e365a61bf94 extends Kab8a8c478f7d0077cd {
   private static final K31b67d2e365a61bf94 instance = new K31b67d2e365a61bf94();
   private static final float DEFAULT_SPREAD = 8.0F;
   private static final int DEFAULT_ALPHA = 220;
   private static final Kee1fb7bcdc47aee70b[] SHAPE = new Kee1fb7bcdc47aee70b[]{new Kee1fb7bcdc47aee70b(0.08F, 0.1F, 0.88F), new Kee1fb7bcdc47aee70b(0.28F, 0.34F, 0.78F), new Kee1fb7bcdc47aee70b(0.56F, 0.82F, 0.62F), new Kee1fb7bcdc47aee70b(0.86F, 0.3F, 0.52F), new Kee1fb7bcdc47aee70b(1.14F, 0.46F, 0.4F), new Kee1fb7bcdc47aee70b(1.24F, 0.04F, 0.3F), new Kee1fb7bcdc47aee70b(1.02F, -0.18F, 0.28F), new Kee1fb7bcdc47aee70b(1.18F, -0.64F, 0.22F), new Kee1fb7bcdc47aee70b(0.86F, -0.46F, 0.2F), new Kee1fb7bcdc47aee70b(0.8F, -0.98F, 0.14F), new Kee1fb7bcdc47aee70b(0.54F, -0.74F, 0.16F), new Kee1fb7bcdc47aee70b(0.3F, -1.16F, 0.12F), new Kee1fb7bcdc47aee70b(0.1F, -0.54F, 0.18F)};
   private final K00fef91b3d4d1b3ecc self = (new K00fef91b3d4d1b3ecc("Self")).value(true);
   private final K00fef91b3d4d1b3ecc players = (new K00fef91b3d4d1b3ecc("Players")).value(false);
   private final K00fef91b3d4d1b3ecc friends = (new K00fef91b3d4d1b3ecc("Friends")).value(false);
   private final Kbd66519397e9a89967 mode = (new Kbd66519397e9a89967("Режим")).value("Крылья").values("Крылья", "Катана");
   private final Kbd66519397e9a89967 colorMode = (new Kbd66519397e9a89967("Цвет")).value("Тема").values("Тема", "Кастом").setVisible(() -> ((String)this.mode.getValue()).equals("Катана"));
   private final Ka52d224feda41b0761 customColor = (new Ka52d224feda41b0761("Кастомный цвет")).value(new Color(255, 100, 150)).setVisible(() -> ((String)this.mode.getValue()).equals("Катана") && ((String)this.colorMode.getValue()).equals("Кастом"));
   private final Kb9e875ead00e0d03bd katanaSpread = (new Kb9e875ead00e0d03bd("Расстояние катан")).value(0.15F).range(-0.1F, 0.1F).step(0.01F).setVisible(() -> ((String)this.mode.getValue()).equals("Катана"));
   private final Kb9e875ead00e0d03bd size = (new Kb9e875ead00e0d03bd("Size")).value(1.0F).range(0.75F, 1.35F).step(0.05F);
   private final Kb9e875ead00e0d03bd distance = (new Kb9e875ead00e0d03bd("Расстояние")).value(0.42F).range(0.2F, 0.5F).step(0.02F);
   private float selfBodyYaw;
   private boolean selfBodyYawInitialized;

   public K31b67d2e365a61bf94() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.self, this.players, this.friends, this.mode, this.colorMode, this.customColor, this.katanaSpread, this.size, this.distance});
   }

   public void onDisable() {
      this.selfBodyYawInitialized = false;
   }

   public void onEvent() {
      K8d7165024ff567f5e7 renderEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1687 != null && mc.field_1773 != null) {
            class_4587 matrixStack = event.matrixStack();
            float partialTicks = event.partialTicks();
            class_243 camera = mc.field_1773.method_19418().method_19326();
            matrixStack.method_22903();
            RenderSystem.enableBlend();
            RenderSystem.disableCull();
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.setShader(class_10142.field_53876);

            try {
               if ((Boolean)this.self.getValue() && !mc.field_1690.method_31044().method_31034() && mc.field_1724.method_5805() && !this.Kfd2219255d5015e5e2(mc.field_1724)) {
                  if (((String)this.mode.getValue()).equals("Крылья")) {
                     this.Ke46c998cbb1ae53377(matrixStack, mc.field_1724, partialTicks, camera);
                  } else {
                     this.K3cdbf0e329c0d897f9(matrixStack, mc.field_1724, partialTicks, camera);
                  }
               }

               if ((Boolean)this.players.getValue() || (Boolean)this.friends.getValue()) {
                  for(class_1297 entity : mc.field_1687.method_18112()) {
                     if (entity instanceof class_1657) {
                        class_1657 player = (class_1657)entity;
                        if (player != mc.field_1724 && this.K945def08be69a37917(player)) {
                           if (((String)this.mode.getValue()).equals("Крылья")) {
                              this.Ke46c998cbb1ae53377(matrixStack, player, partialTicks, camera);
                           } else {
                              this.K3cdbf0e329c0d897f9(matrixStack, player, partialTicks, camera);
                           }
                        }
                     }
                  }
               }
            } finally {
               RenderSystem.depthMask(true);
               RenderSystem.enableCull();
               RenderSystem.disableBlend();
               RenderSystem.blendFuncSeparate(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA, class_4535.ONE, class_4534.ZERO);
               RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
               RenderSystem.lineWidth(1.0F);
               matrixStack.method_22909();
            }

         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{renderEvent});
   }

   private boolean Kfd2219255d5015e5e2(class_1657 player) {
      return player.method_6118(class_1304.field_6174).method_31574(class_1802.field_8833);
   }

   private boolean K945def08be69a37917(class_1657 player) {
      if (player.method_5805() && !player.method_5767() && !player.method_6113() && !this.Kfd2219255d5015e5e2(player)) {
         boolean friendTarget = K137d4e93c7270b0a04.getInstance().contains(player.method_5477().getString());
         return friendTarget ? (Boolean)this.friends.getValue() : (Boolean)this.players.getValue();
      } else {
         return false;
      }
   }

   private void Ke46c998cbb1ae53377(class_4587 matrices, class_1657 player, float partialTicks, class_243 camera) {
      double x = class_3532.method_16436((double)partialTicks, player.field_6014, player.method_23317()) - camera.field_1352;
      double y = class_3532.method_16436((double)partialTicks, player.field_6036, player.method_23318()) - camera.field_1351;
      double z = class_3532.method_16436((double)partialTicks, player.field_5969, player.method_23321()) - camera.field_1350;
      float bodyYaw = this.Kbf1ab8f80c3e738c70(player, partialTicks);
      float move = class_3532.method_15363(player.field_42108.method_48570(partialTicks), 0.0F, 1.0F);
      Kf27c1eaa510ec1f661 pose = this.K1a2d5e46b546a72b31(player, partialTicks);
      if (pose != null) {
         float flap = (float)Math.sin((double)(((float)player.field_6012 + partialTicks) * pose.flapSpeed)) * pose.flapAmplitude;
         float open = (8.0F + flap + move * pose.motionSpreadBoost) * pose.openMultiplier;
         float wingScale = this.size.getValue() * pose.scaleMultiplier;
         int baseColor = this.K195d434236da5091ad();
         int glowColor = K46ce892eb7b1549e97(baseColor, K939f04a266bd1c9599(255, 255, 255, 255), 0.28F);
         int coreColor = K46ce892eb7b1549e97(baseColor, K939f04a266bd1c9599(255, 255, 255, 255), 0.55F);
         matrices.method_22903();
         matrices.method_22904(x, y, z);
         matrices.method_22907(class_7833.field_40716.rotationDegrees(-bodyYaw));
         matrices.method_46416(0.0F, 0.0F, -(player.method_17681() * this.distance.getValue() + 0.08F));
         if (pose.preTranslateY != 0.0F || pose.preTranslateZ != 0.0F) {
            matrices.method_46416(0.0F, pose.preTranslateY, pose.preTranslateZ);
         }

         if (pose.pitchRotation != 0.0F) {
            matrices.method_22907(class_7833.field_40714.rotationDegrees(pose.pitchRotation));
         }

         if (pose.rollRotation != 0.0F) {
            matrices.method_22907(class_7833.field_40718.rotationDegrees(pose.rollRotation));
         }

         matrices.method_46416(0.0F, pose.anchorY, pose.anchorZ);
         matrices.method_22905(wingScale, wingScale, wingScale);
         this.K5764fa04076196df5a(matrices, -1.0F, open, baseColor, glowColor, coreColor, pose);
         this.K5764fa04076196df5a(matrices, 1.0F, open, baseColor, glowColor, coreColor, pose);
         matrices.method_22909();
      }
   }

   private void K3cdbf0e329c0d897f9(class_4587 matrices, class_1657 player, float partialTicks, class_243 camera) {
      double x = class_3532.method_16436((double)partialTicks, player.field_6014, player.method_23317()) - camera.field_1352;
      double y = class_3532.method_16436((double)partialTicks, player.field_6036, player.method_23318()) - camera.field_1351;
      double z = class_3532.method_16436((double)partialTicks, player.field_5969, player.method_23321()) - camera.field_1350;
      float bodyYaw = this.Kbf1ab8f80c3e738c70(player, partialTicks);
      float katanaScale = this.size.getValue() * 0.85F;
      float spread = this.katanaSpread.getValue();
      int accentColor;
      if (((String)this.colorMode.getValue()).equals("Тема")) {
         Color themeColor = Kb18d549c13309af9a5.primary();
         accentColor = K939f04a266bd1c9599(themeColor.getRed(), themeColor.getGreen(), themeColor.getBlue(), 220);
      } else {
         Color custom = (Color)this.customColor.getValue();
         accentColor = K939f04a266bd1c9599(custom.getRed(), custom.getGreen(), custom.getBlue(), 220);
      }

      matrices.method_22903();
      matrices.method_22904(x, y, z);
      matrices.method_22907(class_7833.field_40716.rotationDegrees(-bodyYaw));
      matrices.method_22903();
      matrices.method_46416(spread, 1.0F, -(player.method_17681() * this.distance.getValue() + 0.12F));
      matrices.method_22907(class_7833.field_40718.rotationDegrees(-55.0F));
      matrices.method_22905(katanaScale, katanaScale, katanaScale);
      this.Ka4286b101a0348c58e(matrices, accentColor);
      matrices.method_22909();
      matrices.method_22903();
      matrices.method_46416(-spread, 1.0F, -(player.method_17681() * this.distance.getValue() + 0.12F));
      matrices.method_22907(class_7833.field_40718.rotationDegrees(55.0F));
      matrices.method_22905(katanaScale, katanaScale, katanaScale);
      this.Ka4286b101a0348c58e(matrices, accentColor);
      matrices.method_22909();
      matrices.method_22909();
   }

   private void Ka4286b101a0348c58e(class_4587 matrices, int accentColor) {
      int scabbardColor = K939f04a266bd1c9599(15, 15, 15, 220);
      int goldColor = K939f04a266bd1c9599(212, 175, 55, 220);
      int handleColor = K939f04a266bd1c9599(160, 120, 80, 220);
      this.Kf4d5cbc52fc8850b21(matrices, 0.0F, -0.475F, 0.0F, 0.015F, 0.01F, 0.92F, 12, scabbardColor, accentColor);
      this.Kf4d5cbc52fc8850b21(matrices, 0.0F, 0.445F, 0.0F, 0.016F, 0.011F, 0.035F, 12, goldColor, goldColor);
      this.Kf4d5cbc52fc8850b21(matrices, 0.0F, 0.475F, 0.0F, 0.038F, 0.03F, 0.012F, 16, goldColor, goldColor);
      this.Kf4d5cbc52fc8850b21(matrices, 0.0F, 0.49F, 0.0F, 0.015F, 0.012F, 0.26F, 10, handleColor, accentColor);
      this.Kf4d5cbc52fc8850b21(matrices, 0.0F, 0.52F, 0.0F, 0.017F, 0.013F, 0.016F, 8, accentColor, accentColor);
      this.Kf4d5cbc52fc8850b21(matrices, 0.0F, 0.565F, 0.0F, 0.017F, 0.013F, 0.016F, 8, accentColor, accentColor);
      this.Kf4d5cbc52fc8850b21(matrices, 0.0F, 0.61F, 0.0F, 0.017F, 0.013F, 0.016F, 8, accentColor, accentColor);
      this.Kf4d5cbc52fc8850b21(matrices, 0.0F, 0.655F, 0.0F, 0.017F, 0.013F, 0.016F, 8, accentColor, accentColor);
      this.Kf4d5cbc52fc8850b21(matrices, 0.0F, 0.7F, 0.0F, 0.017F, 0.013F, 0.016F, 8, accentColor, accentColor);
   }

   private void Kf4d5cbc52fc8850b21(class_4587 matrices, float cx, float cy, float cz, float rx, float rz, float height, int segments, int baseColor, int accentColor) {
      int glowColor = K46ce892eb7b1549e97(accentColor, K939f04a266bd1c9599(255, 255, 255, 255), 0.35F);
      int glowAlpha = 35;
      int baseAlpha = 200;
      float e = 0.004F;
      RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
      this.Ke8399410d644852766(matrices, cx, cy - e, cz, rx + e, rz + e, height + e * 2.0F, segments, K6149c289f405d5d8dd(glowColor, glowAlpha));
      RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA);
      this.Ke8399410d644852766(matrices, cx, cy, cz, rx, rz, height, segments, K6149c289f405d5d8dd(baseColor, baseAlpha));
   }

   private void Kaa685c6652bedbec46(class_4587 matrices, float cx, float cy, float cz, float rx, float rz, float height, int segments, int color) {
      Matrix4f matrix = matrices.method_23760().method_23761();
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27377, class_290.field_1576);
      float r = (float)K4b4f0d587e92f6184e(color) / 255.0F;
      float g = (float)Kb977c8d8c8c5dbe100(color) / 255.0F;
      float b = (float)K006aee99ea898f3e48(color) / 255.0F;
      float a = (float)Kf45e1ee16ce9cce5a2(color) / 255.0F;
      GL11.glEnable(2848);
      RenderSystem.lineWidth(1.2F);

      for(int i = 0; i < segments; ++i) {
         float angle1 = (float)((Math.PI * 2D) * (double)i / (double)segments);
         float angle2 = (float)((Math.PI * 2D) * (double)(i + 1) / (double)segments);
         float cos1 = (float)Math.cos((double)angle1);
         float sin1 = (float)Math.sin((double)angle1);
         float cos2 = (float)Math.cos((double)angle2);
         float sin2 = (float)Math.sin((double)angle2);
         float x1 = cx + rx * cos1;
         float z1 = cz + rz * sin1;
         float x2 = cx + rx * cos2;
         float z2 = cz + rz * sin2;
         buffer.method_22918(matrix, x1, cy, z1).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x2, cy, z2).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x1, cy + height, z1).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x2, cy + height, z2).method_22915(r, g, b, a);
         if (i % 3 == 0) {
            buffer.method_22918(matrix, x1, cy, z1).method_22915(r, g, b, a);
            buffer.method_22918(matrix, x1, cy + height, z1).method_22915(r, g, b, a);
         }
      }

      class_286.method_43433(buffer.method_60800());
      GL11.glDisable(2848);
   }

   private void K1e76e5355335e2e5ca(class_4587 matrices, float x, float y, float z, float width, float length, int color) {
      Matrix4f matrix = matrices.method_23760().method_23761();
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);
      float r = (float)K4b4f0d587e92f6184e(color) / 255.0F;
      float g = (float)Kb977c8d8c8c5dbe100(color) / 255.0F;
      float b = (float)K006aee99ea898f3e48(color) / 255.0F;
      float a = (float)Kf45e1ee16ce9cce5a2(color) / 255.0F;
      float baseWidth = width;
      int segments = 12;

      for(int i = 0; i < segments; ++i) {
         float t1 = (float)i / (float)segments;
         float t2 = (float)(i + 1) / (float)segments;
         float y1 = y + length * t1;
         float y2 = y + length * t2;
         float w1 = baseWidth * (1.0F - t1 * 0.6F);
         float w2 = baseWidth * (1.0F - t2 * 0.6F);
         if (t2 > 0.75F) {
            float roundFactor = (t2 - 0.75F) / 0.25F;
            w2 *= 1.0F - roundFactor * roundFactor;
         }

         buffer.method_22918(matrix, x - w1, y1, z).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x + w1, y1, z).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x + w2, y2, z).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x - w1, y1, z).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x + w2, y2, z).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x - w2, y2, z).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x - w1, y1, z + 0.002F).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x + w2, y2, z + 0.002F).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x + w1, y1, z + 0.002F).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x - w1, y1, z + 0.002F).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x - w2, y2, z + 0.002F).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x + w2, y2, z + 0.002F).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x - w1, y1, z).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x - w2, y2, z).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x - w1, y1, z + 0.002F).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x - w1, y1, z + 0.002F).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x - w2, y2, z).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x - w2, y2, z + 0.002F).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x + w1, y1, z).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x + w1, y1, z + 0.002F).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x + w2, y2, z).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x + w1, y1, z + 0.002F).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x + w2, y2, z + 0.002F).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x + w2, y2, z).method_22915(r, g, b, a);
      }

      class_286.method_43433(buffer.method_60800());
   }

   private void Ke8399410d644852766(class_4587 matrices, float x, float y, float z, float rx, float rz, float height, int segments, int color) {
      Matrix4f matrix = matrices.method_23760().method_23761();
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);
      float r = (float)K4b4f0d587e92f6184e(color) / 255.0F;
      float g = (float)Kb977c8d8c8c5dbe100(color) / 255.0F;
      float b = (float)K006aee99ea898f3e48(color) / 255.0F;
      float a = (float)Kf45e1ee16ce9cce5a2(color) / 255.0F;

      for(int i = 0; i < segments; ++i) {
         float angle1 = (float)((Math.PI * 2D) * (double)i / (double)segments);
         float angle2 = (float)((Math.PI * 2D) * (double)(i + 1) / (double)segments);
         float x1 = x + rx * (float)Math.cos((double)angle1);
         float z1 = z + rz * (float)Math.sin((double)angle1);
         float x2 = x + rx * (float)Math.cos((double)angle2);
         float z2 = z + rz * (float)Math.sin((double)angle2);
         buffer.method_22918(matrix, x1, y, z1).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x2, y, z2).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x2, y + height, z2).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x1, y, z1).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x2, y + height, z2).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x1, y + height, z1).method_22915(r, g, b, a);
      }

      for(int i = 0; i < segments; ++i) {
         float angle1 = (float)((Math.PI * 2D) * (double)i / (double)segments);
         float angle2 = (float)((Math.PI * 2D) * (double)(i + 1) / (double)segments);
         float x1 = x + rx * (float)Math.cos((double)angle1);
         float z1 = z + rz * (float)Math.sin((double)angle1);
         float x2 = x + rx * (float)Math.cos((double)angle2);
         float z2 = z + rz * (float)Math.sin((double)angle2);
         buffer.method_22918(matrix, x, y + height, z).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x1, y + height, z1).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x2, y + height, z2).method_22915(r, g, b, a);
      }

      for(int i = 0; i < segments; ++i) {
         float angle1 = (float)((Math.PI * 2D) * (double)i / (double)segments);
         float angle2 = (float)((Math.PI * 2D) * (double)(i + 1) / (double)segments);
         float x1 = x + rx * (float)Math.cos((double)angle1);
         float z1 = z + rz * (float)Math.sin((double)angle1);
         float x2 = x + rx * (float)Math.cos((double)angle2);
         float z2 = z + rz * (float)Math.sin((double)angle2);
         buffer.method_22918(matrix, x, y, z).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x2, y, z2).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x1, y, z1).method_22915(r, g, b, a);
      }

      class_286.method_43433(buffer.method_60800());
   }

   private void Kcba89ec17eb69ca815(class_4587 matrices, float x, float y, float z, float radius, int segments, int color) {
      Matrix4f matrix = matrices.method_23760().method_23761();
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);
      float r = (float)K4b4f0d587e92f6184e(color) / 255.0F;
      float g = (float)Kb977c8d8c8c5dbe100(color) / 255.0F;
      float b = (float)K006aee99ea898f3e48(color) / 255.0F;
      float a = (float)Kf45e1ee16ce9cce5a2(color) / 255.0F;

      for(int i = 0; i < segments; ++i) {
         for(int j = 0; j < segments / 2; ++j) {
            float theta1 = (float)(Math.PI * (double)i / (double)segments);
            float theta2 = (float)(Math.PI * (double)(i + 1) / (double)segments);
            float phi1 = (float)(Math.PI * (double)(j + segments / 2) / (double)segments);
            float phi2 = (float)(Math.PI * (double)(j + 1 + segments / 2) / (double)segments);
            float x1 = x + radius * (float)(Math.sin((double)phi1) * Math.cos((double)theta1));
            float y1 = y + radius * (float)Math.cos((double)phi1);
            float z1 = z + radius * (float)(Math.sin((double)phi1) * Math.sin((double)theta1));
            float x2 = x + radius * (float)(Math.sin((double)phi1) * Math.cos((double)theta2));
            float y2 = y + radius * (float)Math.cos((double)phi1);
            float z2 = z + radius * (float)(Math.sin((double)phi1) * Math.sin((double)theta2));
            float x3 = x + radius * (float)(Math.sin((double)phi2) * Math.cos((double)theta2));
            float y3 = y + radius * (float)Math.cos((double)phi2);
            float z3 = z + radius * (float)(Math.sin((double)phi2) * Math.sin((double)theta2));
            float x4 = x + radius * (float)(Math.sin((double)phi2) * Math.cos((double)theta1));
            float y4 = y + radius * (float)Math.cos((double)phi2);
            float z4 = z + radius * (float)(Math.sin((double)phi2) * Math.sin((double)theta1));
            buffer.method_22918(matrix, x1, y1, z1).method_22915(r, g, b, a);
            buffer.method_22918(matrix, x2, y2, z2).method_22915(r, g, b, a);
            buffer.method_22918(matrix, x3, y3, z3).method_22915(r, g, b, a);
            buffer.method_22918(matrix, x1, y1, z1).method_22915(r, g, b, a);
            buffer.method_22918(matrix, x3, y3, z3).method_22915(r, g, b, a);
            buffer.method_22918(matrix, x4, y4, z4).method_22915(r, g, b, a);
         }
      }

      class_286.method_43433(buffer.method_60800());
   }

   private void Kb245f3f3d8ca7eb1d2(class_4587 matrices, float cx, float cy, float cz, float topRx, float topRz, float bottomRx, float bottomRz, float height, int segments, int color) {
      Matrix4f matrix = matrices.method_23760().method_23761();
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);
      float r = (float)K4b4f0d587e92f6184e(color) / 255.0F;
      float g = (float)Kb977c8d8c8c5dbe100(color) / 255.0F;
      float b = (float)K006aee99ea898f3e48(color) / 255.0F;
      float a = (float)Kf45e1ee16ce9cce5a2(color) / 255.0F;

      for(int i = 0; i < segments; ++i) {
         float angle1 = (float)((Math.PI * 2D) * (double)i / (double)segments);
         float angle2 = (float)((Math.PI * 2D) * (double)(i + 1) / (double)segments);
         float cos1 = (float)Math.cos((double)angle1);
         float sin1 = (float)Math.sin((double)angle1);
         float cos2 = (float)Math.cos((double)angle2);
         float sin2 = (float)Math.sin((double)angle2);
         float x1Top = cx + topRx * cos1;
         float z1Top = cz + topRz * sin1;
         float x2Top = cx + topRx * cos2;
         float z2Top = cz + topRz * sin2;
         float x1Bottom = cx + bottomRx * cos1;
         float z1Bottom = cz + bottomRz * sin1;
         float x2Bottom = cx + bottomRx * cos2;
         float z2Bottom = cz + bottomRz * sin2;
         buffer.method_22918(matrix, x1Top, cy, z1Top).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x2Top, cy, z2Top).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x2Bottom, cy - height, z2Bottom).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x1Top, cy, z1Top).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x2Bottom, cy - height, z2Bottom).method_22915(r, g, b, a);
         buffer.method_22918(matrix, x1Bottom, cy - height, z1Bottom).method_22915(r, g, b, a);
      }

      for(int i = 0; i < segments; ++i) {
         float angle1 = (float)((Math.PI * 2D) * (double)i / (double)segments);
         float angle2 = (float)((Math.PI * 2D) * (double)(i + 1) / (double)segments);

         for(int j = 0; j < segments / 2; ++j) {
            float phi1 = (float)(Math.PI * (double)(j + segments / 2) / (double)segments);
            float phi2 = (float)(Math.PI * (double)(j + 1 + segments / 2) / (double)segments);
            float x1 = cx + bottomRx * (float)(Math.sin((double)phi1) * Math.cos((double)angle1));
            float y1 = cy - height + bottomRx * (float)Math.cos((double)phi1);
            float z1 = cz + bottomRz * (float)(Math.sin((double)phi1) * Math.sin((double)angle1));
            float x2 = cx + bottomRx * (float)(Math.sin((double)phi1) * Math.cos((double)angle2));
            float y2 = cy - height + bottomRx * (float)Math.cos((double)phi1);
            float z2 = cz + bottomRz * (float)(Math.sin((double)phi1) * Math.sin((double)angle2));
            float x3 = cx + bottomRx * (float)(Math.sin((double)phi2) * Math.cos((double)angle2));
            float y3 = cy - height + bottomRx * (float)Math.cos((double)phi2);
            float z3 = cz + bottomRz * (float)(Math.sin((double)phi2) * Math.sin((double)angle2));
            float x4 = cx + bottomRx * (float)(Math.sin((double)phi2) * Math.cos((double)angle1));
            float y4 = cy - height + bottomRx * (float)Math.cos((double)phi2);
            float z4 = cz + bottomRz * (float)(Math.sin((double)phi2) * Math.sin((double)angle1));
            buffer.method_22918(matrix, x1, y1, z1).method_22915(r, g, b, a);
            buffer.method_22918(matrix, x2, y2, z2).method_22915(r, g, b, a);
            buffer.method_22918(matrix, x3, y3, z3).method_22915(r, g, b, a);
            buffer.method_22918(matrix, x1, y1, z1).method_22915(r, g, b, a);
            buffer.method_22918(matrix, x3, y3, z3).method_22915(r, g, b, a);
            buffer.method_22918(matrix, x4, y4, z4).method_22915(r, g, b, a);
         }
      }

      class_286.method_43433(buffer.method_60800());
   }

   private void K5764fa04076196df5a(class_4587 matrices, float side, float open, int baseColor, int glowColor, int coreColor, Kf27c1eaa510ec1f661 pose) {
      matrices.method_22903();
      matrices.method_46416(side * pose.sideOffset, pose.sideYOffset, pose.sideZOffset);
      matrices.method_22907(class_7833.field_40716.rotationDegrees(side * open));
      matrices.method_22907(class_7833.field_40718.rotationDegrees(side * pose.sideRoll));
      matrices.method_22907(class_7833.field_40714.rotationDegrees(pose.sidePitch));
      RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
      this.Ke686637dc8a549fbaf(matrices, side, 1.22F, K6149c289f405d5d8dd(glowColor, 48), K6149c289f405d5d8dd(glowColor, 0));
      this.Ke686637dc8a549fbaf(matrices, side, 0.84F, K6149c289f405d5d8dd(coreColor, 57), K6149c289f405d5d8dd(coreColor, 0));
      RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA);
      this.Ke686637dc8a549fbaf(matrices, side, 1.0F, K6149c289f405d5d8dd(baseColor, 220), K6149c289f405d5d8dd(baseColor, 10));
      RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
      this.K1c8b30ece1c04c3da2(matrices, side, 1.0F, K6149c289f405d5d8dd(baseColor, 136));
      this.K50b431988fbe32fcf6(matrices, side, 0.96F, K6149c289f405d5d8dd(glowColor, 44));
      matrices.method_22909();
   }

   private void Ke686637dc8a549fbaf(class_4587 matrices, float side, float scale, int rootColor, int edgeColor) {
      Matrix4f matrix = matrices.method_23760().method_23761();
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);

      for(int i = 0; i < SHAPE.length; ++i) {
         Kee1fb7bcdc47aee70b current = SHAPE[i];
         Kee1fb7bcdc47aee70b next = SHAPE[(i + 1) % SHAPE.length];
         this.Ka4f960933e7f872890(buffer, matrix, 0.0F, 0.0F, 0.0F, rootColor);
         this.Ka4f960933e7f872890(buffer, matrix, side * current.x * scale, current.y * scale, 0.0F, this.Kb674c4292280179824(edgeColor, current.alphaMul));
         this.Ka4f960933e7f872890(buffer, matrix, side * next.x * scale, next.y * scale, 0.0F, this.Kb674c4292280179824(edgeColor, next.alphaMul));
      }

      class_286.method_43433(buffer.method_60800());
   }

   private void K1c8b30ece1c04c3da2(class_4587 matrices, float side, float scale, int color) {
      Matrix4f matrix = matrices.method_23760().method_23761();
      RenderSystem.lineWidth(1.35F);
      boolean lineSmoothWasEnabled = GL11.glIsEnabled(2848);
      GL11.glEnable(2848);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_29345, class_290.field_1576);

      for(Kee1fb7bcdc47aee70b point : SHAPE) {
         this.Ka4f960933e7f872890(buffer, matrix, side * point.x * scale, point.y * scale, 0.0F, color);
      }

      this.Ka4f960933e7f872890(buffer, matrix, side * SHAPE[0].x * scale, SHAPE[0].y * scale, 0.0F, color);
      class_286.method_43433(buffer.method_60800());
      if (!lineSmoothWasEnabled) {
         GL11.glDisable(2848);
      }

   }

   private void K50b431988fbe32fcf6(class_4587 matrices, float side, float scale, int color) {
      Matrix4f matrix = matrices.method_23760().method_23761();
      int[] ribIndices = new int[]{2, 4, 7, 9, 11};
      RenderSystem.lineWidth(0.9F);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27377, class_290.field_1576);

      for(int index : ribIndices) {
         Kee1fb7bcdc47aee70b point = SHAPE[index];
         this.Ka4f960933e7f872890(buffer, matrix, 0.0F, 0.0F, 0.0F, K6149c289f405d5d8dd(color, Math.max(8, (int)((float)Kf45e1ee16ce9cce5a2(color) * 0.75F))));
         this.Ka4f960933e7f872890(buffer, matrix, side * point.x * scale, point.y * scale, 0.0F, this.Kb674c4292280179824(color, point.alphaMul));
      }

      class_286.method_43433(buffer.method_60800());
   }

   private int K195d434236da5091ad() {
      Color color = Kb18d549c13309af9a5.primary();
      return K939f04a266bd1c9599(color.getRed(), color.getGreen(), color.getBlue(), 255);
   }

   private static int K46ce892eb7b1549e97(int a, int b, float t) {
      int ar = a >> 16 & 255;
      int ag = a >> 8 & 255;
      int ab = a & 255;
      int aa = a >> 24 & 255;
      int br = b >> 16 & 255;
      int bg = b >> 8 & 255;
      int bb = b & 255;
      int ba = b >> 24 & 255;
      return K939f04a266bd1c9599((int)((float)ar + (float)(br - ar) * t), (int)((float)ag + (float)(bg - ag) * t), (int)((float)ab + (float)(bb - ab) * t), (int)((float)aa + (float)(ba - aa) * t));
   }

   private int Kb674c4292280179824(int color, float multiplier) {
      return K6149c289f405d5d8dd(color, Math.max(0, Math.min(255, (int)((float)Kf45e1ee16ce9cce5a2(color) * multiplier))));
   }

   private static int K6149c289f405d5d8dd(int color, int alpha) {
      return class_3532.method_15340(alpha, 0, 255) << 24 | color & 16777215;
   }

   private static int Kf45e1ee16ce9cce5a2(int color) {
      return color >> 24 & 255;
   }

   private static int K4b4f0d587e92f6184e(int color) {
      return color >> 16 & 255;
   }

   private static int Kb977c8d8c8c5dbe100(int color) {
      return color >> 8 & 255;
   }

   private static int K006aee99ea898f3e48(int color) {
      return color & 255;
   }

   private static int K939f04a266bd1c9599(int r, int g, int b, int a) {
      return a << 24 | r << 16 | g << 8 | b;
   }

   private void Ka4f960933e7f872890(class_287 buffer, Matrix4f matrix, float x, float y, float z, int color) {
      buffer.method_22918(matrix, x, y, z).method_22915((float)K4b4f0d587e92f6184e(color) / 255.0F, (float)Kb977c8d8c8c5dbe100(color) / 255.0F, (float)K006aee99ea898f3e48(color) / 255.0F, (float)Kf45e1ee16ce9cce5a2(color) / 255.0F);
   }

   private float Kbf1ab8f80c3e738c70(class_1657 player, float partialTicks) {
      float target = class_3532.method_17821(partialTicks, player.field_6220, player.field_6283);
      if (player != mc.field_1724) {
         return target;
      } else if (this.selfBodyYawInitialized && player.field_6012 >= 2) {
         this.selfBodyYaw = K393f252cea0ac404b0(this.selfBodyYaw, target, 14.0F);
         return this.selfBodyYaw;
      } else {
         this.selfBodyYaw = target;
         this.selfBodyYawInitialized = true;
         return this.selfBodyYaw;
      }
   }

   private static float K393f252cea0ac404b0(float current, float target, float maxDelta) {
      float delta = class_3532.method_15393(target - current);
      delta = class_3532.method_15363(delta, -maxDelta, maxDelta);
      return current + delta;
   }

   private Kf27c1eaa510ec1f661 K1a2d5e46b546a72b31(class_1657 player, float partialTicks) {
      float pitch = class_3532.method_16439(partialTicks, player.field_6004, player.method_36455());
      boolean swimming = player.method_5681() || player.method_5799() && !player.method_24828();
      if (player.method_6113()) {
         return null;
      } else if (player.method_6128()) {
         float flightTicks = (float)player.method_6003() + partialTicks;
         float flightProgress = class_3532.method_15363(flightTicks * flightTicks / 100.0F, 0.0F, 1.0F);
         float pitchRotation = flightProgress * (-90.0F - pitch);
         return new Kf27c1eaa510ec1f661(0.24F, -0.02F, 0.02F, -0.01F, pitchRotation, 0.0F, 0.72F, 0.92F, 0.1F, 0.58F, 0.03F, 0.0F, 0.005F, -5.0F, -2.0F, 0.13F);
      } else if (swimming) {
         return new Kf27c1eaa510ec1f661(0.12F, -0.01F, 0.82F, -0.02F, 48.0F, 0.0F, 0.86F, 0.94F, 0.08F, 3.2F, 0.04F, 0.0F, 0.015F, -9.0F, -7.0F, 0.1F);
      } else {
         return player.method_5715() ? new Kf27c1eaa510ec1f661(0.22F, -0.22F, 0.92F, -0.02F, 18.0F, 0.0F, 1.0F, 1.0F, 0.18F, 4.5F, 0.035F, 0.0F, 0.01F, -11.0F, -4.0F, 0.12F) : new Kf27c1eaa510ec1f661(0.0F, -0.01F, 1.22F, -0.025F, 0.0F, 0.0F, 1.0F, 1.0F, 0.18F, 4.5F, 0.035F, 0.0F, 0.01F, -11.0F, -4.0F, 0.12F);
      }
   }

   @Generated
   public static K31b67d2e365a61bf94 getInstance() {
      return instance;
   }
}
