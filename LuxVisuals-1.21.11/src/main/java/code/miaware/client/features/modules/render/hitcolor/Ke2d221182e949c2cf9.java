package code.miaware.client.features.modules.render.hitcolor;

import code.miaware.client.features.modules.render.Kd9a0d0354b82743c74;
import code.miaware.inject.render.IOverlayTexture;
import java.util.Objects;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_310;

public final class Ke2d221182e949c2cf9 {
   private static int[] original;
   private static int applied;
   private static boolean customApplied;
   private static float appliedIntensity = -1.0F;
   private static class_1011 source;
   public static boolean renderingHurt;
   public static int renderingOverlay;

   private Ke2d221182e949c2cf9() {
   }

   public static void sync() {
      Kd9a0d0354b82743c74 module = Kd9a0d0354b82743c74.getInstance();
      if (module != null && module.isEnabled()) {
         apply(module.getHitColor().getRGB(), module.intensity.getValue() / 100.0F);
      } else if (customApplied) {
         Kccd5897bf8c43d558d();
      }

   }

   private static class_1011 image() {
      class_310 mc = class_310.method_1551();
      if (mc != null && mc.field_1773 != null) {
         class_1043 texture = ((IOverlayTexture)mc.field_1773.method_22975()).lux$texture();
         class_1011 image = texture != null ? texture.method_4525() : null;
         if (image != null && source != image) {
            source = image;
            customApplied = false;
            appliedIntensity = -1.0F;
            int w = image.method_4307();
            int h = image.method_4323();
            original = new int[w * h];

            for(int y = 0; y < h; ++y) {
               for(int x = 0; x < w; ++x) {
                  original[y * w + x] = image.method_61940(x, y);
               }
            }
         }

         return image;
      } else {
         return null;
      }
   }

   private static void apply(int color, float intensity) {
      class_1011 image = image();
      if (image != null) {
         if (!customApplied || color != applied || intensity != appliedIntensity) {
            int w = image.method_4307();

            for(int y = 0; y < Math.min(8, image.method_4323()); ++y) {
               for(int x = 0; x < w; ++x) {
                  image.method_61941(x, y, K6c4a2ec8c78f89fb31.pixel(color, intensity, y * 16 + x));
               }
            }

            upload();
            applied = color;
            customApplied = true;
            appliedIntensity = intensity;
         }
      }
   }

   private static void Kccd5897bf8c43d558d() {
      class_1011 image = image();
      if (image != null && original != null) {
         int w = image.method_4307();
         int h = image.method_4323();
         if (original.length == w * h) {
            for(int y = 0; y < h; ++y) {
               for(int x = 0; x < w; ++x) {
                  image.method_61941(x, y, original[y * w + x]);
               }
            }

            upload();
         }

         customApplied = false;
         appliedIntensity = -1.0F;
      }
   }

   private static void upload() {
      class_310 mc = class_310.method_1551();
      class_1043 texture = ((IOverlayTexture)mc.field_1773.method_22975()).lux$texture();
      if (mc.method_18854()) {
         texture.method_4524();
      } else {
         Objects.requireNonNull(texture);
         mc.execute(texture::method_4524);
      }

   }
}
