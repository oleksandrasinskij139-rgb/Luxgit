package code.miaware.client.features.modules.render;

import java.awt.Color;
import net.minecraft.class_10055;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_3532;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_591;
import net.minecraft.class_7833;
import net.minecraft.class_327.class_6415;

public class Kd3584eececd8e92a34 extends class_3887<class_10055, class_591> {
   private static final String HEART = "❤";
   private static final class_2561 HEART_TEXT = class_2561.method_43470("❤");
   private static final int EMPTY_HEART_COLOR = 1711276032;
   private static final float HEART_SCALE = 0.025F;
   private static final float HEART_GAP = 2.0F;
   private static final float BELOW_ARMOR_OFFSET = 9.0F;

   public Kd3584eececd8e92a34(class_3883<class_10055, class_591> context) {
      super(context);
   }

   public void render(class_4587 matrices, class_4597 vertexConsumers, int light, class_10055 renderState, float limbAngle, float limbDistance) {
      K983de48a5ef1d9f665 module = K983de48a5ef1d9f665.getInstance();
      if (module != null && module.isEnabled()) {
         class_310 mc = class_310.method_1551();
         if (mc.field_1687 != null) {
            class_1297 entity = mc.field_1687.method_8469(renderState.field_53528);
            if (entity instanceof class_1309) {
               class_1309 livingEntity = (class_1309)entity;
               if (livingEntity.method_5805()) {
                  if (!livingEntity.method_5767() && (mc.field_1724 == null || !livingEntity.method_5756(mc.field_1724))) {
                     float maxHealth = livingEntity.method_6063();
                     if (maxHealth <= 0.0F) {
                        return;
                     }

                     float health = class_3532.method_15363(livingEntity.method_6032(), 0.0F, maxHealth);
                     float hearts = health / 2.0F;
                     int maxHearts = class_3532.method_15340(class_3532.method_15386(maxHealth / 2.0F), 1, 20);
                     float y = 0.0F;
                     if (renderState.field_53338 != null) {
                        y = (float)(-renderState.field_53338.field_1351);
                     } else {
                        y = -renderState.field_53330;
                     }

                     matrices.method_22903();
                     matrices.method_46416(0.0F, y, 0.02F);
                     matrices.method_22907(class_7833.field_40716.rotationDegrees(-renderState.field_53446));
                     matrices.method_22907(class_7833.field_40716.rotationDegrees(-Kd03e12bb2d1f64c43b((double)mc.field_1773.method_19418().method_19330())));
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(-mc.field_1773.method_19418().method_19329()));
                     matrices.method_22905(0.025F, 0.025F, 0.025F);
                     class_327 textRenderer = mc.field_1772;
                     int fillColor = ((Color)module.getColor().getValue()).getRGB();
                     int baseAlpha = fillColor >>> 24 & 255;
                     float heartWidth = (float)textRenderer.method_27525(HEART_TEXT);
                     float heartStep = heartWidth + 2.0F;
                     float totalWidth = maxHearts > 0 ? (float)maxHearts * heartWidth + (float)(maxHearts - 1) * 2.0F : 0.0F;
                     float startX = -totalWidth * 0.5F;

                     for(int i = 0; i < maxHearts; ++i) {
                        float drawX = startX + (float)i * heartStep;
                        textRenderer.method_27522(HEART_TEXT, drawX, 9.0F, 1711276032, false, matrices.method_23760().method_23761(), vertexConsumers, class_6415.field_33993, 0, light);
                        float remaining = hearts - (float)i;
                        if (!(remaining <= 0.0F)) {
                           int color = fillColor;
                           if (remaining < 1.0F) {
                              int scaledAlpha = Math.max(1, Math.round((float)baseAlpha * remaining));
                              color = fillColor & 16777215 | scaledAlpha << 24;
                           }

                           textRenderer.method_27522(HEART_TEXT, drawX, 9.0F, color, false, matrices.method_23760().method_23761(), vertexConsumers, class_6415.field_33993, 0, light);
                        }
                     }

                     matrices.method_22909();
                     return;
                  }

                  return;
               }
            }

         }
      }
   }

   private static float Kd03e12bb2d1f64c43b(double yaw) {
      yaw = -Math.toRadians(yaw);
      yaw += Math.PI;
      if (yaw > Math.PI) {
         yaw -= (Math.PI * 2D);
      }

      if (yaw < -Math.PI) {
         yaw += (Math.PI * 2D);
      }

      return (float)Math.toDegrees(yaw);
   }
}
