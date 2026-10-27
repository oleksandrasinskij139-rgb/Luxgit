package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.world.K59e91c1b6e54ef922d;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.rotation.Kef2fce79615318605c;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.minecraft.class_10142;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

@ModuleRegister(
   name = "Hit Bubbles",
   category = Kf76f042931183af5ba.RENDER,
   description = "Создаёт красивые порталы при ударе по сущностям"
)
public class K9826f34fa75d74c813 extends Kab8a8c478f7d0077cd {
   private static final K9826f34fa75d74c813 instance = new K9826f34fa75d74c813();
   private final List<Kec434cecd4c3dbd3d6> particles = new ArrayList();
   private final class_2960 texture = class_2960.method_60655("luxvisuals", "textures/hitbubble.png");

   public void onEnable() {
      super.onEnable();
      this.particles.clear();
   }

   public void onDisable() {
      super.onDisable();
      this.particles.clear();
   }

   public void onEvent() {
      K8d7165024ff567f5e7 attackEvent = K59e91c1b6e54ef922d.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         class_1297 patt0$temp = event.entity();
         if (patt0$temp instanceof class_1309 livingEntity) {
            if (!livingEntity.method_5805()) {
               return;
            }

            long maxTime = 2000L;
            double distance = (double)(mc.field_1724.method_5739(livingEntity) - 1.2F + mc.field_1724.method_17681() / 2.0F);
            class_243 targetPos = Kef2fce79615318605c.getSpot(livingEntity);
            double yawRad = Math.toRadians((double)Kef2fce79615318605c.rotationAt(targetPos).getYaw());
            double xOffset = -Math.sin(yawRad) * distance;
            double zOffset = Math.cos(yawRad) * distance;
            class_243 particlePosition = new class_243(mc.field_1724.method_23317() + xOffset, mc.field_1724.method_23318() + (double)mc.field_1724.method_18381(mc.field_1724.method_18376()) * 0.9, mc.field_1724.method_23321() + zOffset);
            this.particles.add(new Kec434cecd4c3dbd3d6(this.particles.size(), particlePosition, maxTime));
         }

      }));
      K8d7165024ff567f5e7 render3DEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (!this.particles.isEmpty()) {
            class_4587 matrices = event.matrixStack();
            class_243 camera = event.cameraPos();
            this.particles.removeIf(Kec434cecd4c3dbd3d6::isToRemove);
            if (!this.particles.isEmpty()) {
               RenderSystem.enableBlend();
               RenderSystem.blendFunc(770, 1);
               RenderSystem.disableDepthTest();
               RenderSystem.depthMask(false);
               RenderSystem.disableCull();
               RenderSystem.setShader(class_10142.field_53880);
               int textureId = mc.method_1531().method_4619(this.texture).method_4624();
               RenderSystem.setShaderTexture(0, textureId);
               matrices.method_22903();
               class_287 batchBuffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);

               for(Kec434cecd4c3dbd3d6 particle : this.particles) {
                  if (particle.renderRule()) {
                     float maxScale = 1.0F;
                     float alphaPC = 1.0F;
                     int[] colors = this.K33c5cdc657267c0a9f(particle, alphaPC * particle.percentRunner.getPercent010());
                     float extendXY = maxScale * particle.percentRunner.getPercent();
                     class_243 pos = particle.pos;
                     float x = (float)(pos.field_1352 - camera.field_1352);
                     float y = (float)(pos.field_1351 - camera.field_1351);
                     float z = (float)(pos.field_1350 - camera.field_1350);
                     matrices.method_22903();
                     matrices.method_46416(x, y, z);
                     matrices.method_22907(particle.quaternion);
                     float rotatePC01 = particle.percentRunner.getPercent();
                     matrices.method_22907((new Quaternionf()).rotateZ((float)Math.toRadians((double)(rotatePC01 * -360.0F))));
                     Matrix4f matrix = matrices.method_23760().method_23761();

                     for(int i = 0; i < 2; ++i) {
                        batchBuffer.method_22918(matrix, -extendXY, -extendXY, 0.0F).method_22913(0.0F, 0.0F).method_39415(colors[0]);
                        batchBuffer.method_22918(matrix, -extendXY, extendXY, 0.0F).method_22913(0.0F, 1.0F).method_39415(colors[1]);
                        batchBuffer.method_22918(matrix, extendXY, extendXY, 0.0F).method_22913(1.0F, 1.0F).method_39415(colors[2]);
                        batchBuffer.method_22918(matrix, extendXY, -extendXY, 0.0F).method_22913(1.0F, 0.0F).method_39415(colors[3]);
                     }

                     matrices.method_22909();
                  }
               }

               class_286.method_43433(batchBuffer.method_60800());
               matrices.method_22909();
               RenderSystem.enableCull();
               RenderSystem.depthMask(true);
               RenderSystem.enableDepthTest();
               RenderSystem.disableBlend();
            }
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{attackEvent, render3DEvent});
   }

   private int[] K33c5cdc657267c0a9f(Kec434cecd4c3dbd3d6 particle, float alphaPC) {
      int[] colors = new int[4];
      int indexAppend = particle.index * 45;
      Color c0 = Kb18d549c13309af9a5.gradient(indexAppend);
      Color c1 = Kb18d549c13309af9a5.gradient(90 + indexAppend);
      Color c2 = Kb18d549c13309af9a5.gradient(180 + indexAppend);
      Color c3 = Kb18d549c13309af9a5.gradient(270 + indexAppend);
      int alpha = (int)(alphaPC * 255.0F);
      colors[0] = (new Color(c0.getRed(), c0.getGreen(), c0.getBlue(), alpha)).getRGB();
      colors[1] = (new Color(c1.getRed(), c1.getGreen(), c1.getBlue(), alpha)).getRGB();
      colors[2] = (new Color(c2.getRed(), c2.getGreen(), c2.getBlue(), alpha)).getRGB();
      colors[3] = (new Color(c3.getRed(), c3.getGreen(), c3.getBlue(), alpha)).getRGB();
      return colors;
   }

   @Generated
   public static K9826f34fa75d74c813 getInstance() {
      return instance;
   }
}
