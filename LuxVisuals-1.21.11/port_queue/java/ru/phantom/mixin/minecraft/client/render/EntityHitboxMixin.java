package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I5_i4.I1_lIliilII_iI1_I1__ilI;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_10142;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_898;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_898.class})
public class EntityHitboxMixin {
   @Inject(
      method = {"method_3956"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void renderCustomHitbox(class_4587 matrices, class_4588 vertices, class_1297 entity, float red, float green, float blue, float alpha, CallbackInfo ci) {
      I1_lIliilII_iI1_I1__ilI hitboxCustomizer = I1_lIliilII_iI1_I1__ilI.li1iii_li1I1i11iliillI__iiil1();
      if (hitboxCustomizer != null && hitboxCustomizer.isEnable()) {
         class_310 client = class_310.method_1551();
         if (client.field_1724 == null || !client.field_1724.method_6059(class_1294.field_5919)) {
            class_238 boxOffset = entity.method_5829().method_989(-entity.method_23317(), -entity.method_23318(), -entity.method_23321());
            if (hitboxCustomizer.shouldFill()) {
               renderFilledBox(matrices, boxOffset, hitboxCustomizer);
            }

            renderHitboxOutline(matrices, vertices, boxOffset, hitboxCustomizer);
            if (hitboxCustomizer.shouldRenderEyeLines() && entity instanceof class_1309) {
               renderEyeLine(matrices, vertices, entity, 1.0F, hitboxCustomizer);
            }

            ci.cancel();
         }

      }
   }

   private static void renderFilledBox(class_4587 matrices, class_238 box, I1_lIliilII_iI1_I1__ilI hitboxCustomizer) {
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.setShader(class_10142.field_53876);
      RenderSystem.enableDepthTest();
      RenderSystem.depthFunc(515);
      RenderSystem.depthMask(false);
      RenderSystem.disableCull();
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);
      Color color = hitboxCustomizer.getHitboxColor();
      int r = color.getRed();
      int g = color.getGreen();
      int b = color.getBlue();
      Matrix4f matrix = matrices.method_23760().method_23761();
      float minX = (float)box.field_1323;
      float minY = (float)box.field_1322;
      float minZ = (float)box.field_1321;
      float maxX = (float)box.field_1320;
      float maxY = (float)box.field_1325;
      float maxZ = (float)box.field_1324;
      buffer.method_22918(matrix, minX, minY, minZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, minX, maxY, minZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, maxX, maxY, minZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, maxX, minY, minZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, maxX, minY, maxZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, maxX, maxY, maxZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, minX, maxY, maxZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, minX, minY, maxZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, minX, minY, minZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, minX, minY, maxZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, minX, maxY, maxZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, minX, maxY, minZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, maxX, maxY, minZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, maxX, maxY, maxZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, maxX, minY, maxZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, maxX, minY, minZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, minX, minY, minZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, maxX, minY, minZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, maxX, minY, maxZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, minX, minY, maxZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, minX, maxY, maxZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, maxX, maxY, maxZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, maxX, maxY, minZ).method_1336(r, g, b, 64);
      buffer.method_22918(matrix, minX, maxY, minZ).method_1336(r, g, b, 64);
      RenderSystem.enablePolygonOffset();
      RenderSystem.polygonOffset(-1.0F, -10.0F);
      class_286.method_43433(buffer.method_60800());
      RenderSystem.disablePolygonOffset();
      RenderSystem.depthMask(true);
      RenderSystem.enableCull();
      RenderSystem.disableBlend();
   }

   private static void renderHitboxOutline(class_4587 matrices, class_4588 vertexConsumer, class_238 box, I1_lIliilII_iI1_I1__ilI hitboxCustomizer) {
      if (hitboxCustomizer.isCornerMode()) {
         renderCornerOutline(matrices, vertexConsumer, box, hitboxCustomizer);
      } else {
         renderFullOutline(matrices, vertexConsumer, box, hitboxCustomizer);
      }

   }

   private static void renderFullOutline(class_4587 matrices, class_4588 vertexConsumer, class_238 box, I1_lIliilII_iI1_I1__ilI hitboxCustomizer) {
      class_4587.class_4665 entry = matrices.method_23760();
      Color color = hitboxCustomizer.getHitboxColor();
      float r = (float)color.getRed() / 255.0F;
      float g = (float)color.getGreen() / 255.0F;
      float b = (float)color.getBlue() / 255.0F;
      vertexConsumer.method_56824(entry, (float)box.field_1323, (float)box.field_1322, (float)box.field_1321).method_22915(r, g, b, 1.0F).method_60831(entry, 1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1320, (float)box.field_1322, (float)box.field_1321).method_22915(r, g, b, 1.0F).method_60831(entry, 1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1320, (float)box.field_1322, (float)box.field_1321).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 1.0F, 0.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1320, (float)box.field_1325, (float)box.field_1321).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 1.0F, 0.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1320, (float)box.field_1325, (float)box.field_1321).method_22915(r, g, b, 1.0F).method_60831(entry, -1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1323, (float)box.field_1325, (float)box.field_1321).method_22915(r, g, b, 1.0F).method_60831(entry, -1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1323, (float)box.field_1325, (float)box.field_1321).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, -1.0F, 0.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1323, (float)box.field_1322, (float)box.field_1321).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, -1.0F, 0.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1323, (float)box.field_1322, (float)box.field_1324).method_22915(r, g, b, 1.0F).method_60831(entry, 1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1320, (float)box.field_1322, (float)box.field_1324).method_22915(r, g, b, 1.0F).method_60831(entry, 1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1320, (float)box.field_1322, (float)box.field_1324).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 1.0F, 0.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1320, (float)box.field_1325, (float)box.field_1324).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 1.0F, 0.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1320, (float)box.field_1325, (float)box.field_1324).method_22915(r, g, b, 1.0F).method_60831(entry, -1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1323, (float)box.field_1325, (float)box.field_1324).method_22915(r, g, b, 1.0F).method_60831(entry, -1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1323, (float)box.field_1325, (float)box.field_1324).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, -1.0F, 0.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1323, (float)box.field_1322, (float)box.field_1324).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, -1.0F, 0.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1323, (float)box.field_1322, (float)box.field_1321).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, 1.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1323, (float)box.field_1322, (float)box.field_1324).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, 1.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1320, (float)box.field_1322, (float)box.field_1321).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, 1.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1320, (float)box.field_1322, (float)box.field_1324).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, 1.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1320, (float)box.field_1325, (float)box.field_1321).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, 1.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1320, (float)box.field_1325, (float)box.field_1324).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, 1.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1323, (float)box.field_1325, (float)box.field_1321).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, 1.0F);
      vertexConsumer.method_56824(entry, (float)box.field_1323, (float)box.field_1325, (float)box.field_1324).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, 1.0F);
   }

   private static void renderCornerOutline(class_4587 matrices, class_4588 vertexConsumer, class_238 box, I1_lIliilII_iI1_I1__ilI hitboxCustomizer) {
      class_4587.class_4665 entry = matrices.method_23760();
      Color color = hitboxCustomizer.getHitboxColor();
      float r = (float)color.getRed() / 255.0F;
      float g = (float)color.getGreen() / 255.0F;
      float b = (float)color.getBlue() / 255.0F;
      float length = hitboxCustomizer.getCornerLength();
      float minX = (float)box.field_1323;
      float minY = (float)box.field_1322;
      float minZ = (float)box.field_1321;
      float maxX = (float)box.field_1320;
      float maxY = (float)box.field_1325;
      float maxZ = (float)box.field_1324;
      vertexConsumer.method_56824(entry, minX, minY, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, 1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, minX + length, minY, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, 1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, minX, minY, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 1.0F, 0.0F);
      vertexConsumer.method_56824(entry, minX, minY + length, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 1.0F, 0.0F);
      vertexConsumer.method_56824(entry, minX, minY, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, 1.0F);
      vertexConsumer.method_56824(entry, minX, minY, minZ + length).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, 1.0F);
      vertexConsumer.method_56824(entry, maxX, minY, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, -1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, maxX - length, minY, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, -1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, maxX, minY, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 1.0F, 0.0F);
      vertexConsumer.method_56824(entry, maxX, minY + length, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 1.0F, 0.0F);
      vertexConsumer.method_56824(entry, maxX, minY, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, 1.0F);
      vertexConsumer.method_56824(entry, maxX, minY, minZ + length).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, 1.0F);
      vertexConsumer.method_56824(entry, minX, maxY, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, 1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, minX + length, maxY, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, 1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, minX, maxY, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, -1.0F, 0.0F);
      vertexConsumer.method_56824(entry, minX, maxY - length, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, -1.0F, 0.0F);
      vertexConsumer.method_56824(entry, minX, maxY, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, 1.0F);
      vertexConsumer.method_56824(entry, minX, maxY, minZ + length).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, 1.0F);
      vertexConsumer.method_56824(entry, maxX, maxY, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, -1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, maxX - length, maxY, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, -1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, maxX, maxY, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, -1.0F, 0.0F);
      vertexConsumer.method_56824(entry, maxX, maxY - length, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, -1.0F, 0.0F);
      vertexConsumer.method_56824(entry, maxX, maxY, minZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, 1.0F);
      vertexConsumer.method_56824(entry, maxX, maxY, minZ + length).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, 1.0F);
      vertexConsumer.method_56824(entry, minX, minY, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, 1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, minX + length, minY, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, 1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, minX, minY, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 1.0F, 0.0F);
      vertexConsumer.method_56824(entry, minX, minY + length, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 1.0F, 0.0F);
      vertexConsumer.method_56824(entry, minX, minY, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, -1.0F);
      vertexConsumer.method_56824(entry, minX, minY, maxZ - length).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, -1.0F);
      vertexConsumer.method_56824(entry, maxX, minY, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, -1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, maxX - length, minY, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, -1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, maxX, minY, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 1.0F, 0.0F);
      vertexConsumer.method_56824(entry, maxX, minY + length, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 1.0F, 0.0F);
      vertexConsumer.method_56824(entry, maxX, minY, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, -1.0F);
      vertexConsumer.method_56824(entry, maxX, minY, maxZ - length).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, -1.0F);
      vertexConsumer.method_56824(entry, minX, maxY, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, 1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, minX + length, maxY, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, 1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, minX, maxY, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, -1.0F, 0.0F);
      vertexConsumer.method_56824(entry, minX, maxY - length, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, -1.0F, 0.0F);
      vertexConsumer.method_56824(entry, minX, maxY, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, -1.0F);
      vertexConsumer.method_56824(entry, minX, maxY, maxZ - length).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, -1.0F);
      vertexConsumer.method_56824(entry, maxX, maxY, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, -1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, maxX - length, maxY, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, -1.0F, 0.0F, 0.0F);
      vertexConsumer.method_56824(entry, maxX, maxY, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, -1.0F, 0.0F);
      vertexConsumer.method_56824(entry, maxX, maxY - length, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, -1.0F, 0.0F);
      vertexConsumer.method_56824(entry, maxX, maxY, maxZ).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, -1.0F);
      vertexConsumer.method_56824(entry, maxX, maxY, maxZ - length).method_22915(r, g, b, 1.0F).method_60831(entry, 0.0F, 0.0F, -1.0F);
   }

   private static void renderEyeLine(class_4587 matrices, class_4588 vertexConsumer, class_1297 entity, float tickDelta, I1_lIliilII_iI1_I1__ilI hitboxCustomizer) {
      class_4587.class_4665 entry = matrices.method_23760();
      class_243 eyeOffset = new class_243((double)0.0F, (double)entity.method_18381(entity.method_18376()), (double)0.0F);
      class_243 rotationVec = entity.method_5828(tickDelta);
      class_243 end = eyeOffset.method_1019(rotationVec.method_1021((double)2.0F));
      Color color = hitboxCustomizer.getHitboxColor();
      float r = (float)color.getRed() / 255.0F;
      float g = (float)color.getGreen() / 255.0F;
      float b = (float)color.getBlue() / 255.0F;
      vertexConsumer.method_56824(entry, (float)eyeOffset.field_1352, (float)eyeOffset.field_1351, (float)eyeOffset.field_1350).method_22915(r, g, b, 1.0F).method_60831(entry, (float)rotationVec.field_1352, (float)rotationVec.field_1351, (float)rotationVec.field_1350);
      vertexConsumer.method_56824(entry, (float)end.field_1352, (float)end.field_1351, (float)end.field_1350).method_22915(r, g, b, 1.0F).method_60831(entry, (float)rotationVec.field_1352, (float)rotationVec.field_1351, (float)rotationVec.field_1350);
   }
}
