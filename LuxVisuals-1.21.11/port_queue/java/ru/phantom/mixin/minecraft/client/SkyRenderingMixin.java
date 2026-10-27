package ru.phantom.mixin.minecraft.client;

import II1II1II1II1II1II1II1II1.I5_i4.ilI111Il11__1II1IiI1__I1i1i;
import II1II1II1II1II1II1II1II1.I6_i5.IIIiii1liIIl__1i_I_iIi1l_1I_l1;
import II1II1II1II1II1II1II1II1.l9_I8.iIlIIii____Ill___1ii_111IIl1;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_9975;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_9975.class})
public class SkyRenderingMixin {
   @Inject(
      method = {"method_62302(FFF)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderCustomSkybox(float red, float green, float blue, CallbackInfo info) {
      ilI111Il11__1II1IiI1__I1i1i ambience = this.getAmbience();
      if (ambience != null && ambience.shouldRenderSkyShader()) {
         Color adjustedColor = ambience.getAdjustedSkyColor();
         float dimFactor = Math.max(0.0F, Math.min(1.0F, 1.0F - ambience.sky_dimness.getValue()));
         GL11.glClearColor(red * dimFactor, green * dimFactor, blue * dimFactor, 1.0F);
         GL11.glClear(16640);
         RenderSystem.depthMask(false);
         RenderSystem.disableDepthTest();
         float time = ambience.getSkyShaderTime();
         float opacity = 1.0F;
         iIlIIii____Ill___1ii_111IIl1.Ili_lii_lIl1l_IlliII_IIl(IIIiii1liIIl__1i_I_iIi1l_1I_l1.I_IIll_I1_l1i1l_I1__I1__(), adjustedColor, time, opacity, dimFactor);
         RenderSystem.enableDepthTest();
         RenderSystem.depthMask(true);
         info.cancel();
      }

   }

   @Inject(
      method = {"close()V"},
      at = {@At("HEAD")}
   )
   private void closeCustomSkybox(CallbackInfo info) {
      iIlIIii____Ill___1ii_111IIl1.I_1lIiiI_llIlliI__i1lil_il();
   }

   @Redirect(
      method = {"method_62310(Lnet/minecraft/class_9958;FLnet/minecraft/class_4587;)V"},
      at = @At(
   value = "INVOKE",
   target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderColor(FFFF)V",
   ordinal = 0
)
   )
   private void redirectStarColor(float red, float green, float blue, float alpha) {
      ilI111Il11__1II1IiI1__I1i1i ambience = this.getAmbience();
      if (ambience != null && ambience.shouldChangeStars()) {
         Color color = ambience.stars_color.getColor();
         RenderSystem.setShaderColor((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, (float)color.getAlpha() / 255.0F);
      } else {
         RenderSystem.setShaderColor(red, green, blue, alpha);
      }

   }

   private ilI111Il11__1II1IiI1__I1i1i getAmbience() {
      return ilI111Il11__1II1IiI1__I1i1i.iI1__1_l_11i1ll1l11i_11i__1l();
   }
}
