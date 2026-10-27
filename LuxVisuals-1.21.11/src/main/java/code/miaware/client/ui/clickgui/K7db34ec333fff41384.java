package code.miaware.client.ui.clickgui;

class K7db34ec333fff41384 {
   String author;
   float x;
   float y;
   float width;
   float height;

   K7db34ec333fff41384(String author, float x, float y, float width, float height) {
      this.author = author;
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
   }

   boolean contains(float mouseX, float mouseY) {
      return mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
   }
}
