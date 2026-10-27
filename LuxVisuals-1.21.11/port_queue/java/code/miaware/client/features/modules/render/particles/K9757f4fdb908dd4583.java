package code.miaware.client.features.modules.render.particles;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_10142;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

class K9757f4fdb908dd4583 implements K4600bef438dafc1a9c {
   private float prevX;
   private float prevY;
   private float prevZ;
   private float x;
   private float y;
   private float z;
   private float motionX;
   private float motionY;
   private float motionZ;
   private int maxLife;
   private float size;
   private float prevSize = 0.0F;
   private int index;
   private float rotX;
   private float rotY;
   private float rotZ;
   private float prevRotX;
   private float prevRotY;
   private float prevRotZ;
   private float rotSpeedX;
   private float rotSpeedY;
   private float rotSpeedZ;
   private float spawnDuration;
   private float dyingDuration;
   private final Kf39dc27225f4d9fe6e timerUtil = new Kf39dc27225f4d9fe6e();
   private final Ka91715a8c4e3f3b7c4 alphaAnimation = new Ka91715a8c4e3f3b7c4();

   public K9757f4fdb908dd4583(float x, float y, float z, float motionX, float motionY, float motionZ, int index, float size, int lifetime, float spawnDuration, float dyingDuration, float rotateSpeed) {
      this.prevX = x;
      this.prevY = y;
      this.prevZ = z;
      this.x = x;
      this.y = y;
      this.z = z;
      this.motionX = motionX;
      this.motionY = motionY;
      this.motionZ = motionZ;
      this.index = index;
      this.size = size;
      this.maxLife = Ka0484a23398fb5f9ac.randomInRange(Math.max(lifetime / 2, 0), lifetime);
      this.spawnDuration = spawnDuration;
      this.dyingDuration = dyingDuration;
      this.rotX = Ka0484a23398fb5f9ac.randomInRange(-180.0F, 180.0F);
      this.rotY = Ka0484a23398fb5f9ac.randomInRange(-180.0F, 180.0F);
      this.rotZ = Ka0484a23398fb5f9ac.randomInRange(-180.0F, 180.0F);
      this.prevRotX = this.rotX;
      this.prevRotY = this.rotY;
      this.prevRotZ = this.rotZ;
      this.rotSpeedX = Ka0484a23398fb5f9ac.randomInRange(-rotateSpeed, rotateSpeed);
      this.rotSpeedY = Ka0484a23398fb5f9ac.randomInRange(-rotateSpeed, rotateSpeed);
      this.rotSpeedZ = Ka0484a23398fb5f9ac.randomInRange(-rotateSpeed, rotateSpeed);
   }

   public boolean update() {
      this.prevX = this.x;
      this.prevY = this.y;
      this.prevZ = this.z;
      this.prevRotX = this.rotX;
      this.prevRotY = this.rotY;
      this.prevRotZ = this.rotZ;
      this.x += this.motionX;
      this.y += this.motionY;
      this.z += this.motionZ;
      this.rotX += this.rotSpeedX;
      this.rotY += this.rotSpeedY;
      this.rotZ += this.rotSpeedZ;
      return mc.field_1724.method_19538().method_1022(new class_243((double)this.x, (double)this.y, (double)this.z)) >= (double)80.0F || this.alphaAnimation.getValue() <= (double)0.0F && this.timerUtil.finished((this.spawnDuration + this.dyingDuration + (float)this.maxLife) * 50.0F);
   }

   public void updateAlpha() {
      this.alphaAnimation.update();
      float alphaAnim = (float)this.alphaAnimation.getValue();
      if ((double)alphaAnim <= (double)0.0F && !this.timerUtil.finished(this.spawnDuration * 50.0F)) {
         this.alphaAnimation.run((double)1.0F, (long)(this.spawnDuration * 50.0F), Kf3b62ecfba0c3544e6.QUINT_OUT);
      }

      if ((double)alphaAnim >= (double)1.0F && this.timerUtil.finished((this.spawnDuration + (float)this.maxLife) * 50.0F)) {
         this.alphaAnimation.run((double)0.0F, (long)(this.dyingDuration * 50.0F), Kf3b62ecfba0c3544e6.QUINT_OUT);
      }

   }

   public void render(class_4587 matrixStack, boolean glow, float glowSize) {
      class_243 cam = mc.method_1561().field_4686.method_19326();
      float alpha = (float)this.alphaAnimation.getValue();
      double interpX = (double)Ka0484a23398fb5f9ac.interpolate((double)this.prevX, (double)this.x) - cam.field_1352;
      double interpY = (double)Ka0484a23398fb5f9ac.interpolate((double)this.prevY, (double)this.y) - cam.field_1351;
      double interpZ = (double)Ka0484a23398fb5f9ac.interpolate((double)this.prevZ, (double)this.z) - cam.field_1350;
      float interpRotX = Ka0484a23398fb5f9ac.interpolate((double)this.prevRotX, (double)this.rotX);
      float interpRotY = Ka0484a23398fb5f9ac.interpolate((double)this.prevRotY, (double)this.rotY);
      float interpRotZ = Ka0484a23398fb5f9ac.interpolate((double)this.prevRotZ, (double)this.rotZ);
      float halfSize = Ka0484a23398fb5f9ac.interpolate((double)this.prevSize, (double)(this.size * alpha));
      this.prevSize = halfSize;
      Color color = K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(this.index * 90), (int)(255.0F * alpha));
      matrixStack.method_22903();
      matrixStack.method_22904(interpX, interpY, interpZ);
      matrixStack.method_22907(class_7833.field_40714.rotationDegrees(interpRotX));
      matrixStack.method_22907(class_7833.field_40716.rotationDegrees(interpRotY));
      matrixStack.method_22907(class_7833.field_40718.rotationDegrees(interpRotZ));
      if (glow) {
         this.K3faa628bea36c0f567(matrixStack, halfSize * glowSize, color, alpha);
      }

