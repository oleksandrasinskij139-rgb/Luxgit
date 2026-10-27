package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I0_i9.I1l_il_lI1_IlliiIll_iI111l1_I;
import II1II1II1II1II1II1II1II1.I0_i9.II1I_1i1Il_1I_lI_l_Ii__;
import II1II1II1II1II1II1II1II1.I0_i9.I_lII_11_Ii1l1i1lI__i_l_il1;
import II1II1II1II1II1II1II1II1.I0_i9.Iii___11l1li1___11I1l_;
import II1II1II1II1II1II1II1II1.I0_i9.l1IIl1IIi11Iil1lI1lI1I11Ii;
import II1II1II1II1II1II1II1II1.I5_i4.I_l__Il_IiI_1I111IlIl1;
import II1II1II1II1II1II1II1II1.I5_i4.IiI_1_l_Il_1_IllIi__1l_Iii1_1l;
import II1II1II1II1II1II1II1II1.I5_i4.IiIiIlli_1l_ilII1ll1I1;
import II1II1II1II1II1II1II1II1.I5_i4.i_i_III11iI_IlII11ll_1__l11;
import II1II1II1II1II1II1II1II1.I5_i4.iiIIliIli1Il_I_i_I1iiil11;
import II1II1II1II1II1II1II1II1.I6_i5.l1___I_lIi__1l1iI_liIi__;
import II1II1II1II1II1II1II1II1.I9_i8.Ii_I___iiiI11I11iiIi1iilllI1_i;
import II1II1II1II1II1II1II1II1.l2_I1.i1I_lIi11lII1__illli_l_1l1_iI1;
import II1II1II1II1II1II1II1II1.l8_I7.II111_iIl_IlI1I_i_IiIili_i_iI;
import II1II1II1II1II1II1II1II1.l8_I7.I_iI1I1_ll11_1ii_1lilI_;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import net.minecraft.class_10156;
import net.minecraft.class_310;
import net.minecraft.class_3262;
import net.minecraft.class_3298;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_5912;
import net.minecraft.class_757;
import net.minecraft.class_9779;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.phantom.Phantom;

@Mixin({class_757.class})
public abstract class GameRendererMixin {
   @Shadow
   @Final
   private class_310 field_4015;
   @Shadow
   private float field_4005;
   @Shadow
   private float field_3988;
   @Shadow
   private float field_4004;

   @Inject(
      method = {"method_34521"},
      at = {@At("RETURN")}
   )
   private void phantom$preloadCustomShaders(class_5912 factory, CallbackInfo ci) {
      class_5912 modFactory = (identifier) -> {
         Optional<class_3298> res = factory.method_14486(identifier);
         if (res.isPresent()) {
            return res;
         } else {
            String var10000 = identifier.method_12836();
            String path = "/assets/" + var10000 + "/" + identifier.method_12832();
            return GameRendererMixin.class.getResource(path) != null ? Optional.of(new class_3298((class_3262)null, () -> {
               InputStream in = GameRendererMixin.class.getResourceAsStream(path);

               String content;
               label71: {
                  InputStream inc;
                  try {
                     if (in == null) {
                        content = null;
                        break label71;
                     }

                     content = new String(in.readAllBytes(), StandardCharsets.UTF_8);
                     if (content.contains("#moj_import <phantom:common.glsl>")) {
                        inc = GameRendererMixin.class.getResourceAsStream("/assets/phantom/shaders/include/common.glsl");

                        try {
                           if (inc != null) {
                              String common = new String(inc.readAllBytes(), StandardCharsets.UTF_8);
                              content = content.replace("#moj_import <phantom:common.glsl>", common);
                           }
                        } catch (Throwable var8) {
                           if (inc != null) {
                              try {
                                 inc.close();
                              } catch (Throwable var7) {
                                 var8.addSuppressed(var7);
                              }
                           }

                           throw var8;
                        }

                        if (inc != null) {
                           inc.close();
                        }
                     }

                     inc = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
                  } catch (Throwable var9) {
                     if (in != null) {
                        try {
                           in.close();
                        } catch (Throwable var6) {
                           var9.addSuppressed(var6);
                        }
                     }

                     throw var9;
                  }

                  if (in != null) {
                     in.close();
                  }

                  return inc;
               }

               if (in != null) {
                  in.close();
               }

               return content;
            })) : Optional.empty();
         }
      };

      try {
         class_310 clientInstance = this.field_4015 != null ? this.field_4015 : class_310.method_1551();
         clientInstance.method_62887().method_62944(modFactory, new class_10156[]{l1IIl1IIi11Iil1lI1lI1I11Ii.lIIlIi1iiliIl_i_l1lIii1_I_1Il_, I1l_il_lI1_IlliiIll_iI111l1_I.IIi1IIiIll_11_I__iiIIll1___, Iii___11l1li1___11I1l_.i_iIi_i_I1lIIiI_iIilI1Il11I});
      } catch (Throwable t) {
         t.printStackTrace();
      }

   }

