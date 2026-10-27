package code.miaware.api.utils.render.display;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.render.Ke8433384857ddcab3d;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_10142;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

public class K9a2b4acec2c36f99d1 implements K4600bef438dafc1a9c {
   private final List<Kde3fdd3419b9b4724f> lines = new ArrayList();
   private static K9a2b4acec2c36f99d1 instance;

   public void drawLine(class_243 from, class_243 to, int color, float width, boolean depthTest) {
      Color c = new Color(color, true);
      this.lines.add(new Kde3fdd3419b9b4724f(from, to, width, c));
   }

   public void setup3DRender(class_4587 matrixStack) {
      if (!this.lines.isEmpty() && mc.field_1687 != null && mc.field_1773 != null) {
         class_4184 camera = mc.field_1773.method_19418();
         class_243 cameraPos = camera.method_19326();
         RenderSystem.enableBlend();
         Ke8433384857ddcab3d.defaultBlendFunc();
         RenderSystem.disableCull();
         RenderSystem.disableDepthTest();
         RenderSystem.setShader(class_10142.field_53864);
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         float currentLineWidth = -1.0F;
         class_289 tessellator = null;
         class_287 buffer = null;
         boolean hasLines = false;

         for(Kde3fdd3419b9b4724f line : this.lines) {
            if (Math.abs(currentLineWidth - line.width) > 0.001F) {
               if (buffer != null && hasLines) {
                  class_286.method_43433(buffer.method_60800());
                  hasLines = false;
               }

               RenderSystem.lineWidth(line.width);
               currentLineWidth = line.width;
               tessellator = class_289.method_1348();
               buffer = tessellator.method_60827(class_5596.field_27377, class_290.field_29337);
            }

            if (buffer == null) {
               tessellator = class_289.method_1348();
               buffer = tessellator.method_60827(class_5596.field_27377, class_290.field_29337);
            }

            Matrix4f model = matrixStack.method_23760().method_23761();
            float x1 = (float)(line.from.field_1352 - cameraPos.field_1352);
            float y1 = (float)(line.from.field_1351 - cameraPos.field_1351);
            float z1 = (float)(line.from.field_1350 - cameraPos.field_1350);
            float x2 = (float)(line.to.field_1352 - cameraPos.field_1352);
            float y2 = (float)(line.to.field_1351 - cameraPos.field_1351);
            float z2 = (float)(line.to.field_1350 - cameraPos.field_1350);
            buffer.method_22918(model, x1, y1, z1).method_1336(line.color.getRed(), line.color.getGreen(), line.color.getBlue(), line.color.getAlpha()).method_22914(0.0F, 1.0F, 0.0F);
            buffer.method_22918(model, x2, y2, z2).method_1336(line.color.getRed(), line.color.getGreen(), line.color.getBlue(), line.color.getAlpha()).method_22914(0.0F, 1.0F, 0.0F);
            hasLines = true;
         }

         if (buffer != null && hasLines) {
            class_286.method_43433(buffer.method_60800());
         }

         RenderSystem.enableCull();
         RenderSystem.enableDepthTest();
         RenderSystem.disableBlend();
         this.lines.clear();
      }
   }

   public static K9a2b4acec2c36f99d1 getInstance() {
      if (instance == null) {
         instance = new K9a2b4acec2c36f99d1();
      }

      return instance;
   }
}
