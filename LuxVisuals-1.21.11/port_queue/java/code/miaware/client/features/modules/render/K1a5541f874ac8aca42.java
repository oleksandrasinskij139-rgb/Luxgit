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
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.Generated;
import net.minecraft.class_10142;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

@ModuleRegister(
   name = "Nimb",
   category = Kf76f042931183af5ba.RENDER,
   description = "Нимб и сферы над головой"
)
public class K1a5541f874ac8aca42 extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K1a5541f874ac8aca42 instance = new K1a5541f874ac8aca42();
   private final Kbd66519397e9a89967 style = (new Kbd66519397e9a89967("Стиль")).value("Нимб").values("Классика", "Нимб", "Звезда", "Сферы");
   private final K00fef91b3d4d1b3ecc showOnSelf = (new K00fef91b3d4d1b3ecc("На себе")).value(true);
   private final K00fef91b3d4d1b3ecc showOnFriends = (new K00fef91b3d4d1b3ecc("На друзьях")).value(true);
   private final K00fef91b3d4d1b3ecc showOnOthers = (new K00fef91b3d4d1b3ecc("На других")).value(false);
   private final K00fef91b3d4d1b3ecc showFirstPerson = (new K00fef91b3d4d1b3ecc("От 1-го лица")).value(true);
   private final Kb9e875ead00e0d03bd distance = (new Kb9e875ead00e0d03bd("Дистанция")).value(72.0F).range(8.0F, 192.0F).step(1.0F);
   private final Kbd66519397e9a89967 quality = (new Kbd66519397e9a89967("Качество")).value("Среднее").values("Низкое", "Среднее", "Высокое", "Ультра").setVisible(() -> ((String)this.style.getValue()).equals("Нимб"));
   private final Ka52d224feda41b0761 color = (new Ka52d224feda41b0761("Цвет")).value(new Color(255, 255, 255, 255));
   private final K00fef91b3d4d1b3ecc animate = (new K00fef91b3d4d1b3ecc("Анимация")).value(true);
   private final Kb9e875ead00e0d03bd nimbRadius = (new Kb9e875ead00e0d03bd("Радиус нимба")).value(0.36F).range(0.18F, 0.9F).step(0.01F).setVisible(() -> ((String)this.style.getValue()).equals("Нимб"));
   private final Kb9e875ead00e0d03bd thickness = (new Kb9e875ead00e0d03bd("Толщина")).value(0.06F).range(0.02F, 0.16F).step(0.01F).setVisible(() -> ((String)this.style.getValue()).equals("Нимб"));
   private final Kb9e875ead00e0d03bd yOffset = (new Kb9e875ead00e0d03bd("Смещение Y")).value(0.1F).range(-0.6F, 0.6F).step(0.01F);
   private final Kbd66519397e9a89967 sphereMode = (new Kbd66519397e9a89967("Режим сфер")).value("Орбита").values("Орбита", "Статика", "Спираль", "Спираль V2", "Волны").setVisible(() -> ((String)this.style.getValue()).equals("Сферы"));
   private final Ka52d224feda41b0761 sphere1Color = (new Ka52d224feda41b0761("Сфера 1")).value(new Color(255, 100, 100, 255)).setVisible(() -> ((String)this.style.getValue()).equals("Сферы"));
   private final Ka52d224feda41b0761 sphere2Color = (new Ka52d224feda41b0761("Сфера 2")).value(new Color(100, 100, 255, 255)).setVisible(() -> ((String)this.style.getValue()).equals("Сферы"));
   private final Ka52d224feda41b0761 sphere3Color = (new Ka52d224feda41b0761("Сфера 3")).value(new Color(255, 255, 100, 255)).setVisible(() -> ((String)this.style.getValue()).equals("Сферы"));
   private final Kb9e875ead00e0d03bd sphereRadius = (new Kb9e875ead00e0d03bd("Радиус орбиты")).value(0.75F).range(0.3F, 2.0F).step(0.05F).setVisible(() -> ((String)this.style.getValue()).equals("Сферы"));
   private final Kb9e875ead00e0d03bd sphereSize = (new Kb9e875ead00e0d03bd("Размер сферы")).value(0.1F).range(0.04F, 0.3F).step(0.01F).setVisible(() -> ((String)this.style.getValue()).equals("Сферы"));
   private final Kb9e875ead00e0d03bd speed = (new Kb9e875ead00e0d03bd("Скорость")).value(1.0F).range(0.1F, 5.0F).step(0.1F).setVisible(() -> ((String)this.style.getValue()).equals("Сферы"));
   private final Kb9e875ead00e0d03bd breathe = (new Kb9e875ead00e0d03bd("Дыхание")).value(0.08F).range(0.0F, 0.2F).step(0.01F).setVisible(() -> ((String)this.style.getValue()).equals("Сферы"));
   private final Kb9e875ead00e0d03bd spiralHeight = (new Kb9e875ead00e0d03bd("Высота спирали")).value(1.2F).range(0.4F, 2.5F).step(0.1F).setVisible(() -> ((String)this.style.getValue()).equals("Сферы") && ((String)this.sphereMode.getValue()).contains("Спираль"));
   private final Kb9e875ead00e0d03bd waveFreq = (new Kb9e875ead00e0d03bd("Частота волн")).value(2.0F).range(0.5F, 8.0F).step(0.5F).setVisible(() -> ((String)this.style.getValue()).equals("Сферы") && ((String)this.sphereMode.getValue()).equals("Волны"));
   private final Kb9e875ead00e0d03bd waveAmp = (new Kb9e875ead00e0d03bd("Амплитуда волн")).value(0.35F).range(0.1F, 1.0F).step(0.05F).setVisible(() -> ((String)this.style.getValue()).equals("Сферы") && ((String)this.sphereMode.getValue()).equals("Волны"));
   private final Kbd66519397e9a89967 effect = (new Kbd66519397e9a89967("Эффект следа")).value("Нет").values("Нет", "След", "Огненный след").setVisible(() -> ((String)this.style.getValue()).equals("Сферы"));
   private final Kb9e875ead00e0d03bd trailLen = (new Kb9e875ead00e0d03bd("Длина следа")).value(16.0F).range(6.0F, 48.0F).step(1.0F).setVisible(() -> ((String)this.style.getValue()).equals("Сферы") && !((String)this.effect.getValue()).equals("Нет"));
   private final Kb9e875ead00e0d03bd trailPower = (new Kb9e875ead00e0d03bd("Яркость следа")).value(0.8F).range(0.2F, 1.5F).step(0.05F).setVisible(() -> ((String)this.style.getValue()).equals("Сферы") && !((String)this.effect.getValue()).equals("Нет"));
   private final Kbc4dd19ce24dfe366b torusCache = new Kbc4dd19ce24dfe366b();
   private final Quaternionf qTmp = new Quaternionf();
   private final Map<UUID, K16dfd93b08cf6dce39> sphereTrailMap = new HashMap();
   private final class_2960 defaultTexture = class_2960.method_60656("textures/particle/generic_0.png");

   public K1a5541f874ac8aca42() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.style, this.showOnSelf, this.showOnFriends, this.showOnOthers, this.showFirstPerson, this.distance, this.quality, this.color, this.animate, this.nimbRadius, this.thickness, this.yOffset, this.sphereMode, this.sphere1Color, this.sphere2Color, this.sphere3Color, this.sphereRadius, this.sphereSize, this.speed, this.breathe, this.spiralHeight, this.waveFreq, this.waveAmp, this.effect, this.trailLen, this.trailPower});
   }

   public void onDisable() {
      super.onDisable();
      this.torusCache.reset();
      this.sphereTrailMap.clear();
   }

   public void onEvent() {
      K8d7165024ff567f5e7 render3DEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            class_4587 ms = event.matrixStack();
            float pt = event.partialTicks();
            class_4184 cam = mc.field_1773.method_19418();
            class_243 camPos = cam.method_19326();
            boolean camFirst = mc.field_1690.method_31044().method_31034();
            double max = Math.max((double)8.0F, Math.min((double)192.0F, (double)this.distance.getValue()));
            double maxSq = max * max;

            for(class_1657 pl : mc.field_1687.method_18456()) {
               if (pl != null && !pl.method_5767()) {
                  boolean isSelf = pl == mc.field_1724;
                  boolean isFriend = K137d4e93c7270b0a04.getInstance().contains(pl.method_5477().getString());
                  if ((!isSelf || (Boolean)this.showOnSelf.getValue()) && (!isSelf || !camFirst || (Boolean)this.showFirstPerson.getValue()) && (isSelf || !isFriend || (Boolean)this.showOnFriends.getValue()) && (isSelf || isFriend || (Boolean)this.showOnOthers.getValue())) {
                     double px = class_3532.method_16436((double)pt, pl.field_6014, pl.method_23317());
                     double py = class_3532.method_16436((double)pt, pl.field_6036, pl.method_23318());
                     double pz = class_3532.method_16436((double)pt, pl.field_5969, pl.method_23321());
                     double dx = camPos.field_1352 - px;
                     double dy = camPos.field_1351 - py;
                     double dz = camPos.field_1350 - pz;
                     if (!(dx * dx + dy * dy + dz * dz > maxSq)) {
                        if (((String)this.style.getValue()).equals("Сферы")) {
                           this.K67586cad4820942a01(ms, cam, camPos, pl, pt, px, py, pz);
                        } else {
                           this.K15bd615279b961b0ed(ms, pt, pl, px, py, pz, cam);
                        }
                     }
                  }
               }
            }

         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{render3DEvent});
   }

   private void K15bd615279b961b0ed(class_4587 matrices, float partialTicks, class_1657 player, double px, double py, double pz, class_4184 camera) {
      float radius = 0.6F;
      matrices.method_22903();
      matrices.method_22904(px - camera.method_19326().field_1352, py - camera.method_19326().field_1351 + (double)player.method_17682() + 0.1, pz - camera.method_19326().field_1350);
      double yaw = (double)camera.method_19330();
      matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(-yaw)));
      RenderSystem.enableBlend();
      RenderSystem.enableDepthTest();
      RenderSystem.depthFunc(515);
      RenderSystem.depthMask(true);
      RenderSystem.disableCull();
      RenderSystem.blendFunc(770, 771);
      RenderSystem.setShader(class_10142.field_53876);
      RenderSystem.lineWidth(3.0F);
      switch ((String)this.style.getValue()) {
         case "Классика":
            this.Kfd108aa0832aa6c598(matrices, radius);
            break;
         case "Нимб":
            matrices.method_22909();
            this.Kc56b351fdaece10ee0(matrices, camera, player, px, py, pz);
            return;
         case "Звезда":
            this.Ke42f0afe9f0d3df618(matrices, radius);
      }

      RenderSystem.disableBlend();
      RenderSystem.disableDepthTest();
      RenderSystem.enableCull();
      matrices.method_22909();
   }

   private void Kfd108aa0832aa6c598(class_4587 matrices, float radius) {
      Matrix4f matrix = matrices.method_23760().method_23761();
      Color baseColor = (Color)this.color.getValue();
      RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
      RenderSystem.depthMask(false);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27381, class_290.field_1576);
      buffer.method_22918(matrix, 0.0F, 0.3F, 0.0F).method_1336(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 64);

      for(int i = 0; i <= 360; ++i) {
         float x = class_3532.method_15374((float)Math.toRadians((double)i)) * radius * 1.3F;
         float z = -class_3532.method_15362((float)Math.toRadians((double)i)) * radius * 1.3F;
         buffer.method_22918(matrix, x, 0.0F, z).method_1336(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 64);
      }

      class_286.method_43433(buffer.method_60800());
      buffer = class_289.method_1348().method_60827(class_5596.field_27381, class_290.field_1576);
      buffer.method_22918(matrix, 0.0F, 0.3F, 0.0F).method_1336(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 32);

      for(int i = 0; i <= 360; ++i) {
         float x = class_3532.method_15374((float)Math.toRadians((double)i)) * radius * 1.8F;
         float z = -class_3532.method_15362((float)Math.toRadians((double)i)) * radius * 1.8F;
         buffer.method_22918(matrix, x, 0.0F, z).method_1336(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 32);
      }

      class_286.method_43433(buffer.method_60800());
      RenderSystem.depthMask(true);
      RenderSystem.defaultBlendFunc();
      buffer = class_289.method_1348().method_60827(class_5596.field_27381, class_290.field_1576);
      buffer.method_22918(matrix, 0.0F, 0.3F, 0.0F).method_1336(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 128);

      for(int i = 0; i <= 360; ++i) {
         float x = class_3532.method_15374((float)Math.toRadians((double)i)) * radius;
         float z = -class_3532.method_15362((float)Math.toRadians((double)i)) * radius;
         buffer.method_22918(matrix, x, 0.0F, z).method_1336(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 128);
      }

      class_286.method_43433(buffer.method_60800());
      buffer = class_289.method_1348().method_60827(class_5596.field_29345, class_290.field_1576);

      for(int i = 0; i <= 360; ++i) {
         float x = class_3532.method_15374((float)Math.toRadians((double)i)) * radius;
         float z = -class_3532.method_15362((float)Math.toRadians((double)i)) * radius;
         buffer.method_22918(matrix, x, 0.0F, z).method_1336(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 255);
      }

      class_286.method_43433(buffer.method_60800());
   }

   private void Ke42f0afe9f0d3df618(class_4587 matrices, float radius) {
      Matrix4f matrix = matrices.method_23760().method_23761();
      int points = 5;
      double innerRadius = (double)radius * (double)0.5F;
      double outerRadius = (double)radius;
      float startAngle = 0.0F;
      float yPos = 0.0F;
      Color baseColor = (Color)this.color.getValue();
      RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
      RenderSystem.depthMask(false);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27381, class_290.field_1576);
      buffer.method_22918(matrix, 0.0F, yPos, 0.0F).method_1336(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 20);

      for(int i = 0; i < points * 2; ++i) {
         double ang = Math.toRadians((double)startAngle) + Math.PI / (double)points * (double)i;
         double r = i % 2 == 0 ? outerRadius * (double)2.0F : innerRadius * (double)2.0F;
         float x = (float)((double)class_3532.method_15374((float)ang) * r);
         float z = (float)((double)(-class_3532.method_15362((float)ang)) * r);
         buffer.method_22918(matrix, x, yPos, z).method_1336(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 0);
      }

      double ang0 = Math.toRadians((double)startAngle);
      buffer.method_22918(matrix, (float)((double)class_3532.method_15374((float)ang0) * outerRadius * (double)2.0F), yPos, (float)((double)(-class_3532.method_15362((float)ang0)) * outerRadius * (double)2.0F)).method_1336(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 0);
      class_286.method_43433(buffer.method_60800());
      buffer = class_289.method_1348().method_60827(class_5596.field_27381, class_290.field_1576);
      buffer.method_22918(matrix, 0.0F, yPos, 0.0F).method_1336(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 40);

      for(int i = 0; i < points * 2; ++i) {
         double ang = Math.toRadians((double)startAngle) + Math.PI / (double)points * (double)i;
         double r = i % 2 == 0 ? outerRadius * 1.4 : innerRadius * 1.4;
         float x = (float)((double)class_3532.method_15374((float)ang) * r);
         float z = (float)((double)(-class_3532.method_15362((float)ang)) * r);
         buffer.method_22918(matrix, x, yPos, z).method_1336(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 0);
      }

      buffer.method_22918(matrix, (float)((double)class_3532.method_15374((float)ang0) * outerRadius * 1.4), yPos, (float)((double)(-class_3532.method_15362((float)ang0)) * outerRadius * 1.4)).method_1336(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 0);
      class_286.method_43433(buffer.method_60800());
      RenderSystem.depthMask(true);
      RenderSystem.defaultBlendFunc();
      buffer = class_289.method_1348().method_60827(class_5596.field_27381, class_290.field_1576);
      buffer.method_22918(matrix, 0.0F, yPos, 0.0F).method_1336(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 120);

      for(int i = 0; i < points * 2; ++i) {
         double ang = Math.toRadians((double)startAngle) + Math.PI / (double)points * (double)i;
         double r = i % 2 == 0 ? outerRadius : innerRadius;
         float x = (float)((double)class_3532.method_15374((float)ang) * r);
         float z = (float)((double)(-class_3532.method_15362((float)ang)) * r);
         buffer.method_22918(matrix, x, yPos, z).method_1336(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 120);
      }

      buffer.method_22918(matrix, (float)((double)class_3532.method_15374((float)ang0) * outerRadius), yPos, (float)((double)(-class_3532.method_15362((float)ang0)) * outerRadius)).method_1336(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 120);
      class_286.method_43433(buffer.method_60800());
      buffer = class_289.method_1348().method_60827(class_5596.field_29345, class_290.field_1576);

      for(int i = 0; i < points * 2; ++i) {
         double angle = Math.toRadians((double)startAngle) + Math.PI / (double)points * (double)i;
         double r = i % 2 == 0 ? outerRadius : innerRadius;
         float xPos = (float)((double)class_3532.method_15374((float)angle) * r);
         float zPos = (float)((double)(-class_3532.method_15362((float)angle)) * r);
         buffer.method_22918(matrix, xPos, yPos, zPos).method_1336(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 255);
      }

      buffer.method_22918(matrix, (float)((double)class_3532.method_15374((float)ang0) * outerRadius), yPos, (float)((double)(-class_3532.method_15362((float)ang0)) * outerRadius)).method_1336(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 255);
      class_286.method_43433(buffer.method_60800());
   }

   private void Kc56b351fdaece10ee0(class_4587 ms, class_4184 cam, class_1657 player, double px, double py, double pz) {
      double hy = py + (double)player.method_17682() + 0.12 + (double)this.yOffset.getValue();
      float t = (float)(System.currentTimeMillis() % 1000000L) / 1000.0F;
      float bobAmp = class_3532.method_15363(this.thickness.getValue() * 0.8F, 0.0F, 0.12F);
      if ((Boolean)this.animate.getValue()) {
         hy += Math.sin((double)(t * 2.8F)) * (double)bobAmp;
      }

      float majorR = this.nimbRadius.getValue();
      float tubeR = Math.max(0.01F, this.thickness.getValue() * 0.5F);
      int col = ((Color)this.color.getValue()).getRGB();
      class_243 viewDir = cam.method_19326().method_1023(px, hy, pz);
      double vlen = viewDir.method_1033();
      if (vlen > 1.0E-6) {
         viewDir = viewDir.method_1021((double)1.0F / vlen);
      } else {
         viewDir = new class_243((double)0.0F, (double)0.0F, (double)1.0F);
      }

      class_243 lightDir = new class_243(0.35, (double)1.0F, (double)0.25F);
      double llen = lightDir.method_1033();
      if (llen > 1.0E-6) {
         lightDir = lightDir.method_1021((double)1.0F / llen);
      } else {
         lightDir = new class_243((double)0.0F, (double)1.0F, (double)0.0F);
      }

      int majorSeg;
      int minorSeg;
      int glowPasses;
      switch ((String)this.quality.getValue()) {
         case "Низкое":
            majorSeg = 64;
            minorSeg = 18;
            glowPasses = 1;
            break;
         case "Высокое":
            majorSeg = 128;
            minorSeg = 30;
            glowPasses = 3;
            break;
         case "Ультра":
            majorSeg = 160;
            minorSeg = 36;
            glowPasses = 3;
            break;
         default:
            majorSeg = 96;
            minorSeg = 24;
            glowPasses = 2;
      }

      this.torusCache.ensure(majorSeg, minorSeg);
      float bodyYawDeg = class_3532.method_17821(0.0F, player.field_6220, player.field_6283);
      double bodyYawRad = Math.toRadians((double)bodyYawDeg);
      ms.method_22903();
      ms.method_22904(px - cam.method_19326().field_1352, hy - cam.method_19326().field_1351, pz - cam.method_19326().field_1350);
      this.qTmp.identity().rotateY((float)bodyYawRad);
      ms.method_22907(this.qTmp);
      RenderSystem.enableDepthTest();
      RenderSystem.depthFunc(515);
      RenderSystem.disableCull();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.setShader(class_10142.field_53876);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.depthMask(false);
      this.K4a71457358b2885b9a(ms, majorR, tubeR, col, viewDir, lightDir);
      RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
      if (glowPasses >= 1) {
         int glowA = this.K9277f24c2ec59a5629(col, 0.34F);
         this.K4a71457358b2885b9a(ms, majorR, tubeR * 1.55F, glowA, viewDir, lightDir);
      }

      if (glowPasses >= 2) {
         int glowB = this.K9277f24c2ec59a5629(col, 0.16F);
         this.K4a71457358b2885b9a(ms, majorR, tubeR * 2.35F, glowB, viewDir, lightDir);
      }

      if (glowPasses >= 3) {
         int glowC = this.K9277f24c2ec59a5629(col, 0.08F);
         this.K4a71457358b2885b9a(ms, majorR, tubeR * 3.25F, glowC, viewDir, lightDir);
      }

      RenderSystem.depthMask(true);
      RenderSystem.enableCull();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableBlend();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      ms.method_22909();
   }

   private void K4a71457358b2885b9a(class_4587 ms, float majorR, float tubeR, int argb, class_243 viewDir, class_243 lightDir) {
      int a0 = argb >>> 24 & 255;
      int r0 = argb >>> 16 & 255;
      int g0 = argb >>> 8 & 255;
      int b0 = argb & 255;
      int majorSeg = this.torusCache.majorSeg;
      int minorSeg = this.torusCache.minorSeg;
      float[] majorCos = this.torusCache.majorCos;
      float[] majorSin = this.torusCache.majorSin;
      float[] minorCos = this.torusCache.minorCos;
      float[] minorSin = this.torusCache.minorSin;
      Matrix4f m = ms.method_23760().method_23761();
      class_287 bb = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);

      for(int i = 0; i < majorSeg; ++i) {
         int i1 = i + 1;
         if (i1 == majorSeg) {
            i1 = 0;
         }

         float ca0 = majorCos[i];
         float sa0 = majorSin[i];
         float ca1 = majorCos[i1];
         float sa1 = majorSin[i1];

         for(int j = 0; j < minorSeg; ++j) {
            int j1 = j + 1;
            if (j1 == minorSeg) {
               j1 = 0;
            }

            float cb0 = minorCos[j];
            float sb0 = minorSin[j];
            float cb1 = minorCos[j1];
            float sb1 = minorSin[j1];
            float nxA = cb0 * ca0;
            float nzA = cb0 * sa0;
            float nxB = cb0 * ca1;
            float nzB = cb0 * sa1;
            float nxC = cb1 * ca1;
            float nzC = cb1 * sa1;
            float nxD = cb1 * ca0;
            float nzD = cb1 * sa0;
            float ringA = majorR + tubeR * cb0;
            float ringB = majorR + tubeR * cb0;
            float ringC = majorR + tubeR * cb1;
            float ringD = majorR + tubeR * cb1;
            float xA = ringA * ca0;
            float yA = tubeR * sb0;
            float zA = ringA * sa0;
            float xB = ringB * ca1;
            float yB = tubeR * sb0;
            float zB = ringB * sa1;
            float xC = ringC * ca1;
            float yC = tubeR * sb1;
            float zC = ringC * sa1;
            float xD = ringD * ca0;
            float yD = tubeR * sb1;
            float zD = ringD * sa0;
            int cA = this.K382fce115317b2d7ea(nxA, sb0, nzA, viewDir, lightDir, r0, g0, b0, a0);
            int cB = this.K382fce115317b2d7ea(nxB, sb0, nzB, viewDir, lightDir, r0, g0, b0, a0);
            int cC = this.K382fce115317b2d7ea(nxC, sb1, nzC, viewDir, lightDir, r0, g0, b0, a0);
            int cD = this.K382fce115317b2d7ea(nxD, sb1, nzD, viewDir, lightDir, r0, g0, b0, a0);
            bb.method_22918(m, xA, yA, zA).method_39415(cA);
            bb.method_22918(m, xB, yB, zB).method_39415(cB);
            bb.method_22918(m, xC, yC, zC).method_39415(cC);
            bb.method_22918(m, xA, yA, zA).method_39415(cA);
            bb.method_22918(m, xC, yC, zC).method_39415(cC);
            bb.method_22918(m, xD, yD, zD).method_39415(cD);
         }
      }

      class_286.method_43433(bb.method_60800());
   }

   private int K382fce115317b2d7ea(float nx, float ny, float nz, class_243 viewDir, class_243 lightDir, int r0, int g0, int b0, int a0) {
      float dv = (float)((double)nx * viewDir.field_1352 + (double)ny * viewDir.field_1351 + (double)nz * viewDir.field_1350);
      dv = Math.max(0.0F, Math.min(1.0F, dv));
      float dl = (float)((double)nx * lightDir.field_1352 + (double)ny * lightDir.field_1351 + (double)nz * lightDir.field_1350);
      dl = (dl + 1.0F) * 0.5F;
      dl = Math.max(0.0F, Math.min(1.0F, dl));
      float rim = 1.0F - dv;
      rim *= rim;
      float dv2 = dv * dv;
      float dv4 = dv2 * dv2;
      float dv8 = dv4 * dv4;
      float dv10 = dv8 * dv2;
      float light = 0.22F + 0.68F * dl + rim * 0.3F + dv10 * 0.2F;
      light = Math.min(1.0F, light);
      return (a0 & 255) << 24 | this.K02c9f71561c443ad1d((int)((float)r0 * light)) << 16 | this.K02c9f71561c443ad1d((int)((float)g0 * light)) << 8 | this.K02c9f71561c443ad1d((int)((float)b0 * light));
   }

   private void K67586cad4820942a01(class_4587 ms, class_4184 cam, class_243 camPos, class_1657 player, float pt, double px, double py, double pz) {
      float bodyYawDeg = class_3532.method_17821(pt, player.field_6220, player.field_6283);
      double bodyYawRad = Math.toRadians((double)bodyYawDeg);
      class_243 forward = new class_243(-Math.sin(bodyYawRad), (double)0.0F, Math.cos(bodyYawRad));
      new class_243(forward.field_1350, (double)0.0F, -forward.field_1352);
      double baseY = py + (double)player.method_17682() * (double)0.5F + (double)this.yOffset.getValue();
      double headY = py + (double)player.method_17682() + (double)this.yOffset.getValue();
      float baseR = this.sphereRadius.getValue();
      float s = this.sphereSize.getValue();
      int[] baseCols = new int[]{((Color)this.sphere1Color.getValue()).getRGB(), ((Color)this.sphere2Color.getValue()).getRGB(), ((Color)this.sphere3Color.getValue()).getRGB()};
      float t = (float)(System.currentTimeMillis() % 1000000L) / 1000.0F;
      float bobAmp = class_3532.method_15363(this.sphereSize.getValue() * 0.85F, 0.0F, 0.18F);
      float[] phase = new float[]{0.0F, 2.0943952F, 4.1887903F};
      float[] staticOffsetsDeg = new float[]{180.0F, -90.0F, 360.0F};
      boolean doTrail = !((String)this.effect.getValue()).equals("Нет");
      boolean fireTrail = ((String)this.effect.getValue()).equals("Огненный след");
      int limit = Math.max(6, Math.min(48, this.trailLen.getValue().intValue()));
      float trailMul = this.trailPower.getValue();
      RenderSystem.enableBlend();
      RenderSystem.enableDepthTest();
      RenderSystem.depthFunc(515);
      RenderSystem.disableCull();

      for(int i = 0; i < 3; ++i) {
         float ph = phase[i];
         float breatheK = 1.0F + (float)(Math.sin((double)(t * 2.1F + ph)) * (double)this.breathe.getValue());
         float rr = baseR * breatheK;
         float alphaMul = 1.0F;
         double sx;
         double sy;
         double sz;
         switch ((String)this.sphereMode.getValue()) {
            case "Орбита":
               float ang = t * this.speed.getValue() + ph + (float)bodyYawRad;
               sx = px + Math.cos((double)ang) * (double)rr;
               sz = pz + Math.sin((double)ang) * (double)rr;
               sy = baseY + Math.sin((double)(t * 2.8F + ph)) * (double)bobAmp;
               break;
            case "Спираль":
               double yTop1 = headY + (double)this.spiralHeight.getValue();
               double yBottom1 = py + (double)this.yOffset.getValue() - (double)this.spiralHeight.getValue();
               double span1 = Math.max(0.001, yTop1 - yBottom1);
               float prog1 = this.Ka20048dfc74027aa8b(t * 0.18F * this.speed.getValue() + (float)i * 0.33333334F);
               float ang1 = t * 2.1F * this.speed.getValue() + ph + (float)bodyYawRad;
               sx = px + Math.cos((double)ang1) * (double)rr;
               sz = pz + Math.sin((double)ang1) * (double)rr;
               sy = yTop1 - (double)prog1 * span1;
               alphaMul = this.K09bb51cc74d5bc8e1c((float)((sy - yBottom1) / span1));
               break;
            case "Спираль V2":
               double yTop2 = headY + (double)this.spiralHeight.getValue();
               double yBottom2 = py + (double)this.yOffset.getValue() - (double)this.spiralHeight.getValue();
               double span2 = Math.max(0.001, yTop2 - yBottom2);
               float prog2 = this.Ka20048dfc74027aa8b(t * 0.22F * this.speed.getValue() + (float)i * 0.33333334F);
               float rr2 = rr * (0.8F + 0.2F * (float)Math.sin((double)(t * 1.7F + ph)));
               float ang2 = t * 2.65F * this.speed.getValue() + ph * 1.15F + (float)bodyYawRad;
               sx = px + Math.cos((double)ang2) * (double)rr2;
               sz = pz + Math.sin((double)ang2) * (double)rr2;
               double base2 = yTop2 - (double)prog2 * span2;
               sy = base2 + Math.sin((double)(t * 1.35F + ph)) * span2 * 0.04;
               alphaMul = this.K09bb51cc74d5bc8e1c((float)((sy - yBottom2) / span2));
               break;
            case "Волны":
               float ang3 = t * this.speed.getValue() + ph + (float)bodyYawRad;
               float w = (float)Math.sin((double)(ang3 * this.waveFreq.getValue() + t * 1.2F));
               sx = px + Math.cos((double)ang3) * (double)rr;
               sz = pz + Math.sin((double)ang3) * (double)rr;
               sy = headY + (double)(w * this.waveAmp.getValue()) + Math.sin((double)(t * 2.8F + ph)) * (double)bobAmp;
               break;
            default:
               double ang4 = Math.toRadians((double)(bodyYawDeg + staticOffsetsDeg[i]));
               sx = px + Math.cos(ang4) * (double)rr;
               sz = pz + Math.sin(ang4) * (double)rr;
               sy = baseY + Math.sin((double)(t * 2.8F + ph)) * (double)bobAmp;
         }

         class_243 viewDir = camPos.method_1023(sx, sy, sz);
         double len = viewDir.method_1033();
         if (len > 1.0E-6) {
            viewDir = viewDir.method_1021((double)1.0F / len);
         } else {
            viewDir = new class_243((double)0.0F, (double)0.0F, (double)1.0F);
         }

         int col = this.K9277f24c2ec59a5629(baseCols[i], alphaMul);
         if (doTrail) {
            this.K46703d9df93914de6a(player.method_5667(), i, new class_243(sx, sy, sz), limit);
            this.K9e6a2ea03db117ff83(ms, cam, player.method_5667(), i, col, trailMul, fireTrail);
         }

         if (alphaMul > 0.0025F) {
            this.K373521e079862fe14a(ms, cam, sx - camPos.field_1352, sy - camPos.field_1351, sz - camPos.field_1350, s, col, viewDir);
         }
      }

      RenderSystem.enableCull();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableBlend();
   }

   private void K373521e079862fe14a(class_4587 ms, class_4184 cam, double x, double y, double z, float r, int argb, class_243 viewDir) {
      ms.method_22903();
      ms.method_22904(x, y, z);
      RenderSystem.setShader(class_10142.field_53876);
      RenderSystem.enableDepthTest();
      RenderSystem.depthFunc(515);
      RenderSystem.depthMask(true);
      RenderSystem.defaultBlendFunc();
      this.K4f8f73eba5360d285e(ms, r, argb, viewDir);
      RenderSystem.setShader(class_10142.field_53880);
      RenderSystem.setShaderTexture(0, this.defaultTexture);
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
      RenderSystem.depthMask(false);
      float bloomSize = r * 6.0F;
      int a0 = argb >>> 24 & 255;
      int bloomA = Math.min(255, (int)((float)a0 * 0.62F));
      int bloom = bloomA << 24 | argb & 16777215;
      this.Kfd3d38f557a2508e1a(ms, cam, bloomSize, bloom);
      RenderSystem.depthMask(true);
      RenderSystem.defaultBlendFunc();
      RenderSystem.setShader(class_10142.field_53876);
      ms.method_22909();
   }

   private void Kfd3d38f557a2508e1a(class_4587 ms, class_4184 cam, float size, int argb) {
      ms.method_22903();
      ms.method_22907(cam.method_23767());
      Matrix4f m = ms.method_23760().method_23761();
      class_287 bb = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
      float h = size * 0.5F;
      bb.method_22918(m, -h, h, 0.0F).method_22913(0.0F, 1.0F).method_39415(argb);
      bb.method_22918(m, h, h, 0.0F).method_22913(1.0F, 1.0F).method_39415(argb);
      bb.method_22918(m, h, -h, 0.0F).method_22913(1.0F, 0.0F).method_39415(argb);
      bb.method_22918(m, -h, -h, 0.0F).method_22913(0.0F, 0.0F).method_39415(argb);
      class_286.method_43433(bb.method_60800());
      ms.method_22909();
   }

   private void K4f8f73eba5360d285e(class_4587 ms, float radius, int argb, class_243 viewDir) {
      int a0 = argb >>> 24 & 255;
      int r0 = argb >>> 16 & 255;
      int g0 = argb >>> 8 & 255;
      int b0 = argb & 255;
      int stacks = 18;
      int slices = 28;
      Matrix4f m = ms.method_23760().method_23761();
      class_287 bb = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);

      for(int i = 0; i < stacks; ++i) {
         double v0 = (double)i / (double)stacks;
         double v1 = (double)(i + 1) / (double)stacks;
         double phi0 = v0 * Math.PI - (Math.PI / 2D);
         double phi1 = v1 * Math.PI - (Math.PI / 2D);
         double sy0 = Math.sin(phi0);
         double sy1 = Math.sin(phi1);
         double sr0 = Math.cos(phi0);
         double sr1 = Math.cos(phi1);

         for(int j = 0; j < slices; ++j) {
            double u0 = (double)j / (double)slices;
            double u1 = (double)(j + 1) / (double)slices;
            double th0 = u0 * (Math.PI * 2D);
            double th1 = u1 * (Math.PI * 2D);
            float x00n = (float)(Math.cos(th0) * sr0);
            float z00n = (float)(Math.sin(th0) * sr0);
            float y00n = (float)sy0;
            float x10n = (float)(Math.cos(th1) * sr0);
            float z10n = (float)(Math.sin(th1) * sr0);
            float y10n = (float)sy0;
            float x01n = (float)(Math.cos(th0) * sr1);
            float z01n = (float)(Math.sin(th0) * sr1);
            float y01n = (float)sy1;
            float x11n = (float)(Math.cos(th1) * sr1);
            float z11n = (float)(Math.sin(th1) * sr1);
            float y11n = (float)sy1;
            int c00 = this.K5314bc2d43d478b820(x00n, y00n, z00n, viewDir, r0, g0, b0, a0);
            int c11 = this.K5314bc2d43d478b820(x11n, y11n, z11n, viewDir, r0, g0, b0, a0);
            int c10 = this.K5314bc2d43d478b820(x10n, y10n, z10n, viewDir, r0, g0, b0, a0);
            int c01 = this.K5314bc2d43d478b820(x01n, y01n, z01n, viewDir, r0, g0, b0, a0);
            bb.method_22918(m, x00n * radius, y00n * radius, z00n * radius).method_39415(c00);
            bb.method_22918(m, x11n * radius, y11n * radius, z11n * radius).method_39415(c11);
            bb.method_22918(m, x10n * radius, y10n * radius, z10n * radius).method_39415(c10);
            bb.method_22918(m, x00n * radius, y00n * radius, z00n * radius).method_39415(c00);
            bb.method_22918(m, x01n * radius, y01n * radius, z01n * radius).method_39415(c01);
            bb.method_22918(m, x11n * radius, y11n * radius, z11n * radius).method_39415(c11);
         }
      }

      class_286.method_43433(bb.method_60800());
   }

   private int K5314bc2d43d478b820(float nx, float ny, float nz, class_243 viewDir, int r0, int g0, int b0, int a0) {
      double dot = (double)nx * viewDir.field_1352 + (double)ny * viewDir.field_1351 + (double)nz * viewDir.field_1350;
      dot = class_3532.method_15350(dot, (double)0.0F, (double)1.0F);
      double rim = (double)1.0F - dot;
      double rim2 = rim * rim;
      double rim4 = rim2 * rim2;
      double light = 0.4 + 0.6 * dot + 0.22 * rim4;
      light = Math.min((double)1.0F, light);
      return (a0 & 255) << 24 | this.K02c9f71561c443ad1d((int)((double)r0 * light)) << 16 | this.K02c9f71561c443ad1d((int)((double)g0 * light)) << 8 | this.K02c9f71561c443ad1d((int)((double)b0 * light));
   }

   private void K46703d9df93914de6a(UUID uuid, int idx, class_243 pos, int limit) {
      K16dfd93b08cf6dce39 st = (K16dfd93b08cf6dce39)this.sphereTrailMap.computeIfAbsent(uuid, (k) -> new K16dfd93b08cf6dce39(6));
      ArrayDeque<Ka12296f43d5e0e0e80> q = st.queues[class_3532.method_15340(idx, 0, st.queues.length - 1)];
      q.addFirst(new Ka12296f43d5e0e0e80(pos, System.currentTimeMillis()));

      while(q.size() > limit) {
         q.removeLast();
      }

   }

   private void K9e6a2ea03db117ff83(class_4587 ms, class_4184 cam, UUID uuid, int idx, int argb, float mul, boolean fire) {
      K16dfd93b08cf6dce39 st = (K16dfd93b08cf6dce39)this.sphereTrailMap.get(uuid);
      if (st != null) {
         ArrayDeque<Ka12296f43d5e0e0e80> q = st.queues[class_3532.method_15340(idx, 0, st.queues.length - 1)];
         if (!q.isEmpty()) {
            float a0 = (float)(argb >>> 24 & 255) / 255.0F;
            float r0 = (float)(argb >>> 16 & 255) / 255.0F;
            float g0 = (float)(argb >>> 8 & 255) / 255.0F;
            float b0 = (float)(argb & 255) / 255.0F;
            RenderSystem.setShader(class_10142.field_53880);
            RenderSystem.setShaderTexture(0, this.defaultTexture);
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
            RenderSystem.depthMask(false);
            RenderSystem.disableCull();
            int n = q.size();
            int k = 0;

            for(Ka12296f43d5e0e0e80 tp : q) {
               float tt = n <= 1 ? 0.0F : (float)k / (float)(n - 1);
               float fade = 1.0F - tt;
               fade *= fade;
               float a = class_3532.method_15363(a0 * 0.6F * fade * mul, 0.0F, 1.0F);
               float ss = this.sphereSize.getValue() * 4.8F * (0.55F + 0.7F * fade);
               float rr = r0;
               float gg = g0;
               float bb = b0;
               if (fire) {
                  float heat = class_3532.method_15363(0.22F + 0.78F * fade, 0.0F, 1.0F);
                  rr = r0 * (1.0F - heat) + 1.0F * heat;
                  gg = g0 * (1.0F - heat) + 0.78F * heat;
                  bb = b0 * (1.0F - heat) + 0.32F * heat;
                  a = class_3532.method_15363(a * 1.18F, 0.0F, 1.0F);
               }

               ms.method_22903();
               ms.method_22904(tp.pos.field_1352 - cam.method_19326().field_1352, tp.pos.field_1351 - cam.method_19326().field_1351, tp.pos.field_1350 - cam.method_19326().field_1350);
               ms.method_22907(cam.method_23767());
               Matrix4f m = ms.method_23760().method_23761();
               class_287 bbld = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
               float h = ss * 0.5F;
               bbld.method_22918(m, -h, h, 0.0F).method_22913(0.0F, 1.0F).method_22915(rr, gg, bb, a);
               bbld.method_22918(m, h, h, 0.0F).method_22913(1.0F, 1.0F).method_22915(rr, gg, bb, a);
               bbld.method_22918(m, h, -h, 0.0F).method_22913(1.0F, 0.0F).method_22915(rr, gg, bb, a);
               bbld.method_22918(m, -h, -h, 0.0F).method_22913(0.0F, 0.0F).method_22915(rr, gg, bb, a);
               class_286.method_43433(bbld.method_60800());
               ms.method_22909();
               ++k;
            }

            RenderSystem.depthMask(true);
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableBlend();
            RenderSystem.enableCull();
         }
      }
   }

   private float K09bb51cc74d5bc8e1c(float norm) {
      float p0 = class_3532.method_15363(norm, 0.0F, 1.0F);
      float edge = 0.14F;
      float f1 = class_3532.method_15363(p0 / edge, 0.0F, 1.0F);
      float f2 = class_3532.method_15363((1.0F - p0) / edge, 0.0F, 1.0F);
      float a = Math.min(f1, f2);
      return a * a * (3.0F - 2.0F * a);
   }

   private float Ka20048dfc74027aa8b(float v) {
      return v - (float)Math.floor((double)v);
   }

   private int K02c9f71561c443ad1d(int v) {
      return Math.max(0, Math.min(255, v));
   }

   private int K9277f24c2ec59a5629(int argb, float mul) {
      int a0 = argb >>> 24 & 255;
      int a = class_3532.method_15340((int)((float)a0 * mul), 0, 255);
      return a << 24 | argb & 16777215;
   }

   private Color Kca172090aefa6f0339(int index, int alpha) {
      if ((Boolean)this.animate.getValue()) {
         return Kb18d549c13309af9a5.gradient(index, alpha);
      } else {
         Color base = (Color)this.color.getValue();
         return new Color(base.getRed(), base.getGreen(), base.getBlue(), alpha);
      }
   }

   @Generated
   public static K1a5541f874ac8aca42 getInstance() {
      return instance;
   }
}
