package code.miaware.client.features.modules.combat.fastswap;

public final class Kef594ce17099203175 {
   private K2d3bf0652632c10ad3 phase;
   private int slot;
   private long lastPress;
   private long swappedAt;
   private boolean pressed;

   public Kef594ce17099203175() {
      this.phase = K2d3bf0652632c10ad3.IDLE;
      this.slot = -1;
   }

   public boolean begin(int inventorySlot, long now) {
      if (!this.busy() && inventorySlot >= 0 && inventorySlot < 36 && this.ready(now)) {
         this.slot = inventorySlot;
         this.phase = K2d3bf0652632c10ad3.PREPARE;
         this.pressed = true;
         this.lastPress = now;
         return true;
      } else {
         return false;
      }
   }

   public boolean ready(long now) {
      return !this.pressed || now - this.lastPress >= 200000000L;
   }

   public boolean busy() {
      return this.phase != K2d3bf0652632c10ad3.IDLE;
   }

   public int slot() {
      return this.slot;
   }

   public boolean tick(long now) {
      switch (this.phase.ordinal()) {
         case 1:
            this.phase = K2d3bf0652632c10ad3.SWAP;
            break;
         case 2:
            this.phase = K2d3bf0652632c10ad3.HOLD;
            this.swappedAt = now;
            return true;
         case 3:
            if (now - this.swappedAt >= 35000000L) {
               this.cancel();
            }
      }

      return false;
   }

   public void cancel() {
      this.phase = K2d3bf0652632c10ad3.IDLE;
      this.slot = -1;
   }

   public static int screenSlot(int inventorySlot) {
      if (inventorySlot >= 0 && inventorySlot < 36) {
         return inventorySlot < 9 ? inventorySlot + 36 : inventorySlot;
      } else {
         return -1;
      }
   }
}
