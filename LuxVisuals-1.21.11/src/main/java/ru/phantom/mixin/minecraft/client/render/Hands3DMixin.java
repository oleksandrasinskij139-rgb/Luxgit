package ru.phantom.mixin.minecraft.client.render;

import II1II1II1II1II1II1II1II1.I5_i4.ili1_l1l_I_i1_1liIIiIii_I;
import II1II1II1II1II1II1II1II1.I5_i4.ill__lIIl1i1i_I_lII_1I1ilIIl1;
import java.util.Random;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1309;
import net.minecraft.class_1747;
import net.minecraft.class_1755;
import net.minecraft.class_1764;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1803;
import net.minecraft.class_1828;
import net.minecraft.class_1839;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2286;
import net.minecraft.class_243;
import net.minecraft.class_2459;
import net.minecraft.class_2462;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_3481;
import net.minecraft.class_3489;
import net.minecraft.class_3532;
import net.minecraft.class_3922;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_5134;
import net.minecraft.class_742;
import net.minecraft.class_759;
import net.minecraft.class_776;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_9285;
import net.minecraft.class_9334;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_759.class})
public abstract class Hands3DMixin {
   @Unique
   private boolean repPower;
   @Unique
   private float prevAge;
   @Unique
   private double previousRotation;
   @Unique
   private float swingAngleY;
   @Unique
   private float swingAngleX;
   @Unique
   private float swingVelocityY;
   @Unique
   private float swingVelocityX;
   @Unique
   private float swingVelocityZ;
   @Unique
   private float vertAngleY;
   @Unique
   private float vertVelocityY;
   @Unique
   private float vertVelocityYSlime;
   @Unique
   private float vertAngleYSlime;
   @Unique
   private float riptideCounter;
   @Unique
   private float netherCounter;
   @Unique
   private float fallCounter;
   @Unique
   private float inWaterCounter;
   @Unique
   private float freezeCounter;
   @Unique
   private float clCount;
   @Unique
   private float crawlCount;
   @Unique
   private float directionalCrawlCount;
   @Unique
   private float climbCount;
   @Unique
   private float mouseHolding = 1.0F;
   @Unique
   private boolean isSwinging;
   @Unique
   private boolean isForward;
   @Unique
   private boolean isAttacking;
   @Unique
   private boolean left;
   @Unique
   private double lastFrameTime;
   @Unique
   private double frameDelta;
   @Shadow
   private class_1799 field_4048;
   @Shadow
   @Final
   private class_310 field_4050;

   @Shadow
   public abstract void method_3233(class_1309 var1, class_1799 var2, class_811 var3, boolean var4, class_4587 var5, class_4597 var6, int var7);

   @Shadow
   protected abstract void method_3224(class_4587 var1, class_1306 var2, float var3);

   @Shadow
   protected abstract void method_3222(class_4587 var1, class_4597 var2, int var3, float var4, class_1306 var5, float var6, class_1799 var7);

   @Shadow
   protected abstract void method_3219(class_4587 var1, class_4597 var2, int var3, float var4, float var5, class_1306 var6);

   @Shadow
   protected abstract void method_3217(class_4587 var1, class_1306 var2, float var3);

   @Unique
   private boolean isToolItem(class_1799 stack) {
      return stack.method_31573(class_3489.field_42611) || stack.method_31573(class_3489.field_42612) || stack.method_31573(class_3489.field_42614) || stack.method_31573(class_3489.field_42615) || stack.method_31573(class_3489.field_42613);
   }

   @Unique
   private float easeInOutBack(float x) {
      float c1 = 1.70158F;
      float c2 = c1 * 1.525F;
      return (float)((double)x < (double)0.5F ? Math.pow((double)(2.0F * x), (double)2.0F) * (double)((c2 + 1.0F) * 2.0F * x - c2) / (double)2.0F : (Math.pow((double)(2.0F * x - 2.0F), (double)2.0F) * (double)((c2 + 1.0F) * (x * 2.0F - 2.0F) + c2) + (double)2.0F) / (double)2.0F);
   }

   @Unique
   private float getAttackDamage(class_1799 stack) {
      class_9285 modifiers = (class_9285)stack.method_57353().method_57829(class_9334.field_49636);
      if (modifiers == null) {
         return 0.0F;
      } else {
         float totalDamage = 0.0F;

         for(class_9285.class_9287 entry : modifiers.comp_2393()) {
            if (entry.comp_2395().comp_349() == class_5134.field_23721.comp_349()) {
               totalDamage += (float)entry.comp_2396().comp_2449();
            }
         }

         return totalDamage;
      }
   }

   @Unique
   private void altSwing(class_4587 matrices, class_1306 arm, float swingProgress, class_1799 item) {
      int i = arm == class_1306.field_6183 ? 1 : -1;
      matrices.method_22907(class_7833.field_40716.rotationDegrees((float)i * 45.0F));
      matrices.method_22907(class_7833.field_40716.rotationDegrees((float)i * -45.0F));
   }

   @Inject(
      method = {"method_3228"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onRenderFirstPersonItem(class_742 player, float tickDelta, float pitch, class_1268 hand, float swingProgress, class_1799 item, float equipProgress, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
      ill__lIIl1i1i_I_lII_1I1ilIIl1 module = ill__lIIl1i1i_I_lII_1I1ilIIl1.Il_Ili___llI_iiIIiiilIll11();
      if (module != null && module.isEnable()) {
         ci.cancel();
         double currentTime = (double)System.nanoTime() / (double)1.0E9F;
         this.frameDelta = currentTime - this.lastFrameTime;
         this.lastFrameTime = currentTime;
         if (this.frameDelta > 0.05) {
            this.frameDelta = 0.05;
         }

         if (this.field_4050.method_1493()) {
            this.frameDelta = (double)0.0F;
         }

         double tt = this.frameDelta * (double)30.0F * (double)module.animSpeed.getValue();
         float yaw = player.method_36454();
         double radians = Math.toRadians((double)yaw);
         double forwardX = -Math.sin(radians);
         double forwardZ = Math.cos(radians);
         class_243 horizontalVelocity = player.method_18798();
         double dotProduct = horizontalVelocity.field_1352 * forwardX + horizontalVelocity.field_1350 * forwardZ;
         double crossProduct = player.method_18798().method_61890().field_1352 * forwardZ - horizontalVelocity.field_1350 * forwardX;
         float al = player.method_36455() != 0.0F ? 90.0F / player.method_36455() / 10.0F : 1.0F;
         if (al > 1.0F) {
            al = 1.0F;
         }

         if (al < 0.0F) {
            al = 1.0F;
         }

         boolean bl = hand == class_1268.field_5808;
         class_1306 arm = bl ? player.method_6068() : player.method_6068().method_5928();
         float kj = bl ? 1.0F : -1.0F;
         matrices.method_22903();
         matrices.method_22903();
         ili1_l1l_I_i1_1liIIiIii_I vm = ili1_l1l_I_i1_1liIIiIii_I.llii_ll_I__lI_lii_lIi1();
         float vmx = 0.0F;
         float vmy = 0.0F;
         float vmz = 0.0F;
         if (vm != null && vm.isEnable()) {
            vmx = bl ? vm.mainX.getValue() : vm.offX.getValue();
            vmy = bl ? vm.mainY.getValue() : vm.offY.getValue();
            vmz = bl ? vm.mainZ.getValue() : vm.offZ.getValue();
         }

         matrices.method_46416(vmx * kj, vmy, vmz);
         float swing_rot = (double)swingProgress < 0.6 ? class_3532.method_15374(class_3532.method_15363(swingProgress, 0.0F, 0.12506F) * 12.56F) : class_3532.method_15374(class_3532.method_15363(swingProgress, 0.62532F, 0.75038F) * 12.56F);
         float swing = class_3532.method_15374(swingProgress * 3.14F);
         swing = this.easeInOutBack(swing);
         float swingSpeed = module.swingSpeed.getValue();
         swing *= swingSpeed;
         swing_rot *= swingSpeed;
         if ((item.method_31574(class_1802.field_8287) || item.method_31574(class_1802.field_49098) || item.method_31574(class_1802.field_8803) || item.method_31574(class_1802.field_8449) || item.method_31574(class_1802.field_8543) || item.method_31574(class_1802.field_8634) || item.method_7909() instanceof class_1803 || item.method_7909() instanceof class_1828) && player.method_6079().method_7960() && item.method_7976() != class_1839.field_8951 && !item.method_31574(class_1802.field_8814) && !player.method_5681() && !player.method_20448() && !player.method_6039()) {
            if (player.method_6068() == class_1306.field_6182) {
               bl = !bl;
            }

            float jj = bl ? 1.0F : -1.0F;
            matrices.method_22903();
            matrices.method_22907(class_7833.field_40716.rotationDegrees(-25.0F * jj));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(-10.0F));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(25.0F * jj * swing));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0F * swing));
            matrices.method_22904(-0.15 * (double)jj, 0.1, 0.1);
            matrices.method_22904((double)0.0F, -0.55 * (double)swing, 0.4 * (double)swing * (double)3.14F);
            this.method_3219(matrices, vertexConsumers, light, equipProgress, 0.0F, arm.method_5928());
            matrices.method_22909();
         }

