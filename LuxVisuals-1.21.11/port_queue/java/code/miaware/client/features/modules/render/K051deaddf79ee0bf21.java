package code.miaware.client.features.modules.render;

import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import java.awt.Color;
import lombok.Generated;
import net.minecraft.class_243;
import net.minecraft.class_287;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import org.joml.Matrix4f;

class K051deaddf79ee0bf21 {
   private final class_243 position;
   private final float yaw;
   private final int colorIndex;
   private final int lifeTime;
   private final int spawnDur;
   private final int dyingDur;
   private final String colorMode;
   private final boolean isFriend;
   private final Kf39dc27225f4d9fe6e timerUtil = new Kf39dc27225f4d9fe6e();
   private final Ka91715a8c4e3f3b7c4 animation = new Ka91715a8c4e3f3b7c4();
   private boolean isBack = false;

   public void renderBatched(class_4587 matrixStack, class_287 buffer, class_243 camera) {
      this.animation.update();
      this.animation.run(this.isBack ? (double)0.0F : (double)1.0F, this.isBack ? (long)this.dyingDur : (long)this.spawnDur, Kf3b62ecfba0c3544e6.SINE_OUT);
      float anim = (float)this.animation.getValue();
      float smoothAnim = anim * anim * (3.0F - 2.0F * anim);
      int alpha = (int)(smoothAnim * 255.0F);
      Color color;
      if (this.isFriend) {
         color = K80ee108ea129adac11.setAlpha(new Color(0, 255, 0), alpha);
      } else if (this.colorMode.equals("Радуга")) {
         float hue = (float)this.colorIndex / 360.0F;
         color = K80ee108ea129adac11.setAlpha(Color.getHSBColor(hue, 1.0F, 1.0F), alpha);
      } else {
         color = K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.primary(), alpha);
      }

      int rgb = color.getRGB();
      matrixStack.method_22903();
      matrixStack.method_22904(this.position.field_1352 - camera.field_1352, this.position.field_1351 - camera.field_1351, this.position.field_1350 - camera.field_1350);
      matrixStack.method_22907(class_7833.field_40714.rotationDegrees(90.0F));
      matrixStack.method_22907(class_7833.field_40718.rotationDegrees(-this.yaw + 270.0F));
      float size = 0.3F * smoothAnim;
      Matrix4f matrix = matrixStack.method_23760().method_23761();
      buffer.method_22918(matrix, -size, -size, 0.0F).method_22913(0.0F, 0.0F).method_39415(rgb);
      buffer.method_22918(matrix, -size, size, 0.0F).method_22913(0.0F, 1.0F).method_39415(rgb);
      buffer.method_22918(matrix, size, size, 0.0F).method_22913(1.0F, 1.0F).method_39415(rgb);
      buffer.method_22918(matrix, size, -size, 0.0F).method_22913(1.0F, 0.0F).method_39415(rgb);
      matrixStack.method_22909();
   }

   @Generated
   public K051deaddf79ee0bf21(class_243 position, float yaw, int colorIndex, int lifeTime, int spawnDur, int dyingDur, String colorMode, boolean isFriend) {
      this.position = position;
      this.yaw = yaw;
      this.colorIndex = colorIndex;
      this.lifeTime = lifeTime;
      this.spawnDur = spawnDur;
      this.dyingDur = dyingDur;
      this.colorMode = colorMode;
      this.isFriend = isFriend;
   }
}
