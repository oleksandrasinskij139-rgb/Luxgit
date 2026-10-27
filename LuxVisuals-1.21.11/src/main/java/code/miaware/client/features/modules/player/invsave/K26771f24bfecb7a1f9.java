package code.miaware.client.features.modules.player.invsave;

public record K26771f24bfecb7a1f9(int x, int y, int width, int height, int rowHeight, int rows) {
   public static K26771f24bfecb7a1f9 fit(int screenWidth, int screenHeight) {
      int width = Math.min(300, Math.max(1, screenWidth - 24));
      int height = Math.min(236, Math.max(1, screenHeight - 24));
      int rowHeight = height < 260 ? 36 : 42;
      int rows = Math.max(0, (height - 132) / rowHeight);
      int groupWidth = width + 10 + 190;
      int x = screenWidth >= groupWidth + 24 ? (screenWidth - groupWidth) / 2 : (screenWidth - width) / 2;
      return new K26771f24bfecb7a1f9(x, (screenHeight - height) / 2, width, height, rowHeight, rows);
   }

   public int listY() {
      return this.y + 98;
   }

   public int rowY(int row) {
      return this.listY() + row * this.rowHeight;
   }

   public int footerY() {
      return this.y + this.height - 20;
   }

   public boolean contains(double mx, double my) {
      return inside(mx, my, this.x, this.y, this.width, this.height);
   }

   public static boolean inside(double mx, double my, int x, int y, int width, int height) {
      return mx >= (double)x && my >= (double)y && mx < (double)(x + width) && my < (double)(y + height);
   }
}
