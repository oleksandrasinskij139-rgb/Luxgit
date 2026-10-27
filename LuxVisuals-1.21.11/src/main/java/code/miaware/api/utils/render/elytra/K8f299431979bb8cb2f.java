package code.miaware.api.utils.render.elytra;

public final class K8f299431979bb8cb2f {
   public static final int MAX_RINGS = 12;
   public static final double LIFETIME = 0.95;
   private final K571a00567230f04d40[] rings = new K571a00567230f04d40[12];
   private int cursor;
   private double time;
   private double speed;
   private double intensity;
   private double previousIntensity;
   private double boost;
   private double previousBoost;
   private double sinceRing = (double)10.0F;
   private double distance;
   private boolean flying;

   public K8f299431979bb8cb2f() {
      for(int i = 0; i < this.rings.length; ++i) {
         this.rings[i] = new K571a00567230f04d40();
      }

   }

   public K571a00567230f04d40[] rings() {
      return this.rings;
   }

   public void reset() {
      this.cursor = 0;
      this.time = this.speed = this.intensity = this.previousIntensity = this.boost = this.previousBoost = this.distance = (double)0.0F;
      this.sinceRing = (double)10.0F;
      this.flying = false;

      for(K571a00567230f04d40 ring : this.rings) {
         ring.born = (double)-10.0F;
      }

   }

   public double time(float partial) {
      return this.time + clamp((double)partial) * 0.05;
   }

   public double intensity(float partial) {
      return this.previousIntensity + (this.intensity - this.previousIntensity) * clamp((double)partial);
   }

   public double boost(float partial) {
      return this.previousBoost + (this.boost - this.previousBoost) * clamp((double)partial);
   }

   public float expandedFov(float original, float maximumExtra, float partial) {
      float extra = (float)Math.min(1.15, this.intensity(partial) + this.boost(partial) * 0.22) * maximumExtra;
      return original + Math.min(extra, Math.max(0.0F, 150.0F - original));
   }

   public static double clamp(double value) {
      return Math.max((double)0.0F, Math.min((double)1.0F, value));
   }

   public void tick(boolean gliding, double newSpeed, double x, double y, double z, double dx, double dy, double dz, double sensitivity) {
      this.time += 0.05;
      this.previousIntensity = this.intensity;
      this.previousBoost = this.boost;
      this.sinceRing += 0.05;
      if (Double.isFinite(newSpeed) && Double.isFinite(x + y + z + dx + dy + dz)) {
         this.boost *= Math.exp(-0.10416666666666667);
         double acceleration = gliding && this.flying ? newSpeed - this.speed : (double)0.0F;
         boolean burst = gliding && newSpeed > (double)8.0F && acceleration > 1.15 / Math.max((double)0.25F, sensitivity);
         if (burst) {
            this.boost = Math.max(this.boost, Math.max(0.4, clamp(acceleration * sensitivity / (double)6.0F)));
         }

         double target = gliding ? clamp((newSpeed - (double)10.0F) / (double)34.0F + this.boost * 0.55) : (double)0.0F;
         this.intensity += (target - this.intensity) * ((double)1.0F - Math.exp(-0.05 / (target > this.intensity ? 0.16 : 0.38)));
         if (!gliding) {
            this.boost *= (double)0.75F;
         }

         if (gliding) {
            this.distance += newSpeed * 0.05;
            boolean trail = newSpeed > (double)20.0F && this.distance > (double)5.5F && this.sinceRing >= 0.19;
            if (burst && this.sinceRing >= 0.14 || trail) {
               double length = Math.sqrt(dx * dx + dy * dy + dz * dz);
               if (length > 1.0E-6) {
                  dx /= length;
                  dy /= length;
                  dz /= length;
                  K571a00567230f04d40 ring = this.rings[this.cursor];
                  this.cursor = (this.cursor + 1) % 12;
                  ring.x = x - dx * 1.15;
                  ring.y = y - dy * 1.15;
                  ring.z = z - dz * 1.15;
                  ring.dx = dx;
                  ring.dy = dy;
                  ring.dz = dz;
                  if (Math.abs(dy) < 0.92) {
                     ring.ux = -dz;
                     ring.uy = (double)0.0F;
                     ring.uz = dx;
                  } else {
                     ring.ux = (double)0.0F;
                     ring.uy = dz;
                     ring.uz = -dy;
                  }

                  double uLength = Math.sqrt(ring.ux * ring.ux + ring.uy * ring.uy + ring.uz * ring.uz);
                  ring.ux /= uLength;
                  ring.uy /= uLength;
                  ring.uz /= uLength;
                  ring.vx = dy * ring.uz - dz * ring.uy;
                  ring.vy = dz * ring.ux - dx * ring.uz;
                  ring.vz = dx * ring.uy - dy * ring.ux;
                  ring.born = this.time;
                  ring.strength = Math.max((double)0.25F, clamp(this.intensity + this.boost * 0.6));
                  ring.phase = (double)this.cursor * 2.3999632297 + this.time * 0.7;
                  this.sinceRing = (double)0.0F;
                  this.distance = (double)0.0F;
               }
            }
         } else {
            this.distance = (double)0.0F;
         }

         this.speed = newSpeed;
         this.flying = gliding;
      } else {
         this.reset();
      }
   }
}
