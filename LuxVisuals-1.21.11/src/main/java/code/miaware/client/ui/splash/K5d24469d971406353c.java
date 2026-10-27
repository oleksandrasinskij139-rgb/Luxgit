package code.miaware.client.ui.splash;

import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import net.minecraft.class_332;

class K5d24469d971406353c {
   float x;
   float y;
   float vx;
   float vy;
   float size;
   int alpha;
   // $FF: synthetic field
   final K0c57d339ab743d4837 this$0;

   K5d24469d971406353c(final K0c57d339ab743d4837 var1) {
      this.this$0 = var1;
      this.reset();
      this.y = var1.random.nextFloat() * (float)var1.field_22790;
   }

   void reset() {
      this.x = this.this$0.random.nextFloat() * (float)this.this$0.field_22789;
      this.y = -10.0F;
      this.vx = (this.this$0.random.nextFloat() - 0.5F) * 0.3F;
      this.vy = this.this$0.random.nextFloat() * 0.3F + 0.1F;
      this.size = this.this$0.random.nextFloat() * 2.0F + 0.5F;
      this.alpha = this.this$0.random.nextInt(80) + 20;
   }

   void update() {
      this.x += this.vx;
      this.y += this.vy;
      if (this.y > (float)(this.this$0.field_22790 + 10) || this.x < -10.0F || this.x > (float)(this.this$0.field_22789 + 10)) {
         this.reset();
      }

   }

   void render(class_332 context) {
      int color = Kb18d549c13309af9a5.primary().getRGB();
      int a = this.alpha << 24 | color & 16777215;
      context.method_25294((int)this.x, (int)this.y, (int)(this.x + this.size), (int)(this.y + this.size), a);
   }
}
