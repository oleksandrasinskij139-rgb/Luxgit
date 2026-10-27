package ru.phantom.mixin.minecraft.client.entity;

import II1II1II1II1II1II1II1II1.I5_i4.i_1111i1il1ll__lili1I__il;
import net.minecraft.class_1665;
import net.minecraft.class_1676;
import net.minecraft.class_243;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1676.class})
public class ProjectileEntityMixin {
   @Inject(
      method = {"method_7454"},
      at = {@At("HEAD")}
   )
   private void onProjectileEntityHit(class_3966 entityHitResult, CallbackInfo ci) {
      if (this instanceof class_1665) {
         class_1676 projectile = (class_1676)this;
         if (projectile.method_37908() != null && projectile.method_37908().method_8608()) {
            class_243 pos = entityHitResult.method_17784();
            class_243 velocity = projectile.method_18798();
            i_1111i1il1ll__lili1I__il.iI11i_llIIi_1IiII1li_lIl__I_Ii1(pos, velocity);
         }
      }
   }

   @Inject(
      method = {"method_24920"},
      at = {@At("HEAD")}
   )
   private void onProjectileBlockHit(class_3965 blockHitResult, CallbackInfo ci) {
      if (this instanceof class_1665) {
         class_1676 projectile = (class_1676)this;
         if (projectile.method_37908() != null && projectile.method_37908().method_8608()) {
            class_243 pos = blockHitResult.method_17784();
            class_243 velocity = projectile.method_18798();
            i_1111i1il1ll__lili1I__il.iI11i_llIIi_1IiII1li_lIl__I_Ii1(pos, velocity);
         }
      }
   }
}
