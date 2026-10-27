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
import net.minecraft.class_10142;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

public class Kcb7b8bdbc7b44bb0ac extends K57a68ff1a2b51cf90d {
   private float rotation;
   private float prevRotation;
   private float impactProgress;
   private int prevHurtTime;

   public void onUpdate() {
      if (currentTarget != null && this.canDraw()) {
         K34929def563f4a28cf module = K34929def563f4a28cf.getInstance();
         this.prevRotation = this.rotation;
         this.rotation += 3.0F;
         this.K1f266a40503fc550c6();
      }
   }

   public void onRender3D(K9ee087b2e1791ad6b2 event) {
      if (currentTarget != null && this.canDraw()) {
         class_4587 stack = event.matrixStack();
         K34929def563f4a28cf module = K34929def563f4a28cf.getInstance();
         RenderSystem.enableBlend();
         RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
         RenderSystem.disableCull();
         RenderSystem.disableDepthTest();
         RenderSystem.depthMask(false);
         int count = 3;
         float show = (float)showAnimation.getValue();
         float sizeAnim = Ka0484a23398fb5f9ac.interpolate((double)this.prevSizeAnimation, sizeAnimation.getValue());
         float orbit = (currentTarget.method_17681() * 0.55F + 0.18F) * sizeAnim;
         float size = 0.22999999F * sizeAnim;
         float verticalAmplitude = currentTarget.method_17682() * 0.18F + 0.06F;
         float rotationValue = Ka0484a23398fb5f9ac.interpolate((double)this.prevRotation, (double)this.rotation);

         try {
            for(int i = 0; i < count; ++i) {
               float angle = rotationValue + 360.0F / (float)Math.max(1, count) * (float)i;
               double x = getTargetX() + Math.cos(Math.toRadians((double)angle)) * (double)orbit - mc.method_1561().field_4686.method_19326().field_1352;
               double y = getTargetY() + (double)currentTarget.method_17682() * 0.55 + Math.sin(Math.toRadians((double)(angle * 1.35F))) * (double)verticalAmplitude - mc.method_1561().field_4686.method_19326().field_1351;
               double z = getTargetZ() + Math.sin(Math.toRadians((double)angle)) * (double)orbit - mc.method_1561().field_4686.method_19326().field_1350;
               this.Kb0ebb6c80b3a998827(stack, x, y, z, size, show, i * 60);
            }
         } finally {
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         }

      }
   }

   private void Kb0ebb6c80b3a998827(class_4587 stack, double x, double y, double z, float size, float show, int index) {
      stack.method_22903();
      stack.method_22904(x, y, z);
      stack.method_22907(mc.field_1773.method_19418().method_23767());
      stack.method_22907(class_7833.field_40718.rotationDegrees((float)index * 7.0F));
      Matrix4f matrix = stack.method_23760().method_23761();
      RenderSystem.setShaderTexture(0, Kc1f6f8c785f6a08448.getImage("particles/glow"));
      RenderSystem.setShader(class_10142.field_53880);
      Color color = this.Kc15494f9b511c27945(K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient(index), (int)(175.0F * show)));
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
      buffer.method_22918(matrix, -size, -size, 0.0F).method_22913(0.0F, 0.0F).method_39415(color.getRGB());
      buffer.method_22918(matrix, -size, size, 0.0F).method_22913(0.0F, 1.0F).method_39415(color.getRGB());
      buffer.method_22918(matrix, size, size, 0.0F).method_22913(1.0F, 1.0F).method_39415(color.getRGB());
      buffer.method_22918(matrix, size, -size, 0.0F).method_22913(1.0F, 0.0F).method_39415(color.getRGB());
      class_286.method_43433(buffer.method_60800());
      stack.method_22909();
   }

   private void K1f266a40503fc550c6() {
      this.impactProgress = 0.0F;
      this.prevHurtTime = 0;
   }

   private Color Kc15494f9b511c27945(Color color) {
      return this.impactProgress <= 0.0F ? color : K80ee108ea129adac11.interpolate(new Color(255, 86, 104, color.getAlpha()), color, Math.min(1.0F, this.impactProgress));
   }
}
