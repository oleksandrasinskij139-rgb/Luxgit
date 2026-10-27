package code.miaware.client.features.modules.render.particles;

import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import code.miaware.api.utils.player.K131e64425767c5c203;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import lombok.Generated;
import net.minecraft.class_10142;
import net.minecraft.class_2189;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

public class Kbd989b7b534ee9cdce implements K4600bef438dafc1a9c {
   private float prevX;
   private float prevY;
   private float prevZ;
   private float x;
   private float y;
   private float z;
   private float motionX;
   private float motionY;
   private float motionZ;
   private int lifeTime;
   private int maxLife;
   private float prevSize = 0.0F;
   private float rotation;
   private float prevRotation = 0.0F;
   private float rotateSpeed = 20.0F;
   private float size;
   private int index;
   private class_2960 identifier;
   private boolean dropPhysics;
   private boolean rotating;
   private float spawnDuration;
   private float dyingDuration;
   private final Kf39dc27225f4d9fe6e timerUtil = new Kf39dc27225f4d9fe6e();
   private final Ka91715a8c4e3f3b7c4 alphaAnimation = new Ka91715a8c4e3f3b7c4();
   private boolean gravityFalls = false;
   private boolean trail;
   private float trailLength = 5.0F;
   private boolean dyingEffect;
   private final Deque<class_243> trailPoints = new ArrayDeque();
   public static String[] textures = new String[]{"Spark", "Star", "Heart", "Dollar", "Snowflake", "Glow", "Ball"};

   public Kbd989b7b534ee9cdce(float x, float y, float z, int lifeTime) {
      this.prevX = x;
      this.prevY = y;
      this.prevZ = z;
      this.x = x;
      this.y = y;
      this.z = z;
      this.maxLife = Ka0484a23398fb5f9ac.randomInRange(Math.max(lifeTime / 2, 0), lifeTime);
      this.rotation = Ka0484a23398fb5f9ac.randomInRange(-180.0F, 180.0F);
   }

   public static class_2960 getTexture(String mode) {
      class_2960 var10000;
      switch (mode) {
         case "Spark" -> var10000 = Kc1f6f8c785f6a08448.getImage("particles/spark_" + Ka0484a23398fb5f9ac.randomInRange(1, 4));
         default -> var10000 = Kc1f6f8c785f6a08448.getImage("particles/" + mode.toLowerCase());
      }

      return var10000;
   }

   public boolean update() {
      float gravity = this.gravityFalls ? (float)this.alphaAnimation.getValue() * 0.3F : 1.0F;
      this.prevX = this.x;
      this.prevY = this.y;
      this.prevZ = this.z;
      this.x += this.motionX;
      this.y += this.motionY * gravity;
      this.z += this.motionZ;
      double speed = Math.sqrt((double)(this.motionX * this.motionX + this.motionZ * this.motionZ));
      float halfSize = this.prevSize;
      if (this.Kff95a1aa332df8ad55(this.x, this.y - halfSize - 0.05F, this.z)) {
         this.motionY = -this.motionY / 1.1F;
         this.motionX /= 1.1F;
         this.motionZ /= 1.1F;
      } else if (!this.Kff95a1aa332df8ad55(this.x - (float)speed - halfSize, this.y, this.z - (float)speed - halfSize) && !this.Kff95a1aa332df8ad55(this.x + (float)speed + halfSize, this.y, this.z + (float)speed + halfSize) && !this.Kff95a1aa332df8ad55(this.x + (float)speed + halfSize, this.y, this.z - (float)speed - halfSize) && !this.Kff95a1aa332df8ad55(this.x - (float)speed - halfSize, this.y, this.z + (float)speed + halfSize) && !this.Kff95a1aa332df8ad55(this.x + (float)speed + halfSize, this.y, this.z) && !this.Kff95a1aa332df8ad55(this.x - (float)speed - halfSize, this.y, this.z) && !this.Kff95a1aa332df8ad55(this.x, this.y, this.z + (float)speed + halfSize) && !this.Kff95a1aa332df8ad55(this.x, this.y, this.z - (float)speed - halfSize)) {
         if (this.dropPhysics) {
            this.motionY -= 0.02F;
         }
      } else {
         this.motionX = -this.motionX;
         this.motionZ = -this.motionZ;
         --this.maxLife;
      }

      this.prevRotation = this.rotation;
      this.rotation -= this.prevRotation > 0.0F ? -this.rotateSpeed : this.rotateSpeed;
      if (!this.gravityFalls) {
         float scale = 1.1F;
         this.motionX /= scale;
         this.motionY /= scale;
         this.motionZ /= scale;
      }

      if (this.trail) {
         this.trailPoints.addFirst(new class_243((double)this.x, (double)this.y, (double)this.z));

         while((float)this.trailPoints.size() > this.trailLength) {
            this.trailPoints.removeLast();
         }
      }

      return mc.field_1724.method_19538().method_1022(new class_243((double)this.x, (double)this.y, (double)this.z)) >= (double)80.0F || this.alphaAnimation.getValue() <= (double)0.0F && this.timerUtil.finished((this.spawnDuration + this.dyingDuration + (float)this.maxLife) * 50.0F);
   }

