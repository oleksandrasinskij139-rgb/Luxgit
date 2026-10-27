package code.miaware.client.features.modules.movement;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.minecraft.class_10142;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

@ModuleRegister(
   name = "Fire Fly",
   category = Kf76f042931183af5ba.MOVEMENT,
   description = "Отображает красивые светлячки в мире"
)
public class K4dad1424dccce51a10 extends Kab8a8c478f7d0077cd {
   private static final K4dad1424dccce51a10 instance = new K4dad1424dccce51a10();
   private final Kb9e875ead00e0d03bd count = (new Kb9e875ead00e0d03bd("Количество")).value(100.0F).range(10.0F, 300.0F).step(10.0F);
   private final K00fef91b3d4d1b3ecc randomColor = (new K00fef91b3d4d1b3ecc("Случайный цвет")).value(false);
   private final List<Kc480ace3baebb823ee> particles = new ArrayList();
   private final class_2960 texture = class_2960.method_60655("luxvisuals", "textures/firefly.png");

   public K4dad1424dccce51a10() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.count, this.randomColor});
   }

   public void onEnable() {
      super.onEnable();
      this.particles.clear();
      if (mc.field_1724 != null) {
         this.Kf81510cfbcdf0fa940();
      }

   }

   public void onDisable() {
      super.onDisable();
      this.particles.clear();
   }

   public void onEvent() {
      K8d7165024ff567f5e7 render3DEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            this.particles.removeIf((particlex) -> particlex.time.finished(5000L) || particlex.position.method_1022(mc.field_1724.method_19538()) >= (double)60.0F);
            if (this.particles.size() <= this.count.getValue().intValue()) {
               this.Kf81510cfbcdf0fa940();
            }

            class_4587 matrices = event.matrixStack();
            class_243 camera = mc.field_1773.method_19418().method_19326();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.disableCull();
            RenderSystem.setShader(class_10142.field_53880);
            matrices.method_22903();
            int textureId = mc.method_1531().method_4619(this.texture).method_4624();
            RenderSystem.setShaderTexture(0, textureId);
            this.particles.forEach((fireFly) -> fireFly.update());
            float size = 0.2F;

            for(Kc480ace3baebb823ee particle : this.particles) {
               long duration = 150L;
               if ((int)particle.alpha.getValue() != 255 && !particle.time.finished(duration)) {
                  particle.alpha.run(255.0F, duration, Kf3b62ecfba0c3544e6.LINEAR);
               }

               if ((int)particle.alpha.getValue() != 0 && particle.time.finished(5000L - duration)) {
                  particle.alpha.run(0.0F, duration, Kf3b62ecfba0c3544e6.LINEAR);
               }

               particle.alpha.update();
               int alphaValue = (int)class_3532.method_15350(particle.alpha.getValue(), (double)0.0F, (double)particle.getAngle());
               Color colorGlow;
               if ((Boolean)this.randomColor.getValue()) {
                  colorGlow = new Color(particle.color, true);
               } else {
                  colorGlow = Kb18d549c13309af9a5.gradient(particle.index * 99);
               }

               colorGlow = new Color(colorGlow.getRed(), colorGlow.getGreen(), colorGlow.getBlue(), alphaValue);
               class_243 vec = particle.position;
               float x = (float)(vec.field_1352 - camera.field_1352);
               float y = (float)(vec.field_1351 - camera.field_1351);
               float z = (float)(vec.field_1350 - camera.field_1350);
               matrices.method_22903();
               matrices.method_46416(x, y, z);
               matrices.method_22907(mc.field_1773.method_19418().method_23767());
               Matrix4f matrix = matrices.method_23760().method_23761();
               class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
               buffer.method_22918(matrix, -size, -size, 0.0F).method_22913(0.0F, 0.0F).method_39415(colorGlow.getRGB());
               buffer.method_22918(matrix, -size, size, 0.0F).method_22913(0.0F, 1.0F).method_39415(colorGlow.getRGB());
               buffer.method_22918(matrix, size, size, 0.0F).method_22913(1.0F, 1.0F).method_39415(colorGlow.getRGB());
               buffer.method_22918(matrix, size, -size, 0.0F).method_22913(1.0F, 0.0F).method_39415(colorGlow.getRGB());
               class_286.method_43433(buffer.method_60800());
               float smallSize = size / 2.0F;
               Color whiteColor = new Color(255, 255, 255, alphaValue);
               buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
               buffer.method_22918(matrix, -smallSize, -smallSize, 0.0F).method_22913(0.0F, 0.0F).method_39415(whiteColor.getRGB());
               buffer.method_22918(matrix, -smallSize, smallSize, 0.0F).method_22913(0.0F, 1.0F).method_39415(whiteColor.getRGB());
               buffer.method_22918(matrix, smallSize, smallSize, 0.0F).method_22913(1.0F, 1.0F).method_39415(whiteColor.getRGB());
               buffer.method_22918(matrix, smallSize, -smallSize, 0.0F).method_22913(1.0F, 0.0F).method_39415(whiteColor.getRGB());
               class_286.method_43433(buffer.method_60800());
               matrices.method_22909();
            }

            matrices.method_22909();
            RenderSystem.enableCull();
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            RenderSystem.disableBlend();
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{render3DEvent});
   }

   private void Kf81510cfbcdf0fa940() {
      double distance = (double)Ka0484a23398fb5f9ac.randomInRange(5, 50);
      double yawRad = Math.toRadians((double)Ka0484a23398fb5f9ac.randomInRange(0, 360));
      double xOffset = -Math.sin(yawRad) * distance;
      double zOffset = Math.cos(yawRad) * distance;
      this.particles.add(new Kc480ace3baebb823ee(this, new class_243(mc.field_1724.method_23317() + xOffset, mc.field_1724.method_23318() + (double)Ka0484a23398fb5f9ac.randomInRange(10, 20), mc.field_1724.method_23321() + zOffset), this.particles.size(), (Boolean)this.randomColor.getValue() ? Color.HSBtoRGB((float)Math.random(), 1.0F, 1.0F) : -1));
   }

   @Generated
   public static K4dad1424dccce51a10 getInstance() {
      return instance;
   }
}
