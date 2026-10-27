package code.miaware.client.features.modules.player.invsave;

import code.miaware.client.util.render.effects.K6588cb1ea6dceff65b;
import code.miaware.client.util.render.effects.Kb74d5d2c9e20d74d0d;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.nio.ByteBuffer;
import java.util.List;
import net.minecraft.class_10366;
import net.minecraft.class_1661;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_5944;
import net.minecraft.class_6367;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

public final class K402a602b5f2f2b781f implements AutoCloseable {
   private static boolean capturing;
   private final K6588cb1ea6dceff65b pass = new K6588cb1ea6dceff65b();
   private ByteBuffer data;
   private class_6367 atlas;
   private long revision = -1L;
   private static final int SIZE = 672;

   public static boolean isCapturing() {
      return capturing;
   }

   private void Kd974cda5cf48dcedce(class_332 parent, K9e64dd2dd1a3d84dbb layout, long revision) {
      class_310 mc = class_310.method_1551();
      parent.method_51452();
      int draw = GL11.glGetInteger(36006);
      int[] viewport = new int[4];
      GL11.glGetIntegerv(2978, viewport);
      float[] clear = new float[4];
      GL11.glGetFloatv(3106, clear);
      double depthClear = GL11.glGetDouble(2931);
      boolean scissor = GL11.glIsEnabled(3089);
      class_5944 shader = RenderSystem.getShader();
      float[] shaderColor = (float[])RenderSystem.getShaderColor().clone();
      int[] textures = new int[]{RenderSystem.getShaderTexture(0), RenderSystem.getShaderTexture(1), RenderSystem.getShaderTexture(2)};
      int active = GL11.glGetInteger(34016);
      int[] bindings = new int[3];

      for(int i = 0; i < bindings.length; ++i) {
         GL13.glActiveTexture('蓀' + i);
         bindings[i] = GL11.glGetInteger(32873);
      }

      GL13.glActiveTexture(active);
      Matrix4f projection = new Matrix4f(RenderSystem.getProjectionMatrix());
      class_10366 projectionType = RenderSystem.getProjectionType();
      RenderSystem.getModelViewStack().pushMatrix().identity();
      capturing = true;
      boolean var28 = false;

      try {
         var28 = true;
         Kb74d5d2c9e20d74d0d ignored = new Kb74d5d2c9e20d74d0d();

         try {
            if (this.atlas == null) {
               this.atlas = new class_6367(288, 216, true);
            }

            GL30.glBindFramebuffer(36160, this.atlas.field_1476);
            GL11.glViewport(0, 0, 288, 216);
            GL11.glDisable(3089);
            RenderSystem.depthMask(true);
            GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
            GL11.glClearDepth((double)1.0F);
            GL11.glClear(16640);
            RenderSystem.setProjectionMatrix((new Matrix4f()).setOrtho(0.0F, 144.0F, 108.0F, 0.0F, -1000.0F, 1000.0F), class_10366.field_54954);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            class_332 context = new class_332(mc, mc.method_22940().method_23000());

            for(int i = 0; i < 41; ++i) {
               class_1799 stack = (class_1799)layout.slots().get(i);
               if (!stack.method_7960()) {
                  context.method_51427(stack, i % 8 * 18, i / 8 * 18);
               }
            }

            context.method_51452();
            this.revision = revision;
         } catch (Throwable var30) {
            try {
               ignored.close();
            } catch (Throwable var29) {
               var30.addSuppressed(var29);
            }

            throw var30;
         }

         ignored.close();
         var28 = false;
      } finally {
         if (var28) {
            capturing = false;
            GlStateManager._glBindFramebuffer(36009, draw);
            GL11.glViewport(viewport[0], viewport[1], viewport[2], viewport[3]);
            GL11.glClearColor(clear[0], clear[1], clear[2], clear[3]);
            GL11.glClearDepth(depthClear);
            if (scissor) {
               GL11.glEnable(3089);
            } else {
               GL11.glDisable(3089);
            }

            RenderSystem.getModelViewStack().popMatrix();
            RenderSystem.setProjectionMatrix(projection, projectionType);
            RenderSystem.setShader(shader);
            RenderSystem.setShaderColor(shaderColor[0], shaderColor[1], shaderColor[2], shaderColor[3]);

            for(int i = 0; i < textures.length; ++i) {
               RenderSystem.setShaderTexture(i, textures[i]);
            }

            GlStateManager._glUseProgram(GL11.glGetInteger(35725));
            RenderSystem.blendFuncSeparate(GL11.glGetInteger(32969), GL11.glGetInteger(32968), GL11.glGetInteger(32971), GL11.glGetInteger(32970));
            if (GL11.glIsEnabled(3042)) {
               RenderSystem.enableBlend();
            } else {
               RenderSystem.disableBlend();
            }

            if (GL11.glIsEnabled(2929)) {
               RenderSystem.enableDepthTest();
            } else {
               RenderSystem.disableDepthTest();
            }

            if (GL11.glIsEnabled(2884)) {
               RenderSystem.enableCull();
            } else {
               RenderSystem.disableCull();
            }

            RenderSystem.depthMask(GL11.glGetBoolean(2930));
            RenderSystem.depthFunc(GL11.glGetInteger(2932));

            for(int i = 0; i < bindings.length; ++i) {
               GL13.glActiveTexture('蓀' + i);
               GlStateManager._activeTexture('蓀' + i);
               GlStateManager._bindTexture(bindings[i]);
            }

            GL13.glActiveTexture(active);
            GlStateManager._activeTexture(active);
         }
      }

      capturing = false;
      GlStateManager._glBindFramebuffer(36009, draw);
      GL11.glViewport(viewport[0], viewport[1], viewport[2], viewport[3]);
      GL11.glClearColor(clear[0], clear[1], clear[2], clear[3]);
      GL11.glClearDepth(depthClear);
      if (scissor) {
         GL11.glEnable(3089);
      } else {
         GL11.glDisable(3089);
      }

      RenderSystem.getModelViewStack().popMatrix();
      RenderSystem.setProjectionMatrix(projection, projectionType);
      RenderSystem.setShader(shader);
      RenderSystem.setShaderColor(shaderColor[0], shaderColor[1], shaderColor[2], shaderColor[3]);

      for(int i = 0; i < textures.length; ++i) {
         RenderSystem.setShaderTexture(i, textures[i]);
      }

      GlStateManager._glUseProgram(GL11.glGetInteger(35725));
      RenderSystem.blendFuncSeparate(GL11.glGetInteger(32969), GL11.glGetInteger(32968), GL11.glGetInteger(32971), GL11.glGetInteger(32970));
      if (GL11.glIsEnabled(3042)) {
         RenderSystem.enableBlend();
      } else {
         RenderSystem.disableBlend();
      }

      if (GL11.glIsEnabled(2929)) {
         RenderSystem.enableDepthTest();
      } else {
         RenderSystem.disableDepthTest();
      }

      if (GL11.glIsEnabled(2884)) {
         RenderSystem.enableCull();
      } else {
         RenderSystem.disableCull();
      }

      RenderSystem.depthMask(GL11.glGetBoolean(2930));
      RenderSystem.depthFunc(GL11.glGetInteger(2932));

      for(int i = 0; i < bindings.length; ++i) {
         GL13.glActiveTexture('蓀' + i);
         GlStateManager._activeTexture('蓀' + i);
         GlStateManager._bindTexture(bindings[i]);
      }

      GL13.glActiveTexture(active);
      GlStateManager._activeTexture(active);
   }

