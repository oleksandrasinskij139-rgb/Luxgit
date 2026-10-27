package code.miaware.client.features.modules.render.particles;

import net.minecraft.class_2960;

class K64aaf3466ded2a5ec8 extends Kbd989b7b534ee9cdce {
   public K64aaf3466ded2a5ec8(final K4dddebb6ed6d3a429d var1, float x, float y, float z, float motionX, float motionY, float motionZ, int index, class_2960 identifier, float size, boolean drop, boolean rotate, int lifetime, float sp, float dy, boolean trail, int trailLength) {
      super(x, y, z, lifetime);
      super.gravityFalls(false).dropPhysics(drop).size(size).index(index).motionX(motionX).motionY(motionY).motionZ(motionZ).identifier(identifier).rotating(rotate).spawnDuration(sp).dyingDuration(dy).trail(trail).trailLength((float)trailLength);
   }
}
