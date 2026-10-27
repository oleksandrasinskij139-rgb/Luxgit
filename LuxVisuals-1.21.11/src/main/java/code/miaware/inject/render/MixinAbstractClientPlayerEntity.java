package code.miaware.inject.render;

import code.miaware.client.features.cosmetics.K697051202eac7cfc74;
import code.miaware.client.features.modules.render.Kc4e4e0706855d7b594;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2960;
import net.minecraft.class_742;
import net.minecraft.class_8685;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_742.class})
public abstract class MixinAbstractClientPlayerEntity {
   @Inject(
      method = {"getSkinTextures"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void onGetSkinTextures(CallbackInfoReturnable<class_8685> cir) {
      class_742 player = (class_742)this;
      class_2960 cosmeticCape = player.method_7340() ? K697051202eac7cfc74.getInstance().capeTexture() : null;
      if ((cosmeticCape != null || Kc4e4e0706855d7b594.isCapeEnabled()) && player.method_7340()) {
         class_2960 customCape = cosmeticCape != null ? cosmeticCape : Kc4e4e0706855d7b594.getInstance().getCape();
         if (customCape != null) {
            class_8685 original = (class_8685)cir.getReturnValue();
            class_1799 chestplate = player.method_31548().method_7372(2);
            boolean hasElytra = !chestplate.method_7960() && chestplate.method_31574(class_1802.field_8833);
            class_2960 capeTexture = hasElytra ? null : customCape;
            class_2960 elytraTexture = original.comp_1628();
            cir.setReturnValue(new class_8685(original.comp_1626(), original.comp_1911(), capeTexture, elytraTexture, original.comp_1629(), original.comp_1630()));
         }
      }

   }
}
