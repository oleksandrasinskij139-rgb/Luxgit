package code.miaware.client.features.modules.render.targetesp.modes;

import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.client.features.modules.render.targetesp.K34929def563f4a28cf;
import code.miaware.client.features.modules.render.targetesp.K57a68ff1a2b51cf90d;
import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.class_10142;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public class K68336bb745c58b1b21 extends K57a68ff1a2b51cf90d {
   private final List<Kea7a6a0a05bd6af579> particles = new ArrayList();
   private final Random random = new Random();
   private float angle = 0.0F;
   private float prevAngle = 0.0F;
   private float impactProgress = 0.0F;
   private int prevHurtTime = 0;

   public void onUpdate() {
      this.Kdce3abcf40465a17b7();
      this.prevAngle = this.angle;
      this.angle += K34929def563f4a28cf.getInstance().ringSpeed.getValue() * 6.0F;
      this.Kf89a1275daaa4daa19();
      if (currentTarget != null && (Boolean)K34929def563f4a28cf.getInstance().ringParticles.getValue() && this.particles.size() < 3200) {
         int spawnCount = (int)((float)(175 + this.random.nextInt(66)) * Math.max(0.15F, (float)showAnimation.getValue()));

         for(int i = 0; i < spawnCount && this.particles.size() < 3200; ++i) {
            this.K27597652ad2df55236();
         }
      }

   }

   private void Kdce3abcf40465a17b7() {
      K34929def563f4a28cf module = K34929def563f4a28cf.getInstance();
      if ((Boolean)module.ringRedOnImpact.getValue() && currentTarget != null) {
         float fadeIn = 0.3F;
         float fadeOut = 0.08F;
         float maxIntensity = 1.0F;
         int currentHurtTime = currentTarget.field_6235;
         if (currentHurtTime <= this.prevHurtTime && (currentHurtTime <= 0 || this.prevHurtTime != 0)) {
            if (currentHurtTime > 0) {
               this.impactProgress = Math.min(maxIntensity, this.impactProgress + fadeIn * 0.5F);
            } else {
               this.impactProgress = Math.max(0.0F, this.impactProgress - fadeOut);
            }
         } else {
            this.impactProgress = Math.min(maxIntensity, this.impactProgress + fadeIn);
         }

         this.prevHurtTime = currentHurtTime;
      } else {
         this.impactProgress = 0.0F;
         this.prevHurtTime = 0;
      }
   }

   private void Kf89a1275daaa4daa19() {
      Iterator<Kea7a6a0a05bd6af579> iterator = this.particles.iterator();

      while(iterator.hasNext()) {
         Kea7a6a0a05bd6af579 particle = (Kea7a6a0a05bd6af579)iterator.next();
         particle.prevPos = particle.pos;
         particle.pos = particle.pos.method_1019(particle.velocity);
         particle.velocity = new class_243(particle.velocity.field_1352 * 0.96, particle.velocity.field_1351 - 0.0022, particle.velocity.field_1350 * 0.96);
         ++particle.age;
         if (particle.age >= particle.maxAge || particle.pos.field_1351 < particle.groundY) {
            iterator.remove();
         }
      }

   }

   private void K27597652ad2df55236() {
      K34929def563f4a28cf module = K34929def563f4a28cf.getInstance();
      float animationScale = (float)sizeAnimation.getValue();
      float radius = (currentTarget.method_17681() * 0.86F + 0.18F) * module.ringSize.getValue() * animationScale;
      float verticalProgress = this.Ka85e8643a865664b31(this.angle);
      double centerX = currentTarget.method_23317();
      double minY = currentTarget.method_23318() + 0.03;
      double maxY = currentTarget.method_23318() + (double)currentTarget.method_17682() - 0.03;
      double centerY = minY + (maxY - minY) * (double)verticalProgress;
      double centerZ = currentTarget.method_23321();
      double particleAngle = this.random.nextDouble() * Math.PI * (double)2.0F;
      double spread = (double)radius * (0.88 + this.random.nextDouble() * 0.22);
      double heightLimit = (double)(currentTarget.method_17682() * module.ringParticleHeight.getValue());
      double y;
      if (module.ringParticleHeight.getValue() >= 0.98F) {
         y = minY + this.random.nextDouble() * (maxY - minY);
      } else {
         double halfRange = heightLimit * (double)0.5F;
         y = centerY + (this.random.nextDouble() - (double)0.5F) * heightLimit;
         double bottom = Math.max(minY, centerY - halfRange);
         double top = Math.min(maxY, centerY + halfRange);
         if (top > bottom) {
            y = Math.max(bottom, Math.min(top, y));
         }
      }

      y += (this.random.nextDouble() - (double)0.5F) * 0.025;
      y = Math.max(minY, Math.min(maxY, y));
      class_243 pos = new class_243(centerX + Math.cos(particleAngle) * spread, y, centerZ + Math.sin(particleAngle) * spread);
      class_243 outward = new class_243(Math.cos(particleAngle), (double)0.0F, Math.sin(particleAngle));
      class_243 velocity = outward.method_1021((this.random.nextDouble() - (double)0.5F) * 0.01).method_1031((double)0.0F, -8.0E-4 - this.random.nextDouble() * 0.006, (double)0.0F);
      int maxAge = Math.max(1, (int)((float)(34 + this.random.nextInt(28)) * module.ringParticleLife.getValue()));
      float size = (0.0065F + this.random.nextFloat() * 0.014F) * module.ringSize.getValue() * animationScale;
      this.particles.add(new Kea7a6a0a05bd6af579(pos, velocity, minY - 0.08, maxAge, size, this.random.nextInt(360)));
   }

   public void onRender3D(K9ee087b2e1791ad6b2 event) {
      if (currentTarget != null && this.canDraw()) {
         K34929def563f4a28cf module = K34929def563f4a28cf.getInstance();
         class_4587 ms = event.matrixStack();
         class_4184 camera = mc.field_1773.method_19418();
         class_243 cameraPos = camera.method_19326();
         double x = (double)Ka0484a23398fb5f9ac.interpolate((double)((float)currentTarget.field_6014), (double)((float)currentTarget.method_23317())) - cameraPos.field_1352;
         double z = (double)Ka0484a23398fb5f9ac.interpolate((double)((float)currentTarget.field_5969), (double)((float)currentTarget.method_23321())) - cameraPos.field_1350;
         float animationScale = (float)sizeAnimation.getValue();
         float appear = 0.72F + 0.28F * animationScale;
         float radius = (currentTarget.method_17681() * 0.86F + 0.18F) * module.ringSize.getValue() * appear;
         float ringAngle = Ka0484a23398fb5f9ac.interpolate((double)this.prevAngle, (double)this.angle);
         float alpha = (float)showAnimation.getValue();
         float yProgress = this.Ka85e8643a865664b31(ringAngle);
         double y = (double)Ka0484a23398fb5f9ac.interpolate((double)((float)currentTarget.field_6036), (double)((float)currentTarget.method_23318())) - cameraPos.field_1351 + (double)(currentTarget.method_17682() * yProgress);
         ms.method_22903();
         RenderSystem.enableBlend();
         RenderSystem.disableCull();
         RenderSystem.enableDepthTest();
         RenderSystem.depthMask(false);
         this.Ka95d2c23281a99340b(ms, x, y, z, radius, ringAngle, alpha * animationScale);
         if ((Boolean)module.ringParticles.getValue()) {
            this.K7ad7953da532981312(ms, camera, cameraPos, alpha * animationScale);
         }

         RenderSystem.depthMask(true);
         RenderSystem.setShaderTexture(0, 0);
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableBlend();
         RenderSystem.enableCull();
         RenderSystem.enableDepthTest();
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         ms.method_22909();
      }
   }

   private float Ka85e8643a865664b31(float value) {
      return (float)((Math.sin(Math.toRadians((double)value)) + (double)1.0F) * (double)0.5F);
   }

   private void Ka95d2c23281a99340b(class_4587 ms, double x, double y, double z, float radius, float moving, float alpha) {
      RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
      RenderSystem.setShader(class_10142.field_53876);
      RenderSystem.lineWidth(1.25F);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      Matrix4f matrix = ms.method_23760().method_23761();
      class_287 line = class_289.method_1348().method_60827(class_5596.field_29345, class_290.field_1576);
      int segments = 160;

      for(int i = 0; i <= segments; ++i) {
         float degrees = (float)i / (float)segments * 360.0F;
         double radians = Math.toRadians((double)degrees);
         int color = this.K6dab32946d971f53bf((int)(degrees + moving), alpha * 0.92F).getRGB();
         line.method_22918(matrix, (float)(x + Math.cos(radians) * (double)radius), (float)y, (float)(z + Math.sin(radians) * (double)radius)).method_39415(color);
      }

      class_286.method_43433(line.method_60800());
      RenderSystem.lineWidth(1.0F);
      GL11.glDisable(2848);
   }

   private void K7ad7953da532981312(class_4587 ms, class_4184 camera, class_243 cameraPos, float alpha) {
      RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
      RenderSystem.setShader(class_10142.field_53880);
      RenderSystem.setShaderTexture(0, Kc1f6f8c785f6a08448.getImage("particles/glow"));
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);

      for(Kea7a6a0a05bd6af579 particle : this.particles) {
         float life = 1.0F - (float)particle.age / (float)Math.max(1, particle.maxAge);
         if (!(life <= 0.0F)) {
            class_243 pos = new class_243((double)Ka0484a23398fb5f9ac.interpolate((double)((float)particle.prevPos.field_1352), (double)((float)particle.pos.field_1352)), (double)Ka0484a23398fb5f9ac.interpolate((double)((float)particle.prevPos.field_1351), (double)((float)particle.pos.field_1351)), (double)Ka0484a23398fb5f9ac.interpolate((double)((float)particle.prevPos.field_1350), (double)((float)particle.pos.field_1350)));
            float pulse = 0.72F + 0.28F * (float)Math.sin((double)((float)(particle.age + particle.seed) * 0.7F));
            float particleAlpha = alpha * life * pulse * 0.82F;
            float size = particle.size * (0.75F + life * 0.45F);
            double x = pos.field_1352 - cameraPos.field_1352;
            double y = pos.field_1351 - cameraPos.field_1351;
            double z = pos.field_1350 - cameraPos.field_1350;
            this.Kef1d8deef16429866f(buffer, ms, camera, x, y, z, size * 5.4F, this.K6dab32946d971f53bf(particle.seed, particleAlpha * 0.24F).getRGB());
            this.Kef1d8deef16429866f(buffer, ms, camera, x, y, z, size, this.K6dab32946d971f53bf(particle.seed, particleAlpha).getRGB());
         }
      }

      class_286.method_43433(buffer.method_60800());
   }

   private void Kef1d8deef16429866f(class_287 buffer, class_4587 ms, class_4184 camera, double x, double y, double z, float size, int color) {
      ms.method_22903();
      ms.method_22904(x, y, z);
      ms.method_22907(camera.method_23767());
      Matrix4f matrix = ms.method_23760().method_23761();
      buffer.method_22918(matrix, -size, -size, 0.0F).method_22913(0.0F, 0.0F).method_39415(color);
      buffer.method_22918(matrix, -size, size, 0.0F).method_22913(0.0F, 1.0F).method_39415(color);
      buffer.method_22918(matrix, size, size, 0.0F).method_22913(1.0F, 1.0F).method_39415(color);
      buffer.method_22918(matrix, size, -size, 0.0F).method_22913(1.0F, 0.0F).method_39415(color);
      ms.method_22909();
   }

   private Color K6dab32946d971f53bf(int hueOffset, float alpha) {
      int a = Math.max(0, Math.min(255, (int)(255.0F * alpha)));
      Color base = Kb18d549c13309af9a5.gradient(hueOffset, a);
      if (this.impactProgress > 0.0F) {
         Color impact = new Color(255, 32, 32, a);
         return this.K9fd17599d6324e1b22(base, impact, this.impactProgress);
      } else {
         return base;
      }
   }

   private Color K9fd17599d6324e1b22(Color c1, Color c2, float t) {
      t = Math.max(0.0F, Math.min(1.0F, t));
      float s = t * t * (3.0F - 2.0F * t);
      return new Color((int)((float)c1.getRed() + (float)(c2.getRed() - c1.getRed()) * s), (int)((float)c1.getGreen() + (float)(c2.getGreen() - c1.getGreen()) * s), (int)((float)c1.getBlue() + (float)(c2.getBlue() - c1.getBlue()) * s), (int)((float)c1.getAlpha() + (float)(c2.getAlpha() - c1.getAlpha()) * s));
   }

   public void updateTarget() {
      super.updateTarget();
      if (currentTarget == null) {
         this.particles.clear();
         this.impactProgress = 0.0F;
         this.prevHurtTime = 0;
         this.prevAngle = this.angle;
      }

   }
}
