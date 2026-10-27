package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.system.client.K33c58dee118bf41a95;
import code.miaware.api.system.client.K6ac827b634fa8bac43;
import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import lombok.Generated;
import net.minecraft.class_243;
import net.minecraft.class_4587;
import net.minecraft.class_327.class_6415;
import org.joml.Vector3i;

@ModuleRegister(
   name = "Waypoints",
   category = Kf76f042931183af5ba.RENDER,
   description = "Текстовые метки для waypoints в мире"
)
public class K3c7165dabd6caf193e extends Kab8a8c478f7d0077cd implements K4600bef438dafc1a9c {
   private static final K3c7165dabd6caf193e instance = new K3c7165dabd6caf193e();

   public K3c7165dabd6caf193e() {
      this.setEnabled(true);
   }

   public void onEvent() {
      K8d7165024ff567f5e7 render3DEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1687 != null && mc.field_1724 != null) {
            if (!K33c58dee118bf41a95.getInstance().getWaypoints().isEmpty()) {
               class_4587 matrices = event.matrixStack();
               class_243 camera = event.cameraPos();

               for(K6ac827b634fa8bac43 waypoint : K33c58dee118bf41a95.getInstance().getWaypoints()) {
                  this.K42a0c19ed42ba80220(waypoint, matrices, camera);
               }

            }
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{render3DEvent});
   }

   private void K42a0c19ed42ba80220(K6ac827b634fa8bac43 waypoint, class_4587 matrices, class_243 cameraPos) {
      Vector3i pos = waypoint.getPosition();
      double waypointX = (double)pos.x + (double)0.5F;
      double waypointY = (double)pos.y;
      double waypointZ = (double)pos.z + (double)0.5F;
      double distance = Math.sqrt(Math.pow(waypointX - mc.field_1724.method_23317(), (double)2.0F) + Math.pow(waypointY - mc.field_1724.method_23318(), (double)2.0F) + Math.pow(waypointZ - mc.field_1724.method_23321(), (double)2.0F));
      double renderX;
      double renderY;
      double renderZ;
      if (distance > (double)500.0F) {
         double dx = waypointX - cameraPos.field_1352;
         double dy = waypointY - cameraPos.field_1351;
         double dz = waypointZ - cameraPos.field_1350;
         double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
         dx = dx / len * (double)500.0F;
         dy = dy / len * (double)500.0F;
         dz = dz / len * (double)500.0F;
         renderX = cameraPos.field_1352 + dx;
         renderY = cameraPos.field_1351 + dy;
         renderZ = cameraPos.field_1350 + dz;
      } else {
         renderX = waypointX;
         renderY = waypointY;
         renderZ = waypointZ;
      }

      matrices.method_22903();
      matrices.method_22904(renderX - cameraPos.field_1352, renderY - cameraPos.field_1351, renderZ - cameraPos.field_1350);
      matrices.method_22907(mc.field_1773.method_19418().method_23767());
      matrices.method_22905(-1.0F, 1.0F, 1.0F);
      float baseScale = 0.06F;
      float scale;
      if (distance > (double)1000.0F) {
         scale = baseScale * 25.0F;
      } else if (distance > (double)600.0F) {
         scale = baseScale * ((float)distance / 30.0F);
      } else if (distance > (double)300.0F) {
         scale = baseScale * ((float)distance / 35.0F);
      } else if (distance > (double)150.0F) {
         scale = baseScale * ((float)distance / 40.0F);
      } else if (distance > (double)80.0F) {
         scale = baseScale * ((float)distance / 45.0F);
      } else if (distance > (double)40.0F) {
         scale = baseScale * ((float)distance / 25.0F);
      } else if (distance > (double)20.0F) {
         scale = baseScale * 2.0F;
      } else {
         scale = baseScale * 2.5F;
      }

      matrices.method_22905(-scale, -scale, scale);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableDepthTest();
      RenderSystem.disableCull();
      RenderSystem.depthMask(false);
      String distanceText = String.format("%.0fm", distance);
      String nameText = waypoint.getName();
      float padding = 4.0F;
      float lineHeight = 10.0F;
      float textWidth1 = (float)mc.field_1772.method_1727(distanceText);
      float textWidth2 = (float)mc.field_1772.method_1727(nameText);
      float maxWidth = Math.max(textWidth1, textWidth2);
      float panelWidth = maxWidth + padding * 2.0F;
      float panelHeight = lineHeight * 2.0F + padding * 2.0F;
      float x = -panelWidth / 2.0F;
      float y = -panelHeight / 2.0F;
      K2b5e2c5f2b9891b450.RECT.draw(matrices, x - 1.0F, y - 1.0F, panelWidth + 2.0F, panelHeight + 2.0F, 3.0F, new Color(80, 80, 90, 200));
      K2b5e2c5f2b9891b450.BLUR_RECT.draw(matrices, x, y, panelWidth, panelHeight, 2.0F, new Color(30, 30, 36, 230));
      float textX = x + padding;
      float textY = y + padding;
      mc.field_1772.method_27521(distanceText, textX, textY, (new Color(80, 255, 80, 255)).getRGB(), false, matrices.method_23760().method_23761(), mc.method_22940().method_23000(), class_6415.field_33993, 0, 15728880);
      textY += lineHeight;
      mc.field_1772.method_27521(nameText, textX, textY, (new Color(255, 255, 255, 255)).getRGB(), false, matrices.method_23760().method_23761(), mc.method_22940().method_23000(), class_6415.field_33993, 0, 15728880);
      mc.method_22940().method_23000().method_22993();
      RenderSystem.depthMask(true);
      RenderSystem.enableDepthTest();
      RenderSystem.enableCull();
      RenderSystem.disableBlend();
      matrices.method_22909();
   }

   @Generated
   public static K3c7165dabd6caf193e getInstance() {
      return instance;
   }
}