      this.K414bcf8f3736ad0343(matrixStack, halfSize, color);
      matrixStack.method_22909();
   }

   private void K3faa628bea36c0f567(class_4587 matrixStack, float size, Color color, float alpha) {
      class_4184 camera = mc.field_1773.method_19418();
      matrixStack.method_22903();

      try {
         matrixStack.method_22907(class_7833.field_40718.rotationDegrees(-Ka0484a23398fb5f9ac.interpolate((double)this.prevRotZ, (double)this.rotZ)));
         matrixStack.method_22907(class_7833.field_40716.rotationDegrees(-Ka0484a23398fb5f9ac.interpolate((double)this.prevRotY, (double)this.rotY)));
         matrixStack.method_22907(class_7833.field_40714.rotationDegrees(-Ka0484a23398fb5f9ac.interpolate((double)this.prevRotX, (double)this.rotX)));
         matrixStack.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
         matrixStack.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
         Matrix4f glowMatrix = matrixStack.method_23760().method_23761();
         RenderSystem.setShader(class_10142.field_53876);
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableCull();
         class_287 buf = class_289.method_1348().method_60827(class_5596.field_27381, class_290.field_1576);
         int r = color.getRed();
         int g = color.getGreen();
         int b = color.getBlue();
         int centerAlpha = (int)(80.0F * alpha);
         int edgeAlpha = 0;
         buf.method_22918(glowMatrix, 0.0F, 0.0F, 0.0F).method_1336(r, g, b, centerAlpha);
         int segments = 16;

         for(int i = 0; i <= segments; ++i) {
            float angle = (float)((double)(i * 2) * Math.PI / (double)segments);
            float px = (float)Math.cos((double)angle) * size;
            float py = (float)Math.sin((double)angle) * size;
            buf.method_22918(glowMatrix, px, py, 0.0F).method_1336(r, g, b, edgeAlpha);
         }

         class_286.method_43433(buf.method_60800());
      } finally {
         RenderSystem.enableCull();
         RenderSystem.defaultBlendFunc();
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         matrixStack.method_22909();
      }

   }

   private void K414bcf8f3736ad0343(class_4587 matrixStack, float size, Color color) {
      Matrix4f matrix = matrixStack.method_23760().method_23761();
      float s = size;
      RenderSystem.setShader(class_10142.field_53876);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableCull();
      RenderSystem.lineWidth(2.0F);

      try {
         class_287 buf = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);
         int r = color.getRed();
         int g = color.getGreen();
         int b = color.getBlue();
         int a = color.getAlpha();
         buf.method_22918(matrix, -s, -s, -s).method_1336(r, g, b, a);
         buf.method_22918(matrix, s, -s, -s).method_1336(r, g, b, a);
         buf.method_22918(matrix, s, -s, -s).method_1336(r, g, b, a);
         buf.method_22918(matrix, s, -s, s).method_1336(r, g, b, a);
         buf.method_22918(matrix, s, -s, s).method_1336(r, g, b, a);
         buf.method_22918(matrix, -s, -s, s).method_1336(r, g, b, a);
         buf.method_22918(matrix, -s, -s, s).method_1336(r, g, b, a);
         buf.method_22918(matrix, -s, -s, -s).method_1336(r, g, b, a);
         buf.method_22918(matrix, -s, s, -s).method_1336(r, g, b, a);
         buf.method_22918(matrix, s, s, -s).method_1336(r, g, b, a);
         buf.method_22918(matrix, s, s, -s).method_1336(r, g, b, a);
         buf.method_22918(matrix, s, s, s).method_1336(r, g, b, a);
         buf.method_22918(matrix, s, s, s).method_1336(r, g, b, a);
         buf.method_22918(matrix, -s, s, s).method_1336(r, g, b, a);
         buf.method_22918(matrix, -s, s, s).method_1336(r, g, b, a);
         buf.method_22918(matrix, -s, s, -s).method_1336(r, g, b, a);
         buf.method_22918(matrix, -s, -s, -s).method_1336(r, g, b, a);
         buf.method_22918(matrix, -s, s, -s).method_1336(r, g, b, a);
         buf.method_22918(matrix, s, -s, -s).method_1336(r, g, b, a);
         buf.method_22918(matrix, s, s, -s).method_1336(r, g, b, a);
         buf.method_22918(matrix, s, -s, s).method_1336(r, g, b, a);
         buf.method_22918(matrix, s, s, s).method_1336(r, g, b, a);
         buf.method_22918(matrix, -s, -s, s).method_1336(r, g, b, a);
         buf.method_22918(matrix, -s, s, s).method_1336(r, g, b, a);
         buf.method_22918(matrix, -s, -s, -s).method_1336(r, g, b, a / 2);
         buf.method_22918(matrix, s, s, s).method_1336(r, g, b, a / 2);
         buf.method_22918(matrix, s, -s, -s).method_1336(r, g, b, a / 2);
         buf.method_22918(matrix, -s, s, s).method_1336(r, g, b, a / 2);
         buf.method_22918(matrix, -s, -s, s).method_1336(r, g, b, a / 2);
         buf.method_22918(matrix, s, s, -s).method_1336(r, g, b, a / 2);
         buf.method_22918(matrix, s, -s, s).method_1336(r, g, b, a / 2);
         buf.method_22918(matrix, -s, s, -s).method_1336(r, g, b, a / 2);
         class_286.method_43433(buf.method_60800());
      } finally {
         RenderSystem.lineWidth(1.0F);
         RenderSystem.enableCull();
         RenderSystem.defaultBlendFunc();
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      }

   }
}
