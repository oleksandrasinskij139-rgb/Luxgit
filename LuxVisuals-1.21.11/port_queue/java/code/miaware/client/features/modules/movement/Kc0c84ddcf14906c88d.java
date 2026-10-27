package code.miaware.client.features.modules.movement;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.client.network.badge.Ka424c22578b339e00d;
import com.mojang.blaze3d.systems.RenderSystem;
import lombok.Generated;
import net.minecraft.class_2561;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_327;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_327.class_6415;
import org.joml.Matrix4f;

@ModuleRegister(
   name = "SelfNameTag",
   category = Kf76f042931183af5ba.MOVEMENT,
   description = "Показывает ваш ник в режиме F5"
)
public class Kc0c84ddcf14906c88d extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final Kc0c84ddcf14906c88d instance = new Kc0c84ddcf14906c88d();

   public void onEvent() {
      K8d7165024ff567f5e7 render3DEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && !mc.field_1690.method_31044().method_31034()) {
            this.K19307f9f2c5e878eb6(event.matrixStack(), event.partialTicks());
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{render3DEvent});
   }

   private void K19307f9f2c5e878eb6(class_4587 matrices, float partialTicks) {
      if (mc.field_1724 != null && mc.field_1773 != null) {
         class_2561 name = Ka424c22578b339e00d.decorate(mc.field_1724.method_5667(), mc.field_1724.method_7334().getName(), mc.field_1724.method_5477());
         class_327 textRenderer = mc.field_1772;
         class_4184 camera = mc.field_1773.method_19418();
         double camX = camera.method_19326().field_1352;
         double camY = camera.method_19326().field_1351;
         double camZ = camera.method_19326().field_1350;
         double x = mc.field_1724.field_6014 + (mc.field_1724.method_23317() - mc.field_1724.field_6014) * (double)partialTicks - camX;
         double y = mc.field_1724.field_6036 + (mc.field_1724.method_23318() - mc.field_1724.field_6036) * (double)partialTicks + (double)mc.field_1724.method_17682() + (double)0.5F - camY;
         double z = mc.field_1724.field_5969 + (mc.field_1724.method_23321() - mc.field_1724.field_5969) * (double)partialTicks - camZ;
         matrices.method_22903();
         matrices.method_22904(x, y, z);
         matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
         matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
         float scale = 0.025F;
         matrices.method_22905(-scale, -scale, scale);
         Matrix4f matrix = matrices.method_23760().method_23761();
         int textWidth = textRenderer.method_27525(name);
         float xOffset = (float)(-textWidth) / 2.0F;
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableDepthTest();
         class_289 tessellator = class_289.method_1348();
         class_287 buffer = tessellator.method_60827(class_5596.field_27382, class_290.field_1576);
         float padding = 2.0F;
         buffer.method_22918(matrix, xOffset - padding, -1.0F - padding, 0.0F).method_1336(0, 0, 0, 100);
         buffer.method_22918(matrix, xOffset - padding, 8.0F + padding, 0.0F).method_1336(0, 0, 0, 100);
         buffer.method_22918(matrix, xOffset + (float)textWidth + padding, 8.0F + padding, 0.0F).method_1336(0, 0, 0, 100);
         buffer.method_22918(matrix, xOffset + (float)textWidth + padding, -1.0F - padding, 0.0F).method_1336(0, 0, 0, 100);
         class_286.method_43433(buffer.method_60800());
         class_4597.class_4598 immediate = mc.method_22940().method_23000();
         textRenderer.method_27522(name, xOffset, 0.0F, -1, false, matrix, immediate, class_6415.field_33994, 0, 15728880);
         immediate.method_22993();
         RenderSystem.enableDepthTest();
         RenderSystem.disableBlend();
         matrices.method_22909();
      }
   }

   @Generated
   public static Kc0c84ddcf14906c88d getInstance() {
      return instance;
   }
}
