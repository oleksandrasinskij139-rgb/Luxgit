package code.miaware.api.utils.animation;

import java.util.Random;

class K4746d57b1aafb685e6 {
   private static final Random RANDOM = new Random();
   private static final String GLITCH_CHARS = "日月火水木金土天地人口田目手心雨風雪花鳥魚犬猫山川森林海空星光影夢希望愛友平和力強勇気笑涙幸運命絆縁道未来過去現在";
   private final char targetChar;
   private final float duration;
   private final boolean useGlitch;
   private float elapsed = 0.0F;
   private char currentGlitchChar;
   private float nextGlitchChange = 0.0F;

   public K4746d57b1aafb685e6(char character, float duration, boolean useGlitch) {
      this.targetChar = character;
      this.duration = duration;
      this.useGlitch = useGlitch;
      this.currentGlitchChar = Kb4930fdb3c1edf4d4c();
      this.nextGlitchChange = 0.05F;
   }

   public void update(float delta) {
      this.elapsed += delta;
      if (this.elapsed > this.duration) {
         this.elapsed = this.duration;
      }

      if (this.useGlitch && !this.isFinished()) {
         this.nextGlitchChange -= delta;
         if (this.nextGlitchChange <= 0.0F) {
            this.currentGlitchChar = Kb4930fdb3c1edf4d4c();
            this.nextGlitchChange = 0.03F + RANDOM.nextFloat() * 0.05F;
         }
      }

   }

   public float getProgress() {
      return Math.min(this.elapsed / this.duration, 1.0F);
   }

   public boolean isFinished() {
      return this.elapsed >= this.duration;
   }

   public float getOffsetY() {
      if (this.isFinished()) {
         return 0.0F;
      } else {
         float progress = this.getProgress();
         float eased = 1.0F - (float)Math.pow((double)(1.0F - progress), (double)3.0F);
         return -20.0F * (1.0F - eased);
      }
   }

   public float getAlpha() {
      if (this.isFinished()) {
         return 1.0F;
      } else {
         float progress = this.getProgress();
         if (this.useGlitch) {
            if (progress < 0.6F) {
               return 1.0F;
            } else {
               float fadeProgress = (progress - 0.6F) / 0.4F;
               return fadeProgress;
            }
         } else {
            return Math.min(progress * 3.3F, 1.0F);
         }
      }
   }

   public char getDisplayChar(char original) {
      if (this.useGlitch && !this.isFinished()) {
         float progress = this.getProgress();
         return progress < 0.6F ? this.currentGlitchChar : original;
      } else {
         return original;
      }
   }

   private static char Kb4930fdb3c1edf4d4c() {
      int index = RANDOM.nextInt("日月火水木金土天地人口田目手心雨風雪花鳥魚犬猫山川森林海空星光影夢希望愛友平和力強勇気笑涙幸運命絆縁道未来過去現在".length());
      return "日月火水木金土天地人口田目手心雨風雪花鳥魚犬猫山川森林海空星光影夢希望愛友平和力強勇気笑涙幸運命絆縁道未来過去現在".charAt(index);
   }
}
