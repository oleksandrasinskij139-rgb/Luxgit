package code.miaware.client.features.modules.render;

import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import java.util.Objects;
import lombok.Generated;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1309;
import net.minecraft.class_1764;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_742;
import net.minecraft.class_7833;
import net.minecraft.class_811;

@ModuleRegister(
   name = "Swing Animation",
   category = Kf76f042931183af5ba.RENDER,
   description = "Изменяет анимацию взмаха руки"
)
public class Kaca9ea0013e20c91ae extends Kab8a8c478f7d0077cd {
   private static final Kaca9ea0013e20c91ae instance = new Kaca9ea0013e20c91ae();
   public final Kbd66519397e9a89967 mode = (new Kbd66519397e9a89967("Режим")).value("Взмах").values("Взмах", "Взмах назад", "Плавная старая", "Плавная новая", "Пощёчина", "DeadCode", "Нож", "Нижняя сила", "Щипок", "Стук", "Сёрфинг", "Разрушение", "Задний пир", "Ванилла");
   public final Kb9e875ead00e0d03bd strength = (new Kb9e875ead00e0d03bd("Сила")).value(20.0F).range(20.0F, 75.0F).step(0.1F).setVisible(() -> this.mode.is("Нижняя сила"));
   public final K00fef91b3d4d1b3ecc slow = (new K00fef91b3d4d1b3ecc("Медленно")).value(false);
   public final Kb9e875ead00e0d03bd speed;
   public final K00fef91b3d4d1b3ecc showBothHands;
   public final Kbd66519397e9a89967 attackAnimMode;
   public final Kb9e875ead00e0d03bd rightX;
   public final Kb9e875ead00e0d03bd rightY;
   public final Kb9e875ead00e0d03bd rightZ;
   public final Kb9e875ead00e0d03bd leftX;
   public final Kb9e875ead00e0d03bd leftY;
   public final Kb9e875ead00e0d03bd leftZ;

