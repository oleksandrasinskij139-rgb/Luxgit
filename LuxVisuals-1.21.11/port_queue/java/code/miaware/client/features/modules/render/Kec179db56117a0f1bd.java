package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.K14caf22f676a0204f7;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import com.mojang.blaze3d.systems.RenderSystem;
import lombok.Generated;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4587;

@ModuleRegister(
   name = "Waifu",
   category = Kf76f042931183af5ba.RENDER,
   description = "Показывает тянку"
)
public class Kec179db56117a0f1bd extends Kab8a8c478f7d0077cd {
   private static final Kec179db56117a0f1bd instance = new Kec179db56117a0f1bd();
   private final Kbd66519397e9a89967 waifu = (new Kbd66519397e9a89967("Тянка")).value("Тянка 1").values("Тянка 1", "Тянка 2", "Тянка 3", "Тянка 4", "Тянка 5", "Тянка 6", "Тянка 7", "Тянка 8");
   private final Kb9e875ead00e0d03bd scale = (new Kb9e875ead00e0d03bd("Размер")).value(0.3F).range(0.2F, 1.5F).step(0.05F);
   private final Kb9e875ead00e0d03bd positionX = (new Kb9e875ead00e0d03bd("Перемещение")).value(1.0F).range(-500.0F, 20.0F).step(5.0F);
   private final Ka91715a8c4e3f3b7c4 animation = new Ka91715a8c4e3f3b7c4();
   private boolean wasGuiOpen = false;

   public Kec179db56117a0f1bd() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.waifu, this.scale, this.positionX});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 render2DEvent = K14caf22f676a0204f7.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            boolean isGuiOpen = mc.field_1755 != null;
            if (isGuiOpen && !this.wasGuiOpen) {
               this.animation.run((double)1.0F, 500L, Kf3b62ecfba0c3544e6.CUBIC_OUT);
            } else if (!isGuiOpen && this.wasGuiOpen) {
               this.animation.run((double)0.0F, 300L, Kf3b62ecfba0c3544e6.CUBIC_IN);
            }

            this.wasGuiOpen = isGuiOpen;
            this.animation.update();
            if (!(this.animation.getValue() <= 0.01)) {
               this.Kdffe948d0dcfb00a69(event.context(), event.matrixStack(), this.animation.getValue());
            }
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{render2DEvent});
   }

   private void Kdffe948d0dcfb00a69(class_332 context, class_4587 matrices, double animationValue) {
      int screenWidth = mc.method_22683().method_4486();
      int screenHeight = mc.method_22683().method_4502();
      int waifuWidth = (int)(200.0F * this.scale.getValue());
      int waifuHeight = (int)(300.0F * this.scale.getValue());
      int x = screenWidth - waifuWidth - 10 + this.positionX.getValue().intValue();
      int y = screenHeight - waifuHeight - 10;
      float offset = (float)(((double)1.0F - animationValue) * (double)waifuWidth);
      x += (int)offset;
      matrices.method_22903();
      float alpha = (float)animationValue;
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
      class_2960 texture = this.Kc2605ec0320be0a0b8();
      context.method_25290((identifier) -> class_1921.method_62277(identifier), texture, x, y, 0.0F, 0.0F, waifuWidth, waifuHeight, waifuWidth, waifuHeight);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.disableBlend();
      matrices.method_22909();
   }

   private class_2960 Kc2605ec0320be0a0b8() {
      class_2960 var10000;
      switch ((String)this.waifu.getValue()) {
         case "Тянка 2" -> var10000 = class_2960.method_60655("miaware", "textures/waifu/waifu2.png");
         case "Тянка 3" -> var10000 = class_2960.method_60655("miaware", "textures/waifu/waifu3.png");
         case "Тянка 4" -> var10000 = class_2960.method_60655("miaware", "textures/waifu/waifu4.png");
         case "Тянка 5" -> var10000 = class_2960.method_60655("miaware", "textures/waifu/waifu5.png");
         case "Тянка 6" -> var10000 = class_2960.method_60655("miaware", "textures/waifu/waifu6.png");
         case "Тянка 7" -> var10000 = class_2960.method_60655("miaware", "textures/waifu/waifu7.png");
         case "Тянка 8" -> var10000 = class_2960.method_60655("miaware", "textures/waifu/waifu8.png");
         default -> var10000 = class_2960.method_60655("miaware", "textures/waifu/waifu1.png");
      }

      return var10000;
   }

   @Generated
   public static Kec179db56117a0f1bd getInstance() {
      return instance;
   }
}
