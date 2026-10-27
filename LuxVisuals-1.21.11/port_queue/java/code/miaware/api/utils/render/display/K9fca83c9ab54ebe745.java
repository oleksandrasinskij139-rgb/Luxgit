package code.miaware.api.utils.render.display;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_10142;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4604;
import net.minecraft.class_9801;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

public class K9fca83c9ab54ebe745 implements K4600bef438dafc1a9c {
   private final List<K1e8d44c34b687dbad5> outlinedBoxes = new ArrayList();
   private final List<K8b7f14243faf5d42bc> filledBoxes = new ArrayList();
   private final List<Kaa053b0af66ed49864> stripedBoxes = new ArrayList();
   private boolean lastBlendEnabled = false;
   private boolean lastCullEnabled = true;
   private boolean lastDepthTestEnabled = true;

   public void setup3DRender(class_4587 matrixStack) {
      if (!this.filledBoxes.isEmpty() || !this.outlinedBoxes.isEmpty() || !this.stripedBoxes.isEmpty()) {
         if (mc.field_1687 != null && mc.field_1773 != null) {
            class_4184 camera = mc.field_1773.method_19418();
            class_243 cameraPos = camera.method_19326();
            class_4604 frustum = new class_4604(matrixStack.method_23760().method_23761(), RenderSystem.getProjectionMatrix());
            frustum.method_23088(cameraPos.field_1352, cameraPos.field_1351, cameraPos.field_1350);
            class_4587 renderStack = new class_4587();
            if (!this.filledBoxes.isEmpty()) {
               this.K86cfe705adc4af9794();
               RenderSystem.setShader(class_10142.field_53876);
               class_289 tessellator = class_289.method_1348();
               class_287 buffer = tessellator.method_60827(class_5596.field_27382, class_290.field_1576);
               boolean hasVisibleBoxes = false;

               for(K8b7f14243faf5d42bc b : this.filledBoxes) {
                  double minX = b.pos.field_1352;
                  double minY = b.pos.field_1351;
                  double minZ = b.pos.field_1350;
                  double maxX = minX + b.params.field_1352;
                  double maxY = minY + b.params.field_1351;
                  double maxZ = minZ + b.params.field_1350;
                  double centerX = (minX + maxX) * (double)0.5F;
                  double centerY = (minY + maxY) * (double)0.5F;
                  double centerZ = (minZ + maxZ) * (double)0.5F;
                  double dx = cameraPos.field_1352 - centerX;
                  double dy = cameraPos.field_1351 - centerY;
                  double dz = cameraPos.field_1350 - centerZ;
                  double distSq = dx * dx + dy * dy + dz * dz;
                  double renderDistSq = (double)((Integer)mc.field_1690.method_42503().method_41753() * 16 * 16);
                  if (!(distSq > renderDistSq) && frustum.method_23093(new class_238(minX, minY, minZ, maxX, maxY, maxZ))) {
                     this.K442cf4bd1f953d95d0(b.pos, b.params, renderStack, buffer, b.color);
                     hasVisibleBoxes = true;
                  }
               }

               class_9801 built = buffer.method_60794();
               if (built != null) {
                  class_286.method_43433(built);
               }

               this.Ke6e60bd0fdd0d6ea5b();
            }

            if (!this.outlinedBoxes.isEmpty()) {
               this.K86cfe705adc4af9794();
               RenderSystem.setShader(class_10142.field_53864);
               float currentLineWidth = -1.0F;
               class_289 tessellator = null;
               class_287 buffer = null;
               boolean hasVisibleBoxes = false;

               for(K1e8d44c34b687dbad5 b : this.outlinedBoxes) {
                  double minX = b.pos.field_1352;
                  double minY = b.pos.field_1351;
                  double minZ = b.pos.field_1350;
                  double maxX = minX + b.params.field_1352;
                  double maxY = minY + b.params.field_1351;
                  double maxZ = minZ + b.params.field_1350;
                  double centerX = (minX + maxX) * (double)0.5F;
                  double centerY = (minY + maxY) * (double)0.5F;
                  double centerZ = (minZ + maxZ) * (double)0.5F;
                  double dx = cameraPos.field_1352 - centerX;
                  double dy = cameraPos.field_1351 - centerY;
                  double dz = cameraPos.field_1350 - centerZ;
                  double distSq = dx * dx + dy * dy + dz * dz;
                  double renderDistSq = (double)((Integer)mc.field_1690.method_42503().method_41753() * 16 * 16);
                  if (!(distSq > renderDistSq) && frustum.method_23093(new class_238(minX, minY, minZ, maxX, maxY, maxZ))) {
                     if (Math.abs(currentLineWidth - b.lineWidth) > 0.001F) {
                        if (buffer != null && hasVisibleBoxes) {
                           class_286.method_43433(buffer.method_60800());
                           hasVisibleBoxes = false;
                        }

                        RenderSystem.lineWidth(b.lineWidth);
                        currentLineWidth = b.lineWidth;
                        tessellator = class_289.method_1348();
                        buffer = tessellator.method_60827(class_5596.field_27377, class_290.field_29337);
                     }

                     if (buffer == null) {
                        tessellator = class_289.method_1348();
                        buffer = tessellator.method_60827(class_5596.field_27377, class_290.field_29337);
                     }

                     this.K91334f568bfb93c478(b.pos, b.params, renderStack, buffer, b.color);
                     hasVisibleBoxes = true;
                  }
               }

               if (buffer != null && hasVisibleBoxes) {
                  class_286.method_43433(buffer.method_60800());
               }

               this.Ke6e60bd0fdd0d6ea5b();
            }

            if (!this.stripedBoxes.isEmpty()) {
               this.K86cfe705adc4af9794();
               RenderSystem.setShader(class_10142.field_53864);
               float currentLineWidth = -1.0F;
               class_289 tessellator = null;
               class_287 buffer = null;
               boolean hasVisibleBoxes = false;

               for(Kaa053b0af66ed49864 b : this.stripedBoxes) {
                  double minX = b.pos.field_1352;
                  double minY = b.pos.field_1351;
                  double minZ = b.pos.field_1350;
                  double maxX = minX + b.params.field_1352;
                  double maxY = minY + b.params.field_1351;
                  double maxZ = minZ + b.params.field_1350;
                  double centerX = (minX + maxX) * (double)0.5F;
                  double centerY = (minY + maxY) * (double)0.5F;
                  double centerZ = (minZ + maxZ) * (double)0.5F;
                  double dx = cameraPos.field_1352 - centerX;
                  double dy = cameraPos.field_1351 - centerY;
                  double dz = cameraPos.field_1350 - centerZ;
                  double distSq = dx * dx + dy * dy + dz * dz;
                  double renderDistSq = (double)((Integer)mc.field_1690.method_42503().method_41753() * 16 * 16);
                  if (!(distSq > renderDistSq) && frustum.method_23093(new class_238(minX, minY, minZ, maxX, maxY, maxZ))) {
                     if (Math.abs(currentLineWidth - b.lineWidth) > 0.001F) {
                        if (buffer != null && hasVisibleBoxes) {
                           class_286.method_43433(buffer.method_60800());
                           hasVisibleBoxes = false;
                        }

                        RenderSystem.lineWidth(b.lineWidth);
                        currentLineWidth = b.lineWidth;
                        tessellator = class_289.method_1348();
                        buffer = tessellator.method_60827(class_5596.field_27377, class_290.field_29337);
                     }

                     if (buffer == null) {
                        tessellator = class_289.method_1348();
                        buffer = tessellator.method_60827(class_5596.field_27377, class_290.field_29337);
                     }

                     this.Kb96ac1caac61b77a60(b.pos, b.params, renderStack, buffer, b.color, b.gapDistance);
                     hasVisibleBoxes = true;
                  }
               }

               if (buffer != null && hasVisibleBoxes) {
                  class_286.method_43433(buffer.method_60800());
               }

               this.Ke6e60bd0fdd0d6ea5b();
            }

            this.filledBoxes.clear();
            this.outlinedBoxes.clear();
            this.stripedBoxes.clear();
         }
      }
   }

