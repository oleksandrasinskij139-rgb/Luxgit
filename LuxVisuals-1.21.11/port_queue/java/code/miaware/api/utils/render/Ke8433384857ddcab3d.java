package code.miaware.api.utils.render;

import code.miaware.api.utils.render.display.K4b843475c2462d48b1;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.nio.IntBuffer;
import java.util.List;
import net.minecraft.class_10142;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_408;
import net.minecraft.class_4587;
import net.minecraft.class_6367;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

public final class Ke8433384857ddcab3d {
   private static boolean capturing;
   private static K5c7f003ccf3606eac5 activeCoverage;
   private final K5c7f003ccf3606eac5 coverage = new K5c7f003ccf3606eac5();
   private List<K6eebe516d539e8f184> regions = List.of();
   private final K1751f94dd0ac361f5b clock = new K1751f94dd0ac361f5b();
   private class_6367 layer;
   private int guiWidth;
   private int guiHeight;
   private boolean unavailable;
   private Object world;
   private Object player;

   public static boolean isCapturing() {
      return capturing;
   }

   public static void record(Matrix4f matrix, float x, float y, float width, float height) {
      if (activeCoverage != null) {
         activeCoverage.add(matrix, x, y, width, height);
      }

   }

   public static void defaultBlendFunc() {
      if (capturing) {
         RenderSystem.blendFuncSeparate(770, 771, 1, 771);
      } else {
         RenderSystem.defaultBlendFunc();
      }

   }

   public void invalidate() {
      this.clock.invalidate();
   }

   public void release() {
      if (this.layer != null) {
         this.layer.method_1238();
         this.layer = null;
      }

      this.invalidate();
      this.world = null;
      this.player = null;
      this.regions = List.of();
   }

   public void render(class_4587 matrices, Runnable drawHud) {
      class_310 mc = class_310.method_1551();
      if (!this.unavailable && mc.field_1724 != null && mc.field_1687 != null && !(mc.field_1755 instanceof class_408)) {
         int width = mc.method_22683().method_4489();
         int height = mc.method_22683().method_4506();
         if (width > 0 && height > 0) {
            if (this.world != mc.field_1687 || this.player != mc.field_1724) {
               this.invalidate();
               this.world = mc.field_1687;
               this.player = mc.field_1724;
            }

            if (this.layer == null || this.layer.field_1482 != width || this.layer.field_1481 != height || this.guiWidth != mc.method_22683().method_4486() || this.guiHeight != mc.method_22683().method_4502()) {
               int target = GL11.glGetInteger(36006);
               MemoryStack stack = MemoryStack.stackPush();

               try {
                  IntBuffer viewport = stack.mallocInt(4);
                  GL11.glGetIntegerv(2978, viewport);

                  try {
                     this.release();
                     this.layer = new class_6367(width, height, true);
                     this.layer.method_1236(0.0F, 0.0F, 0.0F, 0.0F);
                     this.guiWidth = mc.method_22683().method_4486();
                     this.guiHeight = mc.method_22683().method_4502();
                     this.world = mc.field_1687;
                     this.player = mc.field_1724;
                  } catch (RuntimeException failure) {
                     this.release();
                     this.unavailable = true;
                     System.err.println("[HUD] Render cache unavailable: " + failure.getMessage());
                  } finally {
                     GlStateManager._glBindFramebuffer(36160, target);
                     RenderSystem.viewport(viewport.get(0), viewport.get(1), viewport.get(2), viewport.get(3));
                  }
               } catch (Throwable var33) {
                  if (stack != null) {
                     try {
                        stack.close();
                     } catch (Throwable var28) {
                        var33.addSuppressed(var28);
                     }
                  }

                  throw var33;
               }

               if (stack != null) {
                  stack.close();
               }

               if (this.unavailable) {
                  drawHud.run();
                  return;
               }
            }

            long now = System.nanoTime();
            if (this.clock.needsRefresh(now)) {
               mc.method_22940().method_23000().method_22993();
               int target = GL11.glGetInteger(36006);
               boolean depthMask = GL11.glGetBoolean(2930);
               MemoryStack stack = MemoryStack.stackPush();

               try {
                  IntBuffer viewport = stack.mallocInt(4);
                  GL11.glGetIntegerv(2978, viewport);

                  try {
                     RenderSystem.depthMask(true);
                     this.layer.method_1230();
                     RenderSystem.depthMask(depthMask);
                     this.layer.method_1235(true);
                     this.coverage.reset(this.guiWidth, this.guiHeight);
                     activeCoverage = this.coverage;
                     capturing = true;
                     drawHud.run();
                     mc.method_22940().method_23000().method_22993();
                     this.regions = this.coverage.regions();
                     this.clock.refreshed(now);
                  } finally {
                     capturing = false;
                     activeCoverage = null;
                     RenderSystem.depthMask(depthMask);
                     GlStateManager._glBindFramebuffer(36160, target);
                     RenderSystem.viewport(viewport.get(0), viewport.get(1), viewport.get(2), viewport.get(3));
                     RenderSystem.defaultBlendFunc();
                     K4b843475c2462d48b1.resetGLStateCache();
                  }
               } catch (Throwable var32) {
                  if (stack != null) {
                     try {
                        stack.close();
                     } catch (Throwable var27) {
                        var32.addSuppressed(var27);
                     }
                  }

                  throw var32;
               }

               if (stack != null) {
                  stack.close();
               }
            }

            this.Kf7f0ec46a34251f12d(matrices);
         }
      } else {
         this.invalidate();
         drawHud.run();
      }
   }

   private void Kf7f0ec46a34251f12d(class_4587 matrices) {
      if (!this.regions.isEmpty()) {
         boolean depth = GL11.glIsEnabled(2929);
         boolean cull = GL11.glIsEnabled(2884);
         boolean blend = GL11.glIsEnabled(3042);
         boolean depthMask = GL11.glGetBoolean(2930);

         try {
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.disableCull();
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(1, 771, 1, 771);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShader(class_10142.field_53880);
            RenderSystem.setShaderTexture(0, this.layer.method_30277());
            Matrix4f matrix = matrices.method_23760().method_23761();
            class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);

            for(K6eebe516d539e8f184 region : this.regions) {
               float x = (float)region.x();
               float y = (float)region.y();
               float right = x + (float)region.width();
               float bottom = y + (float)region.height();
               float u0 = x / (float)this.guiWidth;
               float u1 = right / (float)this.guiWidth;
               float v0 = 1.0F - y / (float)this.guiHeight;
               float v1 = 1.0F - bottom / (float)this.guiHeight;
               buffer.method_22918(matrix, x, y, 0.0F).method_22913(u0, v0).method_39415(-1);
               buffer.method_22918(matrix, x, bottom, 0.0F).method_22913(u0, v1).method_39415(-1);
               buffer.method_22918(matrix, right, bottom, 0.0F).method_22913(u1, v1).method_39415(-1);
               buffer.method_22918(matrix, right, y, 0.0F).method_22913(u1, v0).method_39415(-1);
            }

            class_286.method_43433(buffer.method_60800());
         } finally {
            RenderSystem.setShaderTexture(0, 0);
            RenderSystem.defaultBlendFunc();
            RenderSystem.depthMask(depthMask);
            if (depth) {
               RenderSystem.enableDepthTest();
            } else {
               RenderSystem.disableDepthTest();
            }

            if (cull) {
               RenderSystem.enableCull();
            } else {
               RenderSystem.disableCull();
            }

            if (blend) {
               RenderSystem.enableBlend();
            } else {
               RenderSystem.disableBlend();
            }

         }

      }
   }
}
