package code.miaware.client.features.modules.render.particles;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.math.Ka0484a23398fb5f9ac;
import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2338;
import net.minecraft.class_4587;

public class Ka45c1624e28aaa39ba extends Ke7b9a4839fe0ec8d1b implements K4600bef438dafc1a9c {
   private final Kb9e875ead00e0d03bd distance;
   private final Kb9e875ead00e0d03bd riseSpeed;
   private final Kb9e875ead00e0d03bd rotateSpeed;
   private final K00fef91b3d4d1b3ecc glow;
   private final Kb9e875ead00e0d03bd glowSize;
   private final List<K9757f4fdb908dd4583> particles;
   private final Kf39dc27225f4d9fe6e spawnTimer;

   public Ka45c1624e28aaa39ba() {
      super("Cubes");
      this.distance = (new Kb9e875ead00e0d03bd(this.prefix + "Distance")).value(20.0F).range(5.0F, 50.0F).step(1.0F);
      this.riseSpeed = (new Kb9e875ead00e0d03bd(this.prefix + "Rise Speed")).value(0.03F).range(0.01F, 0.1F).step(0.01F);
      this.rotateSpeed = (new Kb9e875ead00e0d03bd(this.prefix + "Rotate Speed")).value(2.0F).range(0.5F, 5.0F).step(0.5F);
      this.glow = (new K00fef91b3d4d1b3ecc(this.prefix + "Glow")).value(true);
      this.glowSize = (new Kb9e875ead00e0d03bd(this.prefix + "Glow Size")).value(2.0F).range(1.0F, 4.0F).step(0.5F);
      this.particles = new ArrayList();
      this.spawnTimer = new Kf39dc27225f4d9fe6e();
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.distance, this.riseSpeed, this.rotateSpeed, this.glow, this.glowSize});
   }

   public void toggle() {
      this.particles.clear();
      this.spawnTimer.reset();
   }

   public void onEvent() {
      K8d7165024ff567f5e7 updateEvent = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 == null) {
            this.particles.clear();
         } else {
            this.particles.removeIf(K9757f4fdb908dd4583::update);
            int diff = this.count().getValue().intValue() - this.particles.size();
            if (diff > 0) {
               float d = this.distance.getValue();
               int toSpawn = Math.min(diff, 5);

               for(int i = 0; i < toSpawn; ++i) {
                  double spawnX = mc.field_1724.method_23317() + (double)Ka0484a23398fb5f9ac.randomInRange(-d, d);
                  double spawnZ = mc.field_1724.method_23321() + (double)Ka0484a23398fb5f9ac.randomInRange(-d, d);
                  double spawnY = this.Kebf16f9e39e7ea81e6(spawnX, spawnZ);
                  this.particles.add(new K9757f4fdb908dd4583((float)spawnX, (float)spawnY, (float)spawnZ, Ka0484a23398fb5f9ac.randomInRange(-0.005F, 0.005F), this.riseSpeed.getValue(), Ka0484a23398fb5f9ac.randomInRange(-0.005F, 0.005F), this.particles.size(), this.size().getValue(), this.lifeTime().getValue().intValue(), 15.0F, 15.0F, this.rotateSpeed.getValue()));
               }
            }

         }
      }));
      K8d7165024ff567f5e7 renderEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         class_4587 matrixStack = event.matrixStack();
         K2b5e2c5f2b9891b450.WORLD.startRender(matrixStack);

         try {
            for(K9757f4fdb908dd4583 particle : this.particles) {
               particle.updateAlpha();
               particle.render(matrixStack, (Boolean)this.glow.getValue(), this.glowSize.getValue());
            }
         } finally {
            K2b5e2c5f2b9891b450.WORLD.endRender(matrixStack);
         }

      }));
      this.addEvents(new K8d7165024ff567f5e7[]{renderEvent, updateEvent});
   }

   private double Kebf16f9e39e7ea81e6(double x, double z) {
      if (mc.field_1724 == null) {
         return (double)0.0F;
      } else if (mc.field_1687 == null) {
         return mc.field_1724.method_23318();
      } else {
         int playerY = (int)mc.field_1724.method_23318();

         for(int y = playerY + 2; y > playerY - 15; --y) {
            class_2338 pos = new class_2338((int)Math.floor(x), y, (int)Math.floor(z));
            class_2338 above = pos.method_10084();
            boolean blockSolid = !mc.field_1687.method_8320(pos).method_26215();
            boolean aboveAir = mc.field_1687.method_8320(above).method_26215();
            if (blockSolid && aboveAir) {
               return (double)y + (double)1.0F;
            }
         }

         return mc.field_1724.method_23318();
      }
   }
}
