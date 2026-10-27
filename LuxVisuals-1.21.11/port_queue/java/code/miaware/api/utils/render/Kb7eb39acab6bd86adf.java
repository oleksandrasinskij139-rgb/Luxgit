package code.miaware.api.utils.render;

import code.miaware.client.features.modules.render.K45978f900e11d6615f;
import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import org.joml.Matrix4f;

public final class Kb7eb39acab6bd86adf {
   private static final List<Kddf5cf479d75866b66> batches = new ArrayList();
   private static boolean isBatching = false;
   private static int currentGLState = 0;

   public static void beginBatch() {
      if (K45978f900e11d6615f.getInstance().isBatchRenderingEnabled()) {
         isBatching = true;
         batches.clear();
      }

   }

   public static void addToBatch(Matrix4f matrix, class_293.class_5596 drawMode, float[] positions, int[] colors) {
      if (isBatching) {
         Kddf5cf479d75866b66 batch = K350c1024b9c1946cce(drawMode, currentGLState);

         for(int i = 0; i < positions.length; i += 3) {
            batch.vertices.add(new Kdacc3de6d9f111539d(positions[i], positions[i + 1], positions[i + 2], colors[i / 3]));
         }

      }
   }

   public static void endBatch(Matrix4f matrix) {
      if (isBatching) {
         for(Kddf5cf479d75866b66 batch : batches) {
            if (!batch.vertices.isEmpty()) {
               class_287 buffer = class_289.method_1348().method_60827(batch.drawMode, class_290.field_1576);

               for(Kdacc3de6d9f111539d v : batch.vertices) {
                  int r = v.color >> 16 & 255;
                  int g = v.color >> 8 & 255;
                  int b = v.color & 255;
                  int a = v.color >> 24 & 255;
                  buffer.method_22918(matrix, v.x, v.y, v.z).method_1336(r, g, b, a);
               }

               class_286.method_43433(buffer.method_60800());
            }
         }

         batches.clear();
         isBatching = false;
      }
   }

   public static void setGLState(boolean blend, boolean depthTest, boolean cull) {
      currentGLState = (blend ? 1 : 0) | (depthTest ? 2 : 0) | (cull ? 4 : 0);
   }

   private static Kddf5cf479d75866b66 K350c1024b9c1946cce(class_293.class_5596 drawMode, int glState) {
      for(Kddf5cf479d75866b66 batch : batches) {
         if (batch.drawMode == drawMode && batch.glState == glState) {
            return batch;
         }
      }

      Kddf5cf479d75866b66 newBatch = new Kddf5cf479d75866b66();
      newBatch.drawMode = drawMode;
      newBatch.glState = glState;
      batches.add(newBatch);
      return newBatch;
   }

   public static void setupGLState(boolean blend, boolean depthTest, boolean cull, float lineWidth) {
      if (blend) {
         RenderSystem.enableBlend();
         RenderSystem.blendFuncSeparate(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA, class_4535.ONE, class_4534.ZERO);
      }

      if (!depthTest) {
         RenderSystem.disableDepthTest();
         RenderSystem.depthMask(false);
      }

      if (!cull) {
         RenderSystem.disableCull();
      }

      if (lineWidth > 1.0F) {
         RenderSystem.lineWidth(lineWidth);
      }

      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static void restoreGLState() {
      RenderSystem.enableDepthTest();
      RenderSystem.depthMask(true);
      RenderSystem.enableCull();
      RenderSystem.disableBlend();
      RenderSystem.lineWidth(1.0F);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
   }

   @Generated
   private Kb7eb39acab6bd86adf() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
