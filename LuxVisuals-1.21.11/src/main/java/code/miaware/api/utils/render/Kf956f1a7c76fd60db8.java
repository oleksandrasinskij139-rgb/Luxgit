package code.miaware.api.utils.render;

import java.awt.Color;
import net.minecraft.class_4588;

public class Kf956f1a7c76fd60db8 implements class_4588 {
   private final class_4588 delegate;
   private final Color color;
   private final float intensity;

   public Kf956f1a7c76fd60db8(class_4588 delegate, Color color, float intensity) {
      this.delegate = delegate;
      this.color = color;
      this.intensity = intensity;
   }

   public class_4588 method_22912(float x, float y, float z) {
      return this.delegate.method_22912(x, y, z);
   }

   public class_4588 method_1336(int red, int green, int blue, int alpha) {
      return this.delegate.method_1336(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), this.color.getAlpha());
   }

   public class_4588 method_22913(float u, float v) {
      return this.delegate.method_22913(u, v);
   }

   public class_4588 method_60796(int u, int v) {
      return this.delegate.method_60796(u, v);
   }

   public class_4588 method_22921(int u, int v) {
      return this.delegate.method_22921(u, v);
   }

   public class_4588 method_60803(int uv) {
      return this.delegate.method_60803(uv);
   }

   public class_4588 method_22914(float x, float y, float z) {
      return this.delegate.method_22914(x, y, z);
   }
}
