package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.K14caf22f676a0204f7;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K1d3d0b4020f933282f;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.client.features.modules.render.shulkerview.Kfb1cafeb5e8b3204dd;
import lombok.Generated;
import net.minecraft.class_310;
import org.lwjgl.glfw.GLFW;

@ModuleRegister(
   name = "Shulker Viewer",
   category = Kf76f042931183af5ba.RENDER,
   description = "Показывает содержимое шалкеров при наведении и зажатии клавиши"
)
public class K6a4053265563878282 extends Kab8a8c478f7d0077cd {
   private static final K6a4053265563878282 instance = new K6a4053265563878282();
   private final K1d3d0b4020f933282f viewKey = (new K1d3d0b4020f933282f("View Key")).value(342);
   private final Kb9e875ead00e0d03bd scale = (new Kb9e875ead00e0d03bd("Scale")).value(0.5F).range(0.25F, 1.0F).step(0.05F);

   public K6a4053265563878282() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.viewKey, this.scale});
   }

   public void onEvent() {
      K8d7165024ff567f5e7 render2DEvent = K14caf22f676a0204f7.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (this.isKeyPressed()) {
            Kfb1cafeb5e8b3204dd.getInstance().renderWorldShulkerPreviews(event.context(), event.partialTicks());
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{render2DEvent});
   }

   public boolean isKeyPressed() {
      class_310 mc = class_310.method_1551();
      if (mc.method_22683() == null) {
         return false;
      } else {
         long handle = mc.method_22683().method_4490();
         int keyCode = (Integer)this.viewKey.getValue();
         return GLFW.glfwGetKey(handle, keyCode) == 1;
      }
   }

   public boolean shouldRenderScreenPreview() {
      return this.isEnabled() && this.isKeyPressed();
   }

   public float getScaleValue() {
      return this.scale.getValue();
   }

   @Generated
   public static K6a4053265563878282 getInstance() {
      return instance;
   }
}
