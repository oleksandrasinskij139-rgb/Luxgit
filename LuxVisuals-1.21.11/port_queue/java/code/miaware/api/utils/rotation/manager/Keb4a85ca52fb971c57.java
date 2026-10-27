package code.miaware.api.utils.rotation.manager;

import code.miaware.api.utils.math.K75fb14cd72c582f1b2;
import code.miaware.api.utils.rotation.Kef2fce79615318605c;
import lombok.Generated;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class Keb4a85ca52fb971c57 {
   private float yaw;
   private float pitch;
   public static final Keb4a85ca52fb971c57 DEFAULT = new Keb4a85ca52fb971c57(0.0F, 0.0F);

   public Keb4a85ca52fb971c57(float yaw, float pitch) {
      this.yaw = yaw;
      this.pitch = pitch;
   }

   public void setYaw(float yaw) {
      this.Kcfc98073cc7d635429();
      this.yaw = yaw;
   }

   public void setPitch(float pitch) {
      this.Kcfc98073cc7d635429();
      this.pitch = pitch;
   }

   public Keb4a85ca52fb971c57 adjustSensitivity() {
      double gcd = (double)K75fb14cd72c582f1b2.getGCD();
      Keb4a85ca52fb971c57 previousRotation = K960322f580344d3bad.getInstance().getServerRotation();
      float adjustedYaw = this.Kd9e67f27bdeba145a1(this.yaw, previousRotation.yaw, gcd);
      float adjustedPitch = this.Kd9e67f27bdeba145a1(this.pitch, previousRotation.pitch, gcd);
      return new Keb4a85ca52fb971c57(adjustedYaw, class_3532.method_15363(adjustedPitch, -90.0F, 90.0F));
   }

   private float Kd9e67f27bdeba145a1(float axisValue, float previousValue, double gcd) {
      float delta = axisValue - previousValue;
      return previousValue + (float)Math.round((double)delta / gcd) * (float)gcd;
   }

   public class_243 getVector() {
      float f = this.pitch * ((float)Math.PI / 180F);
      float g = -this.yaw * ((float)Math.PI / 180F);
      float h = class_3532.method_15362(g);
      float i = class_3532.method_15374(g);
      float j = class_3532.method_15362(f);
      float k = class_3532.method_15374(f);
      return new class_243((double)(i * j), (double)(-k), (double)(h * j));
   }

   public Keb4a85ca52fb971c57 rotationDeltaTo(Keb4a85ca52fb971c57 targetRotation) {
      return Kef2fce79615318605c.calculateDelta(this, targetRotation);
   }

   private void Kcfc98073cc7d635429() {
      if (this == DEFAULT) {
         throw new IllegalStateException("Rotation.DEFAULT is immutable");
      }
   }

   public String toString() {
      return "Rotation(yaw=" + this.yaw + ", pitch=" + this.pitch + ")";
   }

   @Generated
   public float getYaw() {
      return this.yaw;
   }

   @Generated
   public float getPitch() {
      return this.pitch;
   }
}
