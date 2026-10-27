package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4604;
import net.minecraft.class_5498;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

@ModuleRegister(
   name = "Trails",
   category = Kf76f042931183af5ba.RENDER,
   description = "Добавляет след за игроком при движении"
)
public class K4fe6907c39a36ee432 extends Kab8a8c478f7d0077cd {
   private static final K4fe6907c39a36ee432 instance = new K4fe6907c39a36ee432();
   private final Kb9e875ead00e0d03bd length = (new Kb9e875ead00e0d03bd("Length")).value(1500.0F).range(500.0F, 5000.0F).step(100.0F);
   private final Kb9e875ead00e0d03bd size = (new Kb9e875ead00e0d03bd("Size")).value(0.2F).range(0.05F, 0.3F).step(0.01F);
   private final K00fef91b3d4d1b3ecc renderInFirstPerson = (new K00fef91b3d4d1b3ecc("In first person")).value(false);
   private final K00fef91b3d4d1b3ecc physics = (new K00fef91b3d4d1b3ecc("Physics")).value(true);
   private final Kb9e875ead00e0d03bd fadeTime = (new Kb9e875ead00e0d03bd("Fade Time")).value(250.0F).range(100.0F, 1000.0F).step(50.0F);
   private final List<K8cf882ebf128502f73> particles = new ArrayList();
   private final class_2960 bloomTexture = Kc1f6f8c785f6a08448.getImage("particles/glow");

   public K4fe6907c39a36ee432() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.length, this.size, this.renderInFirstPerson, this.physics, this.fadeTime});
   }

   public void onEnable() {
      this.particles.clear();
   }

   public void onEvent() {
      K8d7165024ff567f5e7 updateEvent = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         this.particles.removeIf((particle) -> particle.shouldRemove(this.length.getValue().intValue()));
         boolean isFirstPerson = mc.field_1690.method_31044() == class_5498.field_26664;
         if (!isFirstPerson || (Boolean)this.renderInFirstPerson.getValue()) {
            class_243 playerPos = mc.field_1724.method_19538();
            this.particles.add(new K8cf882ebf128502f73(new class_243(playerPos.field_1352, playerPos.field_1351 + (double)mc.field_1724.method_17682() * (isFirstPerson ? 0.2 : (double)0.5F), playerPos.field_1350), this.particles.size()));
         }
      }));
      K8d7165024ff567f5e7 renderEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         class_4587 matrixStack = event.matrixStack();
         this.Keec6c43de24766bd6b(matrixStack, (Boolean)this.physics.getValue(), this.size.getValue(), this.fadeTime.getValue().intValue(), this.length.getValue().intValue(), this.particles);
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{updateEvent, renderEvent});
   }

   private void Keec6c43de24766bd6b(class_4587 matrixStack, boolean physics, float size, int fadeTime, int length, List<K8cf882ebf128502f73> particles) {
      if (mc.field_1724 != null && mc.field_1687 != null && !particles.isEmpty()) {
         class_4184 gameRendererCamera = mc.field_1773.method_19418();
         class_243 renderCamera = mc.method_1561().field_4686.method_19326();
         float cameraYaw = -gameRendererCamera.method_19330();
         float cameraPitch = gameRendererCamera.method_19329();
         class_4604 frustum = new class_4604(matrixStack.method_23760().method_23761(), RenderSystem.getProjectionMatrix());
         frustum.method_23088(renderCamera.field_1352, renderCamera.field_1351, renderCamera.field_1350);
         int index = 0;

         for(K8cf882ebf128502f73 particle : particles) {
            particle.update(physics);
            class_243 pos = particle.getPosition();
            if (!frustum.method_23093(new class_238(pos.field_1352 - (double)0.5F, pos.field_1351 - (double)0.5F, pos.field_1350 - (double)0.5F, pos.field_1352 + (double)0.5F, pos.field_1351 + (double)0.5F, pos.field_1350 + (double)0.5F))) {
               ++index;
            } else {
               if (index > 0) {
                  K8cf882ebf128502f73 prevParticle = (K8cf882ebf128502f73)particles.get(index - 1);
                  class_243 prevPos = prevParticle.getPosition();
                  class_243 currentPos = particle.getPosition();
                  float smoothFactor = 0.2F;
                  class_243 smoothedPos = new class_243(Ka0484a23398fb5f9ac.interpolate(prevPos.field_1352, currentPos.field_1352, (double)smoothFactor), Ka0484a23398fb5f9ac.interpolate(prevPos.field_1351, currentPos.field_1351, (double)smoothFactor), Ka0484a23398fb5f9ac.interpolate(prevPos.field_1350, currentPos.field_1350, (double)smoothFactor));
                  prevParticle.setPosition(smoothedPos);
               }

               K2b5e2c5f2b9891b450.WORLD.startRender(matrixStack);
               this.K75b98e8ad0ffb6dc73(matrixStack, particle, size, fadeTime, length, particles, renderCamera, cameraYaw, cameraPitch);
               K2b5e2c5f2b9891b450.WORLD.endRender(matrixStack);
               ++index;
            }
         }

      }
   }

   private void K75b98e8ad0ffb6dc73(class_4587 matrixStack, K8cf882ebf128502f73 particle, float size, int fadeTime, int length, List<K8cf882ebf128502f73> particles, class_243 renderCamera, float cameraYaw, float cameraPitch) {
      particle.handleAlphaTransitions(fadeTime, length);
      Color color = K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(particle.getIndex() * 30), (int)particle.getAlpha());
      class_243 pos = particle.getPosition();
      float bloomSize = size;
      if (particles.indexOf(particle) > 0) {
         K8cf882ebf128502f73 prev = (K8cf882ebf128502f73)particles.get(particles.indexOf(particle) - 1);
         double distance = pos.method_1022(prev.getPosition());
         bloomSize = (float)Math.max((double)size, Math.min((double)size * 3.3, distance * (double)4.0F));
      }

      RenderSystem.setShaderTexture(0, this.bloomTexture);
      matrixStack.method_22903();
      matrixStack.method_22904(pos.field_1352 - renderCamera.field_1352, pos.field_1351 - renderCamera.field_1351, pos.field_1350 - renderCamera.field_1350);
      matrixStack.method_22907(class_7833.field_40716.rotationDegrees(cameraYaw));
      matrixStack.method_22907(class_7833.field_40714.rotationDegrees(cameraPitch));
      Matrix4f matrix = matrixStack.method_23760().method_23761();
      class_287 bufferBuilder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
      int rgb = color.getRGB();
      bufferBuilder.method_22918(matrix, bloomSize, -bloomSize, 0.0F).method_22913(0.0F, 1.0F).method_39415(rgb);
      bufferBuilder.method_22918(matrix, -bloomSize, -bloomSize, 0.0F).method_22913(1.0F, 1.0F).method_39415(rgb);
      bufferBuilder.method_22918(matrix, -bloomSize, bloomSize, 0.0F).method_22913(1.0F, 0.0F).method_39415(rgb);
      bufferBuilder.method_22918(matrix, bloomSize, bloomSize, 0.0F).method_22913(0.0F, 0.0F).method_39415(rgb);
      class_286.method_43433(bufferBuilder.method_60800());
      matrixStack.method_22909();
   }

   @Generated
   public static K4fe6907c39a36ee432 getInstance() {
      return instance;
   }
}
