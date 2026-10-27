package code.miaware.client.features.modules.render.particles;

import net.minecraft.class_2960;

class K7b801c9bc83cdf759e extends Kbd989b7b534ee9cdce {
   public K7b801c9bc83cdf759e(final Kca48e46ecd4742eff6 var1, float x, float y, float z, float motionX, float motionY, float motionZ, int index, class_2960 identifier, float size, boolean rotate, int lifetime, float sp, float dy, boolean trail, int trailLength) {
      super(x, y, z, lifetime);
      super.gravityFalls(true).dropPhysics(true).size(size).index(index).motionX(motionX).motionY(motionY).motionZ(motionZ).identifier(identifier).rotating(rotate).spawnDuration(sp).dyingDuration(dy).trail(trail).trailLength((float)trailLength);
   }
}
