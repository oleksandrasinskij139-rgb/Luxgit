package code.miaware.client.features.modules.render.targetesp.modes;

import code.miaware.api.event.events.render.K9ee087b2e1791ad6b2;
import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.client.features.modules.render.targetesp.K34929def563f4a28cf;
import code.miaware.client.features.modules.render.targetesp.K57a68ff1a2b51cf90d;
import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
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

public class K7008defff6ec6a6379 extends K57a68ff1a2b51cf90d {
   private final List<double[]> previousPositions = new ArrayList();
   private float ghostRotationAngle = 0.0F;
   private float ghostYRotationAngle = 0.0F;
   private float prevGhostRotationAngle = 0.0F;
   private float prevGhostYRotationAngle = 0.0F;
   private int updateCounter = 0;

   public void onUpdate() {
      this.updateTarget();
      this.prevGhostRotationAngle = this.ghostRotationAngle;
      this.prevGhostYRotationAngle = this.ghostYRotationAngle;
      float speed = K34929def563f4a28cf.getInstance().rotationSpeed.getValue();
      this.ghostRotationAngle += speed;
      this.ghostYRotationAngle += speed;
   }

   public void onRender3D(K9ee087b2e1791ad6b2 event) {
      if (currentTarget != null && this.canDraw()) {
         class_4587 matrixStack = event.matrixStack();
         K2b5e2c5f2b9891b450.WORLD.startRender(matrixStack);

         try {
            float size = K34929def563f4a28cf.getInstance().cometSize.getValue();
            float dony = 1.15F * Ka0484a23398fb5f9ac.interpolate((double)this.prevSizeAnimation, sizeAnimation.getValue());
            float rem = (float)(0.4 * (double)Ka0484a23398fb5f9ac.interpolate((double)this.prevSizeAnimation, sizeAnimation.getValue()));
            int trailLength = K34929def563f4a28cf.getInstance().cometTrailLength.getValue().intValue();
            int density = K34929def563f4a28cf.getInstance().cometTrailDensity.getValue().intValue();
            ++this.updateCounter;
            boolean shouldAddPosition = this.updateCounter % density == 0;
            List<double[]> currentGhostPositions = new ArrayList();
            double centerX = getTargetX();
            double centerY = getTargetY() + (double)(currentTarget.method_17682() / 2.0F);
            double centerZ = getTargetZ();

            for(int i = 0; i < 3; ++i) {
               float angle = Ka0484a23398fb5f9ac.interpolate((double)this.prevGhostRotationAngle, (double)this.ghostRotationAngle) + (float)(i * 180);
               double radius = (double)(currentTarget.method_17681() * dony);
               double offsetX = Math.cos(Math.toRadians((double)angle)) * radius;
               double offsetZ = Math.sin(Math.toRadians((double)angle)) * radius;
               double offsetY = Math.cos(Math.toRadians((double)angle)) / (double)3.0F * radius;
               double ghostYI = (double)Ka0484a23398fb5f9ac.interpolate((double)this.prevGhostYRotationAngle, (double)this.ghostYRotationAngle);
               if (i == 0) {
                  offsetY = Math.sin(Math.toRadians(ghostYI)) * (double)currentTarget.method_17682() * (double)rem;
               } else if (i == 2) {
                  offsetY = -Math.sin(Math.toRadians(ghostYI)) * (double)currentTarget.method_17682() * (double)rem;
                  offsetX = -Math.cos(Math.toRadians((double)angle)) * radius;
               }

               double ghostX = centerX + offsetX;
               double ghostY = centerY + offsetY;
               double ghostZ = centerZ + offsetZ;
               currentGhostPositions.add(new double[]{ghostX, ghostY, ghostZ, (double)angle});
            }

            if (shouldAddPosition) {
               this.previousPositions.addAll(currentGhostPositions);
            }

            if (this.previousPositions.size() > trailLength * 3) {
               this.previousPositions.subList(0, this.previousPositions.size() - trailLength * 3).clear();
            }

            for(int i = 0; i < 3; ++i) {
               double[] currentPos = (double[])currentGhostPositions.get(i);
               float angle = (float)currentPos[3];
               double renderX = currentPos[0] - mc.method_1561().field_4686.method_19326().method_10216();
               double renderY = currentPos[1] - mc.method_1561().field_4686.method_19326().method_10214();
               double renderZ = currentPos[2] - mc.method_1561().field_4686.method_19326().method_10215();
               this.renderGhost(matrixStack, renderX, renderY, renderZ, angle, size, 1.0F);

               for(int t = 0; t < this.previousPositions.size() / 3; ++t) {
                  int index = t * 3 + i;
                  if (index < this.previousPositions.size()) {
                     double[] trailPos = (double[])this.previousPositions.get(index);
                     double trailRenderX = trailPos[0] - mc.method_1561().field_4686.method_19326().method_10216();
                     double trailRenderY = trailPos[1] - mc.method_1561().field_4686.method_19326().method_10214();
                     double trailRenderZ = trailPos[2] - mc.method_1561().field_4686.method_19326().method_10215();
                     float trailAngle = (float)trailPos[3];
                     float trailAlpha = (float)(t + 1) / ((float)this.previousPositions.size() / 3.0F + 1.0F);
                     float trailSize = Math.max(size * 0.4F, size * trailAlpha);
                     this.renderGhost(matrixStack, trailRenderX, trailRenderY, trailRenderZ, trailAngle, trailSize, trailAlpha);
                  }
               }
            }
         } finally {
            RenderSystem.enableCull();
            RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            K2b5e2c5f2b9891b450.WORLD.endRender(matrixStack);
         }

      }
   }

   public void renderGhost(class_4587 stack, double x, double y, double z, float angle, float size, float alphaMultiplier) {
      stack.method_22903();
      stack.method_22904(x, y, z);
      class_4184 camera = mc.field_1773.method_19418();
      stack.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330() + 180.0F));
      stack.method_22907(class_7833.field_40714.rotationDegrees(-camera.method_19329() + 180.0F));
      RenderSystem.setShaderTexture(0, Kc1f6f8c785f6a08448.getImage("particles/glow"));
      Matrix4f matrix = stack.method_23760().method_23761();
      RenderSystem.setShader(class_10142.field_53880);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
      float[] c = K80ee108ea129adac11.normalize(K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient((int)angle), (int)((double)255.0F * showAnimation.getValue())));
      float alpha = c[3] * alphaMultiplier;
      buffer.method_22918(matrix, -size, size, 0.0F).method_22913(0.0F, 1.0F).method_22915(c[0], c[1], c[2], alpha);
      buffer.method_22918(matrix, size, size, 0.0F).method_22913(1.0F, 1.0F).method_22915(c[0], c[1], c[2], alpha);
      buffer.method_22918(matrix, size, -size, 0.0F).method_22913(1.0F, 0.0F).method_22915(c[0], c[1], c[2], alpha);
      buffer.method_22918(matrix, -size, -size, 0.0F).method_22913(0.0F, 0.0F).method_22915(c[0], c[1], c[2], alpha);
      class_286.method_43433(buffer.method_60800());
      stack.method_22909();
   }
}
