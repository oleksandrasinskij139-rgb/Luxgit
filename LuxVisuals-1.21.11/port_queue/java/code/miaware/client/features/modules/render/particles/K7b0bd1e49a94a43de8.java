package code.miaware.client.features.modules.render.particles;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Ka4b85e821e3749c3d9;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.backend.K5d3444fb5779729097;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Generated;

@ModuleRegister(
   name = "Particles",
   category = Kf76f042931183af5ba.RENDER,
   description = "Настройка отображения частиц"
)
public class K7b0bd1e49a94a43de8 extends Kab8a8c478f7d0077cd {
   private static final K7b0bd1e49a94a43de8 instance = new K7b0bd1e49a94a43de8();
   private final K4dddebb6ed6d3a429d hitParticles = new K4dddebb6ed6d3a429d();
   private final Kca48e46ecd4742eff6 worldParticles = new Kca48e46ecd4742eff6();
   private final Ka45c1624e28aaa39ba cubeParticles = new Ka45c1624e28aaa39ba();
   private final K00fef91b3d4d1b3ecc worldBoolean = (new K00fef91b3d4d1b3ecc("World")).value(true).onAction(() -> {
      if (!(Boolean)this.getWorldBoolean().getValue()) {
         Kca48e46ecd4742eff6 var10001 = this.worldParticles;
         Kca48e46ecd4742eff6 var10002 = this.worldParticles;
         Objects.requireNonNull(var10002);
         this.K09874e38ff4ee77008(var10001, var10002::toggle);
      } else if (this.isEnabled()) {
         this.K85f51d828f9a26147e(this.worldParticles);
      }

   });
   private final K00fef91b3d4d1b3ecc hitBoolean = (new K00fef91b3d4d1b3ecc("Hit")).value(false).onAction(() -> {
      if (!(Boolean)this.getHitBoolean().getValue()) {
         K4dddebb6ed6d3a429d var10001 = this.hitParticles;
         K4dddebb6ed6d3a429d var10002 = this.hitParticles;
         Objects.requireNonNull(var10002);
         this.K09874e38ff4ee77008(var10001, var10002::toggle);
      } else if (this.isEnabled()) {
         this.K85f51d828f9a26147e(this.hitParticles);
      }

   });
   private final K00fef91b3d4d1b3ecc cubeBoolean = (new K00fef91b3d4d1b3ecc("Cubes")).value(false).onAction(() -> {
      if (!(Boolean)this.getCubeBoolean().getValue()) {
         Ka45c1624e28aaa39ba var10001 = this.cubeParticles;
         Ka45c1624e28aaa39ba var10002 = this.cubeParticles;
         Objects.requireNonNull(var10002);
         this.K09874e38ff4ee77008(var10001, var10002::toggle);
      } else if (this.isEnabled()) {
         this.K85f51d828f9a26147e(this.cubeParticles);
      }

   });
   private final Ka4b85e821e3749c3d9 spawn;

   public K7b0bd1e49a94a43de8() {
      this.spawn = (new Ka4b85e821e3749c3d9("Spawn")).value(this.worldBoolean, this.hitBoolean, this.cubeBoolean);
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.spawn});

      for(Kdced9e23f1e229ba9d<?> setting : this.worldParticles.getSettings()) {
         setting.setVisible(() -> this.spawn.isEnabled(this.worldBoolean.getName()));
      }

      this.addSettings(this.worldParticles.getSettings());

      for(Kdced9e23f1e229ba9d<?> setting : this.hitParticles.getSettings()) {
         setting.setVisible(() -> this.spawn.isEnabled(this.hitBoolean.getName()));
      }

      this.addSettings(this.hitParticles.getSettings());

      for(Kdced9e23f1e229ba9d<?> setting : this.cubeParticles.getSettings()) {
         setting.setVisible(() -> this.spawn.isEnabled(this.cubeBoolean.getName()));
      }

      this.addSettings(this.cubeParticles.getSettings());
   }

   public void onEvent() {
      if ((Boolean)this.hitBoolean.getValue()) {
         this.K85f51d828f9a26147e(this.hitParticles);
      }

      if ((Boolean)this.worldBoolean.getValue()) {
         this.K85f51d828f9a26147e(this.worldParticles);
      }

      if ((Boolean)this.cubeBoolean.getValue()) {
         this.K85f51d828f9a26147e(this.cubeParticles);
      }

   }

   public void onDisable() {
      K4dddebb6ed6d3a429d var10001 = this.hitParticles;
      K4dddebb6ed6d3a429d var10002 = this.hitParticles;
      Objects.requireNonNull(var10002);
      this.K09874e38ff4ee77008(var10001, var10002::toggle);
      Kca48e46ecd4742eff6 var1 = this.worldParticles;
      Kca48e46ecd4742eff6 var3 = this.worldParticles;
      Objects.requireNonNull(var3);
      this.K09874e38ff4ee77008(var1, var3::toggle);
      Ka45c1624e28aaa39ba var2 = this.cubeParticles;
      Ka45c1624e28aaa39ba var4 = this.cubeParticles;
      Objects.requireNonNull(var4);
      this.K09874e38ff4ee77008(var2, var4::toggle);
   }

   private void K85f51d828f9a26147e(K5d3444fb5779729097 child) {
      this.K8cab6983c7f3e765b1(child);
      child.onEvent();
      this.addEvents(child.getEventListeners());
   }

   private void K09874e38ff4ee77008(K5d3444fb5779729097 child, Runnable reset) {
      this.K8cab6983c7f3e765b1(child);
      reset.run();
   }

   private void K8cab6983c7f3e765b1(K5d3444fb5779729097 child) {
      List<K8d7165024ff567f5e7> listeners = new ArrayList(child.getEventListeners());
      child.removeAllEvents();
      this.removeEvents(listeners);
   }

   @Generated
   public static K7b0bd1e49a94a43de8 getInstance() {
      return instance;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getWorldBoolean() {
      return this.worldBoolean;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getHitBoolean() {
      return this.hitBoolean;
   }

   @Generated
   public K00fef91b3d4d1b3ecc getCubeBoolean() {
      return this.cubeBoolean;
   }
}
