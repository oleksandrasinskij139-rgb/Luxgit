package code.miaware.api.utils.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_10142;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4587;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public final class K300f7955bbb2ec50a2 {
   private static final Vector3f[] VERTICES = new Vector3f[]{new Vector3f(0.0F, 1.5F, 0.0F), new Vector3f(0.0F, -1.5F, 0.0F), new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(-1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F), new Vector3f(0.0F, 0.0F, -1.0F)};
   private static final int[][] FACES = new int[][]{{0, 2, 4}, {0, 4, 3}, {0, 3, 5}, {0, 5, 2}, {1, 4, 2}, {1, 3, 4}, {1, 5, 3}, {1, 2, 5}};
   private static final float[] FACE_BRIGHTNESS = new float[]{1.0F, 0.8F, 0.6F, 0.9F, 0.7F, 0.5F, 0.4F, 0.6F};

   public static void render(class_4587 matrices, class_287 buffer, float x, float y, float z, float size, Color color) {
      matrices.method_22903();
      matrices.method_46416(x, y, z);
      matrices.method_22905(size, size, size);
      Matrix4f transformationMatrix = matrices.method_23760().method_23761();

      for(int i = 0; i < FACES.length; ++i) {
         int[] face = FACES[i];
         float brightness = FACE_BRIGHTNESS[i];
         Vector3f v1 = VERTICES[face[0]];
         Vector3f v2 = VERTICES[face[1]];
         Vector3f v3 = VERTICES[face[2]];
         int shadedColor = K258cd182dac1e27b5a(color.getRGB(), brightness);
         buffer.method_22918(transformationMatrix, v1.x, v1.y, v1.z).method_39415(shadedColor);
         buffer.method_22918(transformationMatrix, v2.x, v2.y, v2.z).method_39415(shadedColor);
         buffer.method_22918(transformationMatrix, v3.x, v3.y, v3.z).method_39415(shadedColor);
      }

      matrices.method_22909();
   }

   public static class_287 createBuffer() {
      K77d9ea75c35b053873();
      return class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);
   }

   private static void K77d9ea75c35b053873() {
      RenderSystem.setShader(class_10142.field_53876);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private static int K258cd182dac1e27b5a(int color, float brightness) {
      int alpha = color >> 24 & 255;
      int red = (int)((float)(color >> 16 & 255) * brightness);
      int green = (int)((float)(color >> 8 & 255) * brightness);
      int blue = (int)((float)(color & 255) * brightness);
      red = Math.min(255, Math.max(0, red));
      green = Math.min(255, Math.max(0, green));
      blue = Math.min(255, Math.max(0, blue));
      return alpha << 24 | red << 16 | green << 8 | blue;
   }

   private K300f7955bbb2ec50a2() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
