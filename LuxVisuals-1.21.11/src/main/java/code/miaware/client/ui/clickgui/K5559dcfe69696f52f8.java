package code.miaware.client.ui.clickgui;

public final class K5559dcfe69696f52f8 {
   public static final float KEY_SECONDS = 0.065F;
   private static final float HOVER_DELAY = 0.12F;
   private Object owner;
   private String text = "";
   private float elapsed;
   private int visible;

   public boolean isFor(Object owner, String text) {
      return this.owner == owner && this.text.equals(text);
   }

   public boolean update(Object owner, String text, float dt) {
      if (!this.isFor(owner, text)) {
         this.reset();
         this.owner = owner;
         this.text = text;
      } else {
         this.elapsed += Math.max(0.0F, dt);
      }

      int count = text.codePointCount(0, text.length());
      int next = this.elapsed < 0.12F ? 0 : Math.min(count, 1 + (int)((this.elapsed - 0.12F) / 0.065F));
      boolean sound = false;

      for(int i = this.visible; i < next; ++i) {
         int cp = text.codePointAt(text.offsetByCodePoints(0, i));
         sound |= !Character.isWhitespace(cp);
      }

      this.visible = next;
      return sound;
   }

   public String visibleText() {
      return this.text.substring(0, this.text.offsetByCodePoints(0, this.visible));
   }

   public boolean complete() {
      return this.visible == this.text.codePointCount(0, this.text.length());
   }

   public float opacity() {
      return Math.min(1.0F, this.elapsed / 0.12F);
   }

   public boolean caretVisible() {
      return !this.complete() || (int)(this.elapsed / 0.45F) % 2 == 0;
   }

   public void reset() {
      this.owner = null;
      this.text = "";
      this.elapsed = 0.0F;
      this.visible = 0;
   }
}