   private void K86cfe705adc4af9794() {
      if (!this.lastBlendEnabled) {
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         this.lastBlendEnabled = true;
      }

      if (this.lastCullEnabled) {
         RenderSystem.disableCull();
         this.lastCullEnabled = false;
      }

      if (this.lastDepthTestEnabled) {
         RenderSystem.disableDepthTest();
         this.lastDepthTestEnabled = false;
      }

      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void Ke6e60bd0fdd0d6ea5b() {
      if (!this.lastCullEnabled) {
         RenderSystem.enableCull();
         this.lastCullEnabled = true;
      }

      if (!this.lastDepthTestEnabled) {
         RenderSystem.enableDepthTest();
         this.lastDepthTestEnabled = true;
      }

      if (this.lastBlendEnabled) {
         RenderSystem.disableBlend();
         this.lastBlendEnabled = false;
      }

   }

   private void K442cf4bd1f953d95d0(class_243 pos, class_243 params, class_4587 matrices, class_287 buffer, Color color) {
      class_243 cam = mc.field_1773.method_19418().method_19326();
      float x1 = (float)(pos.field_1352 - cam.field_1352);
      float y1 = (float)(pos.field_1351 - cam.field_1351);
      float z1 = (float)(pos.field_1350 - cam.field_1350);
      float x2 = x1 + (float)params.field_1352;
      float y2 = y1 + (float)params.field_1351;
      float z2 = z1 + (float)params.field_1350;
      this.Kda64f90a620502ef35(matrices, buffer, x1, y1, z1, x2, y1, z1, x2, y2, z1, x1, y2, z1, color);
      this.Kda64f90a620502ef35(matrices, buffer, x1, y1, z2, x2, y1, z2, x2, y2, z2, x1, y2, z2, color);
      this.Kda64f90a620502ef35(matrices, buffer, x1, y1, z1, x1, y1, z2, x1, y2, z2, x1, y2, z1, color);
      this.Kda64f90a620502ef35(matrices, buffer, x2, y1, z1, x2, y1, z2, x2, y2, z2, x2, y2, z1, color);
      this.Kda64f90a620502ef35(matrices, buffer, x1, y1, z1, x1, y1, z2, x2, y1, z2, x2, y1, z1, color);
      this.Kda64f90a620502ef35(matrices, buffer, x1, y2, z1, x1, y2, z2, x2, y2, z2, x2, y2, z1, color);
   }

   private void K91334f568bfb93c478(class_243 pos, class_243 params, class_4587 matrices, class_287 buffer, Color color) {
      class_243 cam = mc.field_1773.method_19418().method_19326();
      float x1 = (float)(pos.field_1352 - cam.field_1352);
      float y1 = (float)(pos.field_1351 - cam.field_1351);
      float z1 = (float)(pos.field_1350 - cam.field_1350);
      float x2 = x1 + (float)params.field_1352;
      float y2 = y1 + (float)params.field_1351;
      float z2 = z1 + (float)params.field_1350;
      this.K20c5c0fdb490c01b51(matrices, buffer, x1, y1, z1, x2, y1, z1, color);
      this.K20c5c0fdb490c01b51(matrices, buffer, x2, y1, z1, x2, y1, z2, color);
      this.K20c5c0fdb490c01b51(matrices, buffer, x2, y1, z2, x1, y1, z2, color);
      this.K20c5c0fdb490c01b51(matrices, buffer, x1, y1, z2, x1, y1, z1, color);
      this.K20c5c0fdb490c01b51(matrices, buffer, x1, y2, z1, x2, y2, z1, color);
      this.K20c5c0fdb490c01b51(matrices, buffer, x2, y2, z1, x2, y2, z2, color);
      this.K20c5c0fdb490c01b51(matrices, buffer, x2, y2, z2, x1, y2, z2, color);
      this.K20c5c0fdb490c01b51(matrices, buffer, x1, y2, z2, x1, y2, z1, color);
      this.K20c5c0fdb490c01b51(matrices, buffer, x1, y1, z1, x1, y2, z1, color);
      this.K20c5c0fdb490c01b51(matrices, buffer, x2, y1, z1, x2, y2, z1, color);
      this.K20c5c0fdb490c01b51(matrices, buffer, x1, y1, z2, x1, y2, z2, color);
      this.K20c5c0fdb490c01b51(matrices, buffer, x2, y1, z2, x2, y2, z2, color);
   }

   private void Kb96ac1caac61b77a60(class_243 pos, class_243 params, class_4587 matrices, class_287 buffer, Color color, float gap) {
      class_243 cam = mc.field_1773.method_19418().method_19326();
      float x1 = (float)(pos.field_1352 - cam.field_1352);
      float y1 = (float)(pos.field_1351 - cam.field_1351);
      float z1 = (float)(pos.field_1350 - cam.field_1350);
      float x2 = x1 + (float)params.field_1352;
      float y2 = y1 + (float)params.field_1351;
      float z2 = z1 + (float)params.field_1350;
      this.K51c67df7b2d252bfc6(matrices, buffer, color, x1, y1, z1, x2, y1, z1, gap, gap);
      this.K51c67df7b2d252bfc6(matrices, buffer, color, x2, y1, z1, x2, y1, z2, gap, gap);
      this.K51c67df7b2d252bfc6(matrices, buffer, color, x2, y1, z2, x1, y1, z2, gap, gap);
      this.K51c67df7b2d252bfc6(matrices, buffer, color, x1, y1, z2, x1, y1, z1, gap, gap);
      this.K51c67df7b2d252bfc6(matrices, buffer, color, x1, y2, z1, x2, y2, z1, gap, gap);
      this.K51c67df7b2d252bfc6(matrices, buffer, color, x2, y2, z1, x2, y2, z2, gap, gap);
      this.K51c67df7b2d252bfc6(matrices, buffer, color, x2, y2, z2, x1, y2, z2, gap, gap);
      this.K51c67df7b2d252bfc6(matrices, buffer, color, x1, y2, z2, x1, y2, z1, gap, gap);
      this.K51c67df7b2d252bfc6(matrices, buffer, color, x1, y1, z1, x1, y2, z1, gap, gap);
      this.K51c67df7b2d252bfc6(matrices, buffer, color, x2, y1, z1, x2, y2, z1, gap, gap);
      this.K51c67df7b2d252bfc6(matrices, buffer, color, x1, y1, z2, x1, y2, z2, gap, gap);
      this.K51c67df7b2d252bfc6(matrices, buffer, color, x2, y1, z2, x2, y2, z2, gap, gap);
   }

   private void K51c67df7b2d252bfc6(class_4587 matrices, class_287 buffer, Color color, float x1, float y1, float z1, float x2, float y2, float z2, float dash, float gap) {
      float dx = x2 - x1;
      float dy = y2 - y1;
      float dz = z2 - z1;
      float len = class_3532.method_15355(dx * dx + dy * dy + dz * dz);
      if (!(len <= 0.0F)) {
         float step = (dash + gap) / len;
         float dashPercent = dash / len;

         for(float t = 0.0F; t < 1.0F; t += step) {
            float end = Math.min(t + dashPercent, 1.0F);
            this.K20c5c0fdb490c01b51(matrices, buffer, x1 + dx * t, y1 + dy * t, z1 + dz * t, x1 + dx * end, y1 + dy * end, z1 + dz * end, color);
         }

      }
   }

   private void K20c5c0fdb490c01b51(class_4587 matrices, class_4588 buffer, float x1, float y1, float z1, float x2, float y2, float z2, Color c) {
      Matrix4f model = matrices.method_23760().method_23761();
      buffer.method_22918(model, x1, y1, z1).method_1336(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).method_22914(0.0F, 1.0F, 0.0F);
      buffer.method_22918(model, x2, y2, z2).method_1336(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).method_22914(0.0F, 1.0F, 0.0F);
   }

   private void Kda64f90a620502ef35(class_4587 matrices, class_4588 buffer, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4, Color c) {
      Matrix4f model = matrices.method_23760().method_23761();
      buffer.method_22918(model, x1, y1, z1).method_1336(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).method_22914(0.0F, 1.0F, 0.0F);
      buffer.method_22918(model, x2, y2, z2).method_1336(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).method_22914(0.0F, 1.0F, 0.0F);
      buffer.method_22918(model, x3, y3, z3).method_1336(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).method_22914(0.0F, 1.0F, 0.0F);
      buffer.method_22918(model, x4, y4, z4).method_1336(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).method_22914(0.0F, 1.0F, 0.0F);
   }

   public void drawBox(float x1, float y1, float z1, float x2, float y2, float z2, float lineWidth, Color color, Kdbe29c088d9ae5b9a4 mode, float gap) {
      class_243 pos = new class_243((double)x1, (double)y1, (double)z1);
      class_243 size = new class_243((double)(x2 - x1), (double)(y2 - y1), (double)(z2 - z1));
      if (mode == Kdbe29c088d9ae5b9a4.FILL) {
         this.filledBoxes.add(new K8b7f14243faf5d42bc(pos, size, color));
      } else if (mode == Kdbe29c088d9ae5b9a4.OUTLINE) {
         this.outlinedBoxes.add(new K1e8d44c34b687dbad5(pos, size, lineWidth, color));
      } else if (mode == Kdbe29c088d9ae5b9a4.STRIPED) {
         this.stripedBoxes.add(new Kaa053b0af66ed49864(pos, size, lineWidth, color, gap));
      }

   }
}
