package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I5_i4.IIiI1iiI1l1__I_1i11IiIlIii_;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_266;
import net.minecraft.class_269;
import net.minecraft.class_332;
import net.minecraft.class_355;
import net.minecraft.class_4587;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_355.class})
public abstract class PlayerListHudMixin {
   @Inject(
      method = {"method_1919"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void Phantom$setupTabAnimation(class_332 context, int scaledWidth, class_269 scoreboard, class_266 objective, CallbackInfo ci) {
      IIiI1iiI1l1__I_1i11IiIlIii_ module = IIiI1iiI1l1__I_1i11IiIlIii_.i1I_1IlilIll1_l_11_iiIll();
      if (module != null && module.shouldSmoothTab()) {
         if (!module.isTabSyncRender()) {
            ci.cancel();
         } else {
            float value = module.getTabProgress();
            if (!module.isTabVisible()) {
               ci.cancel();
            } else {
               class_4587 matrices = context.method_51448();
               matrices.method_22903();
               float scale = 0.82F + 0.18F * value;
               matrices.method_46416((float)scaledWidth / 2.0F, 10.0F, 0.0F);
               matrices.method_22905(scale, scale, 1.0F);
               matrices.method_46416((float)(-scaledWidth) / 2.0F, -10.0F, 0.0F);
               matrices.method_46416(0.0F, -14.0F * (1.0F - value), 0.0F);
               RenderSystem.enableBlend();
               RenderSystem.defaultBlendFunc();
               RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, value);
            }
         }
      }
   }

   @Inject(
      method = {"method_1919"},
      at = {@At("RETURN")}
   )
   private void Phantom$cleanupTabAnimation(class_332 context, int scaledWidth, class_269 scoreboard, class_266 objective, CallbackInfo ci) {
      IIiI1iiI1l1__I_1i11IiIlIii_ module = IIiI1iiI1l1__I_1i11IiIlIii_.i1I_1IlilIll1_l_11_iiIll();
      if (module != null && module.shouldSmoothTab() && module.isTabVisible()) {
         context.method_51448().method_22909();
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         RenderSystem.disableBlend();
      }
   }
}
