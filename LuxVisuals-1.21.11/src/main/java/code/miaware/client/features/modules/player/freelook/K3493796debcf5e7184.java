package code.miaware.client.features.modules.player.freelook;

public final class K3493796debcf5e7184 {
   private boolean active;
   private float yaw;
   private float pitch;

   public boolean active() {
      return this.active;
   }

   public float yaw() {
      return this.yaw;
   }

   public float pitch() {
      return this.pitch;
   }

   public void begin(float yaw, float pitch) {
      if (Float.isFinite(yaw) && Float.isFinite(pitch)) {
         this.yaw = K042cc5bef2b8b62606((double)yaw);
         this.pitch = Math.clamp(pitch, -90.0F, 90.0F);
         this.active = true;
      } else {
         this.stop();
      }
   }

   public void stop() {
      this.active = false;
   }

   public void rotate(double x, double y) {
      if (this.active && Double.isFinite(x) && Double.isFinite(y)) {
         this.yaw = K042cc5bef2b8b62606((double)this.yaw + x % (double)2400.0F * 0.15);
         this.pitch = (float)Math.clamp((double)this.pitch + y * 0.15, (double)-90.0F, (double)90.0F);
      }
   }

   private static float K042cc5bef2b8b62606(double value) {
      double result = value % (double)360.0F;
      return (float)(result >= (double)180.0F ? result - (double)360.0F : (result < (double)-180.0F ? result + (double)360.0F : result));
   }
}
