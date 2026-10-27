package code.miaware.client.features.modules.render.targetesp.modes;

import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.client.features.modules.render.targetesp.K34929def563f4a28cf;
import code.miaware.client.features.modules.render.targetesp.K57a68ff1a2b51cf90d;
import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import net.minecraft.class_10142;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

public class K8d77eef01276514f09 extends K57a68ff1a2b51cf90d {
   private static final float TAU = ((float)Math.PI * 2F);
   private static final int TRAIL_MAX = 18;
   private static final float SPARK_FRICTION = 0.93F;
   private static final float SPARK_GRAVITY = 1.8E-4F;
   private float rotation;
   private float prevRotation;
   private float counterRotation;
   private float prevCounterRotation;
   private float impactProgress;
   private int prevHurtTime;
   private float pulseOffset;
   private float prevPulseOffset;
   private float glitchX;
   private float glitchZ;
   private float glitchDecay;
   private final Deque<Float>[] vertexTrails = new Deque[5];
   private final List<Kc025c522f9a02aeb35> sparks = new ArrayList();

   public K8d77eef01276514f09() {
      for(int i = 0; i < this.vertexTrails.length; ++i) {
         this.vertexTrails[i] = new ArrayDeque();
      }

   }

   public void onUpdate() {
      if (currentTarget != null && this.canDraw()) {
         K34929def563f4a28cf module = K34929def563f4a28cf.getInstance();
         this.prevRotation = this.rotation;
         this.prevCounterRotation = this.counterRotation;
         this.prevPulseOffset = this.pulseOffset;
         float speed = module.pentagramSpeed.getValue() * 0.55F;
         this.rotation += speed;
         this.counterRotation -= speed * 0.65F;
         this.Kb7f3ecc539766eb3cf();
         this.K7b1ba5c6fe1efc1a60();
         this.Kafb4e0115314fa9737();
         this.Kf4d1edaf983b934ded();
      }
   }

   private void Kb7f3ecc539766eb3cf() {
      for(int i = 0; i < 5; ++i) {
         this.vertexTrails[i].addFirst(this.rotation + (float)i * 72.0F);

         while(this.vertexTrails[i].size() > 18) {
            this.vertexTrails[i].removeLast();
         }
      }

   }

   private void K7b1ba5c6fe1efc1a60() {
      K34929def563f4a28cf module = K34929def563f4a28cf.getInstance();
      if (currentTarget == null) {
         this.impactProgress = 0.0F;
         this.pulseOffset = 0.0F;
         this.prevHurtTime = 0;
      } else {
         float fadeIn = 0.3F;
         float fadeOut = 0.08F;
         float maxIntensity = 1.0F;
         int hurtTime = currentTarget.field_6235;
         if (hurtTime <= this.prevHurtTime && (hurtTime <= 0 || this.prevHurtTime != 0)) {
            if (hurtTime > 0) {
               this.impactProgress = Math.min(maxIntensity, this.impactProgress + fadeIn * 0.45F);
               this.pulseOffset = Math.max(0.0F, this.pulseOffset - fadeOut * 0.4F);
            } else {
               this.impactProgress = Math.max(0.0F, this.impactProgress - fadeOut * 0.62F);
               this.pulseOffset = Math.max(0.0F, this.pulseOffset - fadeOut * 0.9F);
            }
         } else {
            this.impactProgress = Math.min(maxIntensity, this.impactProgress + fadeIn);
            this.pulseOffset = Math.min(0.22F, this.pulseOffset + fadeIn * 1.8F);
            this.K7f4997eb8f99dc3fff();
            this.Kd44a58b5f8a266eb24();
         }

         this.prevHurtTime = hurtTime;
      }
   }

   private void K7f4997eb8f99dc3fff() {
      float intensity = 0.12F;
      this.glitchX = (float)(Math.random() * (double)2.0F - (double)1.0F) * intensity;
      this.glitchZ = (float)(Math.random() * (double)2.0F - (double)1.0F) * intensity;
      this.glitchDecay = 1.0F;
   }

   private void Kafb4e0115314fa9737() {
      this.glitchDecay = Math.max(0.0F, this.glitchDecay - 0.18F);
      if (this.glitchDecay <= 0.0F) {
         this.glitchX = 0.0F;
         this.glitchZ = 0.0F;
      }

   }

