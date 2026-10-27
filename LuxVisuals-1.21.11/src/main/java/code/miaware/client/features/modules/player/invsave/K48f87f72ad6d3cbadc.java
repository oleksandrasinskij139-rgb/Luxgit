package code.miaware.client.features.modules.player.invsave;

public record K48f87f72ad6d3cbadc(int x, int y) {
   public static final int WIDTH = 190;
   public static final int HEIGHT = 156;

   public static K48f87f72ad6d3cbadc fit(K26771f24bfecb7a1f9 manager, int screenWidth, int screenHeight, int rowY) {
      int right = manager.x() + manager.width() + 10;
      int left = manager.x() - 190 - 10;
      int x = right + 190 <= screenWidth - 8 ? right : (left >= 8 ? left : screenWidth - 190 - 8);
      return new K48f87f72ad6d3cbadc(Math.max(0, x), Math.clamp((long)(rowY - 10), 0, Math.max(0, screenHeight - 156 - 8)));
   }

   public static int slotIndex(int cell) {
      if (cell >= 0 && cell < 41) {
         if (cell < 4) {
            return 39 - cell;
         } else if (cell == 4) {
            return 40;
         } else {
            return cell < 32 ? cell + 4 : cell - 32;
         }
      } else {
         throw new IllegalArgumentException("Preview cell outside inventory");
      }
   }

   public static int cellX(int cell) {
      return 14 + (cell < 4 ? cell : (cell == 4 ? 8 : (cell - 5) % 9)) * 18;
   }

   public static int cellY(int cell) {
      return cell < 5 ? 34 : (cell < 32 ? 60 + (cell - 5) / 9 * 18 : 120);
   }
}
