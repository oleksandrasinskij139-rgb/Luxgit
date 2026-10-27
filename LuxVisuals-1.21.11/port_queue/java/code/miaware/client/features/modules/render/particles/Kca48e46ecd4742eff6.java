package code.miaware.client.features.modules.render.particles;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_4587;

public class Kca48e46ecd4742eff6 extends Ke7b9a4839fe0ec8d1b {
   private final Kb9e875ead00e0d03bd distance;
   private final List<K7b801c9bc83cdf759e> particles;
   private final Kf39dc27225f4d9fe6e timerUtil;

   public Kca48e46ecd4742eff6() {
      super("World");
      this.distance = (new Kb9e875ead00e0d03bd(this.prefix + "Distance")).value(15.0F).range(5.0F, 50.0F).step(1.0F);
      this.particles = new ArrayList();
      this.timerUtil = new Kf39dc27225f4d9fe6e();
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.distance});
   }

   public void toggle() {
      this.particles.clear();
      this.timerUtil.reset();
   }

   public void onEvent() {
      K8d7165024ff567f5e7 updateEvent = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 == null) {
            this.particles.clear();
         } else {
            this.particles.removeIf(Kbd989b7b534ee9cdce::update);
            int diff = this.count().getValue().intValue() - this.particles.size();
            if ((float)this.particles.size() <= this.count().getValue()) {
               float d = this.distance.getValue();
               float defaultHeight = 8.0F;
               float defaultGravity = 0.3F;

               for(int i = 0; i < diff; ++i) {
                  this.particles.add(new K7b801c9bc83cdf759e(this, (float)(mc.field_1724.method_23317() + (double)Ka0484a23398fb5f9ac.randomInRange(-d, d)), (float)(mc.field_1724.method_23318() + (double)defaultHeight), (float)(mc.field_1724.method_23321() + (double)Ka0484a23398fb5f9ac.randomInRange(-d, d)), 0.0F, -Ka0484a23398fb5f9ac.randomInRange(defaultGravity * 0.1F, defaultGravity), 0.0F, this.particles.size(), Kbd989b7b534ee9cdce.getTexture((String)this.textureMode().getValue()), this.size().getValue(), (Boolean)this.rotate().getValue(), this.lifeTime().getValue().intValue(), 15.0F, 15.0F, (Boolean)this.trail().getValue(), 5));
               }
            }

         }
      }));
      K8d7165024ff567f5e7 renderEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         class_4587 matrixStack = event.matrixStack();
         K2b5e2c5f2b9891b450.WORLD.startRender(matrixStack);

         try {
            for(K7b801c9bc83cdf759e particle : this.particles) {
               particle.updateAlpha();
               particle.renderTrail(matrixStack);
               particle.render(matrixStack);
            }
         } finally {
            K2b5e2c5f2b9891b450.WORLD.endRender(matrixStack);
         }

      }));
      this.addEvents(new K8d7165024ff567f5e7[]{renderEvent, updateEvent});
   }
}
