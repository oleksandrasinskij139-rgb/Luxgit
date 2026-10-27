package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I0_i9.l__ilIl_111iiil_Iill1_1liili;
import II1II1II1II1II1II1II1II1.I5_i4.Il1ll11iiiIIIlI1IliI_1l;
import II1II1II1II1II1II1II1II1.I5_i4.iiIIliIli1Il_I_i_I1iiil11;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.class_266;
import net.minecraft.class_329;
import net.minecraft.class_332;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({class_329.class})
public class CustomScoreboardMixin {
   @WrapMethod(
      method = {"method_1757(Lnet/minecraft/class_332;Lnet/minecraft/class_266;)V"}
   )
   private void phantom$customSidebar(class_332 context, class_266 objective, Operation<Void> original) {
      if (!iiIIliIli1Il_I_i_I1iiil11.lii1I11_i_l1il_liIiI111ilil1_("Scoreboard")) {
         if (Il1ll11iiiIIIlI1IliI_1l.iI_i_1_i1i1ll11lII1i111_i()) {
            l__ilIl_111iiil_Iill1_1liili.li11_l11iii_1l_I11i__liIiil__il(context, objective);
         } else {
            original.call(new Object[]{context, objective});
         }

      }
   }
}
