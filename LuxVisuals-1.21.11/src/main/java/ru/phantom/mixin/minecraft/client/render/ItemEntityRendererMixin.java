package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I0_i9.Ill_1I_IIIIl1liI_1IiIIIi_iI1i_;
import II1II1II1II1II1II1II1II1.I5_i4.i_1Ii11l1iii1_Iiil__i1I11i;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.class_10039;
import net.minecraft.class_10444;
import net.minecraft.class_1542;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_5819;
import net.minecraft.class_7833;
import net.minecraft.class_916;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.phantom.mixin.minecraft.client.accessors.EntityRendererAccessor;

@Mixin({class_916.class})
public abstract class ItemEntityRendererMixin {
   @Shadow
   @Final
   private class_5819 field_4725;
   @Unique
   private final Map<class_10039, Boolean> phantom$grounded = new WeakHashMap();

   @Unique
   private float Phantom$getRenderY(class_10039 state, i_1Ii11l1iii1_Iiil__i1I11i module) {
      float h = state.field_55310.method_65609().field_4285.y();
      float vanillaY = class_3532.method_15374(state.field_53328 / 10.0F + state.field_53435) * 0.1F + 0.1F + 0.25F * h;
      return module != null && module.isEnable() && module.shouldRemoveBob() && (Boolean)this.phantom$grounded.getOrDefault(state, false) ? 0.25F * h : vanillaY;
   }

   @Unique
   private void Phantom$renderWithPhysics(class_10039 state, class_4587 matrices, class_4597 vertexConsumers, int light) {
      if (!state.field_55310.method_65606()) {
         matrices.method_22903();
         i_1Ii11l1iii1_Iiil__i1I11i module = i_1Ii11l1iii1_Iiil__i1I11i.lIll1il11l_1ii11_l1I1I_I();
         if (module != null && module.isEnable()) {
            boolean grounded = (Boolean)this.phantom$grounded.getOrDefault(state, false);
            Quaternionf orientation = new Quaternionf();
            if (module.isFlat2DMode()) {
               class_4184 camera = class_310.method_1551().field_1773.method_19418();
               orientation.rotateY((float)Math.toRadians((double)(-camera.method_19330()))).rotateX((float)Math.toRadians((double)camera.method_19329()));
            } else {
               orientation.rotateX((float)Math.toRadians((double)90.0F));
            }

            float y = grounded ? Ill_1I_IIIIl1liI_1IiIIIi_iI1i_.lII1lIl1I1_1i1Ii__i1l_l1llI(state.field_55310, orientation) : this.Phantom$getRenderY(state, module);
            if (grounded && !module.shouldRemoveBob()) {
               y += class_3532.method_15374(state.field_53328 / 10.0F + state.field_53435) * 0.1F + 0.1F;
            }

            if (grounded) {
               matrices.method_46416(0.0F, y, 0.0F);

               for(int i = 0; i < state.field_55311; ++i) {
                  matrices.method_22903();
                  matrices.method_46416(0.0F, (float)i * 0.012F, 0.0F);
                  matrices.method_22907(orientation);
                  state.field_55310.method_65604(matrices, vertexConsumers, light, class_4608.field_21444);
                  matrices.method_22909();
               }

               matrices.method_22909();
               return;
            }

            float rotation = class_1542.method_27314(state.field_53328, state.field_53435);
            matrices.method_46416(0.0F, y, 0.0F);
            if (module.isFlat2DMode()) {
               class_4184 camera = class_310.method_1551().field_1773.method_19418();
               matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
               matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
            } else if (module.isRegularMode()) {
               boolean isOnGround = (Boolean)this.phantom$grounded.getOrDefault(state, false);
               float dir = state.field_53435 % 2.0F < 1.0F ? 1.0F : -1.0F;
               if (isOnGround) {
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(90.0F));
               } else {
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(rotation * 300.0F * dir));
                  matrices.method_22907(class_7833.field_40716.rotationDegrees(rotation * 150.0F * dir));
               }
            } else {
               matrices.method_22907(class_7833.field_40716.rotation(rotation));
            }
         } else {
            float y = this.Phantom$getRenderY(state, (i_1Ii11l1iii1_Iiil__i1I11i)null);
            float rotation = class_1542.method_27314(state.field_53328, state.field_53435);
            matrices.method_46416(0.0F, y, 0.0F);
            matrices.method_22907(class_7833.field_40716.rotation(rotation));
         }

         this.Phantom$renderItemStack(matrices, vertexConsumers, light, state);
         matrices.method_22909();
      }
   }

   @Unique
   private void Phantom$renderItemStack(class_4587 matrices, class_4597 vertexConsumers, int light, class_10039 state) {
      this.field_4725.method_43052((long)state.field_55312);
      int renderedAmount = state.field_55311;
      class_10444 itemRenderState = state.field_55310;
      float scaleZ = itemRenderState.method_65609().field_4285.z();
      if (!itemRenderState.method_65607()) {
         float offsetZ = -0.09375F * (float)(renderedAmount - 1) * 0.5F * scaleZ;
         matrices.method_46416(0.0F, 0.0F, offsetZ);
      }

      for(int i = 0; i < renderedAmount; ++i) {
         matrices.method_22903();
         if (i > 0) {
            float offsetX = (this.field_4725.method_43057() * 2.0F - 1.0F) * 0.15F;
            float offsetY = (this.field_4725.method_43057() * 2.0F - 1.0F) * 0.15F;
            float offsetZ = (this.field_4725.method_43057() * 2.0F - 1.0F) * 0.15F;
            matrices.method_46416(itemRenderState.method_65607() ? offsetX : offsetX * 0.5F, itemRenderState.method_65607() ? offsetY : offsetY * 0.5F, itemRenderState.method_65607() ? offsetZ : 0.0F);
         }

         itemRenderState.method_65604(matrices, vertexConsumers, light, class_4608.field_21444);
         matrices.method_22909();
         if (!itemRenderState.method_65607()) {
            matrices.method_46416(0.0F, 0.0F, 0.09375F * scaleZ);
         }
      }

   }

   @Inject(
      method = {"method_62470(Lnet/minecraft/class_1542;Lnet/minecraft/class_10039;F)V"},
      at = {@At("TAIL")}
   )
   private void Phantom$captureEntity(class_1542 itemEntity, class_10039 state, float tickDelta, CallbackInfo ci) {
      this.phantom$grounded.put(state, itemEntity.method_24828());
   }

   @Inject(
      method = {"method_3996(Lnet/minecraft/class_10039;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;I)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void Phantom$render(class_10039 state, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
      i_1Ii11l1iii1_Iiil__i1I11i module = i_1Ii11l1iii1_Iiil__i1I11i.lIll1il11l_1ii11_l1I1I_I();
      if (module != null && module.isEnable()) {
         this.Phantom$renderWithPhysics(state, matrices, vertexConsumers, light);
         if (state.field_53337 != null) {
            ((EntityRendererAccessor)this).Phantom$renderLabelIfPresent(state, state.field_53337, matrices, vertexConsumers, light);
         }

         ci.cancel();
      }
   }
}
