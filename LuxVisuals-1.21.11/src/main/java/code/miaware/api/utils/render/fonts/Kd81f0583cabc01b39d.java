package code.miaware.api.utils.render.fonts;

import code.miaware.api.utils.render.Ke8433384857ddcab3d;
import net.minecraft.class_4588;
import org.joml.Matrix4f;

public final class Kd81f0583cabc01b39d {
   private final int code;
   private final float minU;
   private final float maxU;
   private final float minV;
   private final float maxV;
   private final float advance;
   private final float topPosition;
   private final float width;
   private final float height;

   public Kd81f0583cabc01b39d(Kd3aa729b1195fc158b data, float atlasWidth, float atlasHeight) {
      this.code = data.unicode();
      this.advance = data.advance();
      Kc455a0ec93f78182f6 atlasBounds = data.atlasBounds();
      if (atlasBounds != null) {
         this.minU = atlasBounds.left() / atlasWidth;
         this.maxU = atlasBounds.right() / atlasWidth;
         this.minV = 1.0F - atlasBounds.top() / atlasHeight;
         this.maxV = 1.0F - atlasBounds.bottom() / atlasHeight;
      } else {
         this.minU = this.maxU = this.minV = this.maxV = 0.0F;
      }

      Kc455a0ec93f78182f6 planeBounds = data.planeBounds();
      if (planeBounds != null) {
         this.width = planeBounds.right() - planeBounds.left();
         this.height = planeBounds.top() - planeBounds.bottom();
         this.topPosition = planeBounds.top();
      } else {
         this.width = this.height = this.topPosition = 0.0F;
      }

   }

   public float apply(Matrix4f matrix, class_4588 consumer, float size, float x, float y, float z, int color) {
      y -= this.topPosition * size;
      float width = this.width * size;
      float height = this.height * size;
      Ke8433384857ddcab3d.record(matrix, x, y, width, height);
      consumer.method_22918(matrix, x, y, z).method_22913(this.minU, this.minV).method_39415(color);
      consumer.method_22918(matrix, x, y + height, z).method_22913(this.minU, this.maxV).method_39415(color);
      consumer.method_22918(matrix, x + width, y + height, z).method_22913(this.maxU, this.maxV).method_39415(color);
      consumer.method_22918(matrix, x + width, y, z).method_22913(this.maxU, this.minV).method_39415(color);
      return this.advance * size;
   }

   public float getWidth(float size) {
      return this.advance * size;
   }

   public int getCharCode() {
      return this.code;
   }

   public float getQuadWidth() {
      return this.width;
   }

   public float getQuadHeight() {
      return this.height;
   }

   public float getTopPosition() {
      return this.topPosition;
   }
}
