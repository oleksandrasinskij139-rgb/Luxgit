package code.miaware.client.features.modules.render;

import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_287;
import net.minecraft.class_4587;

class Kf7a85a9ade792fc05f {
   private final List<K051deaddf79ee0bf21> footsteps;
   private final Kf39dc27225f4d9fe6e spawnTimer;
   private final boolean isFriend;
   private int localRainbowOffset;
   // $FF: synthetic field
   final K419138a37ac07d97bc this$0;

   public Kf7a85a9ade792fc05f(final K419138a37ac07d97bc var1, boolean isFriend) {
      this.this$0 = var1;
      this.footsteps = new ArrayList();
      this.spawnTimer = new Kf39dc27225f4d9fe6e();
      this.localRainbowOffset = 0;
      this.isFriend = isFriend;
      this.localRainbowOffset = var1.rainbowOffset;
   }

   public void update(class_1657 player) {
      this.footsteps.removeIf((f) -> f.timerUtil.finished(8000L));
      class_243 velocity = player.method_18798();
      boolean isMoving = velocity.field_1352 * velocity.field_1352 + velocity.field_1350 * velocity.field_1350 > 0.001;
      if (isMoving && player.method_24828() && this.spawnTimer.finished(150L)) {
         this.spawnTimer.reset();
         class_243 footstepPos = new class_243(player.method_23317(), Math.floor(player.method_23318()) + (double)0.001F, player.method_23321());
         float movementYaw = K419138a37ac07d97bc.K9dec508b3419e71718(velocity);
         this.footsteps.add(new K051deaddf79ee0bf21(footstepPos, movementYaw, this.localRainbowOffset, this.this$0.lifeTime.getValue().intValue() * 50, this.this$0.spawnDur.getValue().intValue() * 50, this.this$0.dyingDur.getValue().intValue() * 50, (String)this.this$0.colorMode.getValue(), this.isFriend));
         this.localRainbowOffset += 40;
         if (this.localRainbowOffset >= 360) {
            this.localRainbowOffset = 0;
         }
      }

   }

   public void render(class_4587 matrixStack, class_287 buffer, class_243 camera) {
      for(K051deaddf79ee0bf21 footstep : this.footsteps) {
         footstep.renderBatched(matrixStack, buffer, camera);
      }

   }
}
