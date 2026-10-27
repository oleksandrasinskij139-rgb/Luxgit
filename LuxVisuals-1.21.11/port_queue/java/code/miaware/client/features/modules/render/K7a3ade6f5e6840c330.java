package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K2a56ae9ebefcb944e3;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.client.ui.journey.K1b44e53424f6810c1d;
import code.miaware.client.ui.journey.Ka13f3e675902dcbffb;
import lombok.Generated;
import net.minecraft.class_332;
import net.minecraft.class_634;

@ModuleRegister(
   name = "Dimension Transition",
   category = Kf76f042931183af5ba.RENDER,
   description = "Cinematic Journey: dimension landscapes replace the terrain loading screen"
)
public final class K7a3ade6f5e6840c330 extends Kab8a8c478f7d0077cd {
   private static final K7a3ade6f5e6840c330 instance = new K7a3ade6f5e6840c330();
   private final Kb9e875ead00e0d03bd duration = (new Kb9e875ead00e0d03bd("Duration")).range(0.5F, 4.0F).step(0.1F).value(2.0F);
   private final Kb9e875ead00e0d03bd intensity = (new Kb9e875ead00e0d03bd("Intensity")).range(10.0F, 80.0F).step(5.0F).value(40.0F);
   private final K00fef91b3d4d1b3ecc title = (new K00fef91b3d4d1b3ecc("Dimension title")).value(true);
   private final K00fef91b3d4d1b3ecc motion = (new K00fef91b3d4d1b3ecc("Background motion")).value(true);
   private final Ka13f3e675902dcbffb playback = new Ka13f3e675902dcbffb();
   private final K1b44e53424f6810c1d renderer = new K1b44e53424f6810c1d();
   private class_634 connection;

   public K7a3ade6f5e6840c330() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.duration, this.intensity, this.title, this.motion});
   }

   public void onEnable() {
      this.playback.reset();
      this.connection = mc.method_1562();
   }

   public void onDisable() {
      this.playback.reset();
      this.connection = null;
   }

   public void onEvent() {
      this.addEvents(new K8d7165024ff567f5e7[]{K2a56ae9ebefcb944e3.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> this.K37463332674939691a()))});
   }

   private void K37463332674939691a() {
      class_634 next = mc.method_1562();
      if (next != this.connection || next == null) {
         this.playback.reset();
      }

      this.connection = next;
   }

   public boolean renderLoading(class_332 context, Object screen) {
      if (!this.isEnabled()) {
         return false;
      } else {
         this.K37463332674939691a();
         long now = System.nanoTime();
         String dimension = mc.field_1687 == null ? "minecraft:overworld" : mc.field_1687.method_27983().method_29177().toString();
         this.playback.begin(screen, dimension, now);
         this.renderer.render(context, dimension, this.playback.seconds(now), 1.0F, this.intensity.getValue(), (Boolean)this.title.getValue(), (Boolean)this.motion.getValue(), true);
         return true;
      }
   }

   public void finishLoading(Object screen) {
      if (this.isEnabled()) {
         this.playback.finish(screen, System.nanoTime());
      }

   }

   public void renderArrival(class_332 context) {
      if (this.isEnabled()) {
         this.K37463332674939691a();
         if (this.playback.leaving()) {
            long now = System.nanoTime();
            String dimension = this.playback.dimension();
            double seconds = this.playback.seconds(now);
            float opacity = this.playback.opacity(now, this.duration.getValue());
            if (!(opacity <= 0.0F) && mc.field_1755 == null && mc.field_1687 != null && mc.field_1724 != null) {
               this.renderer.render(context, dimension, seconds, opacity, this.intensity.getValue(), (Boolean)this.title.getValue(), (Boolean)this.motion.getValue(), false);
            }
         }
      }
   }

   @Generated
   public static K7a3ade6f5e6840c330 getInstance() {
      return instance;
   }
}
