package code.miaware.client.features.modules.render;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Ka52d224feda41b0761;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.client.ui.clickgui.K87984c5639b98954c5;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Generated;
import net.minecraft.class_10142;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_5498;
import net.minecraft.class_638;
import net.minecraft.class_742;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

@ModuleRegister(
   name = "Blink",
   category = Kf76f042931183af5ba.RENDER,
   description = "Дух игрока   "
)
public class K06c5fe9af11c5525ae extends Kab8a8c478f7d0077cd {
   private static final K06c5fe9af11c5525ae instance = new K06c5fe9af11c5525ae();
   private static final long SNAPSHOT_COOLDOWN_MS = 500L;
   private static final long SNAPSHOT_LIFETIME_MS = 1500L;
   private static final int MAX_SNAPSHOTS_PER_PLAYER = 10;
   private static final double MOVE_TRIGGER_DISTANCE_SQ = 0.09;
   private final Kbd66519397e9a89967 trigger = (new Kbd66519397e9a89967("Триггер")).value("Всегда").values("Всегда", "Только прыжки");
   private final Kbd66519397e9a89967 show = (new Kbd66519397e9a89967("Показать")).value("Только я").values("Только я", "Все");
   private final K00fef91b3d4d1b3ecc firstPerson = (new K00fef91b3d4d1b3ecc("От первого лица")).value(false);
   private final K00fef91b3d4d1b3ecc fill = (new K00fef91b3d4d1b3ecc("Заливка")).value(true);
   private final Kbd66519397e9a89967 colorMode = (new Kbd66519397e9a89967("Цвет")).value("Тема").values("Тема", "Свой");
   private final Ka52d224feda41b0761 customColor = (new Ka52d224feda41b0761("Свой цвет")).value(new Color(255, 100, 100)).setVisible(() -> ((String)this.colorMode.getValue()).equals("Свой"));
   private final Map<UUID, List<K422ce647b761c71a03>> snapshots = new ConcurrentHashMap();
   private final Map<UUID, class_243> lastPositions = new ConcurrentHashMap();
   private final HashSet<UUID> activePlayersBuffer = new HashSet();
   private class_638 trackedWorld;

