package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I5_i4.IIiI1iiI1l1__I_1i11IiIlIii_;
import II1II1II1II1II1II1II1II1.l8_I7.II1ll__i1IIIii_l11IlIilI1i_lii_;
import II1II1II1II1II1II1II1II1.l8_I7.illI1_11i_1__Iii_11I_11i11i;
import net.minecraft.class_1297;
import net.minecraft.class_1922;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_5498;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({class_4184.class})
public abstract class CameraMixin {
   @Unique
   private Boolean Phantom$lastThirdPerson;

   @Shadow
   protected abstract float method_19318(float var1);

   @ModifyArgs(
      method = {"method_19321"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_4184;method_19325(FF)V"
)
   )
   private void Phantom$freeLookRotation(Args args, class_1922 area, class_1297 focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta) {
      if (II1ll__i1IIIii_l11IlIilI1i_lii_.Iii___1_1i_1i111IlI_l1_i1 && focusedEntity instanceof illI1_11i_1__Iii_11I_11i11i camera) {
         args.set(0, camera.getCameraYaw());
         args.set(1, camera.getCameraPitch());
      }

   }

   @Redirect(
      method = {"method_19321"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_4184;method_19318(F)F"
)
   )
   private float Phantom$smoothF5(class_4184 instance, float desiredDistance) {
      IIiI1iiI1l1__I_1i11IiIlIii_ module = IIiI1iiI1l1__I_1i11IiIlIii_.i1I_1IlilIll1_l_11_iiIll();
      if (module != null && module.shouldSmoothF5()) {
         boolean thirdPerson = class_310.method_1551().field_1690.method_31044() != class_5498.field_26664;
         if (this.Phantom$lastThirdPerson == null || this.Phantom$lastThirdPerson != thirdPerson) {
            module.resetThirdPerson();
            this.Phantom$lastThirdPerson = thirdPerson;
         }

         return this.method_19318(module.smoothThirdPersonDistance(desiredDistance, thirdPerson));
      } else {
         return this.method_19318(desiredDistance);
      }
   }
}
