package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.client.K8f3417125c2e5b6c2e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.K1d3d0b4020f933282f;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import lombok.Generated;
import net.minecraft.class_3532;
import org.lwjgl.glfw.GLFW;

@ModuleRegister(
   name = "Zoom",
   category = Kf76f042931183af5ba.RENDER,
   description = "Приближение камеры"
)
public class Kc2f9539a5b5775f961 extends Kab8a8c478f7d0077cd {
   private static final Kc2f9539a5b5775f961 instance = new Kc2f9539a5b5775f961();
   private final K1d3d0b4020f933282f key = new K1d3d0b4020f933282f("Клавиша");
   public final Kb9e875ead00e0d03bd zoomLevel = (new Kb9e875ead00e0d03bd("Уровень зума")).value(3.0F).range(1.0F, 10.0F).step(0.5F);
   public final K00fef91b3d4d1b3ecc smoothZoom = (new K00fef91b3d4d1b3ecc("Плавность")).value(true);
   public final Kb9e875ead00e0d03bd smoothSpeed = (new Kb9e875ead00e0d03bd("Скорость плавности")).value(10.0F).range(2.0F, 30.0F).step(1.0F);
   public final K00fef91b3d4d1b3ecc scrollZoom = (new K00fef91b3d4d1b3ecc("Изменение зума скроллом")).value(true);
   private float currentZoom = 1.0F;

   public Kc2f9539a5b5775f961() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.key, this.zoomLevel, this.smoothZoom, this.smoothSpeed, this.scrollZoom});
   }

   public void onEnable() {
      super.onEnable();
      this.currentZoom = 1.0F;
   }

   public void onDisable() {
      this.currentZoom = 1.0F;
      super.onDisable();
   }

   public void onEvent() {
      K8d7165024ff567f5e7 mouseScrollEvent = K8f3417125c2e5b6c2e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if ((Boolean)this.scrollZoom.getValue()) {
            if (this.isZoomKeyPressed()) {
               float scrollAmount = (float)event.verticalAmount();
               float newZoom = this.zoomLevel.getValue() - scrollAmount * 0.5F;
               newZoom = class_3532.method_15363(newZoom, 1.0F, 10.0F);
               this.zoomLevel.value(newZoom);
               K8f3417125c2e5b6c2e.getInstance().setCancel(true);
            }
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{mouseScrollEvent});
   }

   public boolean isZoomKeyPressed() {
      if (this.isEnabled() && mc != null && mc.method_22683() != null) {
         return GLFW.glfwGetKey(mc.method_22683().method_4490(), (Integer)this.key.getValue()) == 1;
      } else {
         return false;
      }
   }

   public float getModifiedFov(float baseFov) {
      if (this.isEnabled() && mc != null && mc.method_22683() != null) {
         boolean isKeyPressed = this.isZoomKeyPressed();
         float targetZoom = isKeyPressed ? this.zoomLevel.getValue() : 1.0F;
         if ((Boolean)this.smoothZoom.getValue()) {
            float delta = mc.method_61966().method_60637(false);
            float smoothing = 1.0F - (float)Math.exp((double)(-delta * this.smoothSpeed.getValue() * 0.15F));
            this.currentZoom += (targetZoom - this.currentZoom) * smoothing;
         } else {
            this.currentZoom = targetZoom;
         }

         return baseFov / Math.max(0.01F, this.currentZoom);
      } else if (this.currentZoom > 1.01F) {
         float delta = mc.method_61966().method_60637(false);
         float smoothing = 1.0F - (float)Math.exp((double)(-delta * this.smoothSpeed.getValue() * 0.15F));
         this.currentZoom += (1.0F - this.currentZoom) * smoothing;
         return baseFov / this.currentZoom;
      } else {
         this.currentZoom = 1.0F;
         return baseFov;
      }
   }

   @Generated
   public static Kc2f9539a5b5775f961 getInstance() {
      return instance;
   }
}
