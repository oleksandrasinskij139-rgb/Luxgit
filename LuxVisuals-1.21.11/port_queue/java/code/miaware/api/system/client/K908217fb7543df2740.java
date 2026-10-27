package code.miaware.api.system.client;

import code.miaware.api.system.interfaces.K4600bef438dafc1a9c;
import code.miaware.api.utils.animation.Ka91715a8c4e3f3b7c4;
import code.miaware.api.utils.animation.Kf3b62ecfba0c3544e6;
import code.miaware.api.utils.color.K80ee108ea129adac11;
import code.miaware.api.utils.color.Kb18d549c13309af9a5;
import code.miaware.api.utils.render.K2b5e2c5f2b9891b450;
import code.miaware.api.utils.render.fonts.K896065ea0a2bba4f47;
import code.miaware.client.services.Ke4708cf52c3b89f5ae;
import java.awt.Color;
import lombok.Generated;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_7833;
import org.joml.Vector2i;

public class K908217fb7543df2740 implements K4600bef438dafc1a9c {
   private static final K908217fb7543df2740 instance = new K908217fb7543df2740();
   private Vector2i gpsPosition = null;
   private Vector2i lastGpsPosition = null;
   private final Ka91715a8c4e3f3b7c4 distanceAnimation = new Ka91715a8c4e3f3b7c4();
   private final Ka91715a8c4e3f3b7c4 switchAnimation = new Ka91715a8c4e3f3b7c4();
   private final class_2960 arrow = class_2960.method_60655("luxvisuals", "textures/arrow.png");

   public void update(class_332 context) {
      if (this.gpsPosition != null) {
         this.lastGpsPosition = this.gpsPosition;
      }

      if (context != null) {
         float scale = Ke4708cf52c3b89f5ae.getInstance().getScale();
         boolean noGps = this.gpsPosition == null;
         boolean noLastGps = this.lastGpsPosition == null;
         float distance = !noLastGps ? this.K9d7d22466b9abcf69d(this.lastGpsPosition) : this.K9d7d22466b9abcf69d(new Vector2i(0, 0));
         float maxDistance = 10.0F;
         float minDistance = 3.0F;
         this.distanceAnimation.update();
         this.switchAnimation.update();
         this.distanceAnimation.run(distance <= minDistance ? (double)0.0F : (distance >= maxDistance ? (double)1.0F : (double)((distance - minDistance) / (maxDistance - minDistance))), 500L, Kf3b62ecfba0c3544e6.EXPO_OUT);
         this.switchAnimation.run(noGps ? (double)0.0F : (double)1.0F, 500L, Kf3b62ecfba0c3544e6.EXPO_OUT);
         if (!(this.distanceAnimation.getValue() < 0.1) && !(this.switchAnimation.getValue() < 0.1)) {
            float switchAnim = (float)this.switchAnimation.getValue();
            double combinedAnim = this.distanceAnimation.getValue() * (double)switchAnim;
            float targetX = noGps ? 0.0F : (float)this.lastGpsPosition.x;
            float targetY = noGps ? 0.0F : (float)this.lastGpsPosition.y;
            double playerYaw = (double)mc.field_1724.method_36454();
            double cos = Math.cos(Math.toRadians(playerYaw));
            double sin = Math.sin(Math.toRadians(playerYaw));
            double radius = (double)75.0F;
            double xWay = ((double)targetX - mc.field_1724.method_19538().field_1352) * 0.01;
            double zWay = ((double)targetY - mc.field_1724.method_19538().field_1350) * 0.01;
            double rotationY = -(zWay * cos - xWay * sin);
            double rotationX = -(xWay * cos + zWay * sin);
            double angle = Math.toDegrees(Math.atan2(rotationY, rotationX));
            double centerX = (double)mc.method_22683().method_4486() / (double)2.0F;
            double centerY = (double)mc.method_22683().method_4502() / (double)2.0F;
            double x = radius * Math.cos(Math.toRadians(angle)) + centerX;
            double y = radius * Math.sin(Math.toRadians(angle)) + centerY;
            context.method_51448().method_22903();
            context.method_51448().method_22904(x, y, (double)0.0F);
            context.method_51448().method_22907(class_7833.field_40718.rotationDegrees((float)angle + 90.0F));
            float arrowSize = 8.0F * scale;
            Color color = K80ee108ea129adac11.setAlpha(Kb18d549c13309af9a5.gradient((int)combinedAnim), (int)((double)255.0F * combinedAnim));
            int textureId = mc.method_1531().method_4619(this.arrow).method_4624();
            K2b5e2c5f2b9891b450.TEXTURE_RECT.draw(context.method_51448(), -arrowSize, -arrowSize, arrowSize * 2.0F, arrowSize * 2.0F, 0.0F, color, 0.0F, 0.0F, 1.0F, 1.0F, textureId);
            context.method_51448().method_22909();
            K896065ea0a2bba4f47.PS_BOLD.drawCenteredText(context.method_51448(), String.format("%.1f", distance) + "m", (float)x, (float)y + arrowSize * 2.0F, 8.0F * scale, Kb18d549c13309af9a5.textColor((int)((double)255.0F * combinedAnim)));
         }
      }
   }

   private float K9d7d22466b9abcf69d(Vector2i targetVec) {
      double x = mc.field_1724.method_19538().field_1352 - (double)targetVec.x;
      double z = mc.field_1724.method_19538().field_1350 - (double)targetVec.y;
      return class_3532.method_15355((float)(x * x + z * z));
   }

   @Generated
   public Vector2i getGpsPosition() {
      return this.gpsPosition;
   }

   @Generated
   public Vector2i getLastGpsPosition() {
      return this.lastGpsPosition;
   }

   @Generated
   public Ka91715a8c4e3f3b7c4 getDistanceAnimation() {
      return this.distanceAnimation;
   }

   @Generated
   public Ka91715a8c4e3f3b7c4 getSwitchAnimation() {
      return this.switchAnimation;
   }

   @Generated
   public class_2960 getArrow() {
      return this.arrow;
   }

   @Generated
   public void setGpsPosition(Vector2i gpsPosition) {
      this.gpsPosition = gpsPosition;
   }

   @Generated
   public void setLastGpsPosition(Vector2i lastGpsPosition) {
      this.lastGpsPosition = lastGpsPosition;
   }

   @Generated
   public static K908217fb7543df2740 getInstance() {
      return instance;
   }
}
