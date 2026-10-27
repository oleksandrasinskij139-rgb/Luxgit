package code.miaware.api.utils.render.fonts;

import lombok.Generated;

public enum Icons {
   COORDS("W"),
   SIGNAL("B"),
   SPEED("V"),
   WLAN("N"),
   PERFORMANCE("Y"),
   KEYBOARD("K"),
   POTION("P"),
   COOLDOWN("D"),
   COMBAT_ICON("u"),
   MOVEMENT_ICON("k"),
   RENDER_ICON("m"),
   PLAYER_ICON("W"),
   OTHER_ICON("U"),
   QUIT("P"),
   MULTIPLAYER("A"),
   OPTIONS("S"),
   SINGLEPLAYER("U"),
   STEP_B("K"),
   STEP_F("L"),
   PAUSE("O"),
   PLAY("I"),
   REPEAT("J"),
   FOLDER("F"),
   RIGHTR("R"),
   REFRESH("G"),
   DOCUMENT("D"),
   TRASH("T"),
   CROSS("C"),
   SEARCH("S"),
   MENU("S"),
   INFO("I");

   private final String letter;

   private Icons(String letter) {
      this.letter = letter;
   }

   public K5fe84dad3db1af4bb7 getFont() {
      switch (this.ordinal()) {
         case 8 -> {
            return K896065ea0a2bba4f47.ICONS_DESHUX;
         }
         case 9 -> {
            return K896065ea0a2bba4f47.ICONS_DESHUX;
         }
         case 10 -> {
            return K896065ea0a2bba4f47.ICONS_DESHUX;
         }
         case 11 -> {
            return K896065ea0a2bba4f47.ICONS_NURIK;
         }
         case 12 -> {
            return K896065ea0a2bba4f47.ICONS_NURIK;
         }
         default -> {
            return K896065ea0a2bba4f47.ICONS;
         }
      }
   }

   public static Icons find(String name) {
      try {
         return valueOf(name);
      } catch (IllegalArgumentException var2) {
         return null;
      }
   }

   @Generated
   public String getLetter() {
      return this.letter;
   }

   // $FF: synthetic method
   private static Icons[] $values() {
      return new Icons[]{COORDS, SIGNAL, SPEED, WLAN, PERFORMANCE, KEYBOARD, POTION, COOLDOWN, COMBAT_ICON, MOVEMENT_ICON, RENDER_ICON, PLAYER_ICON, OTHER_ICON, QUIT, MULTIPLAYER, OPTIONS, SINGLEPLAYER, STEP_B, STEP_F, PAUSE, PLAY, REPEAT, FOLDER, RIGHTR, REFRESH, DOCUMENT, TRASH, CROSS, SEARCH, MENU, INFO};
   }
}
