package code.miaware.client.features.modules.render.particles;

import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.system.backend.K5d3444fb5779729097;
import lombok.Generated;

public class Ke7b9a4839fe0ec8d1b extends K5d3444fb5779729097 {
   private final Kbd66519397e9a89967 textureMode;
   private final Kb9e875ead00e0d03bd count;
   private final Kb9e875ead00e0d03bd size;
   private final Kb9e875ead00e0d03bd lifeTime;
   private final K00fef91b3d4d1b3ecc rotate;
   private final K00fef91b3d4d1b3ecc trail;
   private final K00fef91b3d4d1b3ecc dyingEffect;
   public final String prefix;

   public Ke7b9a4839fe0ec8d1b(String prefix) {
      this.prefix = prefix + ": ";
      this.textureMode = (new Kbd66519397e9a89967(this.prefix + "Texture")).value("Spark").values(Kbd989b7b534ee9cdce.textures);
      this.count = (new Kb9e875ead00e0d03bd(this.prefix + "Count")).value(25.0F).range(10.0F, 100.0F).step(1.0F);
      this.size = (new Kb9e875ead00e0d03bd(this.prefix + "Size")).value(0.2F).range(0.1F, 0.4F).step(0.05F);
      this.lifeTime = (new Kb9e875ead00e0d03bd(this.prefix + "Life time")).value(10.0F).range(2.0F, 100.0F).step(1.0F);
      this.rotate = (new K00fef91b3d4d1b3ecc(this.prefix + "Rotate")).value(true);
      this.trail = (new K00fef91b3d4d1b3ecc(this.prefix + "Trail")).value(false);
      this.dyingEffect = (new K00fef91b3d4d1b3ecc(this.prefix + "Dying effect")).value(false);
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.textureMode, this.count, this.size, this.lifeTime, this.rotate, this.trail, this.dyingEffect});
   }

   @Generated
   public Kbd66519397e9a89967 textureMode() {
      return this.textureMode;
   }

   @Generated
   public Kb9e875ead00e0d03bd count() {
      return this.count;
   }

   @Generated
   public Kb9e875ead00e0d03bd size() {
      return this.size;
   }

   @Generated
   public Kb9e875ead00e0d03bd lifeTime() {
      return this.lifeTime;
   }

   @Generated
   public K00fef91b3d4d1b3ecc rotate() {
      return this.rotate;
   }

   @Generated
   public K00fef91b3d4d1b3ecc trail() {
      return this.trail;
   }

   @Generated
   public K00fef91b3d4d1b3ecc dyingEffect() {
      return this.dyingEffect;
   }

   @Generated
   public String prefix() {
      return this.prefix;
   }
}
