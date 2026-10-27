package ru.phantom.mixin.minecraft.client;

import dev.luxvisuals.menu.PX;
import dev.luxvisuals.menu.PhantomLoading;
import dev.luxvisuals.menu.PhantomMainMenu;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4011;
import net.minecraft.class_4071;
import net.minecraft.class_425;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_425.class})
public class SplashOverlayMixin {
   @Unique
   private PhantomLoading lux$loading;
   @Shadow
   private long field_17771;
   @Final
   @Shadow
   private Consumer<Optional<Throwable>> field_18218;
   @Shadow
   @Final
   private class_4011 field_17767;
   @Shadow
   private long field_18220;
   @Shadow
   @Final
   private boolean field_18219;

   @Inject(
      method = {"<init>(Lnet/minecraft/class_310;Lnet/minecraft/class_4011;Ljava/util/function/Consumer;Z)V"},
      at = {@At("RETURN")}
   )
   public void lux$init(class_310 var1, class_4011 var2, Consumer<Optional<Throwable>> var3, boolean var4, CallbackInfo var5) {
      this.lux$loading = new PhantomLoading();
   }

   @Inject(
      method = {"method_25394(Lnet/minecraft/class_332;IIF)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lux$render(class_332 var1, int var2, int var3, float var4, CallbackInfo var5) {
      var5.cancel();
      int var6 = var1.method_51421();
      int var7 = var1.method_51443();
      long var8 = PX.now();
      if (this.field_18219 && this.field_18220 == -1L) {
         this.field_18220 = var8;
      }

      float var10 = this.field_17771 > -1L ? (float)(var8 - this.field_17771) / 4000.0F : -1.0F;
      float var11 = this.field_18220 > -1L ? (float)(var8 - this.field_18220) / 500.0F : -1.0F;
      if (this.field_17771 == -1L && this.field_17767.method_18787() && (!this.field_18219 || var11 >= 2.0F)) {
         try {
            this.field_17767.method_18849();
            this.field_18218.accept(Optional.empty());
         } catch (Throwable var13) {
            this.field_18218.accept(Optional.of(var13));
         }

         this.field_17771 = var8;
         class_310 var12 = PX.mc();
         if (var12.field_1755 != null) {
            var12.field_1755.method_25423(var12, var6, var7);
         }
      }

      if (var10 >= 0.0F && var10 < 1.0F) {
         this.lux$loading.render(var1, var4, var10);
      }

      if (var10 >= 1.0F) {
         class_310 var14 = PX.mc();
         if (var14.field_1755 == null && var14.field_1687 == null) {
            var14.method_1507(new PhantomMainMenu());
         }

         var14.method_18502((class_4071)null);
      }

   }
}
