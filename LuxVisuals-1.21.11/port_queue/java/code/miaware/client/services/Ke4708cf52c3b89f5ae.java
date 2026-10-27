package code.miaware.client.services;

import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.other.K855ca916f0dcdcda01;
import code.miaware.api.event.events.render.K14caf22f676a0204f7;
import code.miaware.api.event.events.render.Kb590f148cfda70c907;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.client.features.modules.render.K30b126617e809f138b;
import lombok.Generated;
import net.minecraft.class_3532;

public class Ke4708cf52c3b89f5ae implements K4600bef438dafc1a9c {
   private static final Ke4708cf52c3b89f5ae instance = new Ke4708cf52c3b89f5ae();
   private float hudScale = 1.0F;
   private float uiScale = 1.0F;
   private boolean renderListenerRegistered;
   private final Ka68e0b23b1084c01e4<Kb590f148cfda70c907> renderListener = new Ka68e0b23b1084c01e4<Kb590f148cfda70c907>((event) -> this.updateScale());

   public void load() {
      this.register();
      K855ca916f0dcdcda01.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> this.register()));
   }

   private void register() {
      if (!this.renderListenerRegistered) {
         K14caf22f676a0204f7.getInstance().subscribe(this.renderListener);
         this.renderListenerRegistered = true;
      }
   }

   public float scaled(float value) {
      return this.scaledUi(value);
   }

   public float scaledHud(float value) {
      return value * this.hudScale;
   }

   public float scaledUi(float value) {
      return value * this.uiScale;
   }

   public void updateScale() {
      if (mc != null && mc.method_22683() != null) {
         float w = (float)mc.method_22683().method_4486();
         float h = (float)mc.method_22683().method_4502();
         float widthFactor = w / 1366.0F;
         float heightFactor = h / 768.0F;
         float minFactor = Math.min(widthFactor, heightFactor);
         float blendedFactor = 0.9F + (minFactor - 1.0F) * 0.18F;
         float dynamicUiFactor = class_3532.method_15363(blendedFactor, 0.84F, 1.12F);
         float dynamicHudFactor = class_3532.method_15363(0.92F + (minFactor - 1.0F) * 0.14F, 0.86F, 1.08F);
         this.hudScale = dynamicHudFactor;
         this.uiScale = dynamicUiFactor * K30b126617e809f138b.getUiScale();
      }
   }

   public float getScale() {
      return this.uiScale;
   }

   public float getHudScale() {
      return this.hudScale;
   }

   public float getUiScale() {
      return this.uiScale;
   }

   @Generated
   public boolean isRenderListenerRegistered() {
      return this.renderListenerRegistered;
   }

   @Generated
   public Ka68e0b23b1084c01e4<Kb590f148cfda70c907> getRenderListener() {
      return this.renderListener;
   }

   @Generated
   public static Ke4708cf52c3b89f5ae getInstance() {
      return instance;
   }

   @Generated
   public void setHudScale(float hudScale) {
      this.hudScale = hudScale;
   }

   @Generated
   public void setUiScale(float uiScale) {
      this.uiScale = uiScale;
   }
}
