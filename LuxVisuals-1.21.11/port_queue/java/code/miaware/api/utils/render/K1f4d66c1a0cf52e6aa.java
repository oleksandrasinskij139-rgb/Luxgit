package code.miaware.api.utils.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_6367;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class K1f4d66c1a0cf52e6aa {
   private static int shaderProgram = -1;
   private static int vao = -1;
   private static int vbo = -1;
   private static int saturationUniform = -1;
   private static int diffuseSamplerUniform = -1;
   private static boolean initialized = false;
   private static class_276 tempFramebuffer = null;
   private static final float[] FULLSCREEN_QUAD = new float[]{-1.0F, -1.0F, 1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F};

   public static void initialize() {
      if (!initialized) {
         try {
            int vertexShader = K4555974bdb1e571667(35633, "assets/luxvisuals/shaders/saturation.vsh");
            int fragmentShader = K4555974bdb1e571667(35632, "assets/luxvisuals/shaders/saturation.fsh");
            shaderProgram = GL20.glCreateProgram();
            GL20.glAttachShader(shaderProgram, vertexShader);
            GL20.glAttachShader(shaderProgram, fragmentShader);
            GL20.glLinkProgram(shaderProgram);
            if (GL20.glGetProgrami(shaderProgram, 35714) == 0) {
               String log = GL20.glGetProgramInfoLog(shaderProgram);
               throw new RuntimeException("Failed to link shader program: " + log);
            }

            GL20.glDeleteShader(vertexShader);
            GL20.glDeleteShader(fragmentShader);
            saturationUniform = GL20.glGetUniformLocation(shaderProgram, "Saturation");
            diffuseSamplerUniform = GL20.glGetUniformLocation(shaderProgram, "DiffuseSampler");
            vao = GL30.glGenVertexArrays();
            GL30.glBindVertexArray(vao);
            vbo = GL20.glGenBuffers();
            GL20.glBindBuffer(34962, vbo);
            GL20.glBufferData(34962, FULLSCREEN_QUAD, 35044);
            int positionAttrib = GL20.glGetAttribLocation(shaderProgram, "Position");
            GL20.glEnableVertexAttribArray(positionAttrib);
            GL20.glVertexAttribPointer(positionAttrib, 2, 5126, false, 0, 0L);
            GL30.glBindVertexArray(0);
            GL20.glBindBuffer(34962, 0);
            initialized = true;
         } catch (Exception e) {
            e.printStackTrace();
         }

      }
   }

   private static int K4555974bdb1e571667(int type, String path) throws IOException {
      String source = Ke5c8ff67c04c0b13d9(path);
      int shader = GL20.glCreateShader(type);
      GL20.glShaderSource(shader, source);
      GL20.glCompileShader(shader);
      if (GL20.glGetShaderi(shader, 35713) == 0) {
         String log = GL20.glGetShaderInfoLog(shader);
         GL20.glDeleteShader(shader);
         throw new RuntimeException("Failed to compile shader " + path + ": " + log);
      } else {
         return shader;
      }
   }

   private static String Ke5c8ff67c04c0b13d9(String path) throws IOException {
      BufferedReader reader = new BufferedReader(new InputStreamReader(K1f4d66c1a0cf52e6aa.class.getClassLoader().getResourceAsStream(path), StandardCharsets.UTF_8));

      String var4;
      try {
         StringBuilder source = new StringBuilder();

         String line;
         while((line = reader.readLine()) != null) {
            source.append(line).append('\n');
         }

         var4 = source.toString();
      } catch (Throwable var6) {
         try {
            reader.close();
         } catch (Throwable var5) {
            var6.addSuppressed(var5);
         }

         throw var6;
      }

      reader.close();
      return var4;
   }

   public static void render(float saturation, float brightness, float contrast, float redTint, float greenTint, float blueTint) {
      if (!initialized) {
         initialize();
      }

      boolean needsProcessing = Math.abs(saturation - 1.0F) > 0.01F || Math.abs(brightness - 1.0F) > 0.01F || Math.abs(contrast - 1.0F) > 0.01F || Math.abs(redTint - 1.0F) > 0.01F || Math.abs(greenTint - 1.0F) > 0.01F || Math.abs(blueTint - 1.0F) > 0.01F;
      if (shaderProgram != -1 && needsProcessing) {
         class_310 mc = class_310.method_1551();
         class_276 mainFramebuffer = mc.method_1522();
         if (tempFramebuffer == null || tempFramebuffer.field_1482 != mainFramebuffer.field_1482 || tempFramebuffer.field_1481 != mainFramebuffer.field_1481) {
            if (tempFramebuffer != null) {
               tempFramebuffer.method_1238();
            }

            tempFramebuffer = new class_6367(mainFramebuffer.field_1482, mainFramebuffer.field_1481, true);
            tempFramebuffer.method_1236(0.0F, 0.0F, 0.0F, 0.0F);
         }

         RenderSystem.assertOnRenderThread();
         mainFramebuffer.method_35610();
         tempFramebuffer.method_1235(false);
         mainFramebuffer.method_1237(tempFramebuffer.field_1482, tempFramebuffer.field_1481);
         mainFramebuffer.method_1235(false);
         GL20.glUseProgram(shaderProgram);
         GL20.glUniform1f(saturationUniform, saturation);
         int brightnessUniform = GL20.glGetUniformLocation(shaderProgram, "Brightness");
         if (brightnessUniform != -1) {
            GL20.glUniform1f(brightnessUniform, brightness);
         }

         int contrastUniform = GL20.glGetUniformLocation(shaderProgram, "Contrast");
         if (contrastUniform != -1) {
            GL20.glUniform1f(contrastUniform, contrast);
         }

         int colorTintUniform = GL20.glGetUniformLocation(shaderProgram, "ColorTint");
         if (colorTintUniform != -1) {
            GL20.glUniform3f(colorTintUniform, redTint, greenTint, blueTint);
         }

         GL20.glUniform1i(diffuseSamplerUniform, 0);
         GL13.glActiveTexture(33984);
         GL11.glBindTexture(3553, tempFramebuffer.method_30277());
         GL11.glDisable(2929);
         GL11.glDepthMask(false);
         GL11.glDisable(3042);
         GL11.glDisable(2884);
         GL30.glBindVertexArray(vao);
         GL11.glDrawArrays(5, 0, 4);
         GL30.glBindVertexArray(0);
         GL11.glEnable(2929);
         GL11.glDepthMask(true);
         GL11.glEnable(3042);
         GL20.glUseProgram(0);
         mainFramebuffer.method_1240();
      }
   }

   public static void cleanup() {
      if (vao != -1) {
         GL30.glDeleteVertexArrays(vao);
         vao = -1;
      }

      if (vbo != -1) {
         GL20.glDeleteBuffers(vbo);
         vbo = -1;
      }

      if (shaderProgram != -1) {
         GL20.glDeleteProgram(shaderProgram);
         shaderProgram = -1;
      }

      if (tempFramebuffer != null) {
         tempFramebuffer.method_1238();
         tempFramebuffer = null;
      }

      initialized = false;
   }
}
