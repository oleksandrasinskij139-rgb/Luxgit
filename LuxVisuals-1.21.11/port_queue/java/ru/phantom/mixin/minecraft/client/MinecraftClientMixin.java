package ru.phantom.mixin.minecraft.client;

import II1II1II1II1II1II1II1II1.I0_i9.I_lII_11_Ii1l1i1lI__i_l_il1;
import II1II1II1II1II1II1II1II1.I5_i4.i_i_III11iI_IlII11ll_1__l11;
import II1II1II1II1II1II1II1II1.I5_i4.iiIIliIli1Il_I_i_I1iiil11;
import II1II1II1II1II1II1II1II1.I8_i7.Ill_iiIll1___l1_l1li1I1l11I;
import II1II1II1II1II1II1II1II1.l3_I2.lil_I__iiiI1__IlIIl1i1IIliII_;
import II1II1II1II1II1II1II1II1.l4_I3.IIiIiiI1i1IIIi1l_11iI__li_1;
import II1II1II1II1II1II1II1II1.l8_I7.II111_iIl_IlI1I_i_IiIili_i_iI;
import II1II1II1II1II1II1II1II1.l8_I7.l__11IIIii1I__I1i1l1Ii11iI_11l;
import II1II1II1II1II1II1II1II1.l8_I7.lliI1II_i1_lillIl1Il1i;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.minecraft.class_442;
import net.minecraft.class_542;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.phantom.Phantom;
import ru.phantom.protection.client.MinecraftClientMixinProtection;

@Mixin({class_310.class})
public class MinecraftClientMixin implements IIiIiiI1i1IIIi1l_11iI__li_1 {
   @Unique
   class_542 runArgs;

   @Inject(
      method = {"method_1507"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void phantom$onSetScreen(class_437 screen, CallbackInfo ci) {
      boolean var10000 = screen instanceof class_442;
   }

   @Inject(
      method = {"method_1574"},
      at = {@At("HEAD")}
   )
   public void tick(CallbackInfo ci) {
      Phantom.getInstance().getEventBus().post(new lliI1II_i1_lillIl1Il1i());
      l__11IIIii1I__I1i1l1Ii11iI_11l.lI_lllII_l_lI1i_i1I11I_I1i1I1();
   }

   @Inject(
      method = {"method_1523"},
      at = {@At("HEAD")}
   )
   private void phantom$beforeRender(boolean tick, CallbackInfo ci) {
      if (i_i_III11iI_IlII11ll_1__l11.I_i1I__li_1IIllI11IlIi()) {
         I_lII_11_Ii1l1i1lI__i_l_il1.iii1i_l1li_I_1IIIl_11_Iii().tickFrame();
      }

   }

   @Inject(
      method = {"method_1523"},
      at = {@At("TAIL")}
   )
   private void phantom$afterRender(boolean tick, CallbackInfo ci) {
      lil_I__iiiI1__IlIIl1i1IIliII_.I_i_I_i1I__I__iIiilii_lii_();
   }

   @Inject(
      method = {"<init>"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_310;method_15993()V"
)}
   )
   public void initializeClient(class_542 args, CallbackInfo ci) {
      MinecraftClientMixinProtection.init();
      this.runArgs = args;
   }

   @Inject(
      method = {"method_1490"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_310;close()V",
   shift = Shift.AFTER
)}
   )
   public void shutdownClient(CallbackInfo ci) {
      MinecraftClientMixinProtection.shutdown();
   }

   @Inject(
      method = {"method_27022"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void Phantom$cancelPlayerOutline(class_1297 entity, CallbackInfoReturnable<Boolean> cir) {
      if (entity instanceof class_1657 && iiIIliIli1Il_I_i_I1iiil11.lii1I11_i_l1il_liIiI111ilil1_("Свечение игроков")) {
         cir.setReturnValue(false);
      }

   }

   @Inject(
      method = {"method_1493"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void Phantom$noPauseInMenu(CallbackInfoReturnable<Boolean> cir) {
      if (II111_iIl_IlI1I_i_IiIili_i_iI.mc.field_1755 instanceof Ill_iiIll1___l1_l1li1I1l11I) {
         cir.setReturnValue(false);
      }

   }

   public class_542 hachclient4_0$args() {
      return this.runArgs;
   }
}
