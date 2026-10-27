package code.miaware.api.utils.render;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.class_10142;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_293.class_5596;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

public class K357718bb1c119935c3 implements K4600bef438dafc1a9c {
   public final List<Kdbc539c8ead8296ff0> LINE_DEPTH = new ArrayList();
   public final List<Kdbc539c8ead8296ff0> LINE = new ArrayList();
   public final List<Kd39361cb00a49093cc> QUAD_DEPTH = new ArrayList();
   public final List<Kd39361cb00a49093cc> QUAD = new ArrayList();
   private final class_289 tessellator = class_289.method_1348();
   public class_4587.class_4665 lastWorldSpaceMatrix = (new class_4587()).method_23760();
   public class_243 savedCamPos;
   private static K357718bb1c119935c3 INSTANCE;

   public K357718bb1c119935c3() {
      this.savedCamPos = class_243.field_1353;
   }

   public static K357718bb1c119935c3 getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new K357718bb1c119935c3();
      }

      return INSTANCE;
   }

   public void onRender3D() {
      if (!this.LINE.isEmpty()) {
         GL11.glEnable(2848);
         Set<Float> widths = (Set)this.LINE.stream().map((line) -> line.width).collect(Collectors.toCollection(LinkedHashSet::new));
         RenderSystem.enableBlend();
         RenderSystem.disableCull();
         RenderSystem.disableDepthTest();
         RenderSystem.defaultBlendFunc();
         RenderSystem.setShader(class_10142.field_53864);
         widths.forEach((width) -> {
            RenderSystem.lineWidth(width);
            class_287 buffer = this.tessellator.method_60827(class_5596.field_27377, class_290.field_29337);
            this.LINE.stream().filter((line) -> line.width == width).forEach((line) -> this.vertexLine((class_4587.class_4665)line.entry, buffer, (Vector3f)line.start.method_46409(), (Vector3f)line.end.method_46409(), line.colorStart, line.colorEnd));
            class_286.method_43433(buffer.method_60800());
         });
         RenderSystem.enableDepthTest();
         RenderSystem.enableCull();
         RenderSystem.disableBlend();
         this.LINE.clear();
         GL11.glDisable(2848);
      }

      if (!this.QUAD.isEmpty()) {
         RenderSystem.enableBlend();
         RenderSystem.disableCull();
         RenderSystem.disableDepthTest();
         RenderSystem.defaultBlendFunc();
         RenderSystem.setShader(class_10142.field_53876);
         class_287 buffer = this.tessellator.method_60827(class_5596.field_27382, class_290.field_1576);
         this.QUAD.forEach((quad) -> this.vertexQuad(quad.entry, buffer, (class_243)quad.x, (class_243)quad.y, (class_243)quad.w, (class_243)quad.z, quad.color));
         class_286.method_43433(buffer.method_60800());
         RenderSystem.enableDepthTest();
         RenderSystem.enableCull();
         RenderSystem.disableBlend();
         this.QUAD.clear();
      }

      if (!this.LINE_DEPTH.isEmpty()) {
         GL11.glEnable(2848);
         Set<Float> widths = (Set)this.LINE_DEPTH.stream().map((line) -> line.width).collect(Collectors.toCollection(LinkedHashSet::new));
         RenderSystem.enableBlend();
         RenderSystem.disableCull();
         RenderSystem.enableDepthTest();
         RenderSystem.depthMask(false);
         RenderSystem.defaultBlendFunc();
         RenderSystem.setShader(class_10142.field_53864);
         widths.forEach((width) -> {
            RenderSystem.lineWidth(width);
            class_287 buffer = this.tessellator.method_60827(class_5596.field_27377, class_290.field_29337);
            this.LINE_DEPTH.stream().filter((line) -> line.width == width).forEach((line) -> this.vertexLine((class_4587.class_4665)line.entry, buffer, (Vector3f)line.start.method_46409(), (Vector3f)line.end.method_46409(), line.colorStart, line.colorEnd));
            class_286.method_43433(buffer.method_60800());
         });
         RenderSystem.depthMask(true);
         RenderSystem.enableCull();
         RenderSystem.disableBlend();
         this.LINE_DEPTH.clear();
         GL11.glDisable(2848);
      }

      if (!this.QUAD_DEPTH.isEmpty()) {
         RenderSystem.enableBlend();
         RenderSystem.disableCull();
         RenderSystem.enableDepthTest();
         RenderSystem.defaultBlendFunc();
         RenderSystem.setShader(class_10142.field_53876);
         class_287 buffer = this.tessellator.method_60827(class_5596.field_27382, class_290.field_1576);
         this.QUAD_DEPTH.forEach((quad) -> this.vertexQuad(quad.entry, buffer, (class_243)quad.x, (class_243)quad.y, (class_243)quad.w, (class_243)quad.z, quad.color));
         class_286.method_43433(buffer.method_60800());
         RenderSystem.enableCull();
         RenderSystem.disableBlend();
         this.QUAD_DEPTH.clear();
      }

   }

   public void vertexQuad(class_4587.class_4665 entry, class_4588 buffer, class_243 vec1, class_243 vec2, class_243 vec3, class_243 vec4, int color) {
      this.vertexQuad(entry, buffer, vec1.method_46409(), vec2.method_46409(), vec3.method_46409(), vec4.method_46409(), color);
   }

   public void vertexQuad(class_4587.class_4665 entry, class_4588 buffer, Vector3f vec1, Vector3f vec2, Vector3f vec3, Vector3f vec4, int color) {
      if (entry == null) {
         entry = this.lastWorldSpaceMatrix;
      }

      class_243 camPos = this.savedCamPos;
      Vector3f v1 = new Vector3f((float)((double)vec1.x() - camPos.field_1352), (float)((double)vec1.y() - camPos.field_1351), (float)((double)vec1.z() - camPos.field_1350));
      Vector3f v2 = new Vector3f((float)((double)vec2.x() - camPos.field_1352), (float)((double)vec2.y() - camPos.field_1351), (float)((double)vec2.z() - camPos.field_1350));
      Vector3f v3 = new Vector3f((float)((double)vec3.x() - camPos.field_1352), (float)((double)vec3.y() - camPos.field_1351), (float)((double)vec3.z() - camPos.field_1350));
      Vector3f v4 = new Vector3f((float)((double)vec4.x() - camPos.field_1352), (float)((double)vec4.y() - camPos.field_1351), (float)((double)vec4.z() - camPos.field_1350));
      buffer.method_61032(entry, v1).method_39415(color);
      buffer.method_61032(entry, v2).method_39415(color);
      buffer.method_61032(entry, v3).method_39415(color);
      buffer.method_61032(entry, v4).method_39415(color);
   }

   public void vertexLine(class_4587 matrices, class_4588 buffer, class_243 start, class_243 end, int startColor, int endColor) {
      this.vertexLine(matrices.method_23760(), buffer, start.method_46409(), end.method_46409(), startColor, endColor);
   }

   public void vertexLine(class_4587.class_4665 entry, class_4588 buffer, Vector3f start, Vector3f end, int startColor, int endColor) {
      if (entry == null) {
         entry = this.lastWorldSpaceMatrix;
      }

      class_243 camPos = this.savedCamPos;
      Vector3f adjustedStart = new Vector3f((float)((double)start.x() - camPos.field_1352), (float)((double)start.y() - camPos.field_1351), (float)((double)start.z() - camPos.field_1350));
      Vector3f adjustedEnd = new Vector3f((float)((double)end.x() - camPos.field_1352), (float)((double)end.y() - camPos.field_1351), (float)((double)end.z() - camPos.field_1350));
      Vector3f vec = this.getNormal(adjustedStart, adjustedEnd);
      buffer.method_61032(entry, adjustedStart).method_39415(startColor).method_61959(entry, vec);
      buffer.method_61032(entry, adjustedEnd).method_39415(endColor).method_61959(entry, vec);
   }

   public Vector3f getNormal(Vector3f start, Vector3f end) {
      Vector3f normal = (new Vector3f(start)).sub(end);
      float sqrt = class_3532.method_15355(normal.lengthSquared());
      return normal.div(sqrt);
   }

   public void drawLine(class_4587.class_4665 entry, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, int color, float width, boolean depth) {
      this.drawLine(entry, new class_243(minX, minY, minZ), new class_243(maxX, maxY, maxZ), color, color, width, depth);
   }

   public void drawLine(class_243 start, class_243 end, int color, float width, boolean depth) {
      this.drawLine((class_4587.class_4665)null, start, end, color, color, width, depth);
   }

   public void drawLine(class_4587.class_4665 entry, class_243 start, class_243 end, int colorStart, int colorEnd, float width, boolean depth) {
      Kdbc539c8ead8296ff0 line = new Kdbc539c8ead8296ff0(entry, start, end, colorStart, colorEnd, width);
      if (depth) {
         this.LINE_DEPTH.add(line);
      } else {
         this.LINE.add(line);
      }

   }

   public void drawQuad(class_4587.class_4665 entry, class_243 x, class_243 y, class_243 w, class_243 z, int color, boolean depth) {
      Kd39361cb00a49093cc quad = new Kd39361cb00a49093cc(entry, x, y, w, z, color);
      if (depth) {
         this.QUAD_DEPTH.add(quad);
      } else {
         this.QUAD.add(quad);
      }

   }
}
