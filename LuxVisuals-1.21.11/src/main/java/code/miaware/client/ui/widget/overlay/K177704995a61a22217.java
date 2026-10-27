package code.miaware.client.ui.widget.overlay;

import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import java.awt.Color;
import net.minecraft.class_2960;

final class K177704995a61a22217 {
   private final Ka91715a8c4e3f3b7c4 animation = new Ka91715a8c4e3f3b7c4();
   private final class_2960 id;
   private String name;
   private String durationText;
   private Color durationColor;
   private Color accentColor;
   private int durationTicks;
   private boolean urgent;
   private boolean infinite;
   private boolean fading;
   private boolean warned;
   private boolean seenThisFrame;

   private K177704995a61a22217(Kc6773ac34c8c8cb28f snapshot) {
      this.id = snapshot.id;
      this.name = snapshot.name;
      this.durationText = snapshot.durationText;
      this.durationColor = snapshot.durationColor;
      this.accentColor = snapshot.accentColor;
      this.durationTicks = snapshot.durationTicks;
      this.urgent = snapshot.urgent;
      this.infinite = snapshot.infinite;
      this.animation.setValue((double)0.0F);
      this.animation.run((double)1.0F, 220L, Kf3b62ecfba0c3544e6.QUINT_OUT);
   }

   private void Ke33b9991896e8324ba(Kc6773ac34c8c8cb28f snapshot) {
      this.name = snapshot.name;
      this.durationText = snapshot.durationText;
      this.durationColor = snapshot.durationColor;
      this.accentColor = snapshot.accentColor;
      this.durationTicks = snapshot.durationTicks;
      this.urgent = snapshot.urgent;
      this.infinite = snapshot.infinite;
      this.fading = false;
      this.animation.run((double)1.0F, 180L, Kf3b62ecfba0c3544e6.QUINT_OUT);
   }

   private void K306b07533b251fa9de() {
      if (!this.fading) {
         this.fading = true;
         this.animation.run((double)0.0F, 320L, Kf3b62ecfba0c3544e6.QUINT_OUT);
      }
   }

   private boolean K000a5af5a3585a165d() {
      return this.fading && this.animation.isFinished() && this.animation.getValue() <= 0.03;
   }

   private float Kd2eaa153830e7a6be9() {
      return (float)this.animation.getValue();
   }

   private float K1bdf600f4b9e91e617() {
      float value = this.Kd2eaa153830e7a6be9();
      return Math.max(0.0F, Math.min(1.0F, (value - 0.04F) / 0.96F));
   }

   private float K2811cb3568b4e840ed() {
      float value = this.Kd2eaa153830e7a6be9();
      return Math.max(0.0F, Math.min(1.0F, (value - 0.32F) / 0.68F));
   }

   private float K803f3186992d9517b0() {
      return 1.0F;
   }

   private boolean K5e1328c0b1b203e486() {
      return this.fading;
   }

   private int K0de2006ca73eeaf2ec() {
      return this.infinite ? Integer.MAX_VALUE : this.durationTicks;
   }
}
