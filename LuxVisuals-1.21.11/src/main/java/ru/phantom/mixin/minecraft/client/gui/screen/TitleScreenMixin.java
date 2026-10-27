package ru.phantom.mixin.minecraft.client.gui.screen;

import II1II1II1II1II1II1II1II1.l2_I1.ii1Ilil1_liI_iIi_1li11II;
import net.minecraft.class_2561;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_442;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_442.class})
public class TitleScreenMixin extends class_437 {
   protected TitleScreenMixin(class_2561 title) {
      super(title);
   }

   @Inject(
      method = {"method_25426"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void phantom$replaceWithMainMenu(CallbackInfo ci) {
      if (ii1Ilil1_liI_iIi_1li11II.l1Il1I1I1_i_i_Ii__i1_il) {
         if (this.field_22787 != null) {
            this.field_22787.method_1507(new ii1Ilil1_liI_iIi_1li11II());
         }

         ci.cancel();
      }

   }

   @Inject(
      method = {"method_25426"},
      at = {@At("TAIL")}
   )
   public void addAltManagerButton(CallbackInfo ci) {
      this.method_37063(class_4185.method_46430(class_2561.method_43470("Phantom Menu"), (button) -> {
         if (this.field_22787 != null) {
            ii1Ilil1_liI_iIi_1li11II.l1Il1I1I1_i_i_Ii__i1_il = true;
            this.field_22787.method_1507(new ii1Ilil1_liI_iIi_1li11II());
         }

      }).method_46434(10, 10, 90, 20).method_46431());
   }
}
