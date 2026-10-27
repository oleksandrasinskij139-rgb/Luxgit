package code.miaware.client.features.modules.render;

import code.miaware.client.features.modules.render.killeffect.K1037f35c571a07da2f;
import net.minecraft.class_243;

record K0736db54d4568050e4(class_243 position, K1037f35c571a07da2f style, int color, double height, double width, float yaw, long start, long lifetime) {
   boolean expired(long now) {
      return now - this.start >= this.lifetime;
   }

   double progress(long now) {
      return Math.max((double)0.0F, Math.min((double)1.0F, (double)(now - this.start) / (double)this.lifetime));
   }
}
