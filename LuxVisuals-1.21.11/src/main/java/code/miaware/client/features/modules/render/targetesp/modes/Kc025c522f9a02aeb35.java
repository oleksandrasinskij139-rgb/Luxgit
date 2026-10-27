package code.miaware.client.features.modules.render.targetesp.modes;

final class Kc025c522f9a02aeb35 {
   float x;
   float y;
   float vx;
   float vy;
   float life;
   float size;
   int colorIndex;

   Kc025c522f9a02aeb35(float x, float y, float vx, float vy, float size, int colorIndex) {
      this.x = x;
      this.y = y;
      this.vx = vx;
      this.vy = vy;
      this.life = 1.0F;
      this.size = size;
      this.colorIndex = colorIndex;
   }

   void tick() {
      this.vx *= 0.93F;
      this.vy *= 0.93F;
      this.vy -= 1.8E-4F;
      this.x += this.vx;
      this.y += this.vy;
      this.life -= 0.038F;
   }
}
