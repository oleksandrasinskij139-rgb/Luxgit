package code.miaware.client.features.modules.player;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.api.utils.math.Kf39dc27225f4d9fe6e;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;
import lombok.Generated;
import net.minecraft.class_10142;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1531;
import net.minecraft.class_1542;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_4587;
import net.minecraft.class_239.class_240;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

@ModuleRegister(
   name = "Little Bro",
   category = Kf76f042931183af5ba.PLAYER,
   description = "Летающий за вами бро"
)
public class K2050dbfe56d4d85122 extends Kab8a8c478f7d0077cd {
   private static final K2050dbfe56d4d85122 instance = new K2050dbfe56d4d85122();
   private final Kbd66519397e9a89967 texture = (new Kbd66519397e9a89967("Тип")).value("Робот 1").values("Робот 1", "Робот 2", "Робот 3", "Робот 4", "Пинку", "Мистер Аксолотль", "Мистер Свин", "Военный Свин", "КуниВей", "Дракончик");
   private class_243 position;
   private class_243 destination;
   private float yaw;
   private float pitch;
   private final Kf39dc27225f4d9fe6e idleTimer;
   private final Kf39dc27225f4d9fe6e waitTimer;
   private class_1297 targetEntity;
   private K43574a1675939f8f92 currentState;

