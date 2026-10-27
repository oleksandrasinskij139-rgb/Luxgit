package code.miaware.api.utils.render;

import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.other.K51aaa8d55539784c1d;
import code.miaware.api.system.files.Kc1f6f8c785f6a08448;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.framelimiter.K477c655086def6a209;
import code.miaware.client.features.modules.render.K45978f900e11d6615f;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.minecraft.class_10149;
import net.minecraft.class_10156;
import net.minecraft.class_276;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4587;
import net.minecraft.class_5944;
import net.minecraft.class_6367;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

public final class Kecb75e73598eb5799c implements K4600bef438dafc1a9c {
   private static final class_10156 upShader;
   private static final class_10156 downShader;
   public static final List<class_6367> fbos;
   private static boolean init;
   private static final K477c655086def6a209 f;
   private static long lastBlurRunNs;

   public static void load() {
      if (!init) {
         for(int i = 0; i <= Kfae29c687712462375.getPasses(); ++i) {
            fbos.add(K8ac6d1fa65e8d676d6());
         }

         K51aaa8d55539784c1d.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> recreate()));
         init = true;
      }

   }

   public static void recreate() {
      fbos.forEach(class_276::method_1238);
      fbos.clear();

      for(int i = 0; i <= Kfae29c687712462375.getPasses(); ++i) {
         fbos.add(K8ac6d1fa65e8d676d6());
      }

   }

   public static void render(class_4587 matrixStack) {
      if (!K45978f900e11d6615f.getInstance().isBlurDisabled()) {
         if (!(Kfae29c687712462375.getGlassy() >= 0.99F)) {
            long now = System.nanoTime();
            long minIntervalNs = 25000000L;
            if (now - lastBlurRunNs >= minIntervalNs) {
               lastBlurRunNs = now;
               f.execute(40, () -> {
                  int configuredPasses = Kfae29c687712462375.getPasses();
                  int actualPasses = Math.max(Math.min(configuredPasses, fbos.size() - 1), 1);
                  if (mc.method_47599() < 60) {
                     actualPasses = Math.max(actualPasses - 1, 1);
                  }

                  K1427d89b8f8fe78bdd(matrixStack, downShader, mc.method_1522(), (class_276)fbos.getFirst(), 0);

                  for(int i = 0; i < actualPasses; ++i) {
                     K1427d89b8f8fe78bdd(matrixStack, downShader, (class_276)fbos.get(i), (class_276)fbos.get(i + 1), i + 1);
                  }

                  for(int i = actualPasses; i > 0; --i) {
                     K1427d89b8f8fe78bdd(matrixStack, upShader, (class_276)fbos.get(i), (class_276)fbos.get(i - 1), i);
                  }

                  mc.method_1522().method_1235(false);
               });
            }
         }
      }
   }

   private static void K1427d89b8f8fe78bdd(class_4587 matrixStack, class_10156 shaderKey, class_276 source, class_276 destination, int pass) {
      destination.method_1235(false);
      RenderSystem.setShaderTexture(0, source.method_30277());
      class_5944 shader = RenderSystem.setShader(shaderKey);
      float blurPasses = (float)Math.max(fbos.size() - 1, 1);
      shader.method_34582("uHalfTexelSize").method_1255(0.5F / (float)source.field_1482, 0.5F / (float)source.field_1481);
      shader.method_34582("uOffset").method_1251(Kfae29c687712462375.getOffset() * ((float)pass / blurPasses));
      K0336b1e65a5a6bf213(matrixStack.method_23760().method_23761());
      destination.method_1240();
   }

   private static void K0336b1e65a5a6bf213(Matrix4f matrix4f) {
      float width = (float)mc.method_22683().method_4486();
      float height = (float)mc.method_22683().method_4502();
      class_287 builder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1592);
      builder.method_22918(matrix4f, 0.0F, 0.0F, 0.0F);
      builder.method_22918(matrix4f, 0.0F, height, 0.0F);
      builder.method_22918(matrix4f, width, height, 0.0F);
      builder.method_22918(matrix4f, width, 0.0F, 0.0F);
      class_286.method_43433(builder.method_60800());
   }

   private static class_6367 K8ac6d1fa65e8d676d6() {
      return new class_6367(mc.method_22683().method_4489(), mc.method_22683().method_4506(), false);
   }

   @Generated
   private Kecb75e73598eb5799c() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   static {
      upShader = new class_10156(Kc1f6f8c785f6a08448.getShader("post/blur/upscale"), class_290.field_1592, class_10149.field_53930);
      downShader = new class_10156(Kc1f6f8c785f6a08448.getShader("post/blur/downscale"), class_290.field_1592, class_10149.field_53930);
      fbos = new ArrayList();
      init = false;
      f = new K477c655086def6a209(false);
      lastBlurRunNs = 0L;
   }
}