   private void Kd44a58b5f8a266eb24() {
      if (currentTarget != null) {
         K34929def563f4a28cf module = K34929def563f4a28cf.getInstance();
         float radius = currentTarget.method_17681() * 0.7F + 0.08F + module.pentagramSize.getValue() * 0.16F;
         float[][] vertices = K78e8a2ef87af6f1e5a(radius, (float)Math.toRadians((double)this.rotation));

         for(int i = 0; i < 5; ++i) {
            float px = vertices[i * 2][0];
            float py = vertices[i * 2][1];
            int count = 5 + (int)(Math.random() * (double)4.0F);

            for(int j = 0; j < count; ++j) {
               float angle = (float)(Math.random() * (double)((float)Math.PI * 2F));
               float speed = 0.005F + (float)(Math.random() * (double)0.014F);
               this.sparks.add(new Kc025c522f9a02aeb35(px, py, Kc02781962388993cd6(angle) * speed, Kb77e075d5dcbb5f9e4(angle) * speed, 0.016F + (float)(Math.random() * (double)0.024F), i * 40 + (int)(Math.random() * (double)20.0F)));
            }
         }

      }
   }

   private void Kf4d1edaf983b934ded() {
      Iterator<Kc025c522f9a02aeb35> iterator = this.sparks.iterator();

      while(iterator.hasNext()) {
         Kc025c522f9a02aeb35 spark = (Kc025c522f9a02aeb35)iterator.next();
         spark.tick();
         if (spark.life <= 0.0F) {
            iterator.remove();
         }
      }

   }

   public void onRender3D(K9ee087b2e1791ad6b2 event) {
      if (currentTarget != null && this.canDraw()) {
         class_4587 matrices = event.matrixStack();
         class_4184 camera = mc.field_1773.method_19418();
         K34929def563f4a28cf module = K34929def563f4a28cf.getInstance();
         double x = getTargetX() - camera.method_19326().field_1352 + (double)(this.glitchX * this.glitchDecay);
         double y = getTargetY() - camera.method_19326().field_1351 + 0.03;
         double z = getTargetZ() - camera.method_19326().field_1350 + (double)(this.glitchZ * this.glitchDecay);
         float show = (float)showAnimation.getValue();
         float sizeAnim = Ka0484a23398fb5f9ac.interpolate((double)this.prevSizeAnimation, sizeAnimation.getValue());
         float pulse = Ka0484a23398fb5f9ac.interpolate((double)this.prevPulseOffset, (double)this.pulseOffset);
         float baseRadius = (currentTarget.method_17681() * 0.7F + 0.08F + module.pentagramSize.getValue() * 0.16F) * sizeAnim;
         float radius = baseRadius * (1.0F + pulse);
         float rotationValue = Ka0484a23398fb5f9ac.interpolate((double)this.prevRotation, (double)this.rotation);
         float counterRotationValue = Ka0484a23398fb5f9ac.interpolate((double)this.prevCounterRotation, (double)this.counterRotation);
         float outerLineWidth = 1.8F + pulse * 6.0F;
         float innerLineWidth = 1.1F + pulse * 3.0F;
         float pentagramLineWidth = 1.35F + pulse * 4.5F;
         matrices.method_22903();
         matrices.method_22904(x, y, z);
         matrices.method_22907(class_7833.field_40714.rotationDegrees(90.0F));
         RenderSystem.enableBlend();
         RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
         RenderSystem.disableCull();
         RenderSystem.disableDepthTest();
         RenderSystem.depthMask(false);

         try {
            this.Kf8f6f5f1c4878c4252(matrices, radius * (1.25F + module.pentagramBloomSize.getValue() * 0.3F), show);
            matrices.method_22903();
            matrices.method_22907(class_7833.field_40718.rotationDegrees(rotationValue));
            this.K2785250a4d2c41d0f8(matrices, radius, show);
            matrices.method_22909();
            matrices.method_22903();
            matrices.method_22907(class_7833.field_40718.rotationDegrees(rotationValue));
            this.K423f7307053da20f1e(matrices, radius * 1.08F, show, 72, outerLineWidth);
            this.K7a73e7f9d456cea63a(matrices, radius, show, pentagramLineWidth);
            matrices.method_22909();
            matrices.method_22903();
            matrices.method_22907(class_7833.field_40718.rotationDegrees(counterRotationValue));
            this.K70a23fca5c72394477(matrices, radius * 0.72F, show, innerLineWidth);
            matrices.method_22909();
            this.K005ca92fd520f68f35(matrices, radius, show);
            if (!this.sparks.isEmpty()) {
               this.Kcf273ae077fb962a68(matrices, show);
            }
         } finally {
            RenderSystem.lineWidth(1.0F);
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            matrices.method_22909();
         }

      }
   }

