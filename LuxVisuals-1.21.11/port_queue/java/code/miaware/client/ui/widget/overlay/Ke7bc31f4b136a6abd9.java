package code.miaware.client.ui.widget.overlay;

import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.system.client.K33c58dee118bf41a95;
import code.miaware.api.system.client.K6ac827b634fa8bac43;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.client.features.modules.render.Kfae29c687712462375;
import code.miaware.client.ui.widget.K548ca8198549ddf9d7;
import code.miaware.client.ui.widget.Ka20f8cfdbc113ccdec;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_243;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import org.joml.Matrix4f;
import org.joml.Vector3i;
import org.joml.Vector4f;

public class Ke7bc31f4b136a6abd9 extends K548ca8198549ddf9d7 {
   private boolean eventsRegistered = false;

   public Ke7bc31f4b136a6abd9() {
      super(0.0F, 0.0F);
      this.setEnabled(true);
   }

   public String getName() {
      return "Waypoints";
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      if (!this.eventsRegistered) {
         this.K57bba1cf84a030c545();
         this.eventsRegistered = true;
      }

   }

   public void render(class_4587 matrixStack) {
      if (!this.eventsRegistered) {
         this.K57bba1cf84a030c545();
         this.eventsRegistered = true;
      }

   }

   private void K1c95efb562abd02fdd(class_332 context) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         for(K6ac827b634fa8bac43 waypoint : K33c58dee118bf41a95.getInstance().getWaypoints()) {
            this.K42f1ed73ff0a53980a(context, waypoint);
         }

      }
   }

   private void K42f1ed73ff0a53980a(class_332 context, K6ac827b634fa8bac43 waypoint) {
      Vector3i pos = waypoint.getPosition();
      double waypointX = (double)pos.x + (double)0.5F;
      double waypointY = (double)pos.y + (double)0.5F;
      double waypointZ = (double)pos.z + (double)0.5F;
      double dx = waypointX - mc.field_1724.method_23317();
      double dy = waypointY - mc.field_1724.method_23318();
      double dz = waypointZ - mc.field_1724.method_23321();
      double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
      class_243 cameraPos = mc.field_1773.method_19418().method_19326();
      dx = waypointX - cameraPos.field_1352;
      dy = waypointY - cameraPos.field_1351;
      dz = waypointZ - cameraPos.field_1350;
      float pitch = mc.field_1773.method_19418().method_19329();
      float yaw = mc.field_1773.method_19418().method_19330();
      double forwardX = -Math.sin(Math.toRadians((double)yaw)) * Math.cos(Math.toRadians((double)pitch));
      double forwardY = -Math.sin(Math.toRadians((double)pitch));
      double forwardZ = Math.cos(Math.toRadians((double)yaw)) * Math.cos(Math.toRadians((double)pitch));
      double dot = dx * forwardX + dy * forwardY + dz * forwardZ;
      if (!(dot <= (double)0.0F)) {
         Vector4f clipPos = this.K06de315316bd4b265b(waypointX, waypointY, waypointZ);
         if (clipPos != null) {
            int screenWidth = mc.method_22683().method_4486();
            int screenHeight = mc.method_22683().method_4502();
            float screenX = (clipPos.x + 1.0F) * 0.5F * (float)screenWidth;
            float screenY = (1.0F - clipPos.y) * 0.5F * (float)screenHeight;
            if (!(screenX < -100.0F) && !(screenX > (float)(screenWidth + 100)) && !(screenY < -100.0F) && !(screenY > (float)(screenHeight + 100))) {
               this.K8714787a3bbc1a1c6b(context, screenX, screenY, waypoint.getName(), distance);
            }
         }
      }
   }

   private Vector4f K06de315316bd4b265b(double x, double y, double z) {
      class_243 cameraPos = mc.field_1773.method_19418().method_19326();
      class_4587 matrices = new class_4587();
      matrices.method_22904(x - cameraPos.field_1352, y - cameraPos.field_1351, z - cameraPos.field_1350);
      Vector4f pos = new Vector4f(0.0F, 0.0F, 0.0F, 1.0F);
      pos.mul(matrices.method_23760().method_23761());
      pos.mul(new Matrix4f(RenderSystem.getProjectionMatrix()));
      if (pos.w <= 0.0F) {
         return null;
      } else {
         pos.x /= pos.w;
         pos.y /= pos.w;
         pos.z /= pos.w;
         return pos;
      }
   }

   private void K8714787a3bbc1a1c6b(class_332 context, float screenX, float screenY, String name, double distance) {
      String distanceText = String.format("%.0fm", distance);
      float padding = 4.0F;
      float lineHeight = 9.0F;
      float fontSize = 8.0F;
      float distanceWidth = this.getMediumFont().getWidth(distanceText, fontSize);
      float nameWidth = this.getMediumFont().getWidth(name, fontSize);
      float maxWidth = Math.max(distanceWidth, nameWidth);
      float panelWidth = maxWidth + padding * 2.0F;
      float panelHeight = lineHeight * 2.0F + padding * 2.0F;
      float x = screenX - panelWidth / 2.0F;
      float y = screenY - panelHeight / 2.0F;
      class_4587 matrices = context.method_51448();
      matrices.method_22903();
      Color bgColor = Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.BG, 0.94F);
      Color outlineColor = Ka20f8cfdbc113ccdec.RING;
      float outlineWidth = 0.5F;
      float round = 4.0F;
      K2b5e2c5f2b9891b450.RECT.draw(matrices, x - outlineWidth, y - outlineWidth, panelWidth + outlineWidth * 2.0F, panelHeight + outlineWidth * 2.0F, round + outlineWidth, outlineColor);
      K2b5e2c5f2b9891b450.RECT.draw(matrices, x, y, panelWidth, panelHeight, round, bgColor);
      float textX = x + padding;
      float textY = y + padding;
      this.getMediumFont().drawText(matrices, distanceText, textX, textY, fontSize, Ka20f8cfdbc113ccdec.ACCENT_SOFT);
      textY += lineHeight;
      this.getMediumFont().drawText(matrices, name, textX, textY, fontSize, Ka20f8cfdbc113ccdec.TEXT);
      matrices.method_22909();
   }

   private void K57bba1cf84a030c545() {
      Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            this.render3D(event.matrixStack(), event.cameraPos());
         }

      }));
   }

   public void render3D(class_4587 matrices, class_243 cameraPos) {
      if (this.isEnabled() && Kfae29c687712462375.getInstance().isEnabled()) {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            for(K6ac827b634fa8bac43 waypoint : K33c58dee118bf41a95.getInstance().getWaypoints()) {
               this.Kcd056a4cdc8cf3a8eb(matrices, cameraPos, waypoint);
            }

         }
      }
   }

   public void mouseClicked(double mouseX, double mouseY, int button) {
   }

   public void mouseReleased(double mouseX, double mouseY, int button) {
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
   }

   public void mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
   }

   private void Kcd056a4cdc8cf3a8eb(class_4587 matrices, class_243 cameraPos, K6ac827b634fa8bac43 waypoint) {
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
      float lineHeight = 9.0F;
      float distanceWidth = (float)mc.field_1772.method_1727(distanceText);
      float nameWidth = (float)mc.field_1772.method_1727(nameText);
      float maxWidth = Math.max(distanceWidth, nameWidth);
      float panelWidth = maxWidth + padding * 2.0F;
      float panelHeight = lineHeight * 2.0F + padding * 2.0F;
      float x = -panelWidth / 2.0F;
      float y = -panelHeight / 2.0F;
      Color bgColor = Ka20f8cfdbc113ccdec.a(Ka20f8cfdbc113ccdec.BG, 0.94F);
      Color outlineColor = Ka20f8cfdbc113ccdec.RING;
      float outlineWidth = 0.5F;
      float round = 4.0F;
      K2b5e2c5f2b9891b450.RECT.draw(matrices, x - outlineWidth, y - outlineWidth, panelWidth + outlineWidth * 2.0F, panelHeight + outlineWidth * 2.0F, round + outlineWidth, outlineColor);
      K2b5e2c5f2b9891b450.RECT.draw(matrices, x, y, panelWidth, panelHeight, round, bgColor);
      float textX = x + padding;
      float textY = y + padding;
      float fontSize = 8.0F;
      this.getMediumFont().drawText(matrices, distanceText, textX, textY, fontSize, Ka20f8cfdbc113ccdec.ACCENT_SOFT);
      textY += lineHeight;
      this.getMediumFont().drawText(matrices, nameText, textX, textY, fontSize, Ka20f8cfdbc113ccdec.TEXT);
      RenderSystem.depthMask(true);
      RenderSystem.enableDepthTest();
      RenderSystem.enableCull();
      RenderSystem.disableBlend();
      matrices.method_22909();
   }
}
