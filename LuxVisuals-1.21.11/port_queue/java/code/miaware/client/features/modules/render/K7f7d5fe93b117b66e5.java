package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.K14caf22f676a0204f7;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.fonts.K896065ea0a2bba4f47;
import code.miaware.client.ui.clickgui.K87984c5639b98954c5;
import code.miaware.client.util.render.effects.K98eb176eb5e9618d43;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.Objects;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.class_10142;
import net.minecraft.class_1657;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_3965;
import net.minecraft.class_4587;
import net.minecraft.class_239.class_240;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.joml.Vector2d;

@ModuleRegister(
   name = "BlockOverlay",
   category = Kf76f042931183af5ba.RENDER,
   description = "Подсветка блока"
)
public class K7f7d5fe93b117b66e5 extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K7f7d5fe93b117b66e5 instance = new K7f7d5fe93b117b66e5();
   private final Kbd66519397e9a89967 mode = (new Kbd66519397e9a89967("Режим")).value("Плазма").values("Плазма", "Заливка", "Контур", "Название", "Подсветка", "Оба");
   private final K00fef91b3d4d1b3ecc fill = (new K00fef91b3d4d1b3ecc("Заливка")).value(true).setVisible(() -> this.Kc3bf19ffc5c0950e98());
   private final Kb9e875ead00e0d03bd fillOpacity = (new Kb9e875ead00e0d03bd("Прозрачность заливки")).value(50.0F).range(0.0F, 100.0F).step(5.0F).setVisible(() -> this.Kc3bf19ffc5c0950e98() && (Boolean)this.fill.getValue());
   private final K98eb176eb5e9618d43 effect = new K98eb176eb5e9618d43();
   private final K00fef91b3d4d1b3ecc themeOutline = (new K00fef91b3d4d1b3ecc("Обводка темы")).value(true).setVisible(this::K9cc2754210bd1c0b01);
   private final Kb9e875ead00e0d03bd opacity = (new Kb9e875ead00e0d03bd("Прозрачность")).range(0.0F, 1.0F).step(0.01F).value(0.72F).setVisible(this::K9cc2754210bd1c0b01);
   private final Kb9e875ead00e0d03bd plasmaSpeed = (new Kb9e875ead00e0d03bd("Скорость плазмы")).range(0.1F, 4.0F).step(0.05F).value(1.15F).setVisible(() -> this.mode.is("Плазма"));
   private final Kb9e875ead00e0d03bd plasmaScale = (new Kb9e875ead00e0d03bd("Масштаб плазмы")).range(0.5F, 8.0F).step(0.1F).value(2.4F).setVisible(() -> this.mode.is("Плазма"));
   private final Kb9e875ead00e0d03bd outlineWidth = (new Kb9e875ead00e0d03bd("Ширина аутлайна")).range(0.0F, 8.0F).step(0.25F).value(2.75F).setVisible(this::K9cc2754210bd1c0b01);
   private final Kb9e875ead00e0d03bd outlineOpacity = (new Kb9e875ead00e0d03bd("Прозрачность аутлайна")).range(0.0F, 100.0F).step(1.0F).value(70.0F).setVisible(this::K9cc2754210bd1c0b01);
   private final Kb9e875ead00e0d03bd glow = (new Kb9e875ead00e0d03bd("Сила свечения")).range(0.0F, 3.0F).step(0.05F).value(1.05F).setVisible(this::K9cc2754210bd1c0b01);
   private final K00fef91b3d4d1b3ecc smooth = (new K00fef91b3d4d1b3ecc("Плавность")).value(true).setVisible(this::K9cc2754210bd1c0b01);
   private final Kb9e875ead00e0d03bd smoothSpeed = (new Kb9e875ead00e0d03bd("Скорость плавности")).range(2.0F, 30.0F).step(1.0F).value(10.0F).setVisible(() -> this.K9cc2754210bd1c0b01() && (Boolean)this.smooth.getValue());

   private boolean K9cc2754210bd1c0b01() {
      return this.mode.is("Плазма") || this.mode.is("Заливка") || this.mode.is("Контур");
   }

   private boolean Kc3bf19ffc5c0950e98() {
      return this.mode.is("Подсветка") || this.mode.is("Оба");
   }

   public void onEnable() {
      this.effect.reset();
   }

   public void onDisable() {
      this.effect.reset();
   }

   public boolean replacesVanillaOutline() {
      return this.isEnabled() && this.K9cc2754210bd1c0b01() && this.effect.canReplaceOutline();
   }

   public K7f7d5fe93b117b66e5() {
      ClientPlayConnectionEvents.DISCONNECT.register((ClientPlayConnectionEvents.Disconnect)(handler, client) -> {
         K98eb176eb5e9618d43 var10001 = this.effect;
         Objects.requireNonNull(var10001);
         client.execute(var10001::reset);
      });
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.mode, this.fill, this.fillOpacity, this.themeOutline, this.opacity, this.plasmaSpeed, this.plasmaScale, this.outlineWidth, this.outlineOpacity, this.glow, this.smooth, this.smoothSpeed});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 render2DEvent = K14caf22f676a0204f7.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1687 != null && mc.field_1724 != null && !mc.field_1690.field_1842 && (this.mode.is("Название") || this.mode.is("Оба"))) {
            class_2338 blockPos = this.K1209d701b0873b6a2a();
            if (blockPos != null) {
               class_2680 blockState = mc.field_1687.method_8320(blockPos);
               if (!blockState.method_26215() && blockState.method_26204() != class_2246.field_10124) {
                  class_243 blockCenter = new class_243((double)blockPos.method_10263() + (double)0.5F, (double)blockPos.method_10264() + 0.7, (double)blockPos.method_10260() + (double)0.5F);
                  Vector2d screenPos = this.K423c1a8b885f3a69f5(blockCenter.field_1352, blockCenter.field_1351, blockCenter.field_1350);
                  if (screenPos != null) {
                     String blockName = blockState.method_26204().method_9518().getString();
                     float scale = (float)mc.method_22683().method_4495();
                     float fontSize = 5.5F * scale;
                     float textWidth = K896065ea0a2bba4f47.SF_SEMIBOLD.getWidth(blockName, fontSize);
                     float panelWidth = textWidth + 16.0F * scale;
                     float panelHeight = 15.0F * scale;
                     float centerX = (float)(screenPos.x - (double)(panelWidth / 2.0F));
                     float centerY = (float)(screenPos.y - (double)(panelHeight / 2.0F));
                     class_4587 matrices = event.matrixStack();
                     float round = 3.5F * scale;
                     K2b5e2c5f2b9891b450.RECT.draw(matrices, centerX - 0.5F * scale, centerY - 0.5F * scale, panelWidth + 1.0F * scale, panelHeight + 1.0F * scale, round + 0.5F * scale, K87984c5639b98954c5.widgetOutline(120));
                     K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, centerX, centerY, panelWidth, panelHeight, round, K87984c5639b98954c5.widgetBody(200));
                     K896065ea0a2bba4f47.SF_SEMIBOLD.drawCenteredText(matrices, blockName, centerX + panelWidth / 2.0F, centerY + 4.0F * scale, fontSize, Kb18d549c13309af9a5.textColor());
                  }
               }
            }
         }
      }));
      K8d7165024ff567f5e7 render3DEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (this.K9cc2754210bd1c0b01()) {
            this.effect.render(event, (String)this.mode.getValue(), (Boolean)this.themeOutline.getValue(), this.opacity.getValue(), this.plasmaSpeed.getValue(), this.plasmaScale.getValue(), this.outlineWidth.getValue(), this.outlineOpacity.getValue(), this.glow.getValue(), (Boolean)this.smooth.getValue(), this.smoothSpeed.getValue());
         } else if (this.Kc3bf19ffc5c0950e98()) {
            class_239 patt0$temp = mc.field_1765;
            if (patt0$temp instanceof class_3965) {
               class_3965 result = (class_3965)patt0$temp;
               if (result.method_17783() == class_240.field_1332) {
                  class_2338 pos = result.method_17777();
                  if (mc.field_1687 == null) {
                     return;
                  }

                  class_265 shape = mc.field_1687.method_8320(pos).method_26218(mc.field_1687, pos);
                  if (shape.method_1110()) {
                     return;
                  }

                  class_238 box = shape.method_1107();
                  Color color = Kb18d549c13309af9a5.primary();
                  this.Kc62f997cb5c8516dc8(event.matrixStack(), pos, box, color);
               }
            }

         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{render2DEvent, render3DEvent});
   }

   private void Kc62f997cb5c8516dc8(class_4587 matrices, class_2338 pos, class_238 box, Color color) {
      class_243 camPos = mc.field_1773.method_19418().method_19326();
      matrices.method_22903();
      matrices.method_22904((double)pos.method_10263() - camPos.field_1352, (double)pos.method_10264() - camPos.field_1351, (double)pos.method_10260() - camPos.field_1350);
      Matrix4f matrix = matrices.method_23760().method_23761();
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableCull();
      RenderSystem.setShader(class_10142.field_53876);
      if ((Boolean)this.fill.getValue()) {
         RenderSystem.enableDepthTest();
         RenderSystem.depthFunc(515);
         RenderSystem.depthMask(false);
         RenderSystem.enablePolygonOffset();
         RenderSystem.polygonOffset(-1.0F, -10.0F);
         int alpha = (int)(this.fillOpacity.getValue() * 2.55F);
         Color fillColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
         this.Kdd1c8082620ceff4cf(matrix, box, fillColor);
         RenderSystem.polygonOffset(0.0F, 0.0F);
         RenderSystem.disablePolygonOffset();
         RenderSystem.depthMask(true);
         RenderSystem.disableDepthTest();
      }

      RenderSystem.enableCull();
      RenderSystem.disableBlend();
      matrices.method_22909();
   }

   private void Kdd1c8082620ceff4cf(Matrix4f matrix, class_238 box, Color color) {
      float r = (float)color.getRed() / 255.0F;
      float g = (float)color.getGreen() / 255.0F;
      float b = (float)color.getBlue() / 255.0F;
      float a = (float)color.getAlpha() / 255.0F;
      float minX = (float)box.field_1323;
      float minY = (float)box.field_1322;
      float minZ = (float)box.field_1321;
      float maxX = (float)box.field_1320;
      float maxY = (float)box.field_1325;
      float maxZ = (float)box.field_1324;
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);
      buffer.method_22918(matrix, minX, minY, minZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, maxX, minY, minZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, maxX, minY, maxZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, minX, minY, maxZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, minX, maxY, minZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, minX, maxY, maxZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, maxX, maxY, maxZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, maxX, maxY, minZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, minX, minY, minZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, minX, maxY, minZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, maxX, maxY, minZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, maxX, minY, minZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, minX, minY, maxZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, maxX, minY, maxZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, maxX, maxY, maxZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, minX, maxY, maxZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, minX, minY, minZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, minX, minY, maxZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, minX, maxY, maxZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, minX, maxY, minZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, maxX, minY, minZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, maxX, maxY, minZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, maxX, maxY, maxZ).method_22915(r, g, b, a);
      buffer.method_22918(matrix, maxX, minY, maxZ).method_22915(r, g, b, a);
      class_286.method_43433(buffer.method_60800());
   }

   private class_2338 K1209d701b0873b6a2a() {
      if (mc.field_1765 != null && mc.field_1765.method_17783() == class_240.field_1332) {
         class_3965 result = (class_3965)mc.field_1765;
         class_2338 pos = result.method_17777();
         class_1657 player = mc.field_1724;
         if (player != null) {
            double reachDistance = (double)6.0F;
            double distanceSq = player.method_5649((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260());
            if (distanceSq <= reachDistance * reachDistance) {
               return pos;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   private Vector2d K423c1a8b885f3a69f5(double x, double y, double z) {
      class_243 camera = mc.method_1561().field_4686.method_19326();
      double deltaX = x - camera.field_1352;
      double deltaY = y - camera.field_1351;
      double deltaZ = z - camera.field_1350;
      double yaw = Math.toRadians((double)mc.method_1561().field_4686.method_19330());
      double pitch = Math.toRadians((double)mc.method_1561().field_4686.method_19329());
      double cosYaw = Math.cos(yaw);
      double sinYaw = Math.sin(yaw);
      double cosPitch = Math.cos(pitch);
      double sinPitch = Math.sin(pitch);
      double rotX = deltaX * cosYaw + deltaZ * sinYaw;
      double rotY = deltaY * cosPitch - (deltaX * sinYaw - deltaZ * cosYaw) * sinPitch;
      double rotZ = deltaY * sinPitch + (deltaX * sinYaw - deltaZ * cosYaw) * cosPitch;
      if (rotZ >= (double)0.0F) {
         return null;
      } else {
         int width = mc.method_22683().method_4486();
         int height = mc.method_22683().method_4502();
         double fov = (double)(Integer)mc.field_1690.method_41808().method_41753();
         double scaleFactor = (double)height / ((double)2.0F * Math.tan(Math.toRadians(fov / (double)2.0F)));
         double screenX = (double)width / (double)2.0F - rotX / -rotZ * scaleFactor;
         double screenY = (double)height / (double)2.0F - rotY / -rotZ * scaleFactor;
         return new Vector2d(screenX, screenY);
      }
   }

   public static K7f7d5fe93b117b66e5 getInstance() {
      return instance;
   }
}