         if (this.field_4050.field_1690.field_1886.method_1434() && !this.isAttacking && (double)swingProgress == (double)0.0F) {
            this.left = !this.left;
         }

         if (!item.method_7960()) {
            if (player.method_6068() == class_1306.field_6182) {
               bl = !bl;
            }

            float ll = bl ? 1.0F : -1.0F;
            if ((this.left || item.method_31573(class_3489.field_42612) || item.method_7976() == class_1839.field_8951 || item.method_7976() == class_1839.field_8949) && !item.method_31573(class_3489.field_42615)) {
               if (!item.method_31573(class_3489.field_42611) && !item.method_31573(class_3489.field_42612)) {
                  if (item.method_7976() == class_1839.field_8951) {
                     matrices.method_22904((double)0.0F, (double)0.0F, 0.45 * (double)swing_rot);
                     matrices.method_22904((double)-0.25F * (double)kj * (double)swing, -0.35 * (double)swing_rot, -0.6 * (double)swing);
                     matrices.method_22904((double)0.0F, 0.1 * (double)swing, (double)0.0F);
                     matrices.method_22907(class_7833.field_40716.rotationDegrees(15.0F * swing_rot * ll));
                     matrices.method_22907(class_7833.field_40718.rotationDegrees(30.0F * swing_rot * ll));
                  } else if (this.isToolItem(item) && item.method_7976() != class_1839.field_8949 && !item.method_31573(class_3489.field_42615)) {
                     matrices.method_22904(0.1 * (double)ll * (double)swing_rot, 0.1 * (double)swing_rot, (double)-0.5F * (double)swing);
                     matrices.method_22907(class_7833.field_40713.rotationDegrees(-30.0F * swing_rot));
                     matrices.method_22907(class_7833.field_40718.rotationDegrees(-20.0F * swing_rot * ll));
                     matrices.method_22907(class_7833.field_40713.rotationDegrees(40.0F * swing));
                  } else if (item.method_7976() != class_1839.field_8949) {
                     matrices.method_22904(0.1 * (double)ll * (double)swing_rot, 0.1 * (double)swing_rot, -0.1 * (double)swing);
                     matrices.method_22907(class_7833.field_40713.rotationDegrees(-30.0F * swing_rot));
                     matrices.method_22907(class_7833.field_40718.rotationDegrees(-10.0F * swing_rot * ll));
                     matrices.method_22907(class_7833.field_40713.rotationDegrees(40.0F * swing));
                     matrices.method_22907(class_7833.field_40716.rotationDegrees(10.0F * swing * ll));
                  } else {
                     matrices.method_22904(0.1 * (double)ll * (double)swing_rot, 0.1 * (double)swing_rot, -0.2 * (double)swing);
                     matrices.method_22907(class_7833.field_40713.rotationDegrees(-10.0F * swing_rot));
                     matrices.method_22907(class_7833.field_40718.rotationDegrees(-10.0F * swing_rot * ll));
                     matrices.method_22907(class_7833.field_40713.rotationDegrees(20.0F * swing));
                  }
               } else {
                  matrices.method_22904(0.8 * (double)ll * (double)swing_rot, 0.3 * (double)swing_rot, (double)-0.5F * (double)swing);
                  matrices.method_22907(class_7833.field_40716.rotationDegrees(15.0F * swing_rot * ll));
                  matrices.method_22907(class_7833.field_40713.rotationDegrees(-20.0F * swing_rot));
                  matrices.method_22907(class_7833.field_40718.rotationDegrees(-70.0F * swing_rot * ll));
                  if (item.method_31573(class_3489.field_42611)) {
                     matrices.method_22907(class_7833.field_40713.rotationDegrees(40.0F * swing));
                  } else {
                     matrices.method_22907(class_7833.field_40713.rotationDegrees(30.0F * swing));
                  }
               }
            } else if (!item.method_31573(class_3489.field_42615)) {
               if (item.method_31573(class_3489.field_42611)) {
                  matrices.method_22904(-0.55 * (double)ll * (double)swing_rot, -0.8 * (double)swing_rot, -0.77 * (double)swing);
                  matrices.method_22907(class_7833.field_40716.rotationDegrees(5.0F * swing_rot * ll));
                  matrices.method_22907(class_7833.field_40713.rotationDegrees(-30.0F * swing_rot));
                  matrices.method_22907(class_7833.field_40718.rotationDegrees(70.0F * swing_rot * ll));
                  matrices.method_22907(class_7833.field_40713.rotationDegrees(50.0F * swing));
               } else if (this.isToolItem(item) && !item.method_31573(class_3489.field_42615)) {
                  matrices.method_22904(0.1 * (double)ll * (double)swing_rot, 0.1 * (double)swing_rot, (double)-0.5F * (double)swing);
                  matrices.method_22907(class_7833.field_40713.rotationDegrees(-30.0F * swing_rot));
                  matrices.method_22907(class_7833.field_40718.rotationDegrees(-20.0F * swing_rot * ll));
                  matrices.method_22907(class_7833.field_40713.rotationDegrees(40.0F * swing));
               } else {
                  matrices.method_22904(0.1 * (double)ll * (double)swing_rot, 0.1 * (double)swing_rot, -0.1 * (double)swing);
                  matrices.method_22907(class_7833.field_40713.rotationDegrees(-30.0F * swing_rot));
                  matrices.method_22907(class_7833.field_40718.rotationDegrees(-10.0F * swing_rot * ll));
                  matrices.method_22907(class_7833.field_40713.rotationDegrees(40.0F * swing));
                  matrices.method_22907(class_7833.field_40716.rotationDegrees(10.0F * swing * ll));
               }
            } else if (item.method_31573(class_3489.field_42615)) {
               matrices.method_22904((double)0.0F, 0.15 * (double)swing_rot, (double)-0.25F * (double)swing_rot);
               matrices.method_22904((double)0.0F, (double)0.0F, -0.2 * (double)swing);
               matrices.method_22907(class_7833.field_40716.rotationDegrees(15.0F * swing_rot));
               matrices.method_22907(class_7833.field_40714.rotationDegrees(-35.0F * swing_rot));
               matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0F * swing));
            }
         } else if (this.getBlockFromItem(item) != class_2246.field_10124 && (!this.isToolItem(item) || item.method_31573(class_3489.field_41890) || item.method_31573(class_3489.field_40109) || item.method_7976() == class_1839.field_8950 || !item.method_7963()) && item.method_7976() != class_1839.field_8953 && item.method_7976() != class_1839.field_27079 && this.getAttackDamage(item) == 0.0F && item.method_7976() != class_1839.field_8949 && !item.method_31574(class_1802.field_23254) && !item.method_31574(class_1802.field_8184) && !item.method_31574(class_1802.field_8378) && !item.method_31574(class_1802.field_8868)) {
            swingProgress = (float)((double)swingProgress * 1.2);
            if (swingProgress > 1.0F) {
               swingProgress = 0.0F;
            }
         } else {
            swingProgress = (float)((double)swingProgress * (double)1.5F);
            if (swingProgress > 1.0F) {
               swingProgress = 0.0F;
            }
         }

         if (player.method_18798().method_1033() >= 0.08) {
            this.crawlCount = (float)((double)this.crawlCount + 0.1 * player.method_18798().method_1033() * (double)2.0F * tt);
            this.directionalCrawlCount = (float)((double)this.directionalCrawlCount + 0.1 * dotProduct * (double)4.0F * tt);
            this.directionalCrawlCount = (float)((double)this.directionalCrawlCount + (dotProduct > (double)0.0F ? 0.1 * Math.abs(crossProduct) * (double)4.0F * tt : 0.1 * Math.abs(crossProduct) * (double)-1.0F * (double)4.0F * tt));
         }

         if (player.method_18798().method_10214() > (double)0.0F) {
            this.climbCount = (float)((double)this.climbCount + 0.1 * tt);
         }

         if (player.method_18798().method_10214() < (double)0.0F) {
            this.climbCount = (float)((double)this.climbCount - 0.1 * tt);
         }

         if ((player.method_20448() || player.method_6101() && !player.method_24828() && Math.abs(player.method_18798().method_10214()) > (double)0.0F) && !player.method_6115() && swingProgress == 0.0F) {
            this.clCount = (float)((double)this.clCount + 0.1 * tt);
            if (this.clCount > 1.0F) {
               this.clCount = 1.0F;
            }

            if (!item.method_31574(class_1802.field_16539) && !item.method_31574(class_1802.field_22016)) {
               matrices.method_22907(class_7833.field_40714.rotationDegrees(-20.0F * this.clCount));
            }
         } else {
            this.clCount = (float)((double)this.clCount * Math.pow((double)0.88F, tt));
         }

         if (swingProgress == 0.0F) {
            matrices.method_46416(bl ? player.method_36455() / 650.0F * this.clCount * -1.0F : player.method_36455() / 650.0F * this.clCount, 0.0F, 0.0F);
            matrices.method_22907(class_7833.field_40714.rotationDegrees(player.method_36455() * this.clCount));
         }

         if (!item.method_31574(class_1802.field_16539) && !item.method_31574(class_1802.field_22016)) {
            matrices.method_46416(0.0F, 0.0F, player.method_36455() / 120.0F * this.clCount);
         } else if (swingProgress == 0.0F) {
            matrices.method_46416(0.0F, 0.0F, player.method_36455() / 80.0F * this.clCount);
         }

         if (player.method_6101() && !player.method_24828() && !item.method_31574(class_1802.field_16539) && !item.method_31574(class_1802.field_22016) && !player.method_6115()) {
            matrices.method_22904((double)0.0F, 0.1, -0.2);
         }

         if ((player.method_5799() || player.method_5869()) && !player.method_5681() && !player.method_5869()) {
            this.inWaterCounter = (float)((double)this.inWaterCounter + 0.1 * tt);
            if (this.inWaterCounter >= 1.0F) {
               this.inWaterCounter = 1.0F;
            }
         } else {
            this.inWaterCounter = (float)((double)this.inWaterCounter * Math.pow((double)0.88F, tt));
         }

         float freezingScale = class_3532.method_15363((float)player.method_32312() / (float)player.method_32315(), 0.0F, 1.0F);
         if (player.method_5869() && freezingScale > 0.1F) {
            this.freezeCounter = (float)((double)this.freezeCounter + 0.1 * tt);
         } else {
            this.freezeCounter = (float)((double)this.freezeCounter * Math.pow((double)0.88F, tt));
         }

         matrices.method_22904((double)0.0F, 0.02 * (double)this.inWaterCounter, (double)0.0F);
         matrices.method_22907(class_7833.field_40718.rotationDegrees(8.0F * kj * this.inWaterCounter));
         matrices.method_22907(class_7833.field_40714.rotationDegrees(0.3F * class_3532.method_15374(this.freezeCounter * 5.0F)));
         if (player.method_18798().method_10214() < -0.85 && item.method_31574(class_1802.field_49814) && player.method_6047() == item) {
            this.fallCounter = (float)((double)this.fallCounter + 0.1 * tt);
            if (this.fallCounter >= 1.0F) {
               this.fallCounter = 1.0F;
            }
         } else {
            this.fallCounter = (float)((double)this.fallCounter * Math.pow((double)0.88F, tt));
         }

         if (bl) {
            matrices.method_22907(class_7833.field_40714.rotationDegrees(45.0F * this.fallCounter));
            matrices.method_22904((double)0.0F, -0.2 * (double)this.fallCounter, (double)0.0F);
         }

         this.vertAngleY = (float)((double)this.vertAngleY + player.method_18798().method_10214() * 0.015 * tt);
         this.vertAngleY = (float)((double)this.vertAngleY - (double)(0.1F * this.vertAngleY) * tt);
         this.vertAngleY = (float)((double)this.vertAngleY * Math.pow((double)0.88F, tt));
         this.vertVelocityYSlime = (float)((double)this.vertVelocityYSlime + player.method_18798().method_10214() * 0.015 * tt);
         this.vertVelocityYSlime = (float)((double)this.vertVelocityYSlime - (double)(0.1F * this.vertAngleYSlime) * tt);
         this.vertVelocityYSlime = (float)((double)this.vertVelocityYSlime * Math.pow((double)0.88F, tt));
         this.vertAngleYSlime = (float)((double)this.vertAngleYSlime + (double)this.vertVelocityYSlime * tt);
         matrices.method_46416(0.0F, this.vertAngleY * -1.0F, 0.0F);
         matrices.method_22904((double)0.0F, Math.sin((double)player.field_6012 * 0.1) * 0.007 * (double)kj, (double)0.0F);
         matrices.method_22907(class_7833.field_40716.rotationDegrees(0.15F * class_3532.method_15374((float)player.field_6012 * 0.15F) * kj));
         if (!item.method_7960() || player.method_20448() || player.method_6101() && !player.method_24828() || player.method_5681()) {
            if (player.method_6068() == class_1306.field_6182) {
               bl = !bl;
            }

            if (item.method_7976() == class_1839.field_8949) {
               matrices.method_46416(0.0F, 0.0F, 0.0F);
            } else {
               matrices.method_22904((double)0.0F, -0.1, 0.1);
            }
         }

         if (item.method_31574(class_1802.field_16539) || item.method_31574(class_1802.field_22016) || item.method_31573(class_3489.field_40108)) {
            matrices.method_22904((double)0.0F, 0.1, (double)0.0F);
            if (player.method_5681()) {
               matrices.method_22904((double)0.0F, -0.1, 0.1);
            }
         }

         if (player.method_5681() && swingProgress == 0.0F) {
            double distance = (double)this.crawlCount;
            double frequency = (double)2.0F;
            double s = distance * frequency;
            double handRotation = Math.sin(s) * (double)1.5F;
            double smoothRotation = handRotation * 0.8 + this.previousRotation * 0.2;
            matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(bl ? smoothRotation : -smoothRotation)));
            matrices.method_22904((double)0.0F, (double)0.0F, smoothRotation * (double)0.2F);
            double k = (double)(this.crawlCount * 2.0F);
            double a = Math.cos(k);
            double b = a;
            if (a <= (double)0.0F) {
               b = a * (double)0.5F;
            }

            matrices.method_22907(class_7833.field_40715.rotationDegrees((float)(bl ? b * (double)30.0F : b * (double)30.0F * (double)-1.0F)));
            matrices.method_22904((double)0.0F, (double)0.0F, a * (double)0.2F);
            if (item.method_7960() && !bl && !player.method_5767()) {
               float l = bl ? 1.0F : -1.0F;
               matrices.method_22904((double)(1.0F * l), (double)0.0F - (double)equipProgress * 0.3, 0.3);
               matrices.method_22907(class_7833.field_40716.rotationDegrees(45.0F * l));
               matrices.method_22907(class_7833.field_40718.rotationDegrees(-40.0F * l));
               matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0F));
               this.altSwing(matrices, arm, swingProgress, item);
               matrices.method_22905(0.9F, 0.9F, 0.9F);
               this.method_3219(matrices, vertexConsumers, light, 0.0F, 0.0F, arm);
            }

            this.previousRotation = smoothRotation;
         }

         if ((player.method_6101() && !player.method_24828() || player.method_20448() && swingProgress == 0.0F) && !player.method_6115()) {
            double s = (double)this.climbCount;
            float a = class_3532.method_15362((float)s * 2.0F);
            float leftDir = bl ? 1.0F : -1.0F;
            if (player.method_6101()) {
               if (!item.method_31574(class_1802.field_16539) && !item.method_31574(class_1802.field_22016)) {
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(20.0F * a * leftDir));
               } else {
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(1.0F * a * leftDir));
               }
            }

            if (player.method_20448() && !player.method_6115() && swingProgress == 0.0F) {
               float crawlProgress = class_3532.method_15374(this.directionalCrawlCount * 4.0F * this.mouseHolding);
               float upAndDown = class_3532.method_15362(this.directionalCrawlCount * 4.0F * this.mouseHolding);
               if (item.method_31574(class_1802.field_16539) || item.method_31574(class_1802.field_22016)) {
                  crawlProgress *= 0.14F;
                  upAndDown *= 0.14F;
               }

               matrices.method_22904(0.2 * (double)crawlProgress, 0.3 * (double)crawlProgress * (double)leftDir, -0.2 * (double)crawlProgress * (double)leftDir * (double)al);
               matrices.method_22907(class_7833.field_40716.rotationDegrees(25.0F * crawlProgress));
               matrices.method_22907(class_7833.field_40714.rotationDegrees(class_3532.method_15363(20.0F * upAndDown * leftDir, 0.0F, 20.0F)));
            }

            if (item.method_7960() && !bl && !player.method_5767() && (!player.method_24828() && player.method_6101() || player.method_20448())) {
               float l = bl ? 1.0F : -1.0F;
               matrices.method_22904((double)(1.0F * l), (double)0.0F - (double)equipProgress * 0.3, 0.3);
               matrices.method_22907(class_7833.field_40716.rotationDegrees(45.0F * l));
               matrices.method_22907(class_7833.field_40718.rotationDegrees(-40.0F * l));
               matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0F));
               this.altSwing(matrices, arm, swingProgress, item);
               matrices.method_22905(0.9F, 0.9F, 0.9F);
               this.method_3219(matrices, vertexConsumers, light, 0.0F, 0.0F, arm);
            }
         }

         if (item.method_7960()) {
            if (bl && !player.method_5767()) {
               if ((player.method_24828() || !player.method_6101()) && !player.method_5681() && !player.method_20448()) {
                  if (player.method_6068() == class_1306.field_6182) {
                     bl = !bl;
                  }

                  float ll = bl ? 1.0F : -1.0F;
                  matrices.method_22904((double)0.0F, 0.2 * (double)swing_rot, 0.15 * (double)swing_rot);
                  matrices.method_22904(0.1 * (double)ll * (double)swing, 0.15 * (double)swing, -0.45 * (double)swing);
                  matrices.method_22907(class_7833.field_40716.rotationDegrees(35.0F * swing * ll));
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(-30.0F * swing));
                  matrices.method_22907(class_7833.field_40716.rotationDegrees(-10.0F * swing_rot * ll));
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(10.0F * swing_rot));
                  this.method_3219(matrices, vertexConsumers, light, 0.0F, 0.0F, arm);
               } else {
                  float l = bl ? 1.0F : -1.0F;
                  matrices.method_22904((double)(1.0F * l), (double)0.0F - (double)equipProgress * 0.3, 0.3);
                  matrices.method_22907(class_7833.field_40716.rotationDegrees(45.0F * l));
                  matrices.method_22907(class_7833.field_40718.rotationDegrees(-40.0F * l));
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0F));
                  this.altSwing(matrices, arm, swingProgress, item);
                  matrices.method_22905(0.9F, 0.9F, 0.9F);
                  this.method_3219(matrices, vertexConsumers, light, 0.0F, 0.0F, arm);
               }
            }
         } else if (item.method_57826(class_9334.field_49646)) {
            if (bl && this.field_4048.method_7960()) {
               matrices.method_22904((double)0.0F, 0.1, (double)0.0F);
               this.method_3233(player, item, arm == class_1306.field_6183 ? class_811.field_4322 : class_811.field_4321, arm != class_1306.field_6183, matrices, vertexConsumers, light);
            } else {
               matrices.method_22904(bl ? -0.1 : 0.1, 0.1, (double)0.0F);
               this.method_3222(matrices, vertexConsumers, light, equipProgress, arm, swingProgress, item);
            }
         } else if (item.method_7976() == class_1839.field_8947) {
            matrices.method_22903();
            boolean bl2 = class_1764.method_7781(item);
            boolean bl3 = arm == class_1306.field_6183;
            int i = bl3 ? 1 : -1;
            if (player.method_6115() && player.method_6014() > 0 && player.method_6058() == hand) {
               this.method_3224(matrices, arm, equipProgress);
               matrices.method_46416((float)i * -0.4785682F, -0.24387F, 0.05731531F);
               matrices.method_22907(class_7833.field_40714.rotationDegrees(-11.935F));
               matrices.method_22907(class_7833.field_40716.rotationDegrees((float)i * 65.3F));
               matrices.method_22907(class_7833.field_40718.rotationDegrees((float)i * 9.785F));
               float f = (float)item.method_7935(player) - ((float)player.method_6014() - tickDelta + 1.0F);
               float g = f / (float)class_1764.method_7775(item, player);
               if (g > 1.0F) {
                  g = 1.0F;
               }

               if (g > 0.1F) {
                  float h = class_3532.method_15374((f - 0.1F) * 1.3F);
                  float j = g - 0.1F;
                  float k = h * j;
                  matrices.method_46416(k * 0.0F, k * 0.004F, k * 0.0F);
               }

               matrices.method_46416(g * 0.0F, g * 0.0F, g * 0.04F);
               matrices.method_22907(class_7833.field_40715.rotationDegrees((float)i * 45.0F));
            } else {
               this.method_3219(matrices, vertexConsumers, light, equipProgress, swingProgress, arm);
               if (bl2 && swingProgress < 0.001F && bl) {
                  matrices.method_46416((float)i * -0.341864F, 0.0F, 0.0F);
                  matrices.method_22907(class_7833.field_40716.rotationDegrees((float)i * 10.0F));
               }
            }

            float l = bl ? 1.0F : -1.0F;
            matrices.method_46416(0.0F, 0.0F, -1.0F);
            matrices.method_22904(-0.45 * (double)i, 0.45, 1.7);
            matrices.method_22904((double)(1.0F * l), (double)0.0F - (double)equipProgress * 0.3, 0.3);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(45.0F * l));
            matrices.method_22907(class_7833.field_40718.rotationDegrees(-40.0F * l));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0F));
            this.altSwing(matrices, arm, swingProgress, item);
            matrices.method_22905(0.9F, 0.9F, 0.9F);
            this.method_3219(matrices, vertexConsumers, light, 0.0F, 0.0F, arm);
            matrices.method_22904((double)-0.25F * (double)i, (double)1.25F, 0.05);
            matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(-90 * i)));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(77.0F));
            matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(85 * i)));
            matrices.method_22905(1.2F, 1.2F, 1.2F);
            matrices.method_22907(class_7833.field_40714.rotationDegrees(-10.0F));
            matrices.method_22904((double)0.0F, -0.15, 0.15);
            this.method_3233(player, item, bl3 ? class_811.field_4322 : class_811.field_4321, !bl3, matrices, vertexConsumers, light);
            matrices.method_22909();
            if (player.method_6115() && player.method_6014() > 0 && player.method_6058() == hand) {
               float f = (float)item.method_7935(player) - ((float)player.method_6014() - tickDelta + 1.0F);
               float g = f / (float)class_1764.method_7775(item, player);
               if (g > 1.0F) {
                  g = 1.0F;
               }

               if (g > 0.1F) {
                  float h = class_3532.method_15374((f - 0.1F) * 1.3F);
                  float j = g - 0.1F;
                  float k = h * j;
                  matrices.method_46416(k * 0.0F, k * 0.004F, k * 0.0F);
               }

               matrices.method_22907(class_7833.field_40715.rotationDegrees((double)g <= 0.2 ? 75.0F * g * 5.0F * (float)i : (float)(75 * i)));
               matrices.method_22907(class_7833.field_40713.rotationDegrees(10.0F * g * 1.5F));
               matrices.method_22904(-0.37 * (double)i, (double)0.0F, 0.6);
               matrices.method_22904(0.15 * (double)g * (double)i, (double)0.0F, (double)0.0F);
               this.method_3219(matrices, vertexConsumers, light, equipProgress, swingProgress, arm.method_5928());
            }
         } else {
            boolean bl2 = arm == class_1306.field_6183;
            int i = bl2 ? 1 : -1;
            if (player.method_6115() && player.method_6014() > 0 && player.method_6058() == hand) {
               switch (item.method_7976()) {
                  case field_8952:
                     this.method_3224(matrices, arm, equipProgress);
                     break;
                  case field_8950:
                  case field_8946:
                     float u = (float)item.method_7935(player) - ((float)player.method_6014() - tickDelta + 1.0F);
                     float y = u / 5.0F;
                     if (y > 1.0F) {
                        y = 1.0F;
                     }

                     float q = class_3532.method_15374(u / 2.0F * 3.14F);
                     matrices.method_22904((double)(1 * i), 0.1, 0.3);
                     matrices.method_22904(0.2 * (double)i * (double)y, -0.7 * (double)y, -0.2 * (double)y);
                     float var100;
                     matrices.method_22904((double)0.0F, -0.2 * (double)(var100 = q / 10.0F), -0.2 * (double)var100);
                     matrices.method_22904((double)0.0F, 0.1 * (double)this.easeInOutBack(class_3532.method_15374(y * 3.14F)), (double)0.0F);
                     matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(45 * i)));
                     matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(-40 * i)));
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0F));
                     this.altSwing(matrices, arm, swingProgress, item);
                     matrices.method_22905(0.9F, 0.9F, 0.9F);
                     matrices.method_22907(class_7833.field_40716.rotationDegrees(45.0F * y * (float)i));
                     this.method_3219(matrices, vertexConsumers, light, 0.0F, swingProgress, arm);
                     break;
                  case field_8949:
                     float k = (float)item.method_7935(player) - ((float)player.method_6014() - tickDelta + 1.0F);
                     float s = k / 4.0F;
                     float s2 = k / 6.0F;
                     if (s > 1.0F) {
                        s = 1.0F;
                     }

                     if (s2 > 1.0F) {
                        s2 = 1.0F;
                     }

                     matrices.method_22904((double)0.0F, -0.2, (double)0.0F);
                     matrices.method_22904((double)(1 * i), (double)0.0F, 0.3);
                     matrices.method_22904(0.7 * (double)s * (double)i, (double)0.0F, -1.3 * (double)s);
                     matrices.method_22904(-0.2 * (double)i * (double)s2, (double)0.0F, (double)0.0F);
                     matrices.method_22907(class_7833.field_40714.rotationDegrees((float)((double)10.0F * Math.sin((double)s2 * 3.14))));
                     matrices.method_22907(class_7833.field_40716.rotationDegrees(70.0F * s * (float)i));
                     matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(45 * i)));
                     matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(-40 * i)));
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0F));
                     matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(5 * i) * s));
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(-10.0F * s));
                     matrices.method_22904((double)0.0F, (double)0.0F, -0.2 * (double)s);
                     this.altSwing(matrices, arm, swingProgress, item);
                     matrices.method_22905(0.9F, 0.9F, 0.9F);
                     this.method_3219(matrices, vertexConsumers, light, 0.0F, swingProgress, arm);
                     matrices.method_22904(0.35 * (double)i, -0.13, -0.12);
                     matrices.method_22907(class_7833.field_40718.rotationDegrees(10.0F * (float)i));
                     matrices.method_22907(class_7833.field_40716.rotationDegrees(10.0F * (float)i));
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(0.0F));
                     matrices.method_22904(-0.2 * (double)i, -0.04, 0.15);
                     matrices.method_22905(1.0F, 1.0F, 1.0F);
                     break;
                  case field_8953:
                     matrices.method_22903();
                     if (player.method_6068() == class_1306.field_6182) {
                        bl = !bl;
                     }

                     float m1 = (float)item.method_7935(player) - ((float)player.method_6014() - tickDelta + 1.0F);
                     float f1 = m1 / 20.0F;
                     float f = (f1 * f1 + f1 * 2.0F) / 3.0F;
                     if (f1 > 1.0F) {
                        f1 = 1.0F;
                     }

                     if (f1 > 0.1F) {
                        float g1 = class_3532.method_15374((m1 - 0.1F) * 1.3F);
                        float j1 = g1 * f1;
                        matrices.method_46416(j1 * 0.0F, j1 * 0.004F, j1 * 0.0F);
                     }

                     matrices.method_22904(bl ? -0.1 : 0.1, (double)0.0F, (double)f1 * 0.15);
                     this.method_3219(matrices, vertexConsumers, light, equipProgress, swingProgress, arm);
                     matrices.method_22909();
                     matrices.method_22904(bl ? (double)-0.5F : (double)0.5F, -0.45, 0.1);
                     matrices.method_22907(class_7833.field_40714.rotation(0.3F));
                     if (bl) {
                        matrices.method_22907(class_7833.field_40717.rotation(-0.3F));
                        matrices.method_22907(class_7833.field_40715.rotation(1.0F));
                     } else {
                        matrices.method_22907(class_7833.field_40718.rotation(-0.3F));
                        matrices.method_22907(class_7833.field_40716.rotation(1.0F));
                     }

                     this.method_3219(matrices, vertexConsumers, light, equipProgress, swingProgress, arm.method_5928());
                     if (bl) {
                        matrices.method_22907(class_7833.field_40715.rotation(2.5F));
                     } else {
                        matrices.method_22907(class_7833.field_40716.rotation(2.5F));
                     }

                     matrices.method_22904(bl ? -0.65 : 0.65, -0.35, 0.27);
                     if (f1 > 1.0F) {
                        f1 = 1.0F;
                     }

                     matrices.method_22909();
                     matrices.method_22907(class_7833.field_40713.rotationDegrees(75.0F));
                     matrices.method_22907(class_7833.field_40717.rotationDegrees((float)(-15 * i)));
                     matrices.method_22904(0.8 * (double)i, (double)(0.0F - equipProgress * 0.3F), -0.1);
                     if (f > 0.1F) {
                        float g1 = class_3532.method_15374((m1 - 0.1F) * 1.3F);
                        float h1 = f1 - 0.1F;
                        float j1 = g1 * h1;
                        matrices.method_46416(j1 * 0.0F, j1 * 0.004F, j1 * 0.0F);
                     }

                     matrices.method_22903();
                     break;
                  case field_8951:
                     if (player.method_6047().method_7960() && !player.method_20448() && !player.method_5681() && !player.method_6101()) {
                        matrices.method_22903();
                        matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(-25 * i)));
                        matrices.method_22904(-0.15 * (double)i, 0.1, 0.1);
                        this.method_3219(matrices, vertexConsumers, light, equipProgress, swingProgress, arm.method_5928());
                        matrices.method_22909();
                     }

                     float m;
                     float f = (m = (float)item.method_7935(player) - ((float)player.method_6014() - tickDelta + 1.0F)) / 10.0F;
                     if (f > 1.0F) {
                        f = 1.0F;
                     }

                     if (f > 0.1F) {
                        float g = class_3532.method_15374((m - 0.1F) * 1.3F);
                        float h = f - 0.1F;
                        float j = g * h;
                        matrices.method_46416(j * 0.0F, j * 0.004F, j * 0.0F);
                     }

                     matrices.method_22907(class_7833.field_40714.rotationDegrees(45.0F));
                     matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(25 * i)));
                     matrices.method_22904(0.2 * (double)i, (double)0.0F, 0.8);
                     this.method_3219(matrices, vertexConsumers, light, equipProgress, swingProgress, arm);
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(135.0F));
                     matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(-65 * i)));
                     matrices.method_22904((double)(0.65F * (float)i), (double)-1.0F, -0.6);
                     break;
                  case field_42717:
                     float f5 = (float)(player.method_6014() % 10);
                     float g5 = f5 - tickDelta + 1.0F;
                     float h5 = 1.0F - g5 / 10.0F;
                     float n2 = -15.0F + 75.0F * class_3532.method_15362(h5 * 2.0F * (float)Math.PI);
                     float z = (float)item.method_7935(player) - ((float)player.method_6014() - tickDelta + 1.0F);
                     float x = z / 4.0F;
                     if (x > 1.0F) {
                        x = 1.0F;
                     }

                     matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(25 * i) * x));
                     matrices.method_22904((double)(0.3F * (float)i * x), 0.3 * (double)x, 0.1 * (double)x);
                     if (x == 1.0F) {
                        matrices.method_22907(class_7833.field_40716.rotationDegrees(n2 / 20.0F));
                     }

                     this.method_3219(matrices, vertexConsumers, light, equipProgress, swingProgress, arm);
                     break;
                  case field_55494:
                     matrices.method_22904((double)(1 * i), (double)0.0F - (double)equipProgress * 0.3, 0.3);
                     matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(45 * i)));
                     matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(-40 * i)));
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0F));
                     this.altSwing(matrices, arm, swingProgress, item);
                     matrices.method_22905(0.9F, 0.9F, 0.9F);
                     this.method_3219(matrices, vertexConsumers, light, 0.0F, 0.0F, arm);
               }
            } else if (player.method_6123() && item.method_7976() == class_1839.field_8951) {
               this.riptideCounter = (float)((double)this.riptideCounter + 0.15 * tt);
               float m = (float)item.method_7935(player) - ((float)player.method_6014() - tickDelta + 1.0F);
               float f = m / 10.0F;
               if (f > 1.0F) {
                  f = 1.0F;
               }

               if (f > 0.1F) {
                  float g = class_3532.method_15374((m - 0.1F) * 1.3F);
                  float h = f - 0.1F;
                  float j = g * h;
                  matrices.method_46416(j * 0.0F, j * 0.004F, j * 0.0F);
               }

               matrices.method_22907(class_7833.field_40714.rotationDegrees(45.0F - this.riptideCounter * 2.0F));
               matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(25 * i)));
               matrices.method_22904(0.2 * (double)i, (double)0.0F, (double)0.75F);
               matrices.method_22904((double)0.0F, (double)0.0F, 0.01 * (double)class_3532.method_15374(this.riptideCounter * 6.28F));
               this.method_3219(matrices, vertexConsumers, light, equipProgress, swingProgress, arm);
               matrices.method_22907(class_7833.field_40714.rotationDegrees(135.0F));
               matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(-65 * i)));
               matrices.method_22904((double)(0.65F * (float)i), (double)-1.0F, -0.6);
            } else {
               this.riptideCounter = 0.0F;
               if (!item.method_31574(class_1802.field_16539) && !item.method_31574(class_1802.field_22016) && !item.method_31573(class_3489.field_40108)) {
                  if (item.method_7976() == class_1839.field_8949) {
                     matrices.method_22904((double)0.0F, -0.2, (double)0.0F);
                  }
               } else {
                  matrices.method_22904(0.1 * (double)i, (double)0.0F, -0.1);
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(10.0F));
               }

               matrices.method_22904((double)(1 * i), (double)0.0F - (double)equipProgress * 0.3, 0.3);
               matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(45 * i)));
               matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(-40 * i)));
               matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0F));
               this.altSwing(matrices, arm, swingProgress, item);
               matrices.method_22905(0.9F, 0.9F, 0.9F);
               this.method_3219(matrices, vertexConsumers, light, 0.0F, 0.0F, arm);
            }

            matrices.method_22904(-0.3 * (double)i, 0.65, -0.1);
            matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(-65 * i)));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(10.0F));
            if (item.method_31573(class_3489.field_15542)) {
               matrices.method_22904(0.2 * (double)i, -0.1, (double)0.0F);
            }

            class_2248 itemBlock = this.getBlockFromItem(item);
            if (itemBlock != class_2246.field_10124 && item.method_7976() != class_1839.field_8950 && !(item.method_7909() instanceof class_1755)) {
               if (item.method_7964().toString().toLowerCase().contains("torch")) {
                  matrices.method_22905(1.5F, 1.5F, 1.5F);
                  matrices.method_22907(class_7833.field_40715.rotationDegrees((float)(25 * i)));
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(5.0F));
                  matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(75 * i)));
                  matrices.method_22904(0.2 * (double)i, 0.2, 0.05);
               } else if ((item.method_31574(class_1802.field_8276) || item.method_31574(class_1802.field_8725) || item.method_31574(class_1802.field_8865) || item.method_31574(class_1802.field_8366) || itemBlock.method_9564().method_26164(class_3481.field_15463) || itemBlock.method_9564().method_26164(class_3481.field_22414) || item.method_31573(class_3489.field_15553)) && !itemBlock.method_9564().method_26164(class_3481.field_15503) && !itemBlock.method_9564().method_26164(class_3481.field_43170) && !itemBlock.method_9564().method_26164(class_3481.field_15501)) {
                  matrices.method_22904((double)0.0F, (double)0.0F, -0.1);
                  matrices.method_22907(class_7833.field_40715.rotationDegrees((float)(5 * i)));
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(15.0F));
                  matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(75 * i)));
               } else if (!item.method_31574(class_1802.field_16539) && !item.method_31574(class_1802.field_22016) && !item.method_31573(class_3489.field_40108)) {
                  matrices.method_22907(class_7833.field_40715.rotationDegrees((float)(25 * i)));
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(5.0F));
                  matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(75 * i)));
                  matrices.method_22904(0.2 * (double)i, 0.2, 0.05);
                  if (itemBlock.method_9564().method_26164(class_3481.field_15501)) {
                     matrices.method_22904(-0.2 * (double)i, (double)0.0F, (double)0.0F);
                     matrices.method_22905(1.1F, 1.1F, 1.1F);
                  }
               } else {
                  float dt = (float)(this.frameDelta * (double)30.0F);
                  float yawDelta = player.field_6259 - player.method_5791();
                  float pitchDelta = player.field_6004 - player.method_36455();
                  this.swingVelocityY += yawDelta * 0.015F * dt;
                  this.swingVelocityY += swingProgress * 2.0F * dt;
                  this.swingVelocityX += pitchDelta * 0.015F * dt;
                  this.swingVelocityY -= 0.1F * this.swingAngleY * dt;
                  this.swingVelocityX -= 0.1F * this.swingAngleX * dt;
                  this.swingVelocityY = (float)((double)this.swingVelocityY * Math.pow((double)0.88F, (double)dt));
                  this.swingVelocityX = (float)((double)this.swingVelocityX * Math.pow((double)0.88F, (double)dt));
                  this.swingAngleY += this.swingVelocityY * dt;
                  this.swingAngleX += this.swingVelocityX * dt;
                  double currentSpeed = player.method_18798().method_1033();
                  this.swingVelocityZ = (float)((double)this.swingVelocityZ + (bl ? (currentSpeed * (double)-1.0F * (double)15.0F - (double)this.swingVelocityZ) * 0.1 * (double)dt : (currentSpeed * (double)15.0F - (double)this.swingVelocityZ) * 0.1 * (double)dt));
                  if ((currentSpeed > 0.09 && player.method_24828() || player.method_5681() || player.method_6101() && !player.method_24828()) && (Boolean)this.field_4050.field_1690.method_42448().method_41753()) {
                     Random random = new Random();
                     boolean randomBoolean = random.nextBoolean();
                     this.swingVelocityY += (float)(randomBoolean ? (double)-5.5F * currentSpeed * (double)dt : (double)5.5F * currentSpeed * (double)dt);
                  }

                  matrices.method_22904((double)0.0F, (double)0.0F, -0.1);
                  matrices.method_22907(class_7833.field_40715.rotationDegrees((float)(35 * i) + this.swingAngleY));
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(15.0F + this.swingAngleX));
                  matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(75 * i) + this.swingVelocityZ));
                  if (item.method_31573(class_3489.field_40108)) {
                     matrices.method_22904((double)0.0F, -0.1, (double)0.0F);
                     matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(-45 * i)));
                  }

                  matrices.method_22904(0.3 * (double)i, -0.35, (double)0.0F);
                  matrices.method_22904((double)0.0F, (double)0.0F, 0.1);
                  matrices.method_22905(1.5F, 1.5F, 1.5F);
               }
            } else {
               if ((!this.isToolItem(item) || item.method_31573(class_3489.field_41890) || item.method_31573(class_3489.field_40109) || item.method_7976() == class_1839.field_8950 || !item.method_7963()) && item.method_7976() != class_1839.field_8953 && item.method_7976() != class_1839.field_27079 && this.getAttackDamage(item) == 0.0F && item.method_7976() != class_1839.field_8949 && !item.method_31574(class_1802.field_23254) && !item.method_31574(class_1802.field_8184) && !item.method_31574(class_1802.field_8378) && !item.method_31574(class_1802.field_8868) && !item.method_31573(class_3489.field_42613)) {
                  if (item.method_7976() == class_1839.field_42717) {
                     matrices.method_22907(class_7833.field_40713.rotationDegrees(25.0F));
                     matrices.method_22904(bl ? (double)0.0F : 0.35, bl ? (double)0.0F : (double)0.25F, bl ? (double)0.0F : 0.37);
                     if (!bl) {
                        matrices.method_22905(0.75F, 0.75F, 0.75F);
                     }

                     matrices.method_22907(class_7833.field_40717.rotationDegrees((float)(-75 * i)));
                     matrices.method_22907(class_7833.field_40713.rotationDegrees(35.0F));
                     matrices.method_22904(bl ? -0.05 : 0.85, bl ? (double)0.0F : 0.05, bl ? 0.08 : -0.2);
                  } else {
                     matrices.method_22907(class_7833.field_40715.rotationDegrees((float)(5 * i)));
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(15.0F));
                     matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(75 * i)));
                     matrices.method_22904((double)0.0F, -0.05, -0.1);
                     matrices.method_22905(0.7F, 0.7F, 0.7F);
                  }

                  if (item.method_31574(class_1802.field_8153) || item.method_31574(class_1802.field_8777) || item.method_31574(class_1802.field_8323)) {
                     this.vertVelocityYSlime = (float)((double)this.vertVelocityYSlime + (double)swingProgress * 0.03 * tt);
                     if ((player.method_18798().method_1033() > 0.09 && player.method_24828() || player.method_5681() || player.method_20448() || player.method_6101() && !player.method_24828()) && (Boolean)this.field_4050.field_1690.method_42448().method_41753()) {
                        Random random = new Random();
                        boolean randomBoolean = random.nextBoolean();
                        this.vertVelocityYSlime += (float)(-0.05 * player.method_18798().method_1033() * tt);
                     }

                     matrices.method_22905(1.0F, 1.0F + this.vertAngleYSlime * -2.0F, 1.0F);
                  }
               } else if (item.method_7976() == class_1839.field_8949 && item.method_7976() != class_1839.field_8951) {
                  matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(160 * i)));
                  matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(-60 * i)));
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(-70.0F));
                  matrices.method_22905(0.75F, 0.75F, 0.75F);
                  matrices.method_22904(0.15 * (double)i, bl ? 0.35 : 0.45, bl ? -0.15 : -0.1);
                  matrices.method_22904(0.17 * (double)i, (double)0.0F, 0.3);
                  matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(-90 * i)));
               } else if (item.method_7976() == class_1839.field_8951) {
                  matrices.method_22907(class_7833.field_40715.rotationDegrees((float)(75 * i)));
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(90.0F));
                  matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(45 * i)));
                  matrices.method_46416(-0.3F * (float)i, 0.0F, 0.0F);
               } else if (item.method_7976() != class_1839.field_8951) {
                  matrices.method_22907(class_7833.field_40715.rotationDegrees((float)(75 * i)));
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(70.0F));
                  matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(45 * i)));
               }

               if (item.method_7976() != class_1839.field_8949) {
                  matrices.method_22905(1.2F, 1.2F, 1.2F);
               }

               if (item.method_7976() == class_1839.field_8953 && !player.method_6115()) {
                  matrices.method_22904(-0.1 * (double)i, -0.2, (double)0.0F);
               }

               if (item.method_31574(class_1802.field_49814)) {
                  matrices.method_22904(0.1 * (double)i, (double)0.0F, (double)0.0F);
                  matrices.method_22905(0.9F, 0.9F, 0.9F);
               }
            }

            if (item.method_7909() instanceof class_1747 && !(item.method_7909() instanceof class_1755) && item.method_7976() != class_1839.field_8950 && !item.method_31573(class_3489.field_15556) && !item.method_31574(class_1802.field_8276) && !item.method_31574(class_1802.field_8725) && !item.method_31574(class_1802.field_8865) && !item.method_31574(class_1802.field_8366) && !itemBlock.method_9564().method_26164(class_3481.field_15463) && !itemBlock.method_9564().method_26164(class_3481.field_22414) && !item.method_31573(class_3489.field_15553) && !itemBlock.method_9564().method_26164(class_3481.field_15503) && !itemBlock.method_9564().method_26164(class_3481.field_43170)) {
               class_1747 blockItem = (class_1747)item.method_7909();
               class_776 blockRenderManager = class_310.method_1551().method_1541();
               class_2680 blockState = blockItem.method_7711().method_9564();
               matrices.method_22903();
               if (!bl2) {
                  matrices.method_46416(-0.4F, 0.0F, 0.0F);
               }

               matrices.method_22905(0.4F, 0.4F, 0.4F);
               matrices.method_22904(-0.9 * (double)i, -0.45, (double)-0.5F);
               if (itemBlock.method_9564().method_26164(class_3481.field_15493)) {
                  matrices.method_22904(0.2 * (double)i, -0.15, -0.2);
               }

               if (itemBlock.method_9564().method_26164(class_3481.field_24076)) {
                  matrices.method_22904((double)0.0F, 0.1, (double)0.0F);
               }

               if (item.method_31574(class_1802.field_8828) || item.method_31574(class_1802.field_21086) || itemBlock.method_9564().method_26164(class_3481.field_20339) || itemBlock.method_9564().method_26164(class_3481.field_15503) || itemBlock.method_9564().method_26164(class_3481.field_15462) || itemBlock.method_9564().method_26164(class_3481.field_44469)) {
                  this.vertVelocityYSlime = (float)((double)this.vertVelocityYSlime + (double)swingProgress * 0.03 * tt);
                  if ((player.method_18798().method_1033() > 0.09 && player.method_24828() || player.method_5681() || player.method_20448() || player.method_6101() && !player.method_24828()) && (Boolean)this.field_4050.field_1690.method_42448().method_41753()) {
                     Random random = new Random();
                     boolean randomBoolean = random.nextBoolean();
                     this.vertVelocityYSlime += (float)(-0.05 * player.method_18798().method_1033() * tt);
                  }

                  matrices.method_22905(1.0F, 1.0F + this.vertAngleYSlime * -2.0F, 1.0F);
               }

               if ((float)player.field_6012 - this.prevAge >= 100.0F) {
                  this.repPower = !this.repPower;
                  this.prevAge = (float)player.field_6012;
               }

               if (blockItem.method_7711() == class_2246.field_10450 && this.repPower) {
                  blockState = (class_2680)blockState.method_11657(class_2462.field_10911, true);
               }

               if (blockItem.method_7711() == class_2246.field_10377 && this.repPower) {
                  blockState = (class_2680)blockState.method_11657(class_2286.field_10911, true);
               }

               if (blockItem.method_7711() == class_2246.field_10523 && player.method_5869()) {
                  blockState = (class_2680)blockState.method_11657(class_2459.field_11446, false);
               }

               if ((blockItem.method_7711() == class_2246.field_10036 || blockItem.method_7711() == class_2246.field_22089) && player.method_5869()) {
                  blockState = (class_2680)blockState.method_11657(class_3922.field_17352, false);
               }

               if (item.method_31573(class_3489.field_16444)) {
                  if (bl) {
                     matrices.method_22904(0.9, (double)0.0F, 0.8);
                  }

                  matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(90 * i)));
               }

               blockRenderManager.method_3353(blockState, matrices, vertexConsumers, light, class_4608.field_21444);
               matrices.method_22909();
            } else {
               if (this.isToolItem(item) && !item.method_31573(class_3489.field_41890) && !item.method_31573(class_3489.field_40109) && item.method_7976() != class_1839.field_8950 && item.method_7963() || item.method_7976() == class_1839.field_8953 || item.method_7976() == class_1839.field_27079 || this.getAttackDamage(item) != 0.0F || item.method_7976() == class_1839.field_8949 || item.method_31574(class_1802.field_23254) || item.method_31574(class_1802.field_8184) || item.method_31574(class_1802.field_8378) || item.method_31574(class_1802.field_8868)) {
                  if (item.method_31573(class_3489.field_42611)) {
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(-60.0F * swing));
                     matrices.method_22904((double)0.0F, 0.1 * (double)swing, -0.1 * (double)swing);
                  }

                  if (item.method_31573(class_3489.field_42615)) {
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(-80.0F * swing_rot));
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0F * swing));
                  } else if (item.method_7976() == class_1839.field_8951) {
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(-40.0F * swing_rot));
                     matrices.method_22904((double)0.0F, 0.1 * (double)swing_rot, -0.1 * (double)swing_rot);
                  } else if (item.method_7976() != class_1839.field_8949) {
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(-25.0F * swing));
                     matrices.method_22904((double)0.0F, 0.05 * (double)swing, -0.05 * (double)swing);
                  }
               }

               if (!item.method_31574(class_1802.field_8137) && !item.method_31574(class_1802.field_8301)) {
                  this.netherCounter = 0.0F;
               } else {
                  this.netherCounter = (float)((double)this.netherCounter + 0.9 * tt);
                  matrices.method_22904((double)0.0F, (double)0.25F + 0.02 * (double)class_3532.method_15374(this.netherCounter * 0.1F), (double)0.0F);
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(3.0F * class_3532.method_15374(this.netherCounter * 0.2F)));
                  matrices.method_22905(1.0F + 0.01F * class_3532.method_15374(this.netherCounter), 1.0F + 0.01F * class_3532.method_15374(this.netherCounter), 1.0F + 0.01F * class_3532.method_15374(this.netherCounter));
               }

               if (item.method_31573(class_3489.field_42615)) {
                  matrices.method_22904(0.07 * (double)i, (double)0.0F, 0.05);
                  matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(90 * i)));
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(-15.0F));
               }

               this.method_3233(player, item, bl2 ? class_811.field_4322 : class_811.field_4321, !bl2, matrices, vertexConsumers, light);
            }
         }

         matrices.method_22909();
         matrices.method_22909();
         this.isAttacking = this.field_4050.field_1690.field_1886.method_1434();
      }
   }

   @Unique
   private class_2248 getBlockFromItem(class_1799 stack) {
      class_1792 var3 = stack.method_7909();
      if (var3 instanceof class_1747 bi) {
         return bi.method_7711();
      } else {
         return class_2246.field_10124;
      }
   }

   @Redirect(
      method = {"method_65816"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/class_759;method_3217(Lnet/minecraft/class_4587;Lnet/minecraft/class_1306;F)V"
)
   )
   private void applySwing(class_759 instance, class_4587 matrices, class_1306 arm, float swingProgress) {
      ill__lIIl1i1i_I_lII_1I1ilIIl1 module = ill__lIIl1i1i_I_lII_1I1ilIIl1.Il_Ili___llI_iiIIiiilIll11();
      if (module == null || !module.isEnable()) {
         this.method_3217(matrices, arm, swingProgress);
      }
   }
}
