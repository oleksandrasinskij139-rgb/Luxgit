package code.miaware.client.util.render.effects;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL31;
import org.lwjgl.opengl.GL33;

public final class K6588cb1ea6dceff65b implements AutoCloseable {
   private int program;
   private int vao;
   private int buffer;

   public void init(String shader, String block, int size) {
      if (this.program == 0) {
         int vs = 0;
         int fs = 0;

         try {
            vs = K3b8cfcc8fb940f3c63(35633, shader + ".vsh");
            fs = K3b8cfcc8fb940f3c63(35632, shader + ".fsh");
            this.program = GL20.glCreateProgram();
            GL20.glAttachShader(this.program, vs);
            GL20.glAttachShader(this.program, fs);
            GL20.glBindAttribLocation(this.program, 0, "Position");
            GL20.glBindAttribLocation(this.program, 1, "UV0");
            GL20.glBindAttribLocation(this.program, 2, "Color");
            GL20.glLinkProgram(this.program);
            if (GL20.glGetProgrami(this.program, 35714) == 0) {
               throw new IllegalStateException(shader + ": " + GL20.glGetProgramInfoLog(this.program));
            }

            int index = GL31.glGetUniformBlockIndex(this.program, block);
            if (index == -1) {
               throw new IllegalStateException("Missing block " + block);
            }

            GL31.glUniformBlockBinding(this.program, index, 0);
            this.vao = GL30.glGenVertexArrays();
            this.buffer = GL15.glGenBuffers();
            GL15.glBindBuffer(35345, this.buffer);
            GL15.glBufferData(35345, (long)size, 35040);
         } catch (RuntimeException failure) {
            this.close();
            throw failure;
         } finally {
            if (vs != 0) {
               GL20.glDeleteShader(vs);
            }

            if (fs != 0) {
               GL20.glDeleteShader(fs);
            }

         }

      }
   }

   private static int K3b8cfcc8fb940f3c63(int type, String file) {
      String path = "/assets/luxvisuals/shaders/effects/" + file;

      String source;
      try {
         InputStream input = K6588cb1ea6dceff65b.class.getResourceAsStream(path);

         try {
            if (input == null) {
               throw new IOException("Missing " + path);
            }

            source = new String(input.readAllBytes(), StandardCharsets.UTF_8);
         } catch (Throwable var8) {
            if (input != null) {
               try {
                  input.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }
            }

            throw var8;
         }

         if (input != null) {
            input.close();
         }
      } catch (IOException e) {
         throw new IllegalStateException(e);
      }

      int shader = GL20.glCreateShader(type);
      GL20.glShaderSource(shader, source);
      GL20.glCompileShader(shader);
      if (GL20.glGetShaderi(shader, 35713) == 0) {
         String log = GL20.glGetShaderInfoLog(shader);
         GL20.glDeleteShader(shader);
         throw new IllegalStateException(file + ": " + log);
      } else {
         return shader;
      }
   }

   public void bind(ByteBuffer data) {
      GL20.glUseProgram(this.program);
      GL30.glBindVertexArray(this.vao);
      GL15.glBindBuffer(35345, this.buffer);
      GL15.glBufferSubData(35345, 0L, data);
      GL30.glBindBufferBase(35345, 0, this.buffer);
      GL11.glEnable(3042);
      GL20.glBlendEquationSeparate(32774, 32774);
      GL14.glBlendFuncSeparate(770, 771, 1, 771);
      GL11.glEnable(2929);
      GL11.glDepthFunc(515);
      GL11.glDepthMask(false);
      GL11.glDisable(2884);
   }

   public void sampler(String name, int texture) {
      GL13.glActiveTexture(33984);
      GL11.glBindTexture(3553, texture);
      GL33.glBindSampler(0, 0);
      GL20.glUniform1i(GL20.glGetUniformLocation(this.program, name), 0);
   }

   public void close() {
      if (this.buffer != 0) {
         GL15.glDeleteBuffers(this.buffer);
      }

      if (this.vao != 0) {
         GL30.glDeleteVertexArrays(this.vao);
      }

      if (this.program != 0) {
         GL20.glDeleteProgram(this.program);
      }

      this.program = this.vao = this.buffer = 0;
   }
}
