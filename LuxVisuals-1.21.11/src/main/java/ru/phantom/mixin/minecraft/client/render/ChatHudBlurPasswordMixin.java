package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.l8_I7.i11Il_l_1illl1_I_1Il1I11lli__l;
import net.minecraft.class_2561;
import net.minecraft.class_338;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin({class_338.class})
public class ChatHudBlurPasswordMixin {
   @ModifyVariable(
      method = {"method_1812(Lnet/minecraft/class_2561;)V"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private class_2561 phantom$blurPassword(class_2561 message) {
      i11Il_l_1illl1_I_1Il1I11lli__l module = i11Il_l_1illl1_I_1Il1I11lli__l.iIi_11I__i1__iiI1_llil_lI1l();
      if (module != null && module.isEnable() && message != null) {
         String content = message.getString();
         String masked = module.mask(content);
         return (class_2561)(content.equals(masked) ? message : class_2561.method_43470(masked).method_10862(message.method_10866()));
      } else {
         return message;
      }
   }
}
