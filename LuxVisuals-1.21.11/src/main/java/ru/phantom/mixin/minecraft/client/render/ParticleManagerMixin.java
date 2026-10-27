package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I5_i4.i_i_III11iI_IlII11ll_1__l11;
import II1II1II1II1II1II1II1II1.I5_i4.iiIIliIli1Il_I_i_I1iiil11;
import II1II1II1II1II1II1II1II1.I9_i8.ii__ii_I_iiI__iII11111iIil;
import II1II1II1II1II1II1II1II1.l8_I7.II111_iIl_IlI1I_i_IiIili_i_iI;
import net.minecraft.class_238;
import net.minecraft.class_4184;
import net.minecraft.class_702;
import net.minecraft.class_703;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_702.class})
public class ParticleManagerMixin {
   @Inject(
      method = {"method_3058(Lnet/minecraft/class_703;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void Phantom$cancelVanillaParticles(class_703 particle, CallbackInfo ci) {
      if (iiIIliIli1Il_I_i_I1iiil11.lii1I11_i_l1il_liIiI111ilil1_("Vanilla Particles")) {
         ci.cancel();
      } else {
         if (i_i_III11iI_IlII11ll_1__l11.IIi1_l1_i1ilIII1lil1__i() && II111_iIl_IlI1I_i_IiIili_i_iI.mc.field_1773 != null && particle != null) {
            class_4184 camera = II111_iIl_IlI1I_i_IiIili_i_iI.mc.field_1773.method_19418();
            if (camera != null) {
               class_238 box = particle.method_3064();
               double px = (box.field_1323 + box.field_1320) * (double)0.5F;
               double py = (box.field_1322 + box.field_1325) * (double)0.5F;
               double pz = (box.field_1321 + box.field_1324) * (double)0.5F;
               if (!ii__ii_I_iiI__iII11111iIil.lIIl_i1i_il1I__I__1lIl_111l(particle, px, py, pz, camera.method_19326())) {
                  ci.cancel();
               }
            }
         }

      }
   }
}
