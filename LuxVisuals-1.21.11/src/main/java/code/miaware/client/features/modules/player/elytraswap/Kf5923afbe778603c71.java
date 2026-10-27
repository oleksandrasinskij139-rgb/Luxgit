package code.miaware.client.features.modules.player.elytraswap;

public record Kf5923afbe778603c71(int slot, int hotbar) {
   public Kf5923afbe778603c71(int slot, int hotbar) {
      if ((slot == 6 || slot >= 9 && slot <= 35) && hotbar >= 0 && hotbar <= 8) {
         this.slot = slot;
         this.hotbar = hotbar;
      } else {
         throw new IllegalArgumentException("Invalid equipment swap slot");
      }
   }

   public int hotbarSlot() {
      return 36 + this.hotbar;
   }
}
