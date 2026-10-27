package code.miaware.client.util.render.effects;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL32;
import org.lwjgl.opengl.GL33;

public final class Kb74d5d2c9e20d74d0d implements AutoCloseable {
   private final int program = GL11.glGetInteger(35725);
   private final int vao = GL11.glGetInteger(34229);
   private final int array = GL11.glGetInteger(34964);
   private final int uniform = GL11.glGetInteger(35368);
   private final int uniform0 = GL30.glGetIntegeri(35368, 0);
   private final long uniformStart = GL32.glGetInteger64i(35369, 0);
   private final long uniformSize = GL32.glGetInteger64i(35370, 0);
   private final int active = GL11.glGetInteger(34016);
   private final int texture;
   private final int sampler;
   private final int readFbo = GL11.glGetInteger(36010);
   private final boolean blend = GL11.glIsEnabled(3042);
   private final boolean depth = GL11.glIsEnabled(2929);
   private final boolean cull = GL11.glIsEnabled(2884);
   private final boolean depthMask = GL11.glGetBoolean(2930);
   private final int depthFunc = GL11.glGetInteger(2932);
   private final int src = GL11.glGetInteger(32969);
   private final int dst = GL11.glGetInteger(32968);
   private final int srcA = GL11.glGetInteger(32971);
   private final int dstA = GL11.glGetInteger(32970);
   private final int eq = GL11.glGetInteger(32777);
   private final int eqA = GL11.glGetInteger(34877);

   public Kb74d5d2c9e20d74d0d() {
      GL13.glActiveTexture(33984);
      this.texture = GL11.glGetInteger(32873);
      this.sampler = GL30.glGetIntegeri(35097, 0);
   }

   public void close() {
      GL20.glUseProgram(this.program);
      GL30.glBindVertexArray(this.vao);
      GL15.glBindBuffer(34962, this.array);
      if (this.uniform0 != 0 && this.uniformSize > 0L) {
         GL30.glBindBufferRange(35345, 0, this.uniform0, this.uniformStart, this.uniformSize);
      } else {
         GL30.glBindBufferBase(35345, 0, this.uniform0);
      }

      GL15.glBindBuffer(35345, this.uniform);
      GL13.glActiveTexture(33984);
      GL11.glBindTexture(3553, this.texture);
      GL33.glBindSampler(0, this.sampler);
      GL13.glActiveTexture(this.active);
      GL30.glBindFramebuffer(36008, this.readFbo);
      GL14.glBlendFuncSeparate(this.src, this.dst, this.srcA, this.dstA);
      GL20.glBlendEquationSeparate(this.eq, this.eqA);
      GL11.glDepthFunc(this.depthFunc);
      GL11.glDepthMask(this.depthMask);
      K6889458eb8332bbc42(3042, this.blend);
      K6889458eb8332bbc42(2929, this.depth);
      K6889458eb8332bbc42(2884, this.cull);
   }

   private static void K6889458eb8332bbc42(int cap, boolean on) {
      if (on) {
         GL11.glEnable(cap);
      } else {
         GL11.glDisable(cap);
      }

   }
}