   private void Kf8f6f5f1c4878c4252(class_4587 matrices, float radius, float show) {
      if ((Boolean)K34929def563f4a28cf.getInstance().pentagramBloom.getValue()) {
         RenderSystem.setShaderTexture(0, Kc1f6f8c785f6a08448.getImage("particles/glow"));
         RenderSystem.setShader(class_10142.field_53880);
         Matrix4f matrix = matrices.method_23760().method_23761();
         Color color = this.K32640103a4fae8b3ce(new Color(228, 240, 255, (int)(72.0F * show)));
         class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
         buffer.method_22918(matrix, -radius, -radius, 0.0F).method_22913(0.0F, 0.0F).method_39415(color.getRGB());
         buffer.method_22918(matrix, -radius, radius, 0.0F).method_22913(0.0F, 1.0F).method_39415(color.getRGB());
         buffer.method_22918(matrix, radius, radius, 0.0F).method_22913(1.0F, 1.0F).method_39415(color.getRGB());
         buffer.method_22918(matrix, radius, -radius, 0.0F).method_22913(1.0F, 0.0F).method_39415(color.getRGB());
         class_286.method_43433(buffer.method_60800());
      }
   }

   private void K423f7307053da20f1e(class_4587 matrices, float radius, float show, int segments, float lineWidth) {
      RenderSystem.setShader(class_10142.field_53876);
      RenderSystem.lineWidth(lineWidth);
      Matrix4f matrix = matrices.method_23760().method_23761();
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);