   private float K82009e8b510e8aeb43() {
      return (float)this.alphaAnimation.getValue();
   }

   private int K1a4266f385be6ab2d7() {
      return (int)(255.0F * this.K82009e8b510e8aeb43());
   }

   public void updateAlpha() {
      this.alphaAnimation.update();
      float alphaAnim = this.K82009e8b510e8aeb43();
      if ((double)alphaAnim <= (double)0.0F && !this.timerUtil.finished(this.spawnDuration * 50.0F)) {
         this.alphaAnimation.run((double)1.0F, (long)(this.spawnDuration * 50.0F), Kf3b62ecfba0c3544e6.QUINT_OUT);
      }

      if ((double)alphaAnim >= (double)1.0F && this.timerUtil.finished((this.spawnDuration + (float)this.maxLife) * 50.0F)) {
         this.alphaAnimation.run((double)0.0F, (long)(this.dyingDuration * 50.0F), Kf3b62ecfba0c3544e6.QUINT_OUT);
      }

   }

   public void render(class_4587 matrixStack) {
      if (K131e64425767c5c203.canSee(new class_243((double)this.x, (double)this.y, (double)this.z))) {
         class_4184 camera = mc.field_1773.method_19418();
         Color primaryColor = K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(this.index * 90), this.K1a4266f385be6ab2d7());
         class_243 interpolatedPos = this.Kf306a0f4d5559fd0a3(this.prevX, this.prevY, this.prevZ, this.x, this.y, this.z);
         float halfSize = Ka0484a23398fb5f9ac.interpolate((double)this.prevSize, (double)(this.size * this.K82009e8b510e8aeb43()));
         this.prevSize = halfSize;
         matrixStack.method_22903();

         try {
            matrixStack.method_22904(interpolatedPos.field_1352, interpolatedPos.field_1351, interpolatedPos.field_1350);
            matrixStack.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
            matrixStack.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
            if (this.rotating) {
               matrixStack.method_22907(class_7833.field_40718.rotationDegrees(Ka0484a23398fb5f9ac.interpolate((double)this.prevRotation, (double)this.rotation)));
            }

            Matrix4f matrix4f = matrixStack.method_23760().method_23761();
            RenderSystem.setShaderTexture(0, this.identifier);
            RenderSystem.setShader(class_10142.field_53880);
            class_287 bufferBuilder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
            bufferBuilder.method_22918(matrix4f, halfSize, -halfSize, 0.0F).method_22913(0.0F, 1.0F).method_39415(primaryColor.getRGB());
            bufferBuilder.method_22918(matrix4f, -halfSize, -halfSize, 0.0F).method_22913(1.0F, 1.0F).method_39415(primaryColor.getRGB());
            bufferBuilder.method_22918(matrix4f, -halfSize, halfSize, 0.0F).method_22913(1.0F, 0.0F).method_39415(primaryColor.getRGB());
            bufferBuilder.method_22918(matrix4f, halfSize, halfSize, 0.0F).method_22913(0.0F, 0.0F).method_39415(primaryColor.getRGB());
            class_286.method_43433(bufferBuilder.method_60800());
         } finally {
            matrixStack.method_22909();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         }

      }
   }

   public void renderTrail(class_4587 matrixStack) {
      if (this.trail && this.trailPoints.size() > 1) {
         if (K131e64425767c5c203.canSee(new class_243((double)this.x, (double)this.y, (double)this.z))) {
            RenderSystem.setShader(class_10142.field_53876);
            RenderSystem.lineWidth(1.5F);

            try {
               class_287 buf = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);
               Matrix4f mat = matrixStack.method_23760().method_23761();
               class_243 cam = mc.method_1561().field_4686.method_19326();
               Color col = K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(this.index * 90), this.K1a4266f385be6ab2d7());
               double interpX = (double)Ka0484a23398fb5f9ac.interpolate((double)this.prevX, (double)this.x);
               double interpY = (double)Ka0484a23398fb5f9ac.interpolate((double)this.prevY, (double)this.y);
               double interpZ = (double)Ka0484a23398fb5f9ac.interpolate((double)this.prevZ, (double)this.z);
               class_243 last = null;

               for(class_243 p : this.trailPoints) {
                  double smoothX = Ka0484a23398fb5f9ac.interpolate(p.field_1352, interpX, 0.05);
                  double smoothY = Ka0484a23398fb5f9ac.interpolate(p.field_1351, interpY, 0.05);
                  double smoothZ = Ka0484a23398fb5f9ac.interpolate(p.field_1350, interpZ, 0.05);
                  class_243 smooth = new class_243(smoothX, smoothY, smoothZ);
                  if (last != null) {
                     buf.method_22918(mat, (float)(last.field_1352 - cam.field_1352), (float)(last.field_1351 - cam.field_1351), (float)(last.field_1350 - cam.field_1350)).method_1336(col.getRed(), col.getGreen(), col.getBlue(), col.getAlpha());
                     buf.method_22918(mat, (float)(smooth.field_1352 - cam.field_1352), (float)(smooth.field_1351 - cam.field_1351), (float)(smooth.field_1350 - cam.field_1350)).method_1336(col.getRed(), col.getGreen(), col.getBlue(), col.getAlpha());
                  }

                  last = smooth;
               }

               class_286.method_43433(buf.method_60800());
            } finally {
               RenderSystem.lineWidth(1.0F);
            }

         }
      }
   }

   private boolean Kff95a1aa332df8ad55(float x, float y, float z) {
      class_2248 block = mc.field_1687 != null ? mc.field_1687.method_8320(class_2338.method_49637((double)x, (double)y, (double)z)).method_26204() : null;
      return block != null && !(block instanceof class_2189) && block != class_2246.field_10382 && block != class_2246.field_10164 && block != class_2246.field_10376 && block != class_2246.field_10238 && block != class_2246.field_10479 && block != class_2246.field_10214 && block != class_2246.field_10112 && block != class_2246.field_10428 && block != class_2246.field_10597 && block != class_2246.field_10477 && block != class_2246.field_10449 && block != class_2246.field_10182 && block != class_2246.field_10251 && block != class_2246.field_10559;
   }

   private class_243 Kf306a0f4d5559fd0a3(float prevX, float prevY, float prevZ, float currentX, float currentY, float currentZ) {
      class_243 cameraPos = mc.method_1561().field_4686.method_19326();
      double cameraX = cameraPos.field_1352;
      double cameraY = cameraPos.field_1351;
      double cameraZ = cameraPos.field_1350;
      double interpolatedX = (double)Ka0484a23398fb5f9ac.interpolate((double)prevX, (double)currentX) - cameraX;
      double interpolatedY = (double)Ka0484a23398fb5f9ac.interpolate((double)prevY, (double)currentY) - cameraY;
      double interpolatedZ = (double)Ka0484a23398fb5f9ac.interpolate((double)prevZ, (double)currentZ) - cameraZ;
      return new class_243(interpolatedX, interpolatedY, interpolatedZ);
   }

   @Generated
   public Kbd989b7b534ee9cdce prevX(float prevX) {
      this.prevX = prevX;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce prevY(float prevY) {
      this.prevY = prevY;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce prevZ(float prevZ) {
      this.prevZ = prevZ;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce x(float x) {
      this.x = x;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce y(float y) {
      this.y = y;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce z(float z) {
      this.z = z;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce motionX(float motionX) {
      this.motionX = motionX;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce motionY(float motionY) {
      this.motionY = motionY;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce motionZ(float motionZ) {
      this.motionZ = motionZ;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce lifeTime(int lifeTime) {
      this.lifeTime = lifeTime;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce maxLife(int maxLife) {
      this.maxLife = maxLife;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce prevSize(float prevSize) {
      this.prevSize = prevSize;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce rotation(float rotation) {
      this.rotation = rotation;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce prevRotation(float prevRotation) {
      this.prevRotation = prevRotation;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce rotateSpeed(float rotateSpeed) {
      this.rotateSpeed = rotateSpeed;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce size(float size) {
      this.size = size;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce index(int index) {
      this.index = index;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce identifier(class_2960 identifier) {
      this.identifier = identifier;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce dropPhysics(boolean dropPhysics) {
      this.dropPhysics = dropPhysics;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce rotating(boolean rotating) {
      this.rotating = rotating;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce spawnDuration(float spawnDuration) {
      this.spawnDuration = spawnDuration;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce dyingDuration(float dyingDuration) {
      this.dyingDuration = dyingDuration;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce gravityFalls(boolean gravityFalls) {
      this.gravityFalls = gravityFalls;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce trail(boolean trail) {
      this.trail = trail;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce trailLength(float trailLength) {
      this.trailLength = trailLength;
      return this;
   }

   @Generated
   public Kbd989b7b534ee9cdce dyingEffect(boolean dyingEffect) {
      this.dyingEffect = dyingEffect;
      return this;
   }

   @Generated
   public float prevX() {
      return this.prevX;
   }

   @Generated
   public float prevY() {
      return this.prevY;
   }

   @Generated
   public float prevZ() {
      return this.prevZ;
   }

   @Generated
   public float x() {
      return this.x;
   }

   @Generated
   public float y() {
      return this.y;
   }

   @Generated
   public float z() {
      return this.z;
   }

   @Generated
   public float motionX() {
      return this.motionX;
   }

   @Generated
   public float motionY() {
      return this.motionY;
   }

   @Generated
   public float motionZ() {
      return this.motionZ;
   }

   @Generated
   public int lifeTime() {
      return this.lifeTime;
   }

   @Generated
   public int maxLife() {
      return this.maxLife;
   }

   @Generated
   public float prevSize() {
      return this.prevSize;
   }

   @Generated
   public float rotation() {
      return this.rotation;
   }

   @Generated
   public float prevRotation() {
      return this.prevRotation;
   }

   @Generated
   public float rotateSpeed() {
      return this.rotateSpeed;
   }

   @Generated
   public float size() {
      return this.size;
   }

   @Generated
   public int index() {
      return this.index;
   }

   @Generated
   public class_2960 identifier() {
      return this.identifier;
   }

   @Generated
   public boolean dropPhysics() {
      return this.dropPhysics;
   }

   @Generated
   public boolean rotating() {
      return this.rotating;
   }

   @Generated
   public float spawnDuration() {
      return this.spawnDuration;
   }

   @Generated
   public float dyingDuration() {
      return this.dyingDuration;
   }

   @Generated
   public Kf39dc27225f4d9fe6e timerUtil() {
      return this.timerUtil;
   }

   @Generated
   public Ka91715a8c4e3f3b7c4 alphaAnimation() {
      return this.alphaAnimation;
   }

   @Generated
   public boolean gravityFalls() {
      return this.gravityFalls;
   }

   @Generated
   public boolean trail() {
      return this.trail;
   }

   @Generated
   public float trailLength() {
      return this.trailLength;
   }

   @Generated
   public boolean dyingEffect() {
      return this.dyingEffect;
   }

   @Generated
   public Deque<class_243> trailPoints() {
      return this.trailPoints;
   }
}