   public void render(class_332 context, K9e64dd2dd1a3d84dbb layout, long revision, List<class_1735> slots, class_1661 inventory, int x, int y) {
      if (this.data == null) {
         this.data = MemoryUtil.memAlloc(672);
      }

      class_310 mc = class_310.method_1551();
      this.data.clear();
      float opacity = 0.68F + 0.025F * (float)Math.sin((double)System.nanoTime() / (double)6.0E8F);
      this.data.putFloat((float)mc.method_22683().method_4486()).putFloat((float)mc.method_22683().method_4502()).putFloat(opacity).putFloat(0.0F);
      int count = 0;

      for(class_1735 slot : slots) {
         int index = slot.method_34266();
         if (slot.field_7871 == inventory && slot.method_7682() && index >= 0 && index < 41 && !((class_1799)layout.slots().get(index)).method_7960() && !K775957aef270c0236c.fits((class_1799)layout.slots().get(index), slot.method_7677())) {
            boolean occupied = slot.method_7681();
            this.data.putFloat((float)(x + slot.field_7873 + (occupied ? 8 : 0))).putFloat((float)(y + slot.field_7872)).putFloat((float)index).putFloat(occupied ? 8.0F : 16.0F);
            ++count;
         }
      }

      if (count != 0) {
         if (this.atlas == null || this.revision != revision) {
            this.Kd974cda5cf48dcedce(context, layout, revision);
         }

         while(this.data.position() < 672) {
            this.data.putFloat(0.0F);
         }

         this.data.flip();
         context.method_51452();

         try (Kb74d5d2c9e20d74d0d ignored = new Kb74d5d2c9e20d74d0d()) {
            this.pass.init("inventory_ghost", "GhostData", 672);
            this.pass.bind(this.data);
            this.pass.sampler("Icons", this.atlas.method_30277());
            GL11.glDisable(2929);
            GL11.glDrawArrays(4, 0, count * 6);
         }

      }
   }

   public void close() {
      if (this.atlas != null) {
         int draw = GL11.glGetInteger(36006);

         try {
            Kb74d5d2c9e20d74d0d ignored = new Kb74d5d2c9e20d74d0d();

            try {
               this.atlas.method_1238();
            } catch (Throwable var10) {
               try {
                  ignored.close();
               } catch (Throwable var9) {
                  var10.addSuppressed(var9);
               }

               throw var10;
            }

            ignored.close();
         } finally {
            GlStateManager._glBindFramebuffer(36009, draw);
            this.atlas = null;
         }
      }

      this.pass.close();
      if (this.data != null) {
         MemoryUtil.memFree(this.data);
         this.data = null;
      }

      this.revision = -1L;
   }
}
