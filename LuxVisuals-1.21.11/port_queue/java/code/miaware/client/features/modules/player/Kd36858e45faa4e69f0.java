package code.miaware.client.features.modules.player;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Ka4b85e821e3749c3d9;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.configs.K137d4e93c7270b0a04;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import lombok.Generated;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_7833;
import net.minecraft.class_239.class_240;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.joml.Matrix4f;

@ModuleRegister(
   name = "Item Radius",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Показывает радиус действия предметов"
)
public class Kd36858e45faa4e69f0 extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final Kd36858e45faa4e69f0 instance = new Kd36858e45faa4e69f0();
   private final Ka4b85e821e3749c3d9 items = (new Ka4b85e821e3749c3d9("Предметы")).value((new K00fef91b3d4d1b3ecc("Дезка")).value(true), (new K00fef91b3d4d1b3ecc("Явка")).value(true), (new K00fef91b3d4d1b3ecc("Огненый Заряд")).value(true), (new K00fef91b3d4d1b3ecc("Божья Аура")).value(true), (new K00fef91b3d4d1b3ecc("Трапка")).value(true), (new K00fef91b3d4d1b3ecc("Пласт")).value(true));
   private int currentOutlineColor = -1;
   private int targetOutlineColor = -1;
   private boolean lastPlayersInRadius = false;
   private class_243 plastSmoothedCenter;
   private float plastSmoothedYawDeg;
   private float plastSmoothedPitchDeg;
   private boolean plastHasSmoothedPose;

   public Kd36858e45faa4e69f0() {
      this.plastSmoothedCenter = class_243.field_1353;
      this.plastSmoothedYawDeg = 0.0F;
      this.plastSmoothedPitchDeg = 0.0F;
      this.plastHasSmoothedPose = false;
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.items});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 render3DEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4(this::K4be8402bd59aa79ebc));
      this.addEvents(new K8d7165024ff567f5e7[]{render3DEvent});
   }

   private void K4be8402bd59aa79ebc(K9ee087b2e1791ad6b2 event) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         class_1799 main = mc.field_1724.method_6047();
         class_1799 off = mc.field_1724.method_6079();
         boolean handled = false;
         event.matrixStack().method_22903();
         if (this.items.isEnabled("Дезка") && (K2f93c1da75703c9345(main, class_1802.field_8449) || K2f93c1da75703c9345(off, class_1802.field_8449))) {
            class_1799 eyeStack = K2f93c1da75703c9345(main, class_1802.field_8449) ? main : off;
            if (K4e6c8da41dc8bb5dd3(eyeStack, 'Я')) {
               handled = true;
               float radius = 10.0F;
               class_243 center = this.K16154d2d379b0b1628(event.partialTicks());
               boolean playersInRadius = this.Kc2ad7e2025b1637c53(mc.field_1724, center, (double)radius);
               this.K1c39cc68c92c1e8430(playersInRadius, event.partialTicks());
               int fillColor = playersInRadius ? 855703296 : 872415231;
               this.K80fa6a55682cf47ce3(event, center, radius, fillColor);
               this.K472ddc430d7a2e09fb(event, center, radius, this.currentOutlineColor);
            }
         }

         if (!handled && this.items.isEnabled("Явка") && (K2f93c1da75703c9345(main, class_1802.field_8479) || K2f93c1da75703c9345(off, class_1802.field_8479))) {
            class_1799 sugarStack = K2f93c1da75703c9345(main, class_1802.field_8479) ? main : off;
            if (K4e6c8da41dc8bb5dd3(sugarStack, 'Я')) {
               handled = true;
               float radius = 10.0F;
               class_243 center = this.K16154d2d379b0b1628(event.partialTicks());
               boolean playersInRadius = this.Kc2ad7e2025b1637c53(mc.field_1724, center, (double)radius);
               this.K1c39cc68c92c1e8430(playersInRadius, event.partialTicks());
               int fillColor = playersInRadius ? 855703296 : 872415231;
               this.K80fa6a55682cf47ce3(event, center, radius, fillColor);
               this.K472ddc430d7a2e09fb(event, center, radius, this.currentOutlineColor);
            }
         }

         if (!handled && this.items.isEnabled("Огненый Заряд") && (K2f93c1da75703c9345(main, class_1802.field_8814) || K2f93c1da75703c9345(off, class_1802.field_8814))) {
            class_1799 fireChargeStack = K2f93c1da75703c9345(main, class_1802.field_8814) ? main : off;
            if (K4e6c8da41dc8bb5dd3(fireChargeStack, 'С')) {
               handled = true;
               float radius = 10.0F;
               class_243 center = this.K16154d2d379b0b1628(event.partialTicks());
               boolean playersInRadius = this.Kc2ad7e2025b1637c53(mc.field_1724, center, (double)radius);
               this.K1c39cc68c92c1e8430(playersInRadius, event.partialTicks());
               int fillColor = playersInRadius ? 855703296 : 872415231;
               this.K80fa6a55682cf47ce3(event, center, radius, fillColor);
               this.K472ddc430d7a2e09fb(event, center, radius, this.currentOutlineColor);
            }
         }

         if (!handled && this.items.isEnabled("Божья Аура") && (K2f93c1da75703c9345(main, class_1802.field_8614) || K2f93c1da75703c9345(off, class_1802.field_8614))) {
            class_1799 membraneStack = K2f93c1da75703c9345(main, class_1802.field_8614) ? main : off;
            if (K4e6c8da41dc8bb5dd3(membraneStack, 'У')) {
               handled = true;
               float radius = 2.0F;
               class_243 center = this.K16154d2d379b0b1628(event.partialTicks());
               boolean playersInRadius = this.Kc2ad7e2025b1637c53(mc.field_1724, center, (double)radius);
               boolean friendsInRadius = this.K5c5961fba222149068(mc.field_1724, center, (double)radius);
               this.K1c39cc68c92c1e8430(playersInRadius, event.partialTicks());
               int fillColor = friendsInRadius ? 855703296 : 872415231;
               this.K80fa6a55682cf47ce3(event, center, radius, fillColor);
               this.K472ddc430d7a2e09fb(event, center, radius, this.currentOutlineColor);
            }
         }

         if (!handled && this.items.isEnabled("Трапка") && (K2f93c1da75703c9345(main, class_1802.field_22021) || K2f93c1da75703c9345(off, class_1802.field_22021))) {
            class_1799 scrapStack = K2f93c1da75703c9345(main, class_1802.field_22021) ? main : off;
            if (K4e6c8da41dc8bb5dd3(scrapStack, 'А')) {
               handled = true;
               class_243 p = this.Ke1737fbf6383bdc801(event.partialTicks());
               class_243 cubeCenter = new class_243(p.field_1352, p.field_1351 + (double)0.5F + (double)1.625F, p.field_1350);
               boolean playersInRadius = this.Kc2ad7e2025b1637c53(mc.field_1724, cubeCenter, (double)2.5F);
               this.K1c39cc68c92c1e8430(playersInRadius, event.partialTicks());
               this.K470503b8f6aab23bd4(event, cubeCenter, 4.0F, this.currentOutlineColor);
            }
         }

         if (!handled && this.items.isEnabled("Пласт") && (K2f93c1da75703c9345(main, class_1802.field_8551) || K2f93c1da75703c9345(off, class_1802.field_8551))) {
            class_1799 kelpStack = K2f93c1da75703c9345(main, class_1802.field_8551) ? main : off;
            if (K4e6c8da41dc8bb5dd3(kelpStack, 'П')) {
               handled = true;
               K210b017c4c303e2174 pose = this.K580e68833f4603cacd(this.Kd45d90415164e8ef66(event.partialTicks()), event.partialTicks());
               boolean playersInRadius = this.Kc2ad7e2025b1637c53(mc.field_1724, pose.center, (double)2.5F);
               if (playersInRadius) {
                  this.Kfd3d0a101f29168e5f(event, pose, 1727987712);
                  this.Ke567931d5a160ac01d(event, pose, -65536);
               } else {
                  this.Ke567931d5a160ac01d(event, pose, -16711936);
               }
            }
         }

         if (!handled) {
            this.plastHasSmoothedPose = false;
         }

         event.matrixStack().method_22909();
      }
   }

   private void K80fa6a55682cf47ce3(K9ee087b2e1791ad6b2 event, class_243 center, float radius, int fillColor) {
      if (mc.field_1724 != null) {
         try {
            class_243 camera = mc.field_1773.method_19418().method_19326();
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA, class_4535.ONE, class_4534.ZERO);
            RenderSystem.disableCull();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            Matrix4f matrix = event.matrixStack().method_23760().method_23761();
            class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);
            float y = (float)(center.field_1351 + 1.8);
            float cx = (float)(center.field_1352 - camera.field_1352);
            float cy = (float)((double)y - camera.field_1351);
            float cz = (float)(center.field_1350 - camera.field_1350);
            int r = fillColor >> 16 & 255;
            int g = fillColor >> 8 & 255;
            int b = fillColor & 255;
            int a = fillColor >> 24 & 255;
            int stepDeg = 5;
            float prevX = 0.0F;
            float prevZ = 0.0F;
            boolean hasPrev = false;

            for(int deg = 0; deg <= 360; deg += stepDeg) {
               double rad = Math.toRadians((double)deg);
               float x = cx + class_3532.method_15374((float)rad) * radius;
               float z = cz + -class_3532.method_15362((float)rad) * radius;
               if (!hasPrev) {
                  prevX = x;
                  prevZ = z;
                  hasPrev = true;
               } else {
                  buffer.method_22918(matrix, cx, cy, cz).method_1336(r, g, b, a);
                  buffer.method_22918(matrix, prevX, cy, prevZ).method_1336(r, g, b, a);
                  buffer.method_22918(matrix, x, cy, z).method_1336(r, g, b, a);
                  prevX = x;
                  prevZ = z;
               }
            }

            class_286.method_43433(buffer.method_60800());
         } catch (Exception var28) {
         } finally {
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask(true);
            RenderSystem.enableCull();
            RenderSystem.blendFuncSeparate(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA, class_4535.ONE, class_4534.ZERO);
            RenderSystem.disableBlend();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         }

      }
   }

   private K210b017c4c303e2174 K580e68833f4603cacd(K210b017c4c303e2174 target, float tickDelta) {
      if (!this.plastHasSmoothedPose) {
         this.plastSmoothedCenter = target.center;
         this.plastSmoothedYawDeg = target.yawDeg;
         this.plastSmoothedPitchDeg = target.pitchDeg;
         this.plastHasSmoothedPose = true;
         return target;
      } else {
         float speed = 12.0F;
         float t = 1.0F - (float)Math.exp((double)(-speed * Math.max(0.0F, tickDelta)));
         this.plastSmoothedCenter = this.plastSmoothedCenter.method_35590(target.center, (double)t);
         this.plastSmoothedYawDeg = Kd24c15562de81afc34(this.plastSmoothedYawDeg, target.yawDeg, t);
         this.plastSmoothedPitchDeg = Kd24c15562de81afc34(this.plastSmoothedPitchDeg, target.pitchDeg, t);
         return new K210b017c4c303e2174(this.plastSmoothedCenter, this.plastSmoothedYawDeg, this.plastSmoothedPitchDeg);
      }
   }

   private static float Kd24c15562de81afc34(float from, float to, float t) {
      float delta = class_3532.method_15393(to - from);
      return from + delta * t;
   }

   private void K1c39cc68c92c1e8430(boolean playersInRadius, float tickDelta) {
      int baseOutline = -1;
      int lightOutline = -16711936;
      this.currentOutlineColor = playersInRadius ? lightOutline : baseOutline;
      this.targetOutlineColor = this.currentOutlineColor;
      this.lastPlayersInRadius = playersInRadius;
   }

   private static boolean K2f93c1da75703c9345(class_1799 stack, class_1792 item) {
      return stack != null && !stack.method_7960() && stack.method_31574(item);
   }

   private static boolean K4e6c8da41dc8bb5dd3(class_1799 stack, char letter) {
      if (stack != null && !stack.method_7960()) {
         String name = stack.method_7964().getString();
         if (name != null && !name.isEmpty()) {
            String upper = name.toUpperCase();
            return upper.indexOf(Character.toUpperCase(letter)) >= 0;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean Kc2ad7e2025b1637c53(class_1657 self, class_243 centerPos, double radius) {
      if (mc.field_1687 == null) {
         return false;
      } else {
         double r2 = radius * radius;

         for(class_1657 p : mc.field_1687.method_18456()) {
            if (p != null && p != self && p.method_5707(centerPos) <= r2) {
               return true;
            }
         }

         return false;
      }
   }

   private boolean K5c5961fba222149068(class_1657 self, class_243 centerPos, double radius) {
      if (mc.field_1687 == null) {
         return false;
      } else {
         K137d4e93c7270b0a04 friendManager = K137d4e93c7270b0a04.getInstance();
         if (friendManager == null) {
            return false;
         } else {
            double r2 = radius * radius;

            for(class_1657 p : mc.field_1687.method_18456()) {
               if (p != null && p != self && !(p.method_5707(centerPos) > r2)) {
                  String name = p.method_5477().getString();
                  if (name != null && friendManager.contains(name)) {
                     return true;
                  }
               }
            }

            return false;
         }
      }
   }

   private class_243 Ke1737fbf6383bdc801(float tickDelta) {
      if (mc.field_1724 == null) {
         return class_243.field_1353;
      } else {
         double x = class_3532.method_16436((double)tickDelta, mc.field_1724.field_6014, mc.field_1724.method_23317());
         double y = class_3532.method_16436((double)tickDelta, mc.field_1724.field_6036, mc.field_1724.method_23318());
         double z = class_3532.method_16436((double)tickDelta, mc.field_1724.field_5969, mc.field_1724.method_23321());
         return new class_243(x, y, z);
      }
   }

   private class_243 K16154d2d379b0b1628(float tickDelta) {
      class_243 pos = this.Ke1737fbf6383bdc801(tickDelta);
      return pos.method_1031((double)0.0F, -1.4, (double)0.0F);
   }

   private void K472ddc430d7a2e09fb(K9ee087b2e1791ad6b2 event, class_243 center, float radius, int outlineColor) {
      if (mc.field_1724 != null) {
         try {
            Matrix4f matrix = event.matrixStack().method_23760().method_23761();
            class_243 camera = mc.field_1773.method_19418().method_19326();
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA, class_4535.ONE, class_4534.ZERO);
            RenderSystem.disableDepthTest();
            RenderSystem.lineWidth(3.0F);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            class_287 buffer = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);
            float y = (float)(center.field_1351 + 1.8);
            float cx = (float)(center.field_1352 - camera.field_1352);
            float cy = (float)((double)y - camera.field_1351);
            float cz = (float)(center.field_1350 - camera.field_1350);
            int r = outlineColor >> 16 & 255;
            int g = outlineColor >> 8 & 255;
            int b = outlineColor & 255;
            int a = outlineColor >> 24 & 255;
            int stepDeg = 5;
            float firstX = 0.0F;
            float firstZ = 0.0F;
            float prevX = 0.0F;
            float prevZ = 0.0F;
            boolean hasPrev = false;

            for(int deg = 0; deg <= 360; deg += stepDeg) {
               double rad = Math.toRadians((double)deg);
               float x = cx + class_3532.method_15374((float)rad) * radius;
               float z = cz + -class_3532.method_15362((float)rad) * radius;
               if (!hasPrev) {
                  firstX = x;
                  firstZ = z;
                  prevX = x;
                  prevZ = z;
                  hasPrev = true;
               } else {
                  buffer.method_22918(matrix, prevX, cy, prevZ).method_1336(r, g, b, a);
                  buffer.method_22918(matrix, x, cy, z).method_1336(r, g, b, a);
                  prevX = x;
                  prevZ = z;
               }
            }

            if (hasPrev) {
               buffer.method_22918(matrix, prevX, cy, prevZ).method_1336(r, g, b, a);
               buffer.method_22918(matrix, firstX, cy, firstZ).method_1336(r, g, b, a);
            }

            class_286.method_43433(buffer.method_60800());
         } catch (Exception var30) {
         } finally {
            RenderSystem.lineWidth(1.0F);
            RenderSystem.enableDepthTest();
            RenderSystem.blendFuncSeparate(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA, class_4535.ONE, class_4534.ZERO);
            RenderSystem.disableBlend();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         }

      }
   }

   private void Kfd3d0a101f29168e5f(K9ee087b2e1791ad6b2 event, K210b017c4c303e2174 pose, int fillColor) {
      class_243 camera = mc.field_1773.method_19418().method_19326();
      float width = 4.0F;
      float height = 4.0F;
      float thickness = 1.5F;
      float halfW = width / 2.0F;
      float halfH = height / 2.0F;
      float halfT = thickness / 2.0F;
      event.matrixStack().method_22903();
      event.matrixStack().method_22904(pose.center.field_1352 - camera.field_1352, pose.center.field_1351 - camera.field_1351, pose.center.field_1350 - camera.field_1350);
      if (Math.abs(pose.pitchDeg) > 0.001F) {
         event.matrixStack().method_22907(class_7833.field_40714.rotationDegrees(pose.pitchDeg));
      }

      if (Math.abs(pose.yawDeg) > 0.001F) {
         event.matrixStack().method_22907(class_7833.field_40716.rotationDegrees(pose.yawDeg));
      }

      Matrix4f mat = event.matrixStack().method_23760().method_23761();
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);
      float minX = -halfW;
      float minY = -halfH;
      float minZ = -halfT;
      K65ac4500cbb07948f9(buffer, mat, minX, minY, minZ, halfW, halfH, halfT, fillColor);
      class_286.method_43433(buffer.method_60800());
      event.matrixStack().method_22909();
   }

   private static void K65ac4500cbb07948f9(class_287 buffer, Matrix4f matrix, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, int color) {
      int r = color >> 16 & 255;
      int g = color >> 8 & 255;
      int b = color & 255;
      int a = color >> 24 & 255;
      buffer.method_22918(matrix, minX, minY, maxZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, maxX, minY, maxZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, maxX, maxY, maxZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, minX, maxY, maxZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, maxX, minY, minZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, minX, minY, minZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, minX, maxY, minZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, maxX, maxY, minZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, minX, maxY, minZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, minX, maxY, maxZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, maxX, maxY, maxZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, maxX, maxY, minZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, minX, minY, maxZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, minX, minY, minZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, maxX, minY, minZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, maxX, minY, maxZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, minX, minY, minZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, minX, minY, maxZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, minX, maxY, maxZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, minX, maxY, minZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, maxX, minY, maxZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, maxX, minY, minZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, maxX, maxY, minZ).method_1336(r, g, b, a);
      buffer.method_22918(matrix, maxX, maxY, maxZ).method_1336(r, g, b, a);
   }

   private void K470503b8f6aab23bd4(K9ee087b2e1791ad6b2 event, class_243 center, float size, int outlineColor) {
      Matrix4f matrix = event.matrixStack().method_23760().method_23761();
      class_243 camera = mc.field_1773.method_19418().method_19326();
      RenderSystem.lineWidth(6.0F);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);
      float half = size / 2.0F;
      float minX = (float)(center.field_1352 - camera.field_1352 - (double)half);
      float minY = (float)(center.field_1351 - camera.field_1351 - (double)half);
      float minZ = (float)(center.field_1350 - camera.field_1350 - (double)half);
      float maxX = minX + size;
      float maxY = minY + size;
      float maxZ = minZ + size;
      K8b6c685faf0f3863fa(buffer, matrix, minX, minY, minZ, maxX, maxY, maxZ, outlineColor);
      class_286.method_43433(buffer.method_60800());
   }

   private static void K8b6c685faf0f3863fa(class_287 buffer, Matrix4f matrix, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, int color) {
      K3fcc5951a7b0f45f36(buffer, matrix, minX, minY, minZ, maxX, minY, minZ, color);
      K3fcc5951a7b0f45f36(buffer, matrix, maxX, minY, minZ, maxX, minY, maxZ, color);
      K3fcc5951a7b0f45f36(buffer, matrix, maxX, minY, maxZ, minX, minY, maxZ, color);
      K3fcc5951a7b0f45f36(buffer, matrix, minX, minY, maxZ, minX, minY, minZ, color);
      K3fcc5951a7b0f45f36(buffer, matrix, minX, maxY, minZ, maxX, maxY, minZ, color);
      K3fcc5951a7b0f45f36(buffer, matrix, maxX, maxY, minZ, maxX, maxY, maxZ, color);
      K3fcc5951a7b0f45f36(buffer, matrix, maxX, maxY, maxZ, minX, maxY, maxZ, color);
      K3fcc5951a7b0f45f36(buffer, matrix, minX, maxY, maxZ, minX, maxY, minZ, color);
      K3fcc5951a7b0f45f36(buffer, matrix, minX, minY, minZ, minX, maxY, minZ, color);
      K3fcc5951a7b0f45f36(buffer, matrix, maxX, minY, minZ, maxX, maxY, minZ, color);
      K3fcc5951a7b0f45f36(buffer, matrix, maxX, minY, maxZ, maxX, maxY, maxZ, color);
      K3fcc5951a7b0f45f36(buffer, matrix, minX, minY, maxZ, minX, maxY, maxZ, color);
   }

   private static void K3fcc5951a7b0f45f36(class_287 buffer, Matrix4f matrix, float x1, float y1, float z1, float x2, float y2, float z2, int color) {
      int r = color >> 16 & 255;
      int g = color >> 8 & 255;
      int b = color & 255;
      int a = color >> 24 & 255;
      buffer.method_22918(matrix, x1, y1, z1).method_1336(r, g, b, a);
      buffer.method_22918(matrix, x2, y2, z2).method_1336(r, g, b, a);
   }

   private K210b017c4c303e2174 Kd45d90415164e8ef66(float tickDelta) {
      class_243 playerPos = this.Ke1737fbf6383bdc801(tickDelta);
      class_243 start = playerPos.method_1031((double)0.0F, (double)mc.field_1724.method_18381(mc.field_1724.method_18376()), (double)0.0F);
      class_243 lookVec = mc.field_1724.method_5828(tickDelta);
      class_243 end = start.method_1019(lookVec.method_1021((double)4.0F));
      class_3965 hit = mc.field_1687.method_17742(new class_3959(start, end, class_3960.field_17558, class_242.field_1348, mc.field_1724));
      float pitch = mc.field_1724.method_5695(tickDelta);
      boolean isLookingDown = pitch > 45.0F;
      boolean isLookingUp = pitch < -45.0F;
      boolean isLookingHorizontal = !isLookingDown && !isLookingUp;
      float thickness = 1.5F;
      float halfThickness = thickness / 2.0F;
      class_243 center;
      float yawDeg;
      float pitchDeg;
      if (hit.method_17783() == class_240.field_1332 && hit.method_17784().method_1022(start) <= (double)4.0F) {
         class_243 hitPos = hit.method_17784();
         class_2350 face = hit.method_17780();
         if (isLookingDown) {
            center = new class_243(Math.floor(hitPos.field_1352) + (double)0.5F, Math.floor(hitPos.field_1351 + (double)1.0F) - 1.8 + (double)halfThickness, Math.floor(hitPos.field_1350) + (double)0.5F);
            yawDeg = 0.0F;
            pitchDeg = 90.0F;
         } else if (isLookingUp) {
            center = new class_243(Math.floor(hitPos.field_1352) + (double)0.5F, Math.floor(hitPos.field_1351) - (double)halfThickness + 1.6, Math.floor(hitPos.field_1350) + (double)0.5F);
            yawDeg = 0.0F;
            pitchDeg = -90.0F;
         } else {
            double offsetX = face.method_10148() != 0 ? (double)((float)face.method_10148() * halfThickness) : (double)0.0F;
            double offsetZ = face.method_10165() != 0 ? (double)((float)face.method_10165() * halfThickness) : (double)0.0F;
            center = new class_243(Math.floor(hitPos.field_1352) + (double)0.5F + offsetX, Math.floor(hitPos.field_1351) + (double)0.5F + 1.6, Math.floor(hitPos.field_1350) + (double)0.5F + offsetZ);
            float var10000;
            switch (face) {
               case field_11043 -> var10000 = 180.0F;
               case field_11035 -> var10000 = 0.0F;
               case field_11039 -> var10000 = 90.0F;
               case field_11034 -> var10000 = -90.0F;
               default -> var10000 = -mc.field_1724.method_5705(tickDelta);
            }

            yawDeg = var10000;
            pitchDeg = 0.0F;
         }
      } else {
         class_243 approx = start.method_1019(lookVec.method_1021((double)4.0F));
         double y = Math.floor(approx.field_1351) + (isLookingDown ? -1.8 + (double)halfThickness : (isLookingUp ? (double)(-halfThickness) + 1.6 : 2.1));
         center = new class_243(Math.floor(approx.field_1352) + (double)0.5F, y, Math.floor(approx.field_1350) + (double)0.5F);
         yawDeg = -mc.field_1724.method_5705(tickDelta);
         pitchDeg = 0.0F;
      }

      if (!isLookingHorizontal) {
         yawDeg = -mc.field_1724.method_5705(tickDelta);
      }

      return new K210b017c4c303e2174(center, yawDeg, pitchDeg);
   }

   private void Ke567931d5a160ac01d(K9ee087b2e1791ad6b2 event, K210b017c4c303e2174 pose, int outlineColor) {
      Matrix4f matrix = event.matrixStack().method_23760().method_23761();
      class_243 camera = mc.field_1773.method_19418().method_19326();
      RenderSystem.lineWidth(3.0F);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);
      float width = 4.0F;
      float height = 4.0F;
      float thickness = 1.5F;
      float halfW = width / 2.0F;
      float halfH = height / 2.0F;
      float halfT = thickness / 2.0F;
      event.matrixStack().method_22903();
      event.matrixStack().method_22904(pose.center.field_1352 - camera.field_1352, pose.center.field_1351 - camera.field_1351, pose.center.field_1350 - camera.field_1350);
      if (Math.abs(pose.pitchDeg) > 0.001F) {
         event.matrixStack().method_22907(class_7833.field_40714.rotationDegrees(pose.pitchDeg));
      }

      if (Math.abs(pose.yawDeg) > 0.001F) {
         event.matrixStack().method_22907(class_7833.field_40716.rotationDegrees(pose.yawDeg));
      }

      Matrix4f mat = event.matrixStack().method_23760().method_23761();
      float minX = -halfW;
      float minY = -halfH;
      float minZ = -halfT;
      K8b6c685faf0f3863fa(buffer, mat, minX, minY, minZ, halfW, halfH, halfT, outlineColor);
      class_286.method_43433(buffer.method_60800());
      event.matrixStack().method_22909();
   }

   @Generated
   public static Kd36858e45faa4e69f0 getInstance() {
      return instance;
   }
}
