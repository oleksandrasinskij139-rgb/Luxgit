package code.miaware.api.utils.render;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import com.mojang.blaze3d.systems.RenderSystem;
import java.nio.IntBuffer;
import java.util.ArrayDeque;
import java.util.Deque;
import lombok.Generated;
import net.minecraft.class_4587;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

public final class K5b5bf2e4f791eb67a2 implements K4600bef438dafc1a9c {
   private static final Deque<K729cbe6b76ee355d28> STACK = new ArrayDeque();

   public static void start(class_4587 matrixStack, float x, float y, float width, float height) {
      float scale = (float)mc.method_22683().method_4495();
      int framebufferH = mc.method_22683().method_4506();
      int glX = (int)Math.floor((double)(x * scale));
      int glY = (int)Math.floor((double)((float)framebufferH - (y + height) * scale));
      int glRight = (int)Math.ceil((double)((x + width) * scale));
      int glTop = (int)Math.ceil((double)((float)framebufferH - y * scale));
      int glW = width <= 0.0F ? 0 : Math.max(0, glRight - glX);
      int glH = height <= 0.0F ? 0 : Math.max(0, glTop - glY);
      boolean wasOn = GL11.glGetBoolean(3089);
      int px = 0;
      int py = 0;
      int pw = 0;
      int ph = 0;
      if (wasOn) {
         MemoryStack ms = MemoryStack.stackPush();

         try {
            IntBuffer buf = ms.callocInt(4);
            GL11.glGetIntegerv(3088, buf);
            px = buf.get(0);
            py = buf.get(1);
            pw = buf.get(2);
            ph = buf.get(3);
         } catch (Throwable var27) {
            if (ms != null) {
               try {
                  ms.close();
               } catch (Throwable var26) {
                  var27.addSuppressed(var26);
               }
            }

            throw var27;
         }

         if (ms != null) {
            ms.close();
         }
      }

      STACK.push(new K729cbe6b76ee355d28(wasOn, px, py, pw, ph));
      int ix = glX;
      int iy = glY;
      int iw = glW;
      int ih = glH;
      if (wasOn) {
         int x1 = Math.max(glX, px);
         int y1 = Math.max(glY, py);
         int x2 = Math.min(glX + glW, px + pw);
         int y2 = Math.min(glY + glH, py + ph);
         iw = Math.max(0, x2 - x1);
         ih = Math.max(0, y2 - y1);
         ix = x1;
         iy = y1;
      }

      matrixStack.method_22903();
      RenderSystem.enableScissor(ix, iy, iw, ih);
   }

   public static void stop(class_4587 matrixStack) {
      matrixStack.method_22909();
      K729cbe6b76ee355d28 prev = (K729cbe6b76ee355d28)STACK.pop();
      if (!prev.enabled()) {
         RenderSystem.disableScissor();
      } else {
         RenderSystem.enableScissor(prev.x(), prev.y(), prev.w(), prev.h());
      }

   }

   @Generated
   private K5b5bf2e4f791eb67a2() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
