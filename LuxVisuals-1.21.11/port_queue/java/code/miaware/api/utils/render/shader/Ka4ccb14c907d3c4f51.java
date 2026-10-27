package code.miaware.api.utils.render.shader;

import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import com.mojang.blaze3d.systems.RenderSystem;
import lombok.Generated;
import net.minecraft.class_10149;
import net.minecraft.class_10156;
import net.minecraft.class_290;
import net.minecraft.class_5944;

public final class Ka4ccb14c907d3c4f51 implements K4600bef438dafc1a9c {
   private static final class_10156 entityEspShader;
   private static final class_10156 entityGlowShader;

   public static class_5944 applyEntityEspShader(float red, float green, float blue, float alpha) {
      class_5944 shader = RenderSystem.setShader(entityEspShader);
      if (shader != null && shader.method_34582("ColorModulator") != null) {
         shader.method_34582("ColorModulator").method_35657(red, green, blue, alpha);
      }

      return shader;
   }

   public static class_5944 applyEntityGlowShader(float red, float green, float blue, float alpha, float time) {
      class_5944 shader = RenderSystem.setShader(entityGlowShader);
      if (shader != null) {
         if (shader.method_34582("ColorModulator") != null) {
            shader.method_34582("ColorModulator").method_35657(red, green, blue, alpha);
         }

         if (shader.method_34582("Time") != null) {
            shader.method_34582("Time").method_1251(time);
         }
      }

      return shader;
   }

   public static void resetShader() {
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
   }

   @Generated
   private Ka4ccb14c907d3c4f51() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   @Generated
   public static class_10156 getEntityEspShader() {
      return entityEspShader;
   }

   @Generated
   public static class_10156 getEntityGlowShader() {
      return entityGlowShader;
   }

   static {
      entityEspShader = new class_10156(Kc1f6f8c785f6a08448.getShader("entity_esp"), class_290.field_1576, class_10149.field_53930);
      entityGlowShader = new class_10156(Kc1f6f8c785f6a08448.getShader("entity_glow"), class_290.field_1576, class_10149.field_53930);
   }
}
