package code.miaware.client.ui.clickgui;

record K04ecc14f13fb56c724(float x, float y, float w, float h, boolean popup, Object tag, K4ba12f9dfc8560f577 action) {
   boolean contains(float mx, float my) {
      return mx >= this.x && mx <= this.x + this.w && my >= this.y && my <= this.y + this.h;
   }
}
