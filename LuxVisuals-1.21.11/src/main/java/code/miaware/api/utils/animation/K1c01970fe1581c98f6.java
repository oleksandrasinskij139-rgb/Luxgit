package code.miaware.api.utils.animation;

import java.util.HashMap;
import java.util.Map;

public class K1c01970fe1581c98f6 {
   private final Map<Integer, K4746d57b1aafb685e6> animations;
   private String currentText;
   private long lastUpdateTime;
   private final boolean useGlitch;

   public K1c01970fe1581c98f6() {
      this(false);
   }

   public K1c01970fe1581c98f6(boolean useGlitch) {
      this.animations = new HashMap();
      this.currentText = "";
      this.lastUpdateTime = 0L;
      this.useGlitch = useGlitch;
   }

   private boolean Ke7f26caf5118439af5(String newText, boolean forceAnimation) {
      return forceAnimation || !newText.equals(this.currentText);
   }

   public void update(String newText) {
      this.update(newText, false);
   }

   public void update(String newText, boolean forceAnimation) {
      long currentTime = System.currentTimeMillis();
      float delta = this.lastUpdateTime > 0L ? (float)(currentTime - this.lastUpdateTime) / 1000.0F : 0.0F;
      this.lastUpdateTime = currentTime;

      for(K4746d57b1aafb685e6 anim : this.animations.values()) {
         anim.update(delta);
      }

      this.animations.entrySet().removeIf((entry) -> ((K4746d57b1aafb685e6)entry.getValue()).isFinished());
      if (!newText.equals(this.currentText) || forceAnimation) {
         int maxLen = Math.max(this.currentText.length(), newText.length());

         for(int i = 0; i < maxLen; ++i) {
            char oldChar = i < this.currentText.length() ? this.currentText.charAt(i) : 32;
            char newChar = i < newText.length() ? newText.charAt(i) : 32;
            if (oldChar != newChar || forceAnimation) {
               float duration = this.useGlitch ? 0.8F : 0.3F;
               this.animations.put(i, new K4746d57b1aafb685e6(newChar, duration, this.useGlitch));
            }
         }

         this.currentText = newText;
      }
   }

   public float getOffsetY(int index) {
      K4746d57b1aafb685e6 anim = (K4746d57b1aafb685e6)this.animations.get(index);
      return anim != null ? anim.getOffsetY() : 0.0F;
   }

   public float getAlpha(int index) {
      K4746d57b1aafb685e6 anim = (K4746d57b1aafb685e6)this.animations.get(index);
      return anim != null ? anim.getAlpha() : 1.0F;
   }

   public char getDisplayChar(int index, char originalChar) {
      K4746d57b1aafb685e6 anim = (K4746d57b1aafb685e6)this.animations.get(index);
      return anim != null ? anim.getDisplayChar(originalChar) : originalChar;
   }

   public void reset() {
      this.animations.clear();
      this.currentText = "";
      this.lastUpdateTime = 0L;
   }
}
