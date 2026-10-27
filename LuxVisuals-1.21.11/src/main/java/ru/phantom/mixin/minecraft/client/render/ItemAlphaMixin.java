package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I0_i9.Ii___iIIli1_iIIl_I_III____I;
import net.minecraft.class_10444;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin({class_10444.class})
public class ItemAlphaMixin {
   @ModifyVariable(
      method = {"method_65604"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private class_4597 phantom$fadeItem(class_4597 provider) {
      float alpha = Ii___iIIli1_iIIl_I_III____I.iii11_illll_llllIi1_i1__1I1_I1();
      return alpha >= 0.999F ? provider : (layer) -> new class_4588() {
            private final class_4588 delegate = provider.getBuffer(layer);

            public class_4588 method_22912(float x, float y, float z) {
               this.delegate.method_22912(x, y, z);
               return this;
            }

            public class_4588 method_1336(int r, int g, int b, int a) {
               this.delegate.method_1336(r, g, b, Math.round((float)a * alpha));
               return this;
            }

            public class_4588 method_22913(float u, float v) {
               this.delegate.method_22913(u, v);
               return this;
            }

            public class_4588 method_60796(int u, int v) {
               this.delegate.method_60796(u, v);
               return this;
            }

            public class_4588 method_22921(int u, int v) {
               this.delegate.method_22921(u, v);
               return this;
            }

            public class_4588 method_22914(float x, float y, float z) {
               this.delegate.method_22914(x, y, z);
               return this;
            }
         };
   }
}