   public K2050dbfe56d4d85122() {
      this.position = class_243.field_1353;
      this.destination = class_243.field_1353;
      this.yaw = 0.0F;
      this.pitch = 0.0F;
      this.idleTimer = new Kf39dc27225f4d9fe6e();
      this.waitTimer = new Kf39dc27225f4d9fe6e();
      this.targetEntity = null;
      this.currentState = K43574a1675939f8f92.IDLE;
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.texture});
   }

   public String getSuffix() {
      return (String)this.texture.getValue();
   }

   public void onEnable() {
      super.onEnable();
      if (mc.field_1724 != null) {
         this.position = mc.field_1724.method_19538().method_1031((double)0.0F, (double)1.5F, (double)0.0F);
         this.destination = mc.field_1724.method_19538().method_1031((double)0.0F, (double)1.5F, (double)0.0F);
         this.yaw = mc.field_1724.method_36454();
         this.pitch = 0.0F;
      }

      this.idleTimer.reset();
      this.waitTimer.reset();
      this.targetEntity = null;
      this.currentState = K43574a1675939f8f92.IDLE;
   }

   public void onEvent() {
      K8d7165024ff567f5e7 render3DEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            this.Ka5af7fbe5cac013a00();
            this.K382bbccc4975a884f1();
            class_4587 matrices = event.matrixStack();
            class_243 camera = mc.field_1773.method_19418().method_19326();
            matrices.method_22903();
            float x = (float)(this.position.field_1352 - camera.field_1352);
            float y = (float)(this.position.field_1351 - camera.field_1351);
            float z = (float)(this.position.field_1350 - camera.field_1350);
            matrices.method_46416(x, y, z);
            matrices.method_22907((new Quaternionf()).rotateY((float)Math.toRadians((double)(-this.yaw))));
            matrices.method_22907((new Quaternionf()).rotateX((float)Math.toRadians((double)this.pitch)));
            float size = 0.25F;
            this.Ka83e1f13ce9f676e27(matrices, size);
            matrices.method_22909();
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{render3DEvent});
   }

   private void Ka5af7fbe5cac013a00() {
      List<class_1297> nearbyEntities = StreamSupport.stream(mc.field_1687.method_18112().spliterator(), false).filter((entity) -> entity != mc.field_1724).filter((entity) -> !(entity instanceof class_1531)).filter((entity) -> entity instanceof class_1309 || entity instanceof class_1542).filter((entity) -> mc.field_1724.method_5858(entity) <= (double)100.0F).filter(this::K814375e97724216e51).sorted(Comparator.comparingDouble((entity) -> mc.field_1724.method_5858(entity))).toList();
      if (this.waitTimer.finished(1000L) || mc.field_1724.method_5649(this.position.field_1352, this.position.field_1351, this.position.field_1350) > (double)100.0F) {
         this.targetEntity = null;
         this.currentState = K43574a1675939f8f92.IDLE;
         this.waitTimer.reset();
      }

      if (this.currentState == K43574a1675939f8f92.IDLE && !nearbyEntities.isEmpty()) {
         this.targetEntity = (class_1297)nearbyEntities.get(0);
         this.currentState = K43574a1675939f8f92.MOVE_TO_ENTITY;
         this.waitTimer.reset();
      }

      switch (this.currentState.ordinal()) {
         case 0:
            this.Ke3fdd328db6ef44382();
            break;
         case 1:
            if (this.targetEntity != null && this.targetEntity.method_5805()) {
               double x = this.targetEntity.method_23317();
               double y = this.targetEntity.method_23318();
               double z = this.targetEntity.method_23321();
               double distance = (double)0.5F;
               double yawRad = Math.toRadians((double)this.targetEntity.method_36454());
               double xOffset = -Math.sin(yawRad) * distance;
               double zOffset = Math.cos(yawRad) * distance;
               this.destination = new class_243(x + xOffset, y + (double)(this.targetEntity.method_17682() / 2.0F), z + zOffset);
               this.waitTimer.reset();
            } else {
               this.targetEntity = null;
               this.currentState = K43574a1675939f8f92.IDLE;
            }
      }

   }

   private void Ke3fdd328db6ef44382() {
      if (this.idleTimer.finished(2000L)) {
         double distance = (double)5.0F;
         double yawRad = Math.toRadians((double)this.yaw + (Math.random() - (double)0.5F) * (double)180.0F);
         double xOffset = -Math.sin(yawRad) * distance;
         double zOffset = Math.cos(yawRad) * distance;
         double x = this.position.field_1352 + xOffset;
         double y = this.position.field_1351 + (Math.random() - (double)0.5F) * (double)0.25F * (double)10.0F;
         double z = this.position.field_1350 + zOffset;
         double playerX = mc.field_1724.method_23317();
         double playerY = mc.field_1724.method_23318();
         double playerZ = mc.field_1724.method_23321();
         double squareDistance = mc.field_1724.method_5649(x, y, z);
         boolean yCheck = y < playerY - (double)0.25F;
         double range = yCheck ? (double)3.0F : (double)10.0F;
         if (squareDistance > range * range || yCheck) {
            yawRad = Math.toRadians((double)(mc.field_1724.method_36454() + 180.0F));
            xOffset = -Math.sin(yawRad) * range;
            zOffset = Math.cos(yawRad) * range;
            x = this.position.field_1352 + xOffset;
            y = playerY + Math.random() * (double)(mc.field_1724.method_17682() / 2.0F);
            z = this.position.field_1350 + zOffset;
         }

         x += (Math.random() - (double)0.5F) * (double)0.5F;
         z += (Math.random() - (double)0.5F) * (double)0.5F;
         double newSquareDistance = mc.field_1724.method_5649(x, y, z);
         if (newSquareDistance <= range * range) {
            this.destination = new class_243(x, y, z);
         } else {
            x = playerX + xOffset;
            y = playerY + Math.random() * (double)(mc.field_1724.method_17682() / 2.0F);
            z = playerZ + zOffset;
            x += (Math.random() - (double)0.5F) * (double)0.5F;
            z += (Math.random() - (double)0.5F) * (double)0.5F;
            this.destination = new class_243(x, y, z);
         }

         this.idleTimer.reset();
      }

   }

   private void K382bbccc4975a884f1() {
      this.Ka27a96b8bce676e712();
      double speed = 0.1;
      double deltaX = this.destination.field_1352 - this.position.field_1352;
      double deltaY = this.destination.field_1351 - this.position.field_1351;
      double deltaZ = this.destination.field_1350 - this.position.field_1350;
      this.position = new class_243(this.Ka11e0e7b7713ce64ec(this.position.field_1352, this.position.field_1352 + deltaX * speed, 0.05), this.Ka11e0e7b7713ce64ec(this.position.field_1351, this.position.field_1351 + deltaY * speed, 0.05), this.Ka11e0e7b7713ce64ec(this.position.field_1350, this.position.field_1350 + deltaZ * speed, 0.05));
   }

   private double Ka11e0e7b7713ce64ec(double start, double end, double delta) {
      return start + (end - start) * delta;
   }

   private void Ka27a96b8bce676e712() {
      float targetYaw = this.Kd3710a1b395718b2c0(this.destination.field_1352, this.destination.field_1350);
      float targetPitch = this.Kef8546ff6af26aa302(this.destination.field_1352, this.destination.field_1351, this.destination.field_1350);
      float yawDiff = class_3532.method_15393(targetYaw - this.yaw);
      float pitchDiff = targetPitch - this.pitch;
      float yawStep = 10.0F;
      if (yawDiff > yawStep) {
         this.yaw = (float)this.Ka11e0e7b7713ce64ec((double)this.yaw, (double)(this.yaw + yawStep), 0.1);
      } else if (yawDiff < -yawStep) {
         this.yaw = (float)this.Ka11e0e7b7713ce64ec((double)this.yaw, (double)(this.yaw - yawStep), 0.1);
      } else {
         this.yaw = targetYaw % 360.0F;
      }

      this.pitch = (float)this.Ka11e0e7b7713ce64ec((double)this.pitch, (double)(this.pitch + pitchDiff * 0.1F), 0.1);
      this.pitch = class_3532.method_15363(this.pitch, -90.0F, 90.0F);
   }

   private float Kd3710a1b395718b2c0(double x, double z) {
      return (float)(Math.toDegrees(Math.atan2(z - this.position.field_1350, x - this.position.field_1352)) - (double)90.0F);
   }

   private float Kef8546ff6af26aa302(double x, double y, double z) {
      double xDist = x - this.position.field_1352;
      double yDist = y - this.position.field_1351;
      double zDist = z - this.position.field_1350;
      double horizontalDist = Math.sqrt(xDist * xDist + zDist * zDist);
      return (float)(-Math.toDegrees(Math.atan2(yDist, horizontalDist)));
   }

   private boolean K814375e97724216e51(class_1297 entity) {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         class_243 start = mc.field_1724.method_33571();
         class_243 end = entity.method_19538().method_1031((double)0.0F, (double)(entity.method_17682() / 2.0F), (double)0.0F);
         class_3965 hitResult = mc.field_1687.method_17742(new class_3959(start, end, class_3960.field_17558, class_242.field_1348, mc.field_1724));
         return hitResult.method_17783() == class_240.field_1333;
      } else {
         return false;
      }
   }

   private void Ka83e1f13ce9f676e27(class_4587 matrices, float size) {
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(770, 771);
      RenderSystem.disableCull();
      RenderSystem.depthMask(true);
      RenderSystem.setShader(class_10142.field_53880);
      class_2960 petTexture = this.Kad1f4efeb804ff85bc();
      RenderSystem.setShaderTexture(0, petTexture);
      matrices.method_22903();
      float s = size / 2.0F;
      matrices.method_46416(-s, -s, -s);
      Matrix4f matrix = matrices.method_23760().method_23761();
      class_289 tessellator = class_289.method_1348();
      class_287 buffer = tessellator.method_60827(class_5596.field_27382, class_290.field_1575);
      float u1 = 0.125F;
      float v1 = 0.125F;
      float u2 = 0.25F;
      float v2 = 0.25F;
      buffer.method_22918(matrix, 0.0F, size, size).method_22913(u1, v2).method_1336(255, 255, 255, 255);
      buffer.method_22918(matrix, size, size, size).method_22913(u2, v2).method_1336(255, 255, 255, 255);
      buffer.method_22918(matrix, size, 0.0F, size).method_22913(u2, v1).method_1336(255, 255, 255, 255);
      buffer.method_22918(matrix, 0.0F, 0.0F, size).method_22913(u1, v1).method_1336(255, 255, 255, 255);
      u1 = 0.375F;
      v1 = 0.125F;
      u2 = 0.5F;
      v2 = 0.25F;
      buffer.method_22918(matrix, 0.0F, size, 0.0F).method_22913(u1, v2).method_1336(255, 255, 255, 255);
      buffer.method_22918(matrix, size, size, 0.0F).method_22913(u2, v2).method_1336(255, 255, 255, 255);
      buffer.method_22918(matrix, size, 0.0F, 0.0F).method_22913(u2, v1).method_1336(255, 255, 255, 255);
      buffer.method_22918(matrix, 0.0F, 0.0F, 0.0F).method_22913(u1, v1).method_1336(255, 255, 255, 255);
      u1 = 0.0F;
      v1 = 0.125F;
      u2 = 0.125F;
      v2 = 0.25F;
      buffer.method_22918(matrix, 0.0F, size, 0.0F).method_22913(u1, v2).method_1336(255, 255, 255, 255);
      buffer.method_22918(matrix, 0.0F, size, size).method_22913(u2, v2).method_1336(255, 255, 255, 255);
      buffer.method_22918(matrix, 0.0F, 0.0F, size).method_22913(u2, v1).method_1336(255, 255, 255, 255);
      buffer.method_22918(matrix, 0.0F, 0.0F, 0.0F).method_22913(u1, v1).method_1336(255, 255, 255, 255);
      u1 = 0.25F;
      v1 = 0.125F;
      u2 = 0.375F;
      v2 = 0.25F;
      buffer.method_22918(matrix, size, size, size).method_22913(u1, v2).method_1336(255, 255, 255, 255);
      buffer.method_22918(matrix, size, size, 0.0F).method_22913(u2, v2).method_1336(255, 255, 255, 255);
      buffer.method_22918(matrix, size, 0.0F, 0.0F).method_22913(u2, v1).method_1336(255, 255, 255, 255);
      buffer.method_22918(matrix, size, 0.0F, size).method_22913(u1, v1).method_1336(255, 255, 255, 255);
      u1 = 0.125F;
      v1 = 0.0F;
      u2 = 0.25F;
      v2 = 0.125F;
      buffer.method_22918(matrix, 0.0F, size, 0.0F).method_22913(u1, v2).method_1336(255, 255, 255, 255);
      buffer.method_22918(matrix, size, size, 0.0F).method_22913(u2, v2).method_1336(255, 255, 255, 255);
      buffer.method_22918(matrix, size, size, size).method_22913(u2, v1).method_1336(255, 255, 255, 255);
      buffer.method_22918(matrix, 0.0F, size, size).method_22913(u1, v1).method_1336(255, 255, 255, 255);
      u1 = 0.25F;
      v1 = 0.0F;
      u2 = 0.375F;
      v2 = 0.125F;
      buffer.method_22918(matrix, 0.0F, 0.0F, size).method_22913(u1, v2).method_1336(255, 255, 255, 255);
      buffer.method_22918(matrix, size, 0.0F, size).method_22913(u2, v2).method_1336(255, 255, 255, 255);
      buffer.method_22918(matrix, size, 0.0F, 0.0F).method_22913(u2, v1).method_1336(255, 255, 255, 255);
      buffer.method_22918(matrix, 0.0F, 0.0F, 0.0F).method_22913(u1, v1).method_1336(255, 255, 255, 255);
      class_286.method_43433(buffer.method_60800());
      matrices.method_22909();
      RenderSystem.enableCull();
      RenderSystem.disableBlend();
   }

   private class_2960 Kad1f4efeb804ff85bc() {
      String var10000;
      switch ((String)this.texture.getValue()) {
         case "Робот 2" -> var10000 = "type_2";
         case "Робот 3" -> var10000 = "type_3";
         case "Робот 4" -> var10000 = "type_4";
         case "Пинку" -> var10000 = "type_5";
         case "Мистер Аксолотль" -> var10000 = "type_6";
         case "Мистер Свин" -> var10000 = "type_7";
         case "Военный Свин" -> var10000 = "type_8";
         case "КуниВей" -> var10000 = "type_9";
         case "Дракончик" -> var10000 = "textures/entity/end_crystal/end_crystal_beam";
         default -> var10000 = "type_1";
      }

      String type = var10000;
      return ((String)this.texture.getValue()).equals("Дракончик") ? class_2960.method_60656("textures/entity/enderdragon/dragon") : class_2960.method_60655("luxvisuals", "textures/pet/" + type + ".png");
   }

   @Generated
   public static K2050dbfe56d4d85122 getInstance() {
      return instance;
   }
}
