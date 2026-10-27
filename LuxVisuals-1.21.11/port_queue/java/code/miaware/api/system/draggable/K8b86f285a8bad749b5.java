package code.miaware.api.system.draggable;

import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.other.K855ca916f0dcdcda01;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Generated;

public class K8b86f285a8bad749b5 implements K4600bef438dafc1a9c {
   @Expose
   @SerializedName("x")
   private float x;
   @Expose
   @SerializedName("y")
   private float y;
   public float initialXVal;
   public float initialYVal;
   private float startX;
   private float startY;
   private boolean dragging;
   private float width = 0.0F;
   private float height = 0.0F;
   @Expose
   @SerializedName("scale")
   private float widgetScale = 1.0F;
   @Expose
   @SerializedName("name")
   private final String name;
   private final Kab8a8c478f7d0077cd module;

   public void setWidgetScale(float scale) {
      this.widgetScale = Float.isFinite(scale) && scale > 0.0F ? Math.max(0.5F, Math.min(2.5F, scale)) : 1.0F;
   }

   public void resizeWhileDragging(float ratio) {
      if (this.dragging && Float.isFinite(ratio) && !(ratio <= 0.0F)) {
         this.width *= ratio;
         this.height *= ratio;
         this.startX *= ratio;
         this.startY *= ratio;
         this.x = this.K0d3d7f24f1f0610a33((float)this.normaliseX() - this.startX);
         this.y = this.K0d3d7f24f1f0610a33((float)this.normaliseY() - this.startY);
         this.K26c5f20e3c37047dd7();
      }
   }

   public K8b86f285a8bad749b5(Kab8a8c478f7d0077cd module, String name, float initialXVal, float initialYVal) {
      this.module = module;
      this.name = name;
      this.x = this.K0d3d7f24f1f0610a33(initialXVal);
      this.y = this.K0d3d7f24f1f0610a33(initialYVal);
      this.initialXVal = initialXVal;
      this.initialYVal = initialYVal;
      K855ca916f0dcdcda01.getInstance().subscribe(new Ka68e0b23b1084c01e4(-1, (event) -> {
         if (this.dragging) {
            this.K26c5f20e3c37047dd7();
         }

      }));
   }

   public final void onDraw() {
      if (this.dragging) {
         this.x = this.K0d3d7f24f1f0610a33(Ka0484a23398fb5f9ac.interpolate(this.x, (float)this.normaliseX() - this.startX, 0.15F));
         this.y = this.K0d3d7f24f1f0610a33(Ka0484a23398fb5f9ac.interpolate(this.y, (float)this.normaliseY() - this.startY, 0.15F));
         this.K26c5f20e3c37047dd7();
      }

   }

   public final void onClick(int button) {
      if (button == 0 && this.isHovering()) {
         boolean anotherDragging = Kd8969ae8f12d70edf3.getInstance().getDraggables().values().stream().anyMatch(K8b86f285a8bad749b5::isDragging);
         if (!anotherDragging) {
            this.dragging = true;
            this.startX = (float)((int)((float)this.normaliseX() - this.x));
            this.startY = (float)((int)((float)this.normaliseY() - this.y));
         }
      }

   }

   public final void onRelease(int button) {
      if (button == 0 && this.dragging) {
         this.dragging = false;
         Kd8969ae8f12d70edf3.getInstance().save();
      }

   }

   public boolean isHovering() {
      return (float)this.normaliseX() > Math.min(this.x, this.x + this.width) && (float)this.normaliseX() < Math.max(this.x, this.x + this.width) && (float)this.normaliseY() > Math.min(this.y, this.y + this.height) && (float)this.normaliseY() < Math.max(this.y, this.y + this.height);
   }

   public int normaliseX() {
      return (int)(mc.field_1729.method_1603() / mc.method_22683().method_4495());
   }

   public int normaliseY() {
      return (int)(mc.field_1729.method_1604() / mc.method_22683().method_4495());
   }

   private float K0d3d7f24f1f0610a33(float value) {
      return (float)Math.round(value * 2.0F) / 2.0F;
   }

   private void K26c5f20e3c37047dd7() {
      float margin = 3.0F;
      float screenWidth = (float)mc.method_22683().method_4486();
      float screenHeight = (float)mc.method_22683().method_4502();
      this.x = Math.max(margin, Math.min(this.x, Math.max(margin, screenWidth - this.width - margin)));
      this.y = Math.max(margin, Math.min(this.y, Math.max(margin, screenHeight - this.height - margin)));
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
   public float getInitialXVal() {
      return this.initialXVal;
   }

   @Generated
   public float getInitialYVal() {
      return this.initialYVal;
   }

   @Generated
   public float getStartX() {
      return this.startX;
   }

   @Generated
   public float getStartY() {
      return this.startY;
   }

   @Generated
   public boolean isDragging() {
      return this.dragging;
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
   public float getWidgetScale() {
      return this.widgetScale;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public Kab8a8c478f7d0077cd getModule() {
      return this.module;
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
   public void setInitialXVal(float initialXVal) {
      this.initialXVal = initialXVal;
   }

   @Generated
   public void setInitialYVal(float initialYVal) {
      this.initialYVal = initialYVal;
   }

   @Generated
   public void setStartX(float startX) {
      this.startX = startX;
   }

   @Generated
   public void setStartY(float startY) {
      this.startY = startY;
   }

   @Generated
   public void setDragging(boolean dragging) {
      this.dragging = dragging;
   }

   @Generated
   public void setWidth(float width) {
      this.width = width;
   }

   @Generated
   public void setHeight(float height) {
      this.height = height;
   }
}
