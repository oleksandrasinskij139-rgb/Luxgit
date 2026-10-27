package code.miaware.client.ui;

import code.miaware.api.system.interfaces.K6ad25670efc66e08b8;
import code.miaware.client.services.Ke4708cf52c3b89f5ae;
import lombok.Generated;

public abstract class Kd6abad1c41237b249f implements K6ad25670efc66e08b8 {
   private float x;
   private float y;
   private float width;
   private float height;
   private float alpha;

   public float gap() {
      return this.scaled(2.0F);
   }

   public float offset() {
      return this.gap() * 1.5F;
   }

   public float scaled(float value) {
      return Ke4708cf52c3b89f5ae.getInstance().scaledUi(value);
   }

   public float getScale() {
      return Ke4708cf52c3b89f5ae.getInstance().getUiScale();
   }

   @Generated
   public float getX() {
      return this.x;
   }

   @Generated
   public float getY() {
      return this.y;
   }

   @Generated
   public float getWidth() {
      return this.width;
   }

   @Generated
   public float getHeight() {
      return this.height;
   }

   @Generated
   public float getAlpha() {
      return this.alpha;
   }

   @Generated
   public void setX(float x) {
      this.x = x;
   }

   @Generated
   public void setY(float y) {
      this.y = y;
   }

   @Generated
   public void setWidth(float width) {
      this.width = width;
   }

   @Generated
   public void setHeight(float height) {
      this.height = height;
   }

   @Generated
   public void setAlpha(float alpha) {
      this.alpha = alpha;
   }
}