   public Kaca9ea0013e20c91ae() {
      Kb9e875ead00e0d03bd var10001 = (new Kb9e875ead00e0d03bd("Скорость")).value(12.0F).range(1.0F, 50.0F).step(1.0F);
      K00fef91b3d4d1b3ecc var10002 = this.slow;
      Objects.requireNonNull(var10002);
      this.speed = var10001.setVisible(var10002::getValue);
      this.showBothHands = (new K00fef91b3d4d1b3ecc("Обе руки")).value(false);
      Kbd66519397e9a89967 var1 = (new Kbd66519397e9a89967("Анимация атаки")).value("Взмахи").values("Взмахи", "Вперед", "Обычная");
      var10002 = this.showBothHands;
      Objects.requireNonNull(var10002);
      this.attackAnimMode = var1.setVisible(var10002::getValue);
      Kb9e875ead00e0d03bd var2 = (new Kb9e875ead00e0d03bd("Правая-X")).value(0.0F).range(-2.0F, 2.0F).step(0.1F);
      var10002 = this.showBothHands;
      Objects.requireNonNull(var10002);
      this.rightX = var2.setVisible(var10002::getValue);
      var2 = (new Kb9e875ead00e0d03bd("Правая-Y")).value(0.0F).range(-2.0F, 2.0F).step(0.1F);
      var10002 = this.showBothHands;
      Objects.requireNonNull(var10002);
      this.rightY = var2.setVisible(var10002::getValue);
      var2 = (new Kb9e875ead00e0d03bd("Правая-Z")).value(0.0F).range(-2.0F, 2.0F).step(0.1F);
      var10002 = this.showBothHands;
      Objects.requireNonNull(var10002);
      this.rightZ = var2.setVisible(var10002::getValue);
      var2 = (new Kb9e875ead00e0d03bd("Левая-X")).value(0.0F).range(-2.0F, 2.0F).step(0.1F);
      var10002 = this.showBothHands;
      Objects.requireNonNull(var10002);
      this.leftX = var2.setVisible(var10002::getValue);
      var2 = (new Kb9e875ead00e0d03bd("Левая-Y")).value(0.0F).range(-2.0F, 2.0F).step(0.1F);
      var10002 = this.showBothHands;
      Objects.requireNonNull(var10002);
      this.leftY = var2.setVisible(var10002::getValue);
      var2 = (new Kb9e875ead00e0d03bd("Левая-Z")).value(0.0F).range(-2.0F, 2.0F).step(0.1F);
      var10002 = this.showBothHands;
      Objects.requireNonNull(var10002);
      this.leftZ = var2.setVisible(var10002::getValue);
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.mode, this.strength, this.slow, this.speed, this.showBothHands, this.attackAnimMode, this.rightX, this.rightY, this.rightZ, this.leftX, this.leftY, this.leftZ});
   }

   public boolean useSwingAttack() {
      return this.isEnabled() && this.attackAnimMode.is("Взмахи");
   }

   public boolean useForwardAttack() {
      return this.isEnabled() && this.attackAnimMode.is("Вперед");
   }

   public boolean useNormalAttack() {
      return this.isEnabled() && this.attackAnimMode.is("Обычная");
   }

   public void onEvent() {
   }

   private void K911bf8295014f08b0a(class_4587 matrices, float swingProgress, class_1306 arm) {
      this.K3f0d98c51934df0f05(matrices, swingProgress, arm, (String)this.mode.getValue());
   }

   private void K3f0d98c51934df0f05(class_4587 matrices, float swingProgress, class_1306 arm, String animMode) {
      float g = class_3532.method_15374(class_3532.method_15355(swingProgress) * (float)Math.PI);
      float sin1 = class_3532.method_15374(swingProgress * swingProgress * (float)Math.PI);
      float sin2 = class_3532.method_15374(class_3532.method_15355(swingProgress) * (float)Math.PI);
      float amplitude = 1.0F;
      float sinSmooth = (float)(Math.sin((double)swingProgress * Math.PI) * (double)amplitude);
      float isLeft = arm == class_1306.field_6182 ? -1.0F : 1.0F;
      int i = arm == class_1306.field_6183 ? 1 : -1;
      switch (animMode) {
         case "Взмах":
            matrices.method_46416((float)i * 0.56F, -0.32F, -0.72F);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(60.0F));
            matrices.method_22907(class_7833.field_40718.rotationDegrees(-60.0F));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(sin2 * sin1 * -5.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(sin2 * sin1 * -120.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(-60.0F));
            break;
         case "Взмах назад":
            matrices.method_46416((float)i * 0.56F, -0.32F, -0.72F);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(60.0F));
            matrices.method_22907(class_7833.field_40718.rotationDegrees(-60.0F));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(sin2 * sin1 * -5.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(sin2 * sin1 * 120.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(-60.0F));
            break;
         case "Плавная старая":
            matrices.method_46416((float)i * 0.56F, -0.32F, -0.72F);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(76.0F));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(sin2 * -5.0F));
            matrices.method_22907(class_7833.field_40713.rotationDegrees(sin2 * -100.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -155.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(-100.0F));
            break;
         case "Плавная новая":
            matrices.method_46416((float)i * 0.56F, -0.52F, -0.72F);
            matrices.method_22904((double)0.0F, 0.1, (double)0.0F);
            matrices.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -80.0F));
            matrices.method_22907(class_7833.field_40718.rotationDegrees(sin2 * 45.0F));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(sin2 * 15.0F));
            matrices.method_22904((double)0.0F, -0.1, (double)0.0F);
            break;
         case "Пощёчина":
            matrices.method_46416((float)i * 0.56F, -0.52F, -0.72F);
            matrices.method_22907(class_7833.field_40713.rotationDegrees(sin2 * 55.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(sin2 * sin1 * 2.0F * -60.0F));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(sin2 * 60.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(sin2 * 55.0F));
            break;
         case "DeadCode":
            matrices.method_46416((float)i * 0.56F, -0.32F, -0.72F);
            matrices.method_22904((double)0.5F, -0.1, (double)0.0F);
            matrices.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -45.0F));
            matrices.method_22904((double)-0.5F, 0.1, (double)0.0F);
            matrices.method_22904((double)0.5F, -0.1, (double)0.0F);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(sin2 * -20.0F));
            matrices.method_22904((double)-0.5F, 0.1, (double)0.0F);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(50.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(-90.0F));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(50.0F));
            break;
         case "Нож":
            matrices.method_46416((float)i * 0.8F, -0.52F, -1.2F);
            matrices.method_22904((double)0.0F, (double)0.0F, (double)-1.5F * (double)sin2 * (double)1.0F);
            matrices.method_22907(class_7833.field_40718.rotationDegrees(0.0F));
            matrices.method_22907(class_7833.field_40717.rotationDegrees(0.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(-100.0F));
            break;
         case "Нижняя сила":
            matrices.method_46416((float)i * 0.56F, -0.32F, -0.72F);
            matrices.method_46416(-sinSmooth * sinSmooth * sin1, 0.0F, 0.0F);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(61.0F));
            matrices.method_22907(class_7833.field_40718.rotationDegrees(sin2));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(sin2 * sin1 * -5.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(sin2 * sin1 * -30.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(-60.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(sinSmooth * -60.0F));
            break;
         case "Щипок":
            matrices.method_46416((float)i * 0.56F, -0.32F, -0.72F);
            matrices.method_22904((double)0.0F, (double)0.0F, (double)-1.5F * (double)sin2 / (double)5.0F);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(80.0F));
            matrices.method_22907(class_7833.field_40717.rotationDegrees(45.0F));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(-55.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(sin2 * sin1 * 35.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(-80.0F));
            break;
         case "Стук":
            matrices.method_46416((float)i * 0.56F, -0.32F, -0.72F);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(sin2 + 55.0F));
            matrices.method_22907(class_7833.field_40718.rotationDegrees(-90.0F));
            matrices.method_22907(class_7833.field_40718.rotationDegrees(sin2 + 5.0F));
            matrices.method_22907(class_7833.field_40715.rotationDegrees(sin2 * 35.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(-135.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(sin1 + 15.0F));
            break;
         case "Сёрфинг":
            matrices.method_46416((float)i * 0.56F, -0.32F, -0.72F);
            matrices.method_22904((double)0.0F, (double)0.0F, (double)-1.5F * (double)sin2 / (double)5.0F);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(80.0F));
            matrices.method_22907(class_7833.field_40717.rotationDegrees(45.0F));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(-30.0F));
            matrices.method_22904((double)0.0F, (double)0.0F, (double)-0.5F * (double)sin2);
            matrices.method_22907(class_7833.field_40713.rotationDegrees(sin2 * -100.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -155.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(-90.0F));
            break;
         case "Разрушение":
            float translateY = sin2 * 0.2F;
            float animationSpeed = -0.1F;
            translateY += animationSpeed;
            matrices.method_22904((double)0.5F, (double)(-translateY), (double)-0.9F);
            matrices.method_22907(class_7833.field_40718.rotationDegrees(170.0F));
            matrices.method_22907(class_7833.field_40717.rotationDegrees(15.0F));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(-55.0F));
            matrices.method_22903();
            matrices.method_22907(class_7833.field_40716.rotationDegrees(-55.0F));
            matrices.method_22909();
            break;
         case "Задний пир":
            matrices.method_46416((float)i * 0.56F, -0.32F, -0.72F);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(30.0F));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(sin2 * -75.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -15.0F));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(30.0F));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(-80.0F));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(35.0F));
            break;
         case "Ванилла":
            this.K3211db400a438b294c(matrices, arm, 0.0F);
            this.K7310fcee198f4a97cd(matrices, arm, swingProgress);
            break;
         case "Вперед атака":
            matrices.method_46416((float)i * 0.56F, -0.32F, -0.72F);
            float ll = (float)i;
            float swingRot = this.Kf6f09292624402d96e(swingProgress);
            float swing = this.K3b0396e3af2ecfa44b(class_3532.method_15374(swingProgress * (float)Math.PI));
            matrices.method_22904(0.12 * (double)ll * (double)swingRot, 0.04 * (double)swingRot, -0.95 * (double)swing);
            matrices.method_22904(0.02 * (double)ll * (double)swing, 0.1 * (double)swing, -0.1 * (double)swingRot);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(8.0F * swingRot * ll));
            matrices.method_22907(class_7833.field_40713.rotationDegrees(-14.0F * swingRot));
            matrices.method_22907(class_7833.field_40718.rotationDegrees(-18.0F * swingRot * ll));
            matrices.method_22907(class_7833.field_40713.rotationDegrees(32.0F * swing));
            break;
         case "Обычная атака":
            matrices.method_46416((float)i * 0.56F, -0.32F, -0.72F);
            float ll = (float)i;
            float swingRot = this.Kf6f09292624402d96e(swingProgress);
            float swing = this.K3b0396e3af2ecfa44b(class_3532.method_15374(swingProgress * (float)Math.PI));
            matrices.method_22904(0.1 * (double)ll * (double)swingRot, 0.1 * (double)swingRot, -0.1 * (double)swing);
            matrices.method_22907(class_7833.field_40713.rotationDegrees(-30.0F * swingRot));
            matrices.method_22907(class_7833.field_40718.rotationDegrees(-10.0F * swingRot * ll));
            matrices.method_22907(class_7833.field_40713.rotationDegrees(40.0F * swing));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(10.0F * swing * ll));
            break;
         case "Взмахи атака":
            matrices.method_46416((float)i * 0.56F, -0.32F, -0.72F);
            float ll = (float)i;
            float swingRot = this.Kf6f09292624402d96e(swingProgress);
            float swing = this.K3b0396e3af2ecfa44b(class_3532.method_15374(swingProgress * (float)Math.PI));
            matrices.method_22904(0.8 * (double)ll * (double)swingRot, 0.3 * (double)swingRot, (double)-0.5F * (double)swing);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(15.0F * swingRot * ll));
            matrices.method_22907(class_7833.field_40713.rotationDegrees(-20.0F * swingRot));
            matrices.method_22907(class_7833.field_40718.rotationDegrees(-70.0F * swingRot * ll));
            matrices.method_22907(class_7833.field_40713.rotationDegrees(40.0F * swing));
      }

   }

   public void handleRenderItem(class_742 player, float tickDelta, float pitch, class_1268 hand, float swingProgress, class_1799 item, float equipProgress, class_4587 matrices, class_4597 vertexConsumers, int light) {
      if (!player.method_31550()) {
         boolean bl = hand == class_1268.field_5808;
         class_1306 arm = bl ? player.method_6068() : player.method_6068().method_5928();
         matrices.method_22903();
         if (item.method_31574(class_1802.field_8399)) {
            boolean bl2 = class_1764.method_7781(item);
            boolean bl3 = arm == class_1306.field_6183;
            int i = bl3 ? 1 : -1;
            if (player.method_6115() && player.method_6014() > 0 && player.method_6058() == hand) {
               this.K3211db400a438b294c(matrices, arm, equipProgress);
               matrices.method_46416((float)i * -0.4785682F, -0.094387F, 0.05731531F);
               matrices.method_22907(class_7833.field_40714.rotationDegrees(-11.935F));
               matrices.method_22907(class_7833.field_40716.rotationDegrees((float)i * 65.3F));
               matrices.method_22907(class_7833.field_40718.rotationDegrees((float)i * -9.785F));
               float f = (float)item.method_7935(mc.field_1724) - ((float)mc.field_1724.method_6014() - tickDelta + 1.0F);
               float g = f / (float)class_1764.method_7775(item, mc.field_1724);
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
               matrices.method_22905(1.0F, 1.0F, 1.0F + g * 0.2F);
               matrices.method_22907(class_7833.field_40715.rotationDegrees((float)i * 45.0F));
            } else {
               float fx = -0.4F * class_3532.method_15374(class_3532.method_15355(swingProgress) * (float)Math.PI);
               float gx = 0.2F * class_3532.method_15374(class_3532.method_15355(swingProgress) * ((float)Math.PI * 2F));
               float h = -0.2F * class_3532.method_15374(swingProgress * (float)Math.PI);
               matrices.method_46416((float)i * fx, gx, h);
               this.K3211db400a438b294c(matrices, arm, equipProgress);
               this.K7310fcee198f4a97cd(matrices, arm, swingProgress);
               if (bl2 && swingProgress < 0.001F && bl) {
                  matrices.method_46416((float)i * -0.641864F, 0.0F, 0.0F);
                  matrices.method_22907(class_7833.field_40716.rotationDegrees((float)i * 10.0F));
               }
            }

            this.renderItem(player, item, bl3 ? class_811.field_4322 : class_811.field_4321, !bl3, matrices, vertexConsumers, light);
         } else {
            boolean bl2 = arm == class_1306.field_6183;
            if (player.method_6115() && player.method_6014() > 0 && player.method_6058() == hand) {
               int l = bl2 ? 1 : -1;
               switch (item.method_7976()) {
                  case field_8952:
                  case field_8949:
                     this.K3211db400a438b294c(matrices, arm, equipProgress);
                     break;
                  case field_8950:
                  case field_8946:
                     this.Kd66077901cd9ce0816(matrices, tickDelta, arm, item);
                     this.K3211db400a438b294c(matrices, arm, equipProgress);
                     break;
                  case field_8953:
                     this.K3211db400a438b294c(matrices, arm, equipProgress);
                     matrices.method_46416((float)l * -0.2785682F, 0.18344387F, 0.15731531F);
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(-13.935F));
                     matrices.method_22907(class_7833.field_40716.rotationDegrees((float)l * 35.3F));
                     matrices.method_22907(class_7833.field_40718.rotationDegrees((float)l * -9.785F));
                     float mx = (float)item.method_7935(mc.field_1724) - ((float)mc.field_1724.method_6014() - tickDelta + 1.0F);
                     float fxx = mx / 20.0F;
                     fxx = (fxx * fxx + fxx * 2.0F) / 3.0F;
                     if (fxx > 1.0F) {
                        fxx = 1.0F;
                     }

                     if (fxx > 0.1F) {
                        float gx = class_3532.method_15374((mx - 0.1F) * 1.3F);
                        float h = fxx - 0.1F;
                        float j = gx * h;
                        matrices.method_46416(j * 0.0F, j * 0.004F, j * 0.0F);
                     }

                     matrices.method_46416(fxx * 0.0F, fxx * 0.0F, fxx * 0.04F);
                     matrices.method_22905(1.0F, 1.0F, 1.0F + fxx * 0.2F);
                     matrices.method_22907(class_7833.field_40715.rotationDegrees((float)l * 45.0F));
                     break;
                  case field_8951:
                     this.K3211db400a438b294c(matrices, arm, equipProgress);
                     matrices.method_46416((float)l * -0.5F, 0.7F, 0.1F);
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(-55.0F));
                     matrices.method_22907(class_7833.field_40716.rotationDegrees((float)l * 35.3F));
                     matrices.method_22907(class_7833.field_40718.rotationDegrees((float)l * -9.785F));
                     float m = (float)item.method_7935(mc.field_1724) - ((float)mc.field_1724.method_6014() - tickDelta + 1.0F);
                     float fx = m / 10.0F;
                     if (fx > 1.0F) {
                        fx = 1.0F;
                     }

                     if (fx > 0.1F) {
                        float gx = class_3532.method_15374((m - 0.1F) * 1.3F);
                        float h = fx - 0.1F;
                        float j = gx * h;
                        matrices.method_46416(j * 0.0F, j * 0.004F, j * 0.0F);
                     }

                     matrices.method_46416(0.0F, 0.0F, fx * 0.2F);
                     matrices.method_22905(1.0F, 1.0F, 1.0F + fx * 0.2F);
                     matrices.method_22907(class_7833.field_40715.rotationDegrees((float)l * 45.0F));
                     break;
                  case field_42717:
                     this.Kb781d15358db85cbf4(matrices, tickDelta, arm, item, equipProgress);
               }
            } else if (player.method_6123()) {
               this.K3211db400a438b294c(matrices, arm, equipProgress);
               int l = bl2 ? 1 : -1;
               matrices.method_46416((float)l * -0.4F, 0.8F, 0.3F);
               matrices.method_22907(class_7833.field_40716.rotationDegrees((float)l * 65.0F));
               matrices.method_22907(class_7833.field_40718.rotationDegrees((float)l * -85.0F));
            } else {
               float n = -0.4F * class_3532.method_15374(class_3532.method_15355(swingProgress) * (float)Math.PI);
               float mxx = 0.2F * class_3532.method_15374(class_3532.method_15355(swingProgress) * ((float)Math.PI * 2F));
               float fxxx = -0.2F * class_3532.method_15374(swingProgress * (float)Math.PI);
               int o = bl2 ? 1 : -1;
               matrices.method_46416((float)o * n, mxx, fxxx);
               String effectiveMode = (String)this.mode.getValue();
               if (arm == player.method_6068() && swingProgress > 0.0F && (Boolean)this.showBothHands.getValue()) {
                  if (this.useSwingAttack()) {
                     effectiveMode = "Взмахи атака";
                  } else if (this.useForwardAttack()) {
                     effectiveMode = "Вперед атака";
                  } else if (this.useNormalAttack()) {
                     effectiveMode = "Обычная атака";
                  }
               }

               if (arm == player.method_6068()) {
                  this.K3f0d98c51934df0f05(matrices, swingProgress, arm, effectiveMode);
               } else {
                  this.K3211db400a438b294c(matrices, arm, equipProgress);
                  this.K7310fcee198f4a97cd(matrices, arm, swingProgress);
               }
            }

            if (K69d319c58ab678134e.getInstance().isEnabled()) {
               if (hand.equals(class_1268.field_5808)) {
                  float rotX = K69d319c58ab678134e.getInstance().mainHandRotationX.getValue();
                  float rotY = K69d319c58ab678134e.getInstance().mainHandRotationY.getValue();
                  float rotZ = K69d319c58ab678134e.getInstance().mainHandRotationZ.getValue();
                  if (rotX != 0.0F) {
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(rotX));
                  }

                  if (rotY != 0.0F) {
                     matrices.method_22907(class_7833.field_40716.rotationDegrees(rotY));
                  }

                  if (rotZ != 0.0F) {
                     matrices.method_22907(class_7833.field_40718.rotationDegrees(rotZ));
                  }
               } else if (hand.equals(class_1268.field_5810)) {
                  float rotX = K69d319c58ab678134e.getInstance().offHandRotationX.getValue();
                  float rotY = K69d319c58ab678134e.getInstance().offHandRotationY.getValue();
                  float rotZ = K69d319c58ab678134e.getInstance().offHandRotationZ.getValue();
                  if (rotX != 0.0F) {
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(rotX));
                  }

                  if (rotY != 0.0F) {
                     matrices.method_22907(class_7833.field_40716.rotationDegrees(rotY));
                  }

                  if (rotZ != 0.0F) {
                     matrices.method_22907(class_7833.field_40718.rotationDegrees(rotZ));
                  }
               }
            }

            this.renderItem(player, item, bl2 ? class_811.field_4322 : class_811.field_4321, !bl2, matrices, vertexConsumers, light);
         }

         matrices.method_22909();
      }

   }

   private void Kb781d15358db85cbf4(class_4587 matrices, float tickDelta, class_1306 arm, class_1799 stack, float equipProgress) {
      this.K3211db400a438b294c(matrices, arm, equipProgress);
      float f = (float)(mc.field_1724.method_6014() % 10);
      float g = f - tickDelta + 1.0F;
      float h = 1.0F - g / 10.0F;
      float n = -15.0F + 75.0F * class_3532.method_15362(h * 2.0F * (float)Math.PI);
      if (arm != class_1306.field_6183) {
         matrices.method_22904(0.1, 0.83, 0.35);
         matrices.method_22907(class_7833.field_40714.rotationDegrees(-80.0F));
         matrices.method_22907(class_7833.field_40716.rotationDegrees(-90.0F));
         matrices.method_22907(class_7833.field_40714.rotationDegrees(n));
         matrices.method_22904(-0.3, 0.22, 0.35);
      } else {
         matrices.method_22904((double)-0.25F, 0.22, 0.35);
         matrices.method_22907(class_7833.field_40714.rotationDegrees(-80.0F));
         matrices.method_22907(class_7833.field_40716.rotationDegrees(90.0F));
         matrices.method_22907(class_7833.field_40718.rotationDegrees(0.0F));
         matrices.method_22907(class_7833.field_40714.rotationDegrees(n));
      }

   }

   private void Kd66077901cd9ce0816(class_4587 matrices, float tickDelta, class_1306 arm, class_1799 stack) {
      float f = (float)mc.field_1724.method_6014() - tickDelta + 1.0F;
      float g = f / (float)stack.method_7935(mc.field_1724);
      if (g < 0.8F) {
         float h = class_3532.method_15379(class_3532.method_15362(f / 4.0F * (float)Math.PI) * 0.1F);
         matrices.method_46416(0.0F, h, 0.0F);
      }

      float h = 1.0F - (float)Math.pow((double)g, (double)27.0F);
      int i = arm == class_1306.field_6183 ? 1 : -1;
      matrices.method_46416(h * 0.6F * (float)i, h * -0.5F, h * 0.0F);
      matrices.method_22907(class_7833.field_40716.rotationDegrees((float)i * h * 90.0F));
      matrices.method_22907(class_7833.field_40714.rotationDegrees(h * 10.0F));
      matrices.method_22907(class_7833.field_40718.rotationDegrees((float)i * h * 30.0F));
   }

   private void K3211db400a438b294c(class_4587 matrices, class_1306 arm, float equipProgress) {
      int i = arm == class_1306.field_6183 ? 1 : -1;
      matrices.method_46416((float)i * 0.56F, -0.52F + equipProgress * -0.6F, -0.72F);
   }

   private void K7310fcee198f4a97cd(class_4587 matrices, class_1306 arm, float swingProgress) {
      int i = arm == class_1306.field_6183 ? 1 : -1;
      float f = class_3532.method_15374(swingProgress * swingProgress * (float)Math.PI);
      float g = class_3532.method_15374(class_3532.method_15355(swingProgress) * (float)Math.PI);
      matrices.method_22907(class_7833.field_40716.rotationDegrees((float)i * (45.0F + f * -20.0F)));
      matrices.method_22907(class_7833.field_40718.rotationDegrees((float)i * g * -20.0F));
      matrices.method_22907(class_7833.field_40714.rotationDegrees(g * -80.0F));
      matrices.method_22907(class_7833.field_40716.rotationDegrees((float)i * -45.0F));
   }

   public void renderItem(class_1309 entity, class_1799 stack, class_811 renderMode, boolean leftHanded, class_4587 matrices, class_4597 vertexConsumers, int light) {
      if (!stack.method_7960()) {
         mc.method_1480().method_23177(entity, stack, renderMode, leftHanded, matrices, vertexConsumers, entity.method_37908(), light, class_4608.field_21444, entity.method_5628() + renderMode.ordinal());
      }

   }

   private float K3b0396e3af2ecfa44b(float value) {
      float c1 = 1.70158F;
      float c2 = c1 * 1.525F;
      if (value < 0.5F) {
         float doubled = 2.0F * value;
         return doubled * doubled * ((c2 + 1.0F) * doubled - c2) * 0.5F;
      } else {
         float shifted = 2.0F * value - 2.0F;
         return (shifted * shifted * ((c2 + 1.0F) * shifted + c2) + 2.0F) * 0.5F;
      }
   }

   private float Kf6f09292624402d96e(float swingProgress) {
      return swingProgress < 0.6F ? class_3532.method_15374(class_3532.method_15363(swingProgress, 0.0F, 0.12506F) * 12.56F) : class_3532.method_15374(class_3532.method_15363(swingProgress, 0.62532F, 0.75038F) * 12.56F);
   }

   @Generated
   public static Kaca9ea0013e20c91ae getInstance() {
      return instance;
   }
}