   public K06c5fe9af11c5525ae() {
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.trigger, this.show, this.firstPerson, this.fill, this.colorMode, this.customColor});
   }

   public void onEnable() {
      this.Ka6850d603371fb5d72();
      this.trackedWorld = mc.field_1687;
   }

   public void onDisable() {
      this.Ka6850d603371fb5d72();
      this.trackedWorld = null;
   }

   public void onEvent() {
      K8d7165024ff567f5e7 render3DListener = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (!K45978f900e11d6615f.getInstance().isHeavyModulesOptimized()) {
            this.onRender3D(event.matrixStack());
         }
      }));
      K8d7165024ff567f5e7 updateListener = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         if (!K45978f900e11d6615f.getInstance().isHeavyModulesOptimized()) {
            this.Kc1db9081babf0391c1();
         }
      }));
      this.addEvents(new K8d7165024ff567f5e7[]{render3DListener, updateListener});
   }

   private void Kc1db9081babf0391c1() {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         if (this.trackedWorld != mc.field_1687) {
            this.Ka6850d603371fb5d72();
            this.trackedWorld = mc.field_1687;
         }

         long now = System.currentTimeMillis();
         this.K2135388422d939c94f(now);
         this.K915af66b8f815489da(now);
      } else {
         this.Ka6850d603371fb5d72();
         this.trackedWorld = null;
      }
   }

   public void onRender3D(class_4587 matrices) {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         if (!this.snapshots.isEmpty()) {
            long now = System.currentTimeMillis();
            RenderSystem.disableDepthTest();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShader(class_10142.field_53876);

            for(Map.Entry<UUID, List<K422ce647b761c71a03>> entry : this.snapshots.entrySet()) {
               boolean isLocalPlayer = ((UUID)entry.getKey()).equals(mc.field_1724.method_5667());
               if (!isLocalPlayer || mc.field_1690.method_31044() != class_5498.field_26664 || (Boolean)this.firstPerson.getValue()) {
                  for(K422ce647b761c71a03 snapshot : (List)entry.getValue()) {
                     if (!snapshot.K27dba2e84846dc476f(now)) {
                        Color renderColor;
                        if (((String)this.colorMode.getValue()).equals("Тема")) {
                           renderColor = K87984c5639b98954c5.accent(200);
                        } else {
                           Color custom = (Color)this.customColor.getValue();
                           renderColor = new Color(custom.getRed(), custom.getGreen(), custom.getBlue(), 200);
                        }

                        this.K04228a465ecf571b48(matrices, snapshot, renderColor);
                     }
                  }
               }
            }

            RenderSystem.enableDepthTest();
            RenderSystem.disableBlend();
         }
      }
   }

   private void K2135388422d939c94f(long now) {
      HashSet<UUID> activePlayers = this.activePlayersBuffer;
      activePlayers.clear();

      for(class_742 player : mc.field_1687.method_18456()) {
         if (this.K3845911ea332392b7c(player)) {
            UUID uuid = player.method_5667();
            activePlayers.add(uuid);
            class_243 currentPos = player.method_19538();
            class_243 lastPos = (class_243)this.lastPositions.get(uuid);
            boolean triggered = ((String)this.trigger.getValue()).equals("Только прыжки") ? !player.method_24828() && player.method_18798().field_1351 > (double)0.0F : lastPos != null && currentPos.method_1025(lastPos) > 0.09;
            this.lastPositions.put(uuid, currentPos);
            if (triggered) {
               List<K422ce647b761c71a03> playerSnapshots = (List)this.snapshots.computeIfAbsent(uuid, (ignored) -> new ArrayList());
               if (playerSnapshots.isEmpty() || now - ((K422ce647b761c71a03)playerSnapshots.get(playerSnapshots.size() - 1)).createdAt >= 500L) {
                  class_243 snapshotPos = lastPos != null ? lastPos : currentPos;
                  playerSnapshots.add(K422ce647b761c71a03.K27d608350e39941171(player, snapshotPos, now));

                  while(playerSnapshots.size() > 10) {
                     playerSnapshots.remove(0);
                  }
               }
            }
         }
      }

      this.lastPositions.keySet().retainAll(activePlayers);
   }

   private void K915af66b8f815489da(long now) {
      Iterator<Map.Entry<UUID, List<K422ce647b761c71a03>>> iterator = this.snapshots.entrySet().iterator();

      while(iterator.hasNext()) {
         Map.Entry<UUID, List<K422ce647b761c71a03>> entry = (Map.Entry)iterator.next();
         ((List)entry.getValue()).removeIf((snapshot) -> snapshot.K27dba2e84846dc476f(now));
         if (((List)entry.getValue()).isEmpty()) {
            iterator.remove();
         }
      }

   }

   private boolean K3845911ea332392b7c(class_742 player) {
      if (player.method_7325()) {
         return false;
      } else {
         return player == mc.field_1724 ? true : ((String)this.show.getValue()).equals("Все");
      }
   }

   private void K04228a465ecf571b48(class_4587 matrices, K422ce647b761c71a03 snapshot, Color color) {
      class_243 camera = mc.field_1773.method_19418().method_19326();
      matrices.method_22903();
      matrices.method_22904(snapshot.position.field_1352 - camera.field_1352, snapshot.position.field_1351 - camera.field_1351, snapshot.position.field_1350 - camera.field_1350);
      matrices.method_22907(class_7833.field_40716.rotationDegrees(180.0F - snapshot.yaw));
      matrices.method_22903();
      matrices.method_46416(0.0F, 1.5F, 0.0F);
      matrices.method_22907(class_7833.field_40716.rotationDegrees(snapshot.headYaw - snapshot.yaw));
      matrices.method_22907(class_7833.field_40714.rotationDegrees(snapshot.pitch));
      this.K63d33d0d31cf342f01(matrices, 0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 0.5F, color);
      matrices.method_22909();
      this.K63d33d0d31cf342f01(matrices, 0.0F, 0.9F, 0.0F, 0.5F, 0.75F, 0.25F, color);
      matrices.method_22903();
      matrices.method_46416(-0.375F, 1.25F, 0.0F);
      matrices.method_22907(class_7833.field_40714.rotationDegrees((float)Math.toDegrees((double)snapshot.leftArmPitch)));
      matrices.method_22907(class_7833.field_40716.rotationDegrees((float)Math.toDegrees((double)snapshot.leftArmYaw)));
      matrices.method_22907(class_7833.field_40718.rotationDegrees((float)Math.toDegrees((double)snapshot.leftArmRoll)));
      this.K63d33d0d31cf342f01(matrices, 0.0F, -0.35F, 0.0F, 0.25F, 0.75F, 0.25F, color);
      matrices.method_22909();
      matrices.method_22903();
      matrices.method_46416(0.375F, 1.25F, 0.0F);
      matrices.method_22907(class_7833.field_40714.rotationDegrees((float)Math.toDegrees((double)snapshot.rightArmPitch)));
      matrices.method_22907(class_7833.field_40716.rotationDegrees((float)Math.toDegrees((double)snapshot.rightArmYaw)));
      matrices.method_22907(class_7833.field_40718.rotationDegrees((float)Math.toDegrees((double)snapshot.rightArmRoll)));
      this.K63d33d0d31cf342f01(matrices, 0.0F, -0.35F, 0.0F, 0.25F, 0.75F, 0.25F, color);
      matrices.method_22909();
      matrices.method_22903();
      matrices.method_46416(-0.125F, 0.75F, 0.0F);
      matrices.method_22907(class_7833.field_40714.rotationDegrees((float)Math.toDegrees((double)snapshot.leftLegPitch)));
      matrices.method_22907(class_7833.field_40716.rotationDegrees((float)Math.toDegrees((double)snapshot.leftLegYaw)));
      matrices.method_22907(class_7833.field_40718.rotationDegrees((float)Math.toDegrees((double)snapshot.leftLegRoll)));
      this.K63d33d0d31cf342f01(matrices, 0.0F, -0.375F, 0.0F, 0.25F, 0.75F, 0.25F, color);
      matrices.method_22909();
      matrices.method_22903();
      matrices.method_46416(0.125F, 0.75F, 0.0F);
      matrices.method_22907(class_7833.field_40714.rotationDegrees((float)Math.toDegrees((double)snapshot.rightLegPitch)));
      matrices.method_22907(class_7833.field_40716.rotationDegrees((float)Math.toDegrees((double)snapshot.rightLegYaw)));
      matrices.method_22907(class_7833.field_40718.rotationDegrees((float)Math.toDegrees((double)snapshot.rightLegRoll)));
      this.K63d33d0d31cf342f01(matrices, 0.0F, -0.375F, 0.0F, 0.25F, 0.75F, 0.25F, color);
      matrices.method_22909();
      matrices.method_22909();
   }

   private void K63d33d0d31cf342f01(class_4587 matrices, float x, float y, float z, float width, float height, float depth, Color color) {
      float halfWidth = width / 2.0F;
      float halfHeight = height / 2.0F;
      float halfDepth = depth / 2.0F;
      class_238 box = new class_238((double)(x - halfWidth), (double)(y - halfHeight), (double)(z - halfDepth), (double)(x + halfWidth), (double)(y + halfHeight), (double)(z + halfDepth));
      if ((Boolean)this.fill.getValue()) {
         Color fillColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.min(80, color.getAlpha()));
         this.K8f6b1204dc06b7269d(matrices, box, fillColor);
      }

      this.Kf9bb5bb3e8d4da331c(matrices, box, color, 1.5F);
   }

   private void K8f6b1204dc06b7269d(class_4587 matrices, class_238 box, Color color) {
      Matrix4f matrix = matrices.method_23760().method_23761();
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);
      float x1 = (float)box.field_1323;
      float y1 = (float)box.field_1322;
      float z1 = (float)box.field_1321;
      float x2 = (float)box.field_1320;
      float y2 = (float)box.field_1325;
      float z2 = (float)box.field_1324;
      this.K05dd32da41bcbb8ece(buffer, matrix, x1, y1, z1, x2, y1, z1, x2, y2, z1, x1, y2, z1, color);
      this.K05dd32da41bcbb8ece(buffer, matrix, x1, y1, z2, x1, y2, z2, x2, y2, z2, x2, y1, z2, color);
      this.K05dd32da41bcbb8ece(buffer, matrix, x1, y1, z1, x1, y2, z1, x1, y2, z2, x1, y1, z2, color);
      this.K05dd32da41bcbb8ece(buffer, matrix, x2, y1, z1, x2, y1, z2, x2, y2, z2, x2, y2, z1, color);
      this.K05dd32da41bcbb8ece(buffer, matrix, x1, y1, z1, x1, y1, z2, x2, y1, z2, x2, y1, z1, color);
      this.K05dd32da41bcbb8ece(buffer, matrix, x1, y2, z1, x2, y2, z1, x2, y2, z2, x1, y2, z2, color);
      class_286.method_43433(buffer.method_60800());
   }

   private void Kf9bb5bb3e8d4da331c(class_4587 matrices, class_238 box, Color color, float lineWidth) {
      Matrix4f matrix = matrices.method_23760().method_23761();
      RenderSystem.lineWidth(lineWidth);

      try {
         class_287 buffer = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);
         float x1 = (float)box.field_1323;
         float y1 = (float)box.field_1322;
         float z1 = (float)box.field_1321;
         float x2 = (float)box.field_1320;
         float y2 = (float)box.field_1325;
         float z2 = (float)box.field_1324;
         this.line(buffer, matrix, x1, y1, z1, x2, y1, z1, color);
         this.line(buffer, matrix, x2, y1, z1, x2, y1, z2, color);
         this.line(buffer, matrix, x2, y1, z2, x1, y1, z2, color);
         this.line(buffer, matrix, x1, y1, z2, x1, y1, z1, color);
         this.line(buffer, matrix, x1, y2, z1, x2, y2, z1, color);
         this.line(buffer, matrix, x2, y2, z1, x2, y2, z2, color);
         this.line(buffer, matrix, x2, y2, z2, x1, y2, z2, color);
         this.line(buffer, matrix, x1, y2, z2, x1, y2, z1, color);
         this.line(buffer, matrix, x1, y1, z1, x1, y2, z1, color);
         this.line(buffer, matrix, x2, y1, z1, x2, y2, z1, color);
         this.line(buffer, matrix, x1, y1, z2, x1, y2, z2, color);
         this.line(buffer, matrix, x2, y1, z2, x2, y2, z2, color);
         class_286.method_43433(buffer.method_60800());
      } finally {
         RenderSystem.lineWidth(1.0F);
      }

   }

   private void K05dd32da41bcbb8ece(class_4588 buffer, Matrix4f matrix, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4, Color color) {
      buffer.method_22918(matrix, x1, y1, z1).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
      buffer.method_22918(matrix, x2, y2, z2).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
      buffer.method_22918(matrix, x3, y3, z3).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
      buffer.method_22918(matrix, x4, y4, z4).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
   }

   private void line(class_4588 buffer, Matrix4f matrix, float x1, float y1, float z1, float x2, float y2, float z2, Color color) {
      buffer.method_22918(matrix, x1, y1, z1).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
      buffer.method_22918(matrix, x2, y2, z2).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
   }

   private void Ka6850d603371fb5d72() {
      this.snapshots.clear();
      this.lastPositions.clear();
      this.activePlayersBuffer.clear();
   }

   @Generated
   public static K06c5fe9af11c5525ae getInstance() {
      return instance;
   }
}
