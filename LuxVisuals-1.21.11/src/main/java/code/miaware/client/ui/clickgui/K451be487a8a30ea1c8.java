package code.miaware.client.ui.clickgui;

import code.miaware.api.module.Kab8a8c478f7d0077cd;

record K451be487a8a30ea1c8(float x, float y, float w, float h, Kab8a8c478f7d0077cd module) {
   boolean contains(float mx, float my) {
      return mx >= this.x && mx <= this.x + this.w && my >= this.y && my <= this.y + this.h;
   }
}
