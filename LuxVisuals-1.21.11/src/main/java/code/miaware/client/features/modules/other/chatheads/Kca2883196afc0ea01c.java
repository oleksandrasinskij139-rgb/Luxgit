package code.miaware.client.features.modules.other.chatheads;

import net.minecraft.class_640;

public record Kca2883196afc0ea01c(class_640 playerInfo) {
   public static final Kca2883196afc0ea01c EMPTY = new Kca2883196afc0ea01c((class_640)null);

   public static Kca2883196afc0ea01c of(class_640 playerInfo) {
      return playerInfo == null ? EMPTY : new Kca2883196afc0ea01c(playerInfo);
   }

   public boolean isEmpty() {
      return this.playerInfo == null;
   }
}
