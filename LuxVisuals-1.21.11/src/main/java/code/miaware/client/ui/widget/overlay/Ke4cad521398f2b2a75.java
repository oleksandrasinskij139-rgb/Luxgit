package code.miaware.client.ui.widget.overlay;

class Ke4cad521398f2b2a75 {
   String title;
   String artist;
   byte[] artworkPng;
   int position;
   int duration;
   boolean playing;

   Ke4cad521398f2b2a75(String title, String artist, byte[] artworkPng, int position, int duration, boolean playing) {
      this.title = title;
      this.artist = artist;
      this.artworkPng = artworkPng;
      this.position = position;
      this.duration = duration;
      this.playing = playing;
   }

   public String getTitle() {
      return this.title;
   }

   public String getArtist() {
      return this.artist;
   }

   public byte[] getArtworkPng() {
      return this.artworkPng;
   }

   public int getPosition() {
      return this.position;
   }

   public int getDuration() {
      return this.duration;
   }

   public boolean getPlaying() {
      return this.playing;
   }
}
