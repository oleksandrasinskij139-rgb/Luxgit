package code.miaware.client.ui.clickgui;

final class K7cc58e1b7729233c63 {
   float value;
   float target;

   K7cc58e1b7729233c63(float value) {
      this.value = value;
      this.target = value;
   }

   void update(float dt, float speed) {
      this.value += (this.target - this.value) * (1.0F - (float)Math.exp((double)(-dt * speed)));
      if (Math.abs(this.target - this.value) < 0.0015F) {
         this.value = this.target;
      }

   }

   void snap() {
      this.value = this.target;
   }
}
