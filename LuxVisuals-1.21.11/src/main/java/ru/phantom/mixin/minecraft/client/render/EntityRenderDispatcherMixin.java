package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I0_i9.II1ilil_lIi__I_IIi_1_I_ii;
import II1II1II1II1II1II1II1II1.I0_i9.iiIiIil1li_Iil__i1lI1l_;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_4604;
import net.minecraft.class_898;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_898.class})
public class EntityRenderDispatcherMixin {
   @Inject(
      method = {"method_3950"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void phantom$skipHiddenEntities(class_1297 entity, class_4604 frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
      if (cir.getReturnValueZ()) {
         boolean shouldCull = entity instanceof class_1542 ? iiIiIil1li_Iil__i1lI1l_.lI1liIIlIlIIliIiI1_lI1(entity) : iiIiIil1li_Iil__i1lI1l_.li__1__iIl1_1__1_Il_l1llliI_(entity);
         if (shouldCull || II1ilil_lIi__I_IIi_1_I_ii.iIiIIlI__i1ii1i11_l1I1iIIi(entity)) {
            cir.setReturnValue(false);
         }

      }
   }
}
