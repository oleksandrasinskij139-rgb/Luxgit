package code.miaware.api.utils.render.fonts;

import lombok.Generated;

public enum K0cfffc040d3543e4da {
   SECOND_PILOT("A"),
   HEART_RATE("B"),
   STRING_LINE("C"),
   LINE_2("D"),
   CRACK("E"),
   HEART_3_LINE("F"),
   TIKTOK_LINE("G"),
   USER_FOLLOW("H"),
   YOUTUBE_LINE("I");

   private final String letter;

   private K0cfffc040d3543e4da(String letter) {
      this.letter = letter;
   }

   public K11ef91e33d46fe9b37 getFont() {
      return K896065ea0a2bba4f47.ROLE_ICONS_BITMAP;
   }

   public static K0cfffc040d3543e4da find(String name) {
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
   private static K0cfffc040d3543e4da[] $values() {
      return new K0cfffc040d3543e4da[]{SECOND_PILOT, HEART_RATE, STRING_LINE, LINE_2, CRACK, HEART_3_LINE, TIKTOK_LINE, USER_FOLLOW, YOUTUBE_LINE};
   }
}
