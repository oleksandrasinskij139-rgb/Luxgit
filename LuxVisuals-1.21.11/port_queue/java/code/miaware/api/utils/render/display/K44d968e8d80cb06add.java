package code.miaware.api.utils.render.display;

import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10142;
import net.minecraft.class_4587;

public class K44d968e8d80cb06add {
   private static boolean lastBlendState = false;
   private static boolean lastDepthTestState = false;
   private static boolean lastDepthMaskState = false;
   private static int glStateCallsAvoided = 0;

   public void startRender(class_4587 matrixStack) {
      matrixStack.method_22903();
      if (!lastBlendState) {
         RenderSystem.enableBlend();
         RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
         lastBlendState = true;
      } else {
         ++glStateCallsAvoided;
      }

      if (!lastDepthTestState) {
         RenderSystem.enableDepthTest();
         lastDepthTestState = true;
      } else {
         ++glStateCallsAvoided;
      }

      if (lastDepthMaskState) {
         RenderSystem.depthMask(false);
         lastDepthMaskState = false;
      } else {
         ++glStateCallsAvoided;
      }

      RenderSystem.setShader(class_10142.field_53880);
   }

   public void endRender(class_4587 matrixStack) {
      if (!lastDepthMaskState) {
         RenderSystem.depthMask(true);
         lastDepthMaskState = true;
      } else {
         ++glStateCallsAvoided;
      }

      if (lastDepthTestState) {
         RenderSystem.disableDepthTest();
         lastDepthTestState = false;
      } else {
         ++glStateCallsAvoided;
      }

      if (lastBlendState) {
         RenderSystem.disableBlend();
         lastBlendState = false;
      } else {
         ++glStateCallsAvoided;
      }

      matrixStack.method_22909();
   }

   public static void resetGLStateCache() {
      lastBlendState = false;
      lastDepthTestState = false;
      lastDepthMaskState = false;
   }

   public static int getGLStateCallsAvoided() {
      return glStateCallsAvoided;
   }
}