   @Inject(
      method = {"method_3192"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void phantom$onRender(class_9779 tickCounter, boolean tick, CallbackInfo ci) {
      II1I_1i1Il_1I_lI_l_Ii__.illII__l_i__l_i1_l11Ii();
      if (i_i_III11iI_IlII11ll_1__l11.I_i1I__li_1IIllI11IlIi()) {
         I_lII_11_Ii1l1i1lI__i_l_il1 renderManager = I_lII_11_Ii1l1i1lI__i_l_il1.iii1i_l1li_I_1IIIl_11_Iii();
         if (renderManager.isInitialized() && !renderManager.shouldRender() && II111_iIl_IlI1I_i_IiIili_i_iI.mc.field_1755 == null) {
            ci.cancel();
         } else {
            I_lII_11_Ii1l1i1lI__i_l_il1.ll1lii1_i__i11llIlill11i_Il1();
         }
      } else {
         I_lII_11_Ii1l1i1lI__i_l_il1.ll1lii1_i__i11llIlill11i_Il1();
      }

   }

   @Inject(
      method = {"method_3188"},
      at = {@At(
   value = "INVOKE_STRING",
   target = "Lnet/minecraft/class_3695;method_15405(Ljava/lang/String;)V",
   args = {"ldc=hand"}
)}
   )
   private void onRenderWorld(class_9779 tickCounter, CallbackInfo ci, @Local(ordinal = 0) Matrix4f projection, @Local(ordinal = 2) Matrix4f view, @Local(ordinal = 1) float tickDelta, @Local class_4587 matrices) {
      i1I_lIi11lII1__illli_l_1l1_iI1.IliI1IIli1Ilil11iI__1_i(view, projection);
   }

   @Shadow
   public abstract float method_32796();

   @Inject(
      method = {"method_22973"},
      at = {@At("TAIL")},
      cancellable = true
   )
   private void phantom$storeAspectRatio(float fovDegrees, CallbackInfoReturnable<Matrix4f> cir) {
      IiI_1_l_Il_1_IllIi__1l_Iii1_1l aspectRatio = IiI_1_l_Il_1_IllIi__1l_Iii1_1l.Ii1ll1_iI1iliIi_llII1Il1IIiiI1i();
      if (aspectRatio != null && aspectRatio.isEnable()) {
         Matrix4f original = (Matrix4f)cir.getReturnValue();
         float fovY = 2.0F * (float)Math.atan((double)(1.0F / original.m11()));
         Matrix4f modified = new Matrix4f();
         if (this.field_4005 != 1.0F) {
            modified.translate(this.field_3988, -this.field_4004, 0.0F);
            modified.scale(this.field_4005, this.field_4005, 1.0F);
         }

         modified.perspective(fovY, aspectRatio.getScale(), 0.05F, this.method_32796());
         cir.setReturnValue(modified);
      }

   }

   @Inject(
      method = {"method_3188"},
      at = {@At("RETURN")}
   )
   private void Phantom$motionBlurAfterWorld(class_9779 tickCounter, CallbackInfo ci) {
      I_l__Il_IiI_1I111IlIl1 motionBlur = I_l__Il_IiI_1I111IlIl1.i_I__1li11_iiliIiii_llIl();
      if (motionBlur != null && motionBlur.isEnable()) {
         Ii_I___iiiI11I11iiIi1iilllI1_i.li_I_ll_1ili1_Ii_lIli__1l1_lII((int)motionBlur.getAmount().getValue(), i_i_III11iI_IlII11ll_1__l11.I_i1I__li_1IIllI11IlIi());
      }

   }

   @Inject(
      method = {"method_3188"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/class_757;field_3992:Z",
   opcode = 180,
   ordinal = 0
)}
   )
   public void hookWorldRender(class_9779 tickCounter, CallbackInfo ci, @Local(ordinal = 2) Matrix4f matrix4f) {
      class_4587 matrixStack = new class_4587();
      matrixStack.method_34425(matrix4f);
      matrixStack.method_61958(II111_iIl_IlI1I_i_IiIili_i_iI.mc.method_1561().field_4686.method_19326().method_22882());
      l1___I_lIi__1l1iI_liIi__.I_i1I1i_i1ii_1____ll1i_1_1_Il(RenderSystem.getProjectionMatrix());
      l1___I_lIi__1l1iI_liIi__.i1ilII1I_Iii_11Il__lIiII_(matrixStack.method_23760());
      I_iI1I1_ll11_1ii_1lilI_ event = new I_iI1I1_ll11_1ii_1lilI_(matrixStack, tickCounter.method_60637(false), matrix4f, RenderSystem.getProjectionMatrix());
      Phantom.getInstance().getEventBus().post(event);
      l1___I_lIi__1l1iI_liIi__.llliI_IIlliI1I11iIIi1I1i1(event);
   }

   @Inject(
      method = {"method_3198"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void Phantom$cancelHurtCameraShake(class_4587 matrices, float tickDelta, CallbackInfo ci) {
      if (iiIIliIli1Il_I_i_I1iiil11.lii1I11_i_l1il_liIiI111ilil1_("Camera Shake")) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"method_3186"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void Phantom$cancelViewBob(class_4587 matrices, float tickDelta, CallbackInfo ci) {
      if (iiIIliIli1Il_I_i_I1iiil11.lii1I11_i_l1il_liIiI111ilil1_("Camera Shake")) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"method_57796"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void phantom$cancelBuggyBlur(CallbackInfo ci) {
      ci.cancel();
   }

   @Inject(
      method = {"method_3196"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void phantom$zoom(class_4184 camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Float> cir) {
      if (changingFov) {
         IiIiIlli_1l_ilII1ll1I1 zoom = IiIiIlli_1l_ilII1ll1I1.lI1_1ll__i1_111liii111II11_();
         if (zoom != null && zoom.isEnable() && zoom.shouldApplyFov()) {
            cir.setReturnValue(zoom.applyZoom((Float)cir.getReturnValue(), tickDelta));
         }

      }
   }
}
