package ru.phantom.mixin.minecraft.client;

import II1II1II1II1II1II1II1II1.I0_i9.I_lII_11_Ii1l1i1lI__i_l_il1;
import II1II1II1II1II1II1II1II1.I0_i9.Ii1_l_ll_II1__l__Ill__ll11il;
import II1II1II1II1II1II1II1II1.I2_i1.l_Il_1ll__lIIIll1_llIl_i1l1lII;
import II1II1II1II1II1II1II1II1.I5_i4.Illi_lI1l___i_iII1_IliIIIII1lI;
import II1II1II1II1II1II1II1II1.I5_i4.i_i_III11iI_IlII11ll_1__l11;
import II1II1II1II1II1II1II1II1.I5_i4.ilI1_l1II1II__1ii1_il_liIIl1;
import II1II1II1II1II1II1II1II1.l8_I7.II111_iIl_IlI1I_i_IiIili_i_iI;
import net.minecraft.class_310;
import net.minecraft.class_312;
import net.minecraft.class_364;
import net.minecraft.class_7528;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.phantom.Phantom;

@Mixin({class_312.class})
public class MouseMixin {
   @Final
   @Shadow
   private class_310 field_1779;

   @Inject(
      method = {"method_1601"},
      at = {@At("HEAD")}
   )
   public void onMouseButtonHook(long window, int button, int action, int mods, CallbackInfo ci) {
      if (button != -1 && window == this.field_1779.method_22683().method_4490()) {
         Phantom.getInstance().getEventBus().post(new l_Il_1ll__lIIIll1_llIl_i1l1lII(button, action));
      }

   }

   @Inject(
      method = {"method_1598"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onMouseScrollHook(long window, double horizontal, double vertical, CallbackInfo ci) {
      if (window == this.field_1779.method_22683().method_4490() && this.field_1779.field_1755 != null && Math.abs(vertical) > 0.001) {
         class_312 mouse = (class_312)this;
         double mouseX = mouse.method_1603() * (double)this.field_1779.method_22683().method_4486() / (double)this.field_1779.method_22683().method_4480();
         double mouseY = mouse.method_1604() * (double)this.field_1779.method_22683().method_4502() / (double)this.field_1779.method_22683().method_4507();

         for(class_364 child : this.field_1779.field_1755.method_25396()) {
            if (child instanceof class_7528) {
               class_7528 widget = (class_7528)child;
               if (widget.method_25405(mouseX, mouseY)) {
                  Ii1_l_ll_II1__l__Ill__ll11il.iiiii1I_iI__111I1_1IlI1Iilil1i(widget, vertical);
                  ci.cancel();
                  return;
               }
            }
         }
      }

   }

   @Inject(
      method = {"method_55793()V"},
      at = {@At("RETURN")}
   )
   private void tick(CallbackInfo ci) {
      if (!i_i_III11iI_IlII11ll_1__l11.I_i1I__li_1IIllI11IlIi() || I_lII_11_Ii1l1i1lI__i_l_il1.iii1i_l1li_I_1IIIl_11_Iii().shouldRenderCurrentFrame) {
         if (ilI1_l1II1II__1ii1_il_liIIl1.lI_l1_li_llllIil1_i1IiII11l1() != ilI1_l1II1II__1ii1_il_liIIl1.ilII_I_1__ii_IiI__l___()) {
            GLFW.glfwSetCursor(II111_iIl_IlI1I_i_IiIili_i_iI.mc.method_22683().method_4490(), ilI1_l1II1II__1ii1_il_liIIl1.lI_l1_li_llllIil1_i1IiII11l1().getCode());
         }

         ilI1_l1II1II__1ii1_il_liIIl1.I_iiII__Il_lII1l__IiI_(ilI1_l1II1II__1ii1_il_liIIl1.lI_l1_li_llllIil1_i1IiII11l1());
         ilI1_l1II1II__1ii1_il_liIIl1.I_I1iii11IlI_lli_I_IiI(Illi_lI1l___i_iII1_IliIIIII1lI.DEFAULT);
      }
   }
}
