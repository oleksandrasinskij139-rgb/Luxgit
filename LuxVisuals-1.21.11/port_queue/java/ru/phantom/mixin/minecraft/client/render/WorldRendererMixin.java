package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I5_i4.l1_i_l_1il_____1lIl_iIlii;
import net.minecraft.class_4184;
import net.minecraft.class_757;
import net.minecraft.class_761;
import net.minecraft.class_9779;
import net.minecraft.class_9922;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_761.class})
public class WorldRendererMixin {
   @ModifyVariable(
      method = {"method_22710"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private boolean Phantom$cancelVanillaBlockOutline(boolean renderBlockOutline) {
      l1_i_l_1il_____1lIl_iIlii module = l1_i_l_1il_____1lIl_iIlii.iiI__lI_II__I_1l_1_III1l1ll();
      return module != null && module.isEnable() ? false : renderBlockOutline;
   }

   @Inject(
      method = {"method_22710"},
      at = {@At("TAIL")}
   )
   private void Phantom$renderBlockOverlay(class_9922 allocator, class_9779 tickCounter, boolean renderBlockOutline, class_4184 camera, class_757 gameRenderer, Matrix4f positionMatrix, Matrix4f projectionMatrix, CallbackInfo ci) {
      l1_i_l_1il_____1lIl_iIlii module = l1_i_l_1il_____1lIl_iIlii.iiI__lI_II__I_1l_1_III1l1ll();
      if (module != null) {
         module.render(positionMatrix, tickCounter.method_60637(true));
      }

   }
}
