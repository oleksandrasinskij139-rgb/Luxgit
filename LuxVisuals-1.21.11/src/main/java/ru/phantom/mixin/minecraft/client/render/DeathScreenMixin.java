package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.l8_I7.li_Iillii_1l_11l1_lIii1_;
import net.minecraft.class_332;
import net.minecraft.class_418;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_418.class})
public class DeathScreenMixin {
   @Inject(
      method = {"method_25394"},
      at = {@At("HEAD")}
   )
   public void render(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      li_Iillii_1l_11l1_lIii1_.IiI_I__II__il_i1I__Il_I().death();
   }
}
