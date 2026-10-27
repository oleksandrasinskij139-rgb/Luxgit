package code.miaware.client.features.modules.render.particles;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.event.events.player.world.K59e91c1b6e54ef922d;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_4587;

public class K4dddebb6ed6d3a429d extends Ke7b9a4839fe0ec8d1b {
   private final Kbd66519397e9a89967 physics;
   private final Kb9e875ead00e0d03bd motion;
   private final List<K64aaf3466ded2a5ec8> particles;

   public K4dddebb6ed6d3a429d() {
      super("Hit");
      this.physics = (new Kbd66519397e9a89967(this.prefix + "Physics")).value("Drop").values("Fly", "Drop");
      this.motion = (new Kb9e875ead00e0d03bd(this.prefix + "Motion")).value(0.3F).range(0.1F, 1.0F).step(0.1F);
      this.particles = new ArrayList();
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.physics, this.motion});
   }

   public void toggle() {
      this.particles.clear();
      this.removeAllEvents();
   }

   public void onEvent() {
      K8d7165024ff567f5e7 updateEvent = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 == null) {
            this.particles.clear();
         } else {
            this.particles.removeIf(Kbd989b7b534ee9cdce::update);
         }
      }));
      K8d7165024ff567f5e7 renderEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         class_4587 matrixStack = event.matrixStack();
         K2b5e2c5f2b9891b450.WORLD.startRender(matrixStack);

         try {
            for(K64aaf3466ded2a5ec8 particle : this.particles) {
               particle.updateAlpha();
               particle.renderTrail(matrixStack);
               particle.render(matrixStack);
            }
         } finally {
            K2b5e2c5f2b9891b450.WORLD.endRender(matrixStack);
         }

      }));
      K8d7165024ff567f5e7 attackEvent = K59e91c1b6e54ef922d.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         class_1297 patt0$temp = event.entity();
         if (patt0$temp instanceof class_1309 entity) {
            float m = this.motion.getValue();

            for(int i = 0; (float)i < this.count().getValue(); ++i) {
               this.particles.add(new K64aaf3466ded2a5ec8(this, (float)(entity.method_23317() + (double)Ka0484a23398fb5f9ac.randomInRange(-0.1F, 0.1F)), (float)(entity.method_23318() + (double)(entity.method_17682() / 2.0F) + (double)Ka0484a23398fb5f9ac.randomInRange(-0.1F, 0.1F)), (float)(entity.method_23321() + (double)Ka0484a23398fb5f9ac.randomInRange(-0.1F, 0.1F)), Ka0484a23398fb5f9ac.randomInRange(-m, m), Ka0484a23398fb5f9ac.randomInRange(-m, m), Ka0484a23398fb5f9ac.randomInRange(-m, m), this.particles.size(), Kbd989b7b534ee9cdce.getTexture((String)this.textureMode().getValue()), this.size().getValue(), !this.physics.is("Fly"), (Boolean)this.rotate().getValue(), this.lifeTime().getValue().intValue(), 15.0F, 15.0F, (Boolean)this.trail().getValue(), 5));
            }
         }

      }));
      this.addEvents(new K8d7165024ff567f5e7[]{renderEvent, attackEvent, updateEvent});
   }
}
