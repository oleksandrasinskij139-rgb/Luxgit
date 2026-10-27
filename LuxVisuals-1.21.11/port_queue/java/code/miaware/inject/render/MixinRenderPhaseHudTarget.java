package code.miaware.inject.render;

import code.miaware.api.utils.render.Ke8433384857ddcab3d;
import code.miaware.client.features.modules.player.invsave.K402a602b5f2f2b781f;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_4668;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_4668.class})
public abstract class MixinRenderPhaseHudTarget {
   @Inject(
      method = {"startDrawing", "endDrawing"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$keepHudTarget(CallbackInfo ci) {
      if ((Ke8433384857ddcab3d.isCapturing() || K402a602b5f2f2b781f.isCapturing()) && this instanceof class_4668.class_4678) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"startDrawing"},
      at = {@At("TAIL")}
   )
   private void lux$keepHudCoverage(CallbackInfo ci) {
      if ((Ke8433384857ddcab3d.isCapturing() || K402a602b5f2f2b781f.isCapturing()) && this instanceof class_4668.class_4685 && GL11.glIsEnabled(3042)) {
         int src = GL11.glGetInteger(32969);
         int dst = GL11.glGetInteger(32968);
         if (src == 770 && dst == 771) {
            RenderSystem.blendFuncSeparate(src, dst, 1, 771);
         } else if (dst == 1) {
            RenderSystem.blendFuncSeparate(src, dst, 0, 1);
         }

      }
   }
}