      for(int i = 0; i < segments; ++i) {
         float angle1 = ((float)Math.PI * 2F) * (float)i / (float)segments;
         float angle2 = ((float)Math.PI * 2F) * (float)(i + 1) / (float)segments;
         Color color1 = this.K32640103a4fae8b3ce(K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(i * 4), (int)(130.0F * show)));
         Color color2 = this.K32640103a4fae8b3ce(K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient((i + 1) * 4), (int)(130.0F * show)));
         buffer.method_22918(matrix, Kc02781962388993cd6(angle1) * radius, Kb77e075d5dcbb5f9e4(angle1) * radius, 0.0F).method_39415(color1.getRGB());
         buffer.method_22918(matrix, Kc02781962388993cd6(angle2) * radius, Kb77e075d5dcbb5f9e4(angle2) * radius, 0.0F).method_39415(color2.getRGB());
      }

      class_286.method_43433(buffer.method_60800());
   }

   private void K70a23fca5c72394477(class_4587 matrices, float radius, float show, float lineWidth) {
      RenderSystem.setShader(class_10142.field_53876);
      RenderSystem.lineWidth(lineWidth);
      Matrix4f matrix = matrices.method_23760().method_23761();
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);
      int segments = 48;

      for(int i = 0; i < segments; ++i) {
         if (i % 3 != 2) {
            float angle1 = ((float)Math.PI * 2F) * (float)i / (float)segments;
            float angle2 = ((float)Math.PI * 2F) * (float)(i + 1) / (float)segments;
            Color color1 = this.K32640103a4fae8b3ce(K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(256 - i * 5), (int)(90.0F * show)));
            Color color2 = this.K32640103a4fae8b3ce(K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(256 - (i + 1) * 5), (int)(90.0F * show)));
            buffer.method_22918(matrix, Kc02781962388993cd6(angle1) * radius, Kb77e075d5dcbb5f9e4(angle1) * radius, 0.0F).method_39415(color1.getRGB());
            buffer.method_22918(matrix, Kc02781962388993cd6(angle2) * radius, Kb77e075d5dcbb5f9e4(angle2) * radius, 0.0F).method_39415(color2.getRGB());
         }
      }

      class_286.method_43433(buffer.method_60800());
   }

   private void K2785250a4d2c41d0f8(class_4587 matrices, float radius, float show) {
      RenderSystem.setShader(class_10142.field_53876);
      Matrix4f matrix = matrices.method_23760().method_23761();
      float[][] star = K78e8a2ef87af6f1e5a(radius, 0.0F);
      int alpha = Math.min(50, (int)(20.0F * show * (1.0F + this.impactProgress * 0.7F)));
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);

      for(int i = 0; i < 10; ++i) {
         int next = (i + 1) % 10;
         Color color = this.K32640103a4fae8b3ce(K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(i * 28), alpha));
         buffer.method_22918(matrix, 0.0F, 0.0F, 0.0F).method_39415(color.getRGB());
         buffer.method_22918(matrix, star[i][0], star[i][1], 0.0F).method_39415(color.getRGB());
         buffer.method_22918(matrix, star[next][0], star[next][1], 0.0F).method_39415(color.getRGB());
      }

      class_286.method_43433(buffer.method_60800());
   }

   private void K7a73e7f9d456cea63a(class_4587 matrices, float radius, float show, float lineWidth) {
      RenderSystem.setShader(class_10142.field_53876);
      RenderSystem.lineWidth(lineWidth);
      Matrix4f matrix = matrices.method_23760().method_23761();
      float[][] star = K78e8a2ef87af6f1e5a(radius, 0.0F);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);

      for(int i = 0; i < 10; ++i) {
         int next = (i + 1) % 10;
         Color color1 = this.K32640103a4fae8b3ce(K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(i * 28), (int)(155.0F * show)));
         Color color2 = this.K32640103a4fae8b3ce(K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(next * 28), (int)(155.0F * show)));
         buffer.method_22918(matrix, star[i][0], star[i][1], 0.0F).method_39415(color1.getRGB());
         buffer.method_22918(matrix, star[next][0], star[next][1], 0.0F).method_39415(color2.getRGB());
      }

      for(int i = 0; i < 5; ++i) {
         int a = i * 2;
         int b = (i * 2 + 4) % 10;
         Color color1 = this.K32640103a4fae8b3ce(K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(i * 54), (int)(190.0F * show)));
         Color color2 = this.K32640103a4fae8b3ce(K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient((i + 2) * 54), (int)(190.0F * show)));
         buffer.method_22918(matrix, star[a][0], star[a][1], 0.0F).method_39415(color1.getRGB());
         buffer.method_22918(matrix, star[b][0], star[b][1], 0.0F).method_39415(color2.getRGB());
      }

      class_286.method_43433(buffer.method_60800());
      float glowSize = radius * 0.09F * (1.0F + this.pulseOffset * 1.6F);

      for(int i = 0; i < 5; ++i) {
         this.K4200a57d552350aaa6(matrices, star[i * 2][0], star[i * 2][1], glowSize, show, i * 40);
      }

   }

   private void K005ca92fd520f68f35(class_4587 matrices, float radius, float show) {
      RenderSystem.setShader(class_10142.field_53876);
      RenderSystem.lineWidth(1.6F);
      Matrix4f matrix = matrices.method_23760().method_23761();

      for(int vertex = 0; vertex < 5; ++vertex) {
         if (this.vertexTrails[vertex].size() >= 2) {
            Float[] angles = (Float[])this.vertexTrails[vertex].toArray(new Float[0]);
            boolean hasAny = false;
            class_287 buffer = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);

            for(int i = 0; i < angles.length - 1; ++i) {
               float progress = 1.0F - (float)i / (float)angles.length;
               float alphaFactor = progress * progress * show * 0.85F;
               if (!(alphaFactor < 0.01F)) {
                  float angle1 = (float)Math.toRadians((double)(angles[i] - 90.0F));
                  float angle2 = (float)Math.toRadians((double)(angles[i + 1] - 90.0F));
                  int colorIndex = vertex * 40 + i * 8;
                  Color color1 = this.K32640103a4fae8b3ce(K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(colorIndex), (int)(200.0F * alphaFactor)));
                  Color color2 = this.K32640103a4fae8b3ce(K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(colorIndex + 8), (int)(200.0F * alphaFactor * 0.7F)));
                  buffer.method_22918(matrix, Kc02781962388993cd6(angle1) * radius, Kb77e075d5dcbb5f9e4(angle1) * radius, 0.0F).method_39415(color1.getRGB());
                  buffer.method_22918(matrix, Kc02781962388993cd6(angle2) * radius, Kb77e075d5dcbb5f9e4(angle2) * radius, 0.0F).method_39415(color2.getRGB());
                  hasAny = true;
               }
            }

            if (hasAny) {
               class_286.method_43433(buffer.method_60800());
            }
         }
      }

   }

   private void K4200a57d552350aaa6(class_4587 matrices, float x, float y, float size, float show, int colorIndex) {
      RenderSystem.setShaderTexture(0, Kc1f6f8c785f6a08448.getImage("particles/glow"));
      RenderSystem.setShader(class_10142.field_53880);
      Matrix4f matrix = matrices.method_23760().method_23761();
      Color color = this.K32640103a4fae8b3ce(K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(colorIndex), (int)(210.0F * show)));
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
      buffer.method_22918(matrix, x - size, y - size, 0.0F).method_22913(0.0F, 0.0F).method_39415(color.getRGB());
      buffer.method_22918(matrix, x - size, y + size, 0.0F).method_22913(0.0F, 1.0F).method_39415(color.getRGB());
      buffer.method_22918(matrix, x + size, y + size, 0.0F).method_22913(1.0F, 1.0F).method_39415(color.getRGB());
      buffer.method_22918(matrix, x + size, y - size, 0.0F).method_22913(1.0F, 0.0F).method_39415(color.getRGB());
      class_286.method_43433(buffer.method_60800());
   }

   private void Kcf273ae077fb962a68(class_4587 matrices, float show) {
      RenderSystem.setShaderTexture(0, Kc1f6f8c785f6a08448.getImage("particles/glow"));
      RenderSystem.setShader(class_10142.field_53880);
      Matrix4f matrix = matrices.method_23760().method_23761();

      for(Kc025c522f9a02aeb35 spark : this.sparks) {
         float alpha = spark.life * show;
         if (!(alpha <= 0.01F)) {
            Color color = this.K32640103a4fae8b3ce(K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(spark.colorIndex), (int)(220.0F * alpha)));
            float size = spark.size * spark.life;
            class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
            buffer.method_22918(matrix, spark.x - size, spark.y - size, 0.0F).method_22913(0.0F, 0.0F).method_39415(color.getRGB());
            buffer.method_22918(matrix, spark.x - size, spark.y + size, 0.0F).method_22913(0.0F, 1.0F).method_39415(color.getRGB());
            buffer.method_22918(matrix, spark.x + size, spark.y + size, 0.0F).method_22913(1.0F, 1.0F).method_39415(color.getRGB());
            buffer.method_22918(matrix, spark.x + size, spark.y - size, 0.0F).method_22913(1.0F, 0.0F).method_39415(color.getRGB());
            class_286.method_43433(buffer.method_60800());
         }
      }

   }

   private static float[][] K78e8a2ef87af6f1e5a(float radius, float angleOffset) {
      float innerRadius = radius * 0.46F;
      float[][] points = new float[10][2];

      for(int i = 0; i < 10; ++i) {
         double angle = (-Math.PI / 2D) + Math.PI * (double)i / (double)5.0F + (double)angleOffset;
         float currentRadius = i % 2 == 0 ? radius : innerRadius;
         points[i][0] = (float)Math.cos(angle) * currentRadius;
         points[i][1] = (float)Math.sin(angle) * currentRadius;
      }

      return points;
   }

   private static float Kc02781962388993cd6(float radians) {
      return (float)Math.cos((double)radians);
   }

   private static float Kb77e075d5dcbb5f9e4(float radians) {
      return (float)Math.sin((double)radians);
   }

   private Color K32640103a4fae8b3ce(Color color) {
      return this.impactProgress <= 0.0F ? color : K80ee108ea129adac11.interpolate(new Color(255, 80, 92, color.getAlpha()), color, Math.min(1.0F, this.impactProgress));
   }

   public void updateTarget() {
      super.updateTarget();
      if (currentTarget == null) {
         this.impactProgress = 0.0F;
         this.pulseOffset = 0.0F;
         this.prevHurtTime = 0;
         this.glitchX = 0.0F;
         this.glitchZ = 0.0F;
         this.glitchDecay = 0.0F;
         this.sparks.clear();

         for(Deque<Float> trail : this.vertexTrails) {
            trail.clear();
         }
      }

   }
}
